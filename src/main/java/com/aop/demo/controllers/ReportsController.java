package com.aop.demo.controllers;

import com.aop.demo.annotations.AccessIdentifier;
import com.aop.demo.annotations.TimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class ReportsController {


    /**
     * Suppose this is a report fetching API.
     * This report updates in DB every 10 minutes
     * Hence we can block it
     * 1 request in 10 minutes for a user is enough
     *
     * @param reportType
     * @param reportCategory
     * @return
     */
    @AccessIdentifier(duration = 10, durationFormat = TimeFormat.MINS)
    @RequestMapping(path = "/my-report", method = RequestMethod.GET)
    public List<String> getReports(
            @RequestHeader String accessKey,
            @RequestParam(required = false) String reportType,
            @RequestParam(required = false) String reportCategory) {
        return Arrays.asList("Name", "Place");
    }
}
