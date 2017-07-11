package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

public class StatusBarView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JLabel selectedConnection, state, currentRow;
	
	public StatusBarView(){
		setBorder(new TitledBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "Status bar", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setLayout(new BorderLayout());
		
		JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel statePanel = new JPanel(new GridLayout());
		state = new JLabel("Ready");
		statePanel.add(state);
		left.add(statePanel);
		
		JPanel center = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel element = new JPanel(new GridLayout());
		selectedConnection = new JLabel("Connection");
		element.add(selectedConnection);
		center.add(element);
		
		JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel row = new JPanel(new GridLayout());
		currentRow = new JLabel("");
		row.add(currentRow);
		right.add(currentRow);
		
		add(left, BorderLayout.WEST);
		add(center, BorderLayout.CENTER);
		add(right, BorderLayout.EAST);
	}
}
