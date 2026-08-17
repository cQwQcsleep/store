package com.android.tools.r8.internal;

import com.android.tools.r8.TextInputStream;
import com.android.tools.r8.internal.C2140n4;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.profile.art.ArtProfileBuilder;
import com.android.tools.r8.profile.art.ArtProfileClassRuleBuilder;
import com.android.tools.r8.profile.art.ArtProfileMethodRuleBuilder;
import com.android.tools.r8.profile.art.ArtProfileMethodRuleInfoBuilder;
import com.android.tools.r8.profile.art.ArtProfileRulePredicate;
import com.android.tools.r8.profile.art.HumanReadableArtProfileParserBuilder;
import com.android.tools.r8.profile.art.diagnostic.HumanReadableArtProfileParserErrorDiagnostic;
import com.android.tools.r8.references.ClassReference;
import com.android.tools.r8.references.MethodReference;
import com.android.tools.r8.references.Reference;
import com.android.tools.r8.references.TypeReference;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.qt, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C2467qt {
    public static final /* synthetic */ boolean e = true;
    public final Consumer a;
    public final ArtProfileBuilder b;
    public final ArtProfileRulePredicate c;
    public final C2742u50 d;

    public C2467qt(Consumer consumer, ArtProfileBuilder artProfileBuilder, ArtProfileRulePredicate artProfileRulePredicate, C2742u50 c2742u50) {
        this.a = consumer;
        this.b = artProfileBuilder;
        this.c = artProfileRulePredicate;
        this.d = c2742u50;
    }

    public void a(TextInputStream textInputStream, Origin origin) {
        Consumer consumer;
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(textInputStream.getInputStream(), textInputStream.getCharset());
            try {
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                int i = 1;
                while (bufferedReader.ready()) {
                    try {
                        String line = bufferedReader.readLine();
                        int iIndexOf = line.indexOf(35);
                        String strStripTrailing = iIndexOf >= 0 ? line.substring(0, iIndexOf).stripTrailing() : line;
                        for (int i2 = 0; i2 < strStripTrailing.length(); i2++) {
                            if (!Character.isWhitespace(strStripTrailing.charAt(i2))) {
                                if (!b(strStripTrailing) && (consumer = this.a) != null) {
                                    consumer.accept(new HumanReadableArtProfileParserErrorDiagnostic(line, i, origin));
                                    break;
                                }
                                break;
                            }
                        }
                        i++;
                    } catch (Throwable th) {
                        try {
                            bufferedReader.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                        throw th;
                    }
                }
                bufferedReader.close();
                inputStreamReader.close();
                C2742u50 c2742u50 = this.d;
                if (c2742u50 != null) {
                    c2742u50.a();
                }
            } catch (Throwable th3) {
                try {
                    inputStreamReader.close();
                } catch (Throwable th4) {
                    th3.addSuppressed(th4);
                }
                throw th3;
            }
        } catch (IOException e2) {
            u8i.a(e2);
        }
    }

    public boolean b(String str) {
        try {
            C2140n4.a aVarA = C2140n4.a();
            String strA = a(str, aVarA);
            C2140n4 c2140n4A = aVarA.a();
            int iIndexOf = strA.indexOf("->");
            if (iIndexOf >= 0) {
                return a(strA, c2140n4A, iIndexOf);
            }
            if (c2140n4A.a == 0) {
                return a(strA);
            }
            return false;
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: renamed from: com.android.tools.r8.internal.qt$a */
    public static class a implements HumanReadableArtProfileParserBuilder {
        public Consumer a;
        public ArtProfileBuilder b;
        public ArtProfileRulePredicate c = new C1794j2();
        public C2742u50 d;

        public C2467qt a() {
            final C2742u50 c2742u50;
            if (this.a == null && (c2742u50 = this.d) != null) {
                this.a = new Consumer() { // from class: p4i
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        c2742u50.error((HumanReadableArtProfileParserErrorDiagnostic) obj);
                    }
                };
            }
            return new C2467qt(this.a, this.b, this.c, this.d);
        }

        @Override // com.android.tools.r8.profile.art.HumanReadableArtProfileParserBuilder
        public final HumanReadableArtProfileParserBuilder setRulePredicate(ArtProfileRulePredicate artProfileRulePredicate) {
            this.c = artProfileRulePredicate;
            return this;
        }

        public a a(ArtProfileBuilder artProfileBuilder) {
            this.b = artProfileBuilder;
            return this;
        }

        public a a(C2742u50 c2742u50) {
            this.d = c2742u50;
            return this;
        }
    }

    public static a a() {
        return new a();
    }

    public static String a(String str, final C2140n4.a aVar) {
        String strSubstring;
        C1497fb c1497fb = new C1497fb();
        while (true) {
            Objects.requireNonNull(aVar);
            Y1 y1 = new Y1() { // from class: k4i
                @Override // com.android.tools.r8.internal.Y1
                public final void b() {
                    aVar.b();
                }
            };
            if (!str.isEmpty() && str.charAt(0) == 'H' && c1497fb.a('H')) {
                y1.b();
                strSubstring = str.substring(1);
            } else {
                strSubstring = str;
            }
            Y1 y2 = new Y1() { // from class: l4i
                @Override // com.android.tools.r8.internal.Y1
                public final void b() {
                    aVar.d();
                }
            };
            if (!strSubstring.isEmpty() && strSubstring.charAt(0) == 'S' && c1497fb.a('S')) {
                y2.b();
                strSubstring = strSubstring.substring(1);
            }
            Y1 y3 = new Y1() { // from class: m4i
                @Override // com.android.tools.r8.internal.Y1
                public final void b() {
                    aVar.c();
                }
            };
            if (!strSubstring.isEmpty() && strSubstring.charAt(0) == 'P' && c1497fb.a('P')) {
                y3.b();
                strSubstring = strSubstring.substring(1);
            }
            if (strSubstring.equals(str)) {
                return strSubstring;
            }
            str = strSubstring;
        }
    }

    public final boolean a(String str) {
        int i;
        AbstractC0706Nu abstractC0706Nu = C0929Wj.a;
        int i2 = -1;
        while (true) {
            i = i2 + 1;
            if (i >= str.length() || str.charAt(i) != '[') {
                break;
            }
            i2 = i;
        }
        if (i2 >= 0) {
            str = str.substring(i);
        }
        if (!C0929Wj.C(str)) {
            return false;
        }
        TypeReference typeReferenceTypeFromDescriptor = Reference.typeFromDescriptor(str);
        boolean z = e;
        if (!z && typeReferenceTypeFromDescriptor == null) {
            x1f.a();
            return false;
        }
        if (!z && !typeReferenceTypeFromDescriptor.isClass()) {
            x1f.a();
            return false;
        }
        final ClassReference classReferenceAsClass = typeReferenceTypeFromDescriptor.asClass();
        if (!this.c.testClassRule(classReferenceAsClass, C1457f4.a)) {
            return true;
        }
        this.b.addClassRule(new Consumer() { // from class: n4i
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((ArtProfileClassRuleBuilder) obj).setClassReference(classReferenceAsClass);
            }
        });
        return true;
    }

    public final boolean a(String str, final C2140n4 c2140n4, int i) {
        int iIndexOf = str.indexOf(43, i + 2);
        if (iIndexOf > 0) {
            str = str.substring(0, iIndexOf);
        }
        final MethodReference methodReferenceA = MO.a(i, str);
        if (methodReferenceA == null || !C0929Wj.C(methodReferenceA.getHolderClass().getDescriptor())) {
            return false;
        }
        Iterator<TypeReference> it = methodReferenceA.getFormalTypes().iterator();
        while (it.hasNext()) {
            if (!C0929Wj.D(it.next().getDescriptor())) {
                return false;
            }
        }
        if (methodReferenceA.getReturnType() != null && !C0929Wj.D(methodReferenceA.getReturnType().getDescriptor())) {
            return false;
        }
        if (!this.c.testMethodRule(methodReferenceA, c2140n4)) {
            return true;
        }
        this.b.addMethodRule(new Consumer() { // from class: j4i
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((ArtProfileMethodRuleBuilder) obj).setMethodReference(methodReferenceA).setMethodRuleInfo(new Consumer() { // from class: o4i
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj2) {
                        C2140n4 c2140n5 = c2140n4;
                        ((ArtProfileMethodRuleInfoBuilder) obj2).setIsHot(c2140n5.isHot()).setIsStartup(c2140n5.isStartup()).setIsPostStartup(c2140n5.isPostStartup());
                    }
                });
            }
        });
        return true;
    }
}
