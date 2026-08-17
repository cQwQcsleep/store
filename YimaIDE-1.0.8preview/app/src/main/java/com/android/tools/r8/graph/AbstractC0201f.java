package com.android.tools.r8.graph;

/* JADX INFO: renamed from: com.android.tools.r8.graph.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0201f {
    public static final /* synthetic */ boolean b = true;
    public final AbstractC0208g a;

    public AbstractC0201f(AbstractC0208g abstractC0208g) {
        this.a = abstractC0208g;
    }

    public abstract AbstractC0201f a();

    public final AbstractC0201f a(boolean z) {
        AbstractC0208g abstractC0208g = this.a;
        if (z) {
            abstractC0208g.y();
        } else {
            abstractC0208g.D();
        }
        return a();
    }
}
