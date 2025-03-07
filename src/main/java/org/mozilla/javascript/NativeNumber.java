/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

/**
 * This class implements the Number native object.
 * <p>
 * See ECMA 15.7.
 *
 * @author Norris Boyd
 */
final class NativeNumber extends IdScriptableObject {
    private static final long serialVersionUID = 3504516769741512101L;

    /**
     * @see https://www.ecma-international.org/ecma-262/6.0/#sec-number.max_safe_integer
     */
    public static final double MAX_SAFE_INTEGER = 9007199254740991.0; // Math.pow(2, 53) - 1

    private static final Object NUMBER_TAG = "Number";

    private static final int MAX_PRECISION = 100;
    private static final double MIN_SAFE_INTEGER = -MAX_SAFE_INTEGER;

    static void init(Scriptable scope, boolean sealed) {
        NativeNumber obj = new NativeNumber(0.0);
        obj.exportAsJSClass(MAX_PROTOTYPE_ID, scope, sealed);
    }

    NativeNumber(double number) {
        doubleValue = number;
    }

    @Override
    public String getClassName() {
        return "Number";
    }

    @Override
    protected void fillConstructorProperties(IdFunctionObject ctor) {
        final int attr = ScriptableObject.DONTENUM |
                ScriptableObject.PERMANENT |
                ScriptableObject.READONLY;

        ctor.defineProperty("NaN", ScriptRuntime.NaNobj, attr);
        ctor.defineProperty("POSITIVE_INFINITY",
                ScriptRuntime.wrapNumber(Double.POSITIVE_INFINITY),
                attr);
        ctor.defineProperty("NEGATIVE_INFINITY",
                ScriptRuntime.wrapNumber(Double.NEGATIVE_INFINITY),
                attr);
        ctor.defineProperty("MAX_VALUE",
                ScriptRuntime.wrapNumber(Double.MAX_VALUE),
                attr);
        ctor.defineProperty("MIN_VALUE",
                ScriptRuntime.wrapNumber(Double.MIN_VALUE),
                attr);
        ctor.defineProperty("MAX_SAFE_INTEGER",
                ScriptRuntime.wrapNumber(MAX_SAFE_INTEGER),
                attr);
        ctor.defineProperty("MIN_SAFE_INTEGER",
                ScriptRuntime.wrapNumber(MIN_SAFE_INTEGER),
                attr);

        addIdFunctionProperty(ctor, NUMBER_TAG, ConstructorId_isFinite, "isFinite", 1);
        addIdFunctionProperty(ctor, NUMBER_TAG, ConstructorId_isNaN, "isNaN", 1);
        addIdFunctionProperty(ctor, NUMBER_TAG, ConstructorId_isInteger, "isInteger", 1);
        addIdFunctionProperty(ctor, NUMBER_TAG, ConstructorId_isSafeInteger, "isSafeInteger", 1);
        addIdFunctionProperty(ctor, NUMBER_TAG, ConstructorId_parseFloat, "parseFloat", 1);
        addIdFunctionProperty(ctor, NUMBER_TAG, ConstructorId_parseInt, "parseInt", 1);

        super.fillConstructorProperties(ctor);
    }

    @Override
    protected void initPrototypeId(int id) {
        String s;
        int arity;
        switch (id) {
            case Id_constructor:
                arity = 1;
                s = "constructor";
                break;
            case Id_toString:
                arity = 1;
                s = "toString";
                break;
            case Id_toLocaleString:
                arity = 1;
                s = "toLocaleString";
                break;
            case Id_toSource:
                arity = 0;
                s = "toSource";
                break;
            case Id_valueOf:
                arity = 0;
                s = "valueOf";
                break;
            case Id_toFixed:
                arity = 1;
                s = "toFixed";
                break;
            case Id_toExponential:
                arity = 1;
                s = "toExponential";
                break;
            case Id_toPrecision:
                arity = 1;
                s = "toPrecision";
                break;
            default:
                throw new IllegalArgumentException(String.valueOf(id));
        }
        initPrototypeMethod(NUMBER_TAG, id, s, arity);
    }

