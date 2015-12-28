package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import core.Pracownik;
import dao.PracownikDAO;

import java.awt.GridLayout;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;
import java.awt.event.ActionEvent;




public class Main extends JFrame {

	private JPanel contentPane;
	
	
	private JTable table;
	
	private JTextField nazwiskoTextField;
	private PracownikDAO pracownikDao;
	private JScrollPane scrollPane;
	private JTextField stanowiskoTextField;
	private JTextField imieTextField;
	private JTextField ulicaTextField;
	private JTextField miejscowoscTextField;
	private JTextField peselTextField;
	private JTextField nrKontaTextField;
	private JTextField nrTelTextField;
	private JButton btnEdytuj;
	private JButton btnUsun;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		
		//DAO
		pracownikDao = new PracownikDAO();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1230, 589);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane);
		
		JPanel MenuTab = new JPanel();
		tabbedPane.addTab("Menu", null, MenuTab, null);
		MenuTab.setLayout(null);
		
		JLabel lblWitaj = new JLabel("Witaj!");
		lblWitaj.setBounds(358, 157, 46, 14);
		MenuTab.add(lblWitaj);
		
		JPanel PracownicyTab = new JPanel();
		tabbedPane.addTab("Pracownicy", null, PracownicyTab, null);
		PracownicyTab.setLayout(null);
		
		
		
