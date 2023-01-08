package pl.zajavka.mortgage;

import pl.zajavka.mortgage.model.InputData;
import pl.zajavka.mortgage.model.MortgageType;
import pl.zajavka.mortgage.model.Overpayment;
import pl.zajavka.mortgage.services.*;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class MortgageCalculator {

    public static void main(String[] args) {

        Map<Integer, BigDecimal> overpaymentSchema = new TreeMap<>();
        overpaymentSchema.put(5, BigDecimal.valueOf(12000));
        overpaymentSchema.put(19, BigDecimal.valueOf(10000));
        overpaymentSchema.put(28, BigDecimal.valueOf(11000));
        overpaymentSchema.put(64, BigDecimal.valueOf(16000));
        overpaymentSchema.put(78, BigDecimal.valueOf(18000));




        PrintingService printingService = new PrintingServiceImpl();
        RateCalculationService rateCalculationService = new RateCalculationServiceImpl(
            new TimePointCalculationServiceImpl(),
            new OverpaymentCalculationServiceImpl(),
            new AmountsCalculationServiceImpl(
                new ConstantAmountsCalculationServiceImpl(),
                new DecreasingAmountsCalculationServiceImpl()
            ),
            new ResidualCalculationServiceImpl(),
            new ReferenceCalculationServiceImpl()
        );

        MortgageCalculationService mortgageCalculationService = new MortgageCalculationServiceImpl(
            rateCalculationService,
            printingService,
            SummaryServiceFactory.create()
        );
        System.out.println("wpisz kwotę kredytu");
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String kwota = String.valueOf(sc.nextBigDecimal());
            System.out.println("wpisz długość spłacania (w miesiącach)");
            String okres = String.valueOf(sc.nextBigDecimal());

            InputData defaultInputData = InputData.defaultInputData()
                    .withAmount(new BigDecimal(kwota))
                    .withMonthsDuration(BigDecimal.valueOf(Long.parseLong(okres)))
                    .withOverpaymentReduceWay(Overpayment.REDUCE_PERIOD)
                    .withRateType(MortgageType.DECREASING)
                    .withOverpaymentSchema(overpaymentSchema);
            mortgageCalculationService.calculate(defaultInputData);
        }
    }
}
