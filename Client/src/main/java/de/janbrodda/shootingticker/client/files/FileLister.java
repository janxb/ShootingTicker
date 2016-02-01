package de.janbrodda.shootingticker.client.files;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.RegexFileFilter;

public class FileLister {
	protected FileLister() {
	}

	public static List<File> getDirectoriesWithXmlContent(String directory) {
		File file = FileUtils.getReadableDirectory(directory);
		Map<String, File> foundDirs = new HashMap<>();

		Collection<File> files = org.apache.commons.io.FileUtils.listFiles(file, new RegexFileFilter("^(.*).xml$"),
				DirectoryFileFilter.DIRECTORY);
		
		for (File foundFile : files) {
			String foundPath = foundFile.getParent();
			if (!foundDirs.containsKey(foundPath)){
				foundDirs.put(foundPath, foundFile);
			}
		}

		return new ArrayList<File>(foundDirs.values());
	}

	public static List<File> getDirectories(String directory) {
		File file = FileUtils.getReadableDirectory(directory);
		File[] resultArray = file.listFiles((FileFilter) FileFilterUtils.directoryFileFilter());
		return Arrays.asList(resultArray);
	}

	public static List<File> getFiles(String directory) {
		File file = FileUtils.getReadableDirectory(directory);
		File[] resultArray = file.listFiles((FileFilter) FileFilterUtils.fileFileFilter());
		return Arrays.asList(resultArray);
	}

}
