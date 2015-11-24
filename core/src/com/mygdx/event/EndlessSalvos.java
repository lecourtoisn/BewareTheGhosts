package com.mygdx.event;

import com.mygdx.util.RelativeStopWatch;
import com.mygdx.world.World;

public class EndlessSalvos extends Event {
    private static final long DELAY_BETWEEN_SALVO = 2000;
    private IEvent currentSalvo;
    private SalvoParameterTuple salvoParameterSet[];
    private int cursor;

    public EndlessSalvos(World world) {
        super(world);
        this.stopWatch = new RelativeStopWatch();
        salvoParameterSet = new SalvoParameterTuple[] {
            new SalvoParameterTuple(1, true, 1000, 5, 4000),
            new SalvoParameterTuple(3, true, 1000, 5, 4000),
            new SalvoParameterTuple(3, false, 1000, 5, 4000),
            new SalvoParameterTuple(2, false, 1000, 10, 2000),
            new SalvoParameterTuple(3, false, 1000, 10, 2000),
            new SalvoParameterTuple(3, false, 1000, 20, 1000),
        };
        cursor = 0;
    }

    @Override
    protected void run() {
        startNextSalvo();
    }

    private void startNextSalvo() {
        SalvoParameterTuple p = getCurrentParameter();
        currentSalvo = new GhostSalvo(world, p.nbGhost, p.sameDirection, p.arrowWarningDirection, p.nbAttack, p.delayBetweenAttack);
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
        cursor += (cursor == (salvoParameterSet.length - 1)) ? 0 : 1;
    }

    @Override
    public boolean mustEnd() {
        return world.getGarry().isDead();
    }

    public SalvoParameterTuple getCurrentParameter() {
        return salvoParameterSet[cursor];
    }

    /** Kind of a struct to simplify the event flow **/
    private class SalvoParameterTuple {
        public int nbGhost;
        public boolean sameDirection;
        public long arrowWarningDirection;
        public int nbAttack;
        public long delayBetweenAttack;

        public SalvoParameterTuple(int nbGhost, boolean sameDirection, long arrowWarningDirection, int nbAttack, long delayBetweenAttack) {
            this.nbGhost = nbGhost;
            this.sameDirection = sameDirection;
            this.arrowWarningDirection = arrowWarningDirection;
            this.nbAttack = nbAttack;
            this.delayBetweenAttack = delayBetweenAttack;
        }
    }
}
