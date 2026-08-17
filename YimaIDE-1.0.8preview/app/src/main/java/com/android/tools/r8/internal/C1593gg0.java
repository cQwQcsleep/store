package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.gg0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1593gg0 extends YI implements InterfaceC2635sr {
    public final /* synthetic */ List c;
    public final /* synthetic */ boolean d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1593gg0(List list, boolean z) {
        super(2);
        this.c = list;
        this.d = z;
    }

    /* JADX WARN: Code duplicated, block: B:60:0x00da A[EDGE_INSN: B:60:0x00da->B:61:0x00db BREAK  A[LOOP:0: B:28:0x0067->B:42:0x009f]] */
    @Override // com.android.tools.r8.internal.InterfaceC2635sr
    public final Object a(Object obj, Object obj2) {
        Object next;
        C1491fW c1491fW;
        String str;
        Object next2;
        String str2;
        String str3;
        int length;
        CharSequence charSequence = (CharSequence) obj;
        int iIntValue = ((Number) obj2).intValue();
        KB.c(charSequence, "$this$$receiver");
        List list = this.c;
        boolean z = this.d;
        if (z || list.size() != 1) {
            if (iIntValue < 0) {
                iIntValue = 0;
            }
            C3092yA c3092yA = new C3092yA(iIntValue, charSequence.length());
            boolean z2 = charSequence instanceof String;
            int i = c3092yA.c;
            if (!z2) {
                int i2 = c3092yA.d;
                if ((i2 > 0 && iIntValue <= i) || (i2 < 0 && i <= iIntValue)) {
                    while (true) {
                        Iterator it = list.iterator();
                        do {
                            if (!it.hasNext()) {
                                next = null;
                                break;
                            }
                            next = it.next();
                            str = (String) next;
                        } while (!AbstractC1679hg0.a(str, charSequence, iIntValue, str.length(), z));
                        String str4 = (String) next;
                        if (str4 == null) {
                            if (iIntValue == i) {
                                c1491fW = null;
                                break;
                            }
                            iIntValue += i2;
                        } else {
                            c1491fW = new C1491fW(Integer.valueOf(iIntValue), str4);
                            break;
                        }
                    }
                } else {
                    c1491fW = null;
                    break;
                }
            } else {
                int i3 = c3092yA.d;
                if ((i3 > 0 && iIntValue <= i) || (i3 < 0 && i <= iIntValue)) {
                    int i4 = iIntValue;
                    while (true) {
                        Iterator it2 = list.iterator();
                        do {
                            if (!it2.hasNext()) {
                                next2 = null;
                                break;
                            }
                            next2 = it2.next();
                            str2 = (String) next2;
                            str3 = (String) charSequence;
                            length = str2.length();
                        } while (!(!z ? str2.regionMatches(0, str3, i4, length) : str2.regionMatches(z, 0, str3, i4, length)));
                        String str5 = (String) next2;
                        if (str5 == null) {
                            if (i4 == i) {
                                c1491fW = null;
                                break;
                            }
                            i4 += i3;
                        } else {
                            c1491fW = new C1491fW(Integer.valueOf(i4), str5);
                            break;
                        }
                    }
                } else {
                    c1491fW = null;
                    break;
                }
            }
        } else {
            int size = list.size();
            if (size == 0) {
                hb9.a("List is empty.");
                return null;
            }
            if (size != 1) {
                w01.a("List has more than one element.");
                return null;
            }
            String str6 = (String) list.get(0);
            int iA = AbstractC1679hg0.a(charSequence, str6, iIntValue, false, 4);
            if (iA < 0) {
                c1491fW = null;
                break;
            }
            c1491fW = new C1491fW(Integer.valueOf(iA), str6);
        }
        if (c1491fW != null) {
            return new C1491fW(c1491fW.b, Integer.valueOf(((String) c1491fW.c).length()));
        }
        return null;
    }
}
