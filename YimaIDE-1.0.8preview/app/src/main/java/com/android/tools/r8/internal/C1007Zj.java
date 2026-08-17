package com.android.tools.r8.internal;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Zj, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1007Zj {
    public final Set a;
    public final HashMap c = new HashMap();
    public final boolean b = true;

    public C1007Zj(C1941kk[] c1941kkArr) {
        this.a = Collections.newSetFromMap(new IdentityHashMap(c1941kkArr.length));
        for (C1941kk c1941kk : c1941kkArr) {
            this.a.add(c1941kk);
            a(c1941kk);
        }
        for (C1941kk c1941kk2 : this.a) {
            try {
                a(c1941kk2, c1941kk2.b.m());
            } catch (C1091ak e) {
                x01.a(e);
                throw null;
            }
        }
    }

    public final void a(AbstractC2027lk abstractC2027lk) throws C1091ak {
        String strD = abstractC2027lk.d();
        if (strD.length() == 0) {
            throw new C1091ak(abstractC2027lk, "Missing name.");
        }
        for (int i = 0; i < strD.length(); i++) {
            char cCharAt = strD.charAt(i);
            if (('a' > cCharAt || cCharAt > 'z') && (('A' > cCharAt || cCharAt > 'Z') && cCharAt != '_' && ('0' > cCharAt || cCharAt > '9' || i <= 0))) {
                throw new C1091ak(abstractC2027lk, C40.a("\"", strD, "\" is not a valid identifier."));
            }
        }
        String strC = abstractC2027lk.c();
        AbstractC2027lk abstractC2027lk2 = (AbstractC2027lk) this.c.put(strC, abstractC2027lk);
        if (abstractC2027lk2 != null) {
            this.c.put(strC, abstractC2027lk2);
            if (abstractC2027lk.b() != abstractC2027lk2.b()) {
                throw new C1091ak(abstractC2027lk, "\"" + strC + "\" is already defined in file \"" + abstractC2027lk2.b().b.k() + "\".");
            }
            int iLastIndexOf = strC.lastIndexOf(46);
            if (iLastIndexOf == -1) {
                throw new C1091ak(abstractC2027lk, C40.a("\"", strC, "\" is already defined."));
            }
            throw new C1091ak(abstractC2027lk, "\"" + strC.substring(iLastIndexOf + 1) + "\" is already defined in \"" + strC.substring(0, iLastIndexOf) + "\".");
        }
    }

    public final AbstractC2027lk a(int i, String str) {
        AbstractC2027lk abstractC2027lk = (AbstractC2027lk) this.c.get(str);
        if (abstractC2027lk != null && (i == 3 || ((i == 1 && ((abstractC2027lk instanceof C0955Xj) || (abstractC2027lk instanceof C1260ck))) || (i == 2 && ((abstractC2027lk instanceof C0955Xj) || (abstractC2027lk instanceof C1260ck) || (abstractC2027lk instanceof C0981Yj) || (abstractC2027lk instanceof C2284ok)))))) {
            return abstractC2027lk;
        }
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            AbstractC2027lk abstractC2027lk2 = (AbstractC2027lk) ((C1941kk) it.next()).h.c.get(str);
            if (abstractC2027lk2 != null && (i == 3 || ((i == 1 && ((abstractC2027lk2 instanceof C0955Xj) || (abstractC2027lk2 instanceof C1260ck))) || (i == 2 && ((abstractC2027lk2 instanceof C0955Xj) || (abstractC2027lk2 instanceof C1260ck) || (abstractC2027lk2 instanceof C0981Yj) || (abstractC2027lk2 instanceof C2284ok)))))) {
                return abstractC2027lk2;
            }
        }
        return null;
    }

    public final AbstractC2027lk a(AbstractC2027lk abstractC2027lk, String str) throws C1091ak {
        AbstractC2027lk abstractC2027lkA;
        String string;
        if (str.startsWith(".")) {
            string = str.substring(1);
            abstractC2027lkA = a(1, string);
        } else {
            int iIndexOf = str.indexOf(46);
            String strSubstring = iIndexOf == -1 ? str : str.substring(0, iIndexOf);
            StringBuilder sb = new StringBuilder(abstractC2027lk.c());
            while (true) {
                int iLastIndexOf = sb.lastIndexOf(".");
                if (iLastIndexOf == -1) {
                    abstractC2027lkA = a(1, str);
                    string = str;
                    break;
                }
                int i = iLastIndexOf + 1;
                sb.setLength(i);
                sb.append(strSubstring);
                AbstractC2027lk abstractC2027lkA2 = a(2, sb.toString());
                if (abstractC2027lkA2 != null) {
                    if (iIndexOf != -1) {
                        sb.setLength(i);
                        sb.append(str);
                        abstractC2027lkA = a(1, sb.toString());
                    } else {
                        abstractC2027lkA = abstractC2027lkA2;
                    }
                    string = sb.toString();
                    break;
                }
                sb.setLength(iLastIndexOf);
            }
        }
        if (abstractC2027lkA != null) {
            return abstractC2027lkA;
        }
        if (this.b) {
            AbstractC2370pk.a.warning("The descriptor for message type \"" + str + "\" can not be found and a placeholder is created for it");
            C0955Xj c0955Xj = new C0955Xj(string);
            this.a.add(c0955Xj.d);
            return c0955Xj;
        }
        throw new C1091ak(abstractC2027lk, C40.a("\"", str, "\" is not defined."));
    }

    public final void a(C1941kk c1941kk, String str) throws C1091ak {
        String strSubstring;
        int iLastIndexOf = str.lastIndexOf(46);
        if (iLastIndexOf == -1) {
            strSubstring = str;
        } else {
            a(c1941kk, str.substring(0, iLastIndexOf));
            strSubstring = str.substring(iLastIndexOf + 1);
        }
        AbstractC2027lk abstractC2027lk = (AbstractC2027lk) this.c.put(str, new C0981Yj(strSubstring, str, c1941kk));
        if (abstractC2027lk != null) {
            this.c.put(str, abstractC2027lk);
            if (abstractC2027lk instanceof C0981Yj) {
                return;
            }
            throw new C1091ak(c1941kk, "\"" + strSubstring + "\" is already defined (as something other than a package) in file \"" + abstractC2027lk.b().b.k() + "\".");
        }
    }

    public final void a(C1941kk c1941kk) {
        for (C1941kk c1941kk2 : Collections.unmodifiableList(Arrays.asList(c1941kk.g))) {
            if (this.a.add(c1941kk2)) {
                a(c1941kk2);
            }
        }
    }
}
