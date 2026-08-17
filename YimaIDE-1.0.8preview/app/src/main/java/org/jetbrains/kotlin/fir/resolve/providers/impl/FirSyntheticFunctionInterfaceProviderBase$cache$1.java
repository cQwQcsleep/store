package org.jetbrains.kotlin.fir.resolve.providers.impl;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKind;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class FirSyntheticFunctionInterfaceProviderBase$cache$1 extends FunctionReferenceImpl implements Function2<ClassId, FunctionTypeKind, FirRegularClassSymbol> {
    public FirSyntheticFunctionInterfaceProviderBase$cache$1(Object obj) {
        super(2, obj, FirSyntheticFunctionInterfaceProviderBase.class, "createSyntheticFunctionInterface", "createSyntheticFunctionInterface(Lorg/jetbrains/kotlin/name/ClassId;Lorg/jetbrains/kotlin/builtins/functions/FunctionTypeKind;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", 0);
    }

    public final FirRegularClassSymbol invoke(ClassId classId, FunctionTypeKind functionTypeKind) {
        classId.getClass();
        functionTypeKind.getClass();
        return ((FirSyntheticFunctionInterfaceProviderBase) ((CallableReference) this).receiver).createSyntheticFunctionInterface(classId, functionTypeKind);
    }
}
