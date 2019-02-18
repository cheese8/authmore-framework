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

import me.zbl.reactivesecurity.auth.client.ClientDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

import static me.zbl.authmore.SessionProperties.CURRENT_CLIENT;

/**
 * @author JamesZBL
 * created at 2019-02-14
 */
@Controller
public class AuthorizationEndpoint {

    private AuthenticationManager authenticationManager;

    public AuthorizationEndpoint(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/authorize")
    public String authorize(
            @RequestParam("client_id") String clientId,
            @RequestParam("response_type") String responseType,
            @RequestParam("redirect_uri") String redirectUri,
            @RequestParam(value = "scope", required = false) String scope,
            @RequestParam(value = "state", required = false) String state,
            HttpSession session,
            Model model) {
        ClientDetails client = authenticationManager.clientValidate(clientId, redirectUri, scope);
        session.setAttribute(CURRENT_CLIENT, client);
        model.addAttribute("client", client);
        return "authorize";
    }
}