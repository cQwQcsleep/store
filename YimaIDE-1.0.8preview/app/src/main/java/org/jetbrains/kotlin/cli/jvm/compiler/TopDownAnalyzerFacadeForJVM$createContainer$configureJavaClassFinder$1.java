package org.jetbrains.kotlin.cli.jvm.compiler;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.container.StorageComponentContainer;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class TopDownAnalyzerFacadeForJVM$createContainer$configureJavaClassFinder$1 extends FunctionReferenceImpl implements Function1<StorageComponentContainer, Unit> {
    public static final TopDownAnalyzerFacadeForJVM$createContainer$configureJavaClassFinder$1 INSTANCE = new TopDownAnalyzerFacadeForJVM$createContainer$configureJavaClassFinder$1();

    public TopDownAnalyzerFacadeForJVM$createContainer$configureJavaClassFinder$1() {
        super(1, Intrinsics.Kotlin.class, "useJavac", "createContainer$useJavac(Lorg/jetbrains/kotlin/container/StorageComponentContainer;)V", 0);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((StorageComponentContainer) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(StorageComponentContainer storageComponentContainer) {
        storageComponentContainer.getClass();
        TopDownAnalyzerFacadeForJVM.createContainer$useJavac(storageComponentContainer);
    }
}
