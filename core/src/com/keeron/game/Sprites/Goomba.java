package com.keeron.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import com.keeron.game.MyGdxGame;
import com.keeron.game.Screens.PlayScreen;

public class Goomba extends Enemy {

    private float stateTime;
    private Animation walkAnimation;
    private Array<TextureRegion> frames;
    public Goomba(PlayScreen screen, float x, float y) {
        super(screen, x, y);
        frames = new Array<TextureRegion>();
        for (int i = 0; i < 2; i++)
            frames.add(new TextureRegion(screen.getAtlas().findRegion("goomba"), i * 16, 0, 16,16));
        walkAnimation = new Animation(0.4f, frames);
        stateTime = 0;
        setBounds(getX(),getY(), 16/MyGdxGame.PPM,16/MyGdxGame.PPM );
    }

    public void update(float dt){
        stateTime += dt;
        setPosition(b2body.getPosition().x - getWidth() /2, b2body.getPosition().y - getHeight() / 2);
        setRegion((TextureRegion) walkAnimation.getKeyFrame(stateTime,true));
    }

    @Override
    protected void defineEnemy() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / MyGdxGame.PPM, 32 / MyGdxGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(6 / MyGdxGame.PPM);
        fixtureDef.filter.categoryBits = MyGdxGame.ENEMY_BIT;
        fixtureDef.filter.maskBits = MyGdxGame.GROUND_BIT |
                MyGdxGame.COIN_BIT |
                MyGdxGame.BRICK_BIT |
                MyGdxGame.ENEMY_BIT |
                MyGdxGame.OBJECT_BIT |
                MyGdxGame.MARIO_BIT;

        fixtureDef.shape = shape;
        b2body.createFixture(fixtureDef);
    }
}
