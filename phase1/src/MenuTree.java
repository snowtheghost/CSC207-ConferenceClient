import java.util.ArrayList;
import java.util.List;

/**
 * Represents a MenuTree
 * @author Kaiyi Liu, Zachariah Vince
 * */

public class MenuTree {
    private MenuTree parentMenuTree; //if null then it is the root node
    private IController value;
    private List<MenuTree> childMenuTrees; //if null it is youngest

    /**
     * Creates a parent MenuTree (with no parents)
     * @param value the controller this menu option corresponds to.
     */
    public MenuTree(IController value){
        this.value = value;
        this.parentMenuTree = null;
        this.childMenuTrees = new ArrayList<>();
    }

    /**
     * Creates a Menu tree with a parent.
     * @param value the controller this menu option corresponds to.
     * @param parent the parent of this menu option.
     */
    public MenuTree(IController value, MenuTree parent){
        this(value);
        this.parentMenuTree = parent;
    }

    /**
     * Creates a MenuTree with a parent and a list of children.
     * @param value the controller this menu option corresponds to.
     * @param parent the parent of this menu option.
     * @param children the children menus of this menu option.
     */
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

    /**
     * @param index the index number of the children node list.
     * @return the child MenuTree at this index.
     */
    public MenuTree getChild(int index){
        return this.childMenuTrees.get(index);
    }

    /**
     * @return a list of this node's children.
     */
    public List<MenuTree> getChildren(){
        return new ArrayList<>(this.childMenuTrees);
    }

    /**
     * Adds a child node to this tree.
     * @param tree the MenuTree you wish to add.
     */
    public void addChild(MenuTree tree){
        tree.setParent(this);
        this.childMenuTrees.add(tree);
    }

    /**
     * Adds a list of children nodes to this tree.
     * @param trees the list of MenuTrees to add.
     */
    public void addChildren(List<MenuTree> trees){
        for(MenuTree tree : trees){
            tree.setParent(this);
            this.childMenuTrees.add(tree);
        }
    }

    /**
     * Changes the controller class this menu option corresponds to.
     * @param controller the controller class to change to.
     */
    public void setValue(IController controller){
        this.value = controller;
    }

    /**
     * @return the controller class this menu option corresponds to.
     */
    public IController getValue(){
        return this.value;
    }

    /**
     * Call's the controller's run command.
     * @return the state change command int.
     */
    public int run() { return this.value.run(); }
}
