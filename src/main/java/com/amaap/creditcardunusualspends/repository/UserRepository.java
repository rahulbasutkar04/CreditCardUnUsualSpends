package com.amaap.creditcardunusualspends.repository;

import java.util.HashMap;
import java.util.Map;

public interface UserRepository {
   void  addUser(int id,String name,String email);
   Map<Integer, Map<String, String>> getUserData();
}
