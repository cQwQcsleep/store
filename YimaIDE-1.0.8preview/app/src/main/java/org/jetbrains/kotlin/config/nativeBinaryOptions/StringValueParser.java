package org.jetbrains.kotlin.config.nativeBinaryOptions;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\b\bÂ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0002H\u0016R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/config/nativeBinaryOptions/StringValueParser;", "Lorg/jetbrains/kotlin/config/nativeBinaryOptions/BinaryOption$ValueParser;", Argument.Delimiters.none, "<init>", "()V", "parse", "value", "validValuesHint", "getValidValuesHint", "()Ljava/lang/String;", "org.jetbrains.kotlin:binary-options"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class StringValueParser implements BinaryOption.ValueParser<String> {
    public static final StringValueParser INSTANCE = new StringValueParser();

    private StringValueParser() {
    }

    @Override // org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOption.ValueParser
    public String getValidValuesHint() {
        return "string";
    }

    @Override // org.jetbrains.kotlin.config.nativeBinaryOptions.BinaryOption.ValueParser
    public String parse(String value) {
        value.getClass();
        return value;
    }
}
