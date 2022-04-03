package it.polito.tdp.ricorsione.model;

public class Anagrammi {

	/**
	 * Riceve una stringa da anagrammare e stampa tutti i suoi anagrammi
	 * 
	 * @param s
	 */
	public void anagramma(String s) { // interfaccia pubblica che ti permette di preparare i dati che servono alla ricorsione
		anagramma_ricorsiva("", 0, s); // soluzione parziale vuota, livello 0, s come lettere rimanenti
		// questo è l'avvio della ricorsione
	}

	/**
	 * Data una soluzione parziale, stampa TUTTI gli anagrammi che iniziano in quel
	 * modo.
	 * 
	 * @param parziale  Soluzione parziale, iniziale, del mio anagramma. Avrà
	 *                  'livello' caratteri.
	 * @param livello   Livello della ricorsione
	 * @param rimanenti I caratteri ancora da sistemare (lunghezza stringa pari a lunghezza stringa da anagr. - livello)
	 */
	private void anagramma_ricorsiva(String parziale, int livello, String rimanenti) {
		if (rimanenti.length() == 0) { /* caso terminale */
			System.out.println(parziale); // la funzione stampa tutti gli anagrammi
		} else {
			/* caso normale */
			// es: parziale="AC", livello=2, rimanenti="BD"
			// devo decomporre il problema in tanti sotto problemi quanti sono le lettere rimanenti
			for (int pos = 0; pos < rimanenti.length(); pos++) {
				String nuova_parziale = parziale + rimanenti.charAt(pos); // prendo la soluzione parziale e aggiungo al primo for B e al secondo D
				String nuova_rimanenti = rimanenti.substring(0, pos) + rimanenti.substring(pos + 1); // prendo la stringa rimamenti e tolgo il carattere aggiunto alla soluzione parziale
																									 // prendo i caratteri dalla posizione 0 alla posizione pos(esclusa) + i car dalla posizione pos+1 fino alla fine della stringa
				anagramma_ricorsiva(nuova_parziale, livello + 1, nuova_rimanenti);
				
				
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Anagrammi main = new Anagrammi(); // devo avere l'istanza su cui chiamare
		main.anagramma("ABCD");
	}

}
