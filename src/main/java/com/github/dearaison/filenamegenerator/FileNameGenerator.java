package com.github.dearaison.filenamegenerator;

import java.io.File;
import java.nio.file.Path;

/**
 * Created by IntelliJ on Thursday, 07 May, 2020 at 00:57.
 *
 * @author Joseph Maria
 */
public class FileNameGenerator {
    /**
     * Gets file extension (with dot sign) from a given file name string.
     *
     * @param fileName Given file name string
     * @return A file extension (with dot sign) if there is one. Empty if there is no extension in file name string. Extension string after last dot if file name contain only extension and no file name
     */
    public static String getFileExtension(String fileName) {
        int indexOfLastDot = fileName.lastIndexOf('.');
        if (indexOfLastDot == -1) {
            return "";
        }
        return fileName.substring(indexOfLastDot);
    }

    /**
     * Gets file extension (with dot sign) from a given file object.
     *
     * @param file Given file object
     * @return A file extension (with dot sign) if there is one. Empty if there is no extension in file name string. Extension string after last dot if file name contain only extension and no file name
     */
    public static String getFileExtension(File file) {
        return getFileExtension(file.getName());
    }

    /**
     * Gets file extension (with dot sign) from a given path object.
     *
     * @param filePath Given path object
     * @return A file extension (with dot sign) if any. Empty if there is no extension in file name string. Extension string after last dot if file name contain only extension and no file name
     */
    public static String getFileExtension(Path filePath) {
        return getFileExtension(filePath.getFileName().toString());
    }

    /**
     * Gets file name (without file extension) from a given file name string.
     *
     * @param fileName Given file name string
     * @return A file name (without file extension) if any
     */
    public static String getFileName(String fileName) {
        int indexOfLastDot = fileName.lastIndexOf('.');
        if (indexOfLastDot == -1) {
            return fileName;
        }
        return fileName.substring(0, indexOfLastDot);
    }

    /**
     * Gets file name (without file extension) from a given file object.
     *
     * @param file Given file object
     * @return A file name (without file extension) if any
     */
    public static String getFileName(File file) {
        return getFileName(file.getName());
    }

    /**
     * Gets file name (without file extension) from a given path object.
     *
     * @param filePath Given path
     * @return A file name (without file extension) if any
     */
    public static String getFileName(Path filePath) {
        return getFileName(filePath.getFileName().toString());
    }

    /**
     * Generate a file object with unique file name.
     *
     * @param file The file object which contain desired file path
     * @return A file object with unique file name
     */
    public static File generateFilePath(File file) {
        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            throw new IllegalArgumentException("Parent directory don't exist");
        }
        String fileName = getFileName(file);
        String fileExtension = getFileExtension(file);
        long id = 0;
        File checkFile = file;
        while (file.exists()) {
            checkFile = new File(parentDir, fileName + " (" + ++id + ")" + fileExtension);
        }
        return checkFile;
    }

    /**
     * Generate a file path with unique file name.
     *
     * @param filePath The desired file path
     * @return A file path with unique file name
     */
    public static String generateFilePath(String filePath) {
        return generateFilePath(new File(filePath)).getAbsolutePath();
    }

    /**
     * Generate a file path with unique file name.
     *
     * @param filePath The desired file path
     * @return A file path with unique file name
     */
    public static Path generateFilePath(Path filePath) {
        return generateFilePath(filePath.toFile()).toPath();
    }
}
