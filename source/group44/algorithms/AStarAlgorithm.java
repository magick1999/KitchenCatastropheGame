package group44.algorithms;

import java.util.ArrayList;
import java.util.Stack;

import group44.entities.Ground;

public class AStarAlgorithm {
	private group44.entities.Cell[][] cells;
	private group44.entities.Cell start;
	private group44.entities.Cell destination;
	private Node[][] nodesDetails;

	private boolean closedArray[][];
	private ArrayList<Triple> openList = new ArrayList<>();

	/**
	 * Checks whether the cell is stepable or not.
	 * 
	 * @param x - the X coordinate of the cell
	 * @param y - the Y coordinate of the cell
	 * @return true if stepable; otherwise false.
	 */
	private boolean isUnblocked(int x, int y) {
		return this.cells[x][y] instanceof Ground;
	}

	private boolean isDestination(int x, int y) {
		return x == this.destination.getPositionX() && y == this.destination.getPositionY();
	}

	private double calculateHeuristics(int x, int y) {
		return Math.sqrt(Math.pow(this.destination.getPositionX(), 2) + Math.pow(this.destination.getPositionY(), 2));
	}

	// DELETE WHEN DONE
	private void tracePath(Node[][] nodes, Coordinates destination) {
		System.out.println("\nThe path is");
		int x = destination.getX();
		int y = destination.getY();

		Stack<Coordinates> trace = new Stack<Coordinates>();

		while (nodes[x][y].getParent().getCoordinates().getX() != x
				|| nodes[x][y].getParent().getCoordinates().getY() == y) {
			trace.push(new Coordinates(x, y));
			int tmpX = nodes[x][y].getParent().getCoordinates().getX();
			y = nodes[x][y].getParent().getCoordinates().getY();
			x = tmpX;
		}

		trace.push(new Coordinates(x, y));
		while (trace.isEmpty() == false) {
			Coordinates coordinates = trace.pop();
			System.out.printf("-- (%d,%d)", coordinates.getX(), coordinates.getY());
		}
	}

	public boolean search(group44.entities.Cell[][] cells, group44.entities.Cell start,
			group44.entities.Cell destination) {
		this.cells = cells;
		this.start = start;
		this.destination = destination;

		int row = this.cells[0].length;
		int col = this.cells.length;

		this.closedArray = new boolean[row][col];

		if (this.isDestination(this.start.getPositionX(), this.start.getPositionY())) {
			// set velocity to 0,0
		}
		if (this.isUnblocked(this.destination.getPositionX(), this.destination.getPositionY())) {
			// Player stays on upsteppable cell
		}

		for (int x = 0; x < row; x++) {
			for (int y = 0; y < cells.length; y++) {
				nodesDetails[x][y].setF(Double.MAX_VALUE);
				nodesDetails[x][y].setG(Double.MAX_VALUE);
				nodesDetails[x][y].setH(Double.MAX_VALUE);
				nodesDetails[x][y].setParent(null);
			}
		}

		double x = this.start.getPositionX();
		double y = this.start.getPositionY();
		nodesDetails[(int) x][(int) y].setF(0);
		nodesDetails[(int) x][(int) y].setG(0);
		nodesDetails[(int) x][(int) y].setH(0);
		nodesDetails[(int) x][(int) y].setParent(this.nodesDetails[(int) x][(int) y]);

		this.openList.add(new Triple(0, x, y));

		while (this.openList.isEmpty() == false) {
			Triple t = this.openList.remove(0);
			x = t.getY();
			y = t.getZ();

			closedArray[(int) x][(int) y] = true;

			if (x == this.destination.getPositionX() && y == this.destination.getPositionY()) {
				return true;
			}

			if (this.isValid((int) x, ((int) y) - 1)) {
				this.northNode((int) x, (int) y);
			}
			if (this.isValid((int) x, ((int) y) + 1)) {
				this.southNode((int) x, (int) y);
			}
			if (this.isValid((int) x - 1, (int) y)) {
				this.westNode((int) x, (int) y);
			}
			if (this.isValid((int) x + 1, (int) y)) {
				this.eastNode((int) x, (int) y);
			}
		}

		return false;
	}

