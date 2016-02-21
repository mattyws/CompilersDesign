package program2;

/**
 * Program 2 Sample straight-line programs
 *
 * @author (sdb)
 * @version (Jan 2015)
 */
public class Driver {

    public static void main(String[] args) {
        Stm prog;

        //         a := 5+3; b := ( print (a, a-1), 10*a) ; print ( b)
        Stm stm1 = new AssignStm("a", new OpExp(new NumExp(5), Operations.ADD, new NumExp(3)));
        Stm stm2 = new AssignStm("b", new EseqExp(new PrintStm(new PairExpList(new IdExp("a"),
                new LastExpList(new OpExp(new IdExp("a"), Operations.SUB, new NumExp(1))))),
                new OpExp(new NumExp(10), Operations.MUL, new IdExp("a"))));
        Stm stm3 = new PrintStm(new LastExpList(new IdExp("b")));

        prog = new CompoundStm(stm1, new CompoundStm(stm2, stm3));

        System.out.println("\n\nInterpreting prog: \n" + prog + "\n");
        interp(prog);

	//          a := (b:=2,2) + (c:=3,3); print (a,b,c)
        stm1 = new AssignStm("b", new NumExp(2));
        stm2 = new AssignStm("c", new NumExp(3));

        Exp exp1 = new EseqExp(stm1, new NumExp(2));
        Exp exp2 = new EseqExp(stm2, new NumExp(3));
        stm3 = new AssignStm("a", new OpExp(exp1, Operations.ADD, exp2));        

        prog = new CompoundStm(stm3, new PrintStm(new PairExpList(new IdExp("a"), new PairExpList(new IdExp("b"), new LastExpList(new IdExp("c"))))));

        System.out.println("\n\nInterpreting prog: \n" + prog + "\n");
        interp(prog);

       // a := 2;  b = 3;
        // a := (b := (print (a-b, a+b), a*b),a*b);  print (a, b)        
       /////////////////////  
        // Redefine the variable prog for the above straight-line program
        /////////////////////
        
        stm1 = new AssignStm("a", new NumExp(2));
        stm2 = new AssignStm("b", new NumExp(3));
        Stm printStm = new PrintStm(new PairExpList(new OpExp(new IdExp("a"), Operations.SUB, new IdExp("b")), 
                new LastExpList(new OpExp(new IdExp("a"), Operations.ADD, new IdExp("b")))));
        Exp pairExp = new EseqExp(printStm, new OpExp(new IdExp("a"), Operations.MUL, new IdExp("b")));
        pairExp = new EseqExp(new AssignStm("b", pairExp), new OpExp(new IdExp("a"), Operations.MUL, new IdExp("b")));
        stm3 = new AssignStm("a", pairExp);
        Stm stm4 = new PrintStm(new PairExpList(new IdExp("a"), new LastExpList(new IdExp("b"))));
        
        prog = new CompoundStm(stm1, new CompoundStm(stm2, new CompoundStm(stm3, stm4)));
        
        
        System.out.println("\n\nInterpreting prog: \n" + prog + "\n");
        interp(prog);

    }

//    public static int maxArgs (Stm stm)
//    {  return stm.maxArgs();  } 
    public static void interp(Stm s) {
        Table t = new Table();
        t = s.interp(t);
    }
}
