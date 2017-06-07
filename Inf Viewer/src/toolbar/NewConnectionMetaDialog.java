package toolbar;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import net.miginfocom.swing.MigLayout;

public class NewConnectionMetaDialog extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JTextField textField;
	public JTextField textField_1;
	public JButton btnNewButton;
	
	public NewConnectionMetaDialog()
	{
		this.getContentPane().setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][]"));
		
		JLabel lblNewLabel = new JLabel("Ip address:");
		this.getContentPane().add(lblNewLabel, "cell 0 0,alignx trailing");
		
		textField = new JTextField();
		this.getContentPane().add(textField, "cell 1 0,growx");
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Storage name:");
		this.getContentPane().add(lblNewLabel_1, "cell 0 1,alignx trailing");
		
		textField_1 = new JTextField();
		this.getContentPane().add(textField_1, "cell 1 1,growx");
		textField_1.setColumns(10);
		
		
		btnNewButton = new JButton("New button");
		this.getContentPane().add(btnNewButton, "cell 1 8");
		btnNewButton.addActionListener(this);
		
		setSize(600, 400);
		setMinimumSize(new Dimension(400, 400));
		setPreferredSize(new Dimension(600, 400));
		setLocationRelativeTo(null);
		setModal(true);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			MongoClient mongoClient = new MongoClient(new MongoClientURI("xxxxxxxxxxxx"));
			
			DB db = mongoClient.getDB("infviewer");
			
			db.createCollection("hiper", createDBObject());
			
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
	}
	
	private static DBObject createDBObject() {
		BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
								
		docBuilder.append("_id", "5");
		docBuilder.append("name", "dasda");
		docBuilder.append("role", "dsada");
		docBuilder.append("isEmployee", "dasdsa");
		return docBuilder.get();
	}

}
