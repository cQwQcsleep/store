package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class MG {
    public static final /* synthetic */ boolean f = true;
    public AbstractC2515rV a = C2345pV.b;
    public HG b = HG.k;
    public IG c = IG.b;
    public QG d = OG.b;
    public LG e = JG.a;

    /* JADX WARN: Code duplicated, block: B:12:0x0027  */
    /* JADX WARN: Code duplicated, block: B:7:0x0015  */
    /* JADX WARN: Code duplicated, block: B:9:0x001d  */
    public final NG a() {
        QG qg;
        QG qg2 = this.d;
        IG ig = this.c;
        ig.getClass();
        if (IG.c == ig) {
            if (!this.d.b()) {
                qg = this.d;
                qg.getClass();
                if (!(qg instanceof PG)) {
                    defpackage.l0.a("Method constructor pattern must match 'void' type.");
                    return null;
                }
            }
            qg2 = PG.a;
        } else {
            IG ig2 = this.c;
            ig2.getClass();
            if (IG.d == ig2) {
                if (!this.d.b()) {
                    qg = this.d;
                    qg.getClass();
                    if (!(qg instanceof PG)) {
                        defpackage.l0.a("Method constructor pattern must match 'void' type.");
                        return null;
                    }
                }
                qg2 = PG.a;
            }
        }
        return new NG(this.a, this.b, this.c, qg2, this.e);
    }

    public final MG a(FG fg) {
        if (!f && !fg.g()) {
            x1f.a();
            return null;
        }
        HG hg = HG.k;
        this.b = ((GG) new GG().a(fg.d())).c();
        return this;
    }
}
