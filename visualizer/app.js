/**
 * ============================================================================
 * DSA Studio & Algorithmic Workbench Controller
 * ============================================================================
 * Coordinates simulation states, telemetry metrics, synchronized code tracing,
 * Web Audio synthesizer, toasts, and bespoke Amber Canvas rendering.
 */

class SoundFX {
  constructor() {
    this.ctx = null;
    this.enabled = false;
  }

  ensureContext() {
    if (!this.ctx && (typeof window !== "undefined") && (window.AudioContext || window.webkitAudioContext)) {
      const AudioCtx = window.AudioContext || window.webkitAudioContext;
      this.ctx = new AudioCtx();
    }
    if (this.ctx && this.ctx.state === "suspended") {
      this.ctx.resume();
    }
  }

  toggle() {
    this.enabled = !this.enabled;
    if (this.enabled) {
      this.ensureContext();
      this.playStep();
    }
    return this.enabled;
  }

  playStep() {
    if (!this.enabled) return;
    try {
      this.ensureContext();
      if (!this.ctx) return;
      const osc = this.ctx.createOscillator();
      const gain = this.ctx.createGain();
      osc.type = "sine";
      osc.frequency.setValueAtTime(540, this.ctx.currentTime);
      osc.frequency.exponentialRampToValueAtTime(320, this.ctx.currentTime + 0.04);
      gain.gain.setValueAtTime(0.08, this.ctx.currentTime);
      gain.gain.exponentialRampToValueAtTime(0.001, this.ctx.currentTime + 0.04);
      osc.connect(gain);
      gain.connect(this.ctx.destination);
      osc.start();
      osc.stop(this.ctx.currentTime + 0.045);
    } catch (e) {}
  }

  playSuccess() {
    if (!this.enabled) return;
    try {
      this.ensureContext();
      if (!this.ctx) return;
      const notes = [523.25, 659.25, 783.99]; // C5, E5, G5 triad
      notes.forEach((freq, idx) => {
        const osc = this.ctx.createOscillator();
        const gain = this.ctx.createGain();
        osc.type = "triangle";
        osc.frequency.setValueAtTime(freq, this.ctx.currentTime + idx * 0.08);
        gain.gain.setValueAtTime(0.12, this.ctx.currentTime + idx * 0.08);
        gain.gain.exponentialRampToValueAtTime(0.001, this.ctx.currentTime + idx * 0.08 + 0.2);
        osc.connect(gain);
        gain.connect(this.ctx.destination);
        osc.start(this.ctx.currentTime + idx * 0.08);
        osc.stop(this.ctx.currentTime + idx * 0.08 + 0.22);
      });
    } catch (e) {}
  }

  playError() {
    if (!this.enabled) return;
    try {
      this.ensureContext();
      if (!this.ctx) return;
      const osc = this.ctx.createOscillator();
      const gain = this.ctx.createGain();
      osc.type = "sawtooth";
      osc.frequency.setValueAtTime(140, this.ctx.currentTime);
      osc.frequency.linearRampToValueAtTime(110, this.ctx.currentTime + 0.12);
      gain.gain.setValueAtTime(0.12, this.ctx.currentTime);
      gain.gain.exponentialRampToValueAtTime(0.001, this.ctx.currentTime + 0.12);
      osc.connect(gain);
      gain.connect(this.ctx.destination);
      osc.start();
      osc.stop(this.ctx.currentTime + 0.13);
    } catch (e) {}
  }
}

const WIZARD_DATA = {
  structures: [
    {
      id: "array_string",
      icon: "📊",
      title: "Array or String",
      desc: "Linear sequence of numbers, characters, or monotonic tokens.",
      goals: [
        {
          title: "Contiguous Subsegment / Substring Condition",
          desc: "E.g., longest substring without repeating characters, max sum subsegment of size K, at most K distinct elements.",
          patternId: "sliding-window",
          cue: "Look for contiguous windows where expanding right and shrinking left maintains validity."
        },
        {
          title: "Opposite Boundaries or Sorted Pair Search",
          desc: "E.g., Two Sum on sorted array, Container With Most Water, 3Sum, Valid Palindrome.",
          patternId: "two-pointers",
          cue: "Pointers at 0 and N-1 converge inward based on directional monotonicity in O(N)."
        },
        {
          title: "Next Greater Element / Span / Monotonic Boundary",
          desc: "E.g., Daily Temperatures, Next Greater Element, Largest Rectangle in Histogram.",
          patternId: "monotonic-stack",
          cue: "Elements must be resolved when a larger/smaller boundary arrives; maintains monotonic order."
        },
        {
          title: "Search in Logarithmic Time O(log N)",
          desc: "E.g., Binary Search, Rotated Sorted Array Search, Find First and Last Position.",
          patternId: "binary-search",
          cue: "Sorted or rotated monotonic space allowing half the search candidates to be discarded each step."
        },
        {
          title: "Overlapping Ranges or Meeting Schedules",
          desc: "E.g., Merge Intervals, Insert Interval, Non-overlapping Intervals, Meeting Rooms.",
          patternId: "merge-intervals",
          cue: "Sort by start times, then compare adjacent intervals: next.start <= curr.end implies overlap."
        },
        {
          title: "Optimal Subproblems / Min Cost / Combinations",
          desc: "E.g., Coin Change, Longest Increasing Subsequence, Climbing Stairs, 0/1 Knapsack.",
          patternId: "dynamic-programming",
          cue: "Overlapping subproblems and optimal substructure; state transitions via memoization/tabulation."
        }
      ]
    },
    {
      id: "linked_list",
      icon: "🔗",
      title: "Linked List",
      desc: "Sequence of linked nodes with singly/doubly linked pointers.",
      goals: [
        {
          title: "Cycle Detection, Midpoint, or Palindrome Check",
          desc: "E.g., Linked List Cycle, Find Middle Node, Palindrome Linked List in O(1) space.",
          patternId: "fast-slow-pointers",
          cue: "Two pointers advancing at different speeds (1 step vs 2 steps) detect cycles and midpoints without extra memory."
        }
      ]
    },
    {
      id: "tree",
      icon: "🌳",
      title: "Binary Tree or Hierarchy",
      desc: "Hierarchical parent-child node graph.",
      goals: [
        {
          title: "Level-by-Level Traversal, Shortest Depth, Zigzag",
          desc: "E.g., Binary Tree Level Order Traversal, Zigzag Level Order, Right Side View.",
          patternId: "tree-traversal",
          cue: "FIFO Queue BFS snapshots level sizes to process nodes tier-by-tier."
        }
      ]
    },
    {
      id: "dag",
      icon: "🧭",
      title: "DAG with Dependencies",
      desc: "Directed graph representing task ordering, prerequisites, or compilation dependencies.",
      goals: [
        {
          title: "Prerequisite Resolution or Cycle Detection in Tasks",
          desc: "E.g., Course Schedule I & II, Alien Dictionary, Build Systems.",
          patternId: "topological-sort",
          cue: "Kahn's Algorithm tracks in-degrees; nodes with in-degree 0 are scheduled iteratively."
        }
      ]
    },
    {
      id: "matrix_grid",
      icon: "▦",
      title: "2D Grid / Matrix",
      desc: "2D grid where moves are allowed to adjacent cells (up, down, left, right).",
      goals: [
        {
          title: "Shortest Path in Unweighted Grid or Multi-Source Spread",
          desc: "E.g., Rotting Oranges, 01 Matrix, Shortest Path in Binary Matrix.",
          patternId: "grid-bfs",
          cue: "Multi-source BFS queues all starting cells simultaneously to simulate uniform elapsed time waves."
        }
      ]
    },
    {
      id: "stream_frequency",
      icon: "⚡",
      title: "Stream or Frequency Tracking",
      desc: "High-volume stream or array requiring top K elements or rolling medians.",
      goals: [
        {
          title: "Top K Frequent Elements or Kth Largest Element",
          desc: "E.g., Top K Frequent Elements, Kth Largest in Array, Find Median from Data Stream.",
          patternId: "top-k-heap",
          cue: "Min-Heap of size K retains the K largest elements; root eviction happens in O(log K) rather than O(N log N)."
        }
      ]
    }
  ]
};

class DSAStudioApp {
  constructor() {
    this.patterns = PATTERNS_DATA;
    this.currentPatternId = "two-pointers";
    this.currentSteps = [];
    this.currentStepIdx = 0;
    this.isPlaying = false;
    this.playInterval = null;
    this.speedMultiplier = 1.0;
    this.sound = new SoundFX();

    // Educational Workbench State
    this.challengeMode = false;
    this.intuitionTotal = 0;
    this.intuitionCorrect = 0;
    this.activeTab = "code";
    this.challengedSteps = new Set();
    this.wasPlayingBeforeChallenge = false;
    this.activeChallenge = null;

    this.initDOM();
    this.bindEvents();
    this.renderPatternTabs();
    this.renderReferenceGrid();
    this.initWizard();
    this.loadPattern(this.currentPatternId);
  }

  initDOM() {
    this.dom = {
      themeToggle: document.getElementById("themeToggle"),
      soundToggleBtn: document.getElementById("soundToggleBtn"),
      soundIcon: document.getElementById("soundIcon"),
      fullscreenToggleBtn: document.getElementById("fullscreenToggleBtn"),
      patternNavTrack: document.getElementById("patternNavTrack"),
      stageProblemTitle: document.getElementById("stageProblemTitle"),
      stageComplexityPill: document.getElementById("stageComplexityPill"),
      signalKeywordsRow: document.getElementById("signalKeywordsRow"),
      presetPillsRow: document.getElementById("presetPillsRow"),
      customInputBox: document.getElementById("customInputBox"),
      applyInputBtn: document.getElementById("applyInputBtn"),
      canvasDisplayStage: document.getElementById("canvasDisplayStage"),
      playPauseBtn: document.getElementById("playPauseBtn"),
      playIcon: document.getElementById("playIcon"),
      playText: document.getElementById("playText"),
      prevStepBtn: document.getElementById("prevStepBtn"),
      nextStepBtn: document.getElementById("nextStepBtn"),
      resetBtn: document.getElementById("resetBtn"),
      scrubberSlider: document.getElementById("scrubberSlider"),
      scrubberCounter: document.getElementById("scrubberCounter"),
      speedDropdown: document.getElementById("speedDropdown"),
      stepHeadline: document.getElementById("stepHeadline"),
      stepRationaleBody: document.getElementById("stepRationaleBody"),
      telemetryGrid: document.getElementById("telemetryGrid"),
      codeFilenameLabel: document.getElementById("codeFilenameLabel"),
      codeLinesScrollbox: document.getElementById("codeLinesScrollbox"),
      repoPathText: document.getElementById("repoPathText"),
      cliCmdLabel: document.getElementById("cliCmdLabel"),
      copyCliBtn: document.getElementById("copyCliBtn"),
      practiceQueueList: document.getElementById("practiceQueueList"),
      referenceSearchInput: document.getElementById("referenceSearchInput"),
      referenceCountLabel: document.getElementById("referenceCountLabel"),
      referenceCardsGrid: document.getElementById("referenceCardsGrid"),
      toastContainer: document.getElementById("toastContainer"),

      // Educational Workbench Elements
      challengeModeBtn: document.getElementById("challengeModeBtn"),
      challengeBtnText: document.getElementById("challengeBtnText"),
      challengeIcon: document.getElementById("challengeIcon"),
      intuitionScorePill: document.getElementById("intuitionScorePill"),
      intuitionScoreVal: document.getElementById("intuitionScoreVal"),
      challengeCardOverlay: document.getElementById("challengeCardOverlay"),
      challengeStepBadge: document.getElementById("challengeStepBadge"),
      challengeQuestionTitle: document.getElementById("challengeQuestionTitle"),
      challengeContextHint: document.getElementById("challengeContextHint"),
      challengeOptionsList: document.getElementById("challengeOptionsList"),
      challengeFeedbackBox: document.getElementById("challengeFeedbackBox"),
      feedbackStatus: document.getElementById("feedbackStatus"),
      feedbackExplanation: document.getElementById("feedbackExplanation"),
      challengeContinueBtn: document.getElementById("challengeContinueBtn"),

      copyTemplateBtn: document.getElementById("copyTemplateBtn"),
      copyMarkdownTableBtn: document.getElementById("copyMarkdownTableBtn"),
      dryrunStepCountChip: document.getElementById("dryrunStepCountChip"),
      dryrunTableWrapper: document.getElementById("dryrunTableWrapper"),

      playbookCheatCode: document.getElementById("playbookCheatCode"),
      playbookClarifyingList: document.getElementById("playbookClarifyingList"),
      playbookTrapsList: document.getElementById("playbookTrapsList"),
      proofBodyText: document.getElementById("proofBodyText"),
      deepDiveTimeBadge: document.getElementById("deepDiveTimeBadge"),
      deepDiveTimeDesc: document.getElementById("deepDiveTimeDesc"),
      deepDiveSpaceBadge: document.getElementById("deepDiveSpaceBadge"),
      deepDiveSpaceDesc: document.getElementById("deepDiveSpaceDesc"),

      wizardCardStage: document.getElementById("wizardCardStage")
    };
  }

