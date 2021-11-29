package me.emokid.lab2.moves;
import ru.ifmo.se.pokemon.*;

public class Confide extends StatusMove{
    public Confide(){
        super(Type.NORMAL,0,0);
    }
    @Override
    protected String describe(){
        return "downgrade";
    }
    @Override
    protected void applyOppEffects(Pokemon opp){
        opp.setMod(Stat.SPECIAL_ATTACK,-1);
    }
}