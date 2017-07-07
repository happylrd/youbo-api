package io.happylrd.youbo.service.impl;

import com.google.common.collect.Lists;
import io.happylrd.youbo.service.FileService;
import io.happylrd.youbo.util.FTPUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String upload(MultipartFile file, String path) {
        String fileName = file.getOriginalFilename();
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        String uploadFileName = UUID.randomUUID().toString() + "." + fileExtensionName;

        File fileDir = new File(path);
        if (!fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }

        File targetFile = new File(path, uploadFileName);

        try {
            // upload to Tomcat
            file.transferTo(targetFile);

            // upload to FTP
            FTPUtil.uploadFile(Lists.newArrayList(targetFile));

            // delete file in upload dir of Tomcat
            targetFile.delete();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return targetFile.getName();
    }
}
