package org.jetbrains.kotlin.cli.jvm.compiler;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.asJava.classes.UltraLightUtilsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class CliLightClassGenerationSupport$CliLightClassSupport$typeMapper$2$2 extends FunctionReferenceImpl implements Function1<ClassDescriptor, String> {
    public static final CliLightClassGenerationSupport$CliLightClassSupport$typeMapper$2$2 INSTANCE = new CliLightClassGenerationSupport$CliLightClassSupport$typeMapper$2$2();

    public CliLightClassGenerationSupport$CliLightClassSupport$typeMapper$2$2() {
        super(1, UltraLightUtilsKt.class, "tryGetPredefinedName", "tryGetPredefinedName(Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;)Ljava/lang/String;", 1);
    }

    public final String invoke(ClassDescriptor classDescriptor) {
        classDescriptor.getClass();
        return UltraLightUtilsKt.tryGetPredefinedName(classDescriptor);
    }
}
