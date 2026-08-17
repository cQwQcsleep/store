package org.jetbrains.kotlin.fir.resolve.transformers.body.resolve;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousObject;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.types.FirImplicitTypeRef;
import org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0013\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u0013\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010 \u001a\u00020\u00022\u0006\u0010!\u001a\u00020\"2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010#\u001a\u00020\u00022\u0006\u0010$\u001a\u00020%2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010&\u001a\u00020\u00022\u0006\u0010'\u001a\u00020(2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010)\u001a\u00020\u00022\u0006\u0010*\u001a\u00020+2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010,\u001a\u00020\u00022\u0006\u0010-\u001a\u00020\b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0003H\u0016R\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001f\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/NavigationInfoVisitor;", "Lorg/jetbrains/kotlin/fir/visitors/FirDefaultVisitor;", Argument.Delimiters.none, Argument.Delimiters.none, "<init>", "()V", "resultingMap", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "getResultingMap", "()Ljava/util/Map;", "parentForClass", "getParentForClass", "currentPath", Argument.Delimiters.none, "visitElement", "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "data", "visitRegularClass", "regularClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "visitAnonymousObject", "anonymousObject", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousObject;", "visitTypeAlias", "typeAlias", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "visitClass", "klass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "visitNamedFunction", "namedFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "visitProperty", "property", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "visitField", "field", "Lorg/jetbrains/kotlin/fir/declarations/FirField;", "visitConstructor", "constructor", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "visitCallableDeclaration", "callableDeclaration", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class NavigationInfoVisitor extends FirDefaultVisitor<Unit, Object> {
    private final Map<FirCallableDeclaration, FirClassLikeDeclaration> resultingMap = new LinkedHashMap();
    private final Map<FirClassLikeDeclaration, FirClassLikeDeclaration> parentForClass = new LinkedHashMap();
    private final List<FirClassLikeDeclaration> currentPath = new ArrayList();

    public final Map<FirClassLikeDeclaration, FirClassLikeDeclaration> getParentForClass() {
        return this.parentForClass;
    }

    public final Map<FirCallableDeclaration, FirClassLikeDeclaration> getResultingMap() {
        return this.resultingMap;
    }

    /* JADX INFO: renamed from: visitAnonymousObject, reason: collision with other method in class */
    public void m602visitAnonymousObject(FirAnonymousObject anonymousObject, Object data) {
        anonymousObject.getClass();
        m604visitClass((FirClass) anonymousObject, (Object) null);
    }

    /* JADX INFO: renamed from: visitCallableDeclaration, reason: collision with other method in class */
    public void m603visitCallableDeclaration(FirCallableDeclaration callableDeclaration, Object data) {
        callableDeclaration.getClass();
        if (callableDeclaration.getReturnTypeRef() instanceof FirImplicitTypeRef) {
            this.resultingMap.put(callableDeclaration, (FirClassLikeDeclaration) CollectionsKt.last(this.currentPath));
        }
    }

    /* JADX INFO: renamed from: visitClass, reason: collision with other method in class */
    public void m604visitClass(FirClass klass, Object data) {
        klass.getClass();
        this.parentForClass.put(klass, (FirClassLikeDeclaration) CollectionsKt.lastOrNull(this.currentPath));
        this.currentPath.add(klass);
        klass.acceptChildren(this, null);
        List<FirClassLikeDeclaration> list = this.currentPath;
        list.remove(list.size() - 1);
    }

    /* JADX INFO: renamed from: visitConstructor, reason: collision with other method in class */
    public void m605visitConstructor(FirConstructor constructor, Object data) {
        constructor.getClass();
        m603visitCallableDeclaration((FirCallableDeclaration) constructor, (Object) null);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitElement(FirElement firElement, Object obj) {
        m606visitElement(firElement, obj);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: visitField, reason: collision with other method in class */
    public void m607visitField(FirField field, Object data) {
        field.getClass();
        m603visitCallableDeclaration((FirCallableDeclaration) field, (Object) null);
    }

    /* JADX INFO: renamed from: visitNamedFunction, reason: collision with other method in class */
    public void m608visitNamedFunction(FirNamedFunction namedFunction, Object data) {
        namedFunction.getClass();
        m603visitCallableDeclaration((FirCallableDeclaration) namedFunction, (Object) null);
    }

    /* JADX INFO: renamed from: visitProperty, reason: collision with other method in class */
    public void m609visitProperty(FirProperty property, Object data) {
        property.getClass();
        m603visitCallableDeclaration((FirCallableDeclaration) property, (Object) null);
    }

    /* JADX INFO: renamed from: visitRegularClass, reason: collision with other method in class */
    public void m610visitRegularClass(FirRegularClass regularClass, Object data) {
        regularClass.getClass();
        m604visitClass((FirClass) regularClass, (Object) null);
    }

    /* JADX INFO: renamed from: visitTypeAlias, reason: collision with other method in class */
    public void m611visitTypeAlias(FirTypeAlias typeAlias, Object data) {
        typeAlias.getClass();
        this.parentForClass.put(typeAlias, (FirClassLikeDeclaration) CollectionsKt.lastOrNull(this.currentPath));
    }

    /* JADX INFO: renamed from: visitElement, reason: collision with other method in class */
    public void m606visitElement(FirElement element, Object data) {
        element.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitAnonymousObject(FirAnonymousObject firAnonymousObject, Object obj) {
        m602visitAnonymousObject(firAnonymousObject, obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitConstructor(FirConstructor firConstructor, Object obj) {
        m605visitConstructor(firConstructor, obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitField(FirField firField, Object obj) {
        m607visitField(firField, obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitNamedFunction(FirNamedFunction firNamedFunction, Object obj) {
        m608visitNamedFunction(firNamedFunction, obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitProperty(FirProperty firProperty, Object obj) {
        m609visitProperty(firProperty, obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitRegularClass(FirRegularClass firRegularClass, Object obj) {
        m610visitRegularClass(firRegularClass, obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitTypeAlias(FirTypeAlias firTypeAlias, Object obj) {
        m611visitTypeAlias(firTypeAlias, obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitCallableDeclaration(FirCallableDeclaration firCallableDeclaration, Object obj) {
        m603visitCallableDeclaration(firCallableDeclaration, obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitClass(FirClass firClass, Object obj) {
        m604visitClass(firClass, obj);
        return Unit.INSTANCE;
    }
}
