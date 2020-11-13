package entity;

import java.sql.Date;

public class Warehouse extends AbstractEntity{
	private Medic medic;
	private int count;
	private Date dateGetting;
	private Date dateDead;
	private float priceGetting;
	private float pricaeSelling;
	public Warehouse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Warehouse(Medic medic, int count, Date dateGetting, Date dateDead, float priceGetting, float pricaeSelling) {
		super();
		this.medic = medic;
		this.count = count;
		this.dateGetting = dateGetting;
		this.dateDead = dateDead;
		this.priceGetting = priceGetting;
		this.pricaeSelling = pricaeSelling;
	}
	public Medic getMedic() {
		return medic;
	}
	public void setMedic(Medic medic) {
		this.medic = medic;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Date getDateGetting() {
		return dateGetting;
	}
	public void setDateGetting(Date dateGetting) {
		this.dateGetting = dateGetting;
	}
	public Date getDateDead() {
		return dateDead;
	}
	public void setDateDead(Date dateDead) {
		this.dateDead = dateDead;
	}
	public float getPriceGetting() {
		return priceGetting;
	}
	public void setPriceGetting(float priceGetting) {
		this.priceGetting = priceGetting;
	}
	public float getPricaeSelling() {
		return pricaeSelling;
	}
	public void setPricaeSelling(float pricaeSelling) {
		this.pricaeSelling = pricaeSelling;
	}
	@Override
	public String toString() {
		return "medic=" + medic + ", count=" + count + ", dateGetting=" + dateGetting + ", dateDead="
				+ dateDead + ", priceGetting=" + priceGetting + ", pricaeSelling=" + pricaeSelling;
	}
	
}
