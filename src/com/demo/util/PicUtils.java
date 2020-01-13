package com.demo.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * 制作图片缩略图
 */
public class PicUtils {

    /**
     * 强制压缩/放大图片到固定的大小
     * @param in 输入流
     * @param destFile 目录文件
     * @param w 新宽度
     * @param h 新高度
     */
	public static void resize(InputStream in, File destFile, int w, int h) throws IOException {
        resize(ImageIO.read(in), destFile, w, h);
    }
	
    private static void resize(BufferedImage image, File destFile, int w, int h) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        // 绘制缩小后的图
        bufferedImage.getGraphics().drawImage(image, 0, 0, w, h, null);
        // 输出到文件流
        FileOutputStream out = new FileOutputStream(destFile);
        ImageIO.write(bufferedImage, "jpg", out);
        out.close();
    }

	/**
	 * 按照固定的比例缩放图片
	 * @param t 比例
	 */
	public static void resize(InputStream in, File destFile, double t) throws IOException {
        BufferedImage image = ImageIO.read(in);
        int width = image.getWidth(null); // 得到源图宽
        int height = image.getHeight(null); // 得到源图长
        int w = (int) (width * t);
		int h = (int) (height * t);
		resize(image, destFile, w, h);
	}

	/**
	 * 以宽度为基准，等比例放缩图片
	 * @param w 新宽度
	 */
	public static void resizeByWidth(InputStream in, File destFile, int w) throws IOException {
        BufferedImage image = ImageIO.read(in);
        int width = image.getWidth(null); // 得到源图宽
        int height = image.getHeight(null); // 得到源图长
		resize(image, destFile, w, height * w / width);
	}

	/**
	 * 以高度为基准，等比例缩放图片
	 * @param h 新高度
	 */
	public static void resizeByHeight(InputStream in, File destFile, int h) throws IOException {
        BufferedImage image = ImageIO.read(in);
        int width = image.getWidth(null); // 得到源图宽
        int height = image.getHeight(null); // 得到源图长
		resize(image, destFile, width * h / height, h);
	}

	/**
	 * 按照最大高度限制，生成最大的等比例缩略图
	 * @param w 最大宽度
	 * @param h 最大高度
	 */
	public static void resizeFix(InputStream in, File destFile, int w, int h) throws IOException {
        BufferedImage image = ImageIO.read(in);
        int width = image.getWidth(null); // 得到源图宽
        int height = image.getHeight(null); // 得到源图长
		if (width / height > w / h) {
			resizeByWidth(in, destFile, w);
		} else {
			resizeByHeight(in, destFile, h);
		}
	}
}
