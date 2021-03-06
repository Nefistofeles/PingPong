package entity;

import java.util.List;
import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.contacts.Contact;

public class EntityBehaviour implements ContactListener{

	@Override
	public void beginContact(Contact contact) {
		System.out.println("collision begin");
	}

	@Override
	public void endContact(Contact contact) {
		System.out.println("collision end");
		Entity entityA = (Entity)contact.m_fixtureA.getBody().getUserData() ;
		Entity entityB = (Entity)contact.m_fixtureB.getBody().getUserData() ;
		
		if(entityA instanceof Brick && entityB instanceof Ball) {
			entityA.setAlive(false);
		//	entityA.animation();
		}else if(entityB instanceof Brick && entityA instanceof Ball) {
			entityB.setAlive(false);
		}
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}

}
