package org.jetbrains.kotlin.serialization.deserialization;

import kotlin.Metadata;
import org.jetbrains.kotlin.types.SimpleType;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001:\u0001\u0006R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/serialization/deserialization/LocalClassifierTypeSettings;", "", "replacementTypeForLocalClassifiers", "Lorg/jetbrains/kotlin/types/SimpleType;", "getReplacementTypeForLocalClassifiers", "()Lorg/jetbrains/kotlin/types/SimpleType;", "Default", "org.jetbrains.kotlin:deserialization"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public interface LocalClassifierTypeSettings {

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/serialization/deserialization/LocalClassifierTypeSettings$Default;", "Lorg/jetbrains/kotlin/serialization/deserialization/LocalClassifierTypeSettings;", "<init>", "()V", "replacementTypeForLocalClassifiers", "Lorg/jetbrains/kotlin/types/SimpleType;", "getReplacementTypeForLocalClassifiers", "()Lorg/jetbrains/kotlin/types/SimpleType;", "org.jetbrains.kotlin:deserialization"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
    public static final class Default implements LocalClassifierTypeSettings {
        public static final Default INSTANCE = new Default();

        private Default() {
        }

        @Override // org.jetbrains.kotlin.serialization.deserialization.LocalClassifierTypeSettings
        public SimpleType getReplacementTypeForLocalClassifiers() {
            return null;
        }
    }

    SimpleType getReplacementTypeForLocalClassifiers();
}
