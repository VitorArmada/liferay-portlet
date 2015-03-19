package pt.impresa.liferay.content.service;

import com.liferay.portal.service.InvokableLocalService;


public class ImpresaContentLocalServiceClp implements ImpresaContentLocalService {
    private InvokableLocalService _invokableLocalService;
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
    private String _methodName6;
    private String[] _methodParameterTypes6;
    private String _methodName7;
    private String[] _methodParameterTypes7;

    public ImpresaContentLocalServiceClp(
        InvokableLocalService invokableLocalService) {
        _invokableLocalService = invokableLocalService;

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

        _methodName5 = "getExternalContentDetailsJSON";

        _methodParameterTypes5 = new String[] {
                "java.lang.String", "java.lang.String", "java.lang.String",
                "java.util.List", "java.util.List", "java.util.List"
            };

        _methodName6 = "getContentDetailsJSON";

        _methodParameterTypes6 = new String[] {
                "java.lang.String", "java.lang.String", "java.util.List",
                "java.util.List", "java.util.List"
            };

        _methodName7 = "getRelatedContentsJSON";

        _methodParameterTypes7 = new String[] {
                "java.lang.String", "java.lang.String", "java.lang.Integer",
                "java.lang.Integer", "java.util.List", "java.util.List",
                "java.util.List"
            };
    }

    public java.lang.String getBeanIdentifier() {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName0,
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
            _invokableLocalService.invokeMethod(_methodName1,
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
            returnObj = _invokableLocalService.invokeMethod(_methodName3,
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
            returnObj = _invokableLocalService.invokeMethod(_methodName4,
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

    public java.lang.String getExternalContentDetailsJSON(
        java.lang.String publication, java.lang.String articleId,
        java.lang.String externalSystemId,
        java.util.List<java.lang.String> contentTypes,
        java.util.List<java.lang.String> fields,
        java.util.List<java.lang.String> relations) throws java.io.IOException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName5,
                    _methodParameterTypes5,
                    new Object[] {
                        ClpSerializer.translateInput(publication),
                        
                    ClpSerializer.translateInput(articleId),
                        
                    ClpSerializer.translateInput(externalSystemId),
                        
                    ClpSerializer.translateInput(contentTypes),
                        
                    ClpSerializer.translateInput(fields),
                        
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

    public java.lang.String getContentDetailsJSON(
        java.lang.String publication, java.lang.String articleId,
        java.util.List<java.lang.String> contentTypes,
        java.util.List<java.lang.String> fields,
        java.util.List<java.lang.String> relations) throws java.io.IOException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName6,
                    _methodParameterTypes6,
                    new Object[] {
                        ClpSerializer.translateInput(publication),
                        
                    ClpSerializer.translateInput(articleId),
                        
                    ClpSerializer.translateInput(contentTypes),
                        
                    ClpSerializer.translateInput(fields),
                        
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

    public java.lang.String getRelatedContentsJSON(
        java.lang.String publication, java.lang.String articleId,
        java.lang.Integer maxArticles, java.lang.Integer page,
        java.util.List<java.lang.String> fields,
        java.util.List<java.lang.String> contentTypes,
        java.util.List<java.lang.String> relations) throws java.io.IOException {
        Object returnObj = null;

        try {
            returnObj = _invokableLocalService.invokeMethod(_methodName7,
                    _methodParameterTypes7,
                    new Object[] {
                        ClpSerializer.translateInput(publication),
                        
                    ClpSerializer.translateInput(articleId),
                        
                    ClpSerializer.translateInput(maxArticles),
                        
                    ClpSerializer.translateInput(page),
                        
                    ClpSerializer.translateInput(fields),
                        
                    ClpSerializer.translateInput(contentTypes),
                        
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
}
