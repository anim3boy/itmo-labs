package me.emokid.lab2.pokemons;
import me.emokid.lab2.moves.*;
import ru.ifmo.se.pokemon.*;

public class Jigglypuff extends Pokemon {
    public Jigglypuff(String name, int level){
        super(name,level);
        setStats('115', '45', '20', '45', '25', '20');
        setType(Type.NORMAL);
        setMove(new Dream_Eater(), new Icy_Wind(), new Body_Slam());
    }
}