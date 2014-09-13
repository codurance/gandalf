package com.codurance.model.proposal;

import com.codurance.model.craftsman.Craftsman;
import com.codurance.model.craftsman.CraftsmanId;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class ProposalCraftsmen {

	private List<Craftsman> craftsmen = new ArrayList<>();

	public ProposalCraftsmen() {
		this(new Craftsman[] {});
	}

	public ProposalCraftsmen(Craftsman[] craftsmen) {
		if (craftsmen != null) {
			this.craftsmen = asList(craftsmen);
		}
	}

	public static ProposalCraftsmen from(ProposalJson proposalJson) {
		return new ProposalCraftsmen(
						proposalJson.getArray("craftsmenInvolved").values().stream()
								.map((JsonValue json) -> craftsmanFrom(json))
								.toArray(size -> new Craftsman[size]));
	}

	public JsonArray json() {
		JsonArray craftsmenJson = new JsonArray();
		craftsmen.stream()
					.map((Craftsman craftsman) -> jsonFor(craftsman))
					.forEach((JsonObject craftsmanJson) -> craftsmenJson.add(craftsmanJson));
		return craftsmenJson;
	}

	private JsonObject jsonFor(Craftsman craftsman) {
		return new JsonObject()
				.add("id", craftsman.id().intValue())
				.add("name", craftsman.name());
	}

	private static Craftsman craftsmanFrom(JsonValue jsonCraftsman) {
		return new Craftsman(new CraftsmanId(jsonCraftsman.asObject().get("id").asInt()),
							 jsonCraftsman.asObject().get("name").asString());
	}

	@Override
	public String toString() {
		return craftsmen.toString();
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
