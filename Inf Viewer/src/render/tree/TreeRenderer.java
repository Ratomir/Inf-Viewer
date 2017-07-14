package render.tree;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

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
import render.tree.elements.KeyElement;
import render.tree.elements.ReferencesElement;
import render.tree.elements.RootElement;
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

	Icon rootIcon = new ImageIcon("icons/explorer/root.png");

	Icon databaseIcon = new ImageIcon("icons/explorer/database.png");

	Icon tableMainIcon = new ImageIcon("icons/explorer/table-main.png");
	Icon tableIcon = new ImageIcon("icons/explorer/table.png");
	
	Icon keysIcon = new ImageIcon("icons/explorer/key.png");

	Icon storeProcedure = new ImageIcon("icons/explorer/storeprocedure.png");

	Icon columnMainIcon = new ImageIcon("icons/explorer/column-main.png");
	Icon columnIcon = new ImageIcon("icons/explorer/column.png");

	Icon crudMainIcon = new ImageIcon("icons/explorer/crud-main.png");
	Icon crudIcon = new ImageIcon("icons/explorer/crud.png");

	Icon closedIcon = new ImageIcon("icons/explorer/closed.png");
	Icon openIcon = new ImageIcon("icons/explorer/open.png");
	Icon referencesIcon = new ImageIcon("icons/explorer/references.png");

	Color backgroundSelectionColor;
	Color backgroundNonSelectionColor;

	public TreeRenderer() {
		renderer.add(horizontalBox);

		horizontalBox.add(icon);

		Component horizontalStrut = Box.createHorizontalStrut(3);
		horizontalBox.add(horizontalStrut);
		horizontalBox.add(textLabel);

		setClosedIcon(closedIcon);
		setOpenIcon(openIcon);

		DefaultTreeCellRenderer defaultRenderer = new DefaultTreeCellRenderer();
		backgroundSelectionColor = defaultRenderer.getBackgroundSelectionColor();
		backgroundNonSelectionColor = defaultRenderer.getBackgroundNonSelectionColor();
	}

	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean isSelected, boolean expanded,
			boolean leaf, int row, boolean hasFocus) {

		super.getTreeCellRendererComponent(tree, value, isSelected, expanded, leaf, row, hasFocus);

		if (tree.getModel().getRoot() == value) {
			RootElement e = (RootElement) value;
			textLabel.setText(e.getName());
			icon.setIcon(rootIcon);
		} else if (value instanceof ConnectionElement) {
			ConnectionElement e = (ConnectionElement) value;
			textLabel.setText(e.getName());
			icon.setIcon(databaseIcon);
		} else if (value instanceof StoreProcedureElement) {
			StoreProcedureElement e = (StoreProcedureElement) value;
			textLabel.setText(e.getName());
			icon.setIcon(storeProcedure);
		} else if (value instanceof ReferencesElement) {
			ReferencesElement e = (ReferencesElement) value;
			textLabel.setText(e.getRefColumn() + ", " + e.getRefTable());
			icon.setIcon(referencesIcon);
		} else if (value instanceof ColumnElement) {
			ColumnElement e = (ColumnElement) value;
			textLabel.setText(e.getName());

			if (e.getName() == "Columns") {
				icon.setIcon(columnMainIcon);
			} else {
				icon.setIcon(columnIcon);
			}
		} else if (value instanceof TableElement) {
			TableElement e = (TableElement) value;
			textLabel.setText(e.getName());

			if (e.getName() == "Tables") {
				icon.setIcon(tableMainIcon);
			} else {
				icon.setIcon(tableIcon);
			}
		} else if (value instanceof CrudElement) {
			CrudElement e = (CrudElement) value;
			textLabel.setText(e.getCode() == null ? e.getName() : (e.getCode() + ": " + e.getName()));

			if (e.getName() == "CRUD") {
				icon.setIcon(crudMainIcon);
			} else {
				icon.setIcon(crudIcon);
			}
		}
		else if (value instanceof KeyElement) {
			KeyElement e = (KeyElement) value;
			textLabel.setText(e.getName());
			icon.setIcon(keysIcon);
		}

		// if (expanded) {
		// setOpenIcon(openIcon);
		// } else {
		// setClosedIcon(closedIcon);
		// }

		if (isSelected) {
			renderer.setBackground(backgroundSelectionColor);
		} else {
			renderer.setBackground(backgroundNonSelectionColor);
		}

		renderer.setEnabled(tree.isEnabled());

		return renderer;
	}
}
