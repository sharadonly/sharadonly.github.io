<?php
	session_start();
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<?php

	if(isset($_POST['submit']) && !isset($_SESSION['management']))
	{
	$user_name = $_POST['userName'];
	$password = $_POST['password'];
	$DBConnect = mysql_connect("localhost","root","4apollo") Or die("<p>Unable to connect to the database server.</p>"
		."<p>Error code " . mysql_errno($DBConnect)
		. ": ". mysql_error($DBconnect). "</p>") ;
	
	if(!$DBConnect)
		echo "<p>Cannot log on. Cannot log in at the moment. Try again later!</p>";
	else
	{
		$database = mysql_select_db("tunnel", $DBConnect)
		Or die("<p>Unable to execute the query.</p>"."<p>Error code " . mysql_errno($DBConnect)
		. ": ". mysql_error($DBconnect) . "</p>") ;
		//tablename users with fields user_name & password
		$SQLstring = "SELECT password FROM users where user_name ='$user_name'";
		
		$QueryResult = mysql_query($SQLstring) 
		Or die("<p>Unable to execute the query.</p>"."<p>Error code " . mysql_errno($DBConnect)
		. ": ". mysql_error($DBconnect) . "</p>") ;
		$temp = mysql_fetch_row($QueryResult);
		$msg ='';
		$loggedIn = false;
		if (empty($temp[0]))
			$msg ='Incorrect user name';
		else if($temp[0] == $password)
		{
			$msg = "login successful";
			$loggedIn = true;
		}
		else
			$msg = "Incorrect password";
			
		if($loggedIn == true)
		{		
		$_SESSION['management']=$user_name;
		}
	}
	}
	if($_GET['out']=='true')
	{
		$_SESSION = array();
		session_destroy();
		$loggedIn = false;
	}
?>	
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Sign In</title>
<style type="text/css">
<!--
#Layer1 {
	position:absolute;
	width:213px;
	height:198px;
	z-index:1;
	left: 17px;
	top: 24px;
	border: #000000 inset;
	background-color: #FFCC99;
	background-image: url(../main_images/sign%20in%20photo.png);
}
body {
	background-color: #FFCC00;
	background-image: url(../main_images/sign%20in%20background%20copy.png);
}
#Layer2 {
	position:absolute;
	border: #000000 inset;
	width:503px;
	height:279px;
	z-index:2;
	left: 263px;
	top: 18px;
	background-image: url(../main_images/sign%20in%20photo%202.png);
}
.style1 {
	color: #FF0000;
	font-weight: bold;
}
-->
</style>
</head>

<body>
<div id="Layer1">
  <p class="style1">Sign in here Please </p>
  <p>
    <?php
  echo '<p style="color: #FF6600">'.$msg.'</p>';
  if(!isset($_SESSION['management']))
  {
  echo '<form id="form1" name="signIn" method="post" action="sign in.php">
    <p><strong>User Name:</strong>
      <input name="userName" type="text" id="userName" tabindex="1" />
    </p>
    <p><strong>Password:</strong><br />
      <input name="password" type="password" id="password" tabindex="2" />
    </p>
    <p align="center">
      <input type="submit" name="submit" value="submit" tabindex="3" />
    </p>
  </form>';
  }
  else
  	echo '<center><a href="sign in.php?out=true">Log off</a></center>';
  ?>
</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
</div>
<div id="Layer2">
  <p><strong>After signing in, you would be able to: </strong></p>
  <ul>
    <li>View and manage </li>
    <li>Allow submitted comments to be seen by other viewers.</li>
    <li>View all information. </li>
  </ul>
  <?php
  	if(isset($_SESSION['management']))
		echo '<a href="Management.html">Click here to continue</a>';
  ?>
  <p>&nbsp;</p>
</div>
</body>
</html>
