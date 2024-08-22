package GUI.Listeners;

import GUI.Components.Edge;
import GUI.Components.EdgeList;
import GUI.Components.Node;
import GUI.Components.NodeList;
import GUI.Panels.GaleShapleyPanel;
import Algorithms.GaleShapley.OffererNode;
import Algorithms.GaleShapley.OffereeNode;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class MouseListeners extends MouseAdapter {

    private NodeList nodeList;
    private EdgeList edgeList;
    private Node startNode;
    private int dragEndX, dragEndY;
    private Runnable repaintCallback;
    private GaleShapleyPanel gsPanel;

    public MouseListeners(NodeList nodeList, EdgeList edgeList, Runnable repaintCallback, GaleShapleyPanel gsPanel) {
        this.nodeList = nodeList;
        this.edgeList = edgeList;
        this.repaintCallback = repaintCallback;
        this.gsPanel = gsPanel;
        this.startNode = null;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            Node clickedNode = nodeList.getNodeAt(e.getX(), e.getY());
            if (clickedNode != null) {
                startNode = clickedNode;
                dragEndX = e.getX();
                dragEndY = e.getY();
            } else {
                boolean isOfferer = gsPanel.isOffererMode();
                int slots = isOfferer ? Integer.parseInt(gsPanel.getSlotField()) : 0;
                List<String> preferences = gsPanel.getPreferences();
                addNode(e.getX(), e.getY(), nodeList.getNodes().size(), isOfferer, slots, preferences);
            }
        } else if (SwingUtilities.isRightMouseButton(e)) {
            Node clickedNode = nodeList.getNodeAt(e.getX(), e.getY());
            if (clickedNode != null) {
                nodeList.removeNode(clickedNode);
                edgeList.removeEdgesConnectedToNode(clickedNode);
            } else {
                Edge clickedEdge = edgeList.getEdgeAt(e.getX(), e.getY());
                if (clickedEdge != null) {
                    edgeList.removeEdge(clickedEdge);
                }
            }
        }
        repaintCallback.run();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (startNode != null) {
            dragEndX = e.getX();
            dragEndY = e.getY();
            repaintCallback.run();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (startNode != null && SwingUtilities.isLeftMouseButton(e)) {
            Node endNode = nodeList.getNodeAt(e.getX(), e.getY());
            if (endNode != null && startNode != endNode) {
                edgeList.addEdge(startNode, endNode);
            }
            startNode = null;
            repaintCallback.run();
        }
    }

    private void addNode(int x, int y, int id, boolean isOfferer, int slots, List preferences) {
        if (isOfferer) {
            nodeList.addNode(new OffererNode(id, x, y, slots, preferences));
        } else {
            nodeList.addNode(new OffereeNode(id, x, y, preferences));
        }
    }

    public Node getStartNode() {
        return startNode;
    }

    public int getDragEndX() {
        return dragEndX;
    }

    public int getDragEndY() {
        return dragEndY;
    }
}
