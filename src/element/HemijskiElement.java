package element;

public class HemijskiElement {

	private String naziv;
	private String oznaka;
	private int redniBroj;
	private int atomskaTezina;
	private int perioda;
	private String vrsta;
	
	public HemijskiElement(String naziv, String oznaka, int redniBroj, int atomskaTezina, int perioda, String vrsta) {
		this.naziv = naziv;
		this.oznaka = oznaka;
		this.redniBroj = redniBroj;
		this.atomskaTezina = atomskaTezina;
		this.perioda = perioda;
		this.vrsta = vrsta;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOznaka() {
		return oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	public int getRedniBroj() {
		return redniBroj;
	}

	public void setRedniBroj(int redniBroj) {
		this.redniBroj = redniBroj;
	}

	public int getAtomskaTezina() {
		return atomskaTezina;
	}

	public void setAtomskaTezina(int atomskaTezina) {
		this.atomskaTezina = atomskaTezina;
	}

	public int getPerioda() {
		return perioda;
	}

	public void setPerioda(int perioda) {
		this.perioda = perioda;
	}

	public String getVrsta() {
		return vrsta;
	}

	public void setVrsta(String vrsta) {
		this.vrsta = vrsta;
	}
	
}
