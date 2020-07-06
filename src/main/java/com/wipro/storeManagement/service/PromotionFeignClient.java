package com.wipro.storeManagement.service;

import com.wipro.storeManagement.model.CustomerPromotion;
import com.wipro.storeManagement.model.ProductPromotion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "promotion-service")//, fallbackFactory = HystrixClientFallback.class)
public interface PromotionFeignClient {


    @GetMapping("/promotion/customer/{customerId}")
    public CustomerPromotion getPromotionByCustomerId();

    @PostMapping("/promotion/customer/updateCustomer")
    public void addPromotionToCustomer(@RequestBody CustomerPromotion Customer);

    @GetMapping("/promotion/product/{productId}")
    public ProductPromotion getPromotionByProductId(@PathVariable(value = "id") Long id);

    @PostMapping("/promotion/product/updateProduct")
    public void addPromotionToProduct(@RequestBody ProductPromotion produstPromotion);

}


//static class HystrixClientFallback implements HystrixClient {
//    @Override
//    public Hello iFailSometimes() {
//        return new Hello("fallback");
//    }