package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

import dataStructure.Texture;
import drawer.Draw;
import gameEntity.Light;
import main.Creator;
import main.IRenderer;

public class MasterEntityRenderer implements IRenderer {

	private Map<Texture, List<Entity>> entities;
	private EntityRenderer renderer;
	private World world;
	private Creator creator;

	public MasterEntityRenderer(Draw draw) {
		entities = new HashMap<Texture, List<Entity>>();
		renderer = new EntityRenderer(draw);

	}

	public void set(Creator creator, World world, EntityBehaviour contactListener) {
		this.creator = creator;
		this.world = world;
		world.setContactListener(contactListener);
	}

	@Override
	public void update() {

		Iterator<Texture> iter1 = entities.keySet().iterator();
		while (iter1.hasNext()) {
			Texture texture = iter1.next();
			List<Entity> entitylist = entities.get(texture);
			Iterator<Entity> iter = entitylist.iterator();
			while (iter.hasNext()) {
				Entity e = (Entity) iter.next();
				boolean stillAlive = e.isAlive();
				if (!stillAlive) {
					e.dead(creator);
					destroyEntity(iter, e);
				} else {
					e.behaviour();
				}
			}
			if (entitylist.isEmpty()) {
				iter1.remove();
			}

		}

	}

	public void addEntity(Entity entity) {
		Texture texture = entity.getTexture();
		List<Entity> list = entities.get(texture);
		if (list == null) {
			list = new ArrayList<Entity>();
			list.add(entity);
			entities.put(texture, list);
		} else {
			list.add(entity);
		}
	}

	public void addEntity(List<Entity> entityt) {
		for (Entity entity : entityt) {
			Texture texture = entity.getTexture();
			List<Entity> list = entities.get(texture);
			if (list == null) {
				list = new ArrayList<Entity>();
				list.add(entity);
				entities.put(texture, list);
			} else {
				list.add(entity);
			}
		}

	}


	public void destroyEntityAll() {
		Iterator<Texture> iter1 = entities.keySet().iterator();
		while (iter1.hasNext()) {
			Texture texture = iter1.next();
			List<Entity> entitylist = entities.get(texture);
			Iterator<Entity> iter = entitylist.iterator();
			while (iter.hasNext()) {
				Entity e = (Entity) iter.next();
				destroyEntity(iter, e);
				
			}
			if (entitylist.isEmpty()) {
				iter1.remove();
			}

		}
	}

	private void destroyEntity(Iterator<Entity> iter, Entity e) {
		Body b = e.getBody();
		if(b != null)
			world.destroyBody(b);
		iter.remove();
	}

	@Override
	public void render() {

		renderer.render(entities);
	}

	@Override
	public void cleanUp() {
		renderer.cleanUp();

	}

	@Override
	public void init() {

	}

	public Map<Texture, List<Entity>> getEntities() {
		return entities;
	}

}
