<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0 maximum-scale=1">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.css">
<link rel="stylesheet" type="text/css" href="css/admin.css">
<body>
	<div class="logInBox" align="center">
		<h1 class="title">Welcome to the admin page, please log in</h1>
		<form action="/zadaca3/login" method="post"
			class="ui large form form-margin">
			<div>
				<div class="field vote-btn">
					<div class="ui left icon input">
						<i class="user icon"></i> <input type="text" name="email"
							placeholder="E-mail address or user name">
					</div>
				</div>

				<div class="field vote-btn">
					<div class="ui left icon input">
						<i class="lock icon"></i> <input type="password" name="password"
							placeholder="Enter your Password">
					</div>
				</div>
				<button class="btn vote-btn" type="submit">Log in</button>
			</div>
		</form>
		<h1 class="errorCSS" id="error">${error}</h1>
	</div>
</body>
</html>