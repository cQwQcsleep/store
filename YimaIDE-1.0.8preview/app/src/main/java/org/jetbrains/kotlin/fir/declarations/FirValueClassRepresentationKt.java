package org.jetbrains.kotlin.fir.declarations;

import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.InlineClassRepresentation;
import org.jetbrains.kotlin.descriptors.MultiFieldValueClassRepresentation;
import org.jetbrains.kotlin.descriptors.ValueClassRepresentation;
import org.jetbrains.kotlin.fir.types.ConeRigidType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\"?\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001*\u00020\u00042\u000e\u0010\u0000\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00018F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\t\u0010\n\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\"\u001d\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\f*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e\"\u001d\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0010*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"<set-?>", "Lorg/jetbrains/kotlin/descriptors/ValueClassRepresentation;", "Lorg/jetbrains/kotlin/fir/types/ConeRigidType;", "valueClassRepresentation", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "getValueClassRepresentation", "(Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;)Lorg/jetbrains/kotlin/descriptors/ValueClassRepresentation;", "setValueClassRepresentation", "(Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;Lorg/jetbrains/kotlin/descriptors/ValueClassRepresentation;)V", "valueClassRepresentation$delegate", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationDataRegistry$DeclarationDataAccessor;", "inlineClassRepresentation", "Lorg/jetbrains/kotlin/descriptors/InlineClassRepresentation;", "getInlineClassRepresentation", "(Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;)Lorg/jetbrains/kotlin/descriptors/InlineClassRepresentation;", "multiFieldValueClassRepresentation", "Lorg/jetbrains/kotlin/descriptors/MultiFieldValueClassRepresentation;", "getMultiFieldValueClassRepresentation", "(Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;)Lorg/jetbrains/kotlin/descriptors/MultiFieldValueClassRepresentation;", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirValueClassRepresentationKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new MutablePropertyReference1Impl<>(FirValueClassRepresentationKt.class, "valueClassRepresentation", "getValueClassRepresentation(Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;)Lorg/jetbrains/kotlin/descriptors/ValueClassRepresentation;", 1)};
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor valueClassRepresentation$delegate = FirDeclarationDataRegistry.INSTANCE.data(FirValueClassRepresentationKey.INSTANCE);

    public static final InlineClassRepresentation<ConeRigidType> getInlineClassRepresentation(FirRegularClass firRegularClass) {
        firRegularClass.getClass();
        ValueClassRepresentation<ConeRigidType> valueClassRepresentation = getValueClassRepresentation(firRegularClass);
        if (valueClassRepresentation instanceof InlineClassRepresentation) {
            return (InlineClassRepresentation) valueClassRepresentation;
        }
        return null;
    }

    public static final MultiFieldValueClassRepresentation<ConeRigidType> getMultiFieldValueClassRepresentation(FirRegularClass firRegularClass) {
        firRegularClass.getClass();
        ValueClassRepresentation<ConeRigidType> valueClassRepresentation = getValueClassRepresentation(firRegularClass);
        if (valueClassRepresentation instanceof MultiFieldValueClassRepresentation) {
            return (MultiFieldValueClassRepresentation) valueClassRepresentation;
        }
        return null;
    }

    public static final ValueClassRepresentation<ConeRigidType> getValueClassRepresentation(FirRegularClass firRegularClass) {
        firRegularClass.getClass();
        return (ValueClassRepresentation) valueClassRepresentation$delegate.getValue(firRegularClass, $$delegatedProperties[0]);
    }

    public static final void setValueClassRepresentation(FirRegularClass firRegularClass, ValueClassRepresentation<ConeRigidType> valueClassRepresentation) {
        firRegularClass.getClass();
        valueClassRepresentation$delegate.setValue(firRegularClass, $$delegatedProperties[0], valueClassRepresentation);
    }
}
