package jp.co.amiactive.mapper.PAY0004;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.amiactive.model.PAY0004.PAY0004_M_001_detailSqlModel;

@Mapper
public interface PAY0004_M_002_searchMapper {

    Integer registMax(String yearMonth);
    
    int regist(Integer maxPrint, String yearMonth, String today, Object insertUserCd);
    
    List<PAY0004_M_001_detailSqlModel> detail(String yearMonth);
}