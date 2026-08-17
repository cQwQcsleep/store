package org.jetbrains.kotlin.cli.pipeline;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.config.phaser.ActionState;
import org.jetbrains.kotlin.util.PhaseType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0014\bÆ\u0002\u0018\u00002\u00020\u0001:\u0011\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications;", Argument.Delimiters.none, "<init>", "()V", "InitializationStarted", "InitializationFinished", "AnalysisStarted", "AnalysisFinished", "TranslationToIrStarted", "TranslationToIrFinished", "IrPreLoweringStarted", "IrPreLoweringFinished", "IrSerializationStarted", "IrSerializationFinished", "KlibWritingStarted", "KlibWritingFinished", "IrLoweringStarted", "IrLoweringFinished", "BackendStarted", "BackendFinished", "AbstractNotification", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PerformanceNotifications {
    public static final PerformanceNotifications INSTANCE = new PerformanceNotifications();

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$AnalysisFinished;", "Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$AbstractNotification;", "<init>", "()V", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class AnalysisFinished extends AbstractNotification {
        public static final AnalysisFinished INSTANCE = new AnalysisFinished();

        private AnalysisFinished() {
            super(PhaseType.Analysis, false, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$AnalysisStarted;", "Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$AbstractNotification;", "<init>", "()V", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class AnalysisStarted extends AbstractNotification {
        public static final AnalysisStarted INSTANCE = new AnalysisStarted();

        private AnalysisStarted() {
            super(PhaseType.Analysis, true, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$BackendFinished;", "Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$AbstractNotification;", "<init>", "()V", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class BackendFinished extends AbstractNotification {
        public static final BackendFinished INSTANCE = new BackendFinished();

        private BackendFinished() {
            super(PhaseType.Backend, false, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$BackendStarted;", "Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$AbstractNotification;", "<init>", "()V", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class BackendStarted extends AbstractNotification {
        public static final BackendStarted INSTANCE = new BackendStarted();

        private BackendStarted() {
            super(PhaseType.Backend, true, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$InitializationFinished;", "Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$AbstractNotification;", "<init>", "()V", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class InitializationFinished extends AbstractNotification {
        public static final InitializationFinished INSTANCE = new InitializationFinished();

        private InitializationFinished() {
            super(PhaseType.Initialization, false, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$InitializationStarted;", "Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$AbstractNotification;", "<init>", "()V", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class InitializationStarted extends AbstractNotification {
        public static final InitializationStarted INSTANCE = new InitializationStarted();

        private InitializationStarted() {
            super(PhaseType.Initialization, true, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$IrLoweringFinished;", "Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$AbstractNotification;", "<init>", "()V", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class IrLoweringFinished extends AbstractNotification {
        public static final IrLoweringFinished INSTANCE = new IrLoweringFinished();

        private IrLoweringFinished() {
            super(PhaseType.IrLowering, false, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$IrLoweringStarted;", "Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$AbstractNotification;", "<init>", "()V", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class IrLoweringStarted extends AbstractNotification {
        public static final IrLoweringStarted INSTANCE = new IrLoweringStarted();

        private IrLoweringStarted() {
            super(PhaseType.IrLowering, true, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$IrPreLoweringFinished;", "Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$AbstractNotification;", "<init>", "()V", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class IrPreLoweringFinished extends AbstractNotification {
        public static final IrPreLoweringFinished INSTANCE = new IrPreLoweringFinished();

        private IrPreLoweringFinished() {
            super(PhaseType.IrPreLowering, false, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$IrPreLoweringStarted;", "Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$AbstractNotification;", "<init>", "()V", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class IrPreLoweringStarted extends AbstractNotification {
        public static final IrPreLoweringStarted INSTANCE = new IrPreLoweringStarted();

        private IrPreLoweringStarted() {
            super(PhaseType.IrPreLowering, true, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$IrSerializationFinished;", "Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$AbstractNotification;", "<init>", "()V", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class IrSerializationFinished extends AbstractNotification {
        public static final IrSerializationFinished INSTANCE = new IrSerializationFinished();

        private IrSerializationFinished() {
            super(PhaseType.IrSerialization, false, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$IrSerializationStarted;", "Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$AbstractNotification;", "<init>", "()V", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class IrSerializationStarted extends AbstractNotification {
        public static final IrSerializationStarted INSTANCE = new IrSerializationStarted();

        private IrSerializationStarted() {
            super(PhaseType.IrSerialization, true, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$KlibWritingFinished;", "Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$AbstractNotification;", "<init>", "()V", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class KlibWritingFinished extends AbstractNotification {
        public static final KlibWritingFinished INSTANCE = new KlibWritingFinished();

        private KlibWritingFinished() {
            super(PhaseType.KlibWriting, false, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$KlibWritingStarted;", "Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$AbstractNotification;", "<init>", "()V", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class KlibWritingStarted extends AbstractNotification {
        public static final KlibWritingStarted INSTANCE = new KlibWritingStarted();

        private KlibWritingStarted() {
            super(PhaseType.KlibWriting, true, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$TranslationToIrFinished;", "Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$AbstractNotification;", "<init>", "()V", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class TranslationToIrFinished extends AbstractNotification {
        public static final TranslationToIrFinished INSTANCE = new TranslationToIrFinished();

        private TranslationToIrFinished() {
            super(PhaseType.TranslationToIr, false, null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$TranslationToIrStarted;", "Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$AbstractNotification;", "<init>", "()V", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class TranslationToIrStarted extends AbstractNotification {
        public static final TranslationToIrStarted INSTANCE = new TranslationToIrStarted();

        private TranslationToIrStarted() {
            super(PhaseType.TranslationToIr, true, null);
        }
    }

    private PerformanceNotifications() {
    }

    @Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002*\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0001j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004`\u0006B\u0019\b\u0004\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\"\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0004H\u0096\u0082\u0004R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u0082\u0001\u0010\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#$¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$AbstractNotification;", "Lkotlin/Function3;", "Lorg/jetbrains/kotlin/config/phaser/ActionState;", "Lorg/jetbrains/kotlin/cli/pipeline/PipelineArtifact;", "Lorg/jetbrains/kotlin/cli/pipeline/PipelineContext;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/config/phaser/Action;", "phaseType", "Lorg/jetbrains/kotlin/util/PhaseType;", "start", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/util/PhaseType;Z)V", "getPhaseType", "()Lorg/jetbrains/kotlin/util/PhaseType;", "getStart", "()Z", "invoke", "state", "input", "c", "Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$AnalysisFinished;", "Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$AnalysisStarted;", "Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$BackendFinished;", "Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$BackendStarted;", "Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$InitializationFinished;", "Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$InitializationStarted;", "Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$IrLoweringFinished;", "Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$IrLoweringStarted;", "Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$IrPreLoweringFinished;", "Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$IrPreLoweringStarted;", "Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$IrSerializationFinished;", "Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$IrSerializationStarted;", "Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$KlibWritingFinished;", "Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$KlibWritingStarted;", "Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$TranslationToIrFinished;", "Lorg/jetbrains/kotlin/cli/pipeline/PerformanceNotifications$TranslationToIrStarted;", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static abstract class AbstractNotification implements Function3<ActionState, PipelineArtifact, PipelineContext, Unit> {
        private final PhaseType phaseType;
        private final boolean start;

        private AbstractNotification(PhaseType phaseType, boolean z) {
            this.phaseType = phaseType;
            this.start = z;
        }

        public final PhaseType getPhaseType() {
            return this.phaseType;
        }

        public final boolean getStart() {
            return this.start;
        }

        public void invoke(ActionState state, PipelineArtifact input, PipelineContext c) {
            state.getClass();
            input.getClass();
            c.getClass();
            if (this.start) {
                c.getPerformanceManager().notifyPhaseStarted(this.phaseType);
            } else {
                c.getPerformanceManager().notifyPhaseFinished(this.phaseType);
            }
        }

        public /* synthetic */ AbstractNotification(PhaseType phaseType, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
            this(phaseType, z);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((ActionState) obj, (PipelineArtifact) obj2, (PipelineContext) obj3);
            return Unit.INSTANCE;
        }
    }
}
