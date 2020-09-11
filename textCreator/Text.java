package textCreator;

import java.util.ArrayList;
import java.util.List;

import org.jbox2d.common.Vec2;

import dataStructure.Color;
import dataStructure.MeshData;
import dataStructure.Texture;
import guis.GUI;
import utils.Matrix4;

public class Text{

	private MeshData mesh ;
	private String textString ;
	private Vec2 position ;
	private float rotation ;
	private Vec2 scale ;
	private double maxLineSize ;
	private boolean isCentered ;
	private int lineSize ;
	private List<Line> lines ;
	
	private Color color ;
	
	private Matrix4 transformationMatrix ;

	public Text(String textString,double maxLineSize,Color color, Vec2 position, float rotation, Vec2 scale,boolean isCentered) {
		// TODO Auto-generated constructor stub
		this.textString = textString ;
		this.maxLineSize = maxLineSize ;
		this.isCentered = isCentered ;
		this.position = position ;
		this.rotation = rotation;
		this.scale = scale ;
		lines = new ArrayList<Line>();
		lineSize = 0 ;
		mesh = null ;
		this.color = color ;
		transformationMatrix = Matrix4.createTransformationMatrix(position, rotation, scale) ;
	}

	public void isIntersect(boolean isIntersect) {
		// TODO Auto-generated method stub
		
	}
	public void setLineSize(int lineSize) {
		this.lineSize = lineSize;
	}
	public int getLineSize() {
		return lineSize ;
	}
	public void setMeshData(MeshData mesh) {
		this.mesh = mesh ;
	}
	
	public String getTextString() {
		return textString;
	}


	public void setTextString(String textString) {
		this.textString = textString;
	}


	public Vec2 getPosition() {
		return position;
	}


	public void setPosition(Vec2 position) {
		this.position = position;
	}
	public void setPosition(float x , float y) {
		this.position.set(x,y);
	}

	public Vec2 getScale() {
		return scale;
	}


	public void setScale(Vec2 scale) {
		this.scale = scale;
	}
	public void setScale(float x , float y) {
		this.scale.set(x, y);
	}

	public MeshData getMesh() {
		return mesh;
	}


	public double getMaxLineSize() {
		return maxLineSize;
	}


	public boolean isCentered() {
		return isCentered;
	}

	public Color getColor() {
		return color;
	}


	public List<Line> getLines() {
		return lines;
	}

	public void setLines(List<Line> lines) {
		this.lines = lines;
	}

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	public void setMesh(MeshData mesh) {
		this.mesh = mesh;
	}

	public Matrix4 getTransformationMatrix() {
		transformationMatrix = Matrix4.updateTransformationMatrix(transformationMatrix, position, rotation, scale);
		return transformationMatrix;
	}




	
	
	
	
}
