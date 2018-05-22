package com.hsu.netchat.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ValiImg")
public class ValiImg extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Random ran = null;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 设置页面不缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		int height = 26;
		int width = 80;
		//在内存中开辟一个图片空间
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//创建一个画布对象
		Graphics2D g = (Graphics2D) image.getGraphics();
		//画一个矩形
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, width, height);
		//画矩形边框
		g.setColor(Color.gray);
		g.drawRect(0, 0, width-1, height-1);
		//画干扰线
		/*for(int i =0;i<5;i++){
			g.setColor(Color.red);
			g.drawLine(ranNum(0,width), ranNum(0,height), ranNum(0,width), ranNum(0,height));
		}*/
			
		//设置水平方向上的偏移量
		int xMove = 8;
		//设置竖直方向上的偏移量
		int yMove = 20;    
		String para = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String valistr = "";
		
		g.setFont(new Font("楷体",Font.BOLD,25));
		for(int i = 0; i< 4; i++){
			//给验证码字符设置颜色
			g.setColor(Color.black);
			//设置旋转角度
			double ran = ranNum(-45,45);
			g.rotate(ran/180*Math.PI, xMove+(i*18), yMove);
			//添加验证字符
			String s = para.charAt(ranNum(0,para.length()-1))+"";
			valistr += s;
			g.drawString(s, xMove+(i*18), yMove);
			//把角度旋转到初始位置
			g.rotate(-(ran/180*Math.PI), xMove+(i*18), yMove);
		}

		// 将随机产生的验证码转换为小写，并保存在session 中
		valistr = valistr.toLowerCase();
		request.getSession().setAttribute("valistr", valistr);
		//将验证码输出到客户端
		ImageIO.write(image, "jpg", response.getOutputStream());
	}

	/**
	 * 产生干扰线的随机位置
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	private int ranNum(int start ,int end){
		ran = new Random();
		return ran.nextInt(end -start)+start;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
