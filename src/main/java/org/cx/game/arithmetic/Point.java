package org.cx.game.arithmetic;

import java.util.ArrayList;
import java.util.List;

public class Point {

	public Integer x;
	public Integer y;
	
	public Point() {}
	
	public Point(Integer x, Integer y) {
		this.x = x;
		this.y = y;
	}
	
	public Point(String position) {
		String[] ps = position.split("_");
		x = Integer.valueOf(ps[0]);
		y = Integer.valueOf(ps[1]);
	}
	
	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}
	
	public java.awt.Point toPointOfAwt() {
		return new java.awt.Point(x, y);
	}

	public Integer[] toArray() {
		return new Integer[] {x, y};
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof Point) {
			Point point = (Point) obj;
			return point.x==x && point.y==y;
		}
		
		return super.equals(obj);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ""+x+"_"+y;
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return toString().hashCode();
	}
	
	public static Point parse(String position) {
		return new Point(Integer.valueOf(position.split("_")[0]), Integer.valueOf(position.split("_")[1]));
	}
	
	public static List<Point> parse(List<String> list) {
		List<Point> retList = new ArrayList<Point>();
		for(String position : list) retList.add(parse(position));
		return retList;
	}
	
	public static List<String> convertListOfString(List<Point> points) {
        List<String> list = new ArrayList<String>();
        for (Point point : points) list.add(point.toString());
        return list;
    }
}
