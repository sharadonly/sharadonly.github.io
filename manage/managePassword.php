<?php
    session_start();
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Department of Computer Science</title>
</head>
<body>
<?php
if(!isset($_SESSION['management']))
{
		    echo '<script language="javascript">
			       aleert("YOU MUST SIGN IN TO VIEW THIS PAGE");
				   window.location.assign("sign in.php");
				   </script>';
}
else
{
		$DBConnect = mysql_connect("localhost","root","4apollo") Or die("<p>Unable to connect to the database server.</p>"
		."<p>Error code " . mysql_errno($DBConnect)	. ": ". mysql_error($DBconnect). "</p>") ;
		if(!$DBConnect)
			echo "<p>Cannot log on. Cannot log in at the moment. Try again later!</p>";
		else
		{
		$errors = 0;
		$errorString = "<ul style=\"color:red\">";
	 	$database = mysql_select_db("tunnel", $DBConnect)
		Or die("<p>Unable to execute the query.</p>"."<p>Error code " . mysql_errno($DBConnect)
		. ": ". mysql_error($DBconnect) . "</p>") ;
         if(!isset($_POST['password']))
		 {
		 	$errors++;
			$errorString += "<li>You must enter the previous password</li>";
		 }
		 else
		 {
		 	$username = $_SESSION['management'];
		 	$SQLstring = "select * from users where user_name = '$username'";
			$QueryResult = mysql_query($SQLstring) 
			Or die("<p>Unable to execute the query.</p>"."<p>Error code " . mysql_errno($DBConnect)
			. ": ". mysql_error($DBConnect) . "</p>") ;
			$passwords = mysql_fetch_assoc($QueryResult);
			if($_POST['password'] != $passwords['password'])
			{
				$errors++;
				$errorString .= "<li>You entered an invalid password</li>";
			}
		 }
		 if(!isset($_POST['new_password']))
		 {
		    $errors++;
			$errorString .= "<li>You must enter a new password</li>";
		 }
		 else if(!isset($_POST['confirm_password']))
		 {
		 	$errors++;
			$errorString .= "<li>You must confirm your new password</li>";
		 }
		 else if($_POST['new_password'] != $_POST['confirm_password'])
		 {
		 	$errors++;
			$errorString .= "<li>You passwords do not match</li>";
		 }
		 $errorString .= "</ul>";
		if ($errors > 0)
		{
		echo '<center>The following errors have occured<br/>';
		echo $errorString;
		echo 'Please click your browser\'s back button and repair the previous errors<br/></center>';
		//if errors found close database connection
		mysql_free_result($QueryResult);
		mysql_close($DBConnect);
		}//errors occured end program
		else
		{
			$newPassword = $_POST['new_password'];
			$SQLstring = "update users set password = '$newPassword' where user_name = '$username'";
			$QueryResult = mysql_query($SQLstring) Or die("<p>Unable to execute thesd query.</p>"."<p>Error code " . 
			mysql_errno($DBConnect). ": ". mysql_error($DBConnect) . "</p>") ;
			echo '<center>Password has been changed successfully<br/>';
			echo 'click <a href="Management.html">here</a> to return to the management page</a><br/>';
		}		
		}//end check DBConnect
}//end outer else
?>
</body>
</html>
