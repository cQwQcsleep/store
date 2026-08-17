package org.jetbrains.kotlin.fir.resolve.scopes;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.builtins.jvm.JavaToKotlinClassMap;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProviderKt;
import org.jetbrains.kotlin.fir.scopes.jvm.JvmMappedScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.StandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a:\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\f¨\u0006\r"}, d2 = {"wrapScopeWithJvmMapped", "Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;", "klass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "declaredMemberScope", "useSiteSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "memberRequiredPhase", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "filterOutJvmPlatformDeclarations", Argument.Delimiters.none, "org.jetbrains.kotlin:fir-jvm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JvmMappedScopesKt {
    /* JADX WARN: Multi-variable type inference failed */
    public static final FirContainingNamesAwareScope wrapScopeWithJvmMapped(FirClass firClass, FirContainingNamesAwareScope firContainingNamesAwareScope, FirSession firSession, ScopeSession scopeSession, FirResolvePhase firResolvePhase, boolean z) {
        firClass.getClass();
        firContainingNamesAwareScope.getClass();
        firSession.getClass();
        scopeSession.getClass();
        if (firClass instanceof FirRegularClass) {
            ClassId classId = FirDeclarationUtilKt.getClassId(firClass);
            ClassId classIdMapKotlinToJava = JavaToKotlinClassMap.INSTANCE.mapKotlinToJava(classId.asSingleFqName().toUnsafe());
            if (classIdMapKotlinToJava != null) {
                FirClassLikeSymbol<?> classLikeSymbolByClassId = FirSymbolProviderKt.getSymbolProvider(firSession).getClassLikeSymbolByClassId(classIdMapKotlinToJava);
                FirClassLikeDeclaration firClassLikeDeclaration = classLikeSymbolByClassId != null ? (FirClassLikeDeclaration) classLikeSymbolByClassId.getFir() : null;
                FirRegularClass firRegularClass = firClassLikeDeclaration instanceof FirRegularClass ? (FirRegularClass) firClassLikeDeclaration : null;
                if (firRegularClass != null) {
                    FirRegularClass firRegularClass2 = (FirRegularClass) firClass;
                    if (!FunctionalTypeUtilsKt.isSomeFunctionType(firRegularClass2.getSymbol().getLookupTag(), firSession) && !Intrinsics.areEqual(classId, StandardClassIds.INSTANCE.getAny())) {
                        return new JvmMappedScope(firSession, firRegularClass2, firRegularClass, firContainingNamesAwareScope, FirKotlinScopeProviderKt.unsubstitutedScope((FirClass) firRegularClass, firSession, scopeSession, false, firResolvePhase), z);
                    }
                }
            }
        }
        return firContainingNamesAwareScope;
    }

    public static /* synthetic */ FirContainingNamesAwareScope wrapScopeWithJvmMapped$default(FirClass firClass, FirContainingNamesAwareScope firContainingNamesAwareScope, FirSession firSession, ScopeSession scopeSession, FirResolvePhase firResolvePhase, boolean z, int i, Object obj) {
        if ((i & 32) != 0) {
            z = true;
        }
        return wrapScopeWithJvmMapped(firClass, firContainingNamesAwareScope, firSession, scopeSession, firResolvePhase, z);
    }
}
