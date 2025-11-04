package com.scheduledlinkmanager.rules_service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.scheduledlinkmanager.rules_service.dto.RuleDTO;
import com.scheduledlinkmanager.rules_service.model.Rule;

@Component
public class RuleMapper {
        private final int WEEKDAYS_COUNT = 7;

        public Rule ToEntity(RuleDTO ruleDTO) {
        if (ruleDTO == null)
            return null;

        Rule rule = new Rule();

        rule.setId(ruleDTO.getId());
        rule.setStartDate(ruleDTO.getStartDate());
        rule.setEndDate(ruleDTO.getEndDate());
        rule.setStartTime(ruleDTO.getStartTime());
        rule.setEndTime(ruleDTO.getEndTime());
        rule.setDaysCount(ruleDTO.getDaysCount());
        rule.setRouteCounter(ruleDTO.getRouteCounter());
        rule.setRouteURL(ruleDTO.getRouteURL());

        List<Boolean> weekDays = ruleDTO.getWeekDays();
        for (int i = 0; i < Math.min(WEEKDAYS_COUNT, weekDays.size()); i++) {
            rule.setDayEnabled(i, weekDays.get(i));
        }

        return rule;
    }

    public RuleDTO ToDTO(Rule rule) {
        if (rule == null)
            return null;

        RuleDTO ruleDTO = new RuleDTO();
        
        ruleDTO.setId(rule.getId());
        ruleDTO.setStartDate(rule.getStartDate());
        ruleDTO.setEndDate(rule.getEndDate());
        ruleDTO.setStartTime(rule.getStartTime());
        ruleDTO.setEndTime(rule.getEndTime());
        ruleDTO.setDaysCount(rule.getDaysCount());
        ruleDTO.setRouteCounter(rule.getRouteCounter());
        ruleDTO.setRouteURL(rule.getRouteURL());

        List<Boolean> weekDays = new ArrayList<>();
        for (int i = 0; i < WEEKDAYS_COUNT; i++) {
            weekDays.add(rule.isDayEnabled(i));
        }

        ruleDTO.setWeekDays(weekDays);
        return ruleDTO;
    }
}
