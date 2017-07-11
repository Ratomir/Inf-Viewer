package menu;

import java.awt.BorderLayout;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.mongodb.util.JSON;

import core.ApplicationLocation;
import parser.JsonParser;
import parser.JsonWriterInfViewer;
import render.ConnectionExplorer;
import view.model.WindowPropertiesModel;

public class MenuBar extends JMenuBar implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JMenu file;
	private JMenu databse;
	private JMenu help;
	private JTextArea textArea;

	private ConnectionExplorer connectionExplorer;

	public MenuBar() {
		file = new JMenu("Menu");
		file.setMnemonic(KeyEvent.VK_F);

		file.add(new MenuItem("Create scheme", new ImageIcon("icons/ikonice nove/base.png"),
				KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.CTRL_MASK), "createScheme", this));
		// if(View.currentUser.roles.contains("admin")) {
		file.add(new MenuItem("Import scheme", new ImageIcon("icons/ikonice nove/base.png"),
				KeyStroke.getKeyStroke(KeyEvent.VK_O, Event.CTRL_MASK), "importScheme", this));
		// }

		file.addSeparator();
		file.add(new MenuItem("Sign out", new ImageIcon("icons/ikonice nove/logout.png"), null, "logOut", this));
		// file.add(exit);

		this.add(file);

		databse = new JMenu("Database");
		databse.setMnemonic(KeyEvent.VK_E);

		databse.add(new MenuItem("Open in new tab", new ImageIcon("icons/ikonice nove/first.png"),
				KeyStroke.getKeyStroke(KeyEvent.VK_F, Event.CTRL_MASK), "openNewTab", this));

		this.add(databse);

		help = new JMenu("Help");
		help.setMnemonic(KeyEvent.VK_H);

		help.add(new MenuItem("About", new ImageIcon("icons/ikonice nove/info.png"),
				KeyStroke.getKeyStroke(KeyEvent.VK_I, Event.CTRL_MASK), "about", this));
		this.add(help);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();

		switch (actionCommand) {
		case "createScheme":

			JFrame frame = new JFrame();
			JPanel panel = new JPanel();

			// add menu bar
			JMenuBar mb = new JMenuBar();
			JMenu menu1 = new JMenu("Options");
			menu1.add(new MenuItem("Validate", new ImageIcon("icons/ikonice nove/base.png"),
					KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "validate", this));
			menu1.add(new MenuItem("Save", new ImageIcon("icons/ikonice nove/base.png"),
					KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK), "save", this));
			mb.add(menu1);
			frame.setJMenuBar(mb);

			panel.setLayout(new BorderLayout());

			textArea = new JTextArea("Type your meta scheme here...");
			JScrollPane scroll = new JScrollPane(textArea);
			scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

			panel.add(scroll);
			frame.add(panel);

			// Modify some properties.
			textArea.setRows(10000);
			// textArea.setColumns(10);
			panel.setBorder(new EtchedBorder());

			// Display the Swing application.
			frame.setSize(600, 600);
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setSize(700, 400);
			frame.setVisible(true);

			break;
		case "importScheme":

			WindowPropertiesModel windowPropertiesModel = new JsonParser<WindowPropertiesModel>(
					WindowPropertiesModel.class).readFromJsonFile(ApplicationLocation.windowProp);

			File workingDirectory = null;
			if (!windowPropertiesModel.getLastLocationOfImportFiles().isEmpty()) {
				workingDirectory = new File(windowPropertiesModel.getLastLocationOfImportFiles());
			} else {
				workingDirectory = new File(System.getProperty("user.dir"));
			}

			JFileChooser fileCh = new JFileChooser(workingDirectory);
			FileNameExtensionFilter filter = new FileNameExtensionFilter(null, "json");
			fileCh.setFileFilter(filter);
			int option = fileCh.showOpenDialog(null);
			if (option == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileCh.getSelectedFile();

				windowPropertiesModel.setLastLocationOfImportFiles(selectedFile.toString());
				JsonWriterInfViewer.writeToJsonFile(ApplicationLocation.windowProp, windowPropertiesModel);

				connectionExplorer.setTree(selectedFile.toString());
			}
			break;
		case "Exit":
			System.exit(0);
			break;
		case "validate":
			try {
				JSON.parse(textArea.getText());
				JOptionPane.showMessageDialog(null, "Scheme is valid.");
			} catch (Exception exc) {
				JOptionPane.showMessageDialog(null, "Scheme is not valid.");
			}

			break;
		case "save":
			String test = textArea.getText();
			JOptionPane.showMessageDialog(null, test.length());
			// validate
			boolean valid = false;
			try {
				JSON.parse(textArea.getText());
				valid = true;
			} catch (Exception exc) {
				valid = false;
			}

			if (valid == false) {
				int dialogResult = JOptionPane.showConfirmDialog(null,
						"Scheme is not valid. Are you sure you want to save?", "Warning", JOptionPane.YES_NO_OPTION);
				if (dialogResult == JOptionPane.NO_OPTION) {
					break;
				}
			}

			JFileChooser fileChooser = new JFileChooser();
			if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				PrintWriter out;
				try {
					out = new PrintWriter(file.toString() + ".json");
					out.println(textArea.getText());
					out.close();
					JOptionPane.showMessageDialog(null, "Scheme has been saved.");
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "Greska pri snimanju fajla.");
					e1.printStackTrace();
				}

			}
			break;
		default:
			JOptionPane.showMessageDialog(null, "Komanda nije prepoznata.");
			break;
		}
	}

	/**
	 * @return the connectionExplorer
	 */
	public ConnectionExplorer getConnectionExplorer() {
		return connectionExplorer;
	}

	/**
	 * @param connectionExplorer
	 *            the connectionExplorer to set
	 */
	public void setConnectionExplorer(ConnectionExplorer connectionExplorer) {
		this.connectionExplorer = connectionExplorer;
	}

}
