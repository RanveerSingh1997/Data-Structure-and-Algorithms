# Bit Manipulation Detailed Guide

Bitwise operations process numbers at the binary level. They are incredibly fast and memory-efficient. Knowing these common tricks can turn a slow, complex algorithm into a single line of elegant code.

---

## The Core Operators
- `&` **(AND)**: `1` only if *both* bits are `1`.
- `|` **(OR)**: `1` if *either* bit is `1`.
- `^` **(XOR)**: `1` if bits are *different*, `0` if they are the *same*.
- `~` **(NOT)**: Inverts all bits (turns `0` to `1`, and `1` to `0`).
- `<<` **(Left Shift)**: Shifts bits to the left, adding `0`s on the right. Equivalent to multiplying by `2^k`.
- `>>` **(Right Shift)**: Shifts bits to the right. Equivalent to dividing by `2^k`. Preserves the sign bit.

---

## Crucial Tricks & Walkthroughs

### 1. Check if the `i`-th bit is set (is it 1?)
We use a **Bit Mask**. If we want to check the 3rd bit, we shift `1` to the left 3 times (creating `00001000`). If we AND (`&`) this mask with our number, the result will only be non-zero if the 3rd bit was a `1`.

```java
// Check if the 3rd bit of 'num' is 1
int num = 10; // Binary: 1010
int i = 3;
boolean isSet = (num & (1 << i)) != 0; 

// Example: 1010 & (1 << 3)
// 1010 & 1000 = 1000 (!= 0, so it is true)
```

### 2. Set the `i`-th bit (turn it to 1)
We use the OR (`|`) operator. ORing any bit with `1` results in `1`. ORing with `0` leaves it unchanged.
```java
// Turn on the 2nd bit
int num = 10; // Binary: 1010
int i = 2;
num = num | (1 << i);

// Example: 1010 | (1 << 2)
// 1010 | 0100 = 1110 (which is 14)
```

### 3. Clear the `i`-th bit (turn it to 0)
We want to AND our number with a mask where *every* bit is `1` except the `i`-th bit. We create this mask by taking `(1 << i)` and applying the NOT (`~`) operator.
```java
// Turn off the 3rd bit
int num = 10; // Binary: 1010
int i = 3;
num = num & ~(1 << i);

// Example: 1010 & ~(1000)
// 1010 & 0111 = 0010 (which is 2)
```

### 4. Toggle the `i`-th bit (flip 0 to 1, or 1 to 0)
We use XOR (`^`). XORing a bit with `1` flips it. XORing with `0` leaves it unchanged.
```java
int num = 10; // Binary: 1010
int i = 1;
num = num ^ (1 << i);

// Example: 1010 ^ (1 << 1)
// 1010 ^ 0010 = 1000 (which is 8)
```

---

## Advanced Tricks

### 5. Clear the lowest set bit (Brian Kernighan’s Algorithm)
This is arguably the most asked bit manipulation trick. It removes the rightmost `1` from the binary representation of a number.
If you subtract `1` from a number, all bits after the rightmost `1` get flipped, and the rightmost `1` becomes a `0`.

```java
int num = 10; // Binary: 1010
num = num & (num - 1); 

// Example: 1010 & 1001 = 1000 (The rightmost 1 was cleared!)
```
**Common Use Case**: Counting how many `1`s are in a number (Hamming Weight). You can just `while (num > 0) { num = num & (num - 1); count++; }`.

### 6. Extract the lowest set bit
Isolates the lowest set bit and sets all other bits to `0`. Due to Two's Complement representation of negative numbers, `-num` is equivalent to `(~num + 1)`.

```java
int num = 10; // Binary: 1010
int lowestBit = num & (-num);

// Example: 1010 & 0110 = 0010 (Isolates the 2nd bit)
```

### 7. Check if a number is a power of 2
A power of 2 (like 2, 4, 8, 16) has exactly **one** bit set to `1` in binary (e.g., 4 is `0100`, 8 is `1000`). If we clear the lowest set bit, the number should become exactly `0`.

```java
// Ensure num > 0 because 0 is not a power of 2
boolean isPowerOfTwo = (num > 0) && (num & (num - 1)) == 0;
```

### 8. XOR Properties for "Single Number" problems
- `A ^ A = 0` (XORing a number with itself gives 0)
- `A ^ 0 = A` (XORing a number with 0 gives the number itself)
- `A ^ B ^ A = B` (Order doesn't matter. The `A`s cancel out).

**Example Problem**: You have an array where every number appears twice, except one. Find that single number.
**Solution**: Just XOR all numbers together. The duplicates will cancel each other out (become 0), leaving only the single number!
```java
int[] nums = {4, 1, 2, 1, 2};
int result = 0;
for (int num : nums) {
    result ^= num;
}
// result will be 4
```
