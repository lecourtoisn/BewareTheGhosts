package refact;

import com.badlogic.gdx.graphics.Color;

import static com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader.FreeTypeFontLoaderParameter;

public class FontParam {

    public static FreeTypeFontLoaderParameter build(String font, int size, Color color) {
        FreeTypeFontLoaderParameter param = new FreeTypeFontLoaderParameter();
        param.fontFileName = font;
        param.fontParameters.size = size;
        param.fontParameters.color = color;
        return param;
    }
}
