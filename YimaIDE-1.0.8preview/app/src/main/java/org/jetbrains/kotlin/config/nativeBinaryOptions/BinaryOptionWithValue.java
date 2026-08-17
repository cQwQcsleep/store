package org.jetbrains.kotlin.config.nativeBinaryOptions;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.CompilerConfigurationKey;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B%\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0010\u0005\u001a\u00028\u0000\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0006\u0010\u0011\u001a\u00020\u0007R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0005\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/config/nativeBinaryOptions/BinaryOptionWithValue;", "T", Argument.Delimiters.none, "compilerConfigurationKey", "Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", "value", "rawStringValue", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;Ljava/lang/Object;Ljava/lang/String;)V", "getCompilerConfigurationKey", "()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getRawStringValue", "()Ljava/lang/String;", "asCompilerCliArgument", "org.jetbrains.kotlin:binary-options"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class BinaryOptionWithValue<T> {
    private final CompilerConfigurationKey<T> compilerConfigurationKey;
    private final String rawStringValue;
    private final T value;

    /* JADX WARN: Multi-variable type inference failed */
    public BinaryOptionWithValue(CompilerConfigurationKey<? extends T> compilerConfigurationKey, T t, String str) {
        compilerConfigurationKey.getClass();
        t.getClass();
        str.getClass();
        this.compilerConfigurationKey = compilerConfigurationKey;
        this.value = t;
        this.rawStringValue = str;
    }

    public final String asCompilerCliArgument() {
        return "-Xbinary=" + this.compilerConfigurationKey + '=' + this.rawStringValue;
    }

    public final CompilerConfigurationKey<T> getCompilerConfigurationKey() {
        return this.compilerConfigurationKey;
    }

    public final String getRawStringValue() {
        return this.rawStringValue;
    }

    public final T getValue() {
        return this.value;
    }
}
