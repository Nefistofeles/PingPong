package loader;

public class Loader {

	private ModelLoader modelLoader ;
	private TextureLoader textureLoader ;
	
	public Loader() {
		modelLoader = new ModelLoader();
		textureLoader = new TextureLoader();
	}

	public ModelLoader getModelLoader() {
		return modelLoader;
	}

	public TextureLoader getTextureLoader() {
		return textureLoader;
	}
	
	public void cleanUp() {
		modelLoader.cleanUp();
		textureLoader.cleanUp();
	}
	
}
