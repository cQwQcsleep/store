package org.jetbrains.kotlin.fir.declarations;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B#\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/AnnotationsPosition;", Argument.Delimiters.none, "backingFieldAnnotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "propertyAnnotations", "<init>", "(Ljava/util/List;Ljava/util/List;)V", "getBackingFieldAnnotations", "()Ljava/util/List;", "getPropertyAnnotations", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class AnnotationsPosition {
    private final List<FirAnnotation> backingFieldAnnotations;
    private final List<FirAnnotation> propertyAnnotations;

    /* JADX WARN: Multi-variable type inference failed */
    public AnnotationsPosition(List<? extends FirAnnotation> list, List<? extends FirAnnotation> list2) {
        list.getClass();
        list2.getClass();
        this.backingFieldAnnotations = list;
        this.propertyAnnotations = list2;
    }

    public final List<FirAnnotation> getBackingFieldAnnotations() {
        return this.backingFieldAnnotations;
    }

    public final List<FirAnnotation> getPropertyAnnotations() {
        return this.propertyAnnotations;
    }
}
