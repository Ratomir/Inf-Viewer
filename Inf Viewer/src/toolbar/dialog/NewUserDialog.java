package toolbar.dialog;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

import DAL.DBConnection;
import dbmodel.UserModel;
import net.miginfocom.swing.MigLayout;

public class NewUserDialog extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField firstName;
	private JTextField lastName;
	private JTextField age;
	private JTextField email;
	private JTextField userName;
	private JPasswordField passwordField;
	
	private JButton btnNewButton;

	public NewUserDialog() {

		this.getContentPane().setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][]"));

		JLabel lblNewLabel = new JLabel("First name:");
		this.getContentPane().add(lblNewLabel, "cell 0 0,alignx trailing");

		firstName = new JTextField();
		this.getContentPane().add(firstName, "cell 1 0,growx");
		firstName.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Last name:");
		this.getContentPane().add(lblNewLabel_1, "cell 0 1,alignx trailing");

		lastName = new JTextField();
		this.getContentPane().add(lastName, "cell 1 1,growx");
		lastName.setColumns(10);

		JLabel lblAge = new JLabel("Age:");
		this.getContentPane().add(lblAge, "cell 0 2,alignx trailing");

		age = new JTextField();
		this.getContentPane().add(age, "cell 1 2,growx");
		age.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Email:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		this.getContentPane().add(lblNewLabel_2, "cell 0 3,alignx trailing");

		email = new JTextField();
		this.getContentPane().add(email, "cell 1 3,growx");
		email.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("User name:");
		this.getContentPane().add(lblNewLabel_3, "cell 0 4,alignx trailing");

		userName = new JTextField();
		this.getContentPane().add(userName, "cell 1 4,growx");
		userName.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Pasword:");
		this.getContentPane().add(lblNewLabel_4, "cell 0 5,alignx trailing");

		passwordField = new JPasswordField();
		this.getContentPane().add(passwordField, "cell 1 5,growx");

		btnNewButton = new JButton("New button");
		this.getContentPane().add(btnNewButton, "cell 1 8");
		btnNewButton.addActionListener(this);
		
		setSize(600, 400);
		setMinimumSize(new Dimension(400, 400));
		setPreferredSize(new Dimension(600, 400));
		setLocationRelativeTo(null);
		setModal(true);
		pack();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		UserModel userModel = new UserModel();

		DBCollection userCollection = DBConnection.getCollection("user");
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.put("username", "19");
		dbObject.put("password", "19");
		userModel = userModel.toObject(DBConnection.findOne("user", dbObject));
//		userModel.age = age.getText();
//		userModel.email = email.getText();
//		userModel.firstName = email.getText();
//		userModel.lastName = lastName.getText();
//		userModel.password = new String(passwordField.getPassword());
//		userModel.userName = userName.getText();
//		
//		userModel.roles = new ArrayList<RoleModel>();
//		RoleModel roleForUser = new RoleModel();
//		roleForUser.name = "Administrator";
//		userModel.roles.add(roleForUser);
//		
//		userCollection.insert(userModel.createDbObject());
		
	}
}
