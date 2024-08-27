package Algorithms.GaleShapley;

import Components.Node;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GaleShapley {
    public void preformGaleShapley(List<Node> nodes, JPanel graphPanel) {
        List<OffererNode> offererNodes = new ArrayList<>();
        List<OffereeNode> offereeNodes = new ArrayList<>();
        for (Node node : nodes) {
            if(node.getClass().equals(OffererNode.class)) {
                offererNodes.add((OffererNode) node);
            }else if(node.getClass().equals(OffereeNode.class)) {
                offereeNodes.add((OffereeNode) node);
            }
        }
        for(OffererNode offererNode : offererNodes) {
            System.out.println(offererNode.getPreferences());
            System.out.println(offererNode.getSlots());
        }
    }

    public Set<Pair> doGaleShapley(List<OffererNode> offererNodes, List<OffereeNode> offereeNodes) {
        Set<Pair> pairs = new HashSet<>();

        OffererNode offererNode = getFreeOfferer(offererNodes);
        while(offererNode != null) {
            offererNode = getFreeOfferer(offererNodes);
            for(String preference : offererNode.getPreferences()) {
                OffereeNode offereeNode = matchIDWithOfferer(preference, offereeNodes);
                if(offereeNode.isFree()){
                    Pair match = new Pair(offererNode,offereeNode);
                    pairs.add(match);
                    offereeNode.setMatchedOfferer(offererNode);
                }else{
                    OffererNode offereeCurrentMatch = offereeNode.getMatchedOfferer();
                    List<String> offereePreferences = offereeNode.getPreferences();
                    OffererNode offereeMostPreferedChoice = compareOffereePreferences(offereePreferences,offereeCurrentMatch,offererNode);
                    if(offereeMostPreferedChoice.equals(offererNode)) {
                        Pair matchToRemove = new Pair(offererNode,offereeNode);
                        pairs.remove(matchToRemove);

                        for(OffererNode offerer : offererNodes) {
                            if(offerer.getId() == offereeCurrentMatch.getId()){
                                offerer.setFree(true);
                            }
                        }

                        Pair match = new Pair(offererNode,offereeNode);
                        pairs.add(match);
                        offereeNode.setMatchedOfferer(offererNode);
                    }

                }

            }

        }

        return pairs;
    }

    public OffererNode getFreeOfferer(List<OffererNode> offererNodes){
        for(OffererNode offererNode : offererNodes){
            if(offererNode.isFree()){
                return offererNode;
            }
        }
        return null;
    }

    public OffereeNode matchIDWithOfferer(String id, List<OffereeNode> offereeNodes){
        for(OffereeNode offereeNode : offereeNodes){
            if(offereeNode.getId() == Integer.parseInt(id)){
                return offereeNode;
            }
        }
        return null;
    }

    public OffererNode compareOffereePreferences(List<String> prefernceList, OffererNode option1, OffererNode option2){
        for(String preference : prefernceList){
            if(option1.getId() == Integer.parseInt(preference)){
                return option1;
            }else if(option2.getId() == Integer.parseInt(preference)){
                return option2;
            }
        }
        return null;
    }

}
