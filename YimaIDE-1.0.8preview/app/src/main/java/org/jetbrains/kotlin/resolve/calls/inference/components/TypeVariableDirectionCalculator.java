package org.jetbrains.kotlin.resolve.calls.inference.components;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0004B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/resolve/calls/inference/components/TypeVariableDirectionCalculator;", "", "<init>", "()V", "ResolveDirection", "org.jetbrains.kotlin:resolution.common"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final class TypeVariableDirectionCalculator {
    public static final TypeVariableDirectionCalculator INSTANCE = new TypeVariableDirectionCalculator();

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/resolve/calls/inference/components/TypeVariableDirectionCalculator$ResolveDirection;", "", "<init>", "(Ljava/lang/String;I)V", "TO_SUBTYPE", "TO_SUPERTYPE", "UNKNOWN", "org.jetbrains.kotlin:resolution.common"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
    public enum ResolveDirection {
        TO_SUBTYPE,
        TO_SUPERTYPE,
        UNKNOWN;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<ResolveDirection> getEntries() {
            return $ENTRIES;
        }
    }

    private TypeVariableDirectionCalculator() {
    }
}
