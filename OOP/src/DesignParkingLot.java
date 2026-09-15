import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ============================================================================
 * Practice Template: Multi-Floor Parking Lot System (LLD)
 * Difficulty: Medium | Target Companies: Amazon, Google, Uber, Microsoft
 * ============================================================================
 *
 * 📖 PROBLEM DESCRIPTION:
 * Design an automated multi-floor, multi-gate parking lot management system.
 * The system must allocate optimal spots based on vehicle type, issue unique
 * tickets upon entry, calculate parking fees based on duration and vehicle rates
 * upon exit, and track real-time spot availability.
 *
 * 📥 REQUIREMENTS:
 * 1. Vehicle Types: MOTORCYCLE, CAR, TRUCK, ELECTRIC.
 * 2. Spot Types: MOTORCYCLE, COMPACT, LARGE, ELECTRIC.
 *    - Motorcycle can fit in MOTORCYCLE, COMPACT, or LARGE spot.
 *    - Car can fit in COMPACT or LARGE spot.
 *    - Truck can only fit in LARGE spot.
 *    - Electric Vehicle can fit in ELECTRIC, COMPACT, or LARGE spot.
 * 3. Strategy: Allocate the lowest floor first and nearest available spot.
 * 4. Fee Calculation: Hourly rate based on vehicle type (e.g. Motorcycle: $10, Car: $20, EV: $25, Truck: $40).
 * 5. Concurrency: Prevent race conditions when multiple vehicles park simultaneously.
 *
 * 💡 INTERVIEW HINTS:
 * - Use the Strategy pattern for spot search and fee calculations.
 * - Guard `park()` and `unpark()` on `ParkingSpot` with `synchronized` or locks.
 * - Return `Collections.unmodifiableList()` to prevent leaking internal mutable state.
 */
public class DesignParkingLot {

    // =========================================================================
    // 1. ENUMS & VALUE OBJECTS
    // =========================================================================

    public enum VehicleType {
        MOTORCYCLE, CAR, TRUCK, ELECTRIC
    }

    public enum SpotType {
        MOTORCYCLE, COMPACT, LARGE, ELECTRIC
    }

    // =========================================================================
    // 2. DOMAIN MODELS: VEHICLE HIERARCHY
    // =========================================================================

    public static abstract class Vehicle {
        private final String licensePlate;
        private final VehicleType type;

        public Vehicle(String licensePlate, VehicleType type) {
            this.licensePlate = Objects.requireNonNull(licensePlate, "License plate cannot be null");
            this.type = Objects.requireNonNull(type, "Vehicle type cannot be null");
        }

        public String getLicensePlate() { return licensePlate; }
        public VehicleType getType() { return type; }

        /**
         * Returns true if this vehicle can fit into the specified spot type.
         */
        public abstract boolean canFitInSpot(SpotType spotType);
    }

    public static class Motorcycle extends Vehicle {
        public Motorcycle(String licensePlate) { super(licensePlate, VehicleType.MOTORCYCLE); }

        @Override
        public boolean canFitInSpot(SpotType spotType) {
            // TODO: Return true if motorcycle can fit in this spot type
            return false;
        }
    }

    public static class Car extends Vehicle {
        public Car(String licensePlate) { super(licensePlate, VehicleType.CAR); }

        @Override
        public boolean canFitInSpot(SpotType spotType) {
            // TODO: Return true if car can fit in COMPACT or LARGE spot
            return false;
        }
    }

    public static class Truck extends Vehicle {
        public Truck(String licensePlate) { super(licensePlate, VehicleType.TRUCK); }

        @Override
        public boolean canFitInSpot(SpotType spotType) {
            // TODO: Return true if truck can fit in LARGE spot
            return false;
        }
    }

    public static class ElectricCar extends Vehicle {
        public ElectricCar(String licensePlate) { super(licensePlate, VehicleType.ELECTRIC); }

        @Override
        public boolean canFitInSpot(SpotType spotType) {
            // TODO: Return true if electric car can fit in ELECTRIC, COMPACT, or LARGE spot
            return false;
        }
    }

    // =========================================================================
    // 3. PARKING SPOT & FLOOR
    // =========================================================================

    public static class ParkingSpot {
        private final String spotId;
        private final int floorNumber;
        private final SpotType spotType;
        private volatile boolean isOccupied;
        private Vehicle currentVehicle;

        public ParkingSpot(String spotId, int floorNumber, SpotType spotType) {
            this.spotId = spotId;
            this.floorNumber = floorNumber;
            this.spotType = spotType;
            this.isOccupied = false;
        }

        /**
         * Parks a vehicle in this spot if it is unoccupied and compatible.
         *
         * @return true if vehicle was successfully parked; false otherwise
         */
        public synchronized boolean park(Vehicle vehicle) {
            // TODO: Implement thread-safe parking logic:
            // 1. Check if already occupied or vehicle cannot fit in spotType
            // 2. If valid, set currentVehicle, mark isOccupied = true, return true
            return false;
        }

