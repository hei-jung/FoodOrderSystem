# SSSP 식당-주문-멤버십 프로그램

- 플레이데이터 첫 번째 미니 프로젝트
- 데이터베이스 연동 Java 프로젝트


## 1. 주제

식당 회원제 주문 시스템

### 1-1. 왜 SSSP?

Spaghetti Salad Sandwich Pizza의 앞 글자를 따왔습니다.<br>
스파게티, 샐러드, 샌드위치, 피자를 판매하는 식당이라고 가정하였습니다.

```
~메뉴~
치즈오븐스파게티
불닭스파게티
알리오올리오
크림소스스파게티
로제스파게티
연어샐러드
참치샐러드
두부샌드위치
계란샌드위치
사이다
```

Oracle 데이터베이스에 `foods` 테이블을 만들어 메뉴 저장


## 2. 기능

> 전체 메뉴

- 회원게시판
  - 회원가입
  - 새글쓰기
  - 글삭제
  - 전체글보기
  - 내글보기
  - 종료
- 주문
  - 메뉴보기
  - 주문장바구니
  - 주문목록보기
  - 결제(결제 시 학생 회원이면 할인, 포인트 적립 및 사용 가능)
  - 종료
- 주방
  - 조리완료
  - 매출조회
  - 종료
- 종료


## 3. 구현 방법

- Database: Oracle SQL
- Language: Java

### 3-1. DB 테이블 구성

#### foods table (메뉴보기)

![foods](https://user-images.githubusercontent.com/40985307/92239688-63aeff80-eef6-11ea-92bb-7fe84db2a84e.PNG)

#### users table (회원)

![users](https://user-images.githubusercontent.com/40985307/92239843-9b1dac00-eef6-11ea-8ccb-e65f89b5f4b0.PNG)

#### users_log table (주문기록)

![users_log](https://user-images.githubusercontent.com/40985307/92239759-804b3780-eef6-11ea-920b-e2abdbd9f576.PNG)

#### board table (회원게시판)

![board](https://user-images.githubusercontent.com/40985307/92239810-8e995380-eef6-11ea-9877-837e6c3016ac.PNG)
  
### 3-2. 자바 패키지 구성

#### kitchen

![aaa](https://user-images.githubusercontent.com/40985307/92241241-df11b080-eef8-11ea-8b70-2bbbd108abcf.png)

#### order

![bbb](https://user-images.githubusercontent.com/40985307/92241262-e933af00-eef8-11ea-8451-7cc638d200cc.png)

#### post

![ccc](https://user-images.githubusercontent.com/40985307/92241285-f51f7100-eef8-11ea-94aa-86c1059551e6.png)
