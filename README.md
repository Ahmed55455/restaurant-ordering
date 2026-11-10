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

## Project structure
```
src/main/java/restaurant
├── customer    Customer
├── exception   RestaurantException (base) + 6 specific exceptions
├── menu        MenuItem (abstract), Food, Drink, Dessert, Menu
└── order       OrderItem, Order
src/test/java/restaurant
├── customer    CustomerTest
├── menu        MenuItemTest, MenuTest
└── order       OrderItemTest, OrderTest
```

## Design decisions so far
- **Exceptions are unchecked.** Every exception extends `RestaurantException`, which extends `RuntimeException`. Failed operations throw an exception with a clear message and never return `null`.
- **`MenuItem` is abstract.** Name and price validation live in one place, and `Food`, `Drink` and `Dessert` only define their category.
- **Encapsulation.** `Menu.getItems()` and `Order.getItems()` return unmodifiable lists, and `Customer`, `MenuItem` and `OrderItem` are immutable.
- **Order lines are merged.** Adding the same item twice increases the quantity of one line instead of creating a duplicate line.

## Progress log

| Date | Step | Status |
|---|---|---|
| 2026-11-08 | Maven project setup with JUnit 5 | Done |
| 2026-11-08 | Exception hierarchy (7 classes) | Done |
| 2026-11-08 | `MenuItem`, `Food`, `Drink`, `Dessert` + tests | Done |
| 2026-11-08 | `Menu` (add, find by name, filter by category) + tests | Done |
| 2026-11-08 | `Customer` + tests | Done |
| 2026-11-08 | `OrderItem` (menu item + quantity) + tests | Done |
| 2026-11-10 | `Order`: add and remove items + tests | Done |
| 2026-11-10 | `Order.getTotal` + tests | Done |
| | State pattern for the order (new, paid, cancelled) and cancel | Planned |
| | Payment with Strategy pattern (cash and card) | Planned |
| | `MenuItemFactory` (Factory pattern) | Planned |
| | Test gap review, SOLID review, UML, final report | Planned |

## Current status
- 37 unit tests, all passing.
- Exceptions thrown so far: `RestaurantException`, `ItemNotFoundException`, `InvalidQuantityException`.
- Exceptions not thrown yet (they will be once order states and payment exist): `EmptyOrderException`, `OrderAlreadyPaidException`, `OrderCancelledException`, `InvalidPaymentException`.