  bindEvents() {
    // Playback buttons
    this.dom.playPauseBtn.addEventListener("click", () => this.togglePlay());
    this.dom.prevStepBtn.addEventListener("click", () => this.prevStep());
    this.dom.nextStepBtn.addEventListener("click", () => this.nextStep());
    this.dom.resetBtn.addEventListener("click", () => this.resetSimulation());

    // Scrubber
    this.dom.scrubberSlider.addEventListener("input", (e) => {
      this.pause();
      this.goToStep(parseInt(e.target.value, 10));
    });

    // Speed dropdown
    this.dom.speedDropdown.addEventListener("change", (e) => {
      this.speedMultiplier = parseFloat(e.target.value);
      if (this.isPlaying) {
        this.pause();
        this.play();
      }
    });

    // Custom Input Box
    this.dom.applyInputBtn.addEventListener("click", () => this.handleCustomInput());
    this.dom.customInputBox.addEventListener("keydown", (e) => {
      if (e.key === "Enter") this.handleCustomInput();
    });

    // Challenge Mode Toggle
    if (this.dom.challengeModeBtn) {
      this.dom.challengeModeBtn.addEventListener("click", () => this.toggleChallengeMode());
    }

    // Challenge Continue Button
    if (this.dom.challengeContinueBtn) {
      this.dom.challengeContinueBtn.addEventListener("click", () => this.continueAfterChallenge());
    }

    // Inspector Tabs Nav
    document.querySelectorAll(".inspector-tab-btn").forEach(btn => {
      btn.addEventListener("click", () => {
        const tab = btn.getAttribute("data-tab");
        this.switchTab(tab);
      });
    });

    // Copy Starter Template
    if (this.dom.copyTemplateBtn) {
      this.dom.copyTemplateBtn.addEventListener("click", () => this.handleCopyStarterTemplate());
    }

    // Copy Markdown Table
    if (this.dom.copyMarkdownTableBtn) {
      this.dom.copyMarkdownTableBtn.addEventListener("click", () => this.handleCopyMarkdownTable());
    }

    // Sound FX Toggle
    if (this.dom.soundToggleBtn) {
      this.dom.soundToggleBtn.addEventListener("click", () => {
        const enabled = this.sound.toggle();
        if (this.dom.soundIcon) this.dom.soundIcon.innerText = enabled ? "🔊" : "🔇";
        this.dom.soundToggleBtn.title = enabled
          ? "Sound FX: ON (Synthesizer Active)"
          : "Sound FX: OFF (Muted)";
        this.showToast(enabled ? "Sound FX Enabled" : "Sound FX Muted", "info");
      });
    }

    // Fullscreen Toggle
    if (this.dom.fullscreenToggleBtn) {
      this.dom.fullscreenToggleBtn.addEventListener("click", () => {
        if (!document.fullscreenElement) {
          document.documentElement.requestFullscreen().catch(() => {});
          this.showToast("Entered Fullscreen Studio Mode", "info");
        } else {
          document.exitFullscreen().catch(() => {});
          this.showToast("Exited Fullscreen", "info");
        }
      });
    }

    // Reference Matrix Search Filter
    if (this.dom.referenceSearchInput) {
      this.dom.referenceSearchInput.addEventListener("input", (e) => {
        this.renderReferenceGrid(e.target.value.trim().toLowerCase());
      });
    }

    // Copy CLI Command
    this.dom.copyCliBtn.addEventListener("click", () => {
      navigator.clipboard.writeText(this.dom.cliCmdLabel.innerText);
      const prev = this.dom.copyCliBtn.innerText;
      this.dom.copyCliBtn.innerText = "Copied!";
      this.showToast("CLI command copied to clipboard!", "success");
      setTimeout(() => { this.dom.copyCliBtn.innerText = prev; }, 1600);
    });

    // Theme Toggle
    this.dom.themeToggle.addEventListener("click", () => {
      const curr = document.documentElement.getAttribute("data-theme") || "dark";
      const target = curr === "dark" ? "light" : "dark";
      document.documentElement.setAttribute("data-theme", target);
      this.showToast(`Switched to ${target === 'dark' ? 'Amber Obsidian Dark' : 'Amber Sand Light'} theme`, "info");
    });

    // Keyboard Shortcuts
    if (typeof window !== "undefined" && window.addEventListener) {
      window.addEventListener("keydown", (e) => {
      if (e.target.tagName === "INPUT" || e.target.tagName === "TEXTAREA") return;
      if (e.code === "Space") {
        e.preventDefault();
        this.togglePlay();
      } else if (e.code === "ArrowRight") {
        e.preventDefault();
        this.nextStep();
      } else if (e.code === "ArrowLeft") {
        e.preventDefault();
        this.prevStep();
      } else if (e.code === "KeyR") {
        this.resetSimulation();
      }
    });
  }
}

  showToast(message, type = "info") {
    if (!this.dom.toastContainer) return;
    const toast = document.createElement("div");
    toast.className = `toast-message ${type}`;
    const icon = type === "error" ? "⚠️" : (type === "success" ? "✓" : "ℹ️");
    toast.innerHTML = `<span>${icon}</span><span>${message}</span>`;
    this.dom.toastContainer.appendChild(toast);
    setTimeout(() => {
      toast.style.opacity = "0";
      toast.style.transform = "translateY(10px)";
      toast.style.transition = "all 0.25s";
      setTimeout(() => {
        if (typeof toast.remove === "function") toast.remove();
        else if (toast.parentNode) toast.parentNode.removeChild(toast);
      }, 260);
    }, 3200);
  }

  renderPatternTabs() {
    this.dom.patternNavTrack.innerHTML = this.patterns.map(p => `
      <li>
        <button class="pattern-tab-btn ${p.id === this.currentPatternId ? 'active' : ''}" 
                onclick="window.studioApp.loadPattern('${p.id}')">
          <span>${p.name}</span>
          <span class="tab-badge">${p.curatedProblems.length}</span>
        </button>
      </li>
    `).join("");
  }

  loadPattern(patternId, customData = null) {
    this.pause();
    this.currentPatternId = patternId;
    const pattern = this.patterns.find(p => p.id === patternId);
    if (!pattern) return;

    // Refresh active tab state
    this.renderPatternTabs();

    // Stage Top Meta
    this.dom.stageProblemTitle.innerText = pattern.defaultProblem.split(" (")[0];
    this.dom.stageComplexityPill.innerText = pattern.complexity;
    this.dom.signalKeywordsRow.innerHTML = pattern.signalKeywords.map(kw => `
      <span class="kw-tag">${kw}</span>
    `).join("");

    // Presets Row
    if (pattern.presets && pattern.presets.length > 0) {
      this.dom.presetPillsRow.innerHTML = pattern.presets.map((preset, idx) => `
        <button class="preset-chip-btn" onclick="window.studioApp.loadPreset('${pattern.id}', ${idx})">
          ${preset.label}
        </button>
      `).join("");
    } else {
      this.dom.presetPillsRow.innerHTML = "";
    }

    // Code & Repo Meta
    this.dom.codeFilenameLabel.innerText = pattern.repoPath.split("/").pop();
    this.dom.repoPathText.innerText = pattern.repoPath;
    this.dom.cliCmdLabel.innerText = `java -cp out ${pattern.repoClass}`;

    // Practice Queue List
    this.dom.practiceQueueList.innerHTML = pattern.curatedProblems.map(q => `
      <li class="problem-list-entry">
        <span style="display:flex; align-items:center; gap:0.4rem;">
          <span style="font-family:var(--font-mono); color:var(--text-dim);">#${q.id}</span>
          <b>${q.name}</b>
        </span>
        <div style="display:flex; align-items:center; gap:0.4rem;">
          <span class="kw-tag" style="font-size:0.65rem;">${q.company}</span>
          <span class="diff-tag ${q.difficulty.toLowerCase()}">${q.difficulty}</span>
        </div>
      </li>
    `).join("");

    // Simulation Step Generation
    const simEngine = this.getSimEngine(patternId);
    if (simEngine) {
      let input = customData !== null ? customData : simEngine.defaultInput;
      this.formatInputBox(input, patternId);
      this.currentSteps = simEngine.generateSteps(input);
    } else {
      this.currentSteps = [];
    }

    this.currentStepIdx = 0;
    this.dom.scrubberSlider.max = Math.max(0, this.currentSteps.length - 1);
    this.dom.scrubberSlider.value = 0;

    // Reset educational workbench state for new pattern
    this.challengedSteps.clear();
    this.hideChallengeOverlay();
    this.renderPlaybookAndComplexity(pattern);
    this.renderDryRunTable();

    this.renderStep();
  }

  loadPreset(patternId, presetIdx) {
    const pattern = this.patterns.find(p => p.id === patternId);
    if (!pattern || !pattern.presets || !pattern.presets[presetIdx]) return;
    const simEngine = this.getSimEngine(patternId);
    if (!simEngine) return;
    const rawVal = pattern.presets[presetIdx].value;
    const parsed = simEngine.parseInput(rawVal);
    this.loadPattern(patternId, parsed);
    this.showToast(`Loaded preset: "${pattern.presets[presetIdx].label}"`, "info");
  }

