package com.github.pashockerr.webtelemetry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pashockerr.webtelemetry.components.ValuesComponent;
import com.github.pashockerr.webtelemetry.models.Values;

@Controller
@RequestMapping("/api")
public class ApiController {
	@Autowired
	private ValuesComponent valuesComponent;
	
	@GetMapping(value = {"/", ""})
	@ResponseBody
	public Values index() {
		return valuesComponent.getCachedValues();
	}
}
