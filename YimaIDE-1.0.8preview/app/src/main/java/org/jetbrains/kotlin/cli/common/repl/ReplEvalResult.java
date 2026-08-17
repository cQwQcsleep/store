package org.jetbrains.kotlin.cli.common.repl;

import java.io.Serializable;
import kotlin.Function;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageLocation;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \t2\u00020\u0001:\u0006\u0004\u0005\u0006\u0007\b\tB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0005\n\u000b\f\r\u000e¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/repl/ReplEvalResult;", "Ljava/io/Serializable;", "<init>", "()V", "ValueResult", "UnitResult", "Incomplete", "HistoryMismatch", "Error", "Companion", "Lorg/jetbrains/kotlin/cli/common/repl/ReplEvalResult$Error;", "Lorg/jetbrains/kotlin/cli/common/repl/ReplEvalResult$HistoryMismatch;", "Lorg/jetbrains/kotlin/cli/common/repl/ReplEvalResult$Incomplete;", "Lorg/jetbrains/kotlin/cli/common/repl/ReplEvalResult$UnitResult;", "Lorg/jetbrains/kotlin/cli/common/repl/ReplEvalResult$ValueResult;", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class ReplEvalResult implements Serializable {
    private static final long serialVersionUID = 8228307678L;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/repl/ReplEvalResult$HistoryMismatch;", "Lorg/jetbrains/kotlin/cli/common/repl/ReplEvalResult;", "lineNo", Argument.Delimiters.none, "<init>", "(I)V", "getLineNo", "()I", "Companion", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class HistoryMismatch extends ReplEvalResult {
        private static final long serialVersionUID = 1;
        private final int lineNo;

        public HistoryMismatch(int i) {
            super(null);
            this.lineNo = i;
        }

        public final int getLineNo() {
            return this.lineNo;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/repl/ReplEvalResult$Incomplete;", "Lorg/jetbrains/kotlin/cli/common/repl/ReplEvalResult;", "message", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "Companion", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Incomplete extends ReplEvalResult {
        private static final long serialVersionUID = 1;
        private final String message;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Incomplete(String str) {
            super(null);
            str.getClass();
            this.message = str;
        }

        public final String getMessage() {
            return this.message;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/repl/ReplEvalResult$UnitResult;", "Lorg/jetbrains/kotlin/cli/common/repl/ReplEvalResult;", "<init>", "()V", "Companion", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class UnitResult extends ReplEvalResult {
        private static final long serialVersionUID = 1;

        public UnitResult() {
            super(null);
        }
    }

    public /* synthetic */ ReplEvalResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private ReplEvalResult() {
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u000b2\u00020\u0001:\u0003\t\n\u000bB\u0011\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\b\u001a\u00020\u0003H\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\u0002\f\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/repl/ReplEvalResult$Error;", "Lorg/jetbrains/kotlin/cli/common/repl/ReplEvalResult;", "message", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "toString", "Runtime", "CompileTime", "Companion", "Lorg/jetbrains/kotlin/cli/common/repl/ReplEvalResult$Error$CompileTime;", "Lorg/jetbrains/kotlin/cli/common/repl/ReplEvalResult$Error$Runtime;", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static abstract class Error extends ReplEvalResult {
        private static final long serialVersionUID = 1;
        private final String message;

        private Error(String str) {
            super(null);
            this.message = str;
        }

        public final String getMessage() {
            return this.message;
        }

        public String toString() {
            return Reflection.getOrCreateKotlinClass(getClass()).getSimpleName() + "Error(message = \"" + this.message + '\"';
        }

        public /* synthetic */ Error(String str, DefaultConstructorMarker defaultConstructorMarker) {
            this(str);
        }

        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/repl/ReplEvalResult$Error$CompileTime;", "Lorg/jetbrains/kotlin/cli/common/repl/ReplEvalResult$Error;", "message", Argument.Delimiters.none, "location", "Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageLocation;", "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageLocation;)V", "getLocation", "()Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageLocation;", "Companion", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class CompileTime extends Error {
            private static final long serialVersionUID = 1;
            private final CompilerMessageLocation location;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public CompileTime(String str, CompilerMessageLocation compilerMessageLocation) {
                super(str, null);
                str.getClass();
                this.location = compilerMessageLocation;
            }

            public final CompilerMessageLocation getLocation() {
                return this.location;
            }

            public /* synthetic */ CompileTime(String str, CompilerMessageLocation compilerMessageLocation, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this(str, (i & 2) != 0 ? null : compilerMessageLocation);
            }
        }

        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0006\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/repl/ReplEvalResult$Error$Runtime;", "Lorg/jetbrains/kotlin/cli/common/repl/ReplEvalResult$Error;", "message", Argument.Delimiters.none, "cause", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "getCause", "()Ljava/lang/Throwable;", "Companion", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class Runtime extends Error {
            private static final long serialVersionUID = 1;
            private final Throwable cause;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Runtime(String str, Throwable th) {
                super(str, null);
                str.getClass();
                this.cause = th;
            }

            public final Throwable getCause() {
                return this.cause;
            }

            public /* synthetic */ Runtime(String str, Throwable th, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this(str, (i & 2) != 0 ? null : th);
            }
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\r\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\b\u0010\tJ\n\u0010\u0010\u001a\u00020\u0003H\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/repl/ReplEvalResult$ValueResult;", "Lorg/jetbrains/kotlin/cli/common/repl/ReplEvalResult;", ModuleXmlParser.NAME, Argument.Delimiters.none, "value", Argument.Delimiters.none, ModuleXmlParser.TYPE, "snippetInstance", "<init>", "(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)V", "getName", "()Ljava/lang/String;", "getValue", "()Ljava/lang/Object;", "getType", "getSnippetInstance", "toString", "Companion", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ValueResult extends ReplEvalResult {
        private static final long serialVersionUID = 1;
        private final String name;
        private final Object snippetInstance;
        private final String type;
        private final Object value;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ValueResult(String str, Object obj, String str2, Object obj2) {
            super(null);
            str.getClass();
            this.name = str;
            this.value = obj;
            this.type = str2;
            this.snippetInstance = obj2;
        }

        public final String getName() {
            return this.name;
        }

        public final Object getSnippetInstance() {
            return this.snippetInstance;
        }

        public final String getType() {
            return this.type;
        }

        public final Object getValue() {
            return this.value;
        }

        public String toString() {
            Object obj = this.value;
            if (obj instanceof Function) {
                obj = "<function" + TypeIntrinsics.getFunctionArity(this.value) + '>';
            }
            return this.name + ": " + this.type + " = " + obj;
        }

        public /* synthetic */ ValueResult(String str, Object obj, String str2, Object obj2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, obj, str2, (i & 8) != 0 ? null : obj2);
        }
    }
}
