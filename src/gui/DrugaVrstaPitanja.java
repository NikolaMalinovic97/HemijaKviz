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

public class DrugaVrstaPitanja extends JFrame {
	
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
	
	//Lista u kojoj se generisu ponudjeni odgovori
	ArrayList<String> ponudjeniOdgovori = new ArrayList<String>();
	
	
	public DrugaVrstaPitanja(int brojPitanja, int poeni, ArrayList<HemijskiElement> elementi, 
			ArrayList<Odgovor> odgovori) throws HeadlessException {
		
		super("Pitanje br. " + brojPitanja);
		DrugaVrstaPitanja.brojPitanja = brojPitanja;
		this.poeni = poeni;
		this.elementi = elementi;
		this.odgovori = odgovori;
		DrugaVrstaPitanja.sekunde = 30;
		
		setVisible(true);
		setResizable(false);
		getContentPane().setLayout(null);
		setBounds(this.getWidth(), this.getHeight(), 1000, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Generisanje random broja od 0 do 49 (50 elemenata ukupno)
		int random = (int) (Math.random() * 50);
		
		//Dio aplikacije odgovoran za kreiranje nasumicnog pitanja
		ArrayList<Integer> cetiriRazlicitaBroja = null;
		int index;
		
		switch(brojPitanja) {
		
		case 6: pitanje = "Koja je oznaka elementa " + elementi.get(random).getNaziv() + "?";
				tacanOdgovor = elementi.get(random).getOznaka();
				cetiriRazlicitaBroja = dajCetiriRazlicitaBroja(random);
				for (int i = 0; i < 4; i++) {
					index = cetiriRazlicitaBroja.get(i);
					ponudjeniOdgovori.add(elementi.get(index).getOznaka());
				}
				break;
				
		case 7: pitanje = "Kako se zove element cije je oznaka " + elementi.get(random).getOznaka() + "?";
				tacanOdgovor = elementi.get(random).getNaziv();
				cetiriRazlicitaBroja = dajCetiriRazlicitaBroja(random);
				for (int i = 0; i < 4; i++) {
					index = cetiriRazlicitaBroja.get(i);
					ponudjeniOdgovori.add(elementi.get(index).getNaziv());
				}
				break;
				
		case 8: pitanje = "Kolika je atosmka tezina elementa " + elementi.get(random).getNaziv() + "?";
				tacanOdgovor = String.valueOf(elementi.get(random).getAtomskaTezina());
				cetiriRazlicitaBroja = dajCetiriRazlicitaBroja(random);
				for (int i = 0; i < 4; i++) {
					index = cetiriRazlicitaBroja.get(i);
					ponudjeniOdgovori.add("" + elementi.get(index).getAtomskaTezina());
				}
				break;
				
		case 9: pitanje = "Koji je redni broj elementa " + elementi.get(random).getNaziv() + "?";
				tacanOdgovor = String.valueOf(elementi.get(random).getRedniBroj());
				cetiriRazlicitaBroja = dajCetiriRazlicitaBroja(random);
				for (int i = 0; i < 4; i++) {
					index = cetiriRazlicitaBroja.get(i);
					ponudjeniOdgovori.add("" + elementi.get(index).getRedniBroj());
				}
				break;
				
		case 10: pitanje = "Koja je perioda elementa " + elementi.get(random).getNaziv() + "?";
				tacanOdgovor = String.valueOf(elementi.get(random).getPerioda());
				cetiriRazlicitaBroja = dajCetiriRazlicitaBroja(random);
				for (int i = 0; i < 4; i++) {
					index = cetiriRazlicitaBroja.get(i);
					ponudjeniOdgovori.add("" + elementi.get(index).getPerioda());
				}
				break;
		}
		
		//Deklaracija komponenti frejma
		JLabel jlPoeni = new JLabel("POENI: " + poeni);
		JLabel jlVrijeme = new JLabel("VRIJEME: " + sekunde);
		JLabel jlPitanje = new JLabel("PITANJE: " + pitanje);
		JButton jbOdgovorA = new JButton("a) " + ponudjeniOdgovori.get(0));
		JButton jbOdgovorB = new JButton("b) " + ponudjeniOdgovori.get(1));
		JButton jbOdgovorC = new JButton("c) " + ponudjeniOdgovori.get(2));
		JButton jbOdgovorD = new JButton("d) " + ponudjeniOdgovori.get(3));
		JButton preskoci = new JButton("PRESKOCI");
		
		//Pozicioniranje komponenti frejma
		jlPoeni.setBounds(10, 10, 100, 30);
		jlVrijeme.setBounds(10, 40, 100, 30);
		jlPitanje.setBounds(280, 100, 800, 100);
		jlPitanje.setFont(new Font("Serif", Font.PLAIN, 28));
		jbOdgovorA.setBounds(280, 240, 200, 40);
		jbOdgovorB.setBounds(520, 240, 200, 40);
		jbOdgovorC.setBounds(280, 300, 200, 40);
		jbOdgovorD.setBounds(520, 300, 200, 40);
		preskoci.setBounds(400, 480, 200, 40);
		
		//Dodavanje komponenti frejma
		add(jlPoeni);
		add(jlVrijeme);
		add(jlPitanje);
		add(jbOdgovorA);
		add(jbOdgovorB);
		add(jbOdgovorC);
		add(jbOdgovorD);
		add(preskoci);
		
		//Tajmer za odbrojavanje vremena za odgovor		
		Timer tajmer = new Timer();
		TimerTask odbrojavanje = new TimerTask() {
			@Override
			public void run() {
				gui.DrugaVrstaPitanja.sekunde--;
				jlVrijeme.setText("VRIJEME: " + String.valueOf(gui.DrugaVrstaPitanja.sekunde));
				if(gui.DrugaVrstaPitanja.sekunde == 0) {
					int brPitanja = brojPitanja;
					brPitanja++;
					odgovori.add(new Odgovor(pitanje, "Isteklo vrijeme!", tacanOdgovor));
					if(brojPitanja < 11) {
						new DrugaVrstaPitanja(brPitanja, poeni, elementi, odgovori);
					} else {
						new TrecaVrstaPitanja(brPitanja, poeni, elementi, odgovori);
					}
					dispose();
					this.cancel();
				}
			}
		};
		
		//pokretanje odbrojavanja
		tajmer.scheduleAtFixedRate(odbrojavanje, 1000, 1000);
		
		//ActionListener za dugme jbOdgovorA
		jbOdgovorA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				odbrojavanje.cancel();
				//Podesavanje varijabli potrebnih za konstruktor novog pitanja
				int brPitanja = brojPitanja;
				brPitanja++;
				int p = poeni;
				takmicarevOdgovor = ponudjeniOdgovori.get(0);
				odgovori.add(new Odgovor(pitanje, takmicarevOdgovor, tacanOdgovor));
				//Uslovni operator koji odredjuje da li je odgovor tacan i dodjeljuje odgovarajuce bodove
				if(takmicarevOdgovor.equalsIgnoreCase(tacanOdgovor)) {
					p += 5;
				} else {
					p -= 2;
				}
				//Uslovni operator koji odredjuje koja vrsta pitanja treba slijedeca da se generise
				if(brPitanja < 11) {
					new DrugaVrstaPitanja(brPitanja, p, elementi, odgovori);
				} else {
					new TrecaVrstaPitanja(brPitanja, p, elementi, odgovori);
				}
				dispose();
			}
		});
		
		//ActionListener za dugme jbOdgovorB
		jbOdgovorB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				odbrojavanje.cancel();
				//Podesavanje varijabli potrebnih za konstruktor novog pitanja
				int brPitanja = brojPitanja;
				brPitanja++;
				int p = poeni;
				takmicarevOdgovor = ponudjeniOdgovori.get(1);
				odgovori.add(new Odgovor(pitanje, takmicarevOdgovor, tacanOdgovor));
				//Uslovni operator koji odredjuje da li je odgovor tacan i dodjeljuje odgovarajuce bodove
				if(takmicarevOdgovor.equalsIgnoreCase(tacanOdgovor)) {
					p += 5;
				} else {
					p -= 2;
				}
				//Uslovni operator koji odredjuje koja vrsta pitanja treba slijedeca da se generise
				if(brPitanja < 11) {
					new DrugaVrstaPitanja(brPitanja, p, elementi, odgovori);
				} else {
					new TrecaVrstaPitanja(brPitanja, p, elementi, odgovori);
				}
				dispose();
			}
		});
		
		//ActionListener za dugme jbOdgovorC
		jbOdgovorC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				odbrojavanje.cancel();
				//Podesavanje varijabli potrebnih za konstruktor novog pitanja
				int brPitanja = brojPitanja;
				brPitanja++;
				int p = poeni;
				takmicarevOdgovor = ponudjeniOdgovori.get(2);
				odgovori.add(new Odgovor(pitanje, takmicarevOdgovor, tacanOdgovor));
				//Uslovni operator koji odredjuje da li je odgovor tacan i dodjeljuje odgovarajuce bodove
				if(takmicarevOdgovor.equalsIgnoreCase(tacanOdgovor)) {
					p += 5;
				} else {
					p -= 2;
				}
				//Uslovni operator koji odredjuje koja vrsta pitanja treba slijedeca da se generise
				if(brPitanja < 11) {
					new DrugaVrstaPitanja(brPitanja, p, elementi, odgovori);
				} else {
					new TrecaVrstaPitanja(brPitanja, p, elementi, odgovori);
				}
				dispose();
			}
		});
		
		//ActionListener za dugme jbOdgovorD
		jbOdgovorD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				odbrojavanje.cancel();
				//Podesavanje varijabli potrebnih za konstruktor novog pitanja
				int brPitanja = brojPitanja;
				brPitanja++;
				int p = poeni;
				takmicarevOdgovor = ponudjeniOdgovori.get(3);
				odgovori.add(new Odgovor(pitanje, takmicarevOdgovor, tacanOdgovor));
				//Uslovni operator koji odredjuje da li je odgovor tacan i dodjeljuje odgovarajuce bodove
				if(takmicarevOdgovor.equalsIgnoreCase(tacanOdgovor)) {
					p += 5;
				} else {
					p -= 2;
				}
				//Uslovni operator koji odredjuje koja vrsta pitanja treba slijedeca da se generise
				if(brPitanja < 11) {
					new DrugaVrstaPitanja(brPitanja, p, elementi, odgovori);
				} else {
					new TrecaVrstaPitanja(brPitanja, p, elementi, odgovori);
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
				if(brPitanja < 11) {
					new DrugaVrstaPitanja(brPitanja, p, elementi, odgovori);
				} else {
					new TrecaVrstaPitanja(brPitanja, p, elementi, odgovori);
				}				
				dispose();			
			}
		});
		
	}


	private ArrayList<Integer> dajCetiriRazlicitaBroja(int random) {
		ArrayList<Integer> rezultat = new ArrayList<Integer>();
		rezultat.add(random);
		int noviBroj;
		for (int i = 0; i < 3; i++) {
			do {
				noviBroj = (int) (Math.random() * 50);
			} while(rezultat.contains(noviBroj));
			rezultat.add(noviBroj);
		}
		Collections.shuffle(rezultat);
		return rezultat;
	}

}
