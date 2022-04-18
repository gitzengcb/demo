package com.example.server.utils;

import com.example.server.pojo.Classification;
import com.example.server.pojo.Groups;

import java.util.ArrayList;
import java.util.List;

public class Treelist {
    private List<Classification> tree(List<Classification> alllist) {
        List<Classification> listtree = new ArrayList<>();
        for (Classification classification:alllist){
            if (classification.getSuperiorid()==0){
                classification.setClasslist(treelist(alllist,classification.getId()));
                listtree.add(classification);
            }
        }
        return listtree;
    }

    private List<Classification> treelist(List<Classification> alllist, Integer id) {
        List<Classification> zitree = new ArrayList<>();
        for (Classification ov:alllist){
            if (ov.getSuperiorid().equals(id)){
                ov.setClasslist(treelist(alllist,ov.getId()));
                zitree.add(ov);
            }
        }
        return zitree;
    }
}
