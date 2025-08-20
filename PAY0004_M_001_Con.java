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
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;
import jp.co.amiactive.model.PAY0004.PAY0004_M_001_Model;
import jp.co.amiactive.model.PAY0004.PAY0004_M_001_detailSqlModel;
import jp.co.amiactive.model.PAY0004.PAY0004_M_editModel;
import jp.co.amiactive.service.PAY0004.PAY0004_M_001_SearchSer;

@Controller
public class PAY0004_M_001_Con {

    @Autowired
    private PAY0004_M_001_SearchSer payrollService;

    /**
     * URL押下時 1画面目の初期表示
     * @param model
     * @return
     */
    @GetMapping(value = "/payroll_item_search_list")
    public String oneScreen(Model model,PAY0004_M_001_Model PAY0004_M_001_Model,
            HttpSession session, Integer page) throws ParseException {
        model.addAttribute("PAY0004_M_001_Model", new PAY0004_M_001_Model());
        String insertUserCd = "testUser01";
        
        PAY0004_M_001_Model list = (PAY0004_M_001_Model) session.getAttribute("PAY0004_M_001_Model");

        if (list != null) {
            Integer currentPage = list.getCurrentPage();

            List<PAY0004_M_editModel> firstScreenSearch  = payrollService.
                    firstScreenSearch(list, model, PAY0004_M_001_Model, page);
            
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("resultList", firstScreenSearch);
            model.addAttribute("PAY0004_M_001_Model", list);
            yearsScreen(model);
        }
        session.setAttribute("insertUserCd", insertUserCd);
        yearsScreen(model);
        return "payroll_item_search_list";
    }

    /**
     * 検索ボタン 1画面目
     * @param PAY0004_M_001_model (画面DTO)
     * @param result (エラーメッセージ)
     * @param model (テーブルDTO)
     * @return 検索結果の件数
     */
    @PostMapping(value = "/payroll_item_search_list")
    public String searchPayrollItems(
            @ModelAttribute PAY0004_M_001_Model PAY0004_M_001_Model,
            Model model, HttpSession session) throws ParseException {

        // 検索、ページ番号押下時のボタン。ページ数を意味する
        Integer page = PAY0004_M_001_Model.getSearch();

        List<PAY0004_M_editModel> resultList = payrollService.search(
                PAY0004_M_001_Model, page, model, session);
        model.addAttribute("resultList", resultList);

        if (resultList == null) {
            model.addAttribute("msg", "集計年月の期間を選択してください");
        }
        // ページ番号表示
        int currentPage = page;
        model.addAttribute("currentPage", currentPage);
        yearsScreen(model);
        return "payroll_item_search_list"; // 検索結果を表示
    }

    /**
     * <ボタン 1画面目
     * @param PAY0004_M_001_model (画面DTO)
     * @param result (エラーメッセージ)
     * @param model (テーブルDTO)
     * @return 検索結果の件数
     */
    @PostMapping(value = "/payroll_item_search_list", params = "backOne")
    public String backOne(@RequestParam("backOne") Integer page, Model model,
            @ModelAttribute PAY0004_M_001_Model PAY0004_M_001_Model) throws ParseException {

        List<PAY0004_M_editModel> resultList = payrollService.backOne(page,
                PAY0004_M_001_Model, model);

        model.addAttribute("resultList", resultList);
        System.out.println("< [pageのvalueは" + page + "です]");

        // ページ番号表示
        int pageNum = page;
        int currentPage = pageNum;
        model.addAttribute("currentPage", currentPage);
        yearsScreen(model);
        return "payroll_item_search_list"; // 検索結果を表示
    }

