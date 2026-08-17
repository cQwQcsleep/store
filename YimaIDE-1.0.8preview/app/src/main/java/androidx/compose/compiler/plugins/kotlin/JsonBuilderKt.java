package androidx.compose.compiler.plugins.kotlin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.text.Charsets;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a'\u0010\u0000\u001a\u00020\u0001*\u00060\u0002j\u0002`\u00032\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0007\u001a#\u0010\b\u001a\u00020\u0001*\u00020\t2\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0007\u001a'\u0010\u000b\u001a\u00020\u0001*\u00060\u0002j\u0002`\u00032\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0007¨\u0006\r"}, d2 = {"appendJson", "", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "fn", "Lkotlin/Function1;", "Landroidx/compose/compiler/plugins/kotlin/JsonBuilder;", "Lkotlin/ExtensionFunctionType;", "write", "Ljava/io/File;", "Ljava/io/OutputStreamWriter;", "appendCsv", "Landroidx/compose/compiler/plugins/kotlin/CsvBuilder;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class JsonBuilderKt {
    public static final void appendCsv(Appendable appendable, Function1<? super CsvBuilder, Unit> function1) {
        appendable.getClass();
        function1.getClass();
        function1.invoke(new CsvBuilder(appendable));
    }

    public static final void appendJson(Appendable appendable, Function1<? super JsonBuilder, Unit> function1) throws IOException {
        appendable.getClass();
        function1.getClass();
        new JsonBuilder(appendable, 1).with(function1);
    }

    public static final void write(File file, Function1<? super OutputStreamWriter, Unit> function1) throws IOException {
        file.getClass();
        function1.getClass();
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), Charsets.UTF_8);
        try {
            function1.invoke(outputStreamWriter);
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(outputStreamWriter, (Throwable) null);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(outputStreamWriter, th);
                throw th2;
            }
        }
    }
}
