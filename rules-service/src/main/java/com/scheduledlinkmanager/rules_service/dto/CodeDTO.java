package com.scheduledlinkmanager.rules_service.dto;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeDTO {
    private UUID id;
    private Long userId;
    private String title;
    private String description;
    private Boolean isPublic;
    private Boolean isCommentable;
    private Boolean isSharedCounter;
    private List<RuleDTO> rules;
}
