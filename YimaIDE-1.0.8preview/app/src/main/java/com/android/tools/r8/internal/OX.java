package com.android.tools.r8.internal;

import com.android.tools.r8.ClassFileResourceProvider;
import com.android.tools.r8.ProgramResource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class OX implements ClassFileResourceProvider {
    public final Map a;

    public OX(HashMap map) {
        this.a = map;
    }

    public static ClassFileResourceProvider a(String str, byte[] bArr) {
        a aVarA = a();
        aVarA.a(str, bArr);
        return aVarA.a();
    }

    @Override // com.android.tools.r8.ClassFileResourceProvider
    public final Set getClassDescriptors() {
        return AbstractC2780ub0.a((Iterable) this.a.keySet());
    }

    @Override // com.android.tools.r8.ClassFileResourceProvider
    public final ProgramResource getProgramResource(String str) {
        byte[] bArr = (byte[]) this.a.get(str);
        if (bArr == null) {
            return null;
        }
        return ProgramResource.fromBytes(new PX(str), ProgramResource.Kind.CF, bArr, Collections.singleton(str));
    }

    public final String toString() {
        return this.a.size() + " preloaded resources";
    }

    public static a a() {
        return new a();
    }

    public static final class a {
        public static final /* synthetic */ boolean b = true;
        public HashMap a = new HashMap();

        public a a(String str, byte[] bArr) {
            boolean z = b;
            if (!z && this.a == null) {
                x1f.a();
                return null;
            }
            if (!z && str == null) {
                x1f.a();
                return null;
            }
            if (!z && bArr == null) {
                x1f.a();
                return null;
            }
            if (z || !this.a.containsKey(str)) {
                this.a.put(str, bArr);
                return this;
            }
            x1f.a();
            return null;
        }

        public OX a() {
            if (!b && this.a == null) {
                x1f.a();
                return null;
            }
            OX ox = new OX(this.a);
            this.a = null;
            return ox;
        }
    }
}
