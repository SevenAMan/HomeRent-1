package org.zc.homerent.controller.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;
import org.zc.homerent.util.hash.Hash;

import java.io.*;

/**
 * @author FDws
 * Created on 2018/6/15 9:11
 */
@Configuration
public class FileUtil {
    private static Logger log = LoggerFactory.getLogger(FileUtil.class);
    /**
     * Project Image path
     */
    @Value("${file.image-path:./}")
    private String imagePath;
    /**
     * The upload file's max size
     */
    @Value("${file.max-file-size:5242880}")
    private long maxFileSize;

    private File imageParent = null;

    private final Hash hash;

    @Autowired
    public FileUtil(Hash hash) {
        this.hash = hash;
    }

    public long getMaxFileSize() {
        return maxFileSize;
    }

    public File getImageParent() {
        if (imageParent != null) return imageParent;

        imageParent = new File(imagePath);
        if (imageParent.isDirectory()) {
            return imageParent;
        } else if (!imageParent.exists() && imageParent.mkdirs()) {
            log.info("Image path point to " + imageParent.getName());
            return imageParent;
        } else if (imageParent.exists()) {
            imageParent = imageParent.getParentFile();
            log.info("Image path point to " + imageParent.getName());
            return imageParent;
        } else {
            imageParent = new File(System.getProperty("user.home"));
            log.info("Image path point to " + imageParent.getName());
            return imageParent;
        }
    }

    public void readImage(String name, OutputStream stream) {
        File f = new File(getImageParent(), name);
        if (f.exists()) {
            try (
                    InputStream in = new FileInputStream(f)
            ) {
                byte[] b = new byte[2048];
                int len;
                while ((len = in.read(b)) > 0) {
                    stream.write(b, 0, len);
                }
            } catch (IOException e) {
                log.error("Read file error " + name);
            }
        }
    }

    public String saveImage(MultipartFile image) {
		System.out.println("File empty or null " + image==null + " " + image.isEmpty());
        if (image == null || image.isEmpty()) {
            return "";
        }
        String name = "";
        try {
            name = hash.hashBytes(image.getBytes()) + suffix(image.getOriginalFilename());
            image.transferTo(new File(getImageParent(), name));
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Save file error " + name);
        }
		System.out.println(name);
        return name;
    }

    private String suffix(String name) {
        for (int i = name.length() - 1; i >= 0; i--) {
            if (name.charAt(i) == '.') {
                return name.substring(i, name.length());
            }
        }
        return ".";
    }
}
