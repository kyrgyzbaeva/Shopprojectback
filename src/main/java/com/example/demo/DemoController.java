package com.example.demo;


import lombok.RequiredArgsConstructor;
import org.apache.catalina.util.ErrorPageSupport;
import org.hibernate.collection.spi.BagSemantics;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/book")
public class DemoController {

    @GetMapping
    public Get <Bags> getBags(
            @PageableDefault(
                    size = 25,
                    sort = {"publishedYear", "title"},
                    direction = Sort.Direction.DESC)
            Pageable pageable
//            @RequestParam(required = false, defaultValue = "0") Integer page,
//            @RequestParam(required = false, defaultValue = "25") Integer size,
//            String[] sort
    )
}
