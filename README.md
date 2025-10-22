# Spring Boot JWT Example

**Type:** Example Backend · **Tech Stack:** Java, Spring Boot · **Status:** Complete

## **Overview**

This is a reference implementation for using JSON Web Tokens (JWTs) with a Spring Boot application. It currently contains a simple system which will send a JTW upon login.

## **Features**

* **Example Routes:** With the Auth routes, there are additional routes in separate classes that will also compile to make referencing as general as possible.
* **Exclusions:** Some routes do not need JWT verification, and this example shows how to do that.
* **Security Classes:** Classes contain manual examples of JWT encoding and decoding.

## **Purpose**

This was made as a reference for Spring Boot applications and servers for projects. Having a simple project to reference to avoid having to look around online for implementations is part of my learning process, and having this handy will make it easier to optimise my workflows if a Java backend is ever needed.

## **Future Plans**

I will slowly add more and more to this. Ideally in the future, an automated example of a refresh token may be implemented, with access tokens having a much shorter lifespan. No frontend has been provided to work with this, however it is perfectly testable on Postman. A frontend may be made in the future, even if it is basic HTML.
