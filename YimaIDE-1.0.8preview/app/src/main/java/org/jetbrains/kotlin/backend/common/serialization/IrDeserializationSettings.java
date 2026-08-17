package org.jetbrains.kotlin.backend.common.serialization;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001:\u0001\u0010B/\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/IrDeserializationSettings;", "", "allowErrorNodes", "", "allowAlreadyBoundSymbols", "deserializeFunctionBodies", "Lorg/jetbrains/kotlin/backend/common/serialization/IrDeserializationSettings$DeserializeFunctionBodies;", "useNullableAnyAsAnnotationConstructorCallType", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(ZZLorg/jetbrains/kotlin/backend/common/serialization/IrDeserializationSettings$DeserializeFunctionBodies;Z)V", "getAllowErrorNodes", "()Z", "getAllowAlreadyBoundSymbols", "getDeserializeFunctionBodies", "()Lorg/jetbrains/kotlin/backend/common/serialization/IrDeserializationSettings$DeserializeFunctionBodies;", "getUseNullableAnyAsAnnotationConstructorCallType", "DeserializeFunctionBodies", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class IrDeserializationSettings {
    private final boolean allowAlreadyBoundSymbols;
    private final boolean allowErrorNodes;
    private final DeserializeFunctionBodies deserializeFunctionBodies;
    private final boolean useNullableAnyAsAnnotationConstructorCallType;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/IrDeserializationSettings$DeserializeFunctionBodies;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/String;I)V", "ALL", "ONLY_INLINE", "NONE", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public enum DeserializeFunctionBodies {
        ALL,
        ONLY_INLINE,
        NONE;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<DeserializeFunctionBodies> getEntries() {
            return $ENTRIES;
        }
    }

    public /* synthetic */ IrDeserializationSettings(boolean z, boolean z2, DeserializeFunctionBodies deserializeFunctionBodies, boolean z3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? false : z2, (i & 4) != 0 ? DeserializeFunctionBodies.ALL : deserializeFunctionBodies, (i & 8) != 0 ? false : z3);
    }

    public final boolean getAllowAlreadyBoundSymbols() {
        return this.allowAlreadyBoundSymbols;
    }

    public final boolean getAllowErrorNodes() {
        return this.allowErrorNodes;
    }

    public final DeserializeFunctionBodies getDeserializeFunctionBodies() {
        return this.deserializeFunctionBodies;
    }

    public final boolean getUseNullableAnyAsAnnotationConstructorCallType() {
        return this.useNullableAnyAsAnnotationConstructorCallType;
    }

    public IrDeserializationSettings(boolean z, boolean z2, DeserializeFunctionBodies deserializeFunctionBodies, boolean z3) {
        deserializeFunctionBodies.getClass();
        this.allowErrorNodes = z;
        this.allowAlreadyBoundSymbols = z2;
        this.deserializeFunctionBodies = deserializeFunctionBodies;
        this.useNullableAnyAsAnnotationConstructorCallType = z3;
    }

    public IrDeserializationSettings() {
        this(false, false, null, false, 15, null);
    }
}
