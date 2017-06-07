/**
 * 
 */
package main;

import javax.swing.UIManager;

import view.View;

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
		
		
		new View();
	}

}
