package pl.zajavka.mortgage.services;

import lombok.extern.slf4j.Slf4j;
import pl.zajavka.mortgage.model.InputData;
import pl.zajavka.mortgage.model.MortgageType;
import pl.zajavka.mortgage.model.Overpayment;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;
@Slf4j
public class ApplicationService {
    public static void startApplication(Scanner sc,
                                        Map<Integer, BigDecimal> overpaymentSchema,
                                        MortgageCalculationService mortgageCalculationService)
    {
        System.out.println("wpisz kwotę kredytu");
        String kwota = String.valueOf(sc.nextBigDecimal());

        log.info("user launched applications");
        log.info("User entered amount - [{}]", kwota);

        System.out.println("wpisz długość spłacania (w miesiącach)");
        String okres = String.valueOf(sc.nextBigDecimal());
        log.info("user entered repayment length - [{}]", okres);



        InputData defaultInputData = InputData.defaultInputData()
                .withAmount(new BigDecimal(kwota))
                .withMonthsDuration(BigDecimal.valueOf(Long.parseLong(okres)))
                .withOverpaymentReduceWay(Overpayment.REDUCE_PERIOD)
                .withRateType(MortgageType.DECREASING)
                .withOverpaymentSchema(overpaymentSchema);
        mortgageCalculationService.calculate(defaultInputData);
    }


}
