package androidx.compose.material3.internal.colorUtil;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u0013\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0016\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0002J\u0010\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\fH\u0002J\u0010\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\fH\u0002J\u0010\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0006H\u0002J \u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\fH\u0002J \u0010\u0019\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\fH\u0002J \u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u0006H\u0002J(\u0010\u001f\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010!\u001a\u00020\"H\u0002J\u0010\u0010#\u001a\u00020\u00152\u0006\u0010$\u001a\u00020\fH\u0002J\u0018\u0010%\u001a\u00020\u00062\u0006\u0010&\u001a\u00020\f2\u0006\u0010'\u001a\u00020\"H\u0002J#\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010&\u001a\u00020\f2\u0006\u0010)\u001a\u00020\fH\u0002¢\u0006\u0002\u0010*J\u0010\u0010+\u001a\u00020\"2\u0006\u0010$\u001a\u00020\fH\u0002J\u0010\u0010,\u001a\u00020\"2\u0006\u0010$\u001a\u00020\fH\u0002J\u0018\u0010-\u001a\u00020\"2\u0006\u0010&\u001a\u00020\f2\u0006\u0010)\u001a\u00020\fH\u0002J\u0010\u0010.\u001a\u00020\f2\u0006\u0010/\u001a\u00020\fH\u0002J \u00100\u001a\u00020\"2\u0006\u00101\u001a\u00020\f2\u0006\u00102\u001a\u00020\f2\u0006\u0010&\u001a\u00020\fH\u0002J\u001e\u00103\u001a\u00020\"2\u0006\u00104\u001a\u00020\f2\u0006\u00102\u001a\u00020\f2\u0006\u00105\u001a\u00020\fJ\u0010\u00106\u001a\u00020\f2\u0006\u00107\u001a\u00020\fH\u0002R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00068"}, d2 = {"Landroidx/compose/material3/internal/colorUtil/HctSolver;", "", "<init>", "()V", "SCALED_DISCOUNT_FROM_LINRGB", "", "", "[[D", "LINRGB_FROM_SCALED_DISCOUNT", "Y_FROM_LINRGB", "CRITICAL_PLANES", "sanitizeRadians", "", "angle", "trueDelinearized", "rgbComponent", "chromaticAdaptation", "component", "hueOf", "linrgb", "areInCyclicOrder", "", "a", "b", "c", "intercept", "source", "mid", "target", "lerpPoint", "t", "setCoordinate", "coordinate", "axis", "", "isBounded", "x", "nthVertex", "y", "n", "bisectToSegment", "targetHue", "(DD)[[D", "criticalPlaneBelow", "criticalPlaneAbove", "bisectToLimit", "inverseChromaticAdaptation", "adapted", "findResultByJ", "hueRadians", "chroma", "solveToInt", "hueDegrees", "lstar", "sanitizeDegreesDouble", "degrees", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class HctSolver {
    public static final HctSolver INSTANCE = new HctSolver();
    private static final double[][] SCALED_DISCOUNT_FROM_LINRGB = {new double[]{0.001200833568784504d, 0.002389694492170889d, 2.795742885861124E-4d}, new double[]{5.891086651375999E-4d, 0.0029785502573438758d, 3.270666104008398E-4d}, new double[]{1.0146692491640572E-4d, 5.364214359186694E-4d, 0.0032979401770712076d}};
    private static final double[][] LINRGB_FROM_SCALED_DISCOUNT = {new double[]{1373.2198709594231d, -1100.4251190754821d, -7.278681089101213d}, new double[]{-271.815969077903d, 559.6580465940733d, -32.46047482791194d}, new double[]{1.9622899599665666d, -57.173814538844006d, 308.7233197812385d}};
    private static final double[] Y_FROM_LINRGB = {0.2126d, 0.7152d, 0.0722d};
    private static final double[] CRITICAL_PLANES = {0.015176349177441876d, 0.045529047532325624d, 0.07588174588720938d, 0.10623444424209313d, 0.13658714259697685d, 0.16693984095186062d, 0.19729253930674434d, 0.2276452376616281d, 0.2579979360165119d, 0.28835063437139563d, 0.3188300904430532d, 0.350925934958123d, 0.3848314933096426d, 0.42057480301049466d, 0.458183274052838d, 0.4976837250274023d, 0.5391024159806381d, 0.5824650784040898d, 0.6277969426914107d, 0.6751227633498623d, 0.7244668422128921d, 0.775853049866786d, 0.829304845476233d, 0.8848452951698498d, 0.942497089126609d, 1.0022825574869039d, 1.0642236851973577d, 1.1283421258858297d, 1.1946592148522128d, 1.2631959812511864d, 1.3339731595349034d, 1.407011200216447d, 1.4823302800086415d, 1.5599503113873272d, 1.6398909516233677d, 1.7221716113234105d, 1.8068114625156377d, 1.8938294463134073d, 1.9832442801866852d, 2.075074464868551d, 2.1693382909216234d, 2.2660538449872063d, 2.36523901573795d, 2.4669114995532007d, 2.5710888059345764d, 2.6777882626779785d, 2.7870270208169257d, 2.898822059350997d, 3.0131901897720907d, 3.1301480604002863d, 3.2497121605402226d, 3.3718988244681087d, 3.4967242352587946d, 3.624204428461639d, 3.754355295633311d, 3.887192587735158d, 4.022731918402185d, 4.160988767090289d, 4.301978482107941d, 4.445716283538092d, 4.592217266055746d, 4.741496401646282d, 4.893568542229298d, 5.048448422192488d, 5.20615066083972d, 5.3666897647573375d, 5.5300801301023865d, 5.696336044816294d, 5.865471690767354d, 6.037501145825082d, 6.212438385869475d, 6.390297286737924d, 6.571091626112461d, 6.7548350853498045d, 6.941541251256611d, 7.131223617812143d, 7.323895587840543d, 7.5195704746346665d, 7.7182615035334345d, 7.919981813454504d, 8.124744458384042d, 8.332562408825165d, 8.543448553206703d, 8.757415699253682d, 8.974476575321063d, 9.194643831691977d, 9.417930041841839d, 9.644347703669503d, 9.873909240696694d, 10.106627003236781d, 10.342513269534024d, 10.58158024687427d, 10.8238400726681d, 11.069304815507364d, 11.317986476196008d, 11.569896988756009d, 11.825048221409341d, 12.083451977536606d, 12.345119996613247d, 12.610063955123938d, 12.878295467455942d, 13.149826086772048d, 13.42466730586372d, 13.702830557985108d, 13.984327217668513d, 14.269168601521828d, 14.55736596900856d, 14.848930523210871d, 15.143873411576273d, 15.44220572664832d, 15.743938506781891d, 16.04908273684337d, 16.35764934889634d, 16.66964922287304d, 16.985093187232053d, 17.30399201960269d, 17.62635644741625d, 17.95219714852476d, 18.281524751807332d, 18.614349837764564d, 18.95068293910138d, 19.290534541298456d, 19.633915083172692d, 19.98083495742689d, 20.331304511189067d, 20.685334046541502d, 21.042933821039977d, 21.404114048223256d, 21.76888489811322d, 22.137256497705877d, 22.50923893145328d, 22.884842241736916d, 23.264076429332462d, 23.6469514538663d, 24.033477234264016d, 24.42366364919083d, 24.817520537484558d, 25.21505769858089d, 25.61628489293138d, 26.021211842414342d, 26.429848230738664d, 26.842203703840827d, 27.258287870275353d, 27.678110301598522d, 28.10168053274597d, 28.529008062403893d, 28.96010235337422d, 29.39497283293396d, 29.83362889318845d, 30.276079891419332d, 30.722335150426627d, 31.172403958865512d, 31.62629557157785d, 32.08401920991837d, 32.54558406207592d, 33.010999283389665d, 33.4802739966603d, 33.953417292456834d, 34.430438229418264d, 34.911345834551085d, 35.39614910352207d, 35.88485700094671d, 36.37747846067349d, 36.87402238606382d, 37.37449765026789d, 37.87891309649659d, 38.38727753828926d, 38.89959975977785d, 39.41588851594697d, 39.93615253289054d, 40.460400508064545d, 40.98864111053629d, 41.520882981230194d, 42.05713473317016d, 42.597404951718396d, 43.141702194811224d, 43.6900349931913d, 44.24241185063697d, 44.798841244188324d, 45.35933162437017d, 45.92389141541209d, 46.49252901546552d, 47.065252796817916d, 47.64207110610409d, 48.22299226451468d, 48.808024568002054d, 49.3971762874833d, 49.9904556690408d, 50.587870934119984d, 51.189430279724725d, 51.79514187861014d, 52.40501387947288d, 53.0190544071392d, 53.637271562750364d, 54.259673423945976d, 54.88626804504493d, 55.517063457223934d, 56.15206766869424d, 56.79128866487574d, 57.43473440856916d, 58.08241284012621d, 58.734331877617365d, 59.39049941699807d, 60.05092333227251d, 60.715611475655585d, 61.38457167773311d, 62.057811747619894d, 62.7353394731159d, 63.417162620860914d, 64.10328893648692d, 64.79372614476921d, 65.48848194977529d, 66.18756403501224d, 66.89098006357258d, 67.59873767827808d, 68.31084450182222d, 69.02730813691093d, 69.74813616640164d, 70.47333615344107d, 71.20291564160104d, 71.93688215501312d, 72.67524319850172d, 73.41800625771542d, 74.16517879925733d, 74.9167682708136d, 75.67278210128072d, 76.43322770089146d, 77.1981124613393d, 77.96744375590167d, 78.74122893956174d, 79.51947534912904d, 80.30219030335869d, 81.08938110306934d, 81.88105503125999d, 82.67721935322541d, 83.4778813166706d, 84.28304815182372d, 85.09272707154808d, 85.90692527145302d, 86.72564993000343d, 87.54890820862819d, 88.3767072518277d, 89.2090541872801d, 90.04595612594655d, 90.88742016217518d, 91.73345337380438d, 92.58406282226491d, 93.43925555268066d, 94.29903859396902d, 95.16341895893969d, 96.03240364439274d, 96.9059996312159d, 97.78421388448044d, 98.6670533535366d, 99.55452497210776d};
    public static final int $stable = 8;

    private HctSolver() {
    }

    private final boolean areInCyclicOrder(double a, double b, double c) {
        return sanitizeRadians(b - a) < sanitizeRadians(c - a);
    }

    private final int bisectToLimit(double y, double targetHue) {
        int iCriticalPlaneAbove;
        int iCriticalPlaneBelow;
        int i;
        HctSolver hctSolver = this;
        double[][] dArrBisectToSegment = bisectToSegment(y, targetHue);
        double[] dArr = dArrBisectToSegment[0];
        double dHueOf = hctSolver.hueOf(dArr);
        double[] dArr2 = dArrBisectToSegment[1];
        int i2 = 0;
        while (i2 < 3) {
            double d = dArr[i2];
            double d2 = dArr2[i2];
            if (d == d2) {
                i = i2;
            } else {
                if (d < d2) {
                    iCriticalPlaneAbove = hctSolver.criticalPlaneBelow(hctSolver.trueDelinearized(d));
                    iCriticalPlaneBelow = hctSolver.criticalPlaneAbove(hctSolver.trueDelinearized(dArr2[i2]));
                } else {
                    iCriticalPlaneAbove = hctSolver.criticalPlaneAbove(hctSolver.trueDelinearized(d));
                    iCriticalPlaneBelow = hctSolver.criticalPlaneBelow(hctSolver.trueDelinearized(dArr2[i2]));
                }
                int i3 = iCriticalPlaneAbove;
                int i4 = 0;
                int i5 = iCriticalPlaneBelow;
                double d3 = dHueOf;
                double[] dArr3 = dArr2;
                double[] dArr4 = dArr;
                while (i4 < 8 && Math.abs(i5 - i3) > 1.0d) {
                    int iFloor = (int) Math.floor(((double) (i3 + i5)) / 2.0d);
                    double[] coordinate = hctSolver.setCoordinate(dArr4, CRITICAL_PLANES[iFloor], dArr3, i2);
                    double[] dArr5 = dArr4;
                    double[] dArr6 = dArr3;
                    int i6 = i2;
                    double dHueOf2 = hctSolver.hueOf(coordinate);
                    double d4 = d3;
                    if (hctSolver.areInCyclicOrder(d4, targetHue, dHueOf2)) {
                        i5 = iFloor;
                        dArr3 = coordinate;
                        d3 = d4;
                        dArr4 = dArr5;
                    } else {
                        i3 = iFloor;
                        dArr4 = coordinate;
                        dArr3 = dArr6;
                        d3 = dHueOf2;
                    }
                    i4++;
                    hctSolver = this;
                    i2 = i6;
                }
                i = i2;
                dHueOf = d3;
                dArr = dArr4;
                dArr2 = dArr3;
            }
            i2 = i + 1;
            hctSolver = this;
        }
        return CamUtils.INSTANCE.argbFromLinrgbComponents((dArr[0] + dArr2[0]) / 2.0d, (dArr[1] + dArr2[1]) / 2.0d, (dArr[2] + dArr2[2]) / 2.0d);
    }

    private final double[][] bisectToSegment(double y, double targetHue) {
        double d;
        char c = 0;
        double[] dArr = {-1.0d, -1.0d, -1.0d};
        double[] dArr2 = dArr;
        int i = 0;
        boolean z = false;
        char c2 = 1;
        double d2 = 0.0d;
        double d3 = 0.0d;
        while (i < 12) {
            char c3 = c;
            double[] dArrNthVertex = nthVertex(y, i);
            if (dArrNthVertex[c3] < 0.0d) {
                d = d3;
            } else {
                double dHueOf = hueOf(dArrNthVertex);
                if (z) {
                    if (c2 == 0) {
                        d = d3;
                        if (areInCyclicOrder(d2, dHueOf, d3)) {
                        }
                    } else {
                        d = d3;
                    }
                    if (areInCyclicOrder(d2, targetHue, dHueOf)) {
                        d3 = dHueOf;
                        dArr2 = dArrNthVertex;
                        c2 = c3;
                    } else {
                        d2 = dHueOf;
                        dArr = dArrNthVertex;
                        c2 = c3;
                    }
                } else {
                    d2 = dHueOf;
                    d3 = d2;
                    dArr = dArrNthVertex;
                    dArr2 = dArr;
                    z = true;
                }
                i++;
                c = c3;
            }
            d3 = d;
            i++;
            c = c3;
        }
        return new double[][]{dArr, dArr2};
    }

    private final double chromaticAdaptation(double component) {
        double dPow = Math.pow(Math.abs(component), 0.42d);
        return ((((double) CamUtils.INSTANCE.signum(component)) * 400.0d) * dPow) / (dPow + 27.13d);
    }

    private final int criticalPlaneAbove(double x) {
        return (int) Math.ceil(x - 0.5d);
    }

    private final int criticalPlaneBelow(double x) {
        return (int) Math.floor(x - 0.5d);
    }

    private final int findResultByJ(double hueRadians, double chroma, double y) {
        double dSqrt = Math.sqrt(y) * 11.0d;
        Frame frame = Frame.INSTANCE.getDefault();
        double d = 1.0d;
        double dPow = 1.0d / Math.pow(1.64d - Math.pow(0.29d, frame.getN()), 0.73d);
        double d2 = 2.0d;
        double dCos = (Math.cos(hueRadians + 2.0d) + 3.8d) * 0.25d * 3846.153846153846d * ((double) frame.getNc()) * ((double) frame.getNcb());
        double dSin = Math.sin(hueRadians);
        double dCos2 = Math.cos(hueRadians);
        int i = 0;
        while (i < 5) {
            double d3 = d;
            double d4 = dSqrt / 100.0d;
            double d5 = d2;
            double d6 = dSqrt;
            double dPow2 = Math.pow(((chroma == 0.0d || dSqrt == 0.0d) ? 0.0d : chroma / Math.sqrt(d4)) * dPow, 1.1111111111111112d);
            double aw = (((double) frame.getAw()) * Math.pow(d4, (d3 / ((double) frame.getC())) / ((double) frame.getZ()))) / ((double) frame.getNbb());
            double d7 = (((0.305d + aw) * 23.0d) * dPow2) / (((23.0d * dCos) + ((11.0d * dPow2) * dCos2)) + ((dPow2 * 108.0d) * dSin));
            double d8 = d7 * dCos2;
            double d9 = d7 * dSin;
            double d10 = aw * 460.0d;
            double d11 = ((d10 + (451.0d * d8)) + (288.0d * d9)) / 1403.0d;
            double d12 = (d10 - (891.0d * d8)) - (261.0d * d9);
            Frame frame2 = frame;
            double d13 = ((d10 - (d8 * 220.0d)) - (d9 * 6300.0d)) / 1403.0d;
            double dInverseChromaticAdaptation = inverseChromaticAdaptation(d11);
            double dInverseChromaticAdaptation2 = inverseChromaticAdaptation(d12 / 1403.0d);
            double dInverseChromaticAdaptation3 = inverseChromaticAdaptation(d13);
            double[][] dArr = LINRGB_FROM_SCALED_DISCOUNT;
            double[] dArr2 = dArr[0];
            double d14 = (dArr2[0] * dInverseChromaticAdaptation) + (dArr2[1] * dInverseChromaticAdaptation2) + (dArr2[2] * dInverseChromaticAdaptation3);
            double[] dArr3 = dArr[1];
            double d15 = (dArr3[0] * dInverseChromaticAdaptation) + (dArr3[1] * dInverseChromaticAdaptation2) + (dArr3[2] * dInverseChromaticAdaptation3);
            double[] dArr4 = dArr[2];
            double d16 = (dInverseChromaticAdaptation * dArr4[0]) + (dInverseChromaticAdaptation2 * dArr4[1]) + (dInverseChromaticAdaptation3 * dArr4[2]);
            if (d14 >= 0.0d && d15 >= 0.0d && d16 >= 0.0d) {
                double[] dArr5 = Y_FROM_LINRGB;
                double d17 = (dArr5[0] * d14) + (dArr5[1] * d15) + (dArr5[2] * d16);
                if (d17 > 0.0d) {
                    if (i != 4) {
                        double d18 = d17 - y;
                        if (Math.abs(d18) >= 0.002d) {
                            dSqrt = d6 - ((d18 * d6) / (d5 * d17));
                            i++;
                            d = d3;
                            d2 = d5;
                            frame = frame2;
                        }
                    }
                    if (d14 > 100.01d || d15 > 100.01d || d16 > 100.01d) {
                        break;
                        break;
                        break;
                    }
                    return CamUtils.INSTANCE.argbFromLinrgbComponents(d14, d15, d16);
                }
                return 0;
            }
            break;
        }
        return 0;
    }

    private final double hueOf(double[] linrgb) {
        double[][] dArr = SCALED_DISCOUNT_FROM_LINRGB;
        double d = linrgb[0];
        double[] dArr2 = dArr[0];
        double d2 = dArr2[0] * d;
        double d3 = linrgb[1];
        double d4 = d2 + (dArr2[1] * d3);
        double d5 = linrgb[2];
        double d6 = d4 + (dArr2[2] * d5);
        double[] dArr3 = dArr[1];
        double d7 = (dArr3[0] * d) + (dArr3[1] * d3) + (dArr3[2] * d5);
        double[] dArr4 = dArr[2];
        double d8 = (d * dArr4[0]) + (d3 * dArr4[1]) + (d5 * dArr4[2]);
        double dChromaticAdaptation = chromaticAdaptation(d6);
        double dChromaticAdaptation2 = chromaticAdaptation(d7);
        double dChromaticAdaptation3 = chromaticAdaptation(d8);
        return Math.atan2(((dChromaticAdaptation + dChromaticAdaptation2) - (dChromaticAdaptation3 * 2.0d)) / 9.0d, (((dChromaticAdaptation * 11.0d) + ((-12.0d) * dChromaticAdaptation2)) + dChromaticAdaptation3) / 11.0d);
    }

    private final double intercept(double source, double mid, double target) {
        return target == source ? target : (mid - source) / (target - source);
    }

    private final double inverseChromaticAdaptation(double adapted) {
        double dAbs = Math.abs(adapted);
        return ((double) CamUtils.INSTANCE.signum(adapted)) * Math.pow(Math.max(0.0d, (27.13d * dAbs) / (400.0d - dAbs)), 2.380952380952381d);
    }

    private final boolean isBounded(double x) {
        return 0.0d <= x && x <= 100.0d;
    }

    private final double[] lerpPoint(double[] source, double t, double[] target) {
        double d = source[0];
        double d2 = d + ((target[0] - d) * t);
        double d3 = source[1];
        double d4 = d3 + ((target[1] - d3) * t);
        double d5 = source[2];
        return new double[]{d2, d4, d5 + ((target[2] - d5) * t)};
    }

    private final double[] nthVertex(double y, int n) {
        double[] dArr = Y_FROM_LINRGB;
        double d = dArr[0];
        double d2 = dArr[1];
        double d3 = dArr[2];
        double d4 = n % 4 <= 1 ? 0.0d : 100.0d;
        double d5 = n % 2 == 0 ? 0.0d : 100.0d;
        if (n < 4) {
            double d6 = ((y - (d2 * d4)) - (d3 * d5)) / d;
            return isBounded(d6) ? new double[]{d6, d4, d5} : new double[]{-1.0d, -1.0d, -1.0d};
        }
        if (n < 8) {
            double d7 = ((y - (d * d5)) - (d3 * d4)) / d2;
            return isBounded(d7) ? new double[]{d5, d7, d4} : new double[]{-1.0d, -1.0d, -1.0d};
        }
        double d8 = ((y - (d * d4)) - (d2 * d5)) / d3;
        return isBounded(d8) ? new double[]{d4, d5, d8} : new double[]{-1.0d, -1.0d, -1.0d};
    }

    private final double sanitizeDegreesDouble(double degrees) {
        double d = degrees % 360.0d;
        return d < 0.0d ? d + 360.0d : d;
    }

    private final double sanitizeRadians(double angle) {
        return (angle + 25.132741228718345d) % 6.283185307179586d;
    }

    private final double[] setCoordinate(double[] source, double coordinate, double[] target, int axis) {
        return lerpPoint(source, intercept(source[axis], coordinate, target[axis]), target);
    }

    private final double trueDelinearized(double rgbComponent) {
        double d = rgbComponent / 100.0d;
        return (d <= 0.0031308d ? d * 12.92d : (Math.pow(d, 0.4166666666666667d) * 1.055d) - 0.055d) * 255.0d;
    }

    public final int solveToInt(double hueDegrees, double chroma, double lstar) {
        if (chroma < 1.0E-4d || lstar < 1.0E-4d || lstar > 99.9999d) {
            return CamUtils.INSTANCE.argbFromLstar(lstar);
        }
        double radians = Math.toRadians(sanitizeDegreesDouble(hueDegrees));
        double dYFromLstar = CamUtils.INSTANCE.yFromLstar(lstar);
        int iFindResultByJ = findResultByJ(radians, chroma, dYFromLstar);
        return iFindResultByJ != 0 ? iFindResultByJ : bisectToLimit(dYFromLstar, radians);
    }
}
