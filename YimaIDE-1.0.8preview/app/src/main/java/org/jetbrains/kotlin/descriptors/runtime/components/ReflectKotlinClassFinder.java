package org.jetbrains.kotlin.descriptors.runtime.components;

import java.io.InputStream;
import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.load.java.structure.JavaClass;
import org.jetbrains.kotlin.load.kotlin.KotlinClassFinder;
import org.jetbrains.kotlin.load.kotlin.KotlinClassFinder$Result$KotlinClass;
import org.jetbrains.kotlin.metadata.deserialization.MetadataVersion;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.serialization.deserialization.builtins.BuiltInSerializerProtocol;
import org.jetbrains.kotlin.serialization.deserialization.builtins.BuiltInsResourceLoader;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u001a\u0010\f\u001a\u0004\u0018\u00010\t2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u001a\u0010\f\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\n\u001a\u00020\u0018H\u0016J\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0017\u001a\u00020\u0018H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/runtime/components/ReflectKotlinClassFinder;", "Lorg/jetbrains/kotlin/load/kotlin/KotlinClassFinder;", "classLoader", "Ljava/lang/ClassLoader;", "<init>", "(Ljava/lang/ClassLoader;)V", "builtInsResourceLoader", "Lorg/jetbrains/kotlin/serialization/deserialization/builtins/BuiltInsResourceLoader;", "findKotlinClass", "Lorg/jetbrains/kotlin/load/kotlin/KotlinClassFinder$Result;", "fqName", Argument.Delimiters.none, "findKotlinClassOrContent", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "metadataVersion", "Lorg/jetbrains/kotlin/metadata/deserialization/MetadataVersion;", "javaClass", "Lorg/jetbrains/kotlin/load/java/structure/JavaClass;", "findMetadata", "Ljava/io/InputStream;", "findMetadataTopLevelClassesInPackage", Argument.Delimiters.none, "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "hasMetadataPackage", Argument.Delimiters.none, "findBuiltInsData", "org.jetbrains.kotlin:descriptors.runtime"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ReflectKotlinClassFinder implements KotlinClassFinder {
    private final BuiltInsResourceLoader builtInsResourceLoader;
    private final ClassLoader classLoader;

    public ReflectKotlinClassFinder(ClassLoader classLoader) {
        classLoader.getClass();
        this.classLoader = classLoader;
        this.builtInsResourceLoader = new BuiltInsResourceLoader();
    }

    private final KotlinClassFinder.Result findKotlinClass(String fqName) {
        ReflectKotlinClass reflectKotlinClassCreate;
        Class<?> clsTryLoadClass = ReflectJavaClassFinderKt.tryLoadClass(this.classLoader, fqName);
        if (clsTryLoadClass == null || (reflectKotlinClassCreate = ReflectKotlinClass.INSTANCE.create(clsTryLoadClass)) == null) {
            return null;
        }
        return new KotlinClassFinder$Result$KotlinClass(reflectKotlinClassCreate, null, 2, null);
    }

    public InputStream findBuiltInsData(FqName packageFqName) {
        packageFqName.getClass();
        if (packageFqName.startsWith(StandardNames.BUILT_INS_PACKAGE_NAME)) {
            return this.builtInsResourceLoader.loadResource(BuiltInSerializerProtocol.INSTANCE.getBuiltInsFilePath(packageFqName));
        }
        return null;
    }

    public KotlinClassFinder.Result findKotlinClassOrContent(JavaClass javaClass, MetadataVersion metadataVersion) {
        String strAsString;
        javaClass.getClass();
        metadataVersion.getClass();
        FqName fqName = javaClass.getFqName();
        if (fqName == null || (strAsString = fqName.asString()) == null) {
            return null;
        }
        return findKotlinClass(strAsString);
    }

    public InputStream findMetadata(ClassId classId) {
        classId.getClass();
        return null;
    }

    public Set<String> findMetadataTopLevelClassesInPackage(FqName packageFqName) {
        packageFqName.getClass();
        return null;
    }

    public boolean hasMetadataPackage(FqName fqName) {
        fqName.getClass();
        return false;
    }

    public KotlinClassFinder.Result findKotlinClassOrContent(ClassId classId, MetadataVersion metadataVersion) {
        classId.getClass();
        metadataVersion.getClass();
        return findKotlinClass(ReflectKotlinClassFinderKt.toRuntimeFqName(classId));
    }
}
