package service;

import model.InputData;

public class PrintingServiceImpl implements PrintingService{
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

    private void printMessage(StringBuilder msg) {
        System.out.println(msg);
    }


}
