package org.jetbrains.kotlin.config.nativeBinaryOptions;

import kotlin.Metadata;
import kotlin.UInt;
import kotlin.text.UStringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\bÂ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\u0005\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¢\u0006\u0002\b\bR\u0016\u0010\t\u001a\u0004\u0018\u00010\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/config/nativeBinaryOptions/UIntValueParser;", "Lorg/jetbrains/kotlin/config/nativeBinaryOptions/BinaryOption$ValueParser;", "Lkotlin/UInt;", "<init>", "()V", "parse", "value", Argument.Delimiters.none, "parse-gbq4QnA", "validValuesHint", "getValidValuesHint", "()Ljava/lang/String;", "org.jetbrains.kotlin:binary-options"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class UIntValueParser implements BinaryOption.ValueParser<UInt> {
    public static final UIntValueParser INSTANCE = new UIntValueParser();

    private UIntValueParser() {
    }

    @Override // org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOption.ValueParser
    public String getValidValuesHint() {
        return "non-negative-number";
    }

    @Override // org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOption.ValueParser
    /* JADX INFO: renamed from: parse-gbq4QnA, reason: not valid java name and merged with bridge method [inline-methods] */
    public UInt parse(String value) {
        value.getClass();
        return UStringsKt.toUIntOrNull(value);
    }
}
