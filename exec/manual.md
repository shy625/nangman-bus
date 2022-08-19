# 포팅 매뉴얼

## 개발 환경

### 프론트

- Nodejs v16.16.0
- npm v8.11.0
- vue-cli v5.0.4
    - vue v3.2.13
    - vue-axios v3.4.1
    - vue-router v4.1.3
    - vuex v4.0.2
- sockjs-client v1.6.1
- @stomp/stompjs v6.1.2

### 백

- JDK 1.8
- SpringBoot v2.4.5
- IntelliJ IDEA
- MySQL v8.0.30
- Redis v5.0.7

## 빌드 및 배포 가이드

1. 해당 Gitlab 레포의 master 브랜치를 clone
    ```
    git clone https://lab.ssafy.com/s07-webmobile1-sub2/S07P12A704.git
    ```
### 백엔드 빌드 및 배포 진행
2. 프로젝트의 아래 위치로 이동
    ```
    cd back/src/main/resources
    ```
3. 현재 위치한 디렉토리에 `application-prod.properties` 아래 내용으로 파일 생성
    ```
    ``` 
4. 프로젝트의 아래 위치로 이동
```
```
5. 백엔드 빌드 진행
6. 빌드 결과물 디렉토리로 이동
7. 백엔드 jar 파일 실행하여 백엔드 배포
### 프론트엔드 빌드 및 배포 진행
9. 프로젝트의 아래 위치로 이동
10. npm 모듈 설치
11. 프론트엔드 실행
12. 

## 외부 라이브러리

### 국토교통부 공공 API 사용

공공데이터포털 가입 및 서비스 키 발급 필요

- [국토교통부_(TAGO)_버스노선정보](https://www.data.go.kr/data/15098529/openapi.do)
    - 노선정보항목 조회
    - 노선번호목록 조회
    - 노선별경유정류소목록 조회
    - 도시코드 목록 조회
- [국토교통부_(TAGO)_버스위치정보](https://www.data.go.kr/data/15098533/openapi.do)
    - 노선별버스위치 목록조회
    - 도시코드 목록 조회

## DB dump

## 시연 시나리오
