package render;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Vector;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import meta_db_model.relation_db.Connection;
import meta_db_model.relation_db.Database;
import meta_db_model.relation_db.Table;
import render.tree.TreeNodeVector;

public class ConnectionExplorerModel {

	private Vector<Object> rootVector = null;

	public ConnectionExplorerModel(String path) {
		Database newDatabase = null;
		byte[] encoded = null;
		try {
			encoded = Files.readAllBytes(Paths.get(path));
			
			Gson gson = new GsonBuilder().create();
            newDatabase = gson.fromJson(new String(encoded, StandardCharsets.UTF_8), Database.class);
		} catch (IOException e) {
			// TODO Dialog exception
			e.printStackTrace();
		}
		
		Connection[] connectionArray = {newDatabase.getConnection()};
		
//		Vector<Table> connectionNode = new TreeNodeVector<Table>("Connection", connectionArray);
		
		Vector<Table> tableVector = new TreeNodeVector<Table>("Tables", newDatabase.getTable().toArray(new Table[0]));
		
		Object rootNodes[] = { tableVector };
		
		rootVector = new TreeNodeVector<Object>("Root", rootNodes);
	}
	
	public void loadTables(java.util.List<Table> tables) {
		
	}

	/**
	 * @return the rootVector
	 */
	public Vector<Object> getRootVector() {
		return rootVector;
	}

	/**
	 * @param rootVector the rootVector to set
	 */
	public void setRootVector(Vector<Object> rootVector) {
		this.rootVector = rootVector;
	}

}
