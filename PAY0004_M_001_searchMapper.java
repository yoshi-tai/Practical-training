package jp.co.amiactive.mapper.PAY0004;

import java.time.LocalDate;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import jp.co.amiactive.model.PAY0004.PAY0004_M_001_detailSqlModel;
import jp.co.amiactive.model.PAY0004.PAY0004_M_SQLModel;

@Mapper
public interface PAY0004_M_001_searchMapper {

    /**
     * 検索イベント 4つ
     * @param allPage
     * @param upYear
     * @return
     */
    List<PAY0004_M_SQLModel> upMaxPrint(Integer allPage, String upYear);
    
    // recentがあるときのマッパー
    List<PAY0004_M_SQLModel> yearSerRecentMapper(Integer allPage, String upYear, String recent);
    
    List<PAY0004_M_SQLModel> yearMonthRecentMapper(Integer colectStartYearMonth, Integer colectEndYearMonth, Integer allPage, String recent );

    // recentがないときのマッパー
    List<PAY0004_M_SQLModel> yearSerMapper(Integer allPage, String upYear );

    List<PAY0004_M_SQLModel> downMaxPrint(Integer colectStartYearMonth, Integer colectEndYearMonth, Integer allPage);
    
    List<PAY0004_M_SQLModel> yearMonthMapper(Integer colectStartYearMonth, Integer colectEndYearMonth, Integer allPage );
        
    /**
     * 削除イベント
     * @param list
     * @return
     */
    List<PAY0004_M_SQLModel> updateDelete(List<String> list);
    
    /**
     * 詳細イベント
     * @param yearMonth
     * @return
     */
    List<PAY0004_M_001_detailSqlModel> detail(String yearMonth);
    
    List<PAY0004_M_001_detailSqlModel> collectYearMonth(String year);
    
    List<PAY0004_M_001_detailSqlModel> collectMonth(String yearMonth);
    
    Integer registMax(String yearMonth);
    
    int regist(Integer maxPrint, String yearMonth, LocalDate today, Object insertUserCd);

}