package pl.zajavka.mortgage;

import lombok.extern.slf4j.Slf4j;
import pl.zajavka.mortgage.services.*;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
@Slf4j
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
        System.out.println("Wpisz start aby rozpocząć");
        System.out.println("Wpisz exit aby zakończyć");

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String startLine = sc.nextLine();
            switch (startLine){
                case "start"->ApplicationService.startApplication(sc, overpaymentSchema, mortgageCalculationService);
                case "exit"->sc.close();
            }
        }
    }

    }

