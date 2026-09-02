package com.nexushr.Cloud;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class StoragePathService {

    @Autowired
    private Cloudinary cloudinary;

    public String store(MultipartFile file, String folder) {

        try {

            Map upload = cloudinary.uploader()
                    .upload(
                            file.getBytes(),
                            ObjectUtils.asMap(
                                    "folder", folder,
                                    "resource_type", "auto"
                            )
                    );

            String url = (String) upload.get("source_url");

            return url;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}