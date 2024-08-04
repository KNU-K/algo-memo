package com.knuk.algo_memo.domain.solvedac.service;

import com.knuk.algo_memo.domain.solvedac.dto.ProblemDTO;
import com.knuk.algo_memo.domain.solvedac.dto.ResponseWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@AllArgsConstructor
public class SolvedAcService{
    private static final String REST_URI = "https://solved.ac/api/v3/search/problem";
    private RestClient restClient;

    public String createQueryString(String baseUrl, int problemId) {
        return UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("query", problemId)
                .queryParam("direction", "asc")
                .queryParam("sort", "id")
                .queryParam("page", 1)
                .toUriString();
    }
    public ProblemDTO getProblemInfo(Integer problemId){

        try {
            ResponseWrapper responseWrapper = restClient.get()
                    .uri(createQueryString(REST_URI, problemId))
                    .retrieve()
                    .body(ResponseWrapper.class);


            return  responseWrapper.getProblems().get(0);


        }  catch (Exception e) {
            // 기타 예외 처리
            throw new RuntimeException("Unexpected error occurred while retrieving problem information", e);
        }
    }
}
