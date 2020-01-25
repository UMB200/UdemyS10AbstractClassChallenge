package com.UmidJavaUdemy;

public class UdemyLinkList implements UdemyNodeList {
    private UdemyListItem root = null;

    public UdemyLinkList(UdemyListItem root) {
        this.root = root;
    }

    @Override
    public UdemyListItem getRoot() {
        return this.root;
    }

    @Override
    public boolean addItem(UdemyListItem newItem) {
        if(this.root == null){
            //The list was empty thus, this item becomes head of the list
            this.root = newItem;
            return true;
        }
        UdemyListItem currentItem = this.root;
        while (currentItem != null){
            int compared = (currentItem.compareTo(newItem));
            if(compared <0){
                //newItem is greater move right if possible
                if(currentItem.nextListItem() !=null){
                    currentItem =currentItem.nextListItem();
                } else {
                    //there is no next, thus insert to the end of the list
                    currentItem.setNextListItem(newItem).setPreviousListItem(currentItem);
                    return true;
                }
            }else if(compared >0){
                //newItem is less insert before
                if(currentItem.previousListItem() !=null){
                   currentItem.previousListItem().setNextListItem(newItem).setPreviousListItem(currentItem.previousListItem());
                   newItem.setNextListItem(currentItem).setPreviousListItem(newItem);
                } else {
                    // node with the previous is the root
                    newItem.setNextListItem(this.root).setPreviousListItem(newItem);
                    this.root = newItem;
                } return true;

            } else {
                //duplicate
                System.out.println(newItem.getValue() +  " is already present, not added");
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean removeItem(UdemyListItem itemToDelete) {
        if(itemToDelete != null){
            System.out.println("Deleting item "+ itemToDelete.getValue());
        }
        UdemyListItem currentItem = this.root;
        while (currentItem != null){
            int compared = currentItem.compareTo(itemToDelete);
            if(compared == 0){
                //found item to delete
                if(currentItem == this.root){
                    this.root = currentItem.nextListItem();
                } else {
                    currentItem.previousListItem().setNextListItem(currentItem.nextListItem());
                    if(currentItem.nextListItem() !=null ){
                        currentItem.nextListItem().setPreviousListItem(currentItem.previousListItem());
                    }
                }return true;
            }else if(compared < 0){
                currentItem = currentItem.nextListItem();
            } else { // compared >0
                //We are at an item greater than item to delete, which means item is not in the list
                return false;
            }
        }
        //reached end of the list and did not find the item to delete
        return false;
    }

    @Override
    public void traverse(UdemyListItem listItem) {
        if(listItem == null){
            System.out.println("The list is empty");
        } else {
            while (listItem !=null){
                System.out.println(listItem.getValue());
                listItem = listItem.nextListItem();
            }
        }

    }
}
