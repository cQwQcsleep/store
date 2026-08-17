package org.jetbrains.kotlin.fir.expressions;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.WhenMissingCase;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0004\b\t\n\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/ExhaustivenessStatus;", Argument.Delimiters.none, "<init>", "()V", "ProperlyExhaustive", "RedundantlyExhaustive", "ExhaustiveAsNothing", "NotExhaustive", "Lorg/jetbrains/kotlin/fir/expressions/ExhaustivenessStatus$ExhaustiveAsNothing;", "Lorg/jetbrains/kotlin/fir/expressions/ExhaustivenessStatus$NotExhaustive;", "Lorg/jetbrains/kotlin/fir/expressions/ExhaustivenessStatus$ProperlyExhaustive;", "Lorg/jetbrains/kotlin/fir/expressions/ExhaustivenessStatus$RedundantlyExhaustive;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class ExhaustivenessStatus {

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/ExhaustivenessStatus$ExhaustiveAsNothing;", "Lorg/jetbrains/kotlin/fir/expressions/ExhaustivenessStatus;", "<init>", "()V", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class ExhaustiveAsNothing extends ExhaustivenessStatus {
        public static final ExhaustiveAsNothing INSTANCE = new ExhaustiveAsNothing();

        private ExhaustiveAsNothing() {
            super(null);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof ExhaustiveAsNothing);
        }

        public int hashCode() {
            return -434596301;
        }

        public String toString() {
            return "ExhaustiveAsNothing";
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/ExhaustivenessStatus$ProperlyExhaustive;", "Lorg/jetbrains/kotlin/fir/expressions/ExhaustivenessStatus;", "<init>", "()V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ProperlyExhaustive extends ExhaustivenessStatus {
        public static final ProperlyExhaustive INSTANCE = new ProperlyExhaustive();

        private ProperlyExhaustive() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/ExhaustivenessStatus$RedundantlyExhaustive;", "Lorg/jetbrains/kotlin/fir/expressions/ExhaustivenessStatus;", "<init>", "()V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class RedundantlyExhaustive extends ExhaustivenessStatus {
        public static final RedundantlyExhaustive INSTANCE = new RedundantlyExhaustive();

        private RedundantlyExhaustive() {
            super(null);
        }
    }

    public /* synthetic */ ExhaustivenessStatus(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u001f\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/ExhaustivenessStatus$NotExhaustive;", "Lorg/jetbrains/kotlin/fir/expressions/ExhaustivenessStatus;", "reasons", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase;", "subjectType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "<init>", "(Ljava/util/List;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "getReasons", "()Ljava/util/List;", "getSubjectType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Companion", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class NotExhaustive extends ExhaustivenessStatus {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final List<WhenMissingCase> NO_ELSE_BRANCH_REASONS = CollectionsKt.listOf(WhenMissingCase.Unknown.INSTANCE);
        private final List<WhenMissingCase> reasons;
        private final ConeKotlinType subjectType;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public NotExhaustive(List<? extends WhenMissingCase> list, ConeKotlinType coneKotlinType) {
            super(null);
            list.getClass();
            this.reasons = list;
            this.subjectType = coneKotlinType;
        }

        public final List<WhenMissingCase> getReasons() {
            return this.reasons;
        }

        public final ConeKotlinType getSubjectType() {
            return this.subjectType;
        }

        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/ExhaustivenessStatus$NotExhaustive$Companion;", Argument.Delimiters.none, "<init>", "()V", "NO_ELSE_BRANCH_REASONS", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase;", "getNO_ELSE_BRANCH_REASONS", "()Ljava/util/List;", "noElseBranch", "Lorg/jetbrains/kotlin/fir/expressions/ExhaustivenessStatus$NotExhaustive;", "subjectType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final List<WhenMissingCase> getNO_ELSE_BRANCH_REASONS() {
                return NotExhaustive.NO_ELSE_BRANCH_REASONS;
            }

            public final NotExhaustive noElseBranch(ConeKotlinType subjectType) {
                return new NotExhaustive(getNO_ELSE_BRANCH_REASONS(), subjectType);
            }

            private Companion() {
            }
        }
    }

    private ExhaustivenessStatus() {
    }
}
