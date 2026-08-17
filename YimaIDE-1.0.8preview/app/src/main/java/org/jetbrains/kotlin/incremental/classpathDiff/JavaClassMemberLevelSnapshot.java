package org.jetbrains.kotlin.incremental.classpathDiff;

import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\t\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/incremental/classpathDiff/JavaClassMemberLevelSnapshot;", "", "classAbiExcludingMembers", "Lorg/jetbrains/kotlin/incremental/classpathDiff/JavaElementSnapshot;", "fieldsAbi", "", "methodsAbi", "<init>", "(Lorg/jetbrains/kotlin/incremental/classpathDiff/JavaElementSnapshot;Ljava/util/List;Ljava/util/List;)V", "getClassAbiExcludingMembers", "()Lorg/jetbrains/kotlin/incremental/classpathDiff/JavaElementSnapshot;", "getFieldsAbi", "()Ljava/util/List;", "getMethodsAbi", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class JavaClassMemberLevelSnapshot {
    private final JavaElementSnapshot classAbiExcludingMembers;
    private final List<JavaElementSnapshot> fieldsAbi;
    private final List<JavaElementSnapshot> methodsAbi;

    public JavaClassMemberLevelSnapshot(JavaElementSnapshot javaElementSnapshot, List<JavaElementSnapshot> list, List<JavaElementSnapshot> list2) {
        javaElementSnapshot.getClass();
        list.getClass();
        list2.getClass();
        this.classAbiExcludingMembers = javaElementSnapshot;
        this.fieldsAbi = list;
        this.methodsAbi = list2;
    }

    public final JavaElementSnapshot getClassAbiExcludingMembers() {
        return this.classAbiExcludingMembers;
    }

    public final List<JavaElementSnapshot> getFieldsAbi() {
        return this.fieldsAbi;
    }

    public final List<JavaElementSnapshot> getMethodsAbi() {
        return this.methodsAbi;
    }
}
