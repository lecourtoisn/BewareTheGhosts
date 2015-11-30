package com.mygdx.event;

public abstract class DifficultySchema {
    public enum Difficulty {
        NORMAL(normalSchema), HARD(hardSchema);
        private SalvoParameterTuple[] salvos;
        Difficulty(SalvoParameterTuple[] hardSchema) {
             salvos = hardSchema;
        }

        public SalvoParameterTuple get(int salvo) {
            return salvos[salvo];
        }

        public int getSize() {
            return salvos.length;
        }
    }

    private final static SalvoParameterTuple[] normalSchema = new DifficultySchema.SalvoParameterTuple[] {
        new SalvoParameterTuple(1, false, 4, 250, 1, 0),
        new SalvoParameterTuple(2, false, 4, 250, 2, 4000),
        new SalvoParameterTuple(3, false, 4, 250, 3, 3000),
        new SalvoParameterTuple(5, false, 4, 250, 4, 2500),
        new SalvoParameterTuple(5, false, 4, 250, 10, 2000), // 11 - 20
        new SalvoParameterTuple(5, false, 4, 250, 20, 1500), // ..40
        new SalvoParameterTuple(5, false, 4, 250, 20, 1200), // ..60
        new SalvoParameterTuple(5, false, 4, 250, 20, 1000), // ..80
        new SalvoParameterTuple(5, false, 4, 250, 20, 800), // ..100
        new SalvoParameterTuple(5, false, 4, 250, 10, 800), // ..110
        new SalvoParameterTuple(5, false, 3, 250, 10, 800), // ..120
        new SalvoParameterTuple(5, false, 2, 250, 10, 800), // ..130
        new SalvoParameterTuple(5, false, 1, 250, 20, 800), // ..150
        new SalvoParameterTuple(5, false, 3, 250, 10, 800), // ..160
        new SalvoParameterTuple(5, false, 1, 250, 5, 700), // ..165
        new SalvoParameterTuple(5, false, 1, 250, 5, 600), // ..170
        new SalvoParameterTuple(5, false, 1, 250, 5, 500), // ..175
        new SalvoParameterTuple(5, false, 1, 250, 5, 400), // ..180
        new SalvoParameterTuple(5, false, 1, 250, 10, 700), // ..190
        new SalvoParameterTuple(5, false, 1, 250, 10, 600), // ..200
        new SalvoParameterTuple(5, false, 1, 250, 10, 500), // ..210
        new SalvoParameterTuple(5, false, 1, 250, 10, 400), // ..220
    };

    private final static SalvoParameterTuple[] hardSchema = new DifficultySchema.SalvoParameterTuple[] {
            new SalvoParameterTuple(5, false, 4, 250, 20, 800), // ..100
            new SalvoParameterTuple(5, false, 4, 250, 10, 800), // ..110
            new SalvoParameterTuple(5, false, 3, 250, 10, 800), // ..120
            new SalvoParameterTuple(5, false, 2, 250, 10, 800), // ..130
            new SalvoParameterTuple(5, false, 1, 250, 20, 800), // ..150
            new SalvoParameterTuple(5, false, 3, 250, 10, 800), // ..160
            new SalvoParameterTuple(5, false, 1, 250, 5, 700), // ..165
            new SalvoParameterTuple(5, false, 1, 250, 5, 600), // ..170
            new SalvoParameterTuple(5, false, 1, 250, 5, 500), // ..175
            new SalvoParameterTuple(5, false, 1, 250, 5, 400), // ..180
            new SalvoParameterTuple(5, false, 1, 250, 10, 700), // ..190
            new SalvoParameterTuple(5, false, 1, 250, 10, 600), // ..200
            new SalvoParameterTuple(5, false, 1, 250, 10, 500), // ..210
            new SalvoParameterTuple(5, false, 1, 250, 10, 400), // ..220
    };



    /** Kind of a struct to simplify the event flow **/
    public static class SalvoParameterTuple {
        public int nbGhost;
        public boolean sameDirection;
        public int nbAttack;
        public long delayBetweenAttack;
        public int nbArrowBlink;
        public long arrowCycleDuration;

        public SalvoParameterTuple(int nbGhost, boolean sameDirection, int nbArrowBlink, long arrowCycleDuration, int nbAttack, long delayBetweenAttack) {
            this.nbGhost = nbGhost;
            this.sameDirection = sameDirection;
            this.nbAttack = nbAttack;
            this.delayBetweenAttack = delayBetweenAttack;
            this.nbArrowBlink = nbArrowBlink;
            this.arrowCycleDuration = arrowCycleDuration;
        }
    }
}
