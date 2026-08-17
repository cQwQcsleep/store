package org.jetbrains.kotlin.fir.renderer;

import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.utils.Printer;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B\u0013\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u001f\u0010\u000b\u001a\u00020\f2\u0012\u0010\r\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u000e\"\u00020\u0001¢\u0006\u0002\u0010\u000fJ\u001f\u0010\u0010\u001a\u00020\f2\u0012\u0010\r\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u000e\"\u00020\u0001¢\u0006\u0002\u0010\u000fJ\r\u0010\u0011\u001a\u00020\fH\u0000¢\u0006\u0002\b\u0012J\r\u0010\u0013\u001a\u00020\fH\u0000¢\u0006\u0002\b\u0014J\u0006\u0010\u0015\u001a\u00020\fJ(\u0010\u0016\u001a\u00020\f2\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u00182\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\f0\u001bJ'\u0010\u001c\u001a\u00020\f2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e2\n\u0010 \u001a\u00060!R\u00020\"H\u0000¢\u0006\u0002\b#J'\u0010$\u001a\u00020\f2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e2\n\u0010 \u001a\u00060!R\u00020\"H\u0000¢\u0006\u0002\b%J\n\u0010&\u001a\u00020\u0018H\u0096\u0080\u0004R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lorg/jetbrains/kotlin/fir/renderer/FirPrinter;", Argument.Delimiters.none, "builder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "<init>", "(Ljava/lang/StringBuilder;)V", "printer", "Lorg/jetbrains/kotlin/utils/Printer;", "lineBeginning", Argument.Delimiters.none, "print", Argument.Delimiters.none, "objects", Argument.Delimiters.none, "([Ljava/lang/Object;)V", "println", "pushIndent", "pushIndent$org_jetbrains_kotlin_tree", "popIndent", "popIndent$org_jetbrains_kotlin_tree", "newLine", "renderInBraces", "leftBrace", Argument.Delimiters.none, "rightBrace", "f", "Lkotlin/Function0;", "renderSeparated", "elements", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/FirElement;", "visitor", "Lorg/jetbrains/kotlin/fir/renderer/FirRenderer$Visitor;", "Lorg/jetbrains/kotlin/fir/renderer/FirRenderer;", "renderSeparated$org_jetbrains_kotlin_tree", "renderSeparatedWithNewlines", "renderSeparatedWithNewlines$org_jetbrains_kotlin_tree", "toString", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class FirPrinter {
    private boolean lineBeginning;
    private final Printer printer;

    public FirPrinter(StringBuilder sb) {
        sb.getClass();
        this.printer = new Printer(sb, 0, (String) null, 6, (DefaultConstructorMarker) null);
        this.lineBeginning = true;
    }

    public static /* synthetic */ void renderInBraces$default(FirPrinter firPrinter, String str, String str2, Function0 function0, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: renderInBraces");
            return;
        }
        if ((i & 1) != 0) {
            str = "{";
        }
        if ((i & 2) != 0) {
            str2 = "}";
        }
        firPrinter.renderInBraces(str, str2, function0);
    }

    public final void newLine() {
        println(new Object[0]);
    }

    public final void popIndent$org_jetbrains_kotlin_tree() {
        this.printer.popIndent();
    }

    public final void print(Object... objects) {
        objects.getClass();
        if (!this.lineBeginning) {
            this.printer.printWithNoIndent(Arrays.copyOf(objects, objects.length));
        } else {
            this.lineBeginning = false;
            this.printer.print(Arrays.copyOf(objects, objects.length));
        }
    }

    public final void println(Object... objects) {
        objects.getClass();
        print(Arrays.copyOf(objects, objects.length));
        this.printer.printlnWithNoIndent(new Object[0]);
        this.lineBeginning = true;
    }

    public final void pushIndent$org_jetbrains_kotlin_tree() {
        this.printer.pushIndent();
    }

    public final void renderInBraces(String leftBrace, String rightBrace, Function0<Unit> f) {
        leftBrace.getClass();
        rightBrace.getClass();
        f.getClass();
        println(Argument.Delimiters.space + leftBrace);
        pushIndent$org_jetbrains_kotlin_tree();
        f.invoke();
        popIndent$org_jetbrains_kotlin_tree();
        println(rightBrace);
    }

    public final void renderSeparated$org_jetbrains_kotlin_tree(List<? extends FirElement> elements, FirRenderer.Visitor visitor) {
        elements.getClass();
        visitor.getClass();
        int i = 0;
        for (FirElement firElement : elements) {
            int i2 = i + 1;
            if (i > 0) {
                print(", ");
            }
            firElement.accept(visitor);
            i = i2;
        }
    }

    public final void renderSeparatedWithNewlines$org_jetbrains_kotlin_tree(List<? extends FirElement> elements, FirRenderer.Visitor visitor) {
        elements.getClass();
        visitor.getClass();
        int i = 0;
        for (FirElement firElement : elements) {
            int i2 = i + 1;
            if (i > 0) {
                print(Argument.Delimiters.default);
                newLine();
            }
            firElement.accept(visitor);
            i = i2;
        }
    }

    public String toString() {
        return this.printer.toString();
    }
}
