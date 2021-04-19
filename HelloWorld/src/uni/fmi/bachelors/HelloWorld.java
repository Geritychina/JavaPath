package uni.fmi.bachelors;
import java.util.Scanner;
public class HelloWorld {

	public static void main(String[] args) {
		
		Scanner nn=new Scanner(System.in);
		
		System.out.println("Molq vuvedi ime");
		String name=nn.nextLine();
		
		System.out.println("Hello "+name);

	}

}
