package androidx.compose.compiler.plugins.kotlin.lower;

import androidx.compose.compiler.plugins.kotlin.lower.IrSourcePrinterKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.ranges.IntRange;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import org.jetbrains.kotlin.ir.IrElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u001a`\u0010\u0005\u001a\u00020\u0006\"\u0004\b\u0000\u0010\u0007*\u00060\bj\u0002`\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00070\u000b2\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u00012!\u0010\u000f\u001a\u001d\u0012\b\u0012\u00060\bj\u0002`\t\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u00020\u00060\u0010¢\u0006\u0002\b\u0011H\u0082\b¨\u0006\u0012"}, d2 = {"dumpSrc", "", "Lorg/jetbrains/kotlin/ir/IrElement;", "useFir", "", "appendListWith", "", "T", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "list", "", "prefix", "postfix", "separator", "renderItem", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class IrSourcePrinterKt {
    private static final <T> void appendListWith(StringBuilder sb, List<? extends T> list, String str, String str2, String str3, Function2<? super StringBuilder, ? super T, Unit> function2) {
        sb.append(str);
        boolean z = true;
        for (T t : list) {
            if (!z) {
                sb.append(str3);
            }
            function2.invoke(sb, t);
            z = false;
        }
        sb.append(str2);
    }

    public static CharSequence b(MatchResult matchResult) {
        matchResult.getClass();
        return "\n" + CollectionsKt.joinToString$default(new IntRange(0, ((matchResult.getRange().getLast() - matchResult.getRange().getFirst()) - 1) / 5), "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: a37
            public final Object invoke(Object obj) {
                return IrSourcePrinterKt.dumpSrc$lambda$0$0(((Integer) obj).intValue());
            }
        }, 30, (Object) null);
    }

    public static final String dumpSrc(IrElement irElement, boolean z) {
        irElement.getClass();
        StringBuilder sb = new StringBuilder();
        irElement.accept(new IrSourcePrinterVisitor(sb, "%tab%", z), (Object) null);
        String string = sb.toString();
        RegexOption regexOption = RegexOption.MULTILINE;
        return new Regex("}\\n(\\s)*,", regexOption).replace(new Regex("\\n(\\s)*$", regexOption).replace(new Regex("%tab%", regexOption).replace(new Regex("\\n(%tab%)+", regexOption).replace(string, new Function1() { // from class: b37
            public final Object invoke(Object obj) {
                return IrSourcePrinterKt.b((MatchResult) obj);
            }
        }), ""), ""), "},");
    }

    public static /* synthetic */ String dumpSrc$default(IrElement irElement, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return dumpSrc(irElement, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence dumpSrc$lambda$0$0(int i) {
        return "  ";
    }
}
