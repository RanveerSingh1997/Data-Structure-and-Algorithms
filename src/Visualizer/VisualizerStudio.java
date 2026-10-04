package Visualizer;

import java.util.*;

/**
 * ============================================================================
 * SPEC-052: VisualizerStudio — Algorithmic Invariant & State Workbench
 * Spec: specs/Visualizer/VisualizerStudio.spec.md
 * Status: VERIFIED
 * Architecture: Clean Architecture (Robert C. Martin / Inversion of Control)
 * ============================================================================
 */
public class VisualizerStudio {

    // ========================================================================
    // LAYER 1: DOMAIN ENTITIES (Enterprise Business Rules)
    // ========================================================================

    public record SimulationStep(
        int stepIndex,
        String type,
        String title,
        String explanation,
        int activeLine,
        Map<String, Object> telemetry,
        boolean isTerminal
    ) {
        public SimulationStep {
            telemetry = Collections.unmodifiableMap(new LinkedHashMap<>(telemetry));
        }
    }

    public record PatternEntity(
        String id,
        String name,
        String category,
        String complexity,
        String invariantProof,
        String videoUrl,
        String docsUrl,
        String quickSummary
    ) {}

    public record DiagnosticRule(
        String dataStructure,
        String goal,
        String patternId,
        String cue
    ) {}

    // ========================================================================
    // LAYER 2: PORTS / BOUNDARY INTERFACES (Dependency Inversion)
    // ========================================================================

    public interface AudioPort {
        void playStep();
        void playSuccess();
        void playError();
        boolean toggle();
        boolean isEnabled();
    }

    public interface StoragePort {
        String get(String key);
        void set(String key, String value);
        void remove(String key);
    }

    public interface IPlaybackController {
        void loadSteps(List<SimulationStep> steps);
        SimulationStep getCurrentStep();
        int getCurrentIndex();
        int getTotalSteps();
        boolean isPlaying();
        void play();
        void pause();
        void stepForward();
        void stepBackward();
        void seek(int targetIndex);
        void reset();
        void setSpeed(double multiplier);
        double getSpeed();
        boolean tick();
    }

    public interface IChallengeEvaluator {
        boolean submitAnswer(int stepIndex, int optionIndex, int correctIndex);
        int getScore();
        int getTotalAnswered();
        double getAccuracy();
        void resetScore();
    }

    public interface IDiagnosticWizard {
        String diagnose(String dataStructure, String algorithmicGoal);
    }

    public interface ITraceExporter {
        String exportMarkdownTable(List<SimulationStep> steps);
    }

    // ========================================================================
    // LAYER 3: USE CASES / APPLICATION BUSINESS RULES
    // ========================================================================

    /**
     * PlaybackController manages discrete step traversal, bounds protection,
     * speed scaling, and time-travel reversibility without touching the DOM.
     */
    public static class PlaybackController implements IPlaybackController {
        private List<SimulationStep> steps = new ArrayList<>();
        private int currentIndex = 0;
        private boolean playing = false;
        private double speedMultiplier = 1.0;
        private final AudioPort audioPort;

        public PlaybackController(AudioPort audioPort) {
            this.audioPort = audioPort != null ? audioPort : new SilentAudioAdapter();
        }

        @Override
        public void loadSteps(List<SimulationStep> newSteps) {
            if (newSteps == null || newSteps.isEmpty()) {
                throw new IllegalArgumentException("Steps collection cannot be null or empty.");
            }
            this.steps = Collections.unmodifiableList(new ArrayList<>(newSteps));
            this.currentIndex = 0;
            this.playing = false;
        }

        @Override
        public SimulationStep getCurrentStep() {
            if (steps.isEmpty()) return null;
            return steps.get(currentIndex);
        }

        @Override
        public int getCurrentIndex() {
            return currentIndex;
        }

        @Override
        public int getTotalSteps() {
            return steps.size();
        }

        @Override
        public boolean isPlaying() {
            return playing;
        }

        @Override
        public void play() {
            if (steps.isEmpty()) return;
            if (currentIndex >= steps.size() - 1) {
                currentIndex = 0;
            }
            playing = true;
        }

        @Override
        public void pause() {
            playing = false;
        }

        @Override
        public void stepForward() {
            if (currentIndex < steps.size() - 1) {
                currentIndex++;
                audioPort.playStep();
            } else {
                pause();
            }
        }

        @Override
        public void stepBackward() {
            if (currentIndex > 0) {
                currentIndex--;
                audioPort.playStep();
            }
        }