  formatInputBox(input, patternId) {
    if (patternId === "binary-search") {
      if (input && input.nums) {
        this.dom.customInputBox.value = `${input.nums.join(", ")}; ${input.target}`;
      } else {
        this.dom.customInputBox.value = "4, 5, 6, 7, 0, 1, 2; 0";
      }
    } else if (patternId === "fast-slow-pointers") {
      if (input && input.values) {
        if (input.pos >= 0 && input.pos < input.values.length) {
          this.dom.customInputBox.value = `${input.values.join("->")}->${input.values[input.pos]}`;
        } else {
          this.dom.customInputBox.value = `${input.values.join("->")}->null`;
        }
      } else {
        this.dom.customInputBox.value = "1->2->3->4->5->6->3";
      }
    } else if (patternId === "topological-sort") {
      if (input && input.prerequisites) {
        this.dom.customInputBox.value = `${input.numCourses}; ${input.prerequisites.map(p => `[${p[0]},${p[1]}]`).join(",")}`;
      } else {
        this.dom.customInputBox.value = "4; [1,0],[2,0],[3,1],[3,2]";
      }
    } else if (patternId === "top-k-heap") {
      if (input && input.nums) {
        this.dom.customInputBox.value = `[${input.nums.join(", ")}]; ${input.k}`;
      } else {
        this.dom.customInputBox.value = "[1, 1, 1, 2, 2, 3]; 2";
      }
    } else if (patternId === "dynamic-programming") {
      if (input && input.coins) {
        this.dom.customInputBox.value = `[${input.coins.join(", ")}]; ${input.amount}`;
      } else {
        this.dom.customInputBox.value = "[1, 2, 5]; 7";
      }
    } else if (patternId === "grid-bfs") {
      if (Array.isArray(input)) {
        this.dom.customInputBox.value = input.map(r => r.join(" ")).join(" ; ");
      } else {
        this.dom.customInputBox.value = "2 1 1 ; 1 1 0 ; 0 1 1";
      }
    } else if (patternId === "merge-intervals") {
      if (Array.isArray(input)) {
        this.dom.customInputBox.value = `[${input.map(i => `[${i[0]},${i[1]}]`).join(",")}]`;
      } else {
        this.dom.customInputBox.value = "[[1,3],[2,6],[8,10],[15,18]]";
      }
    } else if (Array.isArray(input)) {
      this.dom.customInputBox.value = `[${input.join(", ")}]`;
    } else {
      this.dom.customInputBox.value = input;
    }
  }

  getSimEngine(patternId) {
    switch (patternId) {
      case "two-pointers": return Simulations.twoPointers;
      case "sliding-window": return Simulations.slidingWindow;
      case "monotonic-stack": return Simulations.monotonicStack;
      case "binary-search": return Simulations.binarySearch;
      case "fast-slow-pointers": return Simulations.fastSlowPointers;
      case "tree-traversal": return Simulations.treeTraversal;
      case "grid-bfs": return Simulations.gridBFS;
      case "topological-sort": return Simulations.topologicalSort;
      case "merge-intervals": return Simulations.mergeIntervals;
      case "top-k-heap": return Simulations.topKHeap;
      case "dynamic-programming": return Simulations.dynamicProgramming;
      default: return Simulations.twoPointers;
    }
  }

  handleCustomInput() {
    const raw = this.dom.customInputBox.value.trim();
    if (!raw) {
      this.sound.playError();
      this.showToast("Input box cannot be empty", "error");
      return;
    }

    const simEngine = this.getSimEngine(this.currentPatternId);
    if (!simEngine) return;

    try {
      const parsed = simEngine.parseInput(raw);
      const testSteps = simEngine.generateSteps(parsed);
      if (!testSteps || testSteps.length === 0) {
        throw new Error("Simulation generated 0 steps");
      }
      this.loadPattern(this.currentPatternId, parsed);
      this.sound.playStep();
      this.showToast("Custom input applied successfully!", "success");
    } catch (e) {
      this.sound.playError();
      this.showToast(`Invalid input format for this pattern: ${e.message}`, "error");
    }
  }

  renderStep() {
    if (this.currentSteps.length === 0) return;
    const step = this.currentSteps[this.currentStepIdx];
    const pattern = this.patterns.find(p => p.id === this.currentPatternId);

    // Update Scrubber
    this.dom.scrubberSlider.value = this.currentStepIdx;
    this.dom.scrubberCounter.innerText = `Step ${this.currentStepIdx + 1} / ${this.currentSteps.length}`;
    this.dom.prevStepBtn.disabled = this.currentStepIdx === 0;
    this.dom.nextStepBtn.disabled = this.currentStepIdx === this.currentSteps.length - 1;

    // Audio cue
    if (step.type === "done" || step.type === "collision" || step.type === "found") {
      this.sound.playSuccess();
    } else {
      this.sound.playStep();
    }

    // Update Invariant Text
    this.dom.stepHeadline.innerText = step.title;
    this.dom.stepRationaleBody.innerText = step.explanation;

    // Telemetry HUD
    this.renderTelemetry(step);

    // Canvas Stage
    this.renderCanvasStage(step);

    // Synchronized Code
    this.renderSynchronizedCode(pattern.codeSnippet, step.activeLine);

    // Update Whiteboard Dry-Run Trace Table Active Step
    this.updateDryRunActiveStep();

    // Challenge Mode Checkpoint
    if (this.challengeMode && !this.challengedSteps.has(this.currentStepIdx)) {
      const quiz = this.generateChallengeForStep(this.currentPatternId, step, this.currentStepIdx);
      if (quiz) {
        this.triggerChallenge(quiz, this.currentStepIdx);
      }
    }
  }

  renderTelemetry(step) {
    let cells = [];
    if (this.currentPatternId === "two-pointers") {
      cells = [
        `Left: <b>${step.left}</b>`,
        `Right: <b>${step.right}</b>`,
        `Span Width: <b>${step.width}</b>`,
        `Min H: <b>${step.h}</b>`,
        `Current Area: <b>${step.currArea}</b>`,
        `Max Area: <b>${step.maxArea}</b>`
      ];
    } else if (this.currentPatternId === "sliding-window") {
      cells = [
        `L: <b>${step.left}</b>`,
        `R: <b>${step.right}</b>`,
        `Char: <b>'${step.char || '-'}'</b>`,
        `Window Size: <b>${step.currSub ? step.currSub.length : 0}</b>`,
        `Max Size: <b>${step.maxLen}</b>`
      ];
    } else if (this.currentPatternId === "monotonic-stack") {
      cells = [
        `Current Day: <b>${step.currentIndex >= 0 ? step.currentIndex : 'Done'}</b>`,
        `Stack Depth: <b>${step.stack.length}</b>`,
        `Top Item: <b>${step.stack.length > 0 ? step.stack[step.stack.length - 1] : 'Empty'}</b>`
      ];
    } else if (this.currentPatternId === "binary-search") {
      cells = [
        `Low: <b>${step.low}</b>`,
        `Mid: <b>${step.mid >= 0 ? step.mid : '-'}</b>`,
        `High: <b>${step.high}</b>`,
        `Target: <b>${step.target}</b>`
      ];
    } else if (this.currentPatternId === "fast-slow-pointers") {
      cells = [
        `Slow (1x): <b>Node ${step.slow !== null ? step.slow : 'null'}</b>`,
        `Fast (2x): <b>Node ${step.fast !== null ? step.fast : 'null'}</b>`,
        `Step Count: <b>${step.stepCount}</b>`,
        `Cycle Met: <b>${step.cycleMet ? 'YES ⚡' : 'NO'}</b>`
      ];
    } else if (this.currentPatternId === "grid-bfs") {
      cells = [
        `Elapsed: <b>${step.minutes}m</b>`,
        `Fresh Remaining: <b>${step.freshCount}</b>`,
        `Active Queue: <b>${step.queue.length}</b>`
      ];
    } else if (this.currentPatternId === "topological-sort") {
      cells = [
        `Active Course: <b>${step.activeNode !== null ? step.activeNode : 'None'}</b>`,
        `Queue Size: <b>${step.queue.length}</b>`,
        `Resolved Count: <b>${step.topoOrder.length} / ${step.numCourses}</b>`
      ];
    } else if (this.currentPatternId === "merge-intervals") {
      cells = [
        `Current Index: <b>${step.currentIndex}</b>`,
        `Merged Blocks: <b>${step.merged.length}</b>`
      ];
    } else if (this.currentPatternId === "top-k-heap") {
      cells = [
        `Heap Size: <b>${step.heap.length} / ${step.k}</b>`,
        `Root (Min): <b>${step.heap.length > 0 ? `${step.heap[0]} (${step.count[step.heap[0]]}x)` : 'Empty'}</b>`,
        `Evicted: <b>${step.evicted !== null ? step.evicted : 'None'}</b>`
      ];
    } else if (this.currentPatternId === "dynamic-programming") {
      cells = [
        `Subproblem: <b>dp[${step.currentAmount}]</b>`,
        `Testing Coin: <b>${step.currentCoin !== null ? step.currentCoin : '-'}</b>`,
        `Lookup: <b>${step.lookupIdx !== null ? `dp[${step.lookupIdx}]` : '-'}</b>`,
        `Optimal dp[amt]: <b>${step.dp[step.currentAmount] > step.amount ? '∞' : step.dp[step.currentAmount]}</b>`
      ];
    }

    this.dom.telemetryGrid.innerHTML = cells.map(c => `<span class="telemetry-cell">${c}</span>`).join("");
  }

  renderCanvasStage(step) {
    if (this.currentPatternId === "two-pointers") {
      this.renderTwoPointersCanvas(step);
    } else if (this.currentPatternId === "sliding-window") {
      this.renderSlidingWindowCanvas(step);
    } else if (this.currentPatternId === "monotonic-stack") {
      this.renderMonotonicStackCanvas(step);
    } else if (this.currentPatternId === "binary-search") {
      this.renderBinarySearchCanvas(step);
    } else if (this.currentPatternId === "fast-slow-pointers") {
      this.renderFastSlowPointersCanvas(step);
    } else if (this.currentPatternId === "tree-traversal") {
      this.renderTreeCanvas(step);
    } else if (this.currentPatternId === "grid-bfs") {
      this.renderGridBFSCanvas(step);
    } else if (this.currentPatternId === "topological-sort") {
      this.renderTopologicalSortCanvas(step);
    } else if (this.currentPatternId === "merge-intervals") {
      this.renderMergeIntervalsCanvas(step);
    } else if (this.currentPatternId === "top-k-heap") {
      this.renderTopKHeapCanvas(step);
    } else if (this.currentPatternId === "dynamic-programming") {
      this.renderDynamicProgrammingCanvas(step);
    }
  }

  // 1. Two Pointers Canvas (Amber)
  renderTwoPointersCanvas(step) {
    const heights = step.heights || (this.currentSteps[0] && this.currentSteps[0].heights) || [];
    const maxVal = Math.max(...heights, 1);
    const maxHeightPx = 180;

    let barsHTML = heights.map((h, i) => {
      const barPx = Math.max(16, Math.round((h / maxVal) * maxHeightPx));
      const isLeft = (i === step.left);
      const isRight = (i === step.right);
      const isEliminated = (i < step.left || i > step.right);

      let barClass = "pillar-bar";
      if (isLeft) barClass += " left-active";
      if (isRight) barClass += " right-active";
      if (isEliminated) barClass += " eliminated";

      return `
        <div class="height-col">
          <div class="${barClass}" style="height: ${barPx}px;">${h}</div>
          <span style="font-family:var(--font-mono); font-size:0.7rem; color:var(--text-dim); margin-top:5px;">${i}</span>
          ${isLeft ? '<span class="pointer-pill">L</span>' : ''}
          ${isRight ? '<span class="pointer-pill" style="background:#d97706;">R</span>' : ''}
        </div>
      `;
    }).join("");

    this.dom.canvasDisplayStage.innerHTML = `
      <div class="two-pointers-stage">
        ${barsHTML}
      </div>
      <div style="margin-top:1.25rem; font-family:var(--font-mono); font-size:0.85rem; color:var(--accent-light);">
        Current Volume: <b>${step.currArea}</b> (Width ${step.width} × MinH ${step.h}) | Optimal: <b>${step.maxArea}</b>
      </div>
    `;
  }

