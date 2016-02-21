/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program2;

/**
 *
 * @author mattyws
 */
public abstract class Exp {
    
    public abstract IntAndTable interp (Table t);
    
    @Override
    public abstract String toString();
    
}
