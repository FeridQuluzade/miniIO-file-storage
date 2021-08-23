package az.healthy.form.service;

import az.healthy.form.dto.UserRequestDto;
import az.healthy.form.dto.UserResponseDto;
import az.healthy.form.mapper.UserMapper;
import az.healthy.form.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final FileServiceImpl fileService;
    private final UserMapper userMapper;
    @Value("${minio.folder}")
    private String folder;

    public UserServiceImpl(UserRepository userRepository,
                           FileServiceImpl fileService,
                           UserMapper userMapper) {
        this.userRepository = userRepository;
        this.fileService = fileService;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponseDto create(UserRequestDto userRequestDto) {
        return null;
    }

    @Override
    public UserResponseDto update(UserRequestDto userRequestDto, Long id) {
        return null;
    }

    @Override
    public UserResponseDto findById(Long id) {
        return null;
    }

    @Override
    public UserResponseDto delete(Long id) {
        return null;
    }

    @Override
    public String uploadPhoto(MultipartFile file, Long id) {
        return null;
    }

    @Override
    public String updatePhoto(MultipartFile file, Long id) {
        return null;
    }

    @Override
    public void deleteMtiFile(Long id) {

    }

    @Override
    public void deleteMtiFile(String fileName) {

    }

    @Override
    public byte[] getMtiFile(String fileName) {
        return new byte[0];
    }
}
