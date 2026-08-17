package org.jetbrains.kotlin.fir.declarations;

import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\";\u0010\u0002\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0001*\u00020\u00032\f\u0010\u0000\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00018F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\b\u0010\t\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007\"3\u0010\u000b\u001a\u0004\u0018\u00010\n*\u00020\f2\b\u0010\u0000\u001a\u0004\u0018\u00010\n8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u000f\u0010\t\u001a\u0004\b\u000b\u0010\r\"\u0004\b\u0006\u0010\u000e¨\u0006\u0010"}, d2 = {"<set-?>", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "destructuringDeclarationContainerVariable", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "getDestructuringDeclarationContainerVariable", "(Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "setDestructuringDeclarationContainerVariable", "(Lorg/jetbrains/kotlin/fir/declarations/FirProperty;Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;)V", "destructuringDeclarationContainerVariable$delegate", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationDataRegistry$DeclarationDataAccessor;", Argument.Delimiters.none, "isDestructuringDeclarationContainerVariable", "Lorg/jetbrains/kotlin/fir/declarations/FirVariable;", "(Lorg/jetbrains/kotlin/fir/declarations/FirVariable;)Ljava/lang/Boolean;", "(Lorg/jetbrains/kotlin/fir/declarations/FirVariable;Ljava/lang/Boolean;)V", "isDestructuringDeclarationContainerVariable$delegate", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DestructuringDeclarationAttributesKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new MutablePropertyReference1Impl<>(DestructuringDeclarationAttributesKt.class, "destructuringDeclarationContainerVariable", "getDestructuringDeclarationContainerVariable(Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", 1), new MutablePropertyReference1Impl<>(DestructuringDeclarationAttributesKt.class, "isDestructuringDeclarationContainerVariable", "isDestructuringDeclarationContainerVariable(Lorg/jetbrains/kotlin/fir/declarations/FirVariable;)Ljava/lang/Boolean;", 1)};
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor destructuringDeclarationContainerVariable$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor isDestructuringDeclarationContainerVariable$delegate;

    static {
        FirDeclarationDataRegistry firDeclarationDataRegistry = FirDeclarationDataRegistry.INSTANCE;
        destructuringDeclarationContainerVariable$delegate = firDeclarationDataRegistry.data(DestructuringDeclarationContainerVariableKey.INSTANCE);
        isDestructuringDeclarationContainerVariable$delegate = firDeclarationDataRegistry.data(DestructuringDeclarationContainerVariableMarkerKey.INSTANCE);
    }

    public static final FirVariableSymbol<?> getDestructuringDeclarationContainerVariable(FirProperty firProperty) {
        firProperty.getClass();
        return (FirVariableSymbol) destructuringDeclarationContainerVariable$delegate.getValue(firProperty, $$delegatedProperties[0]);
    }

    public static final Boolean isDestructuringDeclarationContainerVariable(FirVariable firVariable) {
        firVariable.getClass();
        return (Boolean) isDestructuringDeclarationContainerVariable$delegate.getValue(firVariable, $$delegatedProperties[1]);
    }

    public static final void setDestructuringDeclarationContainerVariable(FirProperty firProperty, FirVariableSymbol<?> firVariableSymbol) {
        firProperty.getClass();
        destructuringDeclarationContainerVariable$delegate.setValue(firProperty, $$delegatedProperties[0], firVariableSymbol);
    }

    public static final void setDestructuringDeclarationContainerVariable(FirVariable firVariable, Boolean bool) {
        firVariable.getClass();
        isDestructuringDeclarationContainerVariable$delegate.setValue(firVariable, $$delegatedProperties[1], bool);
    }
}
