package kr.co.adonce.sbp.dao;

import java.util.List;

import kr.co.adonce.sbp.controller.model.CommonSearchReq;
import kr.co.adonce.sbp.dao.model.User;
import open.commons.Result;

public interface IUserDao {

	/**
	 * 회원 가입
	 * 
	 * @param userInfo 회원가입할 사용자 정보
	 * @return
	 *
	 */
	public Result<Integer> insert(User userInfo);

	/**
	 * 전체 사용자 목록을 조회한다.
	 * 
	 * @param cmReqEntity TODO
	 * 
	 * @return
	 *
	 */
	public Result<List<User>> selectAll(CommonSearchReq cmReqEntity);

	/**
	 * ID & 비밀번호 일치하는 사용자를 조회한다.
	 * 
	 * @param userID   사용자 ID
	 * @param password 사용자 비밀번호
	 * @return
	 *
	 */
	public Result<User> selectByUserIdAndPassword(String userID, String password);

	/**
	 * 사용자 ID에 해당하는 사용자를 조회한다.
	 * 
	 * @param userId 사용자 ID
	 * @return
	 *
	 */
	public Result<User> selectByUserId(String userId);

	/**
	 * 토큰 ID에 대한 사용자 정보(ID) 조회
	 * 
	 * @param token 토큰 ID
	 * @return
	 *
	 */
	public Result<User> selectUserIDByToken(String token);

	/**
	 * 사용자 권한을 수정한다.
	 * 
	 * @param id
	 * @param grade
	 * @return
	 *
	 */
	public Result<Integer> updateUserGrade(String id, int grade);

	/**
	 * 사용자 정보를 수정한다.
	 * 
	 * @param user 사용자 정보
	 * @return
	 *
	 */
	public Result<Integer> modify(User user);

	/**
	 * 사용자 정보에 대한 사용자 조회
	 * 
	 * @param id    사용자 ID
	 * @param name  사용자 이름
	 * @param phone 사용자 연락처
	 * @return
	 *
	 */
	public Result<User> selectByUserInfo(String id, String name, String phone);

	/**
	 * 사용자 비밀번호를 수정한다.
	 * 
	 * @param id           사용자 ID
	 * @param tempPassword 사용자 임시 비밀번호
	 * @return
	 *
	 */
	public Result<Integer> modifyPassword(String id, String tempPassword);

}
