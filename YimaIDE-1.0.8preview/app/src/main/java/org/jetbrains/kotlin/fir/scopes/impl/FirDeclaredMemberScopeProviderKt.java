package org.jetbrains.kotlin.fir.scopes.impl;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.util.ArrayMapAccessor;
import org.jetbrains.kotlin.util.TypeRegistry;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u001a \u0010\u0000\u001a\u00020\u0001*\u00020\u00022\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u00072\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u001a \u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\b\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u001a \u0010\t\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u001a\u0014\u0010\r\u001a\u0004\u0018\u00010\u000e*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a&\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\"\u001f\u0010\u0013\u001a\u00020\u0014*\u00020\u00028FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0019"}, d2 = {"declaredMemberScope", "Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;", "Lorg/jetbrains/kotlin/fir/FirSession;", "klass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "memberRequiredPhase", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "session", "declaredMemberScopeWithLazyNestedScope", "existingNames", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", "nestedClassifierScope", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirNestedClassifierScope;", "lazyNestedClassifierScope", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirLazyNestedClassifierScope;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "declaredMemberScopeProvider", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirDeclaredMemberScopeProvider;", "getDeclaredMemberScopeProvider", "(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/scopes/impl/FirDeclaredMemberScopeProvider;", "declaredMemberScopeProvider$delegate", "Lorg/jetbrains/kotlin/util/ArrayMapAccessor;", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDeclaredMemberScopeProviderKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(FirDeclaredMemberScopeProviderKt.class, "declaredMemberScopeProvider", "getDeclaredMemberScopeProvider(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/scopes/impl/FirDeclaredMemberScopeProvider;", 1)};
    private static final ArrayMapAccessor declaredMemberScopeProvider$delegate = TypeRegistry.generateAccessor$default(FirSession.INSTANCE, Reflection.getOrCreateKotlinClass(FirDeclaredMemberScopeProvider.class), (Object) null, 2, (Object) null);

    public static final FirContainingNamesAwareScope declaredMemberScope(FirSession firSession, FirClass firClass, FirResolvePhase firResolvePhase) {
        firSession.getClass();
        firClass.getClass();
        return getDeclaredMemberScopeProvider(firSession).declaredMemberScope(firClass, false, null, firResolvePhase);
    }

    public static final FirContainingNamesAwareScope declaredMemberScopeWithLazyNestedScope(FirSession firSession, FirClass firClass, List<Name> list) {
        firSession.getClass();
        firClass.getClass();
        list.getClass();
        return getDeclaredMemberScopeProvider(firSession).declaredMemberScope(firClass, true, list, null);
    }

    public static final FirDeclaredMemberScopeProvider getDeclaredMemberScopeProvider(FirSession firSession) {
        firSession.getClass();
        return (FirDeclaredMemberScopeProvider) declaredMemberScopeProvider$delegate.getValue(firSession, $$delegatedProperties[0]);
    }

    public static final FirLazyNestedClassifierScope lazyNestedClassifierScope(FirSession firSession, ClassId classId, List<Name> list) {
        firSession.getClass();
        classId.getClass();
        list.getClass();
        if (list.isEmpty()) {
            return null;
        }
        return new FirLazyNestedClassifierScope(firSession, classId, list);
    }

    public static final FirNestedClassifierScope nestedClassifierScope(FirSession firSession, FirClass firClass) {
        firSession.getClass();
        firClass.getClass();
        return getDeclaredMemberScopeProvider(firSession).nestedClassifierScope(firClass);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final FirContainingNamesAwareScope declaredMemberScope(FirSession firSession, FirClassSymbol<?> firClassSymbol, FirResolvePhase firResolvePhase) {
        firSession.getClass();
        firClassSymbol.getClass();
        return declaredMemberScope(firSession, (FirClass) firClassSymbol.getFir(), firResolvePhase);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final FirContainingNamesAwareScope declaredMemberScope(FirClassSymbol<?> firClassSymbol, FirSession firSession, FirResolvePhase firResolvePhase) {
        firClassSymbol.getClass();
        firSession.getClass();
        return declaredMemberScope(firSession, (FirClass) firClassSymbol.getFir(), firResolvePhase);
    }
}
