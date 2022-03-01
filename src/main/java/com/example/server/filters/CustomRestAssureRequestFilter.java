package com.example.server.filters;

import com.example.server.constant.ServerHosts;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author kentzhong
 * @date 2020/4/26
 */

public class CustomRestAssureRequestFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(CustomRestAssureRequestFilter.class);


    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        requestSpec.baseUri(ServerHosts.SERVER_HOST);
        return ctx.next(requestSpec, responseSpec);
    }

}
