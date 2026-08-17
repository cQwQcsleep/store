package org.jetbrains.kotlin.asJava.elements;

import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.JavaResolveResult;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaCodeReferenceElement;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceParameterList;
import com.intellij.psi.PsiType;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.SearchScope;
import javax.swing.Icon;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000ø\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\f\n\u0000\n\u0002\u0010\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0019\n\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B+\u0012\u0006\u0010\u0004\u001a\u00020\u0001\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\n\u0010\u000f\u001a\u0004\u0018\u00010\u0001H\u0016J\u0013\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0016¢\u0006\u0002\u0010\u0013J\n\u0010\u0014\u001a\u0004\u0018\u00010\bH\u0016J\b\u0010\u0015\u001a\u00020\u000eH\u0016J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u001b\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\f0\u00112\u0006\u0010\r\u001a\u00020\u000eH\u0016¢\u0006\u0002\u0010\u001bJ\n\u0010\u001c\u001a\u0004\u0018\u00010\bH\u0016J\n\u0010\u001d\u001a\u0004\u0018\u00010\u0001H\u0016J\n\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J!\u0010 \u001a\u00020\u00172\u0016\b\u0001\u0010!\u001a\f0\"¢\u0006\u0002\b#¢\u0006\u0002\b$:\u0002\b#H\u0096\u0001J!\u0010%\u001a\u00020\u00172\u0016\b\u0001\u0010!\u001a\f0\"¢\u0006\u0002\b#¢\u0006\u0002\b$:\u0002\b#H\u0096\u0001J)\u0010&\u001a\n '*\u0004\u0018\u00010\u00010\u00012\u0016\b\u0001\u0010!\u001a\f0\u0001¢\u0006\u0002\b#¢\u0006\u0002\b$:\u0002\b#H\u0096\u0001JC\u0010(\u001a\n '*\u0004\u0018\u00010\u00010\u00012\u0016\b\u0001\u0010!\u001a\f0\u0001¢\u0006\u0002\b#¢\u0006\u0002\b$:\u0002\b#2\u0018\b\u0001\u0010)\u001a\u000e\u0018\u00010\u0001¢\u0006\u0002\b*¢\u0006\u0002\b$:\u0002\b*H\u0096\u0001JC\u0010+\u001a\n '*\u0004\u0018\u00010\u00010\u00012\u0016\b\u0001\u0010!\u001a\f0\u0001¢\u0006\u0002\b#¢\u0006\u0002\b$:\u0002\b#2\u0018\b\u0001\u0010)\u001a\u000e\u0018\u00010\u0001¢\u0006\u0002\b*¢\u0006\u0002\b$:\u0002\b*H\u0096\u0001J1\u0010,\u001a\n '*\u0004\u0018\u00010\u00010\u00012\u000e\u0010!\u001a\n '*\u0004\u0018\u00010\u00010\u00012\u000e\u0010)\u001a\n '*\u0004\u0018\u00010\u00010\u0001H\u0096\u0001JA\u0010-\u001a\n '*\u0004\u0018\u00010\u00010\u00012\u000e\u0010!\u001a\n '*\u0004\u0018\u00010\u00010\u00012\u000e\u0010)\u001a\n '*\u0004\u0018\u00010\u00010\u00012\u000e\u0010.\u001a\n '*\u0004\u0018\u00010\u00010\u0001H\u0096\u0001JQ\u0010/\u001a\n '*\u0004\u0018\u00010\u00010\u00012\u0016\b\u0001\u0010!\u001a\f0\u0001¢\u0006\u0002\b#¢\u0006\u0002\b$:\u0002\b#2\u0016\b\u0001\u0010)\u001a\f0\u0001¢\u0006\u0002\b#¢\u0006\u0002\b$:\u0002\b#2\u000e\u0010.\u001a\n '*\u0004\u0018\u00010\u00010\u0001H\u0096\u0001J)\u00100\u001a\n '*\u0004\u0018\u00010\u00010\u00012\u0016\b\u0001\u0010!\u001a\f0\u0001¢\u0006\u0002\b#¢\u0006\u0002\b$:\u0002\b#H\u0096\u0001J/\u00101\u001a\u00020\u00172\u0016\b\u0001\u0010!\u001a\f0\u0001¢\u0006\u0002\b#¢\u0006\u0002\b$:\u0002\b#H\u0097\u0001b\f\b2\u0012\b\b3\u0012\u0004\b\b(4J\u0017\u00105\u001a\u00020\u0017H\u0097\u0001b\f\b2\u0012\b\b3\u0012\u0004\b\b(4J\u0011\u00106\u001a\n '*\u0004\u0018\u00010\u00010\u0001H\u0096\u0001J\t\u00107\u001a\u00020\u0017H\u0096\u0001J)\u00108\u001a\u00020\u00172\u000e\u0010!\u001a\n '*\u0004\u0018\u00010\u00010\u00012\u000e\u0010)\u001a\n '*\u0004\u0018\u00010\u00010\u0001H\u0096\u0001J/\u00109\u001a\u000e\u0018\u00010\u0001¢\u0006\u0002\b*¢\u0006\u0002\b$2\u0006\u0010!\u001a\u00020:H\u0097\u0001b\u0002\b*b\f\b;\u0012\b\b<\u0012\u0004\b\u0007\u0010\u0002J/\u0010=\u001a\u000e\u0018\u00010\u0002¢\u0006\u0002\b*¢\u0006\u0002\b$2\u0006\u0010!\u001a\u00020:H\u0097\u0001b\u0002\b*b\f\b;\u0012\b\b<\u0012\u0004\b\u0007\u0010\u0002J \u0010>\u001a\u00110\b¢\u0006\u0002\b#¢\u0006\u0002\b?¢\u0006\u0002\b$H\u0097\u0001b\u0002\b#b\u0002\b?J\\\u0010@\u001a>\u0012\u000e\u0012\f0\u0001¢\u0006\u0002\b#¢\u0006\u0002\b$ '*\u001e\u0012\u0010\b\u0001\u0012\f0\u0001¢\u0006\u0002\b#¢\u0006\u0002\b$0\u0011¢\u0006\u0002\b#¢\u0006\u0002\b$0\u0011¢\u0006\u0002\b#¢\u0006\u0002\b$H\u0097\u0001b\f\b;\u0012\b\b<\u0012\u0004\b\u0007\u0010\u0002b\u0002\b#¢\u0006\u0002\u0010AJ\u001f\u0010B\u001a\n '*\u0004\u0018\u00010C0CH\u0097\u0001b\f\b;\u0012\b\b<\u0012\u0004\b\u0007\u0010\u0002J'\u0010D\u001a\u000e\u0018\u00010\u0001¢\u0006\u0002\b*¢\u0006\u0002\b$H\u0097\u0001b\u0002\b*b\f\b;\u0012\b\b<\u0012\u0004\b\u0007\u0010\u0002Jd\u0010E\u001a\u000e\u0018\u0001HF¢\u0006\u0002\b*¢\u0006\u0002\b$\"\u0010\b\u0000\u0010F*\n '*\u0004\u0018\u00010G0G2$\b\u0001\u0010!\u001a\u001a\u0012\f\u0012\n '*\u0004\u0018\u0001HFHF0H¢\u0006\u0002\b#¢\u0006\u0002\b$:\u0002\b#H\u0097\u0001b\u0002\b*b\f\b;\u0012\b\b<\u0012\u0004\b\u0007\u0010\u0002¢\u0006\u0002\u0010IJ\u0017\u0010J\u001a\f0\u0001¢\u0006\u0002\b#¢\u0006\u0002\b$H\u0097\u0001b\u0002\b#J\u001f\u0010K\u001a\n '*\u0004\u0018\u00010\u00010\u0001H\u0097\u0001b\f\b;\u0012\b\b<\u0012\u0004\b\u0007\u0010\u0002J\u001f\u0010L\u001a\n '*\u0004\u0018\u00010M0M2\f\b\u0001\u0010!\u001a\u00020::\u0002\bNH\u0096\u0001J%\u0010O\u001a\f0P¢\u0006\u0002\b#¢\u0006\u0002\b$H\u0097\u0001b\u0002\b#b\f\b;\u0012\b\b<\u0012\u0004\b\u0007\u0010\u0002J\u001f\u0010Q\u001a\n '*\u0004\u0018\u00010\u00010\u0001H\u0097\u0001b\f\b;\u0012\b\b<\u0012\u0004\b\u0007\u0010\u0002J\u001f\u0010R\u001a\n '*\u0004\u0018\u00010S0SH\u0097\u0001b\f\b;\u0012\b\b<\u0012\u0004\b\u0007\u0010\u0002J\u001f\u0010T\u001a\n '*\u0004\u0018\u00010\u00010\u0001H\u0097\u0001b\f\b;\u0012\b\b<\u0012\u0004\b\u0007\u0010\u0002J\u001f\u0010U\u001a\n '*\u0004\u0018\u00010\u00010\u0001H\u0097\u0001b\f\b;\u0012\b\b<\u0012\u0004\b\u0007\u0010\u0002J\u001f\u0010V\u001a\n '*\u0004\u0018\u00010W0WH\u0097\u0001b\f\b;\u0012\b\b<\u0012\u0004\b\u0007\u0010\u0002J\u001f\u0010X\u001a\n '*\u0004\u0018\u00010\u00010\u0001H\u0097\u0001b\f\b;\u0012\b\b<\u0012\u0004\b\u0007\u0010\u0002J\u001f\u0010Y\u001a\n '*\u0004\u0018\u00010\u00010\u0001H\u0097\u0001b\f\b;\u0012\b\b<\u0012\u0004\b\u0007\u0010\u0002J\u001f\u0010Z\u001a\n '*\u0004\u0018\u00010\u00010\u0001H\u0097\u0001b\f\b;\u0012\b\b<\u0012\u0004\b\u0007\u0010\u0002J%\u0010[\u001a\f0\\¢\u0006\u0002\b#¢\u0006\u0002\b$H\u0097\u0001b\u0002\b#b\f\b;\u0012\b\b<\u0012\u0004\b\u0007\u0010\u0002J\u0017\u0010]\u001a\f0^¢\u0006\u0002\b#¢\u0006\u0002\b$H\u0097\u0001b\u0002\b#J'\u0010_\u001a\u000e\u0018\u00010\u0002¢\u0006\u0002\b*¢\u0006\u0002\b$H\u0097\u0001b\u0002\b*b\f\b;\u0012\b\b<\u0012\u0004\b\u0007\u0010\u0002JT\u0010`\u001a:\u0012\f\u0012\n '*\u0004\u0018\u00010\u00020\u0002 '*\u001c\u0012\u000e\b\u0001\u0012\n '*\u0004\u0018\u00010\u00020\u00020\u0011¢\u0006\u0002\b#¢\u0006\u0002\b$0\u0011¢\u0006\u0002\b#¢\u0006\u0002\b$H\u0097\u0001b\f\b;\u0012\b\b<\u0012\u0004\b\u0007\u0010\u0002¢\u0006\u0002\u0010aJ%\u0010b\u001a\f0c¢\u0006\u0002\b#¢\u0006\u0002\b$H\u0097\u0001b\u0002\b#b\f\b;\u0012\b\b<\u0012\u0004\b\u0007\u0010\u0002J\u0017\u0010d\u001a\u00020:H\u0097\u0001b\f\b;\u0012\b\b<\u0012\u0004\b\u0007\u0010\u0002J-\u0010e\u001a\u0014 '*\t\u0018\u00010\b¢\u0006\u0002\b?0\b¢\u0006\u0002\b?H\u0097\u0001b\f\b;\u0012\b\b<\u0012\u0004\b\u0007\u0010\u0002b\u0002\b?J\u0017\u0010f\u001a\u00020:H\u0097\u0001b\f\b;\u0012\b\b<\u0012\u0004\b\u0007\u0010\u0002J\u0017\u0010g\u001a\u00020:H\u0097\u0001b\f\b;\u0012\b\b<\u0012\u0004\b\u0007\u0010\u0002J\u001f\u0010h\u001a\n '*\u0004\u0018\u00010^0^H\u0097\u0001b\f\b;\u0012\b\b<\u0012\u0004\b\u0007\u0010\u0002J%\u0010i\u001a\f0j¢\u0006\u0002\b#¢\u0006\u0002\b$H\u0097\u0001b\u0002\b#b\f\b;\u0012\b\b<\u0012\u0004\b\u0007\u0010\u0002JV\u0010k\u001a\u000e\u0018\u0001HF¢\u0006\u0002\b*¢\u0006\u0002\b$\"\u0010\b\u0000\u0010F*\n '*\u0004\u0018\u00010G0G2$\b\u0001\u0010!\u001a\u001a\u0012\f\u0012\n '*\u0004\u0018\u0001HFHF0H¢\u0006\u0002\b#¢\u0006\u0002\b$:\u0002\b#H\u0097\u0001b\u0002\b*¢\u0006\u0002\u0010IJ)\u0010l\u001a\n '*\u0004\u0018\u00010\u00010\u00012\u0016\b\u0001\u0010!\u001a\f0\b¢\u0006\u0002\b#¢\u0006\u0002\b$:\u0002\b#H\u0096\u0001J'\u0010m\u001a\u00020\u000e2\u000e\u0010!\u001a\n '*\u0004\u0018\u00010\u00010\u0001H\u0097\u0001b\f\b;\u0012\b\b<\u0012\u0004\b\u0007\u0010\u0002J\u0017\u0010n\u001a\u00020\u000eH\u0097\u0001b\f\b;\u0012\b\b<\u0012\u0004\b\u0007\u0010\u0002J!\u0010o\u001a\u00020\u000e2\u0016\b\u0001\u0010!\u001a\f0\u0001¢\u0006\u0002\b#¢\u0006\u0002\b$:\u0002\b#H\u0096\u0001J\t\u0010p\u001a\u00020\u000eH\u0096\u0001J\u0017\u0010q\u001a\u00020\u000eH\u0097\u0001b\f\b;\u0012\b\b<\u0012\u0004\b\u0007\u0010\u0002J\u0017\u0010r\u001a\u00020\u000eH\u0097\u0001b\f\b;\u0012\b\b<\u0012\u0004\b\u0007\u0010\u0002Jk\u0010s\u001a\u00020\u000e2\u0016\b\u0001\u0010!\u001a\f0\u0019¢\u0006\u0002\b#¢\u0006\u0002\b$:\u0002\b#2\u0016\b\u0001\u0010)\u001a\f0t¢\u0006\u0002\b#¢\u0006\u0002\b$:\u0002\b#2\u0018\b\u0001\u0010.\u001a\u000e\u0018\u00010\u0001¢\u0006\u0002\b*¢\u0006\u0002\b$:\u0002\b*2\u0016\b\u0001\u0010u\u001a\f0\u0001¢\u0006\u0002\b#¢\u0006\u0002\b$:\u0002\b#H\u0096\u0001J`\u0010v\u001a\u00020\u0017\"\u0010\b\u0000\u0010F*\n '*\u0004\u0018\u00010G0G2$\b\u0001\u0010!\u001a\u001a\u0012\f\u0012\n '*\u0004\u0018\u0001HFHF0H¢\u0006\u0002\b#¢\u0006\u0002\b$:\u0002\b#2\u0018\b\u0001\u0010)\u001a\u000e\u0018\u0001HF¢\u0006\u0002\b*¢\u0006\u0002\b$:\u0002\b*H\u0096\u0001¢\u0006\u0002\u0010wJ`\u0010x\u001a\u00020\u0017\"\u0010\b\u0000\u0010F*\n '*\u0004\u0018\u00010G0G2$\b\u0001\u0010!\u001a\u001a\u0012\f\u0012\n '*\u0004\u0018\u0001HFHF0H¢\u0006\u0002\b#¢\u0006\u0002\b$:\u0002\b#2\u0018\b\u0001\u0010)\u001a\u000e\u0018\u0001HF¢\u0006\u0002\b*¢\u0006\u0002\b$:\u0002\b*H\u0096\u0001¢\u0006\u0002\u0010wJ)\u0010y\u001a\n '*\u0004\u0018\u00010\u00010\u00012\u0016\b\u0001\u0010!\u001a\f0\u0001¢\u0006\u0002\b#¢\u0006\u0002\b$:\u0002\b#H\u0096\u0001J\u0019\u0010z\u001a\u000e\u0018\u00010\u0001¢\u0006\u0002\b*¢\u0006\u0002\b$H\u0097\u0001b\u0002\b*J\u001f\u0010{\u001a\u00020\u000e2\u0006\u0010!\u001a\u00020|H\u0097\u0001b\f\b;\u0012\b\b<\u0012\u0004\b\u0007\u0010\u0002J8\u0010}\u001a\u00020\u000e2\u001f\b\u0001\u0010!\u001a\u00110~¢\u0006\u0002\b#¢\u0006\u0002\b\u007f¢\u0006\u0002\b$:\u0002\b#:\u0002\b\u007fH\u0097\u0001b\f\b;\u0012\b\b<\u0012\u0004\b\u0007\u0010\u0002J/\u0010}\u001a\u00020\u000e2\u0016\b\u0001\u0010!\u001a\f0\u0001¢\u0006\u0002\b#¢\u0006\u0002\b$:\u0002\b#H\u0097\u0001b\f\b;\u0012\b\b<\u0012\u0004\b\u0007\u0010\u0002J#\u0010\u0080\u0001\u001a\r0\u0081\u0001¢\u0006\u0002\b#¢\u0006\u0002\b$H\u0097\u0001b\f\b;\u0012\b\b<\u0012\u0004\b\u0007\u0010\u0002R\u000e\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0082\u0001"}, d2 = {"Lorg/jetbrains/kotlin/asJava/elements/KtLightPsiJavaCodeReferenceElement;", "Lcom/intellij/psi/PsiElement;", "Lcom/intellij/psi/PsiReference;", "Lcom/intellij/psi/PsiJavaCodeReferenceElement;", "ktElement", "reference", "Lkotlin/Function0;", "customReferenceName", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lcom/intellij/psi/PsiElement;Lkotlin/jvm/functions/Function0;Ljava/lang/String;)V", "advancedResolve", "Lcom/intellij/psi/JavaResolveResult;", "incompleteCode", "", "getReferenceNameElement", "getTypeParameters", "", "Lcom/intellij/psi/PsiType;", "()[Lcom/intellij/psi/PsiType;", "getReferenceName", "isQualified", "processVariants", "", "processor", "Lcom/intellij/psi/scope/PsiScopeProcessor;", "multiResolve", "(Z)[Lcom/intellij/psi/JavaResolveResult;", "getQualifiedName", "getQualifier", "getParameterList", "Lcom/intellij/psi/PsiReferenceParameterList;", "accept", "p0", "Lcom/intellij/psi/PsiElementVisitor;", "Lorg/jetbrains/annotations/NotNull;", "Lkotlin/jvm/internal/EnhancedNullability;", "acceptChildren", "add", "kotlin.jvm.PlatformType", "addAfter", "p1", "Lorg/jetbrains/annotations/Nullable;", "addBefore", "addRange", "addRangeAfter", "p2", "addRangeBefore", "bindToElement", "checkAdd", "Lkotlin/Deprecated;", "message", "Deprecated in Java", "checkDelete", "copy", "delete", "deleteChildRange", "findElementAt", "", "Lorg/jetbrains/annotations/Contract;", "pure", "findReferenceAt", "getCanonicalText", "Lcom/intellij/openapi/util/NlsSafe;", "getChildren", "()[Lcom/intellij/psi/PsiElement;", "getContainingFile", "Lcom/intellij/psi/PsiFile;", "getContext", "getCopyableUserData", "T", "", "Lcom/intellij/openapi/util/Key;", "(Lcom/intellij/openapi/util/Key;)Ljava/lang/Object;", "getElement", "getFirstChild", "getIcon", "Ljavax/swing/Icon;", "Lcom/intellij/openapi/util/Iconable$IconFlags;", "getLanguage", "Lcom/intellij/lang/Language;", "getLastChild", "getManager", "Lcom/intellij/psi/PsiManager;", "getNavigationElement", "getNextSibling", "getNode", "Lcom/intellij/lang/ASTNode;", "getOriginalElement", "getParent", "getPrevSibling", "getProject", "Lcom/intellij/openapi/project/Project;", "getRangeInElement", "Lcom/intellij/openapi/util/TextRange;", "getReference", "getReferences", "()[Lcom/intellij/psi/PsiReference;", "getResolveScope", "Lcom/intellij/psi/search/GlobalSearchScope;", "getStartOffsetInParent", "getText", "getTextLength", "getTextOffset", "getTextRange", "getUseScope", "Lcom/intellij/psi/search/SearchScope;", "getUserData", "handleElementRename", "isEquivalentTo", "isPhysical", "isReferenceTo", "isSoft", "isValid", "isWritable", "processDeclarations", "Lcom/intellij/psi/ResolveState;", "p3", "putCopyableUserData", "(Lcom/intellij/openapi/util/Key;Ljava/lang/Object;)V", "putUserData", "replace", "resolve", "textContains", "", "textMatches", "", "Lorg/jetbrains/annotations/NonNls;", "textToCharArray", "", "org.jetbrains.kotlin:light-classes"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class KtLightPsiJavaCodeReferenceElement implements PsiElement, PsiJavaCodeReferenceElement, PsiReference {
    private final /* synthetic */ LazyPsiReferenceDelegate $$delegate_1;
    private final String customReferenceName;
    private final PsiElement ktElement;

    public KtLightPsiJavaCodeReferenceElement(PsiElement psiElement, Function0<? extends PsiReference> function0, String str) {
        psiElement.getClass();
        function0.getClass();
        this.$$delegate_1 = new LazyPsiReferenceDelegate(psiElement, function0);
        this.ktElement = psiElement;
        this.customReferenceName = str;
    }

    public void accept(PsiElementVisitor p0) {
        p0.getClass();
        this.ktElement.accept(p0);
    }

    public void acceptChildren(PsiElementVisitor p0) {
        p0.getClass();
        this.ktElement.acceptChildren(p0);
    }

    public PsiElement add(PsiElement p0) {
        p0.getClass();
        return this.ktElement.add(p0);
    }

    public PsiElement addAfter(PsiElement p0, PsiElement p1) {
        p0.getClass();
        return this.ktElement.addAfter(p0, p1);
    }

    public PsiElement addBefore(PsiElement p0, PsiElement p1) {
        p0.getClass();
        return this.ktElement.addBefore(p0, p1);
    }

    public PsiElement addRange(PsiElement p0, PsiElement p1) {
        return this.ktElement.addRange(p0, p1);
    }

    public PsiElement addRangeAfter(PsiElement p0, PsiElement p1, PsiElement p2) {
        return this.ktElement.addRangeAfter(p0, p1, p2);
    }

    public PsiElement addRangeBefore(PsiElement p0, PsiElement p1, PsiElement p2) {
        p0.getClass();
        p1.getClass();
        return this.ktElement.addRangeBefore(p0, p1, p2);
    }

    public JavaResolveResult advancedResolve(boolean incompleteCode) {
        JavaResolveResult javaResolveResult = JavaResolveResult.EMPTY;
        javaResolveResult.getClass();
        return javaResolveResult;
    }

    public PsiElement bindToElement(PsiElement p0) {
        p0.getClass();
        return this.$$delegate_1.bindToElement(p0);
    }

    @Deprecated(message = "Deprecated in Java")
    public void checkAdd(PsiElement p0) {
        p0.getClass();
        this.ktElement.checkAdd(p0);
    }

    @Deprecated(message = "Deprecated in Java")
    public void checkDelete() {
        this.ktElement.checkDelete();
    }

    public PsiElement copy() {
        return this.ktElement.copy();
    }

    public void delete() {
        this.ktElement.delete();
    }

    public void deleteChildRange(PsiElement p0, PsiElement p1) {
        this.ktElement.deleteChildRange(p0, p1);
    }

    public PsiElement findElementAt(int p0) {
        return this.ktElement.findElementAt(p0);
    }

    public PsiReference findReferenceAt(int p0) {
        return this.ktElement.findReferenceAt(p0);
    }

    public String getCanonicalText() {
        return this.$$delegate_1.getCanonicalText();
    }

    public PsiElement[] getChildren() {
        PsiElement[] children = this.ktElement.getChildren();
        children.getClass();
        return children;
    }

    public PsiFile getContainingFile() {
        return this.ktElement.getContainingFile();
    }

    public PsiElement getContext() {
        return this.ktElement.getContext();
    }

    public <T> T getCopyableUserData(Key<T> p0) {
        p0.getClass();
        return (T) this.ktElement.getCopyableUserData(p0);
    }

    public PsiElement getElement() {
        return this.$$delegate_1.getPsiElement();
    }

    public PsiElement getFirstChild() {
        return this.ktElement.getFirstChild();
    }

    public Icon getIcon(int p0) {
        return this.ktElement.getIcon(p0);
    }

    public Language getLanguage() {
        Language language = this.ktElement.getLanguage();
        language.getClass();
        return language;
    }

    public PsiElement getLastChild() {
        return this.ktElement.getLastChild();
    }

    public PsiManager getManager() {
        return this.ktElement.getManager();
    }

    public PsiElement getNavigationElement() {
        return this.ktElement.getNavigationElement();
    }

    public PsiElement getNextSibling() {
        return this.ktElement.getNextSibling();
    }

    public ASTNode getNode() {
        return this.ktElement.getNode();
    }

    public PsiElement getOriginalElement() {
        return this.ktElement.getOriginalElement();
    }

    public PsiReferenceParameterList getParameterList() {
        return null;
    }

    public PsiElement getParent() {
        return this.ktElement.getParent();
    }

    public PsiElement getPrevSibling() {
        return this.ktElement.getPrevSibling();
    }

    public Project getProject() {
        Project project = this.ktElement.getProject();
        project.getClass();
        return project;
    }

    public String getQualifiedName() {
        return null;
    }

    public PsiElement getQualifier() {
        return null;
    }

    public TextRange getRangeInElement() {
        return this.$$delegate_1.getRangeInElement();
    }

    public PsiReference getReference() {
        return this.ktElement.getReference();
    }

    /* JADX INFO: renamed from: getReferenceName, reason: from getter */
    public String getCustomReferenceName() {
        return this.customReferenceName;
    }

    public PsiElement getReferenceNameElement() {
        return null;
    }

    public PsiReference[] getReferences() {
        PsiReference[] references = this.ktElement.getReferences();
        references.getClass();
        return references;
    }

    public GlobalSearchScope getResolveScope() {
        GlobalSearchScope resolveScope = this.ktElement.getResolveScope();
        resolveScope.getClass();
        return resolveScope;
    }

    public int getStartOffsetInParent() {
        return this.ktElement.getStartOffsetInParent();
    }

    public String getText() {
        return this.ktElement.getText();
    }

    public int getTextLength() {
        return this.ktElement.getTextLength();
    }

    public int getTextOffset() {
        return this.ktElement.getTextOffset();
    }

    public TextRange getTextRange() {
        return this.ktElement.getTextRange();
    }

    public PsiType[] getTypeParameters() {
        return new PsiType[0];
    }

    public SearchScope getUseScope() {
        SearchScope useScope = this.ktElement.getUseScope();
        useScope.getClass();
        return useScope;
    }

    public <T> T getUserData(Key<T> p0) {
        p0.getClass();
        return (T) this.ktElement.getUserData(p0);
    }

    public PsiElement handleElementRename(String p0) {
        p0.getClass();
        return this.$$delegate_1.handleElementRename(p0);
    }

    public boolean isEquivalentTo(PsiElement p0) {
        return this.ktElement.isEquivalentTo(p0);
    }

    public boolean isPhysical() {
        return this.ktElement.isPhysical();
    }

    public boolean isQualified() {
        return false;
    }

    public boolean isReferenceTo(PsiElement p0) {
        p0.getClass();
        return this.$$delegate_1.isReferenceTo(p0);
    }

    public boolean isSoft() {
        return this.$$delegate_1.isSoft();
    }

    public boolean isValid() {
        return this.ktElement.isValid();
    }

    public boolean isWritable() {
        return this.ktElement.isWritable();
    }

    public boolean processDeclarations(PsiScopeProcessor p0, ResolveState p1, PsiElement p2, PsiElement p3) {
        p0.getClass();
        p1.getClass();
        p3.getClass();
        return this.ktElement.processDeclarations(p0, p1, p2, p3);
    }

    public void processVariants(PsiScopeProcessor processor) {
        processor.getClass();
    }

    public <T> void putCopyableUserData(Key<T> p0, T p1) {
        p0.getClass();
        this.ktElement.putCopyableUserData(p0, p1);
    }

    public <T> void putUserData(Key<T> p0, T p1) {
        p0.getClass();
        this.ktElement.putUserData(p0, p1);
    }

    public PsiElement replace(PsiElement p0) {
        p0.getClass();
        return this.ktElement.replace(p0);
    }

    public PsiElement resolve() {
        return this.$$delegate_1.resolve();
    }

    public boolean textContains(char p0) {
        return this.ktElement.textContains(p0);
    }

    public boolean textMatches(PsiElement p0) {
        p0.getClass();
        return this.ktElement.textMatches(p0);
    }

    public char[] textToCharArray() {
        char[] cArrTextToCharArray = this.ktElement.textToCharArray();
        cArrTextToCharArray.getClass();
        return cArrTextToCharArray;
    }

    /* JADX INFO: renamed from: multiResolve, reason: merged with bridge method [inline-methods] */
    public JavaResolveResult[] m130multiResolve(boolean incompleteCode) {
        return new JavaResolveResult[0];
    }

    public boolean textMatches(CharSequence p0) {
        p0.getClass();
        return this.ktElement.textMatches(p0);
    }

    public /* synthetic */ KtLightPsiJavaCodeReferenceElement(PsiElement psiElement, Function0 function0, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(psiElement, function0, (i & 4) != 0 ? null : str);
    }
}
