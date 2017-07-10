package view.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.RenderingHints.Key;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.mongodb.BasicDBObject;

import DAL.DBConnection;
import core.ApplicationLocation;
import dbmodel.UserModel;
import net.miginfocom.swing.MigLayout;
import parser.JsonParser;
import parser.JsonWriterInfViewer;
import view.View;
import view.model.RememberMeModel;

public class LoginView extends JFrame implements ActionListener, WindowListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel mainPanel = null;
	private PicturePanel upPanel = null;
	private JPanel inputField = null;

	private JTextField userNameField = null;
	private JTextField passwordField = null;

	private JButton btnLogin = null;
	private JButton btnCancel = null;

	private JLabel lblErrorUserName = null;

	private JCheckBox rememberMe = null;

	private RememberMeModel rememberMeModel = null;

	/**
	 * Konstruktor forme za logovanje.
	 */
	public LoginView() {
		lblErrorUserName = new JLabel("Username or password are incorrect. Please try other combination.");

		JPanel downPanel = new JPanel(new BorderLayout(5, 5));

		lblErrorUserName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblErrorUserName.setHorizontalTextPosition(JLabel.CENTER);
		lblErrorUserName.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrorUserName.setForeground(Color.RED);
		lblErrorUserName.setVisible(false);

		downPanel.add(lblErrorUserName, BorderLayout.NORTH);

		mainPanel = new JPanel(new BorderLayout());

		inputField = new JPanel();
		inputField.setLayout(new MigLayout("", "[][][][][][grow]", "[][][][][][][]"));

		JLabel lblUserName = new JLabel("Username");
		lblUserName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		inputField.add(lblUserName, "cell 3 1,alignx trailing");

		Component horizontalStrut = Box.createHorizontalStrut(20);
		inputField.add(horizontalStrut, "cell 4 1");

		userNameField = new JTextField() {
			private static final long serialVersionUID = 1L;

			// Unleash Your Creativity with Swing and the Java 2D API!
			// http://java.sun.com/products/jfc/tsc/articles/swing2d/index.html
			@Override
			protected void paintComponent(Graphics g) {
				if (!isOpaque()) {
					int w = getWidth();
					int h = getHeight();
					Graphics2D g2 = (Graphics2D) g.create();
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					g2.setColor(UIManager.getColor("TextField.background"));
					g2.fillRoundRect(0, 0, w - 1, h - 1, h, h);
					g2.setColor(Color.GRAY);
					g2.drawRoundRect(0, 0, w - 1, h - 1, h, h);
					g2.dispose();
				}
				super.paintComponent(g);
			}
		};
		inputField.add(userNameField, "flowx,cell 5 1,growx");
		userNameField.setColumns(10);

		userNameField.setOpaque(false);
		userNameField.setBackground(new Color(0, 0, 0, 0));
		userNameField.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		inputField.add(lblPassword, "cell 3 3,alignx trailing");

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		inputField.add(horizontalStrut_1, "cell 4 2");

		passwordField = new JPasswordField() {
			private static final long serialVersionUID = 1L;

			// Unleash Your Creativity with Swing and the Java 2D API!
			// http://java.sun.com/products/jfc/tsc/articles/swing2d/index.html
			@Override
			protected void paintComponent(Graphics g) {
				if (!isOpaque()) {
					int w = getWidth();
					int h = getHeight();
					Graphics2D g2 = (Graphics2D) g.create();
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					g2.setColor(UIManager.getColor("TextField.background"));
					g2.fillRoundRect(0, 0, w - 1, h - 1, h, h);
					g2.setColor(Color.GRAY);
					g2.drawRoundRect(0, 0, w - 1, h - 1, h, h);
					g2.dispose();
				}
				super.paintComponent(g);
			}
		};

		passwordField.setOpaque(false);
		passwordField.setBackground(new Color(0, 0, 0, 0));
		passwordField.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
		inputField.add(passwordField, "flowx,cell 5 3,growx");

		rememberMe = new JCheckBox("Remember me");
		inputField.add(rememberMe, "cell 5 4");

		// Component verticalStrut = Box.createVerticalStrut(20);
		// inputField.add(verticalStrut, "cell 5 4");

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		inputField.add(horizontalStrut_2, "cell 5 1");

		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		inputField.add(horizontalStrut_3, "cell 5 3");

		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		inputField.add(btnCancel, "cell 5 5,growx");
		btnCancel.setActionCommand("cancel");

		btnCancel.setOpaque(false);
		btnCancel.setContentAreaFilled(false);
		btnCancel.setBorderPainted(true);
		btnCancel.setVerticalTextPosition(JLabel.BOTTOM);
		btnCancel.setHorizontalTextPosition(JLabel.CENTER);
		Image img = Toolkit.getDefaultToolkit().getImage("icons/login/Logout.png");
		btnCancel.setIcon(new ImageIcon(img));
		btnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));

		btnCancel.addActionListener(this);

		btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		inputField.add(btnLogin, "flowx,cell 5 5,growx");
		btnLogin.setActionCommand("login");
		btnLogin.setEnabled(false);

		btnLogin.setOpaque(false);
		btnLogin.setContentAreaFilled(false);
		btnLogin.setBorderPainted(true);
		btnLogin.setVerticalTextPosition(JLabel.BOTTOM);
		btnLogin.setHorizontalTextPosition(JLabel.CENTER);
		img = Toolkit.getDefaultToolkit().getImage("icons/login/login2.png");
		btnLogin.setIcon(new ImageIcon(img));
		btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));

		btnLogin.addActionListener(this);

		this.getRootPane().setDefaultButton(btnLogin);

		// read data from json
		rememberMeModel = new JsonParser<RememberMeModel>(RememberMeModel.class)
				.readFromJsonFile(ApplicationLocation.rememberMe);

		if (rememberMeModel.isRememberMe()) {
			this.getUserNameField().setText(rememberMeModel.getUsername());
			this.getPasswordField().setText(rememberMeModel.getPassword());
		}

		this.getRememberMe().setSelected(rememberMeModel.isRememberMe());
		this.getBtnLogin().setEnabled(true);

		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		inputField.add(horizontalStrut_4, "cell 5 4");

		// inputField.add(lblErrorUserName, "cell 4 4");

		TextFieldListener txtController = new TextFieldListener(passwordField, userNameField, btnLogin,
				lblErrorUserName);
		passwordField.getDocument().addDocumentListener(txtController);
		userNameField.getDocument().addDocumentListener(txtController);

		upPanel = new PicturePanel();
		mainPanel.add(upPanel, BorderLayout.NORTH);
		downPanel.add(inputField, BorderLayout.CENTER);
		mainPanel.add(downPanel, BorderLayout.CENTER);
		this.add(mainPanel);

		addWindowListener(this);

		setType(Type.POPUP);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension dimensionWindow = new Dimension(550, 480);

		setTitle("InfViewer");
		setIconImage(new ImageIcon("icons/login/login.png").getImage());

		setSize(dimensionWindow);
		setPreferredSize(dimensionWindow);
		setMinimumSize(dimensionWindow);

		Toolkit tkDimension = Toolkit.getDefaultToolkit();
		Dimension dimensionScrean = tkDimension.getScreenSize();

		setLocation(dimensionScrean.width / 2 - dimensionWindow.width / 2,
				dimensionScrean.height / 2 - dimensionWindow.height / 2);

		this.pack();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		AbstractButton ab = (AbstractButton) e.getSource();

		switch (ab.getActionCommand()) {
		case "login": {

			BasicDBObject dbObject = new BasicDBObject();
			dbObject.put("username", this.getUserNameField().getText());
			dbObject.put("password", this.getPasswordField().getText());
			UserModel userModel = new UserModel().toObject(DBConnection.findOne("user", dbObject));

			if (userModel != null) {

				// nakon uspje�ne autentifikacije otvaramo view od aplikacije

				rememberMeModel.setUsername(this.getUserNameField().getText());
				rememberMeModel.setPassword(this.getPasswordField().getText());
				rememberMeModel.setRememberMe(this.getRememberMe().isSelected());

				JsonWriterInfViewer.writeToJsonFile(ApplicationLocation.rememberMe, rememberMeModel);
				this.dispose();
				new View(userModel);
			} else {
				this.getLblErrorUserName().setVisible(true);
			}

			break;
		}
		case "cancel": {
			rememberMeModel.setRememberMe(this.getRememberMe().isSelected());
			JsonWriterInfViewer.writeToJsonFile(ApplicationLocation.rememberMe, rememberMeModel);

			System.exit(0);
			break;
		}

		default:
			break;
		}

	}

	public RememberMeModel getRememberMeModel() {
		return rememberMeModel;
	}

	public void setRememberMeModel(RememberMeModel rememberMeModel) {
		this.rememberMeModel = rememberMeModel;
	}

	/**
	 * Metoda graphicsRender omogucava dinamicki raspored na aplikaciji
	 */
	public void graphicsRender() {
		Map<Key, Object> hints = new HashMap<>();
		hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // VALUE_ANTIALIAS_ON
																						// |
																						// VALUE_ANTIALIAS_OFF
																						// |
																						// VALUE_ANTIALIAS_DEFAULT
		hints.put(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY); // VALUE_ALPHA_INTERPOLATION_QUALITY
																												// |
																												// VALUE_ALPHA_INTERPOLATION_SPEED
																												// |
																												// VALUE_ALPHA_INTERPOLATION_DEFAULT
		hints.put(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY); // VALUE_COLOR_RENDER_QUALITY
																									// |
																									// VALUE_COLOR_RENDER_SPEED
																									// |
																									// VALUE_COLOR_RENDER_DEFAULT
		hints.put(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE); // VALUE_DITHER_DISABLE
																						// |
																						// VALUE_DITHER_ENABLE
																						// |
																						// VALUE_DITHER_DEFAULT
		hints.put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON); // VALUE_FRACTIONALMETRICS_ON
																									// |
																									// VALUE_FRACTIONALMETRICS_OFF
																									// |
																									// VALUE_FRACTIONALMETRICS_DEFAULT
		hints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR); // VALUE_INTERPOLATION_BICUBIC
																											// |
																											// VALUE_INTERPOLATION_BILINEAR
																											// |
																											// VALUE_INTERPOLATION_NEAREST_NEIGHBOR
		hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY); // VALUE_RENDER_QUALITY
																						// |
																						// VALUE_RENDER_SPEED
																						// |
																						// VALUE_RENDER_DEFAULT
		hints.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE); // VALUE_STROKE_NORMALIZE
																						// |
																						// VALUE_STROKE_DEFAULT
																						// |
																						// VALUE_STROKE_PURE
		hints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP); // VALUE_TEXT_ANTIALIAS_ON
																									// |
																									// VALUE_TEXT_ANTIALIAS_OFF
																									// |
																									// VALUE_TEXT_ANTIALIAS_DEFAULT
																									// |
																									// VALUE_TEXT_ANTIALIAS_GASP
																									// |
																									// VALUE_TEXT_ANTIALIAS_LCD_HRGB
																									// |
																									// VALUE_TEXT_ANTIALIAS_LCD_HBGR
																									// |
																									// VALUE_TEXT_ANTIALIAS_LCD_VRGB
																									// |
																									// VALUE_TEXT_ANTIALIAS_LCD_VBGR
	}

	/**
	 * @return the mainPanel
	 */
	public JPanel getMainPanel() {
		return mainPanel;
	}

	/**
	 * @param mainPanel
	 *            the mainPanel to set
	 */
	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	/**
	 * @return the upPanel
	 */
	public JPanel getUpPanel() {
		return upPanel;
	}

	/**
	 * @param upPanel
	 *            the upPanel to set
	 */
	public void setUpPanel(PicturePanel upPanel) {
		this.upPanel = upPanel;
	}

	/**
	 * @return the inputField
	 */
	public JPanel getDownPanel() {
		return inputField;
	}

	/**
	 * @param inputField
	 *            the inputField to set
	 */
	public void setDownPanel(JPanel downPanel) {
		this.inputField = downPanel;
	}

	/**
	 * @return the userNameField
	 */
	public JTextField getUserNameField() {
		return userNameField;
	}

	/**
	 * @param userNameField
	 *            the userNameField to set
	 */
	public void setUserNameField(JTextField userNameField) {
		this.userNameField = userNameField;
	}

	/**
	 * @return the passwordField
	 */
	public JTextField getPasswordField() {
		return passwordField;
	}

	/**
	 * @param passwordField
	 *            the passwordField to set
	 */
	public void setPasswordField(JTextField passwordField) {
		this.passwordField = passwordField;
	}

	/**
	 * @return the btnLogin
	 */
	public JButton getBtnLogin() {
		return btnLogin;
	}

	/**
	 * @param btnLogin
	 *            the btnLogin to set
	 */
	public void setBtnLogin(JButton btnLogin) {
		this.btnLogin = btnLogin;
	}

	/**
	 * @return the btnCancel
	 */
	public JButton getBtnCancel() {
		return btnCancel;
	}

	/**
	 * @param btnCancel
	 *            the btnCancel to set
	 */
	public void setBtnCancel(JButton btnCancel) {
		this.btnCancel = btnCancel;
	}

	/**
	 * @return the lblErrorUserName
	 */
	public JLabel getLblErrorUserName() {
		return lblErrorUserName;
	}

	/**
	 * @param lblErrorUserName
	 *            the lblErrorUserName to set
	 */
	public void setLblErrorUserName(JLabel lblErrorUserName) {
		this.lblErrorUserName = lblErrorUserName;
	}

	/**
	 * @return the rememberMe
	 */
	public JCheckBox getRememberMe() {
		return rememberMe;
	}

	/**
	 * @param rememberMe
	 *            the rememberMe to set
	 */
	public void setRememberMe(JCheckBox rememberMe) {
		this.rememberMe = rememberMe;
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// Upis u json model
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}
}
