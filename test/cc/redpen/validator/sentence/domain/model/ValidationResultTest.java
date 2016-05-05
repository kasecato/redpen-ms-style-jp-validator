package cc.redpen.validator.sentence.domain.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ValidationResultTest {

    @Test
    public void isFailed() {

        // arrange
        ValidationResult result = ValidationResult.create();
        result.addErrorPosition(0, 0);

        // act
        final boolean isFailed = result.isFailed();

        // assert
        assertEquals(true, isFailed);
    }

    @Test
    public void isSucceed() {

        // arrange
        ValidationResult result = ValidationResult.create();

        // act
        final boolean isSucceed = result.isSucceed();

        // assert
        assertEquals(true, isSucceed);
    }

}
