package com.example.demo.service;

import com.example.demo.dto.ChangeRequest;
import com.example.demo.dto.ChangeResponse;
import com.example.demo.entity.ChangeType;
import com.example.demo.repository.ChangeTypeRepository;
import io.reactivex.rxjava3.core.Single;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CurrencyChangeServiceImpl implements CurrencyChangeService {
    @Autowired
    private ChangeTypeRepository changeTypeRepository;

    //https://spring.io/guides/gs/reactive-rest-service/
    @Override
    public Single<ChangeResponse> getResult(ChangeRequest changeRequest) {
        return Single.create(suscriber -> {

            ChangeResponse changeResponse = new ChangeResponse();
            ChangeType byOCurrency = changeTypeRepository.findByCurrency(changeRequest.getoCurrency());
            changeResponse.setAmount(changeRequest.getAmount());
            changeResponse.setoCurrency(changeRequest.getoCurrency());
            changeResponse.setdCurrency(changeRequest.getdCurrency());
            if (changeRequest.getdCurrency().equals("USD")) {
                changeResponse.setChangeType(new BigDecimal(byOCurrency.getType()).setScale(4, RoundingMode.HALF_EVEN));
                changeResponse.setResultAmount(getResultAmount(changeRequest.getAmount(), BigDecimal.valueOf(byOCurrency.getType())));
            } else {
                ChangeType byDCurrency = changeTypeRepository.findByCurrency(changeRequest.getdCurrency());
                BigDecimal changeType = getChangeType(byOCurrency.getType(), byDCurrency.getType());
                changeResponse.setChangeType(changeType);
                changeResponse.setResultAmount(getResultAmount(changeType, changeRequest.getAmount()));
            }
            suscriber.onSuccess(changeResponse);
        });
    }

    private BigDecimal getChangeType(double changeRequest, double byOCurrency) {
        BigDecimal amount = new BigDecimal(changeRequest);
        BigDecimal type = new BigDecimal(byOCurrency);
        return amount.divide(type, 4, RoundingMode.HALF_EVEN);
    }

    private BigDecimal getResultAmount(BigDecimal changeRequest, BigDecimal byOCurrency) {
        return changeRequest.multiply(byOCurrency).setScale(4, RoundingMode.HALF_EVEN);
    }
}
