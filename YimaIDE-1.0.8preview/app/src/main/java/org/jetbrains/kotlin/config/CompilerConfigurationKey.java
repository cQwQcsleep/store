package org.jetbrains.kotlin.config;

import com.intellij.openapi.util.Key;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u0000 \r*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\u00020\u0002:\u0001\rB\u0017\b\u0002\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0004\b\u0005\u0010\u0006B\u0011\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\u0005\u0010\tJ\n\u0010\f\u001a\u00020\bH\u0096\u0080\u0004R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", "T", Argument.Delimiters.none, "ideaKey", "Lcom/intellij/openapi/util/Key;", "<init>", "(Lcom/intellij/openapi/util/Key;)V", ModuleXmlParser.NAME, Argument.Delimiters.none, "(Ljava/lang/String;)V", "getIdeaKey$org_jetbrains_kotlin_config", "()Lcom/intellij/openapi/util/Key;", "toString", "Companion", "org.jetbrains.kotlin:config"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CompilerConfigurationKey<T> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Key<T> ideaKey;

    /* JADX WARN: Illegal instructions before constructor call */
    public CompilerConfigurationKey(String str) {
        str.getClass();
        Key keyCreate = Key.create(str);
        keyCreate.getClass();
        this(keyCreate);
    }

    @JvmStatic
    public static final <T> CompilerConfigurationKey<T> create(String str) {
        return INSTANCE.create(str);
    }

    public final Key<T> getIdeaKey$org_jetbrains_kotlin_config() {
        return this.ideaKey;
    }

    public String toString() {
        String string = this.ideaKey.toString();
        string.getClass();
        return string;
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\b\b\u0001\u0010\u0006*\u00020\u00012\u0006\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/config/CompilerConfigurationKey$Companion;", Argument.Delimiters.none, "<init>", "()V", CoroutineCodegenUtilKt.SUSPEND_FUNCTION_CREATE_METHOD_NAME, "Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", "T", ModuleXmlParser.NAME, Argument.Delimiters.none, "org.jetbrains.kotlin:config"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final <T> CompilerConfigurationKey<T> create(String name) {
            name.getClass();
            return new CompilerConfigurationKey<>(name);
        }

        private Companion() {
        }
    }

    private CompilerConfigurationKey(Key<T> key) {
        this.ideaKey = key;
    }
}
