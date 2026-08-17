package com.reandroid.arsc.list;

import com.reandroid.arsc.chunk.StagedAlias;
import com.reandroid.arsc.container.BlockList;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class StagedAliasList extends BlockList<StagedAlias> {
    private StagedAlias pickOne() {
        for (StagedAlias stagedAlias : getChildes()) {
            if (stagedAlias != null) {
                return stagedAlias;
            }
        }
        return null;
    }

    public void merge(StagedAliasList stagedAliasList) {
        if (stagedAliasList == null || stagedAliasList == this || stagedAliasList.size() == 0) {
            return;
        }
        StagedAlias stagedAliasPickOne = pickOne();
        if (stagedAliasPickOne == null) {
            stagedAliasPickOne = new StagedAlias();
            add(stagedAliasPickOne);
        }
        Iterator it = stagedAliasList.getChildes().iterator();
        while (it.hasNext()) {
            stagedAliasPickOne.merge((StagedAlias) it.next());
        }
    }
}
