package com.sun.tools.javac.util;

import com.sun.tools.javac.api.DiagnosticFormatter;
import com.sun.tools.javac.code.Lint;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.tree.EndPosTable;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.Options;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class JCDiagnostic implements Diagnostic<JavaFileObject> {

    @Deprecated
    private static DiagnosticFormatter<JCDiagnostic> fragmentFormatter;
    private DiagnosticFormatter<JCDiagnostic> defaultFormatter;
    private final DiagnosticInfo diagnosticInfo;
    private final Set<DiagnosticFlag> flags;
    private final DiagnosticPosition position;
    private final UnaryOperator<JCDiagnostic> rewriter;
    private final DiagnosticSource source;
    private SourcePosition sourcePosition;

    public static final class AnnotatedType {
        private final Type type;

        public AnnotatedType(Type type) {
            this.type = type;
        }

        public final boolean equals(Object obj) {
            return (obj instanceof AnnotatedType) && Objects.equals(this.type, ((AnnotatedType) obj).type);
        }

        public final int hashCode() {
            return Objects.hashCode(this.type);
        }

        public final String toString() {
            return "AnnotatedType[type=" + Objects.toString(this.type) + "]";
        }

        public Type type() {
            return this.type;
        }
    }

    public enum DiagnosticFlag {
        MANDATORY,
        RESOLVE_ERROR,
        SYNTAX,
        RECOVERABLE,
        NON_DEFERRABLE,
        COMPRESSED,
        DEFAULT_ENABLED,
        AGGREGATE,
        API,
        SOURCE_LEVEL,
        STRICT
    }

    public interface DiagnosticPosition {
        int getEndPosition(EndPosTable endPosTable);

        default int getLintPosition() {
            return getStartPosition();
        }

        int getPreferredPosition();

        int getStartPosition();

        JCTree getTree();

        default DiagnosticPosition withLintPosition(final int i) {
            return new DiagnosticPosition(this) { // from class: com.sun.tools.javac.util.JCDiagnostic.DiagnosticPosition.1
                final /* synthetic */ DiagnosticPosition this$0;

                {
                    this.this$0 = this;
                }

                @Override // com.sun.tools.javac.util.JCDiagnostic.DiagnosticPosition
                public int getEndPosition(EndPosTable endPosTable) {
                    return this.getEndPosition(endPosTable);
                }

                @Override // com.sun.tools.javac.util.JCDiagnostic.DiagnosticPosition
                public int getLintPosition() {
                    return i;
                }

                @Override // com.sun.tools.javac.util.JCDiagnostic.DiagnosticPosition
                public int getPreferredPosition() {
                    return this.getPreferredPosition();
                }

                @Override // com.sun.tools.javac.util.JCDiagnostic.DiagnosticPosition
                public int getStartPosition() {
                    return this.getStartPosition();
                }

                @Override // com.sun.tools.javac.util.JCDiagnostic.DiagnosticPosition
                public JCTree getTree() {
                    return this.getTree();
                }
            };
        }
    }

    public enum DiagnosticType {
        FRAGMENT("misc"),
        NOTE("note"),
        WARNING("warn"),
        ERROR("err");

        final String key;

        DiagnosticType(String str) {
            this.key = str;
        }
    }

    public static final class Error extends DiagnosticInfo {
        public Error(Set<DiagnosticFlag> set, String str, String str2, Object... objArr) {
            super(DiagnosticType.ERROR, set, str, str2, objArr);
        }
    }

    public static final class Fragment extends DiagnosticInfo {
        public Fragment(Set<DiagnosticFlag> set, String str, String str2, Object... objArr) {
            super(DiagnosticType.FRAGMENT, set, str, str2, objArr);
        }
    }

    public static final class LintWarning extends Warning {
        final Lint.LintCategory category;

        public LintWarning(Set<DiagnosticFlag> set, Lint.LintCategory lintCategory, String str, String str2, Object... objArr) {
            super(set, str, str2, objArr);
            this.category = lintCategory;
        }

        public Lint.LintCategory getLintCategory() {
            return this.category;
        }
    }

    public static class MultilineDiagnostic extends JCDiagnostic {
        private final List<JCDiagnostic> subdiagnostics;

        public MultilineDiagnostic(JCDiagnostic jCDiagnostic, List<JCDiagnostic> list) {
            super(jCDiagnostic.defaultFormatter, jCDiagnostic.diagnosticInfo, jCDiagnostic.flags, jCDiagnostic.getDiagnosticSource(), jCDiagnostic.position);
            this.subdiagnostics = list;
        }

        @Override // com.sun.tools.javac.util.JCDiagnostic, javax.tools.Diagnostic
        public /* bridge */ /* synthetic */ JavaFileObject getSource() {
            return super.getSource();
        }

        @Override // com.sun.tools.javac.util.JCDiagnostic
        public List<JCDiagnostic> getSubdiagnostics() {
            return this.subdiagnostics;
        }

        @Override // com.sun.tools.javac.util.JCDiagnostic
        public boolean isMultiline() {
            return true;
        }
    }

    public static final class Note extends DiagnosticInfo {
        public Note(Set<DiagnosticFlag> set, String str, String str2, Object... objArr) {
            super(DiagnosticType.NOTE, set, str, str2, objArr);
        }
    }

    public static class SimpleDiagnosticPosition implements DiagnosticPosition {
        private final int pos;

        public SimpleDiagnosticPosition(int i) {
            this.pos = i;
        }

        @Override // com.sun.tools.javac.util.JCDiagnostic.DiagnosticPosition
        public int getEndPosition(EndPosTable endPosTable) {
            return this.pos;
        }

        @Override // com.sun.tools.javac.util.JCDiagnostic.DiagnosticPosition
        public int getPreferredPosition() {
            return this.pos;
        }

        @Override // com.sun.tools.javac.util.JCDiagnostic.DiagnosticPosition
        public int getStartPosition() {
            return this.pos;
        }

        @Override // com.sun.tools.javac.util.JCDiagnostic.DiagnosticPosition
        public JCTree getTree() {
            return null;
        }
    }

    public class SourcePosition {
        private final int column;
        private final int line;

        public SourcePosition() {
            int preferredPosition = JCDiagnostic.this.position == null ? -1 : JCDiagnostic.this.position.getPreferredPosition();
            if (preferredPosition == -1 || JCDiagnostic.this.source == null) {
                this.column = -1;
                this.line = -1;
            } else {
                this.line = JCDiagnostic.this.source.getLineNumber(preferredPosition);
                this.column = JCDiagnostic.this.source.getColumnNumber(preferredPosition, true);
            }
        }

        public int getColumnNumber() {
            return this.column;
        }

        public int getLineNumber() {
            return this.line;
        }
    }

    public static class Warning extends DiagnosticInfo {
        public Warning(Set<DiagnosticFlag> set, String str, String str2, Object... objArr) {
            super(DiagnosticType.WARNING, set, str, str2, objArr);
        }
    }

    public JCDiagnostic(DiagnosticFormatter<JCDiagnostic> diagnosticFormatter, DiagnosticInfo diagnosticInfo, Set<DiagnosticFlag> set, DiagnosticSource diagnosticSource, DiagnosticPosition diagnosticPosition, UnaryOperator<JCDiagnostic> unaryOperator) {
        if (diagnosticSource == null && diagnosticPosition != null && diagnosticPosition.getPreferredPosition() != -1) {
            j2d.a();
            throw null;
        }
        this.defaultFormatter = diagnosticFormatter;
        this.diagnosticInfo = diagnosticInfo;
        this.flags = set;
        this.source = diagnosticSource;
        this.position = diagnosticPosition;
        this.rewriter = unaryOperator;
        set.addAll(diagnosticInfo.flags);
    }

    @Deprecated
    public static JCDiagnostic fragment(String str, Object... objArr) {
        return new JCDiagnostic(getFragmentFormatter(), DiagnosticInfo.of(DiagnosticType.FRAGMENT, null, null, "compiler", str, objArr), EnumSet.noneOf(DiagnosticFlag.class), null, null);
    }

    @Deprecated
    public static DiagnosticFormatter<JCDiagnostic> getFragmentFormatter() {
        if (fragmentFormatter == null) {
            fragmentFormatter = new BasicDiagnosticFormatter(JavacMessages.getDefaultMessages());
        }
        return fragmentFormatter;
    }

    public Object[] getArgs() {
        return this.diagnosticInfo.args;
    }

    @Override // javax.tools.Diagnostic
    public String getCode() {
        return this.diagnosticInfo.key();
    }

    @Override // javax.tools.Diagnostic
    public long getColumnNumber() {
        if (this.sourcePosition == null) {
            this.sourcePosition = new SourcePosition();
        }
        return this.sourcePosition.getColumnNumber();
    }

    public DiagnosticPosition getDiagnosticPosition() {
        return this.position;
    }

    public DiagnosticSource getDiagnosticSource() {
        return this.source;
    }

    @Override // javax.tools.Diagnostic
    public long getEndPosition() {
        return getIntEndPosition();
    }

    public int getIntEndPosition() {
        DiagnosticPosition diagnosticPosition = this.position;
        if (diagnosticPosition == null) {
            return -1;
        }
        return diagnosticPosition.getEndPosition(this.source.getEndPosTable());
    }

    public int getIntPosition() {
        DiagnosticPosition diagnosticPosition = this.position;
        if (diagnosticPosition == null) {
            return -1;
        }
        return diagnosticPosition.getPreferredPosition();
    }

    public int getIntStartPosition() {
        DiagnosticPosition diagnosticPosition = this.position;
        if (diagnosticPosition == null) {
            return -1;
        }
        return diagnosticPosition.getStartPosition();
    }

    @Override // javax.tools.Diagnostic
    public Diagnostic.Kind getKind() {
        int iOrdinal = this.diagnosticInfo.type.ordinal();
        if (iOrdinal == 1) {
            return Diagnostic.Kind.NOTE;
        }
        if (iOrdinal != 2) {
            return iOrdinal != 3 ? Diagnostic.Kind.OTHER : Diagnostic.Kind.ERROR;
        }
        return this.flags.contains(DiagnosticFlag.MANDATORY) ? Diagnostic.Kind.MANDATORY_WARNING : Diagnostic.Kind.WARNING;
    }

    @Override // javax.tools.Diagnostic
    public long getLineNumber() {
        if (this.sourcePosition == null) {
            this.sourcePosition = new SourcePosition();
        }
        return this.sourcePosition.getLineNumber();
    }

    public Lint.LintCategory getLintCategory() {
        DiagnosticInfo diagnosticInfo = this.diagnosticInfo;
        if (diagnosticInfo instanceof LintWarning) {
            return ((LintWarning) diagnosticInfo).category;
        }
        return null;
    }

    @Override // javax.tools.Diagnostic
    public String getMessage(Locale locale) {
        return this.defaultFormatter.formatMessage(this, locale);
    }

    @Override // javax.tools.Diagnostic
    public long getPosition() {
        return getIntPosition();
    }

    public String getPrefix(DiagnosticType diagnosticType) {
        return this.defaultFormatter.formatKind(this, Locale.getDefault());
    }

    @Override // javax.tools.Diagnostic
    public JavaFileObject getSource() {
        DiagnosticSource diagnosticSource = this.source;
        if (diagnosticSource == null) {
            return null;
        }
        return diagnosticSource.getFile();
    }

    @Override // javax.tools.Diagnostic
    public long getStartPosition() {
        return getIntStartPosition();
    }

    public List<JCDiagnostic> getSubdiagnostics() {
        return List.nil();
    }

    public DiagnosticType getType() {
        return this.diagnosticInfo.type;
    }

    public boolean hasLintCategory() {
        return getLintCategory() != null;
    }

    public boolean hasRewriter() {
        return this.rewriter != null;
    }

    public boolean isFlagSet(DiagnosticFlag diagnosticFlag) {
        return this.flags.contains(diagnosticFlag);
    }

    public boolean isMandatory() {
        return this.flags.contains(DiagnosticFlag.MANDATORY);
    }

    public boolean isMultiline() {
        return false;
    }

    public JCDiagnostic rewrite() {
        return (JCDiagnostic) this.rewriter.apply(this);
    }

    public void setFlag(DiagnosticFlag diagnosticFlag) {
        this.flags.add(diagnosticFlag);
        if (this.diagnosticInfo.type == DiagnosticType.ERROR) {
            int iOrdinal = diagnosticFlag.ordinal();
            if (iOrdinal == 1) {
                this.flags.add(DiagnosticFlag.RECOVERABLE);
            } else {
                if (iOrdinal != 2) {
                    return;
                }
                this.flags.remove(DiagnosticFlag.RECOVERABLE);
            }
        }
    }

    public String toString() {
        return this.defaultFormatter.format(this, Locale.getDefault());
    }

    public static class Factory {
        protected static final Context.Key<Factory> diagnosticFactoryKey = new Context.Key<>();
        final Set<DiagnosticFlag> defaultErrorFlags;
        DiagnosticFormatter<JCDiagnostic> formatter;
        final String prefix;

        public Factory(Context context) {
            this(JavacMessages.instance(context), "compiler");
            context.put(diagnosticFactoryKey, this);
            Options.instance(context).whenReady(new Consumer() { // from class: da7
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.initOptions((Options) obj);
                }
            });
        }

        public static /* synthetic */ Object b(Factory factory, Object obj) {
            if (obj instanceof Fragment) {
                return factory.fragment((Fragment) obj);
            }
            factory.getClass();
            return obj;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void initOptions(Options options) {
            if (options.isSet("onlySyntaxErrorsUnrecoverable")) {
                this.defaultErrorFlags.add(DiagnosticFlag.RECOVERABLE);
            }
        }

        public static Factory instance(Context context) {
            Factory factory = (Factory) context.get(diagnosticFactoryKey);
            return factory == null ? new Factory(context) : factory;
        }

        public JCDiagnostic create(DiagnosticType diagnosticType, DiagnosticSource diagnosticSource, DiagnosticPosition diagnosticPosition, String str, UnaryOperator<JCDiagnostic> unaryOperator, Object... objArr) {
            return create(EnumSet.noneOf(DiagnosticFlag.class), diagnosticSource, diagnosticPosition, DiagnosticInfo.of(diagnosticType, null, this.prefix, str, objArr), unaryOperator);
        }

        public JCDiagnostic error(DiagnosticFlag diagnosticFlag, DiagnosticSource diagnosticSource, DiagnosticPosition diagnosticPosition, Error error) {
            JCDiagnostic jCDiagnosticCreate = create(EnumSet.copyOf((Collection) this.defaultErrorFlags), diagnosticSource, diagnosticPosition, error);
            if (diagnosticFlag != null) {
                jCDiagnosticCreate.setFlag(diagnosticFlag);
            }
            return jCDiagnosticCreate;
        }

        public Error errorKey(String str, Object... objArr) {
            return (Error) DiagnosticInfo.of(DiagnosticType.ERROR, null, this.prefix, str, objArr);
        }

        public JCDiagnostic fragment(Fragment fragment) {
            return create(EnumSet.noneOf(DiagnosticFlag.class), null, null, fragment);
        }

        public Fragment fragmentKey(String str, Object... objArr) {
            return (Fragment) DiagnosticInfo.of(DiagnosticType.FRAGMENT, null, this.prefix, str, objArr);
        }

        public DiagnosticInfo normalize(DiagnosticInfo diagnosticInfo) {
            return DiagnosticInfo.of(diagnosticInfo.type, diagnosticInfo.flags, diagnosticInfo instanceof LintWarning ? ((LintWarning) diagnosticInfo).category : null, diagnosticInfo.prefix, diagnosticInfo.code, Stream.of(diagnosticInfo.args).map(new Function() { // from class: ea7
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return JCDiagnostic.Factory.b(this.b, obj);
                }
            }).toArray());
        }

        public JCDiagnostic note(DiagnosticFlag diagnosticFlag, DiagnosticSource diagnosticSource, DiagnosticPosition diagnosticPosition, Note note) {
            return create(diagnosticFlag != null ? EnumSet.of(diagnosticFlag) : EnumSet.noneOf(DiagnosticFlag.class), diagnosticSource, diagnosticPosition, note);
        }

        public Note noteKey(String str, Object... objArr) {
            return (Note) DiagnosticInfo.of(DiagnosticType.NOTE, null, this.prefix, str, objArr);
        }

        public JCDiagnostic warning(DiagnosticFlag diagnosticFlag, DiagnosticSource diagnosticSource, DiagnosticPosition diagnosticPosition, Warning warning) {
            return create(diagnosticFlag != null ? EnumSet.of(diagnosticFlag) : EnumSet.noneOf(DiagnosticFlag.class), diagnosticSource, diagnosticPosition, warning);
        }

        public Warning warningKey(Lint.LintCategory lintCategory, String str, Object... objArr) {
            return (Warning) DiagnosticInfo.of(DiagnosticType.WARNING, null, lintCategory, this.prefix, str, objArr);
        }

        public JCDiagnostic fragment(String str, Object... objArr) {
            return fragment(fragmentKey(str, objArr));
        }

        public JCDiagnostic error(DiagnosticFlag diagnosticFlag, DiagnosticSource diagnosticSource, DiagnosticPosition diagnosticPosition, String str, Object... objArr) {
            return error(diagnosticFlag, diagnosticSource, diagnosticPosition, errorKey(str, objArr));
        }

        public JCDiagnostic note(DiagnosticSource diagnosticSource, DiagnosticPosition diagnosticPosition, String str, Object... objArr) {
            return note((DiagnosticFlag) null, diagnosticSource, diagnosticPosition, noteKey(str, objArr));
        }

        public JCDiagnostic warning(Lint.LintCategory lintCategory, DiagnosticSource diagnosticSource, DiagnosticPosition diagnosticPosition, String str, Object... objArr) {
            return warning(null, diagnosticSource, diagnosticPosition, warningKey(lintCategory, str, objArr));
        }

        public JCDiagnostic create(DiagnosticType diagnosticType, DiagnosticSource diagnosticSource, DiagnosticPosition diagnosticPosition, String str, Object... objArr) {
            return create(EnumSet.noneOf(DiagnosticFlag.class), diagnosticSource, diagnosticPosition, DiagnosticInfo.of(diagnosticType, null, this.prefix, str, objArr));
        }

        public JCDiagnostic create(DiagnosticSource diagnosticSource, DiagnosticPosition diagnosticPosition, DiagnosticInfo diagnosticInfo) {
            return create(EnumSet.noneOf(DiagnosticFlag.class), diagnosticSource, diagnosticPosition, diagnosticInfo);
        }

        public JCDiagnostic create(DiagnosticType diagnosticType, Lint.LintCategory lintCategory, Set<DiagnosticFlag> set, DiagnosticSource diagnosticSource, DiagnosticPosition diagnosticPosition, String str, Object... objArr) {
            return create(set, diagnosticSource, diagnosticPosition, DiagnosticInfo.of(diagnosticType, null, lintCategory, this.prefix, str, objArr));
        }

        public JCDiagnostic create(Set<DiagnosticFlag> set, DiagnosticSource diagnosticSource, DiagnosticPosition diagnosticPosition, DiagnosticInfo diagnosticInfo) {
            return new JCDiagnostic(this.formatter, normalize(diagnosticInfo), set, diagnosticSource, diagnosticPosition);
        }

        public JCDiagnostic create(Set<DiagnosticFlag> set, DiagnosticSource diagnosticSource, DiagnosticPosition diagnosticPosition, DiagnosticInfo diagnosticInfo, UnaryOperator<JCDiagnostic> unaryOperator) {
            return new JCDiagnostic(this.formatter, normalize(diagnosticInfo), set, diagnosticSource, diagnosticPosition, unaryOperator);
        }

        public Factory(JavacMessages javacMessages, String str) {
            this.prefix = str;
            this.formatter = new BasicDiagnosticFormatter(javacMessages);
            this.defaultErrorFlags = EnumSet.of(DiagnosticFlag.MANDATORY);
        }
    }

    public String getPrefix() {
        return getPrefix(this.diagnosticInfo.type);
    }

    public JCDiagnostic(DiagnosticFormatter<JCDiagnostic> diagnosticFormatter, DiagnosticInfo diagnosticInfo, Set<DiagnosticFlag> set, DiagnosticSource diagnosticSource, DiagnosticPosition diagnosticPosition) {
        this(diagnosticFormatter, diagnosticInfo, set, diagnosticSource, diagnosticPosition, null);
    }

    public static abstract class DiagnosticInfo {
        Object[] args;
        String code;
        Set<DiagnosticFlag> flags;
        String prefix;
        DiagnosticType type;

        private DiagnosticInfo(DiagnosticType diagnosticType, Set<DiagnosticFlag> set, String str, String str2, Object... objArr) {
            this.type = diagnosticType;
            this.flags = set == null ? EnumSet.noneOf(DiagnosticFlag.class) : set;
            this.prefix = str;
            this.code = str2;
            this.args = objArr;
        }

        public static DiagnosticInfo of(DiagnosticType diagnosticType, Set<DiagnosticFlag> set, Lint.LintCategory lintCategory, String str, String str2, Object... objArr) {
            int iOrdinal = diagnosticType.ordinal();
            if (iOrdinal == 0) {
                return new Fragment(set, str, str2, objArr);
            }
            if (iOrdinal == 1) {
                return new Note(set, str, str2, objArr);
            }
            if (iOrdinal == 2) {
                return lintCategory == null ? new Warning(set, str, str2, objArr) : new LintWarning(set, lintCategory, str, str2, objArr);
            }
            if (iOrdinal == 3) {
                return new Error(set, str, str2, objArr);
            }
            Assert.error("Wrong diagnostic type: " + diagnosticType);
            return null;
        }

        public Object[] getArgs() {
            return this.args;
        }

        public String getCode() {
            return this.code;
        }

        public boolean hasFlag(DiagnosticFlag diagnosticFlag) {
            return this.flags.contains(diagnosticFlag);
        }

        public String key() {
            return this.prefix + com.sun.org.apache.xalan.internal.templates.Constants.ATTRVAL_THIS + this.type.key + com.sun.org.apache.xalan.internal.templates.Constants.ATTRVAL_THIS + this.code;
        }

        public void setArgs(Object[] objArr) {
            this.args = objArr;
        }

        public static DiagnosticInfo of(DiagnosticType diagnosticType, Set<DiagnosticFlag> set, String str, String str2, Object... objArr) {
            return of(diagnosticType, set, null, str, str2, objArr);
        }
    }
}
