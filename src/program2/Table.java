/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program2;

import java.util.HashMap;

/**
 *
 * @author mattyws
 */
public class Table {
    
    public HashMap<String, Integer> table = new HashMap<>();
    
    public boolean containsKey(String variable){
        return table.containsKey(variable);
    }
    
    public Integer get(String variable){
        if(table.containsKey(variable)){
            return table.get(variable);
        }
        return null;
    }
    
    public void add(String variable, Integer value){
        table.put(variable, value);
    }
    
    public void update(String variable, Integer value){
        table.replace(variable, value);
    }
    
}
