package graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class PacWorldReader {
	private BufferedReader in;
	private Node[][] pacman;
	
	public PacWorldReader(String filename) {
		readMap(filename);
	}
	
	private void readMap(String filename) {
		try {
			// BREITE LAENGE
			in = new BufferedReader(new FileReader(filename));
			String aktuelleZeile;
			int hoehe = 0;
			int laenge = 0;
			while ((aktuelleZeile = in.readLine()) != null) {
				hoehe++;
				if (laenge < aktuelleZeile.length()) {
					laenge = aktuelleZeile.length();
				}
			}
			in.close();
			pacman = new Node[hoehe][laenge];
			in = new BufferedReader(new FileReader(filename));
			verarbeiteDatei(in, laenge, hoehe);
			in.close();

			// Einlesen

		} catch (IOException e) {
			System.out.println("IOException - DATEILESEFEHLER");
			e.printStackTrace();
		} catch (Exception e) {

			System.out.println("ANDERER FEHLER:");
			e.printStackTrace();
		}
	}

	private void verarbeiteDatei(BufferedReader in, int laenge, int hoehe)
			throws IOException {
		String aktuelleZeile;

		Character[][] spielfeld = new Character[hoehe][laenge];

		// EINLESEN SPIELFELD
		int hoeheNow = 0;
		while ((aktuelleZeile = in.readLine()) != null) {
			aktuelleZeile = aktuelleZeile.trim();
			if (aktuelleZeile.length() > 0) {
				for (int i = 0; i < aktuelleZeile.length(); i++) {
					spielfeld[hoeheNow][i] = aktuelleZeile.charAt(i);
				}
				hoeheNow++;
			}

		}
		// VERARBEITEN SPIELFELD
		

		for (int y = 0; y < spielfeld.length; y++) {
			for (int x = 0; x < spielfeld[y].length; x++) {
				char typ = spielfeld[y][x];
				if (typ == 'x') {
					String id = y + "" + x;
					pacman[y][x] = new Kreuzung(Integer.parseInt(id));
				}
				if (typ == 'o') {
					String id = y + "" + x;
					pacman[y][x] = new Weg(Integer.parseInt(id));
				}
				if (typ == 'P') {
					String id = y + "" + x;
					pacman[y][x] = new Weg(Integer.parseInt(id), 'P');
				}
				if (typ == ' ') {
					String id = y + "" + x;
					pacman[y][x] = new Empty(Integer.parseInt(id));
				}
				if (typ == 'G') {
					String id = y + "" + x;
					pacman[y][x] = new Weg(Integer.parseInt(id), 'G');
				}
				if (typ == 'M') {
					String id = y + "" + x;
					pacman[y][x] = new Weg(Integer.parseInt(id), 'M');

				}
			}

		}

		// CONNECTIONS HINZUFUEGEN

		for (int y = 0; y < pacman.length; y++) {
			for (int x = 0; x < pacman[y].length; x++) {
				Node node = pacman[y][x];
				if (node.getClass() == Weg.class) {
					
					if(x != 0 &&  x != pacman[y].length - 1) {
						if (pacman[y][x-1].getClass() == Weg.class || pacman[y][x-1].getClass() == Kreuzung.class) {
							((Weg) node).setNode1(pacman[y][x-1]);
							((Weg) node).setNode2(pacman[y][x+1]);
						}
						
					}
					if(y!= 0 && y != pacman.length - 1) {
						if (pacman[y-1][x].getClass() == Weg.class || pacman[y-1][x].getClass() == Kreuzung.class) {
							((Weg) node).setNode1(pacman[y-1][x]);
							((Weg) node).setNode2(pacman[y+1][x]);
						}
					}
				}

				if (node.getClass() == Kreuzung.class) {
					// Links
					if(x != 0) {
						if (pacman[y][x-1].getClass() == Weg.class) {
							((Kreuzung) node).addConnection(pacman[y][x-1]);
						}
					}
					// RECHTS
					if(x !=  pacman[y].length -1) {
						if (pacman[y][x+1].getClass() == Weg.class) {
							((Kreuzung) node).addConnection(pacman[y][x+1]);
						}
					}
					// OBEN 
					if(y != 0) {
						if (pacman[y -1][x].getClass() == Weg.class) {
							((Kreuzung) node).addConnection(pacman[y-1][x]);
						}
					}
					// UNTEN
					if(y !=  pacman.length -1) {
						if (pacman[y + 1][x].getClass() == Weg.class) {
							((Kreuzung) node).addConnection(pacman[y +1][x]);
						}
					}
					
					
					
				}
			}

		}

	}

	/**
	 * @return the pacman
	 */
	public Node[][] getPacmanMap() {
		return pacman;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String ausgabe = "";
		for (int i = 0; i < pacman.length; i++) {
			for (int j = 0; j < pacman[i].length; j++) {
				if(pacman[i][j].getClass() ==  Kreuzung.class) {
					ausgabe += "x";
				}
				if(pacman[i][j].getClass() ==  Weg.class) {
					ausgabe += "o";
				}
				if(pacman[i][j].getClass() ==  Empty.class) {
					ausgabe += " ";
				}
			}
			ausgabe += "\n";
		}
		return ausgabe;
	}
	
	
	

}
