<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>�ύ����</title>
<link rel="stylesheet" type="text/css" href="style.css" />
<script type="text/javascript" src="js/jquery-2.1.0.js"></script>
<script type="text/javascript" src="js/AjaxFileUploader/ajaxfileupload.js"></script>
<script type="text/javascript">

var loc = null;

function pSubmit() {
	var pTitle = document.getElementById("pTitle");
	var abstr = document.getElementById("pAbstract");
	var pCid = document.getElementById("pConf");
	var uId = document.getElementById("uri").value.replace("iprs/User/", "");
	var kywrd0 = document.getElementById("pKeyword0");
	var kywrd1 = document.getElementById("pKeyword1");
	var kywrd2 = document.getElementById("pKeyword2");
	var pAuthor0 = document.getElementById("pAuthor0");
	var pAuthor1 = document.getElementById("pAuthor1");
	var pAuthor2 = document.getElementById("pAuthor2");
	var pAuthor3 = document.getElementById("pAuthor3");
	$.post("PaperServlet",
			{
			title:pTitle.value,
			abstra:abstr.value,
			uid:uId,
			cid:pCid.value,
			author0:pAuthor0.value,
			author1:pAuthor1.value,
			author2:pAuthor2.value,
			author3:pAuthor3.value,
			keyword0:kywrd0.value,
			keyword1:kywrd1.value,
			keyword2:kywrd2.value,
			location:loc
			});
	}

function uploadFile() {
    $.ajaxFileUpload({
        url: "http://test.ldsink.com/api/File",
        secureuri: false,
        fileElementId: 'file',
        dataType: 'xml',
        success: function (data) {
        	var str = data.getElementsByTagName("string");
            var text = str[0].firstChild.nodeValue;
        	loc = text;
        	document.getElementById("file").value = loc;
        },
        error: function (data) {
            alert("�����ϴ�ʧ�ܣ�");
        },
    });
}

</script>
</head>
<body>
	<form enctype="multipart/form-data">�����ϴ�
		<input id="file" name="file" type="file" />
		<a href="javascript:uploadFile()">�ϴ�</a>
	</form>
	<form id="paperForm">
		<table>
			<tr>
				<td>���⡡����<input id="pTitle" type="text" class="pSubmit-Title"></td>
			</tr>
			<tr>
				<td>Ͷ����飺<input id="pConf" type="text" class="pSubmit-Title"></td>
			</tr>
			<tr>
				<td>��Ҫ������<textarea id="pAbstract" rows="5"
						class="pSubmit-Abstract"></textarea></td>
			</tr>
			<tr>
				<td>�ؼ���1 ��<input id="pKeyword0" type="text"
					class="pSubmit-Title"></td>
			</tr>
			<tr>
				<td>�ؼ���2 ��<input id="pKeyword1" type="text"
					class="pSubmit-Title"></td>
			</tr>
			<tr>
				<td>�ؼ���3 ��<input id="pKeyword2" type="text"
					class="pSubmit-Title"></td>
			</tr>
			<tr>
				<td>ͨѶ���ߣ�<input id="pAuthor0" class="pSubmit-Author"></td>
			</tr>
			<tr>
				<td>��һ���ߣ�<input id="pAuthor1" class="pSubmit-Author"></td>
			</tr>
			<tr>
				<td>�ڶ����ߣ�<input id="pAuthor2" class="pSubmit-Author"></td>
			</tr>
			<tr>
				<td>�������ߣ�<input id="pAuthor3" class="pSubmit-Author"></td>
			</tr>
			<tr>
				<td><input type="hidden" id="uri" value="${user.uri}"><input type="button" value="�ύ" onclick="pSubmit();"></td>
			</tr>
		</table>
	</form>
</body>
</html>