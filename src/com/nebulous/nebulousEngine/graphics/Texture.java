package com.nebulous.nebulousEngine.graphics;

import static  org.lwjgl.opengl.GL11.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;

import com.nebulous.nebulousEngine.core.utils.Console;
import com.nebulous.nebulousEngine.graphics.data.TextureData;

public class Texture {

	private static HashMap<String, TextureData> loadedTextures = new HashMap<String, TextureData>();
	private String filename;
	private TextureData data;
	private int width = 1, height = 1;
	
	public Texture(String filename){
		TextureData check = loadedTextures.get(filename);
		this.filename = filename;
		if(check != null){
			data = check;
			data.increaseIndex();
		} else {
			data = new TextureData(loadTexture(filename));
			loadTexture(filename);
			loadedTextures.put(filename, data);
		}
	}
	
	@Override
	protected void finalize(){
		try {
			super.finalize();
			loadedTextures.remove(filename);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public int getId() {
		return data.getId();
	}
	
	public void bind(){
		glBindTexture(GL_TEXTURE_2D, data.getId());
	}
	
	private static int loadTexture(String filename){
		boolean successful = true;
		BufferedImage image;
		try {
			try{
				image = ImageIO.read(Texture.class.getResource(filename));
			} catch(Exception e){
				Console.printErr("UNABLE TO READ FILE: '" + filename + "'");
				Console.println("Texture - Error reading buffered image, replaced with 'nullTexture.png'.");
				image = ImageIO.read(Texture.class.getResource("/textures/nullTexture.png"));
				successful = false;
			}

			int width = image.getWidth();
			int height = image.getHeight();

			int[] pixels = new int[width * height * 4];
			pixels = image.getRGB(0, 0, width, height, null, 0, width);

			ByteBuffer pixelBuffer = BufferUtils.createByteBuffer(width * height * 4);
			
			boolean hasAlpha = image.getColorModel().hasAlpha();

			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					int pixel = pixels[y * width + x];
					pixelBuffer.put((byte) ((pixel >> 16) & 0xFF)); // RED
					pixelBuffer.put((byte) ((pixel >> 8) & 0xFF)); // GREEN
					pixelBuffer.put((byte) ((pixel >> 0) & 0xFF)); // BLUE
					if(hasAlpha) pixelBuffer.put((byte) ((pixel >> 24) & 0xFF)); // ALPHA
					else pixelBuffer.put((byte)(0xFF));
				}
			}

			pixelBuffer.flip();

			int id = glGenTextures();
			glBindTexture(GL_TEXTURE_2D, id);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST); //GL_LINEAR
			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST); //GL_LINEAR

			glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, pixelBuffer);

			if(successful)
			Console.println("Texture successfully created: " + filename);
			
			return id;

		} catch (IOException e) {
			Console.println("Texture - Error reading buffered image");
			e.printStackTrace();
		}
		
		return 0;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