    /**
     * 数字ボタン 1画面目 name="page"
     * @param PAY0004_M_001_model (画面DTO)
     * @param result (エラーメッセージ)
     * @param model (テーブルDTO)-@@@@@
     * @return 検索結果の件数
     */
    @PostMapping(value = "/payroll_item_search_list", params = "page")
    public String page(@RequestParam("page") Integer page, Model model,
            @ModelAttribute PAY0004_M_001_Model PAY0004_M_001_Model) throws ParseException {

        List<PAY0004_M_editModel> resultList = payrollService.page(page,
                PAY0004_M_001_Model, model);

        model.addAttribute("resultList", resultList);
        System.out.println("1 [pageのvalueは" + page + "です]");

        // ページ番号表示
        int pageNum = page;
        int currentPage = pageNum;
        model.addAttribute("currentPage", currentPage);
        yearsScreen(model);

        return "payroll_item_search_list"; // 検索結果を表示
    }

    /**
     * >ボタン 1画面目
     * @param PAY0004_M_001_model (画面DTO)
     * @param result (エラーメッセージ)
     * @param model (テーブルDTO)
     * @return 検索結果の件数
     */
    @PostMapping(value = "/payroll_item_search_list", params = "next")
    public String next(@RequestParam("next") Integer page, Model model,
            @ModelAttribute PAY0004_M_001_Model PAY0004_M_001_Model) throws ParseException {

        List<PAY0004_M_editModel> resultList = payrollService.next(page,
                PAY0004_M_001_Model, model);

        model.addAttribute("resultList", resultList);
        System.out.println("> [pageのvalueは" + page + "です]");

        // ページ番号表示
        int pageNum = page;
        int currentPage = pageNum;
        model.addAttribute("currentPage", currentPage);
        yearsScreen(model);

        return "payroll_item_search_list"; // 検索結果を表示
    }

    /**
     * メニューボタン 1画面目
     * @return
     */
    @PostMapping(value = "/payroll_item_search_list", params = "menu")
    public String menu(Model model) {
        return "menu";
    }

    /**
     * 登録ボタン 1画面目
     * @return
     */
    @PostMapping(value = "/payroll_item_search_list", params = "registOneScreen")
    public String registOneScreen(
            @ModelAttribute PAY0004_M_001_Model PAY0004_M_001_Model,
            Model model) throws ParseException {
        return "redirect:/payroll_item_list_screen";
    }

    /**
     * 削除ボタン 1画面目
     * @return
     */
    @PostMapping(value = "/payroll_item_search_list", params = "delete")
    public String delete(@RequestParam("check") List<String> check,
            @ModelAttribute PAY0004_M_001_Model PAY0004_M_001_Model,
            Model model, HttpSession session) throws ParseException {

        payrollService.delete(check, PAY0004_M_001_Model, model);

        // 検索、ページ番号押下時のボタン。ページ数を意味する
        Integer page = PAY0004_M_001_Model.getSearch();
        List<PAY0004_M_editModel> resultList = payrollService.search(
                PAY0004_M_001_Model, page, model, session);
        model.addAttribute("resultList", resultList);

        // ページ番号表示
        int currentPage = page;
        model.addAttribute("currentPage", currentPage);

        yearsScreen(model);
        return "payroll_item_search_list";
    }

    /**
     * 詳細ボタン 1画面目
     * @return
     */
    @PostMapping(value = "/payroll_item_search_list", params = "detail")
    public String detail(@RequestParam("detail") List<String> check,
            PAY0004_M_001_Model PAY0004_M_001_Model,
            PAY0004_M_001_detailSqlModel PAY0004_M_001_detailSqlModel,
            Model model, HttpSession session) throws ParseException {

        List<PAY0004_M_001_detailSqlModel> detailList = payrollService.detail(
                check, PAY0004_M_001_Model, PAY0004_M_001_detailSqlModel,
                model);

        // detailListの合計を取得
        payrollService.totals(detailList, model);

        if (detailList == null) {
            detailList = new ArrayList<>();
            model.addAttribute("detailList", detailList);
        }
        model.addAttribute("detailList", detailList);
        yearsScreen(model);
        session.setAttribute("PAY0004_M_001_Model", PAY0004_M_001_Model);
        return "payroll_item_list_screen";
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
