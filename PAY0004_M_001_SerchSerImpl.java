package jp.co.amiactive.service.PAY0004;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import jp.co.amiactive.mapper.PAY0004.PAY0004_M_001_searchMapper;
import jp.co.amiactive.model.PAY0004.PAY0004_M_001_Model;
import jp.co.amiactive.model.PAY0004.PAY0004_M_001_detailSqlModel;
import jp.co.amiactive.model.PAY0004.PAY0004_M_SQLModel;
import jp.co.amiactive.model.PAY0004.PAY0004_M_editModel;

@Service
public class PAY0004_M_001_SerchSerImpl implements PAY0004_M_001_SearchSer {

    @Autowired
    private PAY0004_M_001_searchMapper SearchMapper;

    /**
     * 初期表示
     */
    public List<PAY0004_M_editModel> firstScreenSearch(PAY0004_M_001_Model list,
            Model model, PAY0004_M_001_Model PAY0004_M_001_Model,
            Integer page) throws ParseException {
        // 値取得
        String recent = PAY0004_M_001_Model.getRecent();

        List<PAY0004_M_editModel> resultList = new ArrayList<>();

        String whichResent = "最新".equals(recent) ? "最新" : "最新じゃない";
        if ("最新".equals(whichResent)) {
            resultList = recentRadio(PAY0004_M_001_Model, page, recent);
        } else {
            resultList = radio(PAY0004_M_001_Model, page);
        }
        return resultList;
    }

    /**
     * 検索イベント押下時の処理
     * @param PAY0004_M_001_Model
     * @return
     * @throws ParseException
     */
    public List<PAY0004_M_editModel> search(
            PAY0004_M_001_Model PAY0004_M_001_Model, Integer page, Model model,
            HttpSession session) throws ParseException {

        // 値取得
        String recent = PAY0004_M_001_Model.getRecent();

        List<PAY0004_M_editModel> resultList = new ArrayList<>();

        String whichResent = "最新".equals(recent) ? "最新" : "最新じゃない";
        if ("最新".equals(whichResent)) {
            resultList = recentRadio(PAY0004_M_001_Model, page, recent);
        } else {
            resultList = radio(PAY0004_M_001_Model, page);
        }
        return resultList;
    }

    /**
     * < の処理
     * @param upMaxPrint
     * @param resultYearSer
     * @return
     */
    public List<PAY0004_M_editModel> backOne(Integer page,
            PAY0004_M_001_Model PAY0004_M_001_Model,
            Model model) throws ParseException {
        // 値取得
        String recent = PAY0004_M_001_Model.getRecent();

        List<PAY0004_M_editModel> resultList = new ArrayList<>();

        String whichResent = "最新".equals(recent) ? "最新" : "最新じゃない";
        if ("最新".equals(whichResent)) {
            resultList = recentRadio(PAY0004_M_001_Model, page, recent);
        } else {
            resultList = radio(PAY0004_M_001_Model, page);
        }
        return resultList;
    }

    /**
     * 数字ボタンの処理
     * @param upMaxPrint
     * @param resultYearSer
     * @return
     */
    public List<PAY0004_M_editModel> page(Integer page,
            PAY0004_M_001_Model PAY0004_M_001_Model,
            Model model) throws ParseException {
        // 値取得
        String recent = PAY0004_M_001_Model.getRecent();

        List<PAY0004_M_editModel> resultList = new ArrayList<>();

        String whichResent = "最新".equals(recent) ? "最新" : "最新じゃない";
        if ("最新".equals(whichResent)) {
            resultList = recentRadio(PAY0004_M_001_Model, page, recent);
        } else {
            resultList = radio(PAY0004_M_001_Model, page);
        }
        return resultList;
    }

    /**
     * > の処理
     * @param upMaxPrint
     * @param resultYearSer
     * @return
     */
    public List<PAY0004_M_editModel> next(Integer page,
            PAY0004_M_001_Model PAY0004_M_001_Model,
            Model model) throws ParseException {
        // 値取得
        String recent = PAY0004_M_001_Model.getRecent();

        List<PAY0004_M_editModel> resultList = new ArrayList<>();

        String whichResent = "最新".equals(recent) ? "最新" : "最新じゃない";
        if ("最新".equals(whichResent)) {
            resultList = recentRadio(PAY0004_M_001_Model, page, recent);
        } else {
            resultList = radio(PAY0004_M_001_Model, page);
        }
        return resultList;
    }

