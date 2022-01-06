import java.util.ArrayList;
import java.util.List;

public class Node {

    private Integer number;
    private List<Integer> linked = new ArrayList<>();
    private List<Integer> visited = new ArrayList<>();

    public Node(Integer number) {
        this.number = number;
    }

    public boolean addLinkedNode(Integer node) {
        if (linked.contains(node)) {
            return false;
        } else {
            linked.add(node);
            return true;
        }
    }

    public List<Integer> getLinked() {
        return linked;
    }

}
