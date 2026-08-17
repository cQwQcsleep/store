package com.reandroid.dex.ins;

import com.reandroid.dex.ins.ExtraLine;
import com.reandroid.dex.smali.SmaliWriter;
import java.io.IOException;
import java.util.Comparator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ExtraLine {
    public static final Comparator<ExtraLine> COMPARATOR = new Comparator() { // from class: dk4
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return ((ExtraLine) obj).compareExtraLine((ExtraLine) obj2);
        }
    };
    public static final int ORDER_CATCH = 4;
    public static final int ORDER_DEBUG_LINE = 1;
    public static final int ORDER_DEBUG_LINE_NUMBER = 0;
    public static final int ORDER_EXCEPTION_HANDLER = 3;
    public static final int ORDER_INSTRUCTION_LABEL = 5;
    public static final int ORDER_TRY_END = 2;
    public static final int ORDER_TRY_START = 6;

    void appendExtra(SmaliWriter smaliWriter) throws IOException;

    default int compareExtraLine(ExtraLine extraLine) {
        int sortOrder = getSortOrder();
        int sortOrder2 = extraLine.getSortOrder();
        if (sortOrder < sortOrder2) {
            return -1;
        }
        if (sortOrder2 < sortOrder) {
            return 1;
        }
        int sortOrderFine = getSortOrderFine();
        int sortOrderFine2 = extraLine.getSortOrderFine();
        if (sortOrderFine < sortOrderFine2) {
            return -1;
        }
        return sortOrderFine2 < sortOrderFine ? 1 : 0;
    }

    int getSortOrder();

    default int getSortOrderFine() {
        return 0;
    }

    int getTargetAddress();

    Ins getTargetIns();

    boolean isEqualExtraLine(Object obj);

    boolean isRemoved();

    void setTargetAddress(int i);

    void setTargetIns(Ins ins);

    default void updateTarget() {
        Ins targetIns = getTargetIns();
        if (targetIns == null || targetIns.isRemoved()) {
            return;
        }
        setTargetAddress(targetIns.getAddress());
    }
}
