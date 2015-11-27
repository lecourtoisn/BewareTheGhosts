package com.mygdx.event;

import com.mygdx.util.RelativeStopWatch;
import com.mygdx.world.World;
import com.mygdx.event.DifficultySchema.*;

public class EndlessSalvos extends Event {
    private static final long DELAY_BETWEEN_SALVO = 2000;
    private IEvent currentSalvo;
    private Difficulty salvoSet;
    private int cursor;

    public EndlessSalvos(World world, Difficulty difficulty) {
        super(world);
        this.stopWatch = new RelativeStopWatch();
        salvoSet = difficulty;
        cursor = 0;
    }

    @Override
    protected void run() {
        startNextSalvo();
    }

    private void startNextSalvo() {
        SalvoParameterTuple p = getCurrentParameter();
        currentSalvo = new GhostSalvo(world, p.nbGhost, p.sameDirection, p.nbArrowBlink, p.arrowCycleDuration, p.nbAttack, p.delayBetweenAttack);
        currentSalvo.start();
    }

    @Override
    protected void update(float delta) {
        if (currentSalvo.isOver()) {
            if (!stopWatch.isRunning()) {
                stopWatch.start();
            } else if (stopWatch.getMilliseconds() > DELAY_BETWEEN_SALVO) {
                stopWatch.stop();
                incrementCursor();
                startNextSalvo();
            }
        }
    }

    private void incrementCursor() {
        cursor += (cursor == (salvoSet.getSize() - 1)) ? 0 : 1;
    }

    @Override
    public boolean mustEnd() {
        return world.getGarry().isDead();
    }

    public DifficultySchema.SalvoParameterTuple getCurrentParameter() {
        return salvoSet.get(cursor);
    }


}
