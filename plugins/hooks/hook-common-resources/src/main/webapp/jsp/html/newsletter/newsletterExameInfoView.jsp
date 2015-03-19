<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body style="margin:0px; padding:0px">
<style>
img {display:block;border: none;}
</style>
<table style="background:#000" width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="61" align="center">&nbsp;</td>
    <td width="660" rowspan="2" align="center"><table style="width: 661px;" width="660" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="200" rowspan="2" align="left" valign="top" style="color:#fff; font-family:Arial, Helvetica, sans-serif; font-size:13px"><a href="http://exameinformatica.sapo.pt/" target="_blank"><img src="http://images.cdn.impresa.pt/exameinformatica/2013-04-23-eilogo.gif" alt="Exame Inform&#225;tica" width="200" height="96" border="0" style="display:block" longdesc="http://exameinformatica.sapo.pt/" /></a></td>
          <td height="61" align="right" valign="middle" style="color:#fff; font-family:Arial, Helvetica, sans-serif; font-size:13px; padding-right:20px; padding-top:0">Se n&atilde;o conseguir visualizar esta newsletter correctamente <a href="http://exameinformatica.sapo.pt/common-resources-hook/newsletter?code=${code}" target="_blank" style="color: #12C1EC; font-family: Arial,Helvetica,sans-serif; font-size: 13px; text-decoration: underline;">clique aqui</a></td>
        </tr>
        <tr>
          <td align="right" valign="bottom" bgcolor="#FFFFFF" style="color:#000; font-family:Arial, Helvetica, sans-serif;  font-size:15px; text-transform:uppercase; font-weight:bolder"><table style="border-right:1px solid #DFE5E5" width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td>&nbsp;</td>
                <td align="center"><a style="color:#000; font-family: 'Arial Black', Arial, Helvetica, sans-serif;  font-size:12px; text-transform:uppercase; font-weight:bolder; text-decoration:none" href="http://exameinformatica.sapo.pt/noticias" target="_blank">noticias</a></td>
                <td align="center"><a style="color:#000; font-family: 'Arial Black', Arial, Helvetica, sans-serif;  font-size:12px; text-transform:uppercase; font-weight:bolder; text-decoration:none" href="http://exameinformatica.sapo.pt/melhores-apps" target="_blank">appS</a></td>
                <td align="center"><a style="color:#000; font-family: 'Arial Black', Arial, Helvetica, sans-serif;  font-size:12px; text-transform:uppercase; font-weight:bolder; text-decoration:none" href="http://exameinformatica.sapo.pt/videos" target="_blank">v&#205;deos</a></td>
                <td align="center"><a style="color:#000; font-family: 'Arial Black', Arial, Helvetica, sans-serif;  font-size:12px; text-transform:uppercase; font-weight:bolder; text-decoration:none" href="http://exameinformatica.sapo.pt/ofertas-de-emprego-ti" target="_blank">Empregos TI</a></td>
                <td align="center"><a style="color:#000; font-family: 'Arial Black', Arial, Helvetica, sans-serif;  font-size:12px; text-transform:uppercase; font-weight:bolder; text-decoration:none" href="http://www.assineja.pt/Detalhe/tabid/111/itemID/IF/IdTipoItem/1/Default.aspx" target="_blank">Assine j&#193;</a></td>
                <td height="35">&nbsp;</td>
              </tr>
            </table></td>
        </tr>
      </table></td>
    <td height="61" align="center">&nbsp;</td>
  </tr>
  <tr>
    <td height="30" align="center" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" align="center" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
