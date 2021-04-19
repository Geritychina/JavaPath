package uni.fmi;

import java.util.Random;

public class Supercat implements Runnable {
	
	private double hp;
	private double dmg;
	private double attackSpeed;
	private int dodgeChance;
	private int critChance;
	private String name;
	
	
	private Supercat SuperBos;
	Random random=new Random();
	
	
	
	public Supercat(String name, double hp, double dmg, double attackSpeed,
			int dodgeChance, int critChance) {
		super();
		this.name = name;
		this.hp = hp;
		this.dmg = dmg;
		this.attackSpeed = attackSpeed;
		this.dodgeChance = dodgeChance;
		this.critChance = critChance;
	}
	
	
	
	public void targetMyEnemy(Supercat enemy) {
		SuperBos = enemy;
	}
	
	
	
	
	
	
	
	@Override
	public void run() {
		while(SuperBos.hp > 0 && this.hp > 0) {	
			
			if(random.nextInt(100) <= SuperBos.dodgeChance) {
				System.out.println(name + " hissed and stroke at "
						+ SuperBos.name + " but missed miserably");
			}else if(random.nextInt(100) <= critChance) {
				int critMultiplier = random.nextInt(5)+2;
				
				System.out.println(name + " furiasly clashed with "
						+ SuperBos.name + " and managed to do "
						+ dmg*critMultiplier);
				
				SuperBos.hp -= dmg*critMultiplier;
			}else {
				System.out.println(name + " stroke " + SuperBos.name
						+ " for " + dmg);
				SuperBos.hp -= dmg;				
			}			
			
			System.err.println(SuperBos.name +
					" has " + SuperBos.hp + " left");
			
			try {
				Thread.sleep((long) (1000 / attackSpeed));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(SuperBos.hp > 0) {
			System.out.println(name + 
					" hid away and started licking it`s wounds");
		}else {
			System.out.println(name + " screamed victorious!");
		}
	}

		
	
	
	
	
}
	
	

