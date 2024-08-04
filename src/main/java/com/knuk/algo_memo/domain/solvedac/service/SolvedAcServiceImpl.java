package com.knuk.algo_memo.domain.solvedac.service;

import com.knuk.algo_memo.domain.solvedac.dto.ProblemDTO;
import com.knuk.algo_memo.domain.solvedac.dto.ProblemResponseWrapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SolvedAcServiceImpl implements SolvedAcService{
    private static final String REST_URI = "https://solved.ac/api/v3/search/problem";
    private static final String QUERY_PARAM_QUERY = "query";
    private static final String QUERY_PARAM_DIRECTION = "direction";
    private static final String QUERY_PARAM_SORT = "sort";
    private static final String QUERY_PARAM_PAGE = "page";

    private final RestClient restClient;


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

    /**
     * TODO-1:
     * 현재 비공식 API 쪽에서 요청으로 다루는 중
     * BUT, 데이터에 대한 부분은 초기에 서버에 없는 문제면 찾을 수 있도록 진행
     * 찾은 후에는 서버 내부에 영구 저장 서비스 로직 실행
     * TODO-2:
     * IF, 서버에 존재한다면, RestClient 를 사용하지않고 DB로 접근하기
     * TODO-3:
     * DB에 지속적인 요청 자체는 좋은 방법이 아닐 수도 있음 캐시에 대한 부분을 고려해서
     * 확장 가능서있게 개발
     *
     */
    @Override
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
        }}

}
