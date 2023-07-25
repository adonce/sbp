package kr.co.adonce.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.adonce.controller.model.CommonSearchReq;
import kr.co.adonce.dao.IUserDao;
import kr.co.adonce.dao.model.User;
import open.commons.Result;
import open.commons.database.IConnectionCallbackSetter;
import open.commons.function.SQLBiFunction;
import open.commons.text.NamedTemplate;
import open.commons.utils.SQLUtils;

@Repository(UserDaoImpl.BEAN_QUALIFIER)
public class UserDaoImpl extends PostgreDbGenericDaoImpl implements IUserDao {

	public static final String BEAN_QUALIFIER = "kr.re.etri.iot.fcp.pcl.dao.impl.UserDaoImpl";

	private static final SQLBiFunction<ResultSet, Integer, User> CreateUser = (rs, rowNum) -> {
		return SQLUtils.newInstance(User.class, rs);
	};

	public UserDaoImpl() {

	}

	@Override
	public Result<User> selectByUserIdAndPassword(String userID, String password) {
		String query = getQuery("userDao.select.by.id_password");

		if (logger.isDebugEnabled()) {
			logger.debug("SQL: " + query);
			logger.info("Parameter: " + String.join(", ", userID, password));
		}

		return getObject(query, CreateUser, userID, password);
	}

	@Override
	public Result<Integer> insert(User userInfo) {
		String query = getQuery("userDao.insert");

		if (logger.isDebugEnabled()) {
			logger.debug("SQL: " + query);
			logger.info("Parameter: " + String.join(" ,", userInfo.getId(), userInfo.getName(), userInfo.getPhone(),
					userInfo.getCompName(), userInfo.getCompAddress(), String.valueOf(userInfo.getRank()),
					String.valueOf(userInfo.getBirth()), userInfo.getTask()));
		}

		return execute(query, new IConnectionCallbackSetter() {

			@Override
			public void set(PreparedStatement stmt) throws SQLException {
				int i = 1;
				stmt.setString(i++, userInfo.getId());
				stmt.setString(i++, userInfo.getPassword());
				stmt.setString(i++, userInfo.getName());
				stmt.setInt(i++, userInfo.getGrade());
				stmt.setString(i++, userInfo.getPhone());
				stmt.setString(i++, userInfo.getCompName());
				stmt.setString(i++, userInfo.getDeptName());
				stmt.setString(i++, userInfo.getCompAddress());
				stmt.setLong(i++, userInfo.getBirth());
				stmt.setString(i++, userInfo.getRank());
				stmt.setString(i++, userInfo.getTask());
			}
		});
	}

	@Override
	public Result<List<User>> selectAll(CommonSearchReq cmReqEntity) {
		String query = getQuery("userDao.select.all");

		NamedTemplate nt = new NamedTemplate(query);

		List<Object> params = new ArrayList<>();

		if ("".equals(cmReqEntity.getKeyword())) {
			nt.addValue("where_clause", "");
		} else {
			params.add("%" + cmReqEntity.getKeyword() + "%");
			nt.addValue("where_clause", "AND name like ?");
		}

		nt.addValue("order_column", cmReqEntity.getOrderColumn());
		nt.addValue("is_desc", cmReqEntity.isDesc() ? "DESC" : "ASC");

		query = nt.format();

		if (logger.isDebugEnabled()) {
			logger.debug("SQL: " + query);
		}

		return getObjectList(query, CreateUser, params);
	}

	@Override
	public Result<User> selectByUserId(String userId) {
		String query = getQuery("userDao.select.by.id");

		if (logger.isDebugEnabled()) {
			logger.debug("SQL: " + query);
		}

		return getObject(query, CreateUser, userId);
	}

	@Override
	public Result<User> selectByUserInfo(String id, String name, String phone) {
		String query = getQuery("userDao.select.by.info");

		if (logger.isDebugEnabled()) {
			logger.debug("SQL: " + query);
			logger.debug("Parameter: " + String.join(", ", id, name, phone));
		}

		return getObject(query, CreateUser, id, name, phone);
	}

	@Override
	public Result<User> selectUserIDByToken(String token) {
		String query = getQuery("userDao.select.user_id.by.token_id");

		if (logger.isDebugEnabled()) {
			logger.debug(String.format("[PlatformDaoImpl- getUserIDByToken] token: %s, query: %s", token, query));
		}

		return getObject(query, CreateUser, token);
	}

	@Override
	public Result<Integer> modify(User user) {
		String query = getQuery("userDao.update.user");

		if (logger.isDebugEnabled()) {
			logger.debug("SQL: " + query);
			logger.debug("Parameters: ", String.join(" ,", user.getPhone(), user.getCompName(), user.getDeptName(),
					user.getCompAddress(), user.getTask(), String.valueOf(user.getBirth()), user.getRank()));
		}

		return execute(query, new IConnectionCallbackSetter() {

			@Override
			public void set(PreparedStatement stmt) throws SQLException {
				int i = 1;
				stmt.setString(i++, user.getPassword());
				stmt.setString(i++, user.getPhone());
				stmt.setString(i++, user.getCompName());
				stmt.setString(i++, user.getDeptName());
				stmt.setString(i++, user.getCompAddress());
				stmt.setString(i++, user.getTask());
				stmt.setLong(i++, user.getBirth());
				stmt.setString(i++, user.getRank());
				stmt.setString(i++, user.getId());
			}
		});
	}

	@Override
	public Result<Integer> updateUserGrade(String id, int grade) {
		String query = getQuery("userDao.update.user_grade");

		if (logger.isDebugEnabled()) {
			logger.debug("SQL: " + query);
		}

		return execute(query, new IConnectionCallbackSetter() {

			@Override
			public void set(PreparedStatement stmt) throws SQLException {
				int i = 1;
				stmt.setInt(i++, grade);
				stmt.setString(i++, id);
			}
		});
	}

	@Override
	public Result<Integer> modifyPassword(String id, String tempPassword) {
		String query = getQuery("userDao.update.password");

		if (logger.isDebugEnabled()) {
			logger.debug("SQL: " + query);
			logger.debug("Paramenter: " + String.join(",", id, tempPassword));
		}

		return execute(query, new IConnectionCallbackSetter() {

			@Override
			public void set(PreparedStatement stmt) throws SQLException {
				int i = 1;
				stmt.setString(i++, tempPassword);
				stmt.setString(i++, id);
			}
		});
	}

}
