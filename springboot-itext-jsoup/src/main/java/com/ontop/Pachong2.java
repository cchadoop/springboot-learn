package com.ontop;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
 * 爬虫：腾讯
 * 文件下载
 */
public class Pachong2 {

	public static void main(String[] args) {
		Connection connect = Jsoup.connect("http://www.qq.com");
		try {
			Document document = connect.get();
			Elements imgs = document.getElementsByTag("img");
			System.out.println("检测到图片URL");
			System.out.println("开始下载");
			for (Element element : imgs) {
				// abs: 代表绝对路径
				String imgSrc = element.attr("abs:src");
				System.out.println(imgSrc);

				downImages("/Users/psy/Desktop/temp", imgSrc);
			}
			System.out.println("下载完成");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void downImages(String filePath, String imgUrl) {
		File dir = new File(filePath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		String fileName = imgUrl.substring(imgUrl.lastIndexOf("/") + 1, imgUrl.length());
		try {
			// 文件名里面可能有中文或者空格，所以这里要进行处理。但空格又会被URLEncoder转义为加号
			String urlTail = URLEncoder.encode(fileName, "UTF-8");
			// 因此要将加号转化为UTF-8格式的%20
			imgUrl = imgUrl.substring(0, imgUrl.lastIndexOf('/') + 1) + urlTail.replaceAll("\\+", "\\%20");
		} catch (Exception e) {
			e.printStackTrace();
		}
		File file = new File(filePath + File.separator + fileName);
		try {
			//获取图片URL
			URL url = new URL(imgUrl);
			// 获得连接
			URLConnection connection = url.openConnection();
			//设置10s响应时间
			connection.setConnectTimeout(10 * 1000);
			//获得输出流
			InputStream in = connection.getInputStream();
			//构建缓冲区
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
			byte[] buf = new byte[1024];
			int size;
			//写入到文件
			while (-1 != (size = in.read(buf))) {
				out.write(buf, 0, size);
			}
			out.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
