package builders;

import com.codurance.model.craftman.Craftsman;
import com.codurance.model.craftman.CraftsmanId;

public class CraftsmanBuilder {

	private CraftsmanId id;
	private String name;

	public static CraftsmanBuilder aCraftsman() {
		return new CraftsmanBuilder();
	}

	public CraftsmanBuilder withId(int value) {
		this.id = new CraftsmanId(value);
		return this;
	}

	public CraftsmanBuilder withName(String name) {
		this.name = name;
		return this;
	}

	public Craftsman build() {
		Craftsman craftsman = new Craftsman(id, name);
		return craftsman;
	}
}
