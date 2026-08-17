package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousInitializer;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousObject;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirReplSnippet;
import org.jetbrains.kotlin.fir.declarations.FirScript;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000¨\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0016\u0010\u0003\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR!\u0010\u0003\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR$\u0010\r\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u000f0\u0005j\u0002`\u00100\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R$\u0010\u0013\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00140\u0005j\u0002`\u00150\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R$\u0010\u0017\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00180\u0005j\u0002`\u00190\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0012R$\u0010\u001b\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u001c0\u0005j\u0002`\u001d0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0012R$\u0010\u001f\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020 0\u0005j\u0002`!0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0012R$\u0010#\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020$0\u0005j\u0002`%0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0012R$\u0010'\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020(0\u0005j\u0002`)0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0012R$\u0010+\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020,0\u0005j\u0002`-0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u0012R$\u0010/\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002000\u0005j\u0002`10\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u0012R$\u00103\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002040\u0005j\u0002`50\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\u0012R$\u00107\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002080\u0005j\u0002`90\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b:\u0010\u0012R$\u0010;\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020<0\u0005j\u0002`=0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b>\u0010\u0012R$\u0010?\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020@0\u0005j\u0002`A0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bB\u0010\u0012R$\u0010C\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020D0\u0005j\u0002`E0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bF\u0010\u0012R$\u0010G\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020H0\u0005j\u0002`I0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010\u0012R$\u0010K\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020L0\u0005j\u0002`M0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bN\u0010\u0012R$\u0010O\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020P0\u0005j\u0002`Q0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bR\u0010\u0012R$\u0010S\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020T0\u0005j\u0002`U0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bV\u0010\u0012R$\u0010W\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020X0\u0005j\u0002`Y0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bZ\u0010\u0012R$\u0010[\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\\0\u0005j\u0002`]0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b^\u0010\u0012R$\u0010_\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020`0\u0005j\u0002`a0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bb\u0010\u0012R$\u0010c\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020d0\u0005j\u0002`e0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bf\u0010\u0012¨\u0006g"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FilteredDeclarationCheckers;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/DeclarationCheckers;", "delegate", "predicate", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/DeclarationCheckers;Lkotlin/jvm/functions/Function1;)V", "getDelegate", "()Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/DeclarationCheckers;", "getPredicate", "()Lkotlin/jvm/functions/Function1;", "basicDeclarationCheckers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBasicDeclarationChecker;", "getBasicDeclarationCheckers", "()Ljava/util/Set;", "callableDeclarationCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirCallableDeclarationChecker;", "getCallableDeclarationCheckers", "functionCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirFunctionChecker;", "getFunctionCheckers", "simpleFunctionCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirSimpleFunctionChecker;", "getSimpleFunctionCheckers", "propertyCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirPropertyChecker;", "getPropertyCheckers", "classLikeCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirClassLikeChecker;", "getClassLikeCheckers", "classCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirClassChecker;", "getClassCheckers", "regularClassCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirRegularClassChecker;", "getRegularClassCheckers", "constructorCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirConstructorChecker;", "getConstructorCheckers", "fileCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirFileChecker;", "getFileCheckers", "scriptCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirScript;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirScriptChecker;", "getScriptCheckers", "replSnippetCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirReplSnippet;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirReplSnippetChecker;", "getReplSnippetCheckers", "typeParameterCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirTypeParameterChecker;", "getTypeParameterCheckers", "typeAliasCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirTypeAliasChecker;", "getTypeAliasCheckers", "anonymousFunctionCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirAnonymousFunctionChecker;", "getAnonymousFunctionCheckers", "propertyAccessorCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirPropertyAccessorChecker;", "getPropertyAccessorCheckers", "backingFieldCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirBackingField;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBackingFieldChecker;", "getBackingFieldCheckers", "valueParameterCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirValueParameterChecker;", "getValueParameterCheckers", "enumEntryCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirEnumEntryChecker;", "getEnumEntryCheckers", "anonymousObjectCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousObject;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirAnonymousObjectChecker;", "getAnonymousObjectCheckers", "anonymousInitializerCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousInitializer;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirAnonymousInitializerChecker;", "getAnonymousInitializerCheckers", "receiverParameterCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirReceiverParameterChecker;", "getReceiverParameterCheckers", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FilteredDeclarationCheckers extends DeclarationCheckers {
    private final Set<FirDeclarationChecker<FirAnonymousFunction>> anonymousFunctionCheckers;
    private final Set<FirDeclarationChecker<FirAnonymousInitializer>> anonymousInitializerCheckers;
    private final Set<FirDeclarationChecker<FirAnonymousObject>> anonymousObjectCheckers;
    private final Set<FirDeclarationChecker<FirBackingField>> backingFieldCheckers;
    private final Set<FirDeclarationChecker<FirDeclaration>> basicDeclarationCheckers;
    private final Set<FirDeclarationChecker<FirCallableDeclaration>> callableDeclarationCheckers;
    private final Set<FirDeclarationChecker<FirClass>> classCheckers;
    private final Set<FirDeclarationChecker<FirClassLikeDeclaration>> classLikeCheckers;
    private final Set<FirDeclarationChecker<FirConstructor>> constructorCheckers;
    private final DeclarationCheckers delegate;
    private final Set<FirDeclarationChecker<FirEnumEntry>> enumEntryCheckers;
    private final Set<FirDeclarationChecker<FirFile>> fileCheckers;
    private final Set<FirDeclarationChecker<FirFunction>> functionCheckers;
    private final Function1<FirDeclarationChecker<?>, Boolean> predicate;
    private final Set<FirDeclarationChecker<FirPropertyAccessor>> propertyAccessorCheckers;
    private final Set<FirDeclarationChecker<FirProperty>> propertyCheckers;
    private final Set<FirDeclarationChecker<FirReceiverParameter>> receiverParameterCheckers;
    private final Set<FirDeclarationChecker<FirRegularClass>> regularClassCheckers;
    private final Set<FirDeclarationChecker<FirReplSnippet>> replSnippetCheckers;
    private final Set<FirDeclarationChecker<FirScript>> scriptCheckers;
    private final Set<FirDeclarationChecker<FirNamedFunction>> simpleFunctionCheckers;
    private final Set<FirDeclarationChecker<FirTypeAlias>> typeAliasCheckers;
    private final Set<FirDeclarationChecker<FirTypeParameter>> typeParameterCheckers;
    private final Set<FirDeclarationChecker<FirValueParameter>> valueParameterCheckers;

    /* JADX WARN: Multi-variable type inference failed */
    public FilteredDeclarationCheckers(DeclarationCheckers declarationCheckers, Function1<? super FirDeclarationChecker<?>, Boolean> function1) {
        declarationCheckers.getClass();
        function1.getClass();
        this.delegate = declarationCheckers;
        this.predicate = function1;
        Set<FirDeclarationChecker<FirDeclaration>> basicDeclarationCheckers = declarationCheckers.getBasicDeclarationCheckers();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Object obj : basicDeclarationCheckers) {
            if (((Boolean) function1.invoke(obj)).booleanValue()) {
                linkedHashSet.add(obj);
            }
        }
        this.basicDeclarationCheckers = linkedHashSet;
        Set<FirDeclarationChecker<FirCallableDeclaration>> callableDeclarationCheckers = this.delegate.getCallableDeclarationCheckers();
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        Function1<FirDeclarationChecker<?>, Boolean> function2 = this.predicate;
        for (Object obj2 : callableDeclarationCheckers) {
            if (((Boolean) function2.invoke(obj2)).booleanValue()) {
                linkedHashSet2.add(obj2);
            }
        }
        this.callableDeclarationCheckers = linkedHashSet2;
        Set<FirDeclarationChecker<FirFunction>> functionCheckers = this.delegate.getFunctionCheckers();
        LinkedHashSet linkedHashSet3 = new LinkedHashSet();
        Function1<FirDeclarationChecker<?>, Boolean> function3 = this.predicate;
        for (Object obj3 : functionCheckers) {
            if (((Boolean) function3.invoke(obj3)).booleanValue()) {
                linkedHashSet3.add(obj3);
            }
        }
        this.functionCheckers = linkedHashSet3;
        Set<FirDeclarationChecker<FirNamedFunction>> simpleFunctionCheckers = this.delegate.getSimpleFunctionCheckers();
        LinkedHashSet linkedHashSet4 = new LinkedHashSet();
        Function1<FirDeclarationChecker<?>, Boolean> function4 = this.predicate;
        for (Object obj4 : simpleFunctionCheckers) {
            if (((Boolean) function4.invoke(obj4)).booleanValue()) {
                linkedHashSet4.add(obj4);
            }
        }
        this.simpleFunctionCheckers = linkedHashSet4;
        Set<FirDeclarationChecker<FirProperty>> propertyCheckers = this.delegate.getPropertyCheckers();
        LinkedHashSet linkedHashSet5 = new LinkedHashSet();
        Function1<FirDeclarationChecker<?>, Boolean> function5 = this.predicate;
        for (Object obj5 : propertyCheckers) {
            if (((Boolean) function5.invoke(obj5)).booleanValue()) {
                linkedHashSet5.add(obj5);
            }
        }
        this.propertyCheckers = linkedHashSet5;
        Set<FirDeclarationChecker<FirClassLikeDeclaration>> classLikeCheckers = this.delegate.getClassLikeCheckers();
        LinkedHashSet linkedHashSet6 = new LinkedHashSet();
        Function1<FirDeclarationChecker<?>, Boolean> function6 = this.predicate;
        for (Object obj6 : classLikeCheckers) {
            if (((Boolean) function6.invoke(obj6)).booleanValue()) {
                linkedHashSet6.add(obj6);
            }
        }
        this.classLikeCheckers = linkedHashSet6;
        Set<FirDeclarationChecker<FirClass>> classCheckers = this.delegate.getClassCheckers();
        LinkedHashSet linkedHashSet7 = new LinkedHashSet();
        Function1<FirDeclarationChecker<?>, Boolean> function7 = this.predicate;
        for (Object obj7 : classCheckers) {
            if (((Boolean) function7.invoke(obj7)).booleanValue()) {
                linkedHashSet7.add(obj7);
            }
        }
        this.classCheckers = linkedHashSet7;
        Set<FirDeclarationChecker<FirRegularClass>> regularClassCheckers = this.delegate.getRegularClassCheckers();
        LinkedHashSet linkedHashSet8 = new LinkedHashSet();
        Function1<FirDeclarationChecker<?>, Boolean> function8 = this.predicate;
        for (Object obj8 : regularClassCheckers) {
            if (((Boolean) function8.invoke(obj8)).booleanValue()) {
                linkedHashSet8.add(obj8);
            }
        }
        this.regularClassCheckers = linkedHashSet8;
        Set<FirDeclarationChecker<FirConstructor>> constructorCheckers = this.delegate.getConstructorCheckers();
        LinkedHashSet linkedHashSet9 = new LinkedHashSet();
        Function1<FirDeclarationChecker<?>, Boolean> function9 = this.predicate;
        for (Object obj9 : constructorCheckers) {
            if (((Boolean) function9.invoke(obj9)).booleanValue()) {
                linkedHashSet9.add(obj9);
            }
        }
        this.constructorCheckers = linkedHashSet9;
        Set<FirDeclarationChecker<FirFile>> fileCheckers = this.delegate.getFileCheckers();
        LinkedHashSet linkedHashSet10 = new LinkedHashSet();
        Function1<FirDeclarationChecker<?>, Boolean> function10 = this.predicate;
        for (Object obj10 : fileCheckers) {
            if (((Boolean) function10.invoke(obj10)).booleanValue()) {
                linkedHashSet10.add(obj10);
            }
        }
        this.fileCheckers = linkedHashSet10;
        Set<FirDeclarationChecker<FirScript>> scriptCheckers = this.delegate.getScriptCheckers();
        LinkedHashSet linkedHashSet11 = new LinkedHashSet();
        Function1<FirDeclarationChecker<?>, Boolean> function11 = this.predicate;
        for (Object obj11 : scriptCheckers) {
            if (((Boolean) function11.invoke(obj11)).booleanValue()) {
                linkedHashSet11.add(obj11);
            }
        }
        this.scriptCheckers = linkedHashSet11;
        Set<FirDeclarationChecker<FirReplSnippet>> replSnippetCheckers = this.delegate.getReplSnippetCheckers();
        LinkedHashSet linkedHashSet12 = new LinkedHashSet();
        Function1<FirDeclarationChecker<?>, Boolean> function12 = this.predicate;
        for (Object obj12 : replSnippetCheckers) {
            if (((Boolean) function12.invoke(obj12)).booleanValue()) {
                linkedHashSet12.add(obj12);
            }
        }
        this.replSnippetCheckers = linkedHashSet12;
        Set<FirDeclarationChecker<FirTypeParameter>> typeParameterCheckers = this.delegate.getTypeParameterCheckers();
        LinkedHashSet linkedHashSet13 = new LinkedHashSet();
        Function1<FirDeclarationChecker<?>, Boolean> function13 = this.predicate;
        for (Object obj13 : typeParameterCheckers) {
            if (((Boolean) function13.invoke(obj13)).booleanValue()) {
                linkedHashSet13.add(obj13);
            }
        }
        this.typeParameterCheckers = linkedHashSet13;
        Set<FirDeclarationChecker<FirTypeAlias>> typeAliasCheckers = this.delegate.getTypeAliasCheckers();
        LinkedHashSet linkedHashSet14 = new LinkedHashSet();
        Function1<FirDeclarationChecker<?>, Boolean> function14 = this.predicate;
        for (Object obj14 : typeAliasCheckers) {
            if (((Boolean) function14.invoke(obj14)).booleanValue()) {
                linkedHashSet14.add(obj14);
            }
        }
        this.typeAliasCheckers = linkedHashSet14;
        Set<FirDeclarationChecker<FirAnonymousFunction>> anonymousFunctionCheckers = this.delegate.getAnonymousFunctionCheckers();
        LinkedHashSet linkedHashSet15 = new LinkedHashSet();
        Function1<FirDeclarationChecker<?>, Boolean> function15 = this.predicate;
        for (Object obj15 : anonymousFunctionCheckers) {
            if (((Boolean) function15.invoke(obj15)).booleanValue()) {
                linkedHashSet15.add(obj15);
            }
        }
        this.anonymousFunctionCheckers = linkedHashSet15;
        Set<FirDeclarationChecker<FirPropertyAccessor>> propertyAccessorCheckers = this.delegate.getPropertyAccessorCheckers();
        LinkedHashSet linkedHashSet16 = new LinkedHashSet();
        Function1<FirDeclarationChecker<?>, Boolean> function16 = this.predicate;
        for (Object obj16 : propertyAccessorCheckers) {
            if (((Boolean) function16.invoke(obj16)).booleanValue()) {
                linkedHashSet16.add(obj16);
            }
        }
        this.propertyAccessorCheckers = linkedHashSet16;
        Set<FirDeclarationChecker<FirBackingField>> backingFieldCheckers = this.delegate.getBackingFieldCheckers();
        LinkedHashSet linkedHashSet17 = new LinkedHashSet();
        Function1<FirDeclarationChecker<?>, Boolean> function17 = this.predicate;
        for (Object obj17 : backingFieldCheckers) {
            if (((Boolean) function17.invoke(obj17)).booleanValue()) {
                linkedHashSet17.add(obj17);
            }
        }
        this.backingFieldCheckers = linkedHashSet17;
        Set<FirDeclarationChecker<FirValueParameter>> valueParameterCheckers = this.delegate.getValueParameterCheckers();
        LinkedHashSet linkedHashSet18 = new LinkedHashSet();
        Function1<FirDeclarationChecker<?>, Boolean> function18 = this.predicate;
        for (Object obj18 : valueParameterCheckers) {
            if (((Boolean) function18.invoke(obj18)).booleanValue()) {
                linkedHashSet18.add(obj18);
            }
        }
        this.valueParameterCheckers = linkedHashSet18;
        Set<FirDeclarationChecker<FirEnumEntry>> enumEntryCheckers = this.delegate.getEnumEntryCheckers();
        LinkedHashSet linkedHashSet19 = new LinkedHashSet();
        Function1<FirDeclarationChecker<?>, Boolean> function19 = this.predicate;
        for (Object obj19 : enumEntryCheckers) {
            if (((Boolean) function19.invoke(obj19)).booleanValue()) {
                linkedHashSet19.add(obj19);
            }
        }
        this.enumEntryCheckers = linkedHashSet19;
        Set<FirDeclarationChecker<FirAnonymousObject>> anonymousObjectCheckers = this.delegate.getAnonymousObjectCheckers();
        LinkedHashSet linkedHashSet20 = new LinkedHashSet();
        Function1<FirDeclarationChecker<?>, Boolean> function20 = this.predicate;
        for (Object obj20 : anonymousObjectCheckers) {
            if (((Boolean) function20.invoke(obj20)).booleanValue()) {
                linkedHashSet20.add(obj20);
            }
        }
        this.anonymousObjectCheckers = linkedHashSet20;
        Set<FirDeclarationChecker<FirAnonymousInitializer>> anonymousInitializerCheckers = this.delegate.getAnonymousInitializerCheckers();
        LinkedHashSet linkedHashSet21 = new LinkedHashSet();
        Function1<FirDeclarationChecker<?>, Boolean> function21 = this.predicate;
        for (Object obj21 : anonymousInitializerCheckers) {
            if (((Boolean) function21.invoke(obj21)).booleanValue()) {
                linkedHashSet21.add(obj21);
            }
        }
        this.anonymousInitializerCheckers = linkedHashSet21;
        Set<FirDeclarationChecker<FirReceiverParameter>> receiverParameterCheckers = this.delegate.getReceiverParameterCheckers();
        LinkedHashSet linkedHashSet22 = new LinkedHashSet();
        Function1<FirDeclarationChecker<?>, Boolean> function22 = this.predicate;
        for (Object obj22 : receiverParameterCheckers) {
            if (((Boolean) function22.invoke(obj22)).booleanValue()) {
                linkedHashSet22.add(obj22);
            }
        }
        this.receiverParameterCheckers = linkedHashSet22;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirAnonymousFunction>> getAnonymousFunctionCheckers() {
        return this.anonymousFunctionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirAnonymousInitializer>> getAnonymousInitializerCheckers() {
        return this.anonymousInitializerCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirAnonymousObject>> getAnonymousObjectCheckers() {
        return this.anonymousObjectCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirBackingField>> getBackingFieldCheckers() {
        return this.backingFieldCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirDeclaration>> getBasicDeclarationCheckers() {
        return this.basicDeclarationCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirCallableDeclaration>> getCallableDeclarationCheckers() {
        return this.callableDeclarationCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirClass>> getClassCheckers() {
        return this.classCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirClassLikeDeclaration>> getClassLikeCheckers() {
        return this.classLikeCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirConstructor>> getConstructorCheckers() {
        return this.constructorCheckers;
    }

    public final DeclarationCheckers getDelegate() {
        return this.delegate;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirEnumEntry>> getEnumEntryCheckers() {
        return this.enumEntryCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirFile>> getFileCheckers() {
        return this.fileCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirFunction>> getFunctionCheckers() {
        return this.functionCheckers;
    }

    public final Function1<FirDeclarationChecker<?>, Boolean> getPredicate() {
        return this.predicate;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirPropertyAccessor>> getPropertyAccessorCheckers() {
        return this.propertyAccessorCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirProperty>> getPropertyCheckers() {
        return this.propertyCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirReceiverParameter>> getReceiverParameterCheckers() {
        return this.receiverParameterCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirRegularClass>> getRegularClassCheckers() {
        return this.regularClassCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirReplSnippet>> getReplSnippetCheckers() {
        return this.replSnippetCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirScript>> getScriptCheckers() {
        return this.scriptCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirNamedFunction>> getSimpleFunctionCheckers() {
        return this.simpleFunctionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirTypeAlias>> getTypeAliasCheckers() {
        return this.typeAliasCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirTypeParameter>> getTypeParameterCheckers() {
        return this.typeParameterCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirValueParameter>> getValueParameterCheckers() {
        return this.valueParameterCheckers;
    }
}
