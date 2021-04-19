package uni.fmi;

public class Student extends Person {

	private String fn = "10101010";
	private int age;
	
	public Student(String eng, String name, String fn, int age) {
		super(eng, name);
		setFn(fn);
		this.age = age;	  //kogato pravq konstruktor dobavqm i nasledenite promenlivi
	}

	public String getFn() {
		return fn;
	}

	public void setFn(String fn) {
		this.fn = fn;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student [fn=" + fn + ", age=" + age + ", getFn()=" + getFn() + ", getAge()=" + getAge() + "]";
	}
	
	
	
	
	
	
	

}
