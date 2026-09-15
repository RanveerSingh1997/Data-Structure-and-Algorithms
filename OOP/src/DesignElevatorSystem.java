import java.util.*;

/**
 * ============================================================================
 * Practice Template: Multi-Car Elevator Control System (LLD)
 * Difficulty: Medium–Hard | Target Companies: Google, Microsoft, Amazon, Uber
 * ============================================================================
 *
 * 📖 PROBLEM DESCRIPTION:
 * Design a software controller for an elevator system operating in an N-floor
 * building with K elevator cars. The controller must handle:
 * 1. External hall calls: Passengers on floors pressing UP or DOWN.
 * 2. Internal destination calls: Passengers inside an elevator selecting a floor.
 * 3. LOOK / SCAN Scheduling:
 *    - The car continues moving in its current direction, serving all requests
 *      along its path.
 *    - When there are no further requests in the current direction, it switches
 *      direction or becomes IDLE.
 *
 * 💡 INTERVIEW HINTS:
 * - Maintain two sorted request collections per car: `upRequests` (min-heap or TreeSet)
 *   and `downRequests` (max-heap or reverse TreeSet).
 * - Use the State Pattern or an enum for elevator direction (`UP`, `DOWN`, `IDLE`).
 * - In `selectBestElevator`, prioritize cars already moving towards the request in the same direction.
 */
public class DesignElevatorSystem {

    // =========================================================================
    // 1. ENUMS & VALUE OBJECTS
    // =========================================================================

    public enum Direction {
        UP, DOWN, IDLE
    }

    public enum ElevatorState {
        IDLE, MOVING_UP, MOVING_DOWN, MAINTENANCE
    }

    public enum RequestType {
        HALL_CALL,       // Passenger waiting on a floor
        INTERNAL_DEST    // Passenger inside car selected a floor
    }

    public static class Request {
        private final int floor;
        private final Direction desiredDirection;
        private final RequestType type;

        public Request(int floor, Direction desiredDirection, RequestType type) {
            this.floor = floor;
            this.desiredDirection = desiredDirection;
            this.type = type;
        }

        public int getFloor() { return floor; }
        public Direction getDesiredDirection() { return desiredDirection; }
        public RequestType getType() { return type; }
    }

    // =========================================================================
    // 2. ELEVATOR CAR
    // =========================================================================

    public static class ElevatorCar {
        private final int carId;
        private int currentFloor;
        private Direction direction;
        private ElevatorState state;

        // Up requests sorted ascending; Down requests sorted descending
        private final TreeSet<Integer> upRequests;
        private final TreeSet<Integer> downRequests;

        public ElevatorCar(int carId, int initialFloor) {
            this.carId = carId;
            this.currentFloor = initialFloor;
            this.direction = Direction.IDLE;
            this.state = ElevatorState.IDLE;
            this.upRequests = new TreeSet<>();
            this.downRequests = new TreeSet<>(Collections.reverseOrder());
        }

        /**
         * Adds a destination floor to the elevator's queue.
         */
        public synchronized void addDestination(int floor) {
            // TODO: Implement destination queuing:
            // 1. If floor == currentFloor, already here!
            // 2. If floor > currentFloor, add to upRequests. If idle, set direction = UP, state = MOVING_UP.
            // 3. If floor < currentFloor, add to downRequests. If idle, set direction = DOWN, state = MOVING_DOWN.
        }

        /**
         * Simulates one tick of elevator movement following the LOOK/SCAN algorithm.
         */
        public synchronized void step() {
            // TODO: Implement LOOK/SCAN algorithm:
            // 1. If direction == UP:
            //    - advance currentFloor++
            //    - if upRequests contains currentFloor, remove it (stop to pick up/drop off)
            //    - if upRequests becomes empty, check downRequests. If non-empty, switch direction = DOWN; else IDLE.
            // 2. If direction == DOWN:
            //    - advance currentFloor--
            //    - if downRequests contains currentFloor, remove it
            //    - if downRequests becomes empty, check upRequests. If non-empty, switch direction = UP; else IDLE.
        }

        public int getCarId() { return carId; }
        public int getCurrentFloor() { return currentFloor; }
        public Direction getDirection() { return direction; }
        public ElevatorState getState() { return state; }
        public boolean hasPendingRequests() { return !upRequests.isEmpty() || !downRequests.isEmpty(); }
    }

    // =========================================================================
    // 3. DISPATCH STRATEGY
    // =========================================================================

    public interface DispatchStrategy {
        ElevatorCar selectBestElevator(List<ElevatorCar> cars, Request request);
    }

    public static class LookDispatchStrategy implements DispatchStrategy {
        @Override
        public ElevatorCar selectBestElevator(List<ElevatorCar> cars, Request request) {
            // TODO: Implement elevator selection strategy:
            // 1. Look for a car moving in the requested direction that hasn't passed the floor yet.
            // 2. Otherwise find the closest IDLE car.
            // 3. Fallback to the car with minimum absolute distance.
            return cars.isEmpty() ? null : cars.get(0);
        }
    }

    // =========================================================================
    // 4. ELEVATOR SYSTEM CONTROLLER
    // =========================================================================

    private final int totalFloors;
    private final List<ElevatorCar> cars;
    private final DispatchStrategy dispatchStrategy;

    public DesignElevatorSystem(int totalFloors, int numberOfCars) {
        this.totalFloors = totalFloors;
        this.cars = new ArrayList<>();
        for (int i = 1; i <= numberOfCars; i++) {
            cars.add(new ElevatorCar(i, 1));
        }
        this.dispatchStrategy = new LookDispatchStrategy();
    }

    /**
     * External Hall Call: Passenger on fromFloor presses UP or DOWN.
     */
    public synchronized void requestElevator(int fromFloor, Direction direction) {
        // TODO: Validate floor, construct Request, select best car via dispatchStrategy, and addDestination
    }

    /**
     * Internal Call: Passenger inside car presses destination floor button.
     */
    public synchronized void selectFloor(int carId, int destinationFloor) {
        // TODO: Validate destinationFloor, find car by carId, and call car.addDestination(destinationFloor)
    }

    public void stepAll() {
        for (ElevatorCar car : cars) {
            if (car.hasPendingRequests()) {
                car.step();
            }
        }
    }

    public List<ElevatorCar> getCars() { return Collections.unmodifiableList(cars); }

    // =========================================================================
    // 5. VERIFICATION TEST HARNESS (Run to test your code!)
    // =========================================================================

    public static void main(String[] args) {
        System.out.println("=== Testing: Multi-Car Elevator Control System ===");

        DesignElevatorSystem system = new DesignElevatorSystem(10, 2);

        // Test 1: External Hall Call on Floor 5 going UP
        system.requestElevator(5, Direction.UP);
        int steps = 0;
        while (system.getCars().get(0).hasPendingRequests() && steps++ < 20) {
            system.stepAll();
        }

        if (system.getCars().get(0).getCurrentFloor() == 5) {
            System.out.println("  [PASS] Test 1: Elevator served hall call at Floor 5.");
        } else {
            System.out.println("  [TODO] Test 1: requestElevator() / step() not implemented yet.");
        }

        // Test 2: Passenger selects Floor 8
        system.selectFloor(1, 8);
        steps = 0;
        while (system.getCars().get(0).hasPendingRequests() && steps++ < 20) {
            system.stepAll();
        }

        if (system.getCars().get(0).getCurrentFloor() == 8) {
            System.out.println("  [PASS] Test 2: Elevator served destination call to Floor 8.");
        } else {
            System.out.println("  [TODO] Test 2: selectFloor() / LOOK scheduling not implemented yet.");
        }
    }
}
