package az.healthy.form.service;

import az.healthy.form.dto.UserRequestDto;
import az.healthy.form.dto.UserResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    UserResponseDto create(UserRequestDto userRequestDto);

    UserResponseDto update(UserRequestDto userRequestDto, Long id);

    UserResponseDto findById(Long id);

    UserResponseDto delete(Long id);

    String uploadPhoto(MultipartFile file, Long id);

    String updatePhoto(MultipartFile file, Long id);

    void deleteMtiFile(Long id);

    void deleteMtiFile(String fileName);

    byte[] getMtiFile(String fileName);
}
