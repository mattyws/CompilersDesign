package bergCompiler;
import java.util.*;

/**
 * A Binding associates an Identifier with
 *    Its usage: class, method, variable
 *    Its type, or return type for methods
 *    For methods, a List of the parameter types
 * 
 * @author (sdb) 
 * @version (Mar 2016)
 */
public class Binding
{
   Identifier id;
   IdType usage;
   String type;
   List <String> parms; // parameter types
   
   public Binding (Identifier i, IdType u, String t)
   {    id = i;
       usage = u;
       type = t;
       if (u == IdType.METHOD)
            parms = new LinkedList <String> ();
   }
   
   // class names do not have a type
   public Binding (Identifier i, IdType u)
   {    id = i;
       usage = u;
       if (u == IdType.METHOD)
            parms = new LinkedList <String> ();
   }
   
}
