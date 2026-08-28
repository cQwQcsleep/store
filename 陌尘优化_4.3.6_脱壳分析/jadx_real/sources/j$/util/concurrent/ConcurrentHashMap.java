package j$.util.concurrent;

import com.shadow.okhttp3.internal.http2.Settings;
import j$.util.InterfaceC0089m;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/* loaded from: /workspace/unpacked/classes3.dex */
public class ConcurrentHashMap<K, V> extends AbstractMap<K, V> implements ConcurrentMap<K, V>, Serializable, InterfaceC0089m {
    static final int g = Runtime.getRuntime().availableProcessors();
    private static final j$.sun.misc.a h;
    private static final long i;
    private static final long j;
    private static final long k;
    private static final long l;
    private static final long m;
    private static final int n;
    private static final int o;
    private static final ObjectStreamField[] serialPersistentFields;
    private static final long serialVersionUID = 7249069246763182397L;
    volatile transient l[] a;
    private volatile transient l[] b;
    private volatile transient long baseCount;
    private volatile transient c[] c;
    private volatile transient int cellsBusy;
    private transient i d;
    private transient s e;
    private transient e f;
    private volatile transient int sizeCtl;
    private volatile transient int transferIndex;

    static final int i(int i2) {
        return (i2 ^ (i2 >>> 16)) & Integer.MAX_VALUE;
    }

    static {
        ObjectStreamField objectStreamField = new ObjectStreamField("segments", n[].class);
        Class cls = Integer.TYPE;
        serialPersistentFields = new ObjectStreamField[]{objectStreamField, new ObjectStreamField("segmentMask", cls), new ObjectStreamField("segmentShift", cls)};
        j$.sun.misc.a aVarH = j$.sun.misc.a.h();
        h = aVarH;
        i = aVarH.j(ConcurrentHashMap.class, "sizeCtl");
        j = aVarH.j(ConcurrentHashMap.class, "transferIndex");
        k = aVarH.j(ConcurrentHashMap.class, "baseCount");
        l = aVarH.j(ConcurrentHashMap.class, "cellsBusy");
        m = aVarH.j(c.class, "value");
        n = aVarH.a(l[].class);
        int iB = aVarH.b(l[].class);
        if (((iB - 1) & iB) != 0) {
            throw new ExceptionInInitializerError("array index scale not a power of two");
        }
        o = 31 - Integer.numberOfLeadingZeros(iB);
    }

    private static final int l(int i2) {
        int iNumberOfLeadingZeros = (-1) >>> Integer.numberOfLeadingZeros(i2 - 1);
        if (iNumberOfLeadingZeros < 0) {
            return 1;
        }
        if (iNumberOfLeadingZeros >= 1073741824) {
            return 1073741824;
        }
        return 1 + iNumberOfLeadingZeros;
    }

