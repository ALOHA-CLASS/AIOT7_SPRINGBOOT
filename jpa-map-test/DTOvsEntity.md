# DTO vs Entity

## 1. DTO와 Entity란?

Spring Boot에서 데이터를 다룰 때 `Entity`와 `DTO`를 구분해서 사용하는 경우가 많습니다.

- **Entity** : 데이터베이스 테이블과 연결되는 객체
- **DTO (Data Transfer Object)** : 계층 간 데이터를 전달하기 위한 객체

쉽게 말하면,

> Entity는 **DB를 위한 객체**
>
> DTO는 **데이터 전달을 위한 객체**

입니다.

---

## 2. Entity

Entity는 데이터베이스의 테이블과 매핑되는 클래스입니다.

JPA에서는 `@Entity`를 사용하여 Entity를 정의합니다.

```java
@Entity
@Getter
@Setter
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderNo;

    private String productName;

    private int price;

    private int quantity;
}
```

위 Entity는 데이터베이스의 orders 테이블과 연결됩니다.

```
  Java Entity
      ↓
  Orders
      ↓
  JPA
      ↓
  Database
      ↓
  orders 테이블
```


### Entity의 특징
- 데이터베이스 테이블과 매핑
- JPA가 관리하는 객체
- @Entity 사용
- @Id로 기본 키 지정
- 데이터베이스의 데이터를 저장하거나 조회하는 데 사용
- 다른 Entity와 연관관계를 가질 수 있음


## 3. DTO

DTO는 데이터를 전달하기 위한 객체입니다.

예를 들어 회원가입 요청을 받는다고 생각해보겠습니다.

```
  @Getter
  @Setter
  public class UserRequestDto {

      private String username;

      private String password;

      private String email;
  }
```

Controller에서 클라이언트가 전달한 데이터를 DTO로 받을 수 있습니다.

```
  @PostMapping("/users")
  public String createUser(
          @RequestBody UserRequestDto dto
  ) {

      System.out.println(dto.getUsername());
      System.out.println(dto.getPassword());
      System.out.println(dto.getEmail());

      return "회원가입 완료";
  }

```
DTO는 데이터베이스 테이블과 직접 연결되는 객체가 아닙니다.


### 4. Entity와 DTO의 차이
| 구분                | Entity           | DTO             |
| ----------------- | ---------------- | --------------- |
| 목적                | 데이터베이스 관리        | 데이터 전달          |
| JPA 관리            | O                | X               |
| `@Entity`         | O                | X               |
| DB 테이블 매핑         | O                | X               |
| Controller 요청 데이터 | 직접 사용하지 않는 것을 권장 | 주로 사용           |
| API 응답 데이터        | 직접 사용하지 않는 것을 권장 | 주로 사용           |
| 비즈니스 로직           | 포함 가능            | 보통 포함하지 않음      |
| 변경 시 영향           | DB 구조와 연결        | API 요구사항에 따라 변경 |


### 5. 왜 Entity를 그대로 사용하지 않을까?

다음과 같은 Entity가 있다고 가정해보겠습니다.

```
  @Entity
  public class User {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;

      private String username;

      private String password;

      private String email;
  }
```



# DTO vs Entity — OrderItem 예제로 이해하기

## 1. 전체 구조

이번 예제에서는 주문항목을 처리하기 위해

- `OrderItem` → Entity
- `OrderItemRequest` → DTO

로 구분합니다.

전체적인 데이터 흐름은 다음과 같습니다.

```text
Client
  ↓
JSON 요청
  ↓
OrderItemRequest DTO
  ↓
Controller
  ↓
Service
  ↓
OrderItem Entity
  ↓
Repository
  ↓
Database


즉,

DTO는 데이터를 전달하고, Entity는 DB와 연결됩니다.