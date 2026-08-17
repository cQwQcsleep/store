package org.jetbrains.kotlin.fir.analysis.checkers;

import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.types.ConeAttribute;
import org.jetbrains.kotlin.fir.types.FirTypeRef;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u000e\u001a\u00020\u00002\b\u0010\u000f\u001a\u0004\u0018\u00010\u0000H\u0016J\u0012\u0010\u0010\u001a\u00020\u00002\b\u0010\u000f\u001a\u0004\u0018\u00010\u0000H\u0016J\u0012\u0010\u0011\u001a\u00020\u00002\b\u0010\u000f\u001a\u0004\u0018\u00010\u0000H\u0016J\u0012\u0010\u0012\u001a\u00020\u00132\b\u0010\u000f\u001a\u0004\u0018\u00010\u0000H\u0016J\n\u0010\u0014\u001a\u00020\u0015H\u0096\u0080\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u001c\u0010\u0016\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00000\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/SourceAttribute;", "Lorg/jetbrains/kotlin/fir/types/ConeAttribute;", "data", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirTypeRefSource;", "<init>", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/FirTypeRefSource;)V", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "typeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "getTypeRef", "()Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "union", "other", "intersect", "add", "isSubtypeOf", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "key", "Lkotlin/reflect/KClass;", "getKey", "()Lkotlin/reflect/KClass;", "keepInInferredDeclarationType", "getKeepInInferredDeclarationType", "()Z", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class SourceAttribute extends ConeAttribute<SourceAttribute> {
    private final FirTypeRefSource data;

    public SourceAttribute(FirTypeRefSource firTypeRefSource) {
        firTypeRefSource.getClass();
        this.data = firTypeRefSource;
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeAttribute
    public boolean getKeepInInferredDeclarationType() {
        return false;
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeAttribute
    public KClass<? extends SourceAttribute> getKey() {
        return Reflection.getOrCreateKotlinClass(SourceAttribute.class);
    }

    public final KtSourceElement getSource() {
        return this.data.getSource();
    }

    public final FirTypeRef getTypeRef() {
        return this.data.getTypeRef();
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeAttribute
    public String toString() {
        return "SourceAttribute: " + this.data;
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeAttribute
    public SourceAttribute add(SourceAttribute other) {
        return other == null ? this : other;
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeAttribute
    public SourceAttribute intersect(SourceAttribute other) {
        return other == null ? this : other;
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeAttribute
    public boolean isSubtypeOf(SourceAttribute other) {
        return true;
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeAttribute
    public SourceAttribute union(SourceAttribute other) {
        return other == null ? this : other;
    }
}
