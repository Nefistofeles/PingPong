package main;

import java.util.HashMap;
import java.util.Map;

import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.World;
import org.lwjgl.opengl.GL11;

import dataStructure.Color;
import dataStructure.EntityTexture;
import dataStructure.MeshData;
import dataStructure.ParticleTexture;
import dataStructure.Texture;
import dataStructure.TextureType;
import entity.Background;
import entity.Ball;
import entity.Brick;
import entity.Entity;
import entity.EntityType;
import entity.Player;
import gameEntity.Light;
import guis.GUI;
import guis.GUIArea;
import loader.Loader;
import particleSystem.Particle;
import renderer.Renderer;
import textCreator.FontType;
import textCreator.Text;
import textCreator.button.Button;
import utils.Coordinates;

public class Creator {
	
	private Loader loader;
	private Renderer renderer;
	private static MeshData mesh;
	private Map<String, Texture> textures ;
	private PhysicsBodyCreator physicsCreator ;
	private World world ;
	private FontType font ;
	private Texture fontTexture ;
	
	public Creator(Loader loader, Renderer renderer, World world) {

		this.world = world ;
		physicsCreator = new PhysicsBodyCreator(world);
		this.loader = loader;
		this.renderer = renderer;
		textures = new HashMap<String, Texture>();
		mesh = loader.getModelLoader().loadMesh(Coordinates.Vertex, Coordinates.TextureCoords, Coordinates.indices);

		font = new FontType("candaraTurkish", loader,3);
		fontTexture = new EntityTexture(loader.getTextureLoader().loadTextureForFont("candaraTurkish"));
	}
	public Texture addTexture(String name, TextureType type, int mipmapValue, float bias) {
		Texture texture = null ;
		if(type == TextureType.entity) {
			texture = new EntityTexture(loader.getTextureLoader().loadTexture(name, GL11.GL_REPEAT, mipmapValue,bias)) ;
			textures.put(name, texture) ;
			
		}else if(type == TextureType.particle) {
			texture = new ParticleTexture(loader.getTextureLoader().loadTexture(name, GL11.GL_REPEAT, mipmapValue,bias)) ;
			textures.put(name, texture) ;
			
		}
		return texture ;
	}
	public void addTexture(String name , Texture texture) {
		textures.put(name, texture) ;
		
	}
	public Entity createEntity(EntityType type, Color color, String texture, Vec2 position, float rotation, Vec2 scale,
			float worldPosition) {
		Entity entity = null;
		switch (type) {

		case Player:
			entity = new Player(mesh, color,textures.get(texture).clone(), position, rotation, scale, worldPosition);
			break;
		case Brick:
			entity = new Brick(mesh, color,textures.get(texture).clone(), position, rotation, scale, worldPosition);
			break;
		case Ball :
			entity = new Ball(mesh, color,textures.get(texture).clone(), position, rotation, scale, worldPosition);
			break ;
		case Background :
			entity = new Background(mesh, color,textures.get(texture).clone(), position, rotation, scale, worldPosition);
			break ;
		default:
			
		}
		if(entity != null) {
			renderer.getEntityRenderer().addEntity(entity);
		}
		
		return entity ;
	}
	public void createPhysicsBody(Entity entity,BodyType type, float damping,Vec2[] vertices, float restitution, boolean fixedRotation) {
		Body body = physicsCreator.loadBody(entity,type, damping,fixedRotation) ;
		Shape shape = physicsCreator.loadShape(new Vec2(0,0),vertices) ;
		Fixture fixture = physicsCreator.loadFixture(shape, body, restitution) ;
		entity.setPhysics(body, shape, fixture);
	}
	public void createPhysicsBody(Entity entity,BodyType type, float damping,float radius, float restitution,boolean fixedRotation) {
		Body body = physicsCreator.loadBody(entity,type, damping,fixedRotation) ;
		Shape shape = physicsCreator.loadShape(new Vec2(0,0),radius) ;
		Fixture fixture = physicsCreator.loadFixture(shape, body, restitution) ;
		entity.setPhysics(body, shape, fixture);
	}
	public Particle createParticle(String textureName, Vec2 position,float rotation , Vec2 scale,float worldPosition, float life,int repeat) {
		Particle particle = new Particle(mesh, textures.get(textureName).clone(), position, rotation, scale, worldPosition,life, repeat) ;
		renderer.getParticleRenderer().addParticle(particle);
		return particle ;
	}


	public Text createText(String string, double maxLineSize, Vec2 position,float rotate, Vec2 scale, boolean isCentered ) {
		Text text = new Text(string, maxLineSize, new Color(1,1,1,1), position, rotate, scale, isCentered) ; 
		MeshData mesh = font.createText(text) ;
		text.setMeshData(mesh);
		renderer.getGuiRenderer().addText(text, fontTexture);
		return text ;
	}
	public GUI createGUI(Texture texture,Color color, Vec2 position, float rotation, Vec2 scale, float worldPosition ) {
		GUI gui =  new GUIArea(texture,color, position, rotation, scale,worldPosition) ;
		renderer.getGuiRenderer().addGUI(gui);
		return gui ;
	}
	
	public Button createButton(String string, double maxLineSize,boolean isCentered, String guitexture,
			Vec2 position, float rotation, Vec2 scale, float worldPosition, float padding) {
		
		Text text = createText(string, maxLineSize, position, rotation, scale, isCentered) ; 
		
		Button button = new Button(text,padding,textures.get(guitexture),new Color(0,1,1,1),position.clone(), rotation, scale.clone(),worldPosition);
		renderer.getGuiRenderer().addGUIButton(button, fontTexture);
		
		return button ;
	}
	public Light createLight(Vec2 position, Color color) {
		Light light = new Light(position, color) ;
		renderer.getLightRenderer().addLight(light);
		return light ;
	}
	
	public Loader getLoader() {
		return loader;
	}

	public Renderer getRenderer() {
		return renderer;
	}
	public PhysicsBodyCreator getPhysicsCreator() {
		return physicsCreator;
	}
	public static MeshData getMesh() {
		return mesh;
	}
	public FontType getFont() {
		return font;
	}
	
	

}
