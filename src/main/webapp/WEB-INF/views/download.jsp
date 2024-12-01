<%@ page import="java.io.FileInputStream" %><%--
  Created by IntelliJ IDEA.
  User: hmkan
  Date: 24. 11. 19.
  Time: 오전 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String file = request.getParameter("file");

    String realPath = request.getServletContext().getRealPath("./upload");
    String sFilePath = realPath + "/" + file;
    FileInputStream in =new FileInputStream(sFilePath);
    String sMimeType = request.getServletContext().getMimeType(sFilePath);

    if (sMimeType == null) {
        sMimeType = "application/octet-stream";
    }

    response.setContentType(sMimeType);
    response.setHeader("Content-Disposition", "attachment; filename=" + file);
    ServletOutputStream file1 = response.getOutputStream();
    int numRead;
    byte[] b = new byte[4096];

    while ((numRead = in.read(b, 0, b.length)) != -1) {
        file1.write(b, 0, numRead);
    }
    file1.flush();
    file1.close();
    in.close();
%>

