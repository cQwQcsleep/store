package org.jetbrains.kotlin.fir.analysis.checkers;

import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.types.ConeAttributes;
import org.jetbrains.kotlin.util.NullableArrayMapAccessor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\"!\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0007"}, d2 = {"originalProjection", "Lorg/jetbrains/kotlin/fir/analysis/checkers/OriginalProjectionTypeAttribute;", "Lorg/jetbrains/kotlin/fir/types/ConeAttributes;", "getOriginalProjection", "(Lorg/jetbrains/kotlin/fir/types/ConeAttributes;)Lorg/jetbrains/kotlin/fir/analysis/checkers/OriginalProjectionTypeAttribute;", "originalProjection$delegate", "Lkotlin/properties/ReadOnlyProperty;", "org.jetbrains.kotlin:checkers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FE10LikeConeSubstitutorKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(FE10LikeConeSubstitutorKt.class, "originalProjection", "getOriginalProjection(Lorg/jetbrains/kotlin/fir/types/ConeAttributes;)Lorg/jetbrains/kotlin/fir/analysis/checkers/OriginalProjectionTypeAttribute;", 1)};
    private static final ReadOnlyProperty originalProjection$delegate;

    static {
        NullableArrayMapAccessor nullableArrayMapAccessorGenerateNullableAccessor = ConeAttributes.INSTANCE.generateNullableAccessor(Reflection.getOrCreateKotlinClass(OriginalProjectionTypeAttribute.class));
        nullableArrayMapAccessorGenerateNullableAccessor.getClass();
        originalProjection$delegate = nullableArrayMapAccessorGenerateNullableAccessor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final OriginalProjectionTypeAttribute getOriginalProjection(ConeAttributes coneAttributes) {
        return (OriginalProjectionTypeAttribute) originalProjection$delegate.getValue(coneAttributes, $$delegatedProperties[0]);
    }
}
