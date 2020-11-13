package entity;

public class Medic extends AbstractEntity{
	private String name;
	private MedicType type;
	private String sizeType;
	public Medic() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Medic(String name, MedicType type, String sizeType) {
		super();
		this.name = name;
		this.type = type;
		this.sizeType = sizeType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public MedicType getType() {
		return type;
	}
	public void setType(MedicType type) {
		this.type = type;
	}
	public String getsizeType() {
		return sizeType;
	}
	public void setsizeType(String sizeType) {
		this.sizeType = sizeType;
	}
	@Override
	public String toString() {
		return name + ", " + type;
	}

	
	
}
