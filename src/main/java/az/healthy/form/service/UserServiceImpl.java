package az.healthy.form.service;

import az.healthy.form.dto.UserRequestDto;
import az.healthy.form.dto.UserResponseDto;
import az.healthy.form.entity.User;
import az.healthy.form.error.EntityNotFoundException;
import az.healthy.form.error.FileCantUploadException;
import az.healthy.form.mapper.UserMapper;
import az.healthy.form.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import static net.logstash.logback.argument.StructuredArguments.kv;

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
        log.info("user create started with with :{}", kv("userRequestDto", userRequestDto));
        User user = userRepository.save(userMapper.toUserEntity(userRequestDto));
        UserResponseDto userResponseDto = userMapper.toUserDto(user);
        log.info("created User completed successfully created with: {}", kv("userRequestDto", userResponseDto));
        return userResponseDto;

    }

    @Override
    public UserResponseDto update(UserRequestDto userRequestDto, Long id) {
        log.info("user update started with: {},{}", kv("id", id),
                kv("userRequestDto", userRequestDto));

        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(User.class, id));

        user.setName(userRequestDto.getName());
        user.setSurname(userRequestDto.getSurname());
        User saved = userRepository.save(user);
        UserResponseDto userResponseDto = userMapper.toUserDto(saved);
        log.info("update User completed successfully with: {},{}",
                kv("id", id), kv("userRequestDto", userRequestDto));
        return userResponseDto;
    }

    @Override
    public UserResponseDto findById(Long id) {
        log.info("user findById started with: {}", kv("id", id));
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(User.class, id));
        UserResponseDto userResponseDto = userMapper.toUserDto(user);
        log.info("findById User completed with : {}", kv("id", id));
        return userResponseDto;
    }

    @Override
    public UserResponseDto delete(Long id) {
        log.info("delete User started with: {}", kv("id", id));
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(User.class, id));
        if (user.getMri() != null) {
            deleteMtiFile(user.getMri());
        }
        userRepository.delete(user);
        UserResponseDto userResponseDto = userMapper.toUserDto(user);
        log.info("delete User completed with: {}", kv("id", id));
        return userResponseDto;
    }

    @Override
    public String uploadPhoto(MultipartFile file, Long id) {
        log.info("upload File  to User started with :{}",
                kv("partnerId", id));
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(User.class, id));
        if (user.getMri() == null) {
            String fileName = fileService.upload(file, folder);
            user.setMri(fileName);
            userRepository.save(user);
            log.info("uploadFile to User completed  successfuly with: {}",
                    kv("partnerId", id));
            return fileName;
        }
        throw new FileCantUploadException(file.getOriginalFilename());
    }

    @Override
    @Transactional
    public String updatePhoto(MultipartFile file, Long id) {
        log.info("updateFile to User started with, {}",
                kv("partnerId", id));
        User user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(User.class, id));
        deleteMtiFile(user.getMri());
        String fileName = fileService.upload(file, folder);
        user.setMri(fileName);
        userRepository.save(user);
        log.info("updateFile to User completed successfully with {}",
                kv("partnerId", user));
        return fileName;
    }

    @Override
    public void deleteMtiFile(Long id) {
        log.info("deleteUserPhoto started from User with {}", kv("id", id));
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(User.class, id));
        if (user.getMri() != null) {
            fileService.deleteFile(user.getMri(), folder);
            user.setMri(null);
            userRepository.save(user);
        }
        log.info("deleteMriFile completed successfully  from User with: {}", kv("id", id));
    }

    @Override
    public void deleteMtiFile(String fileName) {
        log.info("deleteFile started from User with {}", kv("fileName", fileName));
        fileService.deleteFile(fileName, folder);
        log.info("deleteFile completed successfully from User with {} ", kv("fileName", fileName));
    }

    @Override
    public byte[] getMtiFile(String fileName) {
        log.info("getPhoto started with {}", kv("fileName", fileName));
        return fileService.getFile(fileName, folder);
    }
}
