package me.emokid.lab2.moves;
import ru.ifmo.se.pokemon.*;

public class RockSlide extends PhysicalMove{

    public RockSlide(){
        super(Type.ROCK, 75, 90);

    }
    @Override
    protected void applyOppEffects(Pokemon opp){
        if (!opp.hasType(Type.ICE)) {
            opp.setCondition(new Effect().chance(0.3).condition(Status.FREEZE).attack(0.0d).turns(-1));
        }
        
        // Effect eff = new Effect();
        // eff = eff.chance(0.3);
        // eff = eff.flinch(opp);
        // opp.addEffect(eff);
    }
    @Override
    protected String describe(){
        return "flinch";
    }
}