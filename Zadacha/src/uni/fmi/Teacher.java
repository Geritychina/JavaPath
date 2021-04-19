package uni.fmi;

public class Teacher extends Person {
	
	public Teacher(String eng, String name, int staj , String katedra) {
		super(eng, name);
		this.staj=staj;
		this.katedra=katedra;
	}
	private int staj;
	private String katedra;
	
	public int getStaj() {
		return staj;
	}
	public void setStaj(int staj) {
		this.staj = staj;
	}
	public String getKatedra() {
		return katedra;
	}
	public void setKatedra(String katedra) {
		this.katedra = katedra;
	}
	@Override
	public String toString() {
		return "Teacher [staj=" + staj + ", katedra=" + katedra + ", getStaj()=" + getStaj() + ", getKatedra()="
				+ getKatedra() + "]";
	}
	
	
	
	
	
	
	

}
