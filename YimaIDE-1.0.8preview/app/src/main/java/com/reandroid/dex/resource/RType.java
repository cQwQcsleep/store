package com.reandroid.dex.resource;

import com.reandroid.dex.model.DexClass;
import com.reandroid.dex.model.DexField;
import com.reandroid.dex.model.DexMethod;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.collection.ComputeIterator;
import java.io.IOException;
import java.util.Iterator;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class RType extends RTypeItem implements Iterable<REntry> {
    public RType(DexClass dexClass) {
        super(dexClass);
    }

    private boolean isEntriesValid() {
        Iterator<REntry> it = iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (!it.next().isValid()) {
                return false;
            }
            z = true;
        }
        return z;
    }

    @Override // com.reandroid.dex.resource.RTypeItem
    public void appendJavaEntries(SmaliWriter smaliWriter) throws IOException {
        for (REntry rEntry : this) {
            smaliWriter.newLine();
            rEntry.appendJava(smaliWriter);
        }
    }

    public REntry create(DexField dexField) {
        return new REntry(dexField);
    }

    public boolean isEmpty() {
        return !iterator().hasNext();
    }

    @Override // com.reandroid.dex.resource.RTypeItem
    public boolean isValid() {
        DexClass dexClass = getDexClass();
        if (dexClass.getInstanceFields().hasNext()) {
            return false;
        }
        Iterator<DexMethod> declaredMethods = dexClass.getDeclaredMethods();
        while (declaredMethods.hasNext()) {
            if (!declaredMethods.next().isConstructor()) {
                return false;
            }
        }
        return isEntriesValid();
    }

    @Override // java.lang.Iterable
    public Iterator<REntry> iterator() {
        return ComputeIterator.of(getDexClass().getStaticFields(), new Function() { // from class: u5c
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.create((DexField) obj);
            }
        });
    }
}
