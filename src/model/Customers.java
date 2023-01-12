package model;

import java.sql.Date;

public class Customers {
	private int customerId;
	private String firstName;
	private String lastName;
	private boolean sex;
	private Date birthDay;
	private String phone;
	private String email;
	private String address;
	private byte[] customerIcon;

	public Customers() {
		super();
	}

	public Customers(int customerId, String firstName, String lastName, boolean sex, Date birthDay, String phone,
			String email, String address, byte[] customerIcon) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.sex = sex;
		this.birthDay = birthDay;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.customerIcon = customerIcon;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
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

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean getSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
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

	public byte[] getCustomerIcon() {
		return customerIcon;
	}

	public void setCustomerIcon(byte[] customerIcon) {
		this.customerIcon = customerIcon;
	}

}
