package com.codurance.model.proposal;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class ClientId {
	private String id;

	public ClientId(String id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		return reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return reflectionHashCode(this);
	}

	public String stringValue() {
		return String.valueOf(id);
	}
}
