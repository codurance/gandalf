package com.codurance.model.proposal;

import static java.lang.String.valueOf;
import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class ProposalId {

	private String value;

	public ProposalId(String value) {
		this.value = value;
	}

	public ProposalId(int id) {
		this.value = valueOf(id);
	}

	@Override
	public String toString() {
		return value;
	}

	@Override
	public boolean equals(Object o) {
		return reflectionEquals(this, o);
	}

	@Override
	public int hashCode() {
		return reflectionHashCode(this);
	}

	public static ProposalId proposalId(String id) {
		return new ProposalId(id);
	}
}
