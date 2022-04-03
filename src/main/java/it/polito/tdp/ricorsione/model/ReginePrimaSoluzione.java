package it.polito.tdp.ricorsione.model;

import java.util.ArrayList;
import java.util.List;

public class ReginePrimaSoluzione {
	
	// questa stesura del codice mi restituisce la prima soluzione trovata al problema delle N regine
	// è un pò più complesso forzare la ricorsione ad interrompersi
	List<Integer> soluzione; // restituisce una lista di interi che coincide con la prima soluzione trovata
						     // quando voglio portare fuori info dalla ricorsione è comodo appoggiarsi ad una proprietà della classe
	
	public List<Integer> cercaRegine(int N) { // metodo pubblico di interfaccia generale con param pari alla dimensione della scacchiera
		this.soluzione = null ; // posso inizializzare la soluzione a null in quanto potrebbe non esserci
		List<Integer> parziale = new ArrayList<Integer>() ; // uso un ArrayList per motivi di efficienza in relazione alle operazioni che faccio
		regine_ricorsiva(parziale, 0, N); // prima chiamata alla ricorsione
		return this.soluzione;
	} // se non c'è neanche una soluzione, ottengo un null
	
	// ciascuna chiamata ricorsiva che io faccio mi dice, tramite un boolean, se è il caso di continuare o meno
	private boolean regine_ricorsiva(List<Integer> parziale, int livello, int N ) {
		if(livello==N) { // caso terminale
//			System.out.println(parziale);
			this.soluzione = new ArrayList<Integer>(parziale);
			return false ; // non continuare! Ho appena ottenuto la prima soluzione di cui avevo bisogno
		} else {
			// ho già parziale[0] fino a parziale[livello-1] già decise
			// devo decidere parziale[livello] tra tutti i valori possibili
			// da 0 a N-1 (colonne), purché compatibili
			// la compatibilità sulle righe la garantisco per costruzione, quella sulle colonne va verificata
			for(int col=0; col<N; col++) { // GENERAZIONE DEI SOTTO PROBLEMI
				if(compatibile(livello, col, parziale)) { // questo è un metodo di appoggio alla ricorsione
					parziale.add(col); // aggiungo un tentativo alla soluzione parziale che mi è stata data
					boolean continua = regine_ricorsiva(parziale, livello+1, N); // passo la mia soluzione ad una nuova procedura ricorsiva
					if(!continua) // se la mia chiamata al livello sotto, mi dice di non continuare esco dicendo agli altri di fare la stessa cosa
						return false;
					parziale.remove(parziale.size()-1); // backtracking (ritornare sui propri passi): dopo aver fatto un tentativo,
					                                    // ritorno al punto di partenza per farne altri
				}
			}
			return true ; // c'è ancora altra roba da fare
		}
	}
	
	private boolean compatibile(int livello, Integer col, List<Integer> parziale) {
		if (parziale.indexOf(col) != -1) // se nella soluzione parziale c'è già il valore di colonna manca la compatibile
			return false;
		for(int riga = 0; riga < parziale.size(); riga++ )  { // analizzo tutte le righe dove ci sono già delle regine
			// regina alle coordinate (R,C)=( riga, parziale.get(riga) )
			// confrontare con (R,C)=(livello, col), ovvero la regina che sto cercando di posizionare
			if(riga + parziale.get(riga) == livello+col)
				return false; // la regina che sto cercando di posizionare si trova sulla diag dx della regina già posizionata
			if(riga - parziale.get(riga) == livello-col)
				return false; // la regina che sto cercando di posizionare si trova sulla diag sx della regina già posizionata
		}// se non è nè sulla stessa colonna nè sulle diag dx e sx, allora la posizione è compatibile
		return true ;
	}
	
}
