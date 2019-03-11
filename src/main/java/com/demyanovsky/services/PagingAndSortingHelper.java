package com.demyanovsky.services;

import com.demyanovsky.exceptions.IncorrectPagingException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public final class PagingAndSortingHelper {
    private static final Integer MAX_LIMIT = 50;
    private static final Integer MIN_VALUE = 0;

    private PagingAndSortingHelper() {
    }

    public static Pageable createPageRequest(Integer page, Integer limit, String sort) {
        if (page == null) {
            page = MIN_VALUE;
        }
        if (page < MIN_VALUE) {
            throw new IncorrectPagingException();
        }
        if (limit == null || limit == MIN_VALUE) {
            limit = MAX_LIMIT;
        }
        if (limit > MAX_LIMIT) {
            limit = MAX_LIMIT;
        }
        if (limit < MIN_VALUE) {
            throw new IncorrectPagingException();
        }
        return PageRequest.of(page, limit, Sort.by(sort));
    }
}
