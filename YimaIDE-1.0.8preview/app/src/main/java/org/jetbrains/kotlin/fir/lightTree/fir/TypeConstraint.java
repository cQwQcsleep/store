package org.jetbrains.kotlin.fir.lightTree.fir;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.types.FirTypeRef;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B/\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/lightTree/fir/TypeConstraint;", Argument.Delimiters.none, "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "identifier", Argument.Delimiters.none, "firTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "<init>", "(Ljava/util/List;Ljava/lang/String;Lorg/jetbrains/kotlin/fir/types/FirTypeRef;Lorg/jetbrains/kotlin/KtSourceElement;)V", "getAnnotations", "()Ljava/util/List;", "getIdentifier", "()Ljava/lang/String;", "getFirTypeRef", "()Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "org.jetbrains.kotlin.fir:light-tree2fir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TypeConstraint {
    private final List<FirAnnotation> annotations;
    private final FirTypeRef firTypeRef;
    private final String identifier;
    private final KtSourceElement source;

    /* JADX WARN: Multi-variable type inference failed */
    public TypeConstraint(List<? extends FirAnnotation> list, String str, FirTypeRef firTypeRef, KtSourceElement ktSourceElement) {
        list.getClass();
        firTypeRef.getClass();
        ktSourceElement.getClass();
        this.annotations = list;
        this.identifier = str;
        this.firTypeRef = firTypeRef;
        this.source = ktSourceElement;
    }

    public final List<FirAnnotation> getAnnotations() {
        return this.annotations;
    }

    public final FirTypeRef getFirTypeRef() {
        return this.firTypeRef;
    }

    public final String getIdentifier() {
        return this.identifier;
    }

    public final KtSourceElement getSource() {
        return this.source;
    }
}
