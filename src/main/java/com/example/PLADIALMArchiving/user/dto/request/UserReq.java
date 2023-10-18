package com.example.PLADIALMArchiving.user.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserReq {
    private Long userId;
    private String name;
    private String role;
}
