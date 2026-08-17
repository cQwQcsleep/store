package org.jetbrains.kotlin.cli.common.repl;

import java.util.List;
import javax.script.ScriptEngineFactory;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.config.KotlinCompilerVersion;
import org.jetbrains.kotlin.idea.KotlinFileType;
import org.jetbrains.kotlin.library.abi.AbiCompoundName;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\b\u0010\u0007\u001a\u00020\u0005H\u0016J\b\u0010\b\u001a\u00020\u0005H\u0016J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\nH\u0016J\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\nH\u0016J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\nH\u0016J\u0012\u0010\r\u001a\u00020\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005H\u0016J1\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00052\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0013\"\u00020\u0005H\u0016¢\u0006\u0002\u0010\u0014J!\u0010\u0015\u001a\u00020\u00052\u0012\u0010\u0016\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0013\"\u00020\u0005H\u0016¢\u0006\u0002\u0010\u0017J\u0014\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/repl/KotlinJsr223JvmScriptEngineFactoryBase;", "Ljavax/script/ScriptEngineFactory;", "<init>", "()V", "getLanguageName", "", "getLanguageVersion", "getEngineName", "getEngineVersion", "getExtensions", "", "getMimeTypes", "getNames", "getOutputStatement", "toDisplay", "getMethodCallSyntax", "obj", "m", "args", "", "(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Ljava/lang/String;", "getProgram", "statements", "([Ljava/lang/String;)Ljava/lang/String;", "getParameter", "", "key", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class KotlinJsr223JvmScriptEngineFactoryBase implements ScriptEngineFactory {
    public String getEngineName() {
        return "kotlin";
    }

    public String getEngineVersion() {
        String str = KotlinCompilerVersion.VERSION;
        str.getClass();
        return str;
    }

    public List<String> getExtensions() {
        return CollectionsKt.listOf(KotlinFileType.SCRIPT_EXTENSION);
    }

    public String getLanguageName() {
        return "kotlin";
    }

    public String getLanguageVersion() {
        String str = KotlinCompilerVersion.VERSION;
        str.getClass();
        return str;
    }

    public String getMethodCallSyntax(String obj, String m, String... args) {
        obj.getClass();
        m.getClass();
        args.getClass();
        return obj + AbiCompoundName.SEPARATOR + m + '(' + ArraysKt.joinToString$default(args, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 63, (Object) null) + ')';
    }

    public List<String> getMimeTypes() {
        return CollectionsKt.listOf("text/x-kotlin");
    }

    public List<String> getNames() {
        return CollectionsKt.listOf("kotlin");
    }

    public String getOutputStatement(String toDisplay) {
        return "print(\"" + toDisplay + "\")";
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public Object getParameter(String key) {
        if (key == null) {
            return null;
        }
        switch (key.hashCode()) {
            case -1073020410:
                if (key.equals("javax.script.engine_version")) {
                    return getEngineVersion();
                }
                return null;
            case -1047659667:
                if (key.equals("javax.script.engine")) {
                    return getEngineName();
                }
                return null;
            case -917703229:
                if (key.equals("javax.script.language")) {
                    return getLanguageName();
                }
                return null;
            case -852670884:
                if (key.equals("javax.script.language_version")) {
                    return getLanguageVersion();
                }
                return null;
            case -125973898:
                if (key.equals("javax.script.name")) {
                    return getEngineName();
                }
                return null;
            default:
                return null;
        }
    }

    public String getProgram(String... statements) {
        statements.getClass();
        String property = System.getProperty("line.separator");
        StringBuilder sb = new StringBuilder();
        property.getClass();
        sb.append(ArraysKt.joinToString$default(statements, property, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
        sb.append(property);
        return sb.toString();
    }
}
