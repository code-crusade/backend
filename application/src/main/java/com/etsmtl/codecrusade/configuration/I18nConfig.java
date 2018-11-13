package com.etsmtl.codecrusade.configuration;

import com.etsmtl.codecrusade.i18n.DatabaseMessageSource;
import com.etsmtl.codecrusade.repository.MessageRepository;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configurations for internationalization.
 */
@Configuration
public class I18nConfig {
	@Bean
	public MessageSource messageSource(MessageRepository messageRepository){
		return new DatabaseMessageSource(messageRepository);
	}
}
