package textCreator;

import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL13;

import dataStructure.Texture;
import drawer.Draw;
import guis.GUI;
import main.IRenderer;
import renderer.EnableOpenGL;
import renderer.Renderer;

public class TextRenderer {

	private TextShader shader ;
	private Draw draw ;
	
	public TextRenderer(Draw draw) {
		this.draw = draw ;
		shader = new TextShader();
		shader.start();
		shader.loadProjectionMatrix(Renderer.projectionMatrix);
		shader.stop();
	}

	public void render(Map<Texture, List<Text>> texts) {
		EnableOpenGL.blendFunc(true);
		//EnableOpenGL.disableDepthTestWithMask(true);
		EnableOpenGL.enableDepthTest(false);
		shader.start();

		for(Texture t : texts.keySet()) {
			List<Text> tlist = texts.get(t) ;
			draw.activateTexture(t);
			
			for(Text text1 : tlist) {
				Text text = text1 ;
				draw.activate(text.getMesh(), 2);
				shader.loadTransformation(text.getTransformationMatrix());
				shader.loadColor(text.getColor());
				draw.renderTriangleUnoptimize(text.getMesh().getVertexCount());
				draw.destroy(2);
			}
		}

		shader.stop();
		EnableOpenGL.blendFunc(false);
	//	EnableOpenGL.disableDepthTestWithMask(false);
		EnableOpenGL.enableDepthTest(true);
	}

	public void cleanUp() {
		shader.cleanUp();
		
	}

	

}
