package com.nebulous.nebulousEngine.graphics.primatives;

import static  org.lwjgl.opengl.GL11.*;
import static  org.lwjgl.opengl.GL15.*;
import static  org.lwjgl.opengl.GL20.*;

import java.util.ArrayList;
import java.util.HashMap;

import com.nebulous.nebulousEngine.core.loader.IndexModel;
import com.nebulous.nebulousEngine.core.loader.OBJModel;
import com.nebulous.nebulousEngine.core.logic.math.Vector3f;
import com.nebulous.nebulousEngine.core.utils.BufferUtilities;
import com.nebulous.nebulousEngine.core.utils.Console;
import com.nebulous.nebulousEngine.graphics.data.MeshData;

import org.lwjgl.opengl.GL15;

public class Mesh {
	
	private static HashMap<String, MeshData> loadedModels = new HashMap<String, MeshData>();
	private String filename;
	private MeshData data;
	
	public Mesh(Vertex[] vertices, int[] indices){
		this(vertices, indices, false);
	}
	
	public Mesh(String filename){
		MeshData check = loadedModels.get(filename);
		this.filename = filename;
		if(check != null){
			data = check;
			data.increaseIndex();
		} else {
			data = new MeshData();
			loadMesh(filename);
			loadedModels.put(filename, data);
		}
	}

	public Mesh(Vertex[] vertices, int[] indices, boolean calcNormals) {
		data = new MeshData();
		filename = "";
		addVertices(vertices, indices, calcNormals);
	}
	
	@Override
	protected void finalize(){
		try {
			super.finalize();
			if(data.decreaseIndex() && filename.isEmpty())
				loadedModels.remove(filename);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	private void addVertices(Vertex[] vertices, int[] indices, boolean calcNormals){
		if(calcNormals)
			calcNormals(vertices, indices);
		data.setSize(indices.length);
		glBindBuffer(GL_ARRAY_BUFFER, data.getVbo());
		GL15.glBufferData(GL_ARRAY_BUFFER, BufferUtilities.createFlippedBuffer(vertices), GL_STATIC_DRAW);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, data.getIbo());
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, BufferUtilities.createFlippedBuffer(indices), GL_STATIC_DRAW);
	}
	
	private Mesh loadMesh(String filename){
		String[] splitArray = filename.split("\\.");
		String ext = splitArray[splitArray.length - 1];
		
		if(!ext.equals("obj")){
			Console.printErr("ERROR LOADING " + filename + "! " + ext + " is not in a usible format (OBJ).");
			new Exception().printStackTrace();
			System.exit(1);
		}
		
		OBJModel objModel = new OBJModel(filename);
		IndexModel model = objModel.toIndexModel();
		model.calcNormals();
	
		ArrayList<Vertex> verticies = new ArrayList<Vertex>();
		
		for(int i = 0; i < model.getPositions().size(); i++){
			verticies.add(new Vertex(model.getPositions().get(i), model.getTexCoords().get(i), model.getNormals().get(i)));
		}
		
		Vertex[] vertData = new Vertex[verticies.size()];
		verticies.toArray(vertData);
		Integer[] indexData = new Integer[model.getIndices().size()];
		model.getIndices().toArray(indexData);
		
		addVertices(vertData, BufferUtilities.toIntArray(indexData), false);
	
		return this;
	}
	
	public void draw(){
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);

        glBindBuffer(GL_ARRAY_BUFFER, data.getVbo());
        glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex.SIZE * 4, 0);
        glVertexAttribPointer(1, 2, GL_FLOAT, false, Vertex.SIZE * 4, 12);
        glVertexAttribPointer(2, 3, GL_FLOAT, false, Vertex.SIZE * 4, 20);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, data.getIbo());
        glDrawElements(GL_TRIANGLES, data.getSize(), GL_UNSIGNED_INT, 0);

        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(2);
	}

	private void calcNormals(Vertex[] vertices, int[] indices){
		for(int i =0; i < indices.length; i += 3){
			int i0 = indices[i];
			int i1 = indices[i + 1];
			int i2 = indices[i + 2];
			Vector3f v1 = vertices[i1].getPos().sub(vertices[i0].getPos());
			Vector3f v2 = vertices[i2].getPos().sub(vertices[i0].getPos());
			Vector3f normal = v1.cross(v2).normalized();
			vertices[i0].setNormal(vertices[i0].getNormal().add(normal));
			vertices[i1].setNormal(vertices[i1].getNormal().add(normal));
			vertices[i2].setNormal(vertices[i2].getNormal().add(normal));
		}
		for(int i =0; i < vertices.length; i++){
			vertices[i].setNormal(vertices[i].getNormal().normalized());
		}
	}

}
