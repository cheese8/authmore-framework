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
package me.zbl.authmore.authorization;

import me.zbl.authmore.ClientDetails;
import me.zbl.authmore.UserDetails;
import me.zbl.authmore.oauth.OAuthException;

/**
 * @author ZHENG BAO LE
 * @since 2019-02-15
 */
public interface AuthenticationManager {

    UserDetails userValidate(String principal, String credential) throws AuthenticationException;

    ClientDetails clientValidate(String clientId, String scope) throws OAuthException;

    ClientDetails clientValidate(String clientId, String redirectUri, String scope) throws AuthorizationException;
}
