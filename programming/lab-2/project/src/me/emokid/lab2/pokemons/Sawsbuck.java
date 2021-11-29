package me.emokid.lab2.pokemons;

import me.emokid.lab2.moves.*;
import ru.ifmo.se.pokemon.*;

public class Sawsbuck extends Pokemon {
    public Sawsbuck(String name, int level){
        super(name,level);
        setStats(80, 100, 70, 60, 70, 95);
        setType(Type.NORMAL, Type.GRASS);
        setMove(new IceBeam(), new Rest(), new Confide(), new BrutalSwing());
    }
}