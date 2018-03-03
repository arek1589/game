package com.ygdrasill;

public class Box {

	private TypeName type;
	private int worth;

	public TypeName getType() {
		return type;
	}

	public int getWorth() {
		return worth;
	}

	public Box(int prize) {
		super();
		if (prize > 0) {
			type = TypeName.WIN;
			worth = prize;
		} else if (prize == -1) {
			type = TypeName.BOOST;
			worth = 0;
		} else if (prize == -100) {
			type = TypeName.LOSE;
			worth = 0;
		}
	}

}
