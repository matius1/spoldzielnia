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
import core.Wlasciciel;
import dao.PracownikDAO;
import dao.WlascicielDAO;

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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private JPanel contentPane;
	
	
	private JTable table;
	private JTable tableW;
	
	private JTextField nazwiskoTextFieldP;
	private JScrollPane scrollPaneP;
	private JTextField stanowiskoTextFieldP;
	private JTextField imieTextFieldP;
	private JTextField ulicaTextFieldP;
	private JTextField miejscowoscTextFieldP;
	private JTextField peselTextFieldP;
	private JTextField nrKontaTextFieldP;
	private JTextField nrTelTextFieldP;
	private JButton btnEdytujP;
	private JButton btnUsunP;
	
	
	private PracownikDAO pracownikDao;
	private WlascicielDAO wlascicielDao;
	private JTextField nazwiskoWTextField;
	private JScrollPane scrollPaneWlasciciele; 
	private JScrollPane scrollPaneWlasciciele_1;
	private JTextField imieWTextField;
	private JTextField peselWTextField;
	private JTextField ulicaWTextField;
	private JTextField miejscowoscWTextField;
	private JTextField nrTelWTextField;
	private JButton btnDodajW;
	private JButton btnEdytujW;
	private JButton btnUsunW;


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
		wlascicielDao = new WlascicielDAO();
		
		
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
		
		
		
		
////////////////////////////////	Pracownicy TAB		
		
		JPanel PracownicyTab = new JPanel();
		tabbedPane.addTab("Pracownicy", null, PracownicyTab, null);
		PracownicyTab.setLayout(null);
		
		
		table = new JTable();
		scrollPaneP = new JScrollPane();
		scrollPaneP.setBounds(10, 71, 1051, 431);
		scrollPaneP.setViewportView(table);
		PracownicyTab.add(scrollPaneP);
		
		
		JButton btnSzukajP = new JButton("Szukaj");
		btnSzukajP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				try{
					//pobierz nazwisko z nazwiskoTextField, sprawdzanie czy nie ma tam tekstu z ficzera, gdy jest zamienia na pustego stringa
					String nazwisko = nazwiskoTextFieldP.getText();
					if(nazwiskoTextFieldP.getText().equals("Nazwisko")){
						nazwisko= "";
					}
					
					String stanowisko = stanowiskoTextFieldP.getText();
					if(stanowiskoTextFieldP.getText().equals("Stanowisko")){
						stanowisko = "";
					}
					
					String imie = imieTextFieldP.getText();
					if(imieTextFieldP.getText().equals("Imie")){
						imie = "";
					}
					
					String ulica = ulicaTextFieldP.getText();
					if(ulicaTextFieldP.getText().equals("Ulica")){
						ulica = "";
					}
					
					String miejscowosc = miejscowoscTextFieldP.getText();
					if(miejscowoscTextFieldP.getText().equals("Miejscowosc")){
						miejscowosc = "";
					}
					
					String pesel = peselTextFieldP.getText();
					if(peselTextFieldP.getText().equals("Pesel")){
						pesel = "";
					}
					
					String nrTel = nrTelTextFieldP.getText();
					if(nrTelTextFieldP.getText().equals("Nr Tel")){
						nrTel = "";
					}
					
					String nrKonta = nrKontaTextFieldP.getText();
					if(nrKontaTextFieldP.getText().equals("Nr Konta")){
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
		btnSzukajP.setBounds(1098, 22, 89, 23);
		PracownicyTab.add(btnSzukajP);
		

		nazwiskoTextFieldP = new JTextField();
		nazwiskoTextFieldP.setForeground(Color.GRAY);
		nazwiskoTextFieldP.setToolTipText("Nazwisko");
		nazwiskoTextFieldP.setText("Nazwisko");
		nazwiskoTextFieldP.setBounds(15, 23, 120, 20);
		PracownicyTab.add(nazwiskoTextFieldP);
		nazwiskoTextFieldP.setColumns(10);
		PracownicyTab.add(btnSzukajP);
		
		//po kilkieciu w okienko wpisywania usuwa tekst, a gdy niczego nie wpisalismy, wpisuje ponownie podpowiedz
	    nazwiskoTextFieldP.addFocusListener(new FocusListener(){
	    	
	        public void focusGained(FocusEvent e){
	        	if (nazwiskoTextFieldP.getText().equals("Nazwisko")){
	        		nazwiskoTextFieldP.setText(null);
	        		nazwiskoTextFieldP.setForeground(Color.BLACK);
			     }
	        }

			public void focusLost(FocusEvent e) {
				if (nazwiskoTextFieldP.getText().equals("")){
					nazwiskoTextFieldP.setForeground(Color.GRAY);
					nazwiskoTextFieldP.setText("Nazwisko");
				     }
			}
	    });
		
		JButton btnDodajP = new JButton("Dodaj");
		btnDodajP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//stworz okienko
				PracownikDialog okienko = new PracownikDialog(pracownikDao, Main.this);
				okienko.setLocationRelativeTo(null);
				okienko.setResizable(false);

				//pokaz okienko
				okienko.setVisible(true);	
			}
		});
		btnDodajP.setBounds(1089, 109, 98, 38);
		PracownicyTab.add(btnDodajP);
		
		stanowiskoTextFieldP = new JTextField();
		stanowiskoTextFieldP.setForeground(Color.GRAY);
		stanowiskoTextFieldP.setToolTipText("Stanowisko");
		stanowiskoTextFieldP.setText("Stanowisko");
		stanowiskoTextFieldP.setBounds(279, 23, 120, 20);
		PracownicyTab.add(stanowiskoTextFieldP);
		stanowiskoTextFieldP.setColumns(10);
		
	    stanowiskoTextFieldP.addFocusListener(new FocusListener(){
	    	
	        public void focusGained(FocusEvent e){
	        	if (stanowiskoTextFieldP.getText().equals("Stanowisko")){
	        		stanowiskoTextFieldP.setText(null);
	        		stanowiskoTextFieldP.setForeground(Color.BLACK);
			     }
	        }

			public void focusLost(FocusEvent e) {
				if (stanowiskoTextFieldP.getText().equals("")){
					stanowiskoTextFieldP.setForeground(Color.GRAY);
				      	stanowiskoTextFieldP.setText("Stanowisko");
				     }
			}
	    });
		
		imieTextFieldP = new JTextField();
		imieTextFieldP.setForeground(Color.GRAY);
		imieTextFieldP.setToolTipText("Imie");
		imieTextFieldP.setText("Imie");
		imieTextFieldP.setBounds(147, 23, 120, 20);
		PracownicyTab.add(imieTextFieldP);
		imieTextFieldP.setColumns(10);
		
		imieTextFieldP.addFocusListener(new FocusListener(){
	    	
	        public void focusGained(FocusEvent e){
	        	if (imieTextFieldP.getText().equals("Imie")){
	        		imieTextFieldP.setText(null);
	        		imieTextFieldP.setForeground(Color.BLACK);
			     }
	            
	        }

			public void focusLost(FocusEvent e) {
				if (imieTextFieldP.getText().equals("")){
					imieTextFieldP.setForeground(Color.GRAY);
					imieTextFieldP.setText("Imie");
				     }
			}
	    });
		
		ulicaTextFieldP = new JTextField();
		ulicaTextFieldP.setForeground(Color.GRAY);
		ulicaTextFieldP.setToolTipText("Ulica");
		ulicaTextFieldP.setText("Ulica");
		ulicaTextFieldP.setBounds(413, 23, 120, 20);
		PracownicyTab.add(ulicaTextFieldP);
		ulicaTextFieldP.setColumns(10);
		
		ulicaTextFieldP.addFocusListener(new FocusListener(){
	    	
	        public void focusGained(FocusEvent e){
	        	if (ulicaTextFieldP.getText().equals("Ulica")){
	        		ulicaTextFieldP.setText(null);
	        		ulicaTextFieldP.setForeground(Color.BLACK);
			     }
	            
	        }

			public void focusLost(FocusEvent e) {
				if (ulicaTextFieldP.getText().equals("")){
					ulicaTextFieldP.setForeground(Color.GRAY);
					ulicaTextFieldP.setText("Ulica");
				     }
			}
	    });
		
		miejscowoscTextFieldP = new JTextField();
		miejscowoscTextFieldP.setForeground(Color.GRAY);
		miejscowoscTextFieldP.setToolTipText("Miejscowosc");
		miejscowoscTextFieldP.setText("Miejscowosc");
		miejscowoscTextFieldP.setBounds(545, 23, 120, 20);
		PracownicyTab.add(miejscowoscTextFieldP);
		miejscowoscTextFieldP.setColumns(10);
		
	    miejscowoscTextFieldP.addFocusListener(new FocusListener(){
	    	
	        public void focusGained(FocusEvent e){
	        	if (miejscowoscTextFieldP.getText().equals("Miejscowosc")){
	        		miejscowoscTextFieldP.setText(null);
	        		miejscowoscTextFieldP.setForeground(Color.BLACK);
			     }
	        }

			public void focusLost(FocusEvent e) {
				if (miejscowoscTextFieldP.getText().equals("")){
					miejscowoscTextFieldP.setForeground(Color.GRAY);
					miejscowoscTextFieldP.setText("Miejscowosc");
				     }
			}
	    });
		
		peselTextFieldP = new JTextField();
		peselTextFieldP.setForeground(Color.GRAY);
		peselTextFieldP.setToolTipText("Pesel");
		peselTextFieldP.setText("Pesel");
		peselTextFieldP.setBounds(677, 23, 120, 20);
		PracownicyTab.add(peselTextFieldP);
		peselTextFieldP.setColumns(10);
		
		peselTextFieldP.addFocusListener(new FocusListener(){
	    	
	        public void focusGained(FocusEvent e){
	        	if (peselTextFieldP.getText().equals("Pesel")){
	        		peselTextFieldP.setText(null);
	        		peselTextFieldP.setForeground(Color.BLACK);
			     }
	            
	        }

			public void focusLost(FocusEvent e) {
				if (peselTextFieldP.getText().equals("")){
					peselTextFieldP.setForeground(Color.GRAY);
					peselTextFieldP.setText("Pesel");
				     }
			}
	    });
		
		nrKontaTextFieldP = new JTextField();
		nrKontaTextFieldP.setForeground(Color.GRAY);
		nrKontaTextFieldP.setToolTipText("Nr Konta");
		nrKontaTextFieldP.setText("Nr Konta");
		nrKontaTextFieldP.setBounds(809, 23, 120, 20);
		PracownicyTab.add(nrKontaTextFieldP);
		nrKontaTextFieldP.setColumns(10);
		
		nrKontaTextFieldP.addFocusListener(new FocusListener(){
	    	
	        public void focusGained(FocusEvent e){
	        	if (nrKontaTextFieldP.getText().equals("Nr Konta")){
	        		nrKontaTextFieldP.setText(null);
	        		nrKontaTextFieldP.setForeground(Color.BLACK);
			     }
	        }

			public void focusLost(FocusEvent e) {
				if (nrKontaTextFieldP.getText().equals("")){
					nrKontaTextFieldP.setForeground(Color.GRAY);
					nrKontaTextFieldP.setText("Nr Konta");
				     }
			}
	    });
		
		nrTelTextFieldP = new JTextField();
		nrTelTextFieldP.setForeground(Color.GRAY);
		nrTelTextFieldP.setToolTipText("Nr Tel");
		nrTelTextFieldP.setText("Nr Tel");
		nrTelTextFieldP.setBounds(941, 23, 120, 20);
		PracownicyTab.add(nrTelTextFieldP);
		nrTelTextFieldP.setColumns(10);
		
		nrTelTextFieldP.addFocusListener(new FocusListener(){
	    	
	        public void focusGained(FocusEvent e){
	        	if (nrTelTextFieldP.getText().equals("Nr Tel")){
	        		nrTelTextFieldP.setText(null);
	        		nrTelTextFieldP.setForeground(Color.BLACK);
			     }
	        }

			public void focusLost(FocusEvent e) {
				if (nrTelTextFieldP.getText().equals("")){
					nrTelTextFieldP.setForeground(Color.GRAY);
					nrTelTextFieldP.setText("Nr Tel");
				     }
			}
	    });
		
		btnEdytujP = new JButton("Edytuj");
		btnEdytujP.addActionListener(new ActionListener() {
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
		btnEdytujP.setBounds(1089, 159, 98, 38);
		PracownicyTab.add(btnEdytujP);
		
		btnUsunP = new JButton("Usun");
		btnUsunP.addActionListener(new ActionListener() {
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
					
					//pobierz zaznaczonego pracownika
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
		btnUsunP.setBounds(1089, 209, 98, 38);
		PracownicyTab.add(btnUsunP);
		
		
//////////////////////////// koniec PracownicyTab
		
		

		
		
		
//////////////////////////////////Wlasciciele TAB
		JPanel WlasicieleTab = new JPanel();
		tabbedPane.addTab("Wlasciciele", null, WlasicieleTab, null);
		WlasicieleTab.setLayout(null);

		
		//
		tableW = new JTable();
		scrollPaneWlasciciele = new JScrollPane();
		scrollPaneWlasciciele_1 = new JScrollPane();
		scrollPaneWlasciciele_1.setBounds(35, 73, 1000, 390);
		scrollPaneWlasciciele_1.setViewportView(tableW);
		WlasicieleTab.add(scrollPaneWlasciciele_1);
		//
		
		JButton btnSzukajWlasciciele = new JButton("Szukaj");
		btnSzukajWlasciciele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				try{
				
					String nazwisko = nazwiskoWTextField.getText();
					if(nazwiskoWTextField.getText().equals("Nazwisko")){
						nazwisko= "";
					}
				
					String imie = imieWTextField.getText();
					if(imieWTextField.getText().equals("Imie")){
						imie = "";
					}
					
					String ulica = ulicaWTextField.getText();
					if(ulicaWTextField.getText().equals("Ulica")){
						ulica = "";
					}
					
					String miejscowosc = miejscowoscWTextField.getText();
					if(miejscowoscWTextField.getText().equals("Miejscowosc")){
						miejscowosc = "";
					}
					
					String pesel = peselWTextField.getText();
					if(peselWTextField.getText().equals("Pesel")){
						pesel = "";
					}
					
					String nrTel = nrTelWTextField.getText();
					if(nrTelWTextField.getText().equals("Nr Tel")){
						nrTel = "";
					}
				
					
				List<Wlasciciel> listaWlascicieli = null;
				
				//wywolaj DAO i pobierz pracownikow o szukanym nazwisku
//				listaWlascicieli = wlascicielDao.szukajWlasciciel(nazwisko);
				listaWlascicieli = wlascicielDao.szukajWlasciciel(nazwisko, imie, ulica, miejscowosc, pesel, nrTel);

				//wyswietl liste
				for(Wlasciciel temp: listaWlascicieli){
					System.out.println(temp);
				}
				
				//stworz model i odswiez tabele, wyswietl liste
				WlascicielModelTabeli wlascicielModel = new WlascicielModelTabeli(listaWlascicieli);
				tableW.setModel(wlascicielModel);

				}catch(Exception e){
					JOptionPane.showMessageDialog(Main.this, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
					System.err.println("B³¹d przy wyszukiwaniu: ");
					System.err.println(e);
				}
				
			}
		});
		
		
		btnSzukajWlasciciele.setBounds(1081, 27, 89, 23);
		WlasicieleTab.add(btnSzukajWlasciciele);
		
		nazwiskoWTextField = new JTextField();
		nazwiskoWTextField.setBounds(35, 28, 86, 20);
		WlasicieleTab.add(nazwiskoWTextField);
		nazwiskoWTextField.setColumns(10);
		
		imieWTextField = new JTextField();
		imieWTextField.setBounds(213, 28, 86, 20);
		WlasicieleTab.add(imieWTextField);
		imieWTextField.setColumns(10);
		
		peselWTextField = new JTextField();
		peselWTextField.setText("");
		peselWTextField.setBounds(424, 28, 86, 20);
		WlasicieleTab.add(peselWTextField);
		peselWTextField.setColumns(10);
		
		ulicaWTextField = new JTextField();
		ulicaWTextField.setBounds(604, 28, 86, 20);
		WlasicieleTab.add(ulicaWTextField);
		ulicaWTextField.setColumns(10);
		
		miejscowoscWTextField = new JTextField();
		miejscowoscWTextField.setBounds(761, 28, 86, 20);
		WlasicieleTab.add(miejscowoscWTextField);
		miejscowoscWTextField.setColumns(10);
		
		nrTelWTextField = new JTextField();
		nrTelWTextField.setBounds(907, 28, 86, 20);
		WlasicieleTab.add(nrTelWTextField);
		nrTelWTextField.setColumns(10);
		
		btnDodajW = new JButton("Dodaj");
		btnDodajW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
			}
		});
		btnDodajW.setBounds(1081, 82, 89, 23);
		WlasicieleTab.add(btnDodajW);
		
		btnEdytujW = new JButton("Edytuj");
		btnEdytujW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btnEdytujW.setBounds(1081, 128, 89, 23);
		WlasicieleTab.add(btnEdytujW);
		
		btnUsunW = new JButton("Usun");
		btnUsunW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				

				try{
					//pobierz numer wiersza
					int row = tableW.getSelectedRow();
					
					//sprawdz czy jest zaznaczony wlasciciel
					if(row<0){
						JOptionPane.showMessageDialog(Main.this, "Musisz zaznaczyc wlasciciela", "BLAD", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					//potwierdzenie od uzytkownika
					int odpowiedz = JOptionPane.showConfirmDialog(Main.this, "Usunac wlasciciela?",
							"Potwierdz", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(odpowiedz != JOptionPane.YES_OPTION){
						return;
					}
					
					//pobierz zaznaczonego wlasciciela
//					Pracownik tempPracownik = (Pracownik) table.getValueAt(row, PracownikModelTabeli.OBJECT_COL);
					Wlasciciel tempWlasciciel = (Wlasciciel) tableW.getValueAt(row, WlascicielModelTabeli.OBJECT_COL);
					
					//usun pracownika
//					pracownikDao.usunPracowni(tempPracownik.getId());
					wlascicielDao.usunWlasciciel(tempWlasciciel.getId());
					
					//odswierz liste
					odswierzListeWlascicieli();
					
					//wyswietl potwierdzenie
					JOptionPane.showMessageDialog(Main.this, "Wlasciciel usuniêty", "Usunieto", JOptionPane.INFORMATION_MESSAGE);
					
				}catch(Exception ex){
					JOptionPane.showMessageDialog(Main.this, "Blad przy usuwaniu Wlasciciela: " + ex.getMessage(), "BLAD", JOptionPane.ERROR_MESSAGE);
					System.err.println("Blad przy usuwaniu wlasciciela: " + ex.getMessage());
					
				}
				
				
			}
		});
		btnUsunW.setBounds(1081, 172, 89, 23);
		WlasicieleTab.add(btnUsunW);
		
		
		
		
		
		
		
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
	
	
	
	public void odswierzListeWlascicieli() {

		try {
			List<Wlasciciel> wlasciciele = wlascicielDao.getAllWlasciciel();

			// stworz model i odswierz tabele
			WlascicielModelTabeli model = new WlascicielModelTabeli(wlasciciele);

			tableW.setModel(model);
			
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "BLAD: " + exc, "BLAD",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
