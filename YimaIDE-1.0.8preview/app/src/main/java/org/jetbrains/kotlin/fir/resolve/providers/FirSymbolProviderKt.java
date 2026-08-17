package org.jetbrains.kotlin.fir.resolve.providers;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.scopes.FirScopeKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirDeclaredMemberScopeProviderKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.util.ArrayMapAccessor;
import org.jetbrains.kotlin.util.TypeRegistry;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\u001a\u0016\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a$\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t\u001a\u0014\u0010\n\u001a\u0004\u0018\u00010\u000b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0014\u0010\f\u001a\u0004\u0018\u00010\u000b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\"\u001f\u0010\r\u001a\u00020\u000e*\u00020\u00028FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010\"\u000e\u0010\u0013\u001a\u00020\u0014X\u0086T¢\u0006\u0002\n\u0000\"\u001f\u0010\u0015\u001a\u00020\u000e*\u00020\u00028FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u0012\u001a\u0004\b\u0016\u0010\u0010¨\u0006\u0018"}, d2 = {"getClassDeclaredMemberScope", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "Lorg/jetbrains/kotlin/fir/FirSession;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "getClassDeclaredPropertySymbols", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "getRegularClassSymbolByClassIdFromDependencies", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "getRegularClassSymbolByClassId", "symbolProvider", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "getSymbolProvider", "(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "symbolProvider$delegate", "Lorg/jetbrains/kotlin/util/ArrayMapAccessor;", "DEPENDENCIES_SYMBOL_PROVIDER_QUALIFIED_KEY", Argument.Delimiters.none, "dependenciesSymbolProvider", "getDependenciesSymbolProvider", "dependenciesSymbolProvider$delegate", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirSymbolProviderKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(FirSymbolProviderKt.class, "symbolProvider", "getSymbolProvider(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", 1), new PropertyReference1Impl<>(FirSymbolProviderKt.class, "dependenciesSymbolProvider", "getDependenciesSymbolProvider(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", 1)};
    public static final String DEPENDENCIES_SYMBOL_PROVIDER_QUALIFIED_KEY = "org.jetbrains.kotlin.fir.resolve.providers.FirDependenciesSymbolProvider";
    private static final ArrayMapAccessor dependenciesSymbolProvider$delegate;
    private static final ArrayMapAccessor symbolProvider$delegate;

    static {
        FirSession.Companion companion = FirSession.INSTANCE;
        symbolProvider$delegate = TypeRegistry.generateAccessor$default(companion, Reflection.getOrCreateKotlinClass(FirSymbolProvider.class), (Object) null, 2, (Object) null);
        dependenciesSymbolProvider$delegate = TypeRegistry.generateAccessor$default(companion, DEPENDENCIES_SYMBOL_PROVIDER_QUALIFIED_KEY, (Object) null, 2, (Object) null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final FirScope getClassDeclaredMemberScope(FirSession firSession, ClassId classId) {
        FirClassLikeSymbol<?> classLikeSymbolByClassId = getSymbolProvider(firSession).getClassLikeSymbolByClassId(classId);
        FirRegularClassSymbol firRegularClassSymbol = classLikeSymbolByClassId instanceof FirRegularClassSymbol ? (FirRegularClassSymbol) classLikeSymbolByClassId : null;
        if (firRegularClassSymbol == null) {
            return null;
        }
        return FirDeclaredMemberScopeProviderKt.declaredMemberScope(firSession, (FirClass) firRegularClassSymbol.getFir(), (FirResolvePhase) null);
    }

    public static final List<FirVariableSymbol<?>> getClassDeclaredPropertySymbols(FirSession firSession, ClassId classId, Name name) {
        firSession.getClass();
        classId.getClass();
        name.getClass();
        FirScope classDeclaredMemberScope = getClassDeclaredMemberScope(firSession, classId);
        List<FirVariableSymbol<?>> properties = classDeclaredMemberScope != null ? FirScopeKt.getProperties(classDeclaredMemberScope, name) : null;
        return properties == null ? CollectionsKt.emptyList() : properties;
    }

    public static final FirSymbolProvider getDependenciesSymbolProvider(FirSession firSession) {
        firSession.getClass();
        return (FirSymbolProvider) dependenciesSymbolProvider$delegate.getValue(firSession, $$delegatedProperties[1]);
    }

    public static final FirRegularClassSymbol getRegularClassSymbolByClassId(FirSession firSession, ClassId classId) {
        firSession.getClass();
        classId.getClass();
        FirClassLikeSymbol<?> classLikeSymbolByClassId = getSymbolProvider(firSession).getClassLikeSymbolByClassId(classId);
        if (classLikeSymbolByClassId instanceof FirRegularClassSymbol) {
            return (FirRegularClassSymbol) classLikeSymbolByClassId;
        }
        return null;
    }

    public static final FirRegularClassSymbol getRegularClassSymbolByClassIdFromDependencies(FirSession firSession, ClassId classId) {
        firSession.getClass();
        classId.getClass();
        FirClassLikeSymbol<?> classLikeSymbolByClassId = getDependenciesSymbolProvider(firSession).getClassLikeSymbolByClassId(classId);
        if (classLikeSymbolByClassId instanceof FirRegularClassSymbol) {
            return (FirRegularClassSymbol) classLikeSymbolByClassId;
        }
        return null;
    }

    public static final FirSymbolProvider getSymbolProvider(FirSession firSession) {
        firSession.getClass();
        return (FirSymbolProvider) symbolProvider$delegate.getValue(firSession, $$delegatedProperties[0]);
    }
}
