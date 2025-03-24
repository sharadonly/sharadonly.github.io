<?php
use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\Exception;

require '/Users/xingqian/Desktop/spark.github.io/PHPMailer/src/PHPMailer.php';
require '/Users/xingqian/Desktop/spark.github.io/PHPMailer/src/SMTP.php';
require '/Users/xingqian/Desktop/spark.github.io/PHPMailer/src/Exception.php';

// Set JSON response header
header('Content-Type: application/json');

// if ($_SERVER["REQUEST_METHOD"] == "POST") {
//     $name = strip_tags(trim($_POST["name"]));
//     $email = filter_var(trim($_POST["email"]), FILTER_SANITIZE_EMAIL);
//     $subject = strip_tags(trim($_POST["subject"]));
//     $message = trim($_POST["message"]);

//     if (empty($name) || empty($email) || empty($subject) || empty($message) || !filter_var($email, FILTER_VALIDATE_EMAIL)) {
//         echo json_encode(["status" => "error", "message" => "Invalid form submission"]);
//         exit;
//     }

//     $mail = new PHPMailer(true);

//     try {
//         // SMTP Configuration
//         $mail->isSMTP();
//         $mail->Host = 'smtp.gmail.com';
//         $mail->SMTPAuth = true;
//         $mail->Username = 'xingq98@gmail.com';
//         $mail->Password = 'xxxxxxx';
//         $mail->SMTPSecure = PHPMailer::ENCRYPTION_STARTTLS;
//         $mail->Port = 587;

//         $mail->setFrom('xingq98@gmail.com', 'Your Name');
//         $mail->addAddress('xqian7@stevens.edu'); // Send to yourself

//         $mail->Subject = "Contact Form: $subject";
//         $mail->Body = "You received a message from $name ($email):\n\n$message";

//         $mail->send();
//         echo json_encode(["status" => "success", "message" => "Email sent successfully"]);
//     } catch (Exception $e) {
//         echo json_encode(["status" => "error", "message" => "Mailer Error: " . $mail->ErrorInfo]);
//     }
// }
?>