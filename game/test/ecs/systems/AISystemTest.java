package ecs.systems;

import static org.junit.Assert.assertEquals;

import controller.SystemController;
import ecs.components.ai.AIComponent;
import ecs.components.ai.transition.ITransition;
import ecs.entities.Entity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import starter.Game;

public class AISystemTest {

    private int updateCounter;
    private AISystem system;
    private Entity entity;

    @Before
    public void setup() {
        Game.systems = Mockito.mock(SystemController.class);
        Game.entities.clear();
        Game.entitiesToAdd.clear();
        Game.entitiesToRemove.clear();
        system = new AISystem();
        entity = new Entity();
        AIComponent component = new AIComponent(entity);
        component.setTransitionAI(
                new ITransition() {
                    @Override
                    public boolean isInFightMode(Entity entity) {
                        updateCounter++;
                        return false;
                    }
                });
        updateCounter = 0;
    }

    @Test
    public void update() {
        Game.entities.addAll(Game.entitiesToAdd);
        Game.entitiesToAdd.clear();
        system.update();
        assertEquals(1, updateCounter);
    }

    @Test
    public void updateWithoutAIComponent() {
        entity.removeComponent(AIComponent.class);
        system.update();
        assertEquals(0, updateCounter);
    }
}
