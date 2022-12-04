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




<details markdown="1">
<summary> 2. Factory method Pattern  </summary>  

> 구체적으로 어떤 인스턴스를 만들지는 서브클래스가 정한다.  
다양한 구현체가 있고, 그 중에서 특정한 구현체를 만들 수 있는 다양한 팩토리를 제공할 수 있다.
팩토리 패턴을 적용하면 변경에는 닫혀있고 확장에는 열려있는 개방폐쇄 원칙을 지키는 소프트웨어를 개발할 수 있다.

<img width="815" alt="ship_factory" src="https://user-images.githubusercontent.com/94272140/205433622-66f3ff3f-aa9f-4d4e-992a-861009bfd8fc.png">

```
-- old
기존 ShipFactory Class에서 분기에 의한 제품을 생산하고 있다.
public static Ship orderShip(String name, String email) {

  // Customizing for specific name
  if (name.equalsIgnoreCase("whiteship")) {
    ship.setLogo("\uD83D\uDEE5️");
  } else if (name.equalsIgnoreCase("blackship")) {
    ship.setLogo("⚓");
  }

  // coloring
  if (name.equalsIgnoreCase("whiteship")) {
    ship.setColor("whiteship");
  } else if (name.equalsIgnoreCase("blackship")) {
    ship.setColor("black");
  }
...


-- new
// ShipFactory 클래스를 Interface로 만들고 이를 상속받은 제품별 Factory 클래스를 생성한다.
// default 메서드를 사용해 Interface 내부에서 구현이 가능하다.

- ShipFactory Interface
public interface ShipFactory {

    default Ship orderShip(String name, String email) {
        validate(name, email);
        prepareFor(name);

        Ship ship = createShip();
        sendEmailTo(email, ship);
        return ship;
    }
...

- WhiteShipFactory Class
public class WhiteShipFactory implements ShipFactory{

    @Override
    public Ship createShip() {
        return new WhiteShip();
    }

// use
Ship whiteship = new WhiteShipFactory().orderShip("WhiteShip", "hong@email.com");
Ship blackship = new BlackShipFactory().orderShip("Blackship", "keesun@mail.com");
```


```
// 하지만, 위와 같이 사용하면 변경에 열려있다. 따라서 아래의 print 메서드와 같이 ShipFactory 인터페이스 형태로 받아 사용한다면 변경에 닫힌 소스를 구현할 수 있다.

Client client = new Client();
client.print(new WhiteShipFactory(), "whiteship", "lee@email.com");
client.print(new BlackShipFactory(), "whiteship", "lee@email.com");

private void print(ShipFactory shipFactory, String name, String email) {
  System.out.println(shipFactory.orderShip(name, email));
}

```

```
실무에서는 아래와 같은 단순한 factory method pattern을 많이 사용한다.
=> 매개변수의 값에 따라 또는 메소드에 따라 각기 다른 인스턴스를 리턴하는 단순한 버전의 팩토리 패턴


public Object createProduct(String name) {
  if (name.equals("whiteship")) {
    return new WhiteShip();
  } else if (name.equals("blackship")) {
    return new BlackShip();
  }

  throw new IllegalArgumentException();
}


JAVA  
- java.lang.Calendar OR java.lang.NumberFormat  

스프링 BeanFactory  
- Object 타입의 Product를 만드는 BeanFactory라는 Creator가 있다.
```
</details>








<details markdown="1">
<summary> 3. Abstract Factory Pattern  </summary>  


> 서로 관련있는 여러 객체를 만들어주는 인터페이스  
구체적으로 어떤 클래스의 인스턴스를 사용하는지 감출 수 있다.  
클라이언트 코드에서 구체적인 클래스의 의존성을 제거한다.  

