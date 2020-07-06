package com.wipro.storeManagement.service;

import com.wipro.storeManagement.model.CustomerPromotion;
import com.wipro.storeManagement.model.ProductPromotion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "promotion-service", fallbackFactory = HystrixClientFallback.class)
public interface PromotionFeignClient {


    @GetMapping("/promotion/customer/{customerId}")
    CustomerPromotion getPromotionByCustomerId(@PathVariable(value="customerId") int id);

    @PostMapping("/promotion/customer/updateCustomer")
    void addPromotionToCustomer(@RequestBody CustomerPromotion Customer);

    @GetMapping("/promotion/product/{productId}")
    ProductPromotion getPromotionByProductId(@PathVariable(value = "id") Long id);

    @PostMapping("/promotion/product/updateProduct")
    void addPromotionToProduct(@RequestBody ProductPromotion produstPromotion);

}


class HystrixClientFallback implements PromotionFeignClient {


    @Override
    public CustomerPromotion getPromotionByCustomerId(int id) {
        return new CustomerPromotion();
    }

    @Override
    public void addPromotionToCustomer(CustomerPromotion Customer) {

    }

    @Override
    public ProductPromotion getPromotionByProductId(Long id) {
        return new ProductPromotion();
    }

    @Override
    public void addPromotionToProduct(ProductPromotion produstPromotion) {

    }
}