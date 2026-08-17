package org.jetbrains.kotlin.builtins.functions;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003\"\u0015\u0010\u0004\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0003\"\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0003¨\u0006\u0006"}, d2 = {"isBuiltin", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/builtins/functions/FunctionTypeKind;", "(Lorg/jetbrains/kotlin/builtins/functions/FunctionTypeKind;)Z", "isSuspendOrKSuspendFunction", "isBasicFunctionOrKFunction", "org.jetbrains.kotlin:compiler.common"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FunctionTypeKindKt {
    public static final boolean isBasicFunctionOrKFunction(FunctionTypeKind functionTypeKind) {
        functionTypeKind.getClass();
        return Intrinsics.areEqual(functionTypeKind.nonReflectKind(), FunctionTypeKind.Function.INSTANCE);
    }

    public static final boolean isBuiltin(FunctionTypeKind functionTypeKind) {
        functionTypeKind.getClass();
        return Intrinsics.areEqual(functionTypeKind, FunctionTypeKind.Function.INSTANCE) || Intrinsics.areEqual(functionTypeKind, FunctionTypeKind.SuspendFunction.INSTANCE) || Intrinsics.areEqual(functionTypeKind, FunctionTypeKind.KFunction.INSTANCE) || Intrinsics.areEqual(functionTypeKind, FunctionTypeKind.KSuspendFunction.INSTANCE);
    }

    public static final boolean isSuspendOrKSuspendFunction(FunctionTypeKind functionTypeKind) {
        functionTypeKind.getClass();
        return Intrinsics.areEqual(functionTypeKind.nonReflectKind(), FunctionTypeKind.SuspendFunction.INSTANCE);
    }
}
