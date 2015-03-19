Liferay.Service.register("Liferay.Service.content", "pt.impresa.liferay.content.service", "service-content-portlet");

Liferay.Service.registerClass(
	Liferay.Service.content, "ImpresaContent",
	{
		getContentsJSON: true,
		getContents: true,
		getNewsletterURl: true
	}
);