package org.jetbrains.kotlin.name;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a\f\u0010\u0006\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a\f\u0010\u0007\u001a\u00020\u0001*\u00020\u0002H\u0002\"\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"jvmId", "Lorg/jetbrains/kotlin/name/ClassId;", "", "JAVA_LANG_PACKAGE", "Lorg/jetbrains/kotlin/name/FqName;", "JAVA_LANG_ANNOTATION_PACKAGE", "javaLangId", "javaAnnotationId", "org.jetbrains.kotlin:compiler.common.jvm"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class JvmStandardClassIdsKt {
    private static final FqName JAVA_LANG_ANNOTATION_PACKAGE;
    private static final FqName JAVA_LANG_PACKAGE;

    static {
        FqName fqName = new FqName("java.lang");
        JAVA_LANG_PACKAGE = fqName;
        Name nameIdentifier = Name.identifier("annotation");
        nameIdentifier.getClass();
        JAVA_LANG_ANNOTATION_PACKAGE = fqName.child(nameIdentifier);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ClassId javaAnnotationId(String str) {
        FqName fqName = JAVA_LANG_ANNOTATION_PACKAGE;
        Name nameIdentifier = Name.identifier(str);
        nameIdentifier.getClass();
        return new ClassId(fqName, nameIdentifier);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ClassId javaLangId(String str) {
        FqName fqName = JAVA_LANG_PACKAGE;
        Name nameIdentifier = Name.identifier(str);
        nameIdentifier.getClass();
        return new ClassId(fqName, nameIdentifier);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ClassId jvmId(String str) {
        FqName base_jvm_package = JvmStandardClassIds.INSTANCE.getBASE_JVM_PACKAGE();
        Name nameIdentifier = Name.identifier(str);
        nameIdentifier.getClass();
        return new ClassId(base_jvm_package, nameIdentifier);
    }
}
