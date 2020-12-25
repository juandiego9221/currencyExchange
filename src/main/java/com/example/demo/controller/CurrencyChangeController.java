package com.example.demo.controller;

import com.example.demo.dto.ChangeRequest;
import com.example.demo.dto.ChangeResponse;
import com.example.demo.dto.MoneyRequest;
import com.example.demo.entity.ChangeType;
import com.example.demo.service.CurrencyChangeService;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    Single<ResponseEntity<Integer>> addMoney(
            @RequestBody
                    MoneyRequest moneyRequest
    ) {
        Single<Integer> result = currencyChangeService.addMoney(moneyRequest);
        return result.subscribeOn(Schedulers.io()).map(ResponseEntity::ok);
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    Single<ResponseEntity<List<ChangeType>>> listAll() {
        Single<List<ChangeType>> result = currencyChangeService.listAllMoney();
        return result.subscribeOn(Schedulers.io()).map(ResponseEntity::ok);
    }

}
