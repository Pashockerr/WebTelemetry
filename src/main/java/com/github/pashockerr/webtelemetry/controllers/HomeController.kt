package com.github.pashockerr.webtelemetry.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class HomeController {
    @GetMapping(path = ["/", ""])
    fun index(): String {
        return "index"
    }
}
