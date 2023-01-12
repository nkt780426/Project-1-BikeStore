package model;

import java.sql.Date;

public class Staffs {
	private Integer staffId;
	private String firstName;
	private String lastName;
	private boolean sex;
	private Date birthDay;
	private String phone;
	private boolean active;
	private Integer storeId;
	private Integer managerId;
	private byte[] staffIcon;

	public Staffs() {
		super();
	}

	public Staffs(Integer staffId, String firstName, String lastName, boolean sex, Date birthDay, String phone,
			boolean active, Integer storeId, Integer managerId, byte[] staffIcon) {
		super();
		this.staffId = staffId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.sex = sex;
		this.birthDay = birthDay;
		this.phone = phone;
		this.active = active;
		this.storeId = storeId;
		this.managerId = managerId;
		this.staffIcon = staffIcon;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
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

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public byte[] getStaffIcon() {
		return staffIcon;
	}

	public void setStaffIcon(byte[] staffIcon) {
		this.staffIcon = staffIcon;
	}

}