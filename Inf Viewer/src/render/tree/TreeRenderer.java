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
import javax.swing.tree.DefaultTreeCellRenderer;

import render.tree.elements.ColumnElement;
import render.tree.elements.ConnectionElement;
import render.tree.elements.CrudElement;
import render.tree.elements.ReferencesElement;
import render.tree.elements.StoreProcedureElement;
import render.tree.elements.TableElement;

public class TreeRenderer extends DefaultTreeCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JLabel textLabel = new JLabel(" ");

	JLabel icon = new JLabel();

	JPanel renderer = new JPanel(new BorderLayout());
	Box horizontalBox = Box.createHorizontalBox();

	DefaultTreeCellRenderer defaultRenderer = new DefaultTreeCellRenderer();

	Icon databaseIcon = new ImageIcon("icons/explorer/database.png");
	Icon tableIcon = new ImageIcon("icons/explorer/table.png");
	Icon storeProcedure = new ImageIcon("icons/explorer/storeprocedure.png");
	
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

		super.getTreeCellRendererComponent(tree, value, isSelected, expanded, leaf, row, hasFocus);

		Component returnValue = null;

		if (tree.getModel().getRoot() == value) {
			ConnectionElement e = (ConnectionElement) value;
			textLabel.setText(e.getName());
			icon.setIcon(databaseIcon);
			if (isSelected) {
				renderer.setBackground(backgroundSelectionColor);
			} else {
				renderer.setBackground(backgroundNonSelectionColor);
			}

			renderer.setEnabled(tree.isEnabled());
			returnValue = renderer;
		} else if (value instanceof StoreProcedureElement) {
			StoreProcedureElement e = (StoreProcedureElement) value;
			textLabel.setText(e.getName());
			icon.setIcon(storeProcedure);
			if (isSelected) {
				renderer.setBackground(backgroundSelectionColor);
			} else {
				renderer.setBackground(backgroundNonSelectionColor);
			}
			renderer.setEnabled(tree.isEnabled());
			returnValue = renderer;
		} else if (value instanceof TableElement) {
			TableElement e = (TableElement) value;
			textLabel.setText(e.getName());
			icon.setIcon(tableIcon);
			if (isSelected) {
				renderer.setBackground(backgroundSelectionColor);
			} else {
				renderer.setBackground(backgroundNonSelectionColor);
			}
			renderer.setEnabled(tree.isEnabled());
			returnValue = renderer;
		} else if (value instanceof ColumnElement) {
			ColumnElement e = (ColumnElement) value;
			textLabel.setText(e.getName());
			icon.setIcon(tableIcon);
			if (isSelected) {
				renderer.setBackground(backgroundSelectionColor);
			} else {
				renderer.setBackground(backgroundNonSelectionColor);
			}
			renderer.setEnabled(tree.isEnabled());
			returnValue = renderer;
		} else if (value instanceof CrudElement) {
			CrudElement e = (CrudElement) value;
			
			textLabel.setText(e.getCode() == null ? e.getName() : (e.getCode() + ": " + e.getName()));
			icon.setIcon(tableIcon);
			if (isSelected) {
				renderer.setBackground(backgroundSelectionColor);
			} else {
				renderer.setBackground(backgroundNonSelectionColor);
			}
			renderer.setEnabled(tree.isEnabled());
			returnValue = renderer;
		} else if (value instanceof ReferencesElement) {
			ReferencesElement e = (ReferencesElement) value;
			textLabel.setText(e.getRefColumn() + ", " + e.getRefTable());
			icon.setIcon(tableIcon);
			if (isSelected) {
				renderer.setBackground(backgroundSelectionColor);
			} else {
				renderer.setBackground(backgroundNonSelectionColor);
			}
			renderer.setEnabled(tree.isEnabled());
			returnValue = renderer;
		}

		return returnValue;
	}
}
