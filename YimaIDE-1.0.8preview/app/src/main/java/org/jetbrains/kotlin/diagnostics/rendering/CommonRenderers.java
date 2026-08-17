package org.jetbrains.kotlin.diagnostics.rendering;

import com.intellij.openapi.util.text.StringUtil;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.diagnostics.rendering.CommonRenderers;
import org.jetbrains.kotlin.diagnostics.rendering.RenderingContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirPlatformIncompatibilityDiagnosticRendererKt;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.types.Variance;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J,\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00160\u00150\u0014\"\u0004\b\u0000\u0010\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u00160\u0014H\u0007b\u0002\b\u0018J,\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00160\u00150\u0005\"\u0004\b\u0000\u0010\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u00160\u0005H\u0007b\u0002\b\u0018J\u009f\u0001\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u001b0\u0005\"\u0004\b\u0000\u0010\u001c\"\u0004\b\u0001\u0010\u001b2\u0006\u0010\u001d\u001a\u00020\b2\u0016\u0010\u001e\u001a\u0012\u0012\u0004\u0012\u0002H\u001c0\u001fj\b\u0012\u0004\u0012\u0002H\u001c` 2\f\u0010!\u001a\b\u0012\u0004\u0012\u0002H\u001c0\u00142!\u0010\"\u001a\u001d\u0012\b\u0012\u00060$j\u0002`%\u0012\u0004\u0012\u0002H\u001b\u0012\u0004\u0012\u00020&0#¢\u0006\u0002\b'2\u0018\u0010(\u001a\u0014\u0012\u0004\u0012\u0002H\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001c0\u00150)2\u0014\b\u0002\u0010*\u001a\u000e\u0012\u0004\u0012\u0002H\u001b\u0012\u0004\u0012\u00020\b0)H\u0007b\u0002\b\u0018R\u001b\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0006¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0006¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0006¢\u0006\u0002\n\u0000R\u001d\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0006¢\u0006\u0002\n\u0000R\u001b\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0006¢\u0006\u0002\n\u0000R\u001b\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0006¢\u0006\u0002\n\u0000R\u001b\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0006¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/rendering/CommonRenderers;", Argument.Delimiters.none, "<init>", "()V", "EMPTY", "Lorg/jetbrains/kotlin/diagnostics/rendering/ContextIndependentParameterRenderer;", "Lkotlin/jvm/JvmField;", "STRING", Argument.Delimiters.none, "NAME", "Lorg/jetbrains/kotlin/name/Name;", "MAVEN_VERSION", "Lorg/jetbrains/kotlin/config/MavenComparableVersion;", "THROWABLE", Argument.Delimiters.none, "RENDER_POSITION_VARIANCE", "Lorg/jetbrains/kotlin/types/Variance;", "CLASS_KIND", "Lorg/jetbrains/kotlin/descriptors/ClassKind;", "commaSeparated", "Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticParameterRenderer;", Argument.Delimiters.none, "T", "itemRenderer", "Lkotlin/jvm/JvmStatic;", "onNextLines", "renderConflictingSignatureData", "Data", "Declaration", "signatureKind", "sortUsing", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "declarationRenderer", "renderSignature", "Lkotlin/Function2;", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "declarations", "Lkotlin/Function1;", "declarationKind", "org.jetbrains.kotlin:frontend.common-psi"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CommonRenderers {
    public static final CommonRenderers INSTANCE = new CommonRenderers();
    public static final ContextIndependentParameterRenderer<Object> EMPTY = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: d82
        public final Object invoke(Object obj) {
            return CommonRenderers.i(obj);
        }
    });
    public static final ContextIndependentParameterRenderer<String> STRING = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: g82
        public final Object invoke(Object obj) {
            return CommonRenderers.l((String) obj);
        }
    });
    public static final ContextIndependentParameterRenderer<Name> NAME = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: h82
        public final Object invoke(Object obj) {
            return CommonRenderers.a((Name) obj);
        }
    });
    public static final ContextIndependentParameterRenderer<MavenComparableVersion> MAVEN_VERSION = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: i82
        public final Object invoke(Object obj) {
            return CommonRenderers.b((MavenComparableVersion) obj);
        }
    });
    public static final ContextIndependentParameterRenderer<Throwable> THROWABLE = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: j82
        public final Object invoke(Object obj) {
            return CommonRenderers.d((Throwable) obj);
        }
    });
    public static final ContextIndependentParameterRenderer<Variance> RENDER_POSITION_VARIANCE = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: k82
        public final Object invoke(Object obj) {
            return CommonRenderers.g((Variance) obj);
        }
    });
    public static final ContextIndependentParameterRenderer<ClassKind> CLASS_KIND = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: l82
        public final Object invoke(Object obj) {
            return CommonRenderers.e((ClassKind) obj);
        }
    });

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[Variance.values().length];
            try {
                iArr[Variance.INVARIANT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Variance.IN_VARIANCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Variance.OUT_VARIANCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[ClassKind.values().length];
            try {
                iArr2[ClassKind.CLASS.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[ClassKind.INTERFACE.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[ClassKind.ENUM_CLASS.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr2[ClassKind.ENUM_ENTRY.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr2[ClassKind.ANNOTATION_CLASS.ordinal()] = 5;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr2[ClassKind.OBJECT.ordinal()] = 6;
            } catch (NoSuchFieldError unused9) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    private CommonRenderers() {
    }

    public static String a(Name name) {
        name.getClass();
        String strAsString = name.asString();
        strAsString.getClass();
        return strAsString;
    }

    public static String b(MavenComparableVersion mavenComparableVersion) {
        String string;
        return (mavenComparableVersion == null || (string = mavenComparableVersion.toString()) == null) ? "initial version" : string;
    }

    public static String c(Object obj) {
        return "declarations";
    }

    @JvmStatic
    public static final <T> DiagnosticParameterRenderer<Collection<? extends T>> commaSeparated(final DiagnosticParameterRenderer<? super T> itemRenderer) {
        itemRenderer.getClass();
        return DiagnosticParameterRendererKt.ContextDependentRenderer(new Function2() { // from class: f82
            public final Object invoke(Object obj, Object obj2) {
                return CommonRenderers.f(itemRenderer, (Collection) obj, (RenderingContext) obj2);
            }
        });
    }

    public static String d(Throwable th) {
        th.getClass();
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        String strFirst = StringUtil.first(stringWriter.toString(), 2048, true);
        strFirst.getClass();
        return strFirst;
    }

    public static String e(ClassKind classKind) {
        classKind.getClass();
        switch (WhenMappings.$EnumSwitchMapping$1[classKind.ordinal()]) {
            case 1:
                return "class";
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
                return "interface";
            case 3:
                return "enum class";
            case 4:
                return "enum entry";
            case 5:
                return "annotation class";
            case 6:
                return "object";
            default:
                bu8.a();
                return null;
        }
    }

    public static String f(DiagnosticParameterRenderer diagnosticParameterRenderer, Collection collection, RenderingContext renderingContext) {
        collection.getClass();
        renderingContext.getClass();
        StringBuilder sb = new StringBuilder();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            sb.append(diagnosticParameterRenderer.render(it.next(), renderingContext));
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    public static String g(Variance variance) {
        variance.getClass();
        int i = WhenMappings.$EnumSwitchMapping$0[variance.ordinal()];
        if (i == 1) {
            return "invariant";
        }
        if (i == 2) {
            return "in";
        }
        if (i == 3) {
            return "out";
        }
        bu8.a();
        return null;
    }

    public static String h(ContextIndependentParameterRenderer contextIndependentParameterRenderer, Collection collection) {
        collection.getClass();
        return CollectionsKt.joinToString$default(collection, "\n", "\n", (CharSequence) null, 0, (CharSequence) null, new CommonRenderers$onNextLines$1$1(contextIndependentParameterRenderer), 28, (Object) null);
    }

    public static String i(Object obj) {
        obj.getClass();
        return Argument.Delimiters.none;
    }

    public static String j(Function1 function1, Comparator comparator, Function1 function2, String str, Function2 function3, final DiagnosticParameterRenderer diagnosticParameterRenderer, Object obj) {
        List listSortedWith = CollectionsKt.sortedWith((Iterable) function1.invoke(obj), comparator);
        final RenderingContext.Impl impl = new RenderingContext.Impl(listSortedWith);
        StringBuilder sb = new StringBuilder();
        sb.append("The following ");
        sb.append((String) function2.invoke(obj));
        sb.append(" have the same ");
        sb.append(str);
        sb.append(" signature (");
        function3.invoke(sb, obj);
        sb.append("):");
        sb.append('\n');
        AddToStdlibKt.joinToWithBuffer$default(listSortedWith, sb, "\n", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function2() { // from class: m82
            public final Object invoke(Object obj2, Object obj3) {
                return CommonRenderers.renderConflictingSignatureData$lambda$1$0$0(diagnosticParameterRenderer, impl, (StringBuilder) obj2, obj3);
            }
        }, 60, (Object) null);
        return sb.toString();
    }

    public static String l(String str) {
        str.getClass();
        return str;
    }

    @JvmStatic
    public static final <T> ContextIndependentParameterRenderer<Collection<? extends T>> onNextLines(final ContextIndependentParameterRenderer<? super T> itemRenderer) {
        itemRenderer.getClass();
        return DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: e82
            public final Object invoke(Object obj) {
                return CommonRenderers.h(itemRenderer, (Collection) obj);
            }
        });
    }

    @JvmStatic
    public static final <Declaration, Data> ContextIndependentParameterRenderer<Data> renderConflictingSignatureData(final String signatureKind, final Comparator<Declaration> sortUsing, final DiagnosticParameterRenderer<? super Declaration> declarationRenderer, final Function2<? super StringBuilder, ? super Data, Unit> renderSignature, final Function1<? super Data, ? extends Collection<? extends Declaration>> declarations, final Function1<? super Data, String> declarationKind) {
        signatureKind.getClass();
        sortUsing.getClass();
        declarationRenderer.getClass();
        renderSignature.getClass();
        declarations.getClass();
        declarationKind.getClass();
        return DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: o82
            public final Object invoke(Object obj) {
                return CommonRenderers.j(declarations, sortUsing, declarationKind, signatureKind, renderSignature, declarationRenderer, obj);
            }
        });
    }

    public static /* synthetic */ ContextIndependentParameterRenderer renderConflictingSignatureData$default(String str, Comparator comparator, DiagnosticParameterRenderer diagnosticParameterRenderer, Function2 function2, Function1 function1, Function1 function3, int i, Object obj) {
        if ((i & 32) != 0) {
            function3 = new Function1() { // from class: n82
                public final Object invoke(Object obj2) {
                    return CommonRenderers.c(obj2);
                }
            };
        }
        return renderConflictingSignatureData(str, comparator, diagnosticParameterRenderer, function2, function1, function3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit renderConflictingSignatureData$lambda$1$0$0(DiagnosticParameterRenderer diagnosticParameterRenderer, RenderingContext.Impl impl, StringBuilder sb, Object obj) {
        sb.getClass();
        sb.append(FirPlatformIncompatibilityDiagnosticRendererKt.INDENTATION_UNIT);
        sb.append(diagnosticParameterRenderer.render(obj, impl));
        return Unit.INSTANCE;
    }
}
