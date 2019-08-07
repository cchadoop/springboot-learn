package com.ontop;

import java.io.FileOutputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootItextHtmlPdfApplicationTests {
	// 表头
	public static final String[] tableHeader = { "姓名", "性别", "年龄", "学院", "专业", "年级" };

	// 数据表字段数
	private static final int colNumber = 6;

	// 表格的设置
	private static final int spacing = 2;

	// 表格的设置
	private static final int padding = 2;

	@Value("${groovyGenPDFtoPath}")
	private String groovyGenPDFtoPath;

	@Test
	public void contextLoads() {
	}

	@Test
	public void exportPdfDocument() {
		// 创建文Pdf文挡50, 50, 50,50左右上下距离
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		try {
			// 使用PDFWriter进行写文件操作
			PdfWriter.getInstance(document, new FileOutputStream(groovyGenPDFtoPath));
			document.open();
			// 中文字体
			BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			Font fontChinese = new Font(bfChinese, 12, Font.NORMAL);
			// 创建有colNumber(6)列的表格
			PdfPTable datatable = new PdfPTable(colNumber);
			// 定义表格的宽度
			int[] cellsWidth = { 8, 2, 2, 8, 5, 3 };
			datatable.setWidths(cellsWidth);
			// 表格的宽度百分比
			datatable.setWidthPercentage(100);
			datatable.getDefaultCell().setPadding(padding);
			datatable.getDefaultCell().setBorderWidth(spacing);
			// 设置表格的底色
			datatable.getDefaultCell().setBackgroundColor(BaseColor.GREEN);
			datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			// 添加表头元素
			for (int i = 0; i < colNumber; i++) {
				datatable.addCell(new Paragraph(tableHeader[i], fontChinese));
			}
			// 添加子元素
			for (int i = 0; i < colNumber; i++) {
				datatable.addCell(new Paragraph(tableHeader[i], fontChinese));
			}
			document.add(datatable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		document.close();
	}
	
	
	@Test
	public void test2() throws Exception{
		Document document = new Document(PageSize.A4);
		PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(groovyGenPDFtoPath));
		
		
		
	}
	

}
