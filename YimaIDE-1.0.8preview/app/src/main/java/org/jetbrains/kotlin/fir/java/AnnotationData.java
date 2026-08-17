package org.jetbrains.kotlin.fir.java;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationArgumentMapping;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/AnnotationData;", Argument.Delimiters.none, "annotationTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "argumentsMapping", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationArgumentMapping;", "<init>", "(Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationArgumentMapping;)V", "getAnnotationTypeRef", "()Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "getArgumentsMapping", "()Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationArgumentMapping;", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class AnnotationData {
    private final FirResolvedTypeRef annotationTypeRef;
    private final FirAnnotationArgumentMapping argumentsMapping;

    public AnnotationData(FirResolvedTypeRef firResolvedTypeRef, FirAnnotationArgumentMapping firAnnotationArgumentMapping) {
        firResolvedTypeRef.getClass();
        firAnnotationArgumentMapping.getClass();
        this.annotationTypeRef = firResolvedTypeRef;
        this.argumentsMapping = firAnnotationArgumentMapping;
    }

    public final FirResolvedTypeRef getAnnotationTypeRef() {
        return this.annotationTypeRef;
    }

    public final FirAnnotationArgumentMapping getArgumentsMapping() {
        return this.argumentsMapping;
    }
}
