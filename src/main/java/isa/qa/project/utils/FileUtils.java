package isa.qa.project.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.io.*;

/**
 * 文件工具类
 *
 * @author May
 * @version 1.0
 * @date 2018/4/19 9:33
 */
public class FileUtils {

    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);

    private FileUtils() {
    }

    /**
     * 创建多级目录
     */
    public static boolean mkdirs(String dirPath) {
        File file = new File(dirPath);

        if (!file.exists()) {
            return file.mkdirs();
        }
        return true;
    }

    /**
     * 保存文件
     *
     * @param inputStream 文件流
     * @param targetFilePath 目标文件路径
     * @return 是否保存成功
     */
    public static boolean saveFile(InputStream inputStream, String targetFilePath) {
        File targetFile = new File(targetFilePath);

        return copyInputStreamToFile(inputStream, targetFile);
    }

    /**
     * 将输入流输出至一个文件中
     *
     * @param inputStream 输入流
     * @param file 文件
     * @return 是否保存成功
     */
    public static boolean copyInputStreamToFile(InputStream inputStream, File file) {
        try (OutputStream outputStream = new FileOutputStream(file)) {
            byte[] bytes = new byte[1024];

            while (inputStream.read(bytes) != -1) {
                outputStream.write(bytes);
            }
            return true;
        } catch (IOException e) {
            LOGGER.error("Save file failed");
            return false;
        }
    }


    /**
     * 删除文件
     *
     * @param filePath 要删除的文件路径
     */
    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);

        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    /**
     * 删除某目录下的所有文件
     */
    public static boolean deleteDir(String dirPath) {
        File file = new File(dirPath);

        if (file.exists() && file.isDirectory()) {
            String[] childFilePath = file.list();
            if (childFilePath != null) {
                for (String aChildFilePath : childFilePath) {
                    boolean result = deleteFile(dirPath + File.separator + aChildFilePath);
                    if (!result) {
                        return false;
                    }
                }
            }
        }
        return file.delete();
    }

    /**
     * 获取文件的扩展名
     *
     * @param fileName 文件名
     * @return 文件的后缀名
     */
    public static String getFileExtension(String fileName) {
        Assert.notNull(fileName, "文件名称不能为空");
        String[] fileNameParts = fileName.split(".");

        if (fileNameParts.length == 0) {
            return "";
        }

        return fileNameParts[fileNameParts.length - 1];
    }
}
