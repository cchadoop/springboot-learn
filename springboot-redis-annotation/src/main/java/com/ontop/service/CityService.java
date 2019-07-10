package com.ontop.service;

import java.util.List;

import com.ontop.pojo.City;


public interface CityService {
	public List<City> findAll();
    public City findById(Long id);
    public int deleteById(Long id);
    public int addCity(City city);
    public City updateCity(Long id,String cityName,String desc);
}
