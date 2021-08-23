package az.healthy.form.service;

//import org.apache.commons.lang.ObjectUtils;


import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import com.cloudinary.*;

import java.io.File;
import java.io.IOException;
import java.util.Map;


@Service
public class CloudinaryService {
    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dxvbz2ztz",
            "api_key", "295165818191814",
            "api_secret", "KQLRFFVnKCLZvMSuq0H1RqEhhYk"));

    public void test() throws IOException {
        File file = new File("C:\\Users\\LEGION\\Downloads\\1.3.12.2.1107.5.2.19.145103.2021081014595044058030837");
        Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        System.out.println(uploadResult);
    }

}
