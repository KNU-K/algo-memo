package com.knuk.algo_memo.api;

import com.knuk.algo_memo.domain.solvedac.dto.ProblemDTO;
import com.knuk.algo_memo.domain.solvedac.service.SolvedAcService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/solved.ac")
@RequiredArgsConstructor
public class SolvedAcController {
    private final SolvedAcService solvedAcService;


    /**
     * @param id required
     *
     * @description
     * 간단한 테스트용
     */
    @GetMapping("/problem")
    public ProblemDTO findProblemById(@RequestParam(value = "id") Integer id){
        return solvedAcService.searchProblemById(id);
    }
}
