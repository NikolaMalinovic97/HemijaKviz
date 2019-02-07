package gui;

import java.awt.Font;
import java.awt.HeadlessException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import element.Odgovor;

public class Rezultat extends JFrame {

	private static final long serialVersionUID = 1L;
	 
	int poeni;
	ArrayList<Odgovor> odgovori;
	DefaultListModel<String> dlm1 = new DefaultListModel<String>();
	DefaultListModel<String> dlm2 = new DefaultListModel<String>();
	
	public Rezultat(int poeni, ArrayList<Odgovor> odgovori) throws HeadlessException {
		
		super("Rezultat");
		this.poeni = poeni;
		this.odgovori = odgovori;
		
		setVisible(true);
		setResizable(false);
		getContentPane().setLayout(null);
		setBounds(this.getWidth(), this.getHeight(), 1000, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//Deklaracija komponenti frejma
		JList<String> tekst1 = new JList<String>(dlm1);
		JList<String> tekst2 = new JList<String>(dlm2);
		JLabel bodovi = new JLabel("BODOVA: " + poeni);
		
		//Pozicioniranje komponenti frejma
		tekst1.setBounds(10, 10, 480, 680);
		tekst2.setBounds(490, 10, 500, 680);
		bodovi.setBounds(450, 700, 200, 40);
		bodovi.setFont(new Font("Serif", Font.PLAIN, 22));
		tekst1.setFont(new Font("Arial", Font.BOLD, 12));
		tekst2.setFont(new Font("Arial", Font.BOLD, 12));
		
		//Dodavanje komponenti frejma
		add(tekst1);
		add(tekst2);
		add(bodovi);
		
		//Dodavanje pitanja i odgovora u JList tekst
		for (int i = 0; i < 10; i++) {
			dlm1.addElement((i+1) + ". " + "PITANJE: " + odgovori.get(i).getPitanje());
			dlm1.addElement("TACAN ODGOVOR: " + odgovori.get(i).getTacanOdgovor());
			dlm1.addElement("TAKMICAREV ODGOVOR: " + odgovori.get(i).getTakmicarevOdgovor());
			dlm1.addElement("\n");
		}
		for (int i = 10; i < odgovori.size(); i++) {
			dlm2.addElement((i+1) + ". " + "PITANJE: " + odgovori.get(i).getPitanje());
			dlm2.addElement("TACAN ODGOVOR: " + odgovori.get(i).getTacanOdgovor());
			dlm2.addElement("TAKMICAREV ODGOVOR: " + odgovori.get(i).getTakmicarevOdgovor());
			dlm2.addElement("\n");
		}
		
	}
	
	
}
