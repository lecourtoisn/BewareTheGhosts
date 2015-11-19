package com.mygdx.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.entity.Position;
import com.mygdx.util.Direction;

import java.util.HashMap;
import java.util.Map;

public class Grid {

    private final static Texture TEXTURE = new Texture("grid.png");
    private final static int CELLPEREDGE = 6;
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

    public Map<Vector2, Direction> getExternalCells(Direction directions[]) {
        Map<Vector2, Direction> externalCells = new HashMap<Vector2, Direction>(4* CELLPEREDGE);

        for (Direction direction : directions) {
            externalCells.putAll(getExternalCells(direction));
        }
        return externalCells;
    }

    public Map<Vector2, Direction> getExternalCells(Direction direction) {
        Map<Vector2, Direction> externalCells = new HashMap<Vector2, Direction>(CELLPEREDGE);

        for (int i = 0; i < CELLPEREDGE; i++) {
            switch (direction) {
                case LEFT:
                    externalCells.put(new Vector2(-1, i), direction);
                    break;
                case UP:
                    externalCells.put(new Vector2(i, CELLPEREDGE), direction);
                    break;
                case RIGHT:
                    externalCells.put(new Vector2(CELLPEREDGE, i), direction);
                    break;
                case DOWN:
                    externalCells.put(new Vector2(i, -1), direction);
                    break;
            }
        }
        return externalCells;
    }
}
