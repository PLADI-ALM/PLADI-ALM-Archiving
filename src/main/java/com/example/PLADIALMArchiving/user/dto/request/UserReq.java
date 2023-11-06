package com.example.PLADIALMArchiving.user.dto.request;

import com.example.PLADIALMArchiving.user.entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserReq {
    private Long userId;
    private String name;
    private Role role;
}
