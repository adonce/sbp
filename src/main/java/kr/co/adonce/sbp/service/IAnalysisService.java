package kr.co.adonce.sbp.service;

import java.util.List;

import kr.co.adonce.sbp.controller.model.AnalysisDataDTO;
import open.commons.Result;

public interface IAnalysisService {

	public Result<List<AnalysisDataDTO>> getAnalysisDataAll();

}
