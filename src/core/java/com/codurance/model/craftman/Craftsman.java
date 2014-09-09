package com.codurance.model.craftman;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class Craftsman {
	private CraftsmanId id;
	private String name;

	public Craftsman(CraftsmanId id, String name) {
		this.id = id;
		this.name = name;
	}

	public CraftsmanId id() {
		return this.id;
	}

	public String name() {
		return name;
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
		return "Craftsman{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
