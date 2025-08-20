package jp.co.amiactive.service.PAY0004;

import java.text.ParseException;
import java.util.List;
import org.springframework.ui.Model;
import jp.co.amiactive.model.PAY0004.PAY0004_M_001_Model;
import jp.co.amiactive.model.PAY0004.PAY0004_M_001_detailSqlModel;

public interface PAY0004_M_002_SearchSer {
    
    /**
     * 登録 の処理　
     * @param page
     * @param PAY0004_M_001_Model
     * @param model
     * @return
     * @throws ParseException
     */
    boolean regist(Model model,String upYear,String month,Object insertUserCd)throws ParseException;

    /**
     * 詳細 の処理　
     * @param page
     * @param PAY0004_M_001_Model
     * @param model
     * @return
     * @throws ParseException
     */
    List<PAY0004_M_001_detailSqlModel> detail(String yearMonth,PAY0004_M_001_Model PAY0004_M_001_Model, PAY0004_M_001_detailSqlModel PAY0004_M_001_detailSqlModel, Model model)throws ParseException;
    
    /**
     * 集計イベント押下時の処理
     * @param PAY0004_M_001_Model
     * @return
     * @throws ParseException
     */
    void totals(List<PAY0004_M_001_detailSqlModel> detailList, Model model) throws ParseException;
    
}