package com.android.tools.r8;

import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.C0473Eu;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
class C3360p implements ClassFileResourceProvider {
    final List<ClassFileResourceProvider> a;
    public final HashSet b = new HashSet();

    public C3360p(AbstractC0551Hu abstractC0551Hu) {
        this.a = abstractC0551Hu;
        abstractC0551Hu.forEach(new Consumer() { // from class: com.android.tools.r8.w0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((ClassFileResourceProvider) obj);
            }
        });
    }

    public final /* synthetic */ void a(ClassFileResourceProvider classFileResourceProvider) {
        this.b.addAll(classFileResourceProvider.getClassDescriptors());
    }

    @Override // com.android.tools.r8.ClassFileResourceProvider
    public final Set getClassDescriptors() {
        return this.b;
    }

    @Override // com.android.tools.r8.ClassFileResourceProvider
    public final ProgramResource getProgramResource(String str) {
        for (ClassFileResourceProvider classFileResourceProvider : this.a) {
            if (classFileResourceProvider.getClassDescriptors().contains(str)) {
                return classFileResourceProvider.getProgramResource(str);
            }
        }
        return null;
    }

    public static a a() {
        return new a();
    }

    /* JADX INFO: renamed from: com.android.tools.r8.p$a */
    public static class a {
        public final C0473Eu a = AbstractC0551Hu.g();
        public boolean b = true;

        public C3360p a() {
            return new C3360p(this.a.a());
        }

        public final boolean b() {
            return this.b;
        }

        public a a(ClassFileResourceProvider classFileResourceProvider) {
            this.a.a(classFileResourceProvider);
            this.b = false;
            return this;
        }
    }
}
