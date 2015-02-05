/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniandes.ecos.model;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Administrador
 */
public class ContadorLOC {
    private static String[] arregloArchivos;
	private static LectorArchivo lectorArchivo;
	private static String pathArchivoConfig = ".\\src\\main\\java\\edu\\uniandes\\ecos\\model\\config.properties";
	private static String pathProgramaObjetivo;
	private static int contadorMetodos;
	private static int contadorLineas;
	private static int contadorLineasTotal;
	private static ArrayList<String[]> arregloConteoBase = new ArrayList<String[]>();// Se usa para contar base,modificadas, adicionadas Y borradas
																						
	private static String[] contadorBase;

        
        
    public void ContadorLineas(String path){
        pathProgramaObjetivo = path;
	List<String[]> arregloSalida = new ArrayList<String[]>();
		// Obtener el listado de archivos para el programa deseado
	arregloArchivos = LectorArchivo.listarArchivosPath(pathProgramaObjetivo);
	// Cargamos el archivo de configuración
	Set<Entry<Object, Object>> archivoConfig = LectorArchivo.retornarProperties(pathArchivoConfig);
	Hashtable<String, String> tablaConfig = new Hashtable<String, String>();

	for (Entry<Object, Object> entry : archivoConfig) {
            tablaConfig.put(entry.getKey().toString(), entry.getValue().toString());
	}
	// Cada archivo se debe cargar en memoria y realizar los conteos
	for (String pathArchivo : arregloArchivos) {
            pathArchivo = pathProgramaObjetivo + "\\" + pathArchivo;
            lectorArchivo = new LectorArchivo(pathArchivo, null);
            // Conteo de métodos y líneas
            contadorLineas = 0;
            contadorMetodos = 0;
            System.out.println("Contando líneas de " + pathArchivo);
            contadorBase = new String[] { pathArchivo, "0", "0", "0" };
            contarOcurrencias(lectorArchivo.retornarArchivo(), tablaConfig);
            arregloSalida.add(new String[] { pathArchivo,String.valueOf(contadorMetodos), String.valueOf(contadorLineas) });
            contadorLineasTotal += contadorLineas;
            arregloConteoBase.add(contadorBase);
        }
	

        // Imprimir resultados
		System.out.println("Part Name\tNumber of items\tPart Size\tTotal Size");
		for (String[] clase : arregloSalida) {
			System.out.println(clase[0] + "\t" + clase[1] + "\t" + clase[2]);
		}
		System.out.println("\t\t\t\t" + contadorLineasTotal);
		System.out.println("**********LINEA BASE*********");
		System.out.println("Nombre\tBorradas\tAdicionadas\tModificadas");
		for (String[] programa : arregloConteoBase) {
			System.out.println(programa[0] + "\t" + programa[1] + "\t"
					+ programa[2] + "\t" + programa[3]);

		}
        }
        
        private static void contarOcurrencias(List<String> archivo,
			Hashtable<String, String> tablaConfig) {
		// TODO Auto-generated method stub
		boolean comentario_bloque = false;
		int contadorLineasFisicas = 1;
		// Usar etiquetas del archivo de config para saber si la línea es
		// comentario, un import, package, o si se debe contar
		for (String linea : archivo) {
			linea = linea.trim();
			if (!comentario_bloque) {
				// Si la linea no está vacía
				if (!linea.equals("")) {
					// Conteo de lineas base
					if (linea.contains(tablaConfig.get("deleted_line"))) {
						contadorBase[1] = String.valueOf(((Integer
								.valueOf(contadorBase[1])) + 1));
					} else if (linea.contains(tablaConfig.get("new_line"))) {
						contadorBase[2] = String.valueOf(((Integer
								.valueOf(contadorBase[2])) + 1));
					} else if (linea.contains(tablaConfig.get("modified_line"))) {
						contadorBase[3] = String.valueOf(((Integer
								.valueOf(contadorBase[3])) + 1));
					}
					// Descartamos package e imports
					if (!(linea.startsWith(tablaConfig.get("import_sentence")) || linea
							.startsWith(tablaConfig.get("package_sentence")))) {
						// Descartamos comentarios de linea
						if (!linea.startsWith(tablaConfig
								.get("single_line_comment"))) {
							// Descartamos comentarios de bloque
							if (!linea.startsWith(tablaConfig
									.get("block_comment"))) {
								System.out.println(contadorLineasFisicas
										+ " es LOC");
								contadorLineas++;
							} else {
								// Descartamos comentarios de elemento para el
								// conteo de lineas, pero aumentamos el contador
								// de métodos
								if (linea.startsWith(tablaConfig
										.get("element_comment"))) {
									contadorMetodos++;
								}

								System.out
										.println(contadorLineasFisicas
												+ " inicia un comentario de bloque o de elemento");
								comentario_bloque = true;
							}
						} else {
							System.out.println(contadorLineasFisicas
									+ " es un comentario de línea");
						}
					} else {
						System.out.println(contadorLineasFisicas
								+ " contiene palabras package o import");
					}
				} else {
					System.out.println(contadorLineasFisicas + " está vacía");
				}
			} else {
				// Si es la terminacion de un comentario de bloque
				if (linea.endsWith(tablaConfig.get("block_comment_end"))) {
					System.out
							.println(contadorLineasFisicas
									+ " finaliza un comentario de bloque o de elemento");
					comentario_bloque = false;
				} else {
					System.out.println(contadorLineasFisicas
							+ " continúa comentario de bloque o de elemento");

				}
			}
			contadorLineasFisicas++;
		}
		contadorMetodos--;// restamos uno porque siempre se cuenta el comentario
							// de la clase, y sólo debe ser de métodos
	}
}