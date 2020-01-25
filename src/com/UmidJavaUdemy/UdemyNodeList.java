package com.UmidJavaUdemy;

public interface UdemyNodeList {
    UdemyListItem getRoot();
    boolean addItem(UdemyListItem item);
    boolean removeItem(UdemyListItem item);
    void traverse(UdemyListItem root);
}
