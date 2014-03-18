/**
 * 
 * @author CHAPON Theo
 * @author El Omari Alaoui Hassan
 * @author Marchais Julien
 * @author Mesdouri Marouan 
 * @author Tuekam Sandjon Marlene
 * 
 * @version 1.0
 */
public class IdVar extends Ident {
	private int offset;

	/**
	 * 
	 * @return
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * 
	 * @param offset
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}

	/**
	 * 
	 */
	public boolean isVar() {
		return true;
	}

	@Override
	/**
	 * 
	 */
	public String toString() {
		return "IdVar [offset=" + offset + " Type="+ getType() +"]";
	}


}
