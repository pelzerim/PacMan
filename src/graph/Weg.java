package graph;

public class Weg extends Node {

	private Node node1;
	private Node node2;
	char typ;
	
	public Weg(int id) {
		super(id);
		typ = 'w';
		// TODO Auto-generated constructor stub
	}
	public Weg(int id, char typ) {
		super(id);
		typ = typ;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the node1
	 */
	public Node getNode1() {
		return node1;
	}

	/**
	 * @param node1 the node1 to set
	 */
	public void setNode1(Node node1) {
		this.node1 = node1;
	}

	/**
	 * @return the node2
	 */
	public Node getNode2() {
		return node2;
	}

	/**
	 * @param node2 the node2 to set
	 */
	public void setNode2(Node node2) {
		this.node2 = node2;
	}

	
	
}
