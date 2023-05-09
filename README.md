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
