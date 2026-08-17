package org.jetbrains.kotlin.ir.overrides;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001:\u0002\u000b\fJ\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&R\u0012\u0010\u0007\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/ir/overrides/IrExternalOverridabilityCondition;", "", "isOverridable", "Lorg/jetbrains/kotlin/ir/overrides/IrExternalOverridabilityCondition$Result;", "superMember", "Lorg/jetbrains/kotlin/ir/overrides/MemberWithOriginal;", "subMember", "contract", "Lorg/jetbrains/kotlin/ir/overrides/IrExternalOverridabilityCondition$Contract;", "getContract", "()Lorg/jetbrains/kotlin/ir/overrides/IrExternalOverridabilityCondition$Contract;", "Result", "Contract", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface IrExternalOverridabilityCondition {

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/ir/overrides/IrExternalOverridabilityCondition$Contract;", "", "<init>", "(Ljava/lang/String;I)V", "CONFLICTS_ONLY", "SUCCESS_ONLY", "BOTH", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public enum Contract {
        CONFLICTS_ONLY,
        SUCCESS_ONLY,
        BOTH;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<Contract> getEntries() {
            return $ENTRIES;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/ir/overrides/IrExternalOverridabilityCondition$Result;", "", "<init>", "(Ljava/lang/String;I)V", "OVERRIDABLE", "INCOMPATIBLE", "UNKNOWN", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public enum Result {
        OVERRIDABLE,
        INCOMPATIBLE,
        UNKNOWN;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<Result> getEntries() {
            return $ENTRIES;
        }
    }

    Contract getContract();

    Result isOverridable(MemberWithOriginal superMember, MemberWithOriginal subMember);
}
