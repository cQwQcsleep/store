package org.jetbrains.kotlin.fir.resolve;

import defpackage.dwe;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.symbols.ConeClassifierLookupTagWithFixedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.ConeClassLikeLookupTagImpl;
import org.jetbrains.kotlin.fir.symbols.impl.ConeClassLikeLookupTagWithFixedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeClassifierLookupTag;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeLookupTagBasedType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.util.WeakPair;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Z\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0018\u0010\u0000\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a!\u0010\u0000\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0001*\u00020\u0002R\u00020\u0005j\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007\u001a\u0018\u0010\b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\t*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a!\u0010\b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\t*\u00020\u0002R\u00020\u0005j\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\n\u001a\u0014\u0010\u000b\u001a\u0004\u0018\u00010\f*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u001d\u0010\u000b\u001a\u0004\u0018\u00010\f*\u00020\u0002R\u00020\u0005j\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\r\u001a\u0018\u0010\u0000\u001a\b\u0012\u0002\b\u0003\u0018\u00010\t*\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u0004\u001a!\u0010\u0000\u001a\b\u0012\u0002\b\u0003\u0018\u00010\t*\u00020\u000eR\u00020\u0005j\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u000f\u001a\u0018\u0010\u0010\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0011*\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0004\u001a!\u0010\u0010\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0011*\u00020\u000eR\u00020\u0005j\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0013\u001a\u0014\u0010\u000b\u001a\u0004\u0018\u00010\f*\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0004\u001a\u001d\u0010\u000b\u001a\u0004\u0018\u00010\f*\u00020\u000eR\u00020\u0005j\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0014\u001a\u0014\u0010\u0015\u001a\u0004\u0018\u00010\u0016*\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u001d\u0010\u0015\u001a\u0004\u0018\u00010\u0016*\u00020\u000eR\u00020\u0005j\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0017\u001a\u0018\u0010\u0000\u001a\b\u0012\u0002\b\u0003\u0018\u00010\t*\u00020\u00182\u0006\u0010\u0012\u001a\u00020\u0004\u001a!\u0010\u0000\u001a\b\u0012\u0002\b\u0003\u0018\u00010\t*\u00020\u0018R\u00020\u0005j\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0019\u001a\u0018\u0010\u0000\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0001*\u00020\u001a2\u0006\u0010\u0012\u001a\u00020\u0004\u001a!\u0010\u0000\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0001*\u00020\u001aR\u00020\u0005j\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u001b\u001a\u0018\u0010\b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\t*\u00020\u001a2\u0006\u0010\u0012\u001a\u00020\u0004\u001a!\u0010\b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\t*\u00020\u001aR\u00020\u0005j\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u001c\u001a\u0014\u0010\u0015\u001a\u0004\u0018\u00010\u0016*\u00020\u001a2\u0006\u0010\u0012\u001a\u00020\u0004\u001a\u001d\u0010\u0015\u001a\u0004\u0018\u00010\u0016*\u00020\u001aR\u00020\u0005j\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u001d\u001a\u0014\u0010\u001e\u001a\u0004\u0018\u00010\u001f*\u00020\u001a2\u0006\u0010\u0012\u001a\u00020\u0004\u001a\u001d\u0010\u001e\u001a\u0004\u0018\u00010\u001f*\u00020\u001aR\u00020\u0005j\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010 \u001a\u0018\u0010\u0010\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0011*\u00020\u001a2\u0006\u0010\u0012\u001a\u00020\u0004\u001a!\u0010\u0010\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0011*\u00020\u001aR\u00020\u0005j\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010!\u001a\u0018\u0010\u0010\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0011*\u00020\u00182\u0006\u0010\u0012\u001a\u00020\u0004\u001a!\u0010\u0010\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0011*\u00020\u0018R\u00020\u0005j\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\"\u001a\u0014\u0010\u000b\u001a\u0004\u0018\u00010\f*\u00020\u001a2\u0006\u0010\u0012\u001a\u00020\u0004\u001a\u001d\u0010\u000b\u001a\u0004\u0018\u00010\f*\u00020\u001aR\u00020\u0005j\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010#\u001a\u0014\u0010\u000b\u001a\u0004\u0018\u00010\f*\u00020\u00182\u0006\u0010\u0012\u001a\u00020\u0004\u001a\u001d\u0010\u000b\u001a\u0004\u0018\u00010\f*\u00020\u0018R\u00020\u0005j\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010$\u001a\u0018\u0010\u0000\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0001*\u00020%2\u0006\u0010\u0012\u001a\u00020\u0004\u001a!\u0010\u0000\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0001*\u00020%R\u00020\u0005j\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010&¨\u0006'"}, d2 = {"toSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "Lorg/jetbrains/kotlin/fir/types/ConeClassifierLookupTag;", "useSiteSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "Lorg/jetbrains/kotlin/fir/SessionHolder;", "sessionHolder", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/types/ConeClassifierLookupTag;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "toClassLikeSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/types/ConeClassifierLookupTag;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "toRegularClassSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/types/ConeClassifierLookupTag;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "toClassSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "session", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "toTypeAliasSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeAliasSymbol;", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeAliasSymbol;", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeAliasSymbol;", "toTypeParameterSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "Lorg/jetbrains/kotlin/name/ClassId;", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/name/ClassId;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ToSymbolUtilsKt {
    public static final FirClassLikeSymbol<?> toClassLikeSymbol(ConeClassifierLookupTag coneClassifierLookupTag, FirSession firSession) {
        coneClassifierLookupTag.getClass();
        firSession.getClass();
        FirClassifierSymbol<?> symbol = toSymbol(coneClassifierLookupTag, firSession);
        if (symbol instanceof FirClassLikeSymbol) {
            return (FirClassLikeSymbol) symbol;
        }
        return null;
    }

    public static final FirClassSymbol<?> toClassSymbol(ConeKotlinType coneKotlinType, FirSession firSession) {
        coneKotlinType.getClass();
        firSession.getClass();
        ConeRigidType coneRigidTypeLowerBoundIfFlexible = ConeTypeUtilsKt.lowerBoundIfFlexible(coneKotlinType);
        ConeClassLikeType coneClassLikeType = coneRigidTypeLowerBoundIfFlexible instanceof ConeClassLikeType ? (ConeClassLikeType) coneRigidTypeLowerBoundIfFlexible : null;
        if (coneClassLikeType != null) {
            return toClassSymbol(coneClassLikeType, firSession);
        }
        return null;
    }

    public static final FirRegularClassSymbol toRegularClassSymbol(ConeKotlinType coneKotlinType, FirSession firSession) {
        coneKotlinType.getClass();
        firSession.getClass();
        ConeRigidType coneRigidTypeLowerBoundIfFlexible = ConeTypeUtilsKt.lowerBoundIfFlexible(coneKotlinType);
        ConeClassLikeType coneClassLikeType = coneRigidTypeLowerBoundIfFlexible instanceof ConeClassLikeType ? (ConeClassLikeType) coneRigidTypeLowerBoundIfFlexible : null;
        if (coneClassLikeType != null) {
            return toRegularClassSymbol(coneClassLikeType, firSession);
        }
        return null;
    }

    public static final FirClassLikeSymbol<?> toSymbol(ConeClassLikeLookupTag coneClassLikeLookupTag, FirSession firSession) {
        WeakPair<FirSession, FirClassLikeSymbol<?>> boundSymbol;
        coneClassLikeLookupTag.getClass();
        firSession.getClass();
        if (coneClassLikeLookupTag instanceof ConeClassLikeLookupTagWithFixedSymbol) {
            return ((ConeClassLikeLookupTagWithFixedSymbol) coneClassLikeLookupTag).getSymbol();
        }
        ConeClassLikeLookupTagImpl coneClassLikeLookupTagImpl = coneClassLikeLookupTag instanceof ConeClassLikeLookupTagImpl ? (ConeClassLikeLookupTagImpl) coneClassLikeLookupTag : null;
        if (coneClassLikeLookupTagImpl != null && (boundSymbol = coneClassLikeLookupTagImpl.getBoundSymbol()) != null) {
            if (boundSymbol.getFirst() != firSession) {
                boundSymbol = null;
            }
            if (boundSymbol != null) {
                return boundSymbol.getSecond();
            }
        }
        FirClassLikeSymbol<?> classLikeSymbolByClassId = FirSymbolProviderKt.getSymbolProvider(firSession).getClassLikeSymbolByClassId(coneClassLikeLookupTag.getClassId());
        ConeClassLikeLookupTagImpl coneClassLikeLookupTagImpl2 = coneClassLikeLookupTag instanceof ConeClassLikeLookupTagImpl ? (ConeClassLikeLookupTagImpl) coneClassLikeLookupTag : null;
        if (coneClassLikeLookupTagImpl2 != null) {
            LookupTagUtilsKt.bindSymbolToLookupTag(coneClassLikeLookupTagImpl2, firSession, classLikeSymbolByClassId);
        }
        return classLikeSymbolByClassId;
    }

    public static final FirTypeAliasSymbol toTypeAliasSymbol(ConeClassLikeLookupTag coneClassLikeLookupTag, FirSession firSession) {
        coneClassLikeLookupTag.getClass();
        firSession.getClass();
        FirClassLikeSymbol<?> symbol = toSymbol(coneClassLikeLookupTag, firSession);
        if (symbol instanceof FirTypeAliasSymbol) {
            return (FirTypeAliasSymbol) symbol;
        }
        return null;
    }

    public static final FirTypeParameterSymbol toTypeParameterSymbol(ConeKotlinType coneKotlinType, FirSession firSession) {
        coneKotlinType.getClass();
        firSession.getClass();
        FirClassifierSymbol<?> symbol = toSymbol(coneKotlinType, firSession);
        if (symbol instanceof FirTypeParameterSymbol) {
            return (FirTypeParameterSymbol) symbol;
        }
        return null;
    }

    public static final FirClassLikeSymbol<?> toClassLikeSymbol(SessionHolder sessionHolder, ConeClassifierLookupTag coneClassifierLookupTag) {
        sessionHolder.getClass();
        coneClassifierLookupTag.getClass();
        return toClassLikeSymbol(coneClassifierLookupTag, sessionHolder.getSession());
    }

    public static final FirTypeAliasSymbol toTypeAliasSymbol(SessionHolder sessionHolder, ConeClassLikeLookupTag coneClassLikeLookupTag) {
        sessionHolder.getClass();
        coneClassLikeLookupTag.getClass();
        return toTypeAliasSymbol(coneClassLikeLookupTag, sessionHolder.getSession());
    }

    public static final FirTypeParameterSymbol toTypeParameterSymbol(SessionHolder sessionHolder, ConeKotlinType coneKotlinType) {
        sessionHolder.getClass();
        coneKotlinType.getClass();
        return toTypeParameterSymbol(coneKotlinType, sessionHolder.getSession());
    }

    public static final FirClassLikeSymbol<?> toClassLikeSymbol(ConeKotlinType coneKotlinType, FirSession firSession) {
        coneKotlinType.getClass();
        firSession.getClass();
        FirClassifierSymbol<?> symbol = toSymbol(coneKotlinType, firSession);
        if (symbol instanceof FirClassLikeSymbol) {
            return (FirClassLikeSymbol) symbol;
        }
        return null;
    }

    public static final FirTypeAliasSymbol toTypeAliasSymbol(ConeKotlinType coneKotlinType, FirSession firSession) {
        coneKotlinType.getClass();
        firSession.getClass();
        FirClassifierSymbol<?> symbol = toSymbol(coneKotlinType, firSession);
        if (symbol instanceof FirTypeAliasSymbol) {
            return (FirTypeAliasSymbol) symbol;
        }
        return null;
    }

    public static final FirClassLikeSymbol<?> toClassLikeSymbol(SessionHolder sessionHolder, ConeKotlinType coneKotlinType) {
        sessionHolder.getClass();
        coneKotlinType.getClass();
        return toClassLikeSymbol(coneKotlinType, sessionHolder.getSession());
    }

    public static final FirTypeAliasSymbol toTypeAliasSymbol(SessionHolder sessionHolder, ConeKotlinType coneKotlinType) {
        sessionHolder.getClass();
        coneKotlinType.getClass();
        return toTypeAliasSymbol(coneKotlinType, sessionHolder.getSession());
    }

    public static final FirClassSymbol<?> toClassSymbol(SessionHolder sessionHolder, ConeClassLikeLookupTag coneClassLikeLookupTag) {
        sessionHolder.getClass();
        coneClassLikeLookupTag.getClass();
        return toClassSymbol(coneClassLikeLookupTag, sessionHolder.getSession());
    }

    public static final FirRegularClassSymbol toRegularClassSymbol(SessionHolder sessionHolder, ConeClassifierLookupTag coneClassifierLookupTag) {
        sessionHolder.getClass();
        coneClassifierLookupTag.getClass();
        return toRegularClassSymbol(coneClassifierLookupTag, sessionHolder.getSession());
    }

    public static final FirClassSymbol<?> toClassSymbol(ConeClassLikeLookupTag coneClassLikeLookupTag, FirSession firSession) {
        coneClassLikeLookupTag.getClass();
        firSession.getClass();
        FirClassLikeSymbol<?> symbol = toSymbol(coneClassLikeLookupTag, firSession);
        if (symbol instanceof FirClassSymbol) {
            return (FirClassSymbol) symbol;
        }
        return null;
    }

    public static final FirRegularClassSymbol toRegularClassSymbol(ConeClassLikeLookupTag coneClassLikeLookupTag, FirSession firSession) {
        coneClassLikeLookupTag.getClass();
        firSession.getClass();
        FirClassLikeSymbol<?> symbol = toSymbol(coneClassLikeLookupTag, firSession);
        if (symbol instanceof FirRegularClassSymbol) {
            return (FirRegularClassSymbol) symbol;
        }
        return null;
    }

    public static final FirClassSymbol<?> toClassSymbol(SessionHolder sessionHolder, ConeKotlinType coneKotlinType) {
        sessionHolder.getClass();
        coneKotlinType.getClass();
        return toClassSymbol(coneKotlinType, sessionHolder.getSession());
    }

    public static final FirRegularClassSymbol toRegularClassSymbol(SessionHolder sessionHolder, ConeClassLikeLookupTag coneClassLikeLookupTag) {
        sessionHolder.getClass();
        coneClassLikeLookupTag.getClass();
        return toRegularClassSymbol(coneClassLikeLookupTag, sessionHolder.getSession());
    }

    public static final FirClassSymbol<?> toClassSymbol(ConeClassLikeType coneClassLikeType, FirSession firSession) {
        coneClassLikeType.getClass();
        firSession.getClass();
        FirClassLikeSymbol<?> symbol = toSymbol(TypeExpansionUtilsKt.fullyExpandedType$default(coneClassLikeType, firSession, (Function1) null, 2, (Object) null), firSession);
        if (symbol instanceof FirClassSymbol) {
            return (FirClassSymbol) symbol;
        }
        return null;
    }

    public static final FirRegularClassSymbol toRegularClassSymbol(ConeClassifierLookupTag coneClassifierLookupTag, FirSession firSession) {
        coneClassifierLookupTag.getClass();
        firSession.getClass();
        FirClassifierSymbol<?> symbol = toSymbol(coneClassifierLookupTag, firSession);
        if (symbol instanceof FirRegularClassSymbol) {
            return (FirRegularClassSymbol) symbol;
        }
        return null;
    }

    public static final FirClassSymbol<?> toClassSymbol(SessionHolder sessionHolder, ConeClassLikeType coneClassLikeType) {
        sessionHolder.getClass();
        coneClassLikeType.getClass();
        return toClassSymbol(coneClassLikeType, sessionHolder.getSession());
    }

    public static final FirRegularClassSymbol toRegularClassSymbol(SessionHolder sessionHolder, ConeKotlinType coneKotlinType) {
        sessionHolder.getClass();
        coneKotlinType.getClass();
        return toRegularClassSymbol(coneKotlinType, sessionHolder.getSession());
    }

    public static final FirRegularClassSymbol toRegularClassSymbol(ConeClassLikeType coneClassLikeType, FirSession firSession) {
        coneClassLikeType.getClass();
        firSession.getClass();
        FirClassLikeSymbol<?> symbol = toSymbol(TypeExpansionUtilsKt.fullyExpandedType$default(coneClassLikeType, firSession, (Function1) null, 2, (Object) null), firSession);
        if (symbol instanceof FirRegularClassSymbol) {
            return (FirRegularClassSymbol) symbol;
        }
        return null;
    }

    public static final FirRegularClassSymbol toRegularClassSymbol(SessionHolder sessionHolder, ConeClassLikeType coneClassLikeType) {
        sessionHolder.getClass();
        coneClassLikeType.getClass();
        return toRegularClassSymbol(coneClassLikeType, sessionHolder.getSession());
    }

    public static final FirClassifierSymbol<?> toSymbol(SessionHolder sessionHolder, ConeClassifierLookupTag coneClassifierLookupTag) {
        sessionHolder.getClass();
        coneClassifierLookupTag.getClass();
        return toSymbol(coneClassifierLookupTag, sessionHolder.getSession());
    }

    public static final FirClassifierSymbol<?> toSymbol(ConeClassifierLookupTag coneClassifierLookupTag, FirSession firSession) {
        coneClassifierLookupTag.getClass();
        firSession.getClass();
        if (coneClassifierLookupTag instanceof ConeClassLikeLookupTag) {
            return toSymbol((ConeClassLikeLookupTag) coneClassifierLookupTag, firSession);
        }
        if (coneClassifierLookupTag instanceof ConeClassifierLookupTagWithFixedSymbol) {
            return ((ConeClassifierLookupTagWithFixedSymbol) coneClassifierLookupTag).getSymbol();
        }
        dwe.a("missing branch for ".concat(coneClassifierLookupTag.getClass().getName()));
        return null;
    }

    public static final FirClassLikeSymbol<?> toSymbol(SessionHolder sessionHolder, ConeClassLikeLookupTag coneClassLikeLookupTag) {
        sessionHolder.getClass();
        coneClassLikeLookupTag.getClass();
        return toSymbol(coneClassLikeLookupTag, sessionHolder.getSession());
    }

    public static final FirClassLikeSymbol<?> toSymbol(ConeClassLikeType coneClassLikeType, FirSession firSession) {
        coneClassLikeType.getClass();
        firSession.getClass();
        return toSymbol(coneClassLikeType.getLookupTag(), firSession);
    }

    public static final FirClassLikeSymbol<?> toSymbol(SessionHolder sessionHolder, ConeClassLikeType coneClassLikeType) {
        sessionHolder.getClass();
        coneClassLikeType.getClass();
        return toSymbol(coneClassLikeType, sessionHolder.getSession());
    }

    public static final FirClassifierSymbol<?> toSymbol(ConeKotlinType coneKotlinType, FirSession firSession) {
        ConeClassifierLookupTag lookupTag;
        coneKotlinType.getClass();
        firSession.getClass();
        ConeRigidType coneRigidTypeLowerBoundIfFlexible = ConeTypeUtilsKt.lowerBoundIfFlexible(coneKotlinType);
        ConeLookupTagBasedType coneLookupTagBasedType = coneRigidTypeLowerBoundIfFlexible instanceof ConeLookupTagBasedType ? (ConeLookupTagBasedType) coneRigidTypeLowerBoundIfFlexible : null;
        if (coneLookupTagBasedType == null || (lookupTag = coneLookupTagBasedType.getLookupTag()) == null) {
            return null;
        }
        return toSymbol(lookupTag, firSession);
    }

    public static final FirClassifierSymbol<?> toSymbol(SessionHolder sessionHolder, ConeKotlinType coneKotlinType) {
        sessionHolder.getClass();
        coneKotlinType.getClass();
        return toSymbol(coneKotlinType, sessionHolder.getSession());
    }

    public static final FirClassifierSymbol<?> toSymbol(ClassId classId, FirSession firSession) {
        classId.getClass();
        firSession.getClass();
        return FirSymbolProviderKt.getSymbolProvider(firSession).getClassLikeSymbolByClassId(classId);
    }

    public static final FirClassifierSymbol<?> toSymbol(SessionHolder sessionHolder, ClassId classId) {
        sessionHolder.getClass();
        classId.getClass();
        return toSymbol(classId, sessionHolder.getSession());
    }
}
