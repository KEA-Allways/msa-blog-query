package com.allways.domain.template.factory;

import com.allways.domain.template.domain.Template;

public class TemplateFactory {
    public static Template createTemplate() {
        return new Template("Template Title", "Template Content", 1L);
    }
}
