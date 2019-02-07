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

import element.HemijskiElement;
import element.Odgovor;

public class CetvrtaVrstaPitanja extends JFrame {

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
	
	public CetvrtaVrstaPitanja(int brojPitanja, int poeni, ArrayList<HemijskiElement> elementi, 
			ArrayList<Odgovor> odgovori)	throws HeadlessException {
		
		super("Pitanje br. " + brojPitanja);
		CetvrtaVrstaPitanja.brojPitanja = brojPitanja;
		this.poeni = poeni;
		this.elementi = elementi;
		this.odgovori = odgovori;
		CetvrtaVrstaPitanja.sekunde = 30;
		
		setVisible(true);
		setResizable(false);
		getContentPane().setLayout(null);
		setBounds(this.getWidth(), this.getHeight(), 1000, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Dio aplikacije odgovoran za kreiranje 4 nasumicna elementa
		ArrayList<Integer> cetiriRazlicitaBroja = dajCetiriRazlicitaBroja();
		ArrayList<String> dugmadi = new ArrayList<String>();
		int index;
		for (int i = 0; i < cetiriRazlicitaBroja.size(); i++) {
			index = cetiriRazlicitaBroja.get(i);
			dugmadi.add(elementi.get(index).getNaziv());
		}
		cetiriRazlicitaBroja.sort(null);
		tacanOdgovor = "";
		for (int j = 0; j < 4; j++) {
			index = cetiriRazlicitaBroja.get(j);
			tacanOdgovor += " " + elementi.get(index).getNaziv();
		}
		
		pitanje = "Poredaj slijedeca 4 elementa po rednom broju (u rastucem redoslijedu)";
		
		
		//Deklaracija komponenti frejma
		JLabel jlPoeni = new JLabel("POENI: " + poeni);
		JLabel jlVrijeme = new JLabel("VRIJEME: " + sekunde);
		JLabel jlPitanje = new JLabel(pitanje);
		JButton jbA = new JButton(dugmadi.get(0));
		JButton jbB = new JButton(dugmadi.get(1));
		JButton jbC = new JButton(dugmadi.get(2));
		JButton jbD = new JButton(dugmadi.get(3));
		JLabel jlTrenutniOdgovor = new JLabel("ODGOVOR:");
		JButton odgovor = new JButton("ODGOVORI");
		JButton obrisi = new JButton("OBRISI");
		JButton preskoci = new JButton("PRESKOCI");
		
		//Pozicioniranje komponenti frejma
		jlPoeni.setBounds(10, 10, 100, 30);
		jlVrijeme.setBounds(10, 40, 100, 30);
		jlPitanje.setBounds(190, 100, 800, 100);
		jlPitanje.setFont(new Font("Serif", Font.PLAIN, 22));
		jbA.setBounds(280, 240, 200, 40);
		jbB.setBounds(520, 240, 200, 40);
		jbC.setBounds(280, 300, 200, 40);
		jbD.setBounds(520, 300, 200, 40);
		jlTrenutniOdgovor.setBounds(10, 390, 800, 20);
		odgovor.setBounds(280, 420, 200, 40);
		odgovor.setEnabled(false);
		obrisi.setBounds(520, 420, 200, 40);
		preskoci.setBounds(400, 480, 200, 40);
		
		//Dodavanje komponenti frejma
		add(jlPoeni);
		add(jlVrijeme);
		add(jlPitanje);
		add(jbA);
		add(jbB);
		add(jbC);
		add(jbD);
		add(jlTrenutniOdgovor);
		add(odgovor);
		add(obrisi);
		add(preskoci);
		
		//Tajmer za odbrojavanje vremena za odgovor		
		Timer tajmer = new Timer();
		TimerTask odbrojavanje = new TimerTask() {
			@Override
			public void run() {
				gui.CetvrtaVrstaPitanja.sekunde--;
				jlVrijeme.setText("VRIJEME: " + String.valueOf(gui.CetvrtaVrstaPitanja.sekunde));
				if(gui.CetvrtaVrstaPitanja.sekunde == 0) {
					int brPitanja = brojPitanja;
					brPitanja++;
					odgovori.add(new Odgovor(pitanje, "Isteklo vrijeme!", tacanOdgovor));
					if(brojPitanja < 21) {
						new CetvrtaVrstaPitanja(brPitanja, poeni, elementi, odgovori);
					} else {
						//new Rezultat(poeni, elementi, odgovori);
					}
					dispose();
					this.cancel();
				}
			}
		};
		
		//pokretanje odbrojavanja
		tajmer.scheduleAtFixedRate(odbrojavanje, 1000, 1000);
		
		
		//ActionListener ua dugme jbA
		jbA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbA.setEnabled(false);
				jlTrenutniOdgovor.setText(jlTrenutniOdgovor.getText() + " " + jbA.getText());
				if(!jbA.isEnabled() && !jbB.isEnabled() && !jbC.isEnabled() && !jbD.isEnabled()) {
					odgovor.setEnabled(true);
				}
			}
		});
		
		//ActionListener ua dugme jbB
		jbB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbB.setEnabled(false);
				jlTrenutniOdgovor.setText(jlTrenutniOdgovor.getText() + " " + jbB.getText());
				if(!jbA.isEnabled() && !jbB.isEnabled() && !jbC.isEnabled() && !jbD.isEnabled()) {
					odgovor.setEnabled(true);
				}
			}
		});
		
		//ActionListener ua dugme jbC
		jbC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbC.setEnabled(false);
				jlTrenutniOdgovor.setText(jlTrenutniOdgovor.getText() + " " + jbC.getText());
				if(!jbA.isEnabled() && !jbB.isEnabled() && !jbC.isEnabled() && !jbD.isEnabled()) {
					odgovor.setEnabled(true);
				}
			}
		});
		
		//ActionListener ua dugme jbD
		jbD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbD.setEnabled(false);
				jlTrenutniOdgovor.setText(jlTrenutniOdgovor.getText() + " " + jbD.getText());
				if(!jbA.isEnabled() && !jbB.isEnabled() && !jbC.isEnabled() && !jbD.isEnabled()) {
					odgovor.setEnabled(true);
				}
			}
		});
		
		//ActionListener za dugme odgovor (ODGOVORI)
		odgovor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				odbrojavanje.cancel();
				int brPitanja = brojPitanja;
				brPitanja++;
				int p = poeni;
				String takmicarevOdgovor = jlTrenutniOdgovor.getText().substring(8);
				odgovori.add(new Odgovor(pitanje, takmicarevOdgovor, tacanOdgovor));
				//Dio koda koji odredjuje koliko korisnik dobija ili gubi bodova
				if(takmicarevOdgovor.equals(tacanOdgovor)) {
					p += 5;
				} else {
					p -= 2;
				}
				//Uslovni operator koji odredjuje koja vrsta pitanja treba slijedeca da se generise
				if(brPitanja < 21) {
					new CetvrtaVrstaPitanja(brPitanja, p, elementi, odgovori);
				} else {
					//new Rezultat(p, elementi, odgovori);
				}				
				dispose();
			}
		});
		
		//ActionListener za dugme obrisi (OBRISI)
		obrisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				odgovor.setEnabled(false);
				jlTrenutniOdgovor.setText("ODGOVOR:");
				jbA.setEnabled(true);
				jbB.setEnabled(true);
				jbC.setEnabled(true);
				jbD.setEnabled(true);
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
				if(brPitanja < 21) {
					new CetvrtaVrstaPitanja(brPitanja, p, elementi, odgovori);
				} else {
					//new Rezultat(brPitanja, p, elementi, odgovori);
				}				
				dispose();			
			}
		});
		
	}
	
	private ArrayList<Integer> dajCetiriRazlicitaBroja() {
		ArrayList<Integer> rezultat = new ArrayList<Integer>();
		int noviBroj;
		for (int i = 0; i < 4; i++) {
			do {
				noviBroj = (int) (Math.random() * 50);
			} while(rezultat.contains(noviBroj));
			rezultat.add(noviBroj);
		}
		return rezultat;
	}
}
