package org.jetbrains.kotlin.fir.expressions.impl;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.expressions.FirAbstractArgumentList;
import org.jetbrains.kotlin.fir.expressions.FirArgumentList;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J5\u0010\u0019\u001a\u00020\u001a\"\u0004\b\u0000\u0010\u001b\"\u0004\b\u0001\u0010\u001c2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u0002H\u001b\u0012\u0004\u0012\u0002H\u001c0\u001e2\u0006\u0010\u001f\u001a\u0002H\u001cH\u0016¢\u0006\u0002\u0010 J)\u0010!\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u001c2\f\u0010\"\u001a\b\u0012\u0004\u0012\u0002H\u001c0#2\u0006\u0010\u001f\u001a\u0002H\u001cH&¢\u0006\u0002\u0010$J)\u0010%\u001a\u00020&\"\u0004\b\u0000\u0010\u001c2\f\u0010\"\u001a\b\u0012\u0004\u0012\u0002H\u001c0#2\u0006\u0010\u001f\u001a\u0002H\u001cH\u0016¢\u0006\u0002\u0010'R\u0014\u0010\u0004\u001a\u0004\u0018\u00010\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R.\u0010\b\u001a\u001e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tj\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b`\fX¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR.\u0010\u000f\u001a\u001e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tj\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b`\fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u000eR\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u00128F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\n0\u00168VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018¨\u0006("}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/impl/FirResolvedArgumentList;", "Lorg/jetbrains/kotlin/fir/expressions/FirAbstractArgumentList;", "<init>", "()V", "originalArgumentList", "Lorg/jetbrains/kotlin/fir/expressions/FirArgumentList;", "getOriginalArgumentList", "()Lorg/jetbrains/kotlin/fir/expressions/FirArgumentList;", "mapping", "Ljava/util/LinkedHashMap;", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "Lkotlin/collections/LinkedHashMap;", "getMapping", "()Ljava/util/LinkedHashMap;", "mappingIncludingContextArguments", "getMappingIncludingContextArguments", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "arguments", Argument.Delimiters.none, "getArguments", "()Ljava/util/List;", "acceptChildren", Argument.Delimiters.none, "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformArguments", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/FirArgumentList;", "transformChildren", "Lorg/jetbrains/kotlin/fir/FirElement;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/FirElement;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirResolvedArgumentList extends FirAbstractArgumentList {
    @Override // org.jetbrains.kotlin.fir.expressions.FirAbstractArgumentList, org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        Iterator<FirExpression> it = getArguments().iterator();
        while (it.hasNext()) {
            it.next().accept(visitor, data);
        }
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirArgumentList
    public List<FirExpression> getArguments() {
        Set<FirExpression> setKeySet = getMapping().keySet();
        setKeySet.getClass();
        return CollectionsKt.toList(setKeySet);
    }

    public abstract LinkedHashMap<FirExpression, FirValueParameter> getMapping();

    public abstract LinkedHashMap<FirExpression, FirValueParameter> getMappingIncludingContextArguments();

    public abstract FirArgumentList getOriginalArgumentList();

    @Override // org.jetbrains.kotlin.fir.expressions.FirArgumentList, org.jetbrains.kotlin.fir.FirElement
    public final KtSourceElement getSource() {
        FirArgumentList originalArgumentList = getOriginalArgumentList();
        if (originalArgumentList != null) {
            return originalArgumentList.getSource();
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAbstractArgumentList, org.jetbrains.kotlin.fir.expressions.FirArgumentList
    public abstract <D> FirArgumentList transformArguments(FirTransformer<? super D> transformer, D data);

    @Override // org.jetbrains.kotlin.fir.expressions.FirAbstractArgumentList, org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <D> FirElement transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        transformArguments(transformer, data);
        return this;
    }
}
