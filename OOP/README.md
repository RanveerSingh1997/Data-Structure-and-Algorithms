# 🏛️ Object-Oriented Programming & Low-Level Design (LLD) Module 🚀

Welcome to the **OOP & Low-Level Design (LLD)** preparation module! This module is dedicated to mastering machine coding, object-oriented analysis and design (OOAD), design patterns, and clean architecture for Tier-1 software engineering interviews (Google, Amazon, Microsoft, Uber, Meta, Bloomberg).

For the full theoretical foundation, SOLID principles deep-dive, GoF design patterns guide, and concurrency playbooks, read the **[📖 LLD & OOP Mastery Guide](../DSANotes/OOP_and_LLD_Mastery_Guide.md)**.

---

## 🎯 Curated Problem Practice Queue

Each problem in this module is structured as a standalone, production-quality interview template with clear requirement briefs, domain entities, enums, interfaces, extension points, and a built-in `main()` simulation driver.

| # | Problem Name | Focus & Domain | Difficulty | Target Companies | Key Design Patterns | File Link |
| :-: | :--- | :--- | :-: | :--- | :--- | :--- |
| **1** | **Design Parking Lot** | Resource Allocation, Multi-floor, Gates | 🟡 Medium | Amazon, Google, Uber | Strategy, Factory, Singleton | [`DesignParkingLot.java`](./src/DesignParkingLot.java) |
| **2** | **Design Elevator System** | State Machine, Scheduling & SCAN Algorithm | 🔴 Medium–Hard | Google, Microsoft, Amazon | State, Strategy, Observer | [`DesignElevatorSystem.java`](./src/DesignElevatorSystem.java) |
| **3** | **Design Chess Game** | Complex Rules, Turn Management, Move Validation | 🔴 Hard | Google, Amazon, Uber | Command, Factory, Template Method | [`DesignChess.java`](./src/DesignChess.java) |
| **4** | **Design Connect Four** | Grid Games, Gravity Placement, Win Invariant | 🟡 Medium | Google, Microsoft, Meta | Strategy, Command | [`DesignConnectFour.java`](./src/DesignConnectFour.java) |
| **5** | **Design Blackjack** | Deck Modeling, Hand Scoring, Dynamic Ace | 🟡 Medium | Bloomberg, Amazon, Meta | Strategy, Factory, Composite | [`DesignBlackJack.java`](./src/DesignBlackJack.java) |
| **6** | **Design Banking System** | Concurrency, ACID, Audit Logs & Deadlocks | 🔴 Medium–Hard | Goldman Sachs, Stripe, Google Pay | Template Method, Strategy, Command | [`DesignBank.java`](./src/DesignBank.java) |
| **7** | **Design Movie Recommendation** | Pluggable Recommendation Strategies & Scoring | 🟡 Medium | Netflix, Amazon, Spotify | Strategy, Builder, Observer | [`DesginMovieRecomendation.java`](./src/DesginMovieRecomendation.java) |

---

## ⚡ How to Compile & Run Practice Problems

Every design problem has its own independent `main(String[] args)` method with end-to-end verification scenarios.

### In IntelliJ IDEA:
1. Open any file in `OOP/src/` (e.g., `DesignParkingLot.java`).
2. Click the green **Run (▶)** button next to the `main` method or press `Ctrl + Shift + R` (`Control + Shift + R` on macOS).

### Via Terminal:
```bash
# From repository root directory:

# Compile all OOP files into out directory
javac -d out OOP/src/*.java

# Run any problem's test driver
java -cp out DesignParkingLot
java -cp out DesignElevatorSystem
java -cp out DesignChess
java -cp out DesignConnectFour
java -cp out DesignBlackJack
java -cp out DesignBank
java -cp out DesginMovieRecomendation
```

---

## ⏱️ 45-Minute LLD Interview Time Allocation

When practicing or in a live interview, time-box your solution into these 5 phases:

```text
[00:00 - 08:00]  1. Clarify Requirements, Constraints, Actors & Scope
[08:00 - 15:00]  2. Identify Core Entities, Enums, Value Objects & Data Structures
[15:00 - 22:00]  3. Establish Interfaces, Abstractions & UML Relationships
[22:00 - 37:00]  4. Implement Business Logic & Wire Design Patterns
[37:00 - 45:00]  5. Concurrency, Edge Cases & Verification Test Run
```

---

## 🧠 Pre-Practice Quick Checklist

Before coding each problem:
1. **Clarify Scope**: What is IN-SCOPE and what is OUT-OF-SCOPE? (e.g., DB persistence, REST API, GUI are usually out of scope).
2. **Defensive Design**: Guard against `null`, negative values, full capacity, and unauthorized actions.
3. **Open/Closed Principle**: Can you plug in a new strategy (e.g., a new vehicle type, pricing policy, or elevator scheduling algorithm) without editing existing classes?
4. **Thread-Safety**: Identify where concurrent access can happen (e.g., simultaneous gate entries, concurrent money transfers, multiple hall calls) and safeguard with atomic primitives or locks.
