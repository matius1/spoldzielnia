package spoldzielnia_mieszkaniowa;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
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
		setBounds(100, 100, 1018, 518);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{81, 39, 240, 612, 0};
		gbl_contentPane.rowHeights = new int[]{23, 402, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNazwisko = new JLabel("Nazwisko");
		GridBagConstraints gbc_lblNazwisko = new GridBagConstraints();
		gbc_lblNazwisko.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNazwisko.insets = new Insets(0, 0, 5, 5);
		gbc_lblNazwisko.gridx = 0;
		gbc_lblNazwisko.gridy = 0;
		contentPane.add(lblNazwisko, gbc_lblNazwisko);
		
		nazwiskoTextField = new JTextField();
		GridBagConstraints gbc_nazwiskoTextField = new GridBagConstraints();
		gbc_nazwiskoTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nazwiskoTextField.insets = new Insets(0, 0, 5, 5);
		gbc_nazwiskoTextField.gridx = 2;
		gbc_nazwiskoTextField.gridy = 0;
		contentPane.add(nazwiskoTextField, gbc_nazwiskoTextField);
		nazwiskoTextField.setColumns(10);
		
		JButton btnSzukaj = new JButton("Szukaj");
		btnSzukaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//pobierz nazwisko z nazwiskoTextField
				
				
				//wywolaj DAO i pobierz pracownikow o szukanym nazwisku
				
				//jesli nazwiskoTextField jest puste to lista wszystkich pracownikow
				
				//wyswietl liste
				
				try{
					String nazwisko = nazwiskoTextField.getText();
					
					List<Pracownik> listaPracownikow = null;
					
					if(nazwisko != null && nazwisko.trim().length() > 0 ){
						listaPracownikow = pracownikDao.szukajPracownik(nazwisko);
					}else{
						listaPracownikow = pracownikDao.getAllPracownik();
					}
					
					for(Pracownik temp: listaPracownikow){
						System.out.println(temp);
					}
					
					//stworz model i odswiez tabele
					PracownikModelTabeli pracownikModel = new PracownikModelTabeli(listaPracownikow);
					table.setModel(pracownikModel);
					
					
					
				}catch(Exception e){
					JOptionPane.showMessageDialog(PracownikSearch.this, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE); 
				}
				
			}
		});
		GridBagConstraints gbc_btnSzukaj = new GridBagConstraints();
		gbc_btnSzukaj.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnSzukaj.insets = new Insets(0, 0, 5, 0);
		gbc_btnSzukaj.gridx = 3;
		gbc_btnSzukaj.gridy = 0;
		contentPane.add(btnSzukaj, gbc_btnSzukaj);
		
		
		table = new JTable();
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		contentPane.add(scrollPane, gbc_scrollPane);
		
	}
}
