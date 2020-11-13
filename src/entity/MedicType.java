package entity;

public class MedicType extends AbstractEntity{
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public MedicType(String type) {
		super();
		this.type = type;
	}

	public MedicType() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return type;
	}

	
	
}
