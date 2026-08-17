package org.jetbrains.kotlin.codegen.inline;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.load.java.JvmAbi;
import org.jetbrains.org.objectweb.asm.Label;
import org.jetbrains.org.objectweb.asm.MethodVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001¢\u0006\u0004\b\u0005\u0010\u0006B!\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u0004\u001a\u00020\u0001¢\u0006\u0004\b\u0005\u0010\u000bJ\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J:\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\b\u0010\u0016\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u000fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/SourceMapCopyingMethodVisitor;", "Lorg/jetbrains/org/objectweb/asm/MethodVisitor;", "smapCopier", "Lorg/jetbrains/kotlin/codegen/inline/SourceMapCopier;", "mv", "<init>", "(Lorg/jetbrains/kotlin/codegen/inline/SourceMapCopier;Lorg/jetbrains/org/objectweb/asm/MethodVisitor;)V", "target", "Lorg/jetbrains/kotlin/codegen/inline/SourceMapper;", "source", "Lorg/jetbrains/kotlin/codegen/inline/SMAP;", "(Lorg/jetbrains/kotlin/codegen/inline/SourceMapper;Lorg/jetbrains/kotlin/codegen/inline/SMAP;Lorg/jetbrains/org/objectweb/asm/MethodVisitor;)V", "visitLineNumber", Argument.Delimiters.none, "line", Argument.Delimiters.none, "start", "Lorg/jetbrains/org/objectweb/asm/Label;", "visitLocalVariable", ModuleXmlParser.NAME, Argument.Delimiters.none, "descriptor", "signature", "end", "index", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SourceMapCopyingMethodVisitor extends MethodVisitor {
    private final SourceMapCopier smapCopier;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SourceMapCopyingMethodVisitor(SourceMapper sourceMapper, SMAP smap, MethodVisitor methodVisitor) {
        this(new SourceMapCopier(sourceMapper, smap, null, 4, null), methodVisitor);
        sourceMapper.getClass();
        smap.getClass();
        methodVisitor.getClass();
    }

    public void visitLineNumber(int line, Label start) {
        start.getClass();
        super.visitLineNumber(this.smapCopier.mapLineNumber(line), start);
    }

    public void visitLocalVariable(String name, String descriptor, String signature, Label start, Label end, int index) {
        name.getClass();
        descriptor.getClass();
        start.getClass();
        end.getClass();
        if (JvmAbi.isFakeLocalVariableForInline(name)) {
            super.visitLocalVariable(InlineScopesGeneratorKt.updateCallSiteLineNumber(name, new AnonymousClass1(this.smapCopier)), descriptor, signature, start, end, index);
        } else {
            super.visitLocalVariable(name, descriptor, signature, start, end, index);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.codegen.inline.SourceMapCopyingMethodVisitor$visitLocalVariable$1, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Integer, Integer> {
        public AnonymousClass1(Object obj) {
            super(1, obj, SourceMapCopier.class, "mapLineNumber", "mapLineNumber(I)I", 0);
        }

        public final Integer invoke(int i) {
            return Integer.valueOf(((SourceMapCopier) ((CallableReference) this).receiver).mapLineNumber(i));
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).intValue());
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SourceMapCopyingMethodVisitor(SourceMapCopier sourceMapCopier, MethodVisitor methodVisitor) {
        super(589824, methodVisitor);
        sourceMapCopier.getClass();
        methodVisitor.getClass();
        this.smapCopier = sourceMapCopier;
    }
}
