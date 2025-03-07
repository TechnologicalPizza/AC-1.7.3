package io.github.ryuu.adventurecraft.scripting;


import net.minecraft.util.io.CompoundTag;
import org.mozilla.javascript.Scriptable;

public class ScopeTag {
    public static CompoundTag getTagFromScope(Scriptable scope) {
        CompoundTag tag = new CompoundTag();
        for (Object id : scope.getIds()) {
            if (id instanceof String) {
                String key = (String) id;
                Object value = scope.get(key, scope);
                saveProperty(tag, key, value);
            }
        }
        return tag;
    }

    private static void saveProperty(CompoundTag tag, String key, Object value) {
        if (value instanceof String) {
            String strValue = (String) value;
            tag.a("String_" + key, strValue);
        } else if (value instanceof Boolean) {
            boolean bValue = ((Boolean) value).booleanValue();
            tag.a("Boolean_" + key, bValue);
        } else if (value instanceof Number) {
            Number nValue = (Number) value;
            double dValue = nValue.doubleValue();
            float fValue = nValue.floatValue();
            long lValue = nValue.longValue();
            int iValue = nValue.intValue();
            short sValue = nValue.shortValue();
            if (dValue != fValue) {
                tag.a("Double_" + key, dValue);
            } else if (fValue != (float) lValue) {
                tag.a("Float_" + key, fValue);
            } else if (lValue != iValue) {
                tag.a("Long_" + key, lValue);
            } else if (iValue != sValue) {
                tag.a("Integer_" + key, iValue);
            } else {
                tag.a("Short_" + key, sValue);
            }
        }
    }

    public static void loadScopeFromTag(Scriptable scope, CompoundTag tag) {
        for (String varKey : tag.getKeys()) {
            String[] parts = varKey.split("_", 2);
            if (parts.length != 2) {
                System.out.printf("Unknown key in tag: %s %d\n", varKey, Integer.valueOf(parts.length));
                continue;
            }
            String type = parts[0];
            String key = parts[1];
            if (type.equals("String")) {
                String value = tag.i(varKey);
                scope.put(key, scope, value);
                continue;
            }
            if (type.equals("Boolean")) {
                boolean value = tag.m(varKey);
                scope.put(key, scope, new Boolean(value));
                continue;
            }
            if (type.equals("Double")) {
                double value = tag.h(varKey);
                scope.put(key, scope, new Double(value));
                continue;
            }
            if (type.equals("Float")) {
                float value = tag.g(varKey);
                scope.put(key, scope, new Float(value));
                continue;
            }
            if (type.equals("Long")) {
                long value = tag.f(varKey);
                scope.put(key, scope, new Long(value));
                continue;
            }
            if (type.equals("Integer")) {
                int value = tag.e(varKey);
                scope.put(key, scope, new Integer(value));
                continue;
            }
            if (type.equals("Short")) {
                short value = tag.d(varKey);
                scope.put(key, scope, new Short(value));
                continue;
            }
            System.out.printf("Unknown type: %s\n", type);
        }
    }
}
