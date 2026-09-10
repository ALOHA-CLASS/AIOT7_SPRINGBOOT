# 테스트 하는 방법
1. 현재 프로젝트 디렉토리에서
   * 프로젝트 우클릭 > [통합 터미널에서 열기]
2. ./gradlew test
   ./gradlew clean test --tests "com.aloha.jpa.ProductRepositoryTest"
   ./gradlew clean test --tests "com.aloha.jpa.OrderTest"
3. BUILD SUCCESSFUL.. -> ✅ 성공



## @DataJpaTest 
- 테스트 시 기본적으로 H2 DB를 사용해서 테스트함

```
  @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
```
를 적용하면 H2 DB를 사용하지 않고, application.properties 에 설정한 DB 를 사용함
