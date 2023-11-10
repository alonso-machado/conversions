package com.alonso.conversion.model.response;

import java.time.LocalDate;

// This is the Important Part of the Data that we get from the Fiscal Data API
public record FiscalData(String country_currency_desc, Double exchange_rate, LocalDate record_date) {
}
