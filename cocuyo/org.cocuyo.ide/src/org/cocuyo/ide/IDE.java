package org.cocuyo.ide;

import java.awt.BorderLayout;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.cocuyo.ide.action.FileActionGroup;
import org.cocuyo.ide.editor.EditorPart;

//import com.nilo.plaf.nimrod.NimRODLookAndFeel;

public class IDE extends JFrame {

	private JMenuBar fMenuBar;
	private JToolBar fToolBar;
	private static EditorPart fEditorPart;
	private static IDE instance;

	public IDE() {
		super("Cocuyo IDE");
		instance = this;
		init();
	}

	public static EditorPart getEditorPart() {
		if (fEditorPart == null) {
			fEditorPart = new EditorPart();
		}
		return fEditorPart;
	}

	public void run() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		init();
		UIManager.put("swing.boldMetal", Boolean.FALSE);
		setVisible(true);
	}

	private void init() {
		JPanel _panel = new JPanel();
		_panel.setLayout(new BorderLayout());
		setContentPane(_panel);
		setLocationByPlatform(true);
		setSize(400, 400);
		// setExtendedState(MAXIMIZED_BOTH);
		initMenuBar();
		initToolBar();
		intiParts();
	}

	private void intiParts() {
		JPanel _left = new JPanel();
		JPanel _rigth = new JPanel();
		JPanel _center = new JPanel();
		getContentPane().add(_left, BorderLayout.WEST);
		getContentPane().add(_rigth, BorderLayout.EAST);
		getContentPane().add(_center, BorderLayout.CENTER);

		_center.setLayout(new BorderLayout());
		_center.add(getEditorPart().getPane());
	}

	private void initMenuBar() {
		fMenuBar = new JMenuBar();
		setJMenuBar(fMenuBar);
		fMenuBar.add(FileActionGroup.instance.getMenu());

	}

	private void initToolBar() {
		fToolBar = new JToolBar();
		getContentPane().add(fToolBar, BorderLayout.PAGE_START);
		FileActionGroup.instance.fillToolBar(fToolBar);
	}

	/**
	 * @param args
	 * @throws UnsupportedLookAndFeelException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException, ParseException {

		/*NimRODLookAndFeel _laf = new NimRODLookAndFeel();
		System.out.println(_laf.getName() + "," + _laf.getID());
		UIManager.setLookAndFeel(_laf);*/
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		IDE _ide = new IDE();
		_ide.run();
	}

	public static void exit() {
		System.exit(1);
	}
}
