package menjacnica.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class IzvrsiZamenuGUI extends JFrame {

	private JPanel contentPane;
	private JLabel lblKupovniKurs;
	private JLabel lblProdajniKurs;
	private JComboBox comboBox;
	private JTextField textFieldKupovni;
	private JTextField textFieldProdajni;
	private JLabel lblIznos;
	private JLabel lblVrstaTransakcije;
	private JRadioButton rdbtnKupovina;
	private JRadioButton rdbtnProdaja;
	private JTextField textFieldIznos;
	private JButton btnIzvrsiZamenu;
	private JButton btnOdustani;
	private JSlider slider;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	
	
	/**
	 * Create the frame.
	 */
	public IzvrsiZamenuGUI() {
		setTitle("Izvrsi zamenu");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 418, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblKupovniKurs());
		contentPane.add(getLblProdajniKurs());
		contentPane.add(getComboBox());
		contentPane.add(getTextFieldKupovni());
		contentPane.add(getTextFieldProdajni());
		contentPane.add(getLblIznos());
		contentPane.add(getLblVrstaTransakcije());
		contentPane.add(getRdbtnKupovina());
		contentPane.add(getRdbtnProdaja());
		contentPane.add(getTextFieldIznos());
		contentPane.add(getBtnIzvrsiZamenu());
		contentPane.add(getBtnOdustani());
		contentPane.add(getSlider());
		textFieldIznos.setText("50");
		textFieldKupovni.setText("121");
		textFieldProdajni.setText("123");
	}
	private JLabel getLblKupovniKurs() {
		if (lblKupovniKurs == null) {
			lblKupovniKurs = new JLabel("Kupovni kurs");
			lblKupovniKurs.setBounds(38, 11, 82, 14);
		}
		return lblKupovniKurs;
	}
	private JLabel getLblProdajniKurs() {
		if (lblProdajniKurs == null) {
			lblProdajniKurs = new JLabel("Prodajni kurs");
			lblProdajniKurs.setBounds(286, 11, 87, 14);
		}
		return lblProdajniKurs;
	}
	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String valuta = comboBox.getSelectedItem().toString();
					if(valuta=="EUR"){
						textFieldKupovni.setText("121");
						textFieldProdajni.setText("123");
					}else if(valuta=="USD"){
						textFieldKupovni.setText("106");
						textFieldProdajni.setText("108");
					}else{
						textFieldKupovni.setText("111");
						textFieldProdajni.setText("113");
					}
				}
			});
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"EUR", "USD", "CHF"}));
			comboBox.setBounds(172, 36, 67, 20);
		}
		return comboBox;
	}
	private JTextField getTextFieldKupovni() {
		if (textFieldKupovni == null) {
			textFieldKupovni = new JTextField();
			textFieldKupovni.setEditable(false);
			textFieldKupovni.setBounds(38, 36, 86, 20);
			textFieldKupovni.setColumns(10);
		}
		return textFieldKupovni;
	}
	private JTextField getTextFieldProdajni() {
		if (textFieldProdajni == null) {
			textFieldProdajni = new JTextField();
			textFieldProdajni.setEditable(false);
			textFieldProdajni.setBounds(286, 36, 86, 20);
			textFieldProdajni.setColumns(10);
		}
		return textFieldProdajni;
	}
	private JLabel getLblIznos() {
		if (lblIznos == null) {
			lblIznos = new JLabel("Iznos");
			lblIznos.setBounds(38, 92, 46, 14);
		}
		return lblIznos;
	}
	private JLabel getLblVrstaTransakcije() {
		if (lblVrstaTransakcije == null) {
			lblVrstaTransakcije = new JLabel("Vrsta transakcije");
			lblVrstaTransakcije.setBounds(286, 67, 98, 14);
		}
		return lblVrstaTransakcije;
	}
	private JRadioButton getRdbtnKupovina() {
		if (rdbtnKupovina == null) {
			rdbtnKupovina = new JRadioButton("Kupovina");
			buttonGroup.add(rdbtnKupovina);
			rdbtnKupovina.setBounds(286, 88, 109, 23);
		}
		return rdbtnKupovina;
	}
	private JRadioButton getRdbtnProdaja() {
		if (rdbtnProdaja == null) {
			rdbtnProdaja = new JRadioButton("Prodaja");
			buttonGroup.add(rdbtnProdaja);
			rdbtnProdaja.setBounds(286, 114, 109, 23);
		}
		return rdbtnProdaja;
	}
	private JTextField getTextFieldIznos() {
		if (textFieldIznos == null) {
			textFieldIznos = new JTextField();
			textFieldIznos.setBounds(38, 115, 168, 20);
			textFieldIznos.setColumns(10);
		}
		return textFieldIznos;
	}
	private JButton getBtnIzvrsiZamenu() {
		if (btnIzvrsiZamenu == null) {
			btnIzvrsiZamenu = new JButton("Izvrsi zamenu");
			btnIzvrsiZamenu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(rdbtnKupovina.isSelected()){
						MenjacnicaGUI.textAreaStatus.append("Klijent zeli da kupi " +textFieldIznos.getText()+ comboBox.getSelectedItem().toString()+"\n");
					}else if(rdbtnProdaja.isSelected()){
						MenjacnicaGUI.textAreaStatus.append("Klijent zeli da proda " +textFieldIznos.getText()+ comboBox.getSelectedItem().toString()+"\n");
					}
					else{
						JOptionPane.showMessageDialog(null, "Morate izabrti opciju kupovine ili prodaje!", "", JOptionPane.OK_OPTION);
					}
				}
			});
			btnIzvrsiZamenu.setBounds(38, 238, 130, 23);
		}
		return btnIzvrsiZamenu;
	}
	private JButton getBtnOdustani() {
		if (btnOdustani == null) {
			btnOdustani = new JButton("Odustani");
			btnOdustani.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			btnOdustani.setBounds(243, 238, 130, 23);
		}
		return btnOdustani;
	}
	private JSlider getSlider() {
		if (slider == null) {
			slider = new JSlider();
			slider.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					int iznos = slider.getValue();
					textFieldIznos.setText(""+iznos);
				}
			});
			slider.setPaintLabels(true);
			slider.setMajorTickSpacing(10);
			slider.setMinorTickSpacing(5);
			slider.setPaintTicks(true);
			slider.setBounds(38, 168, 335, 45);
		}
		return slider;
	}
}
