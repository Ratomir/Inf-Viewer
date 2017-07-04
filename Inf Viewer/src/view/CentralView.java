package view;

import javax.swing.JScrollPane;

public class CentralView extends JScrollPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CentralView()
	{
		super(VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED);
	}
}
