package model;

public class Brands {
	private Integer brandId;
	private String brandName;
	private byte[] brandIcon;
	private String describe;

	public Brands() {
		super();
	}

	public Brands(Integer brandId, String brandName, byte[] brandIcon, String describe) {
		super();
		this.brandId = brandId;
		this.brandName = brandName;
		this.brandIcon = brandIcon;
		this.describe = describe;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public byte[] getBrandIcon() {
		return brandIcon;
	}

	public void setBrandIcon(byte[] brandIcon) {
		this.brandIcon = brandIcon;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

}
