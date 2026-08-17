package org.jetbrains.kotlin.fir.resolve.inference;

import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.calls.inference.components.InferenceLogger;
import org.jetbrains.kotlin.resolve.calls.inference.components.TrivialConstraintTypeInferenceOracle;
import org.jetbrains.kotlin.resolve.calls.inference.components.VariableReadinessCalculator;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class InferenceComponents$variableFixationFinder$1$variableReadinessCalculatorBuilder$1 extends FunctionReferenceImpl implements Function3<TrivialConstraintTypeInferenceOracle, LanguageVersionSettings, InferenceLogger, VariableReadinessCalculator> {
    public static final InferenceComponents$variableFixationFinder$1$variableReadinessCalculatorBuilder$1 INSTANCE = new InferenceComponents$variableFixationFinder$1$variableReadinessCalculatorBuilder$1();

    public InferenceComponents$variableFixationFinder$1$variableReadinessCalculatorBuilder$1() {
        super(3, VariableReadinessCalculator.class, "<init>", "<init>(Lorg/jetbrains/kotlin/resolve/calls/inference/components/TrivialConstraintTypeInferenceOracle;Lorg/jetbrains/kotlin/config/LanguageVersionSettings;Lorg/jetbrains/kotlin/resolve/calls/inference/components/InferenceLogger;)V", 0);
    }

    public final VariableReadinessCalculator invoke(TrivialConstraintTypeInferenceOracle trivialConstraintTypeInferenceOracle, LanguageVersionSettings languageVersionSettings, InferenceLogger inferenceLogger) {
        trivialConstraintTypeInferenceOracle.getClass();
        languageVersionSettings.getClass();
        return new VariableReadinessCalculator(trivialConstraintTypeInferenceOracle, languageVersionSettings, inferenceLogger);
    }
}
