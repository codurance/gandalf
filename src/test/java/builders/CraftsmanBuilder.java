package builders;

import com.codurance.model.craftsman.Craftsman;
import com.codurance.model.craftsman.CraftsmanId;

public class CraftsmanBuilder {

	private CraftsmanId id = new CraftsmanId(-1);
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
