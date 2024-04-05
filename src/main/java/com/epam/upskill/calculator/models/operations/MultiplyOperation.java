package com.epam.upskill.calculator.models.operations;

import com.epam.upskill.calculator.models.Result;

import java.math.BigDecimal;

public class MultiplyOperation extends OperationAbstract{

	@Override
	public Result perform() throws Exception {
		Result result = operandsValidator();
		if (!result.getError()) {
			result.setResult(operand1.multiply(operand2));
			result.setError(Boolean.FALSE);
		}
		return result;
	}

	@Override
	public String toString() {
		return super.toString("Multiply ");
	}

}
