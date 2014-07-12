package curs5;

import java.io.Serializable;

public class PisicaObisnuita implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 61120082146L;
	String nume;
	
	public PisicaObisnuita(String numePisica) {
		nume = numePisica;
	}
	
	void Afisaza(){
		System.out.println(nume);
	}
	
	String cumOCheama(){
		return nume;
	}
}
