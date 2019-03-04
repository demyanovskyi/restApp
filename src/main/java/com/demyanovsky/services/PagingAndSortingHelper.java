package com.demyanovsky.services;

import com.demyanovsky.exceptions.IncorrectPagingException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public final class PagingAndSortingHelper {
    private PagingAndSortingHelper() {
    }

    public static Pageable createPageRequest(Integer page, Integer limit, String sort) {
        if (page == null) {
            page = 0;
        }
        if (page < 0) {
            throw new IncorrectPagingException();
        }

        if (limit == null || limit == 0) {
            limit = 25;
        }
        if (limit > 50) {
            limit = 50;
        }
        if (limit < 0) {
            throw new IncorrectPagingException();
        }
        return PageRequest.of(page, limit, Sort.by(sort));
    }
}
