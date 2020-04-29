package com.mooc.house.biz.service.impl;


import com.mooc.house.biz.mapper.AgencyMapper;
import com.mooc.house.biz.service.AgencyService;
import com.mooc.house.common.models.Agency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgencyServiceImpl implements AgencyService {

  @Autowired
  private AgencyMapper agencyMapper;


  public List<Agency> getAllAgency() {
    return agencyMapper.select(new Agency());
  }
  


}
