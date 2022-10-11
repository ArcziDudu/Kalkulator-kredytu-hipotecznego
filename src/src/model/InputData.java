package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class InputData {
    private static final BigDecimal PERCENT = BigDecimal.valueOf(100);
    private LocalDate repaymentStartDate = LocalDate.of(2022, 10, 10);
    private BigDecimal wiborPercent = BigDecimal.valueOf(1.73);
    private BigDecimal amount = BigDecimal.valueOf(300000);
    private BigDecimal monthsDuration = BigDecimal.valueOf(180);
    private BigDecimal bankMarginPercent = BigDecimal.valueOf(1.9);
    private RateType rateType = RateType.CONSTANT;

    //making withers

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
        return wiborPercent.add(bankMarginPercent).divide(PERCENT, 10, RoundingMode.HALF_UP);
    }

    public BigDecimal getInterestDisplay() {
        return wiborPercent.add(bankMarginPercent).setScale(2, RoundingMode.HALF_UP);
    }
}
