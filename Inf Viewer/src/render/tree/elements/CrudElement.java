package render.tree.elements;

import render.tree.TreeElement;

public class CrudElement extends TreeElement {

	private String createSProc = null;
	private String retrieveSProc = null;
	private String updateSProc = null;
	private String deleteSProc = null;

	public String getCreateSProc() {
		return createSProc;
	}

	public void setCreateSProc(String createSProc) {
		this.createSProc = createSProc;
	}

	public String getRetrieveSProc() {
		return retrieveSProc;
	}

	public void setRetrieveSProc(String retrieveSProc) {
		this.retrieveSProc = retrieveSProc;
	}

	public String getUpdateSProc() {
		return updateSProc;
	}

	public void setUpdateSProc(String updateSProc) {
		this.updateSProc = updateSProc;
	}

	public String getDeleteSProc() {
		return deleteSProc;
	}

	public void setDeleteSProc(String deleteSProc) {
		this.deleteSProc = deleteSProc;
	}
}