  // 2. Sliding Window Canvas (Amber)
  renderSlidingWindowCanvas(step) {
    const s = step.s;
    let cellsHTML = "";
    for (let i = 0; i < s.length; i++) {
      const inWin = (i >= step.left && i <= step.right);
      const isDup = inWin && step.isDuplicate && (s[i] === step.char);

      let cellClass = "window-cell";
      if (inWin) cellClass += " active-window";
      if (isDup) cellClass += " duplicate-breach";

      cellsHTML += `
        <div class="${cellClass}">
          <span class="cell-sub-idx">${i}</span>
          <span>${s[i]}</span>
        </div>
      `;
    }

    const seenMapTags = Object.entries(step.lastSeen || {}).map(([c, idx]) => `
      <span class="char-seen-tag ${c === step.char ? 'highlighted' : ''}">
        '${c}' → idx ${idx}
      </span>
    `).join("");

    this.dom.canvasDisplayStage.innerHTML = `
      <div class="sliding-window-stage">
        <div class="char-strip-grid">
          ${cellsHTML}
        </div>
        <div style="display:flex; flex-direction:column; align-items:center; gap:0.5rem;">
          <span style="font-size:0.75rem; font-family:var(--font-mono); color:var(--text-dim); text-transform:uppercase;">Character Last Seen Lookup:</span>
          <div class="char-seen-strip">${seenMapTags || '<span style="color:var(--text-dim); font-size:0.8rem;">Empty</span>'}</div>
        </div>
      </div>
    `;
  }

  // 3. Monotonic Stack Canvas (Amber)
  renderMonotonicStackCanvas(step) {
    const temps = step.temperatures;
    const stackItems = step.stack.map((idx, pos) => {
      const isTop = pos === step.stack.length - 1;
      return `
        <div class="stack-card-item ${isTop ? 'top-marker' : ''}">
          day ${idx} (${temps[idx]}°) ${isTop ? '▲ TOP' : ''}
        </div>
      `;
    }).join("");

    const answerCells = step.answer.map((ans, idx) => `
      <div style="display:flex; flex-direction:column; align-items:center;">
        <div style="width:36px; height:36px; border:1px solid var(--border); border-radius:4px; display:grid; place-items:center; font-family:var(--font-mono); font-size:0.85rem; font-weight:700; ${ans > 0 ? 'background:var(--accent-glow-subtle); color:var(--accent-light); border-color:var(--accent);' : 'color:var(--text-dim);'}">
          ${ans}
        </div>
        <span style="font-size:0.65rem; color:var(--text-dim); font-family:var(--font-mono); margin-top:2px;">${idx}</span>
      </div>
    `).join("");

    this.dom.canvasDisplayStage.innerHTML = `
      <div class="stack-cockpit-grid">
        <div>
          <div style="font-size:0.75rem; font-family:var(--font-mono); color:var(--text-dim); margin-bottom:0.6rem; text-align:center; text-transform:uppercase;">Input Temperatures:</div>
          <div style="display:flex; flex-wrap:wrap; gap:6px; justify-content:center;">
            ${temps.map((t, i) => `
              <div class="temp-pill ${i === step.currentIndex ? 'current-active' : ''}">
                <span style="font-size:0.6rem; color:var(--text-dim);">${i}</span>
                <span>${t}°</span>
              </div>
            `).join("")}
          </div>
        </div>

        <div>
          <div style="font-size:0.75rem; font-family:var(--font-mono); color:var(--text-dim); margin-bottom:0.4rem; text-align:center; text-transform:uppercase;">Monotonic Chamber</div>
          <div class="stack-beaker">
            ${stackItems || '<div style="text-align:center; color:var(--text-dim); font-size:0.8rem; margin:auto;">Empty Stack</div>'}
          </div>
        </div>

        <div>
          <div style="font-size:0.75rem; font-family:var(--font-mono); color:var(--text-dim); margin-bottom:0.6rem; text-align:center; text-transform:uppercase;">Output Vector (Wait Days):</div>
          <div style="display:flex; flex-wrap:wrap; gap:6px; justify-content:center;">
            ${answerCells}
          </div>
        </div>
      </div>
    `;
  }

  // 4. Binary Search Canvas (Amber)
  renderBinarySearchCanvas(step) {
    const nums = step.nums;
    const boxesHTML = nums.map((val, idx) => {
      let cellClass = "bs-cell";
      if (idx === step.mid) cellClass += " midpoint";
      if (step.type === "found" && idx === step.mid) cellClass += " target-found";
      if (idx < step.low || idx > step.high) cellClass += " discarded-zone";

      return `
        <div class="${cellClass}">
          <span style="font-size:0.65rem; color:var(--text-dim); position:absolute; top:2px;">${idx}</span>
          <span>${val}</span>
          ${idx === step.low ? '<span class="bs-marker" style="left:2px;">L</span>' : ''}
          ${idx === step.mid ? '<span class="bs-marker" style="background:#fbbf24; color:#090c13;">M</span>' : ''}
          ${idx === step.high ? '<span class="bs-marker" style="right:2px; background:#d97706;">H</span>' : ''}
        </div>
      `;
    }).join("");

    this.dom.canvasDisplayStage.innerHTML = `
      <div style="display:flex; flex-direction:column; align-items:center; width:100%;">
        <div class="binary-array-strip">${boxesHTML}</div>
        <div style="font-family:var(--font-mono); font-size:0.85rem; color:var(--text-muted); margin-top:0.5rem;">
          Searching Target: <b style="color:var(--accent-light);">${step.target}</b> | Search Window: [Index ${step.low} .. ${step.high}]
        </div>
      </div>
    `;
  }

  // 5. Fast & Slow Pointers Canvas (Amber)
  renderFastSlowPointersCanvas(step) {
    const vals = step.values;
    const isCollision = (step.type === "collision" || step.cycleMet);

    const nodesHTML = vals.map((v, idx) => {
      const isSlow = (idx === step.slow);
      const isFast = (idx === step.fast);
      const isBoth = (isSlow && isFast);

      let cardClass = "ll-node-card";
      if (isBoth) cardClass += " collision-node";
      else if (isSlow) cardClass += " slow-active";
      else if (isFast) cardClass += " fast-active";

      let tagHTML = "";
      if (isBoth && isCollision) {
        tagHTML = `<span class="ll-pointer-tag collision-tag">⚡ Met at Node ${idx}</span>`;
      } else {
        if (isSlow) tagHTML += `<span class="ll-pointer-tag slow-tag">Slow</span>`;
        if (isFast) tagHTML += `<span class="ll-pointer-tag fast-tag">Fast</span>`;
      }

      const isLast = (idx === vals.length - 1);
      const nextArrow = !isLast
        ? `<span class="ll-arrow-divider">→</span>`
        : (step.pos >= 0
            ? `<span class="ll-arrow-divider" title="Points to node ${step.pos}" style="color:var(--accent);">↴</span>`
            : `<span class="ll-arrow-divider" style="color:var(--text-dim); font-size:0.8rem;">→ null</span>`);

      return `
        <div class="ll-node-wrapper">
          <div class="${cardClass}">
            ${tagHTML}
            <span class="ll-node-val">${v}</span>
            <span class="ll-node-idx">idx ${idx}</span>
          </div>
          ${nextArrow}
        </div>
      `;
    }).join("");

    const loopBanner = step.pos >= 0
      ? `<div class="cycle-loop-banner">
           <span>↺</span>
           <span><b>Cyclic Loop Invariant:</b> Last node (idx ${vals.length - 1}) loops back to node <b>${step.pos}</b> (value ${vals[step.pos]}). Fast closes gap by 1 hop/iter.</span>
         </div>`
      : `<div class="cycle-loop-banner" style="border-color:rgba(255,255,255,0.1); color:var(--text-dim);">
           <span>∅</span>
           <span><b>Acyclic Invariant:</b> No loop present. Fast reaches null in O(N/2) steps.</span>
         </div>`;

    this.dom.canvasDisplayStage.innerHTML = `
      <div class="linked-list-stage">
        <div class="ll-track-row">${nodesHTML}</div>
        ${loopBanner}
      </div>
    `;
  }

  // 6. Binary Tree Canvas (Amber)
  renderTreeCanvas(step) {
    const t = step.tree;
    this.dom.canvasDisplayStage.innerHTML = `
      <svg class="tree-svg-canvas" viewBox="0 0 500 230">
        <line x1="250" y1="35" x2="150" y2="105" stroke="rgba(245,158,11,0.25)" stroke-width="2"/>
        <line x1="250" y1="35" x2="350" y2="105" stroke="rgba(245,158,11,0.25)" stroke-width="2"/>
        <line x1="150" y1="105" x2="100" y2="175" stroke="rgba(245,158,11,0.25)" stroke-width="2"/>
        <line x1="150" y1="105" x2="200" y2="175" stroke="rgba(245,158,11,0.25)" stroke-width="2"/>
        <line x1="350" y1="105" x2="300" y2="175" stroke="rgba(245,158,11,0.25)" stroke-width="2"/>
        <line x1="350" y1="105" x2="400" y2="175" stroke="rgba(245,158,11,0.25)" stroke-width="2"/>

        <!-- Root -->
        <circle cx="250" cy="35" r="22" fill="#182137" stroke="#f59e0b" stroke-width="3"/>
        <text x="250" y="41" text-anchor="middle" fill="#fbbf24" font-family="monospace" font-weight="bold" font-size="14">${t.val}</text>

        <!-- L1 -->
        <circle cx="150" cy="105" r="20" fill="#182137" stroke="${step.currentNode === t.left.val ? '#fbbf24' : '#f59e0b'}" stroke-width="${step.currentNode === t.left.val ? '4' : '2'}"/>
        <text x="150" y="110" text-anchor="middle" fill="#fff" font-family="monospace" font-weight="bold" font-size="13">${t.left.val}</text>

        <circle cx="350" cy="105" r="20" fill="#182137" stroke="${step.currentNode === t.right.val ? '#fbbf24' : '#f59e0b'}" stroke-width="${step.currentNode === t.right.val ? '4' : '2'}"/>
        <text x="350" y="110" text-anchor="middle" fill="#fff" font-family="monospace" font-weight="bold" font-size="13">${t.right.val}</text>

        <!-- Leaves -->
        <circle cx="100" cy="175" r="16" fill="#0f1420" stroke="#475569" stroke-width="2"/>
        <text x="100" y="180" text-anchor="middle" fill="#94a3b8" font-family="monospace" font-size="12">${t.left.left ? t.left.left.val : ''}</text>

        <circle cx="200" cy="175" r="16" fill="#0f1420" stroke="#475569" stroke-width="2"/>
        <text x="200" y="180" text-anchor="middle" fill="#94a3b8" font-family="monospace" font-size="12">${t.left.right ? t.left.right.val : ''}</text>

        <circle cx="300" cy="175" r="16" fill="#0f1420" stroke="#475569" stroke-width="2"/>
        <text x="300" y="180" text-anchor="middle" fill="#94a3b8" font-family="monospace" font-size="12">${t.right.left ? t.right.left.val : ''}</text>

        <circle cx="400" cy="175" r="16" fill="#0f1420" stroke="#475569" stroke-width="2"/>
        <text x="400" y="180" text-anchor="middle" fill="#94a3b8" font-family="monospace" font-size="12">${t.right.right ? t.right.right.val : ''}</text>
      </svg>
    `;
  }

  // 6. Grid BFS Canvas (Amber)
  renderGridBFSCanvas(step) {
    const grid = step.grid;
    const R = grid.length, C = grid[0].length;

    let cellsHTML = "";
    for (let r = 0; r < R; r++) {
      for (let c = 0; c < C; c++) {
        const val = grid[r][c];
        let cellClass = "grid-node-cell";
        let cellContent = "";
        if (val === 2) {
          cellClass += " rotten-orange";
          cellContent = "●";
        } else if (val === 1) {
          cellClass += " fresh-orange";
          cellContent = "○";
        } else {
          cellClass += " empty-space";
          cellContent = "·";
        }

        cellsHTML += `<div class="${cellClass}">${cellContent}</div>`;
      }
    }

    this.dom.canvasDisplayStage.innerHTML = `
      <div style="display:flex; flex-direction:column; align-items:center; gap:1.25rem;">
        <div class="grid-stage-board" style="grid-template-columns: repeat(${C}, 54px);">
          ${cellsHTML}
        </div>
        <div style="font-family:var(--font-mono); font-size:0.85rem; color:var(--text-muted); display:flex; gap:1.5rem;">
          <span>Elapsed Wave: <b style="color:var(--accent-light);">${step.minutes} mins</b></span>
          <span>Unvisited Remaining: <b style="color:#fb7185;">${step.freshCount}</b></span>
        </div>
      </div>
    `;
  }

