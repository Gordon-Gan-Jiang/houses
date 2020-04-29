package com.mooc.house.web.controller;

import com.mooc.house.biz.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author Gordon Gan(Gordon.gan@alo7.com)
 */
@Controller
public class AgencyController {
    @Autowired
    private AgencyService agencyService;
}
