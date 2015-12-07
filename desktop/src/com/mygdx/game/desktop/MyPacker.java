package com.mygdx.game.desktop;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class MyPacker {
    public static void main(String[] args) {
        TexturePacker.process("textures", "textures", "textures.atlas");
        TexturePacker.process("loadingScreen", "loadingScreen", "loading.atlas");
    }
}
