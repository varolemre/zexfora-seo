package com.codingbytime.zexfora.control.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class MessageResolver {
    private final MessageSource messageSource;

    public String getMessage(String value) {
        return messageSource.getMessage(value, null, LocaleContextHolder.getLocale());
    }

    public String getMessage(String value, Locale locale) {
        return messageSource.getMessage(value, null, locale);
    }
}
