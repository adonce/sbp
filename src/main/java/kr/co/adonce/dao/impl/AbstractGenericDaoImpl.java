/*
 *
 * This file is generated under this project, "com.lguplus.items.capacity".
 *
 * Date  : 2017. 12. 29. 오후 5:15:35
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package kr.co.adonce.dao.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiFunction;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.ConnectionProxy;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.nativejdbc.NativeJdbcExtractor;

import kr.co.adonce.dao.DefaultConnectionCallback;
import kr.co.adonce.dao.IGenericDao;
import open.commons.Result;
import open.commons.database.ConnectionCallbackBroker;
import open.commons.database.IConnectionCallbackSetter;
import open.commons.database.IRowMapperSetter;
import open.commons.function.SQLBiFunction;

/**
 * 
 * @since 2018. 3. 14.
 * @author jhlee
 *
 */
public abstract class AbstractGenericDaoImpl implements IGenericDao {

	protected Logger logger = LogManager.getLogger(getClass());

	protected DataSource dataSource;

	protected JdbcTemplate jdbcTemplate;

	protected ReloadableResourceBundleMessageSource querySource;

	public AbstractGenericDaoImpl() {

	}

	public void afterPropertiesSet() throws Exception {
	}

	@Override
	public void destroy() throws Exception {

	}

	/**
	 * 
	 * @param sqlIn
	 * @param paramCount 파라미터 개수
	 * @param emptyValue 파라미터가 없는 경우 사용될 기본값.
	 * @since 2014. 9. 10.
	 */
	public void appendInClause(StringBuffer sqlIn, int paramCount, String emptyValue) {
		sqlIn.append(" (");

		if (paramCount > 0) {
			sqlIn.append('?');
			int i = 1;
			for (; i < paramCount; i++) {
				sqlIn.append(',');
				sqlIn.append(' ');
				sqlIn.append('?');
			}
		} else {
			sqlIn.append(emptyValue);
		}
		sqlIn.append(')');
	}

	/**
	 * 다중 쿼리를 처리한다. <br>
	 * 
	 * <pre>
	 * [개정이력]
	 *      날짜    	| 작성자	|	내용
	 * ------------------------------------------
	 * 2018. 3. 14.		  jhlee		최초 작성
	 * </pre>
	 * 
	 * @param brokers
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("null")
	public Result<Integer> execute(Collection<ConnectionCallbackBroker> brokers) {

		Result<Integer> result = new Result<>();

		Connection con = DataSourceUtils.getConnection(getDataSource());
		Connection conToWork = null;

		JdbcTemplate jdbcTemplate = getJdbcTemplate();
		NativeJdbcExtractor nativeJdbcExtractor = jdbcTemplate.getNativeJdbcExtractor();

		DefaultConnectionCallback action = null;

		try {

			con.setAutoCommit(false);

			if (nativeJdbcExtractor != null) {
				conToWork = nativeJdbcExtractor.getNativeConnection(con);
			} else {
				conToWork = (Connection) Proxy.newProxyInstance(ConnectionProxy.class.getClassLoader(),
						new Class<?>[] { ConnectionProxy.class },
						new CloseSuppressingInvocationHandler(con, jdbcTemplate));
			}

			conToWork.setAutoCommit(false);
			int inserted = 0;
			for (ConnectionCallbackBroker broker : brokers) {
				action = new DefaultConnectionCallback(broker);

				inserted += action.doInConnection(conToWork);
			}

			conToWork.close();
			result.andTrue().setData(inserted);

		} catch (SQLException e) {

			logger.warn("다중 쿼리 실행 실패", e);

			result.setMessage(e.getLocalizedMessage());

			try {

				con.rollback();

			} catch (SQLException ignored) {
				logger.error("SQL Exception.");
			}

			DataAccessException dae = jdbcTemplate.getExceptionTranslator().translate("ConnectionCallback",
					(action != null ? action.getBroker().getQuery() : null), e);

			throw new IllegalArgumentException("ConnectionCallback IllegalArgument Exception.", dae);

		} finally {
			try {
				if (con != null) {
					con.commit();
				}

				if (conToWork != null) {
					conToWork.close();
				}

			} catch (SQLException ignored) {
				logger.error("SQL Exception.");
			}

			DataSourceUtils.releaseConnection(con, dataSource);
			con = null;
			conToWork = null;
		}

		return result;
	}

	/**
	 * 
	 * <br>
	 * 
	 * <pre>
	 * [개정이력]
	 *      날짜    	| 작성자	|	내용
	 * ------------------------------------------
	 * 2018. 3. 14.		  jhlee		최초 작성
	 * </pre>
	 * 
	 * @param brokers
	 * @return
	 * @throws SQLException
	 */
	public Result<Integer> execute(ConnectionCallbackBroker... brokers) {
		return execute(Arrays.asList(brokers));
	}

