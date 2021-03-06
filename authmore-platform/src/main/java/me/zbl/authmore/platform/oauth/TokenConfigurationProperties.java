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

package me.zbl.authmore.platform.oauth;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import static org.springframework.util.StringUtils.isEmpty;

/**
 * @author ZHENG BAO LE
 * @since 2019-05-30
 */
@Component
@ConfigurationProperties(prefix = "token")
public class TokenConfigurationProperties {

    private final static String ENV_TOKEN_TYPE = "token_type";

    private String tokenType = System.getenv(ENV_TOKEN_TYPE);

    enum TokenPolicy {
        REDIS, JWT
    }

    private TokenPolicy policy;

    public TokenPolicy getPolicy() {
        String tokenTypeName = getTokenTypeName();
        if (isEmpty(tokenTypeName)) {
            return policy;
        }
        switch (tokenTypeName) {
            case "jwt":
                return TokenPolicy.JWT;
            case "redis":
                return TokenPolicy.REDIS;
            default:
                return policy;
        }
    }

    private String getTokenTypeName() {
        String name = null;
        if (!isEmpty(tokenType)) {
            name = tokenType.toLowerCase();
        }
        return name;
    }

    public void setPolicy(TokenPolicy policy) {
        this.policy = policy;
    }
}
