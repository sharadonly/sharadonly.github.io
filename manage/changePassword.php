<?php
	session_start();
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Department of Computer Science</title>
<style type="text/css">
<!--
body {
	background-image: url(../main_images/sign%20in%20background.png);
}
#Layer1 {	position:absolute;
	border: inset #333333;
	width:497px;
	height:276px;
	z-index:1;
	left: 170px;
	top: 18px;
	background-image: url(../main_images/sign%20in%20photo%202.png);
}
-->
</style>
<?php

	if(!isset($_SESSION['management']))
	{
			echo '<script language="javascript">
					alert("YOU MUST SIGN IN TO VIEW THIS PAGE");
					window.location.assign("sign in.php");
			  </script>';	
	}
?>	</head>

<body>
<div id="Layer1">
  <h2><strong>Change Password: </strong></h2>
    <?php
  echo '<p style="color: #FF6600">'.$msg.'</p>';
  if(isset($_SESSION['management']))
  {
  echo '<form id="password" name="password" method="post" action="managePassword.php">
    <p><strong>Old Password:</strong>&nbsp;&nbsp; &nbsp; &nbsp; <input name="password" type="password" id="password" tabindex="2" />
    </p>
    <p><strong>New Password:</strong>&nbsp; &nbsp; &nbsp; <input name="new_password" type="password" id="password" tabindex="2" />
    </p>
    <p><strong>Confirm Password:</strong><input name="confirm_password" type="password" id="password" tabindex="2" />
    </p>
    <p align="center">
      <input type="submit" name="submit" value="Change Password" tabindex="3" />
    </p>
  </form>';
  }
  
  ?>
</div>
</body>
</html>
