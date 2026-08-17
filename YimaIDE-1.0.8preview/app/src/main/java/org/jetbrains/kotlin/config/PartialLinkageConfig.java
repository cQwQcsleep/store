package org.jetbrains.kotlin.config;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010\u000f\u001a\u00020\u0010HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/config/PartialLinkageConfig;", Argument.Delimiters.none, "logLevel", "Lorg/jetbrains/kotlin/config/PartialLinkageLogLevel;", "<init>", "(Lorg/jetbrains/kotlin/config/PartialLinkageLogLevel;)V", "getLogLevel", "()Lorg/jetbrains/kotlin/config/PartialLinkageLogLevel;", "component1", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "Companion", "org.jetbrains.kotlin:util"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class PartialLinkageConfig {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final PartialLinkageConfig DEFAULT = new PartialLinkageConfig(PartialLinkageLogLevel.INSTANCE.getDEFAULT());
    private final PartialLinkageLogLevel logLevel;

    public PartialLinkageConfig(PartialLinkageLogLevel partialLinkageLogLevel) {
        partialLinkageLogLevel.getClass();
        this.logLevel = partialLinkageLogLevel;
    }

    public static /* synthetic */ PartialLinkageConfig copy$default(PartialLinkageConfig partialLinkageConfig, PartialLinkageLogLevel partialLinkageLogLevel, int i, Object obj) {
        if ((i & 1) != 0) {
            partialLinkageLogLevel = partialLinkageConfig.logLevel;
        }
        return partialLinkageConfig.copy(partialLinkageLogLevel);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final PartialLinkageLogLevel getLogLevel() {
        return this.logLevel;
    }

    public final PartialLinkageConfig copy(PartialLinkageLogLevel logLevel) {
        logLevel.getClass();
        return new PartialLinkageConfig(logLevel);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof PartialLinkageConfig) && this.logLevel == ((PartialLinkageConfig) other).logLevel;
    }

    public final PartialLinkageLogLevel getLogLevel() {
        return this.logLevel;
    }

    public int hashCode() {
        return this.logLevel.hashCode();
    }

    public String toString() {
        return "PartialLinkageConfig(logLevel=" + this.logLevel + ')';
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/config/PartialLinkageConfig$Companion;", Argument.Delimiters.none, "<init>", "()V", "DEFAULT", "Lorg/jetbrains/kotlin/config/PartialLinkageConfig;", "getDEFAULT", "()Lorg/jetbrains/kotlin/config/PartialLinkageConfig;", "org.jetbrains.kotlin:util"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final PartialLinkageConfig getDEFAULT() {
            return PartialLinkageConfig.DEFAULT;
        }

        private Companion() {
        }
    }
}
