package com.example.demo.service;

import com.example.demo.dto.ChangeRequest;
import com.example.demo.dto.ChangeResponse;
import io.reactivex.rxjava3.core.Single;

public interface CurrencyChangeService {

    Single<ChangeResponse> getResult(ChangeRequest changeRequest);
}
