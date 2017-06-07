package toolbar;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ToolBarItem extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public ToolBarItem(ImageIcon icon, String text, String actionCommand)
	{
		this.setIcon(icon);
		this.setToolTipText(text);
		this.setActionCommand(actionCommand);
	}
}
