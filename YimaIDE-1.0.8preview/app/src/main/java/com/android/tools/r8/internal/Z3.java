package com.android.tools.r8.internal;

import com.android.tools.r8.TextInputStream;
import com.android.tools.r8.internal.Z3;
import com.android.tools.r8.profile.art.ArtProfileBuilder;
import com.android.tools.r8.startup.StartupClassBuilder;
import com.android.tools.r8.startup.StartupMethodBuilder;
import com.android.tools.r8.startup.StartupProfileBuilder;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Z3 implements ArtProfileBuilder {
    public final /* synthetic */ StartupProfileBuilder a;

    public Z3(StartupProfileBuilder startupProfileBuilder) {
        this.a = startupProfileBuilder;
    }

    public static void a(C1033a4 c1033a4, StartupClassBuilder startupClassBuilder) {
        startupClassBuilder.setClassReference(c1033a4.a);
    }

    @Override // com.android.tools.r8.profile.art.ArtProfileBuilder
    public final ArtProfileBuilder addClassRule(Consumer consumer) {
        final C1033a4 c1033a4 = new C1033a4();
        consumer.accept(c1033a4);
        this.a.addStartupClass(new Consumer() { // from class: k6g
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Z3.a(c1033a4, (StartupClassBuilder) obj);
            }
        });
        return this;
    }

    @Override // com.android.tools.r8.profile.art.ArtProfileBuilder
    public final ArtProfileBuilder addHumanReadableArtProfile(TextInputStream textInputStream, Consumer consumer) {
        throw new Kk0();
    }

    @Override // com.android.tools.r8.profile.art.ArtProfileBuilder
    public final ArtProfileBuilder addMethodRule(Consumer consumer) {
        final C1119b4 c1119b4 = new C1119b4();
        consumer.accept(c1119b4);
        this.a.addStartupMethod(new Consumer() { // from class: l6g
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Z3.a(c1119b4, (StartupMethodBuilder) obj);
            }
        });
        return this;
    }

    public static void a(C1119b4 c1119b4, StartupMethodBuilder startupMethodBuilder) {
        startupMethodBuilder.setMethodReference(c1119b4.a);
    }
}
