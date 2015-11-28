package com.mygdx.util;

import java.util.Locale;

import static com.mygdx.util.International.Language.ENGLISH;
import static com.mygdx.util.International.Language.FRENCH;

public class International {
    public enum Language {
        FRENCH, ENGLISH
    }
    public enum Label {
        TITLE("Save Gary !", "Sauve Gary !"),
        NORMALLBL("Normal", "Normal"),
        HARDLBL("Hard", "Difficile"),
        HIGHSCORE("High Score", "Meilleur Score"),
        PAUSE("Pause", "Pause"),
        RESUME("Resume", "Reprendre"),
        TOUCH("Touch!", "Touch!"),
        NEWHIGHSCORE("High Score", "Record"),
        SCORE("Score", "Score"),
        PLAYAGAIN("Play again", "Rejouer"),
        MENU("Menu", "Menu");

        private String fr;
        private String en;


        Label(String en, String fr) {
            this.fr = fr;
            this.en = en;
        }

        public String get(Language language) {
            switch (language) {
                case FRENCH:
                    return fr;
                case ENGLISH:
                    return en;
            }
            return null;
        }
    }

    public static String get(Label lbl) {
        String lgTag = Locale.getDefault().toString();
//        lgTag = "en_EN";
        Language language = lgTag.equals("fr_FR") ? FRENCH : ENGLISH;
        return lbl.get(language);
    }
}
