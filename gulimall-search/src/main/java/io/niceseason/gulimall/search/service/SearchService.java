package io.niceseason.gulimall.search.service;

import io.niceseason.gulimall.search.vo.SearchParam;
import io.niceseason.gulimall.search.vo.SearchResult;

public interface SearchService {
    SearchResult getSearchResult(SearchParam searchParam);
}
