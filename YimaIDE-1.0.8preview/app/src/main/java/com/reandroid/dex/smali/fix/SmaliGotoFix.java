package com.reandroid.dex.smali.fix;

import com.reandroid.dex.ins.Opcode;
import com.reandroid.dex.smali.fix.SmaliGotoFix;
import com.reandroid.dex.smali.model.SmaliInstruction;
import com.reandroid.dex.smali.model.SmaliMethod;
import com.reandroid.utils.collection.CollectionUtil;
import com.reandroid.utils.collection.FilterIterator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliGotoFix {
    private final SmaliMethod smaliMethod;

    public SmaliGotoFix(SmaliMethod smaliMethod) {
        this.smaliMethod = smaliMethod;
    }

    public static /* synthetic */ boolean a(SmaliInstruction smaliInstruction) {
        Opcode<?> opcode = smaliInstruction.getOpcode();
        return opcode == Opcode.GOTO || opcode == Opcode.GOTO_16;
    }

    private boolean fix(SmaliInstruction smaliInstruction) {
        Opcode<?> replacement = getReplacement(smaliInstruction);
        if (replacement == null) {
            return false;
        }
        smaliInstruction.replaceOpcode(replacement);
        return true;
    }

    private Opcode<?> getReplacement(SmaliInstruction smaliInstruction) {
        long dataAsLong = smaliInstruction.getDataAsLong();
        if (dataAsLong < -128 || dataAsLong > 127) {
            return (dataAsLong < -32768 || dataAsLong > 32767) ? Opcode.GOTO_32 : Opcode.GOTO_16;
        }
        return null;
    }

    public void apply() {
        List list = CollectionUtil.toList(FilterIterator.of(this.smaliMethod.getInstructions(), new Predicate() { // from class: vgd
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return SmaliGotoFix.a((SmaliInstruction) obj);
            }
        }));
        if (list.isEmpty()) {
            return;
        }
        Iterator it = list.iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (fix((SmaliInstruction) it.next())) {
                z = true;
            }
        }
        if (z) {
            this.smaliMethod.getCodeSet().updateAddresses();
        }
    }
}
