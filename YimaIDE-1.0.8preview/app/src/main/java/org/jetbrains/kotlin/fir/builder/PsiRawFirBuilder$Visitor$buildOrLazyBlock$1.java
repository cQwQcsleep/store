package org.jetbrains.kotlin.fir.builder;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirLazyBlock;
import org.jetbrains.kotlin.fir.expressions.builder.FirLazyBlockBuilderKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class PsiRawFirBuilder$Visitor$buildOrLazyBlock$1 extends FunctionReferenceImpl implements Function0<FirLazyBlock> {
    public static final PsiRawFirBuilder$Visitor$buildOrLazyBlock$1 INSTANCE = new PsiRawFirBuilder$Visitor$buildOrLazyBlock$1();

    public PsiRawFirBuilder$Visitor$buildOrLazyBlock$1() {
        super(0, FirLazyBlockBuilderKt.class, "buildLazyBlock", "buildLazyBlock()Lorg/jetbrains/kotlin/fir/expressions/FirLazyBlock;", 1);
    }

    public final FirLazyBlock invoke() {
        return FirLazyBlockBuilderKt.buildLazyBlock();
    }
}
