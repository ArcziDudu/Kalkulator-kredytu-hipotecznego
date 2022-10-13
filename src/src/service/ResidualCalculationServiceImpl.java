package service;

import model.InputData;
import model.MortgageResidual;
import model.Rate;
import model.RateAmounts;

import java.math.BigDecimal;

public class ResidualCalculationServiceImpl implements ResidualCalculationService {
    @Override
    public MortgageResidual calculate(RateAmounts rateAmounts, InputData inputData) {
        BigDecimal residualAmount = calculateResidualAmount(inputData.getAmount(), rateAmounts);
        BigDecimal residualDuration = inputData.getMonthsDuration().subtract(BigDecimal.ONE);
        return new MortgageResidual(residualAmount, residualDuration);
    }

    @Override
    public MortgageResidual calculate(RateAmounts rateAmounts, Rate previousRate) {
        MortgageResidual residual = previousRate.getMortgageResidual();
        BigDecimal previousDuration = previousRate.getMortgageResidual().getDuration();

        BigDecimal residualAmount = calculateResidualAmount(residual.getAmount(), rateAmounts);
        BigDecimal residualDuration = previousDuration.subtract(BigDecimal.ONE);
        return new MortgageResidual(residualAmount, residualDuration);
    }

    private static BigDecimal calculateResidualAmount(BigDecimal amount, RateAmounts rateAmounts) {
        return amount
                .subtract(rateAmounts.getCapitalAmount())
                .subtract(rateAmounts.getOverpayment().getAmount())
                .max(BigDecimal.ZERO);
    }
}
