package org.cx.game.arithmetic;

import java.awt.Point;
import java.util.LinkedList;

/**
 * A*寻路算法，六边形版本
 * 被用于Ground.route
 * @author chenxian
 *
 */
public class PathFinding {

	private OpenedList openedList;
	private LinkedList<Node> closedList;
	private int[][] _map;
	private int[] _limit;

	public PathFinding(int[][] map, int[] limit) {
		_map = map;
		_limit = limit;
		openedList = new OpenedList();
		closedList = new LinkedList<Node>();
	}

	/* 原方法 
	public List searchPath(Point startPos, Point destiPos) {
		Node startNode = new Node(startPos);
		Node destiNode = new Node(destiPos);
		startNode.sourcePoint = 0;
		startNode.destiPoint = startNode.GetCost(destiNode);
		startNode._parentnode = null;
		openedList.add(startNode);
		while (!openedList.isEmpty()) {
			// remove the initialized component
			Node firstNode = (Node) openedList.removeFirst();
			// check the equality
			if (firstNode.equals(destiNode)) {
				//
				return makePath(firstNode);
			} else {
				//
				// add to the closedList
				closedList.add(firstNode);
				// get the mobile area of firstNode
				LinkedList _limit = firstNode.getLimit();
				// visit
				for (int i = 0; i < _limit.size(); i++) {
					// get the adjacent node
					Node neighborNode = (Node) _limit.get(i);
					//
					boolean isOpen = openedList.contains(neighborNode);
					// check if it can work
					boolean isClosed = closedList.contains(neighborNode);
					//
					boolean isHit = isHit(neighborNode._Pos.x,
							neighborNode._Pos.y);
					// all of them are negative
					if (!isOpen && !isClosed && !isHit) {
						// set the costFromStart
						neighborNode.sourcePoint = firstNode.sourcePoint + 1;
						// set the costToObject
						neighborNode.destiPoint = neighborNode
								.GetCost(destiNode);
						// change the neighborNode's parent nodes
						neighborNode._parentnode = firstNode;
						// add to level
						openedList.add(neighborNode);
					}
				}
			}
		}
		// clear the data
		openedList.clear();
		closedList.clear();
		//
		return null;
	}*/
	
	/**
	 * 计算路径
	 * @param startPos
	 * @param destiPos 目标位置，如果为不可到达则返回null
	 * @return
	 */
	public LinkedList<Node> searchPath(Point startPos, Point destiPos) {
		Node startNode = new Node(startPos);
		Node destiNode = new Node(destiPos);
		startNode.sourcePoint = 0;
		startNode.destiPoint = startNode.GetCost(destiNode);
		startNode._parentnode = null;
		openedList.add(startNode);
		while (!openedList.isEmpty()) {
			// remove the initialized component
			Node firstNode = (Node) openedList.removeFirst();
			// check the equality
			if (firstNode.equals(destiNode)) {
				//
				return makePath(firstNode);
			} else {
				//
				// add to the closedList
				closedList.add(firstNode);
				// get the mobile area of firstNode
				LinkedList<Node> _limit = firstNode.getLimit();
				// visit
				for (int i = 0; i < _limit.size(); i++) {
					// get the adjacent node
					Node neighborNode = _limit.get(i);
					//
					boolean isOpen = openedList.contains(neighborNode);
					// check if it can work
					boolean isClosed = closedList.contains(neighborNode);
					//
					boolean isHit = isHit(neighborNode._Pos.x,
							neighborNode._Pos.y);
					// all of them are negative
					if (!isOpen && !isClosed && !isHit) {
						// set the costFromStart
						neighborNode.sourcePoint = firstNode.sourcePoint + (firstNode.consume==0 ? 1 : firstNode.consume);   //
						// set the costToObject
						neighborNode.destiPoint = neighborNode
								.GetCost(destiNode);
						// set the consume
						neighborNode.consume = this._map[neighborNode._Pos.x][neighborNode._Pos.y];
						// change the neighborNode's parent nodes
						neighborNode._parentnode = firstNode;
						// add to level
						openedList.add(neighborNode);
					}
				}
			}
		}
		// clear the data
		openedList.clear();
		closedList.clear();
		//
		return null;
	}

	private LinkedList<Node> makePath(Node node) {
		LinkedList<Node> path = new LinkedList<Node>();
		while (node._parentnode != null) {
			path.addFirst(node);
			node = node._parentnode;
		}
		path.addFirst(node);
		return path;
	}

	private boolean isHit(int x, int y) {
		for (int i = 0; i < _limit.length; i++) {
			if (_map[x][y] == _limit[i]) {
				return true;
			}
		}
		return false;
	}

	private class OpenedList extends LinkedList<Node> {
		private static final long serialVersionUID = 1L;
		
		public boolean add(Node node) {
			for (int i = 0; i < size(); i++) {
				if (node.compareTo(get(i)) <= 0) {
					add(i, node);
				}
			}
			addLast(node);
			return true;
		}
	}
}
