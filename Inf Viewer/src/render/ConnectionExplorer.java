package render;

import java.awt.Font;
import java.awt.event.MouseListener;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionListener;
import javax.swing.plaf.FontUIResource;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import render.tree.TreeRenderer;

public class ConnectionExplorer extends JScrollPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTree connectionTree;
	private ConnectionExplorerModel model = null;
	
	private ConnectionExplorerController controller = null;
	
	public ConnectionExplorer(){
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Database");
    	model = new ConnectionExplorerModel(root);
    	
    	connectionTree = new JTree(model);
		connectionTree.setCellRenderer(new TreeRenderer());
    	connectionTree.setBorder(new EmptyBorder(2, 2, 2, 2));
    	connectionTree.getSelectionModel().setSelectionMode (TreeSelectionModel.SINGLE_TREE_SELECTION);
		
    	controller = new ConnectionExplorerController();
    	
		connectionTree.addTreeSelectionListener((TreeSelectionListener) controller);
		connectionTree.addMouseListener((MouseListener) controller);
    	
		connectionTree.setFont(new FontUIResource("Arial",Font.LAYOUT_LEFT_TO_RIGHT,16));
		setViewportView(connectionTree);
	}
	
	public void setTree(String path){
    	model.updateConnectionTreeModel(path);
    	model.reload();
	}
}
