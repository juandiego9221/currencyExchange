package com.example.demo.controller;

import com.example.demo.dto.ChangeRequest;
import com.example.demo.dto.ChangeResponse;
import com.example.demo.service.CurrencyChangeService;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api/change")
@RestController
public class CurrencyChangeController {
    @Autowired
    CurrencyChangeService currencyChangeService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    Single<ResponseEntity<ChangeResponse>> changeAmount(
            @RequestBody
                    ChangeRequest changeRequest
    ) {
        Single<ChangeResponse> result = currencyChangeService.getResult(changeRequest);
        return result.subscribeOn(Schedulers.io())
                .map(ResponseEntity::ok);
    }
}
