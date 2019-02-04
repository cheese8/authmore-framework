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
package me.zbl.cart;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author JamesZBL
 * @email 1146556298@qq.com
 * @date 2019-01-29
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    @GetMapping
    public String cart() {
        return "cart";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }
}