	/**
	 * 
	 * <br>
	 * 
	 * <pre>
	 * [개정이력]
	 *      날짜    	| 작성자	|	내용
	 * ------------------------------------------
	 * 2018. 3. 14.		  jhlee		최초 작성
	 * </pre>
	 * 
	 * @param sql    {@link PreparedStatement}가 생성된 후 조건절 생성
	 * @param setter
	 * @return
	 */
	protected Result<Integer> execute(String sql, IConnectionCallbackSetter setter) {

		Result<Integer> result = new Result<Integer>();

		try {
			int count = getJdbcTemplate().execute(new ConnectionCallback<Integer>() {
				@Override
				public Integer doInConnection(Connection con) throws SQLException, DataAccessException {
					int count = 0;
					PreparedStatement stmt = null;
					try {
						stmt = con.prepareStatement(sql);

						if (setter != null) {
							setter.set(stmt);
						}

						count = stmt.executeUpdate();
					} catch (Exception e) {
						result.setMessage(e.getLocalizedMessage());

						logger.warn(e.getLocalizedMessage(), e);

						throw e;
					} finally {
						if (stmt != null) {
							stmt.close();
						}
					}

					return count;
				}
			});

			result.setData(count);
			if (count > 0) {
				result.setMessage("OK");
			} else if (result.getMessage() == null) {
				result.setMessage("변경된 데이타가 없습니다.");
			}

			result.setResult(true);
			// result.setResult(count > 0);

		} catch (DataAccessException e) {
			logger.warn("query: " + sql + e);

			result.setData(0);
			result.setMessage(e.getLocalizedMessage());
		}

		return result;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public JdbcTemplate getJdbcTemplate() {
		if (jdbcTemplate == null) {
			jdbcTemplate = new JdbcTemplate(dataSource);
		}

		return jdbcTemplate;
	}

	protected <T> Result<T> getObject(String sql, BiFunction<ResultSet, Integer, T> setter, Object... params) {
		return getObject(sql, params, setter);
	}

	protected <T> Result<T> getObject(String sql, Object[] params, BiFunction<ResultSet, Integer, T> setter) {
		Result<T> result = new Result<T>();

		try {
			T object = queryForObject(sql, params, setter);

			result.setData(object);
			result.setMessage(object != null ? "OK" : "검색결과가 없습니다.");
			result.setResult(true);
		} catch (EmptyResultDataAccessException e) {
			logger.warn("SELECT 한 결과가 0개 입니다. query: " + sql + ", Exception: " + e.getLocalizedMessage());

			result.setMessage(e.getLocalizedMessage());

		} catch (Exception e) {
			logger.warn("query: " + sql, e);

			result.setMessage(e.getLocalizedMessage());
		}

		return result;
	}

	/**
	 * 하나의 튜플만 반환한다. <br>
	 * 
	 * <pre>
	 * [개정이력]
	 *      날짜    	| 작성자	|	내용
	 * ------------------------------------------
	 * 2018. 3. 14.		  jhlee		최초 작성
	 * </pre>
	 * 
	 * @param sql
	 * @param params
	 * @param setter
	 * @return
	 */
	protected <T> Result<T> getObject(String sql, Object[] params, IRowMapperSetter<T> setter) {
		Result<T> result = new Result<T>();

		try {
			T object = queryForObject(sql, params, setter);

			result.setData(object);
			result.setMessage(object != null ? "OK" : "검색결과가 없습니다.");
			result.setResult(true);
		} catch (EmptyResultDataAccessException e) {
			logger.warn("SELECT 한 결과가 0개 입니다. query: " + sql + ", Exception: " + e.getLocalizedMessage());

			result.setMessage(e.getLocalizedMessage());

		} catch (Exception e) {
			logger.warn("query: " + sql, e);

			result.setMessage(e.getLocalizedMessage());
		}

		return result;
	}

	protected <T> Result<T> getObject(String sql, Object[] params, SQLBiFunction<ResultSet, Integer, T> setter) {
		Result<T> result = new Result<T>();

		try {
			T object = queryForObject(sql, params, setter);

			result.setData(object);
			result.setMessage(object != null ? "OK" : "검색결과가 없습니다.");
			result.setResult(true);
		} catch (EmptyResultDataAccessException e) {
			logger.warn("SELECT 한 결과가 0개 입니다. query: " + sql + ", Exception: " + e.getLocalizedMessage());

			result.setResult(true);
			result.setMessage(e.getLocalizedMessage());

		} catch (Exception e) {
			logger.warn("query: " + sql, e);

			result.setMessage(e.getLocalizedMessage());
		}

		return result;
	}

	protected <T> Result<T> getObject(String sql, SQLBiFunction<ResultSet, Integer, T> setter, List<Object> params) {
		return getObject(sql, params.toArray(new Object[] {}), setter);
	}

	protected <T> Result<T> getObject(String sql, SQLBiFunction<ResultSet, Integer, T> setter, Object... params) {
		return getObject(sql, params, setter);
	}

	protected <T> Result<List<T>> getObjectList(String sql, BiFunction<ResultSet, Integer, T> setter,
			List<Object> params) {
		return getObjectList(sql, params.toArray(new Object[] {}), setter);
	}

	protected <T> Result<List<T>> getObjectList(String sql, BiFunction<ResultSet, Integer, T> setter,
			Object... params) {
		return getObjectList(sql, params, setter);
	}

	protected <T> Result<List<T>> getObjectList(String sql, Object[] params, BiFunction<ResultSet, Integer, T> setter) {
		Result<List<T>> result = new Result<List<T>>();

		try {
			List<T> objects = query(sql, params, setter);

			result.setData(objects);
			result.setResult(objects != null);

		} catch (EmptyResultDataAccessException e) {
			logger.warn("SELECT 한 결과가 0개 입니다. query: " + sql, e);

			result.setMessage(e.getLocalizedMessage());
			result.setResult(true);

		} catch (Exception e) {
			logger.warn("query: " + sql, e);

			result.setMessage(e.getLocalizedMessage());
		}

		return result;
	}

	/**
	 * 하나의 튜플만 반환한다. <br>
	 * 
	 * <pre>
	 * [개정이력]
	 *      날짜    	| 작성자	|	내용
	 * ------------------------------------------
	 * 2018. 3. 14.		  jhlee		최초 작성
	 * </pre>
	 * 
	 * @param sql
	 * @param params
	 * @param setter
	 * @return
	 */
	protected <T> Result<List<T>> getObjectList(String sql, Object[] params, IRowMapperSetter<T> setter) {
		Result<List<T>> result = new Result<List<T>>();

		try {
			List<T> objects = query(sql, params, setter);

			result.setData(objects);
			result.setResult(objects != null);

		} catch (EmptyResultDataAccessException e) {
			logger.warn("SELECT 한 결과가 0개 입니다. query: " + sql, e);

			result.setMessage(e.getLocalizedMessage());
			result.setResult(true);

		} catch (Exception e) {
			logger.warn("query: " + sql, e);

			result.setMessage(e.getLocalizedMessage());
		}

		return result;
	}

	protected <T> Result<List<T>> getObjectList(String sql, Object[] params,
			SQLBiFunction<ResultSet, Integer, T> setter) {
		Result<List<T>> result = new Result<List<T>>();

		try {
			List<T> objects = query(sql, params, setter);

			result.setData(objects);
			result.setResult(objects != null);

		} catch (EmptyResultDataAccessException e) {
			logger.warn("SELECT 한 결과가 0개 입니다. query: " + sql, e);

			result.setMessage(e.getLocalizedMessage());
			result.setResult(true);

		} catch (Exception e) {
			logger.warn("query: " + sql, e);

			result.setMessage(e.getLocalizedMessage());
		}

		return result;
	}

	protected <T> Result<List<T>> getObjectList(String sql, SQLBiFunction<ResultSet, Integer, T> setter,
			List<Object> params) {
		return getObjectList(sql, params.toArray(new Object[] {}), setter);
	}

	protected <T> Result<List<T>> getObjectList(String sql, SQLBiFunction<ResultSet, Integer, T> setter,
			Object... params) {
		return getObjectList(sql, params, setter);
	}

	public String getQuery(String name) throws NoSuchMessageException {
		return querySource.getMessage(name, null, null);
	}

	public String getQuery(String name, Object[] args, Locale locale) throws NoSuchMessageException {
		return querySource.getMessage(name, args, locale);
	}

	public ReloadableResourceBundleMessageSource getQuerySource() {
		return querySource;
	}

	protected Result<Integer> noExecuteResult(String message) {

		Result<Integer> result = new Result<>();
		result.setMessage(message != null ? message : "데이터가 존재하지 않습니다.");

		return result;

	}

	protected <T> List<T> query(String sql, IRowMapperSetter<T> setter) {
		return getJdbcTemplate().query(sql, new RowMapper<T>() {

			@Override
			public T mapRow(ResultSet rs, int rowNum) throws SQLException {
				return setter.set(rs, rowNum);
			}
		});
	}

	protected <T> List<T> query(String sql, Object[] params, BiFunction<ResultSet, Integer, T> setter) {
		return getJdbcTemplate().query(sql, params == null ? new Object[] {} : params, new RowMapper<T>() {

			@Override
			public T mapRow(ResultSet rs, int rowNum) throws SQLException {
				return setter.apply(rs, rowNum);
			}
		});
	}

	protected <T> List<T> query(String sql, Object[] params, IRowMapperSetter<T> setter) {
		return getJdbcTemplate().query(sql, params == null ? new Object[] {} : params, new RowMapper<T>() {

			@Override
			public T mapRow(ResultSet rs, int rowNum) throws SQLException {
				return setter.set(rs, rowNum);
			}
		});
	}

	protected <T> List<T> query(String sql, Object[] params, SQLBiFunction<ResultSet, Integer, T> setter) {
		return getJdbcTemplate().query(sql, params == null ? new Object[] {} : params, new RowMapper<T>() {

			@Override
			public T mapRow(ResultSet rs, int rowNum) throws SQLException {
				return setter.apply(rs, rowNum);
			}
		});
	}

	protected <T> T queryForObject(String sql, IRowMapperSetter<T> setter) {
		return getJdbcTemplate().queryForObject(sql, new RowMapper<T>() {

			@Override
			public T mapRow(ResultSet rs, int rowNum) throws SQLException {
				return setter.set(rs, rowNum);
			}
		});
	}

	protected <T> T queryForObject(String sql, Object[] params, BiFunction<ResultSet, Integer, T> setter) {
		return getJdbcTemplate().queryForObject(sql, params == null ? new Object[] {} : params, new RowMapper<T>() {

			@Override
			public T mapRow(ResultSet rs, int rowNum) throws SQLException {
				return setter.apply(rs, rowNum);
			}
		});
	}

	protected <T> T queryForObject(String sql, Object[] params, IRowMapperSetter<T> setter) {
		return getJdbcTemplate().queryForObject(sql, params == null ? new Object[] {} : params, new RowMapper<T>() {

			@Override
			public T mapRow(ResultSet rs, int rowNum) throws SQLException {
				return setter.set(rs, rowNum);
			}
		});
	}

	protected <T> T queryForObject(String sql, Object[] params, SQLBiFunction<ResultSet, Integer, T> setter) {
		return getJdbcTemplate().queryForObject(sql, params == null ? new Object[] {} : params, new RowMapper<T>() {

			@Override
			public T mapRow(ResultSet rs, int rowNum) throws SQLException {
				return setter.apply(rs, rowNum);
			}
		});
	}

	/**
	 * DBMS 접속을 위한 정보를 설정한다.
	 * 
	 * @param dataSource
	 *
	 */

	public abstract void setDataSource(DataSource dataSource);

	/**
	 * DBMS에 대한 쿼리 정보를 설정한다.
	 *
	 * @param querySource
	 *
	 */
	public abstract void setQuerySource(ReloadableResourceBundleMessageSource querySource);

	public StringBuffer setSqlPagination(int pageIndex, int itemsPerPage, StringBuffer buf) {

		buf.append(" LIMIT ");
		buf.append(itemsPerPage);
		buf.append(" OFFSET ");
		buf.append((pageIndex - 1) * itemsPerPage);

		return buf;
	}

	public StringBuffer setSqlWhereForMapParams(Map<String, Object> parameters, StringBuffer buf) {

		Iterator<Entry<String, Object>> itrEntrySet = parameters.entrySet().iterator();

		// a = ? or b = ? or c = ?
		Entry<String, Object> entry = null;
		if (itrEntrySet.hasNext()) {
			entry = itrEntrySet.next();

			buf.append(" WHERE ");
			buf.append(entry.getKey());
			buf.append('=');
			buf.append('?');

			while (itrEntrySet.hasNext()) {
				entry = itrEntrySet.next();

				buf.append(" OR ");
				buf.append(entry.getKey());
				buf.append('=');
				buf.append('?');
			}
		}

		return buf;
	}

	public StringBuffer setSqlWhereForStringParams(String columnName, StringBuffer buf) {

		buf.append(" AND ");
		buf.append(columnName);
		buf.append('=');
		buf.append('?');

		return buf;
	}

	public StringBuffer setSqlWhereIn(String columnName, Collection<String> data, StringBuffer buf,
			String concatenator) {

		buf.append(concatenator);

		buf.append(' ');
		buf.append(columnName);
		buf.append(' ');
		buf.append(" IN (");
		buf.append('?');

		for (int i = 1; i < data.size(); i++) {
			buf.append(",?");
		}

		buf.append(')');

		return buf;
	}

	/**
	 * 주어진 검색어를 'LIKE' 검색에 사용할 수 있도록 '%'를 추가하여 반환한다. <br>
	 * 
	 * <pre>
	 * [개정이력]
	 *      날짜    	| 작성자	|	내용
	 * ------------------------------------------
	 * 2018. 3. 14.		  jhlee		최초 작성
	 * </pre>
	 * 
	 * @param searchWord
	 * @param direction
	 * @return
	 */
	protected String toLikeSearch(String searchWord, LikeSearchDirection direction) {
		StringBuffer buf = new StringBuffer();

		switch (direction) {
		case ALL:
			buf.append('%');
			buf.append(searchWord);
			buf.append('%');
			break;
		case START:
			buf.append('%');
			buf.append(searchWord);
			break;
		case END:
			buf.append(searchWord);
			buf.append('%');
			break;
		default:
			break;
		}

		return buf.toString();
	}

	/**
	 * Invocation handler that suppresses close calls on JDBC Connections. Also
	 * prepares returned Statement (Prepared/CallbackStatement) objects.
	 * 
	 * @see java.sql.Connection#close()
	 */
	private class CloseSuppressingInvocationHandler implements InvocationHandler {

		private final Connection target;

		private JdbcTemplate jdbcTemplate;

		public CloseSuppressingInvocationHandler(Connection target, JdbcTemplate jdbcTemplate) {
			this.target = target;
			this.jdbcTemplate = jdbcTemplate;
		}

		private void applyStatementSettings(JdbcTemplate jdbcTemplate, Statement stmt) throws SQLException {
			int fetchSize = jdbcTemplate.getFetchSize();
			if (fetchSize > 0) {
				stmt.setFetchSize(fetchSize);
			}
			int maxRows = jdbcTemplate.getMaxRows();
			if (maxRows > 0) {
				stmt.setMaxRows(maxRows);
			}
			DataSourceUtils.applyTimeout(stmt, getDataSource(), jdbcTemplate.getQueryTimeout());
		}

		@SuppressWarnings("rawtypes")
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			// Invocation on ConnectionProxy interface coming in...

			if (method.getName().equals("equals")) {
				// Only consider equal when proxies are identical.
				return (proxy.equals(args[0]));
			} else if (method.getName().equals("hashCode")) {
				// Use hashCode of PersistenceManager proxy.
				return System.identityHashCode(proxy);
			} else if (method.getName().equals("unwrap")) {
				if (((Class) args[0]).isInstance(proxy)) {
					return proxy;
				}
			} else if (method.getName().equals("isWrapperFor")) {
				if (((Class) args[0]).isInstance(proxy)) {
					return true;
				}
			} else if (method.getName().equals("close")) {
				// Handle close method: suppress, not valid.
				return null;
			} else if (method.getName().equals("isClosed")) {
				return false;
			} else if (method.getName().equals("getTargetConnection")) {
				// Handle getTargetConnection method: return underlying Connection.
				return this.target;
			}

			// Invoke method on target Connection.
			try {
				Object retVal = method.invoke(this.target, args);

				// If return value is a JDBC Statement, apply statement settings
				// (fetch size, max rows, transaction timeout).
				if (retVal instanceof Statement) {
					applyStatementSettings(jdbcTemplate, ((Statement) retVal));
				}

				return retVal;
			} catch (InvocationTargetException ex) {
				throw ex.getTargetException();
			}
		}
	}

