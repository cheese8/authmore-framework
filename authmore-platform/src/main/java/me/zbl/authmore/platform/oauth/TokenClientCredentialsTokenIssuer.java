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

import me.zbl.authmore.ClientDetails;
import me.zbl.authmore.oauth.OAuthUtil;
import me.zbl.authmore.oauth.TokenManager;
import me.zbl.authmore.oauth.TokenResponse;
import org.springframework.stereotype.Component;

import java.util.Set;

import static me.zbl.authmore.oauth.OAuthProperties.GrantTypes.CLIENT_CREDENTIALS;

/**
 * @author ZHENG BAO LE
 * @since 2019-03-03
 */
@Component
public final class TokenClientCredentialsTokenIssuer {

    private final TokenManager tokenManager;

    public TokenClientCredentialsTokenIssuer(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    public TokenResponse issue(ClientDetails client, String scope) {
        OAuthUtil.validateClientAndGrantType(client, CLIENT_CREDENTIALS);
        OAuthUtil.validateClientAndScope(client, scope);
        Set<String> scopes = OAuthUtil.scopeSet(scope);
        return tokenManager.create(client, null, scopes);
    }
}
