package kr.co.adonce.sbp.dao;

import java.util.List;

import kr.co.adonce.sbp.dao.model.AnalysisData;
import open.commons.Result;

public interface IAnalysisDao {

	/**
	 * 모든 데이터 조회
	 * 
	 * @return
	 */
	public Result<List<AnalysisData>> selectAnalysisDataAll();

}