    /**
     * 削除の処理
     * @param check
     * @return
     */
    public void delete(List<String> check,
            PAY0004_M_001_Model PAY0004_M_001_Model,
            Model model) throws ParseException {

        List<String> list = new ArrayList<>();

        for (String box : check) {
            // checkの値を編集する
            String year = box.substring(0, 4);
            String month = box.substring(5, 7);
            String yearMonth = year + month;
            list.add(yearMonth);
        }
        SearchMapper.updateDelete(list);
    }

    /**
     * 詳細の処理
     * @param check
     * @return
     */
    public List<PAY0004_M_001_detailSqlModel> detail(
            List<String> checkOrCollect,
            PAY0004_M_001_Model PAY0004_M_001_Model,
            PAY0004_M_001_detailSqlModel PAY0004_M_001_detailSqlModel,
            Model model) throws ParseException {

        String editYearMonth = checkOrCollect.get(0);

        // checkの値を編集する
        String year = editYearMonth.substring(0, 4);
        String month = editYearMonth.substring(5, 7);
        String yearMonth = year + month;
        PAY0004_M_001_Model.setMonth(month);

        List<PAY0004_M_001_detailSqlModel> detailList = SearchMapper.detail(
                yearMonth);
        detailListCheck(detailList, model);

        return detailList;
    }

