package AoC.day_12;

import java.io.IOException;
import java.util.*;


public class CaveGraph {

    private final String EDGE_DELIMITER = "-";

    private final Map<String, ArrayList<String>> adjacencyMap;
    private ArrayList<String> validPaths = new ArrayList();

    public CaveGraph(List<String> edges) {
        this.adjacencyMap = createEdges(edges);
    }

    public int countPathsInGraph(boolean enableVisitSmallNodesTwice) throws IOException {
        GraphExplorer explorer = new GraphExplorer(adjacencyMap);
        explorer.exploreGraph();
        addNewPaths(explorer.getValidPaths());

        if (enableVisitSmallNodesTwice) {
            countPathsInGraph(getSmallNodes());
        }
        return validPaths.size();
    }

    public List<String> getValidPathsInGraph() {
        return validPaths;
    }

    private void countPathsInGraph(ArrayList<String> smallNodes) throws IOException {
        for (String smallNode : smallNodes) {
            GraphExplorer explorer = new GraphExplorer(adjacencyMap, smallNode, 2);
            explorer.exploreGraph();
            addNewPaths(explorer.getValidPaths());
        }
    }

    private ArrayList<String> getSmallNodes() {
        ArrayList<String> smallNodes = new ArrayList();
        Iterator<String> iter = adjacencyMap.keySet().iterator();
        while (iter.hasNext()) {
            String node = iter.next();
            if (GraphExplorer.isSmallNode(node) && !GraphExplorer.isStartNode(node) && !GraphExplorer.isEndNode(node))
                smallNodes.add(node);
        }
        return smallNodes;
    }

    private void addNewPaths(List<String> exploredPaths) {
        for (String path : exploredPaths)
            if (!validPaths.contains(path))
                validPaths.add(path);
    }

    private Map<String, ArrayList<String>> createEdges(List<String> edges) {
        Map<String, ArrayList<String>> map = new HashMap();
        for (String edge : edges) {
            String[] nodes = edge.split(EDGE_DELIMITER);
            addEdge(nodes[0], nodes[1], map);
            addEdge(nodes[1], nodes[0], map);
        }
        return map;
    }

    private void addEdge(String edgeV, String edgeW, Map<String, ArrayList<String>> map) {
        if (!map.containsKey(edgeV))
            map.put(edgeV, new ArrayList());
        map.get(edgeV).add(edgeW);
    }

}
