package org.jetbrains.kotlin.config;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/config/LanguageFeatureBehaviorAfterSinceVersion;", Argument.Delimiters.none, "<init>", "()V", "CannotBeDisabled", "CanStillBeDisabledForNow", "Lorg/jetbrains/kotlin/config/LanguageFeatureBehaviorAfterSinceVersion$CanStillBeDisabledForNow;", "Lorg/jetbrains/kotlin/config/LanguageFeatureBehaviorAfterSinceVersion$CannotBeDisabled;", "org.jetbrains.kotlin:language.version-settings"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class LanguageFeatureBehaviorAfterSinceVersion {

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/config/LanguageFeatureBehaviorAfterSinceVersion$CanStillBeDisabledForNow;", "Lorg/jetbrains/kotlin/config/LanguageFeatureBehaviorAfterSinceVersion;", "relevantTicketId", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;)V", "getRelevantTicketId", "()Ljava/lang/String;", "component1", "copy", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", "org.jetbrains.kotlin:language.version-settings"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class CanStillBeDisabledForNow extends LanguageFeatureBehaviorAfterSinceVersion {
        private final String relevantTicketId;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CanStillBeDisabledForNow(String str) {
            super(null);
            str.getClass();
            this.relevantTicketId = str;
        }

        public static /* synthetic */ CanStillBeDisabledForNow copy$default(CanStillBeDisabledForNow canStillBeDisabledForNow, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = canStillBeDisabledForNow.relevantTicketId;
            }
            return canStillBeDisabledForNow.copy(str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getRelevantTicketId() {
            return this.relevantTicketId;
        }

        public final CanStillBeDisabledForNow copy(String relevantTicketId) {
            relevantTicketId.getClass();
            return new CanStillBeDisabledForNow(relevantTicketId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof CanStillBeDisabledForNow) && Intrinsics.areEqual(this.relevantTicketId, ((CanStillBeDisabledForNow) other).relevantTicketId);
        }

        public final String getRelevantTicketId() {
            return this.relevantTicketId;
        }

        public int hashCode() {
            return this.relevantTicketId.hashCode();
        }

        public String toString() {
            return "CanStillBeDisabledForNow(relevantTicketId=" + this.relevantTicketId + ')';
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/config/LanguageFeatureBehaviorAfterSinceVersion$CannotBeDisabled;", "Lorg/jetbrains/kotlin/config/LanguageFeatureBehaviorAfterSinceVersion;", "<init>", "()V", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:language.version-settings"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class CannotBeDisabled extends LanguageFeatureBehaviorAfterSinceVersion {
        public static final CannotBeDisabled INSTANCE = new CannotBeDisabled();

        private CannotBeDisabled() {
            super(null);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof CannotBeDisabled);
        }

        public int hashCode() {
            return 385222341;
        }

        public String toString() {
            return "CannotBeDisabled";
        }
    }

    public /* synthetic */ LanguageFeatureBehaviorAfterSinceVersion(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private LanguageFeatureBehaviorAfterSinceVersion() {
    }
}
