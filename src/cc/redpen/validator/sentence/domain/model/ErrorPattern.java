package cc.redpen.validator.sentence.domain.model;

import lombok.Getter;

import java.util.regex.Pattern;

public class ErrorPattern {

    @Getter
    private final Pattern errorPattern;

    @Getter
    private final String errorMessage;

    private ErrorPattern(final String errorPattern, final String errorMessage) {
        this.errorPattern = Pattern.compile(errorPattern);
        this.errorMessage = errorMessage;
    }

    public static ErrorPattern create(final String pattern, final String errorMessage) {
        return new ErrorPattern(pattern, errorMessage);
    }

}
