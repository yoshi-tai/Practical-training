package jp.co.amiactive.service.PAY0004;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import jp.co.amiactive.mapper.PAY0004.PAY0004_M_002_searchMapper;
import jp.co.amiactive.model.PAY0004.PAY0004_M_001_Model;
import jp.co.amiactive.model.PAY0004.PAY0004_M_001_detailSqlModel;

@Service
public class PAY0004_M_002_SerchSerImpl implements PAY0004_M_002_SearchSer {
    
    @Autowired
    private PAY0004_M_002_searchMapper SearchMapper;
    
    /**
     * 登録の処理
     * @param check
     * @return
     */
    public boolean regist(Model model,String upYear,String month,Object insertUserCd) throws ParseException {

        String yearMonth = upYear + month;
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        Integer registMax = SearchMapper.registMax(yearMonth);
        if (registMax == null) {
            registMax = 0; // 初回登録なら0など
        }
        Integer maxPrint = registMax + 1;
        
        int result = SearchMapper.regist(maxPrint, yearMonth,today, insertUserCd);
        
        return result > 0;
    }
    
    /**
     * 詳細の処理
     * @param check
     * @return
     */
    public List<PAY0004_M_001_detailSqlModel> detail(
            String yearMonth,
            PAY0004_M_001_Model PAY0004_M_001_Model,
            PAY0004_M_001_detailSqlModel PAY0004_M_001_detailSqlModel,
            Model model) throws ParseException {

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
}
