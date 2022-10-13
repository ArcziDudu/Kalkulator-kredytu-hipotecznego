package service;

import model.InputData;
import model.Rate;
import model.Summary;

import java.util.List;

public interface PrintingService {
    //    constants needed for calculations and for printing information
    String INTEREST_SUM = "SUMA ODSETEK: ";
    String OVERPAYMENT_PROVISION = "PROWIZJA ZA NADPŁATY: ";
    String LOSTS_SUM = "SUMA STRAT: ";
    String OVERPAYMENT_REDUCE_RATE ="NADPŁATA, ZMNIEJSZENIE RATY";
    String OVERPAYMENT_REDUCE_PERIOD = "NADPŁATA, SKRÓCENIE OKRESU";
    String OVERPAYMENT_FREQUENCY = "SCHEMAT DOKONYWANIA NADPŁAT: ";


    String RATE_NUMBER = "NR: ";
    String YEAR = "ROK: ";
    String MONTH = "MIESIĄC: ";
    String DATE = "DATA: ";
    String MONTHS = " MIESIĘCY";
    String RATE = "RATA: ";
    String INTEREST = "ODSETKI: ";
    String CAPITAL = "KAPITAŁ: ";
    String OVERPAYMENT = " NADPŁATA: ";
    String LEFT_AMOUNT = "POZOSTAŁA KWOTA: ";
    String LEFT_MONTHS = "POZOSTAŁO MIESIĘCY: ";
    String MORTAGE_AMOUNT = "KWOTA KREDYTU: ";
    String MORTAGE_PERIOD = "OKRES KREDYTOWANIA: ";


    String CURRENCY = " Zł ";
    String NEW_LINE = "\n";
    String PERCENT = "%";

    //method for printing information
    void printInputDataInfo(final InputData inputData);

    void printRates(List<Rate> rates);


    void printSummary(Summary summary);
}