  // 8. Topological Sort Canvas (Amber)
  renderTopologicalSortCanvas(step) {
    const num = step.numCourses;
    const inDegree = step.inDegree;
    const queue = step.queue;
    const topoOrder = step.topoOrder;

    const cardsHTML = Array.from({ length: num }, (_, i) => {
      const isDone = topoOrder.includes(i);
      const isCurrent = (i === step.activeNode);
      const isInQueue = queue.includes(i);
      const deg = inDegree[i];

      let cardClass = "topo-course-card";
      if (isCurrent) cardClass += " active-processing";
      else if (isDone) cardClass += " completed-course";
      else if (isInQueue) cardClass += " in-queue";

      let statusText = "Pending";
      if (isDone) statusText = "Completed ✓";
      else if (isCurrent) statusText = "Active ⚡";
      else if (isInQueue) statusText = "In Queue";

      return `
        <div class="${cardClass}">
          <span class="course-id-label">Course ${i}</span>
          <span class="in-degree-badge ${deg === 0 ? 'zero' : ''}">In-Degree: ${deg}</span>
          <span style="font-size:0.65rem; color:${isDone ? 'var(--accent-emerald)' : (isCurrent ? 'var(--accent-light)' : 'var(--text-dim)')};">${statusText}</span>
        </div>
      `;
    }).join("");

    const queuePills = queue.length > 0
      ? queue.map(c => `<span class="topo-course-pill">Course ${c}</span>`).join("")
      : `<span style="font-size:0.8rem; color:var(--text-dim); font-family:var(--font-mono);">Empty Queue</span>`;

    const orderPills = topoOrder.length > 0
      ? topoOrder.map((c, idx) => `<span class="topo-course-pill" style="border-color:var(--accent-emerald); color:var(--accent-emerald);">#${idx + 1}: Course ${c}</span>`).join("<span style='color:var(--text-dim); font-family:var(--font-mono);'>→</span>")
      : `<span style="font-size:0.8rem; color:var(--text-dim); font-family:var(--font-mono);">None yet</span>`;

    this.dom.canvasDisplayStage.innerHTML = `
      <div class="topo-sort-stage">
        <div class="topo-graph-grid">${cardsHTML}</div>
        <div class="topo-rack-row">
          <span class="topo-rack-label">BFS Worklist:</span>
          <div class="topo-pill-track">${queuePills}</div>
        </div>
        <div class="topo-rack-row">
          <span class="topo-rack-label">Resolved Order:</span>
          <div class="topo-pill-track">${orderPills}</div>
        </div>
      </div>
    `;
  }

  // 9. Merge Intervals Canvas (Amber)
  renderMergeIntervalsCanvas(step) {
    const all = step.intervals;
    const merged = step.merged;
    const maxTime = Math.max(...all.map(i => i[1]), 20);

    const mergedRows = merged.map(inter => {
      const leftPct = (inter[0] / maxTime) * 100;
      const widthPct = ((inter[1] - inter[0]) / maxTime) * 100;
      return `
        <div class="timeline-row">
          <span style="font-family:var(--font-mono); font-size:0.75rem; width:54px; color:var(--accent-light);">Merged</span>
          <div class="timeline-groove">
            <div class="timeline-bar-span" style="left:${leftPct}%; width:${widthPct}%;">
              [${inter[0]}, ${inter[1]}]
            </div>
          </div>
        </div>
      `;
    }).join("");

    this.dom.canvasDisplayStage.innerHTML = `
      <div class="timeline-track-suite">
        <div style="font-size:0.75rem; color:var(--text-dim); font-family:var(--font-mono); text-transform:uppercase; margin-bottom:0.4rem;">Contiguous Interval Union:</div>
        ${mergedRows || '<div style="color:var(--text-dim); font-size:0.85rem;">Processing segments...</div>'}
      </div>
    `;
  }

  // 10. Top 'K' Heap Canvas (Amber)
  renderTopKHeapCanvas(step) {
    const count = step.count;
    const heap = step.heap;
    const k = step.k;
    const evicted = step.evicted;
    const activeKey = step.activeKey;

    const maxFreq = Math.max(...Object.values(count), 1);

    const freqCardsHTML = Object.entries(count).map(([val, freq]) => {
      const isActive = (Number(val) === activeKey);
      const pct = Math.round((freq / maxFreq) * 100);
      return `
        <div class="freq-entry-card ${isActive ? 'active-key' : ''}">
          <span style="font-size:1.05rem; font-weight:700; color:var(--text-main);">${val}</span>
          <span style="font-size:0.65rem; color:var(--text-dim);">${freq} occurrence(s)</span>
          <div style="width:100%; height:4px; background:rgba(255,255,255,0.06); border-radius:2px; margin-top:2px; overflow:hidden;">
            <div style="width:${pct}%; height:100%; background:linear-gradient(90deg, #f59e0b, #fbbf24);"></div>
          </div>
        </div>
      `;
    }).join("");

    const heapNodesHTML = heap.map((item, idx) => {
      const isRoot = (idx === 0);
      return `
        <div class="heap-node-pill ${isRoot ? 'min-root' : ''}">
          <span>Val: <b>${item}</b></span>
          <span style="font-size:0.7rem; opacity:0.8;">(${count[item]}x)</span>
          ${isRoot ? '<span style="font-size:0.6rem; padding:1px 4px; background:#fb7185; color:#090c13; border-radius:3px; font-weight:800;">ROOT</span>' : ''}
        </div>
      `;
    }).join("");

    const evictionNotice = evicted !== null
      ? `<div class="eviction-notice">⏏ Evicted Minimum Root: Value <b>${evicted}</b> (Freq ${count[evicted]}) — Exceeded capacity K=${k}</div>`
      : "";

    this.dom.canvasDisplayStage.innerHTML = `
      <div class="top-k-stage">
        <div>
          <div style="font-size:0.75rem; font-family:var(--font-mono); color:var(--text-dim); margin-bottom:0.4rem; text-align:center; text-transform:uppercase;">Frequency Distribution Map:</div>
          <div class="freq-cards-row">${freqCardsHTML}</div>
        </div>
        <div class="heap-chamber-suite">
          <div style="font-size:0.75rem; font-family:var(--font-mono); color:var(--text-dim); text-transform:uppercase;">Min-Heap Chamber (Capacity K = ${k}):</div>
          <div class="heap-items-rack">${heapNodesHTML || '<span style="color:var(--text-dim); font-size:0.85rem; font-family:var(--font-mono);">Heap Empty</span>'}</div>
          ${evictionNotice}
        </div>
      </div>
    `;
  }

  // 11. Dynamic Programming Canvas (Amber)
  renderDynamicProgrammingCanvas(step) {
    const dp = step.dp;
    const amount = step.amount;
    const currentAmt = step.currentAmount;
    const currentCoin = step.currentCoin;
    const lookupIdx = step.lookupIdx;
    const coins = step.coins;

    const cellsHTML = dp.map((val, amt) => {
      const isCurrent = (amt === currentAmt);
      const isLookup = (amt === lookupIdx);
      const isImproved = isCurrent && step.isImproved;

      let cellClass = "dp-cell-item";
      if (isImproved) cellClass += " improved-cell";
      else if (isCurrent) cellClass += " current-subproblem";
      else if (isLookup) cellClass += " lookup-subproblem";

      const displayVal = val > amount ? "∞" : val;

      return `
        <div class="${cellClass}">
          <span class="dp-cell-amt">a=${amt}</span>
          <span class="dp-cell-val">${displayVal}</span>
          ${isCurrent ? '<span style="position:absolute; bottom:-16px; font-size:0.6rem; color:var(--accent-light); font-weight:700;">CURR</span>' : ''}
          ${isLookup ? '<span style="position:absolute; top:-16px; font-size:0.6rem; color:var(--accent-cyan); font-weight:700;">LOOKUP</span>' : ''}
        </div>
      `;
    }).join("");

    const coinChipsHTML = coins.map(c => `
      <span class="kw-tag" style="${c === currentCoin ? 'background:var(--accent); color:#090c13; font-weight:700;' : ''}">
        🪙 Coin ${c}
      </span>
    `).join("");

    let bannerHTML = "";
    if (currentCoin !== null && lookupIdx !== null) {
      bannerHTML = `
        <div class="dp-computation-banner">
          <span>Subproblem: <b>dp[${currentAmt}]</b></span>
          <span>·</span>
          <span>Test Coin: <b>${currentCoin}</b></span>
          <span>·</span>
          <span>Lookup: <b>dp[${lookupIdx}] = ${step.lookupVal}</b></span>
          <span>·</span>
          <span>Candidate: <b>1 + ${step.lookupVal} = ${step.candidateVal}</b></span>
          <span>·</span>
          <span style="color:${step.isImproved ? 'var(--accent-emerald)' : 'var(--text-dim)'}; font-weight:700;">
            ${step.isImproved ? '✓ Improved!' : 'No change'}
          </span>
        </div>
      `;
    } else {
      bannerHTML = `
        <div class="dp-computation-banner">
          <span>Available Denominations: ${coinChipsHTML}</span>
        </div>
      `;
    }

    this.dom.canvasDisplayStage.innerHTML = `
      <div class="dp-table-stage">
        <div class="dp-cells-track">${cellsHTML}</div>
        ${bannerHTML}
      </div>
    `;
  }

  renderSynchronizedCode(codeSnippet, activeLine) {
    const lines = codeSnippet.split("\n");
    this.dom.codeLinesScrollbox.innerHTML = lines.map((lineText, idx) => {
      const lineNum = idx + 1;
      const isActive = lineNum === activeLine;
      return `
        <div class="code-row ${isActive ? 'active-line' : ''}">
          <span class="line-num">${lineNum}</span>
          <span class="line-code-text">${this.escapeHTML(lineText)}</span>
        </div>
      `;
    }).join("");

    const activeEl = this.dom.codeLinesScrollbox.querySelector(".code-row.active-line");
    if (activeEl) {
      activeEl.scrollIntoView({ block: "nearest", behavior: "smooth" });
    }
  }

