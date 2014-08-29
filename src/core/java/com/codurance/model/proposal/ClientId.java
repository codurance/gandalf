package com.codurance.model.proposal;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class ClientId {
	private int value;

	public ClientId(int value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object obj) {
		return reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return reflectionHashCode(this);
	}

	public String asString() {
		return String.valueOf(value);
	}

	public int intValue() {
		return value;
	}
}
