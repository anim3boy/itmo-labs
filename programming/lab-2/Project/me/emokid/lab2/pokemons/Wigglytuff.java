package me.emokid.lab2.pokemons;
import me.emokid.lab2.moves.*;
import ru.ifmo.se.pokemon.*;

public class Wigglytuff extends Pokemon {
    public Wigglytuff(String name, int level){
        super(name,level);
        setStats('140', '70', '45', '85', '50', '45');
        setType(Type.NORMAL);
        setMove(new Dream_Eater(), new Icy_Wind(), new Body_Slam(), new Flamethrower());
    }
}