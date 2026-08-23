# Math & Number Theory Detailed Guide

Mathematical and Number Theory concepts are often the hidden key to optimizing a brute-force `O(N)` solution into an `O(1)` or `O(log N)` solution. Here is a detailed breakdown of the most critical math concepts for coding interviews, complete with examples.

---

## 1. Greatest Common Divisor (GCD) & Lowest Common Multiple (LCM)

### The Concept
The GCD of two numbers `a` and `b` is the largest positive integer that divides both numbers without a remainder.
The **Euclidean Algorithm** is the most efficient way to compute the GCD. It relies on the principle that `GCD(a, b) = GCD(b, a % b)`.

### Code Template
```java
/**
 * Calculates the Greatest Common Divisor using the Euclidean Algorithm.
 * Time Complexity: O(log(min(a, b)))
 * Space Complexity: O(log(min(a, b))) for the recursion stack
 */
public int gcd(int a, int b) {
    if (b == 0) return a;
    return gcd(b, a % b);
}
```

### Example Walkthrough
Let's find `gcd(48, 18)`:
1. `gcd(48, 18)` -> `18` is not 0, so return `gcd(18, 48 % 18)` which is `gcd(18, 12)`
2. `gcd(18, 12)` -> `12` is not 0, so return `gcd(12, 18 % 12)` which is `gcd(12, 6)`
3. `gcd(12, 6)` -> `6` is not 0, so return `gcd(6, 12 % 6)` which is `gcd(6, 0)`
4. `gcd(6, 0)` -> `b` is 0, so return `a`, which is `6`.
Result: 6.

### Lowest Common Multiple (LCM)
The LCM is the smallest positive integer that is perfectly divisible by both `a` and `b`.
**Formula**: `LCM(a, b) = (a * b) / GCD(a, b)`

**Important Note on Overflow**: Always divide before multiplying to prevent integer overflow!
```java
public int lcm(int a, int b) {
    return (a / gcd(a, b)) * b; 
}
```

---

## 2. Fast Exponentiation (Modular Exponentiation)

### The Concept
If a problem asks you to calculate `base^exp`, a simple `for` loop takes `O(exp)` time. If `exp` is `10^9`, your program will get a Time Limit Exceeded (TLE) error.
**Fast Exponentiation** uses binary representation of the exponent to compute the result in `O(log exp)` time. Because the result can be massive, problems usually ask you to return the result modulo a number (typically `10^9 + 7`).

### Code Template
```java
/**
 * Computes (base^exp) % mod efficiently.
 * Time Complexity: O(log(exp))
 */
public long power(long base, long exp, long mod) {
    long res = 1;
    base = base % mod; // Initial modulo just in case base > mod
    
    while (exp > 0) {
        // If the current lowest bit of the exponent is 1, multiply the result
        if (exp % 2 == 1) {
            res = (res * base) % mod;
        }
        // Square the base for the next bit
        base = (base * base) % mod;
        
        // Shift the exponent right by 1 bit (equivalent to exp / 2)
        exp /= 2;
    }
    return res;
}
```

### Example Walkthrough
Calculate `3^5`:
- Binary of `5` is `101`. We can think of `3^5` as `3^4 * 3^1`.
- Step 1: `exp = 5` (odd), `res = 1 * 3 = 3`. `base = 3 * 3 = 9`. `exp = 2`.
- Step 2: `exp = 2` (even). `res` stays `3`. `base = 9 * 9 = 81`. `exp = 1`.
- Step 3: `exp = 1` (odd). `res = 3 * 81 = 243`. `base = 81 * 81 = 6561`. `exp = 0`.
- Loop ends. Result is 243.

---

## 3. Sieve of Eratosthenes

### The Concept
If a problem asks you to find all prime numbers up to `N` (e.g., `N = 10^6`), checking each number individually takes `O(N * sqrt(N))` which is too slow. 
The Sieve algorithm creates a boolean array and iteratively marks the multiples of every prime number as "not prime" (false).

### Code Template
```java
/**
 * Generates an array where isPrime[i] is true if i is a prime number.
 * Time Complexity: O(N log(log N))
 * Space Complexity: O(N)
 */
public boolean[] sieve(int n) {
    boolean[] isPrime = new boolean[n + 1];
    Arrays.fill(isPrime, true); // Assume all are prime initially
    
    // 0 and 1 are not prime numbers
    isPrime[0] = false;
    isPrime[1] = false;
    
    // We only need to check up to the square root of n
    for (int p = 2; p * p <= n; p++) {
        // If p is still marked as prime, it is a prime!
        if (isPrime[p]) {
            // Mark all multiples of p as not prime.
            // We start at p*p because smaller multiples would have been marked by smaller primes.
            for (int i = p * p; i <= n; i += p) {
                isPrime[i] = false;
            }
        }
    }
    return isPrime;
}
```

### Example Usage
```java
boolean[] primes = sieve(20);
System.out.println(primes[7]); // Output: true
System.out.println(primes[9]); // Output: false
```

---

## 4. Modulo Arithmetic Rules

### The Concept
When dealing with large numbers (like permutations or combinations), problems often state: *"Return the answer modulo 10^9 + 7"*. `10^9 + 7` (or `1000000007`) is a large prime number that fits in a 32-bit integer.

If you don't apply modulo at **every single step** of your calculation, your variables will overflow their `int` or `long` limits, resulting in garbage negative numbers.

### The Rules
Let `MOD = 1000000007`.
1. **Addition**: `(A + B) % MOD = ((A % MOD) + (B % MOD)) % MOD`
2. **Multiplication**: `(A * B) % MOD = ((A % MOD) * (B % MOD)) % MOD`
3. **Subtraction**: `(A - B) % MOD = ((A % MOD) - (B % MOD) + MOD) % MOD`
   * **WARNING**: In Java, `%` is the remainder operator, not a true modulo. If `A < B`, `(A - B) % MOD` will be negative. You **must** add `MOD` before taking the final modulo to ensure the result is positive.

### Example Walkthrough (Adding large numbers)
```java
int MOD = 1000000007;
long a = 1000000000; // 1 billion
long b = 1000000000; // 1 billion

// WRONG: This might overflow before the modulo is applied depending on variable types!
long wrongAns = (a + b) % MOD; 

// CORRECT: Apply modulo safely
long correctAns = ((a % MOD) + (b % MOD)) % MOD;
```
