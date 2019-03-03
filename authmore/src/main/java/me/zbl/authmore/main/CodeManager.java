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

import me.zbl.authmore.core.ClientDetails;

import java.util.Set;

/**
 * @author JamesZBL
 * @since 2019-02-18
 */
public interface CodeManager {

    void saveCodeBinding(ClientDetails client, String code, Set<String> scopes, String redirectUri, String userId);

    CodeBinding getCodeDetails(String clientId, String code);

    void expireCode(String code);
}
