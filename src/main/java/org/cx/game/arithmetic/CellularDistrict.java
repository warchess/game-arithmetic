package org.cx.game.arithmetic;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.arithmetic.tools.ArithmeticUtil;

/** 
 * 蜂窝小区，以1为中心，顺时针编号，编号最大限定为100000。 求任意两编号之间的最短距离。 两个相邻小区的距离为1 
 *  
 *  
 */
public class CellularDistrict {

	private int maxSeq = 0;  
    private Point firstPoint;  
    private Point secondPoint;  
    
    //private static final Long OverPoint = 110010l;
    //private static final Integer base = 100;
    
    private static Integer xBorder = 100;
    private static Integer yBorder = 100;
    
    private static Map<Integer, org.cx.game.arithmetic.Point> coordinateMap = new HashMap<Integer, org.cx.game.arithmetic.Point>();
    private static Map<String, Integer> serialNumberMap = new HashMap<String, Integer>();
    
    static {
    	initCoordinateSystem(getCenterPoint(xBorder,yBorder),Math.max(xBorder, yBorder));
    }
    
    /** 
     * 初始化蜂窝小区信息 
     *  
     * @param iMaxSeqValue 
     *            蜂窝小区的最大值编号，注：编号从1开始 
     * @return 成功返回0，失败返回-1 
     */  
    private int initCellularDistrict(int iMaxSeqValue) {  
        if (iMaxSeqValue > 0 && iMaxSeqValue <= 100000) {  
            this.maxSeq = iMaxSeqValue;  
            return 0;  
        }
        return -1;  
    }
    
    /** 
     * 计算出蜂窝小区指定两点（编号值）之间的最短距离 
     *  
     * @param iFirstValue 
     *            起点编号值 
     * @param iSecondValue 
     *            终点编号值 
     * @return 计算成功返回最短距离，失败返回-1 
     */  
    public int getShortestPathLength(int iFirstValue, int iSecondValue) {  
        if (0 < iFirstValue && iFirstValue <= this.maxSeq && 0 < iSecondValue  
                && iSecondValue <= this.maxSeq) {  
            firstPoint = new Point(iFirstValue);  
            secondPoint = new Point(iSecondValue);  
            return firstPoint.minus(secondPoint);  
        }
        return -1;  
    }
    
    /**
     * 是否在直线上
     * @param origin
     * @param dest
     * @return
     */
    private Boolean _isLine(org.cx.game.arithmetic.Point origin, org.cx.game.arithmetic.Point dest) {
    	Integer sn1 = getSerialNumber(origin);
    	Integer sn2 = getSerialNumber(dest);
    	Point p1 = new Point(sn1);
    	Point p2 = new Point(sn2);
    	return p1.isLine(p2);
    }
    
    /** 
     * 清空相关信息 
     */  
    private void clear() {  
        maxSeq = 0;  
        firstPoint = null;  
        secondPoint = null;  
    }
    
    //--------------------- static ---------------------  这部分主要实现 坐标系 与 数字编号 的转换
    
    public static Integer getShortPathLength(org.cx.game.arithmetic.Point origin, org.cx.game.arithmetic.Point dest){
    	CellularDistrict cellularDistrict = new CellularDistrict();
    	cellularDistrict.initCellularDistrict(getMaxSerial(Math.max(xBorder, yBorder)));
		return cellularDistrict.getShortestPathLength(getSerialNumber(origin), getSerialNumber(dest));
	}
    
    public static Boolean isLine(org.cx.game.arithmetic.Point origin, org.cx.game.arithmetic.Point dest) {
    	CellularDistrict cellularDistrict = new CellularDistrict();
    	return cellularDistrict._isLine(origin, dest);
    }
    
    /**
     * 将坐标转换成数字编号
     * @param coordinate 坐标
     * @return
     */
    public static Integer getSerialNumber(org.cx.game.arithmetic.Point coordinate){
    	return serialNumberMap.get(coordinate.toString());
    }
    
    /**
     * 将数字编号转换成坐标
     * @param serialNumber 数字编号
     * @return
     */
    public static org.cx.game.arithmetic.Point getCoordinate(Integer serialNumber){
    	return coordinateMap.get(serialNumber);
    }
    
