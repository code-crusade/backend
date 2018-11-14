package com.etsmtl.codecrusade.i18n;

import com.etsmtl.codecrusade.repository.MessageRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractMessageSource;

import java.text.MessageFormat;
import java.util.Locale;

/**
 * A message source that resolves messages from a database.
 * <p>
 * see https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/support/AbstractMessageSource.html
 * for details.
 * </p>
 */
@Log4j2
public class DatabaseMessageSource extends AbstractMessageSource {

    private MessageRepository messageRepository;

    @Autowired
    public DatabaseMessageSource(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
        this.setUseCodeAsDefaultMessage(true);
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        return messageRepository.findById(code)
                                .map(found -> createMessageFormat(found.getLocalizations().get(locale.toLanguageTag()), locale))
                                .orElse(createMessageFormat(getDefaultMessage(code), locale));
    }

    @Override
    protected String resolveCodeWithoutArguments(String code, Locale locale) {
        return messageRepository.findById(code)
                                .map(found -> found.getLocalizations().get(locale.toLanguageTag()))
                                .orElse(getDefaultMessage(code));
    }
}
