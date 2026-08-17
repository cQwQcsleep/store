package org.jetbrains.kotlin.descriptors;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.NotFoundClasses;
import org.jetbrains.kotlin.descriptors.impl.EmptyPackageFragmentDescriptor;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.storage.MemoizedFunctionToNotNull;
import org.jetbrains.kotlin.storage.StorageManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0002\u0015\u0016B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001c\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/NotFoundClasses;", Argument.Delimiters.none, "storageManager", "Lorg/jetbrains/kotlin/storage/StorageManager;", ModuleXmlParser.MODULE, "Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "<init>", "(Lorg/jetbrains/kotlin/storage/StorageManager;Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;)V", "packageFragments", "Lorg/jetbrains/kotlin/storage/MemoizedFunctionToNotNull;", "Lorg/jetbrains/kotlin/name/FqName;", "Lorg/jetbrains/kotlin/descriptors/PackageFragmentDescriptor;", "classes", "Lorg/jetbrains/kotlin/descriptors/NotFoundClasses$ClassRequest;", "Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", "getClass", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "typeParametersCount", Argument.Delimiters.none, Argument.Delimiters.none, "ClassRequest", "MockClassDescriptor", "org.jetbrains.kotlin:descriptors"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class NotFoundClasses {
    private final MemoizedFunctionToNotNull<ClassRequest, ClassDescriptor> classes;
    private final ModuleDescriptor module;
    private final MemoizedFunctionToNotNull<FqName, PackageFragmentDescriptor> packageFragments;
    private final StorageManager storageManager;

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0006HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/NotFoundClasses$ClassRequest;", Argument.Delimiters.none, "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "typeParametersCount", Argument.Delimiters.none, Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/name/ClassId;Ljava/util/List;)V", "getClassId", "()Lorg/jetbrains/kotlin/name/ClassId;", "getTypeParametersCount", "()Ljava/util/List;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:descriptors"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class ClassRequest {
        private final ClassId classId;
        private final List<Integer> typeParametersCount;

        public ClassRequest(ClassId classId, List<Integer> list) {
            classId.getClass();
            list.getClass();
            this.classId = classId;
            this.typeParametersCount = list;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ClassRequest copy$default(ClassRequest classRequest, ClassId classId, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                classId = classRequest.classId;
            }
            if ((i & 2) != 0) {
                list = classRequest.typeParametersCount;
            }
            return classRequest.copy(classId, list);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final ClassId getClassId() {
            return this.classId;
        }

        public final List<Integer> component2() {
            return this.typeParametersCount;
        }

        public final ClassRequest copy(ClassId classId, List<Integer> typeParametersCount) {
            classId.getClass();
            typeParametersCount.getClass();
            return new ClassRequest(classId, typeParametersCount);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ClassRequest)) {
                return false;
            }
            ClassRequest classRequest = (ClassRequest) other;
            return Intrinsics.areEqual(this.classId, classRequest.classId) && Intrinsics.areEqual(this.typeParametersCount, classRequest.typeParametersCount);
        }

        public final ClassId getClassId() {
            return this.classId;
        }

        public final List<Integer> getTypeParametersCount() {
            return this.typeParametersCount;
        }

        public int hashCode() {
            return (this.classId.hashCode() * 31) + this.typeParametersCount.hashCode();
        }

        public String toString() {
            return "ClassRequest(classId=" + this.classId + ", typeParametersCount=" + this.typeParametersCount + ')';
        }
    }

    public NotFoundClasses(StorageManager storageManager, ModuleDescriptor moduleDescriptor) {
        storageManager.getClass();
        moduleDescriptor.getClass();
        this.storageManager = storageManager;
        this.module = moduleDescriptor;
        this.packageFragments = storageManager.createMemoizedFunction(new Function1() { // from class: wia
            public final Object invoke(Object obj) {
                return NotFoundClasses.a(this.b, (FqName) obj);
            }
        });
        this.classes = storageManager.createMemoizedFunction(new Function1() { // from class: org.jetbrains.kotlin.descriptors.a
            public final Object invoke(Object obj) {
                return NotFoundClasses.b(this.b, (NotFoundClasses.ClassRequest) obj);
            }
        });
    }

    public static PackageFragmentDescriptor a(NotFoundClasses notFoundClasses, FqName fqName) {
        fqName.getClass();
        return new EmptyPackageFragmentDescriptor(notFoundClasses.module, fqName);
    }

    public static ClassDescriptor b(NotFoundClasses notFoundClasses, ClassRequest classRequest) {
        ClassDescriptor classDescriptor;
        classRequest.getClass();
        ClassId classId = classRequest.getClassId();
        List<Integer> listComponent2 = classRequest.component2();
        if (classId.isLocal()) {
            zwe.a("Unresolved local class: ", classId);
            return null;
        }
        ClassId outerClassId = classId.getOuterClassId();
        if (outerClassId == null || (classDescriptor = notFoundClasses.getClass(outerClassId, CollectionsKt.drop(listComponent2, 1))) == null) {
            classDescriptor = (ClassOrPackageFragmentDescriptor) notFoundClasses.packageFragments.invoke(classId.getPackageFqName());
        }
        ClassDescriptor classDescriptor2 = classDescriptor;
        boolean zIsNestedClass = classId.isNestedClass();
        StorageManager storageManager = notFoundClasses.storageManager;
        Name shortClassName = classId.getShortClassName();
        Integer num = (Integer) CollectionsKt.firstOrNull(listComponent2);
        return new MockClassDescriptor(storageManager, classDescriptor2, shortClassName, zIsNestedClass, num != null ? num.intValue() : 0);
    }

    public final ClassDescriptor getClass(ClassId classId, List<Integer> typeParametersCount) {
        classId.getClass();
        typeParametersCount.getClass();
        return (ClassDescriptor) this.classes.invoke(new ClassRequest(classId, typeParametersCount));
    }
}
