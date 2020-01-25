package com.UmidJavaUdemy;

public class UdemyNode extends UdemyListItem {
    public UdemyNode(Object value) {
        super(value);
    }

    @Override
    UdemyListItem nextListItem() {
        return this.rightItem;
    }

    @Override
    UdemyListItem setNextListItem(UdemyListItem udemyListItem) {
        this.rightItem = udemyListItem;
        return this.rightItem;
    }

    @Override
    UdemyListItem previousListItem() {
        return this.leftItem;
    }

    @Override
    UdemyListItem setPreviousListItem(UdemyListItem udemyListItem) {
        this.leftItem = udemyListItem;
        return this.leftItem;
    }

    @Override
    int compareTo(UdemyListItem udemyListItem) {
        if(udemyListItem != null){
            return ((String) super.getValue()).compareTo((String) udemyListItem.getValue());
        }
        else {
            return -1;
        }
    }
}
