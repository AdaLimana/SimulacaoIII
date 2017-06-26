/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Porcentagem;

public class Porcentagem {
    
    public static float calculo(int porcentagem, int total){
    
        return (porcentagem*total)/100;
    }
    
    /*
        retorna a quantdade referente a (porcentagem) de (total);
    */
    public static int calculoArrendondado(int porcentagem, int total){
    
        return Math.round(calculo(porcentagem, total)); /*arredonda*/
    }
    
}
