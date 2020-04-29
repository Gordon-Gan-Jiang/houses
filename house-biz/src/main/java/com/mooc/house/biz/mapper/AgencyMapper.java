package com.mooc.house.biz.mapper;

import com.mooc.house.common.models.Agency;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Gordon Gan(Gordon.gan@alo7.com)
 */
@Mapper
public interface AgencyMapper {
    List<Agency> select(Agency agency);
}
