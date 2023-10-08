package com.example.PLADIALMArchiving.user.service;

import com.example.PLADIALMArchiving.user.dto.request.UserReq;

public interface UserService {
    void addUser(UserReq userReq);

    void changeUserProfile(UserReq userReq);

    void deleteUser(UserReq userReq);
}
