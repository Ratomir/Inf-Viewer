package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import toolbar.ToolbarView;

public class View extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JPanel mainPanel = null;
	
	public ToolbarView toolbar = null;  
	
	public View()
	{
		//poziv konstruktora cije klase formiraju prikaz editora
		
		mainPanel = new JPanel(new BorderLayout());

		toolbar = new ToolbarView();
		
		this.add(mainPanel);
		
		this.getContentPane().add(mainPanel, BorderLayout.CENTER);
		this.getContentPane().add(toolbar, BorderLayout.NORTH);
		
		this.setLocation(150, 150);
		mainPanel.setPreferredSize(new Dimension(500,  500));
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		this.setTitle("Inf Viewer");
		this.setResizable(true);
		this.setType(Type.NORMAL);
		
		this.setVisible(true);
		this.pack(); //dynamic size of frame based to chiled component 
	}
}
