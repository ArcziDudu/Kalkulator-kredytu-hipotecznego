package model;

import java.math.BigDecimal;

public class RateAmounts {
    private final BigDecimal rateAmount;
    private final BigDecimal interestAmount;
    private final BigDecimal capitalAmount;

    public RateAmounts(BigDecimal rateAmount, BigDecimal interestAmount, BigDecimal capitalAmount) {
        this.rateAmount = rateAmount;
        this.interestAmount = interestAmount;
        this.capitalAmount = capitalAmount;
    }

    public BigDecimal getRateAmount() {
        return rateAmount;
    }

    public BigDecimal getInterestAmount() {
        return interestAmount;
    }

    public BigDecimal getCapitalAmount() {
        return capitalAmount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RateAmounts{");
        sb.append("rateAmount=").append(rateAmount);
        sb.append(", interestAmount=").append(interestAmount);
        sb.append(", capitalAmount=").append(capitalAmount);
        sb.append('}');
        return sb.toString();
    }
}
