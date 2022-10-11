package service;

import model.InputData;

public interface PrintingService {
    //    constants needed for calculations and for printing information
    String INTEREST_SUM = "SUMA ODSETEK: ";
    String RATE_NUMBER = "NR: ";
    String YEAR = "ROK: ";
    String MONTH = "MIESIĄC: ";
    String DATE = "DATA: ";
    String MONTHS = " MIESIĘCY";
    String RATE = "RATA: ";
    String INTEREST = "OPROCENTOWANIE: ";
    String CAPITAL = "KAPITAŁ: ";
    String LEFT = "POZOSTAŁO: ";
    String MORTAGE_AMOUNT = "KWOTA KREDYTU: ";
    String MORTAGE_PERIOD = "OKRES KREDYTOWANIA: ";


    String CURRENCY = " Zł ";
    String NEW_LINE = "\n";
    String PERCENT = "%";

    //method for printing information
    void printInputDataInfo(final InputData inputData);
}
