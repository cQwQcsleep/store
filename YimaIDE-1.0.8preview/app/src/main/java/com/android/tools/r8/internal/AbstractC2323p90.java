package com.android.tools.r8.internal;

import com.android.tools.r8.DataResource;
import com.reandroid.arsc.chunk.TypeBlock;
import defpackage.l0i;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: renamed from: com.android.tools.r8.internal.p90, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2323p90 implements InterfaceC0952Xg, Serializable {
    public final InterfaceC0952Xg b;

    public AbstractC2323p90(InterfaceC0952Xg interfaceC0952Xg) {
        this.b = interfaceC0952Xg;
        if (interfaceC0952Xg == null || interfaceC0952Xg.getContext() == C0750Pm.b) {
            return;
        }
        w01.a("Coroutines with restricted suspension must have EmptyCoroutineContext");
        throw null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v7 */
    @Override // com.android.tools.r8.internal.InterfaceC0952Xg
    public final void a(Object obj) {
        ?? r1 = this;
        while (true) {
            AbstractC2323p90 abstractC2323p90 = (AbstractC2323p90) r1;
            InterfaceC0952Xg interfaceC0952Xg = abstractC2323p90.b;
            KB.a(interfaceC0952Xg);
            try {
                Object objC = abstractC2323p90.c(obj);
                if (objC == EnumC1171bh.b) {
                    return;
                }
                obj = objC;
                if (!(interfaceC0952Xg instanceof AbstractC2323p90)) {
                    interfaceC0952Xg.a(obj);
                    return;
                }
                r1 = interfaceC0952Xg;
            } catch (Throwable th) {
                obj = new C2493r90(th);
            }
        }
    }

    public abstract Object c(Object obj);

    public void c() {
    }

    @Override // com.android.tools.r8.internal.InterfaceC0952Xg
    public final C0750Pm getContext() {
        return C0750Pm.b;
    }

    public String toString() {
        int iIntValue;
        String strC;
        StringBuilder sb = new StringBuilder("Continuation at ");
        InterfaceC0693Nh interfaceC0693Nh = (InterfaceC0693Nh) getClass().getAnnotation(InterfaceC0693Nh.class);
        Object name = null;
        str = null;
        str = null;
        str = null;
        String str = null;
        if (interfaceC0693Nh != null) {
            int iV = interfaceC0693Nh.v();
            if (iV > 1) {
                l0i.a("Debug metadata version mismatch. Expected: 1, got ", iV, ". Please update the Kotlin standard library.");
                return null;
            }
            try {
                Field declaredField = getClass().getDeclaredField("label");
                declaredField.setAccessible(true);
                Object obj = declaredField.get(this);
                Integer num = obj instanceof Integer ? (Integer) obj : null;
                iIntValue = (num != null ? num.intValue() : 0) - 1;
            } catch (Exception unused) {
                iIntValue = -1;
            }
            int i = iIntValue >= 0 ? interfaceC0693Nh.l()[iIntValue] : -1;
            C1655hP c1655hP = AbstractC1741iP.b;
            if (c1655hP == null) {
                try {
                    C1655hP c1655hP2 = new C1655hP(Class.class.getDeclaredMethod("getModule", null), getClass().getClassLoader().loadClass("java.lang.Module").getDeclaredMethod("getDescriptor", null), getClass().getClassLoader().loadClass("java.lang.module.ModuleDescriptor").getDeclaredMethod(TypeBlock.NAME_name, null));
                    AbstractC1741iP.b = c1655hP2;
                    c1655hP = c1655hP2;
                } catch (Exception unused2) {
                    c1655hP = AbstractC1741iP.a;
                    AbstractC1741iP.b = c1655hP;
                }
            }
            if (c1655hP != AbstractC1741iP.a) {
                Method method = c1655hP.a;
                Object objInvoke = method != null ? method.invoke(getClass(), null) : null;
                if (objInvoke != null) {
                    Method method2 = c1655hP.b;
                    Object objInvoke2 = method2 != null ? method2.invoke(objInvoke, null) : null;
                    if (objInvoke2 != null) {
                        Method method3 = c1655hP.c;
                        Object objInvoke3 = method3 != null ? method3.invoke(objInvoke2, null) : null;
                        if (objInvoke3 instanceof String) {
                            str = (String) objInvoke3;
                        }
                    }
                }
            }
            if (str == null) {
                strC = interfaceC0693Nh.c();
            } else {
                strC = str + DataResource.SEPARATOR + interfaceC0693Nh.c();
            }
            name = new StackTraceElement(strC, interfaceC0693Nh.m(), interfaceC0693Nh.f(), i);
        }
        if (name == null) {
            name = getClass().getName();
        }
        sb.append(name);
        return sb.toString();
    }

    public InterfaceC0952Xg a(Va0 va0, Va0 va1) {
        throw new UnsupportedOperationException("create(Any?;Continuation) has not been overridden");
    }
}
