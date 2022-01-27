package AoC.day_12;

import AoC.data_manager.DataConverter;
import org.junit.platform.commons.util.StringUtils;

import java.io.IOException;
import java.util.*;

public class GraphExplorer {

    private static final String START_NODE = "start";
    private static final String END_NODE = "end";

    private final Map<String, ArrayList<String>> adjacencyMap;
    private ArrayList<LinkedList<String>> validPaths = new ArrayList();
    private final String revisitableNode;
    private final int timesRevisitable;

    public GraphExplorer(Map<String, ArrayList<String>> adjacencyMap) throws IOException {
        isValidGraphAndThrowException(adjacencyMap, null);
        this.adjacencyMap = adjacencyMap;
        this.revisitableNode = null;
        this.timesRevisitable = -1;
    }

    public GraphExplorer(Map<String, ArrayList<String>> adjacencyMap, String revisitableNode, int timesRevisitable) throws IOException {
        isValidGraphAndThrowException(adjacencyMap, revisitableNode);
        this.adjacencyMap = adjacencyMap;
        this.revisitableNode = isSmallNode(revisitableNode) ? revisitableNode : null;
        this.timesRevisitable = isSmallNode(revisitableNode) ? timesRevisitable : -1;
    }

    public int exploreGraph() {
        explorePath(START_NODE, new LinkedList<String>());
        return validPaths.size();
    }

    private void explorePath(String node, LinkedList<String> exploredNodesInPath) {
        LinkedList<String> exploredNodes = new LinkedList(DataConverter.deepCopyList(exploredNodesInPath));
        if (isStartNode(node) && !exploredNodes.isEmpty())
            return;

        if (isSmallNode(node)) {
            if (hasRevisitableNode() && revisitableNode.equals(node)) {
                if (Collections.frequency(exploredNodes, revisitableNode) == timesRevisitable)
                    return;
            } else if (exploredNodes.contains(node))
                    return;
        }

        exploredNodes.add(node);
        if (isEndNode(node)) {
            validPaths.add(exploredNodes);
            return;
        }

        ArrayList<String> adjacentNodes = adjacencyMap.get(node);
        for (String adjacentNode : adjacentNodes) {
            explorePath(adjacentNode, exploredNodes);
        }
    }

    public List<String> getValidPaths() {
        ArrayList<String> outPaths = new ArrayList();
        for (LinkedList<String> path : validPaths) {
            outPaths.add(DataConverter.convertListToString(path));
        }
        return outPaths;
    }

    private boolean isValidGraphAndThrowException(Map<String, ArrayList<String>> graph, String revisitableNode) throws IOException {
        if (!graph.containsKey(START_NODE) || !graph.containsKey(END_NODE))
            throw new IOException(String.format("Invalid graph! '%s' node or '%s' node is missing!", START_NODE, END_NODE));
        if (revisitableNode != null && !graph.containsKey(revisitableNode))
            throw new IOException(String.format("Re-visitable node '%s' is not a node of the graph!", revisitableNode));
        return true;
    }

    private boolean hasRevisitableNode() {
        return !StringUtils.isBlank(revisitableNode);
    }

    public static boolean isSmallNode(String node) {
        return node.toLowerCase().equals(node);
    }

    public static boolean isStartNode(String node) {
        return node.equals(START_NODE);
    }

    public static  boolean isEndNode(String node) {
        return node.equals(END_NODE);
    }
}
