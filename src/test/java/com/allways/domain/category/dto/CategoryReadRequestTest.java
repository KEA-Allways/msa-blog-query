package com.allways.domain.category.dto;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryReadRequestTest {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    void categoryReadRequestValidation() {
        // Given
        CategoryReadRequest readRequest = new CategoryReadRequest(
                1L,
                "categoryName",
                1L,
                1L);

        // When
        Set<ConstraintViolation<CategoryReadRequest>> violations =
                validator.validate(readRequest);

        // Then
        assertEquals(0, violations.size(),
                "위반 사항이 없습니다.");
    }

    @Test
    void categoryReadCategorySeqValidation() {
        // Given
        CategoryReadRequest readRequest = new CategoryReadRequest(
                null,
                "categoryName",
                1L,
                1L);

        // When
        Set<ConstraintViolation<CategoryReadRequest>> violations =
                validator.validate(readRequest);

        // Then
        assertEquals(1, violations.size(),
                "categorySeq가 null입니다.");
    }

    @Test
    void categoryReadCategoryNameValidation() {
        // Given
        CategoryReadRequest readRequest = new CategoryReadRequest(
                1L,
                "",
                1L,
                1L);

        // When
        Set<ConstraintViolation<CategoryReadRequest>> violations =
                validator.validate(readRequest);

        // Then
        assertEquals(1, violations.size(),
                "categoryName이 Blank입니다.");
    }

    @Test
    void categoryReadCategoryOrderValidation() {
        // Given
        CategoryReadRequest readRequest = new CategoryReadRequest(
                1L,
                "categoryName",
                null,
                1L);

        // When
        Set<ConstraintViolation<CategoryReadRequest>> violations =
                validator.validate(readRequest);

        // Then
        assertEquals(1, violations.size(),
                "categoryOrder가 null입니다.");
    }

    @Test
    void categoryReadThemeSeqValidation() {
        // Given
        CategoryReadRequest readRequest = new CategoryReadRequest(
                1L,
                "categoryName",
                1L,
                null);

        // When
        Set<ConstraintViolation<CategoryReadRequest>> violations =
                validator.validate(readRequest);

        // Then
        assertEquals(1, violations.size(),
                "themeSeq가 null입니다.");
    }
}
