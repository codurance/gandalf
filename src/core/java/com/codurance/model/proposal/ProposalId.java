package com.codurance.model.proposal;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class ProposalId {

	private int value;

	public static ProposalId proposalId(int id) {
		return new ProposalId(id);
	}

	public ProposalId(int id) {
		this.value = id;
	}

	public ProposalId(String id) {
		this.value = Integer.valueOf(id);
	}

	public int intValue() {
		return value;
	}

	public String asString() {
		return String.valueOf(value);
	}

	@Override
	public String toString() {
		return "ProposalId{" +
				"value='" + value + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		return reflectionEquals(this, o);
	}

	@Override
	public int hashCode() {
		return reflectionHashCode(this);
	}

}
