package cc.redpen.validator.sentence.domain.style;

import cc.redpen.model.Sentence;
import cc.redpen.validator.sentence.domain.model.ErrorPattern;
import cc.redpen.validator.sentence.domain.model.ValidationResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.regex.Matcher;

public interface MSStyleJP {


    default List<ValidationResult> validateMSStyle(Sentence sentence) {

        List<ValidationResult> results = new ArrayList<>();

        validators
                .stream()
                .parallel()
                .forEach(validator
                        -> results.add(validator.apply(sentence)));

        return results;
    }


    /*------------------------------------------------------------------------------------------------------------------
     * Common
     *----------------------------------------------------------------------------------------------------------------*/
    BiPredicate<String, String> matches = (sentence, pattern)
            -> sentence.matches(String.format(".*%s.*", pattern));


    BiFunction<Sentence, ErrorPattern, ValidationResult> valid = (sentence, pattern)
            -> {

        ValidationResult result = ValidationResult.create();

        final boolean valid = matches.negate().test(sentence.getContent(), pattern.getErrorPattern().pattern());
        if (valid) {
            return result;
        }

        result.setErrorMessage(pattern.getErrorMessage());
        final Matcher matcher = pattern.getErrorPattern().matcher(sentence.getContent());
        while (matcher.find()) {
            result.addErrorPosition(matcher.start(), matcher.end());
        }

        return result;

    };

    /*------------------------------------------------------------------------------------------------------------------
     * Validator
     *----------------------------------------------------------------------------------------------------------------*/

    /**
     * Full-width characters should be used unless it is necessary to use half-width characters.
     */
    Function<Sentence, ValidationResult> validateKatakana = sentence
            -> valid.apply(
            sentence,
            Error.Katakana.HALF_WIDTH
    );


    /**
     * Characters pronounced as “ka” should be written in Hiragana or Kanji.
     */
    Function<Sentence, ValidationResult> validateKatakanaKa = (sentence)
            -> valid.apply(
            sentence,
            Error.Katakana.KA
    );

    /**
     * Characters pronounced as “ko” should be written in Hiragana or Kanji.
     */
    Function<Sentence, ValidationResult> validateKatakanaKo = (sentence)
            -> valid.apply(
            sentence,
            Error.Katakana.KO
    );

    /**
     * In principle, use a long vowel when a source English term has following suffixes: -er, -or, -ar
     * In other cases, use a long vowel when a corresponding Katakana word has less than 4 characters. Note that the long vowel should be counted while geminative consonant (Sokuon, small “tu”, ッ), contracted sound (Yoon, small “ya”, “yu”, “yo”, ャ, ュ, ョ) and small a, i, u (ァ, ィ, ゥ) should not.
     * When the English term consists of a prefix and a stem word, the words should be considered one by one.
     */
    Function<Sentence, ValidationResult> validateLongVowel = (sentence)
            -> null;


    /**
     * Half-width characters should be used unless it is necessary to use full-width characters.
     * If necessary, words can be hyphenated at the end of a line. Make sure to check dictionary for syllables. Do
     * not hyphenate trademarks and names of product, company, file and path.
     */
    Function<Sentence, ValidationResult> validateEnglishLetters = (sentence)
            -> valid.apply(
            sentence,
            Error.EnglishLetters.FULL_WIDTH
    );

    /**
     * Between characters, a space should be inserted as follows.
     * Between full-width and half-width characters.
     */
    Function<Sentence, ValidationResult> validateSpacesFullHalf = (sentence)
            -> valid.apply(
            sentence,
            Error.Spaces.FULL_HALF
    );

    /**
     * Between characters, a space should be inserted as follows.
     * Between full-width and half-width characters.
     * <p>
     * Exceptionally, do not insert a space in the following cases:
     * Between ideographic full stop (。) and a half-width character.
     */
    Function<Sentence, ValidationResult> validateSpacesFullStop = (sentence)
            -> valid.apply(
            sentence,
            Error.Spaces.FULL_STOP
    );