        /**
         * Unparks the vehicle from this spot.
         *
         * @return the removed Vehicle, or null if spot was already empty
         */
        public synchronized Vehicle unpark() {
            // TODO: Implement thread-safe unparking logic:
            // 1. If not occupied, return null
            // 2. Clear currentVehicle, mark isOccupied = false, return the unparked vehicle
            return null;
        }

        public String getSpotId() { return spotId; }
        public int getFloorNumber() { return floorNumber; }
        public SpotType getSpotType() { return spotType; }
        public boolean isOccupied() { return isOccupied; }
        public Vehicle getCurrentVehicle() { return currentVehicle; }
    }

    public static class ParkingFloor {
        private final int floorNumber;
        private final List<ParkingSpot> spots = new ArrayList<>();

        public ParkingFloor(int floorNumber) {
            this.floorNumber = floorNumber;
        }

        public void addSpot(ParkingSpot spot) {
            spots.add(spot);
        }

        public List<ParkingSpot> getSpots() {
            return Collections.unmodifiableList(spots);
        }

        public int getFloorNumber() { return floorNumber; }

        /**
         * Returns count of available (unoccupied) spots of the given type on this floor.
         */
        public int getAvailableSpotsCount(SpotType type) {
            // TODO: Count and return unoccupied spots matching type
            return 0;
        }
    }

    // =========================================================================
    // 4. TICKETING & RECEIPTS
    // =========================================================================

    public static class ParkingTicket {
        private static final AtomicInteger ID_GEN = new AtomicInteger(1000);

        private final String ticketId;
        private final String licensePlate;
        private final VehicleType vehicleType;
        private final ParkingSpot spot;
        private final LocalDateTime entryTime;

        public ParkingTicket(Vehicle vehicle, ParkingSpot spot, LocalDateTime entryTime) {
            this.ticketId = "TKT-" + ID_GEN.incrementAndGet();
            this.licensePlate = vehicle.getLicensePlate();
            this.vehicleType = vehicle.getType();
            this.spot = spot;
            this.entryTime = entryTime;
        }

        public String getTicketId() { return ticketId; }
        public String getLicensePlate() { return licensePlate; }
        public VehicleType getVehicleType() { return vehicleType; }
        public ParkingSpot getSpot() { return spot; }
        public LocalDateTime getEntryTime() { return entryTime; }
    }

    public static class ParkingReceipt {
        private final String ticketId;
        private final String licensePlate;
        private final LocalDateTime entryTime;
        private final LocalDateTime exitTime;
        private final double totalFee;

        public ParkingReceipt(ParkingTicket ticket, LocalDateTime exitTime, double totalFee) {
            this.ticketId = ticket.getTicketId();
            this.licensePlate = ticket.getLicensePlate();
            this.entryTime = ticket.getEntryTime();
            this.exitTime = exitTime;
            this.totalFee = totalFee;
        }

        public double getTotalFee() { return totalFee; }
        public String getTicketId() { return ticketId; }
    }

    // =========================================================================
    // 5. STRATEGY PATTERNS: ALLOCATION & PRICING
    // =========================================================================

    public interface ParkingStrategy {
        ParkingSpot findSpot(List<ParkingFloor> floors, Vehicle vehicle);
    }

    public static class LowestFloorFirstStrategy implements ParkingStrategy {
        @Override
        public ParkingSpot findSpot(List<ParkingFloor> floors, Vehicle vehicle) {
            // TODO: Search floors in ascending order; return first unoccupied spot that vehicle canFitInSpot()
            return null;
        }
    }

    public interface PricingStrategy {
        double calculateFee(ParkingTicket ticket, LocalDateTime exitTime);
    }

    public static class HourlyPricingStrategy implements PricingStrategy {
        private final Map<VehicleType, Double> hourlyRates = new HashMap<>();

        public HourlyPricingStrategy() {
            hourlyRates.put(VehicleType.MOTORCYCLE, 10.0);
            hourlyRates.put(VehicleType.CAR, 20.0);
            hourlyRates.put(VehicleType.ELECTRIC, 25.0);
            hourlyRates.put(VehicleType.TRUCK, 40.0);
        }

        @Override
        public double calculateFee(ParkingTicket ticket, LocalDateTime exitTime) {
            // TODO: Calculate fee based on duration hours * hourlyRates for vehicle type
            // Tip: Use Duration.between(ticket.getEntryTime(), exitTime).toMinutes() / 60.0 ceil
            return 0.0;
        }
    }

    // =========================================================================
    // 6. MAIN CONTROLLER: PARKING LOT
    // =========================================================================

