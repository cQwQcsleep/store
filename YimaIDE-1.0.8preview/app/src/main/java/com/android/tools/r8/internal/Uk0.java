package com.android.tools.r8.internal;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Uk0 extends Wk0 {
    public final /* synthetic */ Method b;

    public Uk0(Method method) {
        this.b = method;
    }

    @Override // com.android.tools.r8.internal.Wk0
    public final Object a(Class cls) {
        String strConcat;
        int modifiers = cls.getModifiers();
        if (Modifier.isInterface(modifiers)) {
            strConcat = "Interfaces can't be instantiated! Register an InstanceCreator or a TypeAdapter for this type. Interface name: ".concat(cls.getName());
        } else {
            strConcat = Modifier.isAbstract(modifiers) ? "Abstract classes can't be instantiated! Register an InstanceCreator or a TypeAdapter for this type. Class name: ".concat(cls.getName()) : null;
        }
        if (strConcat == null) {
            return this.b.invoke(null, cls, Object.class);
        }
        x01.a("UnsafeAllocator is used for non-instantiable type: ".concat(strConcat));
        return null;
    }
}
