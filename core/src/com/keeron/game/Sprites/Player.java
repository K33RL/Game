package com.keeron.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.keeron.game.MyGdxGame;
import com.keeron.game.Screens.PlayScreen;

public class Player extends Sprite {
    public World world;
    public Body b2body;
    private TextureRegion marioStand;

    public Player(World world, PlayScreen screen) {
        super(screen.getAtlas().findRegion("little_mario"));
        this.world = world;
        definePlayer();
        marioStand = new TextureRegion(getTexture(),0,0, 16,16);
        setBounds(0,0,16/ MyGdxGame.PPM, 16 / MyGdxGame.PPM );
        setRegion(marioStand);
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth() /2, b2body.getPosition().y - getHeight() /2 );
    }

    public void definePlayer() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / MyGdxGame.PPM, 32 / MyGdxGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(6 / MyGdxGame.PPM);

        fixtureDef.shape = shape;
        b2body.createFixture(fixtureDef);
    }
}
