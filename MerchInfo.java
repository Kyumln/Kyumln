package minkyu;

public class MerchInfo {
	private String merch_name;
	private int merch_barcode;
	private String merch_production;
	private int merch_price;
	private boolean merch_isadult;
	private String expiry_date;
	private int merch_stock;
	
	public MerchInfo(String name, String production, String isadult, int barcode, int price, String expiry_date, int merch_stock) {
		setMerch_name(name);
		setMerch_production(production);
		if(isadult.equals("예")) {
			setMerch_isadult(true);
		}else if(isadult.equals("아니오")) {
			setMerch_isadult(false);
		}
		setMerch_barcode(barcode);
		setMerch_price(price);
		setExpiry_date(expiry_date);
		setMerch_stock(merch_stock);
	}
	
	
	public String getMerch_name() {
		return merch_name;
	}
	public void setMerch_name(String merch_name) {
		this.merch_name = merch_name;
	}
	public int getMerch_barcode() {
		return merch_barcode;
	}
	public void setMerch_barcode(int merch_barcode) {
		this.merch_barcode = merch_barcode;
	}
	public String getMerch_production() {
		return merch_production;
	}
	public void setMerch_production(String merch_production) {
		this.merch_production = merch_production;
	}
	public int getMerch_price() {
		return merch_price;
	}
	public void setMerch_price(int merch_price) {
		this.merch_price = merch_price;
	}
	public boolean isMerch_isadult() {
		return merch_isadult;
	}
	public void setMerch_isadult(boolean merch_isadult) {
		this.merch_isadult = merch_isadult;
	}
	public String getExpiry_date() {
		return expiry_date;
	}
	public void setExpiry_date(String expiry_date) {
		this.expiry_date = expiry_date;
	}
	public int getMerch_stock() {
		return merch_stock;
	}
	public void setMerch_stock(int merch_stock) {
		this.merch_stock = merch_stock;
	}
	
}
