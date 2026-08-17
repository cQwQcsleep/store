package com.reandroid.dex.smali.model;

import com.reandroid.dex.smali.SmaliDirective;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.smali.model.SmaliMethod;
import com.reandroid.utils.collection.FilterIterator;
import java.io.IOException;
import java.util.Iterator;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliMethodSet extends SmaliDefSet<SmaliMethod> {
    public static SmaliMethodSet read(SmaliReader smaliReader) throws IOException {
        SmaliMethodSet smaliMethodSet = new SmaliMethodSet();
        smaliMethodSet.parse(smaliReader);
        if (smaliMethodSet.isEmpty()) {
            return null;
        }
        return smaliMethodSet;
    }

    @Override // com.reandroid.dex.smali.model.SmaliDefSet, com.reandroid.dex.smali.model.SmaliSet, com.reandroid.dex.smali.model.Smali, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        Iterator<SmaliMethod> directMethods = getDirectMethods();
        boolean z = false;
        boolean z2 = false;
        while (directMethods.hasNext()) {
            smaliWriter.newLineDouble();
            if (!z2) {
                smaliWriter.appendComment("direct methods");
                smaliWriter.newLine();
            }
            directMethods.next().append(smaliWriter);
            z2 = true;
        }
        Iterator<SmaliMethod> virtualMethods = getVirtualMethods();
        while (virtualMethods.hasNext()) {
            smaliWriter.newLineDouble();
            if (!z) {
                smaliWriter.appendComment("virtual methods");
                smaliWriter.newLine();
            }
            virtualMethods.next().append(smaliWriter);
            z = true;
        }
    }

    @Override // com.reandroid.dex.smali.model.SmaliDefSet
    public SmaliMethod createNew() {
        return new SmaliMethod();
    }

    public Iterator<SmaliMethod> getDirectMethods() {
        return FilterIterator.of(iterator(), new Predicate() { // from class: wgd
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((SmaliMethod) obj).isDirect();
            }
        });
    }

    @Override // com.reandroid.dex.smali.SmaliRegion
    public SmaliDirective getSmaliDirective() {
        return SmaliDirective.METHOD;
    }

    public Iterator<SmaliMethod> getVirtualMethods() {
        return FilterIterator.of(iterator(), new Predicate() { // from class: xgd
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((SmaliMethod) obj).isVirtual();
            }
        });
    }
}
