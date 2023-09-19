package kr.co.adonce.sbp.dao.impl;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.adonce.sbp.dao.IAnalysisDao;
import kr.co.adonce.sbp.dao.model.AnalysisData;
import open.commons.Result;
import open.commons.function.SQLBiFunction;
import open.commons.utils.SQLUtils;

@Repository(AnalysisDaoImpl.BEAN_QUALIFIER)
public class AnalysisDaoImpl extends PostgreDbGenericDaoImpl implements IAnalysisDao {

	public static final String BEAN_QUALIFIER = "kr.co.adonce.sbp.dao.impl.AnalysisDaoImpl";

	private static final SQLBiFunction<ResultSet, Integer, AnalysisData> CreateAnalysisData = (rs, rowNum) -> {
		return SQLUtils.newInstance(AnalysisData.class, rs);
	};

	@Override
	public Result<List<AnalysisData>> selectAnalysisDataAll() {

		String query = getQuery("analysisDao.select.all.data");

		if (logger.isDebugEnabled()) {
			logger.debug("SQL: " + query);
		}

		return getObjectList(query, CreateAnalysisData);
	}

}