</table>
<table style="background:#E6EAEB" align="center" cellpadding="0" cellspacing="0" class="newsletter_main">
  <tr>
    <td width="100%"><table style="border-left:1px solid #DFE5E5; border-right:1px solid #DFE5E5; background:#E6EAEB" width="661" align="center" cellpadding="0" cellspacing="0">
        <tbody>
          <tr>
            <td style="background:#FFF" width="100%" align="center"><table style="margin-bottom:40px" width="91%" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                  <tr>
                    <td style="padding-top:30px" align="left"></td>
                    <td width="300" align="right" valign="bottom" style="font-family:Arial, Helvetica, sans-serif; font-size:11px; text-transform:uppercase; color:#D1DEE5">PUB</td>
                  </tr>
                  <tr>
                    <td align="left" valign="top"><!--DESTAQUE-->
                      
                     <c:forEach items="${contents0}" var="exameInfoContent" >
                        <table width="278" border="0" cellpadding="0" cellspacing="0" style="border-top:1px dotted #D3E0E9">
                          <tbody>
                            <tr>
                              <td valign="top" ><a href="http://exameinformatica.sapo.pt${exameInfoContent.getString('sectionUrl')}/${exameInfoContent.getString('articleUrl')}" target="_blank"><img style="border:1px solid #DFE4E7; font-family:Arial, Helvetica, sans-serif; font-size:13px" src="${cdnurl}${publication0}${exameInfoContent.getString('imageSrc')}${w220h138}" alt="IMG" width="275" height="173" id="IMG" /></a></td>
                            </tr>
                            <tr>
                              <td height="25" valign="bottom" ><a href="http://exameinformatica.sapo.pt${exameInfoContent.getString('sectionUrl')}" target="_blank" style=" font-family:Tahoma, Helvetica, sans-serif; font-weight:bold; font-size:12px; color:#16C2EA; text-decoration:none; text-transform:uppercase; font-weight:bold">${exameInfoContent.getString("sectionName")}</a></td>
                            </tr>
                            <tr>
                              <td style="padding:5px 0"><a href="http://exameinformatica.sapo.pt${exameInfoContent.getString('sectionUrl')}/${exameInfoContent.getString('articleUrl')}" target="_blank" style="font-family: Tahoma,Helvetica,sans-serif; font-size: 15px; color:#000; text-decoration: none; font-weight:bold"> ${exameInfoContent.getString("title")}</a></td>
                            </tr>
                            <tr>
                              <td valign="top" >&nbsp;</td>
                            </tr>
                          </tbody>
                        </table>
                      </c:forEach>
                      
                      <!--FIM DESTAQUE--></td>
                    <td ><a href="http://pub.sapo.pt/ck.php?n=a653e4f4&amp;cb=[SAPO_RANDOM_NUMBER]&amp;OAID=[SAPO_HASH]&amp;kw=[SAPO_NEWSLETTER]" target="_blank"><img src="http://pub.sapo.pt/avw.php?zoneid=1612&amp;OAID=[SAPO_HASH]&amp;cb=[SAPO_RANDOM_NUMBER]&amp;n=a653e4f4" border="0" alt=""></a></td>
                  </tr>
                </tbody>
              </table>
              
              <!--INICIO LISTA-->
               <table width="91%" border="0" cellspacing="0" cellpadding="0">
<tr>
                  <td style="display: block; font-family:'Arial Black', Arial,sans-serif ;color: #00A6EB;text-transform: uppercase;cursor: auto; font-size:15px" align="left">&#218;LTIMAS</td>
                </tr>
