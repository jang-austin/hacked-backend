<<<<<<< HEAD
# Hacked Backend

일반 버튼 클릭 시 닉네임별 시도횟수를 추적하고 콘솔에 출력하는 스프링부트 서버입니다.

## 기능

- **일반 버튼 클릭 처리**: hack-library의 모든 버튼 클릭을 받아 처리
- **닉네임별 시도횟수 추적**: 메모리에 닉네임별 시도횟수를 저장하고 매 요청마다 통계 출력
- **OpenTelemetry 모니터링**: Zipkin을 통한 분산 추적 지원
- **Spring Boot Actuator**: 메트릭 및 헬스체크 엔드포인트 제공

## API 엔드포인트

### POST /api/button-click
일반 버튼 클릭 시 호출되는 엔드포인트

**요청:**
```json
{
  "buttonType": "red",
  "nickname": "사용자닉네임"
}
```

**응답:**
```
일반 버튼 클릭 이벤트가 성공적으로 처리되었습니다.
```

**콘솔 출력:**
```
=== 일반 버튼 클릭 이벤트 (hacked-backend) ===
버튼 타입: red
닉네임: 사용자닉네임
시도횟수: 5
--- 전체 시도횟수 통계 ---
사용자1: 3회
사용자2: 2회
사용자닉네임: 5회
================================
```

### GET /api/health
서버 상태 확인

**응답:**
```
hacked-backend 서버가 정상적으로 실행 중입니다.
```

## 실행 방법

### 로컬 개발

```bash
mvn spring-boot:run
```

### Docker (모니터링 포함)

```bash
# 모든 서버와 모니터링 시작
./start-all-with-monitoring.sh

# 모든 서버와 모니터링 중지
./stop-all-with-monitoring.sh
```

### Render 배포

```bash
# GitHub에 push 후 Render에서 자동 배포
git push origin main
```

자세한 배포 가이드는 [RENDER_DEPLOYMENT.md](RENDER_DEPLOYMENT.md)를 참조하세요.

## 모니터링

- **Zipkin 분산 추적**: http://localhost:9411
- **Spring Boot Actuator**: http://localhost:8082/actuator
- **서버 포트**: 8082

## 기술 스택

- Java 17
- Spring Boot 3.2.0
- Maven
- OpenTelemetry
- Zipkin
- Spring Boot Actuator

## 프로젝트 구조

```
src/main/java/com/hacked/
├── HackedBackendApplication.java          # 메인 애플리케이션
└── controller/
    └── ButtonController.java             # API 컨트롤러

src/main/resources/
└── application.yml                       # 설정 파일
```

## 설정

### application.yml
```yaml
server:
  port: 8082

spring:
  application:
    name: hacked-backend
  zipkin:
    base-url: http://localhost:9411
  sleuth:
    zipkin:
      base-url: http://localhost:9411

management:
  endpoints:
    web:
      exposure:
        include: "*"
  tracing:
    sampling:
      probability: 1.0
```

## 관련 프로젝트

- **hack-library**: 프론트엔드 라이브러리 (모든 버튼 컴포넌트)
- **hacked-system**: 데모 웹 애플리케이션
- **hack-backend**: 보라색 버튼 확률 당첨 처리 서버
=======
# hacked-backend
123
>>>>>>> b83567731414561ea83fc76d65a572e67eb2d62b
