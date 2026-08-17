package com.android.tools.r8.shaking;

import com.android.tools.r8.InputDependencyGraphConsumer;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.Bc0;
import com.android.tools.r8.internal.C2742u50;
import com.android.tools.r8.internal.P40;
import com.android.tools.r8.shaking.S3;
import com.android.tools.r8.utils.StringDiagnostic;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class T2 {
    public static final AbstractC0551Hu f = AbstractC0551Hu.a("protomapping", "target", "maximuminlinedcodelength");
    public static final AbstractC0551Hu g = AbstractC0551Hu.a("runtype", "laststageoutput");
    public static final AbstractC0551Hu h = AbstractC0551Hu.a("forceprocessing", "dontpreverify", "experimentalshrinkunusedprotofields", "filterlibraryjarswithorginalprogramjars", "dontskipnonpubliclibraryclasses", "dontskipnonpubliclibraryclassmembers", "invokebasemethod", "overloadaggressively", "mergeinterfacesaggressively", "android", "allowruntypeandignoreoptimizationpasses", "dontshrinkduringoptimization", "convert_proto_enum_to_string", "adaptkotlinmetadata");
    public static final AbstractC0551Hu i = AbstractC0551Hu.a("isclassnamestring", "whyarenotsimple");
    public static final Bc0 j = new Bc0("outjars");
    public static final Bc0 k = new Bc0("dump");
    public static final Bc0 l = new Bc0("useuniqueclassmembernames");
    public static final AbstractC0551Hu m = AbstractC0551Hu.a("assumenoexternalsideeffects", "assumenoescapingparameters", "assumenoexternalreturnvalues");
    public static final Bc0 n = new Bc0("skipnonpubliclibraryclasses");
    public final R2.a a;
    public final com.android.tools.r8.graph.B1 b;
    public final W2 c;
    public final C2742u50 d;
    public final InputDependencyGraphConsumer e;

    public T2(com.android.tools.r8.graph.B1 b1, C2742u50 c2742u50) {
        this(b1, c2742u50, W2.a().a(false).b(false).c(false).d(false).a());
    }

    public final void a(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Z2 z2 = (Z2) it.next();
            try {
                new V2(this, z2).j();
            } catch (J3 e) {
                this.d.error(e);
            } catch (IOException e2) {
                this.d.error(new StringDiagnostic("Failed to read file: " + e2.getMessage(), z2.getOrigin()));
            }
        }
        this.d.a();
    }

    public R2 b() {
        c();
        return this.a.b();
    }

    public final void c() {
        if (this.a.j() && this.a.k()) {
            this.d.a(new StringDiagnostic("-keepparameternames is not supported", this.a.f(), this.a.g()));
            throw null;
        }
        if (this.a.i()) {
            if (this.a.k()) {
                this.d.info(new StringDiagnostic("Build is not being obfuscated due to the use of -addconfigurationdebugging"));
                this.a.c();
            }
            if (this.a.h()) {
                this.d.info(new StringDiagnostic("Applying the obfuscation map (-applymapping) is disabled due to the use of -addconfigurationdebugging"));
                this.a.l();
            }
        }
    }

    public static class a {
        public final String a;
        public final List b;

        public a(String str, List<S3> list) {
            this.a = str;
            this.b = list;
        }

        public final boolean a() {
            return !this.b.isEmpty() && this.b.stream().anyMatch(new Predicate() { // from class: vzd
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((S3) obj).d();
                }
            });
        }

        public final boolean b() {
            if (!this.a.contains("<") && !this.a.contains(">")) {
                return false;
            }
            int i = 0;
            int i2 = 0;
            for (int i3 = 0; i3 < this.a.length(); i3++) {
                char cCharAt = this.a.charAt(i3);
                if (cCharAt == '<') {
                    i++;
                }
                if (cCharAt == '>') {
                    i2++;
                }
            }
            return (i == i2 && i == this.b.size()) ? false : true;
        }

        public static a a(String str) {
            int i = AbstractC0551Hu.c;
            return new a(str, P40.e);
        }
    }

    public T2(com.android.tools.r8.graph.B1 b1, C2742u50 c2742u50, W2 w2) {
        this(b1, c2742u50, w2, null);
    }

    public T2(com.android.tools.r8.graph.B1 b1, C2742u50 c2742u50, W2 w2, InputDependencyGraphConsumer inputDependencyGraphConsumer) {
        this.a = R2.a(b1, c2742u50);
        this.b = b1;
        this.c = w2;
        this.d = c2742u50;
        this.e = inputDependencyGraphConsumer == null ? new S2() : inputDependencyGraphConsumer;
    }

    public void a(Path path) {
        C3372b3 c3372b3 = new C3372b3(path);
        int i2 = AbstractC0551Hu.c;
        a(new Bc0(c3372b3));
    }

    public void a(Z2 z2) {
        int i2 = AbstractC0551Hu.c;
        a(new Bc0(z2));
    }

    public R2 a() {
        c();
        return this.a.a();
    }
}