    /**
     * メソッド 2画面目 (detailListの合計を出す)
     * @param detailList
     * @param model
     */
    public void totals(List<PAY0004_M_001_detailSqlModel> detailList,
            Model model) { // 明細リストとThymeleaf用のmodelを受け取る

        for (PAY0004_M_001_detailSqlModel details : detailList) {

            // 合計用変数を用意
            int totalPrescribedWorkDate = 0;
            int totalPrescribedWorkCount = 0;
            int totalAttendancesNum = 0;
            int totalAttendanceBasicDays = 0;
            int totalActualWorkingHours = 0;
            int totalAbsenteeismDays = 0;
            int totalAbsentBasicDays = 0;
            int totalPaidDays = 0;
            int totalUsedPaidLeaveHours = 0;
            int totalUnusedPaidLeaveDays = 0;
            int totalUnusedPaidLeaveHours = 0;
            int totalHolidayWorkingHours = 0;
            int totalSpecialLeaveDays = 0;
            int totalBaseSalary = 0;
            int totalBasicMonthlySalary = 0;
            int totalPositionalAllowance = 0;
            int totalExecutiveCompensation = 0;
            int totalOvertimePay = 0;
            int totalExpensesForWorkingOnHolidays = 0;
            int totalTaxFreeComExpenses = 0;
            int totalTaxComExpenses = 0;
            int totalIncomeTax = 0;
            int totalIncomeTaxCumulative = 0;
            int totalResidentTax = 0;
            int totalTaxPaymentCumulative = 0;
            int totalTaxTable = 0;
            int totalWelfarePensionFund = 0;
            int totalEmpPensionIns = 0;
            int totalWelfarePensionStandardRemuneration = 0;
            int totalSocialInsTotal = 0;
            int totalSocialInsurancePremiumCalculation = 0;
            int totalSocialInsurancePremiumAdjustment = 0;
            int totalSocialInsurancePremiumAfterDeduction = 0;
            int totalEmpInsFee = 0;
            int totalHealthInsFee = 0;
            int totalHealthInsuranceStandardRemuneration = 0;
            int totalNursCareInsFee = 0;
            int totalYearEndAdjustmentWithholding = 0;
            int totalYearEndAdjustmentRefund = 0;
            int totalDeductionTotal = 0;
            int totalNetPaymentTotal = 0;
            int totalNumberOfDependents = 0;

            // 明細をループして加算
            for (PAY0004_M_001_detailSqlModel item : detailList) {
                totalPrescribedWorkDate += Double.parseDouble(details
                        .getPrescribedWorkDate());
                totalPrescribedWorkCount += details.getPrescribedWorkCount();
                totalAttendancesNum += details.getAttendancesNum();
                totalAttendanceBasicDays += details.getAttendanceBasicDays();
                totalActualWorkingHours += details.getActualWorkingHours();
                totalAbsenteeismDays += details.getAbsenteeismDays();
                totalAbsentBasicDays += details.getAbsentBasicDays();
                totalPaidDays += details.getPaidDays();
                totalUsedPaidLeaveHours += details.getUsedPaidLeaveHours();
                totalUnusedPaidLeaveDays += details.getUnusedPaidLeaveDays();
                totalUnusedPaidLeaveHours += details.getUnusedPaidLeaveHours();
                totalHolidayWorkingHours += details.getHolidayWorkingHours();
                totalSpecialLeaveDays += details.getSpecialLeaveDays();
                totalBaseSalary += details.getBaseSalary();
                totalBasicMonthlySalary += details.getBasicMonthlySalary();
                totalPositionalAllowance += details.getPositionalAllowance();
                totalExecutiveCompensation += details
                        .getExecutiveCompensation();
                totalOvertimePay += details.getOvertimePay();
                totalExpensesForWorkingOnHolidays += details
                        .getExpensesForWorkingOnHolidays();
                totalTaxFreeComExpenses += details.getTaxFreeComExpenses();
                totalTaxComExpenses += details.getTaxComExpenses();
                totalIncomeTax += details.getIncomeTax();
                totalIncomeTaxCumulative += details.getIncomeTaxCumulative();
                totalResidentTax += details.getResidentTax();
                totalTaxPaymentCumulative += details.getTaxPaymentCumulative();
                // totalTaxTable += Integer.parseInt(details.getTaxTable());
                totalWelfarePensionFund += details.getWelfarePensionFund();
                totalEmpPensionIns += details.getEmpPensionIns();
                totalWelfarePensionStandardRemuneration += details
                        .getWelfarePensionStandardRemuneration();
                totalSocialInsTotal += details.getSocialInsTotal();
                totalSocialInsurancePremiumCalculation += details
                        .getSocialInsurancePremiumCalculation();
                totalSocialInsurancePremiumAdjustment += details
                        .getSocialInsurancePremiumAdjustment();
                totalSocialInsurancePremiumAfterDeduction += details
                        .getSocialInsurancePremiumAfterDeduction();
                totalEmpInsFee += details.getEmpInsFee();
                totalHealthInsFee += details.getHealthInsFee();
                totalHealthInsuranceStandardRemuneration += details
                        .getHealthInsuranceStandardRemuneration();
                totalNursCareInsFee += details.getNursCareInsFee();
                totalYearEndAdjustmentWithholding += details
                        .getYearEndAdjustmentWithholding();
                totalYearEndAdjustmentRefund += details
                        .getYearEndAdjustmentRefund();
                totalDeductionTotal += details.getDeductionTotal();
                totalNetPaymentTotal += details.getNetPaymentTotal();
                totalNumberOfDependents += details.getNumberOfDependents();
            }
            model.addAttribute("prescribedWorkDate", totalPrescribedWorkDate);
            model.addAttribute("prescribedWorkCount", totalPrescribedWorkCount);
            model.addAttribute("attendancesNum", totalAttendancesNum);
            model.addAttribute("attendanceBasicDays", totalAttendanceBasicDays);
            model.addAttribute("actualWorkingHours", totalActualWorkingHours);
            model.addAttribute("absenteeismDays", totalAbsenteeismDays);
            model.addAttribute("absentBasicDays", totalAbsentBasicDays);
            model.addAttribute("paidDays", totalPaidDays);
            model.addAttribute("usedPaidLeaveHours", totalUsedPaidLeaveHours);
            model.addAttribute("unusedPaidLeaveDays", totalUnusedPaidLeaveDays);
            model.addAttribute("unusedPaidLeaveHours",
                    totalUnusedPaidLeaveHours);
            model.addAttribute("holidayWorkingHours", totalHolidayWorkingHours);
            model.addAttribute("specialLeaveDays", totalSpecialLeaveDays);
            model.addAttribute("baseSalary", totalBaseSalary);
            model.addAttribute("basicMonthlySalary", totalBasicMonthlySalary);
            model.addAttribute("positionalAllowance", totalPositionalAllowance);
            model.addAttribute("executiveCompensation",
                    totalExecutiveCompensation);
            model.addAttribute("overtimePay", totalOvertimePay);
            model.addAttribute("expensesForWorkingOnHolidays",
                    totalExpensesForWorkingOnHolidays);
            model.addAttribute("taxFreeComExpenses", totalTaxFreeComExpenses);
            model.addAttribute("taxComExpenses", totalTaxComExpenses);
            model.addAttribute("incomeTax", totalIncomeTax);
            model.addAttribute("incomeTaxCumulative", totalIncomeTaxCumulative);
            model.addAttribute("residentTax", totalResidentTax);
            model.addAttribute("taxPaymentCumulative",
                    totalTaxPaymentCumulative);
            model.addAttribute("taxTable", totalTaxTable);
            model.addAttribute("welfarePensionFund", totalWelfarePensionFund);
            model.addAttribute("empPensionIns", totalEmpPensionIns);
            model.addAttribute("welfarePensionStandardRemuneration",
                    totalWelfarePensionStandardRemuneration);
            model.addAttribute("socialInsTotal", totalSocialInsTotal);
            model.addAttribute("socialInsurancePremiumCalculation",
                    totalSocialInsurancePremiumCalculation);
            model.addAttribute("socialInsurancePremiumAdjustment",
                    totalSocialInsurancePremiumAdjustment);
            model.addAttribute("socialInsurancePremiumAfterDeduction",
                    totalSocialInsurancePremiumAfterDeduction);
            model.addAttribute("empInsFee", totalEmpInsFee);
            model.addAttribute("healthInsFee", totalHealthInsFee);
            model.addAttribute("healthInsuranceStandardRemuneration",
                    totalHealthInsuranceStandardRemuneration);
            model.addAttribute("nursCareInsFee", totalNursCareInsFee);
            model.addAttribute("yearEndAdjustmentWithholding",
                    totalYearEndAdjustmentWithholding);
            model.addAttribute("yearEndAdjustmentRefund",
                    totalYearEndAdjustmentRefund);
            model.addAttribute("deductionTotal", totalDeductionTotal);
            model.addAttribute("netPaymentTotal", totalNetPaymentTotal);
            model.addAttribute("numberOfDependents", totalNumberOfDependents);
        }
    }

