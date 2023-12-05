package com.allways.domain.template.dto;

import com.allways.domain.template.entity.Template;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateResponse {
    private Long templateSeq;
    private String templateTitle;
    private String templateContent;

    public static TemplateResponse toDto(Template template) {
        return new TemplateResponse(template.getTemplateSeq(),
                template.getTemplateTitle(),
                template.getTemplateContent());
    }
}