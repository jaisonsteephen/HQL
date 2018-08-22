
public class Contact {
	int id;
	int customerFkid;
	String info;
	String type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerFkid() {
		return customerFkid;
	}

	public void setCustomerFkid(int customerFkid) {
		this.customerFkid = customerFkid;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
