package org.jetbrains.kotlin.fir.expressions;

import java.util.LinkedHashMap;
import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.expressions.builder.FirArgumentListBuilder;
import org.jetbrains.kotlin.fir.expressions.impl.FirResolvedArgumentList;
import org.jetbrains.kotlin.fir.expressions.impl.FirResolvedArgumentListForErrorCall;
import org.jetbrains.kotlin.fir.expressions.impl.FirResolvedArgumentListImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0016\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0003\u001a4\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u00012\"\u0010\n\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\f0\u000bj\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\f`\r\u001a6\u0010\u000e\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u00012&\u0010\n\u001a\"\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bj\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\f`\r¨\u0006\u000f"}, d2 = {"buildUnaryArgumentList", "Lorg/jetbrains/kotlin/fir/expressions/FirArgumentList;", "argument", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "buildBinaryArgumentList", "left", "right", "buildResolvedArgumentList", "Lorg/jetbrains/kotlin/fir/expressions/impl/FirResolvedArgumentList;", "original", "mapping", "Ljava/util/LinkedHashMap;", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "Lkotlin/collections/LinkedHashMap;", "buildArgumentListForErrorCall", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirArgumentUtilKt {
    public static final FirArgumentList buildArgumentListForErrorCall(FirArgumentList firArgumentList, LinkedHashMap<FirExpression, FirValueParameter> linkedHashMap) {
        firArgumentList.getClass();
        linkedHashMap.getClass();
        return new FirResolvedArgumentListForErrorCall(firArgumentList, linkedHashMap);
    }

    public static final FirArgumentList buildBinaryArgumentList(FirExpression firExpression, FirExpression firExpression2) {
        firExpression.getClass();
        firExpression2.getClass();
        FirArgumentListBuilder firArgumentListBuilder = new FirArgumentListBuilder();
        firArgumentListBuilder.getArguments().add(firExpression);
        firArgumentListBuilder.getArguments().add(firExpression2);
        return firArgumentListBuilder.build();
    }

    public static final FirResolvedArgumentList buildResolvedArgumentList(FirArgumentList firArgumentList, LinkedHashMap<FirExpression, FirValueParameter> linkedHashMap) {
        linkedHashMap.getClass();
        return new FirResolvedArgumentListImpl(firArgumentList, linkedHashMap);
    }

    public static final FirArgumentList buildUnaryArgumentList(FirExpression firExpression) {
        firExpression.getClass();
        FirArgumentListBuilder firArgumentListBuilder = new FirArgumentListBuilder();
        firArgumentListBuilder.getArguments().add(firExpression);
        return firArgumentListBuilder.build();
    }
}
