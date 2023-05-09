package ecs.components;

import ecs.entities.Entity;
import graphic.Animation;
import java.util.List;
import java.util.logging.Logger;
import logging.CustomLogLevel;
import semanticAnalysis.types.DSLContextMember;
import semanticAnalysis.types.DSLType;
import semanticAnalysis.types.DSLTypeMember;

/**
 * The VelocityComponent allows the associated entity to move in the dungeon.
 *
 * <p>The VelocityComponent stores the speed at which the entity can move along the x and y axes, as
 * well as the animations to play when the entity is moving.
 *
 * <p>It also stores the current movement speed on the x and y axes. This information will be used
 * by the {@link ecs.systems.VelocitySystem VelocitySystem} to move the entity at the given speed.
 *
 * <p>The current movement speed can be set by other Systems like the {@link
 * ecs.systems.PlayerSystem PlayerSystem}.
 *
 * <p>Note that a positive current movement speed means that the entity is moving right or up, and a
 * negative current movement speed means that the entity is moving left/down. If the current x and y
 * speed is 0, that means the entity is currently not moving.
 */
@DSLType(name = "velocity_component")
public class VelocityComponent extends Component {
    private static List<String> missingTexture = List.of("animation/missingTexture.png");
    private float currentXVelocity;
    private float currentYVelocity;
    private @DSLTypeMember(name = "x_velocity") float xVelocity;
    private @DSLTypeMember(name = "y_velocity") float yVelocity;

    private @DSLTypeMember(name = "move_right_animation") Animation moveRightAnimation;
    private @DSLTypeMember(name = "move_left_animation") Animation moveLeftAnimation;
    private final Logger velocityCompLogger = Logger.getLogger(this.getClass().getName());

    /**
     * Create a new VelocityComponent with the given configuration.
     *
     * @param entity associated entity
     * @param xVelocity Speed with which the entity can move on the x-axis
     * @param yVelocity Speed with which the entity can move on the y-axis
     * @param moveLeftAnimation Animation that plays when the entity moves to the left
     * @param moveRightAnimation Animation that plays when the entity moves to the right
     */
    public VelocityComponent(
            Entity entity,
            float xVelocity,
            float yVelocity,
            Animation moveLeftAnimation,
            Animation moveRightAnimation) {
        super(entity);
        this.currentXVelocity = 0;
        this.currentYVelocity = 0;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.moveLeftAnimation = moveLeftAnimation;
        this.moveRightAnimation = moveRightAnimation;
    }

    /**
     * Create a new VelocityComponent with the default configuration.
     *
     * <p>In the default configuration, the movementspeed is set to 0, so the entity will not move.
     *
     * @param entity associated entity
     */
    public VelocityComponent(@DSLContextMember(name = "entity") Entity entity) {
        super(entity);
        this.currentXVelocity = 0;
        this.currentYVelocity = 0;
        this.xVelocity = 0;
        this.yVelocity = 0;
        this.moveLeftAnimation = new Animation(missingTexture, 100);
        this.moveRightAnimation = new Animation(missingTexture, 100);
    }

    /**
     * @return current speed on the x-axis
     */
    public float getCurrentXVelocity() {
        return currentXVelocity;
    }

    /**
     * @param currentXVelocity set current speed on the x-axis
     */
    public void setCurrentXVelocity(float currentXVelocity) {
        this.currentXVelocity = currentXVelocity;
    }

    /**
     * @return current speed on the y-axis
     */
    public float getCurrentYVelocity() {
        return currentYVelocity;
    }

    /**
     * @param currentYVelocity set current speed on the y-axis
     */
    public void setCurrentYVelocity(float currentYVelocity) {
        this.currentYVelocity = currentYVelocity;
    }

    /**
     * @return speed with which the entity can move on the x-axis
     */
    public float getXVelocity() {
        velocityCompLogger.log(
                CustomLogLevel.DEBUG,
                "Fetching x-velocity for entity '"
                        + entity.getClass().getSimpleName()
                        + "': "
                        + xVelocity);
        return xVelocity;
    }

    /**
     * @param xVelocity set speed with which the entity can move on the x-axis
     */
    public void setXVelocity(float xVelocity) {
        this.xVelocity = xVelocity;
    }

    /**
     * @return speed with which the entity can move on the y-axis
     */
    public float getYVelocity() {
        velocityCompLogger.log(
                CustomLogLevel.DEBUG,
                "Fetching y-velocity for entity '"
                        + entity.getClass().getSimpleName()
                        + "': "
                        + yVelocity);
        return yVelocity;
    }
    /**
     * @param yVelocity set speed with which the entity can move on the y-axis
     */
    public void setYVelocity(float yVelocity) {
        this.yVelocity = yVelocity;
    }

    /**
     * @return animation that plays when the entity moves to the right
     */
    public Animation getMoveRightAnimation() {
        return moveRightAnimation;
    }

    /**
     * @return Animation that plays when the entity moves to the left
     */
    public Animation getMoveLeftAnimation() {
        return moveLeftAnimation;
    }
}
