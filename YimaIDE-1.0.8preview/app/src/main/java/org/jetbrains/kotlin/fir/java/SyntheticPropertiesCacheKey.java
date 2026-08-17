package org.jetbrains.kotlin.fir.java;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007HÆ\u0003J/\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007HÆ\u0001J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u001aHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/SyntheticPropertiesCacheKey;", Argument.Delimiters.none, ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "receiverParameterType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "contextParameterTypes", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/name/Name;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Ljava/util/List;)V", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "getReceiverParameterType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getContextParameterTypes", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class SyntheticPropertiesCacheKey {
    private final List<ConeKotlinType> contextParameterTypes;
    private final Name name;
    private final ConeKotlinType receiverParameterType;

    public SyntheticPropertiesCacheKey(Name name, ConeKotlinType coneKotlinType, List<? extends ConeKotlinType> list) {
        name.getClass();
        list.getClass();
        this.name = name;
        this.receiverParameterType = coneKotlinType;
        this.contextParameterTypes = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SyntheticPropertiesCacheKey copy$default(SyntheticPropertiesCacheKey syntheticPropertiesCacheKey, Name name, ConeKotlinType coneKotlinType, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            name = syntheticPropertiesCacheKey.name;
        }
        if ((i & 2) != 0) {
            coneKotlinType = syntheticPropertiesCacheKey.receiverParameterType;
        }
        if ((i & 4) != 0) {
            list = syntheticPropertiesCacheKey.contextParameterTypes;
        }
        return syntheticPropertiesCacheKey.copy(name, coneKotlinType, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Name getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final ConeKotlinType getReceiverParameterType() {
        return this.receiverParameterType;
    }

    public final List<ConeKotlinType> component3() {
        return this.contextParameterTypes;
    }

    public final SyntheticPropertiesCacheKey copy(Name name, ConeKotlinType receiverParameterType, List<? extends ConeKotlinType> contextParameterTypes) {
        name.getClass();
        contextParameterTypes.getClass();
        return new SyntheticPropertiesCacheKey(name, receiverParameterType, contextParameterTypes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SyntheticPropertiesCacheKey)) {
            return false;
        }
        SyntheticPropertiesCacheKey syntheticPropertiesCacheKey = (SyntheticPropertiesCacheKey) other;
        return Intrinsics.areEqual(this.name, syntheticPropertiesCacheKey.name) && Intrinsics.areEqual(this.receiverParameterType, syntheticPropertiesCacheKey.receiverParameterType) && Intrinsics.areEqual(this.contextParameterTypes, syntheticPropertiesCacheKey.contextParameterTypes);
    }

    public final List<ConeKotlinType> getContextParameterTypes() {
        return this.contextParameterTypes;
    }

    public final Name getName() {
        return this.name;
    }

    public final ConeKotlinType getReceiverParameterType() {
        return this.receiverParameterType;
    }

    public int hashCode() {
        int iHashCode = this.name.hashCode() * 31;
        ConeKotlinType coneKotlinType = this.receiverParameterType;
        return ((iHashCode + (coneKotlinType == null ? 0 : coneKotlinType.hashCode())) * 31) + this.contextParameterTypes.hashCode();
    }

    public String toString() {
        return "SyntheticPropertiesCacheKey(name=" + this.name + ", receiverParameterType=" + this.receiverParameterType + ", contextParameterTypes=" + this.contextParameterTypes + ')';
    }
}
