package graph;

import java.util.Hashtable;

public class Graph {

	private Hashtable<Integer, Node> nodes;
	
	/**
	 * Konstruktor
	 */
	public Graph() {
		this.nodes = new  Hashtable<Integer, Node>();
	}
	
	public void addNode(int id) {
		if (!nodes.contains(id)) {
			Node temp = new Node(id);
			nodes.put(id, temp);
		}
 	}
	
	public static void main(String[] args) {
		PacWorldReader reader = new PacWorldReader("maps/easy.map");
		System.out.println(reader.toString());
	}
}
