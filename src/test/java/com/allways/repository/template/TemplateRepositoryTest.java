package com.allways.repository.template;

import com.allways.domain.template.domain.Template;
import com.allways.domain.template.repository.TemplateRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TemplateRepositoryTest {

    @Autowired
    private TemplateRepository templateRepository;

    @Test
    void findByTemplateSeqTest() {
        // given
        Template templateToSave = new Template("Template Title", "Template Content", 1L);
        Template savedTemplate = templateRepository.save(templateToSave);
    }
}
