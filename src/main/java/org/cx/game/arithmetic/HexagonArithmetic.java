package org.cx.game.arithmetic;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.cx.game.tools.Utility;

public class HexagonArithmetic implements IArithmetic {

	public static final int Relative_LeftTop = 10;
    public static final int Relative_Left = 9;
    public static final int Relative_LeftBottom = 8;

    public static final int Relative_RightTop = 2;
    public static final int Relative_Right = 3;
    public static final int Relative_RightBottom = 4;
	
	@Override
	public Integer getDirection(Integer stand, Integer target) {
		// TODO Auto-generated method stub
		Integer ret = null;
		
		Integer [] p1 = Utility.integerToPoint(stand);
		Integer [] p2 = Utility.integerToPoint(target);
		
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

	@Override
	public Integer getPosition(Integer stand, Integer direction) {
		// TODO Auto-generated method stub
		Integer [] p1 = Utility.integerToPoint(stand);
		Integer ret = null;
		
		if(p1[1]%2==0){
			switch (direction) {
			case 10:
				ret = Utility.pointToInteger(p1[0]-1, p1[1]-1);
				break;
			case 2:
				ret = Utility.pointToInteger(p1[0], p1[1]-1);
				break;
			case 9:
				ret = Utility.pointToInteger(p1[0]-1, p1[1]);
				break;
			case 3:
				ret = Utility.pointToInteger(p1[0]+1, p1[1]);
				break;
			case 8:
				ret = Utility.pointToInteger(p1[0]-1, p1[1]+1);
				break;
			case 4:
				ret = Utility.pointToInteger(p1[0], p1[1]+1);
				break;

			default:
				break;
			}
		}else{
			switch (direction) {
			case 10:
				ret = Utility.pointToInteger(p1[0], p1[1]-1);
				break;
			case 2:
				ret = Utility.pointToInteger(p1[0]+1, p1[1]-1);
				break;
			case 9:
				ret = Utility.pointToInteger(p1[0]-1, p1[1]);
				break;
			case 3:
				ret = Utility.pointToInteger(p1[0]+1, p1[1]);
				break;
			case 8:
				ret = Utility.pointToInteger(p1[0], p1[1]+1);
				break;
			case 4:
				ret = Utility.pointToInteger(p1[0]+1, p1[1]+1);
				break;

			default:
				break;
			}
		}
		
		return ret ;
	}

	@Override
	public List<Integer> twoFlanks(Integer stand, Integer direction) {
		// TODO Auto-generated method stub
		List<Integer> list = new ArrayList<Integer>();
		
		Integer position  = null;
		
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
	public List<Integer> getLine(Integer stand, Integer direction, Integer step) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isLine(Integer stand, Integer target) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> rectangle(Integer start, Integer stop) {
		// TODO Auto-generated method stub
		String [] starts = start.toString().split(Utility.Space);
		Integer startx = Integer.valueOf(starts[0]);
		Integer starty = Integer.valueOf(starts[1]);
		
		String [] stops = stop.toString().split(Utility.Space);
		Integer stopx = Integer.valueOf(stops[0]);
		Integer stopy = Integer.valueOf(stops[1]);
		
		List<Integer> list = new ArrayList<Integer>();
		for(int i=startx;i<=stopx;i++)
			for(int j=starty;j<=stopy;j++)
				list.add(Integer.valueOf(i+Utility.Space+j));
		return list;
	}

	@Override
	public LinkedList<Node> route(Integer start, Integer stop, int[][] MAP, int[] hit) {
		// TODO Auto-generated method stub
		PathFinding pathFinding = new PathFinding(MAP,hit);
		
		Integer[] starts = Utility.integerToPoint(start);
		Integer[] stops = Utility.integerToPoint(stop);
		Point startPos = new Point(starts[0], starts[1]);
		Point stopPos = new Point(stops[0],stops[1]);
		LinkedList<Node> path = pathFinding.searchPath(startPos, stopPos);
		
		return path;
	}

}
