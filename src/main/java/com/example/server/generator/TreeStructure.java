package com.example.server.generator;

import com.example.server.pojo.Classification;

import java.util.ArrayList;
import java.util.List;

/**
* 数结构方法
**/
public class TreeStructure {
    public List<Classification> tree(List<Classification> alllist) {
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
