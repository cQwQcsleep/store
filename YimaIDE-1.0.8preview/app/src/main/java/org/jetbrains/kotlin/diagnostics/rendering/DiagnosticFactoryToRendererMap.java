package org.jetbrains.kotlin.diagnostics.rendering;

import com.intellij.psi.PsiElement;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.kotlin.config.LanguageVersion;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticFactory;
import org.jetbrains.kotlin.diagnostics.DiagnosticFactory0;
import org.jetbrains.kotlin.diagnostics.DiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.DiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.DiagnosticFactory3;
import org.jetbrains.kotlin.diagnostics.DiagnosticFactory4;
import org.jetbrains.kotlin.diagnostics.DiagnosticFactoryForDeprecation;
import org.jetbrains.kotlin.diagnostics.DiagnosticFactoryForDeprecation0;
import org.jetbrains.kotlin.diagnostics.DiagnosticFactoryForDeprecation1;
import org.jetbrains.kotlin.diagnostics.DiagnosticFactoryForDeprecation2;
import org.jetbrains.kotlin.diagnostics.DiagnosticFactoryForDeprecation3;
import org.jetbrains.kotlin.diagnostics.DiagnosticFactoryForDeprecation4;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public final class DiagnosticFactoryToRendererMap {
    private boolean immutable;
    private final Map<DiagnosticFactory<?>, DiagnosticRenderer<?>> map;
    private final String name;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        switch (i) {
            case 1:
            case 3:
            case 5:
            case 8:
            case 10:
            case 12:
            case 16:
            case 18:
            case 20:
            case 23:
            case 25:
            case 27:
                objArr[0] = "message";
                break;
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
            case 4:
            case 7:
            case 9:
            case 11:
            case 13:
            case 15:
            case 17:
            case 19:
            case 22:
            case 24:
            case 26:
            default:
                objArr[0] = "factory";
                break;
            case 6:
            case 21:
                objArr[0] = "rendererA";
                break;
            case 14:
                objArr[0] = "renderer";
                break;
        }
        objArr[1] = "org/jetbrains/kotlin/diagnostics/rendering/DiagnosticFactoryToRendererMap";
        if (i != 28) {
            objArr[2] = "put";
        } else {
            objArr[2] = "get";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public DiagnosticFactoryToRendererMap(String str) {
        this.map = new HashMap();
        this.immutable = false;
        this.name = str;
    }

    private void checkMutability() {
        if (this.immutable) {
            k2d.a("factory to renderer map is already immutable");
        }
    }

    private static String deprecationMessage(DiagnosticFactoryForDeprecation<?, ?, ?> diagnosticFactoryForDeprecation, String str) {
        LanguageVersion sinceVersion = diagnosticFactoryForDeprecation.getDeprecatingFeature().getSinceVersion();
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(". This will become an error");
        if (sinceVersion != null) {
            sb.append(" in Kotlin ");
            sb.append(sinceVersion.getVersionString());
        } else {
            sb.append(" in a future release");
        }
        return sb.toString();
    }

    public DiagnosticRenderer<?> get(DiagnosticFactory<?> diagnosticFactory) {
        if (diagnosticFactory == null) {
            $$$reportNull$$$0(28);
        }
        return this.map.get(diagnosticFactory);
    }

    public <E extends PsiElement, A, B, C, D> void put(DiagnosticFactoryForDeprecation4<E, A, B, C, D> diagnosticFactoryForDeprecation4, String str, DiagnosticParameterRenderer<? super A> diagnosticParameterRenderer, DiagnosticParameterRenderer<? super B> diagnosticParameterRenderer2, DiagnosticParameterRenderer<? super C> diagnosticParameterRenderer3, DiagnosticParameterRenderer<? super D> diagnosticParameterRenderer4) {
        if (diagnosticFactoryForDeprecation4 == null) {
            $$$reportNull$$$0(26);
        }
        if (str == null) {
            $$$reportNull$$$0(27);
        }
        checkMutability();
        this.map.put(diagnosticFactoryForDeprecation4.getErrorFactory(), new DiagnosticWithParameters4Renderer(str, diagnosticParameterRenderer, diagnosticParameterRenderer2, diagnosticParameterRenderer3, diagnosticParameterRenderer4));
        this.map.put(diagnosticFactoryForDeprecation4.getWarningFactory(), new DiagnosticWithParameters4Renderer(deprecationMessage(diagnosticFactoryForDeprecation4, str), diagnosticParameterRenderer, diagnosticParameterRenderer2, diagnosticParameterRenderer3, diagnosticParameterRenderer4));
    }

    public void setImmutable() {
        this.immutable = false;
    }

    public String toString() {
        return "DiagnosticFactory#" + this.name;
    }

    public DiagnosticFactoryToRendererMap() {
        this("<unnamed>");
    }

    public <E extends PsiElement, A> void put(DiagnosticFactory1<E, A> diagnosticFactory1, String str, DiagnosticParameterRenderer<? super A> diagnosticParameterRenderer) {
        if (diagnosticFactory1 == null) {
            $$$reportNull$$$0(2);
        }
        if (str == null) {
            $$$reportNull$$$0(3);
        }
        checkMutability();
        this.map.put(diagnosticFactory1, new DiagnosticWithParameters1Renderer(str, diagnosticParameterRenderer));
    }

    public <E extends PsiElement, A> void put(DiagnosticFactory1<E, A> diagnosticFactory1, String str, MultiRenderer<? super A> multiRenderer) {
        if (diagnosticFactory1 == null) {
            $$$reportNull$$$0(4);
        }
        if (str == null) {
            $$$reportNull$$$0(5);
        }
        if (multiRenderer == null) {
            $$$reportNull$$$0(6);
        }
        checkMutability();
        this.map.put(diagnosticFactory1, new DiagnosticWithParametersMultiRenderer(str, multiRenderer));
    }

    public <E extends PsiElement, A, B> void put(DiagnosticFactory2<E, A, B> diagnosticFactory2, String str, DiagnosticParameterRenderer<? super A> diagnosticParameterRenderer, DiagnosticParameterRenderer<? super B> diagnosticParameterRenderer2) {
        if (diagnosticFactory2 == null) {
            $$$reportNull$$$0(7);
        }
        if (str == null) {
            $$$reportNull$$$0(8);
        }
        checkMutability();
        this.map.put(diagnosticFactory2, new DiagnosticWithParameters2Renderer(str, diagnosticParameterRenderer, diagnosticParameterRenderer2));
    }

    public <E extends PsiElement, A, B, C> void put(DiagnosticFactory3<E, A, B, C> diagnosticFactory3, String str, DiagnosticParameterRenderer<? super A> diagnosticParameterRenderer, DiagnosticParameterRenderer<? super B> diagnosticParameterRenderer2, DiagnosticParameterRenderer<? super C> diagnosticParameterRenderer3) {
        if (diagnosticFactory3 == null) {
            $$$reportNull$$$0(9);
        }
        if (str == null) {
            $$$reportNull$$$0(10);
        }
        checkMutability();
        this.map.put(diagnosticFactory3, new DiagnosticWithParameters3Renderer(str, diagnosticParameterRenderer, diagnosticParameterRenderer2, diagnosticParameterRenderer3));
    }

    public <E extends PsiElement, A, B, C, D> void put(DiagnosticFactory4<E, A, B, C, D> diagnosticFactory4, String str, DiagnosticParameterRenderer<? super A> diagnosticParameterRenderer, DiagnosticParameterRenderer<? super B> diagnosticParameterRenderer2, DiagnosticParameterRenderer<? super C> diagnosticParameterRenderer3, DiagnosticParameterRenderer<? super D> diagnosticParameterRenderer4) {
        if (diagnosticFactory4 == null) {
            $$$reportNull$$$0(11);
        }
        if (str == null) {
            $$$reportNull$$$0(12);
        }
        checkMutability();
        this.map.put(diagnosticFactory4, new DiagnosticWithParameters4Renderer(str, diagnosticParameterRenderer, diagnosticParameterRenderer2, diagnosticParameterRenderer3, diagnosticParameterRenderer4));
    }

    public void put(DiagnosticFactory<?> diagnosticFactory, DiagnosticRenderer<?> diagnosticRenderer) {
        if (diagnosticFactory == null) {
            $$$reportNull$$$0(13);
        }
        if (diagnosticRenderer == null) {
            $$$reportNull$$$0(14);
        }
        checkMutability();
        this.map.put(diagnosticFactory, diagnosticRenderer);
    }

    public <E extends PsiElement> void put(DiagnosticFactoryForDeprecation0<E> diagnosticFactoryForDeprecation0, String str) {
        if (diagnosticFactoryForDeprecation0 == null) {
            $$$reportNull$$$0(15);
        }
        if (str == null) {
            $$$reportNull$$$0(16);
        }
        checkMutability();
        this.map.put(diagnosticFactoryForDeprecation0.getErrorFactory(), new SimpleDiagnosticRenderer(str));
        this.map.put(diagnosticFactoryForDeprecation0.getWarningFactory(), new SimpleDiagnosticRenderer(deprecationMessage(diagnosticFactoryForDeprecation0, str)));
    }

    public <E extends PsiElement, A> void put(DiagnosticFactoryForDeprecation1<E, A> diagnosticFactoryForDeprecation1, String str, DiagnosticParameterRenderer<? super A> diagnosticParameterRenderer) {
        if (diagnosticFactoryForDeprecation1 == null) {
            $$$reportNull$$$0(17);
        }
        if (str == null) {
            $$$reportNull$$$0(18);
        }
        checkMutability();
        this.map.put(diagnosticFactoryForDeprecation1.getErrorFactory(), new DiagnosticWithParameters1Renderer(str, diagnosticParameterRenderer));
        this.map.put(diagnosticFactoryForDeprecation1.getWarningFactory(), new DiagnosticWithParameters1Renderer(deprecationMessage(diagnosticFactoryForDeprecation1, str), diagnosticParameterRenderer));
    }

    public <E extends PsiElement, A> void put(DiagnosticFactoryForDeprecation1<E, A> diagnosticFactoryForDeprecation1, String str, MultiRenderer<? super A> multiRenderer) {
        if (diagnosticFactoryForDeprecation1 == null) {
            $$$reportNull$$$0(19);
        }
        if (str == null) {
            $$$reportNull$$$0(20);
        }
        if (multiRenderer == null) {
            $$$reportNull$$$0(21);
        }
        checkMutability();
        this.map.put(diagnosticFactoryForDeprecation1.getErrorFactory(), new DiagnosticWithParametersMultiRenderer(str, multiRenderer));
        this.map.put(diagnosticFactoryForDeprecation1.getWarningFactory(), new DiagnosticWithParametersMultiRenderer(deprecationMessage(diagnosticFactoryForDeprecation1, str), multiRenderer));
    }

    public <E extends PsiElement, A, B> void put(DiagnosticFactoryForDeprecation2<E, A, B> diagnosticFactoryForDeprecation2, String str, DiagnosticParameterRenderer<? super A> diagnosticParameterRenderer, DiagnosticParameterRenderer<? super B> diagnosticParameterRenderer2) {
        if (diagnosticFactoryForDeprecation2 == null) {
            $$$reportNull$$$0(22);
        }
        if (str == null) {
            $$$reportNull$$$0(23);
        }
        checkMutability();
        this.map.put(diagnosticFactoryForDeprecation2.getErrorFactory(), new DiagnosticWithParameters2Renderer(str, diagnosticParameterRenderer, diagnosticParameterRenderer2));
        this.map.put(diagnosticFactoryForDeprecation2.getWarningFactory(), new DiagnosticWithParameters2Renderer(deprecationMessage(diagnosticFactoryForDeprecation2, str), diagnosticParameterRenderer, diagnosticParameterRenderer2));
    }

    public <E extends PsiElement, A, B, C> void put(DiagnosticFactoryForDeprecation3<E, A, B, C> diagnosticFactoryForDeprecation3, String str, DiagnosticParameterRenderer<? super A> diagnosticParameterRenderer, DiagnosticParameterRenderer<? super B> diagnosticParameterRenderer2, DiagnosticParameterRenderer<? super C> diagnosticParameterRenderer3) {
        if (diagnosticFactoryForDeprecation3 == null) {
            $$$reportNull$$$0(24);
        }
        if (str == null) {
            $$$reportNull$$$0(25);
        }
        checkMutability();
        this.map.put(diagnosticFactoryForDeprecation3.getErrorFactory(), new DiagnosticWithParameters3Renderer(str, diagnosticParameterRenderer, diagnosticParameterRenderer2, diagnosticParameterRenderer3));
        this.map.put(diagnosticFactoryForDeprecation3.getWarningFactory(), new DiagnosticWithParameters3Renderer(deprecationMessage(diagnosticFactoryForDeprecation3, str), diagnosticParameterRenderer, diagnosticParameterRenderer2, diagnosticParameterRenderer3));
    }

    public <E extends PsiElement> void put(DiagnosticFactory0<E> diagnosticFactory0, String str) {
        if (diagnosticFactory0 == null) {
            $$$reportNull$$$0(0);
        }
        if (str == null) {
            $$$reportNull$$$0(1);
        }
        checkMutability();
        this.map.put(diagnosticFactory0, new SimpleDiagnosticRenderer(str));
    }
}
