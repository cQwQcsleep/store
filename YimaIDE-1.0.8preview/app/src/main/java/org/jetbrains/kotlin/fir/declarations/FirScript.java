package org.jetbrains.kotlin.fir.declarations;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.references.FirControlFlowGraphReference;
import org.jetbrains.kotlin.fir.symbols.impl.FirScriptSymbol;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u000b\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J5\u00102\u001a\u0002H3\"\u0004\b\u0000\u00103\"\u0004\b\u0001\u001042\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u0002H3\u0012\u0004\u0012\u0002H4062\u0006\u00107\u001a\u0002H4H\u0016¢\u0006\u0002\u00108J3\u00109\u001a\u0002H:\"\b\b\u0000\u0010:*\u00020;\"\u0004\b\u0001\u001042\f\u0010<\u001a\b\u0012\u0004\u0012\u0002H40=2\u0006\u00107\u001a\u0002H4H\u0016¢\u0006\u0002\u0010>J\u0016\u0010?\u001a\u00020@2\f\u0010A\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H&J\u0012\u0010B\u001a\u00020@2\b\u0010C\u001a\u0004\u0018\u00010\u0017H&J\u0016\u0010D\u001a\u00020@2\f\u0010E\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006H&J)\u0010F\u001a\u00020\u0000\"\u0004\b\u0000\u001042\f\u0010<\u001a\b\u0012\u0004\u0012\u0002H40=2\u0006\u00107\u001a\u0002H4H&¢\u0006\u0002\u0010GJ)\u0010H\u001a\u00020\u0000\"\u0004\b\u0000\u001042\f\u0010<\u001a\b\u0012\u0004\u0012\u0002H40=2\u0006\u00107\u001a\u0002H4H&¢\u0006\u0002\u0010GJ)\u0010I\u001a\u00020\u0000\"\u0004\b\u0000\u001042\f\u0010<\u001a\b\u0012\u0004\u0012\u0002H40=2\u0006\u00107\u001a\u0002H4H&¢\u0006\u0002\u0010GJ)\u0010J\u001a\u00020\u0000\"\u0004\b\u0000\u001042\f\u0010<\u001a\b\u0012\u0004\u0012\u0002H40=2\u0006\u00107\u001a\u0002H4H&¢\u0006\u0002\u0010GR\u0018\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0012\u0010\u0012\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u0004\u0018\u00010\u0017X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0012\u0010\u001a\u001a\u00020\u001bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR$\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00010\u00068&X§\u0004r\u0002\b!¢\u0006\f\u0012\u0004\b\u001f\u0010\u0004\u001a\u0004\b \u0010\tR\u0012\u0010\"\u001a\u00020#X¦\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%R\u0012\u0010&\u001a\u00020'X¦\u0004¢\u0006\u0006\u001a\u0004\b(\u0010)R\u0018\u0010*\u001a\b\u0012\u0004\u0012\u00020+0\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b,\u0010\tR\u0018\u0010-\u001a\b\u0012\u0004\u0012\u00020.0\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b/\u0010\tR\u0014\u00100\u001a\u0004\u0018\u00010\u001bX¦\u0004¢\u0006\u0006\u001a\u0004\b1\u0010\u001d¨\u0006K"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirScript;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/declarations/FirControlFlowGraphOwner;", "<init>", "()V", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations", "()Ljava/util/List;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "getModuleData", "()Lorg/jetbrains/kotlin/fir/FirModuleData;", "origin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "getOrigin", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "attributes", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", "getAttributes", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", "controlFlowGraphReference", "Lorg/jetbrains/kotlin/fir/references/FirControlFlowGraphReference;", "getControlFlowGraphReference", "()Lorg/jetbrains/kotlin/fir/references/FirControlFlowGraphReference;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "declarations", "getDeclarations$annotations", "getDeclarations", "Lorg/jetbrains/kotlin/fir/declarations/DirectDeclarationsAccess;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirScriptSymbol;", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirScriptSymbol;", "parameters", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "getParameters", "receivers", "Lorg/jetbrains/kotlin/fir/declarations/FirScriptReceiverParameter;", "getReceivers", "resultPropertyName", "getResultPropertyName", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "transform", "E", "Lorg/jetbrains/kotlin/fir/FirElement;", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/FirElement;", "replaceAnnotations", Argument.Delimiters.none, "newAnnotations", "replaceControlFlowGraphReference", "newControlFlowGraphReference", "replaceDeclarations", "newDeclarations", "transformAnnotations", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/declarations/FirScript;", "transformDeclarations", "transformParameters", "transformReceivers", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirScript extends FirDeclaration implements FirControlFlowGraphOwner {
    public FirScript() {
        super(null);
    }

    @DirectDeclarationsAccess
    public static /* synthetic */ void getDeclarations$annotations() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirElementWithResolveState, org.jetbrains.kotlin.fir.FirElement
    public <R, D> R accept(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        return visitor.visitScript(this, data);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public abstract List<FirAnnotation> getAnnotations();

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeclaration
    public abstract FirDeclarationAttributes getAttributes();

    @Override // org.jetbrains.kotlin.fir.declarations.FirControlFlowGraphOwner
    public abstract FirControlFlowGraphReference getControlFlowGraphReference();

    public abstract List<FirDeclaration> getDeclarations();

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirElementWithResolveState
    public abstract FirModuleData getModuleData();

    public abstract Name getName();

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeclaration
    public abstract FirDeclarationOrigin getOrigin();

    public abstract List<FirProperty> getParameters();

    public abstract List<FirScriptReceiverParameter> getReceivers();

    public abstract Name getResultPropertyName();

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirElementWithResolveState, org.jetbrains.kotlin.fir.FirElement
    public abstract KtSourceElement getSource();

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeclaration
    public abstract FirScriptSymbol getSymbol();

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public abstract void replaceAnnotations(List<? extends FirAnnotation> newAnnotations);

    @Override // org.jetbrains.kotlin.fir.declarations.FirControlFlowGraphOwner
    public abstract void replaceControlFlowGraphReference(FirControlFlowGraphReference newControlFlowGraphReference);

    public abstract void replaceDeclarations(List<? extends FirDeclaration> newDeclarations);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirElementWithResolveState, org.jetbrains.kotlin.fir.FirElement
    public <E extends FirElement, D> E transform(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirScript firScriptTransformScript = transformer.transformScript(this, data);
        firScriptTransformScript.getClass();
        return firScriptTransformScript;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public abstract <D> FirScript transformAnnotations(FirTransformer<? super D> transformer, D data);

    public abstract <D> FirScript transformDeclarations(FirTransformer<? super D> transformer, D data);

    public abstract <D> FirScript transformParameters(FirTransformer<? super D> transformer, D data);

    public abstract <D> FirScript transformReceivers(FirTransformer<? super D> transformer, D data);

    @Override // org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirDeclaration transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }
}
