import java.util.ArrayList;
import java.util.List;

/**
 * Represents a MenuTree
 * @author Kaiyi Liu, Zachary Vince
 * */

public class MenuTree {
    private MenuTree parentMenuTree; //if null then it is the root node
    private IController value;
    private List<MenuTree> childMenuTrees; //if null it is youngest

    public MenuTree(IController value){
        this.value = value;
        this.parentMenuTree = null;
        this.childMenuTrees = new ArrayList<>();
    }

    public MenuTree(IController value, MenuTree parent){
        this(value);
        this.parentMenuTree = parent;
    }

    public MenuTree(IController value, MenuTree parent, List<MenuTree> children){
        this(value, parent);
        this.childMenuTrees = children;
    }

    /**
     * Sets the parent MenuTree.
     * @param parent the parent MenuTree of this MenuTree instance
     */
    public void setParent(MenuTree parent){
        this.parentMenuTree = parent;
    }

    /**
     * Gets the parent of this MenuTree node.
     * @return the parent MenuTree of this instance.
     */
    public MenuTree getParent(){
        return this.parentMenuTree;
    }

    public MenuTree getChild(int index){
        return this.childMenuTrees.get(index);
    }

    public List<MenuTree> getChildren(){
        return new ArrayList<>(this.childMenuTrees);
    }

    public void addChild(IController controller){
        MenuTree child = new MenuTree(controller, this.parentMenuTree);
        this.childMenuTrees.add(child);
    }

    public void addChildren(List<IController> controllers){
        for(IController controller : controllers){
            MenuTree child = new MenuTree(controller, this.parentMenuTree);
            this.childMenuTrees.add(child);
        }
    }

    public void setValue(IController controller){
        this.value = controller;
    }

    public IController getValue(){
        return this.value;
    }


}
