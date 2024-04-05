package com.epam.upskill.calculator.models.operations;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.epam.upskill.calculator.models.Result;
import com.epam.upskill.calculator.util.Constants;

public class DivideOperation extends OperationAbstract {

	private final BigDecimal ZERO = new BigDecimal(0);
	private final int SCALE = 10; // Set the scale for rounding
	
	@Override
	public Result perform() throws Exception {
		Result result = operandsValidator();
		if (!result.getError()) {
			if (operand2.compareTo(ZERO) == 0) {
				result.setError(Boolean.TRUE);
				result.setMessage(Constants.ERROR_MESSAGE_DIVIDE_BY_ZERO);
				getLogger().error("Can't divide by Zero");
			} else {
				BigDecimal divisionResult = operand1.divide(operand2, 10, RoundingMode.CEILING);
				if (divisionResult.stripTrailingZeros().scale() <= 0) {
					divisionResult = divisionResult.setScale(0, RoundingMode.DOWN);
				}
				result.setResult(divisionResult);
				result.setError(Boolean.FALSE);
			}
		}
		return result;
	}

	@Override
	public String toString() {
		return super.toString("Divide ");
	}

}
