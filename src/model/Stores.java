package model;

public class Stores {
	private Integer storeId; // primary identity
	private String storeName;
	private String phone;
	private String email;
	private String address;
	private byte[] storeIcon;

	public Stores() {
		super();
	}

	public Stores(Integer storeId, String storeName, String phone, String email, String address, byte[] storeIcon) {
		super();
		this.storeId = storeId;
		this.storeName = storeName;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.storeIcon = storeIcon;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public byte[] getStoreIcon() {
		return storeIcon;
	}

	public void setStoreIcon(byte[] storeIcon) {
		this.storeIcon = storeIcon;
	}

}