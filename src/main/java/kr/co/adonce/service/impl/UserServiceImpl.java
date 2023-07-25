package kr.co.adonce.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import kr.co.adonce.controller.model.CommonSearchReq;
import kr.co.adonce.dao.IUserDao;
import kr.co.adonce.dao.IUserGradeDao;
import kr.co.adonce.dao.impl.UserDaoImpl;
import kr.co.adonce.dao.impl.UserGradeDaoImpl;
import kr.co.adonce.dao.model.User;
import kr.co.adonce.dao.model.UserGrade;
import kr.co.adonce.pagination.PaginationResult;
import kr.co.adonce.service.IUserService;
import kr.co.adonce.util.CommonUtils;
import kr.co.adonce.util.EncryptUtils;
import open.commons.Result;

@Service(UserServiceImpl.BEAN_QUALIFIER)
public class UserServiceImpl extends GenericServiceImpl implements IUserService {

	public static final String BEAN_QUALIFIER = "kr.re.etri.iot.fcp.pcl.service.impl.UserServiceImpl";

	@Autowired
	@Qualifier(UserGradeDaoImpl.BEAN_QUALIFIER)
	private IUserGradeDao userGradeDao;

	@Autowired
	@Qualifier(UserDaoImpl.BEAN_QUALIFIER)
	private IUserDao userDao;

	public UserServiceImpl() {
	}

	@Override
	public Result<List<UserGrade>> getGradesAll() {
		return userGradeDao.selectAll();
	}

	@Override
	public Result<Integer> checkPassword(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<Integer> registUser(User userInfo) {
		return userDao.insert(userInfo);
	}

	@Override
	public Result<User> select(String userID, String password) {

		Result<User> result = new Result<>();

		Result<User> userResult = userDao.selectByUserIdAndPassword(userID, password);
		if (userResult.getResult() && userResult.getData() != null) {

			User user = userResult.getData();

			Result<UserGrade> gradeResult = userGradeDao.getUserGrade(user.getGrade());
			UserGrade grade = gradeResult.getData();

			user.setUserGradeObj(grade);

			result.andTrue().setData(user);

			if (logger.isDebugEnabled()) {
				logger.debug(userResult.getData());
			}
		} else {
			result.setMessage("해당 ID/PASSWORD에 존재하는 사용자가 없습니다." + userResult.getMessage());
		}

		return result;
	}

	@Override
	public Result<PaginationResult<User>> selectAll(CommonSearchReq cmReqEntity)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		// 전체 결과를 위한 result
		Result<PaginationResult<User>> result = new Result<>();
		Result<List<User>> getUsersResult = userDao.selectAll(cmReqEntity);
		if (!getUsersResult.getResult()) {
			result.setMessage("사용자 목록을 불러올 수 없습니다.");
			return result;
		}

		List<User> userList = getUsersResult.getData();

		String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();

		// #1. 사용자 정보의 ID 를 암호화 해서 저장해야함
		for (User user : userList) {
			user.setId(EncryptUtils.encrypt(sessionId, user.getId()));
		}
		// 페이지네이션 처리
		PaginationResult<User> pResult = CommonUtils.paginate(userList, cmReqEntity.getPageNum(),
				cmReqEntity.getItemCountPerPage());

		result.andTrue().setData(pResult);
		return result;
	}

	@Override
	public Result<User> selectUserIdByToken(String token) {
		return userDao.selectUserIDByToken(token);
	}

	@Override
	public Result<Integer> updateUserGrade(User user)
			throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

		Result<Integer> result = new Result<>();
		// key: 세션 ID
		String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
		String userId = EncryptUtils.decrypt(sessionId, user.getId());

		result = userDao.updateUserGrade(userId, user.getGrade());
		if (!result.getResult()) {
			result.setMessage("사용자 권한을 수정할 수 없습니다.");
		}

		return result;
	}

	@Override
	public Result<User> selectUserById(String id) {

		return userDao.selectByUserId(id);
	}

	@Override
	public Result<Integer> modifyUserInfo(User user) {
		// 전체 결과를 위한 result
		Result<Integer> result = new Result<>();

		// #1. 비밀번호를 확인한다.
		// id에 대한 비밀번호를 가진 사용자가 있는지 확인한다.
		Result<User> checkPwdResult = userDao.selectByUserIdAndPassword(user.getId(), user.getCurrentPassword());
		if (!checkPwdResult.getResult()) {
			result.setMessage("사용자 정보를 조회할 수 없습니다.");
			return result;
		}
		// #2. 조회했는데 데이터가 존재하지 않으면, 비밀번호가 일치하지 않는 것
		if (checkPwdResult.getData() == null) {
			result.setMessage("사용자 ID와 비밀번호가 일치하는 데이터가 존재하지 않습니다.");
			return result;
		}
		// #3. 비밀번호가 일치하면 데이터 수정 요청
		Result<Integer> userInfoModifyResult = userDao.modify(user);
		if (!userInfoModifyResult.getResult()) {
			result.setMessage("사용자 정보 수정에 실패하였습니다.");
			return result;
		}

		result.andTrue();
		return result;
	}

	@Override
	public Result<String> initPassword(User user) {
		// 전체 결과를 위한 result
		Result<String> result = new Result<>();

		// #1. 사용자 정보가 있는지 확인한다.
		Result<User> userResult = userDao.selectByUserInfo(user.getId(), user.getName(), user.getPhone());
		if (!userResult.getResult()) {
			result.setMessage("사용자 정보를 가져올 수 없습니다.");
			return result;
		}
		// #2-1. 없으면 메세지 설정 후 return
		if (userResult.getData() == null) {
			result.setMessage("사용자 정보가 존재하지 않습니다.");
			return result;
		}
		// #2-2. 있으면 비밀번호를 수정
		// 임시 비밀번호 생성
		String tempPassword = makeTempPassword();
		// #3. 비밀번호를 임시비밀번호로 수정
		Result<Integer> updatePasswordResult = userDao.modifyPassword(user.getId(), tempPassword);
		if (!updatePasswordResult.getResult()) {
			result.setMessage("사용자 비밀번호 초기화에 실패하였습니다.");
			return result;
		}

		result.andTrue().setData(tempPassword);
		return result;
	}

	/**
	 * 임시 비밀번호 생성
	 *
	 * @return
	 */
	private String makeTempPassword() {
		// 임시 비밀번호 생성
		String tempPw = UUID.randomUUID().toString();
		// 8자리로 만들어줌
		tempPw = tempPw.substring(0, 8);
		return tempPw;
	}

}