  renderReferenceGrid(query = "") {
    const q = query.trim().toLowerCase();
    const filtered = q === ""
      ? this.patterns
      : this.patterns.filter(p => {
          const matchName = p.name.toLowerCase().includes(q);
          const matchCat = p.category.toLowerCase().includes(q);
          const matchOverview = p.overview.toLowerCase().includes(q);
          const matchKeywords = p.signalKeywords.some(kw => kw.toLowerCase().includes(q));
          const matchCompany = p.curatedProblems.some(cp => cp.company.toLowerCase().includes(q) || cp.name.toLowerCase().includes(q));
          const matchComplexity = p.complexity.toLowerCase().includes(q);
          return matchName || matchCat || matchOverview || matchKeywords || matchCompany || matchComplexity;
        });

    if (this.dom.referenceCountLabel) {
      this.dom.referenceCountLabel.innerText = `Showing ${filtered.length} / ${this.patterns.length} patterns`;
    }

    if (filtered.length === 0) {
      this.dom.referenceCardsGrid.innerHTML = `
        <div style="grid-column: 1 / -1; text-align:center; padding:3rem; color:var(--text-dim); font-family:var(--font-mono);">
          No patterns found matching "${query}". Try searching "Google", "BFS", "Stack", "O(1)", or "Tree".
        </div>
      `;
      return;
    }

    this.dom.referenceCardsGrid.innerHTML = filtered.map(p => `
      <div class="matrix-entry-card">
        <div class="matrix-top-row">
          <span class="status-tag">${p.category}</span>
          <span style="font-family:var(--font-mono); font-size:0.75rem; color:var(--accent-light);">${p.badge}</span>
        </div>
        <h3 class="matrix-pattern-title">${p.name}</h3>
        <p class="matrix-overview-text">${p.overview}</p>
        
        <div class="keywords-tag-row">
          ${p.signalKeywords.map(kw => `<span class="kw-tag">${kw}</span>`).join("")}
        </div>

        <p style="font-size:0.8rem; color:var(--text-muted); line-height:1.5; margin-bottom:1rem;">
          <b style="color:var(--accent-light);">Invariant Proof:</b> ${p.invariantProof}
        </p>

        <div class="matrix-card-bottom">
          <span>${p.complexity}</span>
          <button class="matrix-load-btn" onclick="window.studioApp.loadPattern('${p.id}'); window.scrollTo({top:0, behavior:'smooth'});">
            Launch in Studio ⚡
          </button>
        </div>
      </div>
    `).join("");
  }

  togglePlay() {
    if (this.isPlaying) {
      this.pause();
    } else {
      this.play();
    }
  }

  play() {
    if (this.currentStepIdx >= this.currentSteps.length - 1) {
      this.currentStepIdx = 0;
    }
    this.isPlaying = true;
    this.dom.playText.innerText = "Pause";
    this.dom.playIcon.innerText = "⏸";
    const delay = Math.round(900 / this.speedMultiplier);
    this.playInterval = setInterval(() => {
      if (this.currentStepIdx < this.currentSteps.length - 1) {
        this.currentStepIdx++;
        this.renderStep();
      } else {
        this.pause();
      }
    }, delay);
  }

  pause() {
    this.isPlaying = false;
    this.dom.playText.innerText = "Play";
    this.dom.playIcon.innerText = "▶";
    if (this.playInterval) {
      clearInterval(this.playInterval);
      this.playInterval = null;
    }
  }

  nextStep() {
    this.pause();
    if (this.currentStepIdx < this.currentSteps.length - 1) {
      this.currentStepIdx++;
      this.renderStep();
    }
  }

  prevStep() {
    this.pause();
    if (this.currentStepIdx > 0) {
      this.currentStepIdx--;
      this.renderStep();
    }
  }

  goToStep(idx) {
    this.currentStepIdx = Math.max(0, Math.min(idx, this.currentSteps.length - 1));
    this.renderStep();
  }

  resetSimulation() {
    this.pause();
    this.currentStepIdx = 0;
    this.renderStep();
  }

  // ============================================================================
  // Educational Pillar 1: Interactive Intuition Challenge Mode
  // ============================================================================
  toggleChallengeMode() {
    this.challengeMode = !this.challengeMode;
    if (this.dom.challengeModeBtn) {
      this.dom.challengeModeBtn.classList.toggle("active", this.challengeMode);
    }
    if (this.dom.challengeBtnText) {
      this.dom.challengeBtnText.innerText = this.challengeMode ? "Challenge: ON" : "Challenge: OFF";
    }
    this.showToast(
      this.challengeMode
        ? "Active Prediction Mode ON — Simulation pauses at invariant decision points"
        : "Challenge Mode OFF — Uninterrupted simulation active",
      "info"
    );
    if (this.challengeMode) {
      const step = this.currentSteps[this.currentStepIdx];
      const quiz = this.generateChallengeForStep(this.currentPatternId, step, this.currentStepIdx);
      if (quiz) {
        this.triggerChallenge(quiz, this.currentStepIdx);
      }
    } else {
      this.hideChallengeOverlay();
    }
  }

  updateIntuitionBadge() {
    if (!this.dom.intuitionScoreVal) return;
    const pct = this.intuitionTotal > 0 ? Math.round((this.intuitionCorrect / this.intuitionTotal) * 100) : 100;
    this.dom.intuitionScoreVal.innerText = `${this.intuitionCorrect}/${this.intuitionTotal} (${pct}%)`;
  }

  triggerChallenge(quiz, stepIdx) {
    this.wasPlayingBeforeChallenge = this.isPlaying;
    this.pause();
    this.activeChallenge = quiz;
    this.challengedSteps.add(stepIdx);

    if (!this.dom.challengeCardOverlay) return;

    if (this.dom.challengeStepBadge) {
      this.dom.challengeStepBadge.innerText = `Step ${stepIdx + 1} of ${this.currentSteps.length}`;
    }
    if (this.dom.challengeQuestionTitle) {
      this.dom.challengeQuestionTitle.innerText = quiz.question;
    }
    if (this.dom.challengeContextHint) {
      this.dom.challengeContextHint.innerText = quiz.context || "Select the provably optimal action:";
    }
    if (this.dom.challengeFeedbackBox) {
      this.dom.challengeFeedbackBox.classList.add("hidden");
    }

    if (this.dom.challengeOptionsList) {
      this.dom.challengeOptionsList.innerHTML = quiz.options.map((opt, idx) => `
        <button class="challenge-option-btn" onclick="window.studioApp.handleChallengeAnswer(${idx})">
          <span style="font-family:var(--font-mono); font-weight:700; color:var(--accent-light);">${String.fromCharCode(65 + idx)}.</span>
          <span>${this.escapeHTML(opt.text)}</span>
        </button>
      `).join("");
    }

    this.dom.challengeCardOverlay.classList.remove("hidden");
  }

  handleChallengeAnswer(selectedIdx) {
    if (!this.activeChallenge) return;
    const quiz = this.activeChallenge;
    const buttons = this.dom.challengeOptionsList ? this.dom.challengeOptionsList.querySelectorAll(".challenge-option-btn") : [];
    buttons.forEach((btn, idx) => {
      btn.disabled = true;
      if (quiz.options[idx].correct) {
        btn.classList.add("correct");
      } else if (idx === selectedIdx) {
        btn.classList.add("wrong");
      }
    });

    const isCorrect = quiz.options[selectedIdx] && quiz.options[selectedIdx].correct;
    this.intuitionTotal++;
    if (isCorrect) {
      this.intuitionCorrect++;
      this.sound.playSuccess();
      if (this.dom.feedbackStatus) {
        this.dom.feedbackStatus.innerText = "✓ Correct Invariant Intuition!";
        this.dom.feedbackStatus.className = "feedback-status correct";
      }
    } else {
      this.sound.playError();
      if (this.dom.feedbackStatus) {
        this.dom.feedbackStatus.innerText = "⚠️ Invariant Misprediction";
        this.dom.feedbackStatus.className = "feedback-status wrong";
      }
    }

    this.updateIntuitionBadge();

    if (this.dom.feedbackExplanation) {
      this.dom.feedbackExplanation.innerText = quiz.explanation;
    }
    if (this.dom.challengeFeedbackBox) {
      this.dom.challengeFeedbackBox.classList.remove("hidden");
    }
  }

  continueAfterChallenge() {
    this.hideChallengeOverlay();
    if (this.wasPlayingBeforeChallenge) {
      this.wasPlayingBeforeChallenge = false;
      this.play();
    } else if (this.currentStepIdx < this.currentSteps.length - 1) {
      this.nextStep();
    }
  }

  hideChallengeOverlay() {
    if (this.dom.challengeCardOverlay) {
      this.dom.challengeCardOverlay.classList.add("hidden");
    }
    this.activeChallenge = null;
  }

