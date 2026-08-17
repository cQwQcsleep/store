package com.android.tools.r8.utils.structural;

import com.android.tools.r8.utils.structural.x;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface x<T extends x<T>> extends s<T> {
    T R();

    default void a(o oVar) {
        x xVarR = R();
        y<T> yVarO = R().o();
        q qVar = (q) oVar;
        qVar.getClass();
        yVarO.a(new p(xVarR, qVar));
    }

    y<T> o();

    default int a(T t, t tVar) {
        x xVarR = R();
        defpackage.e eVar = new defpackage.e();
        if (xVarR == t) {
            return 0;
        }
        return eVar.a(xVarR, t, new f(tVar));
    }

    default int a(T t, AbstractC3519a abstractC3519a) {
        return abstractC3519a.a((T) R(), t, R().o());
    }

    default void a(n nVar) {
        i.a(R(), nVar, new defpackage.h());
    }

    default void a(m mVar, t tVar) {
        new defpackage.h().a(R(), new q(mVar, tVar));
    }

    @Override // com.android.tools.r8.utils.structural.s, java.lang.Comparable
    /* JADX INFO: renamed from: a */
    default int compareTo(T t) {
        return h.a(R(), t, new defpackage.e());
    }
}
