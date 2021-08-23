package az.healthy.form.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    byte[] getFile(String fileName,String folder);

    String upload(MultipartFile fileName,String folder);

    void deleteFile(String fileName,String folder);

}
