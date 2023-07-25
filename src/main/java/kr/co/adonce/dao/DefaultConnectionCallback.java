/*
 *
 * This file is generated under this project, "com.lguplus.items.capacity".
 *
 * Date  : 2017. 12. 29. 오후 5:17:56
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package kr.co.adonce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;

import open.commons.database.ConnectionCallbackBroker;
import open.commons.database.IConnectionCallbackSetter;

/**
 * 
 * @since 2018. 3. 14.
 * @author jhlee
 *
 */
public class DefaultConnectionCallback implements ConnectionCallback<Integer> {

    protected Logger logger = LogManager.getLogger(getClass());

    private final ConnectionCallbackBroker broker;

    /**
     * 
     * @since 2017. 12. 13.
     */
    public DefaultConnectionCallback(ConnectionCallbackBroker broker) {
        this.broker = broker;
    }

    /**
     * @see org.springframework.jdbc.core.ConnectionCallback#doInConnection(java.sql.Connection)
     */
    @Override
    public Integer doInConnection(Connection con) throws SQLException, DataAccessException {

        int count = 0;
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(broker.getQuery());

            IConnectionCallbackSetter setter = broker.getSetter();

            if (setter != null) {
                setter.set(stmt);
            }

            count = stmt.executeUpdate();

        } catch (SQLException e ) {
        	logger.error("SQL Exception.");
        } catch (Exception e) {

            logger.warn(e.getLocalizedMessage(), e);

            throw e;
        } finally {
        	
            try {
				if (stmt != null) {
				    stmt.close();
				}
			} catch (SQLException e) {
				logger.error("Do In Exception. ", e);
			}
        }
        
        return count;
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
     * @return
     */
    public ConnectionCallbackBroker getBroker() {
        return broker;
    }
}
