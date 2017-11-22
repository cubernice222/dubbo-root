package com.carrot.biz;


import com.carrot.connector.model.User;

public interface CamelChooseService {

    User nameEq_cuber(String name);

    User nameEq_eva(String name);

    User otherwise(String name);
}
