package render;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import DAL.DBConnection;
import DAL.DbCollections;

public class TableDbView extends JScrollPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JTable table;
	
	public ButtonColumnRender buttonColumnRender = null;

	public TableDbView(String dbCollection) {
		super(VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED);

		buttonColumnRender = new ButtonColumnRender();
		
		String[] columnNames = { "id", "firstName", "lastName", "Edit", "Delete" };

		DBCursor cursor = DBConnection.find(dbCollection);

		DefaultTableModel model = new DefaultTableModel(columnNames, 0);

		int arrayOfDocument = cursor.size();

		for (int i = 0; i < arrayOfDocument; i++) {

			DBObject obj = cursor.next();
			// String id = (String)obj.get("_id");
			String firstName = (String) obj.get("firstName");
			String lastName = (String) obj.get("lastName");

			model.addRow(new Object[] { i + 1, firstName, lastName, new JButton("Edit"), new JButton("Delete") });
		}

		table = new JTable(model);

		table.setRowHeight(34);
		
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setRowSelectionInterval(0, 0);
		table.setFillsViewportHeight(true);
		table.getTableHeader().setReorderingAllowed(false);

		// ENABLE SORTER
		table.setAutoCreateRowSorter(true);

		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(20);
		int columnCount = columnModel.getColumnCount();
		int vrijednost = 0;
		JLabel lbl = new JLabel();
		FontMetrics fm;
		Font font;
		String columnName;
		for (int i = 1; i < columnCount - 2; i++) {
			vrijednost = this.getMaxWidthRow(i);
			font = lbl.getFont();
			fm = lbl.getFontMetrics(font);
			columnName = this.table.getColumnName(i);
			if (vrijednost > fm.stringWidth(columnName)) {
				columnModel.getColumn(i).setPreferredWidth(vrijednost + 20);
			} else {
				columnModel.getColumn(i).setPreferredWidth(fm.stringWidth(this.table.getColumnName(i)) + 20);
			}
		}

		try {
			table.setRowSelectionInterval(0, 0);
		} catch (Exception e) {
		}
		
		table.setDefaultRenderer(Object.class, new TableRender());
		table.getColumn("Edit").setCellRenderer(buttonColumnRender);
		table.getColumn("Delete").setCellRenderer(buttonColumnRender);
		
		this.setViewportView(table);
	}

	public int getMaxWidthRow(int column) {
		int value = 0, temp = 0;
		JLabel data = new JLabel();
		int rows = this.table.getRowCount();
		FontMetrics fm;
		Font font;
		String columnValue;
		for (int i = 0; i < rows; i++) {
			font = data.getFont();
			fm = data.getFontMetrics(font);
			columnValue = this.table.getValueAt(i, column).toString();
			temp = fm.stringWidth(columnValue);
			if (temp > value) {
				value = temp;
			}
		}

		return value;
	}
}
