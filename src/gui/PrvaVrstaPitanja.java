package gui;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import element.HemijskiElement;
import element.Odgovor;

public class PrvaVrstaPitanja extends JFrame {

	private static final long serialVersionUID = 1L;
	
	static int brojPitanja;
	int poeni;
	ArrayList<HemijskiElement> elementi;
	ArrayList<Odgovor> odgovori;
	static int sekunde = 30;
	
	//Deklarisanje varijabli potrebnih za pitanje i odgovor
	String pitanje = null;
	String tacanOdgovor = null;
	String takmicarevOdgovor = null;
	
	
	public PrvaVrstaPitanja(int brojPitanja, int poeni, ArrayList<HemijskiElement> elementi,
			ArrayList<Odgovor> odgovori) throws HeadlessException {
		
		super("Pitanje br. " + brojPitanja);
		PrvaVrstaPitanja.brojPitanja = brojPitanja;
		this.poeni = poeni;
		this.elementi = elementi;
		this.odgovori = odgovori;
		PrvaVrstaPitanja.sekunde = 30;
		
		setVisible(true);
		setResizable(false);
		getContentPane().setLayout(null);
		setBounds(this.getWidth(), this.getHeight(), 1000, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Generisanje random broja od 0 do 49 (50 elemenata ukupno)
		int random = (int) (Math.random() * 50);
		
		//Dio aplikacije odgovoran za kreiranje nasumicnog pitanja
		switch(brojPitanja) {
		
			case 1: pitanje = "Koja je oznaka elementa " + elementi.get(random).getNaziv() + "?";
					tacanOdgovor = elementi.get(random).getOznaka();
					break;
					
			case 2: pitanje = "Kako se zove element cije je oznaka " + elementi.get(random).getOznaka() + "?";
					tacanOdgovor = elementi.get(random).getNaziv();
					break;
					
			case 3: pitanje = "Kolika je atosmka tezina elementa " + elementi.get(random).getNaziv() + "?";
					tacanOdgovor = String.valueOf(elementi.get(random).getAtomskaTezina());
					break;
					
			case 4: pitanje = "Koji je redni broj elementa " + elementi.get(random).getNaziv() + "?";
					tacanOdgovor = String.valueOf(elementi.get(random).getRedniBroj());
					break;
					
			case 5: pitanje = "Koje je vrste element " + elementi.get(random).getNaziv() + "?";
					tacanOdgovor = elementi.get(random).getVrsta();
					break;
		}
		
		//Deklaracija komponenti frejma
		JLabel jlPoeni = new JLabel("POENI: " + poeni);
		JLabel jlVrijeme = new JLabel("VRIJEME: " + sekunde);
		JLabel jlPitanje = new JLabel("PITANJE: " + pitanje);
		JTextField jtfOdgovor = new JTextField();
		JButton odgovor = new JButton("ODGOVORI");
		JButton preskoci = new JButton("PRESKOCI");
		
		//Pozicioniranje komponenti frejma
		jlPoeni.setBounds(10, 10, 100, 30);
		jlVrijeme.setBounds(10, 40, 100, 30);
		jlPitanje.setBounds(300, 100, 800, 100);
		jlPitanje.setFont(new Font("Serif", Font.PLAIN, 28));
		jtfOdgovor.setBounds(400, 240, 200, 40);
		odgovor.setBounds(400, 300, 200, 40);
		preskoci.setBounds(400, 480, 200, 40);
		
		//Dodavanje komponenti frejma
		add(jlPoeni);
		add(jlVrijeme);
		add(jlPitanje);
		add(jtfOdgovor);
		add(odgovor);
		add(preskoci);
		
		//Tajmer za odbrojavanje vremena za odgovor		
		Timer tajmer = new Timer();
		TimerTask odbrojavanje = new TimerTask() {
			@Override
			public void run() {
				gui.PrvaVrstaPitanja.sekunde--;
				jlVrijeme.setText("VRIJEME: " + String.valueOf(gui.PrvaVrstaPitanja.sekunde));
				if(gui.PrvaVrstaPitanja.sekunde == 0) {
					int brPitanja = brojPitanja;
					brPitanja++;
					odgovori.add(new Odgovor(pitanje, "Isteklo vrijeme!", tacanOdgovor));
					if(brojPitanja < 6) {
						new PrvaVrstaPitanja(brPitanja, poeni, elementi, odgovori);
					} else {
						new DrugaVrstaPitanja(brPitanja, poeni, elementi, odgovori);
					}
					dispose();
					this.cancel();
				}
			}
		};
		
		//pokretanje odbrojavanja
		tajmer.scheduleAtFixedRate(odbrojavanje, 1000, 1000);
		
		//ActionListener za dugme odgovor (ODGOVORI)
		odgovor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				odbrojavanje.cancel();
				//Podesavanje varijabli potrebnih za konstruktor novog pitanja
				int brPitanja = brojPitanja;
				brPitanja++;
				int p = poeni;
				takmicarevOdgovor = jtfOdgovor.getText();
				odgovori.add(new Odgovor(pitanje, takmicarevOdgovor, tacanOdgovor));
				//Uslovni operator koji odredjuje da li je odgovor tacan i dodjeljuje odgovarajuce bodove
				if(takmicarevOdgovor.equalsIgnoreCase(tacanOdgovor)) {
					p += 5;
				} else {
					p -= 2;
				}
				//Uslovni operator koji odredjuje koja vrsta pitanja treba slijedeca da se generise
				if(brPitanja < 6) {
					new PrvaVrstaPitanja(brPitanja, p, elementi, odgovori);
				} else {
					new DrugaVrstaPitanja(brPitanja, p, elementi, odgovori);
				}
				dispose();
			}
		});
		
		//ActionListener za dugme preskoci (PRESKOCI)
		preskoci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				odbrojavanje.cancel();
				int brPitanja = brojPitanja;
				brPitanja++;
				int p = poeni;
				takmicarevOdgovor = "Preskocio!";
				odgovori.add(new Odgovor(pitanje, takmicarevOdgovor, tacanOdgovor));
				//Uslovni operator koji odredjuje koja vrsta pitanja treba slijedeca da se generise
				if(brPitanja < 6) {
					new PrvaVrstaPitanja(brPitanja, p, elementi, odgovori);
				} else {
					new DrugaVrstaPitanja(brPitanja, p, elementi, odgovori);
				}				
				dispose();			
			}
		});
		
	}
	
	
	
}
