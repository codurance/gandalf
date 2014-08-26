package builders;

import com.codurance.model.proposal.Contact;

public class ContactBuilder {

	private String name;
	private String email;

	public static ContactBuilder aContact() {
		return new ContactBuilder();
	}

	public ContactBuilder withName(String name) {
		this.name = name;
		return this;
	}

	public ContactBuilder withEmail(String email) {
		this.email = email;
		return this;
	}

	public Contact build() {
		return new Contact(name, email);
	}

}
