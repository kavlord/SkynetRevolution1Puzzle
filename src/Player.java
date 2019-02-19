import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {
    private static List<Node> allNodes;
    private static int N;
    private static int L;
    private static int E;
    private static List<Node> exitNodes;
    private static List<Pair<Node, Node>> linksBlocked;
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt(); // the total number of nodes in the level, including the gateways
        L = in.nextInt(); // the number of links
        E = in.nextInt(); // the number of exit gateways
        allNodes = new ArrayList<>();
        for (int i = 0; i < L; i++) {
            // N1 and N2 defines a link between these nodes
            Node N1 = new Node(in.nextInt());
            Node N2 = new Node(in.nextInt());
            fillNodesList(N1, N2);
        }
        exitNodes = new ArrayList<>();
        for (int i = 0; i < E; i++) {
            exitNodes.add(allNodes.get(allNodes.indexOf(new Node(in.nextInt())))); // the index of a gateway node
        }

        linksBlocked = new ArrayList<>();
        // game loop
        while (true) {
            int SI = in.nextInt(); // The index of the node on which the Skynet agent is positioned this turn
            Node agent = allNodes.get(allNodes.indexOf(new Node(SI)));
            if(checkIfAgentAboutToLeave(agent)){

            }else{
                blockInFrontOfAgent(agent);
            }
            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");


            // Example: 0 1 are the indices of the nodes you wish to sever the link between
        }
    }
    private static boolean checkIfAgentAboutToLeave(Node agent){
        for (Node exitNode :
                exitNodes) {
            Pair<Node, Node> pair = new Pair<>(agent, exitNode);
            if(allNodes.get(allNodes.indexOf(agent)).getLinkedNodes().contains(exitNode) && !linksBlocked.contains(pair)){
                System.out.println(agent.getNumber() + " " + exitNode.getNumber());
                linksBlocked.add(pair);
                return true;
            }
        }
        return false;
    }
    private static void blockInFrontOfAgent(Node agent){
        for (Node linkedNode :
                agent.getLinkedNodes()) {
            Pair<Node, Node> pair = new Pair<>(agent, linkedNode);
            if(!linksBlocked.contains(pair)) {
                System.out.println(agent.getNumber() + " " + linkedNode.getNumber());
                linksBlocked.add(pair);
                return;
            }
        }
    }
    private static void fillNodesList(Node N1, Node N2){
        if(allNodes.contains(N1)){
            allNodes.get(allNodes.indexOf(N1)).addLinkedNodes(N2);
        }else{
            allNodes.add(N1.addLinkedNodes(N2));
        }
        if(allNodes.contains(N2)){
            allNodes.get(allNodes.indexOf(N2)).addLinkedNodes(N1);
        }else{
            allNodes.add(N2.addLinkedNodes(N1));
        }
    }
}
class Node {
    private Integer number;
    private List<Node> linkedNodes = new ArrayList<>();

    public Node() {
    }

    Node(int number) {
        this.number = number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public List<Node> getLinkedNodes() {
        return linkedNodes;
    }

    public Node addLinkedNodes(Node linkedTo) {
        linkedNodes.add(linkedTo);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(number, node.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}