package menu;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

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

	private ConnectionExplorer connectionExplorer;

	public MenuBar() {
		file = new JMenu("Menu");
		file.setMnemonic(KeyEvent.VK_F);

		// if(View.currentUser.roles.contains("admin")){
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