    /**
     * Between characters, a space should be inserted as follows.
     * Between full-width and half-width characters.
     * <p>
     * Exceptionally, do not insert a space in the following cases:
     * Between ideographic ideographic comma (、) and a half-width character.
     */
    Function<Sentence, ValidationResult> validateSpacesComma = (sentence)
            -> valid.apply(
            sentence,
            Error.Spaces.COMMA
    );

    /**
     * Between characters, a space should be inserted as follows.
     * Between full-width and half-width characters.
     * <p>
     * Exceptionally, do not insert a space in the following cases:
     * Between a numeral and unit of angle (°).
     */
    Function<Sentence, ValidationResult> validateSpacesAngle = (sentence)
            -> valid.apply(
            sentence,
            Error.Spaces.ANGLE
    );

    /**
     * Between characters, a space should be inserted as follows.
     * Between full-width and half-width characters.
     * <p>
     * Exceptionally, do not insert a space in the following cases:
     * On each side of text enclosed by parentheses.
     */
    Function<Sentence, ValidationResult> validateSpacesParentheses = (sentence)
            -> valid.apply(
            sentence,
            Error.Spaces.PARENTHESES
    );

    /**
     * Between characters, a space should be inserted as follows.
     * Between full-width and half-width characters.
     * <p>
     * Exceptionally, do not insert a space in the following cases:
     * On each side of text enclosed by quotation marks.
     */
    Function<Sentence, ValidationResult> validateSpacesQuotationMarks = (sentence)
            -> valid.apply(
            sentence,
            Error.Spaces.QUOTATION_MARKS
    );

    /**
     * Between characters, a space should be inserted as follows.
     * Between full-width and half-width characters.
     * <p>
     * Exceptionally, do not insert a space in the following cases:
     * On each side of text enclosed by brackets.
     */
    Function<Sentence, ValidationResult> validateSpacesBrackets = (sentence)
            -> valid.apply(
            sentence,
            Error.Spaces.BRACKETS
    );

    /**
     * Between characters, a space should be inserted as follows.
     * Between full-width and half-width characters.
     * <p>
     * Exceptionally, do not insert a space in the following cases:
     * On each side of slash.
     */
    Function<Sentence, ValidationResult> validateSpacesSlash = (sentence)
            -> valid.apply(
            sentence,
            Error.Spaces.SLASH
    );

    /**
     * Between characters, a space should be inserted as follows.
     * Between full-width and half-width characters.
     * <p>
     * Exceptionally, do not insert a space in the following cases:
     * Between a full-width character and question mark (?)/exclamation mark (!)/colon (:)/ellipses (...) that ends the term.
     */
    Function<Sentence, ValidationResult> validateSpacesFullWidth = (sentence)
            -> valid.apply(
            sentence,
            Error.Spaces.FULL_WIDTH
    );

    /**
     * Between characters, a space should be inserted as follows.
     * Between full-width and half-width characters.
     * <p>
     * Exceptionally, do not insert a space in the following cases:
     * Between a character and access key enclosed by parentheses in the user interface.
     */
    Function<Sentence, ValidationResult> validateSpacesAccessKey = (sentence)
            -> valid.apply(
            sentence,
            Error.Spaces.ACCESS_KEY
    );


    /**
     * Between half-width characters.
     * In principle, do not insert a space.
     *
     * Exceptionally, insert a space in the following cases:
     * Between a numeral and a measurement unit, except for % and mm (in the context of photograph/projection).
     */
    /*
    Function<Sentence, ValidationResult> validateSpacesMeasurementUnit = (sentence)
            -> valid.apply(
            sentence,
            Error.Spaces.MEASUREMENT_UNIT
    );
    */

    /**
     * Between half-width characters.
     * In principle, do not insert a space.
     * <p>
     * Exceptionally, insert a space in the following cases:
     * Between a half-width parenthesis and half-width character outside, except for parentheses enclosing access keys in the user interface or trademark symbols.
     */
    Function<Sentence, ValidationResult> validateSpacesParenthesis = (sentence)
            -> valid.apply(
            sentence,
            Error.Spaces.PARENTHESIS
    );

