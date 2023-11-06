package com.example.PLADIALMArchiving.user.entity;

import com.example.PLADIALMArchiving.global.entity.BaseEntity;
import com.example.PLADIALMArchiving.user.dto.request.UserReq;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
@Entity
@SQLDelete(sql = "UPDATE user SET is_enable = false, update_at = current_timestamp WHERE user_id = ?")
public class User extends BaseEntity {
    @Id
    @Column(nullable = false)
    private Long userId;
    private String name;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Builder
    public User(Long userId, String name, Role role){
        this.userId = userId;
        this.name = name;
        this.role = role;
    }

    public void changeProfile(String name, Role role) {
        this.name = name;
        this.role = role;
    }

    public static User toEntity(UserReq userReq) {
        return User.builder()
                .userId(userReq.getUserId())
                .name(userReq.getName())
                .role(userReq.getRole())
                .build();
    }

}
