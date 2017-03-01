package com.lsy.javaweb;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ValidateColorServlet extends HttpServlet {

	public static final String CHECK_CODE_KEY="CHECK_CODE_KEY";
	
	private static final long serialVersionUID = 1L;
	//������֤ͼƬ�Ŀ��, �߶�, ��֤��ĸ���
	private int width = 152;
	private int height = 40;
	private int codeCount = 4;
	
	//��֤������ĸ߶�
	private int fontHeight = 4;
	
	//��֤���еĵ����ַ�����. ������֤���еĵ����ַ�λ����֤��ͼ�����Ͻǵ� (codeX, codeY) λ�ô�
	private int codeX = 0;
	private int codeY = 0;
	
	//��֤������Щ�ַ����
	char [] codeSequence = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz23456789".toCharArray();
	
	//��ʼ����֤��ͼ������
	public void init(){
		fontHeight = height - 2;
		codeX = width / (codeCount + 2);
		codeY = height - 4;
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//����һ������Ϊ BufferedImage.TYPE_INT_BGR ���͵�ͼ�񻺴�
		BufferedImage buffImg = null;
		buffImg = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
	
		//�� buffImg �д���һ�� Graphics2D ͼ��
		Graphics2D graphics = null;
		graphics = buffImg.createGraphics();
		
		//����һ����ɫ, ʹ Graphics2D ����ĺ���ͼ��ʹ�������ɫ
		graphics.setColor(Color.WHITE);
		
		//���һ��ָ���ľ���: x - Ҫ�����ε� x ����; y - Ҫ�����ε� y ����; width - Ҫ�����εĿ��; height - Ҫ�����εĸ߶�
		graphics.fillRect(0, 0, width, height);
		
		//����һ�� Font ����: name - ��������; style - Font ����ʽ����; size - Font �ĵ��С
		Font font = null;
		font = new Font("", Font.BOLD, fontHeight);
		//ʹ Graphics2D ����ĺ���ͼ��ʹ�ô�����
		graphics.setFont(font);
		
		graphics.setColor(Color.BLACK);
		
		//����ָ�����εı߿�, ���Ƴ��ľ��ν��ȹ�����һ��Ҳ��һ������
		graphics.drawRect(0, 0, width - 1, height - 1);
		
		//������� 15 ��������, ʹͼ���е���֤�벻�ױ���������̽�⵽
		Random random = null;
		random = new Random();
		graphics.setColor(Color.GREEN);
		for(int i = 0; i < 15; i++){
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int x1 = random.nextInt(20);
			int y1 = random.nextInt(20);
			graphics.drawLine(x, y, x + x1, y + y1);
		}
		
		//���� randomCode ����, ���ڱ��������������֤��, �Ա��û���¼�������֤
		StringBuffer randomCode;
		randomCode = new StringBuffer();
		
		for(int i = 0; i < codeCount; i++){
			//�õ������������֤������
			String strRand = null;
			strRand = String.valueOf(codeSequence[random.nextInt(36)]);
			//把正产生的随机字符放入到StringBuffer中
			randomCode.append(strRand);
			
			graphics.setColor(Color.BLUE);
			graphics.drawString(strRand, (i + 1)* codeX, codeY);
		}
		//再把存有所有随机字符的StringBuffer对应的字符串放入到HttpSession中
		request.getSession().setAttribute(CHECK_CODE_KEY, randomCode.toString());
		//��ֹͼ�񻺴�
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		
		//��ͼ��������������
		ServletOutputStream sos = null;
		sos = response.getOutputStream();
		ImageIO.write(buffImg, "jpeg", sos); 
		sos.close();
	}
}
