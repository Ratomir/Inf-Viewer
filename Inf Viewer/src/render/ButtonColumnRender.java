package render;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ButtonColumnRender extends DefaultTableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Podesavanje izgleda edit button
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int col)
	{
		JButton button = (JButton)value;
		button.setMargin(new Insets(5,5,5,5));
		
		try {
		    button.setIcon(new ImageIcon("icons/update.png"));
		  } catch (Exception ex) {
		    System.out.println(ex);
		  }
		
		button.setBorderPainted(false); 
		button.setContentAreaFilled(false); 
        button.setFocusPainted(false); 
        button.setOpaque(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setToolTipText(button.getText());
        button.setText("");

		return button;
	}

}
