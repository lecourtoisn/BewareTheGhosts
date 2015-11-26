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
        new SalvoParameterTuple(1, true, 1000, 5, 4000),
                new SalvoParameterTuple(3, true, 1000, 5, 4000),
                new SalvoParameterTuple(3, false, 1000, 5, 4000),
                new SalvoParameterTuple(2, false, 1000, 10, 2000),
                new SalvoParameterTuple(3, false, 1000, 10, 2000),
                new SalvoParameterTuple(3, false, 1000, 20, 1000),
    };

    private final static SalvoParameterTuple[] hardSchema = new DifficultySchema.SalvoParameterTuple[] {
            new SalvoParameterTuple(3, true, 1000, 20, 1000),
            new SalvoParameterTuple(3, false, 1000, 20, 750),
    };



    /** Kind of a struct to simplify the event flow **/
    public static class SalvoParameterTuple {
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
