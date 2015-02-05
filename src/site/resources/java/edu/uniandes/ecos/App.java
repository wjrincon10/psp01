package edu.uniandes.ecos;

import java.util.*;
/**
 * Hello world!
 *
 */
public class App 
{
       
    public static void main( String[] args )
    {
        float media = 0;
        double desv = 0;       
        
        Calcular calc = new Calcular();      
        
        LinkedList<Float> lista1 = CargarDatos1();  
        LinkedList<Float> lista2 = CargarDatos2();

        ImprimirLista(lista2);        
        media = calc.CalcularMedia(lista2);
        desv = calc.CalcularDesviacion(lista2, media);
        
        System.out.println( "Media: " + media + " Desviacion: " + desv);            
        
    }
    
    public static LinkedList<Float> CargarDatos1(){
        LinkedList<Float> lista1= new LinkedList();
        
        lista1.add((float)160);
        lista1.add((float)591);
        lista1.add((float)114);
        lista1.add((float)229);
        lista1.add((float)230);
        lista1.add((float)270);
        lista1.add((float)128);
        lista1.add((float)1657);
        lista1.add((float)624);
        lista1.add((float)1503);
        
        return lista1;        
    }
    
    public static LinkedList<Float> CargarDatos2(){
        LinkedList<Float> lista= new LinkedList();       
        
        lista.add((float)15);
        lista.add((float)69.9);
        lista.add((float)6.5);
        lista.add((float)22.4);
        lista.add((float)28.4);
        lista.add((float)65.9);
        lista.add((float)19.4);
        lista.add((float)198.7);
        lista.add((float)38.8);
        lista.add((float)138.2);
        
        return lista;        
    }
    
    
    public static void ImprimirLista (LinkedList<Float> lista){
        for(int i=0;i< lista.size();i++){
             System.out.println( lista.get(i) );
        }
    }
    
}
