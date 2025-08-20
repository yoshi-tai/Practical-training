package jp.co.amiactive.service.PAY0004;

import java.text.ParseException;
import java.util.List;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import jp.co.amiactive.model.PAY0004.PAY0004_M_001_Model;
import jp.co.amiactive.model.PAY0004.PAY0004_M_001_detailSqlModel;
import jp.co.amiactive.model.PAY0004.PAY0004_M_editModel;


public interface PAY0004_M_001_SearchSer {
    
    /**
     * 初期表示用　検索処理
     * @param PAY0004_M_001_Model
     * @throws ParseException
     */
    List<PAY0004_M_editModel> firstScreenSearch(PAY0004_M_001_Model list, Model model, PAY0004_M_001_Model PAY0004_M_001_Model, Integer page) throws ParseException;
    
    /**
     * 検索イベント押下時の処理
     * @param PAY0004_M_001_Model
     * @throws ParseException
     */
    List<PAY0004_M_editModel> search(PAY0004_M_001_Model PAY0004_M_001_Model,Integer page ,Model model, HttpSession session) throws ParseException;
    
    /** 
     * < の処理　
     * @param page
     * @param PAY0004_M_001_Model
     * @param model
     * @return
     * @throws ParseException
     */
    List<PAY0004_M_editModel> backOne(Integer page,PAY0004_M_001_Model PAY0004_M_001_Model, Model model)throws ParseException;
    
    /**
     * 数字ボタンの処理　
     * @param page
     * @param PAY0004_M_001_Model
     * @param model
     * @return
     * @throws ParseException
     */
    List<PAY0004_M_editModel> page(Integer page,PAY0004_M_001_Model PAY0004_M_001_Model, Model model)throws ParseException;

    /**
     * > の処理　
     * @param page
     * @param PAY0004_M_001_Model
     * @param model
     * @return
     * @throws ParseException
     */
    List<PAY0004_M_editModel> next(Integer page,PAY0004_M_001_Model PAY0004_M_001_Model, Model model)throws ParseException;

    /**
     * 削除の処理　
     * @param page
     * @param PAY0004_M_001_Model
     * @param model
     * @return
     * @throws ParseException
     */
    void delete(List<String> check,PAY0004_M_001_Model PAY0004_M_001_Model, Model model)throws ParseException;

    /**
     * 詳細 の処理　
     * @param page
     * @param PAY0004_M_001_Model
     * @param model
     * @return
     * @throws ParseException
     */
    List<PAY0004_M_001_detailSqlModel> detail(List<String> collect,PAY0004_M_001_Model PAY0004_M_001_Model, PAY0004_M_001_detailSqlModel PAY0004_M_001_detailSqlModel, Model model)throws ParseException;
     
    /**
     * 集計イベント押下時の処理
     * @param PAY0004_M_001_Model
     * @return
     * @throws ParseException
     */
    void totals(List<PAY0004_M_001_detailSqlModel> detailList, Model model) throws ParseException;
    
    





}