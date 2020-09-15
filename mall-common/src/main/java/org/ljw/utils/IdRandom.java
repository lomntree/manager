package org.ljw.utils;

import java.util.Random;

public class IdRandom {
	
		public static long getId() {
			long timeMillis = System.currentTimeMillis();
			Random random = new Random();
			int nextInt = random.nextInt(99);
			String randonStr = "";
			if (nextInt < 10) {
				randonStr = "0" + nextInt;
			} else {
				randonStr = "" + nextInt;
			}
			randonStr += timeMillis;
			System.out.println(randonStr);
			return new Long(randonStr);
		}
	}

