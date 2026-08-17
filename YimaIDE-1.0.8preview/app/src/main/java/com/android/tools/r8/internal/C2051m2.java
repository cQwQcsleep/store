package com.android.tools.r8.internal;

import com.android.tools.r8.DataResource;
import com.android.tools.r8.ResourceShrinker;
import com.android.tools.r8.references.MethodReference;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* JADX INFO: renamed from: com.android.tools.r8.internal.m2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2051m2 implements ResourceShrinker.ReferenceChecker {
    public final C0620Kl a;

    public C2051m2(C0620Kl c0620Kl) {
        this.a = c0620Kl;
    }

    @Override // com.android.tools.r8.ResourceShrinker.ReferenceChecker
    public final void endMethodVisit(MethodReference methodReference) {
        KB.c(methodReference, "methodReference");
        C0620Kl c0620Kl = this.a;
        c0620Kl.getClass();
        WO wo = c0620Kl.c;
        wo.a = false;
        wo.b = null;
    }

    @Override // com.android.tools.r8.ResourceShrinker.ReferenceChecker
    public final void referencedInt(int i) {
        C0620Kl c0620Kl = this.a;
        if (!c0620Kl.a() && Y50.a(c0620Kl.a.b.a(i))) {
            ((C1062aR) c0620Kl.a.a).getClass();
        }
    }

    @Override // com.android.tools.r8.ResourceShrinker.ReferenceChecker
    public final void referencedMethod(String str, String str2, String str3) {
        KB.c(str, "internalName");
        KB.c(str2, "methodName");
        KB.c(str3, "methodDescriptor");
        C0620Kl c0620Kl = this.a;
        c0620Kl.getClass();
        if (c0620Kl.b) {
            WO wo = c0620Kl.c;
            if (wo.a && KB.a((Object) wo.b, (Object) "<clinit>")) {
                return;
            }
        }
        if (str.equals("android/content/res/Resources") && str2.equals("getIdentifier") && str3.equals("(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I")) {
            LinkedHashSet linkedHashSet = T2.a;
            String strA = AbstractC1679hg0.a(str, DataResource.SEPARATOR, '.');
            WS ws = c0620Kl.a.c;
            KB.b(ws, "model.obfuscatedClasses");
            LinkedHashSet linkedHashSet2 = T2.a;
            String str4 = (String) ws.a.get(strA);
            if (str4 != null) {
                strA = str4;
            }
            if (linkedHashSet2.contains(strA)) {
                return;
            } else {
                c0620Kl.a.e = true;
            }
        }
        if (str.equals("android/webkit/WebView") && AbstractC1679hg0.a(str2, "load")) {
            c0620Kl.a.f = true;
        }
    }

    @Override // com.android.tools.r8.ResourceShrinker.ReferenceChecker
    public final void referencedStaticField(String str, String str2) {
        KB.c(str, "internalName");
        KB.c(str2, "fieldName");
        C0620Kl c0620Kl = this.a;
        c0620Kl.getClass();
        if (c0620Kl.a()) {
            return;
        }
        WS ws = c0620Kl.a.c;
        C0843Tb c0843Tb = new C0843Tb(AbstractC1679hg0.a(str, DataResource.SEPARATOR, '.'), str2);
        ws.getClass();
        Object c0843Tb2 = ws.b.get(c0843Tb);
        if (c0843Tb2 == null) {
            String str3 = (String) ws.a.get(c0843Tb.a);
            if (str3 == null) {
                str3 = c0843Tb.a;
            }
            KB.b(str3, "obfuscatedClasses[obfusc…bfuscatedMethod.className");
            c0843Tb2 = new C0843Tb(str3, c0843Tb.b);
        }
        C0843Tb c0843Tb3 = (C0843Tb) c0843Tb2;
        if (AbstractC1679hg0.a(AbstractC1679hg0.a(c0843Tb3.a, '.'), "R$")) {
            U50 u50 = (U50) U50.I.get(AbstractC1679hg0.a(c0843Tb3.a, '$'));
            if (u50 != null) {
                Iterator it = c0620Kl.a.b.a(u50, c0843Tb3.b).iterator();
                while (it.hasNext()) {
                    Y50.a((W50) it.next());
                }
            }
        }
    }

    @Override // com.android.tools.r8.ResourceShrinker.ReferenceChecker
    public final void referencedString(String str) {
        KB.c(str, "value");
        C0620Kl c0620Kl = this.a;
        c0620Kl.getClass();
        if (c0620Kl.a() || str.length() == 0 || str.length() > 80) {
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (!Character.isJavaIdentifierPart(cCharAt) && cCharAt != '.' && cCharAt != ':' && cCharAt != '/' && cCharAt != '%') {
                return;
            }
        }
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (Character.isJavaIdentifierPart(str.charAt(i2))) {
                c0620Kl.a.d.add(str);
                H50 h50 = c0620Kl.a;
                h50.f = h50.f || AbstractC1679hg0.a((CharSequence) str, "android_res/");
                return;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0045  */
    @Override // com.android.tools.r8.ResourceShrinker.ReferenceChecker
    public final boolean shouldProcess(String str) {
        boolean z;
        KB.c(str, "internalName");
        C0620Kl c0620Kl = this.a;
        c0620Kl.getClass();
        WS ws = c0620Kl.a.c;
        String strA = AbstractC1679hg0.a(str, DataResource.SEPARATOR, '.');
        String str2 = (String) ws.a.get(strA);
        if (str2 != null) {
            strA = str2;
        }
        String strA2 = AbstractC1679hg0.a(strA, '.');
        if (AbstractC1679hg0.a(strA2, "R$")) {
            String strSubstring = strA2.substring(2);
            KB.b(strSubstring, "substring(...)");
            if (((U50) U50.I.get(strSubstring)) != null) {
                z = true;
            } else {
                z = false;
            }
        } else {
            z = false;
        }
        c0620Kl.b = z;
        return true;
    }

    @Override // com.android.tools.r8.ResourceShrinker.ReferenceChecker
    public final void startMethodVisit(MethodReference methodReference) {
        KB.c(methodReference, "methodReference");
        C0620Kl c0620Kl = this.a;
        c0620Kl.getClass();
        WO wo = c0620Kl.c;
        wo.a = true;
        wo.b = methodReference.getMethodName();
    }
}
