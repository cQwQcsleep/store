package com.android.tools.r8.internal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Jr, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0600Jr extends AbstractC0574Ir implements UN {
    public C0494Fp c = C0494Fp.d;
    public boolean d;

    public final void a(Lr lr) {
        Dc0 dc0;
        if (!this.d) {
            this.c = this.c.m10clone();
            this.d = true;
        }
        C0494Fp c0494Fp = this.c;
        C0494Fp c0494Fp2 = lr.b;
        c0494Fp.getClass();
        int i = 0;
        while (true) {
            int size = c0494Fp2.a.c.size();
            dc0 = c0494Fp2.a;
            if (i >= size) {
                break;
            }
            c0494Fp.b((Map.Entry) dc0.c.get(i));
            i++;
        }
        Iterator it = (dc0.d.isEmpty() ? Jc0.b : dc0.d.entrySet()).iterator();
        while (it.hasNext()) {
            c0494Fp.b((Map.Entry) it.next());
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final AbstractC0600Jr a(C0703Nr c0703Nr, Serializable serializable) {
        Object objB;
        if (c0703Nr.a == d()) {
            if (!this.d) {
                this.c = this.c.m10clone();
                this.d = true;
            }
            C0494Fp c0494Fp = this.c;
            C0677Mr c0677Mr = c0703Nr.d;
            if (c0677Mr.d) {
                if (c0677Mr.c.b == Om0.j) {
                    objB = serializable;
                    ArrayList arrayList = new ArrayList();
                    Iterator it = ((List) serializable).iterator();
                    while (it.hasNext()) {
                        arrayList.add(c0703Nr.b(it.next()));
                    }
                    objB = arrayList;
                }
            } else {
                objB = c0703Nr.b(serializable);
            }
            objB = serializable;
            c0494Fp.c(c0677Mr, objB);
            return this;
        }
        w01.a("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
        return null;
    }
}
