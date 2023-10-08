package com.example.PLADIALMArchiving.user.entity;

import com.example.PLADIALMArchiving.global.entity.BaseEntity;
import com.example.PLADIALMArchiving.user.dto.request.UserReq;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
@Entity
public class User extends BaseEntity {
    @Id
    @Column(nullable = false)
    private Long userId;
    private String name;

    @Builder
    public User(Long userId, String name){
        this.userId = userId;
        this.name = name;
    }

    public void modifyProfile(String name) {
        this.name = name;
    }

    public static User toEntity(UserReq userReq) {
        return User.builder().userId(userReq.getUserId()).name(userReq.getName()).build();
    }
}
