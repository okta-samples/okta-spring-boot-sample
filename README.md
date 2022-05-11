# Okta Spring Boot Sample

This example shows you how to use the [Okta Spring Boot Starter][] to login a user. The login is achieved through the [Authorization Code Flow][] where the user is redirected to the Okta-Hosted login page. After the user authenticates, they are redirected back to the application and a local cookie session is created.

It also shows how to return the user's information from an API using an OAuth 2.0 access token.

## Prerequisites

Before running this sample, you will need the following:

* [Java 11+](https://sdkman.io/jdks)
* [The Okta CLI Tool](https://github.com/okta/okta-cli/#installation)
* An Okta Developer Account, create one using `okta register`, or configure an existing one with `okta login`

## Get the Code

Grab and configure this project using `okta start spring-boot`.

> **NOTE**: If you'd like to use WebFlux instead of Spring MVC, use `okta start spring-boot --branch webflux`.

You can also clone this repo and run `okta start` in it.

```bash
git clone https://github.com/okta-samples/okta-spring-boot-sample.git
cd okta-spring-boot-sample
okta start
```

## Run the Example

```bash
./mvnw spring-boot:run
```

Log in at `http://localhost:8080`. 

## API Access with OAuth 2.0

You can also retrieve user information from the `/hello` endpoint with an OAuth 2.0 access token.

First, you'll need to generate an access token.

1. Run `okta apps create spa`. Set `oidcdebugger` as an app name and press **Enter**.

2. Use `https://oidcdebugger.com/debug` for the Redirect URI and set the Logout Redirect URI to `https://oidcdebugger.com`.

3. Navigate to the [OpenID Connect Debugger website](https://oidcdebugger.com/).

    1. Fill in your client ID 
    2. Use `https://{yourOktaDomain}/oauth2/default/v1/authorize` for the Authorize URI
    3. Select **code** for the response type and **Use PKCE**
    4. Click **Send Request** to continue

4. Set the access token as a `TOKEN` environment variable in a terminal window.

       TOKEN=eyJraWQiOiJYa2pXdjMzTDRBYU1ZSzNGM...

5. Test the API with [HTTPie](https://httpie.io/cli) and an access token.

       http :8080/hello Authorization:"Bearer $TOKEN"

## Learn More

For more details on how to build an application with Okta and Spring Boot / Spring Security you can read [A Quick Guide to Spring Boot Login Options](https://developer.okta.com/blog/2019/05/15/spring-boot-login-options).

[Okta Spring Boot Starter]: https://github.com/okta/okta-spring-boot
[OIDC Web Application Setup Instructions]: https://developer.okta.com/docs/guides/implement-grant-type/authcode/main/#1-setting-up-your-application
[Authorization Code Flow]: https://developer.okta.com/docs/guides/implement-grant-type/authcode/main/
