package service;

import model.InputData;
import model.MortgageReference;

public interface ReferenceCalculationService {
    MortgageReference calculate(InputData inputData);

}
