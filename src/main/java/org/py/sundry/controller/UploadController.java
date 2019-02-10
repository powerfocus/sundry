package org.py.sundry.controller;

import java.nio.file.Paths;

import javax.validation.Valid;

import org.py.sundry.model.UploadModel;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UploadController {
	@GetMapping({"upload"})
	public String index(UploadModel m) {
		System.out.println(Paths.get("").toAbsolutePath());
		return "index/upload";
	}
	@PostMapping({"upload"})
	public String act(@Valid UploadModel m, Errors errors) {
		if(errors.hasErrors())
			return "index/upload";
		return "index/upload";
	}
}