        @Override
        public void seek(int targetIndex) {
            if (steps.isEmpty()) return;
            if (targetIndex < 0) {
                currentIndex = 0;
            } else if (targetIndex >= steps.size()) {
                currentIndex = steps.size() - 1;
            } else {
                currentIndex = targetIndex;
            }
            audioPort.playStep();
        }

        @Override
        public void reset() {
            pause();
            currentIndex = 0;
            audioPort.playStep();
        }

        @Override
        public void setSpeed(double multiplier) {
            if (multiplier <= 0) {
                throw new IllegalArgumentException("Speed multiplier must be strictly positive.");
            }
            this.speedMultiplier = multiplier;
        }

        @Override
        public double getSpeed() {
            return speedMultiplier;
        }

        @Override
        public boolean tick() {
            if (!playing) return false;
            if (currentIndex < steps.size() - 1) {
                currentIndex++;
                if (getCurrentStep().isTerminal()) {
                    audioPort.playSuccess();
                } else {
                    audioPort.playStep();
                }
                return true;
            } else {
                pause();
                return false;
            }
        }
    }

    /**
     * ChallengeEvaluator handles interactive prediction quizzes without mutating the tape.
     */
    public static class ChallengeEvaluator implements IChallengeEvaluator {
        private int correctCount = 0;
        private int totalCount = 0;
        private final AudioPort audioPort;

        public ChallengeEvaluator(AudioPort audioPort) {
            this.audioPort = audioPort != null ? audioPort : new SilentAudioAdapter();
        }

        @Override
        public boolean submitAnswer(int stepIndex, int optionIndex, int correctIndex) {
            totalCount++;
            boolean isCorrect = (optionIndex == correctIndex);
            if (isCorrect) {
                correctCount++;
                audioPort.playSuccess();
            } else {
                audioPort.playError();
            }
            return isCorrect;
        }

        @Override
        public int getScore() {
            return correctCount;
        }

        @Override
        public int getTotalAnswered() {
            return totalCount;
        }

        @Override
        public double getAccuracy() {
            if (totalCount == 0) return 1.0;
            return (double) correctCount / totalCount;
        }

        @Override
        public void resetScore() {
            correctCount = 0;
            totalCount = 0;
        }
    }

    /**
     * DiagnosticWizardEngine evaluates structural constraints and recommends optimal invariants.
     */
    public static class DiagnosticWizardEngine implements IDiagnosticWizard {
        private final List<DiagnosticRule> rules = new ArrayList<>();

        public DiagnosticWizardEngine() {
            // Seed canonical diagnostic rules
            rules.add(new DiagnosticRule("array", "subarray-sum-k", "sliding-window", "Contiguous span with monotonic window"));
            rules.add(new DiagnosticRule("array", "boundary-pairs", "two-pointers", "Opposite boundary inward elimination"));
            rules.add(new DiagnosticRule("array", "next-greater", "monotonic-stack", "O(N) lookup of dominant boundary"));
            rules.add(new DiagnosticRule("graph", "dependency-resolution", "topological-sort", "Kahn's in-degree zero BFS traversal"));
            rules.add(new DiagnosticRule("graph", "shortest-path-unweighted", "grid-bfs", "Multi-source level-order propagation"));
            rules.add(new DiagnosticRule("heap", "k-largest-smallest", "top-k-heap", "Min-heap of fixed capacity K"));
            rules.add(new DiagnosticRule("linkedlist", "cycle-detection", "fast-slow-pointers", "Floyd's 2x speed differential"));
            rules.add(new DiagnosticRule("intervals", "overlapping-merge", "merge-intervals", "Sort by start times then sweep"));
        }

        @Override
        public String diagnose(String dataStructure, String algorithmicGoal) {
            if (dataStructure == null || algorithmicGoal == null) {
                return "unknown";
            }
            String ds = dataStructure.trim().toLowerCase();
            String goal = algorithmicGoal.trim().toLowerCase();

            for (DiagnosticRule r : rules) {
                if (r.dataStructure().equalsIgnoreCase(ds) && r.goal().equalsIgnoreCase(goal)) {
                    return r.patternId();
                }
            }
            return "two-pointers"; // Fallback safe default
        }
    }

