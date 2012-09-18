/** TheWalls2: The Walls 2 plugin.
  * Copyright (C) 2012  Andrew Stevanus (Hoot215) <hoot893@gmail.com>
  * 
  * This program is free software: you can redistribute it and/or modify
  * it under the terms of the GNU Affero General Public License as published by
  * the Free Software Foundation, either version 3 of the License, or
  * (at your option) any later version.
  * 
  * This program is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU Affero General Public License for more details.
  * 
  * You should have received a copy of the GNU Affero General Public License
  * along with this program.  If not, see <http://www.gnu.org/licenses/>.
  */

package me.Hoot215.TheWalls2.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileUtils {
	public static void copyFolder(File srcFile, File destFile) throws IOException {
		if (srcFile.isDirectory()) {
			if (!destFile.exists()) {
				destFile.mkdir();
			}
			
			String[] files = srcFile.list();
			
			for (String file : files) {
				File newSrcFile = new File(srcFile, file);
				File newDestFile = new File(destFile, file);
				
				try {
					copyFolder(newSrcFile, newDestFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		else {
			if (!destFile.exists()) {
				destFile.createNewFile();
			}
			
			FileChannel srcChannel = null;
			FileChannel destChannel = null;
			
			try {
				srcChannel = new FileInputStream(srcFile).getChannel();
				destChannel = new FileOutputStream(destFile).getChannel();
				destChannel.transferFrom(srcChannel, 0L, srcChannel.size());
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (srcChannel != null) {
					srcChannel.close();
				}
				if (destChannel != null) {
					destChannel.close();
				}
			}
		}
	}
	
	public static void deleteFolder(File file) {
		File[] files = file.listFiles();
	    if (files != null) {
	        for(File f : files) {
	            if (f.isDirectory())
	                deleteFolder(f);
	            else
	                f.delete();
	        }
	    }
	    file.delete();
	}
}