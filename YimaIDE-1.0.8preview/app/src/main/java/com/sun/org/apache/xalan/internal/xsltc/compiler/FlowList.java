package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.BranchHandle;
import com.sun.org.apache.bcel.internal.generic.InstructionHandle;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class FlowList {
    private List<InstructionHandle> _elements;

    public FlowList(InstructionHandle instructionHandle) {
        ArrayList arrayList = new ArrayList();
        this._elements = arrayList;
        arrayList.add(instructionHandle);
    }

    public FlowList add(InstructionHandle instructionHandle) {
        if (this._elements == null) {
            this._elements = new ArrayList();
        }
        this._elements.add(instructionHandle);
        return this;
    }

    public FlowList append(FlowList flowList) {
        if (this._elements == null) {
            this._elements = flowList._elements;
            return this;
        }
        List<InstructionHandle> list = flowList._elements;
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                this._elements.add(list.get(i));
            }
        }
        return this;
    }

    public void backPatch(InstructionHandle instructionHandle) {
        List<InstructionHandle> list = this._elements;
        if (list == null) {
            return;
        }
        int size = list.size();
        int i = 0;
        while (true) {
            List<InstructionHandle> list2 = this._elements;
            if (i >= size) {
                list2.clear();
                return;
            } else {
                ((BranchHandle) list2.get(i)).setTarget(instructionHandle);
                i++;
            }
        }
    }

    public FlowList copyAndRedirect(InstructionList instructionList, InstructionList instructionList2) {
        FlowList flowList = new FlowList();
        List<InstructionHandle> list = this._elements;
        if (list != null) {
            int size = list.size();
            Iterator<InstructionHandle> it = instructionList2.iterator();
            for (InstructionHandle instructionHandle : instructionList) {
                InstructionHandle next = it.next();
                for (int i = 0; i < size; i++) {
                    if (this._elements.get(i) == instructionHandle) {
                        flowList.add(next);
                    }
                }
            }
        }
        return flowList;
    }

    public FlowList() {
        this._elements = null;
    }

    public FlowList(FlowList flowList) {
        this._elements = flowList._elements;
    }
}
