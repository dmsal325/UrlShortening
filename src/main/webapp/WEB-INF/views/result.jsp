<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Eunmi's Url Shortening</title>
<%@ page import="java.net.URLEncoder"%>

<script type="text/javascript">
	function CopyToClipboard(tagToCopy, textarea) {

		textarea.parentNode.style.display = "block";

		var textToClipboard = "";
		if ("value" in tagToCopy) {
			textToClipboard = tagToCopy.value;
		} else {
			textToClipboard = ("innerText" in tagToCopy) ? tagToCopy.innerText
					: tagToCopy.textContent;
		}

		var success = false;

		if (window.clipboardData) {
			window.clipboardData.setData("Text", textToClipboard);
			success = true;
		} else {
			textarea.value = textToClipboard;

			var rangeToSelect = document.createRange();

			rangeToSelect.selectNodeContents(textarea);

			var selection = window.getSelection();
			selection.removeAllRanges();
			selection.addRange(rangeToSelect);

			success = true;

			try {
				if (window.netscape
						&& (netscape.security && netscape.security.PrivilegeManager)) {
					netscape.security.PrivilegeManager
							.enablePrivilege("UniversalXPConnect");
				}

				textarea.select();
				success = document.execCommand("copy", false, null);
			} catch (error) {
				success = false;
			}
		}

		textarea.parentNode.style.display = "none";
		textarea.value = "";

		if (success) {
			alert(' 클립보드에 복사되었습니다. \n "Ctrl+v"를 사용하여 원하는 곳에 붙여넣기 하세요. ');
		} else {
			alert(" 복사하지 못했습니다. ");
		}

		/* 
		if ( success ){    alert( ' The texts were copied to clipboard. \n\n Paste it, using "Ctrl + v". \n ' );    } 
		else {    alert( " It was failed to copy. \n " );    } 
		 */
	}
</script>
<style>
.layer {
	position: absolute;
	top: 200;
	left: 0;
	width: 100%;
	height: 100%;
	text-align: center
}
</style>
</head>
<body>

	<div class="layer">
<br><br><br><br><br>
		<h2>▼ URL을 클릭하시면 복사됩니다 ▼</h2>
<br>
		<div style="display: none;">
			<textarea id="myClipboard"></textarea>
		</div>
		<h1 onclick="CopyToClipboard( this, myClipboard )" type="text"
			value=${shortAddress }/>
		${shortAddress}

	</div>
</body>
</html>