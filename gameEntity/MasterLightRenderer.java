package gameEntity;

import java.util.ArrayList;
import java.util.List;

import org.jbox2d.common.Vec2;

import drawer.Draw;
import loader.Loader;
import main.IRenderer;

public class MasterLightRenderer implements IRenderer {

	private LightRenderer renderer ;
	private List<Light> lights ;
	
	public MasterLightRenderer(Draw draw, Loader loader) {
		renderer = new LightRenderer(draw, loader) ;
		lights = new ArrayList<Light>();
	}

	@Override
	public void init() {
		
	}

	@Override
	public void update() {
		
		
	}
	public void addLight(Light light) {
		lights.add(light) ;
	}

	@Override
	public void render() {
		renderer.renderLight(lights);
		
	}
	public void clear() {
		lights.clear();
	}

	@Override
	public void cleanUp() {
		renderer.cleanUp();
		
	}

}
