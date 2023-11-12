package com.alonso.conversion.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Fiscal {
	private List<FiscalData> data;
	private MetaData meta;
	private Links links;
}