    /**
     * Between half-width characters.
     * In principle, do not insert a space.
     * <p>
     * Exceptionally, insert a space in the following cases:
     * Between question mark/exclamation mark that ends the term and a half-width character that starts the next term.
     */
    Function<Sentence, ValidationResult> validateSpacesMarkEndHalfStart = (sentence)
            -> valid.apply(
            sentence,
            Error.Spaces.MARK_END_HALF_START
    );

    /**
     * Numbers can be written with Arabic and Chinese numerals.
     * Arabic numerals should be half-width unless it is necessary to use full-width numerals.
     */
    Function<Sentence, ValidationResult> validateArabicNumeralsFull = (sentence)
            -> valid.apply(
            sentence,
            Error.Numbers.ARABIC_NUMERALS_FULL
    );

    /**
     * Numbers can be written with Arabic and Chinese numerals.
     * Use Arabic numerals when the numeral can be replaced by other numerals.
     */
    Function<Sentence, ValidationResult> validateChineseNumerals = (sentence)
            -> valid.apply(
            sentence,
            Error.Numbers.CHINESE_NUMERALS
    );

    /**
     * Measurement units.
     */
    Function<Sentence, ValidationResult> validateMeasurementUnits = (sentence)
            -> valid.apply(
            sentence,
            Error.Numbers.MEASUREMENT_UNITS
    );

    /**
     * Measurement units.
     * Insert a space between the number and the unit except for ones listed under the category “Units without a space” (°, % and mm).
     */
    Function<Sentence, ValidationResult> validateMeasurementUnitsWithoutASpace = (sentence)
            -> valid.apply(
            sentence,
            Error.Numbers.MEASUREMENT_UNITS_WITHOUT_A_SPACE
    );


    List<Function<Sentence, ValidationResult>> validators = Arrays.asList(
            validateKatakana,
            validateKatakanaKa,
            validateKatakanaKo,
            //validateLongVowel,
            validateEnglishLetters,
            validateSpacesFullHalf,
            validateSpacesFullStop,
            validateSpacesComma,
            validateSpacesAngle,
            validateSpacesParentheses,
            validateSpacesQuotationMarks,
            validateSpacesBrackets,
            validateSpacesSlash,
            validateSpacesFullWidth,
            validateSpacesAccessKey,
            validateSpacesParenthesis,
            validateSpacesMarkEndHalfStart,
            validateArabicNumeralsFull,
            validateChineseNumerals,
            validateMeasurementUnits,
            validateMeasurementUnitsWithoutASpace
    );

    /*------------------------------------------------------------------------------------------------------------------
     * Regex
     *----------------------------------------------------------------------------------------------------------------*/

    interface Error {

        interface Katakana {

            ErrorPattern HALF_WIDTH = ErrorPattern.create(
                    "[｡-ﾟ]+",
                    "Full-width characters should be used unless it is necessary to use half-width characters."
            );
            ErrorPattern KA = ErrorPattern.create(
                    "\\d+ (ケ|ヶ|カ|ヵ)(年|月)",
                    "Characters pronounced as “ka” should be written in Hiragana or Kanji."
            );
            ErrorPattern KO = ErrorPattern.create(
                    "\\d+ (ケ|コ)",
                    "Characters pronounced as “ko” should be written in Hiragana or Kanji."
            );
        }

        interface EnglishLetters {

            ErrorPattern FULL_WIDTH = ErrorPattern.create(
                    "[Ａ-ｚ０-９]+",
                    "Half-width characters should be used unless it is necessary to use full-width characters."
            );
        }

        interface Spaces {

