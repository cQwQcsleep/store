package org.jetbrains.kotlin.fir.resolve.inference;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.calls.ConePostponedResolvedAtom;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.resolve.calls.inference.components.ConstraintSystemCompletionContext;
import org.jetbrains.kotlin.resolve.calls.inference.components.ConstraintSystemCompletionMode;
import org.jetbrains.kotlin.resolve.calls.inference.components.TrivialConstraintTypeInferenceOracle;
import org.jetbrains.kotlin.resolve.calls.inference.model.Constraint;
import org.jetbrains.kotlin.resolve.calls.inference.model.VariableWithConstraints;
import org.jetbrains.kotlin.resolve.calls.model.CollectionLiteralAtomMarker;
import org.jetbrains.kotlin.types.AbstractTypeChecker;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.kotlin.types.model.RigidTypeMarker;
import org.jetbrains.kotlin.types.model.SimpleTypeMarker;
import org.jetbrains.kotlin.types.model.TypeArgumentMarker;
import org.jetbrains.kotlin.types.model.TypeParameterMarker;
import org.jetbrains.kotlin.types.model.TypeVariableMarker;
import org.jetbrains.kotlin.types.model.TypeVariance;
import org.jetbrains.kotlin.utils.CollectionsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001:\u0002=>B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\u0010\u0006\u001a\u00060\u0007j\u0002`\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u0006\u0010\u001e\u001a\u00020\u001fJ\u0010\u0010 \u001a\u00020!*\u00060\u0007j\u0002`\bH\u0002J\u0010\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020\u000fH\u0002J\u0010\u0010$\u001a\u00020%*\u00060\u0007j\u0002`\bH\u0002J\u0010\u0010&\u001a\u00020!2\u0006\u0010'\u001a\u00020(H\u0002J.\u0010)\u001a\u00020!*\u00060\u0007j\u0002`\b2\u0006\u0010*\u001a\u00020\u00162\u0006\u0010+\u001a\u00020,2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020(0\u0012H\u0002J(\u0010.\u001a\u00020,*\u00060\u0007j\u0002`\b2\u0006\u0010+\u001a\u00020,2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u000202H\u0002J\f\u00103\u001a\u00020,*\u00020,H\u0002J.\u00104\u001a\u00020!*\u00060\u0007j\u0002`\b2\u0006\u0010*\u001a\u00020\u00162\u0006\u0010.\u001a\u00020,2\f\u00105\u001a\b\u0012\u0004\u0012\u00020(0\u0012H\u0002J \u00106\u001a\u00020%*\u00060\u0007j\u0002`\b2\u0006\u0010#\u001a\u00020\u000f2\u0006\u00107\u001a\u00020\u0010H\u0002J\u0014\u00108\u001a\u00020%*\u0002092\u0006\u00107\u001a\u00020\u0010H\u0002J \u0010:\u001a\u00020%*\u00060\u0007j\u0002`\b2\u0006\u0010;\u001a\u0002092\u0006\u0010<\u001a\u00020\u0013H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u00060\u0007j\u0002`\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u00188BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001a\u0010\u001b¨\u0006?"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/CalculatorForNestedCall;", Argument.Delimiters.none, "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "returnType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "context", "Lorg/jetbrains/kotlin/resolve/calls/inference/components/ConstraintSystemCompletionContext;", "Lorg/jetbrains/kotlin/fir/resolve/inference/CsCompleterContext;", "oracle", "Lorg/jetbrains/kotlin/resolve/calls/inference/components/TrivialConstraintTypeInferenceOracle;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/resolve/calls/inference/components/ConstraintSystemCompletionContext;Lorg/jetbrains/kotlin/resolve/calls/inference/components/TrivialConstraintTypeInferenceOracle;)V", "fixationDirectionsForVariables", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/resolve/calls/inference/model/VariableWithConstraints;", "Lorg/jetbrains/kotlin/fir/resolve/inference/CalculatorForNestedCall$FixationDirection;", "variablesWithQueuedConstraints", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/types/model/TypeVariableMarker;", "typesToProcess", "Ljava/util/Queue;", "Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "postponedAtoms", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/ConePostponedResolvedAtom;", "getPostponedAtoms", "()Ljava/util/List;", "postponedAtoms$delegate", "Lkotlin/Lazy;", "computeCompletionMode", "Lorg/jetbrains/kotlin/resolve/calls/inference/components/ConstraintSystemCompletionMode;", "computeDirections", Argument.Delimiters.none, "enqueueTypesFromConstraints", "variableWithConstraints", "directionRequirementsForVariablesHold", Argument.Delimiters.none, "updateDirection", "directionForVariable", "Lorg/jetbrains/kotlin/fir/resolve/inference/CalculatorForNestedCall$FixationDirectionForVariable;", "collectRequiredDirectionsForVariables", ModuleXmlParser.TYPE, "outerVariance", "Lorg/jetbrains/kotlin/types/model/TypeVariance;", "fixationDirectionsCollector", "compositeVariance", "argument", "Lorg/jetbrains/kotlin/types/model/TypeArgumentMarker;", "parameter", "Lorg/jetbrains/kotlin/types/model/TypeParameterMarker;", "reversed", "processTypeWithoutParameters", "newRequirementsCollector", "hasProperConstraint", "direction", "hasRequiredKind", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/Constraint;", "isLowerConstraintForPartiallyAnalyzedVariable", "constraint", "variable", "FixationDirection", "FixationDirectionForVariable", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class CalculatorForNestedCall {
    private final Candidate candidate;
    private final ConstraintSystemCompletionContext context;
    private final Map<VariableWithConstraints, FixationDirection> fixationDirectionsForVariables;
    private final TrivialConstraintTypeInferenceOracle oracle;

    /* JADX INFO: renamed from: postponedAtoms$delegate, reason: from kotlin metadata */
    private final Lazy postponedAtoms;
    private final ConeKotlinType returnType;
    private final Queue<KotlinTypeMarker> typesToProcess;
    private final Set<TypeVariableMarker> variablesWithQueuedConstraints;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/CalculatorForNestedCall$FixationDirection;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "TO_SUBTYPE", "EQUALITY", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public enum FixationDirection {
        TO_SUBTYPE,
        EQUALITY;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<FixationDirection> getEntries() {
            return $ENTRIES;
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/CalculatorForNestedCall$FixationDirectionForVariable;", Argument.Delimiters.none, "variable", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/VariableWithConstraints;", "direction", "Lorg/jetbrains/kotlin/fir/resolve/inference/CalculatorForNestedCall$FixationDirection;", "<init>", "(Lorg/jetbrains/kotlin/resolve/calls/inference/model/VariableWithConstraints;Lorg/jetbrains/kotlin/fir/resolve/inference/CalculatorForNestedCall$FixationDirection;)V", "getVariable", "()Lorg/jetbrains/kotlin/resolve/calls/inference/model/VariableWithConstraints;", "getDirection", "()Lorg/jetbrains/kotlin/fir/resolve/inference/CalculatorForNestedCall$FixationDirection;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class FixationDirectionForVariable {
        private final FixationDirection direction;
        private final VariableWithConstraints variable;

        public FixationDirectionForVariable(VariableWithConstraints variableWithConstraints, FixationDirection fixationDirection) {
            variableWithConstraints.getClass();
            fixationDirection.getClass();
            this.variable = variableWithConstraints;
            this.direction = fixationDirection;
        }

        public static /* synthetic */ FixationDirectionForVariable copy$default(FixationDirectionForVariable fixationDirectionForVariable, VariableWithConstraints variableWithConstraints, FixationDirection fixationDirection, int i, Object obj) {
            if ((i & 1) != 0) {
                variableWithConstraints = fixationDirectionForVariable.variable;
            }
            if ((i & 2) != 0) {
                fixationDirection = fixationDirectionForVariable.direction;
            }
            return fixationDirectionForVariable.copy(variableWithConstraints, fixationDirection);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final VariableWithConstraints getVariable() {
            return this.variable;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final FixationDirection getDirection() {
            return this.direction;
        }

        public final FixationDirectionForVariable copy(VariableWithConstraints variable, FixationDirection direction) {
            variable.getClass();
            direction.getClass();
            return new FixationDirectionForVariable(variable, direction);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof FixationDirectionForVariable)) {
                return false;
            }
            FixationDirectionForVariable fixationDirectionForVariable = (FixationDirectionForVariable) other;
            return Intrinsics.areEqual(this.variable, fixationDirectionForVariable.variable) && this.direction == fixationDirectionForVariable.direction;
        }

        public final FixationDirection getDirection() {
            return this.direction;
        }

        public final VariableWithConstraints getVariable() {
            return this.variable;
        }

        public int hashCode() {
            return (this.variable.hashCode() * 31) + this.direction.hashCode();
        }

        public String toString() {
            return "FixationDirectionForVariable(variable=" + this.variable + ", direction=" + this.direction + ')';
        }
    }

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[TypeVariance.values().length];
            try {
                iArr[TypeVariance.INV.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[TypeVariance.OUT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[TypeVariance.IN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[FixationDirection.values().length];
            try {
                iArr2[FixationDirection.TO_SUBTYPE.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[FixationDirection.EQUALITY.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public CalculatorForNestedCall(Candidate candidate, ConeKotlinType coneKotlinType, ConstraintSystemCompletionContext constraintSystemCompletionContext, TrivialConstraintTypeInferenceOracle trivialConstraintTypeInferenceOracle) {
        candidate.getClass();
        constraintSystemCompletionContext.getClass();
        trivialConstraintTypeInferenceOracle.getClass();
        this.candidate = candidate;
        this.returnType = coneKotlinType;
        this.context = constraintSystemCompletionContext;
        this.oracle = trivialConstraintTypeInferenceOracle;
        this.fixationDirectionsForVariables = CollectionsKt.newLinkedHashMapWithExpectedSize(constraintSystemCompletionContext.getNotFixedTypeVariables().size());
        this.variablesWithQueuedConstraints = new LinkedHashSet();
        this.typesToProcess = new ArrayDeque();
        this.postponedAtoms = LazyKt.lazy(new Function0() { // from class: org.jetbrains.kotlin.fir.resolve.inference.b
            public final Object invoke() {
                return CalculatorForNestedCall.b(this.b);
            }
        });
    }

    public static List b(CalculatorForNestedCall calculatorForNestedCall) {
        return ConstraintSystemCompleter.INSTANCE.getOrderedNotAnalyzedPostponedArguments$org_jetbrains_kotlin_resolve(calculatorForNestedCall.candidate);
    }

    public static boolean c(ConstraintSystemCompletionContext constraintSystemCompletionContext, KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return constraintSystemCompletionContext.getNotFixedTypeVariables().containsKey(constraintSystemCompletionContext.typeConstructor(kotlinTypeMarker));
    }

    private final void collectRequiredDirectionsForVariables(ConstraintSystemCompletionContext constraintSystemCompletionContext, KotlinTypeMarker kotlinTypeMarker, TypeVariance typeVariance, Set<FixationDirectionForVariable> set) {
        RigidTypeMarker rigidTypeMarkerLowerBoundIfFlexible = constraintSystemCompletionContext.lowerBoundIfFlexible(kotlinTypeMarker);
        int iArgumentsCount = constraintSystemCompletionContext.argumentsCount(rigidTypeMarkerLowerBoundIfFlexible);
        if (iArgumentsCount <= 0 || constraintSystemCompletionContext.isError(rigidTypeMarkerLowerBoundIfFlexible) || iArgumentsCount != constraintSystemCompletionContext.parametersCount(constraintSystemCompletionContext.typeConstructor(rigidTypeMarkerLowerBoundIfFlexible))) {
            processTypeWithoutParameters(constraintSystemCompletionContext, kotlinTypeMarker, typeVariance, set);
            return;
        }
        for (int i = 0; i < iArgumentsCount; i++) {
            TypeArgumentMarker argument = constraintSystemCompletionContext.getArgument(rigidTypeMarkerLowerBoundIfFlexible, i);
            TypeParameterMarker parameter = constraintSystemCompletionContext.getParameter(constraintSystemCompletionContext.typeConstructor(rigidTypeMarkerLowerBoundIfFlexible), i);
            KotlinTypeMarker type = constraintSystemCompletionContext.getType(argument);
            if (type != null) {
                collectRequiredDirectionsForVariables(constraintSystemCompletionContext, type, compositeVariance(constraintSystemCompletionContext, typeVariance, argument, parameter), set);
            }
        }
    }

    private final TypeVariance compositeVariance(ConstraintSystemCompletionContext constraintSystemCompletionContext, TypeVariance typeVariance, TypeArgumentMarker typeArgumentMarker, TypeParameterMarker typeParameterMarker) {
        TypeVariance typeVarianceEffectiveVariance = AbstractTypeChecker.INSTANCE.effectiveVariance(constraintSystemCompletionContext.getVariance(typeParameterMarker), constraintSystemCompletionContext.getVariance(typeArgumentMarker));
        if (typeVarianceEffectiveVariance == null) {
            typeVarianceEffectiveVariance = TypeVariance.INV;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[typeVariance.ordinal()];
        if (i == 1) {
            return TypeVariance.INV;
        }
        if (i == 2) {
            return typeVarianceEffectiveVariance;
        }
        if (i == 3) {
            return reversed(typeVarianceEffectiveVariance);
        }
        bu8.a();
        return null;
    }

    private final void computeDirections(final ConstraintSystemCompletionContext constraintSystemCompletionContext) {
        KotlinTypeMarker kotlinTypeMarkerPoll;
        while (!this.typesToProcess.isEmpty() && (kotlinTypeMarkerPoll = this.typesToProcess.poll()) != null) {
            if (constraintSystemCompletionContext.contains(kotlinTypeMarkerPoll, new Function1() { // from class: org.jetbrains.kotlin.fir.resolve.inference.c
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(CalculatorForNestedCall.c(constraintSystemCompletionContext, (KotlinTypeMarker) obj));
                }
            })) {
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                collectRequiredDirectionsForVariables(constraintSystemCompletionContext, kotlinTypeMarkerPoll, TypeVariance.OUT, linkedHashSet);
                for (FixationDirectionForVariable fixationDirectionForVariable : linkedHashSet) {
                    updateDirection(fixationDirectionForVariable);
                    enqueueTypesFromConstraints(fixationDirectionForVariable.getVariable());
                }
            }
        }
    }

    private final boolean directionRequirementsForVariablesHold(ConstraintSystemCompletionContext constraintSystemCompletionContext) {
        for (Map.Entry<VariableWithConstraints, FixationDirection> entry : this.fixationDirectionsForVariables.entrySet()) {
            if (!hasProperConstraint(constraintSystemCompletionContext, entry.getKey(), entry.getValue())) {
                return false;
            }
        }
        return true;
    }

    private final void enqueueTypesFromConstraints(VariableWithConstraints variableWithConstraints) {
        TypeVariableMarker typeVariable = variableWithConstraints.getTypeVariable();
        if (this.variablesWithQueuedConstraints.contains(typeVariable)) {
            return;
        }
        Iterator it = variableWithConstraints.getConstraints().iterator();
        while (it.hasNext()) {
            this.typesToProcess.add(((Constraint) it.next()).getType());
        }
        this.variablesWithQueuedConstraints.add(typeVariable);
    }

    private final List<ConePostponedResolvedAtom> getPostponedAtoms() {
        return (List) this.postponedAtoms.getValue();
    }

    private final boolean hasProperConstraint(ConstraintSystemCompletionContext constraintSystemCompletionContext, VariableWithConstraints variableWithConstraints, FixationDirection fixationDirection) {
        List<Constraint> constraints = variableWithConstraints.getConstraints();
        TypeVariableMarker typeVariable = variableWithConstraints.getTypeVariable();
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        for (Constraint constraint : constraints) {
            if (hasRequiredKind(constraint, fixationDirection) && constraintSystemCompletionContext.isProperType(constraint.getType())) {
                if (constraintSystemCompletionContext.isIntegerLiteralTypeConstructor(constraintSystemCompletionContext.typeConstructor(constraint.getType()))) {
                    z2 = true;
                } else if (this.oracle.isSuitableResultedType(constraint.getType())) {
                    z = true;
                    z3 = true;
                } else if (!isLowerConstraintForPartiallyAnalyzedVariable(constraintSystemCompletionContext, constraint, typeVariable)) {
                    z = true;
                }
            }
        }
        if (z) {
            return !z2 || z3;
        }
        return false;
    }

    private final boolean hasRequiredKind(Constraint constraint, FixationDirection fixationDirection) {
        int i = WhenMappings.$EnumSwitchMapping$1[fixationDirection.ordinal()];
        if (i == 1) {
            return constraint.getKind().isLower() || constraint.getKind().isEqual();
        }
        if (i == 2) {
            return constraint.getKind().isEqual();
        }
        bu8.a();
        return false;
    }

    private final boolean isLowerConstraintForPartiallyAnalyzedVariable(ConstraintSystemCompletionContext constraintSystemCompletionContext, Constraint constraint, TypeVariableMarker typeVariableMarker) {
        final SimpleTypeMarker simpleTypeMarkerDefaultType = constraintSystemCompletionContext.defaultType(typeVariableMarker);
        if (constraint.getKind().isLower()) {
            List<ConePostponedResolvedAtom> postponedAtoms = getPostponedAtoms();
            if (!(postponedAtoms instanceof Collection) || !postponedAtoms.isEmpty()) {
                Iterator<T> it = postponedAtoms.iterator();
                while (it.hasNext()) {
                    ConeKotlinType coneKotlinTypeMo581getExpectedType = ((ConePostponedResolvedAtom) it.next()).mo581getExpectedType();
                    if (coneKotlinTypeMo581getExpectedType != null ? constraintSystemCompletionContext.contains(coneKotlinTypeMo581getExpectedType, new Function1() { // from class: org.jetbrains.kotlin.fir.resolve.inference.a
                        public final Object invoke(Object obj) {
                            return Boolean.valueOf(CalculatorForNestedCall.isLowerConstraintForPartiallyAnalyzedVariable$lambda$0$0(simpleTypeMarkerDefaultType, (KotlinTypeMarker) obj));
                        }
                    }) : false) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isLowerConstraintForPartiallyAnalyzedVariable$lambda$0$0(SimpleTypeMarker simpleTypeMarker, KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return Intrinsics.areEqual(simpleTypeMarker, kotlinTypeMarker);
    }

    private final void processTypeWithoutParameters(ConstraintSystemCompletionContext constraintSystemCompletionContext, KotlinTypeMarker kotlinTypeMarker, TypeVariance typeVariance, Set<FixationDirectionForVariable> set) {
        FixationDirection fixationDirection;
        VariableWithConstraints variableWithConstraints = (VariableWithConstraints) constraintSystemCompletionContext.getNotFixedTypeVariables().get(constraintSystemCompletionContext.typeConstructor(kotlinTypeMarker));
        if (variableWithConstraints == null) {
            return;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[typeVariance.ordinal()];
        if (i == 1) {
            fixationDirection = FixationDirection.EQUALITY;
        } else if (i == 2) {
            fixationDirection = FixationDirection.TO_SUBTYPE;
        } else {
            if (i != 3) {
                bu8.a();
                return;
            }
            fixationDirection = FixationDirection.EQUALITY;
        }
        set.add(new FixationDirectionForVariable(variableWithConstraints, fixationDirection));
    }

    private final TypeVariance reversed(TypeVariance typeVariance) {
        int i = WhenMappings.$EnumSwitchMapping$0[typeVariance.ordinal()];
        if (i == 1) {
            return TypeVariance.INV;
        }
        if (i == 2) {
            return TypeVariance.IN;
        }
        if (i == 3) {
            return TypeVariance.OUT;
        }
        bu8.a();
        return null;
    }

    private final void updateDirection(FixationDirectionForVariable directionForVariable) {
        VariableWithConstraints variable = directionForVariable.getVariable();
        FixationDirection direction = directionForVariable.getDirection();
        FixationDirection fixationDirection = this.fixationDirectionsForVariables.get(variable);
        if (fixationDirection == null) {
            this.fixationDirectionsForVariables.put(variable, direction);
            return;
        }
        FixationDirection fixationDirection2 = FixationDirection.EQUALITY;
        if (fixationDirection == fixationDirection2 || fixationDirection == direction) {
            return;
        }
        this.fixationDirectionsForVariables.put(variable, fixationDirection2);
    }

    public final ConstraintSystemCompletionMode computeCompletionMode() {
        ConstraintSystemCompletionContext constraintSystemCompletionContext = this.context;
        this.typesToProcess.add(this.returnType);
        computeDirections(constraintSystemCompletionContext);
        if (directionRequirementsForVariablesHold(constraintSystemCompletionContext)) {
            List<ConePostponedResolvedAtom> postponedAtoms = getPostponedAtoms();
            if (!(postponedAtoms instanceof Collection) || !postponedAtoms.isEmpty()) {
                for (ConePostponedResolvedAtom conePostponedResolvedAtom : postponedAtoms) {
                    if (!(conePostponedResolvedAtom instanceof CollectionLiteralAtomMarker) || conePostponedResolvedAtom.getAnalyzed()) {
                    }
                }
            }
            return ConstraintSystemCompletionMode.FULL;
        }
        return ConstraintSystemCompletionMode.PARTIAL;
    }
}
