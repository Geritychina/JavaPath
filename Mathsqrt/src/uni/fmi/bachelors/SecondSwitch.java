package uni.fmi.bachelors;
import java.util.Scanner;
public class SecondSwitch {

	public static void main(String[] args) {
		
		Scanner kl=new Scanner(System.in);
		
		switch("mounts"){
		
		case "1": case "3": case "5": case "7": case "10": case "12": {
			System.out.println("Tozi mesec ima 31 dni");
			break;
		}
		
		case "4":
		case "6":
		case "8":
		case "11":
			System.out.println("Tozi mesec ima 30 dni");
		break;
		
		
		case "2":
			int year=kl.nextInt();
			int mounth=kl.nextInt();
			
			if(year%4==0&&year%100!=0||year%400==0) {
		    System.out.println("29");

	}else {
		System.out.println("28");
	}

			kl.close();
			
   }

  }
}
