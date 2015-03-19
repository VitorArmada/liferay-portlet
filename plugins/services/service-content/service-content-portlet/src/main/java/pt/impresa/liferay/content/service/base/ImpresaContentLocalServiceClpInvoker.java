package pt.impresa.liferay.content.service.base;

import pt.impresa.liferay.content.service.ImpresaContentLocalServiceUtil;

import java.util.Arrays;


public class ImpresaContentLocalServiceClpInvoker {
    private String _methodName20;
    private String[] _methodParameterTypes20;
    private String _methodName21;
    private String[] _methodParameterTypes21;
    private String _methodName24;
    private String[] _methodParameterTypes24;
    private String _methodName25;
    private String[] _methodParameterTypes25;
    private String _methodName26;
    private String[] _methodParameterTypes26;
    private String _methodName27;
    private String[] _methodParameterTypes27;
    private String _methodName28;
    private String[] _methodParameterTypes28;

    public ImpresaContentLocalServiceClpInvoker() {
        _methodName20 = "getBeanIdentifier";

        _methodParameterTypes20 = new String[] {  };

        _methodName21 = "setBeanIdentifier";

        _methodParameterTypes21 = new String[] { "java.lang.String" };

        _methodName24 = "getContentsJSON";

        _methodParameterTypes24 = new String[] {
                "java.lang.String", "java.lang.String", "boolean",
                "java.lang.String", "int", "java.util.List", "java.util.List",
                "java.lang.Integer", "java.lang.String", "java.util.List"
            };

        _methodName25 = "getContents";

        _methodParameterTypes25 = new String[] {
                "java.lang.String", "java.lang.String", "boolean",
                "java.lang.String", "int", "java.util.List", "java.util.List",
                "java.lang.Integer", "java.lang.String", "java.util.List"
            };

        _methodName26 = "getExternalContentDetailsJSON";

        _methodParameterTypes26 = new String[] {
                "java.lang.String", "java.lang.String", "java.lang.String",
                "java.util.List", "java.util.List", "java.util.List"
            };

        _methodName27 = "getContentDetailsJSON";

        _methodParameterTypes27 = new String[] {
                "java.lang.String", "java.lang.String", "java.util.List",
                "java.util.List", "java.util.List"
            };

        _methodName28 = "getRelatedContentsJSON";

        _methodParameterTypes28 = new String[] {
                "java.lang.String", "java.lang.String", "java.lang.Integer",
                "java.lang.Integer", "java.util.List", "java.util.List",
                "java.util.List"
            };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName20.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes20, parameterTypes)) {
            return ImpresaContentLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName21.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes21, parameterTypes)) {
            ImpresaContentLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);
        }

        if (_methodName24.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes24, parameterTypes)) {
            return ImpresaContentLocalServiceUtil.getContentsJSON((java.lang.String) arguments[0],
                (java.lang.String) arguments[1],
                ((Boolean) arguments[2]).booleanValue(),
                (java.lang.String) arguments[3],
                ((Integer) arguments[4]).intValue(),
                (java.util.List<java.lang.String>) arguments[5],
                (java.util.List<java.lang.String>) arguments[6],
                (java.lang.Integer) arguments[7],
                (java.lang.String) arguments[8],
                (java.util.List<java.lang.String>) arguments[9]);
        }

        if (_methodName25.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes25, parameterTypes)) {
            return ImpresaContentLocalServiceUtil.getContents((java.lang.String) arguments[0],
                (java.lang.String) arguments[1],
                ((Boolean) arguments[2]).booleanValue(),
                (java.lang.String) arguments[3],
                ((Integer) arguments[4]).intValue(),
                (java.util.List<java.lang.String>) arguments[5],
                (java.util.List<java.lang.String>) arguments[6],
                (java.lang.Integer) arguments[7],
                (java.lang.String) arguments[8],
                (java.util.List<java.lang.String>) arguments[9]);
        }

        if (_methodName26.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes26, parameterTypes)) {
            return ImpresaContentLocalServiceUtil.getExternalContentDetailsJSON((java.lang.String) arguments[0],
                (java.lang.String) arguments[1],
                (java.lang.String) arguments[2],
                (java.util.List<java.lang.String>) arguments[3],
                (java.util.List<java.lang.String>) arguments[4],
                (java.util.List<java.lang.String>) arguments[5]);
        }

        if (_methodName27.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes27, parameterTypes)) {
            return ImpresaContentLocalServiceUtil.getContentDetailsJSON((java.lang.String) arguments[0],
                (java.lang.String) arguments[1],
                (java.util.List<java.lang.String>) arguments[2],
                (java.util.List<java.lang.String>) arguments[3],
                (java.util.List<java.lang.String>) arguments[4]);
        }

        if (_methodName28.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes28, parameterTypes)) {
            return ImpresaContentLocalServiceUtil.getRelatedContentsJSON((java.lang.String) arguments[0],
                (java.lang.String) arguments[1],
                (java.lang.Integer) arguments[2],
                (java.lang.Integer) arguments[3],
                (java.util.List<java.lang.String>) arguments[4],
                (java.util.List<java.lang.String>) arguments[5],
                (java.util.List<java.lang.String>) arguments[6]);
        }

        throw new UnsupportedOperationException();
    }
}
