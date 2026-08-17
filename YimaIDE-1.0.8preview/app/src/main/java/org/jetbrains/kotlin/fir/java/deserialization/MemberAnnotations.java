package org.jetbrains.kotlin.fir.java.deserialization;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.load.kotlin.MemberSignature;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B5\u0012\u0018\u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0003\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0003HÆ\u0003J\u0015\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t0\bHÆ\u0003J;\u0010\u0011\u001a\u00020\u00002\u001a\b\u0002\u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00032\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t0\bHÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004R#\u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\r¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/deserialization/MemberAnnotations;", Argument.Delimiters.none, "memberAnnotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/load/kotlin/MemberSignature;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "annotationMethodsDefaultValues", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "<init>", "(Ljava/util/Map;Ljava/util/Map;)V", "getMemberAnnotations", "()Ljava/util/Map;", "getAnnotationMethodsDefaultValues", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class MemberAnnotations {
    private final Map<MemberSignature, FirExpression> annotationMethodsDefaultValues;
    private final Map<MemberSignature, List<FirAnnotation>> memberAnnotations;

    /* JADX WARN: Multi-variable type inference failed */
    public MemberAnnotations(Map<MemberSignature, List<FirAnnotation>> map, Map<MemberSignature, ? extends FirExpression> map2) {
        map.getClass();
        map2.getClass();
        this.memberAnnotations = map;
        this.annotationMethodsDefaultValues = map2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MemberAnnotations copy$default(MemberAnnotations memberAnnotations, Map map, Map map2, int i, Object obj) {
        if ((i & 1) != 0) {
            map = memberAnnotations.memberAnnotations;
        }
        if ((i & 2) != 0) {
            map2 = memberAnnotations.annotationMethodsDefaultValues;
        }
        return memberAnnotations.copy(map, map2);
    }

    public final Map<MemberSignature, List<FirAnnotation>> component1() {
        return this.memberAnnotations;
    }

    public final Map<MemberSignature, FirExpression> component2() {
        return this.annotationMethodsDefaultValues;
    }

    public final MemberAnnotations copy(Map<MemberSignature, List<FirAnnotation>> memberAnnotations, Map<MemberSignature, ? extends FirExpression> annotationMethodsDefaultValues) {
        memberAnnotations.getClass();
        annotationMethodsDefaultValues.getClass();
        return new MemberAnnotations(memberAnnotations, annotationMethodsDefaultValues);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MemberAnnotations)) {
            return false;
        }
        MemberAnnotations memberAnnotations = (MemberAnnotations) other;
        return Intrinsics.areEqual(this.memberAnnotations, memberAnnotations.memberAnnotations) && Intrinsics.areEqual(this.annotationMethodsDefaultValues, memberAnnotations.annotationMethodsDefaultValues);
    }

    public final Map<MemberSignature, FirExpression> getAnnotationMethodsDefaultValues() {
        return this.annotationMethodsDefaultValues;
    }

    public final Map<MemberSignature, List<FirAnnotation>> getMemberAnnotations() {
        return this.memberAnnotations;
    }

    public int hashCode() {
        return (this.memberAnnotations.hashCode() * 31) + this.annotationMethodsDefaultValues.hashCode();
    }

    public String toString() {
        return "MemberAnnotations(memberAnnotations=" + this.memberAnnotations + ", annotationMethodsDefaultValues=" + this.annotationMethodsDefaultValues + ')';
    }
}
