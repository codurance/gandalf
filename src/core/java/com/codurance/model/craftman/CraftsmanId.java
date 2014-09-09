package com.codurance.model.craftman;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class CraftsmanId {

	private int value;

	public CraftsmanId(int value) {
		this.value = value;
	}

	public int intValue() {
		return value;
	}

	@Override
	public boolean equals(Object obj) {
		return reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return reflectionHashCode(this);
	}

	@Override
	public String toString() {
		return "CraftsmanId{" +
				"value=" + value +
				'}';
	}
}
