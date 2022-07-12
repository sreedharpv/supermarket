package com.lendingwork.supermarket.controller;

import com.lendingwork.supermarket.exception.ApplicationException;
import com.lendingwork.supermarket.service.SuperMarketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;

/**
 * This Controller class calculate the price based on selected SKUs
 */
@RestController
@Validated
public class SuperMarketController extends AbstractSuperMarketController {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    SuperMarketService service;

    /**
     * Method to calculate price for the selected SKUs
     * @param checkOutItemsStr
     * @return
     */
    @GetMapping(value = CALCULATE_PRICE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> calculatePrice(@RequestParam("items") String checkOutItemsStr) throws ApplicationException {
        LOG.info("Start calculatePrice API ");
        if(isEmptyString.apply(checkOutItemsStr)) {
            throw new ApplicationException(HttpStatus.BAD_REQUEST.value(),ITEMS_VALIDATION_MESSAGE);
        }
        return ResponseEntity.ok(service.calculatePrice(Arrays.asList(checkOutItemsStr.split(","))));
    }

}
