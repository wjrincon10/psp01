/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniandes.ecos.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
/**
 *
 * @author Administrador
 */
public class LectorArchivo {
    String separador;
    File archivo;
    BufferedReader br;
    FileReader fr;
    
public LectorArchivo(String pathArchivo, String separador) {
    this.separador = separador;
    this.archivo = new File(pathArchivo);
    try {
	this.fr = new FileReader(archivo);
	this.br = new BufferedReader(fr);
    } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        System.out.println("Error abriendo el archivo:/n");
        e.printStackTrace();
    }
}
        
public List<String> retornarArchivo() {
	List<String> archivoLeido = new ArrayList<String>();
	String[] lineaTemp = null;
	while ((lineaTemp = retornarLinea()).length > 0) {
            archivoLeido.add(lineaTemp[0]);
	}
	return archivoLeido;
}


public static String[] listarArchivosPath(String path) { 
		File directorio = new File(path);
		String[] listaArchivos = directorio.list(new FilenameFilter() {
					@Override
					public boolean accept(File dir, String name) {
						// TODO Auto-generated method stub
						return name.toLowerCase().endsWith(".java");
					}
				});
		if (listaArchivos == null) {
			System.out.println("No hay ficheros en el directorio especificado");
			return new String[] {}; 
		} else {
			return listaArchivos;
		}
	}

public static Set<Entry<Object, Object>> retornarProperties(
			String pathConfigFile) {
		/*
		 * Crear un objeto para almacenar las Properties
		 */
		Properties propiedades = new Properties();
		try {
			propiedades.load(new FileInputStream(pathConfigFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error abriendo archivo de propiedades "+ pathConfigFile);
			e.printStackTrace();
		}
		return propiedades.entrySet();
	}

public String[] retornarLinea() {
    String lineaTemp = null;
		String[] linea = null;
		try {
			lineaTemp = br.readLine();
			if (lineaTemp == null) {
				return new String[0];
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (separador == null) {
			return new String[] { lineaTemp };
		}

		StringTokenizer tokenizer;
		int n_cols = 0;
		if (lineaTemp != null) {
			tokenizer = new StringTokenizer(lineaTemp, separador);
		} else {
			return new String[] {};
		}
		// Contar las los tokens (# de columnas)
		n_cols = tokenizer.countTokens();
		linea = new String[n_cols];
		for (int i = 0; i < n_cols; i++) {
			linea[i] = tokenizer.nextToken();
		}
                
    return linea;
                
}
}
