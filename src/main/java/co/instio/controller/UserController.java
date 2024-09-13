package co.instio.controller;

import co.instio.dto.ResponseModel;
import co.instio.entity.Users;
import co.instio.exceptions.ControllerException;
import co.instio.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UsersService usersService;

    @PostMapping
    public ResponseModel<?> createUsers(@Valid @RequestBody List<Users> users, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw  new ControllerException(bindingResult.getFieldError().toString());
        }
        return ResponseModel.of(usersService.createUsers(users));
    }

    @GetMapping("/all")
    public ResponseModel<?> getAllUsers(){
        return ResponseModel.of(usersService.getAllUsers());
    }

    @GetMapping("{id}")
    public ResponseModel<?> getUserById(@PathVariable("id") Long userId){
        return ResponseModel.of(usersService.getById(userId));
    }

    @PutMapping("{id}")
    public ResponseModel<?> updateUsers(@PathVariable("id") Long userId , @RequestBody Users users){
        return ResponseModel.of(usersService.updateUsers(userId,users));
    }

    @DeleteMapping("{id}")
    public ResponseModel<?> deleteUsers(@PathVariable("id") Long userId){
        usersService.deleteUser(userId);
        return ResponseModel.of(HttpStatus.OK);
    }


}
