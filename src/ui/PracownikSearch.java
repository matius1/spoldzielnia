package ui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Caret;

import core.Pracownik;
import dao.PracownikDAO;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class PracownikSearch extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nazwiskoTextField;
	private JTable table;
	
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
					PracownikSearch frame = new PracownikSearch();
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
	public PracownikSearch() {
		
		//DAO
		pracownikDao = new PracownikDAO();
		
		
		//okno
		setTitle("Szukaj Pracownika");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1226, 569);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnSzukaj = new JButton("Szukaj");
		btnSzukaj.setBounds(1089, 34, 98, 38);
		btnSzukaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
		
				try{
					//pobierz nazwisko z nazwiskoTextField
					// sprawdzanie czy nie ma tam tekstu z ficzera
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
							
					//jesli nazwiskoTextField jest puste to lista wszystkich pracownikow
//					if(nazwisko != null ){
//						listaPracownikow = pracownikDao.szukajPracownik(nazwisko, stanowisko);
//					}
//					else{
//						listaPracownikow = pracownikDao.getAllPracownik();
//					}
					
					//wyswietl liste
					for(Pracownik temp: listaPracownikow){
						System.out.println(temp);
					}
					
					//stworz model i odswiez tabele, wyswietl liste
					PracownikModelTabeli pracownikModel = new PracownikModelTabeli(listaPracownikow);
					table.setModel(pracownikModel);
					
					
					
				}catch(Exception e){
					JOptionPane.showMessageDialog(PracownikSearch.this, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE); 
				}
				
			}
		});
		contentPane.setLayout(null);
		
		nazwiskoTextField = new JTextField();
		nazwiskoTextField.setForeground(Color.GRAY);
		nazwiskoTextField.setToolTipText("Nazwisko");
		nazwiskoTextField.setText("Nazwisko");
		nazwiskoTextField.setBounds(15, 40, 120, 20);
		contentPane.add(nazwiskoTextField);
		nazwiskoTextField.setColumns(10);
		contentPane.add(btnSzukaj);
		
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
		
		
		table = new JTable();
		scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 71, 1047, 453);
		scrollPane.setViewportView(table);
		contentPane.add(scrollPane);
		
		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//stworz okienko
				PracownikDialog okienko = new PracownikDialog(pracownikDao, PracownikSearch.this);
				
				//pokaz okienko
				okienko.setVisible(true);
				
				
			}
		});
		btnDodaj.setBounds(1089, 123, 98, 38);
		contentPane.add(btnDodaj);
		
		stanowiskoTextField = new JTextField();
		stanowiskoTextField.setForeground(Color.GRAY);
		stanowiskoTextField.setToolTipText("Stanowisko");
		stanowiskoTextField.setText("Stanowisko");
		stanowiskoTextField.setBounds(275, 40, 120, 20);
		contentPane.add(stanowiskoTextField);
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
		imieTextField.setBounds(145, 40, 120, 20);
		contentPane.add(imieTextField);
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
		ulicaTextField.setBounds(405, 40, 120, 20);
		contentPane.add(ulicaTextField);
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
		miejscowoscTextField.setBounds(535, 40, 120, 20);
		contentPane.add(miejscowoscTextField);
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
		peselTextField.setBounds(665, 40, 120, 20);
		contentPane.add(peselTextField);
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
		nrKontaTextField.setBounds(795, 40, 120, 20);
		contentPane.add(nrKontaTextField);
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
		nrTelTextField.setBounds(925, 40, 120, 20);
		contentPane.add(nrTelTextField);
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
					JOptionPane.showMessageDialog(PracownikSearch.this, "Musisz zaznaczyc pracownika", "BLAD", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				//pobierz pracownika
				Pracownik tempPracownik = (Pracownik) table.getValueAt(row, PracownikModelTabeli.OBJECT_COL);
				
				//stworz okienko
				PracownikDialog okienko = new PracownikDialog(pracownikDao, PracownikSearch.this, tempPracownik, true);
				
				//pokaz okienko
				okienko.setVisible(true);
				
			}
		});
		btnEdytuj.setBounds(1089, 190, 98, 38);
		contentPane.add(btnEdytuj);
		
		btnUsun = new JButton("Usun");
		btnUsun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					//pobierz numer wiersza
					int row = table.getSelectedRow();
					
					//sprawdz czy jest zaznaczony pracownik
					if(row<0){
						JOptionPane.showMessageDialog(PracownikSearch.this, "Musisz zaznaczyc pracownika", "BLAD", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					//potwierdzenie od uzytkownika
					int odpowiedz = JOptionPane.showConfirmDialog(PracownikSearch.this, "Usunac pracownika?",
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
					JOptionPane.showMessageDialog(PracownikSearch.this, "Pracownik usuni�ty", "Usunieto", JOptionPane.INFORMATION_MESSAGE);
					
				}catch(Exception ex){
					JOptionPane.showMessageDialog(PracownikSearch.this, "Blad przy usuwaniu pracownika: " + ex.getMessage(), "BLAD", JOptionPane.ERROR_MESSAGE);
					
				}
				
				
			}
		});
		btnUsun.setBounds(1089, 260, 98, 38);
		contentPane.add(btnUsun);
		
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
