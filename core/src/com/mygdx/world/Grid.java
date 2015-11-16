package com.mygdx.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.entity.Position;

public class Grid {

    private final static Texture TEXTURE = new Texture("grid.png");
    private Position position;
    private float size;
    private Position firstCellPosition;

    public Grid(float size, float worldWidth, float worldHeight) {
        this.size = size;

        Vector2 vPosition = new Vector2(worldWidth - size, worldHeight - size).scl(0.5f);
        position = new Position(vPosition.x, vPosition.y);

        float ratio = getRatio();
        firstCellPosition = new Position(58.5f*ratio, 57.5f*ratio);
        firstCellPosition.add(position);
    }

    public float getRatio() {
        return size / TEXTURE.getHeight();
    }

    public void draw(Batch batch) {
        batch.draw(TEXTURE, position.getX(), position.getY(), size, size);
    }

    public float getCellUnit() {
        return 94f * getRatio();
    }

    public Position getCellPosition(int x, int y) {
        Position pos = new Position(firstCellPosition);
        pos.move(x, y, this);
        return pos;
    }
}
