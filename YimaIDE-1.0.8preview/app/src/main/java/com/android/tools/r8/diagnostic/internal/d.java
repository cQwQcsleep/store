package com.android.tools.r8.diagnostic.internal;

import com.android.tools.r8.diagnostic.DefinitionClassContext;
import com.android.tools.r8.diagnostic.DefinitionContext;
import com.android.tools.r8.diagnostic.DefinitionFieldContext;
import com.android.tools.r8.diagnostic.DefinitionMethodContext;
import com.android.tools.r8.graph.InterfaceC0265o0;
import com.android.tools.r8.graph.InterfaceC0339y5;
import com.android.tools.r8.internal.C0416Cp;
import com.android.tools.r8.internal.MO;
import defpackage.hkh;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class d {
    public static final /* synthetic */ boolean a = true;

    public static DefinitionContext a(InterfaceC0339y5 interfaceC0339y5) {
        b bVarA;
        InterfaceC0265o0 context = interfaceC0339y5.getContext();
        if (context.isClass()) {
            bVarA = a.a().a(context.asClass().P0());
        } else if (context.f0()) {
            bVarA = e.a().a(context.d().getReference().z0());
        } else {
            if (!context.d0()) {
                hkh.a();
                return null;
            }
            bVarA = f.a().a(context.c().A());
        }
        return bVarA.a(context.getOrigin()).a();
    }

    public static void a(DefinitionContext definitionContext, Consumer<DefinitionClassContext> consumer, Consumer<DefinitionFieldContext> consumer2, Consumer<DefinitionMethodContext> consumer3) {
        if (definitionContext.isClassContext()) {
            consumer.accept(definitionContext.asClassContext());
            return;
        }
        if (definitionContext.isFieldContext()) {
            consumer2.accept(definitionContext.asFieldContext());
        } else if (a || definitionContext.isMethodContext()) {
            consumer3.accept(definitionContext.asMethodContext());
        } else {
            x1f.a();
        }
    }

    public static String a(DefinitionContext definitionContext) {
        Object objApply;
        Function function = new Function() { // from class: glg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((DefinitionClassContext) obj).getClassReference().getTypeName();
            }
        };
        Function function2 = new Function() { // from class: hlg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C0416Cp.a(((DefinitionFieldContext) obj).getFieldReference());
            }
        };
        Function function3 = new Function() { // from class: ilg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return MO.b(((DefinitionMethodContext) obj).getMethodReference());
            }
        };
        if (definitionContext.isClassContext()) {
            objApply = function.apply(definitionContext.asClassContext());
        } else if (definitionContext.isFieldContext()) {
            objApply = function2.apply(definitionContext.asFieldContext());
        } else {
            if (!a && !definitionContext.isMethodContext()) {
                x1f.a();
                return null;
            }
            objApply = function3.apply(definitionContext.asMethodContext());
        }
        return (String) objApply;
    }
}
