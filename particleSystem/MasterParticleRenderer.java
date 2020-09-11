package particleSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jbox2d.common.Vec2;

import dataStructure.Texture;
import drawer.Draw;
import loader.Loader;
import main.Creator;
import main.IRenderer;

public class MasterParticleRenderer implements IRenderer {

	private ParticleRenderer particleRenderer;
	private Map<Texture, List<Particle>> particles;
	

	public MasterParticleRenderer(Draw draw, Loader loader) {
		particleRenderer = new ParticleRenderer(draw, loader);
		particles = new HashMap<Texture, List<Particle>>();
	}

	public void addParticle(Particle particle) {
		Texture texture = particle.getTexture();
		List<Particle> particlelist = particles.get(texture);
		if (particlelist == null) {
			particlelist = new ArrayList<Particle>();
			particlelist.add(particle);
			particles.put(texture, particlelist);
		} else {
			particlelist.add(particle);
		}
	}

	public void destroyParticle(Particle particle) {
		Texture texture = particle.getTexture();
		List<Particle> particlelist = particles.get(texture) ;
		if(particlelist != null) {
			particlelist.remove(particlelist.lastIndexOf(particle)) ;
			
			
		}
		if (particles.get(texture).isEmpty()) {
			particles.remove(texture);

		}
		

	}

	@Override
	public void init() {
		particleRenderer.init();

	}

	@Override
	public void update() {
		particleRenderer.update();
		
		Iterator<Texture> iter1 = particles.keySet().iterator() ;
		while(iter1.hasNext()) {
			Texture texture = iter1.next() ;
			List<Particle> particlelist = particles.get(texture);
			Iterator<Particle> iter = particlelist.iterator() ;
			while(iter.hasNext()) {
				Particle p = iter.next() ;
				if(p.isDead()) {
					iter.remove();
				}else {
					p.update();
					
				}
			}
			if(particlelist.isEmpty()) {
				iter1.remove(); 
			}
		}


	}

	@Override
	public void render() {
		particleRenderer.render(particles);

	}

	@Override
	public void cleanUp() {
		particleRenderer.cleanUp();

	}
	public void clear() {
		particles.clear();
	}

}
