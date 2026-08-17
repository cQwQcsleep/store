package org.jetbrains.kotlin.codegen.inline;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.Type;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0019\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/ExtractedDefaultLambda;", Argument.Delimiters.none, ModuleXmlParser.TYPE, "Lorg/jetbrains/org/objectweb/asm/Type;", "capturedArgs", Argument.Delimiters.none, "offset", Argument.Delimiters.none, "needReification", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/org/objectweb/asm/Type;[Lorg/jetbrains/org/objectweb/asm/Type;IZ)V", "getType", "()Lorg/jetbrains/org/objectweb/asm/Type;", "getCapturedArgs", "()[Lorg/jetbrains/org/objectweb/asm/Type;", "[Lorg/jetbrains/org/objectweb/asm/Type;", "getOffset", "()I", "getNeedReification", "()Z", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ExtractedDefaultLambda {
    private final Type[] capturedArgs;
    private final boolean needReification;
    private final int offset;
    private final Type type;

    public ExtractedDefaultLambda(Type type, Type[] typeArr, int i, boolean z) {
        type.getClass();
        typeArr.getClass();
        this.type = type;
        this.capturedArgs = typeArr;
        this.offset = i;
        this.needReification = z;
    }

    public final Type[] getCapturedArgs() {
        return this.capturedArgs;
    }

    public final boolean getNeedReification() {
        return this.needReification;
    }

    public final int getOffset() {
        return this.offset;
    }

    public final Type getType() {
        return this.type;
    }
}
