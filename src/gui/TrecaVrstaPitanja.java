package gui;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import element.HemijskiElement;
import element.Odgovor;

public class TrecaVrstaPitanja extends JFrame {

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
	
	//Varijabla potrebne za spajanje takmicarevih odgovora
	boolean click = false;
	String prvaStavka;
	String drugaStavka;
	String spojenaStavka;
	
	public TrecaVrstaPitanja(int brojPitanja, int poeni, ArrayList<HemijskiElement> elementi,
			ArrayList<Odgovor> odgovori) throws HeadlessException {
		
		super("Pitanje br. " + brojPitanja);
		TrecaVrstaPitanja.brojPitanja = brojPitanja;
		this.poeni = poeni;
		this.elementi = elementi;
		this.odgovori = odgovori;
		TrecaVrstaPitanja.sekunde = 30;
		
		setVisible(true);
		setResizable(false);
		getContentPane().setLayout(null);
		setBounds(this.getWidth(), this.getHeight(), 1000, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Dio aplikacije odgovoran za kreiranje nasumicnog pitanja
		ArrayList<Integer> cetiriRazlicitaBroja = null;
		ArrayList<String> dugmadiA = new ArrayList<String>();
		ArrayList<String> dugmadiB = new ArrayList<String>();
		int index;
		
		switch(brojPitanja) {
		
		case 11: pitanje = "Povezi nazive elemenata i oznake elemenata.";
				 tacanOdgovor = "";
				 cetiriRazlicitaBroja = dajCetiriRazlicitaBroja();
				 for (int i = 0; i < 4; i++) {
					index = cetiriRazlicitaBroja.get(i);
					dugmadiA.add(elementi.get(index).getNaziv());
					tacanOdgovor += " " + elementi.get(index).getNaziv() + "-" + elementi.get(index).getOznaka();
				 }
				 Collections.shuffle(cetiriRazlicitaBroja);
				 for (int i = 0; i < 4; i++) {
					index = cetiriRazlicitaBroja.get(i);
					dugmadiB.add(elementi.get(index).getOznaka());
				 }
				 break;
				
		case 12: pitanje = "Povezi nazive elemenata i redne brojeve.";
				 tacanOdgovor = "";
				 cetiriRazlicitaBroja = dajCetiriRazlicitaBroja();
				 for (int i = 0; i < 4; i++) {
					index = cetiriRazlicitaBroja.get(i);
					dugmadiA.add(elementi.get(index).getNaziv());
					tacanOdgovor += " " + elementi.get(index).getNaziv() + "-" + elementi.get(index).getRedniBroj();
				 }
				 Collections.shuffle(cetiriRazlicitaBroja);
				 for (int i = 0; i < 4; i++) {
					index = cetiriRazlicitaBroja.get(i);
					dugmadiB.add("" + elementi.get(index).getRedniBroj());
				 }
				 break;
				
		case 13: pitanje = "Povezi oznake elemenata i redne brojeve.";
				 tacanOdgovor = "";
				 cetiriRazlicitaBroja = dajCetiriRazlicitaBroja();
				 for (int i = 0; i < 4; i++) {
					index = cetiriRazlicitaBroja.get(i);
					dugmadiA.add(elementi.get(index).getOznaka());
					tacanOdgovor += " " + elementi.get(index).getOznaka() + "-" + elementi.get(index).getRedniBroj();
				 }
				 Collections.shuffle(cetiriRazlicitaBroja);
				 for (int i = 0; i < 4; i++) {
					index = cetiriRazlicitaBroja.get(i);
					dugmadiB.add("" + elementi.get(index).getRedniBroj());
				 }
				 break;
				
		case 14: pitanje = "Povezi oznake elemenata i atomske tezine.";
				 tacanOdgovor = "";
				 cetiriRazlicitaBroja = dajCetiriRazlicitaBroja();
				 for (int i = 0; i < 4; i++) {
					index = cetiriRazlicitaBroja.get(i);
					dugmadiA.add(elementi.get(index).getOznaka());
					tacanOdgovor += " " + elementi.get(index).getOznaka() + "-" + elementi.get(index).getAtomskaTezina();
				 }
				 Collections.shuffle(cetiriRazlicitaBroja);
				 for (int i = 0; i < 4; i++) {
					index = cetiriRazlicitaBroja.get(i);
					dugmadiB.add("" + elementi.get(index).getAtomskaTezina());
					tacanOdgovor += " " + elementi.get(index).getNaziv() + "-" + elementi.get(index).getRedniBroj();
				 }
				 break;
				
		case 15: pitanje = "Povezi nazive elemenata i periode.";
				 tacanOdgovor = "";
				 cetiriRazlicitaBroja = dajCetiriRazlicitaBroja();
				 for (int i = 0; i < 4; i++) {
					index = cetiriRazlicitaBroja.get(i);
					dugmadiA.add(elementi.get(index).getNaziv());
					tacanOdgovor += " " + elementi.get(index).getNaziv() + "-" + elementi.get(index).getPerioda();
				 }
				 Collections.shuffle(cetiriRazlicitaBroja);
				 for (int i = 0; i < 4; i++) {
					index = cetiriRazlicitaBroja.get(i);
					dugmadiB.add("" + elementi.get(index).getPerioda());
				 }
				 break;
		}
		
		//Deklaracija komponenti frejma
		JLabel jlPoeni = new JLabel("POENI: " + poeni);
		JLabel jlVrijeme = new JLabel("VRIJEME: " + sekunde);
		JLabel jlPitanje = new JLabel(pitanje);
		JButton jbA1 = new JButton(dugmadiA.get(0));
		JButton jbA2 = new JButton(dugmadiA.get(1));
		JButton jbA3 = new JButton(dugmadiA.get(2));
		JButton jbA4 = new JButton(dugmadiA.get(3));
		JButton jbB1 = new JButton(dugmadiB.get(0));
		JButton jbB2 = new JButton(dugmadiB.get(1));
		JButton jbB3 = new JButton(dugmadiB.get(2));
		JButton jbB4 = new JButton(dugmadiB.get(3));
		JLabel jlTrenutniOdgovor = new JLabel("ODGOVOR:");
		JButton odgovor = new JButton("ODGOVORI");
		JButton obrisi = new JButton("OBRISI");
		JButton preskoci = new JButton("PRESKOCI");
		
		//Pozicioniranje komponenti frejma
		jlPoeni.setBounds(10, 10, 100, 30);
		jlVrijeme.setBounds(10, 40, 100, 30);
		jlPitanje.setBounds(280, 10, 800, 100);
		jlPitanje.setFont(new Font("Serif", Font.PLAIN, 28));
		jbA1.setBounds(150, 120, 200, 40);
		jbA2.setBounds(150, 190, 200, 40);
		jbA3.setBounds(150, 260, 200, 40);
		jbA4.setBounds(150, 330, 200, 40);
		jbB1.setBounds(650, 120, 200, 40);
		jbB2.setBounds(650, 190, 200, 40);
		jbB3.setBounds(650, 260, 200, 40);
		jbB4.setBounds(650, 330, 200, 40);
		jlTrenutniOdgovor.setBounds(10, 390, 800, 20);
		odgovor.setBounds(280, 420, 200, 40);
		odgovor.setEnabled(false);
		obrisi.setBounds(520, 420, 200, 40);
		preskoci.setBounds(400, 480, 200, 40);
		
		//Dodavanje komponenti frejma
		add(jlPoeni);
		add(jlVrijeme);
		add(jlPitanje);
		add(jbA1);
		add(jbA2);
		add(jbA3);
		add(jbA4);
		add(jbB1);
		add(jbB2);
		add(jbB3);
		add(jbB4);
		add(jlTrenutniOdgovor);
		add(odgovor);
		add(obrisi);
		add(preskoci);
		
		
		//Tajmer za odbrojavanje vremena za odgovor		
		Timer tajmer = new Timer();
		TimerTask odbrojavanje = new TimerTask() {
			@Override
			public void run() {
				gui.TrecaVrstaPitanja.sekunde--;
				jlVrijeme.setText("VRIJEME: " + String.valueOf(gui.TrecaVrstaPitanja.sekunde));
				if(gui.TrecaVrstaPitanja.sekunde == 0) {
					int brPitanja = brojPitanja;
					brPitanja++;
					odgovori.add(new Odgovor(pitanje, "Isteklo vrijeme!", tacanOdgovor));
					if(brojPitanja < 16) {
						new TrecaVrstaPitanja(brPitanja, poeni, elementi, odgovori);
					} else {
						new CetvrtaVrstaPitanja(brPitanja, poeni, elementi, odgovori);
					}
					dispose();
					this.cancel();
				}
			}
		};
		
		//pokretanje odbrojavanja
		tajmer.scheduleAtFixedRate(odbrojavanje, 1000, 1000);
		
		
		
		//ActionListener za dugme jbA1
		jbA1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbA1.setEnabled(false);
				click = !click;
				if(click) {
					prvaStavka = jbA1.getText();
				} else {
					prvaStavka = jbA1.getText();
					spojenaStavka = prvaStavka + "-" + drugaStavka;
					prvaStavka = null;
					drugaStavka = null;
					if(spojenaStavka.contains("null")) {
						spojenaStavka = "greska!";
					}
					jlTrenutniOdgovor.setText(jlTrenutniOdgovor.getText() + " " + spojenaStavka);
					//Dio koda koji porvjera da li su ipunjeni uslovi da se dugme ODGOVORI enable-uje
					if(!jbA1.isEnabled() && !jbA2.isEnabled() && !jbA3.isEnabled() && !jbA4.isEnabled()
						&& !jbB1.isEnabled() && !jbB2.isEnabled() && !jbB3.isEnabled() && !jbB4.isEnabled()
						&& !jlTrenutniOdgovor.getText().contains("greska!")) {
							odgovor.setEnabled(true);
					}
				}
			}
		});
		
		//ActionListener za dugme jbA2
		jbA2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbA2.setEnabled(false);
				click = !click;
				if(click) {
					prvaStavka = jbA2.getText();
				} else {
					prvaStavka = jbA2.getText();
					spojenaStavka = prvaStavka + "-" + drugaStavka;
					prvaStavka = null;
					drugaStavka = null;
					if(spojenaStavka.contains("null")) {
						spojenaStavka = "greska!";
					}
					jlTrenutniOdgovor.setText(jlTrenutniOdgovor.getText() + " " + spojenaStavka);
					//Dio koda koji porvjera da li su ipunjeni uslovi da se dugme ODGOVORI enable-uje
					if(!jbA1.isEnabled() && !jbA2.isEnabled() && !jbA3.isEnabled() && !jbA4.isEnabled()
						&& !jbB1.isEnabled() && !jbB2.isEnabled() && !jbB3.isEnabled() && !jbB4.isEnabled()
						&& !jlTrenutniOdgovor.getText().contains("greska!")) {
							odgovor.setEnabled(true);
					}
				}
			}
		});
		
		//ActionListener za dugme jbA3
		jbA3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbA3.setEnabled(false);
				click = !click;
				if(click) {
					prvaStavka = jbA3.getText();
				} else {
					prvaStavka = jbA3.getText();
					spojenaStavka = prvaStavka + "-" + drugaStavka;
					prvaStavka = null;
					drugaStavka = null;
					if(spojenaStavka.contains("null")) {
						spojenaStavka = "greska!";
					}
					jlTrenutniOdgovor.setText(jlTrenutniOdgovor.getText() + " " + spojenaStavka);
					//Dio koda koji porvjera da li su ipunjeni uslovi da se dugme ODGOVORI enable-uje
					if(!jbA1.isEnabled() && !jbA2.isEnabled() && !jbA3.isEnabled() && !jbA4.isEnabled()
						&& !jbB1.isEnabled() && !jbB2.isEnabled() && !jbB3.isEnabled() && !jbB4.isEnabled()
						&& !jlTrenutniOdgovor.getText().contains("greska!")) {
							odgovor.setEnabled(true);
					}
				}
			}
		});
		
		//ActionListener za dugme jbA4
		jbA4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbA4.setEnabled(false);
				click = !click;
				if(click) {
					prvaStavka = jbA4.getText();
				} else {
					prvaStavka = jbA4.getText();
					spojenaStavka = prvaStavka + "-" + drugaStavka;
					prvaStavka = null;
					drugaStavka = null;
					if(spojenaStavka.contains("null")) {
						spojenaStavka = "greska!";
					}
					jlTrenutniOdgovor.setText(jlTrenutniOdgovor.getText() + " " + spojenaStavka);
					//Dio koda koji porvjera da li su ipunjeni uslovi da se dugme ODGOVORI enable-uje
					if(!jbA1.isEnabled() && !jbA2.isEnabled() && !jbA3.isEnabled() && !jbA4.isEnabled()
						&& !jbB1.isEnabled() && !jbB2.isEnabled() && !jbB3.isEnabled() && !jbB4.isEnabled()
						&& !jlTrenutniOdgovor.getText().contains("greska!")) {
							odgovor.setEnabled(true);
					}
				}
			}
		});
		
		//ActionListener za dugme jbB1
		jbB1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbB1.setEnabled(false);
				click = !click;
				if(click) {
					drugaStavka = jbB1.getText();
				} else {
					drugaStavka = jbB1.getText();
					spojenaStavka = prvaStavka + "-" + drugaStavka;
					prvaStavka = null;
					drugaStavka = null;
					if(spojenaStavka.contains("null")) {
						spojenaStavka = "greska!";
					}
					jlTrenutniOdgovor.setText(jlTrenutniOdgovor.getText() + " " + spojenaStavka);
					//Dio koda koji porvjera da li su ipunjeni uslovi da se dugme ODGOVORI enable-uje
					if(!jbA1.isEnabled() && !jbA2.isEnabled() && !jbA3.isEnabled() && !jbA4.isEnabled()
						&& !jbB1.isEnabled() && !jbB2.isEnabled() && !jbB3.isEnabled() && !jbB4.isEnabled()
						&& !jlTrenutniOdgovor.getText().contains("greska!")) {
							odgovor.setEnabled(true);
					}
				}
			}
		});
		
		//ActionListener za dugme jbB2
		jbB2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbB2.setEnabled(false);
				click = !click;
				if(click) {
					drugaStavka = jbB2.getText();
				} else {
					drugaStavka = jbB2.getText();
					spojenaStavka = prvaStavka + "-" + drugaStavka;
					prvaStavka = null;
					drugaStavka = null;
					if(spojenaStavka.contains("null")) {
						spojenaStavka = "greska!";
					}
					jlTrenutniOdgovor.setText(jlTrenutniOdgovor.getText() + " " + spojenaStavka);
					//Dio koda koji porvjera da li su ipunjeni uslovi da se dugme ODGOVORI enable-uje
					if(!jbA1.isEnabled() && !jbA2.isEnabled() && !jbA3.isEnabled() && !jbA4.isEnabled()
						&& !jbB1.isEnabled() && !jbB2.isEnabled() && !jbB3.isEnabled() && !jbB4.isEnabled()
						&& !jlTrenutniOdgovor.getText().contains("greska!")) {
							odgovor.setEnabled(true);
					}
				}
			}
		});
		
		//ActionListener za dugme jbB3
		jbB3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbB3.setEnabled(false);
				click = !click;
				if(click) {
					drugaStavka = jbB3.getText();
				} else {
					drugaStavka = jbB3.getText();
					spojenaStavka = prvaStavka + "-" + drugaStavka;
					prvaStavka = null;
					drugaStavka = null;
					if(spojenaStavka.contains("null")) {
						spojenaStavka = "greska!";
					}
					jlTrenutniOdgovor.setText(jlTrenutniOdgovor.getText() + " " + spojenaStavka);
					//Dio koda koji porvjera da li su ipunjeni uslovi da se dugme ODGOVORI enable-uje
					if(!jbA1.isEnabled() && !jbA2.isEnabled() && !jbA3.isEnabled() && !jbA4.isEnabled()
						&& !jbB1.isEnabled() && !jbB2.isEnabled() && !jbB3.isEnabled() && !jbB4.isEnabled()
						&& !jlTrenutniOdgovor.getText().contains("greska!")) {
							odgovor.setEnabled(true);
					}
				}
			}
		});
		
		//ActionListener za dugme jbB4
		jbB4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbB4.setEnabled(false);
				click = !click;
				if(click) {
					drugaStavka = jbB4.getText();
				} else {
					drugaStavka = jbB4.getText();
					spojenaStavka = prvaStavka + "-" + drugaStavka;
					prvaStavka = null;
					drugaStavka = null;
					if(spojenaStavka.contains("null")) {
						spojenaStavka = "greska!";
					}
					jlTrenutniOdgovor.setText(jlTrenutniOdgovor.getText() + " " + spojenaStavka);
					//Dio koda koji porvjera da li su ipunjeni uslovi da se dugme ODGOVORI enable-uje
					if(!jbA1.isEnabled() && !jbA2.isEnabled() && !jbA3.isEnabled() && !jbA4.isEnabled()
						&& !jbB1.isEnabled() && !jbB2.isEnabled() && !jbB3.isEnabled() && !jbB4.isEnabled()
						&& !jlTrenutniOdgovor.getText().contains("greska!")) {
							odgovor.setEnabled(true);
					}
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
				String takmicarevOdgovor = jlTrenutniOdgovor.getText();
				odgovori.add(new Odgovor(pitanje, takmicarevOdgovor, tacanOdgovor));
				//Dio koda koji odredjuje koliko korisnik dobija ili gubi bodova
				String[] stavke = takmicarevOdgovor.split("\\s+");
				for (int i = 1; i < stavke.length; i++) {
					if(tacanOdgovor.contains(stavke[i])) {
						p += 2;
					} else {
						p -= 1;
					}
				}
				//Uslovni operator koji odredjuje koja vrsta pitanja treba slijedeca da se generise
				if(brPitanja < 16) {
					new TrecaVrstaPitanja(brPitanja, p, elementi, odgovori);
				} else {
					new CetvrtaVrstaPitanja(brPitanja, p, elementi, odgovori);
				}				
				dispose();
			}
		});
		
		//ActionListener za dugme obrisi (OBRISI)
		obrisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				odgovor.setEnabled(false);
				jlTrenutniOdgovor.setText("ODGOVOR:");
				jbA1.setEnabled(true);
				jbA2.setEnabled(true);
				jbA3.setEnabled(true);
				jbA4.setEnabled(true);
				jbB1.setEnabled(true);
				jbB2.setEnabled(true);
				jbB3.setEnabled(true);
				jbB4.setEnabled(true);
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
				if(brPitanja < 16) {
					new TrecaVrstaPitanja(brPitanja, p, elementi, odgovori);
				} else {
					new CetvrtaVrstaPitanja(brPitanja, p, elementi, odgovori);
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
