package org.jetbrains.kotlin.fir.resolve;

import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.StandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0004\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0005\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0006\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0007\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\b\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\t\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\n\u001a\u00020\u0001*\u00020\u0002\u001a\u0014\u0010\u000b\u001a\u0004\u0018\u00010\f*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f\u001a\u0016\u0010\u0010\u001a\u00020\u0001*\u00020\u00112\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u0013¨\u0006\u0014"}, d2 = {"isInvoke", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/CallableId;", "isFunctionOrSuspendFunctionInvoke", "isSuspendFunctionInvoke", "isKSuspendFunctionInvoke", "isFunctionInvoke", "isKFunctionInvoke", "isIteratorNext", "isIteratorHasNext", "isIterator", "fqName", "Lorg/jetbrains/kotlin/name/FqName;", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "isRealOwnerOf", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "declarationSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "org.jetbrains.kotlin:semantics"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CallableIdUtilsKt {
    public static final FqName fqName(FirAnnotation firAnnotation, FirSession firSession) {
        FirRegularClassSymbol regularClassSymbol;
        firAnnotation.getClass();
        firSession.getClass();
        FirResolvedTypeRef annotationTypeRef = firAnnotation.getAnnotationTypeRef();
        FirResolvedTypeRef firResolvedTypeRef = annotationTypeRef instanceof FirResolvedTypeRef ? annotationTypeRef : null;
        ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
        if (!(coneType instanceof ConeSimpleKotlinType)) {
            coneType = null;
        }
        ConeSimpleKotlinType coneSimpleKotlinType = (ConeSimpleKotlinType) coneType;
        if (coneSimpleKotlinType == null || (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(coneSimpleKotlinType, firSession)) == null) {
            return null;
        }
        return regularClassSymbol.getClassId().asSingleFqName();
    }

    public static final boolean isFunctionInvoke(CallableId callableId) {
        FqName className;
        String strAsString;
        callableId.getClass();
        return Intrinsics.areEqual(callableId.getCallableName().asString(), "invoke") && (className = callableId.getClassName()) != null && (strAsString = className.asString()) != null && StringsKt.startsWith$default(strAsString, "Function", false, 2, (Object) null) && Intrinsics.areEqual(callableId.getPackageName(), StandardClassIds.INSTANCE.getBASE_KOTLIN_PACKAGE());
    }

    public static final boolean isFunctionOrSuspendFunctionInvoke(CallableId callableId) {
        callableId.getClass();
        return isFunctionInvoke(callableId) || isSuspendFunctionInvoke(callableId);
    }

    public static final boolean isInvoke(CallableId callableId) {
        callableId.getClass();
        return isFunctionInvoke(callableId) || isKFunctionInvoke(callableId);
    }

    public static final boolean isIterator(CallableId callableId) {
        callableId.getClass();
        return Intrinsics.areEqual(callableId.getCallableName().asString(), "iterator") && ArraysKt.contains(new String[]{"kotlin", "kotlin.collections", "kotlin.ranges"}, callableId.getPackageName().asString());
    }

    public static final boolean isIteratorHasNext(CallableId callableId) {
        FqName className;
        String strAsString;
        callableId.getClass();
        return Intrinsics.areEqual(callableId.getCallableName().asString(), "hasNext") && (className = callableId.getClassName()) != null && (strAsString = className.asString()) != null && StringsKt.endsWith$default(strAsString, "Iterator", false, 2, (Object) null) && Intrinsics.areEqual(callableId.getPackageName(), StandardClassIds.INSTANCE.getBASE_COLLECTIONS_PACKAGE());
    }

    public static final boolean isIteratorNext(CallableId callableId) {
        FqName className;
        String strAsString;
        callableId.getClass();
        return Intrinsics.areEqual(callableId.getCallableName().asString(), "next") && (className = callableId.getClassName()) != null && (strAsString = className.asString()) != null && StringsKt.endsWith$default(strAsString, "Iterator", false, 2, (Object) null) && Intrinsics.areEqual(callableId.getPackageName(), StandardClassIds.INSTANCE.getBASE_COLLECTIONS_PACKAGE());
    }

    public static final boolean isKFunctionInvoke(CallableId callableId) {
        FqName className;
        String strAsString;
        callableId.getClass();
        return Intrinsics.areEqual(callableId.getCallableName().asString(), "invoke") && (className = callableId.getClassName()) != null && (strAsString = className.asString()) != null && StringsKt.startsWith$default(strAsString, "KFunction", false, 2, (Object) null) && Intrinsics.areEqual(callableId.getPackageName(), StandardClassIds.INSTANCE.getBASE_REFLECT_PACKAGE());
    }

    public static final boolean isKSuspendFunctionInvoke(CallableId callableId) {
        FqName className;
        String strAsString;
        callableId.getClass();
        return Intrinsics.areEqual(callableId.getCallableName().asString(), "invoke") && (className = callableId.getClassName()) != null && (strAsString = className.asString()) != null && StringsKt.startsWith$default(strAsString, "KSuspendFunction", false, 2, (Object) null) && Intrinsics.areEqual(callableId.getPackageName(), StandardClassIds.INSTANCE.getBASE_REFLECT_PACKAGE());
    }

    public static final boolean isRealOwnerOf(ConeClassLikeLookupTag coneClassLikeLookupTag, FirCallableSymbol<?> firCallableSymbol) {
        coneClassLikeLookupTag.getClass();
        firCallableSymbol.getClass();
        return Intrinsics.areEqual(coneClassLikeLookupTag, ClassMembersKt.dispatchReceiverClassLookupTagOrNull(firCallableSymbol));
    }

    public static final boolean isSuspendFunctionInvoke(CallableId callableId) {
        FqName className;
        String strAsString;
        callableId.getClass();
        return Intrinsics.areEqual(callableId.getCallableName().asString(), "invoke") && (className = callableId.getClassName()) != null && (strAsString = className.asString()) != null && StringsKt.startsWith$default(strAsString, "SuspendFunction", false, 2, (Object) null) && Intrinsics.areEqual(callableId.getPackageName(), StandardClassIds.INSTANCE.getBASE_COROUTINES_PACKAGE());
    }
}
