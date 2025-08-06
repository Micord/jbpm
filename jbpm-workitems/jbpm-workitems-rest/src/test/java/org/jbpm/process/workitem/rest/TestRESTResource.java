/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jbpm.process.workitem.rest;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.stream.Collectors;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.CookieParam;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/test")
public class TestRESTResource {

    @GET
    @Produces("text/plain")
    public String get(@QueryParam("param") String param) {

        return "Hello from REST" + (param != null ? " " + param : "");
    }

    @GET
    @Path("/charset")
    @Produces("text/plain")
    public Response getPlainText(@HeaderParam("Accept-Charset") String acceptCharset) {
        String entity = "Š"; // utf string
        Charset charset = Charset.forName(acceptCharset);

        Response myResponse = Response.status(Status.OK)
                .entity(entity.getBytes(charset))
                .build();
        return myResponse;
    }

    @POST
    @Path("/{name}")
    @Consumes("text/plain")
    @Produces("text/plain")
    public String post(@PathParam("name") String name) {

        return "Created resource with name " + name;
    }

    @POST
    @Path("/xml")
    @Consumes("application/xml")
    @Produces("application/xml")
    public Person postXml(Person person) {
        person.setName("Post " + person.getName());
        return person;
    }

    @PUT
    @Path("/xml")
    @Consumes("application/xml")
    @Produces("application/xml")
    public Person putXml(Person person) {
        person.setName("Put " + person.getName());
        return person;
    }

    @PATCH
    @Path("/xml")
    @Consumes("application/xml")
    @Produces(MediaType.TEXT_PLAIN)
    public Response patchXml(Person person) {
        person.setName("Patch " + person.getName());
        return Response.status(Status.NO_CONTENT).build();
    }

    @PUT
    @Path("/xml-charset")
    @Consumes("application/xml")
    @Produces("application/xml; charset=UTF-8")
    public Person putXmlWithCharset(Person person) {
        person.setName("Put " + person.getName());
        return person;
    }

    @DELETE
    @Path("/xml/{name}")
    @Produces("application/xml")
    public Person delete(@PathParam("name") String name) {
        Person person = new Person();
        person.setAge(-1);
        person.setName("deleted " + name);
        return person;
    }

    @GET
    @Path("/xml")
    @Produces("application/xml")
    public Person getXml() {
        Person person = new Person();
        person.setName("Person Xml");
        return person;
    }

    @GET
    @Path("/json")
    @Produces("application/json")
    public String getJson() {

        return "{\"name\":\"Person Json\"}";
    }

    @GET
    @Path("/xml-charset")
    @Produces("application/xml; charset=utf-8")
    public Person getXmlWithCharset() {
        Person person = new Person();
        person.setName("Person Xml");
        return person;
    }

    @GET
    @Path("/json-charset")
    @Produces("application/json; charset=utf-8")
    public String getJsonWithCharset() {

        return "{\"name\":\"Person Json\"}";
    }

    @GET
    @Path("/header/{headerKey}")
    @Produces("text/plain")
    public String getHeaderValue(@PathParam("headerKey") String headerKey,
    		@Context HttpHeaders headers) {
    	return headers.getRequestHeaders().getOrDefault(headerKey, Collections.emptyList())
    			.stream().collect(Collectors.joining(","));
    }

    @GET
    @Path("/testSingleCookie")
    @Produces("application/xml")
    public String getSingleCookie(@CookieParam("cookieParam1") Cookie cookieParam1) {

        return cookieParam1.getName() + "=" + cookieParam1.getValue();
    }

	@GET
	@Path("/testMultipleCookie")
	@Produces("application/xml")
	public String getMultipleCookie(@CookieParam("cookieParam1") Cookie cookieParam1,
        	                        @CookieParam("cookieParam2") Cookie cookieParam2) {
		return cookieParam1.getName() + "=" + cookieParam1.getValue() + ";" + cookieParam2.getName() + "="
				+ cookieParam2.getValue();
	}

}
