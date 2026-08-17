package org.jetbrains.kotlin.fir.resolve;

import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.builtins.PrimitiveType;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.resolve.ArrayFqNames;
import org.jetbrains.kotlin.resolve.CollectionNames$Factories;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007\u001a$\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\r"}, d2 = {"toArrayOfFactoryName", "Lorg/jetbrains/kotlin/name/Name;", "expectedType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "eagerlyReturnNonPrimitive", Argument.Delimiters.none, "toCollectionOfFactoryPackageAndName", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/name/FqName;", "expectedClass", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class StdlibFactoryFunctionsUtilsKt {
    public static final Name toArrayOfFactoryName(ConeKotlinType coneKotlinType, FirSession firSession, boolean z) {
        coneKotlinType.getClass();
        firSession.getClass();
        ConeKotlinType coneKotlinTypeFullyExpandedType$default = TypeExpansionUtilsKt.fullyExpandedType$default(coneKotlinType, firSession, (Function1) null, 2, (Object) null);
        if (ConeBuiltinTypeUtilsKt.isPrimitiveArray(coneKotlinTypeFullyExpandedType$default)) {
            ConeKotlinType coneKotlinTypeArrayElementType$default = FirTypeUtilsKt.arrayElementType$default(coneKotlinTypeFullyExpandedType$default, false, 1, null);
            coneKotlinTypeArrayElementType$default.getClass();
            ClassId classId = ConeTypeUtilsKt.getClassId(coneKotlinTypeArrayElementType$default);
            PrimitiveType.Companion companion = PrimitiveType.Companion;
            classId.getClass();
            String strAsString = classId.getShortClassName().asString();
            strAsString.getClass();
            Object obj = ArrayFqNames.INSTANCE.getPRIMITIVE_TYPE_TO_ARRAY().get(companion.getByShortName(strAsString));
            obj.getClass();
            return (Name) obj;
        }
        if (!ConeBuiltinTypeUtilsKt.isUnsignedArray(coneKotlinTypeFullyExpandedType$default)) {
            if (ConeBuiltinTypeUtilsKt.isNonPrimitiveArray(coneKotlinTypeFullyExpandedType$default) || z) {
                return ArrayFqNames.INSTANCE.getARRAY_OF_FUNCTION();
            }
            return null;
        }
        ConeKotlinType coneKotlinTypeArrayElementType$default2 = FirTypeUtilsKt.arrayElementType$default(coneKotlinTypeFullyExpandedType$default, false, 1, null);
        coneKotlinTypeArrayElementType$default2.getClass();
        ClassId classId2 = ConeTypeUtilsKt.getClassId(coneKotlinTypeArrayElementType$default2);
        Map unsigned_type_to_array = ArrayFqNames.INSTANCE.getUNSIGNED_TYPE_TO_ARRAY();
        classId2.getClass();
        Object obj2 = unsigned_type_to_array.get(classId2.asSingleFqName());
        obj2.getClass();
        return (Name) obj2;
    }

    public static final Pair<FqName, Name> toCollectionOfFactoryPackageAndName(FirRegularClassSymbol firRegularClassSymbol, FirSession firSession) {
        firRegularClassSymbol.getClass();
        firSession.getClass();
        Name arrayOfFactoryName = toArrayOfFactoryName(ScopeUtilsKt.defaultType(firRegularClassSymbol), firSession, false);
        if (arrayOfFactoryName != null) {
            return TuplesKt.to(StandardNames.BUILT_INS_PACKAGE_FQ_NAME, arrayOfFactoryName);
        }
        ClassId classId = firRegularClassSymbol.getClassId();
        StandardClassIds standardClassIds = StandardClassIds.INSTANCE;
        if (Intrinsics.areEqual(classId, standardClassIds.getList())) {
            return TuplesKt.to(StandardNames.COLLECTIONS_PACKAGE_FQ_NAME, CollectionNames$Factories.INSTANCE.getLIST_OF());
        }
        if (Intrinsics.areEqual(classId, standardClassIds.getMutableList())) {
            return TuplesKt.to(StandardNames.COLLECTIONS_PACKAGE_FQ_NAME, CollectionNames$Factories.INSTANCE.getMUTABLE_LIST_OF());
        }
        if (Intrinsics.areEqual(classId, standardClassIds.getSet())) {
            return TuplesKt.to(StandardNames.COLLECTIONS_PACKAGE_FQ_NAME, CollectionNames$Factories.INSTANCE.getSET_OF());
        }
        if (Intrinsics.areEqual(classId, standardClassIds.getMutableSet())) {
            return TuplesKt.to(StandardNames.COLLECTIONS_PACKAGE_FQ_NAME, CollectionNames$Factories.INSTANCE.getMUTABLE_SET_OF());
        }
        if (Intrinsics.areEqual(classId, standardClassIds.getSequence())) {
            return TuplesKt.to(StandardNames.SEQUENCES_PACKAGE_FQ_NAME, CollectionNames$Factories.INSTANCE.getSEQUENCE_OF());
        }
        return null;
    }
}
