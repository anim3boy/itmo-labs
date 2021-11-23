package me.emokid.lab2.pokemons;
import me.emokid.lab2.moves.*;
import ru.ifmo.se.pokemon.*;

public class Igglybuff extends Pokemon {
    public Igglybuff(String name, int level){
        super(name,level);
        setStats('90', '30', '15', '40', '20', '15');
        setType(Type.NORMAL);
        setMove(new Dream_Eater(), new Icy_Wind());
    }
}