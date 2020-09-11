package animation;

import particleSystem.Particle;
import utils.DisplayManager;

public class Animation {
	
	private float time ;
	private int repeat ;
	
	public Animation() {
		time = 0 ;
		repeat = 0 ;
	}
	

	public void workParticle(Particle particle, float frameRate){
		time += DisplayManager.getFrameTime() ;
		
		
		if(time > frameRate * particle.getLife()) {
			increaseParticleOffset(particle);
			time = 0 ;
			repeat++ ;
			if(repeat > particle.getRepeat() * particle.getTexture().getTextureSize()) {
				particle.setDead(true);
		/*		if(particle.getTexture().getIndexX() == particle.getTexture().getTextureSize()) {
					particle.setDead(true);
				}*/
			}
			
		}
	}
	private void increaseParticleOffset(Particle particle) {
		int x = particle.getIndex1() + 1 < particle.getTexture().getTextureSize() ? particle.getIndex1() + 1 : particle.getIndex1();
		particle.setIndex1(x) ;
		int xx = x + 1 < particle.getTexture().getTextureSize() ? x + 1 : x ;
		particle.setIndex2(xx) ; 
		
	}
	private void clearAnimation() {
		time = 0 ;
	}
}
