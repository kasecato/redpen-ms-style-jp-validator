package cc.redpen.validator.sentence;

import cc.redpen.model.Sentence;
import cc.redpen.validator.Validator;
import cc.redpen.validator.sentence.domain.style.MSStyleJP;

import java.util.List;
import java.util.Locale;

import static java.util.Collections.singletonList;

public class MSStyleJPValidator extends Validator implements MSStyleJP {

    @Override
    public List<String> getSupportedLanguages() {
        return singletonList(Locale.JAPANESE.getLanguage());
    }

    @Override
    public void validate(Sentence sentence) {

        validateMSStyle(sentence)
                .stream()
                .forEach(result
                        -> result.getErrorPositions()
                        .stream()
                        .forEach(position
                                -> addErrorWithPosition(result.getErrorMessage(), sentence, position.row, position.col)));

    }

}
