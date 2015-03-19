<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>
	<body style="margin:0; padding:0">
		<script type='text/javascript'>
			<% String zoneId = request.getParameter("zoneId"); %>
			
			var m3_u = (location.protocol == 'https:' ? 'https://pub.sapo.pt/ajs.php' : 'http://pub.sapo.pt/ajs.php');
			var m3_r = Math.floor(Math.random() * 99999999999);
			if (!document.MAX_used) document.MAX_used = ',';
			document.write("<scr" + "ipt type='text/javascript' src='" + m3_u);
			document.write('?zoneid=<%= zoneId %>');
			document.write('&cb=' + m3_r);
			if (document.MAX_used != ',') document.write("&exclude=" + document.MAX_used);
			document.write(document.charset ? '&charset=' + document.charset : (document.characterSet ? '&charset=' + document.characterSet : ''));
			document.write("&loc=" + escape(window.location));
			if (document.referrer) document.write("&referer=" + escape(document.referrer));
			if (document.context) document.write("&context=" + escape(document.context));
			if (document.mmm_fo) document.write("&;mmm_fo=1");
			document.write("'><\/scr" + "ipt>");
		</script>
		<noscript>
			<a href='http://pub.sapo.pt/ck.php?n=ae99405b&cb=INSERT_RANDOM_NUMBER_HERE' target='_blank'>
				<img src='http://pub.sapo.pt/avw.php?zoneid=<%= zoneId %>&cb=INSERT_RANDOM_NUMBER_HERE&n=ae99405b' border='0' alt='' />
			</a>
		</noscript>
	</body>
</html>
