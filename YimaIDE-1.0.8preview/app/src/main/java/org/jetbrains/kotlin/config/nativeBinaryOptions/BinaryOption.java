package org.jetbrains.kotlin.config.nativeBinaryOptions;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.CompilerConfigurationKey;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002:\u0001\u0011B-\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b¢\u0006\u0004\b\t\u0010\nR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/config/nativeBinaryOptions/BinaryOption;", "T", Argument.Delimiters.none, ModuleXmlParser.NAME, Argument.Delimiters.none, "valueParser", "Lorg/jetbrains/kotlin/config/nativeBinaryOptions/BinaryOption$ValueParser;", "compilerConfigurationKey", "Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/config/nativeBinaryOptions/BinaryOption$ValueParser;Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;)V", "getName", "()Ljava/lang/String;", "getValueParser", "()Lorg/jetbrains/kotlin/config/nativeBinaryOptions/BinaryOption$ValueParser;", "getCompilerConfigurationKey", "()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", "ValueParser", "org.jetbrains.kotlin:binary-options"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class BinaryOption<T> {
    private final CompilerConfigurationKey<T> compilerConfigurationKey;
    private final String name;
    private final ValueParser<T> valueParser;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\bf\u0018\u0000*\b\b\u0001\u0010\u0001*\u00020\u00022\u00020\u0002J\u0017\u0010\u0003\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0004\u001a\u00020\u0005H&¢\u0006\u0002\u0010\u0006R\u0014\u0010\u0007\u001a\u0004\u0018\u00010\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/config/nativeBinaryOptions/BinaryOption$ValueParser;", "T", Argument.Delimiters.none, "parse", "value", Argument.Delimiters.none, "(Ljava/lang/String;)Ljava/lang/Object;", "validValuesHint", "getValidValuesHint", "()Ljava/lang/String;", "org.jetbrains.kotlin:binary-options"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public interface ValueParser<T> {
        String getValidValuesHint();

        T parse(String value);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public BinaryOption(String str, ValueParser<T> valueParser, CompilerConfigurationKey<? extends T> compilerConfigurationKey) {
        str.getClass();
        valueParser.getClass();
        compilerConfigurationKey.getClass();
        this.name = str;
        this.valueParser = valueParser;
        this.compilerConfigurationKey = compilerConfigurationKey;
    }

    public final CompilerConfigurationKey<T> getCompilerConfigurationKey() {
        return this.compilerConfigurationKey;
    }

    public final String getName() {
        return this.name;
    }

    public final ValueParser<T> getValueParser() {
        return this.valueParser;
    }

    public /* synthetic */ BinaryOption(String str, ValueParser valueParser, CompilerConfigurationKey compilerConfigurationKey, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, valueParser, (i & 4) != 0 ? CompilerConfigurationKey.INSTANCE.create(str) : compilerConfigurationKey);
    }
}
