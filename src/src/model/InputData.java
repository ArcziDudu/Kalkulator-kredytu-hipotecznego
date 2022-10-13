package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Map;

public class InputData {
    private static final BigDecimal PERCENT = BigDecimal.valueOf(100);
    private LocalDate repaymentStartDate = LocalDate.of(2020, 01, 06);
    private BigDecimal wiborPercent = BigDecimal.valueOf(1.73);
    private BigDecimal amount = BigDecimal.valueOf(298000);
    private BigDecimal monthsDuration = BigDecimal.valueOf(180);
    private BigDecimal bankMarginPercent = BigDecimal.valueOf(1.9);
    private RateType rateType = RateType.CONSTANT;

    //integer is a month, BD is overpayment value
    private Map<Integer, BigDecimal> overpaymentSchema = Map.of(
            5, BigDecimal.valueOf(10000),
            6, BigDecimal.valueOf(10000),
            7, BigDecimal.valueOf(10000),
            8, BigDecimal.valueOf(10000)
    );
    //Overpayment is a class for overpayment value
    private String overpaymentReduceWay = Overpayment.REDUCE_PERIOD;
    private BigDecimal overpaymentProvisionPercent = BigDecimal.valueOf(3);
    private BigDecimal overpaymentProvisionMonths = BigDecimal.valueOf(3);

    public InputData withOverpaymentSchema(Map<Integer, BigDecimal>overpaymentSchema){
this.overpaymentSchema = overpaymentSchema;
return this;
    }
public InputData withOverpaymentReduceWay(String overpaymentReduceWay){
    this.overpaymentReduceWay = overpaymentReduceWay;
    return this;
}
public InputData withOverpaymentProvisionPerccent(BigDecimal overpaymentProvisionPerccent){
    this.overpaymentProvisionPercent = overpaymentProvisionPerccent;
    return this;
}
public InputData withOverpaymentProvisionMonths(BigDecimal overpaymentProvisionMonths){
    this.overpaymentProvisionMonths = overpaymentProvisionMonths;
    return this;
}


    public InputData withRepaymentStartDate(LocalDate repaymentStartDate) {
        this.repaymentStartDate = repaymentStartDate;
        return this;
    }

    public InputData withWiborPercent(BigDecimal wiborPercent) {
        this.wiborPercent = wiborPercent;
        return this;
    }

    public InputData withAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public InputData withMonthsDuration(BigDecimal monthsDuration) {
        this.monthsDuration = monthsDuration;
        return this;
    }

    public InputData withBankMarginPercent(BigDecimal bankMarginPercent) {
        this.bankMarginPercent = bankMarginPercent;
        return this;
    }

    public InputData withRateType(RateType rateType) {
        this.rateType = rateType;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getMonthsDuration() {
        return monthsDuration;
    }

    public BigDecimal getInterestPercent() {
        return wiborPercent.add(bankMarginPercent).divide(PERCENT, 4, RoundingMode.HALF_UP);
    }

    public BigDecimal getInterestDisplay() {
        return wiborPercent.add(bankMarginPercent).setScale(2, RoundingMode.HALF_UP);
    }

    public LocalDate getRepaymentStartDate() {
        return repaymentStartDate;
    }

    public RateType getRateType() {
        return rateType;
    }

    public Map<Integer, BigDecimal> getOverpaymentSchema() {
        return overpaymentSchema;
    }

    public String getOverpaymentReduceWay() {
        return overpaymentReduceWay;
    }

    public BigDecimal getOverpaymentProvisionPercent() {
        return overpaymentProvisionPercent.divide(PERCENT, 4, RoundingMode.HALF_UP);
    }

    public BigDecimal getOverpaymentProvisionMonths() {
        return overpaymentProvisionMonths;
    }
}
