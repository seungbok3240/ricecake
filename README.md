### 소개
JPA 학습 이전 지금까지 공부한 내용들을 정리하기 위한 쇼핑몰 토이프로젝트입니다.
* * *
### 기술스택
<p>
  <img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
  <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
  <img src="https://img.shields.io/badge/h2database-22ADF6?style=for-the-badge&logo=h2database&logoColor=white">
  <img src="https://img.shields.io/badge/thmeleaf-005F0F?style=for-the-badge&logo=thmeleaf&logoColor=white">
</p>

* * *
### 기능

##### 판매자
* 회원가입
* 로그인
* 로그아웃
* 상품등록
* 상품수정

##### 구매자
* 회원가입
* 로그인
* 로그아웃
* 상품구매
* * *

### 실행 시 주의사항
현재 ItemRepository중 이미지 저장 경로에 관한 부분이 H2 DB로 구현이 되어있지 않습니다. 따라서 ItemRepository는 HashMap을 사용하도록 되어있습니다. 만일 판매자, 구매자 또한 HashMap을 사용하고 싶다면 admin, customer 하위에 repository에서 memoryRepository를 컨포넌트스캔하면 됩니다.
* * *

### 기타

아직 완성이라고 할 수 없는 상태이다. 위에서 말한 ItemRepository와 같은 부분만 봐도 그렇다. 여기서 멈추는 이유는 경험해보고 싶은 기능들은 한 번씩 경험해보았기 때문이다. 프로젝트를 시작하게 된 이유가 JPA 학습 이전 지금까지 배운 내용들을 경험해보는 것이기 때문에 여기서 잠시 멈추고 JPA 학습 후 같은 프로젝트를 진행하여 2개의 프로젝트의 차이점을 확인해보고 싶다.
* * *

### 프로젝트 진행기록
[블로그](https://boksjewelbox.tistory.com/category/Spring/Toy%20Project)



