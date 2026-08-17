package javax.tools;

import java.util.Locale;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Diagnostic<S> {
    public static final long NOPOS = -1;

    public enum Kind {
        ERROR,
        WARNING,
        MANDATORY_WARNING,
        NOTE,
        OTHER
    }

    String getCode();

    long getColumnNumber();

    long getEndPosition();

    Kind getKind();

    long getLineNumber();

    String getMessage(Locale locale);

    long getPosition();

    S getSource();

    long getStartPosition();
}
