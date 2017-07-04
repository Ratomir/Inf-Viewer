package render;

import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

public class ConnectionExplorer extends JScrollPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTree connectionTree;
	private TreeRenderer treeRenderer = null;
	private ConnectionExplorerModel model = null;
	
	public void setTree(String path){
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Database");
    	model = new ConnectionExplorerModel(root, path);
    	
    	connectionTree = new JTree(model);
    	connectionTree.setBorder(new EmptyBorder(2, 2, 2, 2));
    	connectionTree.setRowHeight(33);
    	connectionTree.getSelectionModel().setSelectionMode (TreeSelectionModel.SINGLE_TREE_SELECTION);
		
//		connectionTree.addTreeSelectionListener((TreeSelectionListener) controller);
//		connectionTree.addMouseListener((MouseListener) controller);
		
		connectionTree.setCellRenderer(treeRenderer);
		connectionTree.setFont(new FontUIResource("Arial",Font.LAYOUT_LEFT_TO_RIGHT,16));
		setViewportView(connectionTree);	
	}
}
