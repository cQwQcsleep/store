package org.jetbrains.kotlin.cli.jvm.config;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.CompilerConfigurationKey;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.load.java.JavaClassesTracker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/config/ClassicFrontendSpecificJvmConfigurationKeys;", Argument.Delimiters.none, "<init>", "()V", "JAVA_CLASSES_TRACKER", "Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", "Lorg/jetbrains/kotlin/load/java/JavaClassesTracker;", "org.jetbrains.kotlin:cli-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ClassicFrontendSpecificJvmConfigurationKeys {
    public static final ClassicFrontendSpecificJvmConfigurationKeys INSTANCE = new ClassicFrontendSpecificJvmConfigurationKeys();
    public static final CompilerConfigurationKey<JavaClassesTracker> JAVA_CLASSES_TRACKER = CompilerConfigurationKey.INSTANCE.create("JAVA_CLASSES_TRACKER");

    private ClassicFrontendSpecificJvmConfigurationKeys() {
    }
}