    @Override
    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope,
                             Scriptable thisObj, Object[] args) {
        if (!f.hasTag(NUMBER_TAG)) {
            return super.execIdCall(f, cx, scope, thisObj, args);
        }
        int id = f.methodId();
        if (id == Id_constructor) {
            double val = (args.length >= 1)
                    ? ScriptRuntime.toNumber(args[0]) : 0.0;
            if (thisObj == null) {
                // new Number(val) creates a new Number object.
                return new NativeNumber(val);
            }
            // Number(val) converts val to a number value.
            return ScriptRuntime.wrapNumber(val);

        } else if (id < Id_constructor) {
            return execConstructorCall(id, args);
        }

        // The rest of Number.prototype methods require thisObj to be Number

        if (!(thisObj instanceof NativeNumber))
            throw incompatibleCallError(f);
        double value = ((NativeNumber) thisObj).doubleValue;

        switch (id) {

            case Id_toString:
            case Id_toLocaleString: {
                // toLocaleString is just an alias for toString for now
                int base = (args.length == 0 || args[0] == Undefined.instance)
                        ? 10 : ScriptRuntime.toInt32(args[0]);
                return ScriptRuntime.numberToString(value, base);
            }

            case Id_toSource:
                return "(new Number(" + ScriptRuntime.toString(value) + "))";

            case Id_valueOf:
                return ScriptRuntime.wrapNumber(value);

            case Id_toFixed:
                int precisionMin = cx.version < Context.VERSION_ES6 ? -20 : 0;
                return num_to(value, args, DToA.DTOSTR_FIXED, DToA.DTOSTR_FIXED, precisionMin, 0);

            case Id_toExponential: {
                // Handle special values before range check
                if (Double.isNaN(value)) {
                    return "NaN";
                }
                if (Double.isInfinite(value)) {
                    if (value >= 0) {
                        return "Infinity";
                    }
                    return "-Infinity";
                }
                // General case
                return num_to(value, args, DToA.DTOSTR_STANDARD_EXPONENTIAL,
                        DToA.DTOSTR_EXPONENTIAL, 0, 1);
            }

            case Id_toPrecision: {
                // Undefined precision, fall back to ToString()
                if (args.length == 0 || args[0] == Undefined.instance) {
                    return ScriptRuntime.numberToString(value, 10);
                }
                // Handle special values before range check
                if (Double.isNaN(value)) {
                    return "NaN";
                }
                if (Double.isInfinite(value)) {
                    if (value >= 0) {
                        return "Infinity";
                    }
                    return "-Infinity";
                }
                return num_to(value, args, DToA.DTOSTR_STANDARD,
                        DToA.DTOSTR_PRECISION, 1, 0);
            }

            default:
                throw new IllegalArgumentException(String.valueOf(id));
        }
    }

    private static Object execConstructorCall(int id, Object[] args) {
        switch (id) {
            case ConstructorId_isFinite:
                if ((args.length == 0) || (Undefined.instance == args[0])) {
                    return Boolean.FALSE;
                }
                if (args[0] instanceof Number) {
                    // Match ES6 polyfill, which only works for "number" types
                    return isFinite(args[0]);
                }
                return Boolean.FALSE;

            case ConstructorId_isNaN:
                if ((args.length == 0) || (Undefined.instance == args[0])) {
                    return Boolean.FALSE;
                }
                if (args[0] instanceof Number) {
                    return isNaN((Number) args[0]);
                }
                return Boolean.FALSE;

            case ConstructorId_isInteger:
                if ((args.length == 0) || (Undefined.instance == args[0])) {
                    return Boolean.FALSE;
                }
                if (args[0] instanceof Number) {
                    return Boolean.valueOf(isInteger((Number) args[0]));
                }
                return Boolean.FALSE;

            case ConstructorId_isSafeInteger:
                if ((args.length == 0) || (Undefined.instance == args[0])) {
                    return Boolean.FALSE;
                }
                if (args[0] instanceof Number) {
                    return Boolean.valueOf(isSafeInteger((Number) args[0]));
                }
                return Boolean.FALSE;

            case ConstructorId_parseFloat:
                return NativeGlobal.js_parseFloat(args);

            case ConstructorId_parseInt:
                return NativeGlobal.js_parseInt(args);

            default:
                throw new IllegalArgumentException(String.valueOf(id));
        }
    }

    @Override
    public String toString() {
        return ScriptRuntime.numberToString(doubleValue, 10);
    }

    private static String num_to(double val,
                                 Object[] args,
                                 int zeroArgMode, int oneArgMode,
                                 int precisionMin, int precisionOffset) {
        int precision;
        if (args.length == 0) {
            precision = 0;
            oneArgMode = zeroArgMode;
        } else {
            /* We allow a larger range of precision than
               ECMA requires; this is permitted by ECMA. */
            double p = ScriptRuntime.toInteger(args[0]);
            if (p < precisionMin || p > MAX_PRECISION) {
                String msg = ScriptRuntime.getMessage1(
                        "msg.bad.precision", ScriptRuntime.toString(args[0]));
                throw ScriptRuntime.rangeError(msg);
            }
            precision = ScriptRuntime.toInt32(p);
        }
        StringBuilder sb = new StringBuilder();
        DToA.JS_dtostr(sb, oneArgMode, precision + precisionOffset, val);
        return sb.toString();
    }

    static Object isFinite(Object val) {
        double d = ScriptRuntime.toNumber(val);
        Double nd = Double.valueOf(d);
        return ScriptRuntime.wrapBoolean(!nd.isInfinite() && !nd.isNaN());
    }

    private static Boolean isNaN(Number val) {
        if (val instanceof Double) {
            return Boolean.valueOf(((Double) val).isNaN());
        }

        double d = val.doubleValue();
        return Boolean.valueOf(Double.isNaN(d));
    }

    private static boolean isInteger(Number val) {
        if (val instanceof Double) {
            return isDoubleInteger((Double) val);
        }
        return isDoubleInteger(val.doubleValue());
    }

    private static boolean isDoubleInteger(Double d) {
        return !d.isInfinite() &&
                !d.isNaN() &&
                (Math.floor(d.doubleValue()) == d.doubleValue());
    }

    private static boolean isDoubleInteger(double d) {
        return !Double.isInfinite(d) &&
                !Double.isNaN(d) &&
                (Math.floor(d) == d);
    }

    private static boolean isSafeInteger(Number val) {
        if (val instanceof Double) {
            return isDoubleSafeInteger((Double) val);
        }
        return isDoubleSafeInteger(val.doubleValue());
    }

    private static boolean isDoubleSafeInteger(Double d) {
        return isDoubleInteger(d) &&
                (d.doubleValue() <= MAX_SAFE_INTEGER) &&
                (d.doubleValue() >= MIN_SAFE_INTEGER);
    }

    private static boolean isDoubleSafeInteger(double d) {
        return isDoubleInteger(d) &&
                (d <= MAX_SAFE_INTEGER) &&
                (d >= MIN_SAFE_INTEGER);
    }
