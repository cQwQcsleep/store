package org.jetbrains.kotlin.analysis.decompiler.stub;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.constant.ConstantValue;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0005¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u0019\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0005HÆ\u0003J-\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0018\b\u0002\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0005HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR!\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/analysis/decompiler/stub/AnnotationWithArgs;", "", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "args", "", "Lorg/jetbrains/kotlin/name/Name;", "Lorg/jetbrains/kotlin/constant/ConstantValue;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/name/ClassId;Ljava/util/Map;)V", "getClassId", "()Lorg/jetbrains/kotlin/name/ClassId;", "getArgs", "()Ljava/util/Map;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.jetbrains.kotlin:decompiler-to-stubs"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class AnnotationWithArgs {
    private final Map<Name, ConstantValue<?>> args;
    private final ClassId classId;

    public AnnotationWithArgs(ClassId classId, Map<Name, ? extends ConstantValue<?>> map) {
        classId.getClass();
        map.getClass();
        this.classId = classId;
        this.args = map;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AnnotationWithArgs copy$default(AnnotationWithArgs annotationWithArgs, ClassId classId, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            classId = annotationWithArgs.classId;
        }
        if ((i & 2) != 0) {
            map = annotationWithArgs.args;
        }
        return annotationWithArgs.copy(classId, map);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ClassId getClassId() {
        return this.classId;
    }

    public final Map<Name, ConstantValue<?>> component2() {
        return this.args;
    }

    public final AnnotationWithArgs copy(ClassId classId, Map<Name, ? extends ConstantValue<?>> args) {
        classId.getClass();
        args.getClass();
        return new AnnotationWithArgs(classId, args);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AnnotationWithArgs)) {
            return false;
        }
        AnnotationWithArgs annotationWithArgs = (AnnotationWithArgs) other;
        return Intrinsics.areEqual(this.classId, annotationWithArgs.classId) && Intrinsics.areEqual(this.args, annotationWithArgs.args);
    }

    public final Map<Name, ConstantValue<?>> getArgs() {
        return this.args;
    }

    public final ClassId getClassId() {
        return this.classId;
    }

    public int hashCode() {
        return (this.classId.hashCode() * 31) + this.args.hashCode();
    }

    public String toString() {
        return "AnnotationWithArgs(classId=" + this.classId + ", args=" + this.args + Util.C_PARAM_END;
    }
}
