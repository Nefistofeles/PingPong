package dataStructure;

public class MeshData {

	private int vaoID ;
	private int vertexCount ;
	private int[] vboIDs ;
			
	public MeshData(int vaoID,int[] vboIDs,int vertexCount) {
		this.vaoID = vaoID;
		this.vertexCount = vertexCount;
		this.vboIDs = vboIDs ;
	}

	public int getVaoID() {
		return vaoID;
	}

	public int getVertexCount() {
		return vertexCount;
	}

	public void setVertexCount(int vertexCount) {
		this.vertexCount = vertexCount;
	}


	public void setVaoID(int vaoID) {
		this.vaoID = vaoID;
	}

	public int[] getVboIDs() {
		return vboIDs;
	}
	
	
	
	
	
	
	
	
}