    /**
     * メソッド (値の編集処理)
     * @param upMaxPrint
     * @param resultYearSer
     * @return
     */
    private List<PAY0004_M_editModel> edit(List<PAY0004_M_SQLModel> upMaxPrint,
            List<PAY0004_M_SQLModel> resultYearSer) {

        // 編集後の箱生成
        List<PAY0004_M_editModel> editList = new ArrayList<>();

        // nullと空文字チェック
        if (upMaxPrint == null || upMaxPrint.isEmpty() || upMaxPrint.get(
                0) == null) {
            return editList;
        }
        // 最大印刷実施連番を取り出す
        Integer maxPrint = upMaxPrint.get(0).getPrintImplementationNum();

        for (int i = 0; i < resultYearSer.size(); i++) {
            // 集計年月を取り出す
            String aggregatePeriod = resultYearSer.get(i).getAggregatePeriod();
            Integer editPrintImplementationNum = resultYearSer.get(i)
                    .getPrintImplementationNum();
            System.out.println(editPrintImplementationNum+ "ああああああああああああああああああああああああああ");

            // 集計年月の値を編集する
            if (aggregatePeriod != null && aggregatePeriod.length() == 6) {
                String year = aggregatePeriod.substring(0, 4);
                String month = aggregatePeriod.substring(4, 6);
                String yearMonth = year + "/" + month;

                // 印刷連番を取り出して、同じ値だった場合、最新データ、違ったら旧データにする
                Integer max = resultYearSer.get(i).getPrintImplementationNum();
                String status = (max.equals(maxPrint)) ? "最新データ" : "旧データ";

                if (resultYearSer.size() > 1 ) {

                }

                // コンストラクタを呼び出して、PAY0004_M_editModel に年/月とステータスをセット
                PAY0004_M_editModel editedModel = new PAY0004_M_editModel(yearMonth, status, editPrintImplementationNum); // "最新データ"
                                                                                                                          // or
                                                                                                                          // "旧データ"
                editList.add(editedModel);
            }
        }
        return editList;
    }

    /**
     * メソッド detailListがnullの場合、空のリストを代入する
     * @param detailList
     * @param model
     */
    private void detailListCheck(List<PAY0004_M_001_detailSqlModel> detailList,
            Model model) {
        if (detailList == null) {
            detailList = new ArrayList<>();
            model.addAttribute("detailList", detailList);
        }
    }

