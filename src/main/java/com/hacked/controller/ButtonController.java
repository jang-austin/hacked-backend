package com.hacked.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ButtonController {

    private static final Logger logger = LoggerFactory.getLogger(ButtonController.class);

    // 닉네임별 시도횟수를 저장하는 메모리 맵
    private static final ConcurrentHashMap<String, Integer> attemptCounts = new ConcurrentHashMap<>();

    @PostMapping("/button-click")
    public ResponseEntity<String> handleButtonClick(@RequestBody Map<String, Object> request) {
        try {
            // 요청에서 버튼 타입 추출
            String buttonType = (String) request.get("buttonType");
            String nickname = (String) request.get("nickname");
            
            // 닉네임별 시도횟수 증가
            if (nickname != null) {
                attemptCounts.put(nickname, attemptCounts.getOrDefault(nickname, 0) + 1);
            }
            
            logger.info("=== 일반 버튼 클릭 이벤트 (hacked-backend) ===");
            logger.info("버튼 타입: {}", buttonType != null ? buttonType : "알 수 없음");
            if (nickname != null) {
                logger.info("닉네임: {}", nickname);
                logger.info("시도횟수: {}", attemptCounts.get(nickname));
            }
            
            // 전체 시도횟수 통계 출력
            logger.info("--- 전체 시도횟수 통계 ---");
            attemptCounts.forEach((name, count) -> {
                logger.info("{}: {}회", name, count);
            });
            logger.info("================================");
            
            return ResponseEntity.ok("일반 버튼 클릭 이벤트가 성공적으로 처리되었습니다.");
        } catch (Exception e) {
            logger.error("버튼 클릭 처리 중 오류 발생: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().body("서버 오류가 발생했습니다.");
        }
    }
    
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("hacked-backend 서버가 정상적으로 실행 중입니다.");
    }
}