    private static void addCoordinateMap(Integer serialNumber, org.cx.game.arithmetic.Point point){
		coordinateMap.put(serialNumber, point);
    	serialNumberMap.put(point.toString(), serialNumber);
    }
    
    /**
     * 缓存坐标与数字编号的对应表
     * @param centerPoint 坐标系中心点
     * @param circleNumber 圈数
     */
    private static void initCoordinateSystem(org.cx.game.arithmetic.Point centerPoint, Integer circleNumber){
    	
    	Integer n = 0;
    	Integer [] point = centerPoint.toArray();
    	
    	org.cx.game.arithmetic.Point starPoint = centerPoint;
    	n += 1;
		addCoordinateMap(n, starPoint);
    	
    	starPoint = getPosition(starPoint, HexagonArithmetic.Relative_Left);
		n += 1;
		addCoordinateMap(n, starPoint);
    	
    	for (int i = 1; i < circleNumber+1; i++) {
			for (int j = 1; j < i; j++) {
				starPoint = getPosition(starPoint, HexagonArithmetic.Relative_LeftTop);
				n += 1;
				addCoordinateMap(n, starPoint);
			}
			
			for (int j = 0; j < i; j++) {
				starPoint = getPosition(starPoint, HexagonArithmetic.Relative_RightTop);
				n += 1;
				addCoordinateMap(n, starPoint);
			}
			
			for (int j = 0; j < i; j++) {
				starPoint = getPosition(starPoint, HexagonArithmetic.Relative_Right);
				n += 1;
				addCoordinateMap(n, starPoint);
			}
			
			for (int j = 0; j < i; j++) {
				starPoint = getPosition(starPoint, HexagonArithmetic.Relative_RightBottom);
				n += 1;
				addCoordinateMap(n, starPoint);
			}
			
			for (int j = 0; j < i; j++) {
				starPoint = getPosition(starPoint, HexagonArithmetic.Relative_LeftBottom);
				n += 1;
				addCoordinateMap(n, starPoint);
			}
			
			for (int j = 0; j < i+1; j++) {
				starPoint = getPosition(starPoint, HexagonArithmetic.Relative_Left);
				n += 1;
				addCoordinateMap(n, starPoint);
			}
		}
    }
    
    private static org.cx.game.arithmetic.Point getPosition(org.cx.game.arithmetic.Point stand, Integer direction) {
		// TODO Auto-generated method stub
		Integer [] p1 = stand.toArray();
		org.cx.game.arithmetic.Point ret = null;
		
		if(p1[1]%2==0){
			switch (direction) {
			case 10:
				ret = new org.cx.game.arithmetic.Point(p1[0]-1, p1[1]-1);
				break;
			case 2:
				ret = new org.cx.game.arithmetic.Point(p1[0], p1[1]-1);
				break;
			case 9:
				ret = new org.cx.game.arithmetic.Point(p1[0]-1, p1[1]);
				break;
			case 3:
				ret = new org.cx.game.arithmetic.Point(p1[0]+1, p1[1]);
				break;
			case 8:
				ret = new org.cx.game.arithmetic.Point(p1[0]-1, p1[1]+1);
				break;
			case 4:
				ret = new org.cx.game.arithmetic.Point(p1[0], p1[1]+1);
				break;

			default:
				break;
			}
		}else{
			switch (direction) {
			case 10:
				ret = new org.cx.game.arithmetic.Point(p1[0], p1[1]-1);
				break;
			case 2:
				ret = new org.cx.game.arithmetic.Point(p1[0]+1, p1[1]-1);
				break;
			case 9:
				ret = new org.cx.game.arithmetic.Point(p1[0]-1, p1[1]);
				break;
			case 3:
				ret = new org.cx.game.arithmetic.Point(p1[0]+1, p1[1]);
				break;
			case 8:
				ret = new org.cx.game.arithmetic.Point(p1[0], p1[1]+1);
				break;
			case 4:
				ret = new org.cx.game.arithmetic.Point(p1[0]+1, p1[1]+1);
				break;

			default:
				break;
			}
		}
		
		return ret ;
	}
    
