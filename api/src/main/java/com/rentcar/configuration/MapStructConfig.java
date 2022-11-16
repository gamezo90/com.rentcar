package com.rentcar.configuration;

import com.rentcar.controller.mappers.CarMapper;
import com.rentcar.controller.mappers.OrderMapper;
import com.rentcar.controller.mappers.RoleMapper;
import com.rentcar.controller.mappers.UserMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapStructConfig {

    @Bean
    public UserMapper userMapper() {
        return Mappers.getMapper(UserMapper.class);
    }

    @Bean
    public RoleMapper roleMapper() {
        return Mappers.getMapper(RoleMapper.class);
    }

    @Bean
    public CarMapper carMapper() {
        return Mappers.getMapper(CarMapper.class);
    }

    @Bean
    public OrderMapper orderMapper() {
        return Mappers.getMapper(OrderMapper.class);
    }

}
