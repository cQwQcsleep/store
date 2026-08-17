package com.android.tools.r8.internal;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class I40 implements Ai0 {
    public final C0692Ng b;
    public final EnumC2890vp c;
    public final C2975wo d;
    public final C1472fD e;
    public final List f;

    public I40(C0692Ng c0692Ng, C2975wo c2975wo, C1472fD c1472fD, List list) {
        C2292op c2292op = EnumC2890vp.b;
        this.b = c0692Ng;
        this.c = c2292op;
        this.d = c2975wo;
        this.e = c1472fD;
        this.f = list;
    }

    /* JADX WARN: Code duplicated, block: B:48:0x00f0  */
    /* JADX WARN: Code duplicated, block: B:50:0x00f8  */
    /* JADX WARN: Code duplicated, block: B:56:0x0111  */
    /* JADX WARN: Code duplicated, block: B:63:0x012b  */
    /* JADX WARN: Code duplicated, block: B:66:0x013b  */
    /* JADX WARN: Code duplicated, block: B:67:0x0149  */
    /* JADX WARN: Code duplicated, block: B:70:0x014f  */
    /* JADX WARN: Code duplicated, block: B:71:0x0152  */
    /* JADX WARN: Code duplicated, block: B:73:0x0155  */
    /* JADX WARN: Code duplicated, block: B:77:0x017b  */
    /* JADX WARN: Code duplicated, block: B:89:0x01b2 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:92:0x01a0 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:95:0x017c A[SYNTHETIC] */
    public final LinkedHashMap a(C0471Es c0471Es, Fj0 fj0, Class cls, boolean z) {
        Method methodA;
        List list;
        List listSingletonList;
        int size;
        D40 d40;
        int i;
        Field[] fieldArr;
        int i2;
        int i3;
        D40 d41;
        boolean z2;
        Fj0 fj1;
        Class cls2;
        boolean z3;
        int modifiers;
        boolean z4;
        InterfaceC1386eD interfaceC1386eD;
        AbstractC3220zi0 abstractC3220zi0A;
        boolean z5;
        D40 d42;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (!cls.isInterface()) {
            Fj0 fj2 = fj0;
            Class cls3 = cls;
            while (cls3 != Object.class) {
                Field[] declaredFields = cls3.getDeclaredFields();
                if (cls3 != cls && declaredFields.length > 0) {
                    Iterator it = this.f.iterator();
                    if (it.hasNext()) {
                        it.next().getClass();
                        throw new ClassCastException();
                    }
                }
                int length = declaredFields.length;
                boolean z6 = false;
                int i4 = 0;
                while (i4 < length) {
                    Field field = declaredFields[i4];
                    boolean zA = this.a(field, true);
                    boolean zA2 = this.a(field, z6);
                    if (zA || zA2) {
                        if (!z) {
                            methodA = null;
                        } else if (Modifier.isStatic(field.getModifiers())) {
                            zA2 = z6;
                            methodA = null;
                        } else {
                            methodA = B40.a.a(cls3, field);
                            B40.a(methodA);
                            if (methodA.getAnnotation(InterfaceC1669hb0.class) != null && field.getAnnotation(InterfaceC1669hb0.class) == null) {
                                throw new C1729iD(C40.a("@SerializedName on ", B40.a(methodA, z6), " is not supported"));
                            }
                        }
                        if (methodA == null) {
                            B40.a(field);
                        }
                        boolean z7 = true;
                        Type typeA = AbstractC1278d.a(fj2.b, cls3, field.getGenericType(), new HashMap());
                        InterfaceC1669hb0 interfaceC1669hb0 = (InterfaceC1669hb0) field.getAnnotation(InterfaceC1669hb0.class);
                        if (interfaceC1669hb0 == null) {
                            listSingletonList = Collections.singletonList(this.c.a(field));
                        } else {
                            String strValue = interfaceC1669hb0.value();
                            String[] strArrAlternate = interfaceC1669hb0.alternate();
                            if (strArrAlternate.length == 0) {
                                listSingletonList = Collections.singletonList(strValue);
                            } else {
                                ArrayList arrayList = new ArrayList(strArrAlternate.length + 1);
                                arrayList.add(strValue);
                                Collections.addAll(arrayList, strArrAlternate);
                                list = arrayList;
                            }
                            size = list.size();
                            d40 = null;
                            i = 0;
                            while (i < size) {
                                String str = (String) list.get(i);
                                if (i != 0) {
                                    zA = false;
                                }
                                int i5 = i;
                                fj1 = new Fj0(typeA);
                                List list2 = list;
                                cls2 = fj1.a;
                                if (cls2 == null && cls2.isPrimitive()) {
                                    z3 = z7;
                                } else {
                                    z3 = false;
                                }
                                modifiers = field.getModifiers();
                                if (Modifier.isStatic(modifiers) || !Modifier.isFinal(modifiers)) {
                                    z4 = false;
                                } else {
                                    z4 = z7;
                                }
                                int i6 = size;
                                interfaceC1386eD = (InterfaceC1386eD) field.getAnnotation(InterfaceC1386eD.class);
                                Field[] fieldArr2 = declaredFields;
                                if (interfaceC1386eD != null) {
                                    C1472fD c1472fD = this.e;
                                    C0692Ng c0692Ng = this.b;
                                    c1472fD.getClass();
                                    abstractC3220zi0A = C1472fD.a(c0692Ng, c0471Es, fj1, interfaceC1386eD);
                                } else {
                                    abstractC3220zi0A = null;
                                }
                                int i7 = length;
                                boolean z8 = zA;
                                if (abstractC3220zi0A != null) {
                                    z5 = z7;
                                } else {
                                    z5 = false;
                                }
                                if (abstractC3220zi0A == null) {
                                    abstractC3220zi0A = c0471Es.a(fj1);
                                }
                                int i8 = i4;
                                d42 = d40;
                                boolean z9 = z7;
                                Type type = typeA;
                                boolean z10 = zA2;
                                d40 = (D40) linkedHashMap.put(str, new D40(str, field, z8, z10, false, methodA, z5, abstractC3220zi0A, c0471Es, fj1, z3, z4));
                                if (d42 == null) {
                                    d40 = d42;
                                }
                                i = i5 + 1;
                                this = this;
                                c0471Es = c0471Es;
                                zA = z8;
                                zA2 = z10;
                                methodA = methodA;
                                length = i7;
                                typeA = type;
                                list = list2;
                                z7 = z9;
                                size = i6;
                                declaredFields = fieldArr2;
                                i4 = i8;
                            }
                            fieldArr = declaredFields;
                            i2 = length;
                            i3 = i4;
                            d41 = d40;
                            z2 = false;
                            if (d41 == null) {
                                StringBuilder sb = new StringBuilder("Class ");
                                sb.append(cls.getName());
                                sb.append(" declares multiple JSON fields named '");
                                sb.append(d41.a);
                                sb.append("'; conflict is caused by fields ");
                                Field field2 = d41.b;
                                AbstractC3082y40 abstractC3082y40 = B40.a;
                                sb.append(field2.getDeclaringClass().getName() + "#" + field2.getName());
                                sb.append(" and ");
                                sb.append(field.getDeclaringClass().getName() + "#" + field.getName());
                                throw new IllegalArgumentException(sb.toString());
                            }
                        }
                        list = listSingletonList;
                        size = list.size();
                        d40 = null;
                        i = 0;
                        while (i < size) {
                            String str2 = (String) list.get(i);
                            if (i != 0) {
                                zA = false;
                            }
                            int i9 = i;
                            fj1 = new Fj0(typeA);
                            List list3 = list;
                            cls2 = fj1.a;
                            if (cls2 == null) {
                                z3 = false;
                            } else {
                                z3 = false;
                            }
                            modifiers = field.getModifiers();
                            if (Modifier.isStatic(modifiers)) {
                                z4 = false;
                            } else {
                                z4 = false;
                            }
                            int i10 = size;
                            interfaceC1386eD = (InterfaceC1386eD) field.getAnnotation(InterfaceC1386eD.class);
                            Field[] fieldArr3 = declaredFields;
                            if (interfaceC1386eD != null) {
                                C1472fD c1472fD2 = this.e;
                                C0692Ng c0692Ng2 = this.b;
                                c1472fD2.getClass();
                                abstractC3220zi0A = C1472fD.a(c0692Ng2, c0471Es, fj1, interfaceC1386eD);
                            } else {
                                abstractC3220zi0A = null;
                            }
                            int i11 = length;
                            boolean z11 = zA;
                            if (abstractC3220zi0A != null) {
                                z5 = z7;
                            } else {
                                z5 = false;
                            }
                            if (abstractC3220zi0A == null) {
                                abstractC3220zi0A = c0471Es.a(fj1);
                            }
                            int i12 = i4;
                            d42 = d40;
                            boolean z12 = z7;
                            Type type2 = typeA;
                            boolean z13 = zA2;
                            d40 = (D40) linkedHashMap.put(str2, new D40(str2, field, z11, z13, false, methodA, z5, abstractC3220zi0A, c0471Es, fj1, z3, z4));
                            if (d42 == null) {
                                d40 = d42;
                            }
                            i = i9 + 1;
                            this = this;
                            c0471Es = c0471Es;
                            zA = z11;
                            zA2 = z13;
                            methodA = methodA;
                            length = i11;
                            typeA = type2;
                            list = list3;
                            z7 = z12;
                            size = i10;
                            declaredFields = fieldArr3;
                            i4 = i12;
                        }
                        fieldArr = declaredFields;
                        i2 = length;
                        i3 = i4;
                        d41 = d40;
                        z2 = false;
                        if (d41 == null) {
                            StringBuilder sb2 = new StringBuilder("Class ");
                            sb2.append(cls.getName());
                            sb2.append(" declares multiple JSON fields named '");
                            sb2.append(d41.a);
                            sb2.append("'; conflict is caused by fields ");
                            Field field3 = d41.b;
                            AbstractC3082y40 abstractC3082y41 = B40.a;
                            sb2.append(field3.getDeclaringClass().getName() + "#" + field3.getName());
                            sb2.append(" and ");
                            sb2.append(field.getDeclaringClass().getName() + "#" + field.getName());
                            throw new IllegalArgumentException(sb2.toString());
                        }
                    } else {
                        cls3 = cls3;
                        fieldArr = declaredFields;
                        i2 = length;
                        z2 = z6;
                        i3 = i4;
                    }
                    i4 = i3 + 1;
                    this = this;
                    c0471Es = c0471Es;
                    length = i2;
                    cls3 = cls3;
                    declaredFields = fieldArr;
                    z6 = z2;
                }
                Class cls4 = cls3;
                fj2 = new Fj0(AbstractC1278d.a(fj2.b, cls4, cls4.getGenericSuperclass(), new HashMap()));
                cls3 = fj2.a;
            }
        }
        return linkedHashMap;
    }

    @Override // com.android.tools.r8.internal.Ai0
    public final AbstractC3220zi0 a(C0471Es c0471Es, Fj0 fj0) {
        Class cls = fj0.a;
        if (!Object.class.isAssignableFrom(cls)) {
            return null;
        }
        Iterator it = this.f.iterator();
        if (!it.hasNext()) {
            if (B40.a.c(cls)) {
                return new H40(cls, a(c0471Es, fj0, cls, true));
            }
            return new G40(this.b.a(fj0), a(c0471Es, fj0, cls, false));
        }
        it.next().getClass();
        throw new ClassCastException();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void a(Object obj, AccessibleObject accessibleObject) {
        if (Modifier.isStatic(((Member) accessibleObject).getModifiers())) {
            obj = null;
        }
        if (AbstractC2911w40.a.a(obj, accessibleObject)) {
            return;
        }
        throw new C1729iD(B40.a(accessibleObject, true) + " is not accessible and ReflectionAccessFilter does not permit making it accessible. Register a TypeAdapter for the declaring type, adjust the access filter or increase the visibility of the element and its declaring type.");
    }

    public final boolean a(Field field, boolean z) {
        C2975wo c2975wo = this.d;
        Class<?> type = field.getType();
        c2975wo.getClass();
        if (C2975wo.a(type)) {
            return false;
        }
        Iterator it = (z ? c2975wo.b : c2975wo.c).iterator();
        if (!it.hasNext()) {
            C2975wo c2975wo2 = this.d;
            c2975wo2.getClass();
            if ((field.getModifiers() & 136) != 0 || field.isSynthetic() || C2975wo.a(field.getType())) {
                return false;
            }
            List list = z ? c2975wo2.b : c2975wo2.c;
            if (list.isEmpty()) {
                return true;
            }
            new C1185bp(field);
            Iterator it2 = list.iterator();
            if (!it2.hasNext()) {
                return true;
            }
            it2.next().getClass();
            throw new ClassCastException();
        }
        it.next().getClass();
        throw new ClassCastException();
    }
}
