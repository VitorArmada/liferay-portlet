package pt.impresa.liferay.content.service;

import com.liferay.portal.service.InvokableService;


public class ImpresaContentServiceClp implements ImpresaContentService {
    private InvokableService _invokableService;
    private String _methodName0;
    private String[] _methodParameterTypes0;
    private String _methodName1;
    private String[] _methodParameterTypes1;
    private String _methodName3;
    private String[] _methodParameterTypes3;
    private String _methodName4;
    private String[] _methodParameterTypes4;
    private String _methodName5;
    private String[] _methodParameterTypes5;

    public ImpresaContentServiceClp(InvokableService invokableService) {
        _invokableService = invokableService;

        _methodName0 = "getBeanIdentifier";

        _methodParameterTypes0 = new String[] {  };

        _methodName1 = "setBeanIdentifier";

        _methodParameterTypes1 = new String[] { "java.lang.String" };

        _methodName3 = "getContentsJSON";

        _methodParameterTypes3 = new String[] {
                "java.lang.String", "java.lang.String", "boolean",
                "java.lang.String", "int", "java.util.List", "java.util.List",
                "java.lang.Integer", "java.lang.String", "java.util.List"
            };

        _methodName4 = "getContents";

        _methodParameterTypes4 = new String[] {
                "java.lang.String", "java.lang.String", "boolean",
                "java.lang.String", "int", "java.util.List", "java.util.List",
                "java.lang.Integer", "java.lang.String", "java.util.List"
            };

        _methodName5 = "getNewsletterURl";

        _methodParameterTypes5 = new String[] { "java.lang.String" };
    }

    public java.lang.String getBeanIdentifier() {
        Object returnObj = null;

        try {
            returnObj = _invokableService.invokeMethod(_methodName0,
                    _methodParameterTypes0, new Object[] {  });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.String) ClpSerializer.translateOutput(returnObj);
    }

    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        try {
            _invokableService.invokeMethod(_methodName1,
                _methodParameterTypes1,
                new Object[] { ClpSerializer.translateInput(beanIdentifier) });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
    }

    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        throw new UnsupportedOperationException();
    }

    public java.lang.String getContentsJSON(java.lang.String publication,
        java.lang.String section, boolean includeSubsections,
        java.lang.String groupId, int maxArticles,
        java.util.List<java.lang.String> contentTypes,
        java.util.List<java.lang.String> fields, java.lang.Integer page,
        java.lang.String searchKeywords,
        java.util.List<java.lang.String> relations) throws java.io.IOException {
        Object returnObj = null;

        try {
            returnObj = _invokableService.invokeMethod(_methodName3,
                    _methodParameterTypes3,
                    new Object[] {
                        ClpSerializer.translateInput(publication),
                        
                    ClpSerializer.translateInput(section),
                        
                    includeSubsections,
                        
                    ClpSerializer.translateInput(groupId),
                        
                    maxArticles,
                        
                    ClpSerializer.translateInput(contentTypes),
                        
                    ClpSerializer.translateInput(fields),
                        
                    ClpSerializer.translateInput(page),
                        
                    ClpSerializer.translateInput(searchKeywords),
                        
                    ClpSerializer.translateInput(relations)
                    });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof java.io.IOException) {
                throw (java.io.IOException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.String) ClpSerializer.translateOutput(returnObj);
    }

    public pt.impresa.liferay.content.service.model.GetContentResponse getContents(
        java.lang.String publication, java.lang.String section,
        boolean includeSubsections, java.lang.String groupId, int maxArticles,
        java.util.List<java.lang.String> contentTypes,
        java.util.List<java.lang.String> fields, java.lang.Integer page,
        java.lang.String searchKeywords,
        java.util.List<java.lang.String> relations) throws java.io.IOException {
        Object returnObj = null;

        try {
            returnObj = _invokableService.invokeMethod(_methodName4,
                    _methodParameterTypes4,
                    new Object[] {
                        ClpSerializer.translateInput(publication),
                        
                    ClpSerializer.translateInput(section),
                        
                    includeSubsections,
                        
                    ClpSerializer.translateInput(groupId),
                        
                    maxArticles,
                        
                    ClpSerializer.translateInput(contentTypes),
                        
                    ClpSerializer.translateInput(fields),
                        
                    ClpSerializer.translateInput(page),
                        
                    ClpSerializer.translateInput(searchKeywords),
                        
                    ClpSerializer.translateInput(relations)
                    });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof java.io.IOException) {
                throw (java.io.IOException) t;
            }

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (pt.impresa.liferay.content.service.model.GetContentResponse) ClpSerializer.translateOutput(returnObj);
    }

    public java.lang.String getNewsletterURl(java.lang.String code) {
        Object returnObj = null;

        try {
            returnObj = _invokableService.invokeMethod(_methodName5,
                    _methodParameterTypes5,
                    new Object[] { ClpSerializer.translateInput(code) });
        } catch (Throwable t) {
            t = ClpSerializer.translateThrowable(t);

            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.String) ClpSerializer.translateOutput(returnObj);
    }
}
