package es.uam.eps.bmi.search;

import es.uam.eps.bmi.search.indexing.Index;
import es.uam.eps.bmi.search.indexing.LuceneIndex;
import es.uam.eps.bmi.search.indexing.Posting;
import es.uam.eps.bmi.search.parsing.HTMLSimpleParser;
import es.uam.eps.bmi.search.parsing.TextParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Clase compuesta por un main que nos devolvera informacion
 * sobre las frecuencias de los terminos.
 * 
 * @author Mario Valdemaro Garcia Roque <mariov.garcia@estudiante.uam.es> y
 * Angel Fuente Ortega <angel.fuente@estudiante.uam.es>
 *
 */


public class TextIndex {

	/**
	 * Ordena una lista de terminos en funcion de sus frecuencias
	 * @param terms una lista con los terminos a ordenar
	 * @param freqs una lista con las frecuencias
	 */
	static void sort(List<String> terms, List<Integer> freqs) {
        SortedMap<Integer, String> map = new TreeMap<Integer, String>(new Comparator<Integer>() {
            public int compare(Integer n, Integer m) { return m - n; }
        });
        for (int i = 0; i < terms.size(); i++) map.put(freqs.get(i), terms.get(i));
        Iterator iter = map.entrySet().iterator();
        terms.clear();
        freqs.clear();
        for (int i = 0; iter.hasNext(); i++) {
            Map.Entry<Integer, String> entry = (Map.Entry<Integer, String>) iter.next();
            terms.add(entry.getValue());
            freqs.add(entry.getKey());
        }
    }
	
   /** Creamos el índice, con los documentos que nos dan, indexamos los datos, y obtenemos la lista
    *  de terminos con sus frecuencias y número de archivos donde sale ordenada por frecuencia.
	* 
	* @param args argumentos que le pasamos al main. El estilo de la entrada es:
	* 1ºEl lugar donde se encuentran los documentos.
	* 2ºEl lugar donde crear el indice.
	* 3ºEl archivo donde guardar los resultados estadisticos.
	*/
	public static void main(String [] args) throws IOException{
		File f = new File(args[2]);
		FileWriter fw = new FileWriter(f);
		Index i = new LuceneIndex();
		TextParser parser= new HTMLSimpleParser();
		System.out.println(args[0] +" "+ args[1]+" "+ args[2]);
		i.build(args[0], args[1], parser);
		System.out.println("indices creados");
		List<String> terms = i.getTerms();
		List<Integer> freqs = new ArrayList<Integer>();
		System.out.println("obteniendo lista de terminos");
		for(String s : terms){
			List<Posting> post= i.getTermPostings(s);
			int freq =0;
			for(Posting p : post){
				freq+= p.getTermFrequency();
			}
			freqs.add(freq);
		}
		System.out.println("ordenando lista de terminos");
		sort(terms,freqs);
		System.out.println("escribiendo lista de terminos");
		for(int cont = 0; cont < terms.size(); cont++){
			List<Posting> post= i.getTermPostings(terms.get(cont));
			fw.write(terms.get(cont)+" "+freqs.get(cont)+" "+post.size()+"\n");
		}
		fw.close();
		System.out.println("fin");
	}
	
}
