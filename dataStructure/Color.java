package dataStructure;

public class Color {

	public float r,g,b,alpha;
	public boolean useColor ;

	public Color(float r, float g, float b,float alpha) {
		useColor = false ;
		this.r = r;
		this.g = g;
		this.b = b;
		this.alpha = alpha ;
	}

	public void setColor(float r, float g, float b,float alpha) {
		this.r = r ;
		this.g = g ;
		this.b = b ;
		this.alpha = alpha ;
	}
	
	@Override
	public String toString() {
		String s = "color : " + r +" "+ g+" " + b ;
		return s ;
		
	}

	
	
	
	
	
}
