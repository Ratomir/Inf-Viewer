package render.tree;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

import meta_db_model.relation_db.Connection;
import meta_db_model.relation_db.Table;

public class TreeRenderer implements TreeCellRenderer {

	JLabel textLabel = new JLabel(" ");

	JLabel icon = new JLabel();
	
	JPanel renderer = new JPanel(new BorderLayout());
	Box horizontalBox = Box.createHorizontalBox();
	
	DefaultTreeCellRenderer defaultRenderer = new DefaultTreeCellRenderer();

	Icon databaseIcon = new ImageIcon("icons/explorer/database.png");
	Icon tableIcon = new ImageIcon("icons/explorer/table.png");

	Color backgroundSelectionColor;
	Color backgroundNonSelectionColor;

	public TreeRenderer() {
		
		renderer.add(horizontalBox);
		
		horizontalBox.add(icon);
		horizontalBox.add(textLabel);
		
		backgroundSelectionColor = defaultRenderer.getBackgroundSelectionColor();
		backgroundNonSelectionColor = defaultRenderer.getBackgroundNonSelectionColor();
	}

	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean isSelected, boolean expanded,
			boolean leaf, int row, boolean hasFocus) {

		Component returnValue = null;
		if ((value != null) && (value instanceof DefaultMutableTreeNode)) {
			Object userObject = ((DefaultMutableTreeNode) value).getUserObject();
			if (userObject instanceof Connection) {
				Connection e = (Connection) userObject;
				textLabel.setText(e.getConnectionName());
				icon.setIcon(databaseIcon);
				if (isSelected) {
					renderer.setBackground(backgroundSelectionColor);
				} else {
					renderer.setBackground(backgroundNonSelectionColor);
				}
				
				renderer.setEnabled(tree.isEnabled());
				returnValue = renderer;
			}
			else if(userObject instanceof Table){
				Table e = (Table) userObject;
				textLabel.setText(e.getName());
				icon.setIcon(tableIcon);
				if (isSelected) {
					renderer.setBackground(backgroundSelectionColor);
				} else {
					renderer.setBackground(backgroundNonSelectionColor);
				}
				renderer.setEnabled(tree.isEnabled());
				returnValue = renderer;
			}
		}
		
		if (returnValue == null) {
			returnValue = defaultRenderer.getTreeCellRendererComponent(tree, value, isSelected, expanded, leaf, row,
					hasFocus);
		}
		return returnValue;
	}
}
