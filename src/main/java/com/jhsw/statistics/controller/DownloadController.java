/**
 * 
 */
package com.jhsw.statistics.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 *
 */
@Controller
@Slf4j
public class DownloadController {

	@RequestMapping({ "/download/", "/download" })
	public ModelAndView index() {
		return new ModelAndView("index");
	}

	@GetMapping({ "/ios", "/download/ios" })
	@ResponseBody
	public void downloadIos(HttpServletRequest request, HttpServletResponse response) {
		File df = new File("/home/jhsw/hzzSpecial.ipa");
		if (!df.exists()) {
			return;
		}

		response.setContentType("application/force-download");
		response.addHeader("Content-Disposition", "attachment;fileName=hzzSpecial.ipa");

		try (OutputStream os = response.getOutputStream(); FileInputStream ios = new FileInputStream(df); BufferedInputStream bis = new BufferedInputStream(ios)) {
			byte[] buffer = new byte[1024];
			int i = bis.read(buffer);
			while (i != -1) {
				os.write(buffer, 0, i);
				i = bis.read(buffer);
			}
			os.flush();
		} catch (Exception e) {
			log.error("生成csv文件失败", e);
		}
	}

}
