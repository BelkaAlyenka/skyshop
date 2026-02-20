package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.model.search.SearchResult;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public List<SearchResult> search(String request) {
        Collection<Searchable> allSearchable = storageService.getAllSearchable();
        return allSearchable.stream()
                .filter(searchable -> searchable.getName().contains(request))
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toList());
    }
}
