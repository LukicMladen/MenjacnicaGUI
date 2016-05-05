package menjacnica.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Dimension;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenjacnicaGUI extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JButton btnDodajKurs;
	private JButton btnIzbrisiKurs;
	private JButton btnIzvrsiIzmenu;
	static JTextArea textAreaStatus;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenu mnHelp;
	private JMenuItem mntmOpen;
	private JMenuItem mntmSave;
	private JMenuItem mntmExit;
	private JMenuItem mntmAbout;
	public static JTable table;
	private JPopupMenu popupMenu;
	private JMenuItem mntmDodajKurs;
	private JMenuItem mntmIzbrisiKurs;
	private JMenuItem mntmIzvrsiIzmenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenjacnicaGUI frame = new MenjacnicaGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenjacnicaGUI() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ugasiAplikaciju();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenjacnicaGUI.class.getResource("/icons/payment-512.png")));
		setTitle("Menjacnica");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 550, 408);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanel(), BorderLayout.EAST);
		contentPane.add(getScrollPane(), BorderLayout.CENTER);
		contentPane.add(getScrollPane_1(), BorderLayout.SOUTH);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setPreferredSize(new Dimension(120, 10));
			panel.setLayout(new MigLayout("", "[109.00px]", "[23px][23px][23px]"));
			panel.add(getBtnDodajKurs(), "cell 0 0,growx,aligny top");
			panel.add(getBtnIzbrisiKurs(), "cell 0 1,growx,aligny top");
			panel.add(getBtnIzvrsiIzmenu(), "cell 0 2,growx,aligny top");
		}
		return panel;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}

	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setPreferredSize(new Dimension(2, 80));
			scrollPane_1.setViewportView(getTextAreaStatus());
		}
		return scrollPane_1;
	}

	private JButton getBtnDodajKurs() {
		if (btnDodajKurs == null) {
			btnDodajKurs = new JButton("Dodaj kurs");
			btnDodajKurs.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					otvoriDodajKurs();
				}
			});
		}
		return btnDodajKurs;
	}

	private JButton getBtnIzbrisiKurs() {
		if (btnIzbrisiKurs == null) {
			btnIzbrisiKurs = new JButton("Izbrisi kurs");
			btnIzbrisiKurs.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					try {
						int red = table.getSelectedRow();
						if (red != -1) {
							int opcija = JOptionPane.showConfirmDialog(null,
									"Da li ste sigurni da zelite da obrisete " + (red + 1) + ". red?", "Zatvaranje",
									JOptionPane.YES_NO_OPTION);
							if (opcija == JOptionPane.YES_OPTION) {

								DefaultTableModel model = (DefaultTableModel) table.getModel();
								model.removeRow(red);
								JOptionPane.showMessageDialog(null, "Red je uspesno obrisan!", "", JOptionPane.OK_OPTION);
								textAreaStatus.append("Izbrisan je " + (red + 1) + ". red!\n");
							}

						} else {
							JOptionPane.showMessageDialog(null, "Morate odabrati red pre brisanja!", "Greska",
									JOptionPane.OK_OPTION);
						}
					} catch (HeadlessException e) {
					JOptionPane.showMessageDialog(null, "Doslo je do greske prilikom brisanja kursa!", "", JOptionPane.OK_OPTION);
					}

				}
			});
		}
		return btnIzbrisiKurs;
	}

	private JButton getBtnIzvrsiIzmenu() {
		if (btnIzvrsiIzmenu == null) {
			btnIzvrsiIzmenu = new JButton("Izvrsi izmenu");
			btnIzvrsiIzmenu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					IzvrsiZamenuGUI izg = new IzvrsiZamenuGUI();
					izg.setVisible(true);
				}
			});
		}
		return btnIzvrsiIzmenu;
	}

	private JTextArea getTextAreaStatus() {
		if (textAreaStatus == null) {
			textAreaStatus = new JTextArea();
			textAreaStatus.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "STATUS",
					TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		}
		return textAreaStatus;
	}

	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnFile());
			menuBar.add(getMnHelp());
		}
		return menuBar;
	}

	private JMenu getMnFile() {
		if (mnFile == null) {
			mnFile = new JMenu("File");
			mnFile.add(getMntmOpen());
			mnFile.add(getMntmSave());
			mnFile.add(getMntmExit());
		}
		return mnFile;
	}

	private JMenu getMnHelp() {
		if (mnHelp == null) {
			mnHelp = new JMenu("Help");
			mnHelp.add(getMntmAbout());
		}
		return mnHelp;
	}

	private JMenuItem getMntmOpen() {
		if (mntmOpen == null) {
			mntmOpen = new JMenuItem("Open");
			mntmOpen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFileChooser jfc = new JFileChooser();
					int vrednost = jfc.showOpenDialog(null);
					if (vrednost == JFileChooser.APPROVE_OPTION) {
						jfc.getSelectedFile();
						textAreaStatus.append("Ucitaj fajl: " + jfc.getSelectedFile().getAbsolutePath() + "\n");
					}
				}
			});
			mntmOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
			mntmOpen.setIcon(new ImageIcon(
					MenjacnicaGUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/Directory.gif")));
			mntmOpen.setSelectedIcon(new ImageIcon(
					MenjacnicaGUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/Directory.gif")));
		}
		return mntmOpen;
	}

	private JMenuItem getMntmSave() {
		if (mntmSave == null) {
			mntmSave = new JMenuItem("Save");
			mntmSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFileChooser jfc = new JFileChooser();
					int vrednost = jfc.showSaveDialog(null);
					if (vrednost == JFileChooser.APPROVE_OPTION) {
						jfc.getSelectedFile();
						textAreaStatus.append("Sacuvan fajl: " + jfc.getSelectedFile().getAbsolutePath() + "\n");
					}
				}
			});
			mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
			mntmSave.setIcon(new ImageIcon(
					MenjacnicaGUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
		}
		return mntmSave;
	}

	private JMenuItem getMntmExit() {
		if (mntmExit == null) {
			mntmExit = new JMenuItem("Exit");
			mntmExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ugasiAplikaciju();
				}
			});
			mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));

		}
		return mntmExit;
	}

	private JMenuItem getMntmAbout() {
		if (mntmAbout == null) {
			mntmAbout = new JMenuItem("About");
			mntmAbout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null,
							"Mladen Lukic 257/14, student Fakulteta organizacionih nauka u Beogradu.");
				}
			});
		}
		return mntmAbout;
	}

	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.setShowGrid(false);
			table.setFillsViewportHeight(true);
			table.setModel(new DefaultTableModel(new Object[][] {},
					new String[] { "Sifra", "Skraceni naziv", "Prodajni", "Srednji", "Kupovni", "Naziv" }));
			table.getColumnModel().getColumn(0).setPreferredWidth(36);
			table.getColumnModel().getColumn(1).setPreferredWidth(85);
			table.getColumnModel().getColumn(2).setPreferredWidth(54);
			table.getColumnModel().getColumn(3).setPreferredWidth(48);
			table.getColumnModel().getColumn(4).setPreferredWidth(54);
			table.getColumnModel().getColumn(5).setPreferredWidth(41);
			addPopup(table, getPopupMenu());
		}
		return table;
	}

	private JPopupMenu getPopupMenu() {
		if (popupMenu == null) {
			popupMenu = new JPopupMenu();
			popupMenu.add(getMntmDodajKurs());
			popupMenu.add(getMntmIzbrisiKurs());
			popupMenu.add(getMntmIzvrsiIzmenu());
		}
		return popupMenu;
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	private JMenuItem getMntmDodajKurs() {
		if (mntmDodajKurs == null) {
			mntmDodajKurs = new JMenuItem("Dodaj kurs");
			mntmDodajKurs.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					otvoriDodajKurs();
				}
			});
		}
		return mntmDodajKurs;
	}

	private JMenuItem getMntmIzbrisiKurs() {
		if (mntmIzbrisiKurs == null) {
			mntmIzbrisiKurs = new JMenuItem("Izbrisi kurs");
			mntmIzbrisiKurs.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						int red = table.getSelectedRow();
						if (red != -1) {
							int opcija = JOptionPane.showConfirmDialog(null,
									"Da li ste sigurni da zelite da obrisete " + (red + 1) + ". red?", "Zatvaranje",
									JOptionPane.YES_NO_OPTION);
							if (opcija == JOptionPane.YES_OPTION) {

								DefaultTableModel model = (DefaultTableModel) table.getModel();
								model.removeRow(red);
								JOptionPane.showMessageDialog(null, "Red je uspesno obrisan!", "", JOptionPane.OK_OPTION);
								textAreaStatus.append("Izbrisan je " + (red + 1) + ". red!\n");
							}

						} else {
							JOptionPane.showMessageDialog(null, "Morate odabrati red pre brisanja!", "Greska",
									JOptionPane.OK_OPTION);
						}
					} catch (HeadlessException e1) {
					JOptionPane.showMessageDialog(null, "Doslo je do greske prilikom brisanja kursa!", "", JOptionPane.OK_OPTION);
					}

				}
			});
		}
		return mntmIzbrisiKurs;
	}

	private JMenuItem getMntmIzvrsiIzmenu() {
		if (mntmIzvrsiIzmenu == null) {
			mntmIzvrsiIzmenu = new JMenuItem("Izvrsi izmenu");
			mntmIzvrsiIzmenu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					IzvrsiZamenuGUI izg = new IzvrsiZamenuGUI();
					izg.setVisible(true);
				}
			});
		}
		return mntmIzvrsiIzmenu;
	}

	public static void ugasiAplikaciju() {
		int opcija = JOptionPane.showConfirmDialog(null, "Da li zelite da izadjete iz programa?", "Zatvaranje",
				JOptionPane.YES_NO_OPTION);
		if (opcija == JOptionPane.YES_OPTION)
			System.exit(0);
	}

	public void otvoriDodajKurs() {
		DodajKursGUI dkg = new DodajKursGUI();
		dkg.setVisible(true);
	}

	public static void napisiKursNaStatus(int sifra, String naziv, double prodajni, double kupovni, double srednji,
			String skraceniNaziv) {
		textAreaStatus.append("Sifra: " + sifra + " Naziv: " + naziv + " Skraceni naziv: " + skraceniNaziv
				+ " Prodajni kurs: " + prodajni + " Kupovni kurs: " + kupovni + " Srednji: " + srednji + "\n");
	}

	public static void dodajKursUTabelu(int sifra, String naziv, double prodajni, double kupovni, double srednji,
			String skraceniNaziv) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addRow(new Object[] { sifra, naziv, prodajni, kupovni, srednji, skraceniNaziv });
	}
}
