package org.jetbrains.kotlin.incremental.classpathDiff.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.transform.OutputKeys;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.load.java.structure.impl.classFiles.CommonMixinsKt;
import org.jetbrains.kotlin.resolve.jvm.JvmClassName;
import org.jetbrains.org.objectweb.asm.ClassVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004JI\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u00062\b\u0010\u0011\u001a\u0004\u0018\u00010\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u00062\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0014H\u0016¢\u0006\u0002\u0010\u0015J\u0006\u0010\u0016\u001a\u00020\u0006J\u0006\u0010\u0017\u001a\u00020\bJ\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\tR\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/incremental/classpathDiff/impl/BasicClassInfoClassVisitor;", "Lorg/jetbrains/org/objectweb/asm/ClassVisitor;", "cv", "<init>", "(Lorg/jetbrains/org/objectweb/asm/ClassVisitor;)V", "className", "", "classAccess", "", "Ljava/lang/Integer;", "supertypeNames", "", "visit", "", OutputKeys.VERSION, "access", "name", "signature", "superName", "interfaces", "", "(IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)V", "getClassName", "getAccessFlags", "getSupertypes", "", "Lorg/jetbrains/kotlin/resolve/jvm/JvmClassName;", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class BasicClassInfoClassVisitor extends ClassVisitor {
    private Integer classAccess;
    private String className;
    private final List<String> supertypeNames;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BasicClassInfoClassVisitor(ClassVisitor classVisitor) {
        super(CommonMixinsKt.ASM_API_VERSION_FOR_CLASS_READING, classVisitor);
        classVisitor.getClass();
        this.supertypeNames = new ArrayList();
    }

    public final int getAccessFlags() {
        Integer num = this.classAccess;
        num.getClass();
        return num.intValue();
    }

    public final String getClassName() {
        String str = this.className;
        str.getClass();
        return str;
    }

    public final List<JvmClassName> getSupertypes() {
        List<String> list = this.supertypeNames;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(JvmClassName.byInternalName((String) it.next()));
        }
        return arrayList;
    }

    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        name.getClass();
        this.className = name;
        this.classAccess = Integer.valueOf(access);
        if (superName != null) {
            this.supertypeNames.add(superName);
        }
        if (interfaces != null) {
            CollectionsKt.addAll(this.supertypeNames, interfaces);
        }
        super.visit(version, access, name, signature, superName, interfaces);
    }
}
