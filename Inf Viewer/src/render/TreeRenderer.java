package render;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

public class TreeRenderer extends DefaultTreeCellRenderer  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Component getTreeCellRendererComponent
	(JTree tree, Object value, boolean isSelected, boolean expanded, boolean leaf, int row, boolean hasFocus)
	{
		
//		if (tree.getModel().getRoot() == value) 
//		{
//			setIcon(databaseIcon);
//		}
//		else if(value instanceof TreeElement.Package)
//		{
//			setIcon(packageIcon);
//		}
//		else if(value instanceof TreeElement.Table)
//		{
//			setIcon(tableIcon);
//		}
//		else if(value instanceof TreeElement.Column)
//		{
//			setIcon(columnIcon);
//		}
		
//		if(expanded)
//		{
//			setOpenIcon(openIcon);
//		}
//		else
//		{
//			setClosedIcon(closedIcon);
//		}
//		
//		
////		setLeafIcon(loadIcon(((BaseNode) value).getLeafIconResourceFilename()));
//		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
		return this;
	}
}
