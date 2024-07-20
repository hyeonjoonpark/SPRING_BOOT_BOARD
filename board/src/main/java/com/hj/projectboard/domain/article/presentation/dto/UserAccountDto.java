package com.hj.projectboard.domain.article.presentation.dto;

import com.hj.projectboard.UserAccount;

import java.io.Serializable;
import java.time.LocalDateTime;

public record UserAccountDto(
        String userId,
        String userPassword,
        String email,
        String nickname,
        String memo,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) implements Serializable {
    public static UserAccountDto from(UserAccount userAccount) {
        return new UserAccountDto(
                userAccount.getUserId(),
                userAccount.getUserPassword(),
                userAccount.getEmail(),
                userAccount.getNickname(),
                userAccount.getMemo(),
                userAccount.getCreatedAt(),
                userAccount.getCreatedBy(),
                userAccount.getModifiedAt(),
                userAccount.getModifiedBy()
        );
    }

}