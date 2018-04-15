package com.apra.graphics;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class Main {

	public static void main(String[] args) throws IOException
	{
		try {
			Files.createDirectory(Paths.get("out"));
		} catch (FileAlreadyExistsException e) {
			// no harm in ignoring this one
		}
		for(int i=0;i<10;i++)
			makeOne();
	}


	private static void makeOne() throws IOException {
		int width=640;
		int height=480;

		Generator gen = createGen();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        WritableRaster pixels = (WritableRaster) image.getData();
		for(int i=0;i<width;i++)
		{
			for(int j=0;j<height;j++)
			{
				pixels.setPixel(i, j, gen.getPixelValue(i, j));
			}
		}
		image.setData(pixels);
		String fileName= "out/"+System.currentTimeMillis()+".jpg";
		ImageIO.write(image, "jpg", new File(fileName));
		System.out.println(fileName+" done");
	}
	

	private static Generator createGen() {
		//return new RandomFunctionGenerator();
		return new JuliaFunc();
	}
}
