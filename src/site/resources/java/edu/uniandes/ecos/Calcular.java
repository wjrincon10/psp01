/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniandes.ecos;

import java.util.*;

/**
 *
 * @author Administrador
 */
public class Calcular {
    
    public float CalcularMedia(LinkedList<Float> lista)
    {
        int cant =0;
        float acum =0;
                
        for (Float lista1 : lista) {
            acum = acum + (float) lista1;
            cant++;
        }
          
        return acum /cant;
    }
    
     public double CalcularDesviacion(LinkedList<Float> lista, float media)
    {
        int cant =0;
        double acum =0;
        
        Iterator iterador = lista.iterator();
        
        while(iterador.hasNext())
        {
            acum = acum + Math.pow((double) lista.poll() - media,2);
            cant++;
        }
        
        cant--;
        return Math.sqrt((double)acum / cant);
    }
}
