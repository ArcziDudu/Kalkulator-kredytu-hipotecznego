package service;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class calculateInterestAmount {
   public final BigDecimal YEAR = BigDecimal.valueOf(12);
    public BigDecimal calculateInterestAmount(BigDecimal residualAmount, BigDecimal interestPercent) {
        return residualAmount.multiply(interestPercent).divide(YEAR, 50, RoundingMode.HALF_UP);
    }
}