package graph;

import java.util.LinkedList;

public class Kreuzung extends Node {
	private LinkedList<Node> connections;
	
	public Kreuzung(int id) {
		super(id);
		this.connections = new LinkedList<Node>();
	}
	
	public void addConnection(Node c) {
		if (c == null) {
			throw new IllegalArgumentException();

		}
		connections.add(c);

	}

	/**
	 * @return the connections
	 */
	public LinkedList<Node> getConnections() {
		return connections;
	}
	
	

	
}
