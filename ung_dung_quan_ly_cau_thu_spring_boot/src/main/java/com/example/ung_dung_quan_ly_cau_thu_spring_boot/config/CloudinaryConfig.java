package com.example.ung_dung_quan_ly_cau_thu_spring_boot.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dhlp7pnpn",
                "api_key", "411312418664949",
                "api_secret", "3YoWzAE9KWP-WfmTbXaqtugg6YU",
                "secure", true
        ));
    }
}
