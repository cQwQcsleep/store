package org.jetbrains.kotlin.fir.types;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.types.model.TypeSystemContextHelpersKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u000e\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010R\u001b\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/CapturedArguments;", Argument.Delimiters.none, "capturedArguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "originalType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "<init>", "([Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "getCapturedArguments", "()[Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "[Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "isSuitableForType", Argument.Delimiters.none, ModuleXmlParser.TYPE, "context", "Lorg/jetbrains/kotlin/fir/types/ConeTypeContext;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class CapturedArguments {
    private final ConeTypeProjection[] capturedArguments;
    private final ConeKotlinType originalType;

    public CapturedArguments(ConeTypeProjection[] coneTypeProjectionArr, ConeKotlinType coneKotlinType) {
        coneTypeProjectionArr.getClass();
        coneKotlinType.getClass();
        this.capturedArguments = coneTypeProjectionArr;
        this.originalType = coneKotlinType;
    }

    public final ConeTypeProjection[] getCapturedArguments() {
        return this.capturedArguments;
    }

    public final boolean isSuitableForType(ConeKotlinType type, ConeTypeContext context) {
        type.getClass();
        context.getClass();
        Iterable<IndexedValue> iterableWithIndex = ArraysKt.withIndex(type.getTypeArguments());
        if (!(iterableWithIndex instanceof Collection) || !((Collection) iterableWithIndex).isEmpty()) {
            for (IndexedValue indexedValue : iterableWithIndex) {
                int index = indexedValue.getIndex();
                ConeTypeProjection coneTypeProjection = (ConeTypeProjection) indexedValue.component2();
                if (this.originalType.getTypeArguments().length <= index || !Intrinsics.areEqual(coneTypeProjection, this.originalType.getTypeArguments()[index])) {
                    return false;
                }
            }
        }
        return Intrinsics.areEqual(TypeSystemContextHelpersKt.typeConstructor(this.originalType, context), TypeSystemContextHelpersKt.typeConstructor(type, context)) || ConeFlexibleTypeBoundsChecker.INSTANCE.areTypesMayBeLowerAndUpperBoundsOfSameFlexibleTypeByMutability(this.originalType, type);
    }
}
