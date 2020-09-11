package particleSystem;

import org.jbox2d.common.Vec2;

import animation.Animation;
import dataStructure.MeshData;
import dataStructure.Texture;
import utils.Matrix4;

public class Particle {

	private MeshData mesh ;
	private Texture texture ;
	private Vec2 position ;
	private float rotation ;
	private Vec2 scale ;
	private float worldPosition ;

	private Matrix4 transformation ;
	
	private float life ;
	private boolean isDead ;
	private Animation animation ;
	private int repeat ;
	
	private int index1 ;
	private int index2 ;
	private float interpolate ;
	
	private Vec2 velocity ;
	
	public Particle(MeshData mesh, Texture texture, Vec2 position, float rotation, Vec2 scale, float worldPosition, float life, int repeat) {
	
		this.mesh = mesh;
		this.texture = texture;
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
		this.life = life ;
		this.worldPosition = worldPosition ;
		isDead = false ;
		transformation = Matrix4.createTransformationMatrix(position, rotation, scale) ;
		animation= new Animation();
		this.repeat = repeat ;
		index1 = 0;
		index2 = 0;
		interpolate = 0 ;
		velocity = new Vec2(0,0);
		
	}
	
	public void update() {
		getPosition().addLocal(velocity);
		animation.workParticle(this, 1/15f) ;
		
		
	}
	public Vec2 getPosition() {
		return position;
	}

	public void setPosition(Vec2 position) {
		this.position = position;
	}

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	public Vec2 getScale() {
		return scale;
	}

	public void setScale(Vec2 scale) {
		this.scale = scale;
	}


	public MeshData getMesh() {
		return mesh;
	}

	public Texture getTexture() {
		return texture;
	}

	public Matrix4 getTransformation() {
		transformation = Matrix4.updateTransformationMatrix(transformation, position, rotation, scale);
		return transformation;
	}

	public float getLife() {
		return life;
	}

	public void setLife(float life) {
		this.life = life;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public float getWorldPosition() {
		return worldPosition;
	}

	public void setWorldPosition(float worldPosition) {
		this.worldPosition = worldPosition;
	}

	public int getRepeat() {
		return repeat;
	}

	public void setRepeat(int repeat) {
		this.repeat = repeat;
	}

	
	public int getIndex1() {
		return index1;
	}

	public int getIndex2() {
		return index2;
	}

	public Vec2 getOffset1() {
		Vec2 offset = texture.getOffset(index1, 0) ;
		return offset;
	}

	public void setIndex1(int index1) {
		this.index1 = index1;
	}

	public Vec2 getOffset2() {
		Vec2 offset = texture.getOffset(index2, 0) ;
		return offset;
	}

	public void setIndex2(int index2) {
		this.index2 = index2;
	}

	public float getInterpolate() {
		interpolate = getOffset1().x % 1 ;
		return interpolate;
	}

	public void setInterpolate(float interpolate) {
		this.interpolate = interpolate;
	}

	public Vec2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vec2 velocity) {
		this.velocity = velocity;
	}

	
	
	
	
	
	
	
	
}
