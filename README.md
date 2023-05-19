# 🐭 Intro
 - 부경대학교 빅데이터 자바 개발과정 수강
 <br/>
 
 - 신입 개발자 이동준 Spring Boot Study readme 
 <br/>
 
# ✏ Spring Boot Study
> Back-End Developer Spring Boot Study
<br/>

## 2023.04.27
 - Spring Boot Setting
    - Spring Boot Project Create
    - java11, maven 환경
    
 <br/>
 
## 2023.04.28
 - Spring Boot Board practice
    - Spring MyBatis 및 h2 DB 연동
    - Board MVC Create
    - 생성한 MVC Package 설정(@ComponentScan 내부스캔으로 Service와 Controller설정,@MapperScan Mapper설정 )
    - 게시판 글 생성 및 수정 삭제 구현
<br/>
<img src="https://raw.githubusercontent.com/ldj8196/boot_20230427/main/src/main/studyimage/boardimage.png" width="500" height="500">

<br/>

## 2023.05.01
  - Spring Boot Item and ItemImage practice
    - Item(물품) 등록 구현
    - ItemImage(물품사진) 이미지 등록 및 삭제
    - 물품등록사진 전체 조회

<img src="https://raw.githubusercontent.com/ldj8196/boot_20230427/main/src/main/studyimage/imageone.png" width="300">
<br/>

## 2023.05.02
  - Spring Boot Item Batch Insert,Update,Delete
    - 물품 일괄등록 구현
    - 물품 일괄삭제 구현
    - 물품 일괄수정 구현
<img src="https://raw.githubusercontent.com/ldj8196/boot_20230427/main/src/main/studyimage/itembatch.png" width="300">
<br/>

## 2023.05.03
  - Spring Boot SecurityFilterChain
    - 고객, 판매자, 관리자 권한을 이용하여 SecurityFilter 학습
    - 각 권한 login, logout 통일 및 handler 사용
    - 권한 별 로그인 및 Filter 확인
  
<img src="https://raw.githubusercontent.com/ldj8196/boot_20230427/main/src/main/studyimage/20230503.png" width="300">

## 2023.05.04
  - Spring Boot inheritance dto create
    - UserDetailsService 상속받은 User를 다시 CustomUser 클래스에 상속
    - User안에 있는 값 보다 더 필요한 값들을 등록하여 Session에 저장
    - 상속을 사용하여 세션에 더 많은 정보를 저장할 수 있다

  - Spring Boot Restful api 연동(RestController) practice
    - RestController GET을 이용한 게시판 조회
    - RestController POST를 이용한 게시판 글쓰기(추가)
    - SecurityConfig 에서 POST를 전송하기위해 /api 주소로 시작하는 모든 url에 csrf를 무시
    - login시 Token 발행
    
<img src="https://raw.githubusercontent.com/ldj8196/boot_20230427/main/src/main/studyimage/restboardinsert.png" width="300">
<img src="https://raw.githubusercontent.com/ldj8196/boot_20230427/main/src/main/studyimage/token.png" width="300">

## 2023.05.08
  - Spring Boot jpa(hibernate) practice
    - Entity Create(Tabel, Column), Repository Create(Member1)
    - DB Connection CRUD practice
    - 게시판, 답글쓰기 실습

<img src="https://raw.githubusercontent.com/ldj8196/boot_20230427/main/src/main/studyimage/reply.png" width="300">


## 2023.05.09
  - Spring Boot jpa(hibernate) 1:N practice
    - Entity Create(Address1, Boardimage1), Repository Create(Address1, Boardimage1)
    - @ManyToOne, @OneToMany 1:N 관계 외래키 조회
    - board1 Entity와 boardimage1 Entity 관계 설정
    - board1 값에서 boardimage1 값 조회

<img src="https://raw.githubusercontent.com/ldj8196/boot_20230427/main/src/main/studyimage/repositoryimage.png" width="300">

## 2023.05.10
  - Spring Boot jpa(hibernate) DB Mapping, VIEW Entity
    - Entity validate, 게시판 페이지네이션
    - Create VIEW Entity(Board1View, PurchaseView)

<img src="https://raw.githubusercontent.com/ldj8196/boot_20230427/main/src/main/studyimage/repositoryimage.png" width="300">

## 2023.05.11
  - Spring Boot jpa Projection Practice
    - Member1, Address1 Entity Projection Create
    - Restaurant1, Menu1 CRUD
    - Menu1 Insert AND Image 출력

  <img src="https://raw.githubusercontent.com/ldj8196/boot_20230427/main/src/main/studyimage/menu1.png" width="300">

## 2023.05.12
  - Spring Boot jpa OneToOne Practice, Image Projection
    - Member1, Memberinfo1 Entity Create
    - 1:1관계 데이터 등록시 데이터가 동시(시간 차 있음)에 들어간다.
    - Menu1 Image Projection 출력, Update, Delete Practice

## 2023.05.15
  - Spring Boot jpa Practice
    - 도서관 시스템 jpa 실습
    - REST, jpa 이용
    - 도서관 추가 및 목록 조회 RestController 사용

## 2023.05.16
  - Spring Boot Token 발급
    - Security Filter 이용 Login, Logout 처리
    - Login시 Token 발행
    - Token 발행 및 Token 검증 Component 생성

<img src="https://raw.githubusercontent.com/ldj8196/boot_20230427/main/src/main/studyimage/tokenpublication.png" width="300">
<img src="https://raw.githubusercontent.com/ldj8196/boot_20230427/main/src/main/studyimage/tokencheck.png" width="300">

## 2023.05.17
  - Spring Boot Filter Practice
    - 토큰 발행 유무 필터 구현
    - 이전 페이지의 URL 저장 필터 구현

## 2023.05.18
  - Spring Boot SSE
    - Rest subscribe, publish 구현
    - 서버 정상 구동 확인을 위한 chatting 만들기

<img src="https://raw.githubusercontent.com/ldj8196/boot_20230427/main/src/main/studyimage/chat.png" width="300">

## 2023.05.19
  - Spring Boot Interceptor, AOP Practice
    - Interceptor 구현
    - Interceptor란 controller 실행 전 후를 필터하는 기능
    - AOP 구현
    - AOP는 개발자가 작성한 특정 메소드에서만 반응

<img src="https://raw.githubusercontent.com/ldj8196/boot_20230427/main/src/main/studyimage/aop.png" width="300">