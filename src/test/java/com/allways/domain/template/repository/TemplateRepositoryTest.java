package com.allways.domain.template.repository;

import com.allways.domain.template.entity.Template;
import com.allways.domain.template.factory.TemplateFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 실제 데이터베이스 사용
@SpringBootTest
@ActiveProfiles("test")
public class TemplateRepositoryTest {

    @Autowired
    private TemplateRepository templateRepository;

    @Test
    @Transactional
    void findByTemplateSeqTest() {
        // Given
        Template templateToSave = TemplateFactory.createTemplate();
        Template savedTemplate = templateRepository.save(templateToSave);

        // When
        Optional<Template> foundTemplateOptional = templateRepository.findByTemplateSeq(savedTemplate.getTemplateSeq());

        // Then
        assertThat(foundTemplateOptional).isPresent(); // 찾은 템플릿이 존재하는지 확인
        Template foundTemplate = foundTemplateOptional.get();
        assertThat(foundTemplate.getTemplateSeq()).isEqualTo(savedTemplate.getTemplateSeq()); // 템플릿 번호가 일치하는지 확인
        assertThat(foundTemplate.getTemplateTitle()).isEqualTo(savedTemplate.getTemplateTitle()); // 템플릿 제목이 일치하는지 확인
        assertThat(foundTemplate.getTemplateContent()).isEqualTo(savedTemplate.getTemplateContent()); // 템플릿 내용이 일치하는지 확인
        assertThat(foundTemplate.getUserSeq()).isEqualTo(savedTemplate.getUserSeq()); // 유저 시퀀스가 일치하는지 확인
    }
}
