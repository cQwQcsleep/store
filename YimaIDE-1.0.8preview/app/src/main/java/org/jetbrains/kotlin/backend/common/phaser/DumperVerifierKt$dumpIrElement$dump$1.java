package org.jetbrains.kotlin.backend.common.phaser;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.AdaptedFunctionReference;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.ir.util.DumpKotlinLikeKt;
import org.jetbrains.kotlin.ir.util.KotlinLikeDumpOptions;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
public final /* synthetic */ class DumperVerifierKt$dumpIrElement$dump$1 extends AdaptedFunctionReference implements Function1<IrElement, String> {
    public static final DumperVerifierKt$dumpIrElement$dump$1 INSTANCE = new DumperVerifierKt$dumpIrElement$dump$1();

    public DumperVerifierKt$dumpIrElement$dump$1() {
        super(1, DumpKotlinLikeKt.class, "dumpKotlinLike", "dumpKotlinLike(Lorg/jetbrains/kotlin/ir/IrElement;Lorg/jetbrains/kotlin/ir/util/KotlinLikeDumpOptions;)Ljava/lang/String;", 1);
    }

    public final String invoke(IrElement irElement) {
        irElement.getClass();
        return DumpKotlinLikeKt.dumpKotlinLike$default(irElement, (KotlinLikeDumpOptions) null, 1, (Object) null);
    }
}
