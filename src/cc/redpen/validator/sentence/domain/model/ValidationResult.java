package cc.redpen.validator.sentence.domain.model;

import cc.redpen.parser.latex.Position;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {

    @Getter
    private final List<Position> errorPositions;

    @Getter
    @Setter
    private String errorMessage;

    private ValidationResult() {
        this.errorPositions = new ArrayList<>();
        this.errorMessage = null;
    }

    public static ValidationResult create() {
        return new ValidationResult();
    }

    public ValidationResult addErrorPosition(final int start, final int end) {
        errorPositions.add(new Position(start, end));
        return this;
    }

    public boolean isFailed() {
        return 0 < errorPositions.size();
    }

    public boolean isSucceed() {
        return 0 == errorPositions.size();
    }

}
