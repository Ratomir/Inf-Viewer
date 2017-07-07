/**
 * 
 */
package menu;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * Klasa MenuItem sluzi za definisanje menuItem-a pri cemu se parametri salju preko konstruktora.
 * @author Ratomir
 *
 */
public class MenuItem extends JMenuItem
{
	private static final long serialVersionUID = 1L;

	public MenuItem(String text, ImageIcon icon, KeyStroke actionKey, String actionCommand, ActionListener actionListener)
	{
		this.setText(text);
		
		if(icon != null){
			this.setIcon(icon);	
		}
		
		if(actionKey != null){
			this.setAccelerator(actionKey);
		}
		
		this.setActionCommand(actionCommand);
		this.addActionListener(actionListener);
	}
}
