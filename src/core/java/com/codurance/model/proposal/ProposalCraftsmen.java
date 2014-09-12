package com.codurance.model.proposal;

import com.codurance.model.craftsman.Craftsman;
import com.codurance.model.craftsman.CraftsmanId;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static java.util.Arrays.stream;
import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class ProposalCraftsmen {

	private Craftsman[] craftsmen;

	public ProposalCraftsmen() {
		this(new Craftsman[] {});
	}

	public ProposalCraftsmen(Craftsman[] craftsmen) {
		this.craftsmen = (craftsmen != null) ? craftsmen : new Craftsman[] {};
	}

	public static ProposalCraftsmen from(ProposalJson proposalJson) {
		List<Craftsman> craftsmen = new ArrayList<>();
		Iterator<JsonValue> jsonCraftsmen = proposalJson.getArray("craftsmenInvolved").iterator();
		while (jsonCraftsmen.hasNext()) {
			JsonObject jsonCraftsman = jsonCraftsmen.next().asObject();
			craftsmen.add(new Craftsman(new CraftsmanId(jsonCraftsman.get("id").asInt()),
										jsonCraftsman.get("name").asString()));
		}
		return new ProposalCraftsmen(craftsmen.toArray(new Craftsman[craftsmen.size()]));
	}

	public JsonArray json() {
		JsonArray craftsmenJson = new JsonArray();
		stream(craftsmen).forEach(craftsman -> addCraftsmanJson(craftsmenJson, craftsman));
		return craftsmenJson;
	}

	private void addCraftsmanJson(JsonArray craftsmenJson, Craftsman craftsman) {
		craftsmenJson.add(new JsonObject()
				.add("id", craftsman.id().intValue())
				.add("name", craftsman.name()));
	}

	@Override
	public String toString() {
		return Arrays.toString(craftsmen);
	}

	@Override
	public boolean equals(Object obj) {
		return reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return reflectionHashCode(this);
	}

}
