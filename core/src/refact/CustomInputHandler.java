package refact;

import com.badlogic.gdx.input.GestureDetector;

public class CustomInputHandler extends GestureDetector.GestureAdapter {

    public CustomInputHandler() {
        super();
    }

    public void keyDown(int keycode) {
        // Does nothing by default
    }

    public void keyUp(int keycode) {
        // Does nothing by default
    }

    /** Input Redirection **/
    public GestureDetector getDetector() {
        return detector;
    }

    private GestureDetector detector = new GestureDetector(this) {
        @Override
        public boolean keyDown(int keycode) {
            CustomInputHandler.this.keyDown(keycode);
            return false;
        }

        @Override
        public boolean keyUp(int keycode) {
            CustomInputHandler.this.keyUp(keycode);
            return false;
        }

    };
}
