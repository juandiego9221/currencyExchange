package com.example.demo.service;

import com.example.demo.dto.ChangeRequest;
import com.example.demo.dto.ChangeResponse;
import com.example.demo.dto.MoneyRequest;
import com.example.demo.entity.ChangeType;
import com.example.demo.repository.ChangeTypeRepository;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class CurrencyChangeServiceImpl implements CurrencyChangeService {
    @Autowired
    private ChangeTypeRepository changeTypeRepository;

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

    @Override
    public Single<Integer> addMoney(MoneyRequest moneyRequest) {
        return Single.create(suscriber -> {
            ChangeType money = new ChangeType();
            money.setCurrency(moneyRequest.getMoneyName());
            money.setType(moneyRequest.getValue());
            int id = changeTypeRepository.save(money).getId();
            suscriber.onSuccess(id);
        });
    }

    @Override
    public Single<List<ChangeType>> listAllMoney() {
        return Single.create(suscriber -> {
            List<ChangeType> allMoney = changeTypeRepository.findAll();
            suscriber.onSuccess(allMoney);
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
