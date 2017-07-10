package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.javadocking.DockingManager;
import com.javadocking.dock.BorderDock;
import com.javadocking.dock.Position;
import com.javadocking.dock.SplitDock;
import com.javadocking.dock.TabDock;
import com.javadocking.dockable.DefaultDockable;
import com.javadocking.dockable.Dockable;
import com.javadocking.model.FloatDockModel;

import core.ApplicationLocation;
import dbmodel.UserModel;
import menu.MenuBar;
import parser.JsonParser;
import parser.JsonWriterInfViewer;
import render.ConnectionExplorer;
import toolbar.ToolbarView;
import view.login.LoginView;
import view.model.WindowPropertiesModel;

public class View extends JFrame implements WindowListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private WindowPropertiesModel windowPropertiesModel = null;

	public JPanel mainPanel = null;

	public ConnectionExplorer connectionExplorer = null;
	public CentralView centralMainView = null;
	public ElementPropertiesView elementPropertiesView = null;

	public MenuBar menuBar = null;
	public ToolbarView toolbar = null;

	public static UserModel currentUser;

	public View(UserModel userModel) {
		this.currentUser = userModel;

		// poziv konstruktora cije klase formiraju prikaz editora
		this.addWindowListener(this);
		
		mainPanel = new JPanel(new BorderLayout());

		toolbar = new ToolbarView();

		connectionExplorer = new ConnectionExplorer();
		centralMainView = new CentralView();
		elementPropertiesView = new ElementPropertiesView();

		this.add(mainPanel);

		this.getContentPane().add(mainPanel, BorderLayout.CENTER);
		this.getContentPane().add(toolbar, BorderLayout.NORTH);

		windowPropertiesModel = new JsonParser<WindowPropertiesModel>(WindowPropertiesModel.class)
				.readFromJsonFile(ApplicationLocation.windowProp);

		Toolkit tkDimension = Toolkit.getDefaultToolkit();
		Dimension dimensionScrean = tkDimension.getScreenSize();

		Dimension dimensionWindow = new Dimension(windowPropertiesModel.getWidth(), windowPropertiesModel.getHeight());

		setSize(dimensionWindow);
		setPreferredSize(dimensionWindow);

		setLocation(dimensionScrean.width / 2 - dimensionWindow.width / 2,
				dimensionScrean.height / 2 - dimensionWindow.height / 2);

		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		menuBar = new MenuBar();

		// Set dependency

		menuBar.setConnectionExplorer(connectionExplorer);

		// End dependency

		this.setJMenuBar(menuBar);

		this.dynamicWindow();

		this.setTitle("Inf Viewer");
		this.setResizable(true);
		this.setType(Type.NORMAL);

		this.setVisible(true);
		this.pack(); // dynamic size of frame based to chiled component
	}

	public void dynamicWindow() {

		FloatDockModel dockModel = new FloatDockModel();
		dockModel.addOwner("infview", this);
		DockingManager.setDockModel(dockModel);

		Dockable dockableDBExplorer = new DefaultDockable("ce", connectionExplorer, "Connection explorer");
		Dockable dockableDBGrid = new DefaultDockable("cmv", centralMainView, "Database table view");
		Dockable dockableDBInput = new DefaultDockable("epv", elementPropertiesView, "Database input");

		Dockable dockableColumnTable = new DefaultDockable("dbp", elementPropertiesView, "Column properties");

		TabDock dock1 = new TabDock();
		TabDock dock2 = new TabDock();
		TabDock dock3 = new TabDock();

		dock1.addDockable(dockableColumnTable, new Position(1));
		dock1.addDockable(dockableDBExplorer, new Position(0));

		dock2.addDockable(dockableDBGrid, new Position(0));
		dock3.addDockable(dockableDBInput, new Position(0));

		SplitDock horizontalSplitDock = new SplitDock();
		SplitDock centerSplitDock = new SplitDock();
		SplitDock rightSplitDock = new SplitDock();

		centerSplitDock.addChildDock(dock1, new Position(Position.LEFT));
		centerSplitDock.addChildDock(rightSplitDock, new Position(Position.RIGHT));
		centerSplitDock.setDividerLocation(300);

		rightSplitDock.addChildDock(horizontalSplitDock, new Position(Position.RIGHT));
		rightSplitDock.setMaximumSize(new Dimension(300, getHeight()));
		rightSplitDock.setDividerLocation(600);

		horizontalSplitDock.addChildDock(dock3, new Position(Position.BOTTOM));
		horizontalSplitDock.addChildDock(dock2, new Position(Position.TOP));
		horizontalSplitDock.setDividerLocation(380);

		dockModel.addRootDock("root", centerSplitDock, this);

		BorderDock borderDock = new BorderDock();
		borderDock.setCenterComponent(centerSplitDock);

		add(borderDock, BorderLayout.CENTER);

		// ######KRAJ JAVADOKINGA ######

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		windowPropertiesModel.setWidth(this.getSize().width);
		windowPropertiesModel.setHeight(this.getSize().height);
		JsonWriterInfViewer.writeToJsonFile(ApplicationLocation.windowProp, windowPropertiesModel);

		// State.setState(null);

		this.setVisible(false); // you can't see me!
		new LoginView();
		this.dispose(); // Destroy the JFrame object
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}
}
