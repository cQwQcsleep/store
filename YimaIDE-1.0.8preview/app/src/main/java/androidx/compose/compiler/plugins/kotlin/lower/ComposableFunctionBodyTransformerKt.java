package androidx.compose.compiler.plugins.kotlin.lower;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext;
import org.jetbrains.kotlin.backend.jvm.ir.JvmIrTypeUtilsKt;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationParent;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationsKt;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.declarations.IrPackageFragment;
import org.jetbrains.kotlin.ir.declarations.IrParameterKind;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;
import org.jetbrains.kotlin.ir.expressions.IrContainerExpression;
import org.jetbrains.kotlin.ir.expressions.IrStatementOrigin;
import org.jetbrains.kotlin.ir.expressions.impl.BuildersKt;
import org.jetbrains.kotlin.ir.types.IrSimpleType;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.types.IrTypePredicatesKt;
import org.jetbrains.kotlin.ir.types.IrTypesKt;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.FqNameUnsafe;
import org.jetbrains.kotlin.name.SpecialNames;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000~\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0016\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001\u001a\u000e\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u0001\u001a\u000e\u0010\t\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u0001\u001a\u0016\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0001\u001a\u000e\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0001\u001a\u000e\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u0001\u001a\u0018\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00012\b\b\u0002\u0010\u0010\u001a\u00020\u0001\u001a\u001c\u0010\u0016\u001a\u00020\u0017*\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u0017H\u0002\u001a\f\u0010\u001a\u001a\u00020\u001b*\u00020\u000bH\u0002\u001ah\u0010\u001c\u001a\u00020\u001d\"\u0004\b\u0000\u0010\u001e\"\u0004\b\u0001\u0010\u001f\"\u0004\b\u0002\u0010 2\f\u0010!\u001a\b\u0012\u0004\u0012\u0002H\u001e0\"2\f\u0010#\u001a\b\u0012\u0004\u0012\u0002H\u001f0\"2\f\u0010$\u001a\b\u0012\u0004\u0012\u0002H 0\"2\u001e\u0010%\u001a\u001a\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u0002H\u001f\u0012\u0004\u0012\u0002H \u0012\u0004\u0012\u00020\u001d0&H\u0086\bø\u0001\u0000\u001a0\u0010'\u001a\u00020\u001d\"\u0004\b\u0000\u0010(*\b\u0012\u0004\u0012\u0002H(0\"2\u0012\u0010)\u001a\u000e\u0012\u0004\u0012\u0002H(\u0012\u0004\u0012\u00020\u001d0*H\u0086\bø\u0001\u0000\u001aE\u0010+\u001a\u00020\u001d\"\u0004\b\u0000\u0010(*\b\u0012\u0004\u0012\u0002H(0\"2'\u0010)\u001a#\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(\b\u0012\u0004\u0012\u0002H(\u0012\u0004\u0012\u00020\u001d0,H\u0086\bø\u0001\u0000\u001aL\u0010+\u001a\u00020\u001d\"\u0004\b\u0000\u0010(*\n\u0012\u0006\b\u0001\u0012\u0002H(0/2'\u0010)\u001a#\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(\b\u0012\u0004\u0012\u0002H(\u0012\u0004\u0012\u00020\u001d0,H\u0086\bø\u0001\u0000¢\u0006\u0002\u00100\u001a%\u00101\u001a\u00020\u001b*\u0002022\u0006\u00103\u001a\u0002042\n\b\u0002\u00105\u001a\u0004\u0018\u00010\u001bH\u0002¢\u0006\u0002\u00106\u001a\u0014\u00107\u001a\u00020\u001b*\u0002022\u0006\u00103\u001a\u000204H\u0002\u001a\u0014\u00108\u001a\u00020\u001b*\u0002022\u0006\u00103\u001a\u000204H\u0002\u001a\n\u00109\u001a\u00020\u001b*\u000202\u001a\n\u0010:\u001a\u00020\u001b*\u000202\u001a\u0010\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020>H\u0002\u001a\f\u0010?\u001a\u00020\u0017*\u00020\u000bH\u0002\u001a\f\u0010@\u001a\u00020\u0017*\u00020\u000bH\u0002\u001a\f\u0010A\u001a\u00020\u0017*\u00020\u000bH\u0002\u001a\u0018\u0010B\u001a\u00020\u001d*\u00060Cj\u0002`D2\u0006\u0010E\u001a\u00020FH\u0002\u001a\u000e\u0010G\u001a\u0004\u0018\u00010\u0017*\u00020\u000bH\u0002\u001a\f\u0010H\u001a\u00020\u0001*\u00020\u000bH\u0002\u001a\f\u0010I\u001a\u00020\u0017*\u00020\u000bH\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u0015\u0010\n\u001a\u00020\u0001*\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\r\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006J"}, d2 = {"BITS_PER_INT", "", "SLOTS_PER_INT", "BITS_PER_SLOT", "bitsForSlot", "bits", "slot", "defaultsParamIndex", "index", "defaultsBitIndex", "thisParamCount", "Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "getThisParamCount", "(Lorg/jetbrains/kotlin/ir/declarations/IrFunction;)I", "changedParamCount", "realValueParams", "thisParams", "changedParamCountFromTotal", "totalParamsIncludingThisParams", "defaultParamCount", "valueParams", "composeSyntheticParamCount", "replacePrefix", "", "prefix", "replacement", "isLambda", "", "forEachWith", "", "A", "B", "C", "a", "", "b", "c", "fn", "Lkotlin/Function3;", "fastForEach", "T", "action", "Lkotlin/Function1;", "fastForEachIndexed", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "isClassType", "Lorg/jetbrains/kotlin/ir/types/IrType;", "fqName", "Lorg/jetbrains/kotlin/name/FqNameUnsafe;", "hasQuestionMark", "(Lorg/jetbrains/kotlin/ir/types/IrType;Lorg/jetbrains/kotlin/name/FqNameUnsafe;Ljava/lang/Boolean;)Z", "isNotNullClassType", "isNullableClassType", "isNullableUnit", "isUnitOrNullableUnit", "mutableStatementContainer", "Lorg/jetbrains/kotlin/ir/expressions/IrContainerExpression;", "context", "Lorg/jetbrains/kotlin/backend/common/extensions/IrPluginContext;", "callInformation", "parameterInformation", "parameterNameInformation", "appendParameterType", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "parameter", "Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;", "packageName", "packageHash", "sourceFileInformation", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class ComposableFunctionBodyTransformerKt {
    public static final int BITS_PER_INT = 31;
    public static final int BITS_PER_SLOT = 3;
    public static final int SLOTS_PER_INT = 10;

    private static final void appendParameterType(StringBuilder sb, IrValueParameter irValueParameter) {
        FqName fqNameWhenAvailable;
        IrClass irClass = IrTypesKt.getClass(irValueParameter.getType());
        if (irClass == null || (fqNameWhenAvailable = IrUtilsKt.getFqNameWhenAvailable(irClass)) == null) {
            return;
        }
        sb.append(':');
        sb.append(replacePrefix(fqNameWhenAvailable.asString(), "androidx.compose.", "c#"));
    }

    public static final int bitsForSlot(int i, int i2) {
        return i << (((i2 % 10) * 3) + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String callInformation(IrFunction irFunction) {
        StringBuilder sb = new StringBuilder("C");
        if (irFunction.isInline()) {
            sb.append('C');
        }
        if (!isLambda(irFunction)) {
            sb.append('(');
            sb.append(irFunction.getName().asString());
            sb.append(')');
        }
        return sb.toString();
    }

    public static final int changedParamCount(int i, int i2) {
        int i3 = i + i2;
        if (i3 == 0) {
            return 1;
        }
        return (int) Math.ceil(((double) i3) / 10.0d);
    }

    public static final int changedParamCountFromTotal(int i) {
        int i2 = i - 2;
        int i3 = 0;
        do {
            i2 -= 10;
            i3++;
        } while (i2 > 0);
        return i3;
    }

    public static final int composeSyntheticParamCount(int i, int i2) {
        return changedParamCount(i, i2) + 1;
    }

    public static /* synthetic */ int composeSyntheticParamCount$default(int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        return composeSyntheticParamCount(i, i2);
    }

    public static final int defaultParamCount(int i) {
        return (int) Math.ceil(((double) i) / 31.0d);
    }

    public static final int defaultsBitIndex(int i) {
        return i % 31;
    }

    public static final int defaultsParamIndex(int i) {
        return i / 31;
    }

    public static final <T> void fastForEach(List<? extends T> list, Function1<? super T, Unit> function1) {
        list.getClass();
        function1.getClass();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            function1.invoke(list.get(i));
        }
    }

    public static final <T> void fastForEachIndexed(List<? extends T> list, Function2<? super Integer, ? super T, Unit> function2) {
        list.getClass();
        function2.getClass();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            function2.invoke(Integer.valueOf(i), list.get(i));
        }
    }

    public static final <A, B, C> void forEachWith(List<? extends A> list, List<? extends B> list2, List<? extends C> list3, Function3<? super A, ? super B, ? super C, Unit> function3) {
        list.getClass();
        list2.getClass();
        list3.getClass();
        function3.getClass();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            function3.invoke(list.get(i), list2.get(i), list3.get(i));
        }
    }

    public static final int getThisParamCount(IrFunction irFunction) {
        irFunction.getClass();
        List<IrValueParameter> parameters = irFunction.getParameters();
        int i = 0;
        if ((parameters instanceof Collection) && parameters.isEmpty()) {
            return 0;
        }
        for (IrValueParameter irValueParameter : parameters) {
            if (irValueParameter.getKind() == IrParameterKind.DispatchReceiver || irValueParameter.getKind() == IrParameterKind.Context || irValueParameter.getKind() == IrParameterKind.ExtensionReceiver) {
                i++;
                if (i < 0) {
                    CollectionsKt.throwCountOverflow();
                }
            }
        }
        return i;
    }

    private static final boolean isClassType(IrType irType, FqNameUnsafe fqNameUnsafe, Boolean bool) {
        if (!(irType instanceof IrSimpleType)) {
            return false;
        }
        if (bool == null || !Intrinsics.areEqual(Boolean.valueOf(IrTypePredicatesKt.isMarkedNullable((IrSimpleType) irType)), bool)) {
            return IrTypePredicatesKt.isClassWithFqName(((IrSimpleType) irType).getClassifier(), fqNameUnsafe);
        }
        return false;
    }

    public static /* synthetic */ boolean isClassType$default(IrType irType, FqNameUnsafe fqNameUnsafe, Boolean bool, int i, Object obj) {
        if ((i & 2) != 0) {
            bool = null;
        }
        return isClassType(irType, fqNameUnsafe, bool);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isLambda(IrFunction irFunction) {
        return Intrinsics.areEqual(irFunction.getName(), SpecialNames.ANONYMOUS);
    }

    private static final boolean isNotNullClassType(IrType irType, FqNameUnsafe fqNameUnsafe) {
        return isClassType(irType, fqNameUnsafe, Boolean.FALSE);
    }

    private static final boolean isNullableClassType(IrType irType, FqNameUnsafe fqNameUnsafe) {
        return isClassType(irType, fqNameUnsafe, Boolean.TRUE);
    }

    public static final boolean isNullableUnit(IrType irType) {
        irType.getClass();
        return isNullableClassType(irType, StandardNames.FqNames.unit);
    }

    public static final boolean isUnitOrNullableUnit(IrType irType) {
        irType.getClass();
        return IrTypePredicatesKt.isUnit(irType) || isNullableUnit(irType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IrContainerExpression mutableStatementContainer(IrPluginContext irPluginContext) {
        return BuildersKt.IrCompositeImpl$default(-1, -1, irPluginContext.getIrBuiltIns().getUnitType(), (IrStatementOrigin) null, 8, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int packageHash(IrFunction irFunction) {
        String strPackageName = packageName(irFunction);
        if (strPackageName == null) {
            return 0;
        }
        int iCharAt = 0;
        for (int i = 0; i < strPackageName.length(); i++) {
            iCharAt = (iCharAt * 31) + strPackageName.charAt(i);
        }
        return Math.abs(iCharAt);
    }

    private static final String packageName(IrFunction irFunction) {
        IrDeclarationParent parent = irFunction.getParent();
        while (!(parent instanceof IrPackageFragment)) {
            if (!(parent instanceof IrDeclaration)) {
                return null;
            }
            parent = ((IrDeclaration) parent).getParent();
        }
        return ((IrPackageFragment) parent).getPackageFqName().asString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String parameterInformation(IrFunction irFunction) {
        StringBuilder sb = new StringBuilder("P(");
        List<IrValueParameter> namedParameters = AbstractComposeLoweringKt.getNamedParameters(irFunction);
        ArrayList arrayList = new ArrayList();
        for (Object obj : namedParameters) {
            String strAsString = ((IrValueParameter) obj).getName().asString();
            strAsString.getClass();
            if (!StringsKt.startsWith$default(strAsString, "$", false, 2, (Object) null)) {
                arrayList.add(obj);
            }
        }
        int size = arrayList.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = i;
        }
        List mutableList = ArraysKt.toMutableList(iArr);
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        int i2 = 0;
        for (Object obj2 : arrayList) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            arrayList2.add(new Pair(Integer.valueOf(i2), (IrValueParameter) obj2));
            i2 = i3;
        }
        int i4 = 0;
        for (Object obj3 : CollectionsKt.sortedWith(arrayList2, new Comparator() { // from class: androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformerKt$parameterInformation$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(((IrValueParameter) ((Pair) t).getSecond()).getName(), ((IrValueParameter) ((Pair) t2).getSecond()).getName());
            }
        })) {
            int i5 = i4 + 1;
            if (i4 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            iArr[((Number) ((Pair) obj3).component1()).intValue()] = i4;
            i4 = i5;
        }
        Ref.IntRef intRef = new Ref.IntRef();
        int size2 = arrayList.size();
        boolean z = false;
        for (int i6 = 0; i6 < size2; i6++) {
            IrValueParameter irValueParameter = (IrValueParameter) arrayList.get(i6);
            if (((Number) CollectionsKt.first(mutableList)).intValue() != iArr[i6] || JvmIrTypeUtilsKt.isInlineClassType(irValueParameter.getType())) {
                parameterInformation$emitRun(intRef, sb, arrayList, i6);
                if (i6 > 0) {
                    sb.append(',');
                }
                int i7 = iArr[i6];
                sb.append(i7);
                mutableList.remove(Integer.valueOf(i7));
                if (JvmIrTypeUtilsKt.isInlineClassType(irValueParameter.getType())) {
                    appendParameterType(sb, irValueParameter);
                }
                z = true;
            } else {
                intRef.element++;
                mutableList.remove(0);
            }
        }
        sb.append(')');
        return z ? sb.toString() : "";
    }

    private static final void parameterInformation$emitRun(Ref.IntRef intRef, StringBuilder sb, List<? extends IrValueParameter> list, int i) {
        if (intRef.element > 0) {
            sb.append('!');
            if (i < list.size() - 1) {
                sb.append(intRef.element);
            }
            intRef.element = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String parameterNameInformation(IrFunction irFunction) {
        int i;
        List<IrValueParameter> namedParameters = AbstractComposeLoweringKt.getNamedParameters(irFunction);
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = namedParameters.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            String strAsString = ((IrValueParameter) next).getName().asString();
            strAsString.getClass();
            if (!StringsKt.startsWith$default(strAsString, "$", false, 2, (Object) null)) {
                arrayList.add(next);
            }
        }
        if (arrayList.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("N(");
        int size = arrayList.size();
        for (i = 0; i < size; i++) {
            if (i > 0) {
                sb.append(',');
            }
            IrValueParameter irValueParameter = (IrValueParameter) arrayList.get(i);
            sb.append(irValueParameter.getName().asString());
            if (JvmIrTypeUtilsKt.isInlineClassType(irValueParameter.getType())) {
                appendParameterType(sb, irValueParameter);
            }
        }
        sb.append(')');
        return sb.toString();
    }

    private static final String replacePrefix(String str, String str2, String str3) {
        if (!StringsKt.startsWith$default(str, str2, false, 2, (Object) null)) {
            return str;
        }
        return str3 + str.substring(str2.length());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String sourceFileInformation(IrFunction irFunction) {
        int iPackageHash = packageHash(irFunction);
        if (iPackageHash == 0) {
            return IrDeclarationsKt.getName(IrUtilsKt.getFile(irFunction));
        }
        StringBuilder sb = new StringBuilder();
        sb.append(IrDeclarationsKt.getName(IrUtilsKt.getFile(irFunction)));
        sb.append('#');
        String string = Integer.toString(iPackageHash, CharsKt.checkRadix(36));
        string.getClass();
        sb.append(string);
        return sb.toString();
    }

    public static final <T> void fastForEachIndexed(T[] tArr, Function2<? super Integer, ? super T, Unit> function2) {
        tArr.getClass();
        function2.getClass();
        int length = tArr.length;
        for (int i = 0; i < length; i++) {
            function2.invoke(Integer.valueOf(i), tArr[i]);
        }
    }
}
