package utils;

import service.AmountsCalculationService;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculationUtils {
    private CalculationUtils() {}

    public static final BigDecimal YEAR = BigDecimal.valueOf(12);
    public static BigDecimal calculateInterestAmount(BigDecimal residualAmount, BigDecimal interestPercent) {
        return residualAmount.multiply(interestPercent).divide(YEAR, 50, RoundingMode.HALF_UP);
    }

   public static BigDecimal calculateQ(final BigDecimal interestPercent) {

        return interestPercent.divide(YEAR, 10, RoundingMode.HALF_UP).add(BigDecimal.ONE);
    }
}
