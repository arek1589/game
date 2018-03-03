package com.ygdrasill;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

	List<Box> boxes = new ArrayList<Box>();

	public void prepare() {
		List<Integer> prizes = new ArrayList<Integer>();
		Random r = new Random();
		int tmp;

		prizes.add(100);
		prizes.add(20);
		prizes.add(20);
		prizes.add(5);
		prizes.add(5);
		prizes.add(5);
		prizes.add(5);
		prizes.add(5);
		prizes.add(-1);
		prizes.add(-100);
		prizes.add(-100);
		prizes.add(-100);

		while (!prizes.isEmpty()) {
			tmp = r.nextInt(prizes.size());
			boxes.add(new Box(prizes.get(tmp)));
			prizes.remove(tmp);
		}

	}

	public double play() {
		int life = 1;
		double money = 0;
		int tmp;
		int consolation = 1;
		Random r = new Random();
		GamePhase phase = GamePhase.CHOOSING;
		this.prepare();

		while (phase != GamePhase.GAMEOVER) {
			if (phase == GamePhase.CHOOSING) {
				while (life > 0) {
					tmp = r.nextInt(boxes.size());
					if (boxes.get(tmp).getType() == TypeName.WIN) {
						money = money + boxes.get(tmp).getWorth();
					//	System.out.println(boxes.get(tmp).getWorth());
					} else if (boxes.get(tmp).getType() == TypeName.BOOST) {
						life = life + 1;
				//		System.out.println("+life");
					} else if (boxes.get(tmp).getType() == TypeName.LOSE) {
						life = life - 1;
					//	System.out.println("-life");
					}

					boxes.remove(tmp);

					if (consolation == 1) {
						phase = GamePhase.CONS1;
					} else {
						phase = GamePhase.CONS2;
					}
				}
			} else if (phase == GamePhase.CONS1) {
				tmp = r.nextInt(4);
				if (tmp < 3) {
					money = money + Math.pow(2, tmp) * 5;
					phase = GamePhase.GAMEOVER;
				//	System.out.println(Math.pow(2, tmp) * 5);
				} else {
					consolation = 0;
					life = 1;
					phase = GamePhase.CHOOSING;
			//		System.out.println("wracam do gry");
				}

			} else if (phase == GamePhase.CONS2) {
				tmp = r.nextInt(3);
				money = money + Math.pow(2, tmp) * 5;
				phase = GamePhase.GAMEOVER;
	//			System.out.println(Math.pow(2, tmp) * 5);
			}

		}

		return money;
	}

	public static void main(String[] args) {
		Game g = new Game();
		double whole_money = 0;
		double average;
		double quantity = 10000000;

		for (int i = 0; i < quantity; i++) {
			whole_money = whole_money + g.play();
			System.out.println(whole_money); 
		}

		average = whole_money / quantity;
		System.out.println("average: "+ average);
		
	}

}