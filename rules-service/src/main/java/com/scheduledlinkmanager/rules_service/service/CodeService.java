package com.scheduledlinkmanager.rules_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.scheduledlinkmanager.rules_service.dto.CodeDTO;
import com.scheduledlinkmanager.rules_service.dto.RuleDTO;
import com.scheduledlinkmanager.rules_service.model.Code;
import com.scheduledlinkmanager.rules_service.model.Rule;
import com.scheduledlinkmanager.rules_service.repository.CodeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CodeService {

    private final CodeRepository codeRepository;
    public void createCode(CodeDTO codeDTO) {
        Code code = new Code();

        code.setId(codeDTO.getId());
        code.setUserId(codeDTO.getUserId());
        code.setTitle(codeDTO.getTitle());
        code.setDescription(codeDTO.getDescription());
        code.setIsPublic(codeDTO.getIsPublic());
        code.setIsCommentable(codeDTO.getIsCommentable());
        code.setIsSharedCounter(codeDTO.getIsSharedCounter());

        List<Rule> rules = codeDTO.getRules()
                                .stream()
                                .map(this::mapRuleFromDTO)
                                .toList();

        rules.forEach(rule -> rule.setCode(code));

        code.setRules(rules);
        codeRepository.save(code);
    }

    public Rule mapRuleFromDTO(RuleDTO ruleDTO) {
        Rule rule = new Rule();

        rule.setId(ruleDTO.getId());
        rule.setStartDate(ruleDTO.getStartDate());
        rule.setEndDate(ruleDTO.getEndDate());
        rule.setDaysCount(ruleDTO.getDaysCount());
        rule.setRouteCounter(ruleDTO.getRouteCounter());

        List<Boolean> weekDays = ruleDTO.getWeekDays();
        for (int i = 0; i < Math.min(7, weekDays.size()); i++) {
            rule.setDayEnabled(i, weekDays.get(i));
        }

        return rule;
    }
}
