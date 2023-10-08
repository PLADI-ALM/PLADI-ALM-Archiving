package com.example.PLADIALMArchiving.user.service;

import com.example.PLADIALMArchiving.user.dto.request.UserReq;
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

    }

    @Override
    public void deleteUser(UserReq userReq) {

    }

//    private final UserAssembler userAssembler;

//    @Transactional
//    @Override
//    public void addUser(UserReq userReq) {
//        this.userRepository.save(this.userAssembler.toEntity(userReq));
//    }
//
//    @Transactional
//    @Override
//    public void changeUserProfile(UserReq userReq) {
//        User user = this.userRepository.findByUserIdxAndIsEnable(userReq.getUserIdx(), true).orElseThrow(UserNotFoundException::new);
//        user.modifyProfile(userReq.getNickname(), userReq.getProfileImgKey());
//    }
//
//
//    @Transactional
//    @Override
//    public void deleteUser(UserReq userReq) {
//        User user = this.userRepository.findByUserIdxAndIsEnable(userReq.getUserIdx(), true).orElseThrow(UserNotFoundException::new);
//        this.userRepository.deleteById(userReq.getUserIdx());
//    }
}
