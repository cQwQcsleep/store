package com.reandroid.dex.smali.formatters;

import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.model.DexDeclaration;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.smali.SmaliWriterSetting;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.collection.CollectionUtil;
import com.reandroid.utils.collection.ComputeIterator;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface SmaliComment {
    static void writeDeclarationComment(SmaliWriter smaliWriter, String str, Iterator<? extends DexDeclaration> it) throws IOException {
        int i;
        int maximumCommentLines;
        List list = CollectionUtil.toList(ComputeIterator.of(it, new Function() { // from class: sgd
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((DexDeclaration) obj).getDefining();
            }
        }));
        list.sort(CompareUtil.getComparableComparator());
        int size = list.size();
        SmaliWriterSetting writerSetting = smaliWriter.getWriterSetting();
        if (writerSetting == null || (maximumCommentLines = writerSetting.getMaximumCommentLines()) < 0 || maximumCommentLines >= size) {
            i = 0;
        } else {
            i = maximumCommentLines != 0 ? size - maximumCommentLines : 0;
            size = maximumCommentLines;
        }
        for (int i2 = 0; i2 < size; i2++) {
            smaliWriter.newLine();
            TypeKey typeKey = (TypeKey) list.get(i2);
            smaliWriter.appendComment(str);
            smaliWriter.appendComment(typeKey.getTypeName());
        }
        if (i != 0) {
            smaliWriter.newLine();
            smaliWriter.appendComment(str + " +" + i + " more");
        }
    }
}
