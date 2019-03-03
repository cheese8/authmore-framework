/*
 * Copyright 2019 ZHENG BAO LE
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package me.zbl.authmore.main;

import me.zbl.authmore.main.OAuthProperties.*;
import me.zbl.reactivesecurity.common.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static me.zbl.authmore.main.OAuthProperties.*;

/**
 * @author JamesZBL
 * @since 2019-03-02
 */
public abstract class ClientAbstractTokenManager implements ClientTokenOperations {

    private final RestTemplate client;
    private final String clientId;
    private final String clientSecret;
    private final String tokenIssueUrl;

    ClientAbstractTokenManager(
            RestTemplate client,
            String clientId,
            String clientSecret,
            String tokenIssueUrl) {
        this.client = client;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.tokenIssueUrl = tokenIssueUrl;
    }

    private String queryUrlWithParams(Map<String, String> params) {
        return tokenIssueUrl + "?" + RequestUtil.queryStringOf(params);
    }

    @Override
    public final TokenResponse getToken(String scope, Map<String, String> restParams) {
        restParams.put(PARAM_SCOPE, scope);
        enhanceQueryParams(restParams);
        String queryUrlWithParams = queryUrlWithParams(restParams);
        return client.postForObject(queryUrlWithParams, null, TokenResponse.class);
    }

    protected void enhanceQueryParams(Map<String, String> params) {
        String scope = params.get(PARAM_SCOPE);
        Assert.notEmpty(scope, "scope cannot be empty");
        Assert.notEmpty(clientId, "client_id cannot be empty");
        Assert.notEmpty(clientSecret, "client_secret cannot be empty");
        params.put(PARAM_CLIENT_ID, clientId);
        params.put(PARAM_CLIENT_SECRET, clientSecret);
        params.put(PARAM_GRANT_TYPE, getGrantType().getName());
    }

    protected abstract GrantTypes getGrantType();
}
