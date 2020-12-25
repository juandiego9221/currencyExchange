package com.example.demo.service;

import com.example.demo.dto.ChangeRequest;
import com.example.demo.dto.ChangeResponse;
import com.example.demo.dto.MoneyRequest;
import com.example.demo.entity.ChangeType;
import io.reactivex.rxjava3.core.Single;

import java.util.List;

public interface CurrencyChangeService {

    Single<ChangeResponse> getResult(ChangeRequest changeRequest);

    Single<Integer> addMoney(MoneyRequest moneyRequest);

    Single<List<ChangeType>> listAllMoney();
}
