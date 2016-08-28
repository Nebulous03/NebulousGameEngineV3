package com.nebulous.nebulousEngine.graphics.shaders;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL32.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import com.nebulous.nebulousEngine.core.RenderEngine;
import com.nebulous.nebulousEngine.core.logic.math.Matrix4f;
import com.nebulous.nebulousEngine.graphics.Material;
import com.nebulous.nebulousEngine.core.logic.math.Vector3f;
import com.nebulous.nebulousEngine.core.utils.BufferUtilities;
import com.nebulous.nebulousEngine.core.utils.Console;
import com.nebulous.nebulousEngine.graphics.Transform;

public class Shader {
	
	private int program;
	private HashMap<String, Integer> uniforms;

	public Shader() {
		program = glCreateProgram();
		uniforms = new HashMap<String, Integer>();
		if(program == 0){
			Console.printErr("ERROR CREATING SHADER PROGRAM, Could not find valid memory location. Returned zero.");
			new Exception().printStackTrace();
			System.exit(1);
		}
	}
	
	public void bind(){
		glUseProgram(program);
	}

	public void addVertexShaderFromFile(String text){
		addProgram(loadShader(text), GL_VERTEX_SHADER);
	}
	
	public void addFragmentShaderFromFile(String text){
		addProgram(loadShader(text), GL_FRAGMENT_SHADER);
	}

	public void addGeometryShaderFromFile(String text){
		addProgram(loadShader(text), GL_GEOMETRY_SHADER);
	}
	
	public void addVertexShader(String text){
		addProgram(text, GL_VERTEX_SHADER);
	}
	
	public void addFragmentShader(String text){
		addProgram(text, GL_FRAGMENT_SHADER);
	}

	public void addGeometryShader(String text){
		addProgram(text, GL_GEOMETRY_SHADER);
	}

    public void setAttribLocation(String attribName, int location){
        glBindAttribLocation(program, location, attribName);
    }
	
	public static String loadShader(String filename){
		StringBuilder source = new StringBuilder();
		BufferedReader reader = null;
		final String INCLUDE_DIRECTIVE = "#include";
		
		try{
			InputStream input = Shader.class.getResourceAsStream("/shaders/" + filename);
			reader = new BufferedReader(new InputStreamReader(input));
			String line;
			while((line = reader.readLine()) != null)
				if(line.startsWith(INCLUDE_DIRECTIVE)){
					source.append(loadShader(line.substring(INCLUDE_DIRECTIVE.length() + 2, line.length() -1)));
				} else {
					source.append(line).append("\n");
				}
			reader.close();
		} catch (Exception e){
			Console.printErr("ERROR LOADING SHADER : " + filename);
			e.printStackTrace();
		}
		
		return source.toString();
	}
	
	public void updateUniforms(Transform transform, Material material, RenderEngine engine){
		
	}
	
	public void addAllAttributes(String shaderText){
		final String ATTRIBUTE = "attribute";
		int attribStartLocation = shaderText.indexOf(ATTRIBUTE);
		int attribNum = 0;
		while(attribStartLocation != -1){
			int begin = attribStartLocation + ATTRIBUTE.length() + 1;
			int end = shaderText.indexOf(";", begin);
			String attribute = shaderText.substring(begin, end);
			String attribName = attribute.substring(attribute.indexOf(" ") + 1, attribute.length());
			
			setAttribLocation(attribName, attribNum);
			
			attribStartLocation = shaderText.indexOf(ATTRIBUTE, attribStartLocation + ATTRIBUTE.length());
			attribNum++;
		}
	}
	
	public void addAllUniforms(String shaderText){
		final String UNIFORM = "uniform";
		int uniformStartLocation = shaderText.indexOf(UNIFORM);
		while(uniformStartLocation != -1){
			int begin = uniformStartLocation + UNIFORM.length() + 1;
			int end = shaderText.indexOf(";", begin);
			String uniform = shaderText.substring(begin, end);
			String uniformName = uniform.substring(uniform.indexOf(" ") + 1, uniform.length());
			uniformStartLocation = shaderText.indexOf(UNIFORM, uniformStartLocation + UNIFORM.length());
			addUniform(uniformName);
		}
	}
	
	public void addUniform(String uniform){
		int uniformLocation = glGetUniformLocation(program, uniform);
		if(uniformLocation == 0xFFFFFFFF){
			Console.printErr("ERROR ADDING UNIFORM, Could not find uniform '" + uniform + "'. Returned zero.");
			new Exception().printStackTrace();
			System.exit(1);
		}
		uniforms.put(uniform, uniformLocation);
	}
	
	private HashMap<String, ArrayList<String>> findUniformStructs(String shaderText){
		final String STRUCT = "uniform";
		int uniformStartLocation = shaderText.indexOf(STRUCT);
		while(uniformStartLocation != -1){
			int begin = uniformStartLocation + STRUCT.length() + 1;
			int end = shaderText.indexOf(";", begin);
			String uniform = shaderText.substring(begin, end);
			String uniformName = uniform.substring(uniform.indexOf(" ") + 1, uniform.length());
			uniformStartLocation = shaderText.indexOf(STRUCT, uniformStartLocation + STRUCT.length());
			addUniform(uniformName);
		}
		return null;
	}
	
	public void compile(){
		glLinkProgram(program);
		if(glGetProgrami(program, GL_LINK_STATUS) == 0){
			Console.printErr("ERROR LOADING SHADER : " + program);
			Console.printErr("ERROR CREATING SHADER PROGRAM, Program could not be linked. Returned zero.");
			System.err.println(glGetProgramInfoLog(program, 1024));
			new Exception().printStackTrace();
			System.exit(1);
		}
		glValidateProgram(program);
		if(glGetProgrami(program, GL_VALIDATE_STATUS) == 0){
			Console.printErr("ERROR LOADING SHADER : " + program);
			Console.printErr("ERROR CREATING SHADER PROGRAM, Program could not be validated. Returned zero.");
			System.err.println(glGetProgramInfoLog(program, 1024));
			new Exception().printStackTrace();
			System.exit(1);
		}
	}
	
	private void addProgram(String text, int type){
		int shader = glCreateShader(type);
		if(shader == 0){
			Console.printErr("ERROR LOADING SHADER : " + program);
			Console.printErr("ERROR CREATING SHADER PROGRAM, Could not find valid memory location. Returned zero.");
			new Exception().printStackTrace();
			System.exit(1);
		}
		glShaderSource(shader, text);
		glCompileShader(shader);
		if(glGetShaderi(shader, GL_COMPILE_STATUS) == 0){
			Console.printErr("ERROR LOADING SHADER : " + program);
			Console.printErr("ERROR CREATING SHADER PROGRAM, Shader could not be compiled. Returned zero.");
			System.err.println(glGetShaderInfoLog(shader, 1024));
			new Exception().printStackTrace();
			System.exit(1);
		}
		glAttachShader(program, shader);
	}
	
	public void setUniformi(String uniform, int value){
		glUniform1i(uniforms.get(uniform), value);
	}
	
	public void setUniformf(String uniform, float value){
		glUniform1f(uniforms.get(uniform), value);
	}
	
	public void setUniform(String uniform, Vector3f value){
		glUniform3f(uniforms.get(uniform), value.getX(), value.getY(), value.getZ());
	}
	
	public void setUniform(String uniformName, Matrix4f value){
		glUniformMatrix4fv(uniforms.get(uniformName), true, BufferUtilities.createFlippedBuffer(value));
	}

}
