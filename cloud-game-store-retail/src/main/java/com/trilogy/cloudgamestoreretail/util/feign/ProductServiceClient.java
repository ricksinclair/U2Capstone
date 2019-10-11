package com.trilogy.cloudgamestoreretail.util.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "product-service")
public interface ProductServiceClient {
}
