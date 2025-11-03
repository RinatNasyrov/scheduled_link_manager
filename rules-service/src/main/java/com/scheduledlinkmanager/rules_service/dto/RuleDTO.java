package com.scheduledlinkmanager.rules_service.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RuleDTO {
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long daysCount;
    private List<Boolean> weekDays;
    private Long routeCounter;
}
