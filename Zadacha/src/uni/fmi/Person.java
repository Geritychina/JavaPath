package uni.fmi;

public class Person {

	private String egn; 
	private String name;
	
	public Person(String egn, String name) {
		 this.egn = egn;
		 this.name = name;
	}
	
	
	public String getEgn() {
		return egn;
	}
	public void setEgn(String egn) {
		this.egn = egn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
