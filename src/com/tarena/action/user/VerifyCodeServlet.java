package com.tarena.action.user;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VerifyCodeServlet
 */
public class VerifyCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private int width = 60;
	private int height = 20;
	private int codeCount = 4;
	private int x = 0;
	private int fontHeight;
	private int codeY;
	private char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
			'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
			'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	private void imgInit() throws ServletException {
		String strWidth = "80";
		String strHeight = "30";
		String strCodeCount = "4";
		try {
			if (strWidth != null && strWidth.length() != 0) {
				width = Integer.parseInt(strWidth);
			}
			if (strHeight != null && strHeight.length() != 0) {
				height = Integer.parseInt(strHeight);
			}
			if (strCodeCount != null && strCodeCount.length() != 0) {
				codeCount = Integer.parseInt(strCodeCount);
			}
		} catch (NumberFormatException e) {
		}
		x = width / (codeCount + 1);
		fontHeight = height - 2;
		codeY = height - 4;
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		imgInit();
		response.setContentType("image/jpeg");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		Random random = new Random();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		Font font = new Font("Fixedsys", Font.PLAIN, fontHeight);
		g.setFont(font);

		g.setColor(Color.BLACK);
		g.drawRect(0, 0, width - 1, height - 1);
		for (int i = 0; i < 10; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}
		StringBuffer randomCode = new StringBuffer();
		int red = 0, green = 0, blue = 0;
		for (int i = 0; i < codeCount; i++) {
			String strRand = String.valueOf(codeSequence[random.nextInt(36)]);
			red = random.nextInt(255);
			green = random.nextInt(255);
			blue = random.nextInt(255);
			g.setColor(new Color(red, green, blue));
			g.drawString(strRand, (i + 1) * x - 10, codeY);
			randomCode.append(strRand);
		}
		request.getSession().setAttribute("verifyCode", randomCode.toString());
		OutputStream os = response.getOutputStream();
		ImageIO.write(image, "jpeg", os);
//		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
//		encoder.encode(image);
		os.close();
	}
}
