package com.UmidJavaUdemy;

public abstract class UdemyListItem {
    protected UdemyListItem rightItem = null;
    protected UdemyListItem leftItem = null;

    protected Object value;

    public UdemyListItem(Object value) {
        this.value = value;
    }

    abstract UdemyListItem nextListItem();
    abstract UdemyListItem setNextListItem(UdemyListItem udemyListItem);
    abstract UdemyListItem previousListItem();
    abstract UdemyListItem setPreviousListItem(UdemyListItem udemyListItem);

    abstract int compareTo(UdemyListItem udemyListItem);

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
