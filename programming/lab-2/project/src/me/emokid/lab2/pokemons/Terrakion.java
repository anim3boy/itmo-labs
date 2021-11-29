package me.emokid.lab2.pokemons;

import me.emokid.lab2.moves.*;
import ru.ifmo.se.pokemon.*;

public class Terrakion extends Pokemon {
    public Terrakion(String name, int level){
        super(name,level);
        setStats(91, 129, 90, 72, 90, 108);
        setType(Type.ROCK, Type.FIGHTING);
        setMove(new DrillPeck(), new BubbleBeam(), new HydroPump(), new Swagger());
    }
}
