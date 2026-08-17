package org.jetbrains.kotlin.fir.types.builder;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.properties.Delegates;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder;
import org.jetbrains.kotlin.fir.builder.FirBuilderDsl;
import org.jetbrains.kotlin.fir.builder.FirBuilderDslKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.types.FirQualifierPart;
import org.jetbrains.kotlin.fir.types.FirUserTypeRef;
import org.jetbrains.kotlin.fir.types.impl.FirUserTypeRefImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@FirBuilderDsl
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\b\u0017\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u001a\u001a\u00020\u001bH\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR+\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u00108V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u000eÊ\u0001\u0002\b\u001d¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/builder/FirUserTypeRefBuilder;", "Lorg/jetbrains/kotlin/fir/builder/FirAnnotationContainerBuilder;", "<init>", "()V", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "setSource", "(Lorg/jetbrains/kotlin/KtSourceElement;)V", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations", "()Ljava/util/List;", "<set-?>", Argument.Delimiters.none, "isMarkedNullable", "()Z", "setMarkedNullable", "(Z)V", "isMarkedNullable$delegate", "Lkotlin/properties/ReadWriteProperty;", "qualifier", "Lorg/jetbrains/kotlin/fir/types/FirQualifierPart;", "getQualifier", "build", "Lorg/jetbrains/kotlin/fir/types/FirUserTypeRef;", "org.jetbrains.kotlin:tree", "Lorg/jetbrains/kotlin/fir/builder/FirBuilderDsl;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class FirUserTypeRefBuilder implements FirAnnotationContainerBuilder {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new MutablePropertyReference1Impl<>(FirUserTypeRefBuilder.class, "isMarkedNullable", "isMarkedNullable()Z", 0)};
    private final List<FirAnnotation> annotations = new ArrayList();

    /* JADX INFO: renamed from: isMarkedNullable$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty isMarkedNullable = Delegates.INSTANCE.notNull();
    private final List<FirQualifierPart> qualifier = new ArrayList();
    public KtSourceElement source;

    @Override // org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder
    public FirUserTypeRef build() {
        return new FirUserTypeRefImpl(getSource(), isMarkedNullable(), this.qualifier, FirBuilderDslKt.toMutableOrEmpty(getAnnotations()), (DefaultConstructorMarker) null);
    }

    @Override // org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public List<FirAnnotation> getAnnotations() {
        return this.annotations;
    }

    public final List<FirQualifierPart> getQualifier() {
        return this.qualifier;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final KtSourceElement getSource() throws UninitializedPropertyAccessException {
        KtSourceElement ktSourceElement = this.source;
        if (ktSourceElement != null) {
            return ktSourceElement;
        }
        Intrinsics.throwUninitializedPropertyAccessException("source");
        return null;
    }

    public boolean isMarkedNullable() {
        return ((Boolean) this.isMarkedNullable.getValue(this, $$delegatedProperties[0])).booleanValue();
    }

    public void setMarkedNullable(boolean z) {
        this.isMarkedNullable.setValue(this, $$delegatedProperties[0], Boolean.valueOf(z));
    }

    public final void setSource(KtSourceElement ktSourceElement) {
        ktSourceElement.getClass();
        this.source = ktSourceElement;
    }
}
