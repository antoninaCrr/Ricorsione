package it.polito.tdp.ricorsione.model;

import java.util.ArrayList;
import java.util.List;

public class Regine {
	
	// questa è la prima stesura del codice che mi calcola TUTTE le soluzioni
	// la ricorsione una volta partita tende a generare tutte le soluzioni
	private List<List<Integer>> tutte ;

	public List<List<Integer>> cercaRegine(int N) {
		this.tutte = new ArrayList<List<Integer>>(); // contenitore di soluzioni che creiamo vuoto
		List<Integer> parziale = new ArrayList<Integer>() ;
		regine_ricorsiva(parziale, 0, N);	
		return this.tutte ;
	}// se non ci sono soluzioni, ottengo una lista vuota
	
	private void regine_ricorsiva(List<Integer> parziale, int livello, int N ) {
		if(livello==N) { // caso terminale
//			System.out.println(parziale);
			this.tutte.add(new ArrayList<Integer>(parziale)); // anzichè fare un'aggiunta sulla console, la faccio in questo contenitore
															  // senza new otterrei una lista vuota, ripulita dal backtracking
		} else {
			// ho già parziale[0] fino a parziale[livello-1] già decise
			// devo decidere parziale[livello] tra tutti i valori possibili
			// da 0 a N-1 (colonne), purché compatibili
			for(int col=0; col<N; col++) {
				if(compatibile(livello, col, parziale)) {
					parziale.add(col);
					regine_ricorsiva(parziale, livello+1, N);
					parziale.remove(parziale.size()-1); // backtracking
				}
			}
		}
	}
	
	private boolean compatibile(int livello, Integer col, List<Integer> parziale) {
		if (parziale.indexOf(col) != -1)
			return false;
		for(int riga = 0; riga < parziale.size(); riga++ )  {
			// regina alle coordinate (R,C)=( riga, parziale.get(riga) )
			// confrontare con (R,C)=(livello, col)
			if(riga + parziale.get(riga) == livello+col)
				return false;
			if(riga - parziale.get(riga) == livello-col)
				return false;
		}
		return true ;
	}
	
}
