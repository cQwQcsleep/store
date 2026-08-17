package org.jetbrains.kotlin.fir.lightTree.fir.modifier;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/lightTree/fir/modifier/TypeProjectionModifierList;", "Lorg/jetbrains/kotlin/fir/lightTree/fir/modifier/ModifierList;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "varianceModifiers", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/KtSourceElement;J)V", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "org.jetbrains.kotlin.fir:light-tree2fir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TypeProjectionModifierList extends ModifierList {
    private final KtSourceElement source;

    public /* synthetic */ TypeProjectionModifierList(KtSourceElement ktSourceElement, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : ktSourceElement, (i & 2) != 0 ? ModifierFlag.NONE.getValue() : j);
    }

    public final KtSourceElement getSource() {
        return this.source;
    }

    public TypeProjectionModifierList(KtSourceElement ktSourceElement, long j) {
        super(j);
        this.source = ktSourceElement;
    }

    public TypeProjectionModifierList() {
        this(null, 0L, 3, null);
    }
}
