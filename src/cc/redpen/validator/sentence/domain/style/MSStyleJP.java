package cc.redpen.validator.sentence.domain.style;

import cc.redpen.model.Sentence;
import cc.redpen.validator.sentence.domain.model.ErrorPattern;
import cc.redpen.validator.sentence.domain.model.ValidationResult;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

public interface MSStyleJP {


    default List<ValidationResult> validateMSStyle(final Sentence sentence) {

        return validators
                .stream()
                //.parallel()
                .map(v -> v.apply(sentence))
                .collect(Collectors.toList());
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
            Error.Characters.Katakana.HALF_WIDTH
    );


    /**
     * Characters pronounced as “ka” should be written in Hiragana or Kanji.
     */
    Function<Sentence, ValidationResult> validateKatakanaKa = (sentence)
            -> valid.apply(
            sentence,
            Error.Characters.Katakana.KA
    );

    /**
     * Characters pronounced as “ko” should be written in Hiragana or Kanji.
     */
    Function<Sentence, ValidationResult> validateKatakanaKo = (sentence)
            -> valid.apply(
            sentence,
            Error.Characters.Katakana.KO
    );

    /**
     * In principle, use a long vowel when a source English term has following suffixes: -er, -or, -ar
     * In other cases, use a long vowel when a corresponding Katakana word has less than 4 characters. Note that the long vowel should be counted while geminative consonant (Sokuon, small “tu”, ッ), contracted sound (Yoon, small “ya”, “yu”, “yo”, ャ, ュ, ョ) and small a, i, u (ァ, ィ, ゥ) should not.
     * When the English term consists of a prefix and a stem word, the words should be considered one by one.
     */
    Function<Sentence, ValidationResult> validateLongVowel = (sentence)
            -> null;

    /**
     * In principle, use a long vowel when a source English term has following suffixes: -er, -or, -ar
     * In other cases, use a long vowel when a corresponding Katakana word has less than 4 characters. Note that the long vowel should be counted while geminative consonant (Sokuon, small “tu”, ッ), contracted sound (Yoon, small “ya”, “yu”, “yo”, ャ, ュ, ョ) and small a, i, u (ァ, ィ, ゥ) should not.
     * When the English term consists of a prefix and a stem word, the words should be considered one by one.
     * <p>
     * Use a long vowel when a source English term has following terms should be handled differently.
     */
    Function<Sentence, ValidationResult> validateLongVowelExceptionUse = (sentence)
            -> valid.apply(
            sentence,
            Error.Characters.LongVowel.EXCEPTION_USE
    );


    /**
     * In principle, use a long vowel when a source English term has following suffixes: -er, -or, -ar
     * In other cases, use a long vowel when a corresponding Katakana word has less than 4 characters. Note that the long vowel should be counted while geminative consonant (Sokuon, small “tu”, ッ), contracted sound (Yoon, small “ya”, “yu”, “yo”, ャ, ュ, ョ) and small a, i, u (ァ, ィ, ゥ) should not.
     * When the English term consists of a prefix and a stem word, the words should be considered one by one.
     * <p>
     * Do not use a long vowel when a source English term has following terms should be handled differently.
     */
    Function<Sentence, ValidationResult> validateLongVowelExceptionNotUse = (sentence)
            -> valid.apply(
            sentence,
            Error.Characters.LongVowel.EXCEPTION_NOT_USE
    );

    /**
     * Half-width characters should be used unless it is necessary to use full-width characters.
     * If necessary, words can be hyphenated at the end of a line. Make sure to check dictionary for syllables. Do
     * not hyphenate trademarks and names of product, company, file and path.
     */
    Function<Sentence, ValidationResult> validateEnglishLetters = (sentence)
            -> valid.apply(
            sentence,
            Error.Characters.EnglishLetters.FULL_WIDTH
    );

