package com.example.PLADIALMArchiving.archiving.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "아카이빙 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/archives")
public class ArchivingController {
}
