package org.jetbrains.kotlin.fir.serialization.constant;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.constant.KClassValue;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000¨\u0006\u0006"}, d2 = {CoroutineCodegenUtilKt.SUSPEND_FUNCTION_CREATE_METHOD_NAME, "Lorg/jetbrains/kotlin/constant/KClassValue;", "argumentType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "org.jetbrains.kotlin:fir-serialization"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConstantValueUtilsKt {
    /* JADX WARN: Multi-variable type inference failed */
    public static final KClassValue create(ConeKotlinType coneKotlinType, FirSession firSession) {
        ConeKotlinType coneKotlinTypeArrayElementType$default;
        coneKotlinType.getClass();
        firSession.getClass();
        if ((coneKotlinType instanceof ConeErrorType) || !(coneKotlinType instanceof ConeClassLikeType)) {
            return null;
        }
        ConeKotlinType coneKotlinTypeFullyExpandedType$default = TypeExpansionUtilsKt.fullyExpandedType$default((ConeClassLikeType) coneKotlinType, firSession, (Function1) null, 2, (Object) null);
        int i = 0;
        while (!ConeBuiltinTypeUtilsKt.isPrimitiveArray(coneKotlinTypeFullyExpandedType$default) && (coneKotlinTypeArrayElementType$default = FirTypeUtilsKt.arrayElementType$default(coneKotlinTypeFullyExpandedType$default, false, 1, null)) != null) {
            i++;
            coneKotlinTypeFullyExpandedType$default = coneKotlinTypeArrayElementType$default;
        }
        FirClassLikeSymbol<?> classLikeSymbol = ToSymbolUtilsKt.toClassLikeSymbol(coneKotlinTypeFullyExpandedType$default, firSession);
        if (classLikeSymbol == null) {
            return null;
        }
        return ((FirClassLikeDeclaration) classLikeSymbol.getFir()).getIsLocal() ? new KClassValue(new KClassValue.Value.LocalClass(classLikeSymbol)) : new KClassValue(classLikeSymbol.getClassId(), i);
    }
}
