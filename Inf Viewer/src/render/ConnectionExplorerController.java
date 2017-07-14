package render;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

import render.tree.TreeElement;
import render.tree.elements.ConnectionElement;
import render.tree.elements.TableElement;

public class ConnectionExplorerController implements MouseListener, TreeSelectionListener {

	private JTree tree;
	private Object node;
	
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		tree = (JTree) e.getSource();
		node = tree.getLastSelectedPathComponent();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if ((e.getSource() instanceof JTree) && (tree != null))
		{
			node = tree.getLastSelectedPathComponent();

			//
			// Klik na tabelu u stablu.
			//
			if ((e.getClickCount() == 2) && (node instanceof TableElement))
			{

				// TO-DO Treba iskljuciti akcije na toolbaru
//				State.setState(new ConnectionState());
//				this.getToolBar().disableComponents();
				
				TableElement currentTable = (TableElement) node;
//				TableElement connectionElement = (TableElement)(((TreeElement)node).getParent());
				
				// Tabela ispravno ucitana.
//				if (this.dataBaseModel.loadTable((TableElement) node))
//				{
//					// TO-DO Treba podesiti ostale parametre.
//					this.statusBar.setSelectedTable("Selected resourse: " + ((TableElement) node).getName());
//				}
			}
		}

		if (tree != null)
		{
			int row = tree.getRowForLocation(e.getX(), e.getY());

			if (row == -1)
			{
				tree.clearSelection();
			}
			else
			{
				TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
				tree.setSelectionPath(selPath);
			}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
