package com.example.PLADIALMArchiving.global.feign.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;


@Component
@FeignClient(name="main-server", url = "${server.main.url}")
public interface MainServerClient {

//    @GetMapping("fridges/food-lists")
//    ResponseCustom<RecipeFridgeFoodListsRes> getFridgeFoodLists(@RequestParam(name = "fridgeIdx", required = false) Long fridgeIdx,
//                                                                @RequestParam(name = "multiFridgeIdx", required = false) Long multiFridgeIdx,
//                                                                @RequestParam(name = "userIdx") Long userIdx);
}
