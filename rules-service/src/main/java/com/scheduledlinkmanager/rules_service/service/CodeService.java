package com.scheduledlinkmanager.rules_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.scheduledlinkmanager.rules_service.dto.CodeDTO;
import com.scheduledlinkmanager.rules_service.dto.RuleDTO;
import com.scheduledlinkmanager.rules_service.mapper.RuleMapper;
import com.scheduledlinkmanager.rules_service.model.Code;
import com.scheduledlinkmanager.rules_service.model.Rule;
import com.scheduledlinkmanager.rules_service.repository.CodeRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CodeService {
    private final CodeRepository codeRepository;
    private final RuleMapper ruleMapper;

    public void createCode(CodeDTO codeDTO) {
        if (codeDTO == null) 
            return;
        
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
                                .map(ruleMapper::ToEntity)
                                .toList();

        rules.forEach(rule -> rule.setCode(code));

        code.setRules(rules);
        codeRepository.save(code);
    }

    public void deleteCode(UUID id) {
        if (id != null)
            codeRepository.deleteById(id);
    }

    public CodeDTO getCode(UUID id) {
        if (id == null)
            return null;

        Code code = codeRepository.getReferenceById(id);
        CodeDTO codeDTO = new CodeDTO();
        
        try {
            codeDTO.setId(code.getId());
            codeDTO.setUserId(code.getUserId());
            codeDTO.setTitle(code.getTitle());
            codeDTO.setDescription(code.getDescription());
            codeDTO.setIsPublic(code.getIsPublic());
            codeDTO.setIsCommentable(code.getIsCommentable());
            codeDTO.setIsSharedCounter(code.getIsSharedCounter());
        } catch (EntityNotFoundException e) {
            return null;
        }

        List<RuleDTO> rulesDTO = code.getRules()
                                .stream()
                                .map(ruleMapper::ToDTO)
                                .toList();

        codeDTO.setRules(rulesDTO);

        return codeDTO;
    }

        public List<CodeDTO> getRandomCodes(int count) {
        List<Code> codes = codeRepository.findRandomCodesEfficient(count);
        List<CodeDTO> codeDTOs = new ArrayList<>();
        CodeDTO codeDTO;
        Code code;
        for (int i = 0; i < codes.size(); i++)
        {
            code = codes.get(i);
            codeDTO = new CodeDTO();
            codeDTO.setId(code.getId());
            codeDTO.setUserId(code.getUserId());
            codeDTO.setTitle(code.getTitle());
            codeDTO.setDescription(code.getDescription());
            codeDTO.setIsPublic(code.getIsPublic());
            codeDTO.setIsCommentable(code.getIsCommentable());
            codeDTO.setIsSharedCounter(code.getIsSharedCounter());

            List<RuleDTO> rulesDTO = code.getRules()
                                    .stream()
                                    .map(ruleMapper::ToDTO)
                                    .toList();

            codeDTO.setRules(rulesDTO);
            codeDTOs.add(codeDTO);
        }
        
        return codeDTOs;
    }
}
