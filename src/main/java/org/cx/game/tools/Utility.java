package org.cx.game.tools;

public class Utility {

	public static final String Space = "8008";
	
	/**
	 * 数字和点的互换
	 * @param x
	 * @param y
	 * @return 1,1 = 180081
	 */
	public static Integer pointToInteger(Integer x,Integer y){
		return Integer.valueOf(x+Space+y);
	}
	
	/**
	 * 数字和点的互换
	 * @param point 
	 * @return 180081 = 1,1
	 */
	public static Integer[] integerToPoint(Integer point){
		String [] points = point.toString().split(Space);
		Integer x = Integer.valueOf(points[0]);
		Integer y = Integer.valueOf(points[1]);

		return new Integer[]{x,y}; 
	}
}
