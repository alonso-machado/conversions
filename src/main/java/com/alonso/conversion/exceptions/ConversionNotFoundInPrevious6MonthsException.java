package com.alonso.conversion.exceptions;

public class ConversionNotFoundInPrevious6MonthsException extends RuntimeException {
	public ConversionNotFoundInPrevious6MonthsException() {
		super("Purchase Conversion with this ID cannot be Converted within 6 months of Purchase Date!");
	}
}
