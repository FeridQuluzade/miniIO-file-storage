package az.healthy.form.controller;

import az.healthy.form.dto.UserRequestDto;
import az.healthy.form.dto.UserResponseDto;
import az.healthy.form.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get User by Id")
    public ResponseEntity<UserResponseDto> getById(@PathVariable("id") Long id){
        return ResponseEntity.status(200).body(userService.findById(id));
    }

    @PostMapping
    @ApiOperation("Add User")
    public ResponseEntity<UserResponseDto> create(@Valid @RequestBody UserRequestDto userRequestDto){
        return ResponseEntity.status(201).body(userService.create(userRequestDto));
    }

    @PutMapping("/{id}")
    @ApiOperation("Update User")
    public ResponseEntity<UserResponseDto> update( @PathVariable("id") Long id,
            @Valid @RequestBody UserRequestDto userRequestDto){
        return ResponseEntity.status(200).body(userService.update(userRequestDto, id));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete User")
    public ResponseEntity<UserResponseDto> delete(@PathVariable("id") Long id){
        return ResponseEntity.status(200).body(userService.delete(id));
    }

    @PostMapping("/image/{id}")
    @ApiOperation(value = "Add User File")
    public ResponseEntity<String> createImage(@PathVariable("id") Long id,
                                              @Valid @RequestParam MultipartFile file){
        return ResponseEntity.status(200).body(userService.updatePhoto(file,id));
    }

    @PutMapping("/image/{id}")
    @ApiOperation(value = "Update User File")
    public ResponseEntity<String> updateImage(@PathVariable("id")Long id,
                                              @Valid @RequestParam MultipartFile multipartFile){
        return ResponseEntity.status(200).body(userService.updatePhoto(multipartFile,id));
    }

    @GetMapping("/image/{fileName}")
    @ApiOperation(value = "Get User photo")
    public byte [] getImage(@PathVariable("fileName") String fileName){
        return userService.getMtiFile(fileName);
    }

    @DeleteMapping("/image/{id}")
    public void deleteUserFile(@PathVariable Long id){
        userService.deleteMtiFile(id);
    }
}