    private final String name;
    private final List<ParkingFloor> floors;
    private final Map<String, ParkingTicket> activeTickets;
    private ParkingStrategy parkingStrategy;
    private PricingStrategy pricingStrategy;

    public DesignParkingLot(String name) {
        this.name = name;
        this.floors = new ArrayList<>();
        this.activeTickets = new ConcurrentHashMap<>();
        this.parkingStrategy = new LowestFloorFirstStrategy();
        this.pricingStrategy = new HourlyPricingStrategy();
    }

    public void addFloor(ParkingFloor floor) {
        floors.add(floor);
    }

    public void setParkingStrategy(ParkingStrategy strategy) {
        this.parkingStrategy = strategy;
    }

    public void setPricingStrategy(PricingStrategy strategy) {
        this.pricingStrategy = strategy;
    }

    /**
     * Entry Gate: Finds a spot using parkingStrategy, parks vehicle, and issues a ticket.
     */
    public synchronized ParkingTicket parkVehicle(Vehicle vehicle) {
        // TODO: Implement entry gate workflow:
        // 1. Find spot using parkingStrategy.findSpot(floors, vehicle)
        // 2. If null, print full message and return null
        // 3. Attempt spot.park(vehicle). If false, return null
        // 4. Create new ParkingTicket, put into activeTickets map, and return ticket
        return null;
    }

    /**
     * Exit Gate: Unparks vehicle, calculates fee using pricingStrategy, and produces receipt.
     */
    public synchronized ParkingReceipt unparkVehicle(String ticketId) {
        // TODO: Implement exit gate workflow:
        // 1. Retrieve ticket from activeTickets
        // 2. Unpark from spot
        // 3. Calculate fee using pricingStrategy
        // 4. Create and return ParkingReceipt
        return null;
    }

    // =========================================================================
    // 7. VERIFICATION TEST HARNESS (Run to test your code!)
    // =========================================================================

    public static void main(String[] args) {
        System.out.println("=== Testing: Multi-Floor Parking Lot System ===");

        DesignParkingLot lot = new DesignParkingLot("Grand Central Garage");

        // Floor 1: 1 Motorcycle, 1 Compact, 1 Large
        ParkingFloor f1 = new ParkingFloor(1);
        f1.addSpot(new ParkingSpot("F1-M1", 1, SpotType.MOTORCYCLE));
        f1.addSpot(new ParkingSpot("F1-C1", 1, SpotType.COMPACT));
        f1.addSpot(new ParkingSpot("F1-L1", 1, SpotType.LARGE));
        lot.addFloor(f1);

        // Floor 2: 1 Electric, 1 Large
        ParkingFloor f2 = new ParkingFloor(2);
        f2.addSpot(new ParkingSpot("F2-E1", 2, SpotType.ELECTRIC));
        f2.addSpot(new ParkingSpot("F2-L1", 2, SpotType.LARGE));
        lot.addFloor(f2);

        // Test 1: Park a Car
        Vehicle car1 = new Car("KA-01-AB-1234");
        ParkingTicket t1 = lot.parkVehicle(car1);
        if (t1 != null && "F1-C1".equals(t1.getSpot().getSpotId())) {
            System.out.println("  [PASS] Test 1: Car parked in Floor 1 Compact spot.");
        } else {
            System.out.println("  [TODO] Test 1: parkVehicle() for Car not implemented yet.");
        }

        // Test 2: Park a Truck
        Vehicle truck1 = new Truck("MH-12-TR-9999");
        ParkingTicket t2 = lot.parkVehicle(truck1);
        if (t2 != null && "F1-L1".equals(t2.getSpot().getSpotId())) {
            System.out.println("  [PASS] Test 2: Truck parked in Floor 1 Large spot.");
        } else {
            System.out.println("  [TODO] Test 2: parkVehicle() for Truck not implemented yet.");
        }

        // Test 3: Park an Electric Car
        Vehicle ev1 = new ElectricCar("DL-04-EV-0001");
        ParkingTicket t3 = lot.parkVehicle(ev1);
        if (t3 != null && "F2-E1".equals(t3.getSpot().getSpotId())) {
            System.out.println("  [PASS] Test 3: Electric Car parked in Floor 2 EV spot.");
        } else {
            System.out.println("  [TODO] Test 3: parkVehicle() for EV not implemented yet.");
        }

        // Test 4: Exit and Fee Payment
        if (t1 != null) {
            ParkingReceipt r1 = lot.unparkVehicle(t1.getTicketId());
            if (r1 != null && r1.getTotalFee() > 0) {
                System.out.println("  [PASS] Test 4: Car unparked and fee computed: $" + r1.getTotalFee());
            } else {
                System.out.println("  [TODO] Test 4: unparkVehicle() fee computation not implemented yet.");
            }
        } else {
            System.out.println("  [TODO] Test 4: Skipped (depends on Test 1).");
        }
    }
}
