package com.android.tools.r8.internal;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Sk0 extends Wk0 {
    public final /* synthetic */ Method b;
    public final /* synthetic */ Object c;

    public Sk0(Method method, Object obj) {
        this.b = method;
        this.c = obj;
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
            return this.b.invoke(this.c, cls);
        }
        x01.a("UnsafeAllocator is used for non-instantiable type: ".concat(strConcat));
        return null;
    }
}