    private static org.cx.game.arithmetic.Point getCenterPoint(Integer xBorder, Integer yBorder){
    	return new org.cx.game.arithmetic.Point(xBorder/2, yBorder/2);
    }
    
    private static Integer getMaxSerial(Integer circleNumber){
    	Integer number = 1;
    	for (int i = 1; i < circleNumber+1; i++) {
			number += i*6;
		}
    	return number;
    }
    
    //----------------------- static ----------------------  
  
    class Point {  
        private int x;  
        private int y;  
        private int z;  
  
        /** 
         * 构造方法 
         */  
        Point(int seqValue) {  
            // 点在哪一个圈  
            int i = 0;  
  
            // 点所在圈序号最大的点  
            int v = 1;  
  
            // 查找给定点是属于哪一个圈  
            for (; v < seqValue; v += 6 * (++i))  
                ;  
  
            // 获取点的x、y和z坐标  
            if (i > 0) {  
                // 点在圈的哪一条边  
                int side = (v - seqValue) / i;  
  
                // 点在边的位置  
                int step = (v - seqValue) % i;  
                switch (side) {  
                case 0:  
                    x = i;  
                    y = -i + step;  
                    z = x + y;  
                    break;  
                case 1:  
                    z = i;  
                    y = step;  
                    x = z - y;  
                    break;  
                case 2:  
                    y = i;  
                    z = i - step;  
                    x = z - y;  
                    break;  
                case 3:  
                    x = -i;  
                    y = i - step;  
                    z = x + y;  
                    break;  
                case 4:  
                    z = -i;  
                    y = -step;  
                    x = z - y;  
                    break;  
                case 5:  
                    y = -i;  
                    z = -i + step;  
                    x = z - y;  
                    break;  
                default:  
                    break;  
                }  
            }  
        }  
        
        Boolean isLine(Point p) {
        	if(x == p.x || y == p.y || z == p.z)
        		return true;
        	return false;
        }
  
        // 计算给定点和本点的距离  
        int minus(Point p) {  
            int i = x > p.x ? x - p.x : p.x - x;  
            int j = y > p.y ? y - p.y : p.y - y;  
            int k = z > p.z ? z - p.z : p.z - z;  
            return i > j ? (i > k ? i : k) : (j > k ? j : k);  
        }  
    }
    
    public static void main(String[] args) {
    	//CellularDistrict cellularDistrict = new CellularDistrict(21,12);
    	//cellularDistrict.initCellularDistrict(5000);
    	//System.out.println(cellularDistrict.getShortestPathLength(1, 18));
    	
    	
    	//CellularDistrict cellularDistrict = new CellularDistrict();
    	//cellularDistrict.initCellularDistrict(getMaxSerial(Math.max(xBorder, yBorder)));
    	
    	//initCoordinateSystem(getCentrePoint(xBorder, yBorder),Math.max(xBorder, yBorder));
    	
    	//initCoordinateSystem(getCentrePoint(xBorder, yBorder),21);
    	
    	//System.out.println(getCentrePoint(xBorder, yBorder));
    	
    	//for(Integer i : coordinateMap.keySet())
    	//	System.out.println(i.toString() +"-"+ coordinateMap.get(i));
    	//System.out.println(coordinateMap.get(20));
    	
    	//System.out.println(serialNumberMap.get(110011));
    	//System.out.println(serialNumberMap.get(15100112));
    	
    	//System.out.println(getCentrePoint(xBorder, yBorder));
    	//System.out.println(Math.max(xBorder, yBorder));
    	//System.out.println(getMaxSerial(8));
    	//System.out.println(serialNumberMap.size());
    	//System.out.println(coordinateMap.size());
    	
    	org.cx.game.arithmetic.Point p1 = new org.cx.game.arithmetic.Point(3,6);
    	org.cx.game.arithmetic.Point p2 = new org.cx.game.arithmetic.Point(4,7);
    	org.cx.game.arithmetic.Point p3 = new org.cx.game.arithmetic.Point(3,6);
    	System.out.println(CellularDistrict.getShortPathLength(p1, p2));
    	
    	
    	//System.out.println(getShortPathLength(380086, 480087));
    	//System.out.println(getShortPathLength(580086, 480087));
	}

}

