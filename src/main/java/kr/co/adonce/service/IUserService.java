package kr.co.adonce.service;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import kr.co.adonce.controller.model.CommonSearchReq;
import kr.co.adonce.dao.model.User;
import kr.co.adonce.dao.model.UserGrade;
import kr.co.adonce.pagination.PaginationResult;
import open.commons.Result;

public interface IUserService {

	/**
	 * 사용자 권한 목록 제공
	 * 
	 * @return
	 *
	 */
	public Result<List<UserGrade>> getGradesAll();

	/**
	 * 비밀번호 확인
	 * 
	 * @param userObj
	 * @return
	 *
	 */
	public Result<Integer> checkPassword(User user);

	/**
	 * 회원가입
	 * 
	 * @param userInfo 회원가입할 사용자 정보
	 * @return
	 *
	 */
	public Result<Integer> registUser(User userInfo);

	/**
	 * 사용자 조회
	 * 
	 * @param userID   사용자 ID
	 * @param password 사용자 비밀번호
	 * @return
	 *
	 */
	public Result<User> select(String userID, String password);

	/**
	 * 전체 사용자 조회
	 * 
	 * @param cmReqEntity
	 * 
	 * @return
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws InvalidAlgorithmParameterException
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 *
	 */
	public Result<PaginationResult<User>> selectAll(CommonSearchReq cmReqEntity)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;

	/**
	 * 토큰 ID에 대한 사용자 정보(ID)조회
	 * 
	 * @param token 토큰 ID
	 * @return
	 *
	 */
	public Result<User> selectUserIdByToken(String token);

	/**
	 * 사용자 권한을 수정한다.
	 * 
	 * @param user
	 * @return
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws InvalidAlgorithmParameterException
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 * @throws InvalidKeyException
	 *
	 */
	public Result<Integer> updateUserGrade(User user)
			throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;

	/**
	 * ID에 대한 사용자 정보 조회
	 * 
	 * @param id
	 * @return
	 *
	 */
	public Result<User> selectUserById(String id);

	/**
	 * 사용자 정보를 수정한다.
	 * 
	 * @param user 사용자 정보
	 * @return
	 *
	 */
	public Result<Integer> modifyUserInfo(User user);

	/**
	 * 사용자 비밀번호를 초기화 한다.
	 * 
	 * @param user 사용자 정보
	 * @return
	 *
	 */
	public Result<String> initPassword(User user);

}
