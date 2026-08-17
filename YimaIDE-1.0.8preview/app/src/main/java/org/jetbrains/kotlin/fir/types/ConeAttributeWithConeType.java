package org.jetbrains.kotlin.fir.types;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.types.ConeAttributeWithConeType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u0000*\u0010\b\u0000\u0010\u0001 \u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00002\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\t\u001a\u00028\u00002\u0006\u0010\n\u001a\u00020\u0006H&¢\u0006\u0002\u0010\u000bR\u0012\u0010\u0005\u001a\u00020\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/ConeAttributeWithConeType;", "T", "Lorg/jetbrains/kotlin/fir/types/ConeAttribute;", "<init>", "()V", "coneType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getConeType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "copyWith", "newType", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Lorg/jetbrains/kotlin/fir/types/ConeAttributeWithConeType;", "org.jetbrains.kotlin:cones"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class ConeAttributeWithConeType<T extends ConeAttributeWithConeType<? extends T>> extends ConeAttribute<T> {
    public abstract T copyWith(ConeKotlinType newType);

    public abstract ConeKotlinType getConeType();
}
