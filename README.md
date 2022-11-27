# GoF-design-patterns

## ***객체 생성 관련 디자인 패턴***

<details markdown="1">
<summary> 1. Singleton Pattern  </summary>  
  
  
> 인스턴스를 오직 한개만 제공하는 클래스  
  
  
시스템 런타임, 환경 세팅에 대한 정보 등, 인스턴스가 여러개 일 때 문제가 생길 수 있는 경우가 있다.  
인스턴스를 오직 한개만 만들어 제공하는 클래스가 필요하다.  

```
아래는 가장 대표적인 SingleTon 방식이지만,
다른 쓰레드가 if문 안에 동시에 들어가게 된다면 Thread Safe하지 않게 되는 문제가 있다.

private static Object instance;

public static Object getInstance(){
  if(instance == null) {
    instance = new Object();
  }
  return instance;
}
```

```
# synchronized
동기화를 위해 synchronized 키워드를 사용할 수 있다.
이 방법의 단점은, getInstance() 메서드를 호출할 떄 마다 동기화 처리 작업떄문에 성능에 약간의 불이득이 생길 수 있다.

public static synchronized Object getInstance(){}
```

```
# 이른 초기화(eager initializtion)
객체를 생성하는데에 비용이 비싸지 않다면 아래의 이른 초기화(eager initializtion) 방식을 사용해 미리 생성할 수 있다.

private static final Object INSTANCE = new Object();

public static Object getInstance() {
  return INSTANCE;
}
```

```
# double checked locking
double checked locking으로 효율적인 동기화 블럭을 만들 수 있다.
여러 쓰레드가 활발한 상황에서 if문 안에 들어왔을때에만 대비해서 synchronized를 사용하기 떄문에 method단에 synchronized를 명시한 것 보다 성능에 유리하다.
또,instance를 필요한 시점에 만들 수 있다는 장점이 있다.
JAVA 1.5 이상부터 사용 가능하다.

private static volatile Object insatnce;

public static Object getInstance() {
  if(instance == null) {
    synchronized(Object.class) {
      if(instance == null) {
        instance = new Object();
      }
    }
  }
  return instance;
}
```

```
# static inner class
권장되는 방법중에 하나인 inner class를 활용하는 방법이다.
double checked locking 보다 단순하며 멀티쓰레드 환경에서도 안전하고, getInstance()가 호출될 때 ObjectHolder 클래스가 로딩이 되고 그 때   
instance를 생성하는 lazy loding이 가능한 코드가 된다.

private static class ObjectHolder {
  private static final Object INSTANCE = new Object();
}

public static Object getInstance() {
  return ObjectHolder.INSTANCE;
}


하지만, 리플랙션을 사용하게 되면 Singleton이 깨지게 된다.
Object object = Object.getInstance();

Constructor<Object> constructor = Object.class.getDeclaredConstructor();
constructor.setAccessible(true);
Object object1 = constructor.newInstance();

System.out.println(object == object1) ==> false 

따라서, 실무에서 사용을 할때에는 spring context에 bean으로 등록하고 사용하는게 좋다.
```



</details>







## ***구조 관련 디자인 패턴***  

<details markdown="1">
<summary> 1. Adapter Pattern  </summary>  
  
  
> 기존 코드를 클라이언트가 사용하는 인터페이스의 구현체로 바꿔주는 패턴

클라이언트가 사용하는 인터페이슬ㄹ 따르지 않는 기존 코드를 재사용할 수 있게 해준다.


장점
- 기존 코드를 변경하지 않고 원하는 인터페이스 구현체를 만들어 재사용할 수 있다. (개방 폐쇄의 원칙)
- 기존 코드가 하던 일과 특정 인터페이스 구현체로 변환하는 작업을 각기 다른 클래스로 분리하여 관찰할 수 있다. (단일 책임 원칙)
  
단점
- 새 클래스가 생겨 복잡도가 증가할 수 있다. 경우에 따라서는 기존 코드가 해당 인터페이스를 구현하도록 수정하는 것이 좋은 선택이 될 수도 있다.

</details>




## ***행동 관련 디자인 패턴***  


<details markdown="1">
<summary> 1. Chain-of-Responsibility Pattern (책임 연쇄 패턴)  </summary>  
  
  
> 요청을 보내는 쪽(sender)과 요청을 처리하는 쪽(receiver)의 분리하는 패턴

핸들러 체인을 사용해서 요청을 처리한다.


장점
- 메인코드를 변경하지 않고 새로운 핸들러를 체인에 추가할 수 있다.
- 또한 핸들러 순서를 얼마든지 변경할 수 있다.
- 핸들러들은 각각 본인의 할 일만 가지고 있다.(단일 책임 원칙)

단점
- 연쇄적으로 흘러가다 보니 디버깅이 번거로울 수 있다.







</details>
