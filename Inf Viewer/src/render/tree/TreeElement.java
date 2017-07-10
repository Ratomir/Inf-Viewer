package render.tree;

import java.util.Vector;

/**
 * 
 * Objekat cvor stablo strukture.
 * 
 * @author Ratomir
 *
 */
public abstract class TreeElement {
	protected String code = null;
	protected String name = null;

	private Vector<TreeElement> treeElements = new Vector<>();

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}

	public int getIndexOfElement(TreeElement el) {
		return treeElements.indexOf(el);
	}

	public void addElement(TreeElement element) {
		treeElements.add(element);
	}

	public Vector<TreeElement> getAllElements() {
		return this.treeElements;
	}

	public TreeElement getElementAt(int indeks) {
		return treeElements.elementAt(indeks);
	}
	
}
