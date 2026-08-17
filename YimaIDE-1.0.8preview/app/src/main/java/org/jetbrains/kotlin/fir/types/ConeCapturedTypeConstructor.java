package org.jetbrains.kotlin.fir.types;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.types.model.CaptureStatus;
import org.jetbrains.kotlin.types.model.CapturedTypeConstructorMarker;
import org.jetbrains.kotlin.types.model.TypeParameterMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u00002\u00020\u00012\u00020\u0002B?\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\r\u0010\u000eR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\"\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/ConeCapturedTypeConstructor;", "Lorg/jetbrains/kotlin/types/model/CapturedTypeConstructorMarker;", "Lorg/jetbrains/kotlin/fir/types/ConeTypeConstructorMarker;", "projection", "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "lowerType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "captureStatus", "Lorg/jetbrains/kotlin/types/model/CaptureStatus;", "supertypes", Argument.Delimiters.none, "typeParameterMarker", "Lorg/jetbrains/kotlin/types/model/TypeParameterMarker;", "<init>", "(Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/types/model/CaptureStatus;Ljava/util/List;Lorg/jetbrains/kotlin/types/model/TypeParameterMarker;)V", "getProjection", "()Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "getLowerType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getCaptureStatus", "()Lorg/jetbrains/kotlin/types/model/CaptureStatus;", "getSupertypes", "()Ljava/util/List;", "setSupertypes", "(Ljava/util/List;)V", "getTypeParameterMarker", "()Lorg/jetbrains/kotlin/types/model/TypeParameterMarker;", "org.jetbrains.kotlin:cones"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeCapturedTypeConstructor implements ConeTypeConstructorMarker, CapturedTypeConstructorMarker {
    private final CaptureStatus captureStatus;
    private final ConeKotlinType lowerType;
    private final ConeTypeProjection projection;
    private List<? extends ConeKotlinType> supertypes;
    private final TypeParameterMarker typeParameterMarker;

    public ConeCapturedTypeConstructor(ConeTypeProjection coneTypeProjection, ConeKotlinType coneKotlinType, CaptureStatus captureStatus, List<? extends ConeKotlinType> list, TypeParameterMarker typeParameterMarker) {
        coneTypeProjection.getClass();
        captureStatus.getClass();
        this.projection = coneTypeProjection;
        this.lowerType = coneKotlinType;
        this.captureStatus = captureStatus;
        this.supertypes = list;
        this.typeParameterMarker = typeParameterMarker;
    }

    public final CaptureStatus getCaptureStatus() {
        return this.captureStatus;
    }

    public final ConeKotlinType getLowerType() {
        return this.lowerType;
    }

    public final ConeTypeProjection getProjection() {
        return this.projection;
    }

    public final List<ConeKotlinType> getSupertypes() {
        return this.supertypes;
    }

    public final TypeParameterMarker getTypeParameterMarker() {
        return this.typeParameterMarker;
    }

    public final void setSupertypes(List<? extends ConeKotlinType> list) {
        this.supertypes = list;
    }

    public /* synthetic */ ConeCapturedTypeConstructor(ConeTypeProjection coneTypeProjection, ConeKotlinType coneKotlinType, CaptureStatus captureStatus, List list, TypeParameterMarker typeParameterMarker, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(coneTypeProjection, coneKotlinType, captureStatus, (i & 8) != 0 ? null : list, (i & 16) != 0 ? null : typeParameterMarker);
    }
}
