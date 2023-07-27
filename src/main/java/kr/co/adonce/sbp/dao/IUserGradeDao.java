package kr.co.adonce.sbp.dao;

import java.util.List;

import kr.co.adonce.sbp.dao.model.UserGrade;
import open.commons.Result;

public interface IUserGradeDao {

	/**
	 * 사용자의 ROLE을 반환한다.
	 * 
	 * @param gradeId
	 * @return
	 */
	public Result<UserGrade> getUserGrade(int gradeId);

	/**
	 * 모든 ROLE을 반환한다.
	 * 
	 * @return
	 */
	public Result<List<UserGrade>> selectAll();

}
