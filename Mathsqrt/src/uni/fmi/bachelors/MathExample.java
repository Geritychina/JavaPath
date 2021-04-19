package uni.fmi.bachelors;
import java.util.Scanner;
public class MathExample {

	public static void main(String[] args) {
		Scanner kb=new Scanner(System.in);
		
		System.out.println("Molq vuvedete tri chisla");
		
		double a = kb.nextDouble();
		double b = kb.nextDouble();
		double c = kb.nextDouble();
		double d=b*b-4*a*c;
		
		if(d>0){
			
			double x1=(-b+Math.sqrt(d)/2*a);
			double x2=(-b-Math.sqrt(d)/2*a);
			System.out.println("X1= "+ x1+ "X2= "+ x2 );
		}else if(d<0){
			
			System.out.println("Nqma realni koreni");
			
		}else {
			double x=-b/(2*a);
			System.out.println("X= "+ x );
			
		}
		
		kb.close();

	}

}
