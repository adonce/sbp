package kr.co.adonce.dao;

import java.util.Locale;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * IGenericDao
 * @since 2018. 3. 14.
 * @author jhlee
 *
 */
public interface IGenericDao extends InitializingBean, DisposableBean {

    ReloadableResourceBundleMessageSource getQuerySource();

    JdbcTemplate getJdbcTemplate();

    String getQuery(String name);

    String getQuery(String name, Object[] args, Locale locale);

}
