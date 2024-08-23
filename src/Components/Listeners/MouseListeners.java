package Components.Listeners;

import Components.Edge;
import Components.EdgeList;
import Components.Node;
import Components.NodeList;
import Algorithms.GaleShapley.OffererNode;
import Algorithms.GaleShapley.OffereeNode;
import Components.SolveManager.DropDownHandler;
import GUI.Panels.GaleShapleyPanel;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class MouseListeners extends MouseAdapter {

    private NodeList nodeList;
    private EdgeList edgeList;
    private Node startNode;
    private int dragEndX, dragEndY;
    private Runnable repaintCallback;
    DropDownHandler dropDownHandler;
    GaleShapleyPanel gsPanel;


    public MouseListeners(NodeList nodeList, EdgeList edgeList, Runnable repaintCallback,DropDownHandler dropDownHandler,
                          GaleShapleyPanel gsPanel) {
        this.nodeList = nodeList;
        this.edgeList = edgeList;
        this.repaintCallback = repaintCallback;
        this.startNode = null;
        this.dropDownHandler = dropDownHandler;
        this.gsPanel = gsPanel;
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
                if (dropDownHandler.isGSPanelVisible()) {
                    gsPanel.handleGaleShapleyNodeCreation(e.getX(), e.getY());
                } else {
                    // For non-Gale-Shapley mode, just add a regular node
                    Node nodeToAdd = new Node(e.getX(), e.getY(), nodeList.getNodes().size() + 1);
                    nodeList.addNode(nodeToAdd);
                }
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
