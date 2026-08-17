package org.jetbrains.kotlin.fir.expressions.impl;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameterKind;
import org.jetbrains.kotlin.fir.expressions.FirArgumentList;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirTransformerUtilKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B5\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\"\u0010\u0004\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007`\b¢\u0006\u0004\b\t\u0010\nJ$\u0010\u0012\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007`\bH\u0002J)\u0010\u0013\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00140\u00162\u0006\u0010\u0017\u001a\u0002H\u0014H\u0016¢\u0006\u0002\u0010\u0018R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fRV\u0010\u000e\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007`\b2\"\u0010\r\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007`\b@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010RV\u0010\u0004\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007`\b2\"\u0010\r\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007`\b@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/impl/FirResolvedArgumentListImpl;", "Lorg/jetbrains/kotlin/fir/expressions/impl/FirResolvedArgumentList;", "originalArgumentList", "Lorg/jetbrains/kotlin/fir/expressions/FirArgumentList;", "mapping", "Ljava/util/LinkedHashMap;", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "Lkotlin/collections/LinkedHashMap;", "<init>", "(Lorg/jetbrains/kotlin/fir/expressions/FirArgumentList;Ljava/util/LinkedHashMap;)V", "getOriginalArgumentList", "()Lorg/jetbrains/kotlin/fir/expressions/FirArgumentList;", "value", "mappingIncludingContextArguments", "getMappingIncludingContextArguments", "()Ljava/util/LinkedHashMap;", "getMapping", "filterArgumentMapping", "transformArguments", "D", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/FirArgumentList;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirResolvedArgumentListImpl extends FirResolvedArgumentList {
    private LinkedHashMap<FirExpression, FirValueParameter> mapping;
    private LinkedHashMap<FirExpression, FirValueParameter> mappingIncludingContextArguments;
    private final FirArgumentList originalArgumentList;

    public FirResolvedArgumentListImpl(FirArgumentList firArgumentList, LinkedHashMap<FirExpression, FirValueParameter> linkedHashMap) {
        linkedHashMap.getClass();
        this.originalArgumentList = firArgumentList;
        this.mappingIncludingContextArguments = linkedHashMap;
        this.mapping = filterArgumentMapping();
    }

    private final LinkedHashMap<FirExpression, FirValueParameter> filterArgumentMapping() {
        LinkedHashMap<FirExpression, FirValueParameter> mappingIncludingContextArguments = getMappingIncludingContextArguments();
        LinkedHashMap<FirExpression, FirValueParameter> linkedHashMap = new LinkedHashMap<>();
        for (Map.Entry<FirExpression, FirValueParameter> entry : mappingIncludingContextArguments.entrySet()) {
            if (entry.getValue().getValueParameterKind() == FirValueParameterKind.Regular) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        return linkedHashMap.size() == getMappingIncludingContextArguments().size() ? getMappingIncludingContextArguments() : linkedHashMap;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.impl.FirResolvedArgumentList
    public LinkedHashMap<FirExpression, FirValueParameter> getMapping() {
        return this.mapping;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.impl.FirResolvedArgumentList
    public LinkedHashMap<FirExpression, FirValueParameter> getMappingIncludingContextArguments() {
        return this.mappingIncludingContextArguments;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.impl.FirResolvedArgumentList
    public FirArgumentList getOriginalArgumentList() {
        return this.originalArgumentList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.expressions.impl.FirResolvedArgumentList, org.jetbrains.kotlin.fir.expressions.FirAbstractArgumentList, org.jetbrains.kotlin.fir.expressions.FirArgumentList
    public <D> FirArgumentList transformArguments(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        LinkedHashMap<FirExpression, FirValueParameter> mappingIncludingContextArguments = getMappingIncludingContextArguments();
        LinkedHashMap<FirExpression, FirValueParameter> linkedHashMap = new LinkedHashMap<>(MapsKt.mapCapacity(mappingIncludingContextArguments.size()));
        Iterator<T> it = mappingIncludingContextArguments.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            linkedHashMap.put((FirExpression) FirTransformerUtilKt.transformSingle((FirExpression) entry.getKey(), transformer, data), entry.getValue());
        }
        this.mappingIncludingContextArguments = linkedHashMap;
        this.mapping = filterArgumentMapping();
        return this;
    }
}
