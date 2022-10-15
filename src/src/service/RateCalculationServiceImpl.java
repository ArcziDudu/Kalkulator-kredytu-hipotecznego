package service;

import model.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

public class RateCalculationServiceImpl implements RateCalculationService {
    private final TimePointService timePointService;
    private final AmountsCalculationService amountsCalculationService;
    private final OverpaymentCalculationService overpaymentCalculationService;
    private final ResidualCalculationService residualCalculationService;
    private final ReferenceCalculationService referenceCalculationService;

    public RateCalculationServiceImpl(TimePointService timePointService
            , AmountsCalculationService amountsCalculationService
            , OverpaymentCalculationService overpaymentCalculationService
            , ResidualCalculationService residualCalculationService
            , ReferenceCalculationService referenceCalculationService) {
        this.timePointService = timePointService;
        this.amountsCalculationService = amountsCalculationService;
        this.overpaymentCalculationService = overpaymentCalculationService;
        this.residualCalculationService = residualCalculationService;
        this.referenceCalculationService = referenceCalculationService;
    }

    @Override
    public List<Rate> calculate(InputData inputData) {
        List<Rate> rates = new LinkedList<>();
        BigDecimal rateNumber = BigDecimal.ONE;
        Rate firstRate = calculateRate(rateNumber, inputData);
        rates.add(firstRate);
        Rate previousRate = firstRate;
        for (BigDecimal i = rateNumber.add(BigDecimal.ONE);
             i.compareTo(inputData.getMonthsDuration()) <= 0;
             i = i.add(BigDecimal.ONE)
        ) {
            Rate nextRate = calculateRate(i, inputData, previousRate);
            rates.add(nextRate);
            previousRate = nextRate;
            if(mortgageFinished(nextRate)){
                break;
            }
        }
        return rates;
    }

    private boolean mortgageFinished(Rate nextRate) {
        return BigDecimal.ZERO.equals(nextRate.getMortgageResidual().getAmount().setScale(0, RoundingMode.HALF_UP));
    }

    private Rate calculateRate(final BigDecimal rateNumber, final InputData inputData) {
        TimePoint timePoint = timePointService.calculate(rateNumber, inputData);
        Overpayment overpayment = overpaymentCalculationService.calculate(rateNumber, inputData);
        RateAmounts rateAmounts = amountsCalculationService.calculate(inputData, overpayment);
        MortgageResidual mortgageResidual = residualCalculationService.calculate(rateAmounts, inputData);
        MortgageReference mortgageReference = referenceCalculationService.calculate(inputData);

        return new Rate(rateNumber, timePoint, rateAmounts, mortgageResidual, mortgageReference);
    }

    private Rate calculateRate(BigDecimal rateNumber, InputData inputData, Rate previousRate) {
        TimePoint timePoint = timePointService.calculate(rateNumber, inputData);
        Overpayment overpayment  = overpaymentCalculationService.calculate(rateNumber, inputData);
        RateAmounts rateAmounts = amountsCalculationService.calculate(inputData,overpayment, previousRate);
        MortgageResidual mortgageResidual = residualCalculationService.calculate(rateAmounts, previousRate);
        MortgageReference mortgageReference = referenceCalculationService.calculate(inputData, rateAmounts, previousRate);

        return new Rate(rateNumber, timePoint, rateAmounts, mortgageResidual, mortgageReference);
    }
}
