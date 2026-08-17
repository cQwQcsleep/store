package org.jetbrains.kotlin.fir.java.declarations;

import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.builder.FirBuilderDsl;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationArgumentMapping;
import org.jetbrains.kotlin.fir.types.FirTypeRef;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@FirBuilderDsl
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0010\u001a\u00020\u0011R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fÊ\u0001\u0002\b\u0013¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaExternalAnnotationBuilder;", Argument.Delimiters.none, "<init>", "()V", "annotationTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "getAnnotationTypeRef", "()Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "setAnnotationTypeRef", "(Lorg/jetbrains/kotlin/fir/types/FirTypeRef;)V", "argumentMapping", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationArgumentMapping;", "getArgumentMapping", "()Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationArgumentMapping;", "setArgumentMapping", "(Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationArgumentMapping;)V", "build", "Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaExternalAnnotation;", "org.jetbrains.kotlin:fir-jvm", "Lorg/jetbrains/kotlin/fir/builder/FirBuilderDsl;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJavaExternalAnnotationBuilder {
    public FirTypeRef annotationTypeRef;
    public FirAnnotationArgumentMapping argumentMapping;

    public final FirJavaExternalAnnotation build() {
        return new FirJavaExternalAnnotation(getAnnotationTypeRef(), getArgumentMapping());
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirTypeRef getAnnotationTypeRef() throws UninitializedPropertyAccessException {
        FirTypeRef firTypeRef = this.annotationTypeRef;
        if (firTypeRef != null) {
            return firTypeRef;
        }
        Intrinsics.throwUninitializedPropertyAccessException("annotationTypeRef");
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirAnnotationArgumentMapping getArgumentMapping() throws UninitializedPropertyAccessException {
        FirAnnotationArgumentMapping firAnnotationArgumentMapping = this.argumentMapping;
        if (firAnnotationArgumentMapping != null) {
            return firAnnotationArgumentMapping;
        }
        Intrinsics.throwUninitializedPropertyAccessException("argumentMapping");
        return null;
    }

    public final void setAnnotationTypeRef(FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        this.annotationTypeRef = firTypeRef;
    }

    public final void setArgumentMapping(FirAnnotationArgumentMapping firAnnotationArgumentMapping) {
        firAnnotationArgumentMapping.getClass();
        this.argumentMapping = firAnnotationArgumentMapping;
    }
}
