package com.safeway.postest.Data.model;

import java.util.List;

public class ItemIdRequest {

    private List<List<Object>> item_ids;
    private List<Item_ids_remove_list> items_ids;

//    public ItemIdRequest(List<item_ids_remove_list> items_list) {
//        this.items_ids = items_list;
//    }
//
//    public void setItems_list(List<item_ids_remove_list> items_list) {
//        this.items_ids = items_list;
//    }
//
//    public List<item_ids_remove_list> getItems_list() {
//        return items_ids;
//    }

    public ItemIdRequest(List<List<Object>> item_ids){
        this.item_ids = item_ids;
    }

    public List<List<Object>> getItem_ids() {
        return item_ids;
    }

    public void setItem_ids(List<List<Object>> item_ids) {
        this.item_ids = item_ids;
    }
}
