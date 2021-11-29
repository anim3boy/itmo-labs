package me.emokid.lab2;

import ru.ifmo.se.pokemon.*;
import me.emokid.lab2.pokemons.*;

class Main {
    public static void main(String[] args) {
        Battle b = new Battle();
        
        Terrakion p1 = new Terrakion("Paul McCartney", 2);
        Deerling p2 = new Deerling("John Lennon", 2);
        Sawsbuck p3 = new Sawsbuck("George Harrison", 2);
        Timburr p4 = new Timburr("Kurt Cobain", 2);
        Gurdurr p5 = new Gurdurr("Tim Bergling", 2);
        Conkeldurr p6 = new Conkeldurr("Billy Joel", 2);

        b.addAlly(p1);
        b.addAlly(p2);
        b.addAlly(p3);
        b.addFoe(p4);
        b.addFoe(p5);
        b.addFoe(p6);
        b.go();
    }
}