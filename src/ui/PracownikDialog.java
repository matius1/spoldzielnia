package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import core.Pracownik;
import dao.PracownikDAO;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PracownikDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField imieTextField;
	private JTextField nazwiskoTextField;
	private JTextField stanowiskoTextField;
	private JTextField peselTextField;
	private JTextField ulicaTextField;
	private JTextField miejscowoscTextField;
	private JTextField nrKontaTextField;
	private JTextField nrTelTextField;
	
	
	private PracownikDAO pracownikDAO;
	private PracownikSearch pracownikSearch;
	
	private Pracownik poprzedniPracownik = null;
	private boolean edycja= false;
	
	
	
	
	
	public PracownikDialog(PracownikDAO pracownikDAO, PracownikSearch pracownikSearch, Pracownik poprzedniPracownik,
			boolean edycja) {
		this();
		this.pracownikDAO = pracownikDAO;
		this.pracownikSearch = pracownikSearch;
		this.poprzedniPracownik = poprzedniPracownik;
		this.edycja = edycja;
		
		if(edycja){
			setTitle("Edycja");
			
			populateGui(poprzedniPracownik);
		}
		
	}
	
	
	
	private void populateGui(Pracownik pracownik) {

		imieTextField.setText(pracownik.getImie());
		nazwiskoTextField.setText(pracownik.getNazwisko());
		stanowiskoTextField.setText(pracownik.getStanowisko());
		ulicaTextField.setText(pracownik.getUlica());
		miejscowoscTextField.setText(pracownik.getMiejscowosc());
		peselTextField.setText(pracownik.getPesel());
		nrKontaTextField.setText(pracownik.getNrKonta());
		nrTelTextField.setText(pracownik.getNrTelefonu());
		
	
	}

	public PracownikDialog(PracownikDAO pracownikDAO, PracownikSearch pracownikSearch) {
		this();
		this.pracownikDAO = pracownikDAO;
		this.pracownikSearch = pracownikSearch;
	}


	public static void main(String[] args) {
		try {
			PracownikDialog dialog = new PracownikDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public PracownikDialog() {
		setTitle("Dodaj Pracownika");
		setBounds(100, 100, 450, 350);
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
			JLabel lblStanowisko = new JLabel("Stanowisko");
			GridBagConstraints gbc_lblStanowisko = new GridBagConstraints();
			gbc_lblStanowisko.anchor = GridBagConstraints.EAST;
			gbc_lblStanowisko.insets = new Insets(0, 0, 5, 5);
			gbc_lblStanowisko.gridx = 1;
			gbc_lblStanowisko.gridy = 3;
			contentPanel.add(lblStanowisko, gbc_lblStanowisko);
		}
		{
			stanowiskoTextField = new JTextField();
			GridBagConstraints gbc_stanowiskoTextField = new GridBagConstraints();
			gbc_stanowiskoTextField.insets = new Insets(0, 0, 5, 0);
			gbc_stanowiskoTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_stanowiskoTextField.gridx = 2;
			gbc_stanowiskoTextField.gridy = 3;
			contentPanel.add(stanowiskoTextField, gbc_stanowiskoTextField);
			stanowiskoTextField.setColumns(10);
		}
		{
			JLabel lblPesel = new JLabel("Pesel");
			GridBagConstraints gbc_lblPesel = new GridBagConstraints();
			gbc_lblPesel.anchor = GridBagConstraints.EAST;
			gbc_lblPesel.insets = new Insets(0, 0, 5, 5);
			gbc_lblPesel.gridx = 1;
			gbc_lblPesel.gridy = 4;
			contentPanel.add(lblPesel, gbc_lblPesel);
		}
		{
			peselTextField = new JTextField();
			GridBagConstraints gbc_peselTextField = new GridBagConstraints();
			gbc_peselTextField.insets = new Insets(0, 0, 5, 0);
			gbc_peselTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_peselTextField.gridx = 2;
			gbc_peselTextField.gridy = 4;
			contentPanel.add(peselTextField, gbc_peselTextField);
			peselTextField.setColumns(10);
		}
		{
			JLabel lblUlica = new JLabel("Ulica");
			GridBagConstraints gbc_lblUlica = new GridBagConstraints();
			gbc_lblUlica.anchor = GridBagConstraints.EAST;
			gbc_lblUlica.insets = new Insets(0, 0, 5, 5);
			gbc_lblUlica.gridx = 1;
			gbc_lblUlica.gridy = 5;
			contentPanel.add(lblUlica, gbc_lblUlica);
		}
		{
			ulicaTextField = new JTextField();
			GridBagConstraints gbc_ulicaTextField = new GridBagConstraints();
			gbc_ulicaTextField.insets = new Insets(0, 0, 5, 0);
			gbc_ulicaTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_ulicaTextField.gridx = 2;
			gbc_ulicaTextField.gridy = 5;
			contentPanel.add(ulicaTextField, gbc_ulicaTextField);
			ulicaTextField.setColumns(10);
		}
		{
			JLabel lblMiejscowosc = new JLabel("Miejscowosc");
			GridBagConstraints gbc_lblMiejscowosc = new GridBagConstraints();
			gbc_lblMiejscowosc.anchor = GridBagConstraints.EAST;
			gbc_lblMiejscowosc.insets = new Insets(0, 0, 5, 5);
			gbc_lblMiejscowosc.gridx = 1;
			gbc_lblMiejscowosc.gridy = 6;
			contentPanel.add(lblMiejscowosc, gbc_lblMiejscowosc);
		}
		{
			miejscowoscTextField = new JTextField();
			GridBagConstraints gbc_miejscowoscTextField = new GridBagConstraints();
			gbc_miejscowoscTextField.insets = new Insets(0, 0, 5, 0);
			gbc_miejscowoscTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_miejscowoscTextField.gridx = 2;
			gbc_miejscowoscTextField.gridy = 6;
			contentPanel.add(miejscowoscTextField, gbc_miejscowoscTextField);
			miejscowoscTextField.setColumns(10);
		}
		{
			JLabel lblNrKonta = new JLabel("Nr Konta");
			GridBagConstraints gbc_lblNrKonta = new GridBagConstraints();
			gbc_lblNrKonta.anchor = GridBagConstraints.EAST;
			gbc_lblNrKonta.insets = new Insets(0, 0, 5, 5);
			gbc_lblNrKonta.gridx = 1;
			gbc_lblNrKonta.gridy = 7;
			contentPanel.add(lblNrKonta, gbc_lblNrKonta);
		}
		{
			nrKontaTextField = new JTextField();
			GridBagConstraints gbc_nrKontaTextField = new GridBagConstraints();
			gbc_nrKontaTextField.insets = new Insets(0, 0, 5, 0);
			gbc_nrKontaTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_nrKontaTextField.gridx = 2;
			gbc_nrKontaTextField.gridy = 7;
			contentPanel.add(nrKontaTextField, gbc_nrKontaTextField);
			nrKontaTextField.setColumns(10);
		}
		{
			JLabel lblNrTelefonu = new JLabel("Nr Telefonu");
			GridBagConstraints gbc_lblNrTelefonu = new GridBagConstraints();
			gbc_lblNrTelefonu.anchor = GridBagConstraints.EAST;
			gbc_lblNrTelefonu.insets = new Insets(0, 0, 0, 5);
			gbc_lblNrTelefonu.gridx = 1;
			gbc_lblNrTelefonu.gridy = 8;
			contentPanel.add(lblNrTelefonu, gbc_lblNrTelefonu);
		}
		{
			nrTelTextField = new JTextField();
			GridBagConstraints gbc_nrTelTextField = new GridBagConstraints();
			gbc_nrTelTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_nrTelTextField.gridx = 2;
			gbc_nrTelTextField.gridy = 8;
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
						zapiszPracownika();
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
	
	protected void zapiszPracownika(){
		//info z giu
		
		String imie = imieTextField.getText();
		String nazwisko = nazwiskoTextField.getText();
		String pesel = peselTextField.getText();
		String ulica = ulicaTextField.getText();
		String miejscowosc = miejscowoscTextField.getText();
		String stanowisko = stanowiskoTextField.getText();
		String nrKonta = nrKontaTextField.getText();
		String nrTelefonu = nrTelTextField.getText();
		
		Pracownik tempPracownik = null;
		
		if(edycja){
			tempPracownik = poprzedniPracownik;
			
			tempPracownik.setImie(imie);
			tempPracownik.setNazwisko(nazwisko);
			tempPracownik.setPesel(pesel);
			tempPracownik.setUlica(ulica);
			tempPracownik.setMiejscowosc(miejscowosc);
			tempPracownik.setStanowisko(stanowisko);
			tempPracownik.setNrKonta(nrKonta);
			tempPracownik.setNrTelefonu(nrTelefonu);
			
		}else{
			tempPracownik = new Pracownik(imie, nazwisko, pesel, ulica, miejscowosc, stanowisko, nrKonta, nrTelefonu);
			
		}
	
		
		try{
			//zapisz do bazy
			if(edycja){
				pracownikDAO.edytujPracownik(tempPracownik);
				
			}else{
				pracownikDAO.addPracownik(tempPracownik);	

			}
			
			//zamknij okienko
			setVisible(false);
			dispose();
			
			//odswiez liste
			pracownikSearch.odswierzListePracownikow();
			
			//potwierdzenie
			if(edycja){
				JOptionPane.showMessageDialog(pracownikSearch, "Edycja przebiegla pomy�lnie.", "Pracownik Dodany", JOptionPane.INFORMATION_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(pracownikSearch, "Pracownik dodany pomy�lnie.", "Pracownik Dodany", JOptionPane.INFORMATION_MESSAGE);
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(pracownikSearch, "Blad przy dodawaniu pracownika: " + e.getMessage(), "BLAD!", JOptionPane.INFORMATION_MESSAGE);
		}
		
		
		
	}

}
