package org.jetbrains.kotlin.fir.resolve.inference;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolvedLambdaAtom;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001JX\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0011À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/LambdaAnalyzer;", Argument.Delimiters.none, "analyzeAndGetLambdaReturnArguments", "Lorg/jetbrains/kotlin/fir/resolve/inference/ReturnArgumentsAnalysisResult;", "lambdaAtom", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolvedLambdaAtom;", "receiverType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "contextParameters", Argument.Delimiters.none, "parameters", "expectedReturnType", "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "withPCLASession", Argument.Delimiters.none, "forOverloadByLambdaReturnType", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface LambdaAnalyzer {
    ReturnArgumentsAnalysisResult analyzeAndGetLambdaReturnArguments(ConeResolvedLambdaAtom lambdaAtom, ConeKotlinType receiverType, List<? extends ConeKotlinType> contextParameters, List<? extends ConeKotlinType> parameters, ConeKotlinType expectedReturnType, Candidate candidate, boolean withPCLASession, boolean forOverloadByLambdaReturnType);
}
