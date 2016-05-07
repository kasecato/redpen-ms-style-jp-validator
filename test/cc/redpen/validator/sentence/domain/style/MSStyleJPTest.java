package cc.redpen.validator.sentence.domain.style;

import cc.redpen.model.Sentence;
import cc.redpen.validator.ValidationError;
import cc.redpen.validator.sentence.MSStyleJPValidator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MSStyleJPTest implements MSStyleJP {

    @Test
    public void validate_Failed_Test() {

        // arrange
        final Sentence ngData = new Sentence("デスﾏｰﾁ", 0);
        final MSStyleJPValidator validator = new MSStyleJPValidator();
        final List<ValidationError> errors = new ArrayList<>();
        validator.setErrorList(errors);

        // act
        validator.validate(ngData);

        // assert
        assertEquals(1, errors.size());
        assertEquals(2, errors.get(0).getStartPosition().get().offset);
        assertEquals(5, errors.get(0).getEndPosition().get().offset);
        assertEquals("Full-width characters should be used unless it is necessary to use half-width characters.", errors.get(0).getMessage());
    }

    @Test
    public void validateKatakana_Failed_Test() {

        // arrange
        final Sentence ngData = new Sentence("デスﾏｰﾁ", 0);

        // act
        final boolean actual = validateKatakana.apply(ngData).isFailed();

        // assert
        assertEquals(true, actual);
    }

    @Test
    public void validateKatakanaKa_Failed_Test() {

        // arrange
        final Sentence ngData = new Sentence("3 ケ月, 3 ヶ月, 3 カ月, 3 ヵ月", 0);

        // act
        final boolean actual = validateKatakanaKa.apply(ngData).isFailed();

        // assert
        assertEquals(true, actual);
    }

    @Test
    public void validateKatakanaKo_Failed_Test() {

        // arrange
        final Sentence ngData = new Sentence("5 ケ, 5 コ", 0);

        // act
        final boolean actual = validateKatakanaKo.apply(ngData).isFailed();

        // assert
        assertEquals(true, actual);
    }

    @Test
    public void validateLongVowelExceptionUse_Failed_Test() {

        // arrange
        final Sentence ngData = new Sentence("インタビュする", 0);

        // act
        final boolean actual = validateLongVowelExceptionUse.apply(ngData).isFailed();

        // assert
        assertEquals(true, actual);
    }


    @Test
    public void validateLongVowelExceptionNotUse_Failed_Test() {

        // arrange
        final Sentence ngData = new Sentence("コンパイラー", 0);

        // act
        final boolean actual = validateLongVowelExceptionNotUse.apply(ngData).isFailed();

        // assert
        assertEquals(true, actual);
    }

    @Test
    public void validateEnglishLetters_Failed_Test() {

        // arrange
        final Sentence ngData = new Sentence("ＮＯＴＥ", 0);

        // act
        final boolean actual = validateEnglishLetters.apply(ngData).isFailed();

        // assert
        assertEquals(true, actual);
    }

    @Test
    public void validateSpacesFullHalf_Failed_Test() {

        // arrange
        final Sentence ngData = new Sentence("第3章", 0);

        // act
        final boolean actual = validateSpacesFullHalf.apply(ngData).isFailed();

        // assert
        assertEquals(true, actual);
    }

    @Test
    public void validateSpacesFullStop_Failed_Test() {

        // arrange
        final Sentence ngData = new Sentence("ボタンをクリックして 、 閉じます 。", 0);

        // act
        final boolean actual = validateSpacesFullStop.apply(ngData).isFailed();

        // assert
        assertEquals(true, actual);
    }

    @Test
    public void validateSpacesComma_Failed_Test() {

        // arrange
        final Sentence ngData = new Sentence("ボタンをクリックして 、 閉じます 。", 0);

        // act
        final boolean actual = validateSpacesComma.apply(ngData).isFailed();

        // assert
        assertEquals(true, actual);
    }

    @Test
    public void validateSpacesAngle_Failed_Test() {

        // arrange
        final Sentence ngData = new Sentence("45 °", 0);

        // act
        final boolean actual = validateSpacesAngle.apply(ngData).isFailed();

        // assert
        assertEquals(true, actual);
    }

    @Test
    public void validateSpacesParentheses_Failed_Test() {

        // arrange
        final Sentence ngData = new Sentence("列 A ( タイトル )", 0);

        // act
        final boolean actual = validateSpacesParentheses.apply(ngData).isFailed();

        // assert
        assertEquals(true, actual);
    }

    @Test
    public void validateSpacesQuotationMarks_Failed_Test() {

        // arrange
        final Sentence ngData = new Sentence("更新しますか ?", 0);

        // act
        final boolean actual = validateSpacesQuotationMarks.apply(ngData).isFailed();

        // assert
        assertEquals(true, actual);
    }

    @Test
    public void validateSpacesBrackets_Failed_Test() {

        // arrange
        final Sentence ngData = new Sentence("[ 新規 ] をクリックします。", 0);

        // act
        final boolean actual = validateSpacesBrackets.apply(ngData).isFailed();

        // assert
        assertEquals(true, actual);
    }

    @Test
    public void validateSpacesSlash_Failed_Test() {

        // arrange
        final Sentence ngData = new Sentence("3 / 14", 0);

        // act
        final boolean actual = validateSpacesSlash.apply(ngData).isFailed();

        // assert
        assertEquals(true, actual);
    }

    @Test
    public void validateSpacesFullWidth_Failed_Test() {

        // arrange
        final Sentence ngData = new Sentence("警告 ！", 0);

        // act
        final boolean actual = validateSpacesFullWidth.apply(ngData).isFailed();

        // assert
        assertEquals(true, actual);
    }

    @Test
    public void validateSpacesAccessKey_Failed_Test() {

        // arrange
        final Sentence ngData = new Sentence("保存 (S)", 0);

        // act
        final boolean actual = validateSpacesAccessKey.apply(ngData).isFailed();

        // assert
        assertEquals(true, actual);
    }

    /*
    @Test
    public void validateSpacesMeasurementUnit_Failed_Test() {

        // arrange
        final Sentence ngData = new Sentence("50 mm", 0);

        // act
        final boolean actual = validateSpacesMeasurementUnit.apply(ngData).isFailed();

        // assert
        assertEquals(true, actual);
    }
    */

    @Test
    public void validateSpacesParenthesis_Failed_Test() {

        // arrange
        final Sentence ngData = new Sentence("10/13(ページ)", 0);

        // act
        final boolean actual = validateSpacesParenthesis.apply(ngData).isFailed();

        // assert
        assertEquals(true, actual);
    }

    @Test
    public void validateSpacesMarkEndHalfStart_Failed_Test() {

        // arrange
        final Sentence ngData = new Sentence("保存しますか?Excelを使用して編集する場合", 0);

        // act
        final boolean actual = validateSpacesMarkEndHalfStart.apply(ngData).isFailed();

        // assert
        assertEquals(true, actual);
    }

    @Test
    public void validateArabicNumeralsFull_Failed_Test() {

        // arrange
        final Sentence ngData = new Sentence("１つ", 0);

        // act
        final boolean actual = validateArabicNumeralsFull.apply(ngData).isFailed();

        // assert
        assertEquals(true, actual);
    }

    @Test
    public void validateChineseNumerals_Failed_Test() {

        // arrange
        final Sentence ngData = new Sentence("一時間十五分", 0);

        // act
        final boolean actual = validateChineseNumerals.apply(ngData).isFailed();

        // assert
        assertEquals(true, actual);
    }

    @Test
    public void validateMeasurementUnits_Failed_Test() {

        // arrange
        final Sentence ngData = new Sentence("1 B", 0);

        // act
        final boolean actual = validateMeasurementUnits.apply(ngData).isFailed();

        // assert
        assertEquals(true, actual);
    }

    @Test
    public void validateMeasurementUnitsWithoutASpace_Failed_Test() {

        // arrange
        final Sentence ngData = new Sentence("50 %", 0);

        // act
        final boolean actual = validateMeasurementUnitsWithoutASpace.apply(ngData).isFailed();

        // assert
        assertEquals(true, actual);
    }

    @Test
    public void validateToneHumbleExpression_Failed_Test() {

        // arrange
        final Sentence ngData = new Sentence("当社", 0);

        // act
        final boolean actual = validateToneHumbleExpression.apply(ngData).isFailed();

        // assert
        assertEquals(true, actual);
    }

    @Test
    public void validateTonePoliteExpression_Failed_Test() {

        // arrange
        final Sentence ngData = new Sentence("ご確認ください", 0);

        // act
        final boolean actual = validateTonePoliteExpression.apply(ngData).isFailed();

        // assert
        assertEquals(true, actual);
    }

    @Test
    public void validateFrequentErrorsFusei_Failed_Test() {

        // arrange
        final Sentence ngData = new Sentence("ファイルが不正です。", 0);

        // act
        final boolean actual = validateFrequentErrorsFusei.apply(ngData).isFailed();

        // assert
        assertEquals(true, actual);
    }

}
