package kr.co.adonce.sbp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.co.adonce.sbp.controller.model.AnalysisDataDTO;
import kr.co.adonce.sbp.dao.IAnalysisDao;
import kr.co.adonce.sbp.dao.impl.AnalysisDaoImpl;
import kr.co.adonce.sbp.dao.model.AnalysisData;
import kr.co.adonce.sbp.service.IAnalysisService;
import open.commons.Result;

@Service(AnalysisServiceImpl.BEAN_QUALIFIER)
public class AnalysisServiceImpl extends GenericServiceImpl implements IAnalysisService {

	public static final String BEAN_QUALIFIER = "kr.co.adonce.sbp.service.impl.AnalysisServiceImpl";

	@Autowired
	@Qualifier(AnalysisDaoImpl.BEAN_QUALIFIER)
	private IAnalysisDao analysisDao;

	public AnalysisServiceImpl() {
	}

	@Override
	public Result<List<AnalysisDataDTO>> getAnalysisDataAll() {

		// 전체 결과
		Result<List<AnalysisDataDTO>> result = new Result<>();

		// #1. DTO , DAO 간 변경하기 위함
		List<AnalysisDataDTO> analysisDataDTOList = new ArrayList<>();
		List<AnalysisData> analysisDataList = new ArrayList<>();

		Result<List<AnalysisData>> analysisDataListResult = analysisDao.selectAnalysisDataAll();

		if (!analysisDataListResult.getResult()) {
			result.setMessage("모든 분석 데이터를 불러오는데 실패하였습니다.");
			return result;
		}

		analysisDataList = analysisDataListResult.getData();

		// #2. DTO 모델에 DAO 데이터를 매칭해서 넣어줌
		for (AnalysisData data : analysisDataList) {
			AnalysisDataDTO dto = new AnalysisDataDTO(data);
			analysisDataDTOList.add(dto);
		}

		result.setData(analysisDataDTOList);

		return result;
	}

}
