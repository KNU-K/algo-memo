package com.knuk.algo_memo.domain.solvedac;

import lombok.Data;
import org.springframework.web.client.RestClient;

public class SolvedAc {
    private static Integer problemId;
    public static void main(String[] args) {
        problemId = 3055;

        /** 직렬화 수정 **/

        RestClient restClient = RestClient.create();
        SolvedAcProblemsResponseDTO result = restClient.get()
                .uri("https://solved.ac/api/v3/search/problem?query="+problemId+"&direction=asc&sort=id&page=1")
                .retrieve()
                .body(SolvedAcProblemsResponseDTO.class);
        System.out.println(result);
    }
}
