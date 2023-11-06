package com.example.PLADIALMArchiving.user.service;

import com.example.PLADIALMArchiving.global.exception.BaseException;
import com.example.PLADIALMArchiving.global.exception.BaseResponseCode;
import com.example.PLADIALMArchiving.user.dto.request.UserReq;
import com.example.PLADIALMArchiving.user.entity.Role;
import com.example.PLADIALMArchiving.user.entity.User;
import com.example.PLADIALMArchiving.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public void addUser(UserReq userReq) {
        this.userRepository.save(User.toEntity(userReq));
    }

    @Override
    public void changeUserProfile(UserReq userReq) {
        User user = this.userRepository.findByUserIdAndIsEnable(userReq.getUserId(), true).orElseThrow(() -> new BaseException(BaseResponseCode.USER_NOT_FOUND));
        user.changeProfile(user.getName(), userReq.getRole());
    }

    @Override
    public void deleteUser(UserReq userReq) {
        User user = this.userRepository.findByUserIdAndIsEnable(userReq.getUserId(), true).orElseThrow(() -> new BaseException(BaseResponseCode.USER_NOT_FOUND));
        userRepository.delete(user);
    }
}
