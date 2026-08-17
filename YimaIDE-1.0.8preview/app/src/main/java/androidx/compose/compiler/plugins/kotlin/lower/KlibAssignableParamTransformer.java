package androidx.compose.compiler.plugins.kotlin.lower;

import androidx.compose.compiler.plugins.kotlin.FeatureFlags;
import androidx.compose.compiler.plugins.kotlin.ModuleMetrics;
import androidx.compose.compiler.plugins.kotlin.analysis.StabilityInferencer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext;
import org.jetbrains.kotlin.descriptors.VariableDescriptor;
import org.jetbrains.kotlin.ir.IrElementsKt;
import org.jetbrains.kotlin.ir.IrStatement;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;
import org.jetbrains.kotlin.ir.declarations.impl.BuildersKt;
import org.jetbrains.kotlin.ir.declarations.impl.IrVariableImpl;
import org.jetbrains.kotlin.ir.expressions.IrBlockBody;
import org.jetbrains.kotlin.ir.expressions.IrBody;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrGetValue;
import org.jetbrains.kotlin.ir.expressions.IrSetValue;
import org.jetbrains.kotlin.ir.expressions.IrStatementOrigin;
import org.jetbrains.kotlin.ir.symbols.impl.IrVariableSymbolImpl;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid;
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoidKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B'\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016¨\u0006\u0015"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/KlibAssignableParamTransformer;", "Landroidx/compose/compiler/plugins/kotlin/lower/AbstractComposeLowering;", "Landroidx/compose/compiler/plugins/kotlin/lower/ModuleLoweringPass;", "context", "Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;", "metrics", "Landroidx/compose/compiler/plugins/kotlin/ModuleMetrics;", "stabilityInferencer", "Landroidx/compose/compiler/plugins/kotlin/analysis/StabilityInferencer;", "featureFlags", "Landroidx/compose/compiler/plugins/kotlin/FeatureFlags;", "<init>", "(Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;Landroidx/compose/compiler/plugins/kotlin/ModuleMetrics;Landroidx/compose/compiler/plugins/kotlin/analysis/StabilityInferencer;Landroidx/compose/compiler/plugins/kotlin/FeatureFlags;)V", "lower", "", "irModule", "Lorg/jetbrains/kotlin/ir/declarations/IrModuleFragment;", "visitFunction", "Lorg/jetbrains/kotlin/ir/IrStatement;", "declaration", "Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class KlibAssignableParamTransformer extends AbstractComposeLowering implements ModuleLoweringPass {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KlibAssignableParamTransformer(IrPluginContext irPluginContext, ModuleMetrics moduleMetrics, StabilityInferencer stabilityInferencer, FeatureFlags featureFlags) {
        super(irPluginContext, moduleMetrics, stabilityInferencer, featureFlags);
        irPluginContext.getClass();
        moduleMetrics.getClass();
        stabilityInferencer.getClass();
        featureFlags.getClass();
    }

    public void lower(IrModuleFragment irModule) {
        irModule.getClass();
        IrElementTransformerVoidKt.transformChildrenVoid(irModule, this);
    }

    public IrStatement visitFunction(IrFunction declaration) {
        IrBlockBody irBlockBodyCreateBlockBody;
        declaration.getClass();
        List parameters = declaration.getParameters();
        final ArrayList arrayList = new ArrayList();
        for (Object obj : parameters) {
            if (((IrValueParameter) obj).isAssignable()) {
                arrayList.add(obj);
            }
        }
        if (arrayList.isEmpty()) {
            return super.visitFunction(declaration);
        }
        final ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (true) {
            irBlockBodyCreateBlockBody = null;
            if (!it.hasNext()) {
                break;
            }
            IrValueParameter irValueParameter = (IrValueParameter) it.next();
            IrVariableImpl IrVariableImpl = BuildersKt.IrVariableImpl(-1, -1, IrDeclarationOrigin.Companion.getDEFINED(), new IrVariableSymbolImpl((VariableDescriptor) null, 1, (DefaultConstructorMarker) null), irValueParameter.getName(), irValueParameter.getType(), true, false, false);
            IrVariableImpl.setParent(declaration);
            IrVariableImpl.setInitializer(org.jetbrains.kotlin.ir.expressions.impl.BuildersKt.IrGetValueImpl$default(-1, -1, irValueParameter.getSymbol(), (IrStatementOrigin) null, 8, (Object) null));
            arrayList2.add(IrVariableImpl);
        }
        IrBody body = declaration.getBody();
        if (body != null) {
            irBlockBodyCreateBlockBody = getContext().getIrFactory().createBlockBody(body.getStartOffset(), body.getEndOffset());
            irBlockBodyCreateBlockBody.getStatements().addAll(arrayList2);
            List statements = IrUtilsKt.getStatements(body);
            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(statements, 10));
            Iterator it2 = statements.iterator();
            while (it2.hasNext()) {
                arrayList3.add(IrElementsKt.transformStatement((IrStatement) it2.next(), new IrElementTransformerVoid() { // from class: androidx.compose.compiler.plugins.kotlin.lower.KlibAssignableParamTransformer$visitFunction$1$1$updatedBody$1$1
                    public IrExpression visitGetValue(IrGetValue expression) {
                        expression.getClass();
                        if (!CollectionsKt.contains(arrayList, expression.getSymbol().getOwner())) {
                            return super.visitGetValue(expression);
                        }
                        return super.visitGetValue(org.jetbrains.kotlin.ir.expressions.impl.BuildersKt.IrGetValueImpl(expression.getStartOffset(), expression.getEndOffset(), expression.getType(), arrayList2.get(CollectionsKt.indexOf(arrayList, expression.getSymbol().getOwner())).getSymbol(), expression.getOrigin()));
                    }

                    public IrExpression visitSetValue(IrSetValue expression) {
                        expression.getClass();
                        if (!CollectionsKt.contains(arrayList, expression.getSymbol().getOwner())) {
                            return super.visitSetValue(expression);
                        }
                        return super.visitSetValue(org.jetbrains.kotlin.ir.expressions.impl.BuildersKt.IrSetValueImpl(expression.getStartOffset(), expression.getEndOffset(), expression.getType(), arrayList2.get(CollectionsKt.indexOf(arrayList, expression.getSymbol().getOwner())).getSymbol(), expression.getValue(), expression.getOrigin()));
                    }
                }));
            }
            irBlockBodyCreateBlockBody.getStatements().addAll(arrayList3);
        }
        declaration.setBody(irBlockBodyCreateBlockBody);
        return super.visitFunction(declaration);
    }
}
