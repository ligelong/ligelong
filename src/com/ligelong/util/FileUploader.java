/*
 * Created on 2011-10-20
 */
package com.ligelong.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

/**
 * 
 * <code>FileUploader</code>
 *
 * @author David Gong
 */
public class FileUploader {
    //
	private FileUploader() {//
	}
	//
	private static final String tmpDirPath;
	//
	private static final String savedDirPath;
	
	static {
		if(System.getProperty("os.name").toLowerCase().contains("windows")) {
			tmpDirPath = "D:\\";
			savedDirPath = "D:\\";
		} else {
			tmpDirPath = "/tmp/";
			savedDirPath = "/tmp/";
		}
	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static Map<String, List<String>> upload(HttpServletRequest request) throws Exception {
	    Map<String, List<String>> retMap = new HashMap<String, List<String>>();
		if (ServletFileUpload.isMultipartContent(request)) {
			
			DiskFileItemFactory dff = new DiskFileItemFactory();
			File tmpDir = new File(tmpDirPath);
			dff.setRepository(tmpDir);
			dff.setSizeThreshold(1024000);
			ServletFileUpload sfu = new ServletFileUpload(dff);
			sfu.setFileSizeMax(5000000);
			sfu.setSizeMax(-1);

			//
			@SuppressWarnings("unchecked")
			List<FileItem> fii = sfu.parseRequest(request);
			for (FileItem item : fii) {
				if (!item.isFormField()) {
					String fileName = item.getName();
					fileName = fileName.substring(fileName.lastIndexOf(File.separator) + 1);
					BufferedInputStream in = new BufferedInputStream(item.getInputStream());
					BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(savedDirPath+ fileName));
					Streams.copy(in, out, true);
					String name = "updateFile";
                    if(!retMap.containsKey(name)) {
                        retMap.put(name, new ArrayList<String>());
                    }
					retMap.get(name).add(savedDirPath + fileName);
				} else {
				    String name = item.getFieldName();
				    if(!retMap.containsKey(name)) {
				        retMap.put(name, new ArrayList<String>());
				    }
				    retMap.get(name).add(item.getString());
				}
			}
		}
		return retMap;
	}
}
