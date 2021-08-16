package br.gov.caixa.siati.spring.ibmmq.jms;

import br.gov.caixa.siati.spring.ibmmq.jms.utils.JsonUtils;

public class Request<T> {

	private String correlationID;

	private T payload;
	
	public Request() {
		
	}

	public Request(String correlationID, T payload) {
		this.correlationID = correlationID;
		this.payload = payload;
	}

	public String getCorrelationID() {
		return correlationID;
	}

	public T getPayload() {
		return payload;
	}

	public String toJsonString() {
		return JsonUtils.toJsonString(this);
	}

	public static Request fromJsonString(String value) {
		return JsonUtils.fromJsonString(value, Request.class);
	}

	public static class Builder {

		private String correlationID;

		private Object payload;

		public static Builder get() {
			return new Builder();
		}

		public Builder correlationID(String correlationID) {
			this.correlationID = correlationID;
			return this;
		}

		public Builder payload(Object payload) {
			this.payload = payload;
			return this;
		}

		public Request build() {
			return new Request(correlationID, payload);
		}

	}

}
