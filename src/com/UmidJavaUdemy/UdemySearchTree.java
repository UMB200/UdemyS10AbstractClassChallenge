package com.UmidJavaUdemy;

public class UdemySearchTree implements UdemyNodeList {
    private UdemyListItem root = null;

    public UdemySearchTree(UdemyListItem root) {
        this.root = root;
    }

    @Override
    public UdemyListItem getRoot() {
        return this.root;
    }

    @Override
    public boolean addItem(UdemyListItem newItem) {
        if(this.root ==null){
            //the tree was empty so our item becomes head of the tree
            this.root = newItem;
            return true;
        }
        //otherwise start comparing from the head of the tree
        UdemyListItem udemyListItem = this.root;
        while (udemyListItem !=null){
            int compared = (udemyListItem.compareTo(newItem));
            if(compared < 0){
                //newItem is greater move right if possible
                if(udemyListItem.nextListItem() !=null){
                    udemyListItem = udemyListItem.nextListItem();
                } else {
                    //there is no node to the right so add at this point
                    udemyListItem.setNextListItem(newItem);
                    return true;
                }
            }
            else if(compared > 0){
                //newItem is less move to the left if possible
                if(udemyListItem.previousListItem() !=null){
                    udemyListItem = udemyListItem.previousListItem();
                }
                else {
                    //there is no node to the left so add at this point
                    udemyListItem.setPreviousListItem(newItem);
                    return true;
                }
            } else {
                //equal thus no add
                System.out.println(newItem.getValue() + " is already present");
                return false;
            }
        }
        return false; //it is unreachable point, thus added to avoid compilation error
    }


    @Override
    public boolean removeItem(UdemyListItem item) {
        if(item !=null){
            System.out.println(item.getValue() + " deleting item");
        }
        UdemyListItem currentItem = this.root;
        UdemyListItem parentItem = currentItem;
        while (currentItem != null){
            int compared = (currentItem.compareTo(item));
            if(compared < 0){
                parentItem = currentItem;
                currentItem =currentItem.nextListItem();
            } else if(compared > 0){
                parentItem = currentItem;
                currentItem = currentItem.previousListItem();
            } else{
                //equal we have found the item so remove it
                actualDelete(currentItem, parentItem);
                return true;
            }
        } return false; //adding because Java won't compile, unreachable anyway
    }
    private void actualDelete(UdemyListItem currentItem, UdemyListItem parent){
        //remove item from the tree
        if(currentItem.nextListItem() == null){
            //no right tree, thus make parent point to left tree (which might be null)
            if(parent.nextListItem() == currentItem){
                //item is right child of its parent
                parent.setNextListItem(currentItem.previousListItem());
            } else if(parent.previousListItem() == currentItem){
                //item is left child of its parent
                parent.setPreviousListItem(currentItem.previousListItem());
            } else {
                //parent must be currentItem which means we were looking at the root of the tree
                this.root = currentItem.previousListItem();
            }
        } else if(currentItem.previousListItem() == null){
            //no left tree, so make parent point to right tree (which might be null)
            if(parent.nextListItem() == currentItem){
                //item is right child of its parents
                parent.setNextListItem(currentItem.nextListItem());
            } else if(parent.previousListItem() == currentItem){
                // item is left child of its parent
                parent.setPreviousListItem(currentItem.nextListItem());
            } else {
                this.root = currentItem.nextListItem();//we're deleting the root
            }
        }else{
            //neither left or right are null, deleting is now a trickier
            //from the right sub-tree find the smallest value (i.e. the leftmost)
            UdemyListItem anotherCurrent = currentItem.nextListItem();
            UdemyListItem leftMostParent = currentItem;
            while (anotherCurrent.previousListItem() !=null){
                leftMostParent = anotherCurrent;
                anotherCurrent = anotherCurrent.previousListItem();
            }//now put the smallest value into our node to be deleted
            currentItem.setValue(anotherCurrent.getValue());
            if(leftMostParent == currentItem){
                //there was no left node so 'current' points to the smallest node
                //(the one that must be now deleted
                currentItem.setNextListItem(anotherCurrent.nextListItem());
            } else {
                //set the smallest node's parent to point to the smallest node's right child (might be null
                leftMostParent.setPreviousListItem(anotherCurrent.nextListItem());
            }
        }
    }

    @Override
    public void traverse(UdemyListItem root) {
        //recursive method
        if(root !=null){
            traverse(root.previousListItem());
            System.out.println(root.getValue());
            traverse(root.nextListItem());
        }
    }
}