    /**
     * Between characters, a space should be inserted as follows.
     * Between full-width and half-width characters.
     */
    Function<Sentence, ValidationResult> validateSpacesFullHalf = (sentence)
            -> valid.apply(
            sentence,
            Error.Characters.Spaces.FULL_HALF
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
            Error.Characters.Spaces.FULL_STOP
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
            Error.Characters.Spaces.COMMA
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
            Error.Characters.Spaces.ANGLE
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
            Error.Characters.Spaces.PARENTHESES
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
            Error.Characters.Spaces.QUOTATION_MARKS
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
            Error.Characters.Spaces.BRACKETS
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
            Error.Characters.Spaces.SLASH
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
            Error.Characters.Spaces.FULL_WIDTH
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
            Error.Characters.Spaces.ACCESS_KEY
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
            Error.Characters.Spaces.MEASUREMENT_UNIT
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
            Error.Characters.Spaces.PARENTHESIS
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
            Error.Characters.Spaces.MARK_END_HALF_START
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


    /**
     * Humble expression (謙譲語).
     * In general, do not use honorific expression and humble expression.
     * In materials in which “supplier-customer” relationship should be considered, use 弊社.
     */
    Function<Sentence, ValidationResult> validateToneHumbleExpression = (sentence)
            -> valid.apply(
            sentence,
            Error.Tone.HUMBLE_EXPRESSION
    );

    /**
     * Polite expression (丁寧語).
     * Do not use excessively polite expression unless necessary.
     */
    Function<Sentence, ValidationResult> validateTonePoliteExpression = (sentence)
            -> valid.apply(
            sentence,
            Error.Tone.POLITE_EXPRESSION
    );

    /**
     * Frequent Errors.
     * Avoid using the term “不正” unless it pertains to something prohibited by law.
     */
    Function<Sentence, ValidationResult> validateFrequentErrorsFusei = (sentence)
            -> valid.apply(
            sentence,
            Error.FrequentErrors.Fusei
    );

    List<Function<Sentence, ValidationResult>> validators = Arrays.asList(
            validateKatakana,
            validateKatakanaKa,
            validateKatakanaKo,
            //validateLongVowel,
            validateLongVowelExceptionUse,
            validateLongVowelExceptionNotUse,
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
            //validateChineseNumerals,
            validateMeasurementUnits,
            validateMeasurementUnitsWithoutASpace,
            validateToneHumbleExpression,
            validateTonePoliteExpression,
            validateFrequentErrorsFusei
    );

    /*------------------------------------------------------------------------------------------------------------------
     * Regex
     *----------------------------------------------------------------------------------------------------------------*/

    interface Error {

        interface Characters {

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

            interface LongVowel {

                ErrorPattern EXCEPTION_USE = ErrorPattern.create(
                        "(アカデミ|アドベンチャ|アレルギ|アスキ|バルコニ|バーベキュ|バースデ|ブルーベリ|カロリ|セレモニ|チータ|チンパンジ|コーヒ|カンパニ|コンピテンシ|マホガニ|マーキ|ムービ|ミステリ|ネイチャ|ネービ|アウトロ|ペイズリ|パンジ|パスキ|パススル|ペッカリ|フォトグラフィ|ポリシ|プレーリ|パブリシティ|クランベリ|デイリ|ドルビ|ドリルスル|イージ|エコノミ|エナジ|エネルギ|ファンシ|ファンタジ|フロッピ|フリークエンシ|ギャラリ|ハーモニ|ヘルシ|ヒーロ|ホットキ|ハウツ|ラズベリ|ランデブ|レスキュ|ロータリ|シーナリ|スクリュ|シーソ|シャンプ|スプレ|ストーリ|ストロベリ|サマリ|シナジ|タクシ|テンキ|タイムリ|トレジャ|トロリ|トロフィ|バリュ|インタビュ|ベンチャ|ジュエリ|ビクトリ|カンガル|ウィスキ|ワークフロ|ラグジュアリ)[^ー]",
                        "Use a long vowel when a source English term has following terms should be handled differently."
                );

                ErrorPattern EXCEPTION_NOT_USE = ErrorPattern.create(
                        "(アクセラレータ|バリア|バザール|ベア|ビール|キャリア|センチメートル|クリア|コンパイラ|コネクタ|コンベヤ|メートル|ミリメートル|アウトドア|ピア|ポリエステル|プレミア|プロセッサ|プログラマ|プロペラ|ラジエータ|ドル|ドア|エンジニア|エクステリア|フロア|フォーマッタ|フロンティア|ギア|ユーモア|リア|レジスタ|スケジューラ|シニア|スリッパ|ステラ|タール|ターミネータ|トランジスタ|インドア|インテリア|ジュニア|ボランティア|リニア)[ー]",
                        "Do not use a long vowel when a source English term has following terms should be handled differently."
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
                        "([!-~][^ -~，、。．　『』「」（）【】｛｝〈〉‘’“”《》/／：・]|[^ -~，、。．　『』「」（）【】｛｝〈〉‘’“”《》/／：・][!-~])",
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

        interface Tone {

            ErrorPattern HUMBLE_EXPRESSION = ErrorPattern.create(
                    "(当社|我社)",
                    "In materials in which “supplier-customer” relationship should be considered, use 弊社."
            );
            ErrorPattern POLITE_EXPRESSION = ErrorPattern.create(
                    "(ご注意ください|ご確認ください)",
                    "Do not use excessively polite expression unless necessary."
            );
        }

        interface FrequentErrors {

            ErrorPattern Fusei = ErrorPattern.create(
                    "(不正)",
                    "Avoid using the term “不正” unless it pertains to something prohibited by law."
            );

        }


    }

}
