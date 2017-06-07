package toolbar;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JToolBar;

public class ToolbarView extends JToolBar implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ToolBarItem createMetaScheme;
	public ToolBarItem logOut;
	
	public ToolbarView()
	{
		createMetaScheme = new ToolBarItem(new ImageIcon("icons/new.png"), "New Meta scheme", "NewMetaSchemeT");
		createMetaScheme.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		add(createMetaScheme);
		
		createMetaScheme.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		NewConnectionMetaDialog dialog = new NewConnectionMetaDialog();
	}
}
