package it.polito.tdp.ricorsione.model;

import java.util.ArrayList;
import java.util.List;

public class QuadratoMagico {
	
	private int sommaCorretta; // dichiariamo questi attributi per far sì che le miliardesime chiamate ricorsive siano più efficienti
	private int N;
	private int N2;

	public void risolviQuadrato(int N) {
		this.N = N;
		this.N2 = N*N;
		this.sommaCorretta = N*(N2+1)/2;
		
		List<Integer> parziale = new ArrayList<Integer>(); // ho preparato per la procedura ricorsiva sia la struttura dati su cui dovrà lavorare sia le costanti che deve conoscere
		cerca(parziale,0);
	}
	
	private void cerca(List<Integer> parziale,int livello) {
		if(livello == this.N2) {
			// caso terminale: ho inserito esattamente N numeri
			if(controllaSomme(parziale)) {
				System.out.println(parziale);
			}
		}else {
			if(livello%this.N == 0 && livello!=0){ // se il livello è un multiplo di N vuol dire che sto iniziando una riga nuova e quindi posso fare il controllo sulla riga prec.
			   if(!controllaRiga(parziale,livello/this.N-1))
				   return;
			}
				
				for(int i = 1; i<= this.N2;i++) { // itero sui valori possibili
					if(!parziale.contains(i)) { // creo delle permutazioni e non delle combinazioni con duplicati
						// provare ad aggiungere 'i' alla cella 'livello'
						parziale.add(i); // provo ad aggiungere ogni valore 
						cerca(parziale,livello+1); // nuova chiamata ricorsiva
						parziale.remove(parziale.size()-1); // backtracking
					}
				}
			}
		}

	private boolean controllaRiga(List<Integer> parziale, int riga) {
		// TODO Auto-generated method stub
		int s = 0;
		for(int col=0;col<N;col++) {
			s = s + parziale.get(riga*this.N+col);
		}
		if(s!=this.sommaCorretta)
			return false;
		else
			return true;
	}

	private boolean controllaSomme(List<Integer> parziale) {
		// TODO Auto-generated method stub
		// controlla la somma per righe
		for(int riga=0; riga<this.N;riga++) {
			int s = 0;
			for(int col=0;col<this.N;col++) 
				s = s + parziale.get(riga*this.N+col); // passo da rappresentazione lineare a rappr. bidimensionale immaginaria
			if(s!=this.sommaCorretta)
				return false; 
		}
		// controlla la somma per colonna
		for(int col=0; col<this.N;col++) {
			int s = 0;
			for(int riga=0;riga<this.N;riga++) 
				s = s + parziale.get(riga*this.N+col); // passo da rappresentazione lineare a rappr. bidimensionale immaginaria
			if(s!=this.sommaCorretta)
				return false; 
		}
		// diag 1 (riga=colonna)
		int s= 0;
		for(int riga=0;riga<this.N;riga++) 
			s = s + parziale.get(riga*this.N+riga);
		if(s!=this.sommaCorretta)
			return false;
		// diag 2
		s= 0;
		for(int riga=0;riga<this.N;riga++) 
			s = s + parziale.get(riga*this.N+(this.N-1-riga));
		if(s!=this.sommaCorretta)
			return false;
		
		return true;
		}
	}

