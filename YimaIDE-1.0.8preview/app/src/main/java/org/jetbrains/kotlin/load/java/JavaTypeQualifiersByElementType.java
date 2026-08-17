package org.jetbrains.kotlin.load.java;

import java.util.EnumMap;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B!\u0012\u0018\u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003j\u0002`\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\u000b\u001a\u0004\u0018\u00010\u00052\b\u0010\f\u001a\u0004\u0018\u00010\u0004H\u0086\u0002R#\u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003j\u0002`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/load/java/JavaTypeQualifiersByElementType;", "", "defaultQualifiers", "Ljava/util/EnumMap;", "Lorg/jetbrains/kotlin/load/java/AnnotationQualifierApplicabilityType;", "Lorg/jetbrains/kotlin/load/java/JavaDefaultQualifiers;", "Lorg/jetbrains/kotlin/load/java/QualifierByApplicabilityType;", "<init>", "(Ljava/util/EnumMap;)V", "getDefaultQualifiers", "()Ljava/util/EnumMap;", "get", "applicabilityType", "org.jetbrains.kotlin:compiler.common.jvm"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class JavaTypeQualifiersByElementType {
    private final EnumMap<AnnotationQualifierApplicabilityType, JavaDefaultQualifiers> defaultQualifiers;

    public JavaTypeQualifiersByElementType(EnumMap<AnnotationQualifierApplicabilityType, JavaDefaultQualifiers> enumMap) {
        enumMap.getClass();
        this.defaultQualifiers = enumMap;
    }

    public final JavaDefaultQualifiers get(AnnotationQualifierApplicabilityType applicabilityType) {
        return this.defaultQualifiers.get(applicabilityType);
    }

    public final EnumMap<AnnotationQualifierApplicabilityType, JavaDefaultQualifiers> getDefaultQualifiers() {
        return this.defaultQualifiers;
    }
}
