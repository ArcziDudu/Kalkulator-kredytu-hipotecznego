package service;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class calculateInterestAmount {

    private BigDecimal interestPercent;
    private BigDecimal amount;
    private BigDecimal interestPercent1;
    private BigDecimal residualAmount;
   public final BigDecimal YEAR = BigDecimal.valueOf(12);
    public BigDecimal calculateInterestAmount(BigDecimal residualAmount, BigDecimal interestPercent) {
        return residualAmount.multiply(interestPercent).divide(YEAR, 50, RoundingMode.HALF_UP);
    }
}
