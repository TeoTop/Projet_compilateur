
public abstract class Ident {
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public abstract boolean isVar();
	
	@Override
	public String toString() {
		return "Ident [type=" + type + "]";
	}



}
