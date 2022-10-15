package service;

import model.InputData;
import model.Overpayment;
import model.Rate;
import model.Summary;

import java.util.List;
import java.util.Optional;

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

        Optional.of(inputData.getOverpaymentSchema())
                        .filter(schema -> schema.size()>0)
                                .ifPresent(schema -> logOverpayment(msg, inputData));

        printMessage(msg);
    }

    private void logOverpayment(StringBuilder msg, InputData inputData) {
        switch (inputData.getOverpaymentReduceWay()) {
            case Overpayment.REDUCE_PERIOD -> msg.append(OVERPAYMENT_REDUCE_PERIOD);
            case Overpayment.REDUCE_RATE -> msg.append(OVERPAYMENT_REDUCE_RATE);
            default -> throw new MortgageException();
        }
        msg.append(NEW_LINE);
        msg.append(OVERPAYMENT_FREQUENCY).append(inputData.getOverpaymentSchema());
        msg.append(NEW_LINE);
    }



    @Override
    public void printRates(List<Rate> rates) {
        String format = "%1s %1s| "+
        "%4s %4s| "+
        "%4s %2s| "+
        "%4s %2s| "+
        "%4s %8s| "+
        "%7s %8s| "+
        "%7s %6s| "+
        "%7s %6s| "+
        "%7s %6s| "+
        "%7s %2s| ";
        for (Rate rate : rates) {
            String message = String.format(format,
                    RATE_NUMBER, rate.getRateNumber(),
                    DATE, rate.getTimePoint().getDate(),
                    YEAR, rate.getTimePoint().getYear(),
                    MONTH, rate.getTimePoint().getMonth(),
                    RATE, rate.getRateAmounts().getRateAmount(),
                    INTEREST, rate.getRateAmounts().getInterestAmount(),
                    CAPITAL, rate.getRateAmounts().getCapitalAmount(),
                    OVERPAYMENT, rate.getRateAmounts().getOverpayment().getAmount(),
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
        msg.append(OVERPAYMENT_PROVISION).append(summary.getOverpaymentProvisionSum()).append(CURRENCY);
        msg.append(NEW_LINE);
        msg.append(LOSTS_SUM).append(summary.getTotalLosts()).append(CURRENCY);
        msg.append(NEW_LINE);

    }

    private void printMessage(StringBuilder msg) {
        System.out.println(msg);
    }
private void printMessage(String message){
    System.out.println(message);
}

}
