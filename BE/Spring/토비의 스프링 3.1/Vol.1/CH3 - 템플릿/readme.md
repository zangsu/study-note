# 03. 템플릿

1장에서 우리는 초난감 DAO 코드에 DI를 적용하면서 관심이 다른 코드를 분리하고, 확장과 변경에 용이하게 대응할 수 있는 설계 구조로 개선시켰다.

OCP원칙은 코드의 특정 부분은 변경을 통해 기능을 확장하려 하며, 어떤 부분은 고정되어 변하지 않으려 함을 말해준다. 즉, 코드에서 변화의 특성이 다른 부분들을 구분하여 각기 다른 목적과 이유에 따라 다른 시점에 독립적으로 변경할 수 있는 효율적인 구조를 만들어 주는 원칙이다.

템플릿은 이 중 변하지 않으며, 일정한 패턴으로 유지되는 특성을 가진 부분을 변경이 자유로운 부분으로 부터 독립시켜 효과적으로 활용할 수 있도록 하는 방법이다. 

## 3.1 다시 보는 초난감 DAO

[3.1 다시 보는 초난감 DAO](https://github.com/zangsu/study-note/blob/main/BE/Spring/%ED%86%A0%EB%B9%84%EC%9D%98%20%EC%8A%A4%ED%94%84%EB%A7%81%203.1/Vol.1/CH3%20-%20%ED%85%9C%ED%94%8C%EB%A6%BF/3.1%20%EB%8B%A4%EC%8B%9C%20%EB%B3%B4%EB%8A%94%20%EC%B4%88%EB%82%9C%EA%B0%90%20DAO.md)

## 3.2 변하는 것과 변하지 않는 것

[3.2 변하는 것과 변하지 않는 것](https://github.com/zangsu/study-note/blob/main/BE/Spring/%ED%86%A0%EB%B9%84%EC%9D%98%20%EC%8A%A4%ED%94%84%EB%A7%81%203.1/Vol.1/CH3%20-%20%ED%85%9C%ED%94%8C%EB%A6%BF/3.2%20%EB%B3%80%ED%95%98%EB%8A%94%20%EA%B2%83%EA%B3%BC%20%EB%B3%80%ED%95%98%EC%A7%80%20%EC%95%8A%EB%8A%94%20%EA%B2%83.md)

## 3.3 JDBC 전략 패턴의 최적화 

[3.3 JDBC 전략 패턴의 최적화](https://github.com/zangsu/study-note/blob/main/BE/Spring/%ED%86%A0%EB%B9%84%EC%9D%98%20%EC%8A%A4%ED%94%84%EB%A7%81%203.1/Vol.1/CH3%20-%20%ED%85%9C%ED%94%8C%EB%A6%BF/3.3%20JDBC%20%EC%A0%84%EB%9E%B5%20%ED%8C%A8%ED%84%B4%EC%9D%98%20%EC%B5%9C%EC%A0%81%ED%99%94.md)

## 3.4 컨텍스트와 DI

[3.4 컨텍스트와 DI](https://github.com/zangsu/study-note/blob/main/BE/Spring/%ED%86%A0%EB%B9%84%EC%9D%98%20%EC%8A%A4%ED%94%84%EB%A7%81%203.1/Vol.1/CH3%20-%20%ED%85%9C%ED%94%8C%EB%A6%BF/3.4%20%EC%BB%A8%ED%85%8D%EC%8A%A4%ED%8A%B8%EC%99%80%20DI.md)

## 3.5 템플릿과 콜백

[3.5 템플릿과 콜백](https://github.com/zangsu/study-note/blob/main/BE/Spring/%ED%86%A0%EB%B9%84%EC%9D%98%20%EC%8A%A4%ED%94%84%EB%A7%81%203.1/Vol.1/CH3%20-%20%ED%85%9C%ED%94%8C%EB%A6%BF/3.5%20%ED%85%9C%ED%94%8C%EB%A6%BF%EA%B3%BC%20%EC%BD%9C%EB%B0%B1.md)

## 3.6 스프링의 JdbcTemplate

[3.6 스프링의 JdbcTemplate](https://github.com/zangsu/study-note/blob/main/BE/Spring/%ED%86%A0%EB%B9%84%EC%9D%98%20%EC%8A%A4%ED%94%84%EB%A7%81%203.1/Vol.1/CH3%20-%20%ED%85%9C%ED%94%8C%EB%A6%BF/3.6%20%EC%8A%A4%ED%94%84%EB%A7%81%EC%9D%98%20JdbcTemplate.md)

## 3.7 정리

다음은 이번 장에서 알아본 내용들이다.

- 예외가 발생할 가능성이 있고, 공유 리소스의 반환이 필요한 코드는 반드시 `try-catch-finally` 코드 블럭으로 작성하여야 한다.
- 일정한 작업 흐름이 반복되며, 그 사이의 일부 기능만 바뀐다면, 이는 전략 패턴으로 적용할 수 있다.
	- 바뀌지 않는, 작업 흐름에 해당하는 부분은 컨텍스트로, 바뀌는 부분은 전략으로 만들어 인터페이스를 통해 유연하게 전략을 변경할 수 있도록 한다.
- 컨텍스트가 하나 이상의 클라이언트 오브젝트에서 사용될 수 있다면, 컨텍스트 부분을 별도의 클래스로 분리하여 공유하는 것이 좋다.
- 컨텍스트는 별도의 빈으로 등록해 DI 받거나, 클래스 내부에서 직접 생성해 사용할 수 있다.
- 단일 전략 메서드를 갖는 전략패턴인 동시에, 매번 전략을 새로 만들어 사용하고, 컨텍스트를 호출하는 시점에서 전략 DI를 진행하는 방식을 템플릿/콜백 패턴이라 한다.
- 템플릿과 콜백은 `Generics` 를 사용해 다양한 타입들에 대해 일반화 할 수 있다.
- 템플릿/콜백을 설계할 때는, 템플릿과 콜백이 각각 주고 받는 리턴값과 파라미터 정보에 관심을 두어야 한다.
