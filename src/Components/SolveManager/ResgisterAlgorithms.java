package Components.SolveManager;

import Algorithms.BFS;
import Algorithms.DFS;
import Algorithms.GaleShapley.GaleShapley;

import java.awt.*;

public class ResgisterAlgorithms {

    private SolveButton solveButtonHandler;

    public ResgisterAlgorithms(SolveButton solveButtonHandler) {
        this.solveButtonHandler = solveButtonHandler;
    }

    public void registerHandlers(BFS bfs, DFS dfs, GaleShapley galeShapley) {
        solveButtonHandler.registerAlgorithmHandler("BFS", (nodes, edges, panel) -> {
            if (!nodes.isEmpty()) {
                Graphics g = panel.getGraphics();
                bfs.performBFS(nodes.get(0), nodes, edges, g);
            } else {
                solveButtonHandler.showError("No nodes available to start BFS.");
            }
        });

        solveButtonHandler.registerAlgorithmHandler("DFS", (nodes, edges, panel) -> {
            if (!nodes.isEmpty()) {
                Graphics g = panel.getGraphics();
                dfs.performDFS(nodes.get(0), nodes, edges, g);
            } else {
                solveButtonHandler.showError("No nodes available to start DFS.");
            }
        });

        solveButtonHandler.registerAlgorithmHandler("Gale-Shapley", (nodes, edges, panel) -> {
            if (!nodes.isEmpty()) {
                galeShapley.preformGaleShapley();
            } else {
                solveButtonHandler.showError("No nodes available to start Gale-Shapley.");
            }
        });
    }
}
