package az.healthy.form.service;

import az.healthy.form.dto.UserRequestDto;
import az.healthy.form.dto.UserResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    UserResponseDto create(UserRequestDto userRequestDto);

    UserResponseDto update(UserRequestDto userRequestDto, Long id);

    UserResponseDto findById(Long id);


    UserResponseDto delete(Long id);

    String uploadImage(MultipartFile file, Long id);

    String updateImage(MultipartFile file, Long id);

    void deleteUserImage(Long id);

    void deleteFile(String fileName, String folder);

    byte[] getFile(String fileName, String folder);

    String uploadVideo(MultipartFile file, Long id);

    String updateVideo(MultipartFile file, Long id);

    void deleteUserVideo(Long id);
}
