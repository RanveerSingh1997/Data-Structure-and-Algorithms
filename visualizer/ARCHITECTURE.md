# 🏛️ Visualizer Clean Architecture & Invariant Engine Specification

This document details the **Clean Architecture** implementation of the **DSA Studio Algorithmic Invariant & State Workbench**, aligning with Robert C. Martin's Dependency Rule, SOLID design principles, and Spec-Driven Development (`SPEC-052: VisualizerStudio`).

---

## 🎯 1. Architectural Philosophy & The Dependency Rule

```text
               +-------------------------------------------------------+
               |             Frameworks & Drivers (UI)                |
               |  Browser DOM · SVG Stage · Web Audio · Native Dialog  |
               |       +---------------------------------------+       |
               |       |         Interface Adapters            |       |
               |       |  AudioPort · StoragePort · Presenters |       |
               |       |       +-----------------------+       |       |
               |       |       |       Use Cases       |       |       |
               |       |       | Playback · Challenge  |       |       |
               |       |       | Wizard · TraceExport  |       |       |
               |       |       |       +-------+       |       |       |
               |       |       |       |Entities|      |       |       |
               |       |       |       | Step  |       |       |       |
               |       |       |       |Pattern|       |       |       |
               |       |       |       +-------+       |       |       |
               |       |       +-----------------------+       |       |
               |       +---------------------------------------+       |
               +-------------------------------------------------------+
```

> **The Dependency Rule**: Source code dependencies point strictly inward. Inner circles know nothing about outer circles. The domain entities and simulation generators have **zero dependencies** on the DOM, CSS, Web Audio, or browser globals.

---

## 🧩 2. Concentric Layer Responsibilities

### Layer 1: Entities & Algorithmic Domain (`patterns-data.js`, `visualizers.js`)
- **`SimulationStep`**: An immutable snapshot frame representing:
  - `stepIndex`: Zero-based step position.
  - `type`: State event classification (`init`, `evaluate`, `advance`, `collision`, `found`, `done`).
  - `title` & `explanation`: Mathematical justification for why this step preserves optimality.
  - `activeLine`: Synchronized source code line in the reference Java solution.
  - `telemetry`: Limiting bottlenecks, pointer indices, and accumulator values.
- **`AlgorithmicPattern`**: Canonical descriptors containing formal invariant proofs, Big-O budgets, signal keywords, preset benchmarks, and curated video/doc links.
- **Pure Step Generators (`Simulations.*`)**: Pure deterministic functions:
  $$\mathcal{G}(\text{Input}) \longrightarrow \vec{S} = \langle S_0, S_1, \dots, S_{m-1} \rangle$$
  Never mutates external state, never touches DOM or audio.

### Layer 2: Use Cases & Application Business Rules
- **`PlaybackController`**:
  - Manages discrete frame pointer $k \in [0, m-1]$.
  - Governs `play()`, `pause()`, `stepForward()`, `stepBackward()`, `seek(k)`, and `reset()`.
  - Implements **Bounded Termination Invariant**: Auto-pauses when reaching the terminal frame.
  - Implements **Reversible Time-Travel**: Seeking to step $k$ returns the exact state $S_k$ regardless of preceding operations.
- **`IntuitionChallengeService`**:
  - Evaluates prediction checkpoints at critical decision branches.
  - Tracks intuition accuracy score ($\text{score} / \text{total}$) without mutating the simulation tape.
- **`DiagnosticWizardService`**:
  - Evaluates data structure characteristics and algorithmic constraints against formal decision rules to determine the optimal pattern.
- **`TraceExporter`**:
  - Formats simulation tape into standard GitHub-flavored Markdown tables for whiteboard review.

### Layer 3: Interface Adapters & Boundary Ports
- **`AudioPort`**:
  - Abstract boundary contract: `playStep()`, `playSuccess()`, `playError()`, `toggle()`, `isEnabled()`.
  - Decouples sound effects from simulation logic.
  - Implements **Null Object Pattern**: When audio is muted or in headless environments, operations execute safely without throwing errors.
- **`StoragePort`**:
  - Abstracts persistence (`get`, `set`, `remove`) for tab selection and user preferences.
- **`TelemetryPresenter` & `CodePresenter`**:
  - Translates raw domain numbers and metrics into clean, human-readable UI badges.

### Layer 4: Frameworks & Drivers (Infrastructure & UI)
- **`StudioDOMView`**:
  - Mounts components to DOM nodes.
  - Draws bespoke SVG / Canvas visualizations (Water reservoirs, character strip arrays, binary trees, Kahn's in-degree grids, min-heap chambers).
  - Handles native `<dialog id="videoModal" closedby="any">` lifecycle with light-dismiss fallbacks.
  - Manages keyboard shortcuts (`Space`, `←`, `→`, `R`).
- **`WebAudioAdapter`**:
  - Web Audio API oscillator synthesis (sine wave chirps, harmonic triads, sawtooth alert cues).
- **`LocalStorageAdapter`**:
  - Browser `localStorage` and URL Search Parameter parser (`?pattern=...&tab=video`).

---

## 📐 3. SOLID Principles Compliance Matrix

| Principle | Visualizer Implementation & Proof |
| :--- | :--- |
| **S - Single Responsibility** | `Simulations.twoPointers` only calculates algorithmic steps; `PlaybackController` only manages playback timing and indexing; `StudioDOMView` only renders DOM elements. |
| **O - Open/Closed** | New algorithmic patterns (e.g. Graph DFS) can be added to `patterns-data.js` and `visualizers.js` without altering `PlaybackController` or the core telemetry engine. |
| **L - Liskov Substitution** | `SilentAudioAdapter` and `WebAudioAdapter` both implement `AudioPort` identically; substituting one for the other never breaks application execution. |
| **I - Interface Segregation** | Boundary ports (`AudioPort`, `StoragePort`, `ITraceExporter`) are fine-grained and focused, preventing clients from depending on unused methods. |
| **D - Dependency Inversion** | High-level Use Cases (`PlaybackController`, `ChallengeEvaluator`) depend upon abstract ports (`AudioPort`), injected via constructor. |

---

## ⚡ 4. Verification & Testing

The visualizer architecture is formally verified in two environments:

1. **Standalone Headless Verification (Java)**:
   ```bash
   ./sdd verify VisualizerStudio
   ```
   Compiles and runs [`src/Visualizer/VisualizerStudio.java`](file:///Users/zml-mac-ranveerg-01/IdeaProjects/Data%20Strucure%20and%20Algorithms/src/Visualizer/VisualizerStudio.java), exercising TC-01 through TC-10 in a pure JVM environment with zero browser dependencies.

2. **Full Repository SDD Verification**:
   ```bash
   ./sdd verify
   ```
   Ensures all 29 repository specifications are sound and all 87 test cases pass.
