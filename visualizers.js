/**
 * ============================================================================
 * Algorithmic Simulation Engines (Step Generators)
 * ============================================================================
 * Produces discrete state frames with visual snapshots, variables, rationales,
 * and synchronized Java code highlights.
 */

const Simulations = {
  // 1. Two Pointers Simulation (Container With Most Water)
  twoPointers: {
    defaultInput: [1, 8, 6, 2, 5, 4, 8, 3, 7],
    parseInput: (str) => {
      try {
        const cleaned = str.replace(/[\[\]]/g, "").trim();
        if (!cleaned) return [1, 8, 6, 2, 5, 4, 8, 3, 7];
        return cleaned.split(",").map(x => parseInt(x.trim(), 10)).filter(x => !isNaN(x) && x >= 0);
      } catch (e) {
        return [1, 8, 6, 2, 5, 4, 8, 3, 7];
      }
    },
    generateSteps: (heights) => {
      const steps = [];
      let left = 0, right = heights.length - 1;
      let maxArea = 0;
      let bestL = 0, bestR = heights.length - 1;

      // Initial state
      steps.push({
        type: "init",
        left,
        right,
        width: right - left,
        h: Math.min(heights[left], heights[right]),
        currArea: 0,
        maxArea: 0,
        bestL: 0,
        bestR: 0,
        eliminatedPointer: null,
        activeLine: 2,
        title: "Initialize Pointers at Opposite Boundaries",
        explanation: `Start with the widest container possible: Left pointer at index ${left} (height ${heights[left]}) and Right pointer at index ${right} (height ${heights[right]}). Width is currently ${right - left}.`,
        heights: [...heights]
      });

      while (left < right) {
        const width = right - left;
        const h = Math.min(heights[left], heights[right]);
        const currArea = width * h;
        const isNewMax = currArea > maxArea;
        if (isNewMax) {
          maxArea = currArea;
          bestL = left;
          bestR = right;
        }

        // Evaluation step
        steps.push({
          type: "evaluate",
          left,
          right,
          width,
          h,
          currArea,
          maxArea,
          bestL,
          bestR,
          isNewMax,
          eliminatedPointer: null,
          activeLine: 6,
          title: `Evaluate Container between index ${left} and ${right}`,
          explanation: `Width = ${right} - ${left} = ${width}. Limiting height = min(${heights[left]}, ${heights[right]}) = ${h}. Current Area = ${width} × ${h} = ${currArea}.${isNewMax ? " 🌟 New maximum area found!" : " (Less than current best " + maxArea + ")"}`
        });

        // Decision step: move pointer
        if (heights[left] < heights[right]) {
          steps.push({
            type: "advance",
            left,
            right,
            width,
            h,
            currArea,
            maxArea,
            bestL,
            bestR,
            eliminatedPointer: "left",
            activeLine: 8,
            title: `Advance Left Pointer (index ${left} -> ${left + 1})`,
            explanation: `Since height[${left}] (${heights[left]}) < height[${right}] (${heights[right]}), height[${left}] is the bottleneck. Moving Right inward cannot help Left achieve a larger area (width shrinks and height is capped by ${heights[left]}). We safely eliminate Left.`
          });
          left++;
        } else {
          steps.push({
            type: "advance",
            left,
            right,
            width,
            h,
            currArea,
            maxArea,
            bestL,
            bestR,
            eliminatedPointer: "right",
            activeLine: 10,
            title: `Advance Right Pointer (index ${right} -> ${right - 1})`,
            explanation: `Since height[${right}] (${heights[right]}) <= height[${left}] (${heights[left]}), height[${right}] is the bottleneck. Moving Left inward cannot help Right achieve a larger area. We safely eliminate Right.`
          });
          right--;
        }
      }

      // Final step
      steps.push({
        type: "done",
        left,
        right,
        width: 0,
        h: 0,
        currArea: 0,
        maxArea,
        bestL,
        bestR,
        eliminatedPointer: null,
        activeLine: 13,
        title: "Search Space Exhausted: Optimum Found!",
        explanation: `Pointers met at index ${left}. Maximum trapped area is ${maxArea} (formed between index ${bestL} and index ${bestR}). Invariant preserved in O(N) time and O(1) space.`
      });

      return steps;
    }
  },

  // 2. Sliding Window Simulation (Longest Substring Without Repeating Characters)
  slidingWindow: {
    defaultInput: "abcabcbb",
    parseInput: (str) => {
      const clean = str.replace(/["']/g, "").trim();
      return clean.length > 0 ? clean : "abcabcbb";
    },
    generateSteps: (s) => {
      const steps = [];
      let left = 0, maxLen = 0;
      let lastSeen = {};
      let bestSub = "";

      steps.push({
        type: "init",
        s,
        left: 0,
        right: -1,
        maxLen: 0,
        bestSub: "",
        currSub: "",
        lastSeen: {},
        activeLine: 2,
        title: "Initialize Empty Sliding Window",
        explanation: "Initialize Left = 0, Right boundary scanning from 0, and an empty hash map to store the most recent index of each character."
      });

      for (let right = 0; right < s.length; right++) {
        const c = s.charAt(right);
        const prevIdx = lastSeen[c];
        const isDuplicate = prevIdx !== undefined && prevIdx >= left;

        // Absorb right character
        steps.push({
          type: "expand",
          s,
          left,
          right,
          char: c,
          prevIdx,
          isDuplicate,
          maxLen,
          bestSub,
          currSub: s.substring(left, right + 1),
          lastSeen: { ...lastSeen },
          activeLine: 5,
          title: `Expand Window Right to include '${c}' at index ${right}`,
          explanation: `Examining character '${c}' at index ${right}. Previous occurrence: ${prevIdx !== undefined ? "index " + prevIdx : "Never seen yet"}.`
        });

        if (isDuplicate) {
          const oldLeft = left;
          left = prevIdx + 1;
          steps.push({
            type: "shrink",
            s,
            left,
            right,
            char: c,
            prevIdx,
            isDuplicate: true,
            maxLen,
            bestSub,
            currSub: s.substring(left, right + 1),
            lastSeen: { ...lastSeen },
            activeLine: 7,
            title: `Duplicate '${c}' detected! Contract Left boundary`,
            explanation: `'${c}' was previously seen inside the active window at index ${prevIdx}. Jump Left boundary from ${oldLeft} to ${left} (index ${prevIdx} + 1) to restore unique substring validity.`
          });
        }

        lastSeen[c] = right;
        const currentLen = right - left + 1;
        const isNewMax = currentLen > maxLen;
        if (isNewMax) {
          maxLen = currentLen;
          bestSub = s.substring(left, right + 1);
        }

        steps.push({
          type: "update",
          s,
          left,
          right,
          char: c,
          maxLen,
          bestSub,
          currSub: s.substring(left, right + 1),
          lastSeen: { ...lastSeen },
          activeLine: 10,
          title: `Update Window State: "${s.substring(left, right + 1)}"`,
          explanation: `Current valid window: "${s.substring(left, right + 1)}" with length ${currentLen}.${isNewMax ? " 🌟 New maximum length recorded!" : " (Max so far: " + maxLen + ")"}`
        });
      }

      steps.push({
        type: "done",
        s,
        left,
        right: s.length - 1,
        maxLen,
        bestSub,
        currSub: bestSub,
        lastSeen: { ...lastSeen },
        activeLine: 12,
        title: "Scanning Completed!",
        explanation: `Processed entire string. The longest substring without repeating characters has length ${maxLen} ("${bestSub}"). Completed in O(N) time with O(min(N, M)) auxiliary space.`
      });

      return steps;
    }
  },

  // 3. Monotonic Stack Simulation (Daily Temperatures)
  monotonicStack: {
    defaultInput: [73, 74, 75, 71, 69, 72, 76, 73],
    parseInput: (str) => {
      try {
        const cleaned = str.replace(/[\[\]]/g, "").trim();
        if (!cleaned) return [73, 74, 75, 71, 69, 72, 76, 73];
        return cleaned.split(",").map(x => parseInt(x.trim(), 10)).filter(x => !isNaN(x));
      } catch (e) {
        return [73, 74, 75, 71, 69, 72, 76, 73];
      }
    },
    generateSteps: (temperatures) => {
      const steps = [];
      const n = temperatures.length;
      const answer = new Array(n).fill(0);
      const stack = []; // indices

      steps.push({
        type: "init",
        temperatures: [...temperatures],
        currentIndex: -1,
        stack: [],
        answer: [...answer],
        activeLine: 4,
        title: "Initialize Monotonic Decreasing Stack",
        explanation: "Initialize an empty stack to hold day indices and an answer array initialized to all 0s. The stack will maintain temperatures in strictly decreasing order."
      });

      for (let i = 0; i < n; i++) {
        const currTemp = temperatures[i];

        steps.push({
          type: "examine",
          temperatures: [...temperatures],
          currentIndex: i,
          stack: [...stack],
          answer: [...answer],
          activeLine: 6,
          title: `Day ${i}: Temperature is ${currTemp}°`,
          explanation: `Inspect day ${i} (temp = ${currTemp}°). Check if this temperature is warmer than the temperature recorded at the top of the stack.`
        });

        while (stack.length > 0 && currTemp > temperatures[stack[stack.length - 1]]) {
          const prevIdx = stack.pop();
          const waitDays = i - prevIdx;
          answer[prevIdx] = waitDays;

          steps.push({
            type: "pop",
            temperatures: [...temperatures],
            currentIndex: i,
            poppedIdx: prevIdx,
            poppedTemp: temperatures[prevIdx],
            waitDays,
            stack: [...stack],
            answer: [...answer],
            activeLine: 8,
            title: `Warmer Day Found! Pop index ${prevIdx} (${temperatures[prevIdx]}°)`,
            explanation: `Day ${i} (${currTemp}°) > Day ${prevIdx} (${temperatures[prevIdx]}°). The next warmer day for Day ${prevIdx} is Day ${i}! Distance = ${i} - ${prevIdx} = ${waitDays} day(s). Record in answer[${prevIdx}].`
          });
        }

        stack.push(i);
        steps.push({
          type: "push",
          temperatures: [...temperatures],
          currentIndex: i,
          stack: [...stack],
          answer: [...answer],
          activeLine: 10,
          title: `Push Day ${i} (${currTemp}°) onto Stack`,
          explanation: `Stack condition restored (all remaining elements in stack are > ${currTemp}°). Push index ${i} onto stack.`
        });
      }

      steps.push({
        type: "done",
        temperatures: [...temperatures],
        currentIndex: n,
        stack: [...stack],
        answer: [...answer],
        activeLine: 12,
        title: "All Days Processed!",
        explanation: `Any indices remaining on the stack (${stack.join(", ")}) have no subsequent warmer day, staying 0. Final result computed in O(N) time with O(N) space.`
      });

      return steps;
    }
  },

  // 4. Binary Search Simulation (Search in Rotated Sorted Array)
  binarySearch: {
    defaultInput: { nums: [4, 5, 6, 7, 0, 1, 2], target: 0 },
    parseInput: (str) => {
      try {
        const parts = str.split(";");
        const arrStr = parts[0].replace(/[\[\]]/g, "").trim();
        const nums = arrStr.split(",").map(x => parseInt(x.trim(), 10)).filter(x => !isNaN(x));
        const target = parts.length > 1 ? parseInt(parts[1].trim(), 10) : 0;
        return { nums: nums.length > 0 ? nums : [4, 5, 6, 7, 0, 1, 2], target: isNaN(target) ? 0 : target };
      } catch (e) {
        return { nums: [4, 5, 6, 7, 0, 1, 2], target: 0 };
      }
    },
    generateSteps: (data) => {
      const { nums, target } = data;
      const steps = [];
      let low = 0, high = nums.length - 1;
      let foundIndex = -1;

      steps.push({
        type: "init",
        nums: [...nums],
        target,
        low,
        high,
        mid: -1,
        activeLine: 2,
        title: `Search Target ${target} in Rotated Array`,
        explanation: `Initialize low = 0 (value ${nums[0]}) and high = ${high} (value ${nums[high]}). Target to find = ${target}.`
      });

      while (low <= high) {
        const mid = low + Math.floor((high - low) / 2);

        steps.push({
          type: "mid",
          nums: [...nums],
          target,
          low,
          high,
          mid,
          activeLine: 4,
          title: `Calculate Midpoint: Index ${mid} (value ${nums[mid]})`,
          explanation: `Mid = ${low} + (${high} - ${low}) / 2 = ${mid}. Checking value nums[${mid}] = ${nums[mid]}.`
        });

        if (nums[mid] === target) {
          foundIndex = mid;
          steps.push({
            type: "found",
            nums: [...nums],
            target,
            low,
            high,
            mid,
            activeLine: 5,
            title: `🎯 Target ${target} Found at Index ${mid}!`,
            explanation: `nums[${mid}] == ${target}. Return index ${mid} immediately in O(log N) operations!`
          });
          break;
        }

        // Left half is sorted
        if (nums[low] <= nums[mid]) {
          const inLeft = target >= nums[low] && target < nums[mid];
          steps.push({
            type: "branch",
            nums: [...nums],
            target,
            low,
            high,
            mid,
            sortedHalf: "left",
            inSortedHalf: inLeft,
            activeLine: 7,
            title: `Left Half [${low}..${mid}] is Sorted (${nums[low]} <= ${nums[mid]})`,
            explanation: `Left segment [${nums[low]}..${nums[mid]}] is monotonic. Does target ${target} lie between [${nums[low]} and ${nums[mid]})? ${inLeft ? "YES -> Discard right half, set high = mid - 1." : "NO -> Target must be in right half, set low = mid + 1."}`
          });

          if (inLeft) {
            high = mid - 1;
          } else {
            low = mid + 1;
          }
        } else {
          // Right half is sorted
          const inRight = target > nums[mid] && target <= nums[high];
          steps.push({
            type: "branch",
            nums: [...nums],
            target,
            low,
            high,
            mid,
            sortedHalf: "right",
            inSortedHalf: inRight,
            activeLine: 13,
            title: `Right Half [${mid}..${high}] is Sorted (${nums[mid]} <= ${nums[high]})`,
            explanation: `Right segment [${nums[mid]}..${nums[high]}] is monotonic. Does target ${target} lie between (${nums[mid]} and ${nums[high]}]? ${inRight ? "YES -> Discard left half, set low = mid + 1." : "NO -> Target must be in left half, set high = mid - 1."}`
          });

          if (inRight) {
            low = mid + 1;
          } else {
            high = mid - 1;
          }
        }
      }

      if (foundIndex === -1) {
        steps.push({
          type: "not_found",
          nums: [...nums],
          target,
          low,
          high,
          mid: -1,
          activeLine: 20,
          title: `Target ${target} Not Found in Array`,
          explanation: `Low (${low}) exceeded High (${high}). Target does not exist in array. Return -1.`
        });
      }

      return steps;
    }
  },

  // 5. Binary Tree Inversion Simulation (Invert Binary Tree)
  treeTraversal: {
    defaultInput: "[4, 2, 7, 1, 3, 6, 9]",
    parseInput: (str) => {
      const clean = str.replace(/[\[\]]/g, "").trim();
      return clean.length > 0 ? "[" + clean + "]" : "[4, 2, 7, 1, 3, 6, 9]";
    },
    generateSteps: (treeStr) => {
      // Representation of tree levels:
      // Node 4: left 2, right 7
      // Node 2: left 1, right 3
      // Node 7: left 6, right 9
      const steps = [];
      let treeState = {
        val: 4,
        left: { val: 2, left: { val: 1, left: null, right: null }, right: { val: 3, left: null, right: null } },
        right: { val: 7, left: { val: 6, left: null, right: null }, right: { val: 9, left: null, right: null } }
      };

      steps.push({
        type: "init",
        tree: JSON.parse(JSON.stringify(treeState)),
        currentNode: 4,
        activeLine: 2,
        title: "Initialize Invert Tree Traversal at Root (4)",
        explanation: "Recursively visit each node in the tree and swap its left and right child pointers."
      });

      // Visit left child 2
      steps.push({
        type: "visit",
        tree: JSON.parse(JSON.stringify(treeState)),
        currentNode: 2,
        activeLine: 5,
        title: "Recursive Call: Subtree at Node 2",
        explanation: "Traverse into left child Node 2. Invert its leaves (1 and 3)."
      });

      // Swap leaves of 2 (1 and 3)
      treeState.left = {
        val: 2,
        left: { val: 3, left: null, right: null },
        right: { val: 1, left: null, right: null }
      };
      steps.push({
        type: "swap",
        tree: JSON.parse(JSON.stringify(treeState)),
        currentNode: 2,
        swappedLeft: 3,
        swappedRight: 1,
        activeLine: 6,
        title: "Swap Children of Node 2: [1 <-> 3]",
        explanation: "Swapped left child (1) with right child (3). Node 2's new left is 3, right is 1."
      });

      // Visit right child 7
      steps.push({
        type: "visit",
        tree: JSON.parse(JSON.stringify(treeState)),
        currentNode: 7,
        activeLine: 5,
        title: "Recursive Call: Subtree at Node 7",
        explanation: "Traverse into right child Node 7. Invert its leaves (6 and 9)."
      });

      // Swap leaves of 7 (6 and 9)
      treeState.right = {
        val: 7,
        left: { val: 9, left: null, right: null },
        right: { val: 6, left: null, right: null }
      };
      steps.push({
        type: "swap",
        tree: JSON.parse(JSON.stringify(treeState)),
        currentNode: 7,
        swappedLeft: 9,
        swappedRight: 6,
        activeLine: 6,
        title: "Swap Children of Node 7: [6 <-> 9]",
        explanation: "Swapped left child (6) with right child (9). Node 7's new left is 9, right is 6."
      });

      // Swap top level (2 and 7)
      const temp = treeState.left;
      treeState.left = treeState.right;
      treeState.right = temp;
      steps.push({
        type: "swap_root",
        tree: JSON.parse(JSON.stringify(treeState)),
        currentNode: 4,
        swappedLeft: 7,
        swappedRight: 2,
        activeLine: 6,
        title: "Swap Children of Root 4: Subtree(2) <-> Subtree(7)",
        explanation: "Swapped entire left subtree (now rooted at 7) with right subtree (now rooted at 2)."
      });

      steps.push({
        type: "done",
        tree: JSON.parse(JSON.stringify(treeState)),
        currentNode: null,
        activeLine: 7,
        title: "Tree Inversion Complete!",
        explanation: "Every node has had its left and right pointers reversed. Level-order output: [4, 7, 2, 9, 6, 3, 1]. Invariant fulfilled in O(N) time."
      });

      return steps;
    }
  },

  // 6. Grid BFS / Rotting Oranges
  gridBFS: {
    defaultInput: [[2, 1, 1], [1, 1, 0], [0, 1, 1]],
    parseInput: (str) => {
      try {
        const rows = str.split(";").map(r => r.trim().split(/\s+/).map(x => parseInt(x, 10)));
        return rows.length > 0 ? rows : [[2, 1, 1], [1, 1, 0], [0, 1, 1]];
      } catch (e) {
        return [[2, 1, 1], [1, 1, 0], [0, 1, 1]];
      }
    },
    generateSteps: (initGrid) => {
      if (typeof initGrid === "string") {
        initGrid = Simulations.gridBFS.parseInput(initGrid);
      }
      const steps = [];
      const R = initGrid.length, C = initGrid[0].length;
      let grid = initGrid.map(row => [...row]);
      let queue = [];
      let freshCount = 0;

      for (let r = 0; r < R; r++) {
        for (let c = 0; c < C; c++) {
          if (grid[r][c] === 2) queue.push([r, c]);
          else if (grid[r][c] === 1) freshCount++;
        }
      }

      steps.push({
        type: "init",
        grid: grid.map(r => [...r]),
        minutes: 0,
        freshCount,
        queue: [...queue],
        activeCells: [],
        activeLine: 4,
        title: "Minute 0: Enqueue Initial Rotten Oranges",
        explanation: `Found ${queue.length} initial rotten orange(s) at coords: ${queue.map(([r, c]) => `(${r},${c})`).join(", ")}. Fresh count = ${freshCount}.`
      });

      let minutes = 0;
      const DIRS = [[1, 0], [-1, 0], [0, 1], [0, -1]];

      while (queue.length > 0 && freshCount > 0) {
        const size = queue.length;
        const nextQueue = [];
        const rottedThisRound = [];

        for (let i = 0; i < size; i++) {
          const [r, c] = queue[i];
          for (const [dr, dc] of DIRS) {
            const nr = r + dr, nc = c + dc;
            if (nr >= 0 && nr < R && nc >= 0 && nc < C && grid[nr][nc] === 1) {
              grid[nr][nc] = 2; // Rots
              freshCount--;
              nextQueue.push([nr, nc]);
              rottedThisRound.push([nr, nc]);
            }
          }
        }

        minutes++;
        queue = nextQueue;

        steps.push({
          type: "wave",
          grid: grid.map(r => [...r]),
          minutes,
          freshCount,
          queue: [...queue],
          activeCells: rottedThisRound,
          activeLine: 18,
          title: `Minute ${minutes}: Multi-Source BFS Wave Expands`,
          explanation: `Rotted ${rottedThisRound.length} adjacent fresh orange(s) at ${rottedThisRound.map(([r, c]) => `(${r},${c})`).join(", ")}. Remaining fresh oranges = ${freshCount}.`
        });
      }

      const isSuccess = freshCount === 0;
      steps.push({
        type: "done",
        grid: grid.map(r => [...r]),
        minutes: isSuccess ? minutes : -1,
        freshCount,
        queue: [],
        activeCells: [],
        activeLine: 24,
        title: isSuccess ? `All Oranges Rotted in ${minutes} Minutes!` : "Impossible to Rot All Oranges!",
        explanation: isSuccess
          ? `BFS finished in ${minutes} waves. All fresh oranges reached!`
          : `BFS queue empty but ${freshCount} fresh orange(s) remained isolated and unreachable. Return -1.`
      });

      return steps;
    }
  },

  // 7. Merge Intervals Simulation
  mergeIntervals: {
    defaultInput: [[1, 3], [2, 6], [8, 10], [15, 18]],
    parseInput: (str) => {
      try {
        const matches = str.match(/\[\s*\d+\s*,\s*\d+\s*\]/g);
        if (!matches) return [[1, 3], [2, 6], [8, 10], [15, 18]];
        return matches.map(m => {
          const nums = m.replace(/[\[\]]/g, "").split(",").map(x => parseInt(x.trim(), 10));
          return [nums[0], nums[1]];
        });
      } catch (e) {
        return [[1, 3], [2, 6], [8, 10], [15, 18]];
      }
    },
    generateSteps: (rawIntervals) => {
      if (typeof rawIntervals === "string") {
        rawIntervals = Simulations.mergeIntervals.parseInput(rawIntervals);
      }
      const steps = [];
      const intervals = [...rawIntervals].sort((a, b) => a[0] - b[0]);
      const merged = [];

      steps.push({
        type: "init",
        intervals: JSON.parse(JSON.stringify(intervals)),
        merged: [],
        currentIndex: 0,
        activeLine: 2,
        title: "Sort Intervals by Start Time",
        explanation: `First, sort all intervals by start time: ${intervals.map(i => `[${i[0]},${i[1]}]`).join(", ")}. Now overlapping intervals are guaranteed to be contiguous.`
      });

      for (let i = 0; i < intervals.length; i++) {
        const curr = intervals[i];
        if (merged.length === 0 || merged[merged.length - 1][1] < curr[0]) {
          merged.push([...curr]);
          steps.push({
            type: "append",
            intervals: JSON.parse(JSON.stringify(intervals)),
            merged: JSON.parse(JSON.stringify(merged)),
            currentIndex: i,
            activeLine: 6,
            title: `Append Non-Overlapping Interval [${curr[0]}, ${curr[1]}]`,
            explanation: `Previous interval ended before current start (${merged.length > 1 ? merged[merged.length - 2][1] : "None"} < ${curr[0]}). No overlap possible, append new interval.`
          });
        } else {
          const prev = merged[merged.length - 1];
          const oldEnd = prev[1];
          prev[1] = Math.max(prev[1], curr[1]);
          steps.push({
            type: "merge",
            intervals: JSON.parse(JSON.stringify(intervals)),
            merged: JSON.parse(JSON.stringify(merged)),
            currentIndex: i,
            oldEnd,
            newEnd: prev[1],
            activeLine: 9,
            title: `Merge Overlapping Intervals! [${prev[0]}, ${oldEnd}] + [${curr[0]}, ${curr[1]}] -> [${prev[0]}, ${prev[1]}]`,
            explanation: `Current start (${curr[0]}) <= previous end (${oldEnd}). Merge them by updating end = max(${oldEnd}, ${curr[1]}) = ${prev[1]}.`
          });
        }
      }

      steps.push({
        type: "done",
        intervals: JSON.parse(JSON.stringify(intervals)),
        merged: JSON.parse(JSON.stringify(merged)),
        currentIndex: intervals.length,
        activeLine: 12,
        title: "All Intervals Merged Successfully!",
        explanation: `Final merged list contains ${merged.length} interval(s): ${merged.map(i => `[${i[0]},${i[1]}]`).join(", ")}. Time complexity O(N log N) dominated by sorting.`
      });

      return steps;
    }
  }
};

if (typeof window !== "undefined") {
  window.Simulations = Simulations;
}
if (typeof module !== "undefined" && module.exports) {
  module.exports = { Simulations };
}
