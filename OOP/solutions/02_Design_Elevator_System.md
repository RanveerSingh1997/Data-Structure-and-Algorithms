# 🛗 Solution & Architecture: Multi-Car Elevator Control System

**Problem**: Design a software controller for $K$ elevator cars operating across $N$ floors.  
**Difficulty**: Medium–Hard | **Target Companies**: Google, Microsoft, Amazon, Uber  
**Practice Template**: [`OOP/src/DesignElevatorSystem.java`](../src/DesignElevatorSystem.java)

---

## 1. Requirements & Clarifications

### Functional Requirements:
1. **Multi-Car Dispatcher**: $K$ cars serve calls across $N$ floors.
2. **Two Call Types**:
   - **External Hall Call**: Passenger on floor $F$ presses UP or DOWN.
   - **Internal Destination Call**: Passenger inside car $C$ presses destination floor $D$.
3. **LOOK / SCAN Scheduling Algorithm**:
   - The car moves in the current direction, picking up and dropping off all passengers along the way.
   - Reverses direction only when no pending requests exist ahead in the current direction.
4. **State Transitions**: `IDLE` $\leftrightarrow$ `MOVING_UP` $\leftrightarrow$ `MOVING_DOWN`.

---

## 2. LOOK / SCAN Scheduling Mental Model

Instead of servicing requests in First-Come-First-Served (FCFS) order (which would cause wild thrashing up and down), the elevator operates like an elevator SCAN algorithm:

```text
Floor 10 |  [Up Request 8]
Floor 8  |  [Dest Call 8]  <-- Stop here!
Floor 6  |  Elevator (Moving UP)
Floor 4  |  [Down Request 3] (Ignore until car reverses to DOWN)
Floor 1  |
```

### Data Structure Selection:
- **`upRequests`**: A `TreeSet<Integer>` sorted in **ascending order**.
- **`downRequests`**: A `TreeSet<Integer>` sorted in **descending order** (`Collections.reverseOrder()`).

---

## 3. Reference Implementation Snippets

```java
// LOOK/SCAN step execution:
public synchronized void step() {
    if (direction == Direction.UP) {
        if (!upRequests.isEmpty()) {
            currentFloor++;
            if (upRequests.contains(currentFloor)) {
                upRequests.remove(currentFloor); // Stop for passenger
            }
        }
        // Reverse direction if upRequests exhausted
        if (upRequests.isEmpty()) {
            direction = downRequests.isEmpty() ? Direction.IDLE : Direction.DOWN;
            state = downRequests.isEmpty() ? ElevatorState.IDLE : ElevatorState.MOVING_DOWN;
        }
    } else if (direction == Direction.DOWN) {
        if (!downRequests.isEmpty()) {
            currentFloor--;
            if (downRequests.contains(currentFloor)) {
                downRequests.remove(currentFloor); // Stop for passenger
            }
        }
        if (downRequests.isEmpty()) {
            direction = upRequests.isEmpty() ? Direction.IDLE : Direction.UP;
            state = upRequests.isEmpty() ? ElevatorState.IDLE : ElevatorState.MOVING_UP;
        }
    }
}

// Dispatch Strategy (Nearest Car in Same Direction):
public ElevatorCar selectBestElevator(List<ElevatorCar> cars, Request request) {
    ElevatorCar bestCar = null;
    int minDistance = Integer.MAX_VALUE;

    for (ElevatorCar car : cars) {
        int distance = Math.abs(car.getCurrentFloor() - request.getFloor());
        boolean sameDirection = (car.getDirection() == request.getDesiredDirection());
        boolean onTheWay = (car.getDirection() == Direction.UP && car.getCurrentFloor() <= request.getFloor()) ||
                           (car.getDirection() == Direction.DOWN && car.getCurrentFloor() >= request.getFloor());

        if ((sameDirection && onTheWay) || car.getDirection() == Direction.IDLE) {
            if (distance < minDistance) {
                minDistance = distance;
                bestCar = car;
            }
        }
    }
    return bestCar != null ? bestCar : cars.get(0);
}
```

---

## 4. Key Interview Pitfalls to Avoid

1. **Starvation of Reverse Requests**: If requests keep arriving in the UP direction, DOWN requests could starve. The SCAN algorithm guarantees reversal once current direction calls are exhausted.
2. **Synchronized Car State**: Hall calls and internal calls can happen concurrently from different passenger interactions; ensure `addDestination()` and `step()` are synchronized on the car.
