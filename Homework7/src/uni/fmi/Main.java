package uni.fmi;

public class Main {

	public static void main(String[] args) {
		Supercat bosa = new Supercat("Bosa", 3000, 30, 2, 30, 15);
		Supercat gosho = new Supercat("Gosho", 1500, 35, 1.5, 40, 50);
		
		bosa.targetMyEnemy(gosho);
		gosho.targetMyEnemy(bosa);
		
		new Thread(bosa).start();
		new Thread(gosho).start();	

	}

}
