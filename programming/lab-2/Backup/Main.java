import ru.ifmo.se.pokemon.*; 
import me.emokid.lab2.pokemons.*;

public class Main{
    public static void main(String[] args){
        Battle b = new Battle();
        //marker
        Spiritomb p1 = new Spiritomb("Paul McCartney", 2);
        Mankey p2 = new Mankey("Kurt Cobain", 2);
        Primeape p3 = new Primeape("Tom DeLonge", 2);
        Igglybuff p4 = new Igglybuff("Lil Peep", 2);
        Jigglypuff p5 = new Jigglypuff("Thom Yorke", 2);
        Wigglytuff p6 = new Wigglytuff("Billy Joe Armstrong", 2);
//marker
        b.addAlly(p1);
        b.addAlly(p2);
        b.addAlly(p3);
        b.addFoe(p4);
        b.addFoe(p5);
        b.addFoe(p6);
        b.go();
    }
}
