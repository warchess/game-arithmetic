package org.cx.game.arithmetic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.cx.game.arithmetic.tools.ArithmeticUtil;

public class HexagonArithmetic implements IArithmetic {

	public static final int Relative_LeftTop = 10;
    public static final int Relative_Left = 9;
    public static final int Relative_LeftBottom = 8;

    public static final int Relative_RightTop = 2;
    public static final int Relative_Right = 3;
    public static final int Relative_RightBottom = 4;
	
	@Override
	public Integer getDirection(Point stand, Point target) {
		// TODO Auto-generated method stub
		Integer ret = null;
		
		Integer [] p1 = stand.toArray();
		Integer [] p2 = target.toArray();
		
		if(p1[1]%2==0){
			if(p1[0]-p2[0]==1 && p1[1]-p2[1]==1)
				ret = Relative_LeftTop;
			
			if(p1[0]-p2[0]==0 && p1[1]-p2[1]==1)
				ret = Relative_RightTop;
				
			if(p1[0]-p2[0]==1 && p1[1]-p2[1]==0)
				ret = Relative_Left;
					
			if(p1[0]-p2[0]==-1 && p1[1]-p2[1]==0)
				ret = Relative_Right;
						
			if(p1[0]-p2[0]==1 && p1[1]-p2[1]==-1)
				ret = Relative_LeftBottom;
							
			if(p1[0]-p2[0]==0 && p1[1]-p2[1]==-1)
				ret = Relative_RightBottom;
		}else{
			if(p1[0]-p2[0]==0 && p1[1]-p2[1]==1)
				ret = Relative_LeftTop;
			
			if(p1[0]-p2[0]==-1 && p1[1]-p2[1]==1)
				ret = Relative_RightTop;
				
			if(p1[0]-p2[0]==1 && p1[1]-p2[1]==0)
				ret = Relative_Left;
					
			if(p1[0]-p2[0]==-1 && p1[1]-p2[1]==0)
				ret = Relative_Right;
						
			if(p1[0]-p2[0]==0 && p1[1]-p2[1]==-1)
				ret = Relative_LeftBottom;
							
			if(p1[0]-p2[0]==-1 && p1[1]-p2[1]==-1)
				ret = Relative_RightBottom;
		}
		
		return ret;
	}
	
	public Integer getDirectionOfRotate(Point stand, Point target, Integer direction) {
		Integer tdir = getDirection(target, stand);
		Integer rotate = ArithmeticUtil.getDirectionOfRotate(tdir, direction);
		if(ArithmeticUtil.Diagonal == rotate)
			return direction;
		else if(ArithmeticUtil.Clockwise == rotate) 
			return ArithmeticUtil.anticlockwise(direction);
		else
			return ArithmeticUtil.clockwise(direction);
	}

	@Override
	public Point getPosition(Point stand, Integer direction) {
		// TODO Auto-generated method stub
		Integer [] p1 = stand.toArray();
		Point ret = null;
		
		if(p1[1]%2==0){
			switch (direction) {
			case 10:
				ret = new Point(p1[0]-1, p1[1]-1);
				break;
			case 2:
				ret = new Point(p1[0], p1[1]-1);
				break;
			case 9:
				ret = new Point(p1[0]-1, p1[1]);
				break;
			case 3:
				ret = new Point(p1[0]+1, p1[1]);
				break;
			case 8:
				ret = new Point(p1[0]-1, p1[1]+1);
				break;
			case 4:
				ret = new Point(p1[0], p1[1]+1);
				break;

			default:
				break;
			}
		}else{
			switch (direction) {
			case 10:
				ret = new Point(p1[0], p1[1]-1);
				break;
			case 2:
				ret = new Point(p1[0]+1, p1[1]-1);
				break;
			case 9:
				ret = new Point(p1[0]-1, p1[1]);
				break;
			case 3:
				ret = new Point(p1[0]+1, p1[1]);
				break;
			case 8:
				ret = new Point(p1[0], p1[1]+1);
				break;
			case 4:
				ret = new Point(p1[0]+1, p1[1]+1);
				break;

			default:
				break;
			}
		}
		
		return ret ;
	}

	@Override
	public List<Point> twoFlanks(Point stand, Integer direction) {
		// TODO Auto-generated method stub
		List<Point> list = new ArrayList<Point>();
		
		Point position  = null;
		
		switch (direction) {
		case 10:
			position = getPosition(stand, Relative_Left);
			list.add(position);
			
			position = getPosition(stand, Relative_RightTop);
			list.add(position);
			
			break;
		case 2:
			position = getPosition(stand, Relative_LeftTop);
			list.add(position);
			
			position = getPosition(stand, Relative_Right);
			list.add(position);
			
			break;
		case 9:
			position = getPosition(stand, Relative_LeftTop);
			list.add(position);
			
			position = getPosition(stand, Relative_LeftBottom);
			list.add(position);
			
			break;
		case 3:
			position = getPosition(stand, Relative_RightTop);
			list.add(position);
			
			position = getPosition(stand, Relative_RightBottom);
			list.add(position);
			
			break;
		case 8:
			position = getPosition(stand, Relative_Left);
			list.add(position);
			
			position = getPosition(stand, Relative_RightBottom);
			list.add(position);
			
			break;
		case 4:
			position = getPosition(stand, Relative_Right);
			list.add(position);
			
			position = getPosition(stand, Relative_LeftBottom);
			list.add(position);
			
			break;

		default:
			break;
		}
		
		return list;
	}

	@Override
	public List<Point> getLine(Point stand, Integer direction, Integer step) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isLine(Point stand, Point target) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Point> rectangle(Point start, Point stop) {
		// TODO Auto-generated method stub
		List<Point> list = new ArrayList<Point>();
		for(int i=start.x;i<=stop.x;i++)
			for(int j=start.y;j<=stop.y;j++)
				list.add(new Point(i,j));
		return list;
	}

	@Override
	public LinkedList<Node> route(Point start, Point stop, int[][] MAP, int[] hit) {
		// TODO Auto-generated method stub
		PathFinding pathFinding = new PathFinding(MAP,hit);
		LinkedList<Node> path = pathFinding.searchPath(start.toPointOfAwt(), stop.toPointOfAwt());
		
		return path;
	}

}
