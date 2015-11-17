package com.mygdx.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.entity.Position;

public class Grid {

    private final static Texture TEXTURE = new Texture("grid.png");
    private Position position;
    private float size;
    private Position firstCellCenterPosition;
    private Vector2 borderThickness;

    public Grid(float size, float worldWidth, float worldHeight) {
        this.size = size;

        Vector2 vPosition = new Vector2(worldWidth - size, worldHeight - size).scl(0.5f);
        position = new Position(vPosition.x, vPosition.y);

        float ratio = getRatio();
        firstCellCenterPosition = new Position(58.5f*ratio, 57.5f*ratio);
        firstCellCenterPosition.add(position);
        borderThickness = new Vector2(14*ratio, 13*ratio);
    }

    private float getRatio() {
        return size / TEXTURE.getHeight();
    }

    public void draw(Batch batch) {
        batch.draw(TEXTURE, position.getX(), position.getY(), size, size);
    }

    public float getCellUnit() {
        return 94f * getRatio();
    }

    public Position getCellCenterPosition(int x, int y) {
        Position pos = new Position(firstCellCenterPosition);
        pos.move(x, y, this);
        return pos;
    }

    public Rectangle getBoundaries() {
        Vector2 recPos = new Vector2(position.getPosition());
        Vector2 recSize = new Vector2(size, size);
        Vector2 doubleBorder = new Vector2(borderThickness).scl(2);
        recPos.add(borderThickness);
        recSize.sub(doubleBorder);

        return new Rectangle(recPos.x, recPos.y, recSize.x, recSize.y);
    }
}
