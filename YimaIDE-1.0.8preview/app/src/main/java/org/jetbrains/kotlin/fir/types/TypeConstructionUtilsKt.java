package org.jetbrains.kotlin.fir.types;

import defpackage.f2f;
import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.symbols.ConeTypeParameterLookupTag;
import org.jetbrains.kotlin.fir.symbols.impl.ConeClassLikeLookupTagImpl;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.impl.ConeClassLikeTypeImpl;
import org.jetbrains.kotlin.fir.types.impl.ConeTypeParameterTypeImpl;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000V\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u001a5\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0010\b\u0002\u0010\u0003\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\n\u001a5\u0010\u000b\u001a\u00020\f*\u00020\r2\u0010\b\u0002\u0010\u0003\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\u000e\u001a\n\u0010\u000f\u001a\u00020\u0010*\u00020\u0011\u001a5\u0010\u0012\u001a\u00020\f*\u00020\u00112\u0010\b\u0002\u0010\u0003\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\u0013\u001a7\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00142\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\u0015\u001a7\u0010\u0000\u001a\u00020\f*\u0006\u0012\u0002\b\u00030\u00162\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\u0017\u001a\"\u0010\u0018\u001a\u00020\f*\u0006\u0012\u0002\b\u00030\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u0006\u001a\u00020\u0007¨\u0006\u001c"}, d2 = {"constructType", "Lorg/jetbrains/kotlin/fir/types/ConeLookupTagBasedType;", "Lorg/jetbrains/kotlin/fir/types/ConeClassifierLookupTag;", "typeArguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "isMarkedNullable", Argument.Delimiters.none, "attributes", "Lorg/jetbrains/kotlin/fir/types/ConeAttributes;", "(Lorg/jetbrains/kotlin/fir/types/ConeClassifierLookupTag;[Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;ZLorg/jetbrains/kotlin/fir/types/ConeAttributes;)Lorg/jetbrains/kotlin/fir/types/ConeLookupTagBasedType;", "constructClassType", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "(Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;[Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;ZLorg/jetbrains/kotlin/fir/types/ConeAttributes;)Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "toLookupTag", "Lorg/jetbrains/kotlin/fir/symbols/impl/ConeClassLikeLookupTagImpl;", "Lorg/jetbrains/kotlin/name/ClassId;", "constructClassLikeType", "(Lorg/jetbrains/kotlin/name/ClassId;[Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;ZLorg/jetbrains/kotlin/fir/types/ConeAttributes;)Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;[Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;ZLorg/jetbrains/kotlin/fir/types/ConeAttributes;)Lorg/jetbrains/kotlin/fir/types/ConeLookupTagBasedType;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;[Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;ZLorg/jetbrains/kotlin/fir/types/ConeAttributes;)Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "constructStarProjectedType", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "typeParameterNumber", Argument.Delimiters.none, "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TypeConstructionUtilsKt {
    public static final ConeClassLikeType constructClassLikeType(ClassId classId, ConeTypeProjection[] coneTypeProjectionArr, boolean z, ConeAttributes coneAttributes) {
        classId.getClass();
        coneTypeProjectionArr.getClass();
        coneAttributes.getClass();
        return new ConeClassLikeTypeImpl(toLookupTag(classId), coneTypeProjectionArr, z, coneAttributes);
    }

    public static /* synthetic */ ConeClassLikeType constructClassLikeType$default(ClassId classId, ConeTypeProjection[] coneTypeProjectionArr, boolean z, ConeAttributes coneAttributes, int i, Object obj) {
        if ((i & 1) != 0) {
            coneTypeProjectionArr = ConeTypeProjection.Companion.getEMPTY_ARRAY();
        }
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            coneAttributes = ConeAttributes.INSTANCE.getEmpty();
        }
        return constructClassLikeType(classId, coneTypeProjectionArr, z, coneAttributes);
    }

    public static final ConeClassLikeType constructClassType(ConeClassLikeLookupTag coneClassLikeLookupTag, ConeTypeProjection[] coneTypeProjectionArr, boolean z, ConeAttributes coneAttributes) {
        coneClassLikeLookupTag.getClass();
        coneTypeProjectionArr.getClass();
        coneAttributes.getClass();
        return new ConeClassLikeTypeImpl(coneClassLikeLookupTag, coneTypeProjectionArr, z, coneAttributes);
    }

    public static /* synthetic */ ConeClassLikeType constructClassType$default(ConeClassLikeLookupTag coneClassLikeLookupTag, ConeTypeProjection[] coneTypeProjectionArr, boolean z, ConeAttributes coneAttributes, int i, Object obj) {
        if ((i & 1) != 0) {
            coneTypeProjectionArr = ConeTypeProjection.Companion.getEMPTY_ARRAY();
        }
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            coneAttributes = ConeAttributes.INSTANCE.getEmpty();
        }
        return constructClassType(coneClassLikeLookupTag, coneTypeProjectionArr, z, coneAttributes);
    }

    public static final ConeClassLikeType constructStarProjectedType(FirClassSymbol<?> firClassSymbol, int i, boolean z) {
        firClassSymbol.getClass();
        ConeClassLikeLookupTag lookupTag = firClassSymbol.getLookupTag();
        ConeStarProjection[] coneStarProjectionArr = new ConeStarProjection[i];
        for (int i2 = 0; i2 < i; i2++) {
            coneStarProjectionArr[i2] = ConeStarProjection.INSTANCE;
        }
        return new ConeClassLikeTypeImpl(lookupTag, coneStarProjectionArr, z, null, 8, null);
    }

    public static /* synthetic */ ConeClassLikeType constructStarProjectedType$default(FirClassSymbol firClassSymbol, int i, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = firClassSymbol.getTypeParameterSymbols().size();
        }
        if ((i2 & 2) != 0) {
            z = false;
        }
        return constructStarProjectedType(firClassSymbol, i, z);
    }

    public static final ConeLookupTagBasedType constructType(ConeClassifierLookupTag coneClassifierLookupTag, ConeTypeProjection[] coneTypeProjectionArr, boolean z, ConeAttributes coneAttributes) {
        coneClassifierLookupTag.getClass();
        coneTypeProjectionArr.getClass();
        coneAttributes.getClass();
        if (coneClassifierLookupTag instanceof ConeTypeParameterLookupTag) {
            return new ConeTypeParameterTypeImpl((ConeTypeParameterLookupTag) coneClassifierLookupTag, z, coneAttributes);
        }
        if (coneClassifierLookupTag instanceof ConeClassLikeLookupTag) {
            return constructClassType((ConeClassLikeLookupTag) coneClassifierLookupTag, coneTypeProjectionArr, z, coneAttributes);
        }
        f2f.a("! ", Reflection.getOrCreateKotlinClass(coneClassifierLookupTag.getClass()));
        return null;
    }

    public static /* synthetic */ ConeLookupTagBasedType constructType$default(ConeClassifierLookupTag coneClassifierLookupTag, ConeTypeProjection[] coneTypeProjectionArr, boolean z, ConeAttributes coneAttributes, int i, Object obj) {
        if ((i & 1) != 0) {
            coneTypeProjectionArr = ConeTypeProjection.Companion.getEMPTY_ARRAY();
        }
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            coneAttributes = ConeAttributes.INSTANCE.getEmpty();
        }
        return constructType(coneClassifierLookupTag, coneTypeProjectionArr, z, coneAttributes);
    }

    public static final ConeClassLikeLookupTagImpl toLookupTag(ClassId classId) {
        classId.getClass();
        return new ConeClassLikeLookupTagImpl(classId);
    }

    public static /* synthetic */ ConeLookupTagBasedType constructType$default(FirClassifierSymbol firClassifierSymbol, ConeTypeProjection[] coneTypeProjectionArr, boolean z, ConeAttributes coneAttributes, int i, Object obj) {
        if ((i & 1) != 0) {
            coneTypeProjectionArr = ConeTypeProjection.Companion.getEMPTY_ARRAY();
        }
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            coneAttributes = ConeAttributes.INSTANCE.getEmpty();
        }
        return constructType((FirClassifierSymbol<?>) firClassifierSymbol, coneTypeProjectionArr, z, coneAttributes);
    }

    public static /* synthetic */ ConeClassLikeType constructType$default(FirClassLikeSymbol firClassLikeSymbol, ConeTypeProjection[] coneTypeProjectionArr, boolean z, ConeAttributes coneAttributes, int i, Object obj) {
        if ((i & 1) != 0) {
            coneTypeProjectionArr = ConeTypeProjection.Companion.getEMPTY_ARRAY();
        }
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            coneAttributes = ConeAttributes.INSTANCE.getEmpty();
        }
        return constructType((FirClassLikeSymbol<?>) firClassLikeSymbol, coneTypeProjectionArr, z, coneAttributes);
    }

    public static final ConeLookupTagBasedType constructType(FirClassifierSymbol<?> firClassifierSymbol, ConeTypeProjection[] coneTypeProjectionArr, boolean z, ConeAttributes coneAttributes) {
        firClassifierSymbol.getClass();
        coneTypeProjectionArr.getClass();
        coneAttributes.getClass();
        if (firClassifierSymbol instanceof FirTypeParameterSymbol) {
            return new ConeTypeParameterTypeImpl(((FirTypeParameterSymbol) firClassifierSymbol).getLookupTag(), z, coneAttributes);
        }
        if (firClassifierSymbol instanceof FirClassLikeSymbol) {
            return constructType((FirClassLikeSymbol<?>) firClassifierSymbol, coneTypeProjectionArr, z, coneAttributes);
        }
        bu8.a();
        return null;
    }

    public static final ConeClassLikeType constructType(FirClassLikeSymbol<?> firClassLikeSymbol, ConeTypeProjection[] coneTypeProjectionArr, boolean z, ConeAttributes coneAttributes) {
        firClassLikeSymbol.getClass();
        coneTypeProjectionArr.getClass();
        coneAttributes.getClass();
        return new ConeClassLikeTypeImpl(firClassLikeSymbol.getLookupTag(), coneTypeProjectionArr, z, coneAttributes);
    }
}
