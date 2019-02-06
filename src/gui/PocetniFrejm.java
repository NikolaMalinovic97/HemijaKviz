package gui;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

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
		ArrayList<Odgovor> odgovori;
		
		setVisible(true);
		setResizable(false);
		getContentPane().setLayout(null);
		setBounds(this.getWidth(), this.getHeight(), 1000, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Deklaracija komponenti frejma
		JButton pocni = new JButton("POCNI");
		
		//Pozicioniranje komponenti frejma
		pocni.setBounds(400, 460, 200, 40);
		
		//Dodavanje komponenti frejma
		add(pocni);
		
		//ActionListener koji poziva frejm za prvo pitanje
		pocni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PrvaVrstaPitanja(brojPitanja, poeni, elementi, odgovori);
				dispose();
			}
		});
	}
	
	

}
