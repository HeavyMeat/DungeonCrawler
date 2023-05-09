package ecs.entities;

import dslToGame.AnimationBuilder;
import ecs.components.*;
import ecs.components.ai.AIComponent;
import ecs.components.ai.fight.CollideAI;
import ecs.components.ai.fight.IFightAI;
import ecs.components.ai.idle.IIdleAI;
import ecs.components.ai.idle.StaticRadiusWalk;
import ecs.components.ai.transition.ITransition;
import ecs.components.ai.transition.RangeTransition;
import graphic.Animation;
import java.util.Random;

public class MonsterFactory {
    public static Random RANDOM = new Random();

    public static Entity createMonster(String texturePath) {
        Entity monster = new Entity();

        new PositionComponent(monster);
        float xSpeed = RANDOM.nextFloat(0.1f, 0.3f);
        float ySpeed = RANDOM.nextFloat(0.1f, 0.3f);
        Animation runLeft = AnimationBuilder.buildAnimation(texturePath + "/runLeft");
        Animation runRight = AnimationBuilder.buildAnimation(texturePath + "/runRight");
        ;
        new VelocityComponent(monster, xSpeed, ySpeed, runLeft, runRight);

        new HitboxComponent(monster);

        Animation hitAnimation = AnimationBuilder.buildAnimation(texturePath + "/idleLeft");
        ;
        Animation dieAniamtion = AnimationBuilder.buildAnimation(texturePath + "/idleRight");
        ;
        IOnDeathFunction onDeathFunction =
                new IOnDeathFunction() {
                    @Override
                    public void onDeath(Entity entity) {}
                };
        new HealthComponent(
                monster, RANDOM.nextInt(20), onDeathFunction, hitAnimation, dieAniamtion);

        IFightAI fightAI = new CollideAI(RANDOM.nextFloat(1.5f));

        ITransition transition = new RangeTransition(RANDOM.nextFloat(2f, 4f));
        IIdleAI idle = new StaticRadiusWalk(RANDOM.nextFloat(1f, 10f), RANDOM.nextInt(2, 4));
        new AIComponent(monster, fightAI, idle, transition);

        Animation il = AnimationBuilder.buildAnimation(texturePath + "/idleLeft");
        ;
        Animation ir = AnimationBuilder.buildAnimation(texturePath + "/idleRight");
        ;
        new AnimationComponent(monster, il, ir);

        return monster;
    }
}
