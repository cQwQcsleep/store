package org.jetbrains.kotlin.fir.expressions;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/FirFunctionConversionKind;", Argument.Delimiters.none, "<init>", "()V", "Sam", "BetweenFunctionTypes", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionConversionKind$BetweenFunctionTypes;", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionConversionKind$Sam;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirFunctionConversionKind {

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/FirFunctionConversionKind$BetweenFunctionTypes;", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionConversionKind;", "isFromSimpleToCustom", Argument.Delimiters.none, "<init>", "(Z)V", "()Z", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class BetweenFunctionTypes extends FirFunctionConversionKind {
        private final boolean isFromSimpleToCustom;

        public BetweenFunctionTypes(boolean z) {
            super(null);
            this.isFromSimpleToCustom = z;
        }

        /* JADX INFO: renamed from: isFromSimpleToCustom, reason: from getter */
        public final boolean getIsFromSimpleToCustom() {
            return this.isFromSimpleToCustom;
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/FirFunctionConversionKind$Sam;", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionConversionKind;", "<init>", "()V", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class Sam extends FirFunctionConversionKind {
        public static final Sam INSTANCE = new Sam();

        private Sam() {
            super(null);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof Sam);
        }

        public int hashCode() {
            return -307653915;
        }

        public String toString() {
            return "Sam";
        }
    }

    public /* synthetic */ FirFunctionConversionKind(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private FirFunctionConversionKind() {
    }
}
