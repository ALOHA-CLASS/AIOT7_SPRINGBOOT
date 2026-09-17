# QueryDSL Q클래스(QProduct) 생성 방법 & 오류 해결

## 1. Q클래스란?

QueryDSL은 `@Entity` 가 붙은 도메인 클래스(`Product`)를 컴파일 시점에 스캔해서,
타입 세이프(type-safe)한 쿼리 작성을 위한 `QProduct` 같은 클래스를 **자동 생성**한다.

- 원본: `com.aloha.querydsl.domain.Product`
- 생성물: `com.aloha.querydsl.domain.QProduct` (같은 패키지에 생성됨)

이 파일은 개발자가 직접 작성하는 게 아니라 **annotation processor(querydsl-apt)** 가
빌드 시점에 자동으로 만들어주는 소스코드다. 따라서 처음 프로젝트를 받으면
Q클래스가 없는 게 정상이며, 최초 1회 컴파일을 해줘야 생성된다.

## 2. 프로젝트 설정 (build.gradle)

```gradle
dependencies {
    // querydsl
    implementation 'com.querydsl:querydsl-jpa:5.1.0:jakarta'
    annotationProcessor 'com.querydsl:querydsl-apt:5.1.0:jakarta'

    // querydsl-apt(JPAAnnotationProcessor)가 @Entity 등을 읽기 위해 필요
    // (annotationProcessor 클래스패스는 implementation 클래스패스와 별도이므로 직접 추가해야 함)
    annotationProcessor 'jakarta.persistence:jakarta.persistence-api'
    annotationProcessor 'jakarta.annotation:jakarta.annotation-api'
}

// Q클래스가 생성될 위치를 build/ 대신 bin/ 하위로 지정 (이 프로젝트 커스텀 설정)
def generatedMainDir = file("$projectDir/bin/generated-sources/annotations")
def generatedTestDir = file("$projectDir/bin/generated-test-sources/annotations")

sourceSets {
    main { java { srcDir generatedMainDir } }
    test { java { srcDir generatedTestDir } }
}

compileJava {
    options.generatedSourceOutputDirectory = generatedMainDir
}
compileTestJava {
    options.generatedSourceOutputDirectory = generatedTestDir
}
```

## 3. Q클래스 생성 방법 (실행 순서)

1. 터미널에서 프로젝트 루트로 이동한다.
2. 아래 명령 중 하나를 실행한다.

   ```bash
   # Windows
   gradlew.bat compileJava

   # macOS/Linux
   ./gradlew compileJava
   ```

3. 빌드가 성공하면 아래 경로에 `QProduct.java` 가 생성된다.

   ```
   bin/generated-sources/annotations/com/aloha/querydsl/domain/QProduct.java
   ```

4. VS Code(Java 확장)에서 빨간 줄(오류)이 안 사라지면, `Java: Clean Java Language Server Workspace` 명령을 실행하거나 창을 다시 로드해 Gradle 소스셋을 다시 인식시킨다.

## 4. "QProduct cannot be resolved to a type" 오류 원인 & 해결

이번 프로젝트에서 발생했던 원인은 2가지였다.

### 원인 1) `QProduct` import 누락 + 오타

`ProductRepositoryImpl.java` 에서 `QProduct` 를 import 하지 않고,
`QProudct` (오타)를 참조하고 있었다.

```java
// 잘못된 코드
private static final QProduct product = QProudct.product; // 오타 + import 없음
```

```java
// 수정된 코드
import com.aloha.querydsl.domain.QProduct;
...
private static final QProduct product = QProduct.product;
```

### 원인 2) annotationProcessor 클래스패스에 jakarta.persistence-api 누락

`querydsl-apt` 는 `@Entity` 를 분석하기 위해 `jakarta.persistence.Entity` 클래스가 필요한데,
Gradle의 `annotationProcessor` 구성(configuration)은 `implementation` 과 **별도의 클래스패스**를 사용한다.
`jakarta.persistence-api` 는 `spring-boot-starter-data-jpa`(implementation)에는 들어있지만
`annotationProcessor` 쪽에는 없어서 아래 예외가 발생했다.

```
java.lang.NoClassDefFoundError: jakarta/persistence/Entity
    at com.querydsl.apt.jpa.JPAAnnotationProcessor.createConfiguration(...)
```

해결: `build.gradle` 의 `annotationProcessor` 에 `jakarta.persistence-api` (필요시 `jakarta.annotation-api`)를 추가한다. (위 2번 항목 참고)

## 5. 체크리스트 (같은 오류가 다시 나면)

- [ ] `Product` 등 도메인 클래스에 `@Entity` 가 붙어있는가?
- [ ] `build.gradle` 의 `annotationProcessor` 에 `querydsl-apt` + `jakarta.persistence-api` 가 모두 있는가?
- [ ] `gradlew compileJava` (또는 `gradlew.bat compileJava`) 를 최소 한 번 실행했는가?
- [ ] `bin/generated-sources/annotations/.../QProduct.java` 가 실제로 생성되었는가?
- [ ] 사용하는 코드에서 `import com.aloha.querydsl.domain.QProduct;` 를 정확한 클래스명(오타 없이)으로 했는가?
- [ ] VS Code Java 언어 서버가 오래된 캐시를 들고 있다면 `Java: Clean Java Language Server Workspace` 실행 후 재시작