	private boolean isValid(int x, int y) {
		return 0 <= x && x < this.cells.length && 0 <= y && y < this.cells[0].length;
	}

	private void eastNode(int x, int y) {
		int nextX = x + 1;
		int nextY = y;

		if (this.closedArray[nextX][nextY] == false && this.isUnblocked(nextX, nextY)
				&& this.nodesDetails[nextX][nextY].getF() == Double.MAX_VALUE) {
			double gNew = this.nodesDetails[x][y].getG() + 1;
			double hNew = this.calculateHeuristics(nextX, nextY);
			double fNew = gNew + hNew;

			if (nodesDetails[nextX][nextY].getF() == Double.MAX_VALUE || nodesDetails[nextX][nextY].getF() > fNew) {
				this.openList.add(new Triple(fNew, nextX, nextY));

				nodesDetails[nextX][nextY].setF(fNew);
				nodesDetails[nextX][nextY].setG(gNew);
				nodesDetails[nextX][nextY].setH(hNew);
				nodesDetails[nextX][nextY].setParent(nodesDetails[x][y]);
			}
		}
	}

	private void westNode(int x, int y) {
		int nextX = x - 1;
		int nextY = y;

		if (this.closedArray[nextX][nextY] == false && this.isUnblocked(nextX, nextY)
				&& this.nodesDetails[nextX][nextY].getF() == Double.MAX_VALUE) {
			// compute G, H, F
			double gNew = this.nodesDetails[x][y].getG() + 1;
			double hNew = this.calculateHeuristics(nextX, nextY);
			double fNew = gNew + hNew;

			if (nodesDetails[nextX][nextY].getF() == Double.MAX_VALUE || nodesDetails[nextX][nextY].getF() > fNew) {
				this.openList.add(new Triple(fNew, nextX, nextY));

				nodesDetails[nextX][nextY].setF(fNew);
				nodesDetails[nextX][nextY].setG(gNew);
				nodesDetails[nextX][nextY].setH(hNew);
				nodesDetails[nextX][nextY].setParent(nodesDetails[x][y]);
			}
		}
	}

	private void southNode(int x, int y) {
		int nextX = x;
		int nextY = y + 1;

		if (this.closedArray[nextX][nextY] == false && this.isUnblocked(nextX, nextY)
				&& this.nodesDetails[nextX][nextY].getF() == Double.MAX_VALUE) {
			// compute G, H, F
			double gNew = this.nodesDetails[x][y].getG() + 1;
			double hNew = this.calculateHeuristics(nextX, nextY);
			double fNew = gNew + hNew;

			if (nodesDetails[nextX][nextY].getF() == Double.MAX_VALUE || nodesDetails[nextX][nextY].getF() > fNew) {
				this.openList.add(new Triple(fNew, nextX, nextY));

				nodesDetails[nextX][nextY].setF(fNew);
				nodesDetails[nextX][nextY].setG(gNew);
				nodesDetails[nextX][nextY].setH(hNew);
				nodesDetails[nextX][nextY].setParent(nodesDetails[x][y]);
			}
		}
	}

	private void northNode(int x, int y) {
		int nextX = x;
		int nextY = y - 1;

		if (this.closedArray[nextX][nextY] == false && this.isUnblocked(nextX, nextY)
				&& this.nodesDetails[nextX][nextY].getF() == Double.MAX_VALUE) {
			// compute G, H, F
			double gNew = this.nodesDetails[x][y].getG() + 1;
			double hNew = this.calculateHeuristics(nextX, nextY);
			double fNew = gNew + hNew;

			if (nodesDetails[nextX][nextY].getF() == Double.MAX_VALUE || nodesDetails[nextX][nextY].getF() > fNew) {
				this.openList.add(new Triple(fNew, nextX, nextY));

				nodesDetails[nextX][nextY].setF(fNew);
				nodesDetails[nextX][nextY].setG(gNew);
				nodesDetails[nextX][nextY].setH(hNew);
				nodesDetails[nextX][nextY].setParent(nodesDetails[x][y]);
			}
		}
	}
}
