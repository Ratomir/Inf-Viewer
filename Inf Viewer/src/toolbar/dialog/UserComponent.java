package toolbar.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;

import DAL.DbCollections;
import render.TableDbView;

public class UserComponent extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JComboBox comboBox;
	
	public UserComponent(){
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		//Kreiranje komandong panela
		JPanel newCommandPanel = new JPanel();
		
		JButton newButton = new JButton("New");
		
		newCommandPanel.add(newButton);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"10", "20", "50"}));
		comboBox.setSelectedIndex(0);
		
		newCommandPanel.add(comboBox);
		
		//Kreiranje panela sa tabelom
		
		TableDbView newTableView = new TableDbView(DbCollections.User);
		
		mainPanel.add(newTableView, BorderLayout.CENTER);
		
		mainPanel.add(newCommandPanel, BorderLayout.NORTH);
		
		this.getContentPane().add(mainPanel);
		
		setSize(600, 400);
		setMinimumSize(new Dimension(400, 400));
		setPreferredSize(new Dimension(600, 400));
		setLocationRelativeTo(null);
		setModal(true);
		pack();
		setVisible(true);
	}
	
}
