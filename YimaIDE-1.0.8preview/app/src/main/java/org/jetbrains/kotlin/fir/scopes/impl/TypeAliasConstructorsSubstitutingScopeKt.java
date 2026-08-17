package org.jetbrains.kotlin.fir.scopes.impl;

import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationDataRegistry;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.util.ArrayMapAccessor;
import org.jetbrains.kotlin.util.TypeRegistry;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\"I\u0010\u0002\u001a\n\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0004*\u0002H\u00032\u000e\u0010\u0000\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00018F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\t\u0010\n\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\"\u001b\u0010\u0002\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0001*\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\f\"\u001f\u0010\r\u001a\u00020\u000e*\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"<set-?>", "Lorg/jetbrains/kotlin/fir/scopes/impl/TypeAliasConstructorInfo;", "typeAliasConstructorInfo", "T", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "getTypeAliasConstructorInfo", "(Lorg/jetbrains/kotlin/fir/declarations/FirFunction;)Lorg/jetbrains/kotlin/fir/scopes/impl/TypeAliasConstructorInfo;", "setTypeAliasConstructorInfo", "(Lorg/jetbrains/kotlin/fir/declarations/FirFunction;Lorg/jetbrains/kotlin/fir/scopes/impl/TypeAliasConstructorInfo;)V", "typeAliasConstructorInfo$delegate", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationDataRegistry$DeclarationDataAccessor;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;)Lorg/jetbrains/kotlin/fir/scopes/impl/TypeAliasConstructorInfo;", "typealiasConstructorsStorage", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirTypealiasConstructorStorage;", "Lorg/jetbrains/kotlin/fir/FirSession;", "getTypealiasConstructorsStorage", "(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/scopes/impl/FirTypealiasConstructorStorage;", "typealiasConstructorsStorage$delegate", "Lorg/jetbrains/kotlin/util/ArrayMapAccessor;", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TypeAliasConstructorsSubstitutingScopeKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new MutablePropertyReference1Impl<>(TypeAliasConstructorsSubstitutingScopeKt.class, "typeAliasConstructorInfo", "getTypeAliasConstructorInfo(Lorg/jetbrains/kotlin/fir/declarations/FirFunction;)Lorg/jetbrains/kotlin/fir/scopes/impl/TypeAliasConstructorInfo;", 1), new PropertyReference1Impl<>(TypeAliasConstructorsSubstitutingScopeKt.class, "typealiasConstructorsStorage", "getTypealiasConstructorsStorage(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/scopes/impl/FirTypealiasConstructorStorage;", 1)};
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor typeAliasConstructorInfo$delegate = FirDeclarationDataRegistry.INSTANCE.data(TypeAliasConstructorInfoKey.INSTANCE);
    private static final ArrayMapAccessor typealiasConstructorsStorage$delegate = TypeRegistry.generateAccessor$default(FirSession.INSTANCE, Reflection.getOrCreateKotlinClass(FirTypealiasConstructorStorage.class), (Object) null, 2, (Object) null);

    public static final <T extends FirFunction> TypeAliasConstructorInfo<T> getTypeAliasConstructorInfo(T t) {
        t.getClass();
        return (TypeAliasConstructorInfo) typeAliasConstructorInfo$delegate.getValue(t, $$delegatedProperties[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FirTypealiasConstructorStorage getTypealiasConstructorsStorage(FirSession firSession) {
        return (FirTypealiasConstructorStorage) typealiasConstructorsStorage$delegate.getValue(firSession, $$delegatedProperties[1]);
    }

    public static final <T extends FirFunction> void setTypeAliasConstructorInfo(T t, TypeAliasConstructorInfo<T> typeAliasConstructorInfo) {
        t.getClass();
        typeAliasConstructorInfo$delegate.setValue(t, $$delegatedProperties[0], typeAliasConstructorInfo);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final TypeAliasConstructorInfo<?> getTypeAliasConstructorInfo(FirConstructorSymbol firConstructorSymbol) {
        firConstructorSymbol.getClass();
        return getTypeAliasConstructorInfo((FirFunction) firConstructorSymbol.getFir());
    }
}
