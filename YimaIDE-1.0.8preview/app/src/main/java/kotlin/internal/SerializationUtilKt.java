package kotlin.internal;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import java.io.InvalidObjectException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u0001H\u0081\u0088\u0004b\u0002\b\u0002\u001a\u001f\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006H\u0081\u0088\u0004b\u0002\b\u0002ø\u0001\u0000*\f\b\u0000\u0010\u0007\"\u00020\b2\u00020\b\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\t"}, d2 = {"throwReadObjectNotSupported", "", "Lkotlin/internal/InlineOnly;", "wrapAsDeserializationException", "", "action", "Lkotlin/Function0;", "ReadObjectParameterType", "Ljava/io/ObjectInputStream;", "kotlin-stdlib"}, k = 2, mv = {2, 4, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class SerializationUtilKt {
    private static final Void throwReadObjectNotSupported() throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization is supported via proxy only");
    }

    private static final void wrapAsDeserializationException(Function0<Unit> function0) throws Throwable {
        function0.getClass();
        try {
            function0.invoke();
        } catch (Throwable th) {
            Throwable thInitCause = new InvalidObjectException(th.getMessage()).initCause(th);
            thInitCause.getClass();
            throw thInitCause;
        }
    }
}
