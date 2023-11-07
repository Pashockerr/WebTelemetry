package com.github.pashockerr.webtelemetry

import com.github.pashockerr.webtelemetry.components.ValuesComponent
import com.github.pashockerr.webtelemetry.models.Values
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/api")
class ApiController {
    @Autowired
    private val valuesComponent: ValuesComponent? = null
    @GetMapping(value = ["/", ""])
    @ResponseBody
    fun index(): String {
        return valuesComponent!!.cachedValues!!.data
    }
}
