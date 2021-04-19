package uni.fmi;
import java.util.ArrayList;
public class MainClass {

	public static void main(String[] args) {
	
		Student s1 = new Student("12313213", "gosho", "3312313", 22);
		Student s2 = new Student("12313213", "ssdaf", "3312313", 33);
		
		Teacher t1 = new Teacher("31224354", "Wdsad", 3, "asd");
		Teacher t2 = new Teacher("45345436", "dfgfgd", 11, "SASsd");
		
		ArrayList<Person> people = new ArrayList<>(); // dobavqm ArrayList za krasota 
		people.add(s1);
		people.add(s2);
		people.add(t1);
		people.add(t2);
		
		int students = 0;  // mnojestveno chislo na studenti i uchiteli 
		int teachers = 0;
		
		
		for(Person p : people) {
			if(p instanceof Teacher) {
				if( ((Teacher)p).getStaj() > 30) {
					teachers++;
				}
			}else {
				if( ((Student)p).getAge() > 25) {
					students++;
				}
			}
		}
		
		for(int i = 0; i < people.size(); i++) {
			System.out.println(people.get(i));
		}
		
	}
	
	}


