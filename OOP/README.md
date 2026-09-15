# 🏛️ Object-Oriented Programming & Low-Level Design (LLD) Module 🚀

Welcome to the **OOP & Low-Level Design (LLD)** preparation module! This module is dedicated to mastering machine coding, object-oriented analysis and design (OOAD), Gang-of-Four (GoF) design patterns, clean architecture, and thread safety for top-tier software engineering interviews (Google, Amazon, Microsoft, Uber, Meta, Bloomberg, Goldman Sachs).

All problem templates in this module are located in [`src/`](./src) and are formatted as **unsolved practice templates** with clear `// TODO` implementation markers, domain models, contracts, and built-in interactive test harnesses.

---

## 📑 Table of Contents

1. [🎯 Curated Problem Practice Queue](#-curated-problem-practice-queue)
2. [⏱️ The 5-Step 45-Minute LLD Interview Playbook](#-the-5-step-45-minute-lld-interview-playbook)
3. [📐 SOLID Principles Quick Reference](#-solid-principles-quick-reference)
4. [🎨 Core GoF Design Patterns for LLD Interviews](#-core-gof-design-patterns-for-lld-interviews)
5. [🧵 Concurrency & Thread-Safety Checklist](#-concurrency--thread-safety-checklist)
6. [🗺️ Detailed Breakdown of the 7 Practice Problems](#-detailed-breakdown-of-the-7-practice-problems)
7. [⚡ How to Compile & Run Practice Problems](#-how-to-compile--run-practice-problems)
8. [✅ Pre-Push Self-Assessment Rubric](#-pre-push-self-assessment-rubric)

---

## 🎯 Curated Problem Practice Queue

> 📊 **Progress Tracker**: Track your solved problems in **[`OOP/SOLVED.md`](./SOLVED.md)**.

| # | System Design Problem | Focus & Domain | Difficulty | Target Companies | Key Design Patterns | Practice Template | Reference Solution |
| :-: | :--- | :--- | :-: | :--- | :--- | :--- | :--- |
| **1** | **Design Parking Lot** | Resource Allocation, Multi-floor, Gates, Ticketing | 🟡 Medium | Amazon, Google, Uber | Strategy, Factory, Singleton | [`DesignParkingLot.java`](./src/DesignParkingLot.java) | [Solution & Architecture](./solutions/01_Design_Parking_Lot.md) |
| **2** | **Design Elevator System** | Scheduling (LOOK/SCAN), State Transitions, Multi-car | 🔴 Medium–Hard | Google, Microsoft, Amazon | State, Strategy, Observer | [`DesignElevatorSystem.java`](./src/DesignElevatorSystem.java) | [Solution & Architecture](./solutions/02_Design_Elevator_System.md) |
| **3** | **Design Chess Game** | Complex Rules, Turn Management, Move Validation | 🔴 Hard | Google, Amazon, Uber | Command, Factory, Template Method | [`DesignChess.java`](./src/DesignChess.java) | [Solution & Architecture](./solutions/03_Design_Chess.md) |
| **4** | **Design Connect Four** | Gravity Grid, $O(1)$ Directional Win Evaluation | 🟡 Medium | Google, Microsoft, Meta | Strategy, Command | [`DesignConnectFour.java`](./src/DesignConnectFour.java) | [Solution & Architecture](./solutions/04_Design_Connect_Four.md) |
| **5** | **Design Blackjack** | 52-Card Deck/Shoe, Hand Scoring, Dynamic Ace (1/11) | 🟡 Medium | Bloomberg, Amazon, Meta | Strategy, Factory, Composite | [`DesignBlackJack.java`](./src/DesignBlackJack.java) | [Solution & Architecture](./solutions/05_Design_Blackjack.md) |
| **6** | **Design Banking System** | Thread-Safe Accounts, ACID, Deadlock-Free Transfers | 🔴 Medium–Hard | Goldman Sachs, Stripe, Google Pay | Template Method, Strategy, Command | [`DesignBank.java`](./src/DesignBank.java) | [Solution & Architecture](./solutions/06_Design_Banking_System.md) |
| **7** | **Design Movie Recommendation** | Pluggable Recommendation Strategies (Genre/Rating) | 🟡 Medium | Netflix, Amazon, Spotify | Strategy, Builder, Observer | [`DesginMovieRecomendation.java`](./src/DesginMovieRecomendation.java) | [Solution & Architecture](./solutions/07_Design_Movie_Recommendation.md) |


---

## ⏱️ The 5-Step 45-Minute LLD Interview Playbook

When practicing or during an actual technical interview, time-box your solution into these 5 distinct phases:

```text
[00:00 - 08:00]  STEP 1: Clarify Requirements, Constraints, Actors & Scope
[08:00 - 15:00]  STEP 2: Identify Core Entities, Enums & Value Objects
[15:00 - 22:00]  STEP 3: Establish Contracts, Interfaces & Class Relationships
[22:00 - 37:00]  STEP 4: Implement Business Logic & Wire Design Patterns
[37:00 - 45:00]  STEP 5: Concurrency, Edge Cases & Verification Test Run
```

### Step 1: Clarify Requirements & Scope (0–8 mins)
- Ask 3–4 clarifying questions:
  - *"Is the parking lot single-floor or multi-floor?"*
  - *"Are rates hourly, flat, or dynamic surge?"*
  - *"Do we need concurrency protection for multiple gates?"*
- Explicitly define **Out-of-Scope** items (e.g., UI, DB persistence, networking, external sensors).

### Step 2: Identify Core Entities (8–15 mins)
- Extract nouns from the problem statement:
  - *Parking Lot*: `Vehicle`, `ParkingSpot`, `ParkingFloor`, `Ticket`, `Receipt`.
  - *Elevator*: `ElevatorCar`, `Request`, `Direction`, `ElevatorState`.
- Define **Enums** first (`VehicleType`, `SpotType`, `Direction`, `GameStatus`) to avoid primitive obsession.

### Step 3: Define Contracts & Interfaces (15–22 mins)
- Design interfaces for extensible components (Open/Closed Principle):
  - `ParkingStrategy`: `findSpot(List<ParkingFloor> floors, Vehicle vehicle)`
  - `PricingStrategy`: `calculateFee(ParkingTicket ticket, LocalDateTime exitTime)`
  - `DispatchStrategy`: `selectBestElevator(List<ElevatorCar> cars, Request request)`

### Step 4: Implement Business Logic (22–37 mins)
- Write cohesive methods with clear responsibility.
- Encapsulate internal collections (`Collections.unmodifiableList()`).
- Handle transitions cleanly (e.g. `spot.park(vehicle)` marks spot occupied and binds vehicle).

### Step 5: Test Run & Edge Cases (37–45 mins)
- Execute the built-in `main()` driver method to verify happy path and edge cases (e.g., lot full, invalid moves, insufficient funds).

---

## 📐 SOLID Principles Quick Reference

| Principle | Meaning in Plain English | Real-World LLD Example |
| :--- | :--- | :--- |
| **S - Single Responsibility** | A class should have only one reason to change. | Separate `FeeCalculator` from `ParkingLot`. Do not mix payment processing with spot management. |
| **O - Open/Closed** | Open for extension, closed for modification. | Use `PricingStrategy` interface. Adding an Electric Vehicle charging fee doesn't require modifying existing car pricing code. |
| **L - Liskov Substitution** | Subtypes must be substitutable for base types without breaking behavior. | `Car` and `Motorcycle` extending `Vehicle` must fulfill all contracts of `Vehicle` without throwing `UnsupportedOperationException`. |
| **I - Interface Segregation** | Prefer small, client-specific interfaces over one giant "fat" interface. | Split a giant `GameController` into `BoardGame` and `CardGame` so implementations only implement methods they actually use. |
| **D - Dependency Inversion** | Depend on abstractions (interfaces), not concrete classes. | `DesignParkingLot` depends on `ParkingStrategy` and `PricingStrategy`, injected via constructor or setter. |

---

## 🎨 Core GoF Design Patterns for LLD Interviews

| Pattern Type | Pattern Name | Problem Signal / Trigger | Example in this Module |
| :--- | :--- | :--- | :--- |
| **Creational** | **Factory Method** | Creation logic depends on type strings or enums. | Creating `Vehicle` (Car, Bike, Truck) or `ChessPiece` (Pawn, Knight). |
| **Creational** | **Builder** | Object has many optional attributes. | Creating a complex `Movie` query or `ParkingTicket`. |
| **Creational** | **Singleton** | Exactly one coordinated global instance needed. | `ParkingLot` or `ElevatorSystem` controller. |
| **Behavioral** | **Strategy** | Multiple interchangeable algorithms at runtime. | `PricingStrategy` in Parking Lot; `RecommendationStrategy` in Movie system. |
| **Behavioral** | **State** | Object changes behavior when internal state changes. | `ElevatorCar` states (`IDLE`, `MOVING_UP`, `MOVING_DOWN`). |
| **Behavioral** | **Observer** | When one object changes state, dependents are notified. | Floor display boards updating when parking spots fill. |
| **Behavioral** | **Command** | Encapsulate actions as objects for history and undo. | `Move` in `DesignChess` (allows `execute()` and `undo()`). |
| **Structural** | **Composite** | Part-whole hierarchies treated uniformly. | `Card` -> `Deck` -> `Hand` in `DesignBlackJack`. |

---

## 🧵 Concurrency & Thread-Safety Checklist

In machine coding and LLD interviews, interviewers frequently ask:
> *"What if 5 cars enter simultaneously through different gates?"*
> *"What if two users transfer money between each other at the exact same millisecond?"*

### Key Principles to Apply:
1. **Critical Sections**: Protect shared mutable state (spots, account balances, elevator queues) using `synchronized` blocks or `ReentrantLock`.
2. **Deadlock Avoidance in Transfers**:
   - Never acquire multiple locks in random order.
   - Always acquire locks in **lexicographical / deterministic order** (e.g. `acc1.getId().compareTo(acc2.getId()) < 0 ? acc1 : acc2`).
3. **Atomic Counters**: Use `AtomicInteger` for thread-safe ID generation (`ticketId`, `transactionId`).
4. **Concurrent Collections**: Use `ConcurrentHashMap` for fast, thread-safe lookups without coarse-grained locking.

---

## 🗺️ Detailed Breakdown of the 7 Practice Problems

### Problem 1: Design Parking Lot
*File*: [`src/DesignParkingLot.java`](./src/DesignParkingLot.java)  
- **Key Classes**: `Vehicle` (Car, Motorcycle, Truck, ElectricCar), `ParkingSpot`, `ParkingFloor`, `ParkingTicket`, `ParkingReceipt`, `DesignParkingLot`.
- **Patterns**: Strategy (`ParkingStrategy`, `PricingStrategy`), Factory.
- **Key Challenge**: Handling multiple spot types (Motorcycle fits anywhere; Car fits Compact/Large; Truck fits Large only). Thread-safe spot allocation.

### Problem 2: Design Elevator System
*File*: [`src/DesignElevatorSystem.java`](./src/DesignElevatorSystem.java)  
- **Key Classes**: `ElevatorCar`, `Request`, `Direction`, `ElevatorState`, `LookDispatchStrategy`, `DesignElevatorSystem`.
- **Patterns**: State Pattern, Strategy Pattern (`DispatchStrategy`).
- **Key Challenge**: LOOK/SCAN scheduling algorithm (serving all requests in current direction before reversing direction).

### Problem 3: Design Chess Game
*File*: [`src/DesignChess.java`](./src/DesignChess.java)  
- **Key Classes**: `Board`, `Spot`, `Piece` (King, Queen, Rook, Bishop, Knight, Pawn), `Move`, `DesignChess`.
- **Patterns**: Command Pattern (`Move.execute()`, `Move.undo()`), Polymorphism.
- **Key Challenge**: Validating valid moves per piece and checking intermediate path clearance (`isPathClear`).

### Problem 4: Design Connect Four
*File*: [`src/DesignConnectFour.java`](./src/DesignConnectFour.java)  
- **Key Classes**: `Board`, `Player`, `DiscColor`, `GameStatus`, `DesignConnectFour`.
- **Patterns**: Strategy Pattern (`WinConditionStrategy`).
- **Key Challenge**: Simulating gravity drops to lowest row, and $O(1)$ win evaluation checking 4 directions from the newly placed disc.

### Problem 5: Design Blackjack
*File*: [`src/DesignBlackJack.java`](./src/DesignBlackJack.java)  
- **Key Classes**: `Card`, `Suit`, `Rank`, `Deck`, `Hand`, `Player`, `Dealer`, `DesignBlackJack`.
- **Patterns**: Strategy Pattern (Dealer fixed strategy vs Player choices).
- **Key Challenge**: Dynamic Ace calculation: count Ace as 11, but automatically downgrade to 1 if hand total exceeds 21. Dealer Soft-17 rule.

### Problem 6: Design Banking System
*File*: [`src/DesignBank.java`](./src/DesignBank.java)  
- **Key Classes**: `Account`, `SavingsAccount`, `CheckingAccount`, `Transaction`, `DesignBank`.
- **Patterns**: Template Method (`canWithdraw()`), Command Pattern.
- **Key Challenge**: Enforcing minimum balance vs overdraft limits; thread-safe atomic transfers with deterministic lock ordering to prevent deadlocks.

### Problem 7: Design Movie Recommendation System
*File*: [`src/DesginMovieRecomendation.java`](./src/DesginMovieRecomendation.java)  
- **Key Classes**: `Movie`, `User`, `Genre`, `RecommendationStrategy`, `TopRatedStrategy`, `GenreBasedStrategy`, `DesginMovieRecomendation`.
- **Patterns**: Strategy Pattern, Builder Pattern.
- **Key Challenge**: Filtering unwatched movies, ranking by average rating and genre overlap with user's highest rated movies (rated $\ge 4.0$).

---

## ⚡ How to Compile & Run Practice Problems

Every problem template contains its own standalone `main(String[] args)` test harness that provides interactive `[PASS]` / `[TODO]` feedback.

### In IntelliJ IDEA:
1. Open any file in `OOP/src/` (e.g. `DesignParkingLot.java`).
2. Click the green **Run (▶)** icon in the gutter next to `main()` or press `Ctrl + Shift + R` (`Control + Shift + R` on macOS).

### Via Terminal:
```bash
# 1. Compile all templates
javac -d out OOP/src/*.java

# 2. Run any problem to verify your implementation:
java -cp out DesignParkingLot
java -cp out DesignElevatorSystem
java -cp out DesignChess
java -cp out DesignConnectFour
java -cp out DesignBlackJack
java -cp out DesignBank
java -cp out DesginMovieRecomendation
```

---

## ✅ Pre-Push Self-Assessment Rubric

Before committing your solved problem:
- [ ] Are all `// TODO` implementation markers replaced with clean, working code?
- [ ] Does the `main()` method output `[PASS]` for all test cases without errors?
- [ ] Are fields marked `private` and appropriately `final`?
- [ ] Did you guard against invalid inputs (`null`, negative values, out-of-bounds)?
- [ ] Are shared mutable data structures protected against concurrent race conditions?
