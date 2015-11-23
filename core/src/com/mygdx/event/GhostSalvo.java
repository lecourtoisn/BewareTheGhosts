package com.mygdx.event;

import com.mygdx.world.World;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GhostSalvo extends Event {
    private List<Event> attacks;
    private Iterator<Event> attacksIterator;

    private long delayBetweenAttacks;

    public GhostSalvo(World world, int nbGhost, boolean sameDirection, float arrowWarningDuration, int nbAttack, long delayBetweenAttacks) {
        super(world);
        attacks = new ArrayList<Event>(nbAttack);
        this.delayBetweenAttacks = delayBetweenAttacks;

        for (int i = 0; i < nbAttack; i++) {
            attacks.add(new GhostAttack(world, nbGhost, sameDirection, arrowWarningDuration));
        }
    }

    @Override
    protected void run() {
        stopWatch.start(delayBetweenAttacks);
        attacksIterator = attacks.iterator();
    }

    @Override
    protected void update(float delta) {
        if(attacksIterator.hasNext() && stopWatch.getMilliseconds() > delayBetweenAttacks) {
            IEvent attack = attacksIterator.next();
            attack.start();
            stopWatch.restart();
        }
    }

    /**
     * Ends if every attacks are over
     */
    @Override
    protected boolean mustEnd() {
        boolean noMoreAttackRunning = true;
        for (Event attack : attacks) {
            if (!attack.isOver()) {
                noMoreAttackRunning = false;
            }
        }
        return noMoreAttackRunning;
    }
}
