/**
 * 
 */
package com.ligelong.util;

import java.io.File;

/**
 * 
 *<code>Util</code>
 *
 * @author David Gong
 */
public class Util {
	private Util() {
	}
	
	public static String getFileNameById(Integer id) {
		int parentDirId = id%1000;
		if(!new File(baseDir + parentDirId).exists()) {
			new File(baseDir + parentDirId).mkdir();
		}
		return baseDir + parentDirId + "/" + id + ".txt";
	}
	
	private static final String baseDir = "/backup/data/";
}
