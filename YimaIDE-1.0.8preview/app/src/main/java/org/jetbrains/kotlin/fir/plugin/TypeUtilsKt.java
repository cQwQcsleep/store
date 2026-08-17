package org.jetbrains.kotlin.fir.plugin;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeAttributes;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.fir.types.impl.ConeClassLikeTypeImpl;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a1\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"createConeType", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "Lorg/jetbrains/kotlin/name/ClassId;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "typeArguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "nullable", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/name/ClassId;Lorg/jetbrains/kotlin/fir/FirSession;[Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;Z)Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "org.jetbrains.kotlin:plugin-utils"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TypeUtilsKt {
    public static final ConeClassLikeType createConeType(ClassId classId, FirSession firSession, ConeTypeProjection[] coneTypeProjectionArr, boolean z) {
        classId.getClass();
        firSession.getClass();
        coneTypeProjectionArr.getClass();
        FirClassLikeSymbol<?> classLikeSymbolByClassId = FirSymbolProviderKt.getSymbolProvider(firSession).getClassLikeSymbolByClassId(classId);
        FirClassSymbol firClassSymbol = classLikeSymbolByClassId instanceof FirClassSymbol ? (FirClassSymbol) classLikeSymbolByClassId : null;
        if (firClassSymbol != null) {
            return coneTypeProjectionArr.length == 0 ? TypeConstructionUtilsKt.constructStarProjectedType$default(firClassSymbol, 0, z, 1, null) : TypeConstructionUtilsKt.constructType$default((FirClassLikeSymbol) firClassSymbol, coneTypeProjectionArr, z, (ConeAttributes) null, 4, (Object) null);
        }
        return new ConeClassLikeTypeImpl(TypeConstructionUtilsKt.toLookupTag(classId), coneTypeProjectionArr, z, null, 8, null);
    }

    public static /* synthetic */ ConeClassLikeType createConeType$default(ClassId classId, FirSession firSession, ConeTypeProjection[] coneTypeProjectionArr, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            coneTypeProjectionArr = new ConeTypeProjection[0];
        }
        if ((i & 4) != 0) {
            z = false;
        }
        return createConeType(classId, firSession, coneTypeProjectionArr, z);
    }
}
