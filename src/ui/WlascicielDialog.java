package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import core.Wlasciciel;
import dao.WlascicielDAO;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WlascicielDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField imieTextField;
	private JTextField nazwiskoTextField;
	private JTextField peselTextField;
	private JTextField ulicaTextField;
	private JTextField miejscowoscTextField;
	private JTextField nrTelTextField;
	
	
	private WlascicielDAO WlascicielDAO;
	
	private Main main1;
	
	private Wlasciciel poprzedniWlasciciel = null;
	private boolean edycja= false;
	
	
	
	
	
	public WlascicielDialog(WlascicielDAO WlascicielDAO, Main main, Wlasciciel poprzedniWlasciciel,
			boolean edycja) {
		this();
		this.WlascicielDAO = WlascicielDAO;
		this.main1 = main;
		this.poprzedniWlasciciel = poprzedniWlasciciel;
		this.edycja = edycja;
		
		if(edycja){
			setTitle("Edycja");
			
			populateGui(poprzedniWlasciciel);
		}
		
	}
	
	
	
	private void populateGui(Wlasciciel wlasciciel) {

		imieTextField.setText(wlasciciel.getImie());
		nazwiskoTextField.setText(wlasciciel.getNazwisko());
		ulicaTextField.setText(wlasciciel.getUlica());
		miejscowoscTextField.setText(wlasciciel.getMiejscowosc());
		peselTextField.setText(wlasciciel.getPesel());
		nrTelTextField.setText(wlasciciel.getNrTelefonu());
		
	
	}

	public WlascicielDialog(WlascicielDAO WlascicielDAO, Main main) {
		this();
		this.WlascicielDAO = WlascicielDAO;
		this.main1 = main;
	}


	public static void main(String[] args) {
		try {
			WlascicielDialog dialog = new WlascicielDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public WlascicielDialog() {
		setTitle("Dodaj Wlasciciela");
		setBounds(100, 100, 450, 322);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblImie = new JLabel("Imie");
			GridBagConstraints gbc_lblImie = new GridBagConstraints();
			gbc_lblImie.anchor = GridBagConstraints.EAST;
			gbc_lblImie.insets = new Insets(0, 0, 5, 5);
			gbc_lblImie.gridx = 1;
			gbc_lblImie.gridy = 1;
			contentPanel.add(lblImie, gbc_lblImie);
		}
		{
			imieTextField = new JTextField();
			GridBagConstraints gbc_imieTextField = new GridBagConstraints();
			gbc_imieTextField.insets = new Insets(0, 0, 5, 0);
			gbc_imieTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_imieTextField.gridx = 2;
			gbc_imieTextField.gridy = 1;
			contentPanel.add(imieTextField, gbc_imieTextField);
			imieTextField.setColumns(10);
		}
		{
			JLabel lblNazwisko = new JLabel("Nazwisko");
			GridBagConstraints gbc_lblNazwisko = new GridBagConstraints();
			gbc_lblNazwisko.anchor = GridBagConstraints.EAST;
			gbc_lblNazwisko.insets = new Insets(0, 0, 5, 5);
			gbc_lblNazwisko.gridx = 1;
			gbc_lblNazwisko.gridy = 2;
			contentPanel.add(lblNazwisko, gbc_lblNazwisko);
		}
		{
			nazwiskoTextField = new JTextField();
			GridBagConstraints gbc_nazwiskoTextField = new GridBagConstraints();
			gbc_nazwiskoTextField.insets = new Insets(0, 0, 5, 0);
			gbc_nazwiskoTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_nazwiskoTextField.gridx = 2;
			gbc_nazwiskoTextField.gridy = 2;
			contentPanel.add(nazwiskoTextField, gbc_nazwiskoTextField);
			nazwiskoTextField.setColumns(10);
		}
		{
			JLabel lblPesel = new JLabel("Pesel");
			GridBagConstraints gbc_lblPesel = new GridBagConstraints();
			gbc_lblPesel.anchor = GridBagConstraints.EAST;
			gbc_lblPesel.insets = new Insets(0, 0, 5, 5);
			gbc_lblPesel.gridx = 1;
			gbc_lblPesel.gridy = 3;
			contentPanel.add(lblPesel, gbc_lblPesel);
		}
		{
			peselTextField = new JTextField();
			GridBagConstraints gbc_peselTextField = new GridBagConstraints();
			gbc_peselTextField.insets = new Insets(0, 0, 5, 0);
			gbc_peselTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_peselTextField.gridx = 2;
			gbc_peselTextField.gridy = 3;
			contentPanel.add(peselTextField, gbc_peselTextField);
			peselTextField.setColumns(10);
		}
		{
			JLabel lblUlica = new JLabel("Ulica");
			GridBagConstraints gbc_lblUlica = new GridBagConstraints();
			gbc_lblUlica.anchor = GridBagConstraints.EAST;
			gbc_lblUlica.insets = new Insets(0, 0, 5, 5);
			gbc_lblUlica.gridx = 1;
			gbc_lblUlica.gridy = 4;
			contentPanel.add(lblUlica, gbc_lblUlica);
		}
		{
			ulicaTextField = new JTextField();
			GridBagConstraints gbc_ulicaTextField = new GridBagConstraints();
			gbc_ulicaTextField.insets = new Insets(0, 0, 5, 0);
			gbc_ulicaTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_ulicaTextField.gridx = 2;
			gbc_ulicaTextField.gridy = 4;
			contentPanel.add(ulicaTextField, gbc_ulicaTextField);
			ulicaTextField.setColumns(10);
		}
		{
			JLabel lblMiejscowosc = new JLabel("Miejscowosc");
			GridBagConstraints gbc_lblMiejscowosc = new GridBagConstraints();
			gbc_lblMiejscowosc.anchor = GridBagConstraints.EAST;
			gbc_lblMiejscowosc.insets = new Insets(0, 0, 5, 5);
			gbc_lblMiejscowosc.gridx = 1;
			gbc_lblMiejscowosc.gridy = 5;
			contentPanel.add(lblMiejscowosc, gbc_lblMiejscowosc);
		}
		{
			miejscowoscTextField = new JTextField();
			GridBagConstraints gbc_miejscowoscTextField = new GridBagConstraints();
			gbc_miejscowoscTextField.insets = new Insets(0, 0, 5, 0);
			gbc_miejscowoscTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_miejscowoscTextField.gridx = 2;
			gbc_miejscowoscTextField.gridy = 5;
			contentPanel.add(miejscowoscTextField, gbc_miejscowoscTextField);
			miejscowoscTextField.setColumns(10);
		}
		{
			JLabel lblNrTelefonu = new JLabel("Nr Telefonu");
			GridBagConstraints gbc_lblNrTelefonu = new GridBagConstraints();
			gbc_lblNrTelefonu.anchor = GridBagConstraints.EAST;
			gbc_lblNrTelefonu.insets = new Insets(0, 0, 5, 5);
			gbc_lblNrTelefonu.gridx = 1;
			gbc_lblNrTelefonu.gridy = 6;
			contentPanel.add(lblNrTelefonu, gbc_lblNrTelefonu);
		}
		{
			nrTelTextField = new JTextField();
			GridBagConstraints gbc_nrTelTextField = new GridBagConstraints();
			gbc_nrTelTextField.insets = new Insets(0, 0, 5, 0);
			gbc_nrTelTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_nrTelTextField.gridx = 2;
			gbc_nrTelTextField.gridy = 6;
			contentPanel.add(nrTelTextField, gbc_nrTelTextField);
			nrTelTextField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton zapiszButton = new JButton("Zapisz");
				zapiszButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						zapiszWlasciciela();
					}
				});
				zapiszButton.setActionCommand("OK");
				buttonPane.add(zapiszButton);
				getRootPane().setDefaultButton(zapiszButton);
			}
			{
				JButton anulujButton = new JButton("Anuluj");
				anulujButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						dispose();
					}
				});
				anulujButton.setActionCommand("Cancel");
				buttonPane.add(anulujButton);
			}
		}
	}
	
	protected void zapiszWlasciciela(){
		//info z giu
		
		String imie = imieTextField.getText();
		String nazwisko = nazwiskoTextField.getText();
		String pesel = peselTextField.getText();
		String ulica = ulicaTextField.getText();
		String miejscowosc = miejscowoscTextField.getText();
		String nrTelefonu = nrTelTextField.getText();
		
		
		
		Wlasciciel tempWlasciciel = null;
		
		if(edycja){
			tempWlasciciel = poprzedniWlasciciel;
			
			tempWlasciciel.setImie(imie);
			tempWlasciciel.setNazwisko(nazwisko);
			tempWlasciciel.setPesel(pesel);
			tempWlasciciel.setUlica(ulica);
			tempWlasciciel.setMiejscowosc(miejscowosc);
			tempWlasciciel.setNrTelefonu(nrTelefonu);
			
		}else{
			
			tempWlasciciel = new Wlasciciel(imie, nazwisko, pesel, ulica, miejscowosc, nrTelefonu);
			
		}
	
		
		try{
			//zapisz do bazy
			if(edycja){
				WlascicielDAO.edytujWlasciciel(tempWlasciciel);
				
			}else{
				
				WlascicielDAO.addWlasciciel(tempWlasciciel);	

			}
			
			//zamknij okienko
			setVisible(false);
			dispose();
			
			//odswiez liste
			main1.odswierzListeWlascicieli();
			
			//potwierdzenie
			if(edycja){
				JOptionPane.showMessageDialog(main1, "Edycja przebiegla pomyslnie.", "Wlasciciel Dodany", JOptionPane.INFORMATION_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(main1, "Wlasciciel dodany pomyslnie.", "Wlasciciel Dodany", JOptionPane.INFORMATION_MESSAGE);
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(main1, "Blad przy dodawaniu wlasciciela: " + e.getMessage(), "BLAD!", JOptionPane.INFORMATION_MESSAGE);
			System.err.println("Blad przy dodawaniu wlasciciela: " + e.getMessage());
		}
		
		
		
	}

}
