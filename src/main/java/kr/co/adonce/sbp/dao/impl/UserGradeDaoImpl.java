package kr.co.adonce.sbp.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.adonce.sbp.dao.IUserGradeDao;
import kr.co.adonce.sbp.dao.model.UserGrade;
import open.commons.Result;
import open.commons.function.SQLBiFunction;
import open.commons.utils.SQLUtils;

@Repository(UserGradeDaoImpl.BEAN_QUALIFIER)
public class UserGradeDaoImpl extends PostgreDbGenericDaoImpl implements IUserGradeDao {

	public static final String BEAN_QUALIFIER = "kr.co.adonce.sbp.dao.impl.UserGradeDaoImpl";

	private static final SQLBiFunction<ResultSet, Integer, UserGrade> CreateUserGrade = (rs, rowNum) -> {
		return SQLUtils.newInstance(UserGrade.class, rs);
	};

	public UserGradeDaoImpl() {
	}

	private void fillUserGrade(UserGrade grade, ResultSet rs) throws SQLException {
		grade.setId(rs.getInt("id"));
		grade.setName(rs.getString("name"));
		grade.setDescr(rs.getString("descr"));
	}

	@Override
	public Result<UserGrade> getUserGrade(int gradeId) {
		String query = getQuery("userGradeDao.select.by.id");

		if (logger.isDebugEnabled()) {
			logger.debug("SQL: " + query);
			logger.info("Parameter: " + String.join(", ", String.valueOf(gradeId)));
		}

		return getObject(query, CreateUserGrade, gradeId);
	}

	@Override
	public Result<List<UserGrade>> selectAll() {
		String sql = getQuery("userGradeDao.select.all");

		return getObjectList(sql, new Object[] {}, new open.commons.database.IRowMapperSetter<UserGrade>() {

			@Override
			public UserGrade set(ResultSet rs, int rowNum) throws SQLException {

				UserGrade userGrade = new UserGrade();

				fillUserGrade(userGrade, rs);

				return userGrade;
			}
		});
	}

}
