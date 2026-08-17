package org.jetbrains.kotlin.incremental.impl;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.incremental.impl.ClassNodeSnapshotter;
import org.jetbrains.org.objectweb.asm.ClassWriter;
import org.jetbrains.org.objectweb.asm.tree.AnnotationNode;
import org.jetbrains.org.objectweb.asm.tree.ClassNode;
import org.jetbrains.org.objectweb.asm.tree.FieldNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\"\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\nJ\u000e\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000eJ\u0016\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0006\u001a\u00020\u0007J\b\u0010\u0016\u001a\u00020\u0007H\u0002¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/incremental/impl/ClassNodeSnapshotter;", "", "<init>", "()V", "snapshotClass", "", "classNode", "Lorg/jetbrains/org/objectweb/asm/tree/ClassNode;", "snapshotClassExcludingMembers", "alsoExcludeKotlinMetaData", "", "alsoExcludeDebugInfo", "snapshotField", "fieldNode", "Lorg/jetbrains/org/objectweb/asm/tree/FieldNode;", "snapshotMethod", "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "classVersion", "", "sortClassMembers", "", "emptyClass", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ClassNodeSnapshotter {
    public static final ClassNodeSnapshotter INSTANCE = new ClassNodeSnapshotter();

    private ClassNodeSnapshotter() {
    }

    public static Comparable a(MethodNode methodNode) {
        return methodNode.desc;
    }

    public static Comparable b(FieldNode fieldNode) {
        return fieldNode.name;
    }

    public static Comparable c(MethodNode methodNode) {
        return methodNode.name;
    }

    public static Comparable d(FieldNode fieldNode) {
        return fieldNode.desc;
    }

    private final ClassNode emptyClass() {
        ClassNode classNode = new ClassNode();
        classNode.name = "SomeClass";
        return classNode;
    }

    public static /* synthetic */ long snapshotClassExcludingMembers$default(ClassNodeSnapshotter classNodeSnapshotter, ClassNode classNode, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            z2 = false;
        }
        return classNodeSnapshotter.snapshotClassExcludingMembers(classNode, z, z2);
    }

    public final long snapshotClass(ClassNode classNode) {
        classNode.getClass();
        ClassWriter classWriter = new ClassWriter(0);
        classNode.accept(classWriter);
        byte[] byteArray = classWriter.toByteArray();
        byteArray.getClass();
        return UtilsKt.hashToLong(byteArray);
    }

    public final long snapshotClassExcludingMembers(ClassNode classNode, boolean alsoExcludeKotlinMetaData, boolean alsoExcludeDebugInfo) {
        ArrayList arrayList;
        classNode.getClass();
        List list = classNode.fields;
        List list2 = classNode.methods;
        List list3 = classNode.visibleAnnotations;
        List list4 = classNode.invisibleAnnotations;
        classNode.fields = CollectionsKt.emptyList();
        classNode.methods = CollectionsKt.emptyList();
        ArrayList arrayList2 = null;
        if (alsoExcludeKotlinMetaData) {
            if (list3 != null) {
                arrayList = new ArrayList();
                for (Object obj : list3) {
                    if (!Intrinsics.areEqual(((AnnotationNode) obj).desc, "Lkotlin/Metadata;")) {
                        arrayList.add(obj);
                    }
                }
            } else {
                arrayList = null;
            }
            classNode.visibleAnnotations = arrayList;
        }
        if (alsoExcludeDebugInfo) {
            if (list4 != null) {
                arrayList2 = new ArrayList();
                for (Object obj2 : list4) {
                    if (!Intrinsics.areEqual(((AnnotationNode) obj2).desc, "Lkotlin/jvm/internal/SourceDebugExtension;")) {
                        arrayList2.add(obj2);
                    }
                }
            }
            classNode.invisibleAnnotations = arrayList2;
        }
        long jSnapshotClass = snapshotClass(classNode);
        classNode.fields = list;
        classNode.methods = list2;
        classNode.visibleAnnotations = list3;
        classNode.invisibleAnnotations = list4;
        return jSnapshotClass;
    }

    public final long snapshotField(FieldNode fieldNode) {
        fieldNode.getClass();
        ClassNode classNodeEmptyClass = emptyClass();
        classNodeEmptyClass.fields.add(fieldNode);
        return snapshotClass(classNodeEmptyClass);
    }

    public final long snapshotMethod(MethodNode methodNode, int classVersion) {
        methodNode.getClass();
        ClassNode classNodeEmptyClass = emptyClass();
        classNodeEmptyClass.version = classVersion;
        classNodeEmptyClass.methods.add(methodNode);
        return snapshotClass(classNodeEmptyClass);
    }

    public final void sortClassMembers(ClassNode classNode) {
        classNode.getClass();
        List list = classNode.fields;
        list.getClass();
        CollectionsKt.sortWith(list, ComparisonsKt.compareBy(new Function1[]{new Function1() { // from class: jw1
            public final Object invoke(Object obj) {
                return ClassNodeSnapshotter.b((FieldNode) obj);
            }
        }, new Function1() { // from class: kw1
            public final Object invoke(Object obj) {
                return ClassNodeSnapshotter.d((FieldNode) obj);
            }
        }}));
        List list2 = classNode.methods;
        list2.getClass();
        CollectionsKt.sortWith(list2, ComparisonsKt.compareBy(new Function1[]{new Function1() { // from class: lw1
            public final Object invoke(Object obj) {
                return ClassNodeSnapshotter.c((MethodNode) obj);
            }
        }, new Function1() { // from class: mw1
            public final Object invoke(Object obj) {
                return ClassNodeSnapshotter.a((MethodNode) obj);
            }
        }}));
    }
}
