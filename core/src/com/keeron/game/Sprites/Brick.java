package com.keeron.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.keeron.game.MyGdxGame;

public class Brick extends  InteractiveTileObject{
    public Brick(World world, TiledMap map, Rectangle bounds){
        super(world,map,bounds);
        fixture.setUserData(this);
        setCategoryFilter(MyGdxGame.BRICK_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Brick","Collision");
        setCategoryFilter(MyGdxGame.DESTROYED_BIT);
        getCell().setTile(null);
    }
}