////////////////////////////////	Pracownicy TAB		
		
		
		table = new JTable();
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 71, 1051, 431);
		scrollPane.setViewportView(table);
		PracownicyTab.add(scrollPane);
		
		
		JButton btnSzukaj = new JButton("Szukaj");
		btnSzukaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				try{
					//pobierz nazwisko z nazwiskoTextField, sprawdzanie czy nie ma tam tekstu z ficzera, gdy jest zamienia na pustego stringa
					String nazwisko = nazwiskoTextField.getText();
					if(nazwiskoTextField.getText().equals("Nazwisko")){
						nazwisko= "";
					}
					
					String stanowisko = stanowiskoTextField.getText();
					if(stanowiskoTextField.getText().equals("Stanowisko")){
						stanowisko = "";
					}
					
					String imie = imieTextField.getText();
					if(imieTextField.getText().equals("Imie")){
						imie = "";
					}
					
					String ulica = ulicaTextField.getText();
					if(ulicaTextField.getText().equals("Ulica")){
						ulica = "";
					}
					
					String miejscowosc = miejscowoscTextField.getText();
					if(miejscowoscTextField.getText().equals("Miejscowosc")){
						miejscowosc = "";
					}
					
					String pesel = peselTextField.getText();
					if(peselTextField.getText().equals("Pesel")){
						pesel = "";
					}
					
					String nrTel = nrTelTextField.getText();
					if(nrTelTextField.getText().equals("Nr Tel")){
						nrTel = "";
					}
					
					String nrKonta = nrKontaTextField.getText();
					if(nrKontaTextField.getText().equals("Nr Konta")){
						nrKonta = "";
					}
					
					
					List<Pracownik> listaPracownikow = null;
					
					//wywolaj DAO i pobierz pracownikow o szukanym nazwisku
					listaPracownikow = pracownikDao.szukajPracownik(nazwisko, stanowisko, imie, ulica, miejscowosc, pesel, nrKonta, nrTel);

					//wyswietl liste
					for(Pracownik temp: listaPracownikow){
						System.out.println(temp);
					}
					
					//stworz model i odswiez tabele, wyswietl liste
					PracownikModelTabeli pracownikModel = new PracownikModelTabeli(listaPracownikow);
					table.setModel(pracownikModel);

				}catch(Exception e){
					JOptionPane.showMessageDialog(Main.this, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
					System.err.println("B³¹d przy wyszukiwaniu: ");
					System.err.println(e);
				}
				
			}
		});
		btnSzukaj.setBounds(1098, 22, 89, 23);
		PracownicyTab.add(btnSzukaj);
		

		nazwiskoTextField = new JTextField();
		nazwiskoTextField.setForeground(Color.GRAY);
		nazwiskoTextField.setToolTipText("Nazwisko");
		nazwiskoTextField.setText("Nazwisko");
		nazwiskoTextField.setBounds(15, 23, 120, 20);
		PracownicyTab.add(nazwiskoTextField);
		nazwiskoTextField.setColumns(10);
		PracownicyTab.add(btnSzukaj);
		
		//po kilkieciu w okienko wpisywania usuwa tekst, a gdy niczego nie wpisalismy, wpisuje ponownie podpowiedz
	    nazwiskoTextField.addFocusListener(new FocusListener(){
	    	
	        public void focusGained(FocusEvent e){
	        	if (nazwiskoTextField.getText().equals("Nazwisko")){
	        		nazwiskoTextField.setText(null);
	        		nazwiskoTextField.setForeground(Color.BLACK);
			     }
	        }

			public void focusLost(FocusEvent e) {
				if (nazwiskoTextField.getText().equals("")){
					nazwiskoTextField.setForeground(Color.GRAY);
					nazwiskoTextField.setText("Nazwisko");
				     }
			}
	    });
		
		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//stworz okienko
				PracownikDialog okienko = new PracownikDialog(pracownikDao, Main.this);
				okienko.setLocationRelativeTo(null);
				okienko.setResizable(false);

				//pokaz okienko
				okienko.setVisible(true);	
			}
		});
		btnDodaj.setBounds(1089, 109, 98, 38);
		PracownicyTab.add(btnDodaj);
		
		stanowiskoTextField = new JTextField();
		stanowiskoTextField.setForeground(Color.GRAY);
		stanowiskoTextField.setToolTipText("Stanowisko");
		stanowiskoTextField.setText("Stanowisko");
		stanowiskoTextField.setBounds(279, 23, 120, 20);
		PracownicyTab.add(stanowiskoTextField);
		stanowiskoTextField.setColumns(10);
		
	    stanowiskoTextField.addFocusListener(new FocusListener(){
	    	
	        public void focusGained(FocusEvent e){
	        	if (stanowiskoTextField.getText().equals("Stanowisko")){
	        		stanowiskoTextField.setText(null);
	        		stanowiskoTextField.setForeground(Color.BLACK);
			     }
	        }

			public void focusLost(FocusEvent e) {
				if (stanowiskoTextField.getText().equals("")){
					stanowiskoTextField.setForeground(Color.GRAY);
				      	stanowiskoTextField.setText("Stanowisko");
				     }
			}
	    });
		
		imieTextField = new JTextField();
		imieTextField.setForeground(Color.GRAY);
		imieTextField.setToolTipText("Imie");
		imieTextField.setText("Imie");
		imieTextField.setBounds(147, 23, 120, 20);
		PracownicyTab.add(imieTextField);
		imieTextField.setColumns(10);
		
		imieTextField.addFocusListener(new FocusListener(){
	    	
	        public void focusGained(FocusEvent e){
	        	if (imieTextField.getText().equals("Imie")){
	        		imieTextField.setText(null);
	        		imieTextField.setForeground(Color.BLACK);
			     }
	            
	        }

			public void focusLost(FocusEvent e) {
				if (imieTextField.getText().equals("")){
					imieTextField.setForeground(Color.GRAY);
					imieTextField.setText("Imie");
				     }
			}
	    });
		
		ulicaTextField = new JTextField();
		ulicaTextField.setForeground(Color.GRAY);
		ulicaTextField.setToolTipText("Ulica");
		ulicaTextField.setText("Ulica");
		ulicaTextField.setBounds(413, 23, 120, 20);
		PracownicyTab.add(ulicaTextField);
		ulicaTextField.setColumns(10);
		
		ulicaTextField.addFocusListener(new FocusListener(){
	    	
	        public void focusGained(FocusEvent e){
	        	if (ulicaTextField.getText().equals("Ulica")){
	        		ulicaTextField.setText(null);
	        		ulicaTextField.setForeground(Color.BLACK);
			     }
	            
	        }

			public void focusLost(FocusEvent e) {
				if (ulicaTextField.getText().equals("")){
					ulicaTextField.setForeground(Color.GRAY);
					ulicaTextField.setText("Ulica");
				     }
			}
	    });
		
		miejscowoscTextField = new JTextField();
		miejscowoscTextField.setForeground(Color.GRAY);
		miejscowoscTextField.setToolTipText("Miejscowosc");
		miejscowoscTextField.setText("Miejscowosc");
		miejscowoscTextField.setBounds(545, 23, 120, 20);
		PracownicyTab.add(miejscowoscTextField);
		miejscowoscTextField.setColumns(10);
		
	    miejscowoscTextField.addFocusListener(new FocusListener(){
	    	
	        public void focusGained(FocusEvent e){
	        	if (miejscowoscTextField.getText().equals("Miejscowosc")){
	        		miejscowoscTextField.setText(null);
	        		miejscowoscTextField.setForeground(Color.BLACK);
			     }
	        }

			public void focusLost(FocusEvent e) {
				if (miejscowoscTextField.getText().equals("")){
					miejscowoscTextField.setForeground(Color.GRAY);
					miejscowoscTextField.setText("Miejscowosc");
				     }
			}
	    });
		
		peselTextField = new JTextField();
		peselTextField.setForeground(Color.GRAY);
		peselTextField.setToolTipText("Pesel");
		peselTextField.setText("Pesel");
		peselTextField.setBounds(677, 23, 120, 20);
		PracownicyTab.add(peselTextField);
		peselTextField.setColumns(10);
		
		peselTextField.addFocusListener(new FocusListener(){
	    	
	        public void focusGained(FocusEvent e){
	        	if (peselTextField.getText().equals("Pesel")){
	        		peselTextField.setText(null);
	        		peselTextField.setForeground(Color.BLACK);
			     }
	            
	        }

			public void focusLost(FocusEvent e) {
				if (peselTextField.getText().equals("")){
					peselTextField.setForeground(Color.GRAY);
					peselTextField.setText("Pesel");
				     }
			}
	    });
		
		nrKontaTextField = new JTextField();
		nrKontaTextField.setForeground(Color.GRAY);
		nrKontaTextField.setToolTipText("Nr Konta");
		nrKontaTextField.setText("Nr Konta");
		nrKontaTextField.setBounds(809, 23, 120, 20);
		PracownicyTab.add(nrKontaTextField);
		nrKontaTextField.setColumns(10);
		
		nrKontaTextField.addFocusListener(new FocusListener(){
	    	
	        public void focusGained(FocusEvent e){
	        	if (nrKontaTextField.getText().equals("Nr Konta")){
	        		nrKontaTextField.setText(null);
	        		nrKontaTextField.setForeground(Color.BLACK);
			     }
	        }

			public void focusLost(FocusEvent e) {
				if (nrKontaTextField.getText().equals("")){
					nrKontaTextField.setForeground(Color.GRAY);
					nrKontaTextField.setText("Nr Konta");
				     }
			}
	    });
		
		nrTelTextField = new JTextField();
		nrTelTextField.setForeground(Color.GRAY);
		nrTelTextField.setToolTipText("Nr Tel");
		nrTelTextField.setText("Nr Tel");
		nrTelTextField.setBounds(941, 23, 120, 20);
		PracownicyTab.add(nrTelTextField);
		nrTelTextField.setColumns(10);
		
		nrTelTextField.addFocusListener(new FocusListener(){
	    	
	        public void focusGained(FocusEvent e){
	        	if (nrTelTextField.getText().equals("Nr Tel")){
	        		nrTelTextField.setText(null);
	        		nrTelTextField.setForeground(Color.BLACK);
			     }
	        }

			public void focusLost(FocusEvent e) {
				if (nrTelTextField.getText().equals("")){
					nrTelTextField.setForeground(Color.GRAY);
					nrTelTextField.setText("Nr Tel");
				     }
			}
	    });
		
		btnEdytuj = new JButton("Edytuj");
		btnEdytuj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("EDYCJA");
				
				//wez zaznaczony element
				int row = table.getSelectedRow();
				System.out.println(row);
				
				//sprawdz czy jest zaznaczony pracownik
				if(row<0){
					JOptionPane.showMessageDialog(Main.this, "Musisz zaznaczyc pracownika", "BLAD", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				//pobierz pracownika
				Pracownik tempPracownik = (Pracownik) table.getValueAt(row, PracownikModelTabeli.OBJECT_COL);
				
				//stworz okienko
				PracownikDialog okienko = new PracownikDialog(pracownikDao, Main.this, tempPracownik, true);
				okienko.setLocationRelativeTo(null);
				okienko.setResizable(false);
				
				//pokaz okienko
				okienko.setVisible(true);
			}
		});
		btnEdytuj.setBounds(1089, 159, 98, 38);
		PracownicyTab.add(btnEdytuj);
		
		btnUsun = new JButton("Usun");
		btnUsun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					//pobierz numer wiersza
					int row = table.getSelectedRow();
					
					//sprawdz czy jest zaznaczony pracownik
					if(row<0){
						JOptionPane.showMessageDialog(Main.this, "Musisz zaznaczyc pracownika", "BLAD", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					//potwierdzenie od uzytkownika
					int odpowiedz = JOptionPane.showConfirmDialog(Main.this, "Usunac pracownika?",
							"Potwierdz", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(odpowiedz != JOptionPane.YES_OPTION){
						return;
					}
					
					//pobierdz zaznaczonego pracownika
					Pracownik tempPracownik = (Pracownik) table.getValueAt(row, PracownikModelTabeli.OBJECT_COL);
					
					//usun pracownika
					pracownikDao.usunPracowni(tempPracownik.getId());
					
					//odswierz liste
					odswierzListePracownikow();
					
					//wyswietl potwierdzenie
					JOptionPane.showMessageDialog(Main.this, "Pracownik usuniï¿½ty", "Usunieto", JOptionPane.INFORMATION_MESSAGE);
					
				}catch(Exception ex){
					JOptionPane.showMessageDialog(Main.this, "Blad przy usuwaniu pracownika: " + ex.getMessage(), "BLAD", JOptionPane.ERROR_MESSAGE);
					System.err.println("Blad przy usuwaniu pracownika: " + ex.getMessage());
					
				}

			}
		});
		btnUsun.setBounds(1089, 209, 98, 38);
		PracownicyTab.add(btnUsun);
//////////////////////////// koniec PracownicyTab
		
		
		
	}
	
	
	
	public void odswierzListePracownikow() {

		try {
			List<Pracownik> pracownicy = pracownikDao.getAllPracownik();

			// stworz model i odswierz tabele
			PracownikModelTabeli model = new PracownikModelTabeli(pracownicy);

			table.setModel(model);
			
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "BLAD: " + exc, "BLAD",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	
}
