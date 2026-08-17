package org.jetbrains.kotlin.fir.resolve.inference;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.types.ConeInferenceContext;
import org.jetbrains.kotlin.fir.types.ConeTypeApproximator;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.resolve.calls.inference.components.AbstractVariableReadinessCalculator;
import org.jetbrains.kotlin.resolve.calls.inference.components.ConstraintIncorporator;
import org.jetbrains.kotlin.resolve.calls.inference.components.ConstraintInjector;
import org.jetbrains.kotlin.resolve.calls.inference.components.PostponedArgumentInputTypesResolver;
import org.jetbrains.kotlin.resolve.calls.inference.components.ResultTypeResolver;
import org.jetbrains.kotlin.resolve.calls.inference.components.TrivialConstraintTypeInferenceOracle;
import org.jetbrains.kotlin.resolve.calls.inference.components.VariableFixationFinder;
import org.jetbrains.kotlin.resolve.calls.inference.model.NewConstraintSystemImpl;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002:\u00010B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006JJ\u0010%\u001a\u00020&2B\b\u0002\u0010'\u001a<\u0012\u0013\u0012\u00110)¢\u0006\f\b*\u0012\b\b+\u0012\u0004\b\b(,\u0012\u0013\u0012\u00110)¢\u0006\f\b*\u0012\b\b+\u0012\u0004\b\b(-\u0012\u0006\u0012\u0004\u0018\u00010.\u0018\u00010(j\u0004\u0018\u0001`/R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0015\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0019\u001a\u00020\u001a¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u001d\u001a\u00020\u001e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0015\u0010!\u001a\u00060\"R\u00020\u0000¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$¨\u00061"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/InferenceComponents;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "Lorg/jetbrains/kotlin/fir/SessionHolder;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "typeContext", "Lorg/jetbrains/kotlin/fir/types/ConeInferenceContext;", "approximator", "Lorg/jetbrains/kotlin/fir/types/ConeTypeApproximator;", "trivialConstraintTypeInferenceOracle", "Lorg/jetbrains/kotlin/resolve/calls/inference/components/TrivialConstraintTypeInferenceOracle;", "getTrivialConstraintTypeInferenceOracle", "()Lorg/jetbrains/kotlin/resolve/calls/inference/components/TrivialConstraintTypeInferenceOracle;", "incorporator", "Lorg/jetbrains/kotlin/resolve/calls/inference/components/ConstraintIncorporator;", "injector", "Lorg/jetbrains/kotlin/resolve/calls/inference/components/ConstraintInjector;", "resultTypeResolver", "Lorg/jetbrains/kotlin/resolve/calls/inference/components/ResultTypeResolver;", "getResultTypeResolver", "()Lorg/jetbrains/kotlin/resolve/calls/inference/components/ResultTypeResolver;", "variableFixationFinder", "Lorg/jetbrains/kotlin/resolve/calls/inference/components/VariableFixationFinder;", "getVariableFixationFinder", "()Lorg/jetbrains/kotlin/resolve/calls/inference/components/VariableFixationFinder;", "postponedArgumentInputTypesResolver", "Lorg/jetbrains/kotlin/resolve/calls/inference/components/PostponedArgumentInputTypesResolver;", "getPostponedArgumentInputTypesResolver", "()Lorg/jetbrains/kotlin/resolve/calls/inference/components/PostponedArgumentInputTypesResolver;", "constraintSystemFactory", "Lorg/jetbrains/kotlin/fir/resolve/inference/InferenceComponents$ConstraintSystemFactory;", "getConstraintSystemFactory", "()Lorg/jetbrains/kotlin/fir/resolve/inference/InferenceComponents$ConstraintSystemFactory;", "createConstraintSystem", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/NewConstraintSystemImpl;", "customSubtypingCallback", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "Lkotlin/ParameterName;", ModuleXmlParser.NAME, "subType", "superType", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/types/model/CustomSubtypingCallback;", "ConstraintSystemFactory", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class InferenceComponents implements FirSessionComponent, SessionHolder {
    private final ConeTypeApproximator approximator;
    private final ConstraintSystemFactory constraintSystemFactory;
    private final ConstraintIncorporator incorporator;
    private final ConstraintInjector injector;
    private final PostponedArgumentInputTypesResolver postponedArgumentInputTypesResolver;
    private final ResultTypeResolver resultTypeResolver;
    private final FirSession session;
    private final TrivialConstraintTypeInferenceOracle trivialConstraintTypeInferenceOracle;
    private final ConeInferenceContext typeContext;
    private final VariableFixationFinder variableFixationFinder;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/InferenceComponents$ConstraintSystemFactory;", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/inference/InferenceComponents;)V", "createConstraintSystem", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/NewConstraintSystemImpl;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public final class ConstraintSystemFactory {
        public ConstraintSystemFactory() {
        }

        public final NewConstraintSystemImpl createConstraintSystem() {
            return InferenceComponents.createConstraintSystem$default(InferenceComponents.this, null, 1, null);
        }
    }

    public InferenceComponents(FirSession firSession) {
        firSession.getClass();
        this.session = firSession;
        ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(getSession());
        this.typeContext = typeContext;
        ConeTypeApproximator typeApproximator = TypeComponentsKt.getTypeApproximator(getSession());
        this.approximator = typeApproximator;
        TrivialConstraintTypeInferenceOracle trivialConstraintTypeInferenceOracleCreate = TrivialConstraintTypeInferenceOracle.Companion.create(typeContext);
        this.trivialConstraintTypeInferenceOracle = trivialConstraintTypeInferenceOracleCreate;
        ConeConstraintSystemUtilContext coneConstraintSystemUtilContext = ConeConstraintSystemUtilContext.INSTANCE;
        ConstraintIncorporator constraintIncorporator = new ConstraintIncorporator(typeApproximator, trivialConstraintTypeInferenceOracleCreate, coneConstraintSystemUtilContext, FirLanguageSettingsComponentKt.getLanguageVersionSettings(getSession()), FirInferenceLoggerKt.getInferenceLogger(getSession()));
        this.incorporator = constraintIncorporator;
        this.injector = new ConstraintInjector(constraintIncorporator, typeApproximator, FirLanguageSettingsComponentKt.getLanguageVersionSettings(getSession()), FirInferenceLoggerKt.getInferenceLogger(getSession()));
        ResultTypeResolver resultTypeResolver = new ResultTypeResolver(typeApproximator, trivialConstraintTypeInferenceOracleCreate, FirLanguageSettingsComponentKt.getLanguageVersionSettings(getSession()));
        this.resultTypeResolver = resultTypeResolver;
        Function3 function3 = LanguageVersionUtilsKt.isEnabled(this, LanguageFeature.LexicographicVariableReadinessCalculation) ? InferenceComponents$variableFixationFinder$1$variableReadinessCalculatorBuilder$1.INSTANCE : null;
        VariableFixationFinder.Default r1 = new VariableFixationFinder.Default(FirLanguageSettingsComponentKt.getLanguageVersionSettings(getSession()), (AbstractVariableReadinessCalculator) (function3 == null ? InferenceComponents$variableFixationFinder$1$variableReadinessCalculatorBuilder$3.INSTANCE : function3).invoke(trivialConstraintTypeInferenceOracleCreate, FirLanguageSettingsComponentKt.getLanguageVersionSettings(getSession()), FirInferenceLoggerKt.getInferenceLogger(getSession())));
        this.variableFixationFinder = r1;
        this.postponedArgumentInputTypesResolver = new PostponedArgumentInputTypesResolver(resultTypeResolver, r1, coneConstraintSystemUtilContext);
        this.constraintSystemFactory = new ConstraintSystemFactory();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ NewConstraintSystemImpl createConstraintSystem$default(InferenceComponents inferenceComponents, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            function2 = null;
        }
        return inferenceComponents.createConstraintSystem(function2);
    }

    public final NewConstraintSystemImpl createConstraintSystem(Function2<? super KotlinTypeMarker, ? super KotlinTypeMarker, Boolean> customSubtypingCallback) {
        return new NewConstraintSystemImpl(this.injector, this.typeContext, FirLanguageSettingsComponentKt.getLanguageVersionSettings(getSession()), customSubtypingCallback);
    }

    public final ConstraintSystemFactory getConstraintSystemFactory() {
        return this.constraintSystemFactory;
    }

    public final PostponedArgumentInputTypesResolver getPostponedArgumentInputTypesResolver() {
        return this.postponedArgumentInputTypesResolver;
    }

    public final ResultTypeResolver getResultTypeResolver() {
        return this.resultTypeResolver;
    }

    @Override // org.jetbrains.kotlin.fir.SessionHolder
    public FirSession getSession() {
        return this.session;
    }

    public final TrivialConstraintTypeInferenceOracle getTrivialConstraintTypeInferenceOracle() {
        return this.trivialConstraintTypeInferenceOracle;
    }

    public final VariableFixationFinder getVariableFixationFinder() {
        return this.variableFixationFinder;
    }
}
