/**
 * 
 */
package main;

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
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e)
		{
		}
		
		
		new LoginView();
	}

}
