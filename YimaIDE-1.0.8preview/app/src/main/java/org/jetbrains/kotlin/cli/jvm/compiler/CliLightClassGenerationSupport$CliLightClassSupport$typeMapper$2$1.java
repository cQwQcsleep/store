package org.jetbrains.kotlin.cli.jvm.compiler;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.asJava.classes.UltraLightUtilsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.types.KotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class CliLightClassGenerationSupport$CliLightClassSupport$typeMapper$2$1 extends FunctionReferenceImpl implements Function1<KotlinType, KotlinType> {
    public static final CliLightClassGenerationSupport$CliLightClassSupport$typeMapper$2$1 INSTANCE = new CliLightClassGenerationSupport$CliLightClassSupport$typeMapper$2$1();

    public CliLightClassGenerationSupport$CliLightClassSupport$typeMapper$2$1() {
        super(1, UltraLightUtilsKt.class, "cleanFromAnonymousTypes", "cleanFromAnonymousTypes(Lorg/jetbrains/kotlin/types/KotlinType;)Lorg/jetbrains/kotlin/types/KotlinType;", 1);
    }

    public final KotlinType invoke(KotlinType kotlinType) {
        kotlinType.getClass();
        return UltraLightUtilsKt.cleanFromAnonymousTypes(kotlinType);
    }
}
