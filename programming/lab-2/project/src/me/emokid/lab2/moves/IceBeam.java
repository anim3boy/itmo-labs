package me.emokid.lab2.moves;
import ru.ifmo.se.pokemon.*;

public class IceBeam extends SpecialMove{
    public IceBeam(){
        super(Type.ICE, 90, 100);
    }
    @Override
    protected String describe(){
        return "confuse";
    }
    @Override
    protected void applyOppEffects(Pokemon opp){
        opp.confuse();
        opp.setMod(Stat.ATTACK, 2);
    }
}