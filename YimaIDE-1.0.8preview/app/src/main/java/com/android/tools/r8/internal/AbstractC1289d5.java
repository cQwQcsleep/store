package com.android.tools.r8.internal;

import com.reandroid.arsc.chunk.OverlayablePolicy;
import java.util.ArrayList;

/* JADX INFO: renamed from: com.android.tools.r8.internal.d5, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1289d5 {
    public static final /* synthetic */ AbstractC2083mQ[] a;
    public static final I6 b;
    public static final I6 c;

    static {
        C2168nQ c2168nQ = new C2168nQ(AbstractC2654t40.a(), "hasAnnotations", "getHasAnnotations(Lkotlinx/metadata/KmClass;)Z");
        AbstractC2654t40.a.getClass();
        a = new AbstractC2083mQ[]{c2168nQ, new C2168nQ(AbstractC2654t40.a(), "hasAnnotations", "getHasAnnotations(Lkotlinx/metadata/KmConstructor;)Z"), new C2168nQ(AbstractC2654t40.a(), "hasAnnotations", "getHasAnnotations(Lkotlinx/metadata/KmFunction;)Z"), new C2168nQ(AbstractC2654t40.a(), "hasAnnotations", "getHasAnnotations(Lkotlinx/metadata/KmProperty;)Z"), new C2168nQ(AbstractC2654t40.a(), "hasAnnotations", "getHasAnnotations(Lkotlinx/metadata/KmPropertyAccessorAttributes;)Z"), new C2168nQ(AbstractC2654t40.a(), "hasAnnotations", "getHasAnnotations(Lkotlinx/metadata/KmValueParameter;)Z"), new C2168nQ(AbstractC2654t40.a(), "hasAnnotations", "getHasAnnotations(Lkotlinx/metadata/KmTypeAlias;)Z"), new C2168nQ(AbstractC2654t40.a(), "modality", "getModality(Lkotlinx/metadata/KmClass;)Lkotlinx/metadata/Modality;"), new C2168nQ(AbstractC2654t40.a(), "visibility", "getVisibility(Lkotlinx/metadata/KmClass;)Lkotlinx/metadata/Visibility;"), new C2168nQ(AbstractC2654t40.a(), "kind", "getKind(Lkotlinx/metadata/KmClass;)Lkotlinx/metadata/ClassKind;"), new C2168nQ(AbstractC2654t40.a(), "isInner", "isInner(Lkotlinx/metadata/KmClass;)Z"), new C2168nQ(AbstractC2654t40.a(), "isData", "isData(Lkotlinx/metadata/KmClass;)Z"), new C2168nQ(AbstractC2654t40.a(), "isExternal", "isExternal(Lkotlinx/metadata/KmClass;)Z"), new C2168nQ(AbstractC2654t40.a(), "isExpect", "isExpect(Lkotlinx/metadata/KmClass;)Z"), new C2168nQ(AbstractC2654t40.a(), "isValue", "isValue(Lkotlinx/metadata/KmClass;)Z"), new C2168nQ(AbstractC2654t40.a(), "isFunInterface", "isFunInterface(Lkotlinx/metadata/KmClass;)Z"), new C2168nQ(AbstractC2654t40.a(), "hasEnumEntries", "getHasEnumEntries(Lkotlinx/metadata/KmClass;)Z"), new C2168nQ(AbstractC2654t40.a(), "visibility", "getVisibility(Lkotlinx/metadata/KmConstructor;)Lkotlinx/metadata/Visibility;"), new C2168nQ(AbstractC2654t40.a(), "isSecondary", "isSecondary(Lkotlinx/metadata/KmConstructor;)Z"), new C2168nQ(AbstractC2654t40.a(), "hasNonStableParameterNames", "getHasNonStableParameterNames(Lkotlinx/metadata/KmConstructor;)Z"), new C2168nQ(AbstractC2654t40.a(), "kind", "getKind(Lkotlinx/metadata/KmFunction;)Lkotlinx/metadata/MemberKind;"), new C2168nQ(AbstractC2654t40.a(), "visibility", "getVisibility(Lkotlinx/metadata/KmFunction;)Lkotlinx/metadata/Visibility;"), new C2168nQ(AbstractC2654t40.a(), "modality", "getModality(Lkotlinx/metadata/KmFunction;)Lkotlinx/metadata/Modality;"), new C2168nQ(AbstractC2654t40.a(), "isOperator", "isOperator(Lkotlinx/metadata/KmFunction;)Z"), new C2168nQ(AbstractC2654t40.a(), "isInfix", "isInfix(Lkotlinx/metadata/KmFunction;)Z"), new C2168nQ(AbstractC2654t40.a(), "isInline", "isInline(Lkotlinx/metadata/KmFunction;)Z"), new C2168nQ(AbstractC2654t40.a(), "isTailrec", "isTailrec(Lkotlinx/metadata/KmFunction;)Z"), new C2168nQ(AbstractC2654t40.a(), "isExternal", "isExternal(Lkotlinx/metadata/KmFunction;)Z"), new C2168nQ(AbstractC2654t40.a(), "isSuspend", "isSuspend(Lkotlinx/metadata/KmFunction;)Z"), new C2168nQ(AbstractC2654t40.a(), "isExpect", "isExpect(Lkotlinx/metadata/KmFunction;)Z"), new C2168nQ(AbstractC2654t40.a(), "hasNonStableParameterNames", "getHasNonStableParameterNames(Lkotlinx/metadata/KmFunction;)Z"), new C2168nQ(AbstractC2654t40.a(), "visibility", "getVisibility(Lkotlinx/metadata/KmProperty;)Lkotlinx/metadata/Visibility;"), new C2168nQ(AbstractC2654t40.a(), "modality", "getModality(Lkotlinx/metadata/KmProperty;)Lkotlinx/metadata/Modality;"), new C2168nQ(AbstractC2654t40.a(), "kind", "getKind(Lkotlinx/metadata/KmProperty;)Lkotlinx/metadata/MemberKind;"), new C2168nQ(AbstractC2654t40.a(), "isVar", "isVar(Lkotlinx/metadata/KmProperty;)Z"), new C2168nQ(AbstractC2654t40.a(), "hasGetter", "getHasGetter(Lkotlinx/metadata/KmProperty;)Z"), new C2168nQ(AbstractC2654t40.a(), "hasSetter", "getHasSetter(Lkotlinx/metadata/KmProperty;)Z"), new C2168nQ(AbstractC2654t40.a(), "isConst", "isConst(Lkotlinx/metadata/KmProperty;)Z"), new C2168nQ(AbstractC2654t40.a(), "isLateinit", "isLateinit(Lkotlinx/metadata/KmProperty;)Z"), new C2168nQ(AbstractC2654t40.a(), "hasConstant", "getHasConstant(Lkotlinx/metadata/KmProperty;)Z"), new C2168nQ(AbstractC2654t40.a(), "isExternal", "isExternal(Lkotlinx/metadata/KmProperty;)Z"), new C2168nQ(AbstractC2654t40.a(), "isDelegated", "isDelegated(Lkotlinx/metadata/KmProperty;)Z"), new C2168nQ(AbstractC2654t40.a(), "isExpect", "isExpect(Lkotlinx/metadata/KmProperty;)Z"), new C2168nQ(AbstractC2654t40.a(), "visibility", "getVisibility(Lkotlinx/metadata/KmPropertyAccessorAttributes;)Lkotlinx/metadata/Visibility;"), new C2168nQ(AbstractC2654t40.a(), "modality", "getModality(Lkotlinx/metadata/KmPropertyAccessorAttributes;)Lkotlinx/metadata/Modality;"), new C2168nQ(AbstractC2654t40.a(), "isNotDefault", "isNotDefault(Lkotlinx/metadata/KmPropertyAccessorAttributes;)Z"), new C2168nQ(AbstractC2654t40.a(), "isExternal", "isExternal(Lkotlinx/metadata/KmPropertyAccessorAttributes;)Z"), new C2168nQ(AbstractC2654t40.a(), "isInline", "isInline(Lkotlinx/metadata/KmPropertyAccessorAttributes;)Z"), new C2168nQ(AbstractC2654t40.a(), "isNullable", "isNullable(Lkotlinx/metadata/KmType;)Z"), new C2168nQ(AbstractC2654t40.a(), "isSuspend", "isSuspend(Lkotlinx/metadata/KmType;)Z"), new C2168nQ(AbstractC2654t40.a(), "isDefinitelyNonNull", "isDefinitelyNonNull(Lkotlinx/metadata/KmType;)Z"), new C2168nQ(AbstractC2654t40.a(), "isReified", "isReified(Lkotlinx/metadata/KmTypeParameter;)Z"), new C2168nQ(AbstractC2654t40.a(), "visibility", "getVisibility(Lkotlinx/metadata/KmTypeAlias;)Lkotlinx/metadata/Visibility;"), new C2168nQ(AbstractC2654t40.a(), "declaresDefaultValue", "getDeclaresDefaultValue(Lkotlinx/metadata/KmValueParameter;)Z"), new C2168nQ(AbstractC2654t40.a(), "isCrossinline", "isCrossinline(Lkotlinx/metadata/KmValueParameter;)Z"), new C2168nQ(AbstractC2654t40.a(), "isNoinline", "isNoinline(Lkotlinx/metadata/KmValueParameter;)Z"), new C2168nQ(AbstractC2654t40.a(), "isNegated", "isNegated(Lkotlinx/metadata/KmEffectExpression;)Z"), new C2168nQ(AbstractC2654t40.a(), "isNullCheckPredicate", "isNullCheckPredicate(Lkotlinx/metadata/KmEffectExpression;)Z")};
        AbstractC2207nq.a(new AbstractC2083mQ() { // from class: com.android.tools.r8.internal.O4
            @Override // com.android.tools.r8.internal.AbstractC2083mQ
            public final Object a(Object obj) {
                return Integer.valueOf(((RH) obj).a);
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final String d() {
                return OverlayablePolicy.NAME_flags;
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final InterfaceC2328pE e() {
                return AbstractC2654t40.a(RH.class);
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final String f() {
                return "getFlags()I";
            }

            @Override // com.android.tools.r8.internal.AbstractC2083mQ
            public final void a(Integer num, Object obj) {
                ((RH) obj).a = num.intValue();
            }
        });
        AbstractC2207nq.a(new AbstractC2083mQ() { // from class: com.android.tools.r8.internal.P4
            @Override // com.android.tools.r8.internal.AbstractC2083mQ
            public final Object a(Object obj) {
                return Integer.valueOf(((XH) obj).a);
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final String d() {
                return OverlayablePolicy.NAME_flags;
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final InterfaceC2328pE e() {
                return AbstractC2654t40.a(XH.class);
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final String f() {
                return "getFlags()I";
            }

            @Override // com.android.tools.r8.internal.AbstractC2083mQ
            public final void a(Integer num, Object obj) {
                ((XH) obj).a = num.intValue();
            }
        });
        AbstractC2207nq.a(new AbstractC2083mQ() { // from class: com.android.tools.r8.internal.J4
            @Override // com.android.tools.r8.internal.AbstractC2083mQ
            public final Object a(Object obj) {
                return Integer.valueOf(((C1989lI) obj).b);
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final String d() {
                return OverlayablePolicy.NAME_flags;
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final InterfaceC2328pE e() {
                return AbstractC2654t40.a(C1989lI.class);
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final String f() {
                return "getFlags()I";
            }

            @Override // com.android.tools.r8.internal.AbstractC2083mQ
            public final void a(Integer num, Object obj) {
                ((C1989lI) obj).b = num.intValue();
            }
        });
        AbstractC2207nq.a(new AbstractC2083mQ() { // from class: com.android.tools.r8.internal.K4
            @Override // com.android.tools.r8.internal.AbstractC2083mQ
            public final Object a(Object obj) {
                return Integer.valueOf(((C2673tI) obj).b);
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final String d() {
                return OverlayablePolicy.NAME_flags;
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final InterfaceC2328pE e() {
                return AbstractC2654t40.a(C2673tI.class);
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final String f() {
                return "getFlags()I";
            }

            @Override // com.android.tools.r8.internal.AbstractC2083mQ
            public final void a(Integer num, Object obj) {
                ((C2673tI) obj).b = num.intValue();
            }
        });
        AbstractC2207nq.a(new AbstractC2083mQ() { // from class: com.android.tools.r8.internal.L4
            @Override // com.android.tools.r8.internal.AbstractC2083mQ
            public final Object a(Object obj) {
                return Integer.valueOf(((C2759uI) obj).a);
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final String d() {
                return OverlayablePolicy.NAME_flags;
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final InterfaceC2328pE e() {
                return AbstractC2654t40.a(C2759uI.class);
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final String f() {
                return "getFlags$kotlinx_metadata()I";
            }

            @Override // com.android.tools.r8.internal.AbstractC2083mQ
            public final void a(Integer num, Object obj) {
                ((C2759uI) obj).a = num.intValue();
            }
        });
        AbstractC2207nq.a(new AbstractC2083mQ() { // from class: com.android.tools.r8.internal.M4
            @Override // com.android.tools.r8.internal.AbstractC2083mQ
            public final Object a(Object obj) {
                return Integer.valueOf(((II) obj).a());
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final String d() {
                return OverlayablePolicy.NAME_flags;
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final InterfaceC2328pE e() {
                return AbstractC2654t40.a(II.class);
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final String f() {
                return "getFlags()I";
            }

            @Override // com.android.tools.r8.internal.AbstractC2083mQ
            public final void a(Integer num, Object obj) {
                ((II) obj).b = num.intValue();
            }
        });
        AbstractC2207nq.a(new AbstractC2083mQ() { // from class: com.android.tools.r8.internal.N4
            @Override // com.android.tools.r8.internal.AbstractC2083mQ
            public final Object a(Object obj) {
                return Integer.valueOf(((C3185zI) obj).a);
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final String d() {
                return OverlayablePolicy.NAME_flags;
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final InterfaceC2328pE e() {
                return AbstractC2654t40.a(C3185zI.class);
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final String f() {
                return "getFlags()I";
            }

            @Override // com.android.tools.r8.internal.AbstractC2083mQ
            public final void a(Integer num, Object obj) {
                ((C3185zI) obj).a = num.intValue();
            }
        });
        AbstractC2207nq.b(new AbstractC2083mQ() { // from class: com.android.tools.r8.internal.V4
            @Override // com.android.tools.r8.internal.AbstractC2083mQ
            public final Object a(Object obj) {
                return Integer.valueOf(((RH) obj).a);
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final String d() {
                return OverlayablePolicy.NAME_flags;
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final InterfaceC2328pE e() {
                return AbstractC2654t40.a(RH.class);
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final String f() {
                return "getFlags()I";
            }

            @Override // com.android.tools.r8.internal.AbstractC2083mQ
            public final void a(Integer num, Object obj) {
                ((RH) obj).a = num.intValue();
            }
        });
        AbstractC2207nq.c(new AbstractC2083mQ() { // from class: com.android.tools.r8.internal.b5
            @Override // com.android.tools.r8.internal.AbstractC2083mQ
            public final Object a(Object obj) {
                return Integer.valueOf(((RH) obj).a);
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final String d() {
                return OverlayablePolicy.NAME_flags;
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final InterfaceC2328pE e() {
                return AbstractC2654t40.a(RH.class);
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final String f() {
                return "getFlags()I";
            }

            @Override // com.android.tools.r8.internal.AbstractC2083mQ
            public final void a(Integer num, Object obj) {
                ((RH) obj).a = num.intValue();
            }
        });
        KB.b(AbstractC2805uq.e, "CLASS_KIND");
        C3059xn c3059xn = EnumC2866vc.c;
        ArrayList arrayList = new ArrayList(AbstractC2015le.a((Iterable) c3059xn));
        C1363e0 c1363e0 = new C1363e0(c3059xn);
        while (c1363e0.hasNext()) {
            arrayList.add(((EnumC2866vc) c1363e0.next()).b);
        }
        C2549rq c2549rq = AbstractC2805uq.f;
        KB.b(c2549rq, "IS_INNER");
        AbstractC2207nq.a(new C2464qq(c2549rq, 1));
        C2549rq c2549rq2 = AbstractC2805uq.g;
        KB.b(c2549rq2, "IS_DATA");
        AbstractC2207nq.a(new C2464qq(c2549rq2, 1));
        C2549rq c2549rq3 = AbstractC2805uq.h;
        KB.b(c2549rq3, "IS_EXTERNAL_CLASS");
        AbstractC2207nq.a(new C2464qq(c2549rq3, 1));
        C2549rq c2549rq4 = AbstractC2805uq.i;
        KB.b(c2549rq4, "IS_EXPECT_CLASS");
        AbstractC2207nq.a(new C2464qq(c2549rq4, 1));
        C2549rq c2549rq5 = AbstractC2805uq.j;
        KB.b(c2549rq5, "IS_VALUE_CLASS");
        AbstractC2207nq.a(new C2464qq(c2549rq5, 1));
        C2549rq c2549rq6 = AbstractC2805uq.k;
        KB.b(c2549rq6, "IS_FUN_INTERFACE");
        AbstractC2207nq.a(new C2464qq(c2549rq6, 1));
        C2549rq c2549rq7 = AbstractC2805uq.l;
        KB.b(c2549rq7, "HAS_ENUM_ENTRIES");
        AbstractC2207nq.a(new C2464qq(c2549rq7, 1));
        AbstractC2207nq.c(new AbstractC2083mQ() { // from class: com.android.tools.r8.internal.c5
            @Override // com.android.tools.r8.internal.AbstractC2083mQ
            public final Object a(Object obj) {
                return Integer.valueOf(((XH) obj).a);
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final String d() {
                return OverlayablePolicy.NAME_flags;
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final InterfaceC2328pE e() {
                return AbstractC2654t40.a(XH.class);
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final String f() {
                return "getFlags()I";
            }

            @Override // com.android.tools.r8.internal.AbstractC2083mQ
            public final void a(Integer num, Object obj) {
                ((XH) obj).a = num.intValue();
            }
        });
        C2549rq c2549rq8 = AbstractC2805uq.m;
        KB.b(c2549rq8, "IS_SECONDARY");
        C2464qq c2464qq = new C2464qq(c2549rq8, 1);
        int i = C1695hq.i;
        if (c2464qq.b != 1 || c2464qq.c != 1) {
            b6c.a(I4.a("BooleanFlagDelegate can work only with boolean flags (bitWidth = 1 and value = 1), but ", c2464qq, " was passed"));
            return;
        }
        C2549rq c2549rq9 = AbstractC2805uq.n;
        KB.b(c2549rq9, "IS_CONSTRUCTOR_WITH_NON_STABLE_PARAMETER_NAMES");
        C2464qq c2464qq2 = new C2464qq(c2549rq9, 1);
        if (c2464qq2.b != 1 || c2464qq2.c != 1) {
            b6c.a(I4.a("BooleanFlagDelegate can work only with boolean flags (bitWidth = 1 and value = 1), but ", c2464qq2, " was passed"));
            return;
        }
        int i2 = S4.i;
        KB.b(AbstractC2805uq.o, "MEMBER_KIND");
        C3059xn c3059xn2 = EnumC2764uN.c;
        ArrayList arrayList2 = new ArrayList(AbstractC2015le.a((Iterable) c3059xn2));
        C1363e0 c1363e1 = new C1363e0(c3059xn2);
        while (c1363e1.hasNext()) {
            arrayList2.add(((EnumC2764uN) c1363e1.next()).b);
        }
        AbstractC2207nq.c(new AbstractC2083mQ() { // from class: com.android.tools.r8.internal.X4
            @Override // com.android.tools.r8.internal.AbstractC2083mQ
            public final Object a(Object obj) {
                return Integer.valueOf(((C1989lI) obj).b);
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final String d() {
                return OverlayablePolicy.NAME_flags;
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final InterfaceC2328pE e() {
                return AbstractC2654t40.a(C1989lI.class);
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final String f() {
                return "getFlags()I";
            }

            @Override // com.android.tools.r8.internal.AbstractC2083mQ
            public final void a(Integer num, Object obj) {
                ((C1989lI) obj).b = num.intValue();
            }
        });
        AbstractC2207nq.b(new AbstractC2083mQ() { // from class: com.android.tools.r8.internal.W4
            @Override // com.android.tools.r8.internal.AbstractC2083mQ
            public final Object a(Object obj) {
                return Integer.valueOf(((C1989lI) obj).b);
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final String d() {
                return OverlayablePolicy.NAME_flags;
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final InterfaceC2328pE e() {
                return AbstractC2654t40.a(C1989lI.class);
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final String f() {
                return "getFlags()I";
            }

            @Override // com.android.tools.r8.internal.AbstractC2083mQ
            public final void a(Integer num, Object obj) {
                ((C1989lI) obj).b = num.intValue();
            }
        });
        C2549rq c2549rq10 = AbstractC2805uq.p;
        KB.b(c2549rq10, "IS_OPERATOR");
        AbstractC2207nq.b(new C2464qq(c2549rq10, 1));
        C2549rq c2549rq11 = AbstractC2805uq.q;
        KB.b(c2549rq11, "IS_INFIX");
        AbstractC2207nq.b(new C2464qq(c2549rq11, 1));
        C2549rq c2549rq12 = AbstractC2805uq.r;
        KB.b(c2549rq12, "IS_INLINE");
        AbstractC2207nq.b(new C2464qq(c2549rq12, 1));
        C2549rq c2549rq13 = AbstractC2805uq.s;
        KB.b(c2549rq13, "IS_TAILREC");
        AbstractC2207nq.b(new C2464qq(c2549rq13, 1));
        C2549rq c2549rq14 = AbstractC2805uq.t;
        KB.b(c2549rq14, "IS_EXTERNAL_FUNCTION");
        AbstractC2207nq.b(new C2464qq(c2549rq14, 1));
        C2549rq c2549rq15 = AbstractC2805uq.u;
        KB.b(c2549rq15, "IS_SUSPEND");
        AbstractC2207nq.b(new C2464qq(c2549rq15, 1));
        C2549rq c2549rq16 = AbstractC2805uq.v;
        KB.b(c2549rq16, "IS_EXPECT_FUNCTION");
        AbstractC2207nq.b(new C2464qq(c2549rq16, 1));
        C2549rq c2549rq17 = AbstractC2805uq.w;
        KB.b(c2549rq17, "IS_FUNCTION_WITH_NON_STABLE_PARAMETER_NAMES");
        AbstractC2207nq.b(new C2464qq(c2549rq17, 1));
        AbstractC2207nq.c(new AbstractC2083mQ() { // from class: com.android.tools.r8.internal.Y4
            @Override // com.android.tools.r8.internal.AbstractC2083mQ
            public final Object a(Object obj) {
                return Integer.valueOf(((C2673tI) obj).b);
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final String d() {
                return OverlayablePolicy.NAME_flags;
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final InterfaceC2328pE e() {
                return AbstractC2654t40.a(C2673tI.class);
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final String f() {
                return "getFlags()I";
            }

            @Override // com.android.tools.r8.internal.AbstractC2083mQ
            public final void a(Integer num, Object obj) {
                ((C2673tI) obj).b = num.intValue();
            }
        });
        AbstractC2207nq.b(new AbstractC2083mQ() { // from class: com.android.tools.r8.internal.T4
            @Override // com.android.tools.r8.internal.AbstractC2083mQ
            public final Object a(Object obj) {
                return Integer.valueOf(((C2673tI) obj).b);
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final String d() {
                return OverlayablePolicy.NAME_flags;
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final InterfaceC2328pE e() {
                return AbstractC2654t40.a(C2673tI.class);
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final String f() {
                return "getFlags()I";
            }

            @Override // com.android.tools.r8.internal.AbstractC2083mQ
            public final void a(Integer num, Object obj) {
                ((C2673tI) obj).b = num.intValue();
            }
        });
        int i3 = R4.i;
        KB.b(AbstractC2805uq.o, "MEMBER_KIND");
        C3059xn c3059xn3 = EnumC2764uN.c;
        ArrayList arrayList3 = new ArrayList(AbstractC2015le.a((Iterable) c3059xn3));
        C1363e0 c1363e2 = new C1363e0(c3059xn3);
        while (c1363e2.hasNext()) {
            arrayList3.add(((EnumC2764uN) c1363e2.next()).b);
        }
        C2549rq c2549rq18 = AbstractC2805uq.x;
        KB.b(c2549rq18, "IS_VAR");
        C2464qq c2464qq3 = new C2464qq(c2549rq18, 1);
        C1950kq c1950kq = C1950kq.i;
        if (c2464qq3.b != 1 || c2464qq3.c != 1) {
            b6c.a(I4.a("BooleanFlagDelegate can work only with boolean flags (bitWidth = 1 and value = 1), but ", c2464qq3, " was passed"));
            return;
        }
        C2549rq c2549rq19 = AbstractC2805uq.y;
        KB.b(c2549rq19, "HAS_GETTER");
        C2464qq c2464qq4 = new C2464qq(c2549rq19, 1);
        if (c2464qq4.b != 1 || c2464qq4.c != 1) {
            b6c.a(I4.a("BooleanFlagDelegate can work only with boolean flags (bitWidth = 1 and value = 1), but ", c2464qq4, " was passed"));
            return;
        }
        C2549rq c2549rq20 = AbstractC2805uq.z;
        KB.b(c2549rq20, "HAS_SETTER");
        C2464qq c2464qq5 = new C2464qq(c2549rq20, 1);
        if (c2464qq5.b != 1 || c2464qq5.c != 1) {
            b6c.a(I4.a("BooleanFlagDelegate can work only with boolean flags (bitWidth = 1 and value = 1), but ", c2464qq5, " was passed"));
            return;
        }
        C2549rq c2549rq21 = AbstractC2805uq.A;
        KB.b(c2549rq21, "IS_CONST");
        C2464qq c2464qq6 = new C2464qq(c2549rq21, 1);
        if (c2464qq6.b != 1 || c2464qq6.c != 1) {
            b6c.a(I4.a("BooleanFlagDelegate can work only with boolean flags (bitWidth = 1 and value = 1), but ", c2464qq6, " was passed"));
            return;
        }
        C2549rq c2549rq22 = AbstractC2805uq.B;
        KB.b(c2549rq22, "IS_LATEINIT");
        C2464qq c2464qq7 = new C2464qq(c2549rq22, 1);
        if (c2464qq7.b != 1 || c2464qq7.c != 1) {
            b6c.a(I4.a("BooleanFlagDelegate can work only with boolean flags (bitWidth = 1 and value = 1), but ", c2464qq7, " was passed"));
            return;
        }
        C2549rq c2549rq23 = AbstractC2805uq.C;
        KB.b(c2549rq23, "HAS_CONSTANT");
        C2464qq c2464qq8 = new C2464qq(c2549rq23, 1);
        if (c2464qq8.b != 1 || c2464qq8.c != 1) {
            b6c.a(I4.a("BooleanFlagDelegate can work only with boolean flags (bitWidth = 1 and value = 1), but ", c2464qq8, " was passed"));
            return;
        }
        C2549rq c2549rq24 = AbstractC2805uq.D;
        KB.b(c2549rq24, "IS_EXTERNAL_PROPERTY");
        C2464qq c2464qq9 = new C2464qq(c2549rq24, 1);
        if (c2464qq9.b != 1 || c2464qq9.c != 1) {
            b6c.a(I4.a("BooleanFlagDelegate can work only with boolean flags (bitWidth = 1 and value = 1), but ", c2464qq9, " was passed"));
            return;
        }
        C2549rq c2549rq25 = AbstractC2805uq.E;
        KB.b(c2549rq25, "IS_DELEGATED");
        C2464qq c2464qq10 = new C2464qq(c2549rq25, 1);
        if (c2464qq10.b != 1 || c2464qq10.c != 1) {
            b6c.a(I4.a("BooleanFlagDelegate can work only with boolean flags (bitWidth = 1 and value = 1), but ", c2464qq10, " was passed"));
            return;
        }
        C2549rq c2549rq26 = AbstractC2805uq.F;
        KB.b(c2549rq26, "IS_EXPECT_PROPERTY");
        C2464qq c2464qq11 = new C2464qq(c2549rq26, 1);
        if (c2464qq11.b != 1 || c2464qq11.c != 1) {
            b6c.a(I4.a("BooleanFlagDelegate can work only with boolean flags (bitWidth = 1 and value = 1), but ", c2464qq11, " was passed"));
            return;
        }
        AbstractC2207nq.c(new AbstractC2083mQ() { // from class: com.android.tools.r8.internal.Z4
            @Override // com.android.tools.r8.internal.AbstractC2083mQ
            public final Object a(Object obj) {
                return Integer.valueOf(((C2759uI) obj).a);
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final String d() {
                return OverlayablePolicy.NAME_flags;
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final InterfaceC2328pE e() {
                return AbstractC2654t40.a(C2759uI.class);
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final String f() {
                return "getFlags$kotlinx_metadata()I";
            }

            @Override // com.android.tools.r8.internal.AbstractC2083mQ
            public final void a(Integer num, Object obj) {
                ((C2759uI) obj).a = num.intValue();
            }
        });
        AbstractC2207nq.b(new AbstractC2083mQ() { // from class: com.android.tools.r8.internal.U4
            @Override // com.android.tools.r8.internal.AbstractC2083mQ
            public final Object a(Object obj) {
                return Integer.valueOf(((C2759uI) obj).a);
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final String d() {
                return OverlayablePolicy.NAME_flags;
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final InterfaceC2328pE e() {
                return AbstractC2654t40.a(C2759uI.class);
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final String f() {
                return "getFlags$kotlinx_metadata()I";
            }

            @Override // com.android.tools.r8.internal.AbstractC2083mQ
            public final void a(Integer num, Object obj) {
                ((C2759uI) obj).a = num.intValue();
            }
        });
        C2549rq c2549rq27 = AbstractC2805uq.J;
        KB.b(c2549rq27, "IS_NOT_DEFAULT");
        C2464qq c2464qq12 = new C2464qq(c2549rq27, 1);
        int i4 = C1865jq.i;
        if (c2464qq12.b != 1 || c2464qq12.c != 1) {
            b6c.a(I4.a("BooleanFlagDelegate can work only with boolean flags (bitWidth = 1 and value = 1), but ", c2464qq12, " was passed"));
            return;
        }
        C2549rq c2549rq28 = AbstractC2805uq.K;
        KB.b(c2549rq28, "IS_EXTERNAL_ACCESSOR");
        C2464qq c2464qq13 = new C2464qq(c2549rq28, 1);
        if (c2464qq13.b != 1 || c2464qq13.c != 1) {
            b6c.a(I4.a("BooleanFlagDelegate can work only with boolean flags (bitWidth = 1 and value = 1), but ", c2464qq13, " was passed"));
            return;
        }
        C2549rq c2549rq29 = AbstractC2805uq.L;
        KB.b(c2549rq29, "IS_INLINE_ACCESSOR");
        C2464qq c2464qq14 = new C2464qq(c2549rq29, 1);
        if (c2464qq14.b != 1 || c2464qq14.c != 1) {
            b6c.a(I4.a("BooleanFlagDelegate can work only with boolean flags (bitWidth = 1 and value = 1), but ", c2464qq14, " was passed"));
            return;
        }
        b = new I6(new AbstractC2083mQ() { // from class: com.android.tools.r8.internal.lq
            @Override // com.android.tools.r8.internal.AbstractC2083mQ
            public final Object a(Object obj) {
                return Integer.valueOf(((C3100yI) obj).e());
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final String d() {
                return OverlayablePolicy.NAME_flags;
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final InterfaceC2328pE e() {
                return AbstractC2654t40.a(C3100yI.class);
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final String f() {
                return "getFlags()I";
            }

            @Override // com.android.tools.r8.internal.AbstractC2083mQ
            public final void a(Integer num, Object obj) {
                ((C3100yI) obj).b = num.intValue();
            }
        }, new C2464qq(0, 1));
        C2464qq c2464qq15 = new C2464qq(1, 1);
        if (c2464qq15.b != 1 || c2464qq15.c != 1) {
            b6c.a(I4.a("BooleanFlagDelegate can work only with boolean flags (bitWidth = 1 and value = 1), but ", c2464qq15, " was passed"));
            return;
        }
        C2549rq c2549rq30 = AbstractC2805uq.a;
        C2464qq c2464qq16 = new C2464qq(c2549rq30.a + 1, c2549rq30.b);
        if (c2464qq16.b != 1 || c2464qq16.c != 1) {
            b6c.a(I4.a("BooleanFlagDelegate can work only with boolean flags (bitWidth = 1 and value = 1), but ", c2464qq16, " was passed"));
            return;
        }
        c = new I6(new AbstractC2083mQ() { // from class: com.android.tools.r8.internal.Q4
            @Override // com.android.tools.r8.internal.AbstractC2083mQ
            public final Object a(Object obj) {
                return Integer.valueOf(((DI) obj).a());
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final String d() {
                return OverlayablePolicy.NAME_flags;
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final InterfaceC2328pE e() {
                return AbstractC2654t40.a(DI.class);
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final String f() {
                return "getFlags()I";
            }

            @Override // com.android.tools.r8.internal.AbstractC2083mQ
            public final void a(Integer num, Object obj) {
                ((DI) obj).b = num.intValue();
            }
        }, new C2464qq(0, 1));
        AbstractC2207nq.c(new AbstractC2083mQ() { // from class: com.android.tools.r8.internal.a5
            @Override // com.android.tools.r8.internal.AbstractC2083mQ
            public final Object a(Object obj) {
                return Integer.valueOf(((C3185zI) obj).a);
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final String d() {
                return OverlayablePolicy.NAME_flags;
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final InterfaceC2328pE e() {
                return AbstractC2654t40.a(C3185zI.class);
            }

            @Override // com.android.tools.r8.internal.AbstractC2063m8
            public final String f() {
                return "getFlags()I";
            }

            @Override // com.android.tools.r8.internal.AbstractC2083mQ
            public final void a(Integer num, Object obj) {
                ((C3185zI) obj).a = num.intValue();
            }
        });
        C2549rq c2549rq31 = AbstractC2805uq.G;
        KB.b(c2549rq31, "DECLARES_DEFAULT_VALUE");
        C2464qq c2464qq17 = new C2464qq(c2549rq31, 1);
        int i5 = C2121mq.i;
        if (c2464qq17.b != 1 || c2464qq17.c != 1) {
            b6c.a(I4.a("BooleanFlagDelegate can work only with boolean flags (bitWidth = 1 and value = 1), but ", c2464qq17, " was passed"));
            return;
        }
        C2549rq c2549rq32 = AbstractC2805uq.H;
        KB.b(c2549rq32, "IS_CROSSINLINE");
        C2464qq c2464qq18 = new C2464qq(c2549rq32, 1);
        if (c2464qq18.b != 1 || c2464qq18.c != 1) {
            b6c.a(I4.a("BooleanFlagDelegate can work only with boolean flags (bitWidth = 1 and value = 1), but ", c2464qq18, " was passed"));
            return;
        }
        C2549rq c2549rq33 = AbstractC2805uq.I;
        KB.b(c2549rq33, "IS_NOINLINE");
        C2464qq c2464qq19 = new C2464qq(c2549rq33, 1);
        if (c2464qq19.b != 1 || c2464qq19.c != 1) {
            b6c.a(I4.a("BooleanFlagDelegate can work only with boolean flags (bitWidth = 1 and value = 1), but ", c2464qq19, " was passed"));
            return;
        }
        C2549rq c2549rq34 = AbstractC2805uq.M;
        KB.b(c2549rq34, "IS_NEGATED");
        C2464qq c2464qq20 = new C2464qq(c2549rq34, 1);
        if (c2464qq20.b != 1 || c2464qq20.c != 1) {
            b6c.a(I4.a("BooleanFlagDelegate can work only with boolean flags (bitWidth = 1 and value = 1), but ", c2464qq20, " was passed"));
            return;
        }
        C2549rq c2549rq35 = AbstractC2805uq.N;
        KB.b(c2549rq35, "IS_NULL_CHECK_PREDICATE");
        C2464qq c2464qq21 = new C2464qq(c2549rq35, 1);
        if (c2464qq21.b == 1 && c2464qq21.c == 1) {
            return;
        }
        b6c.a(I4.a("BooleanFlagDelegate can work only with boolean flags (bitWidth = 1 and value = 1), but ", c2464qq21, " was passed"));
    }
}