  generateChallengeForStep(patternId, step, stepIdx) {
    if (!step) return null;

    if (patternId === "two-pointers") {
      if (step.type === "evaluate" && step.heights && step.left < step.right) {
        const hL = step.heights[step.left];
        const hR = step.heights[step.right];
        const isLeftBottleneck = hL < hR;
        return {
          question: `Boundary Evaluation: height[L=${step.left}] (${hL}) vs height[R=${step.right}] (${hR}). Which pointer must advance to preserve the possibility of a larger area?`,
          context: "Recall the core invariant: Area = min(h[L], h[R]) * (R - L). Width shrinks by 1 on every iteration.",
          options: [
            {
              text: isLeftBottleneck
                ? `Advance Left (left++) because height[${step.left}] (${hL}) is the limiting bottleneck.`
                : `Advance Right (right--) because height[${step.right}] (${hR}) is the limiting bottleneck.`,
              correct: true
            },
            {
              text: isLeftBottleneck
                ? `Advance Right (right--) to preserve the shorter left boundary.`
                : `Advance Left (left++) to preserve the shorter right boundary.`,
              correct: false
            },
            {
              text: "Advance both Left and Right pointers simultaneously.",
              correct: false
            }
          ],
          explanation: `Since height[${isLeftBottleneck ? step.left : step.right}] is strictly the bottleneck, keeping it with any inner boundary would yield a strictly smaller width without being able to exceed its limiting height. Eliminating it is mathematically optimal.`
        };
      }
    } else if (patternId === "sliding-window") {
      if (step.type === "expand" && step.isDuplicate) {
        return {
          question: `Duplicate '${step.char}' encountered! It was previously seen at index ${step.prevIdx}. Where must Left jump?`,
          context: "The invariant requires all characters in [Left..Right] to be unique.",
          options: [
            {
              text: `Jump Left directly to index ${step.prevIdx + 1} (prevIdx + 1) to restore window validity in O(1).`,
              correct: true
            },
            {
              text: "Increment Left by 1 (left++) and linearly scan forward.",
              correct: false
            },
            {
              text: "Reset Left back to index 0.",
              correct: false
            }
          ],
          explanation: `Jumping left directly past the duplicate (to index ${step.prevIdx + 1}) guarantees that the substring becomes unique immediately, avoiding costly O(N) linear shrinking.`
        };
      } else if (step.type === "expand") {
        return {
          question: `Window expanded to [Left=${step.left}..Right=${step.right}]. How is current window length computed?`,
          context: "Consider inclusive array index boundaries.",
          options: [
            { text: `Right - Left + 1 = ${step.right - step.left + 1}`, correct: true },
            { text: `Right - Left = ${step.right - step.left}`, correct: false },
            { text: "HashSet.size() * 2", correct: false }
          ],
          explanation: "For inclusive 0-indexed intervals [L..R], the number of elements is always (R - L + 1)."
        };
      }
    } else if (patternId === "monotonic-stack") {
      if (step.type === "pop") {
        return {
          question: `Today's temp (${step.temp}°) is warmer than stack top day ${step.poppedIdx} (${step.topTemp}°). What invariant action is taken?`,
          context: "Monotonic decreasing stack tracks unresolved days awaiting a warmer day.",
          options: [
            {
              text: `Pop index ${step.poppedIdx} and record wait duration (${step.currentIndex} - ${step.poppedIdx}) = ${step.span} days in result array.`,
              correct: true
            },
            {
              text: "Push today's temperature directly without popping.",
              correct: false
            },
            {
              text: "Discard today's temperature and move to the next day.",
              correct: false
            }
          ],
          explanation: `Because today's temp is strictly warmer than the top of the monotonic decreasing stack, the wait time for day ${step.poppedIdx} is resolved. We pop it and record the span.`
        };
      } else if (step.type === "push") {
        return {
          question: `Why does the monotonic stack store array indices (e.g. index ${step.currentIndex}) rather than raw temperatures?`,
          context: "Consider what metrics need to be calculated upon resolution.",
          options: [
            { text: "Indices encode both time/position (for day span calculation) and temperature lookup in O(1).", correct: true },
            { text: "Stacks in Java cannot store integer values.", correct: false },
            { text: "Indices consume O(1) memory while temperatures consume O(N).", correct: false }
          ],
          explanation: "Storing indices preserves the exact day of occurrence, enabling distance calculation (i - prev_i) while still allowing O(1) temperature queries via temperatures[i]."
        };
      }
    } else if (patternId === "binary-search") {
      if (step.type === "compare") {
        const leftSorted = step.nums && step.nums[step.low] <= step.midVal;
        return {
          question: `Rotated array range [${step.low}..${step.high}] with mid=${step.mid} (${step.midVal}) and target=${step.target}. Which half is guaranteed sorted?`,
          context: "In a rotated sorted array, splitting at mid always preserves monotonic order in at least one half.",
          options: [
            {
              text: leftSorted
                ? `Left half [${step.low}..${step.mid}] is sorted because nums[low] <= nums[mid].`
                : `Right half [${step.mid}..${step.high}] is sorted because nums[mid] <= nums[high].`,
              correct: true
            },
            {
              text: leftSorted
                ? `Right half [${step.mid}..${step.high}] is guaranteed sorted.`
                : `Left half [${step.low}..${step.mid}] is guaranteed sorted.`,
              correct: false
            },
            {
              text: "Neither half is sorted because the array has a rotation point.",
              correct: false
            }
          ],
          explanation: `At least one half of a rotated sorted array is strictly monotonic. Checking nums[low] <= nums[mid] confirms whether the left or right subsegment is sorted.`
        };
      }
    } else if (patternId === "fast-slow-pointers") {
      if (step.type === "move") {
        return {
          question: "Slow moves 1 step per tick, Fast moves 2 steps per tick. Why does Fast never jump over Slow inside a cycle?",
          context: "Analyze the relative velocity in modular arithmetic modulo cycle length C.",
          options: [
            { text: "The relative gap shrinks by exactly (2 - 1) = 1 node per iteration, guaranteeing a collision without skipping.", correct: true },
            { text: "Slow stops moving once Fast enters the cycle.", correct: false },
            { text: "Fast reverses direction when it gets close to Slow.", correct: false }
          ],
          explanation: "In discrete modular steps, decreasing distance by 1 per tick cannot jump over 0. Fast and Slow are provably guaranteed to collide."
        };
      }
    } else if (patternId === "tree-traversal") {
      if (step.type === "level_start") {
        return {
          question: "Why is 'int levelSize = queue.size()' captured before the inner loop in level order traversal?",
          context: "Consider what happens when child nodes are added to the queue.",
          options: [
            { text: "Enqueuing children during the loop increases queue.size(); capturing it freezes the current level count.", correct: true },
            { text: "To prevent Java ConcurrentModificationException on the queue.", correct: false },
            { text: "To optimize time complexity from O(N^2) to O(N).", correct: false }
          ],
          explanation: "Freezing queue.size() before iterating ensures that exactly the nodes belonging to the current level are processed before advancing to the next level."
        };
      }
    } else if (patternId === "grid-bfs") {
      if (step.type === "minute_tick") {
        return {
          question: "Why is Multi-Source BFS strictly required instead of DFS for Rotting Oranges?",
          context: "Consider how contagion spreads in real time across the matrix.",
          options: [
            { text: "Multi-source BFS expands in uniform concentric waves, modeling simultaneous parallel infection across all sources.", correct: true },
            { text: "DFS cannot visit adjacent cells in 4 cardinal directions.", correct: false },
            { text: "DFS requires exponential O(4^(R*C)) memory.", correct: false }
          ],
          explanation: "All rotten oranges infect adjacent fresh oranges simultaneously. Multi-source BFS processes each minute wave tier-by-tier."
        };
      }
    } else if (patternId === "topological-sort") {
      if (step.type === "pop_queue") {
        return {
          question: `Course ${step.course} is dequeued and added to schedule. What update is made to its dependent neighbors?`,
          context: "Kahn's Algorithm simulates fulfilling prerequisite constraints.",
          options: [
            { text: "Decrement in-degree of all outgoing neighbors; if any neighbor's in-degree reaches 0, enqueue it.", correct: true },
            { text: "Immediately mark all neighbor courses as scheduled.", correct: false },
            { text: "Delete all edges from the adjacency list.", correct: false }
          ],
          explanation: "In-degree represents unmet prerequisites. When course completion reduces a neighbor's in-degree to 0, that neighbor is now unlocked and eligible to take."
        };
      }
    } else if (patternId === "merge-intervals") {
      if (step.type === "compare" && step.curr && step.next) {
        const overlaps = step.next[0] <= step.curr[1];
        return {
          question: `Interval A [${step.curr[0]}, ${step.curr[1]}] and Interval B [${step.next[0]}, ${step.next[1]}]. Do they overlap?`,
          context: "Intervals are pre-sorted by start time: next.start >= curr.start.",
          options: [
            {
              text: overlaps
                ? `Yes: next.start (${step.next[0]}) <= curr.end (${step.curr[1]}), so merge into [${step.curr[0]}, max(${step.curr[1]}, ${step.next[1]})].`
                : `No: next.start (${step.next[0]}) > curr.end (${step.curr[1]}), so commit current and advance.`,
              correct: true
            },
            {
              text: overlaps
                ? "No: Intervals with distinct start times never overlap."
                : "Yes: All adjacent intervals in a list must be merged.",
              correct: false
            },
            { text: "Cannot determine without knowing total interval count.", correct: false }
          ],
          explanation: `Since intervals are pre-sorted by start time, next.start is at least curr.start. Overlap exists if and only if next.start <= curr.end.`
        };
      }
    } else if (patternId === "top-k-heap") {
      if (step.type === "check_capacity") {
        return {
          question: "Why use a Min-Heap of size K rather than a Max-Heap to track the Top K Frequent elements?",
          context: "Consider the cost of keeping the K largest elements over a stream of N unique items.",
          options: [
            { text: "The Min-Heap root is the smallest among the top K; evicting it when size > K runs in O(log K).", correct: true },
            { text: "PriorityQueue in Java only supports Min-Heap ordering.", correct: false },
            { text: "Min-Heap uses O(1) space while Max-Heap uses O(N) space.", correct: false }
          ],
          explanation: "A Min-Heap capped at size K guarantees that the smallest candidate is at the root. Evicting the root takes O(log K), yielding total runtime of O(N log K)."
        };
      }
    } else if (patternId === "dynamic-programming") {
      if (step.type === "try_coin") {
        return {
          question: `Target amount ${step.currentAmount} using coin ${step.coin}. What is the optimal subproblem recurrence?`,
          context: "Dynamic programming builds solutions to larger amounts using previously solved smaller amounts.",
          options: [
            { text: "dp[amount] = Math.min(dp[amount], dp[amount - coin] + 1)", correct: true },
            { text: "dp[amount] = dp[amount - coin] * coin", correct: false },
            { text: "dp[amount] = dp[amount - 1] + dp[amount - 2]", correct: false }
          ],
          explanation: "Using 'coin' requires 1 coin plus the optimal number of coins needed for the subproblem (amount - coin). We minimize across all available coins."
        };
      }
    }

    // Generic fallback quiz for step
    const pattern = this.patterns.find(p => p.id === patternId);
    if (stepIdx === 0 && pattern) {
      return {
        question: `What fundamental invariant governs the ${pattern.name} pattern?`,
        context: "Review the theoretical foundation of this algorithm.",
        options: [
          { text: pattern.invariantProof.slice(0, 100) + "...", correct: true },
          { text: "Exhaustive brute force iteration in exponential time.", correct: false },
          { text: "Randomized guessing until validation succeeds.", correct: false }
        ],
        explanation: pattern.invariantProof
      };
    }

    return null;
  }

  // ============================================================================
  // Educational Pillar 2: Whiteboard Dry-Run Trace Table
  // ============================================================================
  getDryRunColumns(patternId) {
    switch (patternId) {
      case "two-pointers":
        return ["Step", "L", "R", "H[L]", "H[R]", "Width", "Limiting H", "Area", "Max Area", "Decision"];
      case "sliding-window":
        return ["Step", "L", "R", "Char", "Window", "Len", "Max Len", "Dup?", "Action"];
      case "monotonic-stack":
        return ["Step", "Day", "Temp", "Stack Top", "Stack State", "Pop?", "Result Array", "Action"];
      case "binary-search":
        return ["Step", "Low", "High", "Mid", "nums[Mid]", "Target", "Comparison", "Action"];
      case "fast-slow-pointers":
        return ["Step", "Slow Idx", "Fast Idx", "Slow Val", "Fast Val", "Met?", "Action"];
      case "tree-traversal":
        return ["Step", "Node", "Queue Size", "Level Items", "Result Levels", "Action"];
      case "grid-bfs":
        return ["Step", "Minute", "Queue Size", "Fresh Left", "Infected", "Action"];
      case "topological-sort":
        return ["Step", "Course", "In-Degrees", "Queue (InDeg 0)", "Processed", "Schedule", "Action"];
      case "merge-intervals":
        return ["Step", "Curr [S,E]", "Next [S,E]", "Overlap?", "Merged List", "Action"];
      case "top-k-heap":
        return ["Step", "Num", "Freq", "Heap Size", "Min Root", "Top K List", "Action"];
      case "dynamic-programming":
        return ["Step", "Amount i", "Coin", "dp[i - coin]", "dp[i] (Min Coins)", "Action"];
      default:
        return ["Step", "Title", "Explanation"];
    }
  }

  getDryRunRowData(patternId, s, idx) {
    switch (patternId) {
      case "two-pointers":
        return [
          idx + 1,
          s.left,
          s.right,
          s.heights ? s.heights[s.left] : "-",
          s.heights ? s.heights[s.right] : "-",
          s.width,
          s.h,
          s.currArea,
          s.maxArea,
          s.eliminatedPointer ? `Eliminate ${s.eliminatedPointer}` : s.title
        ];
      case "sliding-window":
        return [
          idx + 1,
          s.left,
          s.right,
          s.char || "-",
          s.currSub || '""',
          s.currSub ? s.currSub.length : 0,
          s.maxLen,
          s.isDuplicate ? "YES" : "NO",
          s.title
        ];
      case "monotonic-stack":
        return [
          idx + 1,
          s.currentIndex >= 0 ? s.currentIndex : "End",
          s.temp !== undefined ? `${s.temp}°` : "-",
          s.topTemp !== undefined ? `${s.topTemp}°` : "Empty",
          s.stack ? `[${s.stack.join(",")}]` : "[]",
          s.type === "pop" ? "POP" : (s.type === "push" ? "PUSH" : "-"),
          s.result ? `[${s.result.join(",")}]` : "[]",
          s.title
        ];
      case "binary-search":
        return [
          idx + 1,
          s.low,
          s.high,
          s.mid,
          s.midVal !== undefined ? s.midVal : "-",
          s.target,
          s.comparison || "-",
          s.title
        ];
      case "fast-slow-pointers":
        return [
          idx + 1,
          s.slow,
          s.fast,
          s.slowVal !== undefined ? s.slowVal : "-",
          s.fastVal !== undefined ? s.fastVal : "-",
          s.met ? "YES" : "NO",
          s.title
        ];
      case "tree-traversal":
        return [
          idx + 1,
          s.currentNode !== undefined ? s.currentNode : "-",
          s.queue ? s.queue.length : "-",
          s.currentLevel ? `[${s.currentLevel.join(",")}]` : "[]",
          s.result ? JSON.stringify(s.result) : "[]",
          s.title
        ];
      case "grid-bfs":
        return [
          idx + 1,
          s.minute !== undefined ? s.minute : "-",
          s.queue ? s.queue.length : "-",
          s.freshCount !== undefined ? s.freshCount : "-",
          s.infectedThisRound !== undefined ? s.infectedThisRound : "-",
          s.title
        ];
      case "topological-sort":
        return [
          idx + 1,
          s.course !== undefined ? s.course : "-",
          s.inDegrees ? JSON.stringify(s.inDegrees) : "-",
          s.queue ? `[${s.queue.join(",")}]` : "[]",
          s.processedCount !== undefined ? s.processedCount : "-",
          s.order ? `[${s.order.join(",")}]` : "[]",
          s.title
        ];
      case "merge-intervals":
        return [
          idx + 1,
          s.curr ? `[${s.curr.join(",")}]` : "-",
          s.next ? `[${s.next.join(",")}]` : "-",
          s.overlap !== undefined ? (s.overlap ? "YES" : "NO") : "-",
          s.merged ? JSON.stringify(s.merged) : "[]",
          s.title
        ];
      case "top-k-heap":
        return [
          idx + 1,
          s.num !== undefined ? s.num : "-",
          s.freq !== undefined ? s.freq : "-",
          s.heap ? s.heap.length : "-",
          s.minRoot !== undefined ? JSON.stringify(s.minRoot) : "-",
          s.topK ? `[${s.topK.join(",")}]` : "[]",
          s.title
        ];
      case "dynamic-programming":
        return [
          idx + 1,
          s.currentAmount !== undefined ? s.currentAmount : "-",
          s.coin !== undefined ? s.coin : "-",
          s.prevDP !== undefined ? s.prevDP : "-",
          s.dp && s.currentAmount !== undefined ? s.dp[s.currentAmount] : "-",
          s.title
        ];
      default:
        return [idx + 1, s.title, s.explanation];
    }
  }

