/**
 * 
 */
package main;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

import view.login.LoginView;

/**
 * @author pc
 *
 */
public class MainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Icon closedIcon = new ImageIcon("icons/explorer/closed.png");
	    Icon openIcon = new ImageIcon("icons/explorer/open.png");
	    
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager.put("Tree.openIcon", openIcon);
			UIManager.put("Tree.closedIcon", closedIcon);
		}
		catch(Exception e)
		{
		}
		
		
		new LoginView();
	}

}
