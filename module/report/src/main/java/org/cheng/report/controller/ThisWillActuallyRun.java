package org.cheng.report.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@RestController
public class ThisWillActuallyRun {
	
	@Autowired
	private DataSource dataSource;
	
	@RequestMapping("/")
	public String home() {
		return "Hello World!";
	}

	@RequestMapping(value = "/exportPdf")
	public void exportPdf(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		String root_path = this.getClass().getResource("/Wave_Book.jasper").getPath();
		OutputStream out = null;
		Connection conn = null;
		try {
			out = response.getOutputStream();
			conn = dataSource.getConnection();

			// 得到jasper文件
			JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(root_path);
			// null可以改成 map参数
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);

			// 设置响应头 以文件的方式返回
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode("my.pdf", "utf8"));
			JasperExportManager.exportReportToPdfStream(jasperPrint, out);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			{
				try {
					conn.close();
					out.flush();
					out.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}