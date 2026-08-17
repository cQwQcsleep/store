package org.jetbrains.kotlin.fir.declarations;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Ø\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u00012\u00020\u0002B\t\b\u0004¢\u0006\u0004\b\u0003\u0010\u0004J5\u0010U\u001a\u0002HV\"\u0004\b\u0000\u0010V\"\u0004\b\u0001\u0010W2\u0012\u0010X\u001a\u000e\u0012\u0004\u0012\u0002HV\u0012\u0004\u0012\u0002HW0Y2\u0006\u0010Z\u001a\u0002HWH\u0016¢\u0006\u0002\u0010[J3\u0010\\\u001a\u0002H]\"\b\b\u0000\u0010]*\u00020^\"\u0004\b\u0001\u0010W2\f\u0010_\u001a\b\u0012\u0004\u0012\u0002HW0`2\u0006\u0010Z\u001a\u0002HWH\u0016¢\u0006\u0002\u0010aJ\u0010\u0010b\u001a\u00020c2\u0006\u0010d\u001a\u00020\u001bH&J\u0010\u0010e\u001a\u00020c2\u0006\u0010f\u001a\u00020\"H&J\u0012\u0010g\u001a\u00020c2\b\u0010h\u001a\u0004\u0018\u00010&H&J\u0010\u0010i\u001a\u00020c2\u0006\u0010j\u001a\u00020*H&J\u0016\u0010k\u001a\u00020c2\f\u0010l\u001a\b\u0012\u0004\u0012\u0002060\u0016H&J\u0012\u0010m\u001a\u00020c2\b\u0010n\u001a\u0004\u0018\u00010AH&J\u0012\u0010o\u001a\u00020c2\b\u0010p\u001a\u0004\u0018\u00010AH&J\u0012\u0010q\u001a\u00020c2\b\u0010r\u001a\u0004\u0018\u00010IH&J\u0012\u0010s\u001a\u00020c2\b\u0010t\u001a\u0004\u0018\u00010IH&J\u0016\u0010u\u001a\u00020c2\f\u0010v\u001a\b\u0012\u0004\u0012\u00020S0\u0016H&J)\u0010w\u001a\u00020\u0000\"\u0004\b\u0000\u0010W2\f\u0010_\u001a\b\u0012\u0004\u0012\u0002HW0`2\u0006\u0010Z\u001a\u0002HWH&¢\u0006\u0002\u0010xJ)\u0010y\u001a\u00020\u0000\"\u0004\b\u0000\u0010W2\f\u0010_\u001a\b\u0012\u0004\u0012\u0002HW0`2\u0006\u0010Z\u001a\u0002HWH&¢\u0006\u0002\u0010xJ)\u0010z\u001a\u00020\u0000\"\u0004\b\u0000\u0010W2\f\u0010_\u001a\b\u0012\u0004\u0012\u0002HW0`2\u0006\u0010Z\u001a\u0002HWH&¢\u0006\u0002\u0010xJ)\u0010{\u001a\u00020\u0000\"\u0004\b\u0000\u0010W2\f\u0010_\u001a\b\u0012\u0004\u0012\u0002HW0`2\u0006\u0010Z\u001a\u0002HWH&¢\u0006\u0002\u0010xJ)\u0010|\u001a\u00020\u0000\"\u0004\b\u0000\u0010W2\f\u0010_\u001a\b\u0012\u0004\u0012\u0002HW0`2\u0006\u0010Z\u001a\u0002HWH&¢\u0006\u0002\u0010xJ)\u0010}\u001a\u00020\u0000\"\u0004\b\u0000\u0010W2\f\u0010_\u001a\b\u0012\u0004\u0012\u0002HW0`2\u0006\u0010Z\u001a\u0002HWH&¢\u0006\u0002\u0010xJ)\u0010~\u001a\u00020\u0000\"\u0004\b\u0000\u0010W2\f\u0010_\u001a\b\u0012\u0004\u0012\u0002HW0`2\u0006\u0010Z\u001a\u0002HWH&¢\u0006\u0002\u0010xJ)\u0010\u007f\u001a\u00020\u0000\"\u0004\b\u0000\u0010W2\f\u0010_\u001a\b\u0012\u0004\u0012\u0002HW0`2\u0006\u0010Z\u001a\u0002HWH&¢\u0006\u0002\u0010xJ*\u0010\u0080\u0001\u001a\u00020\u0000\"\u0004\b\u0000\u0010W2\f\u0010_\u001a\b\u0012\u0004\u0012\u0002HW0`2\u0006\u0010Z\u001a\u0002HWH&¢\u0006\u0002\u0010xJ*\u0010\u0081\u0001\u001a\u00020\u0000\"\u0004\b\u0000\u0010W2\f\u0010_\u001a\b\u0012\u0004\u0012\u0002HW0`2\u0006\u0010Z\u001a\u0002HWH&¢\u0006\u0002\u0010xJ*\u0010\u0082\u0001\u001a\u00020\u0000\"\u0004\b\u0000\u0010W2\f\u0010_\u001a\b\u0012\u0004\u0012\u0002HW0`2\u0006\u0010Z\u001a\u0002HWH&¢\u0006\u0002\u0010xJ*\u0010\u0083\u0001\u001a\u00020\u0000\"\u0004\b\u0000\u0010W2\f\u0010_\u001a\b\u0012\u0004\u0012\u0002HW0`2\u0006\u0010Z\u001a\u0002HWH&¢\u0006\u0002\u0010xR\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u00020\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0012\u0010\r\u001a\u00020\u000eX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0012\u0010\u0011\u001a\u00020\u0012X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0018\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0012\u0010\u001a\u001a\u00020\u001bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0012\u0010\u001e\u001a\u00020\u001fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010 R\u0012\u0010!\u001a\u00020\"X¦\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$R\u0014\u0010%\u001a\u0004\u0018\u00010&X¦\u0004¢\u0006\u0006\u001a\u0004\b'\u0010(R\u0012\u0010)\u001a\u00020*X¦\u0004¢\u0006\u0006\u001a\u0004\b+\u0010,R\u0014\u0010-\u001a\u0004\u0018\u00010.X¦\u0004¢\u0006\u0006\u001a\u0004\b/\u00100R\u0014\u00101\u001a\u0004\u0018\u000102X¦\u0004¢\u0006\u0006\u001a\u0004\b3\u00104R\u0018\u00105\u001a\b\u0012\u0004\u0012\u0002060\u0016X¦\u0004¢\u0006\u0006\u001a\u0004\b7\u0010\u0019R\u0012\u00108\u001a\u000209X¦\u0004¢\u0006\u0006\u001a\u0004\b:\u0010;R\u0018\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00000=X¦\u0004¢\u0006\u0006\u001a\u0004\b>\u0010?R\u0014\u0010@\u001a\u0004\u0018\u00010AX¦\u0004¢\u0006\u0006\u001a\u0004\bB\u0010CR\u0014\u0010D\u001a\u0004\u0018\u00010AX¦\u0004¢\u0006\u0006\u001a\u0004\bE\u0010CR\u0012\u0010F\u001a\u00020\u001fX¦\u0004¢\u0006\u0006\u001a\u0004\bF\u0010 R\u0012\u0010G\u001a\u00020\u001fX¦\u0004¢\u0006\u0006\u001a\u0004\bG\u0010 R\u0014\u0010H\u001a\u0004\u0018\u00010IX¦\u0004¢\u0006\u0006\u001a\u0004\bJ\u0010KR\u0014\u0010L\u001a\u0004\u0018\u00010IX¦\u0004¢\u0006\u0006\u001a\u0004\bM\u0010KR\u0014\u0010N\u001a\u0004\u0018\u00010OX¦\u0004¢\u0006\u0006\u001a\u0004\bP\u0010QR\u0018\u0010R\u001a\b\u0012\u0004\u0012\u00020S0\u0016X¦\u0004¢\u0006\u0006\u001a\u0004\bT\u0010\u0019\u0082\u0001\bO\u0084\u0001\u0085\u0001\u0086\u00016¨\u0006\u0087\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirVariable;", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "<init>", "()V", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "getModuleData", "()Lorg/jetbrains/kotlin/fir/FirModuleData;", "origin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "getOrigin", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "attributes", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", "getAttributes", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", "typeParameters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRef;", "getTypeParameters", "()Ljava/util/List;", "status", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "getStatus", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "isLocal", Argument.Delimiters.none, "()Z", "returnTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "getReturnTypeRef", "()Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "receiverParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;", "getReceiverParameter", "()Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;", "deprecationsProvider", "Lorg/jetbrains/kotlin/fir/declarations/DeprecationsProvider;", "getDeprecationsProvider", "()Lorg/jetbrains/kotlin/fir/declarations/DeprecationsProvider;", "containerSource", "Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "getContainerSource", "()Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "dispatchReceiverType", "Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "getDispatchReceiverType", "()Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "contextParameters", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "getContextParameters", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "initializer", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getInitializer", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "delegate", "getDelegate", "isVar", "isVal", "getter", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "getGetter", "()Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "setter", "getSetter", "backingField", "Lorg/jetbrains/kotlin/fir/declarations/FirBackingField;", "getBackingField", "()Lorg/jetbrains/kotlin/fir/declarations/FirBackingField;", "annotations", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "transform", "E", "Lorg/jetbrains/kotlin/fir/FirElement;", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/FirElement;", "replaceStatus", Argument.Delimiters.none, "newStatus", "replaceReturnTypeRef", "newReturnTypeRef", "replaceReceiverParameter", "newReceiverParameter", "replaceDeprecationsProvider", "newDeprecationsProvider", "replaceContextParameters", "newContextParameters", "replaceInitializer", "newInitializer", "replaceDelegate", "newDelegate", "replaceGetter", "newGetter", "replaceSetter", "newSetter", "replaceAnnotations", "newAnnotations", "transformTypeParameters", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/declarations/FirVariable;", "transformStatus", "transformReturnTypeRef", "transformReceiverParameter", "transformContextParameters", "transformInitializer", "transformDelegate", "transformGetter", "transformSetter", "transformBackingField", "transformAnnotations", "transformOtherChildren", "Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;", "Lorg/jetbrains/kotlin/fir/declarations/FirField;", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirVariable extends FirCallableDeclaration implements FirStatement {
    private FirVariable() {
        super(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirElementWithResolveState, org.jetbrains.kotlin.fir.FirElement
    public <R, D> R accept(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        return visitor.visitVariable(this, data);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public abstract List<FirAnnotation> getAnnotations();

    @Override // org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration
    public abstract FirDeclarationAttributes getAttributes();

    public abstract FirBackingField getBackingField();

    @Override // org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public abstract DeserializedContainerSource getContainerSource();

    @Override // org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public abstract List<FirValueParameter> getContextParameters();

    public abstract FirExpression getDelegate();

    @Override // org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public abstract DeprecationsProvider getDeprecationsProvider();

    @Override // org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public abstract ConeSimpleKotlinType getDispatchReceiverType();

    public abstract FirPropertyAccessor getGetter();

    public abstract FirExpression getInitializer();

    @Override // org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirElementWithResolveState
    public abstract FirModuleData getModuleData();

    public abstract Name getName();

    @Override // org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration
    public abstract FirDeclarationOrigin getOrigin();

    @Override // org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public abstract FirReceiverParameter getReceiverParameter();

    @Override // org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public abstract FirTypeRef getReturnTypeRef();

    public abstract FirPropertyAccessor getSetter();

    @Override // org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirElementWithResolveState, org.jetbrains.kotlin.fir.FirElement
    public abstract KtSourceElement getSource();

    @Override // org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
    public abstract FirDeclarationStatus getStatus();

    @Override // org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration
    public abstract FirVariableSymbol<FirVariable> getSymbol();

    @Override // org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner, org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner
    public abstract List<FirTypeParameterRef> getTypeParameters();

    @Override // org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
    public abstract boolean isLocal();

    public abstract boolean isVal();

    /* JADX INFO: renamed from: isVar */
    public abstract boolean getIsVar();

    @Override // org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public abstract void replaceAnnotations(List<? extends FirAnnotation> newAnnotations);

    @Override // org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public abstract void replaceContextParameters(List<? extends FirValueParameter> newContextParameters);

    public abstract void replaceDelegate(FirExpression newDelegate);

    @Override // org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public abstract void replaceDeprecationsProvider(DeprecationsProvider newDeprecationsProvider);

    public abstract void replaceGetter(FirPropertyAccessor newGetter);

    public abstract void replaceInitializer(FirExpression newInitializer);

    @Override // org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public abstract void replaceReceiverParameter(FirReceiverParameter newReceiverParameter);

    @Override // org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public abstract void replaceReturnTypeRef(FirTypeRef newReturnTypeRef);

    public abstract void replaceSetter(FirPropertyAccessor newSetter);

    @Override // org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
    public abstract void replaceStatus(FirDeclarationStatus newStatus);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirElementWithResolveState, org.jetbrains.kotlin.fir.FirElement
    public <E extends FirElement, D> E transform(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirStatement firStatementTransformVariable = transformer.transformVariable(this, data);
        firStatementTransformVariable.getClass();
        return firStatementTransformVariable;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public abstract <D> FirVariable transformAnnotations(FirTransformer<? super D> transformer, D data);

    public abstract <D> FirVariable transformBackingField(FirTransformer<? super D> transformer, D data);

    @Override // org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public /* bridge */ /* synthetic */ FirCallableDeclaration transformContextParameters(FirTransformer firTransformer, Object obj) {
        return transformContextParameters((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public abstract <D> FirVariable transformContextParameters(FirTransformer<? super D> transformer, D data);

    public abstract <D> FirVariable transformDelegate(FirTransformer<? super D> transformer, D data);

    public abstract <D> FirVariable transformGetter(FirTransformer<? super D> transformer, D data);

    public abstract <D> FirVariable transformInitializer(FirTransformer<? super D> transformer, D data);

    public abstract <D> FirVariable transformOtherChildren(FirTransformer<? super D> transformer, D data);

    @Override // org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public /* bridge */ /* synthetic */ FirCallableDeclaration transformReceiverParameter(FirTransformer firTransformer, Object obj) {
        return transformReceiverParameter((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public abstract <D> FirVariable transformReceiverParameter(FirTransformer<? super D> transformer, D data);

    @Override // org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public /* bridge */ /* synthetic */ FirCallableDeclaration transformReturnTypeRef(FirTransformer firTransformer, Object obj) {
        return transformReturnTypeRef((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public abstract <D> FirVariable transformReturnTypeRef(FirTransformer<? super D> transformer, D data);

    public abstract <D> FirVariable transformSetter(FirTransformer<? super D> transformer, D data);

    @Override // org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
    public /* bridge */ /* synthetic */ FirCallableDeclaration transformStatus(FirTransformer firTransformer, Object obj) {
        return transformStatus((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
    public abstract <D> FirVariable transformStatus(FirTransformer<? super D> transformer, D data);

    @Override // org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner, org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner
    public /* bridge */ /* synthetic */ FirCallableDeclaration transformTypeParameters(FirTransformer firTransformer, Object obj) {
        return transformTypeParameters((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner, org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner
    public abstract <D> FirVariable transformTypeParameters(FirTransformer<? super D> transformer, D data);

    public /* synthetic */ FirVariable(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirCallableDeclaration transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
    public /* bridge */ /* synthetic */ FirMemberDeclaration transformStatus(FirTransformer firTransformer, Object obj) {
        return transformStatus((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner, org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner
    public /* bridge */ /* synthetic */ FirMemberDeclaration transformTypeParameters(FirTransformer firTransformer, Object obj) {
        return transformTypeParameters((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirDeclaration transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner, org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner
    public /* bridge */ /* synthetic */ FirTypeParameterRefsOwner transformTypeParameters(FirTransformer firTransformer, Object obj) {
        return transformTypeParameters((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirMemberDeclaration transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirStatement transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }
}
