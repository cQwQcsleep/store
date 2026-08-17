package org.jetbrains.kotlin.load.kotlin;

import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.serialization.deserialization.ClassData;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\u000fJ\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0004H&J\u0010\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0007H&J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\u0006\u0010\n\u001a\u00020\u0004H&J\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0003H&J\b\u0010\r\u001a\u00020\u000eH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0010À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/load/kotlin/PackagePartProvider;", "", "findPackageParts", "", "", "packageFqName", "computePackageSetWithNonClassDeclarations", "", "getAnnotationsOnBinaryModule", "Lorg/jetbrains/kotlin/name/ClassId;", "moduleName", "getAllOptionalAnnotationClasses", "Lorg/jetbrains/kotlin/serialization/deserialization/ClassData;", "mayHaveOptionalAnnotationClasses", "", "Empty", "org.jetbrains.kotlin:deserialization.common.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface PackagePartProvider {

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\"\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00052\u0006\u0010\n\u001a\u00020\u0006H\u0016J\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0005H\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\u000e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0010H\u0016¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/load/kotlin/PackagePartProvider$Empty;", "Lorg/jetbrains/kotlin/load/kotlin/PackagePartProvider;", "<init>", "()V", "findPackageParts", "", "", "packageFqName", "getAnnotationsOnBinaryModule", "Lorg/jetbrains/kotlin/name/ClassId;", "moduleName", "getAllOptionalAnnotationClasses", "Lorg/jetbrains/kotlin/serialization/deserialization/ClassData;", "mayHaveOptionalAnnotationClasses", "", "computePackageSetWithNonClassDeclarations", "", "org.jetbrains.kotlin:deserialization.common.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Empty implements PackagePartProvider {
        public static final Empty INSTANCE = new Empty();

        private Empty() {
        }

        @Override // org.jetbrains.kotlin.load.kotlin.PackagePartProvider
        public Set<String> computePackageSetWithNonClassDeclarations() {
            return SetsKt.emptySet();
        }

        @Override // org.jetbrains.kotlin.load.kotlin.PackagePartProvider
        public List<String> findPackageParts(String packageFqName) {
            packageFqName.getClass();
            return CollectionsKt.emptyList();
        }

        @Override // org.jetbrains.kotlin.load.kotlin.PackagePartProvider
        public List<ClassData> getAllOptionalAnnotationClasses() {
            return CollectionsKt.emptyList();
        }

        @Override // org.jetbrains.kotlin.load.kotlin.PackagePartProvider
        public List<ClassId> getAnnotationsOnBinaryModule(String moduleName) {
            moduleName.getClass();
            return CollectionsKt.emptyList();
        }

        @Override // org.jetbrains.kotlin.load.kotlin.PackagePartProvider
        public boolean mayHaveOptionalAnnotationClasses() {
            return false;
        }
    }

    Set<String> computePackageSetWithNonClassDeclarations();

    List<String> findPackageParts(String packageFqName);

    List<ClassData> getAllOptionalAnnotationClasses();

    List<ClassId> getAnnotationsOnBinaryModule(String moduleName);

    boolean mayHaveOptionalAnnotationClasses();
}
