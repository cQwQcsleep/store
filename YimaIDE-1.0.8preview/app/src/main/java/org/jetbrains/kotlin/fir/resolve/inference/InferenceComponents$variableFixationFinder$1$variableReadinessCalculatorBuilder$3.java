package org.jetbrains.kotlin.fir.resolve.inference;

import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.calls.inference.components.InferenceLogger;
import org.jetbrains.kotlin.resolve.calls.inference.components.LegacyVariableReadinessCalculator;
import org.jetbrains.kotlin.resolve.calls.inference.components.TrivialConstraintTypeInferenceOracle;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class InferenceComponents$variableFixationFinder$1$variableReadinessCalculatorBuilder$3 extends FunctionReferenceImpl implements Function3<TrivialConstraintTypeInferenceOracle, LanguageVersionSettings, InferenceLogger, LegacyVariableReadinessCalculator> {
    public static final InferenceComponents$variableFixationFinder$1$variableReadinessCalculatorBuilder$3 INSTANCE = new InferenceComponents$variableFixationFinder$1$variableReadinessCalculatorBuilder$3();

    public InferenceComponents$variableFixationFinder$1$variableReadinessCalculatorBuilder$3() {
        super(3, LegacyVariableReadinessCalculator.class, "<init>", "<init>(Lorg/jetbrains/kotlin/resolve/calls/inference/components/TrivialConstraintTypeInferenceOracle;Lorg/jetbrains/kotlin/config/LanguageVersionSettings;Lorg/jetbrains/kotlin/resolve/calls/inference/components/InferenceLogger;)V", 0);
    }

    public final LegacyVariableReadinessCalculator invoke(TrivialConstraintTypeInferenceOracle trivialConstraintTypeInferenceOracle, LanguageVersionSettings languageVersionSettings, InferenceLogger inferenceLogger) {
        trivialConstraintTypeInferenceOracle.getClass();
        languageVersionSettings.getClass();
        return new LegacyVariableReadinessCalculator(trivialConstraintTypeInferenceOracle, languageVersionSettings, inferenceLogger);
    }
}
