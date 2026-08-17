package com.android.tools.r8.internal;

import com.android.tools.r8.internal.AbstractC1314dQ;
import java.util.Collections;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Function;
import java.util.function.ObjIntConsumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.hQ, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1656hQ {
    public static boolean a(InterfaceC1231cQ interfaceC1231cQ, Object obj) {
        if (obj == interfaceC1231cQ) {
            return true;
        }
        if (obj instanceof InterfaceC1231cQ) {
            InterfaceC1231cQ interfaceC1231cQ2 = (InterfaceC1231cQ) obj;
            if (interfaceC1231cQ.size() == interfaceC1231cQ2.size() && interfaceC1231cQ.entrySet().size() == interfaceC1231cQ2.entrySet().size()) {
                for (AbstractC1314dQ abstractC1314dQ : interfaceC1231cQ2.entrySet()) {
                    if (interfaceC1231cQ.b(abstractC1314dQ.b()) != abstractC1314dQ.a()) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public static boolean a(final InterfaceC1231cQ interfaceC1231cQ, InterfaceC1231cQ interfaceC1231cQ2) {
        if (interfaceC1231cQ2.isEmpty()) {
            return false;
        }
        Objects.requireNonNull(interfaceC1231cQ);
        interfaceC1231cQ2.a(new ObjIntConsumer() { // from class: y2h
            @Override // java.util.function.ObjIntConsumer
            public final void accept(Object obj, int i) {
                interfaceC1231cQ.a(obj, i);
            }
        });
        return true;
    }

    public static C1079ae a(InterfaceC1231cQ interfaceC1231cQ) {
        Spliterator spliterator = interfaceC1231cQ.entrySet().spliterator();
        return AbstractC1165be.a(spliterator, new Function() { // from class: z2h
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                AbstractC1314dQ abstractC1314dQ = (AbstractC1314dQ) obj;
                return Collections.nCopies(abstractC1314dQ.a(), abstractC1314dQ.b()).spliterator();
            }
        }, (spliterator.characteristics() & 1296) | 64, interfaceC1231cQ.size());
    }
}
