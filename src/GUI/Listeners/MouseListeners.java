package GUI.Listeners;
import GUI.Components.Edge;
import GUI.Components.EdgeList;
import GUI.Components.Node;
import GUI.Components.NodeList;

import javax.swing.*;
import java.awt.event.*;

public class MouseListeners extends MouseAdapter{

    private NodeList nodeList;
    private EdgeList edgeList;
    private Node startNode;
    private int dragEndX, dragEndY;
    private Runnable repaintCallback;

    public MouseListeners(NodeList nodeList, EdgeList edgeList, Runnable repaintCallback) {
        this.nodeList = nodeList;
        this.edgeList = edgeList;
        this.repaintCallback = repaintCallback;
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
                nodeList.addNode(e.getX(), e.getY());
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
