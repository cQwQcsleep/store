package com.reandroid.dex.ins;

import com.reandroid.dex.base.DexException;
import com.reandroid.dex.data.InstructionList;
import com.reandroid.dex.data.MethodDef;
import com.reandroid.dex.id.MethodId;
import com.reandroid.dex.sections.DexLayoutBlock;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class InstructionException extends DexException {
    private final Ins ins;

    public InstructionException(String str, Ins ins) {
        super(str);
        this.ins = ins;
    }

    private void appendDex(StringBuilder sb) {
        String simpleName;
        DexLayoutBlock dexLayoutBlock = (DexLayoutBlock) getIns().getParentInstance(DexLayoutBlock.class);
        if (dexLayoutBlock == null || (simpleName = dexLayoutBlock.getDexContainerBlock().getSimpleName()) == null) {
            return;
        }
        sb.append("dex = ");
        sb.append(simpleName);
        sb.append(", ");
    }

    private boolean appendMethod(StringBuilder sb) {
        MethodDef methodDef;
        MethodId id;
        InstructionList instructionList = getIns().getInstructionList();
        if (instructionList == null || (methodDef = instructionList.getCodeItem().getMethodDef()) == null || (id = methodDef.getId()) == null) {
            return false;
        }
        sb.append("class = ");
        sb.append(id.getDefining());
        sb.append(", method = ");
        sb.append(id.getName());
        sb.append(id.getProtoId());
        sb.append(" { ... ");
        sb.append(getIns());
        sb.append(" ... }");
        return true;
    }

    public Ins getIns() {
        return this.ins;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public String getMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append(super/*java.lang.Throwable*/.getMessage());
        sb.append(", ");
        appendDex(sb);
        if (!appendMethod(sb)) {
            sb.append(getIns());
        }
        return sb.toString();
    }
}