    /**
     * メソッド recentが最新の時のマッパー呼び出し
     * @param page
     * @param radio
     * @param upYear
     * @return
     */
    public List<PAY0004_M_editModel> recentRadio(
            PAY0004_M_001_Model PAY0004_M_001_Model, Integer page,
            String recent) {

        // 集計年月
        String downLeftYear = PAY0004_M_001_Model.getDownLeftYear();
        String downLeftMonth = PAY0004_M_001_Model.getDownLeftMonth();
        String downRightYear = PAY0004_M_001_Model.getDownRightYear();
        String downRightMonth = PAY0004_M_001_Model.getDownRightMonth();
        String upYear = PAY0004_M_001_Model.getUpYear();
        String radio = PAY0004_M_001_Model.getRadio();

        List<PAY0004_M_editModel> edit = null;
        Integer totalPage = page - 1;

        // 返却用
        List<PAY0004_M_editModel> result = null;

        if ("集計年".equals(radio)) {
            // 件数取得
            Integer allPage = totalPage * 30;
            List<PAY0004_M_SQLModel> upMaxPrint = SearchMapper.upMaxPrint(
                    allPage, upYear);
            List<PAY0004_M_SQLModel> resultYearSer = SearchMapper
                    .yearSerRecentMapper(allPage, upYear, recent);

            // メソッド呼び出しとmodelに格納
            edit = edit(upMaxPrint, resultYearSer);

            result = edit;

        } else if ("集計年月".equals(radio)) {
            // 集計年月の連結
            Integer colectStartYearMonth = Integer.parseInt(downLeftYear
                    + downLeftMonth);
            Integer colectEndYearMonth = Integer.parseInt(downRightYear
                    + downRightMonth);
            // 件数取得
            Integer allPage = totalPage * 30;
            List<PAY0004_M_SQLModel> downMaxPrint = SearchMapper.downMaxPrint(
                    colectStartYearMonth, colectEndYearMonth, allPage);
            List<PAY0004_M_SQLModel> resultYearMonth = SearchMapper
                    .yearMonthRecentMapper(colectStartYearMonth,
                            colectEndYearMonth, allPage, recent);

            // メソッド呼び出しとmodelに格納
            edit = edit(downMaxPrint, resultYearMonth);
            result = edit;
        }

        return result;
    }

    /**
     * メソッド recentが最新ではない時のマッパー呼び出し
     * @param page
     * @param radio
     * @param upYear
     * @return
     */
    public List<PAY0004_M_editModel> radio(
            PAY0004_M_001_Model PAY0004_M_001_Model, Integer page) {

        // 集計年月
        String downLeftYear = PAY0004_M_001_Model.getDownLeftYear();
        String downLeftMonth = PAY0004_M_001_Model.getDownLeftMonth();
        String downRightYear = PAY0004_M_001_Model.getDownRightYear();
        String downRightMonth = PAY0004_M_001_Model.getDownRightMonth();
        String upYear = PAY0004_M_001_Model.getUpYear();
        String radio = PAY0004_M_001_Model.getRadio();

        List<PAY0004_M_editModel> edit = null;
        Integer totalPage = page - 1;

        // 返却用
        List<PAY0004_M_editModel> result = null;

        if ("集計年".equals(radio)) {
            // 件数取得
            Integer allPage = totalPage * 30;
            List<PAY0004_M_SQLModel> upMaxPrint = SearchMapper.upMaxPrint(
                    allPage, upYear);
            List<PAY0004_M_SQLModel> resultYearSer = SearchMapper.yearSerMapper(
                    allPage, upYear);

            // メソッド呼び出しとmodelに格納
            edit = edit(upMaxPrint, resultYearSer);

            result = edit;

        } else if ("集計年月".equals(radio)) {
            // 集計年月の連結
            Integer colectStartYearMonth = Integer.parseInt(downLeftYear
                    + downLeftMonth);
            Integer colectEndYearMonth = Integer.parseInt(downRightYear
                    + downRightMonth);
            // 件数取得
            Integer allPage = totalPage * 30;
            List<PAY0004_M_SQLModel> downMaxPrint = SearchMapper.downMaxPrint(
                    colectStartYearMonth, colectEndYearMonth, allPage);
            List<PAY0004_M_SQLModel> resultYearMonth = SearchMapper
                    .yearMonthMapper(colectStartYearMonth, colectEndYearMonth,
                            allPage);

            // メソッド呼び出しとmodelに格納
            edit = edit(downMaxPrint, resultYearMonth);
            result = edit;
        }

        return result;
    }
}
