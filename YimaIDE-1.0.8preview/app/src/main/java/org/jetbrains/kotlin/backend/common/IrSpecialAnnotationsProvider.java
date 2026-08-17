package org.jetbrains.kotlin.backend.common;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.ir.expressions.IrAnnotation;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0005H&J\b\u0010\u0007\u001a\u00020\u0005H&J\b\u0010\b\u001a\u00020\u0005H&J\b\u0010\t\u001a\u00020\u0005H&¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", Argument.Delimiters.none, "<init>", "()V", "generateEnhancedNullabilityAnnotation", "Lorg/jetbrains/kotlin/ir/expressions/IrAnnotation;", "generateFlexibleNullabilityAnnotation", "generateFlexibleMutabilityAnnotation", "generateFlexibleArrayElementVarianceAnnotation", "generateRawTypeAnnotation", "org.jetbrains.kotlin:ir.backend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class IrSpecialAnnotationsProvider {
    public abstract IrAnnotation generateEnhancedNullabilityAnnotation();

    public abstract IrAnnotation generateFlexibleArrayElementVarianceAnnotation();

    public abstract IrAnnotation generateFlexibleMutabilityAnnotation();

    public abstract IrAnnotation generateFlexibleNullabilityAnnotation();

    public abstract IrAnnotation generateRawTypeAnnotation();
}
