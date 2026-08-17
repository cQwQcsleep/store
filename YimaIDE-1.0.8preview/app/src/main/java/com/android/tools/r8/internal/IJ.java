package com.android.tools.r8.internal;

import com.android.tools.r8.FeatureSplit;
import com.android.tools.r8.internal.GJ;
import com.android.tools.r8.internal.H50;
import com.android.tools.r8.internal.IJ;
import com.android.tools.r8.internal.W50;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class IJ {
    public final Map a;
    public final List b;
    public final List c;
    public List d;
    public final List e;
    public final Map f;

    public IJ(Map map, List list, List list2, Map map2, List list3, List list4) {
        this.a = map;
        this.b = list;
        this.e = list2;
        this.f = map2;
        this.c = list3;
        this.d = list4;
    }

    public final HJ a() throws ExecutionException, IllegalAccessException, IOException, InvocationTargetException {
        C1062aR c1062aR = C1062aR.a;
        H10 h10 = new H10();
        Iterator it = this.f.keySet().iterator();
        while (it.hasNext()) {
            h10.a(M70.a(((GJ) it.next()).a), false);
        }
        List list = this.d;
        if (list != null) {
            new C1579gZ(list).a(h10);
            this.d = null;
        }
        for (Map.Entry entry : this.a.entrySet()) {
            Path path = Paths.get("in_memory_r8_" + ((String) entry.getKey()) + ".dex", new String[0]);
            F10.a((byte[]) entry.getValue(), path, new C0620Kl(path, h10));
        }
        Iterator it2 = this.e.iterator();
        while (it2.hasNext()) {
            AbstractC2263oZ.a(C1382e90.a(((GJ) it2.next()).a), h10);
        }
        for (GJ gj : this.c) {
            if (gj.b.startsWith("res/raw")) {
                Jh0.a(a(gj.a()), h10);
            }
        }
        final AbstractC0706Nu abstractC0706NuA = new C0629Ku().a((Map) this.c.stream().collect(Collectors.toMap(new Function() { // from class: tf6
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((GJ) obj).b();
            }
        }, new Function() { // from class: uf6
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return IJ.a((GJ) obj);
            }
        }))).a((Map) this.b.stream().collect(Collectors.toMap(new Function() { // from class: tf6
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((GJ) obj).b();
            }
        }, new Function() { // from class: vf6
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return IJ.b((GJ) obj);
            }
        }))).a();
        Iterator it3 = this.f.keySet().iterator();
        while (it3.hasNext()) {
            final M70 m70A = M70.a(((GJ) it3.next()).a);
            new C2991x10(new InterfaceC2827v50() { // from class: wf6
                @Override // com.android.tools.r8.internal.InterfaceC2827v50
                public final byte[] a(String str) {
                    return IJ.a(abstractC0706NuA, str);
                }
            }, new InterfaceC1439er() { // from class: xf6
                @Override // com.android.tools.r8.internal.InterfaceC1439er
                public final Object b(Object obj) {
                    return IJ.a(m70A, (H50) obj);
                }
            }).a(h10);
        }
        R50 r50A = h10.a();
        r50A.b();
        h10.b();
        List listA = AbstractC2066m90.a(new Consumer() { // from class: yf6
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                IJ.b((List) obj);
            }
        }, h10.a().a());
        C1870jv c1870jv = new C1870jv();
        Iterator it4 = AbstractC3179zC.a((Iterable) this.c, (Iterable) this.b).iterator();
        while (true) {
            KC kc = (KC) it4;
            if (!kc.hasNext()) {
                break;
            }
            GJ gj2 = (GJ) kc.next();
            if (G50.a(r50A, gj2.b.toString())) {
                c1870jv.a(gj2.b.toString());
            }
        }
        List listA2 = a(listA);
        HashMap map = new HashMap();
        for (Map.Entry entry2 : this.f.entrySet()) {
            map.put((FeatureSplit) entry2.getValue(), T50.a(M70.a(((GJ) entry2.getKey()).a), listA2));
        }
        return new HJ(c1870jv.a(), map);
    }

    public static /* synthetic */ void b(List list) {
    }

    public static /* synthetic */ GJ b(GJ gj) {
        return gj;
    }

    public static /* synthetic */ M70 a(M70 m70, H50 h50) {
        return m70;
    }

    public static /* synthetic */ GJ a(GJ gj) {
        return gj;
    }

    public static byte[] a(AbstractC0706Nu abstractC0706Nu, String str) {
        return ((GJ) abstractC0706Nu.get(str)).a;
    }

    public static List a(List list) {
        return (List) list.stream().filter(new Predicate() { // from class: zf6
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return IJ.a((W50) obj);
            }
        }).map(new Function() { // from class: ag6
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Integer.valueOf(((W50) obj).f);
            }
        }).collect(Collectors.toList());
    }

    public static /* synthetic */ boolean a(W50 w50) {
        return w50.c != U50.ID;
    }

    public static Reader a(byte[] bArr) {
        int length = bArr.length;
        if (length == 0) {
            return new StringReader(XmlPullParser.NO_NAMESPACE);
        }
        byte b = bArr[0];
        if (b != -17) {
            if (b != -2) {
                if (b != -1) {
                    if (b == 0 && length >= 4 && b == 0 && bArr[1] == 0 && bArr[2] == -2 && bArr[3] == -1) {
                        return new InputStreamReader(new ByteArrayInputStream(bArr, 4, length - 4), "UTF-32BE");
                    }
                } else if (length >= 2 && bArr[1] == -2) {
                    if (length >= 4 && bArr[2] == 0 && bArr[3] == 0) {
                        return new InputStreamReader(new ByteArrayInputStream(bArr, 4, length - 4), "UTF-32LE");
                    }
                    return new InputStreamReader(new ByteArrayInputStream(bArr, 2, length - 2), StandardCharsets.UTF_16LE);
                }
            } else if (length >= 2 && bArr[1] == -1) {
                return new InputStreamReader(new ByteArrayInputStream(bArr, 2, length - 2), StandardCharsets.UTF_16BE);
            }
        } else if (length >= 3 && bArr[1] == -69 && bArr[2] == -65) {
            return new InputStreamReader(new ByteArrayInputStream(bArr, 3, length - 3), StandardCharsets.UTF_8);
        }
        return new InputStreamReader(new ByteArrayInputStream(bArr), StandardCharsets.UTF_8);
    }
}
