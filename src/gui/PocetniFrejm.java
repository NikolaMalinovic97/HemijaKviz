package gui;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import element.HemijskiElement;
import element.Odgovor;

public class PocetniFrejm extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	ArrayList<HemijskiElement> elementi;

	public PocetniFrejm(ArrayList<HemijskiElement> elementi) throws HeadlessException {
		
		super("Pocetak");
		this.elementi = elementi;
		int brojPitanja = 1;
		int poeni = 0;
		ArrayList<Odgovor> odgovori = new ArrayList<Odgovor>();
		
		setVisible(true);
		setResizable(false);
		getContentPane().setLayout(null);
		setBounds(this.getWidth(), this.getHeight(), 1000, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Deklaracija komponenti frejma
		JButton pocni = new JButton("POCNI");
		JLabel jl1 = new JLabel("1. Kviz se sastoji od 4 razlicite vrste pitanja.");
		JLabel jl2 = new JLabel("2. Postavlja se 5 pitanja za svaku vrstu pitanja (ukupno 20).");
		JLabel jl3 = new JLabel("3. Svaki pogresan odgovor rezultuje oduzimanjem 2 boda.");
		JLabel jl4 = new JLabel("4. Ako takmicara preskoci pitanja bodovi ostaju netaknuti.");
		JLabel jl5 = new JLabel("5. Takmicar ima 30 sekundi da odgovori na pitanje.");
		
		//Pozicioniranje komponenti frejma
		pocni.setBounds(400, 460, 200, 40);
		jl1.setBounds(10, 130, 800, 40);
		jl1.setFont(new Font("Serif", Font.PLAIN, 32));
		jl2.setBounds(10, 170, 800, 40);
		jl2.setFont(new Font("Serif", Font.PLAIN, 32));
		jl3.setBounds(10, 210, 800, 40);
		jl3.setFont(new Font("Serif", Font.PLAIN, 32));
		jl4.setBounds(10, 250, 800, 40);
		jl4.setFont(new Font("Serif", Font.PLAIN, 32));
		jl5.setBounds(10, 290, 800, 40);
		jl5.setFont(new Font("Serif", Font.PLAIN, 32));
		
		//Dodavanje komponenti frejma
		add(pocni);
		add(jl1);
		add(jl2);
		add(jl3);
		add(jl4);
		add(jl5);
		
		//ActionListener koji poziva frejm za prvo pitanje
		pocni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PrvaVrstaPitanja(brojPitanja, poeni, elementi, odgovori);
				dispose();
			}
		});
	}
	
	

}