            ErrorPattern FULL_HALF = ErrorPattern.create(
                    "([!-~][^ -~]|[^ -~][!-~])",
                    "Between characters, a space should be inserted between full-width and half-width characters."
            );
            ErrorPattern FULL_STOP = ErrorPattern.create(
                    "([。．] | [。．])",
                    "Do not insert a space in ideographic full stop (。) and a half-width character."
            );
            ErrorPattern COMMA = ErrorPattern.create(
                    "([、，] | [、，])",
                    "Do not insert a space in ideographic ideographic comma (、) and a half-width character."
            );
            ErrorPattern ANGLE = ErrorPattern.create(
                    "\\d+ [°]",
                    "Do not insert a space in a numeral and unit of angle (°)."
            );
            ErrorPattern PARENTHESES = ErrorPattern.create(
                    "(\\( | \\( |\\) | \\))",
                    "Do not insert a space in each side of text enclosed by parentheses."
            );
            ErrorPattern QUOTATION_MARKS = ErrorPattern.create(
                    "( \\?)",
                    "Do not insert a space in each side of text enclosed by quotation marks."
            );
            ErrorPattern BRACKETS = ErrorPattern.create(
                    "([\\{\\[\\<「] | [\\{\\[\\<「]|[\\}\\]\\>」] | [\\}\\]\\>」])",
                    "Do not insert a space in each side of text enclosed by brackets."
            );
            ErrorPattern SLASH = ErrorPattern.create(
                    "(/ | /)",
                    "Do not insert a space in each side of slash."
            );
            ErrorPattern FULL_WIDTH = ErrorPattern.create(
                    "( [？！：…])",
                    "Do not insert a space in a full-width character and question mark (?)/exclamation mark (!)/colon (:)/ellipses (...) that ends the term."
            );
            ErrorPattern ACCESS_KEY = ErrorPattern.create(
                    " \\([A-Z]\\)",
                    "Do not insert a space in a character and access key enclosed by parentheses in the user interface."
            );
            /*Duplicated: Numbers.MEASUREMENT_UNITS_WITHOUT_A_SPACE*/
            //ErrorPattern MEASUREMENT_UNIT = ErrorPattern.create(
            //        " (%|mm)",
            //        "Do not insert a space in % and mm (in the context of photograph/projection)."
            //);
            ErrorPattern PARENTHESIS = ErrorPattern.create(
                    "([!-~]\\(|\\)[!-~])",
                    "Do not insert a space in between a half-width parenthesis and half-width character outside."
            );
            ErrorPattern MARK_END_HALF_START = ErrorPattern.create(
                    "([!！?？][!-~])",
                    "Do not insert a space in Between question mark/exclamation mark that ends the term and a half-width character that starts the next term."
            );
        }

        interface Numbers {

            ErrorPattern ARABIC_NUMERALS_FULL = ErrorPattern.create(
                    "([０-９]+)",
                    "Arabic numerals should be half-width unless it is necessary to use full-width numerals."
            );
            ErrorPattern CHINESE_NUMERALS = ErrorPattern.create(
                    "([一二三四五六七八九十壱弐参拾百千万萬億兆〇]+)",
                    "Use Arabic numerals when the numeral can be replaced by other numerals."
            );
            ErrorPattern MEASUREMENT_UNITS = ErrorPattern.create(
                    "(\\d+ (キロメートル|メートル|デシメートル|センチメートル|ミリメートル|ミリ|ヘクトリットル|リットル|デシリットル|センチリットル|ミリリットル|トン|キログラム|lb|グラム|デシグラム|センチグラム|ミリグラム|in|ft|mi|gal|テラバイト|ギガバイト|メガバイト|キロバイト|B|b|ビット/秒|ギガヘルツ|メガヘルツ|キロヘルツ|ヘルツ|ドット|ドット/インチ|ドット/インチ|sec|ms))",
                    "Do not use the measurement unit unless necessary."
            );
            ErrorPattern MEASUREMENT_UNITS_WITHOUT_A_SPACE = ErrorPattern.create(
                    "(\\d (°|%|mm))",
                    "Insert a space between the number and the unit except for ones listed under the category “Units without a space” (°, % and mm)."
            );

        }


    }

}