	public enum LikeSearchDirection {
		/** 주어진 문자열로 시작하는 */
		START("start"),
		/** 주어진 문자열로 끝나는 */
		END("end"),
		/** 주어진 문자열이 포함된 */
		ALL("all");

		private String direction;

		private LikeSearchDirection(String d) {
			this.direction = d;
		}

		public String get() {
			return this.direction;
		}

		public static LikeSearchDirection get(String direction) {

			try {

				if (direction == null) {
					throw new IllegalArgumentException("'direction' MUST NOT be null. input: " + direction);
				}

				for (LikeSearchDirection value : values()) {
					if (value.direction.equals(direction)) {
						return value;
					}
				}

				throw new IllegalArgumentException(
						"Unexpected 'direction' value of 'LikeSearchDirection'. input: " + direction);

			} catch (Exception e) {
				LoggerFactory.getLogger(AbstractGenericDaoImpl.class).error("LikeSearchDirection Exception.");
				throw e;
			}

		}

		public static LikeSearchDirection get(String direction, boolean ignoreCase) {

			try {

				if (direction == null) {
					throw new IllegalArgumentException("'direction' MUST NOT be null. input: " + direction);
				}

				if (!ignoreCase) {
					return get(direction);
				}

				for (LikeSearchDirection value : values()) {
					if (value.direction.equalsIgnoreCase(direction)) {
						return value;
					}
				}

				throw new IllegalArgumentException(
						"Unexpected 'direction' value of 'LikeSearchDirection'. input: " + direction);
			} catch (Exception e) {
				LoggerFactory.getLogger(AbstractGenericDaoImpl.class).error("LikeSearchDirection Exception.");
				throw e;
			}
		}
	}
}
