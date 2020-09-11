package guis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import dataStructure.Texture;
import drawer.Draw;
import gameEntity.MouseOrtho;
import loader.Loader;
import main.IRenderer;
import textCreator.Text;
import textCreator.TextRenderer;
import textCreator.button.Button;

public class MasterGUIRenderer implements IRenderer{

	private GUIRenderer guiRenderer ;
	private TextRenderer textRenderer ;
	
	private Map<Texture, List<GUI>> guis ;
	private Map<Texture, List<Text>> texts;
	
	private MouseOrtho mouse ;
	private GUIIntersection intersection ;
	
	public MasterGUIRenderer(Loader loader, Draw draw, MouseOrtho mouse) {
		this.mouse = mouse ;
		intersection = new GUIIntersection();
		guiRenderer = new GUIRenderer(loader, draw);
		textRenderer = new TextRenderer(draw) ;
		guis = new HashMap<Texture, List<GUI>>();
		texts = new HashMap<Texture, List<Text>>();
		
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		for(Texture texture : guis.keySet()) {
			List<GUI> guilist = guis.get(texture) ;
			for(GUI g : guilist) {
				if(g instanceof Button) {
					Button b = (Button)g ;
					if(intersection.isIntersect(g, mouse.getGUIMasterPosition())){
						b.isIntersect(true);
					}else {
						b.isIntersect(false);
					}
				}
				
			}
		}
		
	}
	public void addGUI(GUI gui) {
		List<GUI> guilist = guis.get(gui.getTexture());
		if (guilist == null) {
			guilist = new ArrayList<GUI>();
			guilist.add(gui);
			guis.put(gui.getTexture(), guilist);
		} else {
			guilist.add(gui);
		}
	}
	public void destroy(GUI guit) {
		Iterator<Texture> iter1 = guis.keySet().iterator() ;
		while(iter1.hasNext()) {
			Texture texture = iter1.next() ;
			List<GUI> guilist = guis.get(texture);
			Iterator<GUI> iter = guilist.iterator() ;
			while(iter.hasNext()) {
				GUI t = (GUI) iter.next() ;
				if(t.equals(guit))
					iter.remove();
			}
			if(guilist.isEmpty()) {
				iter1.remove();
			}
			
		}
	}
	public void destroyGUIList(List<GUI> guit) {
		Iterator<Texture> iter1 = guis.keySet().iterator() ;
		while(iter1.hasNext()) {
			Texture texture = iter1.next() ;
			List<GUI> guilist = guis.get(texture);
			Iterator<GUI> iter = guilist.iterator() ;
			while(iter.hasNext()) {
				GUI t = (GUI) iter.next() ;
				if(guit.contains(t))
					iter.remove();
			}
			if(guilist.isEmpty()) {
				iter1.remove();
			}
			
		}
	}
	public void addGUIButton(Button button, Texture fontTexture) {
		addGUI(button);
		addText(button.getText(), fontTexture);
	}
	public void destroyGUIButton(Button button) {
		destroy(button.getText());
		destroy(button);
	}
	public void addText(Text text, Texture texture) {
		List<Text> textlist = texts.get(texture);
		if (textlist == null) {
			textlist = new ArrayList<Text>();
			textlist.add(text);
			texts.put(texture, textlist);
		} else {
			textlist.add(text);
		}
	}

	public void destroy(Text textt) {
		Iterator<Texture> iter1 = texts.keySet().iterator() ;
		while(iter1.hasNext()) {
			Texture texture = iter1.next() ;
			List<Text> textlist = texts.get(texture);
			Iterator<Text> iter = textlist.iterator() ;
			while(iter.hasNext()) {
				Text t = (Text) iter.next() ;
				if(t.equals(textt))
					iter.remove();
			}
			if(textlist.isEmpty()) {
				iter1.remove();
			}
			
		}
	}

	public void destroyTextList(List<Text> textt) {
		Iterator<Texture> iter1 = texts.keySet().iterator() ;
		while(iter1.hasNext()) {
			Texture texture = iter1.next() ;
			List<Text> textlist = texts.get(texture);
			Iterator<Text> iter = textlist.iterator() ;
			while(iter.hasNext()) {
				Text t = (Text) iter.next() ;
				if(textt.contains(t))
					iter.remove();
			}
			if(textlist.isEmpty()) {
				iter1.remove();
			}
			
		}
	}

	
	@Override
	public void render() {
		guiRenderer.render(guis);
		textRenderer.render(texts);
	}

	@Override
	public void cleanUp() {
		guis.clear();
		texts.clear();
		guiRenderer.cleanUp();
		textRenderer.cleanUp();
		
	}

	public Map<Texture, List<GUI>> getGuis() {
		return guis;
	}

	public Map<Texture, List<Text>> getTexts() {
		return texts;
	}
	

}
