package org.jetbrains.kotlin.fir.expressions.impl;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.expressions.FirArgumentList;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirTransformerUtilKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B=\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012*\u0010\u0004\u001a&\u0012\u0004\u0012\u00020\u0006\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00070\u0005j\u0012\u0012\u0004\u0012\u00020\u0006\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u0007`\b¢\u0006\u0004\b\t\u0010\nJ$\u0010\u0013\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007`\bH\u0002J)\u0010\u0018\u001a\u00020\u0000\"\u0004\b\u0000\u0010\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00190\u001b2\u0006\u0010\u001c\u001a\u0002H\u0019H\u0016¢\u0006\u0002\u0010\u001dR\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR2\u0010\u0004\u001a&\u0012\u0004\u0012\u00020\u0006\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00070\u0005j\u0012\u0012\u0004\u0012\u00020\u0006\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u0007`\bX\u0082\u000e¢\u0006\u0002\n\u0000RV\u0010\u000e\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007`\b2\"\u0010\r\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007`\b@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R0\u0010\u0011\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007`\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010R\u001a\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00060\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/impl/FirResolvedArgumentListForErrorCall;", "Lorg/jetbrains/kotlin/fir/expressions/impl/FirResolvedArgumentList;", "originalArgumentList", "Lorg/jetbrains/kotlin/fir/expressions/FirArgumentList;", "_mapping", "Ljava/util/LinkedHashMap;", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "Lkotlin/collections/LinkedHashMap;", "<init>", "(Lorg/jetbrains/kotlin/fir/expressions/FirArgumentList;Ljava/util/LinkedHashMap;)V", "getOriginalArgumentList", "()Lorg/jetbrains/kotlin/fir/expressions/FirArgumentList;", "value", "mapping", "getMapping", "()Ljava/util/LinkedHashMap;", "mappingIncludingContextArguments", "getMappingIncludingContextArguments", "computeMapping", "arguments", Argument.Delimiters.none, "getArguments", "()Ljava/util/List;", "transformArguments", "D", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/impl/FirResolvedArgumentListForErrorCall;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirResolvedArgumentListForErrorCall extends FirResolvedArgumentList {
    private LinkedHashMap<FirExpression, ? extends FirValueParameter> _mapping;
    private LinkedHashMap<FirExpression, FirValueParameter> mapping;
    private final FirArgumentList originalArgumentList;

    public FirResolvedArgumentListForErrorCall(FirArgumentList firArgumentList, LinkedHashMap<FirExpression, ? extends FirValueParameter> linkedHashMap) {
        linkedHashMap.getClass();
        this.originalArgumentList = firArgumentList;
        this._mapping = linkedHashMap;
        this.mapping = computeMapping();
    }

    private final LinkedHashMap<FirExpression, FirValueParameter> computeMapping() {
        LinkedHashMap<FirExpression, ? extends FirValueParameter> linkedHashMap = this._mapping;
        LinkedHashMap<FirExpression, FirValueParameter> linkedHashMap2 = new LinkedHashMap<>();
        for (Map.Entry<FirExpression, ? extends FirValueParameter> entry : linkedHashMap.entrySet()) {
            if (entry.getValue() != null) {
                linkedHashMap2.put(entry.getKey(), entry.getValue());
            }
        }
        return linkedHashMap2;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.impl.FirResolvedArgumentList, org.jetbrains.kotlin.fir.expressions.FirArgumentList
    public List<FirExpression> getArguments() {
        Set<FirExpression> setKeySet = this._mapping.keySet();
        setKeySet.getClass();
        return CollectionsKt.toList(setKeySet);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.impl.FirResolvedArgumentList
    public LinkedHashMap<FirExpression, FirValueParameter> getMapping() {
        return this.mapping;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.impl.FirResolvedArgumentList
    public LinkedHashMap<FirExpression, FirValueParameter> getMappingIncludingContextArguments() {
        return getMapping();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.impl.FirResolvedArgumentList
    public FirArgumentList getOriginalArgumentList() {
        return this.originalArgumentList;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.impl.FirResolvedArgumentList, org.jetbrains.kotlin.fir.expressions.FirAbstractArgumentList, org.jetbrains.kotlin.fir.expressions.FirArgumentList
    public <D> FirResolvedArgumentListForErrorCall transformArguments(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        LinkedHashMap<FirExpression, ? extends FirValueParameter> linkedHashMap = this._mapping;
        LinkedHashMap<FirExpression, ? extends FirValueParameter> linkedHashMap2 = new LinkedHashMap<>(MapsKt.mapCapacity(linkedHashMap.size()));
        Iterator<T> it = linkedHashMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            linkedHashMap2.put((FirExpression) FirTransformerUtilKt.transformSingle((FirExpression) entry.getKey(), transformer, data), entry.getValue());
        }
        this._mapping = linkedHashMap2;
        this.mapping = computeMapping();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.impl.FirResolvedArgumentList, org.jetbrains.kotlin.fir.expressions.FirAbstractArgumentList, org.jetbrains.kotlin.fir.expressions.FirArgumentList
    public /* bridge */ /* synthetic */ FirArgumentList transformArguments(FirTransformer firTransformer, Object obj) {
        return transformArguments((FirTransformer<? super Object>) firTransformer, obj);
    }
}
