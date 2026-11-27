# Restaurant Ordering System

Advanced OOP final project (Istanbul Arel University). A small restaurant ordering domain written in Java with JUnit 5.
There is no `main` method and no user interface: the unit tests are the only way the code runs.

## Tech stack
- Java 22
- Maven
- JUnit 5

## Run the tests
```
mvn clean test
```

## Features
- Menu with food, drinks and desserts (add, find by name, filter by category)
- Orders: add and remove items, merged order lines, total calculation
- Order lifecycle: new, paid, cancelled (cancel and pay rules enforced by states)
- Payment: cash (change returned) and card (2% fee, 16-digit card number)
- Menu items created through a factory

## Project structure
```
src/main/java/restaurant
├── customer    Customer
├── exception   RestaurantException (base) + 6 specific exceptions
├── menu        MenuItem (abstract), Food, Drink, Dessert, Menu, MenuItemFactory
├── order       OrderItem, Order, OrderState (+ NewOrderState, PaidOrderState, CancelledOrderState)
└── payment     PaymentStrategy, PaymentResult, CashPayment, CardPayment
src/test/java/restaurant
├── customer    CustomerTest
├── menu        MenuItemTest, MenuTest, MenuItemFactoryTest
├── order       OrderItemTest, OrderTest, OrderStateTest, OrderPayTest
└── payment     CashPaymentTest, CardPaymentTest
```

## Design patterns
| Pattern | Where | Problem it solves |
|---|---|---|
| State | `OrderState`, `NewOrderState`, `PaidOrderState`, `CancelledOrderState` | Avoids scattered `if (paid)` / `if (cancelled)` checks. Each state decides what is allowed, and a new state does not change `Order`. |
| Strategy | `PaymentStrategy`, `CashPayment`, `CardPayment` | Each payment method has different rules. A new method is a new class, with no change to `Order`. |
| Factory | `MenuItemFactory` | One place converts an item type into the right `MenuItem` subclass. |

## Exceptions
All exceptions are **unchecked**: `RestaurantException` extends `RuntimeException`, and every specific exception extends `RestaurantException`. A failed operation throws an exception with a clear message and never returns `null` or prints an error.

| Exception | Thrown when | Tested in |
|---|---|---|
| `RestaurantException` | Invalid name, price, id, null argument, unknown item type | `MenuItemTest`, `CustomerTest`, `OrderTest`, `MenuItemFactoryTest` |
| `ItemNotFoundException` | Item is not in the menu or in the order | `MenuTest`, `OrderTest` |
| `InvalidQuantityException` | Quantity is zero or negative | `OrderItemTest`, `OrderTest` |
| `EmptyOrderException` | Paying an empty order | `OrderStateTest`, `OrderPayTest` |
| `OrderAlreadyPaidException` | Changing, cancelling or paying a paid order | `OrderStateTest`, `OrderPayTest` |
| `OrderCancelledException` | Changing, cancelling or paying a cancelled order | `OrderStateTest`, `OrderPayTest` |
| `InvalidPaymentException` | Cash too low, bad card number, invalid amount | `CashPaymentTest`, `CardPaymentTest`, `OrderPayTest` |

## SOLID
| Principle | Where | How |
|---|---|---|
| Single Responsibility | `Menu`, `Order`, `OrderItem`, payment classes | `Menu` manages the menu, `Order` manages the items and state, each payment class owns one payment rule. |
| Open/Closed | `PaymentStrategy`, `OrderState` | A new payment method or order state is added as a new class without editing existing ones. |
| Liskov Substitution | `MenuItem` and `Food`/`Drink`/`Dessert` | Any subclass works wherever a `MenuItem` is expected. |
| Interface Segregation | `PaymentStrategy` | A small interface with two methods only. |
| Dependency Inversion | `Order.pay(PaymentStrategy)` | `Order` depends on the interface, not on `CashPayment` or `CardPayment`. |

## Other design decisions
- **`MenuItem` is abstract.** Name and price validation live in one place, and subclasses only define their category.
- **Encapsulation.** `Menu.getItems()` and `Order.getItems()` return unmodifiable lists, and `Customer`, `MenuItem` and `OrderItem` are immutable.
- **Order lines are merged.** Adding the same item twice increases the quantity of one line.
- **Payment is atomic.** `Order.pay` validates the order, charges, and only then marks it paid, so a failed payment leaves the order in `NEW`.
- **No system clock.** No code reads the current date.

## Testing
- 73 unit tests, all passing.
- Every public method is tested for success and for failure, and every exception above is thrown by at least one test.
- Each test checks one behavior and its name states it (`method_condition_expectedResult`).
- Not covered: simple getters and the private constructor of `MenuItemFactory`.

## Progress log
| Date | Step | Status |
|---|---|---|
| 2026-11-08 | Maven project setup with JUnit 5 | Done |
| 2026-11-08 | Exception hierarchy (7 classes) | Done |
| 2026-11-08 | `MenuItem`, `Food`, `Drink`, `Dessert` + tests | Done |
| 2026-11-08 | `Menu` (add, find by name, filter by category) + tests | Done |
| 2026-11-08 | `Customer` + tests | Done |
| 2026-11-08 | `OrderItem` (menu item + quantity) + tests | Done |
| 2026-11-10 | `Order`: add and remove items, `getTotal` + tests | Done |
| 2026-11-10 | State pattern for the order and cancel + tests | Done |
| 2026-11-17 | `PaymentStrategy`, `PaymentResult`, `CashPayment` + tests | Done |
| 2026-11-27 | `CardPayment` (2% fee, 16-digit card number) + tests | Done |
| 2026-11-27 | `Order.pay` linking payment to order state + tests | Done |
| 2026-11-27 | `MenuItemFactory` + tests | Done |
| | UML diagram and final report | Planned |