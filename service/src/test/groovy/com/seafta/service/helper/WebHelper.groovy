package com.seafta.service.helper

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component
import org.springframework.web.util.UriComponentsBuilder

@Component
class WebHelper {
    final ObjectMapper mapper

    WebHelper(ObjectMapper obj) {
        mapper = obj
        mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS)
    }

    UriComponentsBuilder buildQuery(String uri, Object request, Pageable pageable = null) {
        Map<String, Object> parameters = Objects.nonNull(request) ? mapper.convertValue(request, Map) : [:]
        if (Objects.nonNull(pageable)) {
            parameters.put("page", pageable.getPageNumber())
            parameters.put("size", pageable.getPageSize())
            if (Objects.nonNull(pageable.getSort())) {
                parameters.put("sort", Sort.collect{"${it.getProperty()},${it.getDirection()}"} as Collection<String>)
            }
        }
        UriComponentsBuilder queryBuilder = UriComponentsBuilder.fromPath(uri)
        parameters.entrySet().collect {
            it -> queryBuilder.queryParam(it.key as String, it.value instanceof Collection ? ((Collection<Object>) it.value).toArray() : it.value)
        }
        return queryBuilder
    }
}
