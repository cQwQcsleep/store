package org.jetbrains.kotlin.backend.konan.cgen;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/backend/konan/cgen/FunctionCType;", "Lorg/jetbrains/kotlin/backend/konan/cgen/CType;", "returnType", "parameterTypes", "", "variadic", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/backend/konan/cgen/CType;Ljava/util/List;Z)V", "render", "", "name", "org.jetbrains.kotlin:ir.backend.native"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class FunctionCType implements CType {
    private final List<CType> parameterTypes;
    private final CType returnType;
    private final boolean variadic;

    /* JADX WARN: Multi-variable type inference failed */
    public FunctionCType(CType cType, List<? extends CType> list, boolean z) {
        cType.getClass();
        list.getClass();
        this.returnType = cType;
        this.parameterTypes = list;
        this.variadic = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence render$lambda$0$0(CType cType) {
        cType.getClass();
        return cType.render("");
    }

    @Override // org.jetbrains.kotlin.backend.konan.cgen.CType
    public String render(String name) {
        name.getClass();
        CType cType = this.returnType;
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(name);
        sb.append(")(");
        CollectionsKt.joinTo$default(this.parameterTypes, sb, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: org.jetbrains.kotlin.backend.konan.cgen.a
            public final Object invoke(Object obj) {
                return FunctionCType.render$lambda$0$0((CType) obj);
            }
        }, 62, (Object) null);
        boolean zIsEmpty = this.parameterTypes.isEmpty();
        boolean z = this.variadic;
        if (zIsEmpty) {
            if (!z) {
                sb.append("void");
            }
        } else if (z) {
            sb.append(", ...");
        }
        sb.append(Util.C_PARAM_END);
        return cType.render(sb.toString());
    }
}
