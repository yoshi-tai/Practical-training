package jp.co.amiactive.controller.PAY0004;

import java.text.ParseException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpSession;
import jp.co.amiactive.model.PAY0004.PAY0004_M_001_Model;
import jp.co.amiactive.model.PAY0004.PAY0004_M_001_detailSqlModel;
import jp.co.amiactive.service.PAY0004.PAY0004_M_002_SearchSer;

@Controller
public class PAY0004_M_002_Con {

    @Autowired
    private PAY0004_M_002_SearchSer payrollService;
    
    /**
     * URL押下時 2画面目の初期表示
     * @param model
     * @return
     */
    @GetMapping(value = "/payroll_item_list_screen")
    public String twoScreenGet(Model model) {
        model.addAttribute("PAY0004_M_001_Model", new PAY0004_M_001_Model());
        yearsScreen(model);
        return "payroll_item_list_screen";
    }
    
    /**
     * 登録ボタン 2画面目
     * @return
     */
    @PostMapping(value = "/payroll_item_list_screen", params = "registTwoScreen")
    public String registTwoScreen(
            @ModelAttribute PAY0004_M_001_Model PAY0004_M_001_Model,
            PAY0004_M_001_detailSqlModel PAY0004_M_001_detailSqlModel,
            HttpSession session, Model model) throws ParseException {

        String upYear = PAY0004_M_001_Model.getUpYear();
        String month = PAY0004_M_001_Model.getMonth();
        String empCount = PAY0004_M_001_Model.getEmpCount();
        Object insertUserCd = session.getAttribute("insertUserCd");

        yearsScreen(model);
        
        boolean result = payrollService.regist(model, upYear, month,
                insertUserCd);

        String msg = !result ? empCount + "　人の登録に失敗しました"
                : empCount + "　人の登録に成功しました";
        
        // 詳細の検索処理
        String yearMonth = upYear + month;
        List<PAY0004_M_001_detailSqlModel> detailList = payrollService.detail(
                yearMonth, PAY0004_M_001_Model, PAY0004_M_001_detailSqlModel,
                model);

        // detailListの合計を取得
        payrollService.totals(detailList, model);

        if (detailList == null) {
            detailList = new ArrayList<>();
            model.addAttribute("detailList", detailList);
        }
        model.addAttribute("detailList", detailList);
        
        model.addAttribute("msg", msg);
        model.addAttribute("PAY0004_M_001_Model", PAY0004_M_001_Model);
        return "payroll_item_list_screen";
    }

    /**
     * 戻るイベント 押下時の処理
     * @param model
     * @return リダイレクト 1画面目
     */
    @PostMapping(value = "/payroll_item_list_screen", params = "back")
    public String back(Model model, HttpSession session) {
        model.addAttribute("PAY0004_M_001_Model", new PAY0004_M_001_Model());
        yearsScreen(model);
        return "redirect:/payroll_item_search_list";
    }
    
    /**
     * メニューボタン 1画面目
     * @return
     */
    @PostMapping(value = "/payroll_item_list_screen", params = "menu")
    public String menu(Model model) {
        
        return "menu";
    }
    
    /**
     * メソッド (プルダウンの入力チェック)
     * @param upYear
     * @param downLeftMonth
     * @param model
     * @return
     */
    public boolean check(String upYear, String month, Model model) {
        if (upYear == null || "empty".equals(upYear) || month == null || "empty"
                .equals(month)) {
            model.addAttribute("msg", "検索年度と検索月を選択して、集計してください");
            return false;// 戻るときはfalse
        }
        return true; // チェックOKならtrue
    }
    
    /**
     * メソッド (現在年含めた過去5年を取得する)
     * @param model
     */
    public void yearsScreen(Model model) {

        int currentYear = Year.now().getValue();
        List<Integer> yearsOld = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            yearsOld.add(currentYear - i);
        }
        model.addAttribute("yearsOld", yearsOld);

        monthesScreen(model);
    }
    
    /**
     * メソッド (現在年含めた過去5年を取得する)
     * @param model
     */
    public void monthesScreen(Model model) {

        List<String> monthes = new ArrayList<>();
        for (int month = 1; month <= 12; month++) {
            // %02d → 整数を「2桁」で表示し、足りない場合は先頭に0を付ける
            monthes.add(String.format("%02d", month));
        }
        model.addAttribute("monthes", monthes);
    }
}
