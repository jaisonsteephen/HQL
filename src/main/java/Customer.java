import java.util.List;
import java.util.Set;

public class Customer {
	int id;
	String firstName;
	String lastName;
	
	Set<Contact> contactLt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public Set<Contact> getContactLt() {
		return contactLt;
	}

	public void setContactLt(Set<Contact> contactLt) {
		this.contactLt = contactLt;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
