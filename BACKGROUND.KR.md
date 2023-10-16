# Background

## Pros and Cons of Kotlin
- NULL safe: NullPointerException을 예방하기 위해 기본적으로 null값을 변수에 담을수 없다.
- Coroutine: 가독성이 좋은 비동기코드를 작성할수 있게 도와주며 (동시성프로그래밍), 스레드가 아닌 서브루틴을 일시중단(suspend)하는 방식이기 때문에 Context-switching에 비용이 들지 않는다
- Java와 100% 호환되며, 자바의 최신기능들을 코틀린을 통해 활용할수 있다.
- 대용량 코드를 처리 할 경우 컴파일 속도가 느리다. 컴파일러 부분에 있는
Anotation Processor(전처리기) 부분이 20%가량 느리게 된다.
- Annotation Processing 호환성 문제가 있을수 있다. (ex: 코틀린코드에서 lombok이 적용된 코드를 호출할때, querydsl로 코틀린 엔티티 클래스를 q클래스로 매핑할때)
