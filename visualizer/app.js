/**
 * ============================================================================
 * DSA Studio & Algorithmic Workbench Controller
 * ============================================================================
 * Coordinates simulation states, telemetry metrics, synchronized code tracing,
 * and bespoke Amber Canvas rendering.
 */

class DSAStudioApp {
  constructor() {
    this.patterns = PATTERNS_DATA;
    this.currentPatternId = "two-pointers";
    this.currentSteps = [];
    this.currentStepIdx = 0;
    this.isPlaying = false;
    this.playInterval = null;
    this.speedMultiplier = 1.0;

    this.initDOM();
    this.bindEvents();
    this.renderPatternTabs();
    this.renderReferenceGrid();
    this.loadPattern(this.currentPatternId);
  }

  initDOM() {
    this.dom = {
      themeToggle: document.getElementById("themeToggle"),
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
      referenceCardsGrid: document.getElementById("referenceCardsGrid")
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

    // Copy CLI Command
    this.dom.copyCliBtn.addEventListener("click", () => {
      navigator.clipboard.writeText(this.dom.cliCmdLabel.innerText);
      const prev = this.dom.copyCliBtn.innerText;
      this.dom.copyCliBtn.innerText = "Copied!";
      setTimeout(() => { this.dom.copyCliBtn.innerText = prev; }, 1600);
    });

    // Theme Toggle
    this.dom.themeToggle.addEventListener("click", () => {
      const curr = document.documentElement.getAttribute("data-theme") || "dark";
      const target = curr === "dark" ? "light" : "dark";
      document.documentElement.setAttribute("data-theme", target);
    });

    // Keyboard Shortcuts
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
  }

  formatInputBox(input, patternId) {
    if (typeof input === "object" && !Array.isArray(input)) {
      this.dom.customInputBox.value = `${input.nums.join(", ")}; ${input.target}`;
    } else if (Array.isArray(input)) {
      if (patternId === "grid-bfs") {
        this.dom.customInputBox.value = input.map(r => r.join(" ")).join(" ; ");
      } else if (patternId === "merge-intervals") {
        this.dom.customInputBox.value = `[${input.map(i => `[${i[0]},${i[1]}]`).join(",")}]`;
      } else {
        this.dom.customInputBox.value = `[${input.join(", ")}]`;
      }
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
      case "tree-traversal": return Simulations.treeTraversal;
      case "grid-bfs": return Simulations.gridBFS;
      case "merge-intervals": return Simulations.mergeIntervals;
      default: return Simulations.twoPointers;
    }
  }

  handleCustomInput() {
    const raw = this.dom.customInputBox.value.trim();
    const simEngine = this.getSimEngine(this.currentPatternId);
    if (!simEngine) return;
    const parsed = simEngine.parseInput(raw);
    this.loadPattern(this.currentPatternId, parsed);
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

    // Update Invariant Text
    this.dom.stepHeadline.innerText = step.title;
    this.dom.stepRationaleBody.innerText = step.explanation;

    // Telemetry HUD
    this.renderTelemetry(step);

    // Canvas Stage
    this.renderCanvasStage(step);

    // Synchronized Code
    this.renderSynchronizedCode(pattern.codeSnippet, step.activeLine);
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
    } else if (this.currentPatternId === "grid-bfs") {
      cells = [
        `Elapsed: <b>${step.minutes}m</b>`,
        `Fresh Remaining: <b>${step.freshCount}</b>`,
        `Active Queue: <b>${step.queue.length}</b>`
      ];
    } else if (this.currentPatternId === "merge-intervals") {
      cells = [
        `Current Index: <b>${step.currentIndex}</b>`,
        `Merged Blocks: <b>${step.merged.length}</b>`
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
    } else if (this.currentPatternId === "tree-traversal") {
      this.renderTreeCanvas(step);
    } else if (this.currentPatternId === "grid-bfs") {
      this.renderGridBFSCanvas(step);
    } else if (this.currentPatternId === "merge-intervals") {
      this.renderMergeIntervalsCanvas(step);
    }
  }

  // 1. Two Pointers Canvas (Amber)
  renderTwoPointersCanvas(step) {
    const heights = step.heights;
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

  // 5. Binary Tree Canvas (Amber)
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

  // 7. Merge Intervals Canvas (Amber)
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

  renderReferenceGrid() {
    this.dom.referenceCardsGrid.innerHTML = this.patterns.map(p => `
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

  escapeHTML(str) {
    return str
      .replace(/&/g, "&amp;")
      .replace(/</g, "&lt;")
      .replace(/>/g, "&gt;");
  }
}

// Bootstrap on DOM ready
document.addEventListener("DOMContentLoaded", () => {
  window.studioApp = new DSAStudioApp();
});
