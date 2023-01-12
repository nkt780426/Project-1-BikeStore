package help;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageAccept {
	public static Image resize(Image originalImage, int targetWidth, int targetHeight) {
		return originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
	}

	// chuyen hinh anh thanh mang cac byte
	public static byte[] toByteArray(Image img, String type) throws IOException {
		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bimage.createGraphics();
		g.drawImage(img, 0, 0, null);
		g.dispose();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bimage, type, baos);
		return baos.toByteArray();
	}

	// chuyen mang cac byte ve doi tuong image
	public static Image createImageFromByteArray(byte[] data, String type) throws IOException {
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		BufferedImage bImage2 = ImageIO.read(bis);
		return bImage2.getScaledInstance(bImage2.getWidth(), bImage2.getHeight(), Image.SCALE_SMOOTH);
	}
}
