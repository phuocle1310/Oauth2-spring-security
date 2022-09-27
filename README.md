# Oauth2-spring-security
step 1: Run 2 project
step 2: In authorization server
 - get the code in the redirect url - GET: http://localhost:8080/oauth2/authorize?response_type=code&client_id=client-id&scope=openid&redirect_uri=http://127.0.0.1:3000/authorized
 - get token with received code using this url - POST: http://localhost:8080/oauth2/token?client_id=client-id&redirect_uri=http://127.0.0.1:3000/authorized&code=<your-code>&grant_type=authorization_code (you can add parameter <code-challenge>)
 step 3: In resource server
 - use token to get resource data.