![ship_abstract_factory](https://user-images.githubusercontent.com/94272140/205433590-3c64f954-bb97-49c6-ac8e-08b080ae818a.png)

```
Abstract Factory Pattern은 Factory pattern과 다르게 팩토리를 사용하는 방법에 초점을 두고있다.

// 사용
ShipFactory shipFactory = new WhiteShipFactory(new WhitePartsProFactory());
Ship ship = shipFactory.createShip();


// WhiteShipFactory
구체적인 팩토리 클래스 내부에서 추상화를 시켜놓은 배에 사용될 Parts 클래스를 주입받아 사용
private ShipPartsFactory shipPartsFactory;

public WhiteShipFactory(ShipPartsFactory shipPartsFactory) {
  this.shipPartsFactory = shipPartsFactory;
}

@Override
public Ship createShip() {
  Ship ship = new WhiteShip();
  ship.setAnchor(shipPartsFactory.createAnchor());
  ship.setWheel(shipPartsFactory.createWheel());
  return ship;
}
```




 







</details>


<details markdown="1">
<summary> 4. Factory Method Pattern VS Abstract Factory Pattern  </summary>  
  
  
- 모양과 효과는 비슷하지만,,,  
둘 다 구체적인 객체 생성 과정을 추상화한 인터페이스를 제공한다.  
  
  
- 관점이 다르다  
팩토리 메서드 패턴은 "팩토리를 구현하는 방법"에 초점을 둔다.  
추상 팩토리 패턴은 "팩토리를 사용하는 방법"에 초점을 둔다.  
  
  
- 목적이 조금 다르다  
팩토리 메서드 패턴은 구체적인 객체 생성 과정을 하위 또는 구체적인 클래스로 옮기는 것이 목적.  
추상 팩토리 패턴은 관련있는 여러 객체를 구체적인 클래스에 의존하지 않고 만들 수 있게 해주는 것이 목적.  
  

> 소스 내부 _04_study의 Computer Class를 기반으로 팩토리, 추상 팩토리 패턴 비교
<img width="2302" alt="스크린샷 2022-12-03 오후 6 20 28" src="https://user-images.githubusercontent.com/94272140/205433753-564fc281-9cec-41ac-8d04-72c640cafc97.png">



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








<details markdown="1">
<summary> 6. Fly Weight Pattern  </summary>  

> 객체를 가볍게 만들어 메모리 사용을 줄이는 패턴
자주 변하는 속성과 변하지 않는 속성을 분리하고 재사용하여 메모리 사용을 줄일 수 있다.

장점  
- 어플리케이션에서 사용하는 메모리를 줄일 수 있다.

단점  
- 복잡도가 증가한다.


```
-- old
// 자주 변하는 속성까지 계속해서 객체를 생성하고 있다.
Character c1 = new Character('h', "white", "Nanum", 12); 
Character c2 = new Character('e', "white", "Nanum", 12);
Character c3 = new Character('l', "white", "Nanum", 12);
...
```

```
-- new
// fontFamily, fontSize는 자주 변하지 않는 속성이라 판단하여 Font 클래스로 묶고 FontFactory 클래스에서 Cache를 통해 관리한다.

// Font 
// 인스턴스 공유를 목적으로 하기 때문에 final 키워드를 붙이고 getter만 구현한다.  
public final class Font {

    final String family;
    final int size;

    public Font(String family, int size) {
        this.family = family;
        this.size = size;
    }

// FontFactory
private Map<String, Font> cache = new HashMap<>();
public Font getFont(String font) {
  if (cache.containsKey(font)) {
    return cache.get(font);
  } else {
    String[] split = font.split(":");
    Font newFont = new Font(split[0], Integer.parseInt(split[1]));
    cache.put(font, newFont);
    return newFont;
  }
}


// Client
FontFactory fontFactory = new FontFactory();
Character c1 = new Character('h', "white", fontFactory.getFont("nanum:12"));
Character c2 = new Character('e', "white", fontFactory.getFont("nanum:12"));
...
```
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








<details markdown="1">
<summary> 9. Strategy Pattern (전략 패턴)  </summary>  

> 일을 수행하는 방법이 여러가지 일때, 여러 알고리즘을 각각의 개별적인 클래스로 캡슐화를 하고 캡슐화되어있는 것을 공통의 인터페이스로 추상화 해서  
로직을 사용하는 곳에서는 추상화된 인터페이스만 사용함으로써 코드는 바뀌지 않지만 알고리즘을 바꿔낄수 있는 패턴이다.

> 여러 알고리즘을 캡슐화 하고 상호 교환 가능하게 만드는 패턴.  
컨텍스트에서 사용할 알고리즘을 클라이언트가 직접 선택한다.

장점 
- 새로운 전략을 추가하더라도 기존 코드를 변경하지 않는다.
- 상속 대신 위임을 사용할 수 있다.
- 런타임에 전략을 변경할 수 있다.


단점
- 복잡도가 증가한다.
- 클라이언트 코드가 구체적인 전략을 알아야 한다.

```
1. Speed라는 인터페이스를 상속받은 전략 클래스를 생성한다.
public class Normal implements Speed {
    @Override
    public void blueLight() {
        System.out.println("무 궁 화    꽃   이");
    }

    @Override
    public void redLight() {
        System.out.println("피 었 습 니  다.");
    }
...

2. 파라미터로 인터페이스를 받아 사용한다.
public class BlueLightRedLight {
  public void blueLight(Speed speed) {
        speed.blueLight();
    }

    public void redLight(Speed speed) {
        speed.redLight();
    }
    ...

3. 전략만 바꿔주며 사용이 가능하며 익명클래스로 사용할 수 있다.
BlueLightRedLight game = new BlueLightRedLight();
        game.blueLight(new Normal());
        game.redLight(new Faster());

        game.blueLight(new Speed() {
            @Override
            public void blueLight() {

            }

            @Override
            public void redLight() {

            }
        });
```
</details>
