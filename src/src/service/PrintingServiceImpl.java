package service;

import model.InputData;
import model.Rate;
import model.Summary;

import java.util.List;

public class PrintingServiceImpl implements PrintingService {
    // class with method from interface to print info on screen
    @Override
    public void printInputDataInfo(InputData inputData) {
        StringBuilder msg = new StringBuilder(NEW_LINE);
        msg.append(MORTAGE_AMOUNT).append(inputData.getAmount()).append(CURRENCY);
        msg.append(NEW_LINE);
        msg.append(MORTAGE_PERIOD).append(inputData.getMonthsDuration()).append(MONTHS);
        msg.append(NEW_LINE);
        msg.append(INTEREST).append(inputData.getInterestDisplay()).append(PERCENT);
        msg.append(NEW_LINE);
        printMessage(msg);
    }

    @Override
    public void printRates(List<Rate> rates) {
        String format = "%4s %4s "+
        "%4s %4s "+
        "%4s %4s "+
        "%4s %4s "+
        "%4s %4s "+
        "%4s %4s "+
        "%4s %4s "+
        "%4s %4s "+
        "%4s %4s ";
        for (Rate rate : rates) {
            String message = String.format(format,
                    RATE_NUMBER, rate.getRateNumber(),
                    DATE, rate.getTimePoint().getDate(),
                    YEAR, rate.getTimePoint().getYear(),
                    MONTH, rate.getTimePoint().getMonth(),
                    RATE, rate.getRateAmounts().getRateAmount(),
                    INTEREST, rate.getRateAmounts().getInterestAmount(),
                    CAPITAL, rate.getRateAmounts().getCapitalAmount(),
                    LEFT_AMOUNT, rate.getMortgageResidual().getAmount(),
                    LEFT_MONTHS, rate.getMortgageResidual().getDuration()
            );
            printMessage(message);
            if(rate.getRateNumber().intValue()%12==0){
                System.out.println();
            }
        }
    }

    @Override
    public void printSummary(Summary summary) {
        StringBuilder msg = new StringBuilder(NEW_LINE);
        msg.append(INTEREST_SUM).append(summary.getInterestSum()).append(CURRENCY);
        msg.append(NEW_LINE);
        printMessage(msg.toString());

    }

    private void printMessage(StringBuilder msg) {
        System.out.println(msg);
    }
private void printMessage(String message){
    System.out.println(message);
}

}