  renderDryRunTable() {
    if (!this.dom.dryrunTableWrapper || this.currentSteps.length === 0) return;
    const cols = this.getDryRunColumns(this.currentPatternId);

    if (this.dom.dryrunStepCountChip) {
      this.dom.dryrunStepCountChip.innerText = `${this.currentSteps.length} Transitions`;
    }

    const rowsHtml = this.currentSteps.map((step, idx) => {
      const rowData = this.getDryRunRowData(this.currentPatternId, step, idx);
      const isActive = idx === this.currentStepIdx;
      return `
        <tr class="dryrun-row ${isActive ? 'active-step' : ''}" data-step="${idx}" onclick="window.studioApp.goToStep(${idx})">
          ${rowData.map(cell => `<td>${this.escapeHTML(String(cell))}</td>`).join("")}
        </tr>
      `;
    }).join("");

    this.dom.dryrunTableWrapper.innerHTML = `
      <table class="dryrun-table">
        <thead>
          <tr>
            ${cols.map(c => `<th>${c}</th>`).join("")}
          </tr>
        </thead>
        <tbody>
          ${rowsHtml}
        </tbody>
      </table>
    `;

    this.updateDryRunActiveStep();
  }

  updateDryRunActiveStep() {
    if (!this.dom.dryrunTableWrapper) return;
    const rows = this.dom.dryrunTableWrapper.querySelectorAll(".dryrun-row");
    rows.forEach((r, idx) => {
      const active = idx === this.currentStepIdx;
      r.classList.toggle("active-step", active);
      if (active && this.activeTab === "dryrun") {
        r.scrollIntoView({ block: "nearest", behavior: "smooth" });
      }
    });
  }

  generateMarkdownTable() {
    const cols = this.getDryRunColumns(this.currentPatternId);
    const pattern = this.patterns.find(p => p.id === this.currentPatternId);
    let md = `### Whiteboard Variable Trace: ${pattern ? pattern.name : this.currentPatternId}\n\n`;
    md += `| ${cols.join(" | ")} |\n`;
    md += `| ${cols.map(() => "---").join(" | ")} |\n`;
    this.currentSteps.forEach((step, idx) => {
      const rowData = this.getDryRunRowData(this.currentPatternId, step, idx);
      md += `| ${rowData.map(v => String(v).replace(/\|/g, "\\|")).join(" | ")} |\n`;
    });
    return md;
  }

  handleCopyMarkdownTable() {
    const md = this.generateMarkdownTable();
    navigator.clipboard.writeText(md);
    const prev = this.dom.copyMarkdownTableBtn.innerText;
    this.dom.copyMarkdownTableBtn.innerText = "Copied Table!";
    this.showToast("Whiteboard trace table copied as Markdown!", "success");
    setTimeout(() => {
      if (this.dom.copyMarkdownTableBtn) this.dom.copyMarkdownTableBtn.innerText = prev;
    }, 1800);
  }

  // ============================================================================
  // Educational Pillar 3: Tabbed Inspector & Interview Playbook
  // ============================================================================
  switchTab(tabId) {
    this.activeTab = tabId;
    document.querySelectorAll(".inspector-tab-btn").forEach(btn => {
      btn.classList.toggle("active", btn.getAttribute("data-tab") === tabId);
    });

    const paneMap = {
      code: "tabPaneCode",
      dryrun: "tabPaneDryrun",
      playbook: "tabPanePlaybook",
      complexity: "tabPaneComplexity"
    };

    Object.keys(paneMap).forEach(key => {
      const pane = document.getElementById(paneMap[key]);
      if (pane) {
        pane.classList.toggle("active", key === tabId);
      }
    });

    if (tabId === "dryrun") {
      this.renderDryRunTable();
    }
  }

  renderPlaybookAndComplexity(pattern) {
    if (this.dom.playbookCheatCode) {
      this.dom.playbookCheatCode.innerText = pattern.cheatCode || "Analyze input bounds";
    }
    if (this.dom.playbookClarifyingList) {
      const qs = pattern.clarifyingQuestions || [];
      this.dom.playbookClarifyingList.innerHTML = qs.map(q => `<li>${this.escapeHTML(q)}</li>`).join("");
    }
    if (this.dom.playbookTrapsList) {
      const traps = pattern.interviewTraps || [];
      this.dom.playbookTrapsList.innerHTML = traps.map(t => `<li>${this.escapeHTML(t)}</li>`).join("");
    }
    if (this.dom.proofBodyText) {
      this.dom.proofBodyText.innerText = pattern.invariantProof || "";
    }
    if (this.dom.deepDiveTimeBadge && pattern.complexityDeepDive) {
      this.dom.deepDiveTimeBadge.innerText = (pattern.complexity.split("·")[0] || "").replace("Time:", "").trim();
    }
    if (this.dom.deepDiveTimeDesc && pattern.complexityDeepDive) {
      this.dom.deepDiveTimeDesc.innerText = pattern.complexityDeepDive.time || "";
    }
    if (this.dom.deepDiveSpaceBadge && pattern.complexityDeepDive) {
      this.dom.deepDiveSpaceBadge.innerText = (pattern.complexity.split("·")[1] || "").replace("Space:", "").trim();
    }
    if (this.dom.deepDiveSpaceDesc && pattern.complexityDeepDive) {
      this.dom.deepDiveSpaceDesc.innerText = pattern.complexityDeepDive.space || "";
    }
  }

  handleCopyStarterTemplate() {
    const pattern = this.patterns.find(p => p.id === this.currentPatternId);
    if (!pattern || !pattern.starterTemplate) return;
    navigator.clipboard.writeText(pattern.starterTemplate);
    const prev = this.dom.copyTemplateBtn.innerText;
    this.dom.copyTemplateBtn.innerText = "Copied Template!";
    this.showToast("Java interview starter boilerplate copied!", "success");
    setTimeout(() => {
      if (this.dom.copyTemplateBtn) this.dom.copyTemplateBtn.innerText = prev;
    }, 1800);
  }

  // ============================================================================
  // Educational Pillar 4: Algorithmic Pattern Diagnostic Wizard
  // ============================================================================
  initWizard() {
    this.renderWizardStep1();
  }

  renderWizardStep1() {
    if (!this.dom.wizardCardStage) return;
    this.dom.wizardCardStage.innerHTML = `
      <div class="wizard-question-box">
        <div class="wizard-step-indicator">
          <span>Step 1 of 2</span> · <span>Input Structure</span>
        </div>
        <h3 class="wizard-question-title">What is your primary Input Data Structure?</h3>
        <div class="wizard-options-grid">
          ${WIZARD_DATA.structures.map(st => `
            <button class="wizard-option-card" onclick="window.studioApp.handleWizardStep1('${st.id}')">
              <div class="wizard-option-title">
                <span>${st.icon}</span> <span>${st.title}</span>
              </div>
              <p class="wizard-option-desc">${st.desc}</p>
            </button>
          `).join("")}
        </div>
      </div>
    `;
  }

  handleWizardStep1(structureId) {
    const struct = WIZARD_DATA.structures.find(s => s.id === structureId);
    if (!struct || !this.dom.wizardCardStage) return;

    this.dom.wizardCardStage.innerHTML = `
      <div class="wizard-question-box">
        <div class="wizard-step-indicator">
          <button class="action-chip-btn" onclick="window.studioApp.renderWizardStep1()" style="padding:0.15rem 0.5rem;">← Back</button>
          <span>Step 2 of 2</span> · <span>${struct.title} Goal</span>
        </div>
        <h3 class="wizard-question-title">What is your core Problem Goal or Constraint?</h3>
        <div class="wizard-options-grid">
          ${struct.goals.map((g, idx) => `
            <button class="wizard-option-card" onclick="window.studioApp.handleWizardStep2('${structureId}', ${idx})">
              <div class="wizard-option-title">${g.title}</div>
              <p class="wizard-option-desc">${g.desc}</p>
            </button>
          `).join("")}
        </div>
      </div>
    `;
  }

  handleWizardStep2(structureId, goalIdx) {
    const struct = WIZARD_DATA.structures.find(s => s.id === structureId);
    if (!struct || !struct.goals[goalIdx] || !this.dom.wizardCardStage) return;
    const goal = struct.goals[goalIdx];
    const pattern = this.patterns.find(p => p.id === goal.patternId);
    if (!pattern) return;

    this.dom.wizardCardStage.innerHTML = `
      <div class="wizard-result-box">
        <div class="wizard-result-badge">Optimal Pattern Recommendation</div>
        <h3 class="wizard-result-name">${pattern.name}</h3>
        <p class="wizard-result-cue"><b style="color:var(--accent-light);">Why this pattern:</b> ${goal.cue}</p>

        <div class="wizard-result-meta-row">
          <span class="complexity-pill">${pattern.complexity}</span>
          <span class="status-tag">${pattern.category}</span>
          <span class="tool-chip">${pattern.badge}</span>
        </div>

        <div class="keywords-tag-row" style="margin-bottom: 1.25rem;">
          ${pattern.signalKeywords.map(kw => `<span class="kw-tag">${kw}</span>`).join("")}
        </div>

        <div class="wizard-result-actions">
          <button class="play-action-btn" onclick="window.studioApp.loadPattern('${pattern.id}'); window.scrollTo({top: 0, behavior: 'smooth'});">
            ⚡ Launch Pattern in Workbench
          </button>
          <button class="control-step-btn" onclick="window.studioApp.renderWizardStep1()">
            ↺ Diagnose Another Problem
          </button>
        </div>
      </div>
    `;
  }

  escapeHTML(str) {
    return str
      .replace(/&/g, "&amp;")
      .replace(/</g, "&lt;")
      .replace(/>/g, "&gt;");
  }
}

// Bootstrap on DOM ready
if (typeof document !== "undefined" && document.addEventListener) {
  document.addEventListener("DOMContentLoaded", () => {
    window.studioApp = new DSAStudioApp();
  });
}

if (typeof window !== "undefined") {
  window.DSAStudioApp = DSAStudioApp;
  window.SoundFX = SoundFX;
}
if (typeof module !== "undefined" && module.exports) {
  module.exports = { DSAStudioApp, SoundFX };
}
