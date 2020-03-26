**### 다모임**
**동네 주민들을 위한 소통의 공간

**## 서비스 소개**
현재 살고 있는 지역의 주민들과 게시판, 중고 거래, 번개 모임 등의 기능을 통해 소통할 수 있는 앱

**## 구현사항**
**#Back end**

**spring boot(REST API)**
1. Spring Data JPA를 통한 DB 접근 및 관리
2. 주 저장소로 AWS RDS(MySQL) 사용
3. QueryDSL를 통해 쿼리를 메소드화하여 쿼리의 오류를 컴파일 시첨에서 처리
4. AOP를 통하여 exception이 발생할 때와 request이 들어올 때를 log로 기록
5. NoSQL인 Redis를 주 저장소인 MySQL 앞에 두어 캐시 역할로 사용
6. Swagger를 사용하여 API 문서 자동화
7. Spring security와 jwt 방식을 사용하여 토큰을 통한 사용자 인증 구현
8. Websocket과 RabbitMQ를 이용한 채팅 기능(구현 예정)
9. 이미지 파일 저장소로 AWS S3 저장소를 사용
10. firebase FCM 토큰을 통한 알람 기능 구현
11. 카카오톡, 네이버 소셜 로그인 기능 구현


**Deploy**
1. AWS ec2 서버에 api server 배포
2. Jenkins를 통한 github에 push 발생 시 빌드 및 배포 자동화
