package com.coca.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PagingResult {
    private List<?> items = new ArrayList();
    private long rowCount = 0;
    private int numberPerPage = 20;
    private int pageNumber = 1;
}
