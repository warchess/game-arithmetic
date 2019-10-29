package org.cx.game.arithmetic.tools;

import org.cx.game.arithmetic.HexagonArithmetic;

public class ArithmeticUtil {

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
	
	/**
	 * 顺时针方向的下一个位置
	 * @param direction
	 * @return
	 */
	public static Integer clockwise(Integer direction) {
		Integer ret = null;
		
		switch (direction) {
		case HexagonArithmetic.Relative_RightTop:
			ret = HexagonArithmetic.Relative_Right;
			break;
			
		case HexagonArithmetic.Relative_Right:
			ret = HexagonArithmetic.Relative_RightBottom;
			break;

		case HexagonArithmetic.Relative_RightBottom:
			ret = HexagonArithmetic.Relative_LeftBottom;
			break;
			
		case HexagonArithmetic.Relative_LeftBottom:
			ret = HexagonArithmetic.Relative_Left;
			break;
			
		case HexagonArithmetic.Relative_Left:
			ret = HexagonArithmetic.Relative_LeftTop;
			break;
			
		case HexagonArithmetic.Relative_LeftTop:
			ret = HexagonArithmetic.Relative_RightTop;
			break;
			
		default:
			break;
		}
		
		return ret;		
	}
	
	/**
	 * 逆时针方向的下一个位置
	 * @param direction
	 * @return
	 */
	public static Integer anticlockwise(Integer direction) {
		Integer ret = null;
		
		switch (direction) {
		case HexagonArithmetic.Relative_RightTop:
			ret = HexagonArithmetic.Relative_LeftTop;
			break;
			
		case HexagonArithmetic.Relative_LeftTop:
			ret = HexagonArithmetic.Relative_Left;
			break;

		case HexagonArithmetic.Relative_Left:
			ret = HexagonArithmetic.Relative_LeftBottom;
			break;
			
		case HexagonArithmetic.Relative_LeftBottom:
			ret = HexagonArithmetic.Relative_RightBottom;
			break;
			
		case HexagonArithmetic.Relative_RightBottom:
			ret = HexagonArithmetic.Relative_Right;
			break;
			
		case HexagonArithmetic.Relative_Right:
			ret = HexagonArithmetic.Relative_RightTop;
			break;
			
		default:
			break;
		}
		
		return ret;
	}
	
	public final static Integer Clockwise = 1;
	public final static Integer Anticlockwise = -1;
	public final static Integer Diagonal = 0;
	
	/**
	 * 判断dir1在dir1的顺时针还是逆时针方向
	 * @param dir1 target相对于stand的朝向
	 * @param dir2 target的朝向
	 * @return
	 */
	public static Integer getDirectionOfRotate(Integer dir1, Integer dir2) {
		Integer ret = null;
		Integer dir = null;
		
		switch (dir2) {
		
		case HexagonArithmetic.Relative_RightTop:
			dir = HexagonArithmetic.Relative_LeftBottom;
			break;
			
		case HexagonArithmetic.Relative_LeftTop:
			dir = HexagonArithmetic.Relative_RightBottom;
			break;

		case HexagonArithmetic.Relative_Left:
			dir = HexagonArithmetic.Relative_Right;
			break;
			
		case HexagonArithmetic.Relative_LeftBottom:
			dir = HexagonArithmetic.Relative_RightTop;
			break;
			
		case HexagonArithmetic.Relative_RightBottom:
			dir = HexagonArithmetic.Relative_LeftTop;
			break;
			
		case HexagonArithmetic.Relative_Right:
			dir = HexagonArithmetic.Relative_Left;
			break;
			
		default:
			break;
		}
		
		if(dir1>dir2 && dir1<dir)
			ret = Clockwise;
		else if(dir1 == dir)
			ret = Diagonal;
		else
			ret = Anticlockwise;
		
		return ret;
	}
}
