<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>提交论文</title>
<link rel="stylesheet" type="text/css" href="style.css" />
<script src="js/jquery-2.1.0.js"></script>
<script type="text/javascript">
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
			keyword2:kywrd2.value
			})
	}

function openUpload(functionId,fileType,maxSize,callback){  
    var url = root+"/paperSubmit.html?method=goFileUpload&";  
    if(functionId!=null){  
        url = url + "functionId="+functionId+"&";  
    }  
    if(fileType!=null){  
        url = url + "fileType="+fileType+"&";  
    }  
    if(maxSize!=null){  
        url = url + "maxSize="+maxSize;  
    }  
    var win = window.showModalDialog(url,"","dialogWidth:300px;dialogHeight:150px;scroll:no;status:no");  
    if(win != null){  
        var arrWin = win.split(",");  
        callback(arrWin[0],arrWin[1],arrWin[2]);  
    }  
}  

function openUpload_(){  
    openUpload(null,'JPG,GIF,JPEG,PNG','5',callback);  
}  

function callback(realName,saveName,maxSize){  
    $("#photo_").val(saveName);  
}  
</script>
</head>
<body>
	<form id="paperForm">
		<table>
			<tr>
				<td>标题　　：<input id="pTitle" type="text" class="pSubmit-Title"></td>
			</tr>
			<tr>
				<td>投稿会议：<input id="pConf" type="text" class="pSubmit-Title"></td>
			</tr>
			<tr>
				<td>概要　　：<textarea id="pAbstract" rows="5"
						class="pSubmit-Abstract"></textarea></td>
			</tr>
			<tr>
				<td>关键词1 ：<input id="pKeyword0" type="text"
					class="pSubmit-Title"></td>
			</tr>
			<tr>
				<td>关键词2 ：<input id="pKeyword1" type="text"
					class="pSubmit-Title"></td>
			</tr>
			<tr>
				<td>关键词3 ：<input id="pKeyword2" type="text"
					class="pSubmit-Title"></td>
			</tr>
			<tr>
				<td>通讯作者：<input id="pAuthor0" class="pSubmit-Author"></td>
			</tr>
			<tr>
				<td>第一作者：<input id="pAuthor1" class="pSubmit-Author"></td>
			</tr>
			<tr>
				<td>第二作者：<input id="pAuthor2" class="pSubmit-Author"></td>
			</tr>
			<tr>
				<td>第三作者：<input id="pAuthor3" class="pSubmit-Author"></td>
			</tr>
			<tr>
				<td>论文上传：<input type="hidden" name="photo" id="photo_"></input><input type="button" onclick="openUpload_()" value="上传"/></td>
			</tr>
			<tr>
				<td><input type="hidden" id="uri" value="${user.uri}"><input type="button" value="提交" onclick="pSubmit();"></td>
			</tr>
		</table>
	</form>
</body>
</html>