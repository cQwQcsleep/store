package org.jetbrains.kotlin.fir;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.utils.FirStatusUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.types.Variance;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a>\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0019\u0010\u0003\u001a\u0015\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004¢\u0006\u0002\b\u00062\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u0004¢\u0006\u0002\b\u0006\u001a\f\u0010\b\u001a\u00020\u0001*\u00020\tH\u0002¨\u0006\n"}, d2 = {"isMaybeMainFunction", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "getPlatformName", "Lkotlin/Function1;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "isPlatformStatic", "isValidMainFunctionParameter", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "org.jetbrains.kotlin:semantics"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class MainFunctionDetectionKt {
    /* JADX WARN: Code duplicated, block: B:42:0x00c5  */
    public static final boolean isMaybeMainFunction(FirNamedFunction firNamedFunction, Function1<? super FirNamedFunction, String> function1, Function1<? super FirNamedFunction, Boolean> function2) {
        boolean zIsValidMainFunctionParameter;
        FirTypeRef typeRef;
        firNamedFunction.getClass();
        function1.getClass();
        function2.getClass();
        if (Intrinsics.areEqual(firNamedFunction.getStatus().getVisibility(), Visibilities.Local.INSTANCE) || !firNamedFunction.getTypeParameters().isEmpty() || !ConeBuiltinTypeUtilsKt.isUnit(FirTypeUtilsKt.getConeType(firNamedFunction.getReturnTypeRef()))) {
            return false;
        }
        boolean z = ClassMembersKt.containingClassLookupTag(firNamedFunction) == null;
        String strAsString = (String) function1.invoke(firNamedFunction);
        if (strAsString == null) {
            strAsString = firNamedFunction.getName().asString();
            strAsString.getClass();
        }
        if (!Intrinsics.areEqual(strAsString, "main")) {
            return false;
        }
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        List list = listCreateListBuilder;
        Iterator<T> it = firNamedFunction.getValueParameters().iterator();
        while (it.hasNext()) {
            list.add(((FirValueParameter) it.next()).getReturnTypeRef());
        }
        FirReceiverParameter receiverParameter = firNamedFunction.getReceiverParameter();
        if (receiverParameter != null) {
            if (FirStatusUtilsKt.isCompanionExtension(firNamedFunction)) {
                receiverParameter = null;
            }
            if (receiverParameter != null && (typeRef = receiverParameter.getTypeRef()) != null) {
                listCreateListBuilder.add(typeRef);
            }
        }
        Iterator<T> it2 = firNamedFunction.getContextParameters().iterator();
        while (it2.hasNext()) {
            list.add(((FirValueParameter) it2.next()).getReturnTypeRef());
        }
        List listBuild = CollectionsKt.build(listCreateListBuilder);
        int size = listBuild.size();
        if (size != 0) {
            if (size != 1) {
                zIsValidMainFunctionParameter = false;
            } else {
                zIsValidMainFunctionParameter = isValidMainFunctionParameter(FirTypeUtilsKt.getConeType((FirTypeRef) CollectionsKt.single(listBuild)));
            }
        } else if (z && Intrinsics.areEqual(firNamedFunction.getName().asString(), "main")) {
            zIsValidMainFunctionParameter = true;
        } else {
            zIsValidMainFunctionParameter = false;
        }
        return zIsValidMainFunctionParameter && (z || ((Boolean) function2.invoke(firNamedFunction)).booleanValue());
    }

    private static final boolean isValidMainFunctionParameter(ConeKotlinType coneKotlinType) {
        ConeKotlinType coneKotlinTypeArrayElementType$default;
        return ConeBuiltinTypeUtilsKt.isArrayType(coneKotlinType) && (coneKotlinTypeArrayElementType$default = FirTypeUtilsKt.arrayElementType$default(coneKotlinType, false, 1, null)) != null && ConeBuiltinTypeUtilsKt.isString(coneKotlinTypeArrayElementType$default) && ConeTypeProjectionKt.getVariance(coneKotlinType) != Variance.IN_VARIANCE;
    }
}
