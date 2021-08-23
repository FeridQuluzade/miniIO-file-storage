package az.healthy.form.mapper;

import az.healthy.form.dto.UserRequestDto;
import az.healthy.form.dto.UserResponseDto;
import az.healthy.form.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface UserMapper {

    UserResponseDto toUserDto(User user);

    User toUserEntity(UserRequestDto userRequestDto);

    List<UserResponseDto> toUserDtoList(List<User> userList);
}
