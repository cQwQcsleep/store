package org.jetbrains.kotlin.backend.common.linkage.partial;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.diagnostics.KtSourcelessDiagnosticFactory;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0081\u0002\u0018\u0000 \b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\bB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0006\u001a\u00020\u0007j\u0002\b\u0004j\u0002\b\u0005¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/linkage/partial/PartialLinkageIssueSignificance;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/String;I)V", "MINOR", "MAJOR", "toDiagnosticFactory", "Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", "Companion", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public enum PartialLinkageIssueSignificance {
    MINOR,
    MAJOR;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PartialLinkageIssueSignificance.values().length];
            try {
                iArr[PartialLinkageIssueSignificance.MINOR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[PartialLinkageIssueSignificance.MAJOR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static EnumEntries<PartialLinkageIssueSignificance> getEntries() {
        return $ENTRIES;
    }

    public final KtSourcelessDiagnosticFactory toDiagnosticFactory() {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            return PartialLinkageDiagnostics.INSTANCE.getMINOR_PARTIAL_LINKAGE_ISSUE();
        }
        if (i == 2) {
            return PartialLinkageDiagnostics.INSTANCE.getMAJOR_PARTIAL_LINKAGE_ISSUE();
        }
        bu8.a();
        return null;
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/linkage/partial/PartialLinkageIssueSignificance$Companion;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "minorIf", "Lorg/jetbrains/kotlin/backend/common/linkage/partial/PartialLinkageIssueSignificance;", "condition", "Lkotlin/Function0;", "", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final PartialLinkageIssueSignificance minorIf(Function0<Boolean> condition) {
            condition.getClass();
            return ((Boolean) condition.invoke()).booleanValue() ? PartialLinkageIssueSignificance.MINOR : PartialLinkageIssueSignificance.MAJOR;
        }

        private Companion() {
        }
    }
}
