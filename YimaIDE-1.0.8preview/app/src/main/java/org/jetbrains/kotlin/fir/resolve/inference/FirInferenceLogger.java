package org.jetbrains.kotlin.fir.resolve.inference;

import defpackage.f2f;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.UninitializedPropertyAccessException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.Regex;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.K2JsArgumentConstants;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.resolve.calls.inference.components.ConstraintSystemMarker;
import org.jetbrains.kotlin.resolve.calls.inference.components.InferenceLogger;
import org.jetbrains.kotlin.resolve.calls.inference.components.LegacyVariableReadinessCalculator;
import org.jetbrains.kotlin.resolve.calls.inference.components.VariableReadinessCalculator;
import org.jetbrains.kotlin.resolve.calls.inference.model.Constraint;
import org.jetbrains.kotlin.resolve.calls.inference.model.ConstraintKind;
import org.jetbrains.kotlin.resolve.calls.inference.model.ConstraintSystemError;
import org.jetbrains.kotlin.resolve.calls.inference.model.InitialConstraint;
import org.jetbrains.kotlin.resolve.calls.inference.model.NewConstraintSystemImpl;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.kotlin.types.model.TypeVariableMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\r\b\u0016\u0018\u0000 [2\u00020\u00012\u00020\u0002:\fPQRSTUVWXYZ[B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000bH\u0002J\u0010\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000bH\u0002J\u0014\u0010\u0015\u001a\u00020\u0012*\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u000bH\u0002J\u0010\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\u0017H\u0002J\u0018\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001cH\u0002J\u000e\u0010!\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020#J\u0016\u0010$\u001a\u00020\u00122\u0006\u0010%\u001a\u00020&2\u0006\u0010\u0013\u001a\u00020\u000bJ\u0018\u0010*\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u0013\u001a\u00020\u000bH\u0016J \u0010+\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\u0013\u001a\u00020\u000bH\u0016J\u0018\u0010,\u001a\u00020\u00122\u0006\u0010-\u001a\u00020.2\u0006\u0010\u0013\u001a\u00020\u000bH\u0016J\u0018\u0010/\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u001b2\u0006\u0010\u0013\u001a\u00020\u000bH\u0016J\u0018\u00100\u001a\u00020\u00122\u0006\u00101\u001a\u0002022\u0006\u0010\u0013\u001a\u00020\u000bH\u0016J\u0014\u00103\u001a\u000204*\u0002022\u0006\u00105\u001a\u000202H\u0002J\u001e\u00103\u001a\u000204*\u0006\u0012\u0002\b\u0003062\f\u00107\u001a\b\u0012\u0002\b\u0003\u0018\u000106H\u0002J0\u0010?\u001a\u0002H@\"\u0004\b\u0000\u0010@2\f\u0010A\u001a\b\u0012\u0004\u0012\u00020\u00180;2\f\u0010B\u001a\b\u0012\u0004\u0012\u0002H@0CH\u0082\b¢\u0006\u0002\u0010DJ)\u0010E\u001a\u0002H@\"\u0004\b\u0000\u0010@2\u0006\u0010\u001e\u001a\u00020\u00172\f\u0010B\u001a\b\u0012\u0004\u0012\u0002H@0CH\u0016¢\u0006\u0002\u0010FJA\u0010G\u001a\u0002H@\"\u0004\b\u0000\u0010@2\u0006\u0010H\u001a\u00020\u001b2\u0006\u0010I\u001a\u00020\u001c2\u0006\u0010J\u001a\u00020\u001b2\u0006\u0010K\u001a\u00020\u001c2\f\u0010B\u001a\b\u0012\u0004\u0012\u0002H@0CH\u0016¢\u0006\u0002\u0010LJ \u0010M\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u001b2\u0006\u0010N\u001a\u00020O2\u0006\u0010\u0013\u001a\u00020\u000bH\u0016R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00070\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00100\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u0019\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001c0\u001a\u0012\u0004\u0012\u00020\u00180\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010'\u001a\b\u0012\u0004\u0012\u00020(0\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b)\u0010\tR\u001c\u00108\u001a\u000204*\u0006\u0012\u0002\b\u0003068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b8\u00109R \u0010:\u001a\b\u0012\u0004\u0012\u00020\u00180;X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010\t\"\u0004\b=\u0010>¨\u0006\\"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceLogger;", "Lorg/jetbrains/kotlin/resolve/calls/inference/components/InferenceLogger;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "<init>", "()V", "topLevelElements", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceLogger$BlockElement;", "getTopLevelElements", "()Ljava/util/List;", "currentSystem", "Lorg/jetbrains/kotlin/resolve/calls/inference/components/ConstraintSystemMarker;", "currentBlock", "systemToKnownBlock", Argument.Delimiters.none, "systemToCandidate", "Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceLogger$BlockOwner$Candidate;", "prepareProperBlock", Argument.Delimiters.none, "system", "updateCurrentSystem", "register", "initialConstraintToKnownElement", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/InitialConstraint;", "Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceLogger$ConstraintElement;", "variableConstraintToKnownElement", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/types/model/TypeVariableMarker;", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/Constraint;", "cachedElementFor", "constraint", "variable", "currentCandidate", "logCandidate", "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "logStage", ModuleXmlParser.NAME, Argument.Delimiters.none, "currentBlockItemElements", "Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceLogger$BlockItemElement;", "getCurrentBlockItemElements", "logInitial", K2JsArgumentConstants.RUNTIME_DIAGNOSTIC_LOG, "logError", "error", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/ConstraintSystemError;", "logNewVariable", "logReadiness", "fixationLog", "Lorg/jetbrains/kotlin/resolve/calls/inference/components/InferenceLogger$FixationLogRecord;", "isSimilarTo", Argument.Delimiters.none, "record", "Lorg/jetbrains/kotlin/resolve/calls/inference/components/InferenceLogger$FixationLogVariableInfo;", "info", "isForbiddenReadiness", "(Lorg/jetbrains/kotlin/resolve/calls/inference/components/InferenceLogger$FixationLogVariableInfo;)Z", "origins", Argument.Delimiters.none, "getOrigins", "setOrigins", "(Ljava/util/List;)V", "withOriginatingElements", "T", "elements", "block", "Lkotlin/Function0;", "(Ljava/util/List;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withOrigin", "(Lorg/jetbrains/kotlin/resolve/calls/inference/model/InitialConstraint;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withOrigins", "variable1", "constraint1", "variable2", "constraint2", "(Lorg/jetbrains/kotlin/types/model/TypeVariableMarker;Lorg/jetbrains/kotlin/resolve/calls/inference/model/Constraint;Lorg/jetbrains/kotlin/types/model/TypeVariableMarker;Lorg/jetbrains/kotlin/resolve/calls/inference/model/Constraint;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "logFixVariable", "resultType", "Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "LoggingElement", "Call", "BlockOwner", "BlockElement", "BlockItemElement", "NewVariableElement", "ErrorElement", "ConstraintElement", "InitialConstraintElement", "VariableConstraintElement", "FixationLogRecordElement", "Companion", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class FirInferenceLogger extends InferenceLogger implements FirSessionComponent {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Regex fqNameRegex = new Regex("(?:\\w+\\.)*(\\w+)@\\w+");
    private BlockElement currentBlock;
    private BlockOwner.Candidate currentCandidate;
    private ConstraintSystemMarker currentSystem;
    private final List<BlockElement> topLevelElements = new ArrayList();
    private final Map<ConstraintSystemMarker, BlockElement> systemToKnownBlock = new LinkedHashMap();
    private final Map<ConstraintSystemMarker, BlockOwner.Candidate> systemToCandidate = new LinkedHashMap();
    private final Map<InitialConstraint, ConstraintElement> initialConstraintToKnownElement = new LinkedHashMap();
    private final Map<Pair<TypeVariableMarker, Constraint>, ConstraintElement> variableConstraintToKnownElement = new LinkedHashMap();
    private List<? extends ConstraintElement> origins = CollectionsKt.emptyList();

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceLogger$ErrorElement;", "Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceLogger$BlockItemElement;", "error", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/ConstraintSystemError;", "<init>", "(Lorg/jetbrains/kotlin/resolve/calls/inference/model/ConstraintSystemError;)V", "getError", "()Lorg/jetbrains/kotlin/resolve/calls/inference/model/ConstraintSystemError;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ErrorElement extends BlockItemElement {
        private final ConstraintSystemError error;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ErrorElement(ConstraintSystemError constraintSystemError) {
            super(null);
            constraintSystemError.getClass();
            this.error = constraintSystemError;
        }

        public final ConstraintSystemError getError() {
            return this.error;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceLogger$FixationLogRecordElement;", "Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceLogger$BlockItemElement;", "record", "Lorg/jetbrains/kotlin/resolve/calls/inference/components/InferenceLogger$FixationLogRecord;", "<init>", "(Lorg/jetbrains/kotlin/resolve/calls/inference/components/InferenceLogger$FixationLogRecord;)V", "getRecord", "()Lorg/jetbrains/kotlin/resolve/calls/inference/components/InferenceLogger$FixationLogRecord;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class FixationLogRecordElement extends BlockItemElement {
        private final InferenceLogger.FixationLogRecord record;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FixationLogRecordElement(InferenceLogger.FixationLogRecord fixationLogRecord) {
            super(null);
            fixationLogRecord.getClass();
            this.record = fixationLogRecord;
        }

        public final InferenceLogger.FixationLogRecord getRecord() {
            return this.record;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceLogger$InitialConstraintElement;", "Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceLogger$ConstraintElement;", "constraint", Argument.Delimiters.none, "position", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getConstraint", "()Ljava/lang/String;", "getPosition", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class InitialConstraintElement extends ConstraintElement {
        private final String constraint;
        private final String position;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public InitialConstraintElement(String str, String str2) {
            super(CollectionsKt.emptyList(), null);
            str.getClass();
            str2.getClass();
            this.constraint = str;
            this.position = str2;
        }

        public final String getConstraint() {
            return this.constraint;
        }

        public final String getPosition() {
            return this.position;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceLogger$NewVariableElement;", "Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceLogger$BlockItemElement;", "variable", "Lorg/jetbrains/kotlin/types/model/TypeVariableMarker;", "<init>", "(Lorg/jetbrains/kotlin/types/model/TypeVariableMarker;)V", "getVariable", "()Lorg/jetbrains/kotlin/types/model/TypeVariableMarker;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class NewVariableElement extends BlockItemElement {
        private final TypeVariableMarker variable;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NewVariableElement(TypeVariableMarker typeVariableMarker) {
            super(null);
            typeVariableMarker.getClass();
            this.variable = typeVariableMarker;
        }

        public final TypeVariableMarker getVariable() {
            return this.variable;
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceLogger$VariableConstraintElement;", "Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceLogger$ConstraintElement;", "constraint", Argument.Delimiters.none, "origins", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;Ljava/util/List;)V", "getConstraint", "()Ljava/lang/String;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class VariableConstraintElement extends ConstraintElement {
        private final String constraint;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public VariableConstraintElement(String str, List<? extends ConstraintElement> list) {
            super(list, null);
            str.getClass();
            list.getClass();
            this.constraint = str;
        }

        public final String getConstraint() {
            return this.constraint;
        }
    }

    private final ConstraintElement cachedElementFor(TypeVariableMarker variable, Constraint constraint) {
        ConstraintElement constraintElement = this.variableConstraintToKnownElement.get(TuplesKt.to(variable, constraint));
        if (constraintElement != null) {
            return constraintElement;
        }
        a11.a("This constraint has not yet been logged: ", variable, " with ", constraint);
        return null;
    }

    @JvmStatic
    public static final String formatConstraint(InitialConstraint initialConstraint) {
        return INSTANCE.formatConstraint(initialConstraint);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    private final List<BlockItemElement> getCurrentBlockItemElements() throws UninitializedPropertyAccessException {
        BlockElement blockElement = this.currentBlock;
        if (blockElement == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentBlock");
            blockElement = null;
        }
        return blockElement.getItems();
    }

    private final boolean isForbiddenReadiness(InferenceLogger.FixationLogVariableInfo<?> fixationLogVariableInfo) {
        Object readiness = fixationLogVariableInfo.getReadiness();
        if (readiness instanceof VariableReadinessCalculator.TypeVariableFixationReadiness) {
            return !((VariableReadinessCalculator.TypeVariableFixationReadiness) readiness).get(VariableReadinessCalculator.TypeVariableFixationReadinessQuality.ALLOWED);
        }
        if (readiness instanceof LegacyVariableReadinessCalculator.TypeVariableFixationReadiness) {
            return readiness == LegacyVariableReadinessCalculator.TypeVariableFixationReadiness.FORBIDDEN;
        }
        f2f.a("Unexpected readiness type: ", Reflection.getOrCreateKotlinClass(readiness.getClass()));
        return false;
    }

    private final boolean isSimilarTo(InferenceLogger.FixationLogRecord fixationLogRecord, InferenceLogger.FixationLogRecord fixationLogRecord2) {
        if (fixationLogRecord2.getChosen() != fixationLogRecord.getChosen() || fixationLogRecord2.getMap().size() != fixationLogRecord.getMap().size()) {
            return false;
        }
        for (Map.Entry entry : fixationLogRecord2.getMap().entrySet()) {
            if (!isSimilarTo((InferenceLogger.FixationLogVariableInfo<?>) entry.getValue(), (InferenceLogger.FixationLogVariableInfo<?>) fixationLogRecord.getMap().get((TypeVariableMarker) entry.getKey()))) {
                return false;
            }
        }
        return true;
    }

    private final void prepareProperBlock(ConstraintSystemMarker system) {
        if (Intrinsics.areEqual(system, this.currentSystem)) {
            return;
        }
        updateCurrentSystem(system);
        BlockElement blockElement = this.systemToKnownBlock.get(system);
        if (blockElement == null || this.currentCandidate == null) {
            k2d.a("UNKNOWN SYSTEM");
            return;
        }
        String str = "Continue " + blockElement.getName();
        BlockOwner blockOwner = this.currentCandidate;
        if (blockOwner == null) {
            blockOwner = BlockOwner.Unknown.INSTANCE;
        }
        BlockElement blockElement2 = new BlockElement(str, null, blockOwner, 2, null);
        register(blockElement2, system);
        this.currentBlock = blockElement2;
    }

    private final void register(BlockElement blockElement, ConstraintSystemMarker constraintSystemMarker) {
        List<BlockItemElement> items;
        BlockElement blockElement2 = (BlockElement) CollectionsKt.lastOrNull(this.topLevelElements);
        if (blockElement2 != null && (items = blockElement2.getItems()) != null && items.isEmpty()) {
            CollectionsKt.removeLast(this.topLevelElements);
        }
        this.systemToKnownBlock.put(constraintSystemMarker, blockElement);
        this.topLevelElements.add(blockElement);
    }

    @JvmStatic
    public static final String sanitizeFqNames(String str) {
        return INSTANCE.sanitizeFqNames(str);
    }

    private final void updateCurrentSystem(ConstraintSystemMarker system) {
        if (Intrinsics.areEqual(system, this.currentSystem)) {
            return;
        }
        this.currentSystem = system;
        this.currentCandidate = this.systemToCandidate.get(system);
    }

    public final List<ConstraintElement> getOrigins() {
        return this.origins;
    }

    public final List<BlockElement> getTopLevelElements() {
        return this.topLevelElements;
    }

    public void log(TypeVariableMarker variable, Constraint constraint, ConstraintSystemMarker system) {
        variable.getClass();
        constraint.getClass();
        system.getClass();
        prepareProperBlock(system);
        VariableConstraintElement variableConstraintElement = new VariableConstraintElement(formatConstraint(variable, constraint), this.origins);
        this.variableConstraintToKnownElement.putIfAbsent(TuplesKt.to(variable, constraint), variableConstraintElement);
        getCurrentBlockItemElements().add(variableConstraintElement);
    }

    public final void logCandidate(Candidate candidate) {
        candidate.getClass();
        BlockOwner.Candidate candidate2 = this.currentCandidate;
        if ((candidate2 != null ? candidate2.getCandidate() : null) != candidate) {
            BlockOwner.Candidate candidate3 = new BlockOwner.Candidate(candidate);
            this.systemToCandidate.put(candidate.getSystem(), candidate3);
            this.currentCandidate = candidate3;
            this.currentSystem = candidate.getSystem();
        }
    }

    public void logError(ConstraintSystemError error, ConstraintSystemMarker system) {
        error.getClass();
        system.getClass();
        prepareProperBlock(system);
        getCurrentBlockItemElements().add(new ErrorElement(error));
    }

    public void logFixVariable(TypeVariableMarker variable, KotlinTypeMarker resultType, ConstraintSystemMarker system) {
        InferenceLogger.FixationLogVariableInfo<?> fixationLogVariableInfo;
        Candidate candidate;
        variable.getClass();
        resultType.getClass();
        system.getClass();
        prepareProperBlock(system);
        List<BlockElement> list = this.topLevelElements;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            BlockOwner owner = ((BlockElement) obj).getOwner();
            NewConstraintSystemImpl system2 = null;
            BlockOwner.Candidate candidate2 = owner instanceof BlockOwner.Candidate ? (BlockOwner.Candidate) owner : null;
            if (candidate2 != null && (candidate = candidate2.getCandidate()) != null) {
                system2 = candidate.getSystem();
            }
            if (Intrinsics.areEqual(system2, system)) {
                arrayList.add(obj);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            for (BlockItemElement blockItemElement : ((BlockElement) it.next()).getItems()) {
                if (blockItemElement instanceof FixationLogRecordElement) {
                    InferenceLogger.FixationLogRecord record = ((FixationLogRecordElement) blockItemElement).getRecord();
                    if (record.getChosen() == variable && ((fixationLogVariableInfo = (InferenceLogger.FixationLogVariableInfo) record.getMap().get(variable)) == null || !isForbiddenReadiness(fixationLogVariableInfo))) {
                        record.setFixedTo(resultType);
                    }
                }
            }
        }
    }

    public void logInitial(InitialConstraint constraint, ConstraintSystemMarker system) {
        constraint.getClass();
        system.getClass();
        prepareProperBlock(system);
        InitialConstraintElement initialConstraintElement = new InitialConstraintElement(formatConstraint(constraint), INSTANCE.sanitizeFqNames(constraint.getPosition().toString()));
        this.initialConstraintToKnownElement.putIfAbsent(constraint, initialConstraintElement);
        getCurrentBlockItemElements().add(initialConstraintElement);
    }

    public void logNewVariable(TypeVariableMarker variable, ConstraintSystemMarker system) {
        variable.getClass();
        system.getClass();
        prepareProperBlock(system);
        getCurrentBlockItemElements().add(new NewVariableElement(variable));
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public void logReadiness(InferenceLogger.FixationLogRecord fixationLog, ConstraintSystemMarker system) throws UninitializedPropertyAccessException {
        fixationLog.getClass();
        system.getClass();
        prepareProperBlock(system);
        List<BlockItemElement> currentBlockItemElements = getCurrentBlockItemElements();
        ArrayList arrayList = new ArrayList();
        for (BlockItemElement blockItemElement : currentBlockItemElements) {
            FixationLogRecordElement fixationLogRecordElement = blockItemElement instanceof FixationLogRecordElement ? (FixationLogRecordElement) blockItemElement : null;
            InferenceLogger.FixationLogRecord record = fixationLogRecordElement != null ? fixationLogRecordElement.getRecord() : null;
            if (record != null) {
                arrayList.add(record);
            }
        }
        if (arrayList.isEmpty() || !isSimilarTo((InferenceLogger.FixationLogRecord) CollectionsKt.last(arrayList), fixationLog)) {
            getCurrentBlockItemElements().add(new FixationLogRecordElement(fixationLog));
        }
    }

    public final void logStage(String name, ConstraintSystemMarker system) {
        name.getClass();
        system.getClass();
        updateCurrentSystem(system);
        BlockOwner blockOwner = this.currentCandidate;
        if (blockOwner == null) {
            blockOwner = BlockOwner.Unknown.INSTANCE;
        }
        BlockElement blockElement = new BlockElement(name, null, blockOwner, 2, null);
        register(blockElement, system);
        this.currentBlock = blockElement;
    }

    public final void setOrigins(List<? extends ConstraintElement> list) {
        list.getClass();
        this.origins = list;
    }

    public <T> T withOrigin(InitialConstraint constraint, Function0<? extends T> block) {
        constraint.getClass();
        block.getClass();
        List<? extends ConstraintElement> listListOf = CollectionsKt.listOf(cachedElementFor(constraint));
        List<? extends ConstraintElement> list = this.origins;
        try {
            this.origins = listListOf;
            return (T) block.invoke();
        } finally {
            this.origins = list;
        }
    }

    public <T> T withOrigins(TypeVariableMarker variable1, Constraint constraint1, TypeVariableMarker variable2, Constraint constraint2, Function0<? extends T> block) {
        variable1.getClass();
        constraint1.getClass();
        variable2.getClass();
        constraint2.getClass();
        block.getClass();
        List<? extends ConstraintElement> listListOf = CollectionsKt.listOf(new ConstraintElement[]{cachedElementFor(variable1, constraint1), cachedElementFor(variable2, constraint2)});
        List<? extends ConstraintElement> list = this.origins;
        try {
            this.origins = listListOf;
            return (T) block.invoke();
        } finally {
            this.origins = list;
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceLogger$BlockOwner;", Argument.Delimiters.none, "<init>", "()V", "Candidate", "Unknown", "Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceLogger$BlockOwner$Candidate;", "Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceLogger$BlockOwner$Unknown;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static abstract class BlockOwner {

        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceLogger$BlockOwner$Candidate;", "Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceLogger$BlockOwner;", "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;)V", "getCandidate", "()Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "owningCall", "Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceLogger$Call;", "getOwningCall", "()Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceLogger$Call;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class Candidate extends BlockOwner {
            private final org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate candidate;
            private final Call owningCall;

            /* JADX WARN: Illegal instructions before constructor call */
            public Candidate(org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate candidate) {
                candidate.getClass();
                DefaultConstructorMarker defaultConstructorMarker = null;
                super(defaultConstructorMarker);
                this.candidate = candidate;
                this.owningCall = new Call(candidate.getCallInfo().getCallSite(), defaultConstructorMarker, 2, defaultConstructorMarker);
            }

            public final org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate getCandidate() {
                return this.candidate;
            }

            public final Call getOwningCall() {
                return this.owningCall;
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceLogger$BlockOwner$Unknown;", "Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceLogger$BlockOwner;", "<init>", "()V", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class Unknown extends BlockOwner {
            public static final Unknown INSTANCE = new Unknown();

            private Unknown() {
                super(null);
            }
        }

        public /* synthetic */ BlockOwner(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private BlockOwner() {
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0005b\u0002\b\bJ\u001c\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u000bH\u0005b\u0002\b\bJ\u0014\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H\u0007b\u0002\b\bR\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceLogger$Companion;", Argument.Delimiters.none, "<init>", "()V", "formatConstraint", Argument.Delimiters.none, "constraint", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/InitialConstraint;", "Lkotlin/jvm/JvmStatic;", "variable", "Lorg/jetbrains/kotlin/types/model/TypeVariableMarker;", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/Constraint;", "fqNameRegex", "Lkotlin/text/Regex;", "sanitizeFqNames", "string", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {

        @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[ConstraintKind.values().length];
                try {
                    iArr[ConstraintKind.UPPER.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[ConstraintKind.LOWER.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[ConstraintKind.EQUALITY.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final String formatConstraint(InitialConstraint constraint) {
            constraint.getClass();
            int i = WhenMappings.$EnumSwitchMapping$0[constraint.getConstraintKind().ordinal()];
            if (i == 1) {
                return constraint.getA() + " <: " + constraint.getB();
            }
            if (i == 2) {
                return constraint.getB() + " <: " + constraint.getA();
            }
            if (i != 3) {
                bu8.a();
                return null;
            }
            return constraint.getA() + " == " + constraint.getB();
        }

        @JvmStatic
        public final String sanitizeFqNames(String string) {
            string.getClass();
            return FirInferenceLogger.fqNameRegex.replace(string, "$1");
        }

        private Companion() {
        }

        @JvmStatic
        public final String formatConstraint(TypeVariableMarker variable, Constraint constraint) {
            variable.getClass();
            constraint.getClass();
            int i = WhenMappings.$EnumSwitchMapping$0[constraint.getKind().ordinal()];
            if (i == 1) {
                return variable + " <: " + constraint.getType();
            }
            if (i == 2) {
                return constraint.getType() + " <: " + variable;
            }
            if (i == 3) {
                return variable + " == " + constraint.getType();
            }
            bu8.a();
            return null;
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0004\u0005¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceLogger$LoggingElement;", Argument.Delimiters.none, "<init>", "()V", "Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceLogger$BlockElement;", "Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceLogger$BlockItemElement;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static abstract class LoggingElement {
        public /* synthetic */ LoggingElement(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private LoggingElement() {
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0004\u0004\u0005\u0006\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceLogger$BlockItemElement;", "Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceLogger$LoggingElement;", "<init>", "()V", "Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceLogger$ConstraintElement;", "Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceLogger$ErrorElement;", "Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceLogger$FixationLogRecordElement;", "Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceLogger$NewVariableElement;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static abstract class BlockItemElement extends LoggingElement {
        private BlockItemElement() {
            super(null);
        }

        public /* synthetic */ BlockItemElement(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\u0017\b\u0004\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00000\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00000\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\u0002\b\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceLogger$ConstraintElement;", "Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceLogger$BlockItemElement;", "origins", Argument.Delimiters.none, "<init>", "(Ljava/util/List;)V", "getOrigins", "()Ljava/util/List;", "Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceLogger$InitialConstraintElement;", "Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceLogger$VariableConstraintElement;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static abstract class ConstraintElement extends BlockItemElement {
        private final List<ConstraintElement> origins;

        /* JADX WARN: Multi-variable type inference failed */
        private ConstraintElement(List<? extends ConstraintElement> list) {
            super(null);
            this.origins = list;
        }

        public final List<ConstraintElement> getOrigins() {
            return this.origins;
        }

        public /* synthetic */ ConstraintElement(List list, DefaultConstructorMarker defaultConstructorMarker) {
            this(list);
        }
    }

    @JvmStatic
    public static final String formatConstraint(TypeVariableMarker typeVariableMarker, Constraint constraint) {
        return INSTANCE.formatConstraint(typeVariableMarker, constraint);
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceLogger$Call;", Argument.Delimiters.none, "fir", "Lorg/jetbrains/kotlin/fir/FirElement;", "render", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/FirElement;Ljava/lang/String;)V", "getFir", "()Lorg/jetbrains/kotlin/fir/FirElement;", "getRender", "()Ljava/lang/String;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Call {
        private final FirElement fir;
        private final String render;

        public Call(FirElement firElement, String str) {
            firElement.getClass();
            str.getClass();
            this.fir = firElement;
            this.render = str;
        }

        public final FirElement getFir() {
            return this.fir;
        }

        public final String getRender() {
            return this.render;
        }

        public /* synthetic */ Call(FirElement firElement, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(firElement, (i & 2) != 0 ? UtilsKt.render(firElement) : str);
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceLogger$BlockElement;", "Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceLogger$LoggingElement;", ModuleXmlParser.NAME, Argument.Delimiters.none, "items", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceLogger$BlockItemElement;", "owner", "Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceLogger$BlockOwner;", "<init>", "(Ljava/lang/String;Ljava/util/List;Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceLogger$BlockOwner;)V", "getName", "()Ljava/lang/String;", "getItems", "()Ljava/util/List;", "getOwner", "()Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceLogger$BlockOwner;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class BlockElement extends LoggingElement {
        private final List<BlockItemElement> items;
        private final String name;
        private final BlockOwner owner;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public BlockElement(String str, List<BlockItemElement> list, BlockOwner blockOwner) {
            super(null);
            str.getClass();
            list.getClass();
            blockOwner.getClass();
            this.name = str;
            this.items = list;
            this.owner = blockOwner;
        }

        public final List<BlockItemElement> getItems() {
            return this.items;
        }

        public final String getName() {
            return this.name;
        }

        public final BlockOwner getOwner() {
            return this.owner;
        }

        public /* synthetic */ BlockElement(String str, List list, BlockOwner blockOwner, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? new ArrayList() : list, blockOwner);
        }
    }

    private final ConstraintElement cachedElementFor(InitialConstraint constraint) {
        ConstraintElement constraintElement = this.initialConstraintToKnownElement.get(constraint);
        if (constraintElement != null) {
            return constraintElement;
        }
        w04.a("This constraint has not yet been logged: ", constraint);
        return null;
    }

    private final boolean isSimilarTo(InferenceLogger.FixationLogVariableInfo<?> fixationLogVariableInfo, InferenceLogger.FixationLogVariableInfo<?> fixationLogVariableInfo2) {
        if (fixationLogVariableInfo2 == null || !Intrinsics.areEqual(fixationLogVariableInfo.getReadiness(), fixationLogVariableInfo2.getReadiness()) || fixationLogVariableInfo.getConstraints().size() != fixationLogVariableInfo2.getConstraints().size()) {
            return false;
        }
        int size = fixationLogVariableInfo.getConstraints().size();
        for (int i = 0; i < size; i++) {
            if (fixationLogVariableInfo.getConstraints().get(i) != fixationLogVariableInfo2.getConstraints().get(i)) {
                return false;
            }
        }
        return true;
    }
}
