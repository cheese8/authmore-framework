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

import me.zbl.authmore.client.AuthorizationTemplate;
import me.zbl.authmore.client.ClientConfigurationProperties;
import me.zbl.authmore.client.ClientRestTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

/**
 * @author ZHENG BAO LE
 * @since 2019-03-07
 */
@Configuration
@ConditionalOnClass({ClientRestTemplate.class})
@ComponentScan("me.zbl.authmore.authorization")
@EnableConfigurationProperties({ClientConfigurationProperties.class})
public class AuthorizeAutoConfiguration {

    private final ClientConfigurationProperties clientProperties;

    public AuthorizeAutoConfiguration(ClientConfigurationProperties clientProperties) {
        this.clientProperties = clientProperties;
    }

    @Bean
    @ConditionalOnMissingBean({AuthorizationTemplate.class})
    public AuthorizationTemplate authorizationTemplate() {
        return new AuthorizationTemplate(clientProperties);
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return authentication -> null;
    }
}
