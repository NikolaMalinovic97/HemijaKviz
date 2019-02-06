package element;

public class Odgovor {

	private String pitanje;
	private String takmicarevOdgovor;
	private String tacanOdgovor;
	
	public Odgovor(String pitanje, String takmicarevOdgovor, String tacanOdgovor) {
		this.pitanje = pitanje;
		this.takmicarevOdgovor = takmicarevOdgovor;
		this.tacanOdgovor = tacanOdgovor;
	}

	public String getPitanje() {
		return pitanje;
	}

	public String getTakmicarevOdgovor() {
		return takmicarevOdgovor;
	}

	public String getTacanOdgovor() {
		return tacanOdgovor;
	}
	
}