    /**
     * MarkdownTraceExporter synthesizes structured trace tables into GitHub Markdown.
     */
    public static class MarkdownTraceExporter implements ITraceExporter {
        @Override
        public String exportMarkdownTable(List<SimulationStep> steps) {
            if (steps == null || steps.isEmpty()) return "";

            // Collect distinct telemetry keys across steps
            Set<String> telemetryKeys = new LinkedHashSet<>();
            for (SimulationStep s : steps) {
                telemetryKeys.addAll(s.telemetry().keySet());
            }

            StringBuilder sb = new StringBuilder();
            sb.append("| Step | Title | Active Line |");
            for (String k : telemetryKeys) {
                sb.append(" ").append(k).append(" |");
            }
            sb.append("\n| --- | --- | --- |");
            for (int i = 0; i < telemetryKeys.size(); i++) {
                sb.append(" --- |");
            }
            sb.append("\n");

            for (SimulationStep s : steps) {
                sb.append("| ").append(s.stepIndex() + 1).append(" | ")
                  .append(s.title()).append(" | ")
                  .append("Line ").append(s.activeLine()).append(" |");
                for (String k : telemetryKeys) {
                    Object val = s.telemetry().getOrDefault(k, "-");
                    sb.append(" ").append(val).append(" |");
                }
                sb.append("\n");
            }

            return sb.toString();
        }
    }

    // ========================================================================
    // LAYER 4: INTERFACE ADAPTERS & IMPLEMENTATIONS (Adapters)
    // ========================================================================

    /**
     * SilentAudioAdapter: Null Object Pattern for headless execution & tests.
     */
    public static class SilentAudioAdapter implements AudioPort {
        private boolean enabled = false;

        @Override public void playStep() {}
        @Override public void playSuccess() {}
        @Override public void playError() {}
        @Override public boolean toggle() { enabled = !enabled; return enabled; }
        @Override public boolean isEnabled() { return enabled; }
    }

    /**
     * InMemoryStorageAdapter: Key-value storage implementation for testing.
     */
    public static class InMemoryStorageAdapter implements StoragePort {
        private final Map<String, String> store = new HashMap<>();

        @Override public String get(String key) { return store.get(key); }
        @Override public void set(String key, String value) { store.put(key, value); }
        @Override public void remove(String key) { store.remove(key); }
    }

    // ========================================================================
    // LAYER 5: STANDALONE TEST RUNNER (Acceptance Criteria TC-01 to TC-10)
    // ========================================================================

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("   VERIFYING SPEC-052: VisualizerStudio (Clean)   ");
        System.out.println("=================================================\n");

        AudioPort audio = new SilentAudioAdapter();
        PlaybackController controller = new PlaybackController(audio);
        ChallengeEvaluator challengeEvaluator = new ChallengeEvaluator(audio);
        DiagnosticWizardEngine wizard = new DiagnosticWizardEngine();
        MarkdownTraceExporter traceExporter = new MarkdownTraceExporter();

        // Sample 5-step Two Pointers Simulation Tape
        List<SimulationStep> tape = new ArrayList<>();
        tape.add(new SimulationStep(0, "init", "Init Boundaries", "L=0, R=4", 2, Map.of("L", 0, "R", 4, "area", 0), false));
        tape.add(new SimulationStep(1, "eval", "Evaluate Container", "Width=4, MinH=1", 4, Map.of("L", 0, "R", 4, "area", 4), false));
        tape.add(new SimulationStep(2, "advance", "Advance Left Bottleneck", "L++", 6, Map.of("L", 1, "R", 4, "area", 4), false));
        tape.add(new SimulationStep(3, "eval", "Evaluate Container", "Width=3, MinH=7", 4, Map.of("L", 1, "R", 4, "area", 21), false));
        tape.add(new SimulationStep(4, "done", "Optimal Solution Found", "Max Area = 21", 10, Map.of("L", 1, "R", 4, "maxArea", 21), true));

        controller.loadSteps(tape);

        // TC-01: Happy Path Step Traversal
        assert controller.getCurrentIndex() == 0 : "TC-01 Failed: Initial step must be 0";
        assert controller.getTotalSteps() == 5 : "TC-01 Failed: Total steps must be 5";
        controller.stepForward();
        controller.stepForward();
        assert controller.getCurrentIndex() == 2 : "TC-01 Failed: Expected index 2 after two stepForward() calls";
        System.out.println("  [PASS] TC-01: Happy Path Step Traversal");

        // TC-02: Bidirectional Reversibility (Time Travel)
        controller.seek(3);
        assert controller.getCurrentIndex() == 3 : "TC-02 Failed: Direct seek to 3";
        controller.stepBackward();
        assert controller.getCurrentIndex() == 2 : "TC-02 Failed: stepBackward from 3 to 2";
        controller.seek(3);
        assert controller.getCurrentStep().telemetry().get("area").equals(21) : "TC-02 Failed: State at step 3 must be strictly identical";
        System.out.println("  [PASS] TC-02: Bidirectional Reversibility (Time Travel)");

        // TC-03: Boundary Protection Underflow
        controller.seek(-5);
        assert controller.getCurrentIndex() == 0 : "TC-03 Failed: Lower bound must clamp to 0";
        controller.stepBackward();
        assert controller.getCurrentIndex() == 0 : "TC-03 Failed: Cannot decrement below index 0";
        System.out.println("  [PASS] TC-03: Boundary Protection Underflow");

