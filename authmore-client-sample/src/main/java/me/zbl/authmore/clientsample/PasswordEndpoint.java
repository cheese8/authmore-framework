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
package me.zbl.authmore.clientsample;

import me.zbl.authmore.client.ClientRestTemplate;
import me.zbl.authmore.client.PasswordTokenManager;
import me.zbl.authmore.oauth.TokenResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZHENG BAO LE
 * @since 2019-03-01
 */
@RestController
public class PasswordEndpoint {

    private final PasswordTokenManager passwordTokenManager;

    public PasswordEndpoint(PasswordTokenManager passwordTokenManager) {
        this.passwordTokenManager = passwordTokenManager;
    }

    @GetMapping(value = "/password", produces = {"application/json"})
    public Object password() {
        Map<String, String> params = new HashMap<>();
        params.put("username", "james");
        params.put("password", "123456");
        TokenResponse token = passwordTokenManager.getToken("EMAIL", params);
        RestTemplate template = new ClientRestTemplate(token.getAccess_token());
        return template.getForObject("http://resource.authmore/inbox", String.class);
    }
}
