package com.group0179.cli;

import com.group0179.cli.controllers_cli.IControllerCLI;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a MenuTree
 * @author Kaiyi Liu, Zachariah Vince
 * */

public class MenuTreeCLI {
    private MenuTreeCLI parentMenuTree; //if null then it is the root node
    private IControllerCLI value;
    private List<MenuTreeCLI> childMenuTrees; //if null it is youngest

    /**
     * Creates a parent MenuTree (with no parents)
     * @param value the controller this menu option corresponds to.
     */
    public MenuTreeCLI(IControllerCLI value){
        this.value = value;
        this.parentMenuTree = null;
        this.childMenuTrees = new ArrayList<>();
    }

    /**
     * Creates a Menu tree with a parent.
     * @param value the controller this menu option corresponds to.
     * @param parent the parent of this menu option.
     */
    public MenuTreeCLI(IControllerCLI value, MenuTreeCLI parent){
        this(value);
        this.parentMenuTree = parent;
    }

    /**
     * Creates a MenuTree with a parent and a list of children.
     * @param value the controller this menu option corresponds to.
     * @param parent the parent of this menu option.
     * @param children the children menus of this menu option.
     */
    public MenuTreeCLI(IControllerCLI value, MenuTreeCLI parent, List<MenuTreeCLI> children){
        this(value, parent);
        this.childMenuTrees = children;
    }

    /**
     * Sets the parent MenuTree.
     * @param parent the parent MenuTree of this MenuTree instance
     */
    public void setParent(MenuTreeCLI parent){
        this.parentMenuTree = parent;
    }

    /**
     * Gets the parent of this MenuTree node.
     * @return the parent MenuTree of this instance.
     */
    public MenuTreeCLI getParent(){
        return this.parentMenuTree;
    }

    /**
     * @param index the index number of the children node list.
     * @return the child MenuTree at this index.
     */
    public MenuTreeCLI getChild(int index){
        return this.childMenuTrees.get(index);
    }

    /**
     * @return a list of this node's children.
     */
    public List<MenuTreeCLI> getChildren(){
        return new ArrayList<>(this.childMenuTrees);
    }

    /**
     * Adds a child node to this tree.
     * @param tree the MenuTree you wish to add.
     */
    public void addChild(MenuTreeCLI tree){
        tree.setParent(this);
        this.childMenuTrees.add(tree);
    }

    /**
     * Adds a list of children nodes to this tree.
     * @param trees the list of MenuTrees to add.
     */
    public void addChildren(List<MenuTreeCLI> trees){
        for(MenuTreeCLI tree : trees){
            tree.setParent(this);
            this.childMenuTrees.add(tree);
        }
    }

    /**
     * Changes the controller class this menu option corresponds to.
     * @param controller the controller class to change to.
     */
    public void setValue(IControllerCLI controller){
        this.value = controller;
    }

    /**
     * @return the controller class this menu option corresponds to.
     */
    public IControllerCLI getValue(){
        return this.value;
    }

    /**
     * Call's the controller's run command.
     * @return the state change command int.
     */
    public int run() { return this.value.run(); }
}
