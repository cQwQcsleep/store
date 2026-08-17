package com.android.tools.r8.retrace;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.internal.AbstractC1759id0;
import com.android.tools.r8.internal.C1928kd0;
import com.android.tools.r8.references.ClassReference;
import com.android.tools.r8.references.Reference;
import com.android.tools.r8.references.TypeReference;
import com.android.tools.r8.retrace.StackTraceElementProxy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class StackTraceElementProxy<T, ST extends StackTraceElementProxy<T, ST>> {
    /* JADX INFO: Access modifiers changed from: private */
    public static void a(MappingSupplierBase mappingSupplierBase, DiagnosticsHandler diagnosticsHandler, TypeReference typeReference) {
        if (typeReference.isArray()) {
            typeReference = typeReference.asArray().getBaseType();
        }
        if (typeReference.isClass()) {
            mappingSupplierBase.mo19registerClassUse(diagnosticsHandler, typeReference.asClass());
        }
    }

    public abstract ClassReference getClassReference();

    public abstract String getFieldName();

    public abstract String getFieldOrReturnType();

    public abstract int getLineNumber();

    public List<TypeReference> getMethodArgumentTypeReferences() {
        if (!hasMethodArguments()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        C1928kd0 c1928kd0A = C1928kd0.a(",\\s*");
        String methodArguments = getMethodArguments();
        methodArguments.getClass();
        Iterator itA = c1928kd0A.c.a(c1928kd0A, methodArguments);
        while (true) {
            AbstractC1759id0 abstractC1759id0 = (AbstractC1759id0) itA;
            if (!abstractC1759id0.hasNext()) {
                return arrayList;
            }
            arrayList.add(Reference.typeFromTypeName((String) abstractC1759id0.next()));
        }
    }

    public abstract String getMethodArguments();

    public abstract String getMethodName();

    public abstract String getSourceFile();

    public abstract boolean hasClassName();

    public abstract boolean hasFieldName();

    public abstract boolean hasFieldOrReturnType();

    public abstract boolean hasLineNumber();

    public abstract boolean hasMethodArguments();

    public abstract boolean hasMethodName();

    public abstract boolean hasSourceFile();

    public void registerUses(final MappingSupplierBase<?> mappingSupplierBase, final DiagnosticsHandler diagnosticsHandler) {
        if (hasClassName()) {
            mappingSupplierBase.mo19registerClassUse(diagnosticsHandler, getClassReference());
        }
        if (hasMethodArguments()) {
            getMethodArgumentTypeReferences().forEach(new Consumer() { // from class: lld
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    StackTraceElementProxy.a(mappingSupplierBase, diagnosticsHandler, (TypeReference) obj);
                }
            });
        }
        if (!hasFieldOrReturnType() || getFieldOrReturnType().equals("void")) {
            return;
        }
        TypeReference typeReferenceTypeFromTypeName = Reference.typeFromTypeName(getFieldOrReturnType());
        if (typeReferenceTypeFromTypeName.isArray()) {
            typeReferenceTypeFromTypeName = typeReferenceTypeFromTypeName.asArray().getBaseType();
        }
        if (typeReferenceTypeFromTypeName.isClass()) {
            mappingSupplierBase.mo19registerClassUse(diagnosticsHandler, typeReferenceTypeFromTypeName.asClass());
        }
    }

    public abstract T toRetracedItem(RetraceStackTraceElementProxy<T, ST> retraceStackTraceElementProxy, boolean z);
}
