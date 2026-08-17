package org.jetbrains.kotlin.psi;

import java.util.List;
import org.jetbrains.kotlin.resolution.KtResolvableCall;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public interface KtCallElement extends KtElement, KtResolvableCall {
    KtExpression getCalleeExpression();

    List<KtLambdaArgument> getLambdaArguments();

    KtTypeArgumentList getTypeArgumentList();

    List<KtTypeProjection> getTypeArguments();

    KtValueArgumentList getValueArgumentList();

    List<? extends ValueArgument> getValueArguments();
}
