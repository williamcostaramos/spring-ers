package com.williamramos.cursoalgaworks.core.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.williamramos.cursoalgaworks.domain.model.Cozinha;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {
    public JacksonMixinModule() {
    }
}
