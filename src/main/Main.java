package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import element.HemijskiElement;
import gui.PocetniFrejm;

public class Main {

	public static void main(String[] args) {
		
		//Deklaracija varijabli
		ArrayList<HemijskiElement> elementi = new ArrayList<HemijskiElement>();
		File filePeriodniSistem = new File("PeriodniSistem.txt");
		BufferedReader brPeriodniSistem = null;
		
		try {
			//Inicijalizacija brPeriodniSistem-a
			brPeriodniSistem = new BufferedReader(new FileReader(filePeriodniSistem));
			String red = null;
			//Citanje hemijskih elemenata iz tabele periodnog sistema (PeriodniSistem.txt) i smjestanje u ArrayList-u "elementi"
			while((red = brPeriodniSistem.readLine()) != null) {
				String[] rijeci = red.split("\\s+");
				String naziv = rijeci[0];
				String oznaka = rijeci[1];
				int redniBroj = Integer.parseInt(rijeci[2]);
				int atomskaTezina = Integer.parseInt(rijeci[3]);
				int perioda = Integer.parseInt(rijeci[4]);
				String vrsta = rijeci[5];
				elementi.add(new HemijskiElement(naziv, oznaka, redniBroj, atomskaTezina, perioda, vrsta));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Instanciranje pocetnog frejma
		new PocetniFrejm(elementi);
	}

}
