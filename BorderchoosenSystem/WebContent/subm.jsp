<%@page contentType="text/html;charset=gb2312"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>�ļ��ϴ�</title>
</head>
 
<body>
<p>&nbsp;</p>
<p align="center">�ϴ��ļ�ѡ��</p>
<FORM METHOD="POST" ACTION="upload.action" ENCTYPE="multipart/form-data">
   <input type="hidden" name="TEST" value="good">
  <table width="75%" border="1" align="center">
    <tr> 
      <td><div align="center">1�� 
          <input type="FILE" name="FILE1" size="30">
        </div></td>
    </tr>
    <tr> 
      <td><div align="center">2�� 
          <input type="FILE" name="FILE2" size="30">
        </div></td>
    </tr>
    <tr> 
      <td><div align="center">3�� 
          <input type="FILE" name="FILE3" size="30">
        </div></td>
    </tr>
    <tr> 
      <td><div align="center">
          <input type="submit" name="Submit" value="�ϴ�����">
        </div></td>
    </tr>
  </table>
</FORM>
</body>
</html>
