package cc.redpen.validator.sentence;

import cc.redpen.model.Sentence;
import cc.redpen.validator.Validator;
import cc.redpen.validator.sentence.domain.style.MSStyleJP;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class MSStyleJPValidator extends Validator implements MSStyleJP {

    @Override
    public List<String> getSupportedLanguages() {
        return Collections.singletonList(Locale.JAPANESE.getLanguage());
    }

    @Override
    public void validate(final Sentence sentence) {

        validateMSStyle(sentence)
                .stream()
                .parallel()
                .forEach(result -> result.getErrorPositions().stream()
                        .forEach(position -> addErrorWithPosition(result.getErrorMessage(), sentence, position.row, position.col)));

    }

}
