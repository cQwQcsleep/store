package org.jetbrains.kotlin.ir.declarations;

import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\bf\u0018\u0000 \t2\u00020\u0001:\u0002\t\nR\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "", "name", "", "getName", "()Ljava/lang/String;", "isSynthetic", "", "()Z", "Companion", "GeneratedByPlugin", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface IrDeclarationOrigin {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\u0015\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0003\bø\u0001\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001b\u0010\u0004\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\n\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\t\u001a\u0004\b\u000b\u0010\u0007R\u001b\u0010\r\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\t\u001a\u0004\b\u000e\u0010\u0007R\u001b\u0010\u0010\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\t\u001a\u0004\b\u0011\u0010\u0007R\u001b\u0010\u0013\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\t\u001a\u0004\b\u0014\u0010\u0007R\u001b\u0010\u0016\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\t\u001a\u0004\b\u0017\u0010\u0007R\u001b\u0010\u0019\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\t\u001a\u0004\b\u001a\u0010\u0007R\u001b\u0010\u001c\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\t\u001a\u0004\b\u001d\u0010\u0007R\u001b\u0010\u001f\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\t\u001a\u0004\b \u0010\u0007R\u001b\u0010\"\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b$\u0010\t\u001a\u0004\b#\u0010\u0007R\u001b\u0010%\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b'\u0010\t\u001a\u0004\b&\u0010\u0007R\u001b\u0010(\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b*\u0010\t\u001a\u0004\b)\u0010\u0007R\u001b\u0010+\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b-\u0010\t\u001a\u0004\b,\u0010\u0007R\u001b\u0010.\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b0\u0010\t\u001a\u0004\b/\u0010\u0007R\u001b\u00101\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b3\u0010\t\u001a\u0004\b2\u0010\u0007R\u001b\u00104\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b6\u0010\t\u001a\u0004\b5\u0010\u0007R\u001b\u00107\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b9\u0010\t\u001a\u0004\b8\u0010\u0007R\u001b\u0010:\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b<\u0010\t\u001a\u0004\b;\u0010\u0007R\u001b\u0010=\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b?\u0010\t\u001a\u0004\b>\u0010\u0007R\u001b\u0010@\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bB\u0010\t\u001a\u0004\bA\u0010\u0007R\u001b\u0010C\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bE\u0010\t\u001a\u0004\bD\u0010\u0007R\u001b\u0010F\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bH\u0010\t\u001a\u0004\bG\u0010\u0007R\u001b\u0010I\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bK\u0010\t\u001a\u0004\bJ\u0010\u0007R\u001b\u0010L\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bN\u0010\t\u001a\u0004\bM\u0010\u0007R\u001b\u0010O\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bQ\u0010\t\u001a\u0004\bP\u0010\u0007R\u001b\u0010R\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bT\u0010\t\u001a\u0004\bS\u0010\u0007R\u001b\u0010U\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bW\u0010\t\u001a\u0004\bV\u0010\u0007R\u001b\u0010X\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bZ\u0010\t\u001a\u0004\bY\u0010\u0007R\u001b\u0010[\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b]\u0010\t\u001a\u0004\b\\\u0010\u0007R\u001b\u0010^\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b`\u0010\t\u001a\u0004\b_\u0010\u0007R\u001b\u0010a\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bc\u0010\t\u001a\u0004\bb\u0010\u0007R\u001b\u0010d\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bf\u0010\t\u001a\u0004\be\u0010\u0007R\u001b\u0010g\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bi\u0010\t\u001a\u0004\bh\u0010\u0007R\u001b\u0010j\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bl\u0010\t\u001a\u0004\bk\u0010\u0007R\u001b\u0010m\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bo\u0010\t\u001a\u0004\bn\u0010\u0007R\u001b\u0010p\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\br\u0010\t\u001a\u0004\bq\u0010\u0007R\u001b\u0010s\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bu\u0010\t\u001a\u0004\bt\u0010\u0007R\u001b\u0010v\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bx\u0010\t\u001a\u0004\bw\u0010\u0007R\u001b\u0010y\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b{\u0010\t\u001a\u0004\bz\u0010\u0007R\u001b\u0010|\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b~\u0010\t\u001a\u0004\b}\u0010\u0007R\u001d\u0010\u007f\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b\u0081\u0001\u0010\t\u001a\u0005\b\u0080\u0001\u0010\u0007R\u001e\u0010\u0082\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b\u0084\u0001\u0010\t\u001a\u0005\b\u0083\u0001\u0010\u0007R\u001e\u0010\u0085\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b\u0087\u0001\u0010\t\u001a\u0005\b\u0086\u0001\u0010\u0007R\u001e\u0010\u0088\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b\u008a\u0001\u0010\t\u001a\u0005\b\u0089\u0001\u0010\u0007R\u001e\u0010\u008b\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b\u008d\u0001\u0010\t\u001a\u0005\b\u008c\u0001\u0010\u0007R\u001e\u0010\u008e\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b\u0090\u0001\u0010\t\u001a\u0005\b\u008f\u0001\u0010\u0007R\u001e\u0010\u0091\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b\u0093\u0001\u0010\t\u001a\u0005\b\u0092\u0001\u0010\u0007R\u001e\u0010\u0094\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b\u0096\u0001\u0010\t\u001a\u0005\b\u0095\u0001\u0010\u0007R\u001e\u0010\u0097\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b\u0099\u0001\u0010\t\u001a\u0005\b\u0098\u0001\u0010\u0007R\u001e\u0010\u009a\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b\u009c\u0001\u0010\t\u001a\u0005\b\u009b\u0001\u0010\u0007R\u001e\u0010\u009d\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b\u009f\u0001\u0010\t\u001a\u0005\b\u009e\u0001\u0010\u0007R\u001e\u0010 \u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b¢\u0001\u0010\t\u001a\u0005\b¡\u0001\u0010\u0007R\u001e\u0010£\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b¥\u0001\u0010\t\u001a\u0005\b¤\u0001\u0010\u0007R\u001e\u0010¦\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b¨\u0001\u0010\t\u001a\u0005\b§\u0001\u0010\u0007R\u001e\u0010©\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b«\u0001\u0010\t\u001a\u0005\bª\u0001\u0010\u0007R\u001e\u0010¬\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b®\u0001\u0010\t\u001a\u0005\b\u00ad\u0001\u0010\u0007R\u001e\u0010¯\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b±\u0001\u0010\t\u001a\u0005\b°\u0001\u0010\u0007R\u001e\u0010²\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b´\u0001\u0010\t\u001a\u0005\b³\u0001\u0010\u0007R\u001e\u0010µ\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b·\u0001\u0010\t\u001a\u0005\b¶\u0001\u0010\u0007R\u001e\u0010¸\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\bº\u0001\u0010\t\u001a\u0005\b¹\u0001\u0010\u0007R\u001e\u0010»\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b½\u0001\u0010\t\u001a\u0005\b¼\u0001\u0010\u0007R\u001e\u0010¾\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\bÀ\u0001\u0010\t\u001a\u0005\b¿\u0001\u0010\u0007R\u001e\u0010Á\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\bÃ\u0001\u0010\t\u001a\u0005\bÂ\u0001\u0010\u0007R\u001e\u0010Ä\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\bÆ\u0001\u0010\t\u001a\u0005\bÅ\u0001\u0010\u0007R\u001e\u0010Ç\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\bÉ\u0001\u0010\t\u001a\u0005\bÈ\u0001\u0010\u0007R\u001e\u0010Ê\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\bÌ\u0001\u0010\t\u001a\u0005\bË\u0001\u0010\u0007R\u001e\u0010Í\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\bÏ\u0001\u0010\t\u001a\u0005\bÎ\u0001\u0010\u0007R\u001e\u0010Ð\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\bÒ\u0001\u0010\t\u001a\u0005\bÑ\u0001\u0010\u0007R\u001e\u0010Ó\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\bÕ\u0001\u0010\t\u001a\u0005\bÔ\u0001\u0010\u0007R\u001e\u0010Ö\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\bØ\u0001\u0010\t\u001a\u0005\b×\u0001\u0010\u0007R\u001e\u0010Ù\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\bÛ\u0001\u0010\t\u001a\u0005\bÚ\u0001\u0010\u0007R\u001e\u0010Ü\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\bÞ\u0001\u0010\t\u001a\u0005\bÝ\u0001\u0010\u0007R\u001e\u0010ß\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\bá\u0001\u0010\t\u001a\u0005\bà\u0001\u0010\u0007R\u001e\u0010â\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\bä\u0001\u0010\t\u001a\u0005\bã\u0001\u0010\u0007R\u001e\u0010å\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\bç\u0001\u0010\t\u001a\u0005\bæ\u0001\u0010\u0007R\u001e\u0010è\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\bê\u0001\u0010\t\u001a\u0005\bé\u0001\u0010\u0007R\u001e\u0010ë\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\bí\u0001\u0010\t\u001a\u0005\bì\u0001\u0010\u0007R\u001e\u0010î\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\bð\u0001\u0010\t\u001a\u0005\bï\u0001\u0010\u0007R\u001e\u0010ñ\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\bó\u0001\u0010\t\u001a\u0005\bò\u0001\u0010\u0007R\u001e\u0010ô\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\bö\u0001\u0010\t\u001a\u0005\bõ\u0001\u0010\u0007R\u001e\u0010÷\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\bù\u0001\u0010\t\u001a\u0005\bø\u0001\u0010\u0007R\u001e\u0010ú\u0001\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\bü\u0001\u0010\t\u001a\u0005\bû\u0001\u0010\u0007¨\u0006ý\u0001"}, d2 = {"Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin$Companion;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "DEFINED", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "getDEFINED", "()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "DEFINED$delegate", "Lkotlin/Lazy;", "FAKE_OVERRIDE", "getFAKE_OVERRIDE", "FAKE_OVERRIDE$delegate", "FOR_LOOP_ITERATOR", "getFOR_LOOP_ITERATOR", "FOR_LOOP_ITERATOR$delegate", "FOR_LOOP_VARIABLE", "getFOR_LOOP_VARIABLE", "FOR_LOOP_VARIABLE$delegate", "FOR_LOOP_IMPLICIT_VARIABLE", "getFOR_LOOP_IMPLICIT_VARIABLE", "FOR_LOOP_IMPLICIT_VARIABLE$delegate", "PROPERTY_BACKING_FIELD", "getPROPERTY_BACKING_FIELD", "PROPERTY_BACKING_FIELD$delegate", "DEFAULT_PROPERTY_ACCESSOR", "getDEFAULT_PROPERTY_ACCESSOR", "DEFAULT_PROPERTY_ACCESSOR$delegate", "DELEGATE", "getDELEGATE", "DELEGATE$delegate", "PROPERTY_DELEGATE", "getPROPERTY_DELEGATE", "PROPERTY_DELEGATE$delegate", "DELEGATED_PROPERTY_ACCESSOR", "getDELEGATED_PROPERTY_ACCESSOR", "DELEGATED_PROPERTY_ACCESSOR$delegate", "DELEGATED_MEMBER", "getDELEGATED_MEMBER", "DELEGATED_MEMBER$delegate", "ENUM_CLASS_SPECIAL_MEMBER", "getENUM_CLASS_SPECIAL_MEMBER", "ENUM_CLASS_SPECIAL_MEMBER$delegate", "FUNCTION_FOR_DEFAULT_PARAMETER", "getFUNCTION_FOR_DEFAULT_PARAMETER", "FUNCTION_FOR_DEFAULT_PARAMETER$delegate", "MASK_FOR_DEFAULT_FUNCTION", "getMASK_FOR_DEFAULT_FUNCTION", "MASK_FOR_DEFAULT_FUNCTION$delegate", "DEFAULT_CONSTRUCTOR_MARKER", "getDEFAULT_CONSTRUCTOR_MARKER", "DEFAULT_CONSTRUCTOR_MARKER$delegate", "SYNTHETIC_CONSTRUCTOR_MARKER", "getSYNTHETIC_CONSTRUCTOR_MARKER", "SYNTHETIC_CONSTRUCTOR_MARKER$delegate", "METHOD_HANDLER_IN_DEFAULT_FUNCTION", "getMETHOD_HANDLER_IN_DEFAULT_FUNCTION", "METHOD_HANDLER_IN_DEFAULT_FUNCTION$delegate", "MOVED_DISPATCH_RECEIVER", "getMOVED_DISPATCH_RECEIVER", "MOVED_DISPATCH_RECEIVER$delegate", "MOVED_EXTENSION_RECEIVER", "getMOVED_EXTENSION_RECEIVER", "MOVED_EXTENSION_RECEIVER$delegate", "MOVED_CONTEXT_RECEIVER", "getMOVED_CONTEXT_RECEIVER", "MOVED_CONTEXT_RECEIVER$delegate", "FILE_CLASS", "getFILE_CLASS", "FILE_CLASS$delegate", "SYNTHETIC_FILE_CLASS", "getSYNTHETIC_FILE_CLASS", "SYNTHETIC_FILE_CLASS$delegate", "JVM_MULTIFILE_CLASS", "getJVM_MULTIFILE_CLASS", "JVM_MULTIFILE_CLASS$delegate", "ERROR_CLASS", "getERROR_CLASS", "ERROR_CLASS$delegate", "SCRIPT_CLASS", "getSCRIPT_CLASS", "SCRIPT_CLASS$delegate", "SCRIPT_THIS_RECEIVER", "getSCRIPT_THIS_RECEIVER", "SCRIPT_THIS_RECEIVER$delegate", "SCRIPT_STATEMENT", "getSCRIPT_STATEMENT", "SCRIPT_STATEMENT$delegate", "SCRIPT_EARLIER_SCRIPTS", "getSCRIPT_EARLIER_SCRIPTS", "SCRIPT_EARLIER_SCRIPTS$delegate", "SCRIPT_CALL_PARAMETER", "getSCRIPT_CALL_PARAMETER", "SCRIPT_CALL_PARAMETER$delegate", "SCRIPT_IMPLICIT_RECEIVER", "getSCRIPT_IMPLICIT_RECEIVER", "SCRIPT_IMPLICIT_RECEIVER$delegate", "SCRIPT_PROVIDED_PROPERTY", "getSCRIPT_PROVIDED_PROPERTY", "SCRIPT_PROVIDED_PROPERTY$delegate", "SCRIPT_RESULT_PROPERTY", "getSCRIPT_RESULT_PROPERTY", "SCRIPT_RESULT_PROPERTY$delegate", "REPL_EVAL_FUNCTION", "getREPL_EVAL_FUNCTION", "REPL_EVAL_FUNCTION$delegate", "REPL_FROM_OTHER_SNIPPET", "getREPL_FROM_OTHER_SNIPPET", "REPL_FROM_OTHER_SNIPPET$delegate", "GENERATED_DATA_CLASS_MEMBER", "getGENERATED_DATA_CLASS_MEMBER", "GENERATED_DATA_CLASS_MEMBER$delegate", "GENERATED_SINGLE_FIELD_VALUE_CLASS_MEMBER", "getGENERATED_SINGLE_FIELD_VALUE_CLASS_MEMBER", "GENERATED_SINGLE_FIELD_VALUE_CLASS_MEMBER$delegate", "GENERATED_MULTI_FIELD_VALUE_CLASS_MEMBER", "getGENERATED_MULTI_FIELD_VALUE_CLASS_MEMBER", "GENERATED_MULTI_FIELD_VALUE_CLASS_MEMBER$delegate", "LOCAL_FUNCTION", "getLOCAL_FUNCTION", "LOCAL_FUNCTION$delegate", "LOCAL_FUNCTION_FOR_LAMBDA", "getLOCAL_FUNCTION_FOR_LAMBDA", "LOCAL_FUNCTION_FOR_LAMBDA$delegate", "CATCH_PARAMETER", "getCATCH_PARAMETER", "CATCH_PARAMETER$delegate", "UNDERSCORE_PARAMETER", "getUNDERSCORE_PARAMETER", "UNDERSCORE_PARAMETER$delegate", "DESTRUCTURED_OBJECT_PARAMETER", "getDESTRUCTURED_OBJECT_PARAMETER", "DESTRUCTURED_OBJECT_PARAMETER$delegate", "INSTANCE_RECEIVER", "getINSTANCE_RECEIVER", "INSTANCE_RECEIVER$delegate", "PRIMARY_CONSTRUCTOR_PARAMETER", "getPRIMARY_CONSTRUCTOR_PARAMETER", "PRIMARY_CONSTRUCTOR_PARAMETER$delegate", "IR_DESTRUCTURED_PARAMETER_VARIABLE", "getIR_DESTRUCTURED_PARAMETER_VARIABLE", "IR_DESTRUCTURED_PARAMETER_VARIABLE$delegate", "IR_TEMPORARY_VARIABLE", "getIR_TEMPORARY_VARIABLE", "IR_TEMPORARY_VARIABLE$delegate", "IR_TEMPORARY_VARIABLE_FOR_INLINED_PARAMETER", "getIR_TEMPORARY_VARIABLE_FOR_INLINED_PARAMETER", "IR_TEMPORARY_VARIABLE_FOR_INLINED_PARAMETER$delegate", "IR_TEMPORARY_VARIABLE_FOR_INLINED_EXTENSION_RECEIVER", "getIR_TEMPORARY_VARIABLE_FOR_INLINED_EXTENSION_RECEIVER", "IR_TEMPORARY_VARIABLE_FOR_INLINED_EXTENSION_RECEIVER$delegate", "IR_EXTERNAL_DECLARATION_STUB", "getIR_EXTERNAL_DECLARATION_STUB", "IR_EXTERNAL_DECLARATION_STUB$delegate", "IR_EXTERNAL_JAVA_DECLARATION_STUB", "getIR_EXTERNAL_JAVA_DECLARATION_STUB", "IR_EXTERNAL_JAVA_DECLARATION_STUB$delegate", "IR_BUILTINS_STUB", "getIR_BUILTINS_STUB", "IR_BUILTINS_STUB$delegate", "BRIDGE", "getBRIDGE", "BRIDGE$delegate", "BRIDGE_SPECIAL", "getBRIDGE_SPECIAL", "BRIDGE_SPECIAL$delegate", "GENERATED_SETTER_GETTER", "getGENERATED_SETTER_GETTER", "GENERATED_SETTER_GETTER$delegate", "FIELD_FOR_ENUM_ENTRY", "getFIELD_FOR_ENUM_ENTRY", "FIELD_FOR_ENUM_ENTRY$delegate", "SYNTHETIC_HELPER_FOR_ENUM_VALUES", "getSYNTHETIC_HELPER_FOR_ENUM_VALUES", "SYNTHETIC_HELPER_FOR_ENUM_VALUES$delegate", "SYNTHETIC_HELPER_FOR_ENUM_ENTRIES", "getSYNTHETIC_HELPER_FOR_ENUM_ENTRIES", "SYNTHETIC_HELPER_FOR_ENUM_ENTRIES$delegate", "FIELD_FOR_ENUM_VALUES", "getFIELD_FOR_ENUM_VALUES", "FIELD_FOR_ENUM_VALUES$delegate", "FIELD_FOR_ENUM_ENTRIES", "getFIELD_FOR_ENUM_ENTRIES", "FIELD_FOR_ENUM_ENTRIES$delegate", "PROPERTY_FOR_ENUM_ENTRIES", "getPROPERTY_FOR_ENUM_ENTRIES", "PROPERTY_FOR_ENUM_ENTRIES$delegate", "FIELD_FOR_OBJECT_INSTANCE", "getFIELD_FOR_OBJECT_INSTANCE", "FIELD_FOR_OBJECT_INSTANCE$delegate", "FIELD_FOR_CLASS_CONTEXT_RECEIVER", "getFIELD_FOR_CLASS_CONTEXT_RECEIVER", "FIELD_FOR_CLASS_CONTEXT_RECEIVER$delegate", "ADAPTER_FOR_CALLABLE_REFERENCE", "getADAPTER_FOR_CALLABLE_REFERENCE", "ADAPTER_FOR_CALLABLE_REFERENCE$delegate", "ADAPTER_PARAMETER_FOR_CALLABLE_REFERENCE", "getADAPTER_PARAMETER_FOR_CALLABLE_REFERENCE", "ADAPTER_PARAMETER_FOR_CALLABLE_REFERENCE$delegate", "ADAPTER_FOR_SUSPEND_CONVERSION", "getADAPTER_FOR_SUSPEND_CONVERSION", "ADAPTER_FOR_SUSPEND_CONVERSION$delegate", "ADAPTER_PARAMETER_FOR_SUSPEND_CONVERSION", "getADAPTER_PARAMETER_FOR_SUSPEND_CONVERSION", "ADAPTER_PARAMETER_FOR_SUSPEND_CONVERSION$delegate", "ADAPTER_FOR_FUN_INTERFACE_CONSTRUCTOR", "getADAPTER_FOR_FUN_INTERFACE_CONSTRUCTOR", "ADAPTER_FOR_FUN_INTERFACE_CONSTRUCTOR$delegate", "GENERATED_SAM_IMPLEMENTATION", "getGENERATED_SAM_IMPLEMENTATION", "GENERATED_SAM_IMPLEMENTATION$delegate", "SYNTHETIC_GENERATED_SAM_IMPLEMENTATION", "getSYNTHETIC_GENERATED_SAM_IMPLEMENTATION", "SYNTHETIC_GENERATED_SAM_IMPLEMENTATION$delegate", "SYNTHETIC_JAVA_PROPERTY_DELEGATE", "getSYNTHETIC_JAVA_PROPERTY_DELEGATE", "SYNTHETIC_JAVA_PROPERTY_DELEGATE$delegate", "FIELD_FOR_OUTER_THIS", "getFIELD_FOR_OUTER_THIS", "FIELD_FOR_OUTER_THIS$delegate", "CONTINUATION", "getCONTINUATION", "CONTINUATION$delegate", "LOWERED_SUSPEND_FUNCTION", "getLOWERED_SUSPEND_FUNCTION", "LOWERED_SUSPEND_FUNCTION$delegate", "SHARED_VARIABLE_IN_EVALUATOR_FRAGMENT", "getSHARED_VARIABLE_IN_EVALUATOR_FRAGMENT", "SHARED_VARIABLE_IN_EVALUATOR_FRAGMENT$delegate", "SYNTHETIC_ACCESSOR", "getSYNTHETIC_ACCESSOR", "SYNTHETIC_ACCESSOR$delegate", "SYNTHETIC_ACCESSOR_CAPTURED_TYPE_PARAMETER", "getSYNTHETIC_ACCESSOR_CAPTURED_TYPE_PARAMETER", "SYNTHETIC_ACCESSOR_CAPTURED_TYPE_PARAMETER$delegate", "FILLED_FOR_UNBOUND_SYMBOL", "getFILLED_FOR_UNBOUND_SYMBOL", "FILLED_FOR_UNBOUND_SYMBOL$delegate", "INLINE_LAMBDA", "getINLINE_LAMBDA", "INLINE_LAMBDA$delegate", "FUNCTION_INTERFACE_MEMBER", "getFUNCTION_INTERFACE_MEMBER", "FUNCTION_INTERFACE_MEMBER$delegate", "STUB_FOR_LENIENT", "getSTUB_FOR_LENIENT", "STUB_FOR_LENIENT$delegate", "STUB_FOR_TYPE_SWITCH", "getSTUB_FOR_TYPE_SWITCH", "STUB_FOR_TYPE_SWITCH$delegate", "VERSION_OVERLOAD_WRAPPER", "getVERSION_OVERLOAD_WRAPPER", "VERSION_OVERLOAD_WRAPPER$delegate", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE;
        static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;

        /* JADX INFO: renamed from: ADAPTER_FOR_CALLABLE_REFERENCE$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> ADAPTER_FOR_CALLABLE_REFERENCE;

        /* JADX INFO: renamed from: ADAPTER_FOR_FUN_INTERFACE_CONSTRUCTOR$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> ADAPTER_FOR_FUN_INTERFACE_CONSTRUCTOR;

        /* JADX INFO: renamed from: ADAPTER_FOR_SUSPEND_CONVERSION$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> ADAPTER_FOR_SUSPEND_CONVERSION;

        /* JADX INFO: renamed from: ADAPTER_PARAMETER_FOR_CALLABLE_REFERENCE$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> ADAPTER_PARAMETER_FOR_CALLABLE_REFERENCE;

        /* JADX INFO: renamed from: ADAPTER_PARAMETER_FOR_SUSPEND_CONVERSION$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> ADAPTER_PARAMETER_FOR_SUSPEND_CONVERSION;

        /* JADX INFO: renamed from: BRIDGE$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> BRIDGE;

        /* JADX INFO: renamed from: BRIDGE_SPECIAL$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> BRIDGE_SPECIAL;

        /* JADX INFO: renamed from: CATCH_PARAMETER$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> CATCH_PARAMETER;

        /* JADX INFO: renamed from: CONTINUATION$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> CONTINUATION;

        /* JADX INFO: renamed from: DEFAULT_CONSTRUCTOR_MARKER$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> DEFAULT_CONSTRUCTOR_MARKER;

        /* JADX INFO: renamed from: DEFAULT_PROPERTY_ACCESSOR$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> DEFAULT_PROPERTY_ACCESSOR;

        /* JADX INFO: renamed from: DEFINED$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> DEFINED;

        /* JADX INFO: renamed from: DELEGATE$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> DELEGATE;

        /* JADX INFO: renamed from: DELEGATED_MEMBER$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> DELEGATED_MEMBER;

        /* JADX INFO: renamed from: DELEGATED_PROPERTY_ACCESSOR$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> DELEGATED_PROPERTY_ACCESSOR;

        /* JADX INFO: renamed from: DESTRUCTURED_OBJECT_PARAMETER$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> DESTRUCTURED_OBJECT_PARAMETER;

        /* JADX INFO: renamed from: ENUM_CLASS_SPECIAL_MEMBER$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> ENUM_CLASS_SPECIAL_MEMBER;

        /* JADX INFO: renamed from: ERROR_CLASS$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> ERROR_CLASS;

        /* JADX INFO: renamed from: FAKE_OVERRIDE$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> FAKE_OVERRIDE;

        /* JADX INFO: renamed from: FIELD_FOR_CLASS_CONTEXT_RECEIVER$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> FIELD_FOR_CLASS_CONTEXT_RECEIVER;

        /* JADX INFO: renamed from: FIELD_FOR_ENUM_ENTRIES$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> FIELD_FOR_ENUM_ENTRIES;

        /* JADX INFO: renamed from: FIELD_FOR_ENUM_ENTRY$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> FIELD_FOR_ENUM_ENTRY;

        /* JADX INFO: renamed from: FIELD_FOR_ENUM_VALUES$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> FIELD_FOR_ENUM_VALUES;

        /* JADX INFO: renamed from: FIELD_FOR_OBJECT_INSTANCE$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> FIELD_FOR_OBJECT_INSTANCE;

        /* JADX INFO: renamed from: FIELD_FOR_OUTER_THIS$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> FIELD_FOR_OUTER_THIS;

        /* JADX INFO: renamed from: FILE_CLASS$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> FILE_CLASS;

        /* JADX INFO: renamed from: FILLED_FOR_UNBOUND_SYMBOL$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> FILLED_FOR_UNBOUND_SYMBOL;

        /* JADX INFO: renamed from: FOR_LOOP_IMPLICIT_VARIABLE$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> FOR_LOOP_IMPLICIT_VARIABLE;

        /* JADX INFO: renamed from: FOR_LOOP_ITERATOR$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> FOR_LOOP_ITERATOR;

        /* JADX INFO: renamed from: FOR_LOOP_VARIABLE$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> FOR_LOOP_VARIABLE;

        /* JADX INFO: renamed from: FUNCTION_FOR_DEFAULT_PARAMETER$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> FUNCTION_FOR_DEFAULT_PARAMETER;

        /* JADX INFO: renamed from: FUNCTION_INTERFACE_MEMBER$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> FUNCTION_INTERFACE_MEMBER;

        /* JADX INFO: renamed from: GENERATED_DATA_CLASS_MEMBER$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> GENERATED_DATA_CLASS_MEMBER;

        /* JADX INFO: renamed from: GENERATED_MULTI_FIELD_VALUE_CLASS_MEMBER$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> GENERATED_MULTI_FIELD_VALUE_CLASS_MEMBER;

        /* JADX INFO: renamed from: GENERATED_SAM_IMPLEMENTATION$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> GENERATED_SAM_IMPLEMENTATION;

        /* JADX INFO: renamed from: GENERATED_SETTER_GETTER$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> GENERATED_SETTER_GETTER;

        /* JADX INFO: renamed from: GENERATED_SINGLE_FIELD_VALUE_CLASS_MEMBER$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> GENERATED_SINGLE_FIELD_VALUE_CLASS_MEMBER;

        /* JADX INFO: renamed from: INLINE_LAMBDA$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> INLINE_LAMBDA;

        /* JADX INFO: renamed from: INSTANCE_RECEIVER$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> INSTANCE_RECEIVER;

        /* JADX INFO: renamed from: IR_BUILTINS_STUB$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> IR_BUILTINS_STUB;

        /* JADX INFO: renamed from: IR_DESTRUCTURED_PARAMETER_VARIABLE$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> IR_DESTRUCTURED_PARAMETER_VARIABLE;

        /* JADX INFO: renamed from: IR_EXTERNAL_DECLARATION_STUB$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> IR_EXTERNAL_DECLARATION_STUB;

        /* JADX INFO: renamed from: IR_EXTERNAL_JAVA_DECLARATION_STUB$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> IR_EXTERNAL_JAVA_DECLARATION_STUB;

        /* JADX INFO: renamed from: IR_TEMPORARY_VARIABLE$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> IR_TEMPORARY_VARIABLE;

        /* JADX INFO: renamed from: IR_TEMPORARY_VARIABLE_FOR_INLINED_EXTENSION_RECEIVER$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> IR_TEMPORARY_VARIABLE_FOR_INLINED_EXTENSION_RECEIVER;

        /* JADX INFO: renamed from: IR_TEMPORARY_VARIABLE_FOR_INLINED_PARAMETER$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> IR_TEMPORARY_VARIABLE_FOR_INLINED_PARAMETER;

        /* JADX INFO: renamed from: JVM_MULTIFILE_CLASS$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> JVM_MULTIFILE_CLASS;

        /* JADX INFO: renamed from: LOCAL_FUNCTION$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> LOCAL_FUNCTION;

        /* JADX INFO: renamed from: LOCAL_FUNCTION_FOR_LAMBDA$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> LOCAL_FUNCTION_FOR_LAMBDA;

        /* JADX INFO: renamed from: LOWERED_SUSPEND_FUNCTION$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> LOWERED_SUSPEND_FUNCTION;

        /* JADX INFO: renamed from: MASK_FOR_DEFAULT_FUNCTION$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> MASK_FOR_DEFAULT_FUNCTION;

        /* JADX INFO: renamed from: METHOD_HANDLER_IN_DEFAULT_FUNCTION$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> METHOD_HANDLER_IN_DEFAULT_FUNCTION;

        /* JADX INFO: renamed from: MOVED_CONTEXT_RECEIVER$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> MOVED_CONTEXT_RECEIVER;

        /* JADX INFO: renamed from: MOVED_DISPATCH_RECEIVER$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> MOVED_DISPATCH_RECEIVER;

        /* JADX INFO: renamed from: MOVED_EXTENSION_RECEIVER$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> MOVED_EXTENSION_RECEIVER;

        /* JADX INFO: renamed from: PRIMARY_CONSTRUCTOR_PARAMETER$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> PRIMARY_CONSTRUCTOR_PARAMETER;

        /* JADX INFO: renamed from: PROPERTY_BACKING_FIELD$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> PROPERTY_BACKING_FIELD;

        /* JADX INFO: renamed from: PROPERTY_DELEGATE$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> PROPERTY_DELEGATE;

        /* JADX INFO: renamed from: PROPERTY_FOR_ENUM_ENTRIES$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> PROPERTY_FOR_ENUM_ENTRIES;

        /* JADX INFO: renamed from: REPL_EVAL_FUNCTION$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> REPL_EVAL_FUNCTION;

        /* JADX INFO: renamed from: REPL_FROM_OTHER_SNIPPET$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> REPL_FROM_OTHER_SNIPPET;

        /* JADX INFO: renamed from: SCRIPT_CALL_PARAMETER$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> SCRIPT_CALL_PARAMETER;

        /* JADX INFO: renamed from: SCRIPT_CLASS$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> SCRIPT_CLASS;

        /* JADX INFO: renamed from: SCRIPT_EARLIER_SCRIPTS$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> SCRIPT_EARLIER_SCRIPTS;

        /* JADX INFO: renamed from: SCRIPT_IMPLICIT_RECEIVER$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> SCRIPT_IMPLICIT_RECEIVER;

        /* JADX INFO: renamed from: SCRIPT_PROVIDED_PROPERTY$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> SCRIPT_PROVIDED_PROPERTY;

        /* JADX INFO: renamed from: SCRIPT_RESULT_PROPERTY$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> SCRIPT_RESULT_PROPERTY;

        /* JADX INFO: renamed from: SCRIPT_STATEMENT$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> SCRIPT_STATEMENT;

        /* JADX INFO: renamed from: SCRIPT_THIS_RECEIVER$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> SCRIPT_THIS_RECEIVER;

        /* JADX INFO: renamed from: SHARED_VARIABLE_IN_EVALUATOR_FRAGMENT$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> SHARED_VARIABLE_IN_EVALUATOR_FRAGMENT;

        /* JADX INFO: renamed from: STUB_FOR_LENIENT$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> STUB_FOR_LENIENT;

        /* JADX INFO: renamed from: STUB_FOR_TYPE_SWITCH$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> STUB_FOR_TYPE_SWITCH;

        /* JADX INFO: renamed from: SYNTHETIC_ACCESSOR$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> SYNTHETIC_ACCESSOR;

        /* JADX INFO: renamed from: SYNTHETIC_ACCESSOR_CAPTURED_TYPE_PARAMETER$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> SYNTHETIC_ACCESSOR_CAPTURED_TYPE_PARAMETER;

        /* JADX INFO: renamed from: SYNTHETIC_CONSTRUCTOR_MARKER$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> SYNTHETIC_CONSTRUCTOR_MARKER;

        /* JADX INFO: renamed from: SYNTHETIC_FILE_CLASS$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> SYNTHETIC_FILE_CLASS;

        /* JADX INFO: renamed from: SYNTHETIC_GENERATED_SAM_IMPLEMENTATION$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> SYNTHETIC_GENERATED_SAM_IMPLEMENTATION;

        /* JADX INFO: renamed from: SYNTHETIC_HELPER_FOR_ENUM_ENTRIES$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> SYNTHETIC_HELPER_FOR_ENUM_ENTRIES;

        /* JADX INFO: renamed from: SYNTHETIC_HELPER_FOR_ENUM_VALUES$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> SYNTHETIC_HELPER_FOR_ENUM_VALUES;

        /* JADX INFO: renamed from: SYNTHETIC_JAVA_PROPERTY_DELEGATE$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> SYNTHETIC_JAVA_PROPERTY_DELEGATE;

        /* JADX INFO: renamed from: UNDERSCORE_PARAMETER$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> UNDERSCORE_PARAMETER;

        /* JADX INFO: renamed from: VERSION_OVERLOAD_WRAPPER$delegate, reason: from kotlin metadata */
        private static final Lazy<IrDeclarationOrigin> VERSION_OVERLOAD_WRAPPER;

        static {
            KProperty<Object>[] kPropertyArr = {new PropertyReference1Impl<>(Companion.class, "DEFINED", "getDEFINED()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "FAKE_OVERRIDE", "getFAKE_OVERRIDE()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "FOR_LOOP_ITERATOR", "getFOR_LOOP_ITERATOR()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "FOR_LOOP_VARIABLE", "getFOR_LOOP_VARIABLE()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "FOR_LOOP_IMPLICIT_VARIABLE", "getFOR_LOOP_IMPLICIT_VARIABLE()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "PROPERTY_BACKING_FIELD", "getPROPERTY_BACKING_FIELD()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "DEFAULT_PROPERTY_ACCESSOR", "getDEFAULT_PROPERTY_ACCESSOR()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "DELEGATE", "getDELEGATE()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "PROPERTY_DELEGATE", "getPROPERTY_DELEGATE()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "DELEGATED_PROPERTY_ACCESSOR", "getDELEGATED_PROPERTY_ACCESSOR()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "DELEGATED_MEMBER", "getDELEGATED_MEMBER()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "ENUM_CLASS_SPECIAL_MEMBER", "getENUM_CLASS_SPECIAL_MEMBER()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "FUNCTION_FOR_DEFAULT_PARAMETER", "getFUNCTION_FOR_DEFAULT_PARAMETER()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "MASK_FOR_DEFAULT_FUNCTION", "getMASK_FOR_DEFAULT_FUNCTION()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "DEFAULT_CONSTRUCTOR_MARKER", "getDEFAULT_CONSTRUCTOR_MARKER()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "SYNTHETIC_CONSTRUCTOR_MARKER", "getSYNTHETIC_CONSTRUCTOR_MARKER()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "METHOD_HANDLER_IN_DEFAULT_FUNCTION", "getMETHOD_HANDLER_IN_DEFAULT_FUNCTION()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "MOVED_DISPATCH_RECEIVER", "getMOVED_DISPATCH_RECEIVER()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "MOVED_EXTENSION_RECEIVER", "getMOVED_EXTENSION_RECEIVER()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "MOVED_CONTEXT_RECEIVER", "getMOVED_CONTEXT_RECEIVER()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "FILE_CLASS", "getFILE_CLASS()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "SYNTHETIC_FILE_CLASS", "getSYNTHETIC_FILE_CLASS()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "JVM_MULTIFILE_CLASS", "getJVM_MULTIFILE_CLASS()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "ERROR_CLASS", "getERROR_CLASS()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "SCRIPT_CLASS", "getSCRIPT_CLASS()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "SCRIPT_THIS_RECEIVER", "getSCRIPT_THIS_RECEIVER()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "SCRIPT_STATEMENT", "getSCRIPT_STATEMENT()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "SCRIPT_EARLIER_SCRIPTS", "getSCRIPT_EARLIER_SCRIPTS()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "SCRIPT_CALL_PARAMETER", "getSCRIPT_CALL_PARAMETER()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "SCRIPT_IMPLICIT_RECEIVER", "getSCRIPT_IMPLICIT_RECEIVER()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "SCRIPT_PROVIDED_PROPERTY", "getSCRIPT_PROVIDED_PROPERTY()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "SCRIPT_RESULT_PROPERTY", "getSCRIPT_RESULT_PROPERTY()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "REPL_EVAL_FUNCTION", "getREPL_EVAL_FUNCTION()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "REPL_FROM_OTHER_SNIPPET", "getREPL_FROM_OTHER_SNIPPET()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "GENERATED_DATA_CLASS_MEMBER", "getGENERATED_DATA_CLASS_MEMBER()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "GENERATED_SINGLE_FIELD_VALUE_CLASS_MEMBER", "getGENERATED_SINGLE_FIELD_VALUE_CLASS_MEMBER()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "GENERATED_MULTI_FIELD_VALUE_CLASS_MEMBER", "getGENERATED_MULTI_FIELD_VALUE_CLASS_MEMBER()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "LOCAL_FUNCTION", "getLOCAL_FUNCTION()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "LOCAL_FUNCTION_FOR_LAMBDA", "getLOCAL_FUNCTION_FOR_LAMBDA()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "CATCH_PARAMETER", "getCATCH_PARAMETER()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "UNDERSCORE_PARAMETER", "getUNDERSCORE_PARAMETER()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "DESTRUCTURED_OBJECT_PARAMETER", "getDESTRUCTURED_OBJECT_PARAMETER()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "INSTANCE_RECEIVER", "getINSTANCE_RECEIVER()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "PRIMARY_CONSTRUCTOR_PARAMETER", "getPRIMARY_CONSTRUCTOR_PARAMETER()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "IR_DESTRUCTURED_PARAMETER_VARIABLE", "getIR_DESTRUCTURED_PARAMETER_VARIABLE()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "IR_TEMPORARY_VARIABLE", "getIR_TEMPORARY_VARIABLE()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "IR_TEMPORARY_VARIABLE_FOR_INLINED_PARAMETER", "getIR_TEMPORARY_VARIABLE_FOR_INLINED_PARAMETER()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "IR_TEMPORARY_VARIABLE_FOR_INLINED_EXTENSION_RECEIVER", "getIR_TEMPORARY_VARIABLE_FOR_INLINED_EXTENSION_RECEIVER()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "IR_EXTERNAL_DECLARATION_STUB", "getIR_EXTERNAL_DECLARATION_STUB()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "IR_EXTERNAL_JAVA_DECLARATION_STUB", "getIR_EXTERNAL_JAVA_DECLARATION_STUB()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "IR_BUILTINS_STUB", "getIR_BUILTINS_STUB()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "BRIDGE", "getBRIDGE()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "BRIDGE_SPECIAL", "getBRIDGE_SPECIAL()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "GENERATED_SETTER_GETTER", "getGENERATED_SETTER_GETTER()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "FIELD_FOR_ENUM_ENTRY", "getFIELD_FOR_ENUM_ENTRY()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "SYNTHETIC_HELPER_FOR_ENUM_VALUES", "getSYNTHETIC_HELPER_FOR_ENUM_VALUES()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "SYNTHETIC_HELPER_FOR_ENUM_ENTRIES", "getSYNTHETIC_HELPER_FOR_ENUM_ENTRIES()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "FIELD_FOR_ENUM_VALUES", "getFIELD_FOR_ENUM_VALUES()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "FIELD_FOR_ENUM_ENTRIES", "getFIELD_FOR_ENUM_ENTRIES()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "PROPERTY_FOR_ENUM_ENTRIES", "getPROPERTY_FOR_ENUM_ENTRIES()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "FIELD_FOR_OBJECT_INSTANCE", "getFIELD_FOR_OBJECT_INSTANCE()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "FIELD_FOR_CLASS_CONTEXT_RECEIVER", "getFIELD_FOR_CLASS_CONTEXT_RECEIVER()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "ADAPTER_FOR_CALLABLE_REFERENCE", "getADAPTER_FOR_CALLABLE_REFERENCE()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "ADAPTER_PARAMETER_FOR_CALLABLE_REFERENCE", "getADAPTER_PARAMETER_FOR_CALLABLE_REFERENCE()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "ADAPTER_FOR_SUSPEND_CONVERSION", "getADAPTER_FOR_SUSPEND_CONVERSION()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "ADAPTER_PARAMETER_FOR_SUSPEND_CONVERSION", "getADAPTER_PARAMETER_FOR_SUSPEND_CONVERSION()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "ADAPTER_FOR_FUN_INTERFACE_CONSTRUCTOR", "getADAPTER_FOR_FUN_INTERFACE_CONSTRUCTOR()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "GENERATED_SAM_IMPLEMENTATION", "getGENERATED_SAM_IMPLEMENTATION()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "SYNTHETIC_GENERATED_SAM_IMPLEMENTATION", "getSYNTHETIC_GENERATED_SAM_IMPLEMENTATION()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "SYNTHETIC_JAVA_PROPERTY_DELEGATE", "getSYNTHETIC_JAVA_PROPERTY_DELEGATE()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "FIELD_FOR_OUTER_THIS", "getFIELD_FOR_OUTER_THIS()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "CONTINUATION", "getCONTINUATION()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "LOWERED_SUSPEND_FUNCTION", "getLOWERED_SUSPEND_FUNCTION()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "SHARED_VARIABLE_IN_EVALUATOR_FRAGMENT", "getSHARED_VARIABLE_IN_EVALUATOR_FRAGMENT()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "SYNTHETIC_ACCESSOR", "getSYNTHETIC_ACCESSOR()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "SYNTHETIC_ACCESSOR_CAPTURED_TYPE_PARAMETER", "getSYNTHETIC_ACCESSOR_CAPTURED_TYPE_PARAMETER()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "FILLED_FOR_UNBOUND_SYMBOL", "getFILLED_FOR_UNBOUND_SYMBOL()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "INLINE_LAMBDA", "getINLINE_LAMBDA()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "FUNCTION_INTERFACE_MEMBER", "getFUNCTION_INTERFACE_MEMBER()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "STUB_FOR_LENIENT", "getSTUB_FOR_LENIENT()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "STUB_FOR_TYPE_SWITCH", "getSTUB_FOR_TYPE_SWITCH()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0), new PropertyReference1Impl<>(Companion.class, "VERSION_OVERLOAD_WRAPPER", "getVERSION_OVERLOAD_WRAPPER()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", 0)};
            $$delegatedProperties = kPropertyArr;
            Companion companion = new Companion();
            $$INSTANCE = companion;
            IrDeclarationOriginImpl.Regular regular = IrDeclarationOriginImpl.Regular.INSTANCE;
            DEFINED = regular.provideDelegate(companion, kPropertyArr[0]);
            FAKE_OVERRIDE = regular.provideDelegate(companion, kPropertyArr[1]);
            FOR_LOOP_ITERATOR = regular.provideDelegate(companion, kPropertyArr[2]);
            FOR_LOOP_VARIABLE = regular.provideDelegate(companion, kPropertyArr[3]);
            FOR_LOOP_IMPLICIT_VARIABLE = regular.provideDelegate(companion, kPropertyArr[4]);
            PROPERTY_BACKING_FIELD = regular.provideDelegate(companion, kPropertyArr[5]);
            DEFAULT_PROPERTY_ACCESSOR = regular.provideDelegate(companion, kPropertyArr[6]);
            IrDeclarationOriginImpl.Synthetic synthetic = IrDeclarationOriginImpl.Synthetic.INSTANCE;
            DELEGATE = synthetic.provideDelegate(companion, kPropertyArr[7]);
            PROPERTY_DELEGATE = regular.provideDelegate(companion, kPropertyArr[8]);
            DELEGATED_PROPERTY_ACCESSOR = regular.provideDelegate(companion, kPropertyArr[9]);
            DELEGATED_MEMBER = regular.provideDelegate(companion, kPropertyArr[10]);
            ENUM_CLASS_SPECIAL_MEMBER = regular.provideDelegate(companion, kPropertyArr[11]);
            FUNCTION_FOR_DEFAULT_PARAMETER = synthetic.provideDelegate(companion, kPropertyArr[12]);
            MASK_FOR_DEFAULT_FUNCTION = synthetic.provideDelegate(companion, kPropertyArr[13]);
            DEFAULT_CONSTRUCTOR_MARKER = synthetic.provideDelegate(companion, kPropertyArr[14]);
            SYNTHETIC_CONSTRUCTOR_MARKER = synthetic.provideDelegate(companion, kPropertyArr[15]);
            METHOD_HANDLER_IN_DEFAULT_FUNCTION = synthetic.provideDelegate(companion, kPropertyArr[16]);
            MOVED_DISPATCH_RECEIVER = regular.provideDelegate(companion, kPropertyArr[17]);
            MOVED_EXTENSION_RECEIVER = regular.provideDelegate(companion, kPropertyArr[18]);
            MOVED_CONTEXT_RECEIVER = regular.provideDelegate(companion, kPropertyArr[19]);
            FILE_CLASS = regular.provideDelegate(companion, kPropertyArr[20]);
            SYNTHETIC_FILE_CLASS = synthetic.provideDelegate(companion, kPropertyArr[21]);
            JVM_MULTIFILE_CLASS = regular.provideDelegate(companion, kPropertyArr[22]);
            ERROR_CLASS = regular.provideDelegate(companion, kPropertyArr[23]);
            SCRIPT_CLASS = regular.provideDelegate(companion, kPropertyArr[24]);
            SCRIPT_THIS_RECEIVER = regular.provideDelegate(companion, kPropertyArr[25]);
            SCRIPT_STATEMENT = regular.provideDelegate(companion, kPropertyArr[26]);
            SCRIPT_EARLIER_SCRIPTS = regular.provideDelegate(companion, kPropertyArr[27]);
            SCRIPT_CALL_PARAMETER = regular.provideDelegate(companion, kPropertyArr[28]);
            SCRIPT_IMPLICIT_RECEIVER = regular.provideDelegate(companion, kPropertyArr[29]);
            SCRIPT_PROVIDED_PROPERTY = regular.provideDelegate(companion, kPropertyArr[30]);
            SCRIPT_RESULT_PROPERTY = regular.provideDelegate(companion, kPropertyArr[31]);
            REPL_EVAL_FUNCTION = regular.provideDelegate(companion, kPropertyArr[32]);
            REPL_FROM_OTHER_SNIPPET = regular.provideDelegate(companion, kPropertyArr[33]);
            GENERATED_DATA_CLASS_MEMBER = regular.provideDelegate(companion, kPropertyArr[34]);
            GENERATED_SINGLE_FIELD_VALUE_CLASS_MEMBER = regular.provideDelegate(companion, kPropertyArr[35]);
            GENERATED_MULTI_FIELD_VALUE_CLASS_MEMBER = regular.provideDelegate(companion, kPropertyArr[36]);
            LOCAL_FUNCTION = regular.provideDelegate(companion, kPropertyArr[37]);
            LOCAL_FUNCTION_FOR_LAMBDA = regular.provideDelegate(companion, kPropertyArr[38]);
            CATCH_PARAMETER = regular.provideDelegate(companion, kPropertyArr[39]);
            UNDERSCORE_PARAMETER = regular.provideDelegate(companion, kPropertyArr[40]);
            DESTRUCTURED_OBJECT_PARAMETER = regular.provideDelegate(companion, kPropertyArr[41]);
            INSTANCE_RECEIVER = regular.provideDelegate(companion, kPropertyArr[42]);
            PRIMARY_CONSTRUCTOR_PARAMETER = regular.provideDelegate(companion, kPropertyArr[43]);
            IR_DESTRUCTURED_PARAMETER_VARIABLE = regular.provideDelegate(companion, kPropertyArr[44]);
            IR_TEMPORARY_VARIABLE = regular.provideDelegate(companion, kPropertyArr[45]);
            IR_TEMPORARY_VARIABLE_FOR_INLINED_PARAMETER = regular.provideDelegate(companion, kPropertyArr[46]);
            IR_TEMPORARY_VARIABLE_FOR_INLINED_EXTENSION_RECEIVER = regular.provideDelegate(companion, kPropertyArr[47]);
            IR_EXTERNAL_DECLARATION_STUB = regular.provideDelegate(companion, kPropertyArr[48]);
            IR_EXTERNAL_JAVA_DECLARATION_STUB = regular.provideDelegate(companion, kPropertyArr[49]);
            IR_BUILTINS_STUB = regular.provideDelegate(companion, kPropertyArr[50]);
            BRIDGE = synthetic.provideDelegate(companion, kPropertyArr[51]);
            BRIDGE_SPECIAL = regular.provideDelegate(companion, kPropertyArr[52]);
            GENERATED_SETTER_GETTER = synthetic.provideDelegate(companion, kPropertyArr[53]);
            FIELD_FOR_ENUM_ENTRY = regular.provideDelegate(companion, kPropertyArr[54]);
            SYNTHETIC_HELPER_FOR_ENUM_VALUES = synthetic.provideDelegate(companion, kPropertyArr[55]);
            SYNTHETIC_HELPER_FOR_ENUM_ENTRIES = synthetic.provideDelegate(companion, kPropertyArr[56]);
            FIELD_FOR_ENUM_VALUES = synthetic.provideDelegate(companion, kPropertyArr[57]);
            FIELD_FOR_ENUM_ENTRIES = synthetic.provideDelegate(companion, kPropertyArr[58]);
            PROPERTY_FOR_ENUM_ENTRIES = regular.provideDelegate(companion, kPropertyArr[59]);
            FIELD_FOR_OBJECT_INSTANCE = regular.provideDelegate(companion, kPropertyArr[60]);
            FIELD_FOR_CLASS_CONTEXT_RECEIVER = synthetic.provideDelegate(companion, kPropertyArr[61]);
            ADAPTER_FOR_CALLABLE_REFERENCE = synthetic.provideDelegate(companion, kPropertyArr[62]);
            ADAPTER_PARAMETER_FOR_CALLABLE_REFERENCE = regular.provideDelegate(companion, kPropertyArr[63]);
            ADAPTER_FOR_SUSPEND_CONVERSION = synthetic.provideDelegate(companion, kPropertyArr[64]);
            ADAPTER_PARAMETER_FOR_SUSPEND_CONVERSION = regular.provideDelegate(companion, kPropertyArr[65]);
            ADAPTER_FOR_FUN_INTERFACE_CONSTRUCTOR = synthetic.provideDelegate(companion, kPropertyArr[66]);
            GENERATED_SAM_IMPLEMENTATION = regular.provideDelegate(companion, kPropertyArr[67]);
            SYNTHETIC_GENERATED_SAM_IMPLEMENTATION = synthetic.provideDelegate(companion, kPropertyArr[68]);
            SYNTHETIC_JAVA_PROPERTY_DELEGATE = synthetic.provideDelegate(companion, kPropertyArr[69]);
            FIELD_FOR_OUTER_THIS = synthetic.provideDelegate(companion, kPropertyArr[70]);
            CONTINUATION = synthetic.provideDelegate(companion, kPropertyArr[71]);
            LOWERED_SUSPEND_FUNCTION = synthetic.provideDelegate(companion, kPropertyArr[72]);
            SHARED_VARIABLE_IN_EVALUATOR_FRAGMENT = synthetic.provideDelegate(companion, kPropertyArr[73]);
            SYNTHETIC_ACCESSOR = synthetic.provideDelegate(companion, kPropertyArr[74]);
            SYNTHETIC_ACCESSOR_CAPTURED_TYPE_PARAMETER = synthetic.provideDelegate(companion, kPropertyArr[75]);
            FILLED_FOR_UNBOUND_SYMBOL = regular.provideDelegate(companion, kPropertyArr[76]);
            INLINE_LAMBDA = regular.provideDelegate(companion, kPropertyArr[77]);
            FUNCTION_INTERFACE_MEMBER = regular.provideDelegate(companion, kPropertyArr[78]);
            STUB_FOR_LENIENT = synthetic.provideDelegate(companion, kPropertyArr[79]);
            STUB_FOR_TYPE_SWITCH = synthetic.provideDelegate(companion, kPropertyArr[80]);
            VERSION_OVERLOAD_WRAPPER = regular.provideDelegate(companion, kPropertyArr[81]);
        }

        private Companion() {
        }

        public final IrDeclarationOrigin getADAPTER_FOR_CALLABLE_REFERENCE() {
            return ADAPTER_FOR_CALLABLE_REFERENCE.getValue();
        }

        public final IrDeclarationOrigin getADAPTER_FOR_FUN_INTERFACE_CONSTRUCTOR() {
            return ADAPTER_FOR_FUN_INTERFACE_CONSTRUCTOR.getValue();
        }

        public final IrDeclarationOrigin getADAPTER_FOR_SUSPEND_CONVERSION() {
            return ADAPTER_FOR_SUSPEND_CONVERSION.getValue();
        }

        public final IrDeclarationOrigin getADAPTER_PARAMETER_FOR_CALLABLE_REFERENCE() {
            return ADAPTER_PARAMETER_FOR_CALLABLE_REFERENCE.getValue();
        }

        public final IrDeclarationOrigin getADAPTER_PARAMETER_FOR_SUSPEND_CONVERSION() {
            return ADAPTER_PARAMETER_FOR_SUSPEND_CONVERSION.getValue();
        }

        public final IrDeclarationOrigin getBRIDGE() {
            return BRIDGE.getValue();
        }

        public final IrDeclarationOrigin getBRIDGE_SPECIAL() {
            return BRIDGE_SPECIAL.getValue();
        }

        public final IrDeclarationOrigin getCATCH_PARAMETER() {
            return CATCH_PARAMETER.getValue();
        }

        public final IrDeclarationOrigin getCONTINUATION() {
            return CONTINUATION.getValue();
        }

        public final IrDeclarationOrigin getDEFAULT_CONSTRUCTOR_MARKER() {
            return DEFAULT_CONSTRUCTOR_MARKER.getValue();
        }

        public final IrDeclarationOrigin getDEFAULT_PROPERTY_ACCESSOR() {
            return DEFAULT_PROPERTY_ACCESSOR.getValue();
        }

        public final IrDeclarationOrigin getDEFINED() {
            return DEFINED.getValue();
        }

        public final IrDeclarationOrigin getDELEGATE() {
            return DELEGATE.getValue();
        }

        public final IrDeclarationOrigin getDELEGATED_MEMBER() {
            return DELEGATED_MEMBER.getValue();
        }

        public final IrDeclarationOrigin getDELEGATED_PROPERTY_ACCESSOR() {
            return DELEGATED_PROPERTY_ACCESSOR.getValue();
        }

        public final IrDeclarationOrigin getDESTRUCTURED_OBJECT_PARAMETER() {
            return DESTRUCTURED_OBJECT_PARAMETER.getValue();
        }

        public final IrDeclarationOrigin getENUM_CLASS_SPECIAL_MEMBER() {
            return ENUM_CLASS_SPECIAL_MEMBER.getValue();
        }

        public final IrDeclarationOrigin getERROR_CLASS() {
            return ERROR_CLASS.getValue();
        }

        public final IrDeclarationOrigin getFAKE_OVERRIDE() {
            return FAKE_OVERRIDE.getValue();
        }

        public final IrDeclarationOrigin getFIELD_FOR_CLASS_CONTEXT_RECEIVER() {
            return FIELD_FOR_CLASS_CONTEXT_RECEIVER.getValue();
        }

        public final IrDeclarationOrigin getFIELD_FOR_ENUM_ENTRIES() {
            return FIELD_FOR_ENUM_ENTRIES.getValue();
        }

        public final IrDeclarationOrigin getFIELD_FOR_ENUM_ENTRY() {
            return FIELD_FOR_ENUM_ENTRY.getValue();
        }

        public final IrDeclarationOrigin getFIELD_FOR_ENUM_VALUES() {
            return FIELD_FOR_ENUM_VALUES.getValue();
        }

        public final IrDeclarationOrigin getFIELD_FOR_OBJECT_INSTANCE() {
            return FIELD_FOR_OBJECT_INSTANCE.getValue();
        }

        public final IrDeclarationOrigin getFIELD_FOR_OUTER_THIS() {
            return FIELD_FOR_OUTER_THIS.getValue();
        }

        public final IrDeclarationOrigin getFILE_CLASS() {
            return FILE_CLASS.getValue();
        }

        public final IrDeclarationOrigin getFILLED_FOR_UNBOUND_SYMBOL() {
            return FILLED_FOR_UNBOUND_SYMBOL.getValue();
        }

        public final IrDeclarationOrigin getFOR_LOOP_IMPLICIT_VARIABLE() {
            return FOR_LOOP_IMPLICIT_VARIABLE.getValue();
        }

        public final IrDeclarationOrigin getFOR_LOOP_ITERATOR() {
            return FOR_LOOP_ITERATOR.getValue();
        }

        public final IrDeclarationOrigin getFOR_LOOP_VARIABLE() {
            return FOR_LOOP_VARIABLE.getValue();
        }

        public final IrDeclarationOrigin getFUNCTION_FOR_DEFAULT_PARAMETER() {
            return FUNCTION_FOR_DEFAULT_PARAMETER.getValue();
        }

        public final IrDeclarationOrigin getFUNCTION_INTERFACE_MEMBER() {
            return FUNCTION_INTERFACE_MEMBER.getValue();
        }

        public final IrDeclarationOrigin getGENERATED_DATA_CLASS_MEMBER() {
            return GENERATED_DATA_CLASS_MEMBER.getValue();
        }

        public final IrDeclarationOrigin getGENERATED_MULTI_FIELD_VALUE_CLASS_MEMBER() {
            return GENERATED_MULTI_FIELD_VALUE_CLASS_MEMBER.getValue();
        }

        public final IrDeclarationOrigin getGENERATED_SAM_IMPLEMENTATION() {
            return GENERATED_SAM_IMPLEMENTATION.getValue();
        }

        public final IrDeclarationOrigin getGENERATED_SETTER_GETTER() {
            return GENERATED_SETTER_GETTER.getValue();
        }

        public final IrDeclarationOrigin getGENERATED_SINGLE_FIELD_VALUE_CLASS_MEMBER() {
            return GENERATED_SINGLE_FIELD_VALUE_CLASS_MEMBER.getValue();
        }

        public final IrDeclarationOrigin getINLINE_LAMBDA() {
            return INLINE_LAMBDA.getValue();
        }

        public final IrDeclarationOrigin getINSTANCE_RECEIVER() {
            return INSTANCE_RECEIVER.getValue();
        }

        public final IrDeclarationOrigin getIR_BUILTINS_STUB() {
            return IR_BUILTINS_STUB.getValue();
        }

        public final IrDeclarationOrigin getIR_DESTRUCTURED_PARAMETER_VARIABLE() {
            return IR_DESTRUCTURED_PARAMETER_VARIABLE.getValue();
        }

        public final IrDeclarationOrigin getIR_EXTERNAL_DECLARATION_STUB() {
            return IR_EXTERNAL_DECLARATION_STUB.getValue();
        }

        public final IrDeclarationOrigin getIR_EXTERNAL_JAVA_DECLARATION_STUB() {
            return IR_EXTERNAL_JAVA_DECLARATION_STUB.getValue();
        }

        public final IrDeclarationOrigin getIR_TEMPORARY_VARIABLE() {
            return IR_TEMPORARY_VARIABLE.getValue();
        }

        public final IrDeclarationOrigin getIR_TEMPORARY_VARIABLE_FOR_INLINED_EXTENSION_RECEIVER() {
            return IR_TEMPORARY_VARIABLE_FOR_INLINED_EXTENSION_RECEIVER.getValue();
        }

        public final IrDeclarationOrigin getIR_TEMPORARY_VARIABLE_FOR_INLINED_PARAMETER() {
            return IR_TEMPORARY_VARIABLE_FOR_INLINED_PARAMETER.getValue();
        }

        public final IrDeclarationOrigin getJVM_MULTIFILE_CLASS() {
            return JVM_MULTIFILE_CLASS.getValue();
        }

        public final IrDeclarationOrigin getLOCAL_FUNCTION() {
            return LOCAL_FUNCTION.getValue();
        }

        public final IrDeclarationOrigin getLOCAL_FUNCTION_FOR_LAMBDA() {
            return LOCAL_FUNCTION_FOR_LAMBDA.getValue();
        }

        public final IrDeclarationOrigin getLOWERED_SUSPEND_FUNCTION() {
            return LOWERED_SUSPEND_FUNCTION.getValue();
        }

        public final IrDeclarationOrigin getMASK_FOR_DEFAULT_FUNCTION() {
            return MASK_FOR_DEFAULT_FUNCTION.getValue();
        }

        public final IrDeclarationOrigin getMETHOD_HANDLER_IN_DEFAULT_FUNCTION() {
            return METHOD_HANDLER_IN_DEFAULT_FUNCTION.getValue();
        }

        public final IrDeclarationOrigin getMOVED_CONTEXT_RECEIVER() {
            return MOVED_CONTEXT_RECEIVER.getValue();
        }

        public final IrDeclarationOrigin getMOVED_DISPATCH_RECEIVER() {
            return MOVED_DISPATCH_RECEIVER.getValue();
        }

        public final IrDeclarationOrigin getMOVED_EXTENSION_RECEIVER() {
            return MOVED_EXTENSION_RECEIVER.getValue();
        }

        public final IrDeclarationOrigin getPRIMARY_CONSTRUCTOR_PARAMETER() {
            return PRIMARY_CONSTRUCTOR_PARAMETER.getValue();
        }

        public final IrDeclarationOrigin getPROPERTY_BACKING_FIELD() {
            return PROPERTY_BACKING_FIELD.getValue();
        }

        public final IrDeclarationOrigin getPROPERTY_DELEGATE() {
            return PROPERTY_DELEGATE.getValue();
        }

        public final IrDeclarationOrigin getPROPERTY_FOR_ENUM_ENTRIES() {
            return PROPERTY_FOR_ENUM_ENTRIES.getValue();
        }

        public final IrDeclarationOrigin getREPL_EVAL_FUNCTION() {
            return REPL_EVAL_FUNCTION.getValue();
        }

        public final IrDeclarationOrigin getREPL_FROM_OTHER_SNIPPET() {
            return REPL_FROM_OTHER_SNIPPET.getValue();
        }

        public final IrDeclarationOrigin getSCRIPT_CALL_PARAMETER() {
            return SCRIPT_CALL_PARAMETER.getValue();
        }

        public final IrDeclarationOrigin getSCRIPT_CLASS() {
            return SCRIPT_CLASS.getValue();
        }

        public final IrDeclarationOrigin getSCRIPT_EARLIER_SCRIPTS() {
            return SCRIPT_EARLIER_SCRIPTS.getValue();
        }

        public final IrDeclarationOrigin getSCRIPT_IMPLICIT_RECEIVER() {
            return SCRIPT_IMPLICIT_RECEIVER.getValue();
        }

        public final IrDeclarationOrigin getSCRIPT_PROVIDED_PROPERTY() {
            return SCRIPT_PROVIDED_PROPERTY.getValue();
        }

        public final IrDeclarationOrigin getSCRIPT_RESULT_PROPERTY() {
            return SCRIPT_RESULT_PROPERTY.getValue();
        }

        public final IrDeclarationOrigin getSCRIPT_STATEMENT() {
            return SCRIPT_STATEMENT.getValue();
        }

        public final IrDeclarationOrigin getSCRIPT_THIS_RECEIVER() {
            return SCRIPT_THIS_RECEIVER.getValue();
        }

        public final IrDeclarationOrigin getSHARED_VARIABLE_IN_EVALUATOR_FRAGMENT() {
            return SHARED_VARIABLE_IN_EVALUATOR_FRAGMENT.getValue();
        }

        public final IrDeclarationOrigin getSTUB_FOR_LENIENT() {
            return STUB_FOR_LENIENT.getValue();
        }

        public final IrDeclarationOrigin getSTUB_FOR_TYPE_SWITCH() {
            return STUB_FOR_TYPE_SWITCH.getValue();
        }

        public final IrDeclarationOrigin getSYNTHETIC_ACCESSOR() {
            return SYNTHETIC_ACCESSOR.getValue();
        }

        public final IrDeclarationOrigin getSYNTHETIC_ACCESSOR_CAPTURED_TYPE_PARAMETER() {
            return SYNTHETIC_ACCESSOR_CAPTURED_TYPE_PARAMETER.getValue();
        }

        public final IrDeclarationOrigin getSYNTHETIC_CONSTRUCTOR_MARKER() {
            return SYNTHETIC_CONSTRUCTOR_MARKER.getValue();
        }

        public final IrDeclarationOrigin getSYNTHETIC_FILE_CLASS() {
            return SYNTHETIC_FILE_CLASS.getValue();
        }

        public final IrDeclarationOrigin getSYNTHETIC_GENERATED_SAM_IMPLEMENTATION() {
            return SYNTHETIC_GENERATED_SAM_IMPLEMENTATION.getValue();
        }

        public final IrDeclarationOrigin getSYNTHETIC_HELPER_FOR_ENUM_ENTRIES() {
            return SYNTHETIC_HELPER_FOR_ENUM_ENTRIES.getValue();
        }

        public final IrDeclarationOrigin getSYNTHETIC_HELPER_FOR_ENUM_VALUES() {
            return SYNTHETIC_HELPER_FOR_ENUM_VALUES.getValue();
        }

        public final IrDeclarationOrigin getSYNTHETIC_JAVA_PROPERTY_DELEGATE() {
            return SYNTHETIC_JAVA_PROPERTY_DELEGATE.getValue();
        }

        public final IrDeclarationOrigin getUNDERSCORE_PARAMETER() {
            return UNDERSCORE_PARAMETER.getValue();
        }

        public final IrDeclarationOrigin getVERSION_OVERLOAD_WRAPPER() {
            return VERSION_OVERLOAD_WRAPPER.getValue();
        }
    }

    String getName();

    default boolean isSynthetic() {
        return false;
    }
}
