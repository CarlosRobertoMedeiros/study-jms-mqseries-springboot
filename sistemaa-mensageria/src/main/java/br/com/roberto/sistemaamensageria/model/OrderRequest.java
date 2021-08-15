package br.com.roberto.sistemaamensageria.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderRequest {
	private String correlationId;
	private String message;
}
