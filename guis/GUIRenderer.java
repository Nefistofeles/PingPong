package guis;

import java.util.List;
import java.util.Map;

import dataStructure.MeshData;
import dataStructure.Texture;
import drawer.Draw;
import loader.Loader;
import renderer.EnableOpenGL;
import renderer.Renderer;
import utils.Coordinates;

public class GUIRenderer {

	private GUIShader shader ;
	private Draw draw ;
	private final MeshData mesh ;
	
	public GUIRenderer(Loader loader ,Draw draw) {
		mesh = loader.getModelLoader().loadMesh(Coordinates.triangleStripVertex) ;
		this.draw = draw ;
		shader = new GUIShader();
		shader.start();
		shader.loadProjectionMatrix(Renderer.projectionMatrix);
		shader.stop();
	}
	
	public void render(Map<Texture, List<GUI>> guis) {
		shader.start();
		
		for(Texture texture : guis.keySet()) {
			List<GUI> guilist = guis.get(texture) ;
			draw.activateTexture(texture);
			for(GUI g : guilist) {
				draw.activate(mesh, 1);
				shader.loadTransformationMatrix(g.getTransformationMatrix());
				shader.loadWorldPosition(g.getWorldPosition());
				shader.loadColor(g.getColor());
				draw.renderTriangleStrip(mesh.getVertexCount());
				draw.destroy(2);
			}
		}
		
		shader.stop();
		
	}
	
	
	public void cleanUp() {
		shader.cleanUp();	
	}
}