        // TC-04: Boundary Protection Overflow
        controller.seek(100);
        assert controller.getCurrentIndex() == 4 : "TC-04 Failed: Upper bound must clamp to totalSteps - 1";
        controller.seek(3);
        controller.play();
        assert controller.isPlaying() : "TC-04 Failed: Play state must be true";
        boolean advancedToTerminal = controller.tick(); // advances from 3 to 4
        assert advancedToTerminal : "TC-04 Failed: Should advance to terminal step 4";
        assert controller.getCurrentIndex() == 4 : "TC-04 Failed: Reached terminal step 4";
        
        boolean nextTick = controller.tick(); // at step 4 (terminal)
        assert !nextTick : "TC-04 Failed: Tick at terminal step must return false";
        assert !controller.isPlaying() : "TC-04 Failed: Terminal tick must auto-pause playback";
        assert controller.getCurrentIndex() == 4 : "TC-04 Failed: Index must remain 4 at terminal step";
        System.out.println("  [PASS] TC-04: Boundary Protection Overflow & Auto-Pause");

        // TC-05: Intuition Challenge Evaluator
        challengeEvaluator.resetScore();
        boolean ans1 = challengeEvaluator.submitAnswer(2, 0, 0); // Correct
        boolean ans2 = challengeEvaluator.submitAnswer(3, 1, 0); // Incorrect
        assert ans1 : "TC-05 Failed: Correct submission must return true";
        assert !ans2 : "TC-05 Failed: Incorrect submission must return false";
        assert challengeEvaluator.getScore() == 1 : "TC-05 Failed: Expected score 1";
        assert challengeEvaluator.getTotalAnswered() == 2 : "TC-05 Failed: Expected 2 answers";
        assert Math.abs(challengeEvaluator.getAccuracy() - 0.5) < 0.001 : "TC-05 Failed: Accuracy must be 50%";
        System.out.println("  [PASS] TC-05: Intuition Challenge Evaluator");

        // TC-06: Diagnostic Wizard Logic
        String p1 = wizard.diagnose("array", "subarray-sum-k");
        String p2 = wizard.diagnose("graph", "dependency-resolution");
        String p3 = wizard.diagnose("unknown", "none");
        assert "sliding-window".equals(p1) : "TC-06 Failed: Expected sliding-window for subarray-sum-k";
        assert "topological-sort".equals(p2) : "TC-06 Failed: Expected topological-sort for dependency-resolution";
        assert "two-pointers".equals(p3) : "TC-06 Failed: Expected two-pointers fallback for unknown query";
        System.out.println("  [PASS] TC-06: Diagnostic Wizard Pure Decision Rules");

        // TC-07: Markdown Trace Table Export
        String md = traceExporter.exportMarkdownTable(tape);
        assert md.contains("| Step | Title | Active Line |") : "TC-07 Failed: Header row missing";
        assert md.contains("| 1 | Init Boundaries |") : "TC-07 Failed: Row 1 missing";
        assert md.contains("| 5 | Optimal Solution Found |") : "TC-07 Failed: Row 5 missing";
        System.out.println("  [PASS] TC-07: Markdown Trace Table Export");

        // TC-08: Audio Port Safe Null-Object Fallback
        audio.playStep();
        audio.playSuccess();
        audio.playError();
        boolean toggled = audio.toggle();
        assert toggled : "TC-08 Failed: Audio toggle must toggle state";
        System.out.println("  [PASS] TC-08: Null Audio Adapter Safe Execution");

        // TC-09: Playback Reset Contract
        controller.seek(3);
        controller.reset();
        assert controller.getCurrentIndex() == 0 : "TC-09 Failed: Reset must restore index 0";
        assert !controller.isPlaying() : "TC-09 Failed: Reset must set isPlaying to false";
        System.out.println("  [PASS] TC-09: Playback Reset Contract");

        // TC-10: Clean Architecture Layer Decoupling & Soundness
        StoragePort storage = new InMemoryStorageAdapter();
        storage.set("dsa_initial_tab", "video");
        assert "video".equals(storage.get("dsa_initial_tab")) : "TC-10 Failed: StoragePort decoupling";
        storage.remove("dsa_initial_tab");
        assert storage.get("dsa_initial_tab") == null : "TC-10 Failed: StoragePort deletion";
        System.out.println("  [PASS] TC-10: Clean Architecture Layer Decoupling & Soundness");

        System.out.println("\nAll VisualizerStudio (Clean Architecture) test cases PASSED!");
    }
}
