package model;

import java.math.BigDecimal;

public class Products {
	private Integer productId;
	private String productName;
	private Integer brandId;
	private Integer categoryId;
	private Integer modelYear;
	private BigDecimal listPrice;
	private byte[] productIcon;

	public Products() {
		super();
	}

	public Products(Integer productId, String productName, Integer brandId, Integer categoryId, Integer modelYear,
			BigDecimal listPrice, byte[] productIcon) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.brandId = brandId;
		this.categoryId = categoryId;
		this.modelYear = modelYear;
		this.listPrice = listPrice;
		this.productIcon = productIcon;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getModelYear() {
		return modelYear;
	}

	public void setModelYear(Integer modelYear) {
		this.modelYear = modelYear;
	}

	public BigDecimal getListPrice() {
		return listPrice;
	}

	public void setListPrice(BigDecimal listPrice) {
		this.listPrice = listPrice;
	}

	public byte[] getProductIcon() {
		return productIcon;
	}

	public void setProductIcon(byte[] productIcon) {
		this.productIcon = productIcon;
	}

	
}
