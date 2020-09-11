package main;

import org.jbox2d.collision.shapes.ChainShape;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.MassData;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.collision.shapes.ShapeType;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import entity.Entity;
import entity.EntityBehaviour;

public class PhysicsBodyCreator {
	
	private World world ;
	
	public PhysicsBodyCreator(World world) {
		this.world = world ;
	}
	
	public Body loadBody(Entity entity, BodyType type, float linearDamping, boolean fixedRotation) {
		
		BodyDef bodydef = new BodyDef() ;
		bodydef.type = type ; 
		bodydef.position = entity.getPosition() ;
		bodydef.angle = entity.getRotation() ;
		bodydef.fixedRotation = fixedRotation ;
		bodydef.linearDamping = linearDamping ; 
		bodydef.userData = entity ;
		Body body = world.createBody(bodydef) ;
		
		return body ;
	}
	public Shape loadShape(Vec2 centerPos, Vec2[] vertices) {
		PolygonShape shape = new PolygonShape();
		shape.m_centroid.set(centerPos) ; 
		shape.set(vertices, vertices.length);
		return shape ;
	}
	public Shape loadShape(Vec2 shapePos, float radius) {
		CircleShape shape = new CircleShape();
		shape.m_p.set(shapePos) ;
		shape.m_radius = radius ;
		return shape ;
	}
	public void loadScreenShape(Vec2[] vertices) {
		BodyDef bodydef = new BodyDef() ;
		bodydef.type = BodyType.STATIC ; 
		bodydef.position = new Vec2(0,0) ;
		bodydef.angle = 0 ;
		bodydef.fixedRotation = true ;
		bodydef.linearDamping = 0 ; 
		Body body = world.createBody(bodydef) ;
		
		ChainShape shape = new ChainShape();
		shape.createChain(vertices, vertices.length);
		
		FixtureDef fixturedef = new FixtureDef();
		fixturedef.restitution = 0.2f;
		fixturedef.shape = shape ;
		fixturedef.density = 0.2f ;
		fixturedef.friction = 0.0f ;
		
		Fixture fixture = body.createFixture(fixturedef) ;
		
	}
	
	
	public Fixture loadFixture(Shape shape,Body body, float restitution) {
		FixtureDef fixturedef = new FixtureDef();
		fixturedef.restitution = restitution;
		fixturedef.shape = shape ;
		fixturedef.density = 0.2f ;
		fixturedef.friction = 0.0f ;
		Fixture fixture = body.createFixture(fixturedef) ;
		return fixture ;
	}
	
}
