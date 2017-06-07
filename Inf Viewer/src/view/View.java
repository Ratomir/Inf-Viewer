package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class View extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel mainPanel;
	
	public View()
	{
		//poziv konstruktora cije klase formiraju prikaz editora
		
		mainPanel = new JPanel(new BorderLayout());

		this.add(mainPanel);
		
		this.setVisible(true);
	}
}
