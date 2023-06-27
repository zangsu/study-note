# 2. 테스트

어플리케이션은 계속해서 변하고, 복잡해져 간다.
이 변화에 대응하는 첫번째 전략은 확장과 변화를 고려한 객체지향적인 설계와 이를 효과적으로 담아낼 수 있는 IoC/DI 같은 기술이다. 
그리고 두번째 전략이 바로 만들어진 코드를 확신할 수 있게 해주고, 변화에 유연하게 대처할 수 있게 해주는 테스트 기술이다.

이번 장에서는 테스트가 무엇인지, 그리고 그 가치와 장점, 활용 전략, 스프링과의 관계를 살펴본다. 이후, 이 책에서 활용할 대표적인 테스트 프레임워크를 소개한다.

## 2.1 UserDAOTest 다시보기

[2.1 UserDAOTest 다시보기.md](https://github.com/zangsu/study-note/blob/main/BE/Spring/%ED%86%A0%EB%B9%84%EC%9D%98%20%EC%8A%A4%ED%94%84%EB%A7%81%203.1/Vol.1/CH2%20-%20%ED%85%8C%EC%8A%A4%ED%8A%B8/2.1%20UserDAOTest%20%EB%8B%A4%EC%8B%9C%EB%B3%B4%EA%B8%B0.md)

## 2.2 UserDAOTest 개선

[2.2 UserDAOTest 개선](https://github.com/zangsu/study-note/blob/main/BE/Spring/%ED%86%A0%EB%B9%84%EC%9D%98%20%EC%8A%A4%ED%94%84%EB%A7%81%203.1/Vol.1/CH2%20-%20%ED%85%8C%EC%8A%A4%ED%8A%B8/2.2%20UserDAOTest%20%EA%B0%9C%EC%84%A0.md)

## 2.3 개발자를 위한 테스팅 프레임워크 JUnit

[2.3 개발자를 위한 테스팅 프레임워크 JUnit](https://github.com/zangsu/study-note/blob/main/BE/Spring/%ED%86%A0%EB%B9%84%EC%9D%98%20%EC%8A%A4%ED%94%84%EB%A7%81%203.1/Vol.1/CH2%20-%20%ED%85%8C%EC%8A%A4%ED%8A%B8/2.3%20%EA%B0%9C%EB%B0%9C%EC%9E%90%EB%A5%BC%20%EC%9C%84%ED%95%9C%20%ED%85%8C%EC%8A%A4%ED%8C%85%20%ED%94%84%EB%A0%88%EC%9E%84%EC%9B%8C%ED%81%AC%20JUnit.md)

>개발자들이 낭만이라고도 생각하는 '눈물 젖은 커피와 함께 며칠간 밤샘을 하며 오류를 잡으려고 애쓰다가 전혀 생각지도 못했던 곳에서 간신이 찾아낸 작은 버그 하나의 추억' 이라는 건, 사실 '진작에 충분한 테스트를 했었다면 쉽게 찾아냈을 것을 미루고 미루다 결국 커다란 삽질로 만들어버린 어리석은 기억'일 뿐이다.

## 2.4 스프링 테스트 적용