// #string_id_map#

    @Override
    protected int findPrototypeId(String s) {
        int id;
// #generated# Last update: 2007-05-09 08:15:50 EDT
        L0:
        {
            id = 0;
            String X = null;
            int c;
            L:
            switch (s.length()) {
                case 7:
                    c = s.charAt(0);
                    if (c == 't') {
                        X = "toFixed";
                        id = Id_toFixed;
                    } else if (c == 'v') {
                        X = "valueOf";
                        id = Id_valueOf;
                    }
                    break L;
                case 8:
                    c = s.charAt(3);
                    if (c == 'o') {
                        X = "toSource";
                        id = Id_toSource;
                    } else if (c == 't') {
                        X = "toString";
                        id = Id_toString;
                    }
                    break L;
                case 11:
                    c = s.charAt(0);
                    if (c == 'c') {
                        X = "constructor";
                        id = Id_constructor;
                    } else if (c == 't') {
                        X = "toPrecision";
                        id = Id_toPrecision;
                    }
                    break L;
                case 13:
                    X = "toExponential";
                    id = Id_toExponential;
                    break L;
                case 14:
                    X = "toLocaleString";
                    id = Id_toLocaleString;
                    break L;
            }
            if (X != null && X != s && !X.equals(s)) id = 0;
            break L0;
        }
// #/generated#
        return id;
    }

    private static final int
            ConstructorId_isFinite = -1,
            ConstructorId_isNaN = -2,
            ConstructorId_isInteger = -3,
            ConstructorId_isSafeInteger = -4,
            ConstructorId_parseFloat = -5,
            ConstructorId_parseInt = -6,

    Id_constructor = 1,
            Id_toString = 2,
            Id_toLocaleString = 3,
            Id_toSource = 4,
            Id_valueOf = 5,
            Id_toFixed = 6,
            Id_toExponential = 7,
            Id_toPrecision = 8,
            MAX_PROTOTYPE_ID = 8;

// #/string_id_map#

    private final double doubleValue;
}
