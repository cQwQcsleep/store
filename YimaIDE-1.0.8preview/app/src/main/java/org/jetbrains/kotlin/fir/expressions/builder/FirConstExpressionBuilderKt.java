package org.jetbrains.kotlin.fir.expressions.builder;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.StandardTypes;
import org.jetbrains.kotlin.fir.builder.FirBuilderDslKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.impl.FirLiteralExpressionImpl;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.types.ConstantValueKind;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u001aH\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\u0006\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u001a\f\u0010\u000f\u001a\u0004\u0018\u00010\u0010*\u00020\u0005¨\u0006\u0011"}, d2 = {"buildLiteralExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirLiteralExpression;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "kind", "Lorg/jetbrains/kotlin/types/ConstantValueKind;", "value", Argument.Delimiters.none, "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "setType", Argument.Delimiters.none, "prefix", Argument.Delimiters.none, "toConeType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirConstExpressionBuilderKt {
    public static final FirLiteralExpression buildLiteralExpression(KtSourceElement ktSourceElement, ConstantValueKind constantValueKind, Object obj, List<FirAnnotation> list, boolean z, String str) {
        constantValueKind.getClass();
        return new FirLiteralExpressionImpl(ktSourceElement, z ? toConeType(constantValueKind) : null, FirBuilderDslKt.toMutableOrEmpty(list), constantValueKind, obj, str, null);
    }

    public static /* synthetic */ FirLiteralExpression buildLiteralExpression$default(KtSourceElement ktSourceElement, ConstantValueKind constantValueKind, Object obj, List list, boolean z, String str, int i, Object obj2) {
        if ((i & 8) != 0) {
            list = null;
        }
        if ((i & 32) != 0) {
            str = null;
        }
        return buildLiteralExpression(ktSourceElement, constantValueKind, obj, list, z, str);
    }

    public static final ConeKotlinType toConeType(ConstantValueKind constantValueKind) {
        constantValueKind.getClass();
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.Boolean.INSTANCE)) {
            return StandardTypes.INSTANCE.getBoolean();
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.Byte.INSTANCE)) {
            return StandardTypes.INSTANCE.getByte();
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.Char.INSTANCE)) {
            return StandardTypes.INSTANCE.getChar();
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.Double.INSTANCE)) {
            return StandardTypes.INSTANCE.getDouble();
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.Float.INSTANCE)) {
            return StandardTypes.INSTANCE.getFloat();
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.Int.INSTANCE)) {
            return StandardTypes.INSTANCE.getInt();
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.Long.INSTANCE)) {
            return StandardTypes.INSTANCE.getLong();
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.Null.INSTANCE)) {
            return StandardTypes.INSTANCE.getNullableAny();
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.Short.INSTANCE)) {
            return StandardTypes.INSTANCE.getShort();
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.String.INSTANCE)) {
            return StandardTypes.INSTANCE.getString();
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.UnsignedByte.INSTANCE)) {
            return StandardTypes.INSTANCE.getUByte();
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.UnsignedInt.INSTANCE)) {
            return StandardTypes.INSTANCE.getUInt();
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.UnsignedLong.INSTANCE)) {
            return StandardTypes.INSTANCE.getULong();
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.UnsignedShort.INSTANCE)) {
            return StandardTypes.INSTANCE.getUShort();
        }
        if (Intrinsics.areEqual(constantValueKind, ConstantValueKind.IntegerLiteral.INSTANCE) || Intrinsics.areEqual(constantValueKind, ConstantValueKind.UnsignedIntegerLiteral.INSTANCE) || Intrinsics.areEqual(constantValueKind, ConstantValueKind.Error.INSTANCE)) {
            return null;
        }
        bu8.a();
        return null;
    }
}
