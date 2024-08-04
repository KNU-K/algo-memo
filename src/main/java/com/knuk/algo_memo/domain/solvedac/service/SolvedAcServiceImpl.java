package com.knuk.algo_memo.domain.solvedac.service;

import com.knuk.algo_memo.domain.solvedac.dto.ProblemDTO;
import com.knuk.algo_memo.domain.solvedac.dto.ProblemResponseWrapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class SolvedAcServiceImpl {
    private static final String REST_URI = "https://solved.ac/api/v3/search/problem";
    private static final String QUERY_PARAM_QUERY = "query";
    private static final String QUERY_PARAM_DIRECTION = "direction";
    private static final String QUERY_PARAM_SORT = "sort";
    private static final String QUERY_PARAM_PAGE = "page";

    private final RestClient restClient;

    public ProblemDTO searchProblemById(Integer problemId) {
        try {
            String uri = createQueryString(problemId);
            ProblemResponseWrapper responseWrapper = restClient.get()
                    .uri(uri)
                    .retrieve()
                    .body(ProblemResponseWrapper.class);

            return extractProblemFromResponse(responseWrapper, problemId);

        } catch (HttpClientErrorException e) {
            log.error("Client error while retrieving problem information: {}", e.getMessage());
            throw new RuntimeException("Problem with ID " + problemId + " not found", e);

        } catch (RestClientException e) {
            log.error("Unexpected error occurred while retrieving problem information: {}", e.getMessage());
            throw new RuntimeException("Unexpected error occurred while retrieving problem information", e);
        }
    }

    private String createQueryString(int problemId) {
        return UriComponentsBuilder.fromHttpUrl(SolvedAcServiceImpl.REST_URI)
                .queryParam(QUERY_PARAM_QUERY, problemId)
                .queryParam(QUERY_PARAM_DIRECTION, "asc")
                .queryParam(QUERY_PARAM_SORT, "id")
                .queryParam(QUERY_PARAM_PAGE, 1)
                .toUriString();
    }
    private ProblemDTO extractProblemFromResponse(ProblemResponseWrapper responseWrapper, Integer problemId) {
        return Optional.ofNullable(responseWrapper)
                .map(ProblemResponseWrapper::getProblems)
                .filter(problems -> !problems.isEmpty() && problems.get(0).getId().equals(problemId))
                .map(problems -> problems.get(0))
                .orElseThrow(() -> new RuntimeException("Problem with ID " + problemId + " not found"));
    }
}
