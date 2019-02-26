/*
 * Copyright 2019 JamesZBL
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
package me.zbl.authmore;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JamesZBL
 * @since 2019-02-26
 */
@RestController
public class TokenCheckEndpoint {

    private final TokenManager tokenManager;

    public TokenCheckEndpoint(TokenManager tokenManager) {this.tokenManager = tokenManager;}

    @GetMapping("/oauth/check_token")
    public TokenCheckResponse checkToken(@RequestParam("token") String token) {
        AccessTokenBinding accessTokenBinding = tokenManager.find(token);
        return null;
    }
}