</table>
              
              <c:forEach items="${contents1}" var="exameInfoContent" >
                <table style="border-top:1px dotted #D3E0E9" border="0" cellpadding="0" cellspacing="0" width="91%">
                  <tbody>
                    <tr>
                      <td valign="top" style="padding: 20px 0px;"><table border="0" cellpadding="0" cellspacing="0">
                          <tbody>
                            <tr>
                              <td rowspan="4" valign="top" ><a href="http://exameinformatica.sapo.pt${exameInfoContent.getString('sectionUrl')}/${exameInfoContent.getString('articleUrl')}" target="_blank"><img style="border:1px solid #DFE4E7; font-family:Arial, Helvetica, sans-serif; font-size:13px" src="${cdnurl}${publication1}${exameInfoContent.getString('imageSrc')}${w220h138}" alt="IMG" width="220" height="138" id="IMG2" /></a></td>
                              <td width="20" rowspan="4" valign="top" style="width:20px">&nbsp;</td>
                              <td height="20" valign="top"><a href="http://exameinformatica.sapo.pt${exameInfoContent.getString('sectionUrl')}" target="_blank" style=" font-family:Tahoma, Helvetica, sans-serif; font-weight:bold; font-size:12px; color:#16C2EA; text-decoration:none; text-transform:uppercase; font-weight:bold">${exameInfoContent.getString("sectionName")}</a></td>
                            </tr>
                            <tr>
                              <td valign="top" style="padding:5px 0"><a href="http://exameinformatica.sapo.pt${exameInfoContent.getString('sectionUrl')}/${exameInfoContent.getString('articleUrl')}" target="_blank" style="font-family: Tahoma,Helvetica,sans-serif; font-size: 15px; color:#000; text-decoration: none; font-weight:bold">${exameInfoContent.getString("title")}</a></td>
                            </tr>
                          </tbody>
                        </table></td>
                    </tr>
                  </tbody>
                </table>
              </c:forEach>
              <table width="0" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td><img src="http://images.cdn.impresa.pt/exameinformatica/2013-04-23-toplist.gif" alt="" width="599" height="24" /></td>
                </tr>
              </table>
              
              <!--FIM LISTA-->
              
              <!---->
              <table  width="0" border="0" cellspacing="0" cellpadding="0">
                                  <tr>
                                    <td style="border: 1px solid #E2E8EB;border-radius: 5px;background-image: -moz-linear-gradient(top,#FDFDFE,#EFF2F3 68%);background-image: -webkit-linear-gradient(top,#FDFDFE,#EFF2F3 68%);background-image: -o-linear-gradient(top,#FDFDFE,#EFF2F3 68%);background-image: -ms-linear-gradient(top,#FDFDFE,#EFF2F3 68%);background-image: linear-gradient(top,#FDFDFE,#EFF2F3 68%);background-color: #E6EAED;text-align: center;text-transform: uppercase;cursor:pointer;padding: 5px 20px; " ><a href="http://exameinformatica.sapo.pt/" target="_blank"  style="font-family: Arial, Helvetica, sans-serif;font-size: 15px;text-decoration: none;color: #648298;text-transform: uppercase;font-weight: bold;text-shadow: #C8D0D6 1px 0 0;">MAIS ARTIGOS</a></td>
                                  </tr>
                                </table>

              <!--CAPA-->
              
              <table width="91%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td style="display: block; font-family:'Arial Black', Arial,sans-serif ;color: #00A6EB;text-transform: uppercase;cursor: auto; font-size:15px" align="left">A N&#195;O PERDER</td>
                </tr>
              </table>
              <table width="0" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td><img src="http://images.cdn.impresa.pt/exameinformatica/2013-04-23-bottomlist.gif" alt="" width="599" height="24" /></td>
                </tr>
              </table>
              <c:forEach items="${contents2}" var="exameInfoContent" >
                <table style="border-top:1px dotted #D3E0E9" border="0" cellpadding="0" cellspacing="0" width="91%">
                  <tbody>
                    <tr>
                      <td valign="top" style="padding: 20px 0px;"><table width="100%" border="0" cellpadding="0" cellspacing="0">
                          <tbody>
                            <tr>
                              <td width="178" rowspan="6" valign="top" ><a href="http://exameinformatica.sapo.pt${exameInfoContent.getString('sectionUrl')}/${exameInfoContent.getString('articleUrl')}" target="_blank"><img style="border:1px solid #DFE4E7; font-family:Arial, Helvetica, sans-serif; font-size:13px" src="${cdnurl}${publication2}${exameInfoContent.getString('imageSrc')}${w220h335}" alt="IMG" width="140" height="213" id="IMG3" /></a></td>
                              <td rowspan="6" valign="top" style="width:20px">&nbsp;</td>
                              <td valign="top" style="padding:5px 0"><a href="http://exameinformatica.sapo.pt${exameInfoContent.getString('sectionUrl')}/${exameInfoContent.getString('articleUrl')}" target="_blank" style="font-family: Tahoma,Helvetica,sans-serif; font-size: 15px; color:#000; text-decoration: none; font-weight:bold">${exameInfoContent.getString("title")}</a></td>
                            </tr>
                            <tr>
                              <td height="10" valign="top">&nbsp;</td>
                            </tr>
                            <tr>
                              <td height="118" valign="top" style="padding:5px 0;font-family:Tahoma, Arial, Geneva, sans-serif; font-size:14px; color:#000">${exameInfoContent.getString("lead")} </td>
                            </tr>
                            <tr>
                              <td valign="top" style=" border:none"><table  width="0" border="0" cellspacing="0" cellpadding="0">
                                  <tr>
                                    <td style="border: 1px solid #E2E8EB;border-radius:6px;background-image: -moz-linear-gradient(top,#3DBBF0,#0190CC 68%);background-image: -webkit-linear-gradient(top,#3DBBF0,#0190CC 68%);background-image: -o-linear-gradient(top,#3DBBF0,#0190CC 68%);background-image: -ms-linear-gradient(top,#3DBBF0,#0190CC 68%);background-image: linear-gradient(top,#3DBBF0,#0190CC 68%);text-align: center;text-transform: uppercase;border: 1px solid #0086BF;letter-spacing: .5px;background-color: #3DBBF0; cursor:pointer;padding: 5px 20px; " ><a style="font-size: 15px;font-family:Arial, Helvetica, sans-serif;color: #FFF; text-decoration:none;font-weight: bold;" href="http://www.assineja.pt/Detalhe/tabid/111/itemID/IF/IdTipoItem/1/Default.aspx" target="_blank">Assine j&#193;</a></td>
                                  </tr>
                                </table></td>
                            </tr>
                          </tbody>
                        </table></td>
                    </tr>
                  </tbody>
                </table>
              </c:forEach>
              
              <!--FIM CAPA-->
              
              <p>&nbsp;</p></td>
          </tr>
        </tbody>
      </table>
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td bgcolor="#000000"><table width="625" cellspacing="10" style="text-align: center; margin: auto;">
              <tbody>
                <tr>
                  <td align="center" style="font-family:Arial, Helvetica, sans-serif; font-size:13px; color:#fff" >Siga-nos tamb&#233;m em:</td>
                </tr>
                <tr>
                  <td align="center" ><table width="0" border="0" cellspacing="0" cellpadding="10">
                      <tr>
                        <td width="30" ><a href="https://twitter.com/ExamInformatica" target="_blank"><img src="http://images.cdn.impresa.pt/exameinformatica/2013-04-23-twitter.gif" alt="facebook" width="29" height="32" border="0" /></a></td>
                        <td width="30" ><a href="http://www.facebook.com/exameinformatica" target="_blank"><img src="http://images.cdn.impresa.pt/exameinformatica/2013-04-23-facebook.gif" alt="facebook" width="29" height="32" border="0" /></a></td>
                        <td width="30" ><a href="http://feeds.feedburner.com/ExameInformatica-geral" target="_blank"><img src="http://images.cdn.impresa.pt/exameinformatica/2013-04-23-rss.gif" alt="facebook" width="29" height="32" border="0" /></a></td>
                      </tr>
                    </table></td>
                </tr>
                <tr>
                  <td ><a href="http://exameinformatica.mailings.impresa.pt/subscribe.php" target="_blank" style="font-family: Arial,Helvetica,sans-serif; font-size: 14px; color: #fff; text-decoration: none;"><strong>Como proceder para subscrever esta newsletter?</strong></a></td>
                </tr>
                <tr>
                  <td align="center" style="text-align:center"><span style=" padding:0px; color:#ffffff; font-family:Arial, Helvetica, sans-serif; font-size:13px; text-decoration:none">O
                    grupo Impresa pretende oferecer um servi&ccedil;o personalizado de qualidade, respeitando sempre o direito &agrave; sua privacidade. Os seus dados s&atilde;o tratados de forma confidencial, sendo utilizados apenas para envio de informa&#231;&atilde;o do grupo.<br />
                    <br />
                    Para deixar de receber esta newsletters, aceda &agrave; sua &aacute;rea pessoal e edite os seus dados de subscri&ccedil;&atilde;o <a href="[urloptout]" target="_blank" style="font-family:Verdana, Helvetica, sans-serif; font-size:13px;color: #12C1EC">clique aqui</a> </span></td>
                </tr>
              </tbody>
            </table></td>
        </tr>
      </table>
      <div style="visibility: hidden;">
<p style="display:none">[USERTRACK]</p> </div>
</td>
  </tr>
</table>
</body>
</html>
