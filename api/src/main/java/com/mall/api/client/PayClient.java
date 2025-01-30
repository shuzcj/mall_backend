package com.mall.api.client;

import com.mall.common.domain.dto.StockUpdateResponse;
import com.mall.common.domain.entity.Payment;
import org.aspectj.weaver.ast.Or;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "pay-service")//microservice name
public interface PayClient {

    @PostMapping("/pay/")//full path
    Integer createPayment(@RequestBody Payment payment);
}
