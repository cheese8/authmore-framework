/*
 * Copyright 2019 ZHENG BAO LE
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.zbl.authmore.configuration;

import me.zbl.authmore.main.ClientClientCredentialsTokenManager;
import me.zbl.authmore.main.ClientConfigurationProperties;
import me.zbl.authmore.main.ClientRestTemplate;
import me.zbl.authmore.main.TokenResponse;
import me.zbl.reactivesecurity.common.Assert;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Collections;

/**
 * @author ZHENG BAO LE
 * @since 2019-03-04
 */
@Configuration
@Import({TokenManagerAutoConfiguration.class})
@EnableConfigurationProperties({ClientConfigurationProperties.class})
public class RestTemplateAutoConfiguration {

    private final boolean isRequestTokenOnStartup;
    private final String scope;
    private final ClientClientCredentialsTokenManager tokenManager;

    public RestTemplateAutoConfiguration(
            ClientConfigurationProperties clientConfigurationProperties,
            ClientClientCredentialsTokenManager tokenManager) {
        this.isRequestTokenOnStartup = clientConfigurationProperties.isRequestTokenOnStartup();
        this.scope = clientConfigurationProperties.getScope();
        this.tokenManager = tokenManager;
    }

    @Bean
    @ConditionalOnMissingBean({ClientRestTemplate.class})
    public ClientRestTemplate clientRestTemplate() {
        if (isRequestTokenOnStartup) {
            Assert.notEmpty(scope, "at least 1 scope is required when requesting token on startup");
            TokenResponse token = tokenManager.getToken(scope, Collections.emptyMap());
            String accessToken = token.getAccess_token();
            return new ClientRestTemplate(accessToken);
        }
        return new ClientRestTemplate();
    }
}