    static Class c(Object obj) {
        Type[] actualTypeArguments;
        if (!(obj instanceof Comparable)) {
            return null;
        }
        Class<?> cls = obj.getClass();
        if (cls == String.class) {
            return cls;
        }
        Type[] genericInterfaces = cls.getGenericInterfaces();
        if (genericInterfaces == null) {
            return null;
        }
        for (Type type : genericInterfaces) {
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                if (parameterizedType.getRawType() == Comparable.class && (actualTypeArguments = parameterizedType.getActualTypeArguments()) != null && actualTypeArguments.length == 1 && actualTypeArguments[0] == cls) {
                    return cls;
                }
            }
        }
        return null;
    }

    static final l k(l[] lVarArr, int i2) {
        return (l) h.g(lVarArr, (i2 << o) + n);
    }

    static final boolean b(l[] lVarArr, int i2, l lVar) {
        return h.e(lVarArr, (i2 << o) + n, lVar);
    }

    static final void h(l[] lVarArr, int i2, l lVar) {
        h.l(lVarArr, (i2 << o) + n, lVar);
    }

    public ConcurrentHashMap() {
    }

    public ConcurrentHashMap(int i2, int i3) {
        if (i2 < 0 || i3 <= 0) {
            throw new IllegalArgumentException();
        }
        long j2 = (long) (((i2 < i3 ? i3 : i2) / 0.75f) + 1.0d);
        this.sizeCtl = j2 >= 1073741824 ? 1073741824 : l((int) j2);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        long j2 = j();
        if (j2 < 0) {
            return 0;
        }
        if (j2 > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) j2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean isEmpty() {
        return j() <= 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        int length;
        l lVarK;
        Object obj2;
        int i2 = i(obj.hashCode());
        l[] lVarArr = this.a;
        if (lVarArr != null && (length = lVarArr.length) > 0 && (lVarK = k(lVarArr, (length - 1) & i2)) != null) {
            int i3 = lVarK.a;
            if (i3 == i2) {
                Object obj3 = lVarK.b;
                if (obj3 == obj || (obj3 != null && obj.equals(obj3))) {
                    return (V) lVarK.c;
                }
            } else if (i3 < 0) {
                l lVarA = lVarK.a(obj, i2);
                if (lVarA != null) {
                    return (V) lVarA.c;
                }
                return null;
            }
            while (true) {
                lVarK = lVarK.d;
                if (lVarK == null) {
                    break;
                }
                if (lVarK.a == i2 && ((obj2 = lVarK.b) == obj || (obj2 != null && obj.equals(obj2)))) {
                    break;
                }
            }
            return (V) lVarK.c;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        return get(obj) != null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsValue(Object obj) {
        obj.getClass();
        l[] lVarArr = this.a;
        if (lVarArr != null) {
            p pVar = new p(lVarArr, lVarArr.length, 0, lVarArr.length);
            while (true) {
                l lVarA = pVar.a();
                if (lVarA == null) {
                    break;
                }
                Object obj2 = lVarA.c;
                if (obj2 == obj) {
                    return true;
                }
                if (obj2 != null && obj.equals(obj2)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K k2, V v) {
        return (V) f(k2, v, false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x006a, code lost:
    
        r7 = r6.c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x006c, code lost:
    
        if (r11 != false) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x006e, code lost:
    
        r6.c = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00a5, code lost:
    
        throw new java.lang.IllegalStateException("Recursive update");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    final Object f(Object obj, Object obj2, boolean z) {
        Object obj3;
        Object obj4;
        Object obj5;
        Object obj6;
        if (obj == null || obj2 == null) {
            throw null;
        }
        int i2 = i(obj.hashCode());
        l[] lVarArrE = this.a;
        int i3 = 0;
        while (true) {
            if (lVarArrE != null) {
                int length = lVarArrE.length;
                if (length != 0) {
                    int i4 = (length - 1) & i2;
                    l lVarK = k(lVarArrE, i4);
                    if (lVarK == null) {
                        if (b(lVarArrE, i4, new l(i2, obj, obj2))) {
                            break;
                        }
                    } else {
                        int i5 = lVarK.a;
                        if (i5 == -1) {
                            lVarArrE = d(lVarArrE, lVarK);
                        } else {
                            if (z && i5 == i2 && (((obj5 = lVarK.b) == obj || (obj5 != null && obj.equals(obj5))) && (obj6 = lVarK.c) != null)) {
                                return obj6;
                            }
                            synchronized (lVarK) {
                                try {
                                    if (k(lVarArrE, i4) == lVarK) {
                                        if (i5 < 0) {
                                            if (lVarK instanceof q) {
                                                r rVarE = ((q) lVarK).e(i2, obj, obj2);
                                                if (rVarE != null) {
                                                    Object obj7 = rVarE.c;
                                                    if (!z) {
                                                        rVarE.c = obj2;
                                                    }
                                                    obj3 = obj7;
                                                } else {
                                                    obj3 = null;
                                                }
                                                i3 = 2;
                                            } else if (lVarK instanceof m) {
                                                break;
                                            }
                                        } else {
                                            i3 = 1;
                                            l lVar = lVarK;
                                            while (true) {
                                                if (lVar.a == i2 && ((obj4 = lVar.b) == obj || (obj4 != null && obj.equals(obj4)))) {
                                                    break;
                                                }
                                                l lVar2 = lVar.d;
                                                if (lVar2 == null) {
                                                    lVar.d = new l(i2, obj, obj2);
                                                    break;
                                                }
                                                i3++;
                                                lVar = lVar2;
                                            }
                                        }
                                    }
                                    obj3 = null;
                                } catch (Throwable th) {
                                    throw th;
                                }
                            }
                            if (i3 != 0) {
                                if (i3 >= 8) {
                                    n(lVarArrE, i4);
                                }
                                if (obj3 != null) {
                                    return obj3;
                                }
                            }
                        }
                    }
                }
            }
            lVarArrE = e();
        }
        a(1L, i3);
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void putAll(Map map) {
        o(map.size());
        for (Map.Entry<K, V> entry : map.entrySet()) {
            f(entry.getKey(), entry.getValue(), false);
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        return (V) g(obj, null, null);
    }

    final Object g(Object obj, Object obj2, Object obj3) {
        int length;
        int i2;
        l lVarK;
        boolean z;
        Object obj4;
        r rVarB;
        Object obj5;
        int i3 = i(obj.hashCode());
        l[] lVarArrD = this.a;
        while (true) {
            if (lVarArrD == null || (length = lVarArrD.length) == 0 || (lVarK = k(lVarArrD, (i2 = (length - 1) & i3))) == null) {
                break;
            }
            int i4 = lVarK.a;
            if (i4 == -1) {
                lVarArrD = d(lVarArrD, lVarK);
            } else {
                synchronized (lVarK) {
                    try {
                        if (k(lVarArrD, i2) == lVarK) {
                            z = true;
                            if (i4 >= 0) {
                                l lVar = null;
                                l lVar2 = lVarK;
                                while (true) {
                                    if (lVar2.a != i3 || ((obj5 = lVar2.b) != obj && (obj5 == null || !obj.equals(obj5)))) {
                                        l lVar3 = lVar2.d;
                                        if (lVar3 == null) {
                                            break;
                                        }
                                        lVar = lVar2;
                                        lVar2 = lVar3;
                                    }
                                }
                                obj4 = lVar2.c;
                                if (obj3 != null && obj3 != obj4 && (obj4 == null || !obj3.equals(obj4))) {
                                    obj4 = null;
                                } else if (obj2 != null) {
                                    lVar2.c = obj2;
                                } else if (lVar != null) {
                                    lVar.d = lVar2.d;
                                } else {
                                    h(lVarArrD, i2, lVar2.d);
                                }
                            } else if (lVarK instanceof q) {
                                q qVar = (q) lVarK;
                                r rVar = qVar.e;
                                if (rVar != null && (rVarB = rVar.b(i3, obj, null)) != null) {
                                    obj4 = rVarB.c;
                                    if (obj3 == null || obj3 == obj4 || (obj4 != null && obj3.equals(obj4))) {
                                        if (obj2 != null) {
                                            rVarB.c = obj2;
                                        } else if (qVar.f(rVarB)) {
                                            h(lVarArrD, i2, p(qVar.f));
                                        }
                                    }
                                }
                                obj4 = null;
                            } else if (lVarK instanceof m) {
                                throw new IllegalStateException("Recursive update");
                            }
                        }
                        z = false;
                        obj4 = null;
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                if (z) {
                    if (obj4 != null) {
                        if (obj2 == null) {
                            a(-1L, -1);
                        }
                        return obj4;
                    }
                }
            }
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        l lVarK;
        l lVar;
        l[] lVarArrD = this.a;
        long j2 = 0;
        loop0: while (true) {
            int i2 = 0;
            while (lVarArrD != null && i2 < lVarArrD.length) {
                lVarK = k(lVarArrD, i2);
                if (lVarK == null) {
                    i2++;
                } else {
                    int i3 = lVarK.a;
                    if (i3 == -1) {
                        break;
                    }
                    synchronized (lVarK) {
                        try {
                            if (k(lVarArrD, i2) == lVarK) {
                                if (i3 >= 0) {
                                    lVar = lVarK;
                                } else {
                                    lVar = lVarK instanceof q ? ((q) lVarK).f : null;
                                }
                                while (lVar != null) {
                                    j2--;
                                    lVar = lVar.d;
                                }
                                h(lVarArrD, i2, null);
                                i2++;
                            }
                        } finally {
                        }
                    }
                }
            }
            lVarArrD = d(lVarArrD, lVarK);
        }
        if (j2 != 0) {
            a(j2, -1);
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set keySet() {
        i iVar = this.d;
        if (iVar != null) {
            return iVar;
        }
        i iVar2 = new i(this);
        this.d = iVar2;
        return iVar2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Collection values() {
        s sVar = this.e;
        if (sVar != null) {
            return sVar;
        }
        s sVar2 = new s(this);
        this.e = sVar2;
        return sVar2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        e eVar = this.f;
        if (eVar != null) {
            return eVar;
        }
        e eVar2 = new e(this);
        this.f = eVar2;
        return eVar2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        l[] lVarArr = this.a;
        int iHashCode = 0;
        if (lVarArr != null) {
            p pVar = new p(lVarArr, lVarArr.length, 0, lVarArr.length);
            while (true) {
                l lVarA = pVar.a();
                if (lVarA == null) {
                    break;
                }
                iHashCode += lVarA.c.hashCode() ^ lVarA.b.hashCode();
            }
        }
        return iHashCode;
    }

    @Override // java.util.AbstractMap
    public final String toString() {
        l[] lVarArr = this.a;
        int length = lVarArr == null ? 0 : lVarArr.length;
        p pVar = new p(lVarArr, length, 0, length);
        StringBuilder sb = new StringBuilder("{");
        l lVarA = pVar.a();
        if (lVarA != null) {
            while (true) {
                Object obj = lVarA.b;
                Object obj2 = lVarA.c;
                if (obj == this) {
                    obj = "(this Map)";
                }
                sb.append(obj);
                sb.append('=');
                if (obj2 == this) {
                    obj2 = "(this Map)";
                }
                sb.append(obj2);
                lVarA = pVar.a();
                if (lVarA == null) {
                    break;
                }
                sb.append(", ");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean equals(Object obj) {
        V value;
        V v;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        l[] lVarArr = this.a;
        int length = lVarArr == null ? 0 : lVarArr.length;
        p pVar = new p(lVarArr, length, 0, length);
        while (true) {
            l lVarA = pVar.a();
            if (lVarA != null) {
                Object obj2 = lVarA.c;
                Object obj3 = map.get(lVarA.b);
                if (obj3 == null || (obj3 != obj2 && !obj3.equals(obj2))) {
                    break;
                }
            } else {
                for (Map.Entry<K, V> entry : map.entrySet()) {
                    K key = entry.getKey();
                    if (key == null || (value = entry.getValue()) == null || (v = get(key)) == null || (value != v && !value.equals(v))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        int i2 = 1;
        int i3 = 0;
        while (i2 < 16) {
            i3++;
            i2 <<= 1;
        }
        int i4 = 32 - i3;
        int i5 = i2 - 1;
        n[] nVarArr = new n[16];
        for (int i6 = 0; i6 < 16; i6++) {
            nVarArr[i6] = new n();
        }
        ObjectOutputStream.PutField putFieldPutFields = objectOutputStream.putFields();
        putFieldPutFields.put("segments", nVarArr);
        putFieldPutFields.put("segmentShift", i4);
        putFieldPutFields.put("segmentMask", i5);
        objectOutputStream.writeFields();
        l[] lVarArr = this.a;
        if (lVarArr != null) {
            p pVar = new p(lVarArr, lVarArr.length, 0, lVarArr.length);
            while (true) {
                l lVarA = pVar.a();
                if (lVarA == null) {
                    break;
                }
                objectOutputStream.writeObject(lVarA.b);
                objectOutputStream.writeObject(lVarA.c);
            }
        }
        objectOutputStream.writeObject(null);
        objectOutputStream.writeObject(null);
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        long j2;
        boolean z;
        boolean z2;
        Object obj;
        this.sizeCtl = -1;
        objectInputStream.defaultReadObject();
        long j3 = 0;
        long j4 = 0;
        l lVar = null;
        while (true) {
            Object object = objectInputStream.readObject();
            Object object2 = objectInputStream.readObject();
            j2 = 1;
            if (object == null || object2 == null) {
                break;
            }
            j4++;
            lVar = new l(i(object.hashCode()), object, object2, lVar);
        }
        if (j4 == 0) {
            this.sizeCtl = 0;
            return;
        }
        long j5 = (long) ((j4 / 0.75f) + 1.0d);
        int iL = j5 >= 1073741824 ? 1073741824 : l((int) j5);
        l[] lVarArr = new l[iL];
        int i2 = iL - 1;
        while (lVar != null) {
            l lVar2 = lVar.d;
            int i3 = lVar.a;
            int i4 = i3 & i2;
            l lVarK = k(lVarArr, i4);
            if (lVarK == null) {
                z2 = true;
            } else {
                Object obj2 = lVar.b;
                if (lVarK.a >= 0) {
                    int i5 = 0;
                    for (l lVar3 = lVarK; lVar3 != null; lVar3 = lVar3.d) {
                        if (lVar3.a == i3 && ((obj = lVar3.b) == obj2 || (obj != null && obj2.equals(obj)))) {
                            z = false;
                            break;
                        }
                        i5++;
                    }
                    z = true;
                    if (!z || i5 < 8) {
                        z2 = z;
                    } else {
                        long j6 = j3 + 1;
                        lVar.d = lVarK;
                        l lVar4 = lVar;
                        r rVar = null;
                        r rVar2 = null;
                        while (lVar4 != null) {
                            long j7 = j6;
                            r rVar3 = new r(lVar4.a, lVar4.b, lVar4.c, null, null);
                            rVar3.h = rVar2;
                            if (rVar2 == null) {
                                rVar = rVar3;
                            } else {
                                rVar2.d = rVar3;
                            }
                            lVar4 = lVar4.d;
                            rVar2 = rVar3;
                            j6 = j7;
                        }
                        h(lVarArr, i4, new q(rVar));
                        j3 = j6;
                    }
                } else if (((q) lVarK).e(i3, obj2, lVar.c) == null) {
                    j3 += j2;
                }
                z2 = false;
            }
            j2 = 1;
            if (z2) {
                j3++;
                lVar.d = lVarK;
                h(lVarArr, i4, lVar);
            }
            lVar = lVar2;
        }
        this.a = lVarArr;
        this.sizeCtl = iL - (iL >>> 2);
        this.baseCount = j3;
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap, j$.util.InterfaceC0089m
    public final Object putIfAbsent(Object obj, Object obj2) {
        return f(obj, obj2, true);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public final boolean remove(Object obj, Object obj2) {
        obj.getClass();
        return (obj2 == null || g(obj, null, obj2) == null) ? false : true;
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public final boolean replace(Object obj, Object obj2, Object obj3) {
        if (obj == null || obj2 == null || obj3 == null) {
            throw null;
        }
        return g(obj, obj3, obj2) != null;
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public final Object replace(Object obj, Object obj2) {
        if (obj == null || obj2 == null) {
            throw null;
        }
        return g(obj, obj2, null);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public final Object getOrDefault(Object obj, Object obj2) {
        V v = get(obj);
        return v == null ? obj2 : v;
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public final void forEach(BiConsumer biConsumer) {
        biConsumer.getClass();
        l[] lVarArr = this.a;
        if (lVarArr == null) {
            return;
        }
        p pVar = new p(lVarArr, lVarArr.length, 0, lVarArr.length);
        while (true) {
            l lVarA = pVar.a();
            if (lVarA == null) {
                return;
            } else {
                biConsumer.accept(lVarA.b, lVarA.c);
            }
        }
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public final void replaceAll(BiFunction biFunction) {
        biFunction.getClass();
        l[] lVarArr = this.a;
        if (lVarArr == null) {
            return;
        }
        p pVar = new p(lVarArr, lVarArr.length, 0, lVarArr.length);
        while (true) {
            l lVarA = pVar.a();
            if (lVarA == null) {
                return;
            }
            Object obj = lVarA.c;
            Object obj2 = lVarA.b;
            do {
                Object objApply = biFunction.apply(obj2, obj);
                objApply.getClass();
                if (g(obj2, objApply, obj) == null) {
                    obj = get(obj2);
                }
            } while (obj != null);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:60:0x008c, code lost:
    
        r5 = r5.c;
     */
    @Override // java.util.Map, java.util.concurrent.ConcurrentMap, j$.util.InterfaceC0089m
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object computeIfAbsent(Object obj, Function function) {
        r rVarB;
        Object obj2;
        Object obj3;
        Object obj4;
        if (obj == null || function == null) {
            throw null;
        }
        int i2 = i(obj.hashCode());
        l[] lVarArrE = this.a;
        Object objApply = null;
        int i3 = 0;
        while (true) {
            if (lVarArrE != null) {
                int length = lVarArrE.length;
                if (length != 0) {
                    int i4 = (length - 1) & i2;
                    l lVarK = k(lVarArrE, i4);
                    boolean z = true;
                    if (lVarK == null) {
                        m mVar = new m();
                        synchronized (mVar) {
                            try {
                                if (b(lVarArrE, i4, mVar)) {
                                    try {
                                        objApply = function.apply(obj);
                                        h(lVarArrE, i4, objApply != null ? new l(i2, obj, objApply) : null);
                                        i3 = 1;
                                    } catch (Throwable th) {
                                        h(lVarArrE, i4, null);
                                        throw th;
                                    }
                                }
                            } finally {
                            }
                        }
                        if (i3 != 0) {
                        }
                    } else {
                        int i5 = lVarK.a;
                        if (i5 == -1) {
                            lVarArrE = d(lVarArrE, lVarK);
                        } else {
                            if (i5 == i2 && (((obj3 = lVarK.b) == obj || (obj3 != null && obj.equals(obj3))) && (obj4 = lVarK.c) != null)) {
                                return obj4;
                            }
                            synchronized (lVarK) {
                                try {
                                    if (k(lVarArrE, i4) == lVarK) {
                                        if (i5 >= 0) {
                                            l lVar = lVarK;
                                            i3 = 1;
                                            while (true) {
                                                if (lVar.a != i2 || ((obj2 = lVar.b) != obj && (obj2 == null || !obj.equals(obj2)))) {
                                                    l lVar2 = lVar.d;
                                                    if (lVar2 == null) {
                                                        Object objApply2 = function.apply(obj);
                                                        if (objApply2 == null) {
                                                            objApply = objApply2;
                                                        } else {
                                                            if (lVar.d != null) {
                                                                throw new IllegalStateException("Recursive update");
                                                            }
                                                            lVar.d = new l(i2, obj, objApply2);
                                                            objApply = objApply2;
                                                        }
                                                    } else {
                                                        i3++;
                                                        lVar = lVar2;
                                                    }
                                                }
                                            }
                                        } else if (lVarK instanceof q) {
                                            q qVar = (q) lVarK;
                                            r rVar = qVar.e;
                                            if (rVar != null && (rVarB = rVar.b(i2, obj, null)) != null) {
                                                objApply = rVarB.c;
                                            } else {
                                                objApply = function.apply(obj);
                                                if (objApply != null) {
                                                    qVar.e(i2, obj, objApply);
                                                }
                                                i3 = 2;
                                            }
                                            z = false;
                                            i3 = 2;
                                        } else if (lVarK instanceof m) {
                                            throw new IllegalStateException("Recursive update");
                                        }
                                    }
                                    z = false;
                                } finally {
                                }
                            }
                            if (i3 != 0) {
                                if (i3 >= 8) {
                                    n(lVarArrE, i4);
                                }
                                if (!z) {
                                    return objApply;
                                }
                            }
                        }
                    }
                }
            }
            lVarArrE = e();
        }
        if (objApply != null) {
            a(1L, i3);
        }
        return objApply;
    }

    /* JADX WARN: Code restructure failed: missing block: B:61:0x00aa, code lost:
    
        throw new java.lang.IllegalStateException("Recursive update");
     */
    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object computeIfPresent(Object obj, BiFunction biFunction) {
        r rVarB;
        Object obj2;
        if (obj == null || biFunction == null) {
            throw null;
        }
        int i2 = i(obj.hashCode());
        l[] lVarArrE = this.a;
        int i3 = 0;
        Object objApply = null;
        int i4 = 0;
        while (true) {
            if (lVarArrE != null) {
                int length = lVarArrE.length;
                if (length != 0) {
                    int i5 = (length - 1) & i2;
                    l lVarK = k(lVarArrE, i5);
                    if (lVarK == null) {
                        break;
                    }
                    int i6 = lVarK.a;
                    if (i6 == -1) {
                        lVarArrE = d(lVarArrE, lVarK);
                    } else {
                        synchronized (lVarK) {
                            try {
                                if (k(lVarArrE, i5) == lVarK) {
                                    if (i6 >= 0) {
                                        i4 = 1;
                                        l lVar = null;
                                        l lVar2 = lVarK;
                                        while (true) {
                                            if (lVar2.a != i2 || ((obj2 = lVar2.b) != obj && (obj2 == null || !obj.equals(obj2)))) {
                                                l lVar3 = lVar2.d;
                                                if (lVar3 == null) {
                                                    break;
                                                }
                                                i4++;
                                                lVar = lVar2;
                                                lVar2 = lVar3;
                                            }
                                        }
                                        objApply = biFunction.apply(obj, lVar2.c);
                                        if (objApply != null) {
                                            lVar2.c = objApply;
                                        } else {
                                            l lVar4 = lVar2.d;
                                            if (lVar != null) {
                                                lVar.d = lVar4;
                                            } else {
                                                h(lVarArrE, i5, lVar4);
                                            }
                                            i3 = -1;
                                        }
                                    } else if (lVarK instanceof q) {
                                        q qVar = (q) lVarK;
                                        r rVar = qVar.e;
                                        if (rVar != null && (rVarB = rVar.b(i2, obj, null)) != null) {
                                            objApply = biFunction.apply(obj, rVarB.c);
                                            if (objApply != null) {
                                                rVarB.c = objApply;
                                            } else {
                                                if (qVar.f(rVarB)) {
                                                    h(lVarArrE, i5, p(qVar.f));
                                                }
                                                i3 = -1;
                                            }
                                        }
                                        i4 = 2;
                                    } else if (lVarK instanceof m) {
                                        break;
                                    }
                                }
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                        if (i4 != 0) {
                            break;
                        }
                    }
                }
            }
            lVarArrE = e();
        }
        if (i3 != 0) {
            a(i3, i4);
        }
        return objApply;
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public final Object compute(Object obj, BiFunction biFunction) {
        l lVar;
        Object obj2;
        if (obj == null || biFunction == null) {
            throw null;
        }
        int i2 = i(obj.hashCode());
        l[] lVarArrE = this.a;
        int i3 = 0;
        Object objApply = null;
        int i4 = 0;
        while (true) {
            if (lVarArrE != null) {
                int length = lVarArrE.length;
                if (length != 0) {
                    int i5 = (length - 1) & i2;
                    l lVarK = k(lVarArrE, i5);
                    if (lVarK == null) {
                        m mVar = new m();
                        synchronized (mVar) {
                            try {
                                if (b(lVarArrE, i5, mVar)) {
                                    try {
                                        objApply = biFunction.apply(obj, null);
                                        if (objApply != null) {
                                            lVar = new l(i2, obj, objApply);
                                            i4 = 1;
                                        } else {
                                            lVar = null;
                                        }
                                        h(lVarArrE, i5, lVar);
                                        i3 = 1;
                                    } catch (Throwable th) {
                                        h(lVarArrE, i5, null);
                                        throw th;
                                    }
                                }
                            } finally {
                            }
                        }
                        if (i3 != 0) {
                        }
                    } else {
                        int i6 = lVarK.a;
                        if (i6 == -1) {
                            lVarArrE = d(lVarArrE, lVarK);
                        } else {
                            synchronized (lVarK) {
                                try {
                                    if (k(lVarArrE, i5) == lVarK) {
                                        if (i6 >= 0) {
                                            l lVar2 = null;
                                            l lVar3 = lVarK;
                                            i3 = 1;
                                            while (true) {
                                                if (lVar3.a != i2 || ((obj2 = lVar3.b) != obj && (obj2 == null || !obj.equals(obj2)))) {
                                                    l lVar4 = lVar3.d;
                                                    if (lVar4 == null) {
                                                        Object objApply2 = biFunction.apply(obj, null);
                                                        if (objApply2 == null) {
                                                            objApply = objApply2;
                                                        } else {
                                                            if (lVar3.d != null) {
                                                                throw new IllegalStateException("Recursive update");
                                                            }
                                                            lVar3.d = new l(i2, obj, objApply2);
                                                            objApply = objApply2;
                                                            i4 = 1;
                                                        }
                                                    } else {
                                                        i3++;
                                                        lVar2 = lVar3;
                                                        lVar3 = lVar4;
                                                    }
                                                }
                                            }
                                            Object objApply3 = biFunction.apply(obj, lVar3.c);
                                            if (objApply3 != null) {
                                                lVar3.c = objApply3;
                                                objApply = objApply3;
                                            } else {
                                                l lVar5 = lVar3.d;
                                                if (lVar2 != null) {
                                                    lVar2.d = lVar5;
                                                } else {
                                                    h(lVarArrE, i5, lVar5);
                                                }
                                                objApply = objApply3;
                                                i4 = -1;
                                            }
                                        } else if (lVarK instanceof q) {
                                            q qVar = (q) lVarK;
                                            r rVar = qVar.e;
                                            r rVarB = rVar != null ? rVar.b(i2, obj, null) : null;
                                            Object objApply4 = biFunction.apply(obj, rVarB == null ? null : rVarB.c);
                                            if (objApply4 != null) {
                                                if (rVarB != null) {
                                                    rVarB.c = objApply4;
                                                } else {
                                                    qVar.e(i2, obj, objApply4);
                                                    i4 = 1;
                                                }
                                            } else if (rVarB != null) {
                                                if (qVar.f(rVarB)) {
                                                    h(lVarArrE, i5, p(qVar.f));
                                                }
                                                i4 = -1;
                                            }
                                            objApply = objApply4;
                                            i3 = 1;
                                        } else if (lVarK instanceof m) {
                                            throw new IllegalStateException("Recursive update");
                                        }
                                    }
                                } finally {
                                }
                            }
                            if (i3 != 0) {
                                if (i3 >= 8) {
                                    n(lVarArrE, i5);
                                }
                            }
                        }
                    }
                }
            }
            lVarArrE = e();
        }
        if (i4 != 0) {
            a(i4, i3);
        }
        return objApply;
    }

    /* JADX WARN: Code restructure failed: missing block: B:69:0x00dd, code lost:
    
        throw new java.lang.IllegalStateException("Recursive update");
     */
    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object merge(Object obj, Object obj2, BiFunction biFunction) {
        int i2;
        Object obj3;
        Object obj4 = obj2;
        if (obj == null || obj4 == null || biFunction == null) {
            throw null;
        }
        int i3 = i(obj.hashCode());
        l[] lVarArrE = this.a;
        int i4 = 0;
        Object obj5 = null;
        int i5 = 0;
        while (true) {
            if (lVarArrE != null) {
                int length = lVarArrE.length;
                if (length != 0) {
                    int i6 = (length - 1) & i3;
                    l lVarK = k(lVarArrE, i6);
                    i2 = 1;
                    if (lVarK == null) {
                        if (b(lVarArrE, i6, new l(i3, obj, obj4))) {
                            break;
                        }
                    } else {
                        int i7 = lVarK.a;
                        if (i7 == -1) {
                            lVarArrE = d(lVarArrE, lVarK);
                        } else {
                            synchronized (lVarK) {
                                try {
                                    if (k(lVarArrE, i6) == lVarK) {
                                        if (i7 >= 0) {
                                            l lVar = null;
                                            l lVar2 = lVarK;
                                            i4 = 1;
                                            while (true) {
                                                if (lVar2.a != i3 || ((obj3 = lVar2.b) != obj && (obj3 == null || !obj.equals(obj3)))) {
                                                    l lVar3 = lVar2.d;
                                                    if (lVar3 == null) {
                                                        lVar2.d = new l(i3, obj, obj4);
                                                        obj5 = obj4;
                                                        i5 = 1;
                                                        break;
                                                    }
                                                    i4++;
                                                    lVar = lVar2;
                                                    lVar2 = lVar3;
                                                }
                                            }
                                            Object objApply = biFunction.apply(lVar2.c, obj4);
                                            if (objApply != null) {
                                                lVar2.c = objApply;
                                                obj5 = objApply;
                                            } else {
                                                l lVar4 = lVar2.d;
                                                if (lVar != null) {
                                                    lVar.d = lVar4;
                                                } else {
                                                    h(lVarArrE, i6, lVar4);
                                                }
                                                obj5 = objApply;
                                                i5 = -1;
                                            }
                                        } else if (lVarK instanceof q) {
                                            q qVar = (q) lVarK;
                                            r rVar = qVar.e;
                                            r rVarB = rVar == null ? null : rVar.b(i3, obj, null);
                                            Object objApply2 = rVarB == null ? obj4 : biFunction.apply(rVarB.c, obj4);
                                            if (objApply2 != null) {
                                                if (rVarB != null) {
                                                    rVarB.c = objApply2;
                                                } else {
                                                    qVar.e(i3, obj, objApply2);
                                                    i5 = 1;
                                                }
                                            } else if (rVarB != null) {
                                                if (qVar.f(rVarB)) {
                                                    h(lVarArrE, i6, p(qVar.f));
                                                }
                                                i5 = -1;
                                            }
                                            i4 = 2;
                                            obj5 = objApply2;
                                        } else if (lVarK instanceof m) {
                                            break;
                                        }
                                    }
                                } catch (Throwable th) {
                                    throw th;
                                }
                            }
                            if (i4 != 0) {
                                if (i4 >= 8) {
                                    n(lVarArrE, i6);
                                }
                                i2 = i5;
                                obj4 = obj5;
                            }
                        }
                    }
                }
            }
            lVarArrE = e();
        }
        if (i2 != 0) {
            a(i2, i4);
        }
        return obj4;
    }

    private final l[] e() {
        while (true) {
            l[] lVarArr = this.a;
            if (lVarArr != null && lVarArr.length != 0) {
                return lVarArr;
            }
            int i2 = this.sizeCtl;
            if (i2 < 0) {
                Thread.yield();
            } else if (h.c(this, i, i2, -1)) {
                try {
                    l[] lVarArr2 = this.a;
                    if (lVarArr2 == null || lVarArr2.length == 0) {
                        int i3 = i2 > 0 ? i2 : 16;
                        l[] lVarArr3 = new l[i3];
                        this.a = lVarArr3;
                        i2 = i3 - (i3 >>> 2);
                        lVarArr2 = lVarArr3;
                    }
                    this.sizeCtl = i2;
                    return lVarArr2;
                } catch (Throwable th) {
                    this.sizeCtl = i2;
                    throw th;
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:98:0x013f, code lost:
    
        if (r25.c != r7) goto L150;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x0141, code lost:
    
        r25.c = (j$.util.concurrent.c[]) java.util.Arrays.copyOf(r7, r8 << 1);
     */
    /* JADX WARN: Removed duplicated region for block: B:146:0x019f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:165:0x00ba A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:6:0x001c  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00f7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void a(long j2, int i2) {
        boolean zD;
        boolean z;
        int length;
        boolean z2;
        int length2;
        int length3;
        c cVar;
        long j3;
        l[] lVarArr;
        int length4;
        l[] lVarArr2;
        c[] cVarArr = this.c;
        if (cVarArr == null) {
            j$.sun.misc.a aVar = h;
            long j4 = k;
            long j5 = this.baseCount;
            j3 = j5 + j2;
            if (!aVar.d(this, j4, j5, j3)) {
                if (cVarArr == null || (length3 = cVarArr.length - 1) < 0 || (cVar = cVarArr[length3 & z.c()]) == null) {
                    zD = true;
                } else {
                    j$.sun.misc.a aVar2 = h;
                    long j6 = m;
                    long j7 = cVar.value;
                    zD = aVar2.d(cVar, j6, j7, j7 + j2);
                    if (zD) {
                        if (i2 <= 1) {
                            return;
                        } else {
                            j3 = j();
                        }
                    }
                }
                int iC = z.c();
                if (iC == 0) {
                    z.g();
                    iC = z.c();
                    zD = true;
                }
                boolean z3 = zD;
                while (true) {
                    boolean z4 = false;
                    while (true) {
                        c[] cVarArr2 = this.c;
                        if (cVarArr2 != null && (length = cVarArr2.length) > 0) {
                            c cVar2 = cVarArr2[(length - 1) & iC];
                            if (cVar2 == null) {
                                if (this.cellsBusy == 0) {
                                    c cVar3 = new c(j2);
                                    if (this.cellsBusy == 0 && h.c(this, l, 0, 1)) {
                                        try {
                                            c[] cVarArr3 = this.c;
                                            if (cVarArr3 == null || (length2 = cVarArr3.length) <= 0) {
                                                z2 = false;
                                                if (!z2) {
                                                    return;
                                                }
                                            } else {
                                                int i3 = (length2 - 1) & iC;
                                                if (cVarArr3[i3] == null) {
                                                    cVarArr3[i3] = cVar3;
                                                    z2 = true;
                                                }
                                                if (!z2) {
                                                }
                                            }
                                        } finally {
                                        }
                                    }
                                }
                            } else {
                                if (z3) {
                                    j$.sun.misc.a aVar3 = h;
                                    long j8 = m;
                                    long j9 = cVar2.value;
                                    if (aVar3.d(cVar2, j8, j9, j9 + j2)) {
                                        return;
                                    }
                                    if (this.c == cVarArr2 && length < g) {
                                        if (!z4) {
                                            z4 = true;
                                        } else if (this.cellsBusy == 0 && aVar3.c(this, l, 0, 1)) {
                                            try {
                                                break;
                                            } finally {
                                            }
                                        }
                                    }
                                } else {
                                    z3 = true;
                                }
                                iC = z.a(iC);
                            }
                            z4 = false;
                            iC = z.a(iC);
                        } else if (this.cellsBusy == 0 && this.c == cVarArr2 && h.c(this, l, 0, 1)) {
                            try {
                                if (this.c == cVarArr2) {
                                    c[] cVarArr4 = new c[2];
                                    cVarArr4[iC & 1] = new c(j2);
                                    this.c = cVarArr4;
                                    z = true;
                                } else {
                                    z = false;
                                }
                                if (z) {
                                    return;
                                }
                            } finally {
                            }
                        } else {
                            j$.sun.misc.a aVar4 = h;
                            long j10 = k;
                            long j11 = this.baseCount;
                            if (aVar4.d(this, j10, j11, j11 + j2)) {
                                return;
                            }
                        }
                    }
                }
            }
        }
        if (i2 < 0) {
            return;
        }
        while (true) {
            int i4 = this.sizeCtl;
            if (j3 < i4 || (lVarArr = this.a) == null || (length4 = lVarArr.length) >= 1073741824) {
                return;
            }
            int iNumberOfLeadingZeros = Integer.numberOfLeadingZeros(length4) | 32768;
            if (i4 < 0) {
                if ((i4 >>> 16) != iNumberOfLeadingZeros || i4 == iNumberOfLeadingZeros + 1 || i4 == iNumberOfLeadingZeros + Settings.DEFAULT_INITIAL_WINDOW_SIZE || (lVarArr2 = this.b) == null || this.transferIndex <= 0) {
                    return;
                }
                if (h.c(this, i, i4, i4 + 1)) {
                    m(lVarArr, lVarArr2);
                }
            } else if (h.c(this, i, i4, (iNumberOfLeadingZeros << 16) + 2)) {
                m(lVarArr, null);
            }
            j3 = j();
        }
    }

    final l[] d(l[] lVarArr, l lVar) {
        int i2;
        if (lVar instanceof g) {
            l[] lVarArr2 = ((g) lVar).e;
            int iNumberOfLeadingZeros = Integer.numberOfLeadingZeros(lVarArr.length) | 32768;
            while (true) {
                if (lVarArr2 != this.b || this.a != lVarArr || (i2 = this.sizeCtl) >= 0 || (i2 >>> 16) != iNumberOfLeadingZeros || i2 == iNumberOfLeadingZeros + 1 || i2 == Settings.DEFAULT_INITIAL_WINDOW_SIZE + iNumberOfLeadingZeros || this.transferIndex <= 0) {
                    break;
                }
                if (h.c(this, i, i2, i2 + 1)) {
                    m(lVarArr, lVarArr2);
                    break;
                }
            }
            return lVarArr2;
        }
        return this.a;
    }

    private final void o(int i2) {
        int length;
        int iL = i2 >= 536870912 ? 1073741824 : l(i2 + (i2 >>> 1) + 1);
        while (true) {
            int i3 = this.sizeCtl;
            if (i3 < 0) {
                return;
            }
            l[] lVarArr = this.a;
            if (lVarArr == null || (length = lVarArr.length) == 0) {
                int i4 = i3 > iL ? i3 : iL;
                if (h.c(this, i, i3, -1)) {
                    try {
                        if (this.a == lVarArr) {
                            this.a = new l[i4];
                            i3 = i4 - (i4 >>> 2);
                        }
                    } finally {
                        this.sizeCtl = i3;
                    }
                } else {
                    continue;
                }
            } else {
                if (iL <= i3 || length >= 1073741824) {
                    return;
                }
                if (lVarArr == this.a) {
                    if (h.c(this, i, i3, ((Integer.numberOfLeadingZeros(length) | 32768) << 16) + 2)) {
                        m(lVarArr, null);
                    }
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v10, types: [j$.util.concurrent.l] */
    /* JADX WARN: Type inference failed for: r13v12, types: [j$.util.concurrent.l] */
    /* JADX WARN: Type inference failed for: r4v0, types: [j$.util.concurrent.l] */
    /* JADX WARN: Type inference failed for: r5v17, types: [j$.util.concurrent.l] */
    /* JADX WARN: Type inference failed for: r5v22, types: [j$.util.concurrent.l] */
    private final void m(l[] lVarArr, l[] lVarArr2) {
        l[] lVarArr3;
        int i2;
        int i3;
        g gVar;
        ConcurrentHashMap<K, V> concurrentHashMap;
        int i4;
        l qVar;
        l qVar2;
        r lVar;
        int i5;
        ConcurrentHashMap<K, V> concurrentHashMap2 = this;
        l[] lVarArr4 = lVarArr;
        int length = lVarArr4.length;
        int i6 = g;
        int i7 = i6 > 1 ? (length >>> 3) / i6 : length;
        int i8 = i7 < 16 ? 16 : i7;
        if (lVarArr2 == null) {
            try {
                l[] lVarArr5 = new l[length << 1];
                concurrentHashMap2.b = lVarArr5;
                concurrentHashMap2.transferIndex = length;
                lVarArr3 = lVarArr5;
            } catch (Throwable unused) {
                concurrentHashMap2.sizeCtl = Integer.MAX_VALUE;
                return;
            }
        } else {
            lVarArr3 = lVarArr2;
        }
        int length2 = lVarArr3.length;
        g gVar2 = new g(lVarArr3);
        int i9 = 0;
        int i10 = 0;
        boolean zB = true;
        boolean z = false;
        while (true) {
            if (zB) {
                int i11 = i10 - 1;
                if (i11 >= i9 || z) {
                    i9 = i9;
                    i10 = i11;
                    zB = false;
                } else {
                    int i12 = concurrentHashMap2.transferIndex;
                    if (i12 <= 0) {
                        i10 = -1;
                    } else {
                        j$.sun.misc.a aVar = h;
                        long j2 = j;
                        int i13 = i12 > i8 ? i12 - i8 : 0;
                        int i14 = i9;
                        if (aVar.c(this, j2, i12, i13)) {
                            i10 = i12 - 1;
                            i9 = i13;
                        } else {
                            i9 = i14;
                            i10 = i11;
                        }
                    }
                    zB = false;
                }
            } else {
                int i15 = i9;
                r lVar2 = null;
                if (i10 < 0 || i10 >= length || (i4 = i10 + length) >= length2) {
                    i2 = i8;
                    i3 = length2;
                    gVar = gVar2;
                    if (z) {
                        this.b = null;
                        this.a = lVarArr3;
                        this.sizeCtl = (length << 1) - (length >>> 1);
                        return;
                    }
                    concurrentHashMap = this;
                    j$.sun.misc.a aVar2 = h;
                    long j3 = i;
                    int i16 = concurrentHashMap.sizeCtl;
                    int i17 = i10;
                    if (!aVar2.c(this, j3, i16, i16 - 1)) {
                        i10 = i17;
                    } else {
                        if (i16 - 2 != ((Integer.numberOfLeadingZeros(length) | 32768) << 16)) {
                            return;
                        }
                        i10 = length;
                        zB = true;
                        z = true;
                    }
                } else {
                    ?? K = k(lVarArr4, i10);
                    if (K == 0) {
                        zB = b(lVarArr4, i10, gVar2);
                        concurrentHashMap = concurrentHashMap2;
                        i2 = i8;
                        i3 = length2;
                        gVar = gVar2;
                    } else {
                        int i18 = K.a;
                        if (i18 == -1) {
                            concurrentHashMap = concurrentHashMap2;
                            i2 = i8;
                            i3 = length2;
                            gVar = gVar2;
                            zB = true;
                        } else {
                            synchronized (K) {
                                try {
                                    if (k(lVarArr4, i10) == K) {
                                        if (i18 >= 0) {
                                            int i19 = i18 & length;
                                            r rVar = K;
                                            for (r rVar2 = K.d; rVar2 != null; rVar2 = rVar2.d) {
                                                int i20 = rVar2.a & length;
                                                if (i20 != i19) {
                                                    rVar = rVar2;
                                                    i19 = i20;
                                                }
                                            }
                                            if (i19 == 0) {
                                                lVar = null;
                                                lVar2 = rVar;
                                            } else {
                                                lVar = rVar;
                                            }
                                            l lVar3 = K;
                                            while (lVar3 != rVar) {
                                                int i21 = lVar3.a;
                                                Object obj = lVar3.b;
                                                int i22 = i8;
                                                Object obj2 = lVar3.c;
                                                if ((i21 & length) == 0) {
                                                    i5 = length2;
                                                    lVar2 = new l(i21, obj, obj2, lVar2);
                                                } else {
                                                    i5 = length2;
                                                    lVar = new l(i21, obj, obj2, lVar);
                                                }
                                                lVar3 = lVar3.d;
                                                i8 = i22;
                                                length2 = i5;
                                            }
                                            i2 = i8;
                                            i3 = length2;
                                            h(lVarArr3, i10, lVar2);
                                            h(lVarArr3, i4, lVar);
                                            h(lVarArr4, i10, gVar2);
                                            gVar = gVar2;
                                        } else {
                                            i2 = i8;
                                            i3 = length2;
                                            if (K instanceof q) {
                                                q qVar3 = (q) K;
                                                r rVar3 = null;
                                                r rVar4 = null;
                                                l lVar4 = qVar3.f;
                                                int i23 = 0;
                                                int i24 = 0;
                                                r rVar5 = null;
                                                while (lVar4 != null) {
                                                    q qVar4 = qVar3;
                                                    int i25 = lVar4.a;
                                                    g gVar3 = gVar2;
                                                    r rVar6 = new r(i25, lVar4.b, lVar4.c, null, null);
                                                    if ((i25 & length) == 0) {
                                                        rVar6.h = rVar4;
                                                        if (rVar4 == null) {
                                                            lVar2 = rVar6;
                                                        } else {
                                                            rVar4.d = rVar6;
                                                        }
                                                        i23++;
                                                        rVar4 = rVar6;
                                                    } else {
                                                        rVar6.h = rVar3;
                                                        if (rVar3 == null) {
                                                            rVar5 = rVar6;
                                                        } else {
                                                            rVar3.d = rVar6;
                                                        }
                                                        i24++;
                                                        rVar3 = rVar6;
                                                    }
                                                    lVar4 = lVar4.d;
                                                    qVar3 = qVar4;
                                                    gVar2 = gVar3;
                                                }
                                                q qVar5 = qVar3;
                                                g gVar4 = gVar2;
                                                if (i23 <= 6) {
                                                    qVar = p(lVar2);
                                                } else {
                                                    qVar = i24 != 0 ? new q(lVar2) : qVar5;
                                                }
                                                if (i24 <= 6) {
                                                    qVar2 = p(rVar5);
                                                } else {
                                                    qVar2 = i23 != 0 ? new q(rVar5) : qVar5;
                                                }
                                                h(lVarArr3, i10, qVar);
                                                h(lVarArr3, i4, qVar2);
                                                lVarArr4 = lVarArr;
                                                gVar = gVar4;
                                                h(lVarArr4, i10, gVar);
                                            }
                                        }
                                        zB = true;
                                    } else {
                                        i2 = i8;
                                        i3 = length2;
                                    }
                                    gVar = gVar2;
                                } finally {
                                }
                            }
                            concurrentHashMap = this;
                        }
                    }
                }
                gVar2 = gVar;
                concurrentHashMap2 = concurrentHashMap;
                i9 = i15;
                i8 = i2;
                length2 = i3;
            }
        }
    }

    final long j() {
        c[] cVarArr = this.c;
        long j2 = this.baseCount;
        if (cVarArr != null) {
            for (c cVar : cVarArr) {
                if (cVar != null) {
                    j2 += cVar.value;
                }
            }
        }
        return j2;
    }

    private final void n(l[] lVarArr, int i2) {
        int length = lVarArr.length;
        if (length < 64) {
            o(length << 1);
            return;
        }
        l lVarK = k(lVarArr, i2);
        if (lVarK == null || lVarK.a < 0) {
            return;
        }
        synchronized (lVarK) {
            try {
                if (k(lVarArr, i2) == lVarK) {
                    r rVar = null;
                    l lVar = lVarK;
                    r rVar2 = null;
                    while (lVar != null) {
                        r rVar3 = new r(lVar.a, lVar.b, lVar.c, null, null);
                        rVar3.h = rVar2;
                        if (rVar2 == null) {
                            rVar = rVar3;
                        } else {
                            rVar2.d = rVar3;
                        }
                        lVar = lVar.d;
                        rVar2 = rVar3;
                    }
                    h(lVarArr, i2, new q(rVar));
                }
            } finally {
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v2, types: [j$.util.concurrent.l] */
    static l p(r rVar) {
        l lVar = null;
        l lVar2 = null;
        for (r rVar2 = rVar; rVar2 != null; rVar2 = rVar2.d) {
            l lVar3 = new l(rVar2.a, rVar2.b, rVar2.c);
            if (lVar2 == null) {
                lVar = lVar3;
            } else {
                lVar2.d = lVar3;
            }
            lVar2 = lVar3;
        }
        return lVar;
    }
}
