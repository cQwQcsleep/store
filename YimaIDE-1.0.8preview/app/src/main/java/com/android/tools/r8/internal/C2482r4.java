package com.android.tools.r8.internal;

import com.android.tools.r8.profile.art.ArtProfileMethodRuleBuilder;
import com.android.tools.r8.references.MethodReference;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.r4, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2482r4 implements ArtProfileMethodRuleBuilder {
    public final /* synthetic */ C1975l7 a;
    public final /* synthetic */ C2568s4 b;

    public C2482r4(C2568s4 c2568s4, C1975l7 c1975l7) {
        this.b = c2568s4;
        this.a = c1975l7;
    }

    @Override // com.android.tools.r8.profile.art.ArtProfileMethodRuleBuilder
    public final ArtProfileMethodRuleBuilder setMethodReference(MethodReference methodReference) {
        this.a.a(methodReference);
        return this;
    }

    @Override // com.android.tools.r8.profile.art.ArtProfileMethodRuleBuilder
    public final ArtProfileMethodRuleBuilder setMethodRuleInfo(Consumer consumer) {
        C2140n4.a aVarA = C2140n4.a();
        consumer.accept(aVarA);
        C2140n4 c2140n4A = aVarA.a();
        try {
            OutputStreamWriter outputStreamWriter = this.b.a;
            if (c2140n4A.isHot()) {
                outputStreamWriter.write(72);
            }
            if (c2140n4A.isStartup()) {
                outputStreamWriter.write(83);
            }
            if (c2140n4A.isPostStartup()) {
                outputStreamWriter.write(80);
            }
            return this;
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }
}
