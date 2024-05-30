package com.amaap.creditcardunusualspends.repository;

import java.util.List;
import java.util.Map;

public interface ExpenditureRepository {
    void addIntoExpenditureData(List<Map<String, Object>> spendData);

    List<Map<String, Object>> getSpendData();
}
