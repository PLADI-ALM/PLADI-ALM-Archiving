package com.example.PLADIALMArchiving.user.controller;

import com.example.PLADIALMArchiving.global.response.ResponseCustom;
import com.example.PLADIALMArchiving.user.dto.request.UserReq;
import com.example.PLADIALMArchiving.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping("")
    public ResponseCustom<?> addUser(@RequestBody UserReq userReq){
        this.userService.addUser(userReq);
        return ResponseCustom.OK();
    }

    @PostMapping("change")
    public ResponseCustom<?> changeUserProfile(@RequestBody UserReq userReq){
        this.userService.changeUserProfile(userReq);
        return ResponseCustom.OK();
    }

    @DeleteMapping("")
    public ResponseCustom<?> deleteUser(@RequestBody UserReq userReq){
        this.userService.deleteUser(userReq);
        return ResponseCustom.OK();
    }
}
