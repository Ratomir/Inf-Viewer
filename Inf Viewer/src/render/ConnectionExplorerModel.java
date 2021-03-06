package render;

import java.util.List;
import java.util.Vector;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import core.CRUDOperations;
import core.DatabaseType;
import meta_db_model.BaseDBStorageModel;
import meta_db_model.Connection;
import meta_db_model.keyvalue_db.KeyValueDBModel;
import meta_db_model.keyvalue_db.Row;
import meta_db_model.no_relation_db.Collection;
import meta_db_model.no_relation_db.NoRelationDBModel;
import meta_db_model.relation_db.Column;
import meta_db_model.relation_db.Crud;
import meta_db_model.relation_db.Database;
import meta_db_model.relation_db.References;
import meta_db_model.relation_db.Table;
import parser.JsonParser;
import render.tree.TreeElement;
import render.tree.elements.ColumnElement;
import render.tree.elements.ConnectionElement;
import render.tree.elements.CrudElement;
import render.tree.elements.KeyElement;
import render.tree.elements.ReferencesElement;
import render.tree.elements.RootElement;
import render.tree.elements.StoreProcedureElement;
import render.tree.elements.TableElement;

public class ConnectionExplorerModel extends DefaultTreeModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Vector<Object> rootVector = null;

	private TreeElement root;
	
	private TableElement tbl = null;

	private ConnectionElement connectionTargetElement = null;

	public ConnectionExplorerModel(TreeNode rootElement) {
		super(rootElement);

		root = new RootElement();
		root.setName("Connections");
	}

	public void updateConnectionTreeModel(String path) {
		Connection metaDbModel = (new JsonParser<Database>(Database.class).readFromJsonFile(path)).getConnection();

		ConnectionElement newConnection = new ConnectionElement();
		newConnection.setCode(metaDbModel.getDatabasetype());
		newConnection.setName(metaDbModel.getConnectionName());

		if (metaDbModel.getDatabasetype().equals(DatabaseType.MsSQL)) {
			BaseDBStorageModel<Table> metaSchemeModel = new JsonParser<Database>(Database.class).readFromJsonFile(path);
			parseMetaSchemeToSQLModel(metaSchemeModel, newConnection);
		} else if (metaDbModel.getDatabasetype().equals(DatabaseType.MongoDB)) {
			BaseDBStorageModel<Collection> metaSchemeModel = new JsonParser<NoRelationDBModel>(NoRelationDBModel.class)
					.readFromJsonFile(path);
			parseMetaSchemeToDocumentModel(metaSchemeModel, newConnection);
		} else if (metaDbModel.getDatabasetype().equals(DatabaseType.Redis)) {
			BaseDBStorageModel<Row> metaSchemeModel = new JsonParser<KeyValueDBModel>(KeyValueDBModel.class)
					.readFromJsonFile(path);

			parseMetaSchemeToKeyValueModel(metaSchemeModel, newConnection);
		}

		root.addElement(newConnection);
	}

	public void parseMetaSchemeToSQLModel(BaseDBStorageModel<Table> model, ConnectionElement connectionElement) {
		List<Table> tables = model.getTableFromMetaScheme();

		TableElement rootTableElement = new TableElement();
		rootTableElement.setCode("Tables");
		rootTableElement.setName("Tables");

		StoreProcedureElement rootProceduresElement = new StoreProcedureElement();
		rootProceduresElement.setCode("Procedure");
		rootProceduresElement.setName("Procedure");

		for (int i = 0; i < tables.size(); i++) {

			TreeElement tableElement = new TableElement();
			tableElement.setName(tables.get(i).getName());

			CrudElement rootCrudElement = new CrudElement();
			rootCrudElement.setCode("CRUD");
			rootCrudElement.setName("CRUD");

			ColumnElement rootColumnElement = new ColumnElement();
			rootColumnElement.setCode("Columns");
			rootColumnElement.setName("Columns");

			for (int j = 0; j < tables.get(i).getColumn().size(); j++) {
				Column currentColumn = tables.get(i).getColumn().get(j);
				ColumnElement columnElement = new ColumnElement();
				columnElement.setCode(currentColumn.getCode());
				columnElement.setName(currentColumn.getName());

				if (currentColumn.getReferences() != null) {
					References currentReferences = currentColumn.getReferences();
					ReferencesElement referenceElement = new ReferencesElement();

					referenceElement.setRefColumn(currentReferences.getRefColumn());
					referenceElement.setRefTable(currentReferences.getRefTable());

					referenceElement.setName(currentReferences.getRefColumn() + ", " + currentReferences.getRefTable());
					columnElement.addElement(referenceElement);
				}

				rootColumnElement.addElement(columnElement);
			}

			Crud crudOperation = tables.get(i).getCrud();
			if (crudOperation != null) {
				if (!crudOperation.getRetrieve().isEmpty()) {
					CrudElement readCrudElement = new CrudElement();
					readCrudElement.setCode(CRUDOperations.Read);
					readCrudElement.setName(crudOperation.getRetrieve());
					rootCrudElement.addElement(readCrudElement);
				}

				if (!crudOperation.getCreate().isEmpty()) {
					CrudElement newCrudElement = new CrudElement();
					newCrudElement.setCode(CRUDOperations.Create);
					newCrudElement.setName(crudOperation.getCreate());
					rootCrudElement.addElement(newCrudElement);
				}

				if (!crudOperation.getUpdate().isEmpty()) {
					CrudElement updateCrudElement = new CrudElement();
					updateCrudElement.setCode(CRUDOperations.Update);
					updateCrudElement.setName(crudOperation.getUpdate());
					rootCrudElement.addElement(updateCrudElement);
				}

				if (!crudOperation.getDelete().isEmpty()) {
					CrudElement deleteCrudElement = new CrudElement();
					deleteCrudElement.setCode(CRUDOperations.Delete);
					deleteCrudElement.setName(crudOperation.getDelete());
					rootCrudElement.addElement(deleteCrudElement);
				}
			}

			tableElement.addElement(rootColumnElement);
			tableElement.addElement(rootCrudElement);

			rootTableElement.addElement(tableElement);
		}

		connectionElement.addElement(rootTableElement); // add tables root
		connectionElement.addElement(rootProceduresElement); // add store
																// procedure
																// root
	}

	public void parseMetaSchemeToDocumentModel(BaseDBStorageModel<Collection> model,
			ConnectionElement connectionElement) {
		List<Collection> collections = model.getTableFromMetaScheme();

		TableElement rootTableElement = new TableElement();
		rootTableElement.setName("Collections");

		for (int i = 0; i < collections.size(); i++) {
			TreeElement tableElement = new TableElement();
			tableElement.setName(collections.get(i).getName());

			rootTableElement.addElement(tableElement);
		}

		connectionElement.addElement(rootTableElement); // add tables root
	}

	public void parseMetaSchemeToKeyValueModel(BaseDBStorageModel<Row> model, ConnectionElement connectionElement) {
		List<Row> rows = model.getTableFromMetaScheme();

		TableElement rootTableElement = new TableElement();
		rootTableElement.setName("Keys");

		for (int i = 0; i < rows.size(); i++) {
			KeyElement keyElement = new KeyElement();
			keyElement.setName(rows.get(i).getKey());

			rootTableElement.addElement(keyElement);
		}

		connectionElement.addElement(rootTableElement); // add tables root
	}

	/**
	 * @return the rootVector
	 */
	public Vector<Object> getRootVector() {
		return rootVector;
	}

	/**
	 * @param rootVector
	 *            the rootVector to set
	 */
	public void setRootVector(Vector<Object> rootVector) {
		this.rootVector = rootVector;
	}


	/**
	 * Metoda pretrazuje stablo i vraca tabelu sa zadatim kodom ako ona postoji
	 * u stablu.
	 * 
	 * @param code
	 *            - kod tabele koju treba pronaci
	 * @return - opis tabele ukoliko ona postoji
	 */
	public TableElement getTable(String code) {
		Vector<TreeElement> nodes = root.getAllElements();
		find(nodes, code);

		return tbl;
	}
	
	/**
	 * Rekurzivna metoda za pronalazenje tabele i njene konekcije u stablu.
	 * 
	 * @param nodes
	 *            - cvorovi koje je potrebno pretraziti
	 * @param code
	 *            - kod tabele koja se trazi
	 */
	private void find(Vector<TreeElement> nodes, String code) {
		for (int i = 0; i < nodes.size(); i++) {
			TreeElement el = nodes.get(i);
			if (el instanceof ConnectionElement) {
				Vector<TreeElement> subNodes = el.getAllElements();
				find(subNodes, code);
			} else if (el instanceof TableElement) {
				if (((TableElement) el).getCode().equalsIgnoreCase(code)) {
					tbl = ((TableElement) el);
				}
			}
		}
	}
	

	/**
	 * Metoda vraca potomak elementa u stablu.
	 */
	@Override
	public Object getChild(Object parent, int index) {
		if (parent instanceof RootElement) {
			return ((RootElement) parent).getElementAt(index);
		} else if (parent instanceof ConnectionElement) {
			return ((ConnectionElement) parent).getElementAt(index);
		} else if (parent instanceof StoreProcedureElement) {
			return ((StoreProcedureElement) parent).getElementAt(index);
		} else if (parent instanceof TableElement) {
			return ((TableElement) parent).getElementAt(index);
		} else if (parent instanceof CrudElement) {
			return ((CrudElement) parent).getElementAt(index);
		} else if (parent instanceof ColumnElement) {
			return ((ColumnElement) parent).getElementAt(index);
		} else if (parent instanceof ReferencesElement) {
			return ((ReferencesElement) parent).getElementAt(index);
		}
		return null;
	}

	/**
	 * Metoda vraca broj potomaka zadatog elementa u stablu.
	 */
	@Override
	public int getChildCount(Object parent) {
		if (parent instanceof RootElement) {
			return ((RootElement) parent).getAllElements().size();
		} else if (parent instanceof ConnectionElement) {
			return ((ConnectionElement) parent).getAllElements().size();
		} else if (parent instanceof StoreProcedureElement) {
			return ((StoreProcedureElement) parent).getAllElements().size();
		} else if (parent instanceof TableElement) {
			return ((TableElement) parent).getAllElements().size();
		} else if (parent instanceof CrudElement) {
			return ((CrudElement) parent).getAllElements().size();
		} else if (parent instanceof ColumnElement) {
			return ((ColumnElement) parent).getAllElements().size();
		} else if (parent instanceof ReferencesElement) {
			return ((ReferencesElement) parent).getAllElements().size();
		}
		return 0;
	}

	/**
	 * Metoda vraca indeks potomka zadatog elementa.
	 */
	@Override
	public int getIndexOfChild(Object parent, Object child) {
		if (parent instanceof RootElement) {
			return ((RootElement) parent).getIndexOfElement((TreeElement) child);
		} else if (parent instanceof ConnectionElement) {
			return ((ConnectionElement) parent).getIndexOfElement((TreeElement) child);
		} else if (parent instanceof StoreProcedureElement) {
			return ((StoreProcedureElement) parent).getIndexOfElement((TreeElement) child);
		} else if (parent instanceof TableElement) {
			return ((TableElement) parent).getIndexOfElement((TreeElement) child);
		} else if (parent instanceof CrudElement) {
			return ((CrudElement) parent).getIndexOfElement((TreeElement) child);
		} else if (parent instanceof ColumnElement) {
			return ((ColumnElement) parent).getIndexOfElement((TreeElement) child);
		} else if (parent instanceof ReferencesElement) {
			return ((ReferencesElement) parent).getIndexOfElement((TreeElement) child);
		}
		return -1;
	}

	/**
	 * Metoda vraca korijeni element.
	 */
	@Override
	public Object getRoot() {
		return this.root;
	}

	/**
	 * Metoda vraca true - ako element nema potomke, false - ako ima.
	 */
	@Override
	public boolean isLeaf(Object node) {
		return (node instanceof References);
	}

	/**
	 * @return the connectionTargetElement
	 */
	public ConnectionElement getConnectionTargetElement() {
		return connectionTargetElement;
	}

	/**
	 * @param connectionTargetElement the connectionTargetElement to set
	 */
	public void setConnectionTargetElement(ConnectionElement connectionTargetElement) {
		this.connectionTargetElement = connectionTargetElement;
	}

}
