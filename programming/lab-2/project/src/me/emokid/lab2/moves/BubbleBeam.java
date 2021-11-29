package me.emokid.lab2.moves;
import ru.ifmo.se.pokemon.*;

public class BubbleBeam extends SpecialMove{

    public BubbleBeam(){
        super(Type.WATER, 65,100);

    }
    @Override
    protected void applySelfEffects(Pokemon att){
        Effect eff = new Effect().chance(0.1).stat(Stat.SPEED, -1);
        att.addEffect(eff);
    }
    @Override
    protected String describe(){
        return "slow down";
    }
}
