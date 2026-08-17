package androidx.compose.compiler.plugins.kotlin.lower;

import androidx.compose.compiler.plugins.kotlin.FeatureFlags;
import androidx.compose.compiler.plugins.kotlin.ModuleMetrics;
import androidx.compose.compiler.plugins.kotlin.analysis.StabilityInferencer;
import androidx.compose.compiler.plugins.kotlin.lower.DurableKeyTransformer;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.ir.IrStatement;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationWithName;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationsKt;
import org.jetbrains.kotlin.ir.declarations.IrEnumEntry;
import org.jetbrains.kotlin.ir.declarations.IrField;
import org.jetbrains.kotlin.ir.declarations.IrFile;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment;
import org.jetbrains.kotlin.ir.declarations.IrPackageFragment;
import org.jetbrains.kotlin.ir.declarations.IrParameterKind;
import org.jetbrains.kotlin.ir.declarations.IrProperty;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.declarations.IrValueDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;
import org.jetbrains.kotlin.ir.declarations.IrVariable;
import org.jetbrains.kotlin.ir.expressions.IrBlock;
import org.jetbrains.kotlin.ir.expressions.IrBlockBody;
import org.jetbrains.kotlin.ir.expressions.IrBody;
import org.jetbrains.kotlin.ir.expressions.IrBranch;
import org.jetbrains.kotlin.ir.expressions.IrCatch;
import org.jetbrains.kotlin.ir.expressions.IrComposite;
import org.jetbrains.kotlin.ir.expressions.IrConstructorCall;
import org.jetbrains.kotlin.ir.expressions.IrDelegatingConstructorCall;
import org.jetbrains.kotlin.ir.expressions.IrElseBranch;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrFunctionAccessExpression;
import org.jetbrains.kotlin.ir.expressions.IrLoop;
import org.jetbrains.kotlin.ir.expressions.IrSetField;
import org.jetbrains.kotlin.ir.expressions.IrSetValue;
import org.jetbrains.kotlin.ir.expressions.IrStatementOrigin;
import org.jetbrains.kotlin.ir.expressions.IrStringConcatenation;
import org.jetbrains.kotlin.ir.expressions.IrTry;
import org.jetbrains.kotlin.ir.expressions.IrVararg;
import org.jetbrains.kotlin.ir.expressions.IrVarargElement;
import org.jetbrains.kotlin.ir.expressions.IrWhen;
import org.jetbrains.kotlin.ir.expressions.impl.BuildersKt;
import org.jetbrains.kotlin.ir.expressions.impl.IrStringConcatenationImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrVarargImpl;
import org.jetbrains.kotlin.ir.types.IrDynamicType;
import org.jetbrains.kotlin.ir.types.IrErrorType;
import org.jetbrains.kotlin.ir.types.IrSimpleType;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.util.AdditionalIrUtilsKt;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoidKt;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.utils.exceptions.PlatformExceptionUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000ú\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010#\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B/\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J0\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00160\u00142\u0006\u0010\u0017\u001a\u00020\u00152\b\b\u0002\u0010\u0018\u001a\u00020\u00152\b\b\u0002\u0010\u0019\u001a\u00020\u0015H\u0004J/\u0010\u001a\u001a\u0002H\u001b\"\u0004\b\u0000\u0010\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00150\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u001b0\u001fH\u0004¢\u0006\u0002\u0010 J)\u0010!\u001a\u0002H\u001b\"\u0004\b\u0000\u0010\u001b2\u0006\u0010\"\u001a\u00020\u00152\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u001b0\u001fH\u0004¢\u0006\u0002\u0010#J)\u0010$\u001a\u0002H\u001b\"\u0004\b\u0000\u0010\u001b2\u0006\u0010\"\u001a\u00020\u00152\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u001b0\u001fH\u0004¢\u0006\u0002\u0010#J!\u0010$\u001a\u0002H\u001b\"\u0004\b\u0000\u0010\u001b2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u001b0\u001fH\u0004¢\u0006\u0002\u0010%J\f\u0010&\u001a\u00020\u0015*\u00020'H\u0004J\u0010\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0016J\u0010\u0010,\u001a\u00020-2\u0006\u0010*\u001a\u00020-H\u0016J\u0010\u0010.\u001a\u00020/2\u0006\u0010*\u001a\u00020/H\u0016J\u0010\u00100\u001a\u0002012\u0006\u00102\u001a\u000203H\u0016J\u0010\u00104\u001a\u0002012\u0006\u00105\u001a\u000206H\u0016J\u0010\u00107\u001a\u00020)2\u0006\u0010*\u001a\u000208H\u0016J\u0010\u00109\u001a\u0002012\u0006\u00105\u001a\u00020:H\u0016J\f\u0010;\u001a\u00020\u0015*\u00020<H\u0004J\f\u0010=\u001a\u00020\u0015*\u00020>H\u0004J\u0010\u0010?\u001a\u00020)2\u0006\u0010*\u001a\u00020>H\u0016J\u0010\u0010@\u001a\u0002012\u0006\u0010A\u001a\u00020BH\u0016J\u0010\u0010C\u001a\u0002012\u0006\u00105\u001a\u00020DH\u0016J\u0010\u0010E\u001a\u0002012\u0006\u00105\u001a\u00020FH\u0016J\u0010\u0010G\u001a\u00020)2\u0006\u0010*\u001a\u00020HH\u0016J\u0010\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020JH\u0016J\u0010\u0010L\u001a\u00020M2\u0006\u0010K\u001a\u00020MH\u0016J\u0010\u0010N\u001a\u0002012\u0006\u00105\u001a\u00020OH\u0016J\u0010\u0010P\u001a\u0002012\u0006\u00105\u001a\u00020QH\u0016J\u0010\u0010R\u001a\u0002012\u0006\u00105\u001a\u00020SH\u0016J\u0010\u0010T\u001a\u0002012\u0006\u00105\u001a\u00020UH\u0016J\u0010\u0010V\u001a\u00020W2\u0006\u0010X\u001a\u00020YH\u0016J\u0010\u0010Z\u001a\u00020)2\u0006\u0010*\u001a\u00020[H\u0016J\u0010\u0010\\\u001a\u00020)2\u0006\u0010*\u001a\u00020]H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006^"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/DurableKeyTransformer;", "Landroidx/compose/compiler/plugins/kotlin/lower/AbstractComposeLowering;", "Landroidx/compose/compiler/plugins/kotlin/lower/ModuleLoweringPass;", "keyVisitor", "Landroidx/compose/compiler/plugins/kotlin/lower/DurableKeyVisitor;", "context", "Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;", "stabilityInferencer", "Landroidx/compose/compiler/plugins/kotlin/analysis/StabilityInferencer;", "metrics", "Landroidx/compose/compiler/plugins/kotlin/ModuleMetrics;", "featureFlags", "Landroidx/compose/compiler/plugins/kotlin/FeatureFlags;", "<init>", "(Landroidx/compose/compiler/plugins/kotlin/lower/DurableKeyVisitor;Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;Landroidx/compose/compiler/plugins/kotlin/analysis/StabilityInferencer;Landroidx/compose/compiler/plugins/kotlin/ModuleMetrics;Landroidx/compose/compiler/plugins/kotlin/FeatureFlags;)V", "lower", "", "irModule", "Lorg/jetbrains/kotlin/ir/declarations/IrModuleFragment;", "buildKey", "Lkotlin/Pair;", "", "", "prefix", "pathSeparator", "siblingSeparator", "root", "T", "keys", "", "block", "Lkotlin/Function0;", "(Ljava/util/Set;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "enter", "key", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "siblings", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "asJvmFriendlyString", "Lorg/jetbrains/kotlin/name/Name;", "visitClass", "Lorg/jetbrains/kotlin/ir/IrStatement;", "declaration", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "visitFile", "Lorg/jetbrains/kotlin/ir/declarations/IrFile;", "visitPackageFragment", "Lorg/jetbrains/kotlin/ir/declarations/IrPackageFragment;", "visitTry", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "aTry", "Lorg/jetbrains/kotlin/ir/expressions/IrTry;", "visitFunctionAccess", "expression", "Lorg/jetbrains/kotlin/ir/expressions/IrFunctionAccessExpression;", "visitEnumEntry", "Lorg/jetbrains/kotlin/ir/declarations/IrEnumEntry;", "visitVararg", "Lorg/jetbrains/kotlin/ir/expressions/IrVararg;", "asString", "Lorg/jetbrains/kotlin/ir/types/IrType;", "signatureString", "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "visitSimpleFunction", "visitLoop", "loop", "Lorg/jetbrains/kotlin/ir/expressions/IrLoop;", "visitStringConcatenation", "Lorg/jetbrains/kotlin/ir/expressions/IrStringConcatenation;", "visitWhen", "Lorg/jetbrains/kotlin/ir/expressions/IrWhen;", "visitValueParameter", "Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;", "visitElseBranch", "Lorg/jetbrains/kotlin/ir/expressions/IrElseBranch;", "branch", "visitBranch", "Lorg/jetbrains/kotlin/ir/expressions/IrBranch;", "visitComposite", "Lorg/jetbrains/kotlin/ir/expressions/IrComposite;", "visitBlock", "Lorg/jetbrains/kotlin/ir/expressions/IrBlock;", "visitSetValue", "Lorg/jetbrains/kotlin/ir/expressions/IrSetValue;", "visitSetField", "Lorg/jetbrains/kotlin/ir/expressions/IrSetField;", "visitBlockBody", "Lorg/jetbrains/kotlin/ir/expressions/IrBody;", "body", "Lorg/jetbrains/kotlin/ir/expressions/IrBlockBody;", "visitVariable", "Lorg/jetbrains/kotlin/ir/declarations/IrVariable;", "visitProperty", "Lorg/jetbrains/kotlin/ir/declarations/IrProperty;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public class DurableKeyTransformer extends AbstractComposeLowering implements ModuleLoweringPass {
    private final DurableKeyVisitor keyVisitor;

    @Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[IrParameterKind.values().length];
            try {
                iArr[IrParameterKind.DispatchReceiver.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[IrParameterKind.ExtensionReceiver.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[IrParameterKind.Context.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[IrParameterKind.Regular.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DurableKeyTransformer(DurableKeyVisitor durableKeyVisitor, IrPluginContext irPluginContext, StabilityInferencer stabilityInferencer, ModuleMetrics moduleMetrics, FeatureFlags featureFlags) {
        super(irPluginContext, moduleMetrics, stabilityInferencer, featureFlags);
        durableKeyVisitor.getClass();
        irPluginContext.getClass();
        stabilityInferencer.getClass();
        moduleMetrics.getClass();
        featureFlags.getClass();
        this.keyVisitor = durableKeyVisitor;
    }

    public static IrStringConcatenationImpl A(final DurableKeyTransformer durableKeyTransformer, final IrStringConcatenation irStringConcatenation) {
        return (IrStringConcatenationImpl) durableKeyTransformer.siblings(new Function0() { // from class: xz3
            public final Object invoke() {
                return DurableKeyTransformer.visitStringConcatenation$lambda$0$0(irStringConcatenation, durableKeyTransformer);
            }
        });
    }

    public static IrPackageFragment B(DurableKeyTransformer durableKeyTransformer, IrPackageFragment irPackageFragment) {
        return super.visitPackageFragment(irPackageFragment);
    }

    public static IrExpression C(DurableKeyTransformer durableKeyTransformer, IrSetField irSetField) {
        return super.visitSetField(irSetField);
    }

    public static IrExpression F(DurableKeyTransformer durableKeyTransformer, IrWhen irWhen) {
        return super.visitWhen(irWhen);
    }

    public static IrExpression G(IrTry irTry, DurableKeyTransformer durableKeyTransformer) {
        IrExpression finallyExpression = irTry.getFinallyExpression();
        if (finallyExpression != null) {
            return finallyExpression.transform(durableKeyTransformer, (Object) null);
        }
        return null;
    }

    public static Unit H(IrTry irTry, final DurableKeyTransformer durableKeyTransformer) {
        for (final IrCatch irCatch : irTry.getCatches()) {
            irCatch.setResult((IrExpression) durableKeyTransformer.enter("catch", new Function0() { // from class: i04
                public final Object invoke() {
                    return DurableKeyTransformer.visitTry$lambda$1$0$0(irCatch, durableKeyTransformer);
                }
            }));
        }
        return Unit.INSTANCE;
    }

    public static IrExpression I(DurableKeyTransformer durableKeyTransformer, IrBlock irBlock) {
        return super.visitBlock(irBlock);
    }

    public static IrBody J(DurableKeyTransformer durableKeyTransformer, IrBlockBody irBlockBody) {
        return super.visitBlockBody(irBlockBody);
    }

    public static IrProperty K(IrProperty irProperty, IrField irField, final DurableKeyTransformer durableKeyTransformer, final IrSimpleFunction irSimpleFunction, final IrSimpleFunction irSimpleFunction2) {
        IrElement irElementTransform = irField != null ? irField.transform(durableKeyTransformer, (Object) null) : null;
        irProperty.setBackingField(irElementTransform instanceof IrField ? (IrField) irElementTransform : null);
        irProperty.setGetter((IrSimpleFunction) durableKeyTransformer.enter("get", new Function0() { // from class: rz3
            public final Object invoke() {
                return DurableKeyTransformer.visitProperty$lambda$0$0(irSimpleFunction, durableKeyTransformer);
            }
        }));
        irProperty.setSetter((IrSimpleFunction) durableKeyTransformer.enter("set", new Function0() { // from class: sz3
            public final Object invoke() {
                return DurableKeyTransformer.visitProperty$lambda$0$1(irSimpleFunction2, durableKeyTransformer);
            }
        }));
        return irProperty;
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0063  */
    /* JADX WARN: Code duplicated, block: B:26:0x006b A[SYNTHETIC] */
    public static IrFunctionAccessExpression M(final IrFunctionAccessExpression irFunctionAccessExpression, final DurableKeyTransformer durableKeyTransformer) {
        String str;
        int i;
        String string;
        int size = irFunctionAccessExpression.getArguments().size();
        final int i2 = 0;
        int i3 = 0;
        while (i2 < size) {
            final IrExpression irExpression = (IrExpression) irFunctionAccessExpression.getArguments().get(i2);
            int i4 = WhenMappings.$EnumSwitchMapping$0[((IrValueParameter) irFunctionAccessExpression.getSymbol().getOwner().getParameters().get(i2)).getKind().ordinal()];
            if (i4 != 1) {
                if (i4 == 2) {
                    str = "$$this";
                } else {
                    if (i4 != 3 && i4 != 4) {
                        bu8.a();
                        return null;
                    }
                    StringBuilder sb = new StringBuilder("arg-");
                    i = i3 + 1;
                    sb.append(i3);
                    string = sb.toString();
                }
                if (irExpression != null) {
                    durableKeyTransformer.enter(string, new Function0() { // from class: uz3
                        public final Object invoke() {
                            return DurableKeyTransformer.visitFunctionAccess$lambda$0$0(irFunctionAccessExpression, i2, irExpression, durableKeyTransformer);
                        }
                    });
                }
                i2++;
                i3 = i;
            } else {
                str = "$this";
            }
            i = i3;
            string = str;
            if (irExpression != null) {
                durableKeyTransformer.enter(string, new Function0() { // from class: uz3
                    public final Object invoke() {
                        return DurableKeyTransformer.visitFunctionAccess$lambda$0$0(irFunctionAccessExpression, i2, irExpression, durableKeyTransformer);
                    }
                });
            }
            i2++;
            i3 = i;
        }
        return irFunctionAccessExpression;
    }

    public static IrExpression P(IrBranch irBranch, DurableKeyTransformer durableKeyTransformer) {
        return irBranch.getResult().transform(durableKeyTransformer, (Object) null);
    }

    public static IrExpression R(DurableKeyTransformer durableKeyTransformer, IrWhen irWhen) {
        return super.visitWhen(irWhen);
    }

    public static IrStatement T(DurableKeyTransformer durableKeyTransformer, IrClass irClass) {
        return super.visitClass(irClass);
    }

    public static /* synthetic */ Pair buildKey$default(DurableKeyTransformer durableKeyTransformer, String str, String str2, String str3, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: buildKey");
            return null;
        }
        if ((i & 2) != 0) {
            str2 = "/";
        }
        if ((i & 4) != 0) {
            str3 = ":";
        }
        return durableKeyTransformer.buildKey(str, str2, str3);
    }

    public static IrLoop k(final IrLoop irLoop, final DurableKeyTransformer durableKeyTransformer) {
        irLoop.setCondition((IrExpression) durableKeyTransformer.enter("cond", new Function0() { // from class: p04
            public final Object invoke() {
                return DurableKeyTransformer.visitLoop$lambda$1$0(irLoop, durableKeyTransformer);
            }
        }));
        irLoop.setBody((IrExpression) durableKeyTransformer.enter("body", new Function0() { // from class: q04
            public final Object invoke() {
                return DurableKeyTransformer.visitLoop$lambda$1$1(irLoop, durableKeyTransformer);
            }
        }));
        return irLoop;
    }

    public static IrExpression l(DurableKeyTransformer durableKeyTransformer, IrComposite irComposite) {
        return super.visitComposite(irComposite);
    }

    public static IrExpression m(IrBranch irBranch, DurableKeyTransformer durableKeyTransformer) {
        return irBranch.getCondition().transform(durableKeyTransformer, (Object) null);
    }

    public static IrExpression n(IrElseBranch irElseBranch, DurableKeyTransformer durableKeyTransformer) {
        return irElseBranch.getResult().transform(durableKeyTransformer, (Object) null);
    }

    public static IrLoop o(final IrLoop irLoop, final DurableKeyTransformer durableKeyTransformer) {
        irLoop.setBody((IrExpression) durableKeyTransformer.enter("body", new Function0() { // from class: j04
            public final Object invoke() {
                return DurableKeyTransformer.visitLoop$lambda$0$0(irLoop, durableKeyTransformer);
            }
        }));
        return irLoop;
    }

    public static IrStatement q(DurableKeyTransformer durableKeyTransformer, IrValueParameter irValueParameter) {
        return super.visitValueParameter(irValueParameter);
    }

    public static IrVarargImpl r(IrVararg irVararg, final DurableKeyTransformer durableKeyTransformer) {
        IrVarargImpl irVarargImpl = (IrVarargImpl) irVararg;
        int i = 0;
        for (Object obj : irVarargImpl.getElements()) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            final IrVarargElement irVarargElement = (IrVarargElement) obj;
            irVarargImpl.getElements().set(i, durableKeyTransformer.enter(String.valueOf(i), new Function0() { // from class: qz3
                public final Object invoke() {
                    return DurableKeyTransformer.visitVararg$lambda$0$0$0(irVarargElement, durableKeyTransformer);
                }
            }));
            i = i2;
        }
        return irVarargImpl;
    }

    public static IrStatement s(DurableKeyTransformer durableKeyTransformer, IrEnumEntry irEnumEntry) {
        return super.visitEnumEntry(irEnumEntry);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence signatureString$lambda$0$1(DurableKeyTransformer durableKeyTransformer, IrValueParameter irValueParameter) {
        irValueParameter.getClass();
        return durableKeyTransformer.asString(irValueParameter.getType());
    }

    public static IrStatement t(DurableKeyTransformer durableKeyTransformer, IrVariable irVariable) {
        return super.visitVariable(irVariable);
    }

    public static IrExpression u(DurableKeyTransformer durableKeyTransformer, IrSetValue irSetValue) {
        return super.visitSetValue(irSetValue);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit visitFunctionAccess$lambda$0$0(IrFunctionAccessExpression irFunctionAccessExpression, int i, IrExpression irExpression, DurableKeyTransformer durableKeyTransformer) {
        irFunctionAccessExpression.getArguments().set(i, irExpression.transform(durableKeyTransformer, (Object) null));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IrExpression visitLoop$lambda$0$0(IrLoop irLoop, DurableKeyTransformer durableKeyTransformer) {
        IrExpression body = irLoop.getBody();
        if (body != null) {
            return body.transform(durableKeyTransformer, (Object) null);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IrExpression visitLoop$lambda$1$0(IrLoop irLoop, DurableKeyTransformer durableKeyTransformer) {
        return irLoop.getCondition().transform(durableKeyTransformer, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IrExpression visitLoop$lambda$1$1(IrLoop irLoop, DurableKeyTransformer durableKeyTransformer) {
        IrExpression body = irLoop.getBody();
        if (body != null) {
            return body.transform(durableKeyTransformer, (Object) null);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IrSimpleFunction visitProperty$lambda$0$0(IrSimpleFunction irSimpleFunction, DurableKeyTransformer durableKeyTransformer) {
        IrElement irElementTransform = irSimpleFunction != null ? irSimpleFunction.transform(durableKeyTransformer, (Object) null) : null;
        if (irElementTransform instanceof IrSimpleFunction) {
            return (IrSimpleFunction) irElementTransform;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IrSimpleFunction visitProperty$lambda$0$1(IrSimpleFunction irSimpleFunction, DurableKeyTransformer durableKeyTransformer) {
        IrElement irElementTransform = irSimpleFunction != null ? irSimpleFunction.transform(durableKeyTransformer, (Object) null) : null;
        if (irElementTransform instanceof IrSimpleFunction) {
            return (IrSimpleFunction) irElementTransform;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IrStringConcatenationImpl visitStringConcatenation$lambda$0$0(IrStringConcatenation irStringConcatenation, final DurableKeyTransformer durableKeyTransformer) {
        IrStringConcatenationImpl irStringConcatenationImpl = (IrStringConcatenationImpl) irStringConcatenation;
        int i = 0;
        for (Object obj : irStringConcatenationImpl.getArguments()) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            final IrExpression irExpression = (IrExpression) obj;
            irStringConcatenationImpl.getArguments().set(i, durableKeyTransformer.enter(String.valueOf(i), new Function0() { // from class: a04
                public final Object invoke() {
                    return DurableKeyTransformer.visitStringConcatenation$lambda$0$0$0$0(irExpression, durableKeyTransformer);
                }
            }));
            i = i2;
        }
        return irStringConcatenationImpl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IrExpression visitStringConcatenation$lambda$0$0$0$0(IrExpression irExpression, DurableKeyTransformer durableKeyTransformer) {
        return irExpression.transform(durableKeyTransformer, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IrExpression visitTry$lambda$1$0$0(IrCatch irCatch, DurableKeyTransformer durableKeyTransformer) {
        return irCatch.getResult().transform(durableKeyTransformer, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IrVarargElement visitVararg$lambda$0$0$0(IrVarargElement irVarargElement, DurableKeyTransformer durableKeyTransformer) {
        IrVarargElement irVarargElementTransform = irVarargElement.transform(durableKeyTransformer, (Object) null);
        irVarargElementTransform.getClass();
        return irVarargElementTransform;
    }

    public static IrExpression x(IrTry irTry, DurableKeyTransformer durableKeyTransformer) {
        return irTry.getTryResult().transform(durableKeyTransformer, (Object) null);
    }

    public static IrStatement y(DurableKeyTransformer durableKeyTransformer, IrSimpleFunction irSimpleFunction) {
        return super.visitSimpleFunction(irSimpleFunction);
    }

    public final String asJvmFriendlyString(Name name) {
        name.getClass();
        if (name.isSpecial()) {
            String strAsString = name.asString();
            strAsString.getClass();
            return StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(strAsString, '<', '$', false, 4, (Object) null), '>', '$', false, 4, (Object) null), ' ', '-', false, 4, (Object) null);
        }
        String identifier = name.getIdentifier();
        identifier.getClass();
        return identifier;
    }

    public final String asString(IrType irType) {
        irType.getClass();
        if (irType instanceof IrDynamicType) {
            return "dynamic";
        }
        if (irType instanceof IrErrorType) {
            return "IrErrorType";
        }
        if (!(irType instanceof IrSimpleType)) {
            bu8.a();
            return null;
        }
        IrDeclarationWithName owner = ((IrSimpleType) irType).getClassifier().getOwner();
        owner.getClass();
        String strAsString = owner.getName().asString();
        strAsString.getClass();
        return strAsString;
    }

    public final Pair<String, Boolean> buildKey(String prefix, String pathSeparator, String siblingSeparator) {
        prefix.getClass();
        pathSeparator.getClass();
        siblingSeparator.getClass();
        return this.keyVisitor.buildPath(prefix, pathSeparator, siblingSeparator);
    }

    public final <T> T enter(String key, Function0<? extends T> block) {
        key.getClass();
        block.getClass();
        return (T) this.keyVisitor.enter(key, block);
    }

    public void lower(IrModuleFragment irModule) {
        irModule.getClass();
        IrElementTransformerVoidKt.transformChildrenVoid(irModule, this);
    }

    public final <T> T root(Set<String> keys, Function0<? extends T> block) {
        keys.getClass();
        block.getClass();
        return (T) this.keyVisitor.root(keys, block);
    }

    public final <T> T siblings(String key, Function0<? extends T> block) {
        key.getClass();
        block.getClass();
        return (T) this.keyVisitor.siblings(key, block);
    }

    public final String signatureString(IrSimpleFunction irSimpleFunction) {
        irSimpleFunction.getClass();
        StringBuilder sb = new StringBuilder();
        IrValueParameter irValueParameterFirstParameterOfKind = AbstractComposeLoweringKt.firstParameterOfKind(irSimpleFunction, IrParameterKind.ExtensionReceiver);
        if (irValueParameterFirstParameterOfKind != null) {
            sb.append(asString(irValueParameterFirstParameterOfKind.getType()));
            sb.append(".");
        }
        sb.append(asJvmFriendlyString(irSimpleFunction.getName()));
        sb.append('(');
        sb.append(CollectionsKt.joinToString$default(AbstractComposeLoweringKt.getNamedParameters(irSimpleFunction), ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: zz3
            public final Object invoke(Object obj) {
                return DurableKeyTransformer.signatureString$lambda$0$1(this.b, (IrValueParameter) obj);
            }
        }, 30, (Object) null));
        sb.append(')');
        sb.append(asString(irSimpleFunction.getReturnType()));
        return sb.toString();
    }

    public IrExpression visitBlock(final IrBlock expression) {
        expression.getClass();
        IrStatementOrigin origin = expression.getOrigin();
        IrStatementOrigin.Companion companion = IrStatementOrigin.Companion;
        if (!Intrinsics.areEqual(origin, companion.getFOR_LOOP()) && !Intrinsics.areEqual(origin, companion.getFOR_LOOP_INNER_WHILE())) {
            return (IrExpression) siblings(new Function0() { // from class: s04
                public final Object invoke() {
                    return DurableKeyTransformer.I(this.b, expression);
                }
            });
        }
        List statements = expression.getStatements();
        IrStatement irStatementTransform = ((IrStatement) expression.getStatements().get(1)).transform(this, (Object) null);
        irStatementTransform.getClass();
        statements.set(1, irStatementTransform);
        return expression;
    }

    public IrBody visitBlockBody(final IrBlockBody body) {
        body.getClass();
        return (IrBody) siblings(new Function0() { // from class: d04
            public final Object invoke() {
                return DurableKeyTransformer.J(this.b, body);
            }
        });
    }

    public IrBranch visitBranch(final IrBranch branch) {
        branch.getClass();
        return BuildersKt.IrBranchImpl(branch.getStartOffset(), branch.getEndOffset(), (IrExpression) enter("cond", new Function0() { // from class: b04
            public final Object invoke() {
                return DurableKeyTransformer.m(branch, this);
            }
        }), (IrExpression) enter("branch", new Function0() { // from class: c04
            public final Object invoke() {
                return DurableKeyTransformer.P(branch, this);
            }
        }));
    }

    public IrStatement visitClass(final IrClass declaration) {
        declaration.getClass();
        if (IrUtilsKt.isAnnotationClass(declaration)) {
            return declaration;
        }
        return (IrStatement) siblings("class-" + asJvmFriendlyString(declaration.getName()), new Function0() { // from class: oz3
            public final Object invoke() {
                return DurableKeyTransformer.T(this.b, declaration);
            }
        });
    }

    public IrExpression visitComposite(final IrComposite expression) {
        expression.getClass();
        return (IrExpression) siblings(new Function0() { // from class: o04
            public final Object invoke() {
                return DurableKeyTransformer.l(this.b, expression);
            }
        });
    }

    public IrElseBranch visitElseBranch(final IrElseBranch branch) {
        branch.getClass();
        return BuildersKt.IrElseBranchImpl(branch.getStartOffset(), branch.getEndOffset(), branch.getCondition(), (IrExpression) enter("else", new Function0() { // from class: e04
            public final Object invoke() {
                return DurableKeyTransformer.n(branch, this);
            }
        }));
    }

    public IrStatement visitEnumEntry(final IrEnumEntry declaration) {
        declaration.getClass();
        return (IrStatement) enter("entry-" + asJvmFriendlyString(declaration.getName()), new Function0() { // from class: tz3
            public final Object invoke() {
                return DurableKeyTransformer.s(this.b, declaration);
            }
        });
    }

    public IrFile visitFile(final IrFile declaration) throws Exception {
        declaration.getClass();
        try {
            return (IrFile) enter("file-" + ((String) CollectionsKt.last(StringsKt.split$default(declaration.getFileEntry().getName(), new char[]{'/'}, false, 0, 6, (Object) null))), new Function0() { // from class: r04
                public final Object invoke() {
                    return super/*org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid*/.visitFile(declaration);
                }
            });
        } catch (Exception e) {
            PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
            throw new Exception("IR lowering failed at: " + IrDeclarationsKt.getName(declaration), e);
        }
    }

    public IrExpression visitFunctionAccess(final IrFunctionAccessExpression expression) {
        expression.getClass();
        IrFunction owner = expression.getSymbol().getOwner();
        if (((expression instanceof IrConstructorCall) || (expression instanceof IrDelegatingConstructorCall)) && IrUtilsKt.isAnnotationClass(IrUtilsKt.getParentAsClass(owner))) {
            return expression;
        }
        return (IrExpression) enter("call-" + asJvmFriendlyString(owner.getName()), new Function0() { // from class: f04
            public final Object invoke() {
                return DurableKeyTransformer.M(expression, this);
            }
        });
    }

    public IrExpression visitLoop(final IrLoop loop) {
        loop.getClass();
        IrStatementOrigin origin = loop.getOrigin();
        IrStatementOrigin.Companion companion = IrStatementOrigin.Companion;
        return (Intrinsics.areEqual(origin, companion.getWHILE_LOOP()) || Intrinsics.areEqual(origin, companion.getFOR_LOOP_INNER_WHILE())) ? (IrExpression) enter("loop", new Function0() { // from class: k04
            public final Object invoke() {
                return DurableKeyTransformer.o(loop, this);
            }
        }) : (IrExpression) enter("loop", new Function0() { // from class: l04
            public final Object invoke() {
                return DurableKeyTransformer.k(loop, this);
            }
        });
    }

    public IrPackageFragment visitPackageFragment(final IrPackageFragment declaration) {
        declaration.getClass();
        return (IrPackageFragment) enter("pkg-" + AdditionalIrUtilsKt.getFqNameForIrSerialization(declaration), new Function0() { // from class: pz3
            public final Object invoke() {
                return DurableKeyTransformer.B(this.b, declaration);
            }
        });
    }

    public IrStatement visitProperty(final IrProperty declaration) {
        declaration.getClass();
        final IrField backingField = declaration.getBackingField();
        final IrSimpleFunction getter = declaration.getGetter();
        final IrSimpleFunction setter = declaration.getSetter();
        return (IrStatement) enter("val-" + asJvmFriendlyString(declaration.getName()), new Function0() { // from class: t04
            public final Object invoke() {
                return DurableKeyTransformer.K(declaration, backingField, this, getter, setter);
            }
        });
    }

    public IrExpression visitSetField(final IrSetField expression) {
        expression.getClass();
        return (IrExpression) enter("set-" + expression.getSymbol().getOwner().getName(), new Function0() { // from class: n04
            public final Object invoke() {
                return DurableKeyTransformer.C(this.b, expression);
            }
        });
    }

    public IrExpression visitSetValue(final IrSetValue expression) {
        expression.getClass();
        IrValueDeclaration owner = expression.getSymbol().getOwner();
        Name name = owner.getName();
        IrDeclarationOrigin origin = owner.getOrigin();
        IrDeclarationOrigin.Companion companion = IrDeclarationOrigin.Companion;
        if (Intrinsics.areEqual(origin, companion.getFOR_LOOP_IMPLICIT_VARIABLE()) || Intrinsics.areEqual(origin, companion.getIR_TEMPORARY_VARIABLE()) || Intrinsics.areEqual(origin, companion.getFOR_LOOP_VARIABLE())) {
            return expression;
        }
        return (IrExpression) enter("set-" + name, new Function0() { // from class: yz3
            public final Object invoke() {
                return DurableKeyTransformer.u(this.b, expression);
            }
        });
    }

    public IrStatement visitSimpleFunction(final IrSimpleFunction declaration) {
        declaration.getClass();
        return (IrStatement) enter("fun-" + signatureString(declaration), new Function0() { // from class: wz3
            public final Object invoke() {
                return DurableKeyTransformer.y(this.b, declaration);
            }
        });
    }

    public IrExpression visitStringConcatenation(final IrStringConcatenation expression) {
        expression.getClass();
        return !(expression instanceof IrStringConcatenationImpl) ? expression : (IrExpression) enter("str", new Function0() { // from class: h04
            public final Object invoke() {
                return DurableKeyTransformer.A(this.b, expression);
            }
        });
    }

    public IrExpression visitTry(final IrTry aTry) {
        aTry.getClass();
        aTry.setTryResult((IrExpression) enter("try", new Function0() { // from class: kz3
            public final Object invoke() {
                return DurableKeyTransformer.x(aTry, this);
            }
        }));
        siblings(new Function0() { // from class: vz3
            public final Object invoke() {
                return DurableKeyTransformer.H(aTry, this);
            }
        });
        aTry.setFinallyExpression((IrExpression) enter("finally", new Function0() { // from class: g04
            public final Object invoke() {
                return DurableKeyTransformer.G(aTry, this);
            }
        }));
        return aTry;
    }

    public IrStatement visitValueParameter(final IrValueParameter declaration) {
        declaration.getClass();
        return (IrStatement) enter("param-" + asJvmFriendlyString(declaration.getName()), new Function0() { // from class: m04
            public final Object invoke() {
                return DurableKeyTransformer.q(this.b, declaration);
            }
        });
    }

    public IrExpression visitVararg(final IrVararg expression) {
        expression.getClass();
        return !(expression instanceof IrVarargImpl) ? expression : (IrExpression) enter("vararg", new Function0() { // from class: u04
            public final Object invoke() {
                return DurableKeyTransformer.r(expression, this);
            }
        });
    }

    public IrStatement visitVariable(final IrVariable declaration) {
        declaration.getClass();
        return (IrStatement) enter("val-" + asJvmFriendlyString(declaration.getName()), new Function0() { // from class: lz3
            public final Object invoke() {
                return DurableKeyTransformer.t(this.b, declaration);
            }
        });
    }

    public IrExpression visitWhen(final IrWhen expression) {
        expression.getClass();
        IrStatementOrigin origin = expression.getOrigin();
        IrStatementOrigin.Companion companion = IrStatementOrigin.Companion;
        if (Intrinsics.areEqual(origin, companion.getANDAND())) {
            expression.getBranches().set(0, ((IrBranch) expression.getBranches().get(0)).transform(this, (Object) null));
            return expression;
        }
        if (!Intrinsics.areEqual(origin, companion.getOROR())) {
            return Intrinsics.areEqual(origin, companion.getIF()) ? (IrExpression) siblings("if", new Function0() { // from class: mz3
                public final Object invoke() {
                    return DurableKeyTransformer.F(this.b, expression);
                }
            }) : (IrExpression) siblings("when", new Function0() { // from class: nz3
                public final Object invoke() {
                    return DurableKeyTransformer.R(this.b, expression);
                }
            });
        }
        expression.getBranches().set(1, ((IrBranch) expression.getBranches().get(1)).transform(this, (Object) null));
        return expression;
    }

    public final <T> T siblings(Function0<? extends T> block) {
        block.getClass();
        return (T) this.keyVisitor.siblings(block);
    }
}
