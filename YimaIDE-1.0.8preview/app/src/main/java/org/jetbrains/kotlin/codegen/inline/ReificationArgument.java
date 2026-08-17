package org.jetbrains.kotlin.codegen.inline;

import kotlin.Metadata;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\f\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0006\u0010\u0010\u001a\u00020\u0003J\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/ReificationArgument;", Argument.Delimiters.none, "parameterName", Argument.Delimiters.none, "nullable", Argument.Delimiters.none, "arrayDepth", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;ZI)V", "getParameterName", "()Ljava/lang/String;", "getNullable", "()Z", "getArrayDepth", "()I", "asString", "combine", "replacement", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ReificationArgument {
    private final int arrayDepth;
    private final boolean nullable;
    private final String parameterName;

    public ReificationArgument(String str, boolean z, int i) {
        str.getClass();
        this.parameterName = str;
        this.nullable = z;
        this.arrayDepth = i;
    }

    public final String asString() {
        StringBuilder sb = new StringBuilder();
        sb.append(StringsKt.repeat("[", this.arrayDepth));
        sb.append(this.parameterName);
        sb.append(this.nullable ? "?" : Argument.Delimiters.none);
        return sb.toString();
    }

    public final ReificationArgument combine(ReificationArgument replacement) {
        replacement.getClass();
        return new ReificationArgument(replacement.parameterName, this.nullable || (replacement.nullable && this.arrayDepth == 0), this.arrayDepth + replacement.arrayDepth);
    }

    public final int getArrayDepth() {
        return this.arrayDepth;
    }

    public final boolean getNullable() {
        return this.nullable;
    }

    public final String getParameterName() {
        return this.parameterName;
    }